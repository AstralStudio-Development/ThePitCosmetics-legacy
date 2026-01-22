package cn.starry.cosmetics.listeners;

import cn.starry.cosmetics.menu.Menu;
import cn.starry.cosmetics.util.classutils.interfaces.AutoRegister;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {
    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        if (!(holder instanceof Menu)) {
            return;
        }
        e.setCancelled(true);
        if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals((Object)Material.AIR)) {
            return;
        }
        ((Menu)holder).handleMenu(e);
    }
}

