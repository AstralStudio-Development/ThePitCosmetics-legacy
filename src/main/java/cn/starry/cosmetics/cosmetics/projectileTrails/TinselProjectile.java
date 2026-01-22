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

public class TinselProjectile
extends ProjectileTrailCosmetic {
    private int r = 5;
    private int g = -5;
    private boolean rb = false;
    private boolean gb = true;

    @Override
    public String getDisplayName() {
        return "圣诞茜草";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "弹射物轨迹");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.SUGAR_CANE);
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
    public void runParticleEffect(Location loc) {
        ParticleBuilder.playColoredDust(loc.clone().add(0.0, (double)this.r / 10.0, 0.0), Color.RED);
        ParticleBuilder.playColoredDust(loc.clone().add(0.0, (double)this.g / 10.0, 0.0), Color.fromRGB((int)26, (int)158, (int)0));
        if (this.r >= 5) {
            this.rb = false;
        } else if (this.r <= -5) {
            this.rb = true;
        }
        this.r = this.rb ? ++this.r : --this.r;
        if (this.g >= 5) {
            this.gb = false;
        } else if (this.g <= -5) {
            this.gb = true;
        }
        this.g = this.gb ? ++this.g : --this.g;
    }
}

