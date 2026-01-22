/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Item
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.cn.starry.cosmetics.util.Vector
 */
package cn.starry.cosmetics.cosmetics.finalKillEffects;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.finalKill.FinalKillCosmetic;
import cn.starry.cosmetics.util.SkullUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GiftExplosionEffect
extends FinalKillCosmetic {
    final String texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU0MTlmY2U1MDZhNDk1MzQzYTFkMzY4YTcxZDIyNDEzZjA4YzZkNjdjYjk1MWQ2NTZjZDAzZjgwYjRkM2QzIn19fQ==";

    @Override
    public String getDisplayName() {
        return "礼盒爆炸";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "作为你的击杀特效");
    }

    @Override
    public ItemStack getIcon() {
        return SkullUtil.makeTextureSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU0MTlmY2U1MDZhNDk1MzQzYTFkMzY4YTcxZDIyNDEzZjA4YzZkNjdjYjk1MWQ2NTZjZDAzZjgwYjRkM2QzIn19fQ==");
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
        return 27;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(Location location) {
        ArrayList<Item> items = new ArrayList<Item>();
        for (double a = 0.0; a <= Math.PI * 2; a += 0.5235987755982988) {
            Item item = location.getWorld().dropItemNaturally(location, this.getIcon());
            item.setPickupDelay(Integer.MAX_VALUE);
            double x = Math.cos(a) * 0.1;
            double z = Math.sin(a) * 0.1;
            item.setVelocity(new Vector(x, 0.5, z));
            items.add(item);
        }
        Bukkit.getScheduler().runTaskLater((Plugin)Cosmetics.getInstance(), () -> items.forEach(Entity::remove), 40L);
    }
}

