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

public class PheabeeBeehive {

    public static ItemStack pheabeeBeehive;

    public static void createPheabeeBeehive() {
        ItemStack item = new ItemStack(Material.BEEHIVE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Pheabee's Beehive");
        List<String> lore = new ArrayList<>();
        lore.add("Summons bees to help attack your enemies!");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        pheabeeBeehive = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("pheabeebeehive"), item);
        sr.shape("LLL", "HHH", "LLL");
        sr.setIngredient('L', Material.OAK_LOG);
        sr.setIngredient('H', Material.HONEYCOMB);
        Bukkit.getServer().addRecipe(sr);
    }
}
