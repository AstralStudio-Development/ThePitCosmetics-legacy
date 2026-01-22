package cn.starry.cosmetics.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class Cuboid {
    private Location loc1;
    private Location loc2;
    World world;
    int topBlockX;
    int bottomBlockX;
    int topBlockY;
    int bottomBlockY;
    int topBlockZ;
    int bottomBlockZ;
    List<Block> blocks;

    public Cuboid(Location loc1, Location loc2) {
        this.loc1 = loc1;
        this.loc2 = loc2;
        this.world = loc1.getWorld();
        this.topBlockX = Math.max(loc1.getBlockX(), loc2.getBlockX());
        this.bottomBlockX = Math.min(loc1.getBlockX(), loc2.getBlockX());
        this.topBlockY = Math.max(loc1.getBlockY(), loc2.getBlockY());
        this.bottomBlockY = Math.min(loc1.getBlockY(), loc2.getBlockY());
        this.topBlockZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());
        this.bottomBlockZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
    }

    public Cuboid(Location loc, int radius) {
        this.world = loc.getWorld();
        this.topBlockX = Math.max(loc.clone().add((double)radius, 0.0, 0.0).getBlockX(), loc.clone().subtract((double)radius, 0.0, 0.0).getBlockX());
        this.bottomBlockX = Math.min(loc.clone().add((double)radius, 0.0, 0.0).getBlockX(), loc.clone().subtract((double)radius, 0.0, 0.0).getBlockX());
        this.topBlockY = Math.max(loc.clone().add(0.0, (double)radius, 0.0).getBlockY(), loc.clone().subtract(0.0, (double)radius, 0.0).getBlockY());
        this.bottomBlockY = Math.min(loc.clone().add(0.0, (double)radius, 0.0).getBlockY(), loc.clone().subtract(0.0, (double)radius, 0.0).getBlockY());
        this.topBlockZ = Math.max(loc.clone().add(0.0, 0.0, (double)radius).getBlockZ(), loc.clone().subtract(0.0, 0.0, (double)radius).getBlockZ());
        this.bottomBlockZ = Math.min(loc.clone().add(0.0, 0.0, (double)radius).getBlockZ(), loc.clone().subtract(0.0, 0.0, (double)radius).getBlockZ());
        this.blocks = this.getBlocks();
    }

    public boolean isInCached(Block block) {
        return this.blocks.contains(block);
    }

    public List<Block> getCachedBlocks() {
        return this.blocks;
    }

    public List<Block> getAirBlocks() {
        ArrayList<Block> blocks = new ArrayList<Block>();
        for (int x = this.bottomBlockX; x <= this.topBlockX; ++x) {
            for (int z = this.bottomBlockZ; z <= this.topBlockZ; ++z) {
                for (int y = this.bottomBlockY; y <= this.topBlockY; ++y) {
                    Block block = this.world.getBlockAt(x, y, z);
                    if (!block.getType().equals((Object)Material.AIR)) continue;
                    blocks.add(block);
                }
            }
        }
        return blocks;
    }

    public List<Block> getBlocks() {
        ArrayList<Block> blocks = new ArrayList<Block>();
        for (int x = this.bottomBlockX; x <= this.topBlockX; ++x) {
            for (int z = this.bottomBlockZ; z <= this.topBlockZ; ++z) {
                for (int y = this.bottomBlockY; y <= this.topBlockY; ++y) {
                    Block block = this.world.getBlockAt(x, y, z);
                    if (block.getType().equals((Object)Material.AIR)) continue;
                    blocks.add(block);
                }
            }
        }
        return blocks;
    }

    public boolean isIn(Location loc) {
        return this.isIn(loc.getBlock());
    }

    public boolean isIn(Block block) {
        return this.getBlocks().contains(block);
    }

    public Location getLoc1() {
        return this.loc1;
    }

    public Location getLoc2() {
        return this.loc2;
    }
}

