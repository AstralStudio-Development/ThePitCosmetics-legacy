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
package cn.starry.cosmetics.cosmetics.projectileTrails;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.projectileTrails.ProjectileTrailCosmetic;
import cn.starry.cosmetics.util.ItemBuilder;
import cn.starry.cosmetics.util.MathUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

public class SnowballRainProjectile
extends ProjectileTrailCosmetic {
    @Override
    public String getDisplayName() {
        return "雪球雨";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "弹射物轨迹");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.SNOW_BALL);
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
        return 21;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runParticleEffect(Location loc) {
        Item i = loc.getWorld().dropItem(loc, new ItemBuilder(Material.SNOW_BALL).setDisplayName(String.valueOf(System.currentTimeMillis())).build());
        i.setPickupDelay(Integer.MAX_VALUE);
        i.setVelocity(new Vector(MathUtil.getNegative(0.0, 0.1), 0.1, MathUtil.getNegative(0.0, 0.1)));
        Bukkit.getScheduler().runTaskLater((Plugin) Cosmetics.getInstance(), () -> ((Item)i).remove(), 60L);
    }
}

