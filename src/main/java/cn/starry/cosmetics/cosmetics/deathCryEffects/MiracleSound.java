/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Sound
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package cn.starry.cosmetics.cosmetics.deathCryEffects;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.deathCries.DeathCryEffect;
import cn.starry.cosmetics.util.SkullUtil;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class MiracleSound
        extends DeathCryEffect {
    final String texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2UzYTUwYjFjYmEyNmY3ZGEyZGE1YmE5YzBiNjE3OWEzYmM4ZWMzYmZjZmJiZmUxMWVlYWI5NTdiYTc0In19fQ==";

    @Override
    public String getDisplayName() {
        return "奇迹";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7当你死亡时", "&7播放奇迹的亡语");
    }

    @Override
    public ItemStack getIcon() {
        return SkullUtil.makeTextureSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2UzYTUwYjFjYmEyNmY3ZGEyZGE1YmE5YzBiNjE3OWEzYmM4ZWMzYmZjZmJiZmUxMWVlYWI5NTdiYTc0In19fQ==");
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

    private void play(float pitch, Player player) {
        player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1.0f, pitch);
    }

    private void play(float pitch, Location location) {
        location.getWorld().playSound(location, Sound.SUCCESSFUL_HIT, 1.0f, pitch);
    }

    @Override
    public void runSound(final Location location) {
        new BukkitRunnable(){
            int i = 40;
            float pitch = 1.0476191f;

            public void run() {
                if (this.i <= 0) {
                    this.cancel();
                    return;
                }
                if (this.i >= 21) {
                    switch (this.i) {
                        case 38: {
                            this.pitch = 1.2539682f;
                            MiracleSound.this.play(this.pitch, location);
                            break;
                        }
                        case 36: {
                            this.pitch = 1.4126984f;
                            MiracleSound.this.play(this.pitch, location);
                            break;
                        }
                        case 34: {
                            this.pitch = 1.5873016f;
                            MiracleSound.this.play(this.pitch, location);
                            break;
                        }
                        case 32: {
                            this.pitch = 1.6666666f;
                            MiracleSound.this.play(this.pitch, location);
                            break;
                        }
                        case 21:
                        case 23:
                        case 26:
                        case 29: {
                            this.pitch = 1.8730159f;
                            MiracleSound.this.play(this.pitch, location);
                        }
                    }
                } else {
                    switch (this.i) {
                        case 14:
                        case 16:
                        case 18:
                        case 20: {
                            this.pitch = 1.8730159f;
                            MiracleSound.this.play(this.pitch, location);
                            break;
                        }
                        case 13:
                        case 15:
                        case 17:
                        case 19: {
                            this.pitch = 2.1111112f;
                            MiracleSound.this.play(this.pitch, location);
                        }
                    }
                }
                --this.i;
            }
        }.runTaskTimer((Plugin) Cosmetics.getInstance(), 0L, 1L);
    }

    @Override
    public void runPreview(final Player player) {
        new BukkitRunnable(){
            int i = 40;
            float pitch = 1.0476191f;

            public void run() {
                if (this.i <= 0) {
                    this.cancel();
                    return;
                }
                if (this.i >= 21) {
                    switch (this.i) {
                        case 38: {
                            this.pitch = 1.2539682f;
                            MiracleSound.this.play(this.pitch, player);
                            break;
                        }
                        case 36: {
                            this.pitch = 1.4126984f;
                            MiracleSound.this.play(this.pitch, player);
                            break;
                        }
                        case 34: {
                            this.pitch = 1.5873016f;
                            MiracleSound.this.play(this.pitch, player);
                            break;
                        }
                        case 32: {
                            this.pitch = 1.6666666f;
                            MiracleSound.this.play(this.pitch, player);
                            break;
                        }
                        case 21:
                        case 23:
                        case 26:
                        case 29: {
                            this.pitch = 1.8730159f;
                            MiracleSound.this.play(this.pitch, player);
                        }
                    }
                } else {
                    switch (this.i) {
                        case 14:
                        case 16:
                        case 18:
                        case 20: {
                            this.pitch = 1.8730159f;
                            MiracleSound.this.play(this.pitch, player);
                            break;
                        }
                        case 13:
                        case 15:
                        case 17:
                        case 19: {
                            this.pitch = 2.1111112f;
                            MiracleSound.this.play(this.pitch, player);
                        }
                    }
                }
                --this.i;
            }
        }.runTaskTimer((Plugin)Cosmetics.getInstance(), 0L, 1L);
    }
}

