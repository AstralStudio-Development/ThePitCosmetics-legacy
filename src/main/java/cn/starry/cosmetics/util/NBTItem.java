package cn.starry.cosmetics.util;

import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.jetbrains.annotations.NotNull;

public class NBTItem {
    private final ItemStack itemStack;

    public NBTItem(@NotNull org.bukkit.inventory.ItemStack item) {
        this.itemStack = CraftItemStack.asNMSCopy((org.bukkit.inventory.ItemStack)item);
        if (this.itemStack.getTag() == null) {
            this.itemStack.setTag(new NBTTagCompound());
        }
    }

    public void setString(String key, String value) {
        this.itemStack.getTag().setString(key, value);
    }

    public void setInteger(String key, int value) {
        this.itemStack.getTag().setInt(key, value);
    }

    public void setBoolean(String key, boolean value) {
        this.itemStack.getTag().setBoolean(key, value);
    }

    public String getString(String key) {
        return this.itemStack.getTag().getString(key);
    }

    public Integer getInteger(String key) {
        return this.itemStack.getTag().getInt(key);
    }

    public Boolean getBoolean(String key) {
        return this.itemStack.getTag().getBoolean(key);
    }

    public boolean hasKey(String key) {
        return this.itemStack.getTag().hasKey(key);
    }

    public org.bukkit.inventory.ItemStack getItem() {
        return CraftItemStack.asBukkitCopy((ItemStack)this.itemStack);
    }
}

