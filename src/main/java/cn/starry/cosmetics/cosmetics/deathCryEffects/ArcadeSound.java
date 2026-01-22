package cn.starry.cosmetics.cosmetics.deathCryEffects;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.deathCries.DeathCryEffect;
import cn.starry.cosmetics.util.SkullUtil;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class ArcadeSound extends DeathCryEffect {
    final String texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzRkMDk4OTRmMTJkZjkwNTNmYTgzYTJlMDJhZjk1MTc2Yzc3ZGRmM2M0M2VhNWZhZGI5ODBmMjNmYmU0YSJ9fX0=";

    @Override
    public String getDisplayName() {
        return "街机";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("&7当你死亡时", "&7经典的旋律将在耳边回荡");
    }

    @Override
    public ItemStack getIcon() {
        return SkullUtil.makeTextureSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzRkMDk4OTRmMTJkZjkwNTNmYTgzYTJlMDJhZjk1MTc2Yzc3ZGRmM2M0M2VhNWZhZGI5ODBmMjNmYmU0YSJ9fX0=");
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
        return 30;
    }

    @Override
    public boolean BuyAble() {
        return true;
    }

    @Override
    public void runSound(final Location location) {
        new BukkitRunnable(){
            int i = 6;

            public void run() {
                if (this.i <= 0) {
                    this.cancel();
                    return;
                }
                switch (this.i) {
                    case 4:
                    case 5:
                    case 6: {
                        ArcadeSound.this.play(1.0476191f, location);
                        break;
                    }
                    case 3: {
                        ArcadeSound.this.play(0.7936508f, location);
                        break;
                    }
                    case 2: {
                        ArcadeSound.this.play(0.8888889f, location);
                        break;
                    }
                    case 1: {
                        ArcadeSound.this.play(0.6984127f, location);
                    }
                }
                --this.i;
            }
        }.runTaskTimer(Cosmetics.getInstance(), 0L, 6L);
    }

    @Override
    public void runPreview(final Player player) {
        new BukkitRunnable(){
            int i = 6;

            public void run() {
                if (this.i <= 0) {
                    this.cancel();
                    return;
                }
                switch (this.i) {
                    case 4:
                    case 5:
                    case 6: {
                        ArcadeSound.this.play(1.0476191f, player);
                        break;
                    }
                    case 3: {
                        ArcadeSound.this.play(0.7936508f, player);
                        break;
                    }
                    case 2: {
                        ArcadeSound.this.play(0.8888889f, player);
                        break;
                    }
                    case 1: {
                        ArcadeSound.this.play(0.6984127f, player);
                    }
                }
                --this.i;
            }
        }.runTaskTimer((Plugin)Cosmetics.getInstance(), 0L, 6L);
    }

    private void play(float pitch, Location location) {
        location.getWorld().playSound(location, Sound.NOTE_PLING, 1.0f, pitch);
    }

    private void play(float pitch, Player player) {
        player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, pitch);
    }
}

