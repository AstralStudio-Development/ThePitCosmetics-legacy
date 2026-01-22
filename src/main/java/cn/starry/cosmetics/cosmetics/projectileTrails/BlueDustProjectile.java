package cn.starry.cosmetics.cosmetics.projectileTrails;

import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.projectileTrails.ProjectileTrailCosmetic;
import cn.starry.cosmetics.util.ParticleBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class BlueDustProjectile extends ProjectileTrailCosmetic {
    @Override
    public String getDisplayName() {
        return "蓝色粉末";
    }

    @Override
    public List<String> getDescription() {
        return List.of("&7选择" + getDisplayName() + "弹射物轨迹");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.INK_SACK, 1, (short) 4);
    }

    @Override
    public int getPrice() {
        return 5000;
    }

    @Override
    public CosmeticRarity getCosmeticRarity() {
        return CosmeticRarity.RARE;
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
    public void runParticleEffect(Location loc) {
        ParticleBuilder.playColoredDust(loc, Color.fromRGB(5, 1, 128));
    }
}

