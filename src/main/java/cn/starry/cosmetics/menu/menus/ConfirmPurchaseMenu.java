package cn.starry.cosmetics.menu.menus;

import cn.charlotte.pit.data.PlayerProfile;
import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.Cosmetic;
import cn.starry.cosmetics.api.database.PlayerData;
import cn.starry.cosmetics.menu.CosmeticMenu;
import cn.starry.cosmetics.menu.Menu;
import cn.starry.cosmetics.util.ItemBuilder;
import cn.starry.cosmetics.util.TextUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ConfirmPurchaseMenu extends Menu {
    private final Cosmetic type;
    private final int page;

    public ConfirmPurchaseMenu(Player player, Cosmetic type, int page) {
        super(player);
        this.type = type;
        this.page = page;
    }

    @Override
    public String getMenuName() {
        return "&8确认购买";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        if (e.getSlot() == 15) {
            CosmeticMenu.open(this.type.getCosmeticType(), this.player);
        } else if (e.getSlot() == 11) {
            PlayerProfile profile = PlayerProfile.getPlayerProfileByUuid(this.player.getUniqueId());
            if (profile.getCoins() < this.type.getPrice()) {
                this.player.sendMessage(TextUtil.colorize("&c你没有足够的硬币"));
                return;
            }
            profile.setCoins(profile.getCoins() - this.type.getPrice());
            PlayerData pd = Cosmetics.getPlayerData(this.player);
            pd.addOwnedCosmetic(this.type.getCosmeticType().getId(), this.type.getId());
            pd.setSelectedCosmetic(this.type.getCosmeticType().getId(), this.type.getId());
            CosmeticMenu.open(this.type.getCosmeticType(), this.player, this.page);
            this.player.playSound(this.player.getLocation(), Sound.NOTE_PLING, 1.0f, 2.0f);
            this.player.sendMessage(TextUtil.colorize("&6你购买了 &a{cosmetic}&6!".replace("{cosmetic}", this.type.getDisplayName())));
        }
    }

    @Override
    public void setMenuItems() {
        this.inventory.setItem(11, new ItemBuilder(Material.STAINED_CLAY).setData(5).setDisplayName("&a购买").setLore("&7你将解锁: " + this.type.getCosmeticRarity().getColor() + this.type.getDisplayName(), "&7花费: &6" + this.type.getFormattedPrice()).build());
        this.inventory.setItem(15, new ItemBuilder(Material.STAINED_CLAY).setData(14).setDisplayName("&c取消").build());
    }
}

