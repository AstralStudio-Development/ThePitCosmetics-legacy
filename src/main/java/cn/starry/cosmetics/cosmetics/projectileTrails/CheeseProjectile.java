/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.entity.Item
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.cn.starry.cosmetics.util.Vector
 */
package cn.starry.cosmetics.cosmetics.projectileTrails;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.projectileTrails.ProjectileTrailCosmetic;
import cn.starry.cosmetics.util.SkullUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

public class CheeseProjectile
extends ProjectileTrailCosmetic {
    final String texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzZjMDFiZmZlY2ZkYWI2ZDNjMGYxYTdjNmRmNmFhMTkzNmYyYWE3YTUxYjU0YTRkMzIzZTFjYWNiYzUzOSJ9fX0=";

    @Override
    public String getDisplayName() {
        return "芝士";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "弹射物轨迹");
    }

    @Override
    public ItemStack getIcon() {
        return SkullUtil.makeTextureSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzZjMDFiZmZlY2ZkYWI2ZDNjMGYxYTdjNmRmNmFhMTkzNmYyYWE3YTUxYjU0YTRkMzIzZTFjYWNiYzUzOSJ9fX0=");
    }

    @Override
    public int getPrice() {
        return 5000;
    }

    @Override
    public CosmeticRarity getCosmeticRarity() {
        return CosmeticRarity.LEGENDARY;
    }

    @Override
    public int getId() {
        return 41;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runParticleEffect(Location loc) {
        Item c = loc.getWorld().dropItem(loc, this.getIcon());
        c.setVelocity(new Vector());
        c.setPickupDelay(Integer.MAX_VALUE);
        Bukkit.getScheduler().runTaskLater((Plugin) Cosmetics.getInstance(), () -> ((Item)c).remove(), 15L);
    }
}

