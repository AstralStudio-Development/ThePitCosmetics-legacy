/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.inventory.ItemStack
 */
package cn.starry.cosmetics.api.cosmetics;

import org.bukkit.inventory.ItemStack;

import java.text.NumberFormat;
import java.util.List;
import java.util.Objects;

public abstract class Cosmetic {
    public abstract String getDisplayName();

    public abstract List<String> getDescription();

    public abstract ItemStack getIcon();

    public abstract int getPrice();

    public abstract CosmeticRarity getCosmeticRarity();

    public abstract CosmeticType getCosmeticType();

    public abstract int getId();

    public abstract boolean BuyAble();

    public String getFormattedPrice() {
        NumberFormat f = NumberFormat.getInstance();
        f.setGroupingUsed(true);
        return f.format(this.getPrice());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Cosmetic playerCache = (Cosmetic)o;
        return Objects.equals(this.getId(), playerCache.getId()) && Objects.equals((Object)this.getCosmeticType(), (Object)playerCache.getCosmeticType());
    }

    public int hashCode() {
        return Objects.hash(this.getCosmeticType().getId() + "_" + this.getId());
    }
}

