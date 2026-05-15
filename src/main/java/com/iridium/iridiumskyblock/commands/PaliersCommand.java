package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.User;
import com.iridium.iridiumskyblock.gui.PaliersGUI;
import com.iridium.iridiumteams.IridiumTeams;
import com.iridium.iridiumteams.commands.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class PaliersCommand extends Command<Island, User> {

    public PaliersCommand() {
        super(Collections.singletonList("paliers"), "View island paliers", "%prefix% &7/is paliers", "", 0);
    }

    @Override
    public boolean execute(User user, Island island, String[] args, IridiumTeams<Island, User> iridiumTeams) {
        Player player = user.getPlayer();
        player.openInventory(new PaliersGUI(island, player).getInventory());
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] args, IridiumTeams<Island, User> iridiumTeams) {
        return Collections.emptyList();
    }
}
