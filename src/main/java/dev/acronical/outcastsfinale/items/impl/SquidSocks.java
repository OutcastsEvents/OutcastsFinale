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

public class SquidSocks {

    public static ItemStack squidSocks;

    public static void createSquidSocks() {
        ItemStack item = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Squid Socks");
        List<String> lore = new ArrayList<>();
        lore.add("Gives you protection 3 and dolphin's grace, at a cost.");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.BINDING_CURSE, 1, false);
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, false);
        item.setItemMeta(meta);
        squidSocks = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("squidsocks"), item);
        sr.shape("D D", "BWB");
        sr.setIngredient('B', Material.DIAMOND_BLOCK);
        sr.setIngredient('D', Material.DIAMOND);
        sr.setIngredient('W', Material.WATER_BUCKET);
        Bukkit.getServer().addRecipe(sr);
    }

}
