package cn.starry.cosmetics.cosmetics.projectileTrails.tasks;

import cn.starry.cosmetics.api.cosmetics.projectileTrails.ProjectileTrailCosmetic;
import org.bukkit.entity.Projectile;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ProjectileParticleHandler
        extends BukkitRunnable {
    private final LinkedHashMap<Projectile, ProjectileTrailCosmetic> projectilesMap = new LinkedHashMap<>();
    private final List<Projectile> toRemove = new ArrayList<>();

    public void run() {
        if (this.projectilesMap.isEmpty()) {
            return;
        }
        this.projectilesMap.forEach((p, c) -> {
            if (p.isDead() || p.doesBounce()) {
                this.toRemove.add(p);
            } else {
                c.play(p);
            }
        });
        this.toRemove.forEach(this.projectilesMap::remove);
        this.toRemove.clear();
    }

    public void add(Projectile p, ProjectileTrailCosmetic c) {
        this.projectilesMap.put(p, c);
    }

    public void remove(Projectile p) {
        this.projectilesMap.remove(p);
    }

    public void start(Plugin p) {
        this.runTaskTimer(p, 0L, 1L);
    }
}

