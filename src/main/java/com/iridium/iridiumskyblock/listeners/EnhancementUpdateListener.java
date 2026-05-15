package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumcore.utils.StringUtils;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.User;
import com.iridium.iridiumteams.api.EnhancementUpdateEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EnhancementUpdateListener implements Listener {

    private static final Set<String> PALIER_ONLY = new HashSet<>(Arrays.asList("cropBoost", "size"));

    @EventHandler
    public void onEnhancementUpdateEvent(EnhancementUpdateEvent<Island, User> event) {
        if (PALIER_ONLY.contains(event.getEnhancement())) {
            event.setCancelled(true);
            User user = event.getUser();
            if (user != null) {
                Player player = user.getPlayer();
                if (player != null) {
                    player.sendMessage(StringUtils.color(
                            IridiumSkyblock.getInstance().getConfiguration().prefix +
                            " &7Cet upgrade s'obtient uniquement via &9/is paliers&7."
                    ));
                }
            }
            return;
        }

        if (event.getEnhancement().equals("size")) {
            Bukkit.getScheduler().runTask(IridiumSkyblock.getInstance(), () ->
                    IridiumSkyblock.getInstance().getTeamManager().getMembersOnIsland(event.getTeam())
                            .forEach(user -> IridiumSkyblock.getInstance().getTeamManager().sendIslandBorder(user.getPlayer()))
            );
        }
    }

}
