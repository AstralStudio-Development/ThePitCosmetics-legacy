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

public class HeartAuraEffect
extends FinalKillCosmetic {
    @Override
    public String getDisplayName() {
        return "爱心光环";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "作为你的击杀特效");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.APPLE);
    }

    @Override
    public int getPrice() {
        return 5000;
    }

    @Override
    public CosmeticRarity getCosmeticRarity() {
        return CosmeticRarity.RARE;
    }

    @Override
    public int getId() {
        return 5;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(final Location location) {
        location.add(0.0, 1.0, 0.0);
        new BukkitRunnable(){
            int time = 20;

            public void run() {
                if (this.time < 1) {
                    this.cancel();
                    return;
                }
                new ParticleBuilder(location, EnumParticle.HEART).play();
                new ParticleBuilder(location.clone().add(0.3, 0.0, 0.0), EnumParticle.HEART).play();
                new ParticleBuilder(location.clone().add(0.0, 0.0, 0.3), EnumParticle.HEART).play();
                new ParticleBuilder(location.clone().add(0.3, 0.0, 0.3), EnumParticle.HEART).play();
                new ParticleBuilder(location.clone().subtract(0.3, 0.0, 0.0), EnumParticle.HEART).play();
                new ParticleBuilder(location.clone().subtract(0.0, 0.0, 0.3), EnumParticle.HEART).play();
                new ParticleBuilder(location.clone().subtract(0.3, 0.0, 0.3), EnumParticle.HEART).play();
                new ParticleBuilder(location, EnumParticle.HEART).play();
                --this.time;
            }
        }.runTaskTimer((Plugin)Cosmetics.getInstance(), 0L, 10L);
    }
}

