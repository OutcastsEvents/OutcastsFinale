package dev.acronical.outcastsfinale.items.impl;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class WoocieHorse {

    public static ItemStack woocieHorse;

    public static void createWoocieHorse() {
        ItemStack item = new ItemStack(Material.LEATHER_HORSE_ARMOR, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Woocie's Horse");
        List<String> lore = new ArrayList<>();
        lore.add("Supplies speed 2 when holding.");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        woocieHorse = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("woociehorse"), item);
        sr.shape("LCL", "SDS", "LCL");
        sr.setIngredient('L', Material.LEATHER);
        sr.setIngredient('D', Material.DIAMOND);
        sr.setIngredient('S', Material.SUGAR);
        sr.setIngredient('C', Material.COAL_BLOCK);
        Bukkit.getServer().addRecipe(sr);
    }

}
