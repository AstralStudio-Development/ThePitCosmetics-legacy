/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package cn.starry.cosmetics.cosmetics.deathCryEffects;

import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.deathCries.DeathCryEffect;
import cn.starry.cosmetics.util.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class FowSound
        extends DeathCryEffect {
    @Override
    public String getDisplayName() {
        return "狗叫";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7当你&6魔怔&7时", "&7将发出&6狗叫！", "", "&7于&2Aria&6Craft&7内测期间免费获得");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.ROTTEN_FLESH).build();
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public CosmeticRarity getCosmeticRarity() {
        return CosmeticRarity.EPIC;
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
    public void runSound(Location location) {
        location.getWorld().playSound(location, Sound.WOLF_PANT, 1.0f, 0.0f);
    }

    @Override
    public void runPreview(Player player) {
        player.playSound(player.getLocation(), Sound.WOLF_PANT, 1.0f, 0.0f);
    }
}

