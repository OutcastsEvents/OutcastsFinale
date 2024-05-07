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

public class TingzNecklace {

    public static ItemStack tingzNecklace;

    public static void createTingzNecklace() {
        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§1Tingz's Necklace");
        List<String> lore = new ArrayList<>();
        lore.add("Gives you resistance 1, and speed 1 for 10 seconds on a kill.");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
        item.setItemMeta(meta);
        tingzNecklace = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("tingznecklace"), item);
        sr.shape("IDI", " I ");
        sr.setIngredient('I', Material.IRON_BLOCK);
        sr.setIngredient('D', Material.DIAMOND);
        Bukkit.getServer().addRecipe(sr);
    }

}
