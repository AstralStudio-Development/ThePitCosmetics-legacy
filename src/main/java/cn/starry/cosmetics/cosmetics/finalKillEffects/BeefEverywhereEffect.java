/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.Item
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.cn.starry.cosmetics.util.Vector
 */
package cn.starry.cosmetics.cosmetics.finalKillEffects;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.finalKill.FinalKillCosmetic;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BeefEverywhereEffect
extends FinalKillCosmetic {
    @Override
    public String getDisplayName() {
        return "到处都是牛肉";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "作为你的击杀特效");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.RAW_BEEF);
    }

    @Override
    public int getPrice() {
        return 5000;
    }

    @Override
    public CosmeticRarity getCosmeticRarity() {
        return CosmeticRarity.EPIC;
    }

    @Override
    public int getId() {
        return 29;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(Location location) {
        for (double a = 0.0; a <= Math.PI * 2; a += 0.5235987755982988) {
            this.drop(location, new Vector(Math.cos(a) * 0.1, 0.7, Math.sin(a) * 0.1));
        }
    }

    public void drop(Location location, Vector vector) {
        Material material = Material.LEATHER;
        int rand = new Random().nextInt(3);
        if (rand == 1) {
            material = Material.RAW_BEEF;
        } else if (rand == 2) {
            material = Material.COOKED_BEEF;
        }
        Item item = location.getWorld().dropItemNaturally(location, new ItemStack(material));
        item.setPickupDelay(Integer.MAX_VALUE);
        item.setVelocity(vector);
        Bukkit.getScheduler().runTaskLater((Plugin) Cosmetics.getInstance(), () -> ((Item)item).remove(), 40L);
    }
}

