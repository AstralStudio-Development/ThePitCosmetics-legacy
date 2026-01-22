/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.entity.Chicken
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.Projectile
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package cn.starry.cosmetics.cosmetics.projectileTrails;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.projectileTrails.ProjectileTrailCosmetic;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Item;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class PeepProjectile
extends ProjectileTrailCosmetic {
    public PeepProjectile() {
        super(true);
    }

    @Override
    public String getDisplayName() {
        return "哔！";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "弹射物轨迹");
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
        return CosmeticRarity.LEGENDARY;
    }

    @Override
    public int getId() {
        return 39;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runParticleEffect(final Projectile p) {
        new BukkitRunnable(){
            private boolean init = false;

            public void run() {
                if (!this.init) {
                    p.setMetadata("CosmeticsProjectile", (MetadataValue)new FixedMetadataValue((Plugin) Cosmetics.getInstance(), (Object)true));
                    this.init = true;
                }
                if (p.isDead() || p.doesBounce()) {
                    this.cancel();
                    return;
                }
                Bukkit.getScheduler().runTaskLater((Plugin)Cosmetics.getInstance(), () -> {
                    Item i = p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.EGG));
                    i.setMetadata("CosmeticsProjectileEntity", (MetadataValue)new FixedMetadataValue((Plugin)Cosmetics.getInstance(), (Object)true));
                    Bukkit.getScheduler().runTaskLater((Plugin)Cosmetics.getInstance(), () -> {
                        Chicken c = (Chicken)i.getWorld().spawn(i.getLocation(), Chicken.class);
                        i.remove();
                        Bukkit.getScheduler().runTaskLater((Plugin)Cosmetics.getInstance(), () -> ((Chicken)c).remove(), 15L);
                    }, 10L);
                }, 10L);
            }
        }.runTaskTimer((Plugin)Cosmetics.getInstance(), 0L, 5L);
    }
}

