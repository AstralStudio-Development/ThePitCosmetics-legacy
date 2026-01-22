package cn.starry.cosmetics.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SkullUtil {
    private static final Map<String, ItemStack> skullCache = new HashMap<String, ItemStack>();

    private SkullUtil() {
    }

    public static ItemStack makeTextureSkull(String base64Texture) {
        if (skullCache.containsKey(base64Texture)) {
            return skullCache.get(base64Texture);
        }
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        if (base64Texture == null || base64Texture.isEmpty()) {
            return skull;
        }
        SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();
        GameProfile fakeProfile = new GameProfile(UUID.randomUUID(), "");
        fakeProfile.getProperties().put("textures", new Property("textures", base64Texture));
        try {
            Field profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, fakeProfile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        skull.setItemMeta((ItemMeta)skullMeta);
        skullCache.put(base64Texture, skull);
        return skull;
    }
}

