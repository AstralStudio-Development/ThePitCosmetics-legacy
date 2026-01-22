/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.entity.Chicken
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package cn.starry.cosmetics.cosmetics.finalKillEffects;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.finalKill.FinalKillCosmetic;
import cn.starry.cosmetics.util.MathUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChickenTowerEffect
extends FinalKillCosmetic {
    private final int constCount = 11;

    @Override
    public String getDisplayName() {
        return "小鸡之塔";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "作为你的击杀特效");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.FEATHER);
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
        return 23;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(final Location location) {
        final ArrayList<Chicken> chickens = new ArrayList<Chicken>();
        location.add(0.0, 1.0, 0.0);
        for (int i = 0; i < 11; ++i) {
            Chicken chicken = (Chicken)location.getWorld().spawnEntity(location.clone().add(MathUtil.getNegative(0.0, 0.5), (double)i * 1.5, MathUtil.getNegative(0.0, 0.5)), EntityType.CHICKEN);
            chicken.setBaby();
            chickens.add(chicken);
        }
        new BukkitRunnable(){
            int count = 1;

            public void run() {
                if (this.count >= 11) {
                    this.cancel();
                    if (this.count == 11) {
                        Bukkit.getScheduler().runTaskLater((Plugin)Cosmetics.getInstance(), () -> chickens.forEach(Entity::remove), 15L);
                    }
                    return;
                }
                ((Chicken)chickens.get(this.count - 1)).setPassenger((Entity)chickens.get(this.count));
                location.getWorld().playSound(location, Sound.CHICKEN_EGG_POP, 1.0f, 0.5f);
                ++this.count;
            }
        }.runTaskTimer((Plugin)Cosmetics.getInstance(), 0L, 15L);
    }
}

