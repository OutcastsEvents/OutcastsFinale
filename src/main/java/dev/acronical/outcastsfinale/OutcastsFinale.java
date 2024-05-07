package dev.acronical.outcastsfinale;

import dev.acronical.outcastsfinale.items.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class OutcastsFinale extends JavaPlugin {

    @Override
    public void onEnable() {
        ItemManager.init();
        getServer().getPluginManager().registerEvents(new PluginEvents(), this);
        getCommand("pvp").setExecutor(new PluginCommands());
        getCommand("crownreset").setExecutor(new PluginCommands());
        getCommand("mealreset").setExecutor(new PluginCommands());
        getServer().getConsoleSender().sendMessage("[Outcasts Finale] Plugin enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("[Outcasts Finale] Plugin disabled!");
    }
}
