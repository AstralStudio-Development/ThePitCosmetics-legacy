package cn.starry.cosmetics.menu;

import cn.charlotte.pit.data.PlayerProfile;
import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticType;
import cn.starry.cosmetics.api.database.SortedData;
import cn.starry.cosmetics.api.database.SortedType;
import cn.starry.cosmetics.menu.menus.cosmetics.CosmeticsMenu;
import cn.starry.cosmetics.menu.menus.cosmetics.DeathCriesMenu;
import cn.starry.cosmetics.menu.menus.cosmetics.FinalKillEffectsMenu;
import cn.starry.cosmetics.menu.menus.cosmetics.ProjectileTrailsMenu;
import cn.starry.cosmetics.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.List;

public abstract class CosmeticMenu extends Menu {
    protected int page = 0;
    protected int index = 0;
    protected int maxPages;
    protected int displayPage;
    protected int maxItemsPerPage = this.getItemsSlots().length;

    public CosmeticMenu(Player player) {
        super(player);
    }

    public abstract List<ItemStack> getItems();

    public abstract CosmeticType getCosmeticType();

    public void handlePaginate(InventoryClickEvent e) {
        int slot = e.getSlot();
        if (slot == 48) {
            new CosmeticsMenu().openMenu(player);
        } else if (slot == 53) {
            if (this.maxPages <= this.displayPage) {
                return;
            }
            ++this.page;
            super.open();
        } else if (slot == 45) {
            if (this.displayPage <= 1) {
                return;
            }
            --this.page;
            super.open();
        } else if (slot == 50) {
            if (e.getClick().equals(ClickType.LEFT)) {
                if (SortedData.SORTED.get(player) == SortedType.LOWEST_FIRST) {
                    SortedData.SORTED.put(player, SortedType.HIGHEST_FIRST);
                } else {
                    SortedData.SORTED.put(player, SortedType.LOWEST_FIRST);
                }
            } else if (e.getClick().equals(ClickType.RIGHT)) {
                if (player.hasMetadata("OwnedFirst")) {
                    player.removeMetadata("OwnedFirst",Cosmetics.getInstance());
                } else {
                    player.setMetadata("OwnedFirst", new FixedMetadataValue(Cosmetics.getInstance(), true));
                }
            }
            player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1.0f, 1.0f);
            super.open();
        }
    }

    public void addMenuBorder() {
        FileConfiguration yml = Cosmetics.getInstance().getConfig();
        this.maxPages = (int)Math.ceil((float)this.getItems().size() / (float)this.maxItemsPerPage);
        this.displayPage = this.page + 1;
        if (this.maxPages > this.displayPage) {
            this.inventory.setItem(53, new ItemBuilder(Material.ARROW).setDisplayName("&a下一页").setLore("&7前往 第 &a" + (this.displayPage + 1) + "&7 页").build());
        }
        this.inventory.setItem(48, new ItemBuilder(Material.ARROW).setDisplayName("&a返回").setLore("&7返回至我的特效").build());
        if (this.displayPage > 1) {
            this.inventory.setItem(45, new ItemBuilder(Material.ARROW).setDisplayName("&a上一页").setLore("&7前往 第 &a" + (this.displayPage - 1) + "&7 页").build());
        }
        this.inventory.setItem(49, new ItemBuilder(Material.EMERALD).setDisplayName("&7硬币总数 &6" + PlayerProfile.getPlayerProfileByUuid(player.getUniqueId()).getCoins()).setLore(yml.getString("server-name")).build());
        this.inventory.setItem(50, new ItemBuilder(Material.HOPPER).setDisplayName("&6排序 &a" + SortedData.SORTED.get(player).getDisplayName()).setLore("&7当前排序 &a" + SortedData.SORTED.get(player).getDisplayName(),"","&7下一排序 &a" + (SortedData.SORTED.get(player).getId() == 0 ? SortedType.HIGHEST_FIRST.getDisplayName() : SortedType.LOWEST_FIRST.getDisplayName()),"&e左键切换","","&7查看拥有的特效 " + (player.hasMetadata("OwnedFirst") ? "&a是" : "&c否"),"&e右键切换","").build());
    }

    public void addItems() {
        if (this.getItemsSlots() == null) {
            return;
        }
        if (this.getItems() == null) {
            return;
        }
        for (int i = 0; i < this.maxItemsPerPage; ++i) {
            this.index = this.maxItemsPerPage * this.page + i;
            if (this.index >= this.getItems().size()) break;
            this.inventory.setItem(this.getItemsSlots()[i], this.getItems().get(this.index));
        }
        /*
        //添加化妆品
        if (SortedData.SORTED.get(player) == SortedType.LOWEST_FIRST) {
            for (int i = 0; i < this.maxItemsPerPage; ++i) {
                this.index = this.maxItemsPerPage * this.page + i;
                if (this.index >= this.getItems().size()) break;
                this.inventory.setItem(this.getItemsSlots()[i], this.getItems().get(this.index));
            }
        } else {
            for (int i = this.maxItemsPerPage; i > 0; --i) {
                this.index = this.maxItemsPerPage * this.page + i;
                if (this.index >= this.getItems().size()) break;
                this.inventory.setItem(this.getItemsSlots()[i], this.getItems().get(this.index));
            }
        }

         */
    }

    public void open(int page) {
        this.page = page;
        this.open();
    }

    public static void open(CosmeticType type, Player player, int ... page) {
        if (type == null) {
            return;
        }
        int p = page == null || page.length == 0 ? 0 : page[0];
        switch (type) {
            case PROJECTILE_TRAIL: {
                new ProjectileTrailsMenu(player).open(p);
                break;
            }
            case FINAL_KILL: {
                new FinalKillEffectsMenu(player).open(p);
                break;
            }
            case DEATH_CRY: {
                new DeathCriesMenu(player).open(p);
                break;
            }
        }
    }

    //Lower to Highest
    public Integer[] getItemsSlots() {
        return new Integer[]{10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34};
    }

}

