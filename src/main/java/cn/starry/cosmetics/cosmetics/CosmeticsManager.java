package cn.starry.cosmetics.cosmetics;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.deathCries.DeathCryEffect;
import cn.starry.cosmetics.api.cosmetics.finalKill.FinalKillCosmetic;
import cn.starry.cosmetics.api.cosmetics.projectileTrails.ProjectileTrailCosmetic;
import cn.starry.cosmetics.cosmetics.projectileTrails.*;
import cn.starry.cosmetics.cosmetics.finalKillEffects.*;
import cn.starry.cosmetics.cosmetics.deathCryEffects.*;

import java.util.Arrays;

public class CosmeticsManager {
    private static boolean loaded = false;

    private CosmeticsManager() {
    }

    public static void loadCosmetics() {
        if (!loaded) {
            Cosmetics.getLog().info("开始加载特效类别...");
            CosmeticsManager.loadProjectileTrails();
            CosmeticsManager.loadFinalKillEffects();
            CosmeticsManager.loadDeathCries();
            Cosmetics.getLog().info("全部特效已经加载");
            loaded = true;
        }
    }

    private static void loadProjectileTrails() {
        Cosmetics.getLog().info("开始加载 弹射物轨迹...");
        if (Cosmetics.getInstance().getConfig().getBoolean("available.projectileTrails")) {
            Arrays.asList(new BloodyProjectile(), new SlimeProjectile(), new EnderProjectile(), new LavaProjectile(), new PotionProjectile(), new WaterProjectile(), new BlackSmokeProjectile(), new WhiteSmokeProjectile(), new NotesProjectile(), new BlueDustProjectile(), new RedDustProjectile(), new PurpleDustProjectile(), new LunarDustProjectile(), new FireProjectile(), new MagicProjectile(), new GreenStarProjectile(), new AngryVillagerProjectile(), new FireworkProjectile(), new BoneProjectile(), new PumpkinPieProjectile(), new SnowballRainProjectile(), new RainyProjectile(), new RocketProjectile(), new LeatherProjectile(), new HeartsProjectile(), new RainbowProjectile(), new BeeProjectile(), new RandomProjectile(), new JackOLanternProjectile(), new WingmanProjectile(), new MerryProjectile(), new TinselProjectile(), new PresentProjectile(), new PeepProjectile(), new CheeseProjectile()).forEach(ProjectileTrailCosmetic::register);
        }
        Cosmetics.getLog().info("弹射物轨迹加载完成。");
    }

    private static void loadFinalKillEffects() {
        Cosmetics.getLog().info("开始加载 击杀特效...");
        if (Cosmetics.getInstance().getConfig().getBoolean("available.killEffect")) {
            Arrays.asList(new SquidMissileEffect(), new FireworksEffect(), new LightningStrike(), new TntEffect(), new HeartAuraEffect(), new CookieFountainEffect(), new ChickenTowerEffect(), new BunnyExplosionEffect(), new CampfireEffect(), new BeefEverywhereEffect(), new TornadoEffect(), new BlackMarkEffect(), new SpiritEffect(), new HatchingEggsEffect(), new CowRocketEffect(), new PinataEffect(), new SnowplosionEffect(), new ShatteredEffect(), new RainParadeEffect(), new GiftExplosionEffect(), new BatcruxEffect(), new PumpkinPopperEffect(), new BloodExplosionEffect()).forEach(FinalKillCosmetic::register);
        }
        Cosmetics.getLog().info("击杀特效加载完成。");
    }

    private static void loadDeathCries() {
        Cosmetics.getLog().info("开始加载 亡语...");
        if (Cosmetics.getInstance().getConfig().getBoolean("available.deathCry")) {
            Arrays.asList(new BazingaSound(), new DeflatedToySound(), new EndermanSound(), new DinosaurSound(), new RobotMouseSound(), new PigSound(), new GrumpyVillagerSound(), new SadPuppySound(), new MonsterBurpSound(), new FireballSound(), new DryBonesSound(), new DingSound(), new SplashSound(), new BatSound(), new PlopSound(), new GoneSound(), new BurpSound(), new ScurrySound(), new SquealSound(), new DousedLanternSound(), new AwwSound(), new EnergySound(), new GrumbleSound(), new HowlSound(), new SniffSound(), new SqueakSound(), new FireworkSound(), new DragonRoarSound(), new SadMooSound(), new ArcadeSound(), new MiracleSound(), new RageSound()).forEach(DeathCryEffect::register);
        }
        Cosmetics.getLog().info("亡语加载完成。");
    }

}

