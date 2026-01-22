package cn.starry.cosmetics.cosmetics.finalKillEffects;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.finalKill.FinalKillCosmetic;
import cn.starry.cosmetics.util.ItemBuilder;
import cn.starry.cosmetics.util.MathUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PinataEffect
extends FinalKillCosmetic {
    @Override
    public String getDisplayName() {
        return "糖果罐";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "作为你的击杀特效");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.INK_SACK).setData(14).build();
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
        return 17;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(final Location location) {
        location.add(0.0, 1.5, 0.0);
        ArrayList<Item> items = new ArrayList<Item>();
        for (int i = 0; i <= 15; ++i) {
            Item item = location.getWorld().dropItemNaturally(location, new ItemBuilder(Material.INK_SACK).setData(i).build());
            item.setVelocity(new Vector(MathUtil.getNegative(0.0, 0.2), 0.2, MathUtil.getNegative(0.0, 0.2)));
            items.add(item);
        }
        new BukkitRunnable(){
            int i = 3;

            public void run() {
                if (this.i <= 0) {
                    this.cancel();
                    return;
                }
                location.getWorld().playSound(location, Sound.ITEM_PICKUP, 1.0f, 1.0f);
                --this.i;
            }
        }.runTaskTimer(Cosmetics.getInstance(), 0L, 5L);
        Bukkit.getScheduler().runTaskLater(Cosmetics.getInstance(), () -> {
            items.forEach(Entity::remove);
            items.clear();
        }, 60L);
    }
}

