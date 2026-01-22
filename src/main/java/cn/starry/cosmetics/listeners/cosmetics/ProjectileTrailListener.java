package cn.starry.cosmetics.listeners.cosmetics;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticType;
import cn.starry.cosmetics.api.cosmetics.projectileTrails.ProjectileTrailCosmetic;
import cn.starry.cosmetics.api.database.PlayerData;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.List;
import java.util.Random;

public class ProjectileTrailListener implements Listener {
    private final Random r = new Random();

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        if (Cosmetics.getInstance().getConfig().getBoolean("available.projectileTrails")) {
            List<Integer> owned;
            PlayerData data;
            int id;
            if (e.isCancelled()) {
                return;
            }
            if (e.getEntity().getShooter() == null) {
                return;
            }
            if (!(e.getEntity().getShooter() instanceof Player)) {
                return;
            }
            if (e.getEntity().isDead()) {
                return;
            }
            if (e.getEntity() instanceof Egg) {
                return;
            }
            if (e.getEntity() instanceof Arrow) {
                ((Arrow) e.getEntity()).setCritical(false);
            }
            if ((id = (data = Cosmetics.getPlayerData((Player) e.getEntity().getShooter())).getSelectedCosmetic(CosmeticType.PROJECTILE_TRAIL)) == -1) {
                return;
            }
            ProjectileTrailCosmetic c = id == 0 ? ((owned = data.getOwnedCosmetics(CosmeticType.PROJECTILE_TRAIL)).isEmpty() ? ProjectileTrailCosmetic.getById(-1) : ProjectileTrailCosmetic.getById(this.r.nextInt(owned.size() + 1))) : ProjectileTrailCosmetic.getById(id);
            if (c.isCustom()) {
                c.play(e.getEntity());
            } else {
                Cosmetics.getInstance().getProjectileHandler().add(e.getEntity(), c);
            }
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        if (Cosmetics.getInstance().getConfig().getBoolean("available.projectileTrails")) {
            if (e.getEntity().isDead()) {
                return;
            }
            if (e.getEntity().getPassenger() != null) {
                e.getEntity().getPassenger().remove();
            }
            if (e.getEntity().hasMetadata("CosmeticsProjectile")) {
                e.getEntity().remove();
            } else {
                Cosmetics.getInstance().getProjectileHandler().remove(e.getEntity());
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (Cosmetics.getInstance().getConfig().getBoolean("available.projectileTrails")) {
            if (e.getEntity().hasMetadata("CosmeticsProjectileEntity")) {
                e.setCancelled(true);
            }
        }
    }
}

