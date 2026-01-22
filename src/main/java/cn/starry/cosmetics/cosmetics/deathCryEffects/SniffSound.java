/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package cn.starry.cosmetics.cosmetics.deathCryEffects;

import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.deathCries.DeathCryEffect;
import cn.starry.cosmetics.util.Misc;
import cn.starry.cosmetics.util.SkullUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class SniffSound
        extends DeathCryEffect {
    final String texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzE0OTY4YWM1YWYzMTQ2ODI2ZmEyYjBkNGRkMTE0ZmRhMTk3ZjhiMjhmNDc1MDU1M2YzZjg4ODM2YTIxZmFjOSJ9fX0=";

    @Override
    public String getDisplayName() {
        return "闻";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7当你死亡时", "&7播放闻的亡语");
    }

    @Override
    public ItemStack getIcon() {
        return SkullUtil.makeTextureSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzE0OTY4YWM1YWYzMTQ2ODI2ZmEyYjBkNGRkMTE0ZmRhMTk3ZjhiMjhmNDc1MDU1M2YzZjg4ODM2YTIxZmFjOSJ9fX0=");
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
        return 25;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runSound(Location location) {
        Misc.play(location, 5.0, "mob.rabbit.idle", 1.0f, 1.0f);
    }

    @Override
    public void runPreview(Player player) {
        player.playSound(player.getLocation(), "mob.rabbit.idle", 1.0f, 1.0f);
    }
}

