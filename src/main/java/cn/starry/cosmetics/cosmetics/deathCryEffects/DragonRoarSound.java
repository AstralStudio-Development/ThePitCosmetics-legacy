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

public class DragonRoarSound
        extends DeathCryEffect {
    final String texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzkxNjQxZDAzOGViMzg2MmIxZTAyMmM1ZTA1ODhkOTYzNjZkYTFjOTQ0ZWU1ZTZlZTEzZTBmNWRkN2I0MjdiIn19fQ==";

    @Override
    public String getDisplayName() {
        return "龙吟";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7当你死亡时", "&7播放龙吟的亡语");
    }

    @Override
    public ItemStack getIcon() {
        return SkullUtil.makeTextureSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzkxNjQxZDAzOGViMzg2MmIxZTAyMmM1ZTA1ODhkOTYzNjZkYTFjOTQ0ZWU1ZTZlZTEzZTBmNWRkN2I0MjdiIn19fQ==");
    }

    @Override
    public int getPrice() {
        return 5000;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public CosmeticRarity getCosmeticRarity() {
        return CosmeticRarity.EPIC;
    }

    @Override
    public int getId() {
        return 28;
    }

    @Override
    public void runSound(Location location) {
        location.getWorld().playSound(location, Sound.ENDERDRAGON_GROWL, 1.0f, 0.0f);
    }

    @Override
    public void runPreview(Player player) {
        player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 0.0f);
    }
}

