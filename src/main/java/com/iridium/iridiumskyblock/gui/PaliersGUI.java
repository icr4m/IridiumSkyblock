package com.iridium.iridiumskyblock.gui;

import com.iridium.iridiumcore.gui.BackGUI;
import com.iridium.iridiumcore.utils.StringUtils;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.configs.Paliers;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.IslandPalier;
import com.iridium.iridiumteams.database.TeamEnhancement;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaliersGUI extends BackGUI {

    private final Island island;

    public PaliersGUI(Island island, Player player) {
        super(IridiumSkyblock.getInstance().getPaliers().gui.background, player, IridiumSkyblock.getInstance().getInventories().backButton);
        this.island = island;
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        Paliers paliers = IridiumSkyblock.getInstance().getPaliers();
        Inventory inventory = Bukkit.createInventory(this, paliers.gui.size, StringUtils.color(paliers.gui.title));
        addContent(inventory);
        return inventory;
    }

    @Override
    public void addContent(Inventory inventory) {
        super.addContent(inventory);

        double islandValue = island.getValue();
        List<IslandPalier> claimedPaliers = IridiumSkyblock.getInstance().getDatabaseManager()
                .getIslandPalierTableManager()
                .getEntries(p -> p.getIslandId() == island.getId());

        for (Paliers.PalierConfig palierConfig : IridiumSkyblock.getInstance().getPaliers().paliers) {
            boolean claimed = claimedPaliers.stream().anyMatch(p -> p.getPalierID() == palierConfig.getId());
            boolean unlocked = islandValue >= palierConfig.getRequiredValue();

            Material glassMaterial;
            if (claimed) {
                glassMaterial = Material.GREEN_STAINED_GLASS_PANE;
            } else if (unlocked) {
                glassMaterial = Material.PURPLE_STAINED_GLASS_PANE;
            } else {
                glassMaterial = Material.RED_STAINED_GLASS_PANE;
            }

            ItemStack itemStack = new ItemStack(glassMaterial);
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName(StringUtils.color(palierConfig.getName()));

            List<String> lore = palierConfig.getLore() != null ? new ArrayList<>(palierConfig.getLore()) : new ArrayList<>();
            lore.replaceAll(line -> StringUtils.color(line.replace("%required_value%",
                    String.valueOf((long) palierConfig.getRequiredValue()))));

            lore.add("");
            if (claimed) {
                lore.add(StringUtils.color(IridiumSkyblock.getInstance().getPaliers().claimedLore));
            } else if (unlocked) {
                lore.add(StringUtils.color(IridiumSkyblock.getInstance().getPaliers().claimableLore));
            } else {
                lore.add(StringUtils.color(IridiumSkyblock.getInstance().getPaliers().lockedLore
                        .replace("%required_value%", String.valueOf((long) palierConfig.getRequiredValue()))));
            }

            meta.setLore(lore);
            itemStack.setItemMeta(meta);
            inventory.setItem(palierConfig.getSlot(), itemStack);
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        super.onInventoryClick(event);

        Player player = (Player) event.getWhoClicked();

        Optional<Paliers.PalierConfig> palierConfigOpt = IridiumSkyblock.getInstance().getPaliers().paliers.stream()
                .filter(p -> p.getSlot() == event.getSlot())
                .findFirst();

        if (!palierConfigOpt.isPresent()) return;

        Paliers.PalierConfig palierConfig = palierConfigOpt.get();

        List<IslandPalier> claimedPaliers = IridiumSkyblock.getInstance().getDatabaseManager()
                .getIslandPalierTableManager()
                .getEntries(p -> p.getIslandId() == island.getId());

        boolean claimed = claimedPaliers.stream().anyMatch(p -> p.getPalierID() == palierConfig.getId());
        if (claimed) return;

        if (island.getValue() < palierConfig.getRequiredValue()) {
            player.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getPaliers().notEnoughValueMessage
                    .replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
            return;
        }

        // Mark as claimed
        IslandPalier islandPalier = new IslandPalier(island, palierConfig.getId());
        IridiumSkyblock.getInstance().getDatabaseManager().getIslandPalierTableManager().addEntry(islandPalier);

        // Apply enhancement (only upgrade, never downgrade)
        TeamEnhancement teamEnhancement = IridiumSkyblock.getInstance().getTeamManager()
                .getTeamEnhancement(island, palierConfig.getEnhancement());
        if (teamEnhancement.getLevel() < palierConfig.getEnhancementLevel()) {
            teamEnhancement.setLevel(palierConfig.getEnhancementLevel());
        }

        // Trigger border refresh if size was upgraded
        if (palierConfig.getEnhancement().equals("size")) {
            Bukkit.getScheduler().runTask(IridiumSkyblock.getInstance(), () ->
                    IridiumSkyblock.getInstance().getTeamManager().getMembersOnIsland(island)
                            .forEach(u -> IridiumSkyblock.getInstance().getTeamManager().sendIslandBorder(u.getPlayer())));
        }

        // Refresh GUI
        player.openInventory(getInventory());
    }
}
