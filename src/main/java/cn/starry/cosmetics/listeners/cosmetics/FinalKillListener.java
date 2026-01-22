package cn.starry.cosmetics.listeners.cosmetics;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticType;
import cn.starry.cosmetics.api.cosmetics.finalKill.FinalKillCosmetic;
import cn.starry.cosmetics.api.database.PlayerData;
import cn.starry.cosmetics.util.classutils.interfaces.AutoRegister;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.LinkedList;
import java.util.Random;

public class FinalKillListener implements Listener {
    @EventHandler
    public void onFinalKill(PlayerDeathEvent e) {
        if (Cosmetics.getInstance().getConfig().getBoolean("available.killEffect")) {
            Player victim = e.getEntity().getPlayer();
            Player attacker = e.getEntity().getKiller();
            if (attacker != null) {
                if (victim.hasMetadata("NPC")) {
                    return;
                }
                PlayerData pd = Cosmetics.getPlayerData(attacker);
                int id = pd.getSelectedCosmetic(CosmeticType.FINAL_KILL);
                if (id == -1) {
                    return;
                }
                if (id == 0) {
                    LinkedList<Integer> indexes = new LinkedList<>();
                    pd.getOwnedCosmetics().entrySet().stream().filter(m -> m.getKey().equals(CosmeticType.FINAL_KILL.getId())).forEach(map -> indexes.addAll(map.getValue()));
                    if (indexes.isEmpty()) {
                        return;
                    }
                    int randIndex = indexes.get(new Random().nextInt(indexes.size()));
                    FinalKillCosmetic.getById(indexes.get(randIndex)).runEffect(victim.getLocation());
                    return;
                }
                FinalKillCosmetic.getById(id).runEffect(victim.getLocation());
            }
        }
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent e) {
        if (!(e.getEntity() instanceof TNTPrimed)) {
            return;
        }
        if (!e.getEntity().hasMetadata("FinalKillEffect")) {
            return;
        }
        e.setCancelled(true);
    }
}

