package cn.starry.cosmetics.menu.menus.cosmetics;

import cn.charlotte.pit.data.PlayerProfile;
import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticType;
import cn.starry.cosmetics.api.cosmetics.deathCries.DeathCryEffect;
import cn.starry.cosmetics.api.cosmetics.finalKill.FinalKillCosmetic;
import cn.starry.cosmetics.api.cosmetics.projectileTrails.ProjectileTrailCosmetic;
import cn.starry.cosmetics.api.database.PlayerData;
import cn.starry.cosmetics.menu.CosmeticMenu;
import cn.starry.cosmetics.util.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class CosmeticsMenu implements Listener {
    String title = "我的特效";
    private Inventory inv;

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public int getOwned(PlayerData data, CosmeticType type) {
        if (!data.getOwnedCosmetics().containsKey(type.getId())) {
            return 0;
        }
        return data.getOwnedCosmetics().get(type.getId()).size();
    }

    public String getSelected(PlayerData data, CosmeticType type) {
        int id = data.getSelectedCosmetic(type);
        switch (type) {
            case PROJECTILE_TRAIL: {
                if (id == 0) {
                    return "随机的弹射物轨迹";
                }
                if (id == -1) {
                    return "无";
                }
                return ProjectileTrailCosmetic.getById(id).getDisplayName();
            }
            case FINAL_KILL: {
                if (id == -1) {
                    return "无";
                }
                if (id == 0) {
                    return "随机的击杀特效";
                }
                return FinalKillCosmetic.getById(id).getDisplayName();
            }
            case DEATH_CRY: {
                if (id == -1) {
                    return "无";
                }
                if (id == 0) {
                    return "随机的亡语";
                }
                return DeathCryEffect.getById(id).getDisplayName();
            }
        }
        return "NOT_AVAILABLE";
    }

    public void init(Player player) {
        PlayerData pd = Cosmetics.getPlayerData(player);
        ItemMeta meta;
        ItemStack item;
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(0);
        FileConfiguration yml = Cosmetics.getInstance().getConfig();
        this.inv = Bukkit.createInventory(null, 36, title);
        //ProjectileTrails
        if (Cosmetics.getInstance().getConfig().getBoolean("available.projectileTrails")) {
            int a = this.getOwned(pd, CosmeticType.PROJECTILE_TRAIL);
            int b = ProjectileTrailCosmetic.getProjectileTrails().size();
            item = new ItemStack(Material.ARROW);
            meta = item.getItemMeta();
            meta.setDisplayName("§a弹射物轨迹");
            List<String> lore = new ArrayList<>();
            lore.add("§7改变你的弹射物轨迹");
            lore.add("§7适用于弓,火球,搭路蛋");
            lore.add(" ");
            if (a != b) {
                lore.add("§7已解锁: §a" + a + "/" + b + " §8(" + numberFormat.format((float) a / (float) b * 100) + "%)");
            } else {
                lore.add("§7已解锁: §a" + a + "/" + b + " §6(" + numberFormat.format((float) a / (float) b * 100) + "%)");
            }
            lore.add("§7目前选择:");
            lore.add("§a" + this.getSelected(pd, CosmeticType.PROJECTILE_TRAIL));
            lore.add("");
            lore.add("§e点击查看");

            meta.setLore(lore);
            item.setItemMeta(meta);
            this.inv.setItem(Cosmetics.getInstance().getConfig().getInt("slot.projectileTrails"), item);
        }
        //FinalKillEffect
        if (Cosmetics.getInstance().getConfig().getBoolean("available.killEffect")) {
            int a = this.getOwned(pd, CosmeticType.FINAL_KILL);
            int b = FinalKillCosmetic.getFinalKillEffects().size();
            item = new ItemStack(Material.IRON_SWORD);
            meta = item.getItemMeta();
            meta.setDisplayName("§a击杀特效");
            List<String> lore = new ArrayList<>();
            lore.add("§7以华丽的特效中");
            lore.add("§7选择一种作为击杀一名");
            lore.add("§7玩家时的收尾操作");
            lore.add(" ");
            if (a != b) {
                lore.add("§7已解锁: §a" + a + "/" + b + " §8(" + numberFormat.format((float) a / (float) b * 100) + "%)");
            } else {
                lore.add("§7已解锁: §a" + a + "/" + b + " §6(" + numberFormat.format((float) a / (float) b * 100) + "%)");
            }
            lore.add("§7目前选择:");
            lore.add("§a" + this.getSelected(pd, CosmeticType.FINAL_KILL));
            lore.add("");
            lore.add("§e点击查看");

            meta.setLore(lore);
            item.setItemMeta(meta);
            this.inv.setItem(Cosmetics.getInstance().getConfig().getInt("slot.killEffect"), item);
        }
        //DeathCries
        if (Cosmetics.getInstance().getConfig().getBoolean("available.deathCry")) {
            int a = this.getOwned(pd, CosmeticType.DEATH_CRY);
            int b = DeathCryEffect.getDeathCryEffects().size();
            item = new ItemStack(Material.GHAST_TEAR);
            meta = item.getItemMeta();
            meta.setDisplayName("§a亡语");
            List<String> lore = new ArrayList<>();
            lore.add("§7当你被一位敌对玩家击杀");
            lore.add("§7将播放一个奇妙的音效！");
            lore.add("§7同时,让他们知道你哭泣时的泪水有多咸！");
            lore.add(" ");
            if (a != b) {
                lore.add("§7已解锁: §a" + a + "/" + b + " §8(" + numberFormat.format((float) a / (float) b * 100) + "%)");
            } else {
                lore.add("§7已解锁: §a" + a + "/" + b + " §6(" + numberFormat.format((float) a / (float) b * 100) + "%)");
            }
            lore.add("§7目前选择:");
            lore.add("§a" + this.getSelected(pd, CosmeticType.DEATH_CRY));
            lore.add("");
            lore.add("§e点击查看");

            meta.setLore(lore);
            item.setItemMeta(meta);
            this.inv.setItem(Cosmetics.getInstance().getConfig().getInt("slot.deathCry"), item);
        }
        //Economy
        {
            item = new ItemStack(Material.EMERALD);
            meta = item.getItemMeta();
            meta.setDisplayName("§7总硬币: §6" + PlayerProfile.getPlayerProfileByUuid(player.getUniqueId()).getCoins());
            List<String> lore = new ArrayList<>();
            lore.add(TextUtil.colorize(yml.getString("server-name")));

            meta.setLore(lore);
            item.setItemMeta(meta);
            this.inv.setItem(31, item);
        }
        player.openInventory(this.inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName() == null) {
            return;
        }
        if (!e.getView().getTitle().equalsIgnoreCase(title)) {
            return;
        }
        if (e.getSlot() == Cosmetics.getInstance().getConfig().getInt("slot.projectileTrails")) {
            CosmeticMenu.open(CosmeticType.getCosmeticType(0), player);
            e.setCancelled(true);
        }
        if (e.getSlot() == Cosmetics.getInstance().getConfig().getInt("slot.killEffect")) {
            CosmeticMenu.open(CosmeticType.getCosmeticType(2), player);
            e.setCancelled(true);
        }
        if (e.getSlot() == Cosmetics.getInstance().getConfig().getInt("slot.deathCry")) {
            CosmeticMenu.open(CosmeticType.getCosmeticType(5), player);
            e.setCancelled(true);
        }
        if (e.getSlot() == 31) {
            e.setCancelled(true);
        }
    }
}
