package com.iridium.iridiumskyblock.enhancements;

import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class VoidEnhancementData extends SkyblockEnhancementData {
    public boolean enabled;
    public double itemLossChance;

    public VoidEnhancementData(int minLevel, int money, Map<String, Double> bankCosts, boolean enabled, double itemLossChance) {
        super(minLevel, money, bankCosts, null);
        this.enabled = enabled;
        this.itemLossChance = itemLossChance;
    }

    public VoidEnhancementData(int minLevel, int money, Map<String, Double> bankCosts, Map<String, Integer> inventoryCosts, boolean enabled, double itemLossChance) {
        super(minLevel, money, bankCosts, inventoryCosts);
        this.enabled = enabled;
        this.itemLossChance = itemLossChance;
    }
}
