package dev.acronical.outcastsfinale;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.Vector;

import java.util.Objects;
import java.util.logging.Logger;

import static dev.acronical.outcastsfinale.items.impl.WymsicaalRock.wymsicaalRock;

public class PluginEvents implements Listener {

    // ! Wymsicaal's Rock Logic
    @EventHandler
    public void spikyRockThrow(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        World world = player.getWorld();
        Player[] players = world.getPlayers().toArray(new Player[0]);
        Location playerLocation = player.getLocation();
        Vector playerDirection = playerLocation.getDirection();
        Team playerTeam = player.getScoreboard().getPlayerTeam(player);
        if (!e.getAction().isRightClick()) return;
        if (!player.getItemInHand().getItemMeta().equals(wymsicaalRock.getItemMeta())) return;
        player.getInventory().removeItem(wymsicaalRock);
        Entity spikyRock = world.spawnFallingBlock(playerLocation, Material.STONE, (byte) 0);
        spikyRock.setGlowing(true);
        spikyRock.setMetadata(player.getName(), new FixedMetadataValue(OutcastsFinale.getPlugin(OutcastsFinale.class), "damage"));
        spikyRock.setVelocity(new Vector(playerDirection.getX(), 2, playerDirection.getZ()));
    }

    public BukkitTask rockCheck = Bukkit.getServer().getScheduler().runTaskTimer(OutcastsFinale.getPlugin(OutcastsFinale.class), () -> {
        if (Bukkit.getOnlinePlayers().isEmpty()) return;
        World world = Objects.requireNonNull(Bukkit.getServer().getOnlinePlayers().stream().findFirst().orElse(null)).getWorld();
        if (world.getEntities().isEmpty()) return;
        for (Entity e : world.getEntities()) {
            if (!e.getType().equals(EntityType.FALLING_BLOCK)) continue;
            if (!e.isGlowing()) continue;
            Entity[] nearbyEntities = e.getNearbyEntities(5, 5, 5).toArray(new Entity[0]);
            for (Entity nearbyEntity : nearbyEntities) {
                if (!(nearbyEntity instanceof Player player)) continue;
                if (e.hasMetadata(player.getName())) continue;;
                player.damage(5);
            }
        }
    }, 0, 20);
//    @EventHandler
//    public void onSpikyRockLand(EntityChangeBlockEvent e) {
//        if (!e.getEntityType().equals(EntityType.FALLING_BLOCK)) return;
//        Entity entity = e.getEntity();
//        if (entity.)
//    }

}
