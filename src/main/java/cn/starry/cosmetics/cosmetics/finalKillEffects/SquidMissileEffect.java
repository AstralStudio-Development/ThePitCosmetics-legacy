/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.EnumParticle
 *  org.bukkit.Color
 *  org.bukkit.FireworkEffect
 *  org.bukkit.FireworkEffect$Type
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Squid
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.cn.starry.cosmetics.util.Vector
 */
package cn.starry.cosmetics.cosmetics.finalKillEffects;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.finalKill.FinalKillCosmetic;
import cn.starry.cosmetics.util.InstantFirework;
import cn.starry.cosmetics.util.ItemBuilder;
import cn.starry.cosmetics.util.Misc;
import cn.starry.cosmetics.util.ParticleBuilder;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Squid;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

public class SquidMissileEffect
extends FinalKillCosmetic {
    @Override
    public String getDisplayName() {
        return "鱿鱼导弹";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "作为你的击杀特效");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.MONSTER_EGG).setData(94).addTag("id", this.getId()).build();
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
        return 1;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(final Location bedLocation) {
        bedLocation.add(0.0, 2.0, 0.0);
        final Squid squid = (Squid)bedLocation.getWorld().spawnEntity(bedLocation, EntityType.SQUID);
        Misc.noAI(squid);
        squid.setVelocity(new Vector(0, 0, 0));
        squid.setCanPickupItems(false);
        squid.setCustomNameVisible(false);
        squid.setRemoveWhenFarAway(true);
        new BukkitRunnable(){
            int y = 0;
            float pitch = 0.5f;

            public void run() {
                if (this.y == 30) {
                    this.cancel();
                    new InstantFirework(FireworkEffect.builder().withColor(Color.BLACK).with(FireworkEffect.Type.BALL_LARGE).build(), squid.getLocation());
                    squid.remove();
                    return;
                }
                squid.teleport(squid.getLocation().add(0.0, (double)this.y / 10.0, 0.0));
                if (this.y % 2 == 0) {
                    new ParticleBuilder(squid.getLocation(), EnumParticle.FLAME).play();
                    Misc.play(bedLocation, 10.0, Sound.ITEM_PICKUP, 1.0f, this.pitch);
                    this.pitch += 0.1f;
                }
                ++this.y;
            }
        }.runTaskTimer(Cosmetics.getInstance(), 0L, 1L);
    }
}

