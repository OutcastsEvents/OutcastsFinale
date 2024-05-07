package dev.acronical.outcastsfinale.items.impl;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class YrrahCrown {

    public static ItemStack yrrahCrown;

    public static void createYrrahCrown() {
        ItemStack item = new ItemStack(Material.GOLDEN_HELMET, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§bYrrah's Crown");
        List<String> lore = new ArrayList<>();
        lore.add("§lA valuble item that can §ksave you once.");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.BINDING_CURSE, 1, false);
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, false);
        meta.addEnchant(Enchantment.DURABILITY, 3, false);
        item.setItemMeta(meta);
        yrrahCrown = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("yrrahcrown"), item);
        sr.shape("AOT", "HGM", "CSD");
        sr.setIngredient('A', Material.AMETHYST_SHARD);
        sr.setIngredient('O', Material.OBSIDIAN);
        sr.setIngredient('T', Material.TNT);
        sr.setIngredient('H', Material.HONEYCOMB);
        sr.setIngredient('G', Material.GOLDEN_HELMET);
        sr.setIngredient('M', Material.MUD);
        sr.setIngredient('C', Material.COOKED_CHICKEN);
        sr.setIngredient('S', Material.SUGAR_CANE);
        sr.setIngredient('D', Material.DIAMOND_BLOCK);
        Bukkit.getServer().addRecipe(sr);
    }
}
