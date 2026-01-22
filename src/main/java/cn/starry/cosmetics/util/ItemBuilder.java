package cn.starry.cosmetics.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class ItemBuilder {
    private ItemStack itemStack;
    private final Material material;
    private int data;
    private int amount;
    private String displayName;
    private List<String> lore;
    private final Map<Enchantment, Integer> enchantments;
    private final Set<ItemFlag> itemFlags;
    private final HashMap<String, String> tags;
    private final HashMap<String, Integer> intTags;
    private NBTItem nbtItem;

    public ItemBuilder(Material material) {
        this.material = material;
        this.data = 0;
        this.amount = 1;
        this.enchantments = new HashMap<Enchantment, Integer>();
        this.itemFlags = new HashSet<ItemFlag>();
        this.tags = new HashMap();
        this.intTags = new HashMap();
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.material = itemStack.getType();
        this.data = itemStack.getDurability();
        this.amount = itemStack.getAmount();
        this.enchantments = new HashMap<Enchantment, Integer>();
        this.itemFlags = new HashSet<ItemFlag>();
        this.tags = new HashMap();
        this.intTags = new HashMap();
    }

    public ItemStack build() {
        if (this.itemStack != null) {
            if (this.material.equals(Material.SKULL_ITEM) && this.data == 3) {
                SkullMeta skullMeta = (SkullMeta)this.itemStack.getItemMeta();
                this.enchantments.forEach((key, value) -> skullMeta.addEnchant(key, value.intValue(), false));
                skullMeta.addItemFlags(this.itemFlags.toArray(new ItemFlag[0]));
                if (this.displayName != null) {
                    skullMeta.setDisplayName(TextUtil.colorize(this.displayName));
                }
                if (this.lore != null) {
                    skullMeta.setLore(TextUtil.colorize(this.lore));
                }
                this.itemStack.setItemMeta((ItemMeta)skullMeta);
                this.nbtItem = new NBTItem(this.itemStack);
                this.tags.forEach((key, value) -> this.nbtItem.setString((String)key, (String)value));
                this.intTags.forEach((key, value) -> this.nbtItem.setInteger((String)key, (int)value));
                return this.nbtItem.getItem();
            }
            if (this.material.toString().contains("LEATHER_")) {
                LeatherArmorMeta meta = (LeatherArmorMeta)this.itemStack.getItemMeta();
                this.enchantments.forEach((key, value) -> meta.addEnchant(key, value.intValue(), false));
                meta.addItemFlags(this.itemFlags.toArray(new ItemFlag[0]));
                if (this.displayName != null) {
                    meta.setDisplayName(TextUtil.colorize(this.displayName));
                }
                if (this.lore != null) {
                    meta.setLore(TextUtil.colorize(this.lore));
                }
                this.itemStack.setItemMeta((ItemMeta)meta);
                this.nbtItem = new NBTItem(this.itemStack);
                this.tags.forEach((key, value) -> this.nbtItem.setString((String)key, (String)value));
                this.intTags.forEach((key, value) -> this.nbtItem.setInteger((String)key, (int)value));
                return this.nbtItem.getItem();
            }
        }
        ItemStack item = new ItemStack(this.material, this.amount, (short)this.data);
        ItemMeta itemMeta = item.getItemMeta();
        this.enchantments.forEach((key, value) -> itemMeta.addEnchant(key, value.intValue(), false));
        itemMeta.addItemFlags(this.itemFlags.toArray(new ItemFlag[0]));
        if (this.displayName != null) {
            itemMeta.setDisplayName(TextUtil.colorize(this.displayName));
        }
        if (this.lore != null) {
            itemMeta.setLore(TextUtil.colorize(this.lore));
        }
        item.setItemMeta(itemMeta);
        this.nbtItem = new NBTItem(item);
        this.tags.forEach((key, value) -> this.nbtItem.setString((String)key, (String)value));
        this.intTags.forEach((key, value) -> this.nbtItem.setInteger((String)key, (int)value));
        return this.nbtItem.getItem();
    }

    public ItemBuilder setData(int data) {
        this.data = data;
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.amount = Math.min(amount, 64);
        return this;
    }

    public ItemBuilder setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ItemBuilder setLore(String ... lore) {
        this.lore = Arrays.asList(lore);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder addLore(String[] strings) {
        LinkedList<String> list = new LinkedList<String>(Arrays.asList(strings));
        this.lore.addAll(list);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int durability) {
        this.enchantments.put(enchantment, durability);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag itemFlag) {
        this.itemFlags.add(itemFlag);
        return this;
    }

    public ItemBuilder addTag(String key, String value) {
        this.tags.put(key, value);
        return this;
    }

    public ItemBuilder addTag(String key, int value) {
        this.intTags.put(key, value);
        return this;
    }

    public static String getTag(ItemStack itemStack, String key) {
        return new NBTItem(itemStack).getString(key);
    }

    public static ItemStack modifyTag(ItemStack itemStack, String key, String value) {
        NBTItem nbti = new NBTItem(itemStack);
        nbti.setString(key, value);
        return nbti.getItem();
    }
}

