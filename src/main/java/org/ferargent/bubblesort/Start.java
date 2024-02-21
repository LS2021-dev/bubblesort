package org.ferargent.bubblesort;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class Start implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] args) {
        Player player = (Player) commandSender;
        World world = player.getWorld();
        Location startingLocation = player.getLocation();

        int[] array = Bubblesort.randomNumbers;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i]; j++) {
                world.getBlockAt(startingLocation.clone().add(i, j, 0)).setType(org.bukkit.Material.DIAMOND_BLOCK);
            }
        }

        // Bubble sort

        return false;
    }

}
