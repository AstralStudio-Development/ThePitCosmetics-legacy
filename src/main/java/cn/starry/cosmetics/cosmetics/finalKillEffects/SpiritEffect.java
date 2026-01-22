/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Wolf
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 */
package cn.starry.cosmetics.cosmetics.finalKillEffects;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.finalKill.FinalKillCosmetic;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public class SpiritEffect
extends FinalKillCosmetic {
    @Override
    public String getDisplayName() {
        return "灵魂";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "作为你的击杀特效");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.SPIDER_EYE);
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
        return 28;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(Location location) {
        Wolf wolf = (Wolf)location.getWorld().spawnEntity(location, EntityType.WOLF);
        Bukkit.getScheduler().runTaskLater((Plugin)Cosmetics.getInstance(), () -> ((Wolf)wolf).remove(), 120L);
    }
}

