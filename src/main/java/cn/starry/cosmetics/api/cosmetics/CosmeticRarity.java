/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 */
package cn.starry.cosmetics.api.cosmetics;

import org.bukkit.ChatColor;

public enum CosmeticRarity {
    COMMON(0, ChatColor.GREEN,"普通"),
    RARE(1, ChatColor.AQUA,"稀有"),
    EPIC(2, ChatColor.DARK_PURPLE,"史诗"),
    LEGENDARY(3, ChatColor.GOLD,"传奇");

    private final int id;
    private final ChatColor color;
    private final String displayName;

    private CosmeticRarity(int id, ChatColor color, String displayName) {
        this.id = id;
        this.color = color;
        this.displayName = color + displayName;
    }

    public int getId() {
        return this.id;
    }

    public ChatColor getColor() {
        return this.color;
    }

    public String getDisplayName() {
        return this.displayName;
    }

}

