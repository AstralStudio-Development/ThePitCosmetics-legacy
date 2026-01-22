package cn.starry.cosmetics;

import cn.starry.cosmetics.api.database.PlayerData;
import cn.starry.cosmetics.commands.CosmeticsCommand;
import cn.starry.cosmetics.cosmetics.CosmeticsManager;
import cn.starry.cosmetics.cosmetics.projectileTrails.tasks.ProjectileParticleHandler;
import cn.starry.cosmetics.database.Database;
import cn.starry.cosmetics.listeners.ChatListener;
import cn.starry.cosmetics.listeners.ConnectionsListener;
import cn.starry.cosmetics.listeners.MenuListener;
import cn.starry.cosmetics.listeners.cosmetics.DeathCryListener;
import cn.starry.cosmetics.listeners.cosmetics.FinalKillListener;
import cn.starry.cosmetics.listeners.cosmetics.ProjectileTrailListener;
import cn.starry.cosmetics.menu.menus.cosmetics.CosmeticsMenu;
import cn.starry.cosmetics.util.classutils.ClassUtil;
import cn.starry.cosmetics.util.classutils.interfaces.AutoRegister;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

public class Cosmetics extends JavaPlugin {
    private static final HashMap<Player, PlayerData> playerDataMap = new HashMap<>();

    @Getter
    private static Cosmetics instance;
    @Getter
    private static Logger log;

    @Getter
    @Setter
    private static Database db;
    @Getter
    @Setter
    private static boolean isPapi = false;

    @Getter
    public ProjectileParticleHandler projectileHandler;

    public static PlayerData getPlayerData(Player player) {
        return playerDataMap.getOrDefault(player, new PlayerData(player.getUniqueId()));
    }

    public static void addPlayerData(Player player, PlayerData playerData) {
        playerDataMap.put(player, playerData);
    }

    public static void removePlayerData(Player player) {
        playerDataMap.remove(player);
    }

    @Override
    public void onLoad() {
        instance = this;
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }
    }


    @Override
    public void onEnable() {
        log = this.getLogger();
        this.saveDefaultConfig();
        this.getCommand("cosmetics").setExecutor(new CosmeticsCommand());
        Cosmetics.setDb(new Database());
        if (Cosmetics.getDb().connect()) {
            Cosmetics.getLog().info("MySQL database successfully connected!");
            Cosmetics.getDb().init0();
        } else {
            Cosmetics.getLog().info("Can't connect to MySQL database, this add-on now will disable.");
            this.onDisable();
            return;
        }
        this.loadListener();
        projectileHandler = new ProjectileParticleHandler();
        projectileHandler.start(this);
        CosmeticsManager.loadCosmetics();
        Cosmetics.setPapi(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null);
        Cosmetics.getLog().info(Cosmetics.isPapi() ? "Hook PlaceholderAPI successfully!" : "Can't find PlaceholderAPI");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RESET + "[ThePitCosmetics] " + ChatColor.GREEN + "ThePitCosmetics install successfully!");

    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        playerDataMap.clear();
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "ThePitCosmetics uninstall successfully!");
    }


    public void loadListener() {
        this.registerListeners(
                new CosmeticsMenu(),
                new DeathCryListener(),
                new FinalKillListener(),
                new ProjectileTrailListener(),
                new ChatListener(),
                new ConnectionsListener(),
                new MenuListener());
    }

    public void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }
}

