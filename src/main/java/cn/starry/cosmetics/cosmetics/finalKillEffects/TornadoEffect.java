/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.EnumParticle
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package cn.starry.cosmetics.cosmetics.finalKillEffects;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.finalKill.FinalKillCosmetic;
import cn.starry.cosmetics.util.ItemBuilder;
import cn.starry.cosmetics.util.ParticleBuilder;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class TornadoEffect
extends FinalKillCosmetic {
    @Override
    public String getDisplayName() {
        return "龙卷风";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "作为你的击杀特效");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.STRING).addTag("id", this.getId()).build();
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
        return 30;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(final Location bedLocation) {
        bedLocation.add(0.0, 0.3, 0.0);
        new BukkitRunnable(){
            int value = 10;
            final int maxHeight = 8;
            final int lines = 15;
            final double maxRadius = 4.0;
            final double heightIncrement = 0.5;
            final double radiusIncrement = 0.5;
            double angle = 0.0;

            public void run() {
                if (this.value <= 0) {
                    this.cancel();
                    return;
                }
                new ParticleBuilder(bedLocation, EnumParticle.SMOKE_NORMAL).setCount(5).play();
                for (int l = 0; l < 15; ++l) {
                    for (double y = 0.0; y < 8.0; y += 0.5) {
                        double radius = y * 0.5;
                        double a = Math.toRadians(24.0 * (double)l + y * 25.0 - this.angle);
                        double x = Math.cos(a) * radius;
                        double z = Math.sin(a) * radius;
                        new ParticleBuilder(bedLocation.clone().add(x, y, z), EnumParticle.FIREWORKS_SPARK).play();
                    }
                    this.angle += 1.0;
                }
                --this.value;
            }
        }.runTaskTimer((Plugin) Cosmetics.getInstance(), 0L, 10L);
    }
}

