
package cn.starry.cosmetics.api.cosmetics.finalKill;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticType;
import cn.starry.cosmetics.api.cosmetics.Cosmetic;
import org.bukkit.Location;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

public abstract class FinalKillCosmetic
extends Cosmetic {
    private static final Set<FinalKillCosmetic> finalKillEffects = new HashSet<FinalKillCosmetic>();

    @Override
    public CosmeticType getCosmeticType() {
        return CosmeticType.FINAL_KILL;
    }

    public abstract void runEffect(Location var1);

    public static void register(FinalKillCosmetic dce) {
        finalKillEffects.add(dce);
        Cosmetics.getLog().log(Level.INFO, "击杀特效类别: " + dce.getDisplayName() + " 加载完成");
    }

    public static FinalKillCosmetic getById(int id) {
        return finalKillEffects.stream().filter(effect -> effect.getId() == id).findFirst().orElse(null);
    }

    public static Set<FinalKillCosmetic> getFinalKillEffects() {
        return finalKillEffects;
    }
}

