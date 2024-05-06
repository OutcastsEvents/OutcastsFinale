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

public class FerveeGhast {

    public static ItemStack ferveeGhast;

    public static void createFerveeGhast() {
        ItemStack item = new ItemStack(Material.GHAST_SPAWN_EGG, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§9Fervee's Ghast");
        List<String> lore = new ArrayList<>();
        lore.add("Spawns a ridable Ghast for you to use!");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        ferveeGhast = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("ferveeghast"), item);
        sr.shape("FCF", "CSC" ,"FCF");
        sr.setIngredient('C', Material.COAL_BLOCK);
        sr.setIngredient('F', Material.FEATHER);
        sr.setIngredient('S', Material.FLINT_AND_STEEL);
        Bukkit.getServer().addRecipe(sr);
    }

}
