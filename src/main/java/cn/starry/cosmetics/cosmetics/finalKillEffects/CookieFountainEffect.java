/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.entity.Item
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.cn.starry.cosmetics.util.Vector
 */
package cn.starry.cosmetics.cosmetics.finalKillEffects;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.finalKill.FinalKillCosmetic;
import cn.starry.cosmetics.util.ItemBuilder;
import cn.starry.cosmetics.util.MathUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

public class CookieFountainEffect
extends FinalKillCosmetic {
    @Override
    public String getDisplayName() {
        return "曲奇喷泉";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "作为你的击杀特效");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.COOKIE);
    }

    @Override
    public int getPrice() {
        return 5000;
    }

    @Override
    public CosmeticRarity getCosmeticRarity() {
        return CosmeticRarity.RARE;
    }

    @Override
    public int getId() {
        return 7;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(final Location location) {
        location.add(0.0, 1.0, 0.0);
        new BukkitRunnable(){
            int count = 40;

            public void run() {
                if (this.count <= 0) {
                    this.cancel();
                    return;
                }
                CookieFountainEffect.this.drop(location);
                --this.count;
            }
        }.runTaskTimer((Plugin)Cosmetics.getInstance(), 0L, 2L);
    }

    public void drop(Location location) {
        Item item = location.getWorld().dropItemNaturally(location, new ItemBuilder(Material.COOKIE).setDisplayName(String.valueOf(System.currentTimeMillis())).build());
        item.setPickupDelay(Integer.MAX_VALUE);
        item.setVelocity(new Vector(MathUtil.getNegative(0.0, 0.3), 0.3, MathUtil.getNegative(0.0, 0.3)));
        location.getWorld().playSound(location, Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
        Bukkit.getScheduler().runTaskLater((Plugin)Cosmetics.getInstance(), () -> ((Item)item).remove(), 40L);
    }
}

