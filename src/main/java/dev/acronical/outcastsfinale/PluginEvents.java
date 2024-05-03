package dev.acronical.outcastsfinale;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.Vector;

import java.util.logging.Logger;

import static dev.acronical.outcastsfinale.items.impl.WymsicaalRock.wymsicaalRock;

public class PluginEvents implements Listener {

    @EventHandler
    public void spikeyRockThrow(PlayerInteractEvent e) {
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
        spikyRock.setVelocity(new Vector(playerDirection.getX(), 2, playerDirection.getZ()));
        for (int i = 0; i < 10; i++) {
            Logger.getLogger("development").info("" + i);
            i = 0;
            if (spikyRock.isOnGround()) {
                world.spawnParticle(Particle.DRAGON_BREATH, spikyRock.getLocation(), 10);
                for (Player p : players) {
                    if (p.getName().equals(player.getName()) || p.getScoreboardTags().contains(playerTeam)) continue;
                    if (p.getLocation().distance(spikyRock.getLocation()) < 5) p.damage(5);
                }
                world.setBlockData(spikyRock.getLocation(), Material.AIR.createBlockData());
                return;
            }
        }
    }

//    @EventHandler
//    public void onSpikeyRockLand(EntityChangeBlockEvent e) {
//        if (!e.getEntityType().equals(EntityType.FALLING_BLOCK)) return;
//        Entity entity = e.getEntity();
//        if (entity.)
//    }

}
