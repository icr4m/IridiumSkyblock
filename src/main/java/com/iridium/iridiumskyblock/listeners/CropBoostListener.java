package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.enhancements.CropBoostEnhancementData;
import com.iridium.iridiumteams.database.TeamEnhancement;
import com.iridium.iridiumteams.enhancements.Enhancement;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;

import java.util.Optional;
import java.util.Random;

public class CropBoostListener implements Listener {

    private final Random random = new Random();

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onBlockGrow(BlockGrowEvent event) {
        if (!(event.getNewState().getBlockData() instanceof Ageable)) return;

        Optional<Island> islandOpt = IridiumSkyblock.getInstance().getTeamManager()
                .getTeamViaLocation(event.getBlock().getLocation());
        if (!islandOpt.isPresent()) return;

        TeamEnhancement teamEnhancement = IridiumSkyblock.getInstance().getTeamManager()
                .getTeamEnhancement(islandOpt.get(), "cropBoost");
        if (teamEnhancement.getLevel() <= 0) return;

        Enhancement<CropBoostEnhancementData> enhancement = IridiumSkyblock.getInstance()
                .getEnhancements().cropBoostEnhancement;
        CropBoostEnhancementData data = enhancement.levels.get(teamEnhancement.getLevel() - 1);
        if (data == null) return;

        // Pour 25%: 25% de chance d'avancer 1 stade de plus
        // Pour 100%: toujours avancer 1 stade de plus (vitesse x2)
        double boost = data.cropBoostPercentage;
        int extraStages = (int) boost;
        if (random.nextDouble() < (boost - extraStages)) extraStages++;

        if (extraStages <= 0) return;

        Ageable newAgeable = (Ageable) event.getNewState().getBlockData();
        int newAge = Math.min(newAgeable.getAge() + extraStages, newAgeable.getMaximumAge());
        newAgeable.setAge(newAge);
        event.getNewState().setBlockData(newAgeable);
    }
}
