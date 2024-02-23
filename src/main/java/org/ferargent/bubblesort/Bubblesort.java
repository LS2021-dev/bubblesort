package org.ferargent.bubblesort;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Bubblesort extends JavaPlugin {

    public static int[] randomNumbers = createRandomNumbers(10);
    private static Bubblesort plugin;

    public static int[] createRandomNumbers(int length) {
        int[] list = new int[length];
        for (int i = 0; i < length; i++) {
            list[i] = (int) (Math.random() * 10+1);
        }
        return list;
    }

    public static Bubblesort getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("random").setExecutor(new Random());
        getCommand("start").setExecutor(new Start());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
