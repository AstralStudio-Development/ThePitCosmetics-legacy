/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.Material
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.Projectile
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.cn.starry.cosmetics.util.Vector
 */
package cn.starry.cosmetics.cosmetics.projectileTrails;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.projectileTrails.ProjectileTrailCosmetic;
import cn.starry.cosmetics.util.ParticleBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

public class JackOLanternProjectile
extends ProjectileTrailCosmetic {
    public JackOLanternProjectile() {
        super(true);
    }

    @Override
    public String getDisplayName() {
        return "南瓜灯";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "弹射物轨迹");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.JACK_O_LANTERN);
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
        return 31;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runParticleEffect(final Projectile p) {
        new BukkitRunnable(){
            private final Item i;
            private boolean init;
            {
                this.i = p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.JACK_O_LANTERN));
                this.init = false;
            }

            public void run() {
                if (!this.init) {
                    p.setMetadata("CosmeticsProjectile", (MetadataValue)new FixedMetadataValue((Plugin) Cosmetics.getInstance(), (Object)true));
                    this.i.setVelocity(new Vector());
                    this.i.setPickupDelay(Integer.MAX_VALUE);
                    p.setPassenger((Entity)this.i);
                    this.init = true;
                }
                if (p.isDead() || p.doesBounce()) {
                    this.cancel();
                    this.i.remove();
                    return;
                }
                ParticleBuilder.playColoredDust(p.getLocation(), Color.ORANGE);
            }
        }.runTaskTimer((Plugin)Cosmetics.getInstance(), 0L, 1L);
    }
}

