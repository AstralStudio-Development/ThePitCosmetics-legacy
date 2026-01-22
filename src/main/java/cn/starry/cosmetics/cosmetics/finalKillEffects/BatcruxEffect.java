/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.EnumParticle
 *  org.bukkit.Location
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package cn.starry.cosmetics.cosmetics.finalKillEffects;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.finalKill.FinalKillCosmetic;
import cn.starry.cosmetics.util.ParticleBuilder;
import cn.starry.cosmetics.util.SkullUtil;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BatcruxEffect
extends FinalKillCosmetic {
    final String texture = "eyJ0aW1lc3RhbXAiOjE1MDc0ODc1OTk5NTMsInByb2ZpbGVJZCI6IjQxZDNhYmMyZDc0OTQwMGM5MDkwZDU0MzRkMDM4MzFiIiwicHJvZmlsZU5hbWUiOiJNZWdha2xvb24iLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzY2Y2ZhYjFkMmY3NjNmMzMzYjVlMWJkY2QxYWQxNjJjN2YxMmExYWMyMjI0NDYzYTE0NDY0M2VjMjNmOTgifX19";

    @Override
    public String getDisplayName() {
        return "蝙蝠十字架";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7选择" + getDisplayName() + "作为你的击杀特效");
    }

    @Override
    public ItemStack getIcon() {
        return SkullUtil.makeTextureSkull("eyJ0aW1lc3RhbXAiOjE1MDc0ODc1OTk5NTMsInByb2ZpbGVJZCI6IjQxZDNhYmMyZDc0OTQwMGM5MDkwZDU0MzRkMDM4MzFiIiwicHJvZmlsZU5hbWUiOiJNZWdha2xvb24iLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzY2Y2ZhYjFkMmY3NjNmMzMzYjVlMWJkY2QxYWQxNjJjN2YxMmExYWMyMjI0NDYzYTE0NDY0M2VjMjNmOTgifX19");
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
        return 34;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runEffect(Location location) {
        final ArrayList<Entity> bats = new ArrayList<Entity>();
        location.add(0.0, 2.0, 0.0);
        for (int i = 0; i < 7; ++i) {
            Entity bat = location.getWorld().spawnEntity(location, EntityType.BAT);
            bats.add(bat);
        }
        new BukkitRunnable(){
            int i = 200;

            public void run() {
                if (this.i <= 0) {
                    this.cancel();
                    bats.forEach(Entity::remove);
                    bats.clear();
                    return;
                }
                bats.forEach(bat -> new ParticleBuilder(bat.getLocation(), EnumParticle.SMOKE_NORMAL).play());
                --this.i;
            }
        }.runTaskTimer((Plugin) Cosmetics.getInstance(), 0L, 1L);
    }
}

