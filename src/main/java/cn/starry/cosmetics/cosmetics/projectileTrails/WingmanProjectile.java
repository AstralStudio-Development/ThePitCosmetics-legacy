/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.EnumParticle
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.entity.Bat
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
import cn.starry.cosmetics.util.ParticleBuilder;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class WingmanProjectile
extends ProjectileTrailCosmetic {
    public WingmanProjectile() {
        super(true);
    }

    @Override
    public String getDisplayName() {
        return "僚机";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "弹射物轨迹");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.COAL);
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
        return 33;
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
                    Bat b = (Bat)p.getWorld().spawn(p.getLocation(), Bat.class);
                    b.setMetadata("CosmeticsProjectileEntity", (MetadataValue)new FixedMetadataValue((Plugin)Cosmetics.getInstance(), (Object)true));
                    new ParticleBuilder(p.getLocation(), EnumParticle.SMOKE_LARGE).play();
                    Bukkit.getScheduler().runTaskLater((Plugin)Cosmetics.getInstance(), () -> {
                        new ParticleBuilder(b.getLocation(), EnumParticle.SMOKE_LARGE).play();
                        b.remove();
                    }, 30L);
                }, 10L);
            }
        }.runTaskTimer((Plugin)Cosmetics.getInstance(), 0L, 5L);
    }
}

