/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.inventory.ItemStack
 */
package cn.starry.cosmetics.cosmetics.projectileTrails;

import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.projectileTrails.ProjectileTrailCosmetic;
import cn.starry.cosmetics.util.ParticleBuilder;
import cn.starry.cosmetics.util.SkullUtil;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class BeeProjectile
extends ProjectileTrailCosmetic {
    final String texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTQ3MzIyZjgzMWUzYzE2OGNmYmQzZTI4ZmU5MjUxNDRiMjYxZTc5ZWIzOWM3NzEzNDlmYWM1NWE4MTI2NDczIn19fQ==";
    private boolean b = false;

    @Override
    public String getDisplayName() {
        return "蜜蜂";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "弹射物轨迹");
    }

    @Override
    public ItemStack getIcon() {
        return SkullUtil.makeTextureSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTQ3MzIyZjgzMWUzYzE2OGNmYmQzZTI4ZmU5MjUxNDRiMjYxZTc5ZWIzOWM3NzEzNDlmYWM1NWE4MTI2NDczIn19fQ==");
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
        return 29;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runParticleEffect(Location loc) {
        ParticleBuilder.playColoredSpell(loc, this.b ? Color.YELLOW : Color.BLACK);
        this.b = !this.b;
    }
}

