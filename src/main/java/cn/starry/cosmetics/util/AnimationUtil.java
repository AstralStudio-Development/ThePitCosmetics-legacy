package cn.starry.cosmetics.util;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

public class AnimationUtil {
    public static void playBlockBreak(Location location, Material material) {
        PacketPlayOutWorldEvent ppowe = new PacketPlayOutWorldEvent(2001, new BlockPosition(location.getX(), location.getY(), location.getZ()), material.getId(), false);
        Bukkit.getOnlinePlayers().forEach(p -> ((CraftPlayer)p).getHandle().playerConnection.sendPacket(ppowe));
    }
}

