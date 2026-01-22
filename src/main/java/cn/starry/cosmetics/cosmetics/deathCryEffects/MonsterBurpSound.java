/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Sound
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package cn.starry.cosmetics.cosmetics.deathCryEffects;

import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.deathCries.DeathCryEffect;
import cn.starry.cosmetics.util.SkullUtil;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class MonsterBurpSound
        extends DeathCryEffect {
    final String texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg3OTM0NTY1YmY1MjJmNmY0NzI2Y2RmZTEyNzEzN2JlMTFkMzdjMzEwZGIzNGQ4YzcwMjUzMzkyYjVmZjViIn19fQ==";

    @Override
    public String getDisplayName() {
        return "怪物打嗝";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7当你死亡时", "&7播放怪物打嗝的亡语");
    }

    @Override
    public ItemStack getIcon() {
        return SkullUtil.makeTextureSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg3OTM0NTY1YmY1MjJmNmY0NzI2Y2RmZTEyNzEzN2JlMTFkMzdjMzEwZGIzNGQ4YzcwMjUzMzkyYjVmZjViIn19fQ==");
    }

    @Override
    public int getPrice() {
        return 5000;
    }

    @Override
    public CosmeticRarity getCosmeticRarity() {
        return CosmeticRarity.COMMON;
    }

    @Override
    public int getId() {
        return 9;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runSound(Location location) {
        location.getWorld().playSound(location, Sound.ZOMBIE_DEATH, 1.0f, 0.0f);
    }

    @Override
    public void runPreview(Player player) {
        player.playSound(player.getLocation(), Sound.ZOMBIE_DEATH, 1.0f, 0.0f);
    }
}

