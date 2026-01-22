package cn.starry.cosmetics.listeners;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.database.PlayerData;
import cn.starry.cosmetics.api.database.SortedData;
import cn.starry.cosmetics.api.database.SortedType;
import cn.starry.cosmetics.util.classutils.interfaces.AutoRegister;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionsListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        SortedData.SORTED.put(player, SortedType.LOWEST_FIRST);
        player.removeMetadata("OwnerFirst",Cosmetics.getInstance());
        Bukkit.getScheduler().runTaskLater(Cosmetics.getInstance(), () -> {
            PlayerData playerData = Cosmetics.getDb().fetchData(player.getUniqueId());
            playerData.setName(player.getName());
            Cosmetics.addPlayerData(player, playerData);
        }, 30L);

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        SortedData.SORTED.put(player, SortedType.LOWEST_FIRST);
        player.removeMetadata("OwnerFirst",Cosmetics.getInstance());
        Cosmetics.getDb().saveData(Cosmetics.getPlayerData(player));
    }
}

