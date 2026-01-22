/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
 */
package cn.starry.cosmetics.cosmetics.projectileTrails;

import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.projectileTrails.ProjectileTrailCosmetic;
import cn.starry.cosmetics.util.ParticleBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class RainbowProjectile
extends ProjectileTrailCosmetic {
    private final List<Color> colors = Arrays.asList(Color.fromRGB((int)255, (int)0, (int)0), Color.fromRGB((int)255, (int)127, (int)0), Color.fromRGB((int)255, (int)255, (int)0), Color.fromRGB((int)0, (int)255, (int)0), Color.fromRGB((int)0, (int)0, (int)255), Color.fromRGB((int)46, (int)43, (int)95), Color.fromRGB((int)139, (int)0, (int)255));
    private int lastIndex = 0;

    @Override
    public String getDisplayName() {
        return "彩虹";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "弹射物轨迹");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.BEACON);
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
        return 28;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runParticleEffect(Location loc) {
        if (this.lastIndex >= this.colors.size()) {
            this.lastIndex = 0;
        }
        ParticleBuilder.playColoredDust(loc, this.colors.get(this.lastIndex++));
    }
}

