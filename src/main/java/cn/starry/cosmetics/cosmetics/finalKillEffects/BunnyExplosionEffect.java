/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.EnumParticle
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Rabbit
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.cn.starry.cosmetics.util.Vector
 */
package cn.starry.cosmetics.cosmetics.finalKillEffects;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.finalKill.FinalKillCosmetic;
import cn.starry.cosmetics.util.MathUtil;
import cn.starry.cosmetics.util.ParticleBuilder;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Rabbit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

public class BunnyExplosionEffect
extends FinalKillCosmetic {
    @Override
    public String getDisplayName() {
        return "兔兔爆炸";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "作为你的击杀特效");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.TNT);
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
        return 22;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(Location location) {
        for (int i = 0; i < 20; ++i) {
            this.spawn(location);
        }
        Bukkit.getScheduler().runTaskLater((Plugin) Cosmetics.getInstance(), () -> location.getWorld().playSound(location, Sound.EXPLODE, 1.0f, 1.0f), 40L);
    }

    public void spawn(Location location) {
        Rabbit rabbit = (Rabbit)location.getWorld().spawnEntity(location, EntityType.RABBIT);
        rabbit.setVelocity(new Vector(MathUtil.getNegative(0.1, 0.5), 0.5, MathUtil.getNegative(0.1, 0.5)));
        Bukkit.getScheduler().runTaskLater((Plugin)Cosmetics.getInstance(), () -> {
            rabbit.remove();
            new ParticleBuilder(rabbit.getLocation(), EnumParticle.SMOKE_LARGE).setVelocity(0.2f).setCount(100).play();
        }, 40L);
    }
}

