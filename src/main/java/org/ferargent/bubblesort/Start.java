package org.ferargent.bubblesort;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class Start implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] args) {
        Player player = (Player) commandSender;
        World world = player.getWorld();
        Location startingLocation = player.getLocation();

        int[] array = Bubblesort.randomNumbers;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i]; j++) {
                world.getBlockAt(startingLocation.clone().add(i, j, 0)).setType(Material.DIAMOND_BLOCK);
            }
        }

        // Bubble sort

        new BukkitRunnable() {
            int i = 0;
            int j = 0;
            boolean select = true;

            @Override
            public void run() {
                if (i < array.length - 1) {
                    // Alle Säulen werden zu Diamantblöcken geändert
                    for (int k = 0; k < array.length; k++) {
                        for (int l = 0; l < array[k]; l++) {
                            world.getBlockAt(startingLocation.clone().add(k, l, 0)).setType(Material.DIAMOND_BLOCK);
                        }
                    }
                    if (j != array.length - 1) {
                        // Die linke der ausgewählten Säulen wird zu Redstone-Blöcken geändert
                        for (int k = 0; k < array[j]; k++) {
                            world.getBlockAt(startingLocation.clone().add(j, k, 0)).setType(Material.REDSTONE_BLOCK);
                        }
                        // Die rechte der ausgewählten Säulen wird zu Redstone-Blöcken geändert
                        for (int k = 0; k < array[j + 1]; k++) {
                            world.getBlockAt(startingLocation.clone().add(j + 1, k, 0)).setType(Material.REDSTONE_BLOCK);
                        }
                    }
                    // Wenn das Array noch nicht vollständig durchlaufen worden ist, ...
                    if (j < array.length - i - 1) {
                        // Wenn die linke Säule höher ist als die rechte, ...
                        if (array[j] > array[j + 1]) {

                            for (int k = 0; k < array[j]; k++) {
                                world.getBlockAt(startingLocation.clone().add(j, k, 0)).setType(Material.AIR);
                            }
                            for (int k = 0; k < array[j + 1]; k++) {
                                world.getBlockAt(startingLocation.clone().add(j + 1, k, 0)).setType(Material.AIR);
                            }
                            int temp = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = temp;
                            if (!(j == array.length - 1)) {
                                for (int k = 0; k < array[j + 1]; k++) {
                                    world.getBlockAt(startingLocation.clone().add(j + 1, k, 0)).setType(Material.REDSTONE_BLOCK);
                                }

                                for (int k = 0; k < array[j]; k++) {
                                    world.getBlockAt(startingLocation.clone().add(j, k, 0)).setType(Material.REDSTONE_BLOCK);
                                }
                            }
                        }
                        if (select) {
                            select = false;
                        } else {
                            j++;
                            select = true;
                        }

                    } else {
                        if (select) {
                            select = false;
                        } else {
                            j = 0;
                            i++;
                            select = true;
                        }
                    }
                } else {
                    // Fertig sortiert
                    for (int k = 0; k < array.length; k++) {
                        for (int l = 0; l < array[k]; l++) {
                            world.getBlockAt(startingLocation.clone().add(k, l, 0)).setType(Material.DIAMOND_BLOCK);
                        }
                    }
                    this.cancel();
                }
            }
        }.runTaskTimer(Bubblesort.getPlugin(Bubblesort.class), 20L, 20L);

        return false;
    }
}
