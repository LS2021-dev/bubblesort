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
            int selectedIndex = 0;
            boolean selected = true;

            @Override
            public void run() {
                // Wenn die Säulen noch nicht aufsteigend sortiert sind, ...
                if (!isSorted(array) || selectedIndex != 0) {
                    // Alle Säulen werden zu Diamantblöcken geändert
                    for (int block_x = 0; block_x < array.length; block_x++) {
                        for (int block_y = 0; block_y < array[block_x]; block_y++) {
                            world.getBlockAt(startingLocation.clone().add(block_x, block_y, 0)).setType(Material.IRON_BLOCK);
                        }
                    }
                    // Wenn nicht die letzte Säule ausgewählt ist, ...
                    if (selectedIndex != array.length - 1) {
                        // Die linke der beiden ausgewählten Säulen wird zu Redstone-Blöcken geändert
                        for (int k = 0; k < array[selectedIndex]; k++) {
                            world.getBlockAt(startingLocation.clone().add(selectedIndex, k, 0)).setType(Material.REDSTONE_BLOCK);
                        }
                        // Die rechte der beiden ausgewählten Säulen wird zu Redstone-Blöcken geändert
                        for (int k = 0; k < array[selectedIndex + 1]; k++) {
                            world.getBlockAt(startingLocation.clone().add(selectedIndex + 1, k, 0)).setType(Material.EMERALD_BLOCK);
                        }
                    }
                    // Wenn das Array noch nicht vollständig durchlaufen worden ist, ...
                    // if (j < array.length - i - 1) {
                    if (selectedIndex < array.length - 1) {
                        // Wenn die linke Säule höher ist als die rechte, ...
                        if (array[selectedIndex] > array[selectedIndex + 1]) {
                            // Wenn nicht ausgewählt wird, tausche die beiden Säulen
                            if (!selected){
                                for (int k = 0; k < array[selectedIndex]; k++) {
                                    world.getBlockAt(startingLocation.clone().add(selectedIndex, k, 0)).setType(Material.AIR);
                                }
                                for (int k = 0; k < array[selectedIndex + 1]; k++) {
                                    world.getBlockAt(startingLocation.clone().add(selectedIndex + 1, k, 0)).setType(Material.AIR);
                                }
                                int temp = array[selectedIndex];
                                array[selectedIndex] = array[selectedIndex + 1];
                                array[selectedIndex + 1] = temp;
                                if (!(selectedIndex == array.length - 1)) {
                                    for (int k = 0; k < array[selectedIndex + 1]; k++) {
                                        world.getBlockAt(startingLocation.clone().add(selectedIndex + 1, k, 0)).setType(Material.EMERALD_BLOCK);
                                    }

                                    for (int k = 0; k < array[selectedIndex]; k++) {
                                        world.getBlockAt(startingLocation.clone().add(selectedIndex, k, 0)).setType(Material.REDSTONE_BLOCK);
                                    }
                                }
                            }
                        }
                        // Wähle nächstes Säulen-Paar aus
                        if (!selected){
                            selectedIndex++;
                        }
                    } else {
                        if (!selected){
                            selectedIndex = 0;
                        }
                    }
                    selected = !selected;
                } else { // Wenn das Sortieren beendet ist
                    for (int k = 0; k < array.length; k++) { // Wandle alle Säulen in Diamant-Blöcke um
                        for (int l = 0; l < array[k]; l++) {
                            world.getBlockAt(startingLocation.clone().add(k, l, 0)).setType(Material.DIAMOND_BLOCK);
                        }
                    }
                    this.cancel();
                }
            }
        }.runTaskTimer(Bubblesort.getPlugin(Bubblesort.class), 5L, 5L);

        return false;
    }

    private boolean isSorted(int[] array){
        for (int i = 0; i < array.length - 1; i++){
            if (array[i] > array[i+1]){
                return false;
            }
        }
        return true;
    }

}
