package org.ferargent.bubblesort;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static org.ferargent.bubblesort.Bubblesort.randomNumbers;

public class Random implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] args) {
        randomNumbers = Bubblesort.createRandomNumbers(10);

        for (int randomNumber : randomNumbers) {
            commandSender.sendMessage("§b" + randomNumber);
        }


        return false;
    }
}
