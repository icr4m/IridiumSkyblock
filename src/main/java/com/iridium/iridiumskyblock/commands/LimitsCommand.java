package com.iridium.iridiumskyblock.commands;

import com.cryptomorin.xseries.XMaterial;
import com.iridium.iridiumcore.utils.StringUtils;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.configs.BlockLimits;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.IslandPalier;
import com.iridium.iridiumskyblock.database.User;
import com.iridium.iridiumteams.IridiumTeams;
import com.iridium.iridiumteams.commands.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class LimitsCommand extends Command<Island, User> {

    public LimitsCommand() {
        super(Collections.singletonList("limits"), "Voir les limites de blocs de l'île", "%prefix% &7/is limits", "", 0);
    }

    @Override
    public boolean execute(User user, Island island, String[] args, IridiumTeams<Island, User> iridiumTeams) {
        Player player = user.getPlayer();
        String prefix = IridiumSkyblock.getInstance().getConfiguration().prefix;
        BlockLimits blockLimits = IridiumSkyblock.getInstance().getBlockLimits();

        List<IslandPalier> claimed = IridiumSkyblock.getInstance().getDatabaseManager()
                .getIslandPalierTableManager()
                .getEntries(p -> p.getIslandId() == island.getId());

        // Collecter tous les blocs qui ont une limite (defaults + paliers réclamés)
        Set<String> allLimitedBlocks = new LinkedHashSet<>(blockLimits.defaultLimits.keySet());
        for (IslandPalier p : claimed) {
            Map<String, Integer> palierLimitMap = blockLimits.palierLimits.get(p.getPalierID());
            if (palierLimitMap != null) allLimitedBlocks.addAll(palierLimitMap.keySet());
        }

        if (allLimitedBlocks.isEmpty()) {
            player.sendMessage(StringUtils.color(prefix + " &7Aucune limite de blocs configurée."));
            return true;
        }

        player.sendMessage(StringUtils.color(prefix + " &9&lLimites de blocs &8— &7" + island.getName()));

        for (String materialName : allLimitedBlocks) {
            Optional<XMaterial> xMaterialOpt = XMaterial.matchXMaterial(materialName);
            if (!xMaterialOpt.isPresent()) continue;

            XMaterial material = xMaterialOpt.get();
            int limit = getEffectiveLimit(island, material, blockLimits, claimed);
            int current = IridiumSkyblock.getInstance().getIslandManager().getTeamBlock(island, material).getAmount();

            String line;
            if (limit < 0) {
                line = "  &7" + materialName + " &8: &a" + current + " &7/ &a∞";
            } else {
                String color = current >= limit ? "&c" : (current >= limit * 0.75 ? "&e" : "&a");
                line = "  &7" + materialName + " &8: " + color + current + " &7/ &f" + limit;
            }

            player.sendMessage(StringUtils.color(line));
        }

        return true;
    }

    private int getEffectiveLimit(Island island, XMaterial material, BlockLimits blockLimits, List<IslandPalier> claimed) {
        String materialName = material.name();

        List<Integer> claimedIds = new ArrayList<>();
        for (IslandPalier p : claimed) claimedIds.add(p.getPalierID());
        claimedIds.sort(Collections.reverseOrder());

        for (int palierID : claimedIds) {
            Map<String, Integer> palierLimitMap = blockLimits.palierLimits.get(palierID);
            if (palierLimitMap != null && palierLimitMap.containsKey(materialName)) {
                return palierLimitMap.get(materialName);
            }
        }

        if (blockLimits.defaultLimits.containsKey(materialName)) {
            return blockLimits.defaultLimits.get(materialName);
        }

        return -1;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] args, IridiumTeams<Island, User> iridiumTeams) {
        return Collections.emptyList();
    }
}
