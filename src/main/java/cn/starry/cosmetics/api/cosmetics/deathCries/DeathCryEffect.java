/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.entity.Player
 */
package cn.starry.cosmetics.api.cosmetics.deathCries;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.Cosmetic;
import cn.starry.cosmetics.api.cosmetics.CosmeticType;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

public abstract class DeathCryEffect
extends Cosmetic {

    private static final Set<DeathCryEffect> deathCryEffects = new HashSet<DeathCryEffect>();

    @Override
    public CosmeticType getCosmeticType() {
        return CosmeticType.DEATH_CRY;
    }

    public abstract void runSound(Location var1);

    public abstract void runPreview(Player var1);

    public static void register(DeathCryEffect dce) {
        deathCryEffects.add(dce);
        Cosmetics.getLog().log(Level.INFO, "亡语类别: " + dce.getDisplayName() + " 加载完成");
    }

    public static DeathCryEffect getById(int id) {
        return deathCryEffects.stream().filter(effect -> effect.getId() == id).findFirst().orElse(null);
    }

    public static Set<DeathCryEffect> getDeathCryEffects() {
        return deathCryEffects;
    }
}

