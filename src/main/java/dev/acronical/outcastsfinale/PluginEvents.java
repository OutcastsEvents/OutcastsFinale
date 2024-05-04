package dev.acronical.outcastsfinale;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
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
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.logging.Logger;

import static dev.acronical.outcastsfinale.items.impl.WymsicaalRock.wymsicaalRock;

public class PluginEvents implements Listener {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PluginEvents.class);
    Logger logger = Logger.getLogger("OutcastsFinale");

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
        if (!player.getActiveItem().getItemMeta().equals(wymsicaalRock.getItemMeta())) return;
        player.getInventory().removeItem(wymsicaalRock);
        Entity spikyRock = world.spawnFallingBlock(playerLocation, Material.STONE, (byte) 0);
        spikyRock.setGlowing(true);
        spikyRock.setMetadata(player.getName(), new FixedMetadataValue(OutcastsFinale.getPlugin(OutcastsFinale.class), "damage"));
        logger.info("Metadata set: " + player.getName());
        spikyRock.setVelocity(new Vector(playerDirection.getX(), playerDirection.getY(), playerDirection.getZ()));
    }

    @EventHandler
    public void onSpikyRockLand(EntityChangeBlockEvent e) {
        if (!e.getEntityType().equals(EntityType.FALLING_BLOCK)) return;
        Entity entity = e.getEntity();
        MetadataValue metadata = entity.getMetadata("damage").contains("damage") ? entity.getMetadata("damage").get(0) : null;
        logger.info("Entity landed");
        logger.info("Entity has metadata: " + metadata);
        for (Entity nearbyEntity : entity.getNearbyEntities(5, 5, 5)) {
            if (nearbyEntity instanceof Player player) {
//                if (Objects.equals(player.getName(), entity.getMetadata("damage").get(0).asString())) continue;
//                if (Objects.requireNonNull(player.getScoreboard().getPlayerTeam(player)).getEntries().contains(entity.getMetadata("damage").get(0).asString())) continue;
                player.damage(5);
                logger.info("Player damaged");
            }
        }
    }

    // ! Razmoose's Meal Logic

}
