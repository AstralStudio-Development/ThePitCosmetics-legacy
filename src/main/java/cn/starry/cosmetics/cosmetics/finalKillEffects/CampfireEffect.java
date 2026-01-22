/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.EnumParticle
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.cn.starry.cosmetics.util.EulerAngle
 */
package cn.starry.cosmetics.cosmetics.finalKillEffects;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.finalKill.FinalKillCosmetic;
import cn.starry.cosmetics.util.ParticleBuilder;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CampfireEffect
extends FinalKillCosmetic {
    @Override
    public String getDisplayName() {
        return "篝火";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "作为你的击杀特效");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.LOG);
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
        return 8;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(final Location location) {
        final ArrayList<ArmorStand> armorStands = new ArrayList<ArmorStand>();
        location.setPitch(0.0f);
        location.setYaw(0.0f);
        for (int i = 0; i < 12; ++i) {
            location.setYaw((float)(i * 30));
            ArmorStand armorStand = (ArmorStand)location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
            armorStand.setGravity(false);
            armorStand.setArms(true);
            armorStand.setBasePlate(false);
            armorStand.setSmall(true);
            armorStand.setVisible(false);
            armorStand.setItemInHand(new ItemStack(Material.STICK));
            armorStand.setRightArmPose(new EulerAngle(2.0, 2.0, 1.5));
            armorStands.add(armorStand);
        }
        new BukkitRunnable(){
            int time = 10;

            public void run() {
                if (this.time <= 0) {
                    this.cancel();
                    armorStands.forEach(Entity::remove);
                    return;
                }
                new ParticleBuilder(location.clone().add(0.0, 0.6, 0.0), EnumParticle.FLAME).setCount(5).setVelocity(0.01f).play();
                new ParticleBuilder(location.clone().add(0.0, 1.5, 0.0), EnumParticle.CLOUD).play();
                new ParticleBuilder(location.clone().add(0.0, 1.5, 0.0), EnumParticle.SMOKE_LARGE).play();
                --this.time;
            }
        }.runTaskTimer((Plugin)Cosmetics.getInstance(), 0L, 10L);
    }
}

