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

public class CrowLeggings {

    public static ItemStack crowLeggings;

    public static void createCrowLeggings() {
        ItemStack item = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Crow's Cushioned Pants");
        List<String> lore = new ArrayList<>();
        lore.add("Reduces fall damage, in some cases.");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        item.setItemMeta(meta);
        crowLeggings = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("crowleggings"), item);
        sr.shape("F F", "FDF", "H H");
        sr.setIngredient('F', Material.FEATHER);
        sr.setIngredient('D', Material.DIAMOND_LEGGINGS);
        sr.setIngredient('H', Material.HAY_BLOCK);
        Bukkit.getServer().addRecipe(sr);
    }

}
