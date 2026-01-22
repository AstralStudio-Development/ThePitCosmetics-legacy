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
import java.util.Random;

public class PumpkinPopperEffect
extends FinalKillCosmetic {
    @Override
    public String getDisplayName() {
        return "南瓜收集器";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "作为你的击杀特效");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.PUMPKIN);
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
        return 37;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(Location location) {
        for (int i = 0; i < 15; ++i) {
            this.drop(location);
        }
    }

    public void drop(Location location) {
        Item item = location.getWorld().dropItemNaturally(location, new ItemStack(new Random().nextBoolean() ? Material.PUMPKIN : Material.PUMPKIN_PIE));
        item.setPickupDelay(Integer.MAX_VALUE);
        item.setVelocity(new Vector(MathUtil.getNegative(0.0, 0.2), 0.4, MathUtil.getNegative(0.0, 0.2)));
        Bukkit.getScheduler().runTaskLater((Plugin)Cosmetics.getInstance(), () -> ((Item)item).remove(), 70L);
    }
}

