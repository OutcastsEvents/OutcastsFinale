package dev.acronical.outcastsfinale;

import dev.acronical.outcastsfinale.items.impl.RazmooseMeal;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PluginCommands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (command.getName().equals("pvp") && (commandSender.isOp() || commandSender.hasPermission("outcastsfinale.pvptoggle"))) {
            World world = commandSender.getServer().getWorld(commandSender.getServer().getWorlds().get(0).getName());
            if (world == null) {
                commandSender.sendMessage("World not found.");
                return true;
            }
            if (strings.length == 0) {
                boolean pvp = world.getPVP();
                if (pvp) {
                    world.setPVP(false);
                    commandSender.sendMessage("You have disabled PVP.");
                    Bukkit.broadcast("§lPvP has been disabled by " + commandSender.getName(), "outcastsfinale.pvptoggle");
                } else {
                    world.setPVP(true);
                    commandSender.sendMessage("You have enabled PVP.");
                    Bukkit.broadcast("§lPvP has been enabled by " + commandSender.getName(), "outcastsfinale.pvptoggle");
                }
                return true;
            }
            if (strings[0].equalsIgnoreCase("on")) {
                world.setPVP(true);
                commandSender.sendMessage("You have enabled PVP.");
                Bukkit.broadcast("§lPvP has been enabled by " + commandSender.getName(), "outcastsfinale.pvptoggle");
                return true;
            }
            if (strings[0].equalsIgnoreCase("off")) {
                world.setPVP(false);
                commandSender.sendMessage("You have disabled PVP.");
                Bukkit.broadcast("§lPvP has been disabled by " + commandSender.getName(), "outcastsfinale.pvptoggle");
                return true;
            }
            commandSender.sendMessage("Usage: /pvp <on|off>");
            return true;
        }

        if (command.getName().equalsIgnoreCase("crownreset") && (commandSender.isOp() || commandSender.hasPermission("outcastsfinale.crownreset"))) {
            if (strings.length == 0) {
                commandSender.sendMessage("Usage: /crownreset <player>");
                return true;
            }
            if (strings.length > 1) {
                commandSender.sendMessage("Usage: /crownreset <player>");
                return true;
            }
            if (strings[0].equalsIgnoreCase("all")) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!player.getScoreboardTags().contains("yrrahCrown")) continue;
                    player.getScoreboardTags().remove("yrrahCrown");
                    player.sendMessage("You can now use Yrrah's Crown again.");
                }
                commandSender.sendMessage("All players can now use Yrrah's Crown again.");
                return true;
            }
            Player player = Bukkit.getPlayer(strings[0]);
            if (player == null) {
                commandSender.sendMessage("Player not found.");
                return true;
            }
            if (!player.getScoreboardTags().contains("yrrahCrown")) {
                commandSender.sendMessage("Player has not used the crown.");
                return true;
            }
            player.getScoreboardTags().remove("yrrahCrown");
            commandSender.sendMessage(player.getName() + " can now use Yrrah's Crown again.");
            player.sendMessage("Your crown has been reset.");
            return true;
        }

        if (command.getName().equalsIgnoreCase("mealreset") && (commandSender.isOp() || commandSender.hasPermission("outcastsfinale.mealreset"))) {
            if (strings.length == 0) {
                commandSender.sendMessage("Usage: /mealreset <player>");
                return true;
            }
            if (strings.length > 1) {
                commandSender.sendMessage("Usage: /mealreset <player>");
                return true;
            }
            if (strings[0].equalsIgnoreCase("all")) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getStatistic(Statistic.USE_ITEM, RazmooseMeal.razmooseMeal.getType()) == 0) continue;
                    player.sendMessage("You can now use Razmoose's meal again.");
                    player.setStatistic(Statistic.USE_ITEM, RazmooseMeal.razmooseMeal.getType(), 0);
                }
                commandSender.sendMessage("All players can now use Razmoose's meal again.");
                return true;
            }
            Player player = Bukkit.getPlayer(strings[0]);
            if (player == null) {
                commandSender.sendMessage("Player not found.");
                return true;
            }
            if (player.getStatistic(Statistic.USE_ITEM, RazmooseMeal.razmooseMeal.getType()) == 0) {
                commandSender.sendMessage("Player has not used the meal.");
                return true;
            }
            player.setStatistic(Statistic.USE_ITEM, RazmooseMeal.razmooseMeal.getType(), 0);
            commandSender.sendMessage(player.getName() + " can now use Razmoose's meal again.");
            player.sendMessage("Your meal has been reset.");
            return true;
        }

        return false;
    }
}
