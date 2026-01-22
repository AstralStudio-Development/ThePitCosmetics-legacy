/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.EnumParticle
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Item
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
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
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HatchingEggsEffect
extends FinalKillCosmetic {
    @Override
    public String getDisplayName() {
        return "彩蛋窃手";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "作为你的击杀特效");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.EGG);
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
        return 21;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(Location location) {
        location.add(0.0, 8.0, 0.0);
        final ArrayList<Item> items = new ArrayList<Item>();
        for (int i = 0; i < 10; ++i) {
            Item item = location.getWorld().dropItemNaturally(location.clone().add(MathUtil.getNegative(0.0, 5.0), 0.0, MathUtil.getNegative(0.0, 5.0)), new ItemStack(Material.EGG));
            items.add(item);
        }
        new BukkitRunnable(){

            public void run() {
                if (items.stream().allMatch(Entity::isDead)) {
                    this.cancel();
                    items.clear();
                    return;
                }
                items.forEach(s -> {
                    if (!s.isDead() && s.isOnGround()) {
                        s.remove();
                        Entity e = s.getLocation().getWorld().spawnEntity(s.getLocation(), EntityType.CHICKEN);
                        new ParticleBuilder(e.getLocation(), EnumParticle.CLOUD).play();
                        Bukkit.getScheduler().runTaskLater((Plugin)Cosmetics.getInstance(), () -> ((Entity)e).remove(), 60L);
                    }
                });
            }
        }.runTaskTimer((Plugin)Cosmetics.getInstance(), 0L, 1L);
    }
}

