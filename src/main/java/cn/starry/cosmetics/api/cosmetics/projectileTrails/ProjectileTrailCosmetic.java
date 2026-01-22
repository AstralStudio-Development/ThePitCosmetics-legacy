package cn.starry.cosmetics.api.cosmetics.projectileTrails;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.Cosmetic;
import cn.starry.cosmetics.api.cosmetics.CosmeticType;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Projectile;

import java.util.LinkedList;
import java.util.logging.Level;

@Getter
public abstract class ProjectileTrailCosmetic extends Cosmetic {
    private final boolean custom;
    @Getter
    private static final LinkedList<ProjectileTrailCosmetic> projectileTrails = new LinkedList<>();

    public ProjectileTrailCosmetic() {
        this.custom = false;
    }

    public ProjectileTrailCosmetic(boolean custom) {
        this.custom = custom;
    }

    @Override
    public CosmeticType getCosmeticType() {
        return CosmeticType.PROJECTILE_TRAIL;
    }

    public void runParticleEffect(Location loc) {
    }

    public void runParticleEffect(Projectile p) {
    }

    public void play(Projectile p) {
        if (this.custom) {
            this.runParticleEffect(p);
        } else {
            this.runParticleEffect(p.getLocation());
        }
    }

    public static void register(ProjectileTrailCosmetic effect) {
        projectileTrails.add(effect);
        Cosmetics.getLog().log(Level.INFO, "弹射物轨迹类别: " + effect.getDisplayName() + " 加载完成");
    }

    public static ProjectileTrailCosmetic getById(int id) {
        return projectileTrails.stream().filter(effect -> effect.getId() == id).findFirst().orElse(null);
    }

}

