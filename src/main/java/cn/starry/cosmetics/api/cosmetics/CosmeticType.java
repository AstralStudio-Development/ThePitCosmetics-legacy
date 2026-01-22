/*
 * Decompiled with CFR 0.152.
 */
package cn.starry.cosmetics.api.cosmetics;

import java.util.Arrays;

public enum CosmeticType {
    PROJECTILE_TRAIL(0),
    FINAL_KILL(2),
    DEATH_CRY(5);

    private final int id;

    private CosmeticType(int id) {
        this.id = id;
    }

    public static CosmeticType getCosmeticType(int id) {
        return Arrays.stream(CosmeticType.values()).filter(ct -> ct.getId() == id).findFirst().orElse(null);
    }

    public int getId() {
        return this.id;
    }

}

