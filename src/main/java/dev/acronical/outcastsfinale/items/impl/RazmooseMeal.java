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

public class RazmooseMeal {

    public static ItemStack razmooseMeal;

    public static void createRazmooseMeal() {
        ItemStack item = new ItemStack(Material.COOKED_CHICKEN, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Razmoose's Emotional Meal");
        List<String> lore = new ArrayList<>();
        lore.add("Gives you saturation, regeneration and levitation!");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        razmooseMeal = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("razmoosemeal"), item);
        sr.shape(" B ", "CSC", " B ");
        sr.setIngredient('B', Material.BREAD);
        sr.setIngredient('S', Material.SUGAR_CANE);
        sr.setIngredient('C', Material.COOKED_CHICKEN);
        Bukkit.getServer().addRecipe(sr);
    }

}
