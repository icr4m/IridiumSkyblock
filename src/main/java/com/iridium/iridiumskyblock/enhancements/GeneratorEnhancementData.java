package com.iridium.iridiumskyblock.enhancements;

import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class GeneratorEnhancementData extends SkyblockEnhancementData {
    public Map<String, Integer> ores;
    public Map<String, Integer> netherOres;

    public GeneratorEnhancementData(int minLevel, int money, Map<String, Double> bankCosts, Map<String, Integer> ores, Map<String, Integer> netherOres) {
        super(minLevel, money, bankCosts, null);
        this.ores = ores;
        this.netherOres = netherOres;
    }

    public GeneratorEnhancementData(int minLevel, int money, Map<String, Double> bankCosts, Map<String, Integer> inventoryCosts, Map<String, Integer> ores, Map<String, Integer> netherOres) {
        super(minLevel, money, bankCosts, inventoryCosts);
        this.ores = ores;
        this.netherOres = netherOres;
    }
}
