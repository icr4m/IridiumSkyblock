package com.iridium.iridiumskyblock.managers;

import com.cryptomorin.xseries.XMaterial;
import com.iridium.iridiumcore.gui.GUI;
import com.iridium.iridiumcore.utils.InventoryUtils;
import com.iridium.iridiumcore.utils.ItemStackUtils;
import com.iridium.iridiumcore.utils.StringUtils;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.commands.supersecretcommands.IsMyComputerOnFire;
import com.iridium.iridiumskyblock.commands.supersecretcommands.IsThisWorking;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.User;
import com.iridium.iridiumteams.commands.BlockValueCommand;
import com.iridium.iridiumteams.commands.BoostersCommand;
import com.iridium.iridiumteams.commands.Command;
import com.iridium.iridiumteams.configs.BlockValues;
import com.iridium.iridiumteams.configs.inventories.NoItemGUI;
import com.iridium.iridiumteams.gui.BlockValuesTypeSelectorGUI;
import com.iridium.iridiumteams.gui.InventoryConfigGUI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CommandManager extends com.iridium.iridiumteams.managers.CommandManager<Island, User> {
    public CommandManager(String command) {
        super(IridiumSkyblock.getInstance(), "&9", command);
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command<Island, User> command, String[] args) {
        // Fix for iridiumteams bug: BlockValuesTypeSelectorGUI calls executeCommand with
        // args = ["blocks"/"spawners", playerName], but BlockValueCommand.execute() uses
        // args[0] ("blocks") as the island lookup key instead of args[last] (playerName).
        // We also bypass BlockValueGUI/SpawnerValueGUI entirely to avoid PagedGUI's next-tick
        // scheduled task which overwrites the synchronously placed items with filler.
        if (command instanceof BlockValueCommand
                && args.length == 2
                && sender instanceof Player
                && (args[0].equalsIgnoreCase("blocks") || args[0].equalsIgnoreCase("spawners"))) {
            Player player = (Player) sender;
            String playerName = args[1];
            Optional<Island> island = IridiumSkyblock.getInstance().getTeamManager().getTeamViaNameOrPlayer(playerName);
            if (!island.isPresent()) {
                String msg = playerName.equals(player.getName())
                        ? IridiumSkyblock.getInstance().getMessages().dontHaveTeam
                        : IridiumSkyblock.getInstance().getMessages().teamDoesntExistByName;
                player.sendMessage(StringUtils.color(msg.replace("%prefix%",
                        IridiumSkyblock.getInstance().getConfiguration().prefix)));
                return false;
            }
            switch (args[0].toLowerCase()) {
                case "blocks":
                    if (IridiumSkyblock.getInstance().getInventories().blockValuesTypeSelectorGUI.blocks.enabled) {
                        player.openInventory(buildBlockValueInventory(island.get()));
                        return true;
                    }
                    break;
                case "spawners":
                    if (IridiumSkyblock.getInstance().getInventories().blockValuesTypeSelectorGUI.spawners.enabled) {
                        player.openInventory(buildSpawnerValueInventory(island.get()));
                        return true;
                    }
                    break;
            }
            player.openInventory(new BlockValuesTypeSelectorGUI<>(playerName, player, IridiumSkyblock.getInstance()).getInventory());
            return true;
        }
        return super.executeCommand(sender, command, args);
    }

    private static class StaticGUI implements GUI {
        private Inventory inventory;

        @Override
        public Inventory getInventory() {
            return inventory;
        }

        @Override
        public void onInventoryClick(InventoryClickEvent event) {
        }

        @Override
        public void addContent(Inventory inventory) {
        }
    }

    private Inventory buildBlockValueInventory(Island island) {
        NoItemGUI guiConfig = IridiumSkyblock.getInstance().getInventories().blockValueGUI;
        BlockValues blockValues = IridiumSkyblock.getInstance().getBlockValues();

        String title = StringUtils.color(guiConfig.title
                .replace("%page%", "1")
                .replace("%max_pages%", "1"));
        StaticGUI holder = new StaticGUI();
        Inventory inventory = Bukkit.createInventory(holder, guiConfig.size, title);
        holder.inventory = inventory;
        InventoryUtils.fillInventory(inventory, guiConfig.background);

        for (Map.Entry<XMaterial, BlockValues.ValuableBlock> entry : blockValues.blockValues.entrySet()) {
            XMaterial material = entry.getKey();
            BlockValues.ValuableBlock block = entry.getValue();
            int amount = IridiumSkyblock.getInstance().getTeamManager()
                    .getTeamBlock(island, material).getAmount();

            List<String> lore = new ArrayList<>();
            lore.add(StringUtils.color(blockValues.valueLore
                    .replace("%block_value%", String.valueOf(block.value))));
            lore.add(StringUtils.color(blockValues.teamValueLore
                    .replace("%total_blocks%", String.valueOf(amount))
                    .replace("%total_block_value%", String.valueOf((double) amount * block.value))));

            ItemStack item = ItemStackUtils.makeItem(material, 1, StringUtils.color(block.name), lore);
            inventory.setItem(block.slot, item);
        }

        return inventory;
    }

    private Inventory buildSpawnerValueInventory(Island island) {
        NoItemGUI guiConfig = IridiumSkyblock.getInstance().getInventories().spawnerValueGUI;
        BlockValues blockValues = IridiumSkyblock.getInstance().getBlockValues();

        String title = StringUtils.color(guiConfig.title
                .replace("%page%", "1")
                .replace("%max_pages%", "1"));
        StaticGUI holder = new StaticGUI();
        Inventory inventory = Bukkit.createInventory(holder, guiConfig.size, title);
        holder.inventory = inventory;
        InventoryUtils.fillInventory(inventory, guiConfig.background);

        for (Map.Entry<EntityType, BlockValues.ValuableBlock> entry : blockValues.spawnerValues.entrySet()) {
            EntityType entityType = entry.getKey();
            BlockValues.ValuableBlock block = entry.getValue();
            int amount = IridiumSkyblock.getInstance().getTeamManager()
                    .getTeamSpawners(island, entityType).getAmount();

            List<String> lore = new ArrayList<>();
            lore.add(StringUtils.color(blockValues.valueLore
                    .replace("%block_value%", String.valueOf(block.value))));
            lore.add(StringUtils.color(blockValues.teamValueLore
                    .replace("%total_blocks%", String.valueOf(amount))
                    .replace("%total_block_value%", String.valueOf((double) amount * block.value))));

            String eggName = entityType.name().toUpperCase() + "_SPAWN_EGG";
            XMaterial material = XMaterial.matchXMaterial(eggName).orElse(XMaterial.SPAWNER);

            ItemStack item = ItemStackUtils.makeItem(material, 1, StringUtils.color(block.name), lore);
            inventory.setItem(block.slot, item);
        }

        return inventory;
    }

    @Override
    public void registerCommands() {
        super.registerCommands();
        getCommands().removeIf(cmd -> cmd instanceof BoostersCommand);
        registerCommand(IridiumSkyblock.getInstance().getCommands().visitCommand);
        registerCommand(IridiumSkyblock.getInstance().getCommands().borderCommand);
        registerCommand(IridiumSkyblock.getInstance().getCommands().regenCommand);
        registerCommand(IridiumSkyblock.getInstance().getCommands().biomeCommand);
        registerCommand(IridiumSkyblock.getInstance().getCommands().clearDataCommand);
        registerCommand(IridiumSkyblock.getInstance().getCommands().paliersCommand);
        registerCommand(new IsThisWorking());
        registerCommand(new IsMyComputerOnFire());
    }

    @Override
    public void noArgsDefault(@NotNull CommandSender commandSender) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            User user = IridiumSkyblock.getInstance().getUserManager().getUser(player);
            if (IridiumSkyblock.getInstance().getTeamManager().getTeamViaID(user.getTeamID()).isPresent()) {
                player.openInventory(new InventoryConfigGUI(IridiumSkyblock.getInstance().getInventories().islandMenu).getInventory());
                return;
            }
            if (IridiumSkyblock.getInstance().getConfiguration().createRequiresName) {
                Bukkit.getServer().dispatchCommand(commandSender, "is help");
                return;
            }
            Bukkit.getServer().dispatchCommand(commandSender, "is create");
        }
    }
}
