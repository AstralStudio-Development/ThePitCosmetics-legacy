/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.FireworkEffect
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
 */
package cn.starry.cosmetics.cosmetics.finalKillEffects;

import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.finalKill.FinalKillCosmetic;
import cn.starry.cosmetics.util.InstantFirework;
import cn.starry.cosmetics.util.ItemBuilder;
import cn.starry.cosmetics.util.MathUtil;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class FireworksEffect
extends FinalKillCosmetic {
    @Override
    public String getDisplayName() {
        return "烟花";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "作为你的击杀特效");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.FIREWORK).addTag("id", this.getId()).build();
    }

    @Override
    public int getPrice() {
        return 5000;
    }

    @Override
    public CosmeticRarity getCosmeticRarity() {
        return CosmeticRarity.COMMON;
    }

    @Override
    public int getId() {
        return 2;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(Location bedLocation) {
        bedLocation.add(0.0, 1.0, 0.0);
        new InstantFirework(FireworkEffect.builder().withColor(new Color[]{Color.fromRGB((int) MathUtil.getRandom(0, 255), (int)MathUtil.getRandom(0, 255), (int)MathUtil.getRandom(0, 255)), Color.fromRGB((int)MathUtil.getRandom(0, 255), (int)MathUtil.getRandom(0, 255), (int)MathUtil.getRandom(0, 255)), Color.fromRGB((int)MathUtil.getRandom(0, 255), (int)MathUtil.getRandom(0, 255), (int)MathUtil.getRandom(0, 255))}).build(), bedLocation);
    }
}

