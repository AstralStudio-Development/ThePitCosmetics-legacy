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

public class MerryProjectile
extends ProjectileTrailCosmetic {
    private int r = 5;
    private int w = -5;
    private boolean rb = false;
    private boolean wb = true;

    @Override
    public String getDisplayName() {
        return "圣诞";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "弹射物轨迹");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.RED_MUSHROOM);
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
    public void runParticleEffect(Location loc) {
        ParticleBuilder.playColoredDust(loc.clone().add(0.0, (double)this.r / 10.0, 0.0), Color.RED);
        ParticleBuilder.playColoredDust(loc.clone().add(0.0, (double)this.w / 10.0, 0.0), Color.WHITE);
        if (this.r >= 5) {
            this.rb = false;
        } else if (this.r <= -5) {
            this.rb = true;
        }
        this.r = this.rb ? ++this.r : --this.r;
        if (this.w >= 5) {
            this.wb = false;
        } else if (this.w <= -5) {
            this.wb = true;
        }
        this.w = this.wb ? ++this.w : --this.w;
    }
}

