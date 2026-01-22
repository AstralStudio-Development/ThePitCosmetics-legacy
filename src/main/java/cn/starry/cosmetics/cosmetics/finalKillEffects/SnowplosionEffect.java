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
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SnowplosionEffect
extends FinalKillCosmetic {
    @Override
    public String getDisplayName() {
        return "雪球爆炸";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "作为你的击杀特效");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.SNOW);
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
        return 38;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(Location location) {
        location.add(0.0, 1.5, 0.0);
        final ArrayList<Item> snowballs = new ArrayList<Item>();
        final ArrayList<Block> blocks = new ArrayList<Block>();  // Change to specific Block type for safety
        for (int i = 0; i < 75; ++i) {
            Item snowball = location.getWorld().dropItemNaturally(location, new ItemStack(Material.SNOW_BALL));
            snowball.setPickupDelay(Integer.MAX_VALUE);
            snowball.setVelocity(new Vector(MathUtil.getNegative(0.0, 0.5), MathUtil.getRandom(0.0, 0.7), MathUtil.getNegative(0.0, 0.5)));
            snowballs.add(snowball);
        }
        new BukkitRunnable(){
            public void run() {
                if (snowballs.stream().allMatch(Entity::isDead)) {
                    this.cancel();
                    snowballs.clear();
                    // Schedule a cleanup task to run a little bit after all snowballs are dead to ensure we clear all the blocks.
                    Bukkit.getScheduler().runTaskLater(Cosmetics.getInstance(), () -> {
                        blocks.forEach(b -> {
                            if (b.getType().equals(Material.SNOW)) {
                                b.setType(Material.AIR);  // Replace snow blocks with air
                            }
                        });
                        blocks.clear();
                    }, 20L); // Delay in server ticks, adjust if necessary
                    return;
                }
                snowballs.forEach(s -> {
                    if (!s.isDead() && s.isOnGround()) {
                        s.remove();
                        Block block = s.getLocation().getBlock();
                        if (block.getType().equals(Material.AIR)) {
                            block.setType(Material.SNOW);
                            blocks.add(block);
                        }
                    }
                });
            }
        }.runTaskTimer(Cosmetics.getInstance(), 0L, 1L);
        Bukkit.getScheduler().runTaskLater(Cosmetics.getInstance(), () -> {
            location.getWorld().playSound(location, Sound.DIG_SNOW, 1.0f, 1.0f);
            blocks.forEach(b -> {
                new ParticleBuilder(b.getLocation(), EnumParticle.FIREWORKS_SPARK).setCount(2).setVelocity(0.1f).play();
            });
        }, 80L);
    }
}

