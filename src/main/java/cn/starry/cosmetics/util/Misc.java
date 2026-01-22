package cn.starry.cosmetics.util;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Misc {
    public static void noAI(Entity bukkitEntity) {
        net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity)bukkitEntity).getHandle();
        NBTTagCompound tag = nmsEntity.getNBTTag();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        nmsEntity.c(tag);
        tag.setInt("NoAI", 1);
        nmsEntity.f(tag);
    }

    public static void play(Location location, double radius, Sound sound, float volume, float pitch) {
        location.getWorld().getNearbyEntities(location, radius, radius, radius).stream().filter(e -> e.getType().equals((Object)EntityType.PLAYER)).forEach(p -> ((Player)p).playSound(p.getLocation(), sound, volume, pitch));
    }

    public static void play(Location location, double radius, String sound, float volume, float pitch) {
        location.getWorld().getNearbyEntities(location, radius, radius, radius).stream().filter(e -> e.getType().equals((Object)EntityType.PLAYER)).forEach(p -> ((Player)p).playSound(p.getLocation(), sound, volume, pitch));
    }

    public static List<Player> getPlayers(Location location, double radius) {
        ArrayList<Player> players = new ArrayList<Player>();
        location.getWorld().getNearbyEntities(location, radius, radius, radius).stream().filter(e -> e.getType().equals((Object)EntityType.PLAYER)).forEach(p -> players.add((Player)p));
        return players;
    }

    public static void play(Location location, double radius, Sound sound) {
        Misc.play(location, radius, sound, 1.0f, 1.0f);
    }
}

