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

public class WymsicaalRock {

    public static ItemStack wymsicaalRock;

    public static void createWymsicaalRock() {
        ItemStack item = new ItemStack(Material.STONE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Wymsicaal's Spiky Rock");
        List<String> lore = new ArrayList<>();
        lore.add("Throw me to deal damage.");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        wymsicaalRock = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("wymsicaalrock"), item);
        sr.shape("SSS", "SAS", "SSS");
        sr.setIngredient('S', Material.STONE);
        sr.setIngredient('A', Material.AMETHYST_SHARD);
        Bukkit.getServer().addRecipe(sr);
    }

}
