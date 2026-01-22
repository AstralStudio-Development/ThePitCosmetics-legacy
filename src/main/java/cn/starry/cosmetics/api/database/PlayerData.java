package cn.starry.cosmetics.api.database;

import cn.starry.cosmetics.api.cosmetics.CosmeticType;

import java.util.*;

public class PlayerData {
    private final UUID uuid;
    private String name;
    private final LinkedHashMap<Integer, Integer> selectedCosmeticsMap;
    private final LinkedHashMap<Integer, LinkedList<Integer>> ownedCosmeticsMap;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
        this.selectedCosmeticsMap = new LinkedHashMap<>();
        this.ownedCosmeticsMap = new LinkedHashMap<>();
        Arrays.stream(CosmeticType.values()).forEach(cosmeticType -> this.setSelectedCosmetic(cosmeticType.getId(), -1));
    }

    public int getSelectedCosmetic(int cosmeticTypeId) {
        return this.selectedCosmeticsMap.get(cosmeticTypeId);
    }

    public int getSelectedCosmetic(CosmeticType cosmeticType) {
        return this.selectedCosmeticsMap.getOrDefault(cosmeticType.getId(), -1);
    }

    public void setSelectedCosmetic(int cosmeticTypeId, int cosmeticId) {
        this.selectedCosmeticsMap.put(cosmeticTypeId, cosmeticId);
    }

    public boolean isSelected(int cosmeticTypeId, int cosmeticId) {
        return this.getSelectedCosmetic(cosmeticTypeId) == cosmeticId;
    }

    public void addOwnedCosmetic(int cosmeticTypeId, int cosmeticId) {
        if (!this.ownedCosmeticsMap.containsKey(cosmeticTypeId)) {
            this.ownedCosmeticsMap.put(cosmeticTypeId, new LinkedList<>());
        }
        this.ownedCosmeticsMap.get(cosmeticTypeId).add(cosmeticId);
    }

    public List<Integer> getOwnedCosmetics(int cosmeticTypeId) {
        return this.ownedCosmeticsMap.get(cosmeticTypeId);
    }

    public List<Integer> getOwnedCosmetics(CosmeticType ct) {
        return this.getOwnedCosmetics(ct.getId());
    }

    public boolean hasEffect(int typeId, int id) {
        if (!this.ownedCosmeticsMap.containsKey(typeId)) {
            return false;
        }
        return this.ownedCosmeticsMap.get(typeId).contains(id);
    }

    public LinkedHashMap<Integer, LinkedList<Integer>> getOwnedCosmetics() {
        return this.ownedCosmeticsMap;
    }

    public LinkedHashMap<Integer, Integer> getSelectedCosmeticsMap() {
        return this.selectedCosmeticsMap;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
