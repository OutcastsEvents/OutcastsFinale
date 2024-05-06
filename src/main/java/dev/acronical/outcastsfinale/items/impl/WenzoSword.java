package dev.acronical.outcastsfinale.items.impl;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class WenzoSword {

    public static ItemStack wenzoSword;

    public static void createWenzoSword() {
        ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§cWenzo's Sword");
        List<String> lore = new ArrayList<>();
        lore.add("A sword that has some interesting power.");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        Damageable damageable = (Damageable) meta;
        damageable.setDamage(240);
        item.setItemMeta(meta);
        wenzoSword = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("wenzosword"), item);
        sr.shape(" ID", "MSI", "MM ");
        sr.setIngredient('I', Material.IRON_BLOCK);
        sr.setIngredient('D', Material.DIAMOND);
        sr.setIngredient('M', Material.MUD);
        sr.setIngredient('S', Material.IRON_SWORD);
        Bukkit.getServer().addRecipe(sr);
    }

}
