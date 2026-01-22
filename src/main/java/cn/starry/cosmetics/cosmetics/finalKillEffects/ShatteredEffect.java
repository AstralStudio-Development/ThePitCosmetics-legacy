/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
 */
package cn.starry.cosmetics.cosmetics.finalKillEffects;

import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.finalKill.FinalKillCosmetic;
import cn.starry.cosmetics.util.AnimationUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ShatteredEffect
extends FinalKillCosmetic {
    @Override
    public String getDisplayName() {
        return "粉碎";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "作为你的击杀特效");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.GLASS_BOTTLE);
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
        return 36;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(Location location) {
        AnimationUtil.playBlockBreak(location, Material.PACKED_ICE);
        AnimationUtil.playBlockBreak(location.clone().add(0.0, 1.0, 0.0), Material.PACKED_ICE);
    }
}

