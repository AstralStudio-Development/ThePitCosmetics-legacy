package cn.starry.cosmetics.api.database;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class SortedData
{
    public static HashMap<Player, SortedType> SORTED;

    static {
        SortedData.SORTED = new HashMap<Player, SortedType>();
    }
}
