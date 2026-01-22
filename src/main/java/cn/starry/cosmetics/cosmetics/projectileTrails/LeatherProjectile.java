/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.Item
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.inventory.meta.LeatherArmorMeta
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.cn.starry.cosmetics.util.Vector
 */
package cn.starry.cosmetics.cosmetics.projectileTrails;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.projectileTrails.ProjectileTrailCosmetic;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LeatherProjectile
extends ProjectileTrailCosmetic {
    private final Random r = new Random();
    private final List<ItemStack> content = Arrays.asList(new ItemStack(Material.LEATHER_CHESTPLATE), new ItemStack(Material.LEATHER_BOOTS), new ItemStack(Material.LEATHER_HELMET), new ItemStack(Material.LEATHER_LEGGINGS));

    @Override
    public String getDisplayName() {
        return "让我们在这里放个皮革";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "弹射物轨迹");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.LEATHER_CHESTPLATE);
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
        return 24;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runParticleEffect(Location loc) {
        ItemStack it = this.content.get(this.r.nextInt(this.content.size()));
        LeatherArmorMeta meta = (LeatherArmorMeta)it.getItemMeta();
        meta.setColor(Color.fromRGB((int)this.r.nextInt(255), (int)this.r.nextInt(255), (int)this.r.nextInt(255)));
        it.setItemMeta((ItemMeta)meta);
        Item i = loc.getWorld().dropItem(loc, it);
        i.setPickupDelay(Integer.MAX_VALUE);
        i.setVelocity(new Vector());
        Bukkit.getScheduler().runTaskLater((Plugin) Cosmetics.getInstance(), () -> ((Item)i).remove(), 15L);
    }
}

