package com.iridium.iridiumskyblock.enhancements;

import com.iridium.iridiumteams.enhancements.EnhancementData;

import java.util.HashMap;
import java.util.Map;

public class SkyblockEnhancementData extends EnhancementData {

    public Map<String, Integer> inventoryCosts = new HashMap<>();

    public SkyblockEnhancementData() {}

    public SkyblockEnhancementData(int minLevel, int money, Map<String, Double> bankCosts, Map<String, Integer> inventoryCosts) {
        super(minLevel, money, bankCosts);
        this.inventoryCosts = inventoryCosts != null ? inventoryCosts : new HashMap<>();
    }
}
