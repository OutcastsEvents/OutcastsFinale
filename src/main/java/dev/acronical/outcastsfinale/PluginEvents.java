package dev.acronical.outcastsfinale;

import dev.acronical.outcastsfinale.items.impl.*;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.logging.Logger;

public class PluginEvents implements Listener {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PluginEvents.class);
    Logger logger = Logger.getLogger("OutcastsFinale");

    // ! Chief's Socks Logic
    public BukkitTask squidSocksTask = Bukkit.getServer().getScheduler().runTaskTimer(OutcastsFinale.getPlugin(OutcastsFinale.class), () -> {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getInventory().getBoots() == null) continue;
            if (!Objects.equals(player.getInventory().getBoots().getLore(), ChiefSocks.squidSocks.getLore())) continue;
            player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 30, 1));
        }
    }, 0, 20);

    // ! Woocie's Horse Logic
    public BukkitTask woocieHorseTask = Bukkit.getServer().getScheduler().runTaskTimer(OutcastsFinale.getPlugin(OutcastsFinale.class), () -> {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getInventory().getItemInMainHand().isEmpty()) continue;
            if (!Objects.equals(player.getInventory().getItemInMainHand().getLore(), WoocieHorse.woocieHorse.getLore())/* || !player.getInventory().getItemInOffHand().getItemMeta().equals(WoocieHorse.woocieHorse.getItemMeta())*/) continue;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 1));
        }
    }, 0, 20);

    // ! Wymsicaal's Rock Logic
    @EventHandler
    public void spikyRockThrow(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        World world = player.getWorld();
        Location playerLocation = player.getLocation();
        Vector playerDirection = playerLocation.getDirection();
        if (!e.getAction().isRightClick()) return;
        if (!player.getInventory().getItemInMainHand().equals(WymsicaalRock.wymsicaalRock)) return;
        if (!player.getTargetBlock(null, 5).getType().equals(Material.AIR)) return;
        player.getInventory().removeItem(WymsicaalRock.wymsicaalRock);
        Entity spikyRock = world.spawnFallingBlock(playerLocation, Material.STONE, (byte) 0);
        spikyRock.setGlowing(true);
        spikyRock.setVelocity(new Vector(playerDirection.getX(), playerDirection.getY() + 0.5, playerDirection.getZ()));
    }

    @EventHandler
    public void onSpikyRockLand(EntityChangeBlockEvent e) {
        if (!e.getEntityType().equals(EntityType.FALLING_BLOCK)) return;
        if (!e.getEntity().isGlowing()) return;
        e.setCancelled(true);
        Entity entity = e.getEntity();
        for (Entity nearbyEntity : entity.getNearbyEntities(3, 3, 3)) {
            if (nearbyEntity instanceof Player player) {
                player.damage(5);
            }
        }
    }

    // ! Razmoose's Meal Logic
    @EventHandler
    public void onRazmooseMealEat(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!e.getAction().isRightClick()) return;
        if (!player.getInventory().getItemInMainHand().equals(RazmooseMeal.razmooseMeal)) return;
        player.getInventory().removeItem(RazmooseMeal.razmooseMeal);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 40 , 254));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 4));
        player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 400, 0));
    }

    // ! Tingz's Necklace Logic
    @EventHandler
    public void onPlayerKill(PlayerDeathEvent e) {
        if (e.getEntity().getKiller() == null) return;
        Player killer = e.getEntity().getKiller();
        if (killer == e.getEntity()) return;
        if (!Objects.equals(killer.getInventory().getChestplate().getLore(), TingzNecklace.tingzNecklace.getLore())) return;
        killer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 0));
        killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 0));
    }

    // ! Crow's Leggings Logic
    @EventHandler
    public void onPlayerFall(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player player)) return;
        if (e.getCause() != EntityDamageEvent.DamageCause.FALL) return;
        if (player.getInventory().getLeggings() == null) return;
        if (!Objects.equals(player.getInventory().getLeggings().getLore(), CrowLeggings.crowLeggings.getLore())) return;
        e.setDamage(6);
    }
}
