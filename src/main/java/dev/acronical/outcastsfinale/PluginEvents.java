package dev.acronical.outcastsfinale;

import dev.acronical.outcastsfinale.items.impl.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Team;
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
            if (!Objects.equals(player.getInventory().getItemInMainHand().getLore(), WoocieHorse.woocieHorse.getLore())) continue;
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
        if (player.getInventory().getItemInMainHand().isEmpty()) return;
        if (!Objects.equals(player.getInventory().getItemInMainHand().getLore(), WymsicaalRock.wymsicaalRock.getLore())) return;
        if (!player.getTargetBlock(null, 5).getType().equals(Material.AIR)) return;
        e.setCancelled(true);
        player.getInventory().removeItem(WymsicaalRock.wymsicaalRock);
        Entity spikyRock = world.spawnFallingBlock(playerLocation, Material.STONE, (byte) 0);
        spikyRock.setGlowing(true);
        spikyRock.setVelocity(new Vector(playerDirection.getX(), playerDirection.getY() + 0.5, playerDirection.getZ()));
        spikyRock.setCustomName(player.getName() + "'s Spiky Rock");
        spikyRock.setCustomNameVisible(true);
        spikyRock.addScoreboardTag(player.getName().toLowerCase());
    }

    @EventHandler
    public void onSpikyRockLand(EntityChangeBlockEvent e) {
        if (!e.getEntityType().equals(EntityType.FALLING_BLOCK)) return;
        if (!e.getEntity().isGlowing()) return;
        if (!Objects.requireNonNull(e.getEntity().getCustomName()).contains("'s Spiky Rock")) return;
        e.setCancelled(true);
        Entity entity = e.getEntity();
        Team team = Bukkit.getPlayer(entity.getScoreboardTags().iterator().next()).getScoreboard().getPlayerTeam(Objects.requireNonNull(Bukkit.getPlayer(entity.getScoreboardTags().iterator().next())));
        for (Entity nearbyEntity : entity.getNearbyEntities(3, 3, 3)) {
            if (nearbyEntity instanceof Player player) {
                if (player.getScoreboard().getPlayerTeam(player).equals(team) || entity.getScoreboardTags().contains(player.getName().toLowerCase())) continue;
                player.damage(5);
            }
        }
    }

    // ! Razmoose's Meal Logic
    @EventHandler
    public void onRazmooseMealEat(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!e.getAction().isRightClick()) return;
        if (player.getInventory().getItemInMainHand().isEmpty()) return;
        if (!Objects.equals(player.getInventory().getItemInMainHand().getLore(), RazmooseMeal.razmooseMeal.getLore())) return;
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
        if (killer.getInventory().getChestplate() == null) return;
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

    // ! Fervee's Ghast Logic
    @EventHandler
    public void onGhastSpawn(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!e.getAction().isRightClick()) return;
        if (player.getInventory().getItemInMainHand().isEmpty()) return;
        if (!Objects.equals(player.getInventory().getItemInMainHand().getLore(), FerveeGhast.ferveeGhast.getLore())) return;
        e.setCancelled(true);
        player.getInventory().removeItem(FerveeGhast.ferveeGhast);
        Ghast ghast = (Ghast) player.getWorld().spawnEntity(player.getLocation(), EntityType.GHAST);
        ghast.setCustomName(player.getName() + "'s Ghast");
        for (Player target : Bukkit.getOnlinePlayers()) {
            if (target.getGameMode() == GameMode.SURVIVAL && target != player && target.getScoreboard().getPlayerTeam(target) != player.getScoreboard().getPlayerTeam(player)) {
                ghast.setTarget(target);
                break;
            }
        }
    }

    public BukkitTask ghastTimeout = Bukkit.getServer().getScheduler().runTaskTimer(OutcastsFinale.getPlugin(OutcastsFinale.class), () -> {
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Ghast ghast) {
                    if (!ghast.isDead() && ghast.getCustomName() != null) {
                        if (ghast.getCustomName().contains("'s Ghast")) {
                            if (ghast.getTicksLived() > 600) {
                                ghast.remove();
                            }
                        }
                    }
                }
            }
        }
    }, 0, 20);

    // ! Pheabee's Beehive Logic
    @EventHandler
    public void onBeehiveUse(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!e.getAction().isRightClick()) return;
        if (player.getInventory().getItemInMainHand().isEmpty()) return;
        if (!Objects.equals(player.getInventory().getItemInMainHand().getLore(), PheabeeBeehive.pheabeeBeehive.getLore())) return;
        e.setCancelled(true);
        player.getInventory().removeItem(PheabeeBeehive.pheabeeBeehive);
        Location location = player.getLocation();
        World world = player.getWorld();
        for (int i = 0; i < 5; i++) {
            Bee bee = (Bee) world.spawnEntity(location, EntityType.BEE);
            bee.setAnger(100);
            bee.setCustomName(player.getName() + "'s Bee");
            for (Player target : Bukkit.getOnlinePlayers()) {
                if (target.getGameMode() == GameMode.SURVIVAL && target != player && target.getScoreboard().getPlayerTeam(target) != player.getScoreboard().getPlayerTeam(player)) {
                    bee.setTarget(target);
                    break;
                }
            }
        }
    }

    public BukkitTask beeTimeout = Bukkit.getServer().getScheduler().runTaskTimer(OutcastsFinale.getPlugin(OutcastsFinale.class), () -> {
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Bee bee) {
                    if (!bee.isDead() && bee.getCustomName() != null) {
                        if (bee.getCustomName().contains("'s Bee")) {
                            if (bee.getTicksLived() > 200) {
                                bee.remove();
                            }
                        }
                    }
                }
            }
        }
    }, 0, 20);

    // ! Wenzo's Sword Logic
    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player player)) return;
        if (!(e.getDamager() instanceof Player damager)) return;
        if (damager.getInventory().getItemInMainHand().isEmpty()) return;
        if (!Objects.equals(damager.getInventory().getItemInMainHand().getLore(), WenzoSword.wenzoSword.getLore())) return;
        World world = player.getWorld();
        Location damagerLocation = damager.getLocation();
        Vector damagerDirection = damagerLocation.getDirection();
        for (int i = 0; i < 3; i++) {
            Entity clyde = world.spawnEntity(damagerLocation, EntityType.FROG);
            clyde.setCustomName("§l§6Clyde");
            clyde.setCustomNameVisible(true);
            clyde.addScoreboardTag(damager.getName().toLowerCase());
            clyde.setGlowing(true);
        }
        for (int i = 0; i < 8; i++) {
            // Shoot the flower in 8 directions creating a circle around the player
            Vector shootDirection = new Vector(damagerDirection.getX() + Math.cos(i * Math.PI / 4), damagerDirection.getY(), damagerDirection.getZ() + Math.sin(i * Math.PI / 4)).normalize().multiply(0.75);
            switch (i) {
                case 0:
                    Entity dandelion = world.dropItem(damager.getLocation(), new ItemStack(Material.DANDELION));
                    dandelion.addScoreboardTag("wenzoFlower");
                    dandelion.setGlowing(true);
                    dandelion.setVelocity(shootDirection);
                    break;
                case 1:
                    Entity poppy = world.dropItem(damager.getLocation(), new ItemStack(Material.POPPY));
                    poppy.addScoreboardTag("wenzoFlower");
                    poppy.setGlowing(true);
                    poppy.setVelocity(shootDirection);
                    break;
                case 2:
                    Entity azureBluet = world.dropItem(damager.getLocation(), new ItemStack(Material.AZURE_BLUET));
                    azureBluet.addScoreboardTag("wenzoFlower");
                    azureBluet.setGlowing(true);
                    azureBluet.setVelocity(shootDirection);
                    break;
                case 3:
                    Entity oxeyeDaisy = world.dropItem(damager.getLocation(), new ItemStack(Material.OXEYE_DAISY));
                    oxeyeDaisy.addScoreboardTag("wenzoFlower");
                    oxeyeDaisy.setGlowing(true);
                    oxeyeDaisy.setVelocity(shootDirection);
                    break;
                case 4:
                    Entity cornflower = world.dropItem(damager.getLocation(), new ItemStack(Material.CORNFLOWER));
                    cornflower.addScoreboardTag("wenzoFlower");
                    cornflower.setGlowing(true);
                    cornflower.setVelocity(shootDirection);
                    break;
                case 5:
                    Entity allium = world.dropItem(damager.getLocation(), new ItemStack(Material.ALLIUM));
                    allium.addScoreboardTag("wenzoFlower");
                    allium.setGlowing(true);
                    allium.setVelocity(shootDirection);
                    break;
                case 6:
                    Entity lilyOfTheValley = world.dropItem(damager.getLocation(), new ItemStack(Material.LILY_OF_THE_VALLEY));
                    lilyOfTheValley.addScoreboardTag("wenzoFlower");
                    lilyOfTheValley.setGlowing(true);
                    lilyOfTheValley.setVelocity(shootDirection);
                    break;
                case 7:
                    Entity redTulip = world.dropItem(damager.getLocation(), new ItemStack(Material.RED_TULIP));
                    redTulip.addScoreboardTag("wenzoFlower");
                    redTulip.setGlowing(true);
                    redTulip.setVelocity(shootDirection);
                    break;
                default:
                    logger.severe("Invalid i value: " + i);
                    logger.severe("Create an issue here to report this bug: https://www.github.com/OutcastsEvents/OutcastsFinale/issues/new");
            }
        }
    }

    @EventHandler
    public void onFlowerPickup(PlayerAttemptPickupItemEvent e) {
        if (!e.getItem().getScoreboardTags().contains("wenzoFlower")) return;
        e.setCancelled(true);
    }

    public BukkitTask clydeAttack = Bukkit.getServer().getScheduler().runTaskTimer(OutcastsFinale.getPlugin(OutcastsFinale.class), () -> {
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Frog frog) {
                    if (!frog.isDead() && frog.getCustomName() != null) {
                        if (frog.getCustomName().contains("Clyde")) {
                            Player spawner = Bukkit.getPlayer(frog.getScoreboardTags().iterator().next());
                            Team team = spawner.getScoreboard().getPlayerTeam(spawner);
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                if (!player.getName().equals(frog.getScoreboardTags().iterator().next()) && player.getGameMode().equals(GameMode.SURVIVAL)) {
                                    if (player.getScoreboard().getPlayerTeam(player) != team) {
                                        frog.setTarget(player);
                                        frog.setVelocity(player.getLocation().toVector().subtract(frog.getLocation().toVector()).normalize().multiply(0.5));
                                        if (frog.getLocation().distance(player.getLocation()) < 3) {
                                            player.damage(1, frog);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }, 0, 20);

    public BukkitTask clydeTimeout = Bukkit.getServer().getScheduler().runTaskTimer(OutcastsFinale.getPlugin(OutcastsFinale.class), () -> {
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Frog frog) {
                    if (!frog.isDead() && frog.getCustomName() != null) {
                        if (frog.getCustomName().contains("Clyde")) {
                            if (frog.getTicksLived() > 100) {
                                frog.remove();
                            }
                        }
                    }
                }
            }
        }
    }, 0, 20);

    public BukkitTask flowerTimeout = Bukkit.getServer().getScheduler().runTaskTimer(OutcastsFinale.getPlugin(OutcastsFinale.class), () -> {
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Item item) {
                    if (item.getScoreboardTags().contains("wenzoFlower")) {
                        if (item.getTicksLived() > 100) {
                            item.remove();
                        }
                    }
                }
            }
        }
    }, 0, 20);

    // ! Astelina's Barrel Logic
    @EventHandler
    public void onBarrelUse(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!e.getAction().isRightClick()) return;
        if (player.getInventory().getItemInMainHand().isEmpty()) return;
        if (!Objects.equals(player.getInventory().getItemInMainHand().getLore(), AstelinaBarrel.astelinaBarrel.getLore())) return;
        e.setCancelled(true);
        player.getInventory().removeItem(AstelinaBarrel.astelinaBarrel);
        Location location = player.getLocation();
        Vector direction = location.getDirection();
        World world = player.getWorld();
        for (int i = 0; i < 3; i++) {
            TNTPrimed tnt = (TNTPrimed) world.spawnEntity(location, EntityType.PRIMED_TNT);
            tnt.setVelocity(new Vector(direction.getX() + Math.random() * 0.5, direction.getY() + Math.random() * 0.5, direction.getZ() + Math.random() * 0.5));
            tnt.setFuseTicks(40);
            tnt.setCustomNameVisible(true);
            tnt.setCustomName(player.getName() + "'s TNT");
            tnt.setGlowing(true);
            tnt.addScoreboardTag(player.getName().toLowerCase());
        }
    }

    @EventHandler
    public void onTNTExplode(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof TNTPrimed tnt)) return;
        if (!tnt.getCustomName().contains("'s TNT")) return;
        if (!(e.getEntity() instanceof Player player)) return;
        Team team = Objects.requireNonNull(Bukkit.getPlayer(tnt.getScoreboardTags().iterator().next())).getScoreboard().getPlayerTeam(Objects.requireNonNull(Bukkit.getPlayer(tnt.getScoreboardTags().iterator().next())));
        if (player.getScoreboard().getPlayerTeam(player) == team || player.getName().toLowerCase().equals(tnt.getScoreboardTags().iterator().next())) {
            e.setCancelled(true);
        }
    }
}
