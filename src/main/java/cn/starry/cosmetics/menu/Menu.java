package cn.starry.cosmetics.menu;

import cn.starry.cosmetics.util.TextUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class Menu implements InventoryHolder {
    protected Player player;
    @Getter
    protected Inventory inventory;

    public Menu(Player player) {
        this.player = player;
    }

    public abstract String getMenuName();

    public abstract int getSlots();

    public abstract void handleMenu(InventoryClickEvent var1);

    public abstract void setMenuItems();

    public void open() {
        this.inventory = Bukkit.createInventory(this, this.getSlots(), TextUtil.colorize(this.getMenuName()));
        this.setMenuItems();
        this.player.openInventory(this.inventory);
    }

}

