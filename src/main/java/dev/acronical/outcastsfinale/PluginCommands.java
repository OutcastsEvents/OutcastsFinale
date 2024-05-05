package dev.acronical.outcastsfinale;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
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
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {        //create new array
        final List<String> completions = new ArrayList<>();
        StringUtil.copyPartialMatches(args[0], List.of(COMMANDS), completions);
        return completions;
    }
}
