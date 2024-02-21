package org.ferargent.bubblesort;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] args) {
        Player player = (Player) commandSender;
        World world = player.getWorld();
        for (int i = 0; i < Bubblesort.randomNumbers.length; i++) {
            for (int j = 0; j < Bubblesort.randomNumbers[i]; j++) {
                world.getBlockAt(player.getLocation().add(i, j, 0)).setType(org.bukkit.Material.DIAMOND_BLOCK);
            }
        }
        return false;
    }
}
