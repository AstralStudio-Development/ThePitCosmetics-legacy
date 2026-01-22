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
import cn.starry.cosmetics.util.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class LightningStrike
extends FinalKillCosmetic {
    @Override
    public String getDisplayName() {
        return "闪电强袭";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "作为你的击杀特效");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.GLASS).addTag("id", this.getId()).build();
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
        return 3;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(Location bedLocation) {
        bedLocation.getWorld().strikeLightningEffect(bedLocation);
    }
}

