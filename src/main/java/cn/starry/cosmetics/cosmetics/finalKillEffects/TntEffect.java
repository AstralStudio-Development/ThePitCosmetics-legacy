/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.EnumParticle
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.TNTPrimed
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 */
package cn.starry.cosmetics.cosmetics.finalKillEffects;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.finalKill.FinalKillCosmetic;
import cn.starry.cosmetics.util.ParticleBuilder;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public class TntEffect
extends FinalKillCosmetic {
    @Override
    public String getDisplayName() {
        return "TNT";
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
        return CosmeticRarity.COMMON;
    }

    @Override
    public int getId() {
        return 4;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(Location location) {
        location.add(0.0, 3.0, 0.0);
        TNTPrimed tntPrimed = (TNTPrimed)location.getWorld().spawn(location, TNTPrimed.class);
        tntPrimed.setMetadata("FinalKillEffect", (MetadataValue)new FixedMetadataValue((Plugin)Cosmetics.getInstance(), (Object)true));
        tntPrimed.setFuseTicks(40);
        Bukkit.getScheduler().runTaskLater((Plugin) Cosmetics.getInstance(), () -> new ParticleBuilder(tntPrimed.getLocation(), EnumParticle.EXPLOSION_HUGE).setVelocity(2.0f).setCount(5).play(), 40L);
    }
}

