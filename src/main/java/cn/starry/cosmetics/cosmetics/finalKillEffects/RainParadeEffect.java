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
import cn.starry.cosmetics.util.ParticleBuilder;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class RainParadeEffect
extends FinalKillCosmetic {
    @Override
    public String getDisplayName() {
        return "风雨交加";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "作为你的击杀特效");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.WATER_BUCKET);
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
        return 24;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(final Location location) {
        location.add(0.0, 3.0, 0.0);
        new BukkitRunnable(){
            int i = 20;

            public void run() {
                if (this.i <= 0) {
                    this.cancel();
                } else if (this.i > 1) {
                    for (int k = 0; k < 5; ++k) {
                        for (int j = 0; j < 5; ++j) {
                            for (int l = 0; l < 5; ++l) {
                                new ParticleBuilder(location.clone().add((double)j * 0.3, (double)k * 0.3, (double)l * 0.3), EnumParticle.CLOUD).play();
                                new ParticleBuilder(location.clone().subtract((double)j * 0.3, (double)k * -0.3, (double)l * 0.3), EnumParticle.CLOUD).play();
                                new ParticleBuilder(location.clone().add((double)j * 0.3, (double)k * 0.3, (double)l * -0.3), EnumParticle.CLOUD).play();
                                new ParticleBuilder(location.clone().subtract((double)j * 0.3, (double)k * -0.3, (double)l * -0.3), EnumParticle.CLOUD).play();
                            }
                        }
                    }
                } else {
                    for (int k = 0; k < 3; ++k) {
                        for (int j = 0; j < 3; ++j) {
                            for (int l = 0; l < 3; ++l) {
                                new ParticleBuilder(location.clone().add((double)j * 0.5, (double)k * 0.5, (double)l * 0.5), EnumParticle.DRIP_WATER).play();
                                new ParticleBuilder(location.clone().subtract((double)j * 0.5, (double)k * -0.5, (double)l * 0.5), EnumParticle.DRIP_WATER).play();
                                new ParticleBuilder(location.clone().add((double)j * 0.5, (double)k * 0.5, (double)l * -0.5), EnumParticle.DRIP_WATER).play();
                                new ParticleBuilder(location.clone().subtract((double)j * 0.5, (double)k * -0.5, (double)l * -0.5), EnumParticle.DRIP_WATER).play();
                            }
                        }
                    }
                }
                --this.i;
            }
        }.runTaskTimer((Plugin)Cosmetics.getInstance(), 0L, 5L);
    }
}

