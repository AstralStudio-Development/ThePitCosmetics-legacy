package cn.starry.cosmetics.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onCheck(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String msg = event.getMessage();
        if (msg.equalsIgnoreCase(".checkcosmetics")) {
            event.setCancelled(true);
            player.sendMessage("ThePit Cosmetics");
            player.sendMessage("Made By Starry_Killer and pi_ka");
        }
    }
}
