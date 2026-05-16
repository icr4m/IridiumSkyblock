package com.iridium.iridiumskyblock.listeners;

import com.cryptomorin.xseries.XMaterial;
import com.iridium.iridiumcore.utils.StringUtils;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.configs.BlockLimits;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.IslandPalier;
import com.iridium.iridiumteams.database.TeamBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.*;

public class BlockLimitListener implements Listener {

    // LOWEST pour courir avant le listener IridiumTeams qui met à jour le TeamBlock,
    // évitant ainsi un décalage de +1 sur le compteur lors de la vérification.
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getPlayer().hasPermission("iridiumskyblock.blocklimitbypass")) return;

        Optional<Island> islandOpt = IridiumSkyblock.getInstance().getIslandManager()
                .getTeamViaLocation(event.getBlock().getLocation());
        if (!islandOpt.isPresent()) return;

        Island island = islandOpt.get();
        XMaterial material = XMaterial.matchXMaterial(event.getBlock().getType());

        int limit = getEffectiveLimit(island, material);
        if (limit < 0) return;

        TeamBlock teamBlock = IridiumSkyblock.getInstance().getIslandManager().getTeamBlock(island, material);
        int current = teamBlock.getAmount();

        if (current >= limit) {
            event.setCancelled(true);
            String message = StringUtils.color(
                    IridiumSkyblock.getInstance().getBlockLimits().limitReachedMessage
                            .replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)
                            .replace("%block%", material.name())
                            .replace("%current%", String.valueOf(current))
                            .replace("%limit%", String.valueOf(limit))
            );
            event.getPlayer().sendMessage(message);
        }
    }

    private int getEffectiveLimit(Island island, XMaterial material) {
        BlockLimits blockLimits = IridiumSkyblock.getInstance().getBlockLimits();
        String materialName = material.name();

        List<IslandPalier> claimed = IridiumSkyblock.getInstance().getDatabaseManager()
                .getIslandPalierTableManager()
                .getEntries(p -> p.getIslandId() == island.getId());

        // Trier du palier le plus élevé au plus bas pour prendre la limite la plus avancée
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

        return -1; // aucune limite configurée pour ce bloc
    }
}
