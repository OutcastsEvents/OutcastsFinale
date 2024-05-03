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

public class AstelinaBarrel {

    public static ItemStack astelinaBarrel;

    public static void createAstelinaBarrel() {
        ItemStack item = new ItemStack(Material.BARREL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Silly Barrel");
        List<String> lore = new ArrayList<>();
        lore.add("Right click to shoot TNT!");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        astelinaBarrel = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("astelinabarrel"), item);
        sr.shape("BTB", "TDT" ,"BTB");
        sr.setIngredient('B', Material.BARREL);
        sr.setIngredient('T', Material.TNT);
        sr.setIngredient('D', Material.DIAMOND);
        Bukkit.getServer().addRecipe(sr);
    }

}
