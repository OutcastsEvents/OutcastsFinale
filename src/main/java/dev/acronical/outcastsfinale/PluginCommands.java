package dev.acronical.outcastsfinale;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PluginCommands implements CommandExecutor, TabCompleter {
    private static final String[] COMMANDS = { "on", "off" };

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
                }
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
            commandSender.sendMessage(player.getName() + " can now use the crown again.");
            player.sendMessage("Your crown has been reset.");
            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {        //create new array
        final List<String> completions = new ArrayList<>();
        if (args[0].equalsIgnoreCase("pvp") && args.length == 1) {
            StringUtil.copyPartialMatches(args[0], List.of(COMMANDS), completions);
            return completions;
        }
        return null;
    }
}
