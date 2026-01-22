package cn.starry.cosmetics.menu.menus.cosmetics;

import cn.charlotte.pit.data.PlayerProfile;
import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticRarity;
import cn.starry.cosmetics.api.cosmetics.CosmeticType;
import cn.starry.cosmetics.api.cosmetics.finalKill.FinalKillCosmetic;
import cn.starry.cosmetics.api.database.PlayerData;
import cn.starry.cosmetics.api.database.SortedData;
import cn.starry.cosmetics.api.database.SortedType;
import cn.starry.cosmetics.menu.CosmeticMenu;
import cn.starry.cosmetics.menu.menus.ConfirmPurchaseMenu;
import cn.starry.cosmetics.util.ItemBuilder;
import cn.starry.cosmetics.util.NBTItem;
import cn.starry.cosmetics.util.TextUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalKillEffectsMenu extends CosmeticMenu {
    private final PlayerData playerData;

    public FinalKillEffectsMenu(Player player) {
        super(player);
        this.playerData = Cosmetics.getPlayerData(player);
    }

    @Override
    public String getMenuName() {
        return "击杀特效";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        this.handlePaginate(e);
        ItemStack item = e.getCurrentItem();
        NBTItem nbtItem = new NBTItem(item);
        if (!nbtItem.hasKey("id")) {
            return;
        }
        int id = nbtItem.getInteger("id");
        FinalKillCosmetic effect = FinalKillCosmetic.getById(id);
        if (e.getClick().equals(ClickType.SHIFT_LEFT) && id != 0 && id != -1 && player.hasPermission("spc.admin") && !this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), id)) {
            new ConfirmPurchaseMenu(this.player, effect, this.page).open();
            return;
        }
        if (this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), id) || id == -1 || id == 0 || player.hasPermission("spc.admin")) {
            if (this.playerData.isSelected(CosmeticType.FINAL_KILL.getId(), id)) {
                return;
            }
            this.playerData.setSelectedCosmetic(CosmeticType.FINAL_KILL.getId(), id);
            String displayName = id == -1 || id == 0 ? item.getItemMeta().getDisplayName() : effect.getDisplayName();
            this.player.sendMessage(TextUtil.colorize("&6你选择了 &a{cosmetic}&6!".replace("{cosmetic}", displayName)));
            this.player.playSound(this.player.getLocation(), Sound.NOTE_PLING, 1.0f, 2.0f);
            this.setMenuItems();
            return;
        }
        if (!effect.BuyAble()) {
            this.player.sendMessage(TextUtil.colorize("&c当前不可用!"));
            this.player.playSound(this.player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
            return;
        }
        if (PlayerProfile.getPlayerProfileByUuid(player.getUniqueId()).getCoins() < (double) effect.getPrice()) {
            this.player.sendMessage(TextUtil.colorize("&c你没有足够的硬币来解锁这个特效!"));
            this.player.playSound(this.player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
            return;
        }
        new ConfirmPurchaseMenu(this.player, effect, this.page).open();
    }

    @Override
    public List<ItemStack> getItems() {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        itemStacks.add(new ItemBuilder(Material.BARRIER).setDisplayName("&a无").setLore(this.getLore(-1)).addTag("id", -1).build());
        itemStacks.add(new ItemBuilder(Material.ENDER_CHEST).setDisplayName("&a随机击杀特效").setLore(this.getLore(0)).addTag("id", 0).build());
        //添加化妆品
        if (SortedData.SORTED.get(player) == SortedType.LOWEST_FIRST) {
            if (player.hasMetadata("OwnedFirst")) {
                for (int i = 1; i <= 120; ++i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.COMMON && this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), i)) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 1; i <= 120; ++i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.RARE && this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), i)) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 1; i <= 120; ++i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.EPIC && this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), i)) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 1; i <= 120; ++i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.LEGENDARY && this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), i)) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 1; i <= 120; ++i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.COMMON && !this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), i)) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 1; i <= 120; ++i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.RARE && !this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), i)) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 1; i <= 120; ++i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.EPIC && !this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), i)) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 1; i <= 120; ++i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.LEGENDARY && !this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), i)) {
                        itemStacks.add(this.getItem(i));
                    }
                }
            } else {
                for (int i = 1; i <= 120; ++i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.COMMON) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 1; i <= 120; ++i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.RARE) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 1; i <= 120; ++i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.EPIC) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 1; i <= 120; ++i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.LEGENDARY) {
                        itemStacks.add(this.getItem(i));
                    }
                }
            }
        } else {
            if (player.hasMetadata("OwnedFirst")) {
                for (int i = 120; i >= 1; --i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.LEGENDARY && this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), i)) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 120; i >= 1; --i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.EPIC && this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), i)) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 120; i >= 1; --i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.RARE && this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), i)) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 120; i >= 1; --i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.COMMON && this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), i)) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 120; i >= 1; --i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.LEGENDARY && !this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), i)) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 120; i >= 1; --i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.EPIC && !this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), i)) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 120; i >= 1; --i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.RARE && !this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), i)) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 120; i >= 1; --i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.COMMON && !this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), i)) {
                        itemStacks.add(this.getItem(i));
                    }
                }
            } else {
                for (int i = 120; i >= 1; --i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.LEGENDARY) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 120; i >= 1; --i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.EPIC) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 120; i >= 1; --i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.RARE) {
                        itemStacks.add(this.getItem(i));
                    }
                }
                for (int i = 120; i >= 1; --i) {
                    if (FinalKillCosmetic.getById(i) == null) continue;
                    if (FinalKillCosmetic.getById(i).getCosmeticRarity() == CosmeticRarity.COMMON) {
                        itemStacks.add(this.getItem(i));
                    }
                }
            }
        }

        return itemStacks;
    }

    @Override
    public CosmeticType getCosmeticType() {
        return CosmeticType.FINAL_KILL;
    }

    @Override
    public void setMenuItems() {
        this.addMenuBorder();
        this.addItems();
    }

    public ItemStack getItem(int id) {
        FinalKillCosmetic effect = FinalKillCosmetic.getById(id);
        return new ItemBuilder(effect.getIcon()).setDisplayName(this.getDisplayName(effect.getId())).setLore(this.getLore(effect.getId())).addTag("id", effect.getId()).build();
    }

    public String getDisplayName(int id) {
        FinalKillCosmetic effect = FinalKillCosmetic.getById(id);
        String color = this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), id) || PlayerProfile.getPlayerProfileByUuid(player.getUniqueId()).getCoins() >= (double) effect.getPrice() ? "&a" : "&c";
        return color + effect.getDisplayName();
    }

    public List<String> getLore(int id) {
        FinalKillCosmetic effect = FinalKillCosmetic.getById(id);
        ArrayList<String> lore = new ArrayList<>();
        if (id != 0) {
            lore.add("&8击杀特效");
        }
        lore.add("");
        switch (id) {
            case -1: {
                lore.addAll(Arrays.asList("&7选择这个选项将", "&7什么事情也不会发生"));
                break;
            }
            case 0: {
                lore.add("&7选择一个随机的击杀特效!");
                break;
            }
            default: {
                lore.addAll(effect.getDescription());
            }
        }
        lore.add("");
        if (id != -1 && id != 0) {
            lore.add("&7稀有度: " + effect.getCosmeticRarity().getDisplayName());
        }
        if (id == -1 || id == 0) {
            if (id == this.playerData.getSelectedCosmetic(CosmeticType.FINAL_KILL)) {
                lore.add("&a已选择!");
            } else {
                lore.add("&e点击选择!");
            }
        } else if (effect.getId() == this.playerData.getSelectedCosmetic(CosmeticType.FINAL_KILL)) {
            lore.addAll(Arrays.asList("", "&a已选择!"));
        } else if (this.playerData.hasEffect(CosmeticType.FINAL_KILL.getId(), id)) {
            lore.addAll(Arrays.asList("", "&e点击选择!"));
        } else {
            if (!player.hasPermission("spc.admin")) {
                if (effect.BuyAble()) {
                    if (effect.getPrice() == 0) {
                        lore.addAll(Arrays.asList("&a是的,这是一份礼物", "", "&e点击购买!"));
                    } else {
                        lore.addAll(Arrays.asList(String.format("&7花费: &6%s", effect.getFormattedPrice()), "", PlayerProfile.getPlayerProfileByUuid(player.getUniqueId()).getCoins() >= (double) effect.getPrice() ? "&e点击购买!" : "&c你没有足够的硬币来解锁这个特效!"));
                    }
                } else {
                    lore.addAll(Arrays.asList("", "&c不可购买!"));
                }
            } else {
                lore.addAll(Arrays.asList("", "&c你并未拥有该特效", "&c当前状态仅供临时使用！", "&c使用SHIFT+左键来购买", "", "&b点击选择!"));
            }
        }
        return lore;
    }
}