package cn.starry.cosmetics.listeners.cosmetics;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticType;
import cn.starry.cosmetics.api.cosmetics.deathCries.DeathCryEffect;
import cn.starry.cosmetics.api.database.PlayerData;
import cn.starry.cosmetics.util.classutils.interfaces.AutoRegister;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

public class DeathCryListener implements Listener {
    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        if (Cosmetics.getInstance().getConfig().getBoolean("available.deathCry")) {
            Player victim = e.getEntity().getPlayer();
            Player attacker = e.getEntity().getKiller();
            if (victim != null) {
                if (victim.hasMetadata("NPC")) {
                    return;
                }
                PlayerData pd = Cosmetics.getPlayerData(victim);
                int id = pd.getSelectedCosmetic(CosmeticType.DEATH_CRY);
                if (id == -1) {
                    return;
                }
                if (id == 0) {
                    LinkedList<Integer> indexes = new LinkedList<>();
                    pd.getOwnedCosmetics().entrySet().stream().filter(m -> (m.getKey()).equals(CosmeticType.DEATH_CRY.getId())).forEach(map -> indexes.addAll((Collection) map.getValue()));
                    if (indexes.isEmpty()) {
                        return;
                    }
                    int randIndex = indexes.get(new Random().nextInt(indexes.size()));
                    DeathCryEffect.getById(randIndex).runSound(victim.getLocation());
                    return;
                }
                DeathCryEffect.getById(id).runSound(victim.getLocation());
            }
        }
    }
}

