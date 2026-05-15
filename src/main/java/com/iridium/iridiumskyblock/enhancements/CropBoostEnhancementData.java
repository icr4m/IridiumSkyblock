package com.iridium.iridiumskyblock.enhancements;

import java.util.Map;

public class CropBoostEnhancementData extends SkyblockEnhancementData {

    public double cropBoostPercentage;

    public CropBoostEnhancementData() {}

    public CropBoostEnhancementData(int minLevel, int money, Map<String, Double> bankCosts, double cropBoostPercentage) {
        super(minLevel, money, bankCosts, null);
        this.cropBoostPercentage = cropBoostPercentage;
    }

    public CropBoostEnhancementData(int minLevel, int money, Map<String, Double> bankCosts, Map<String, Integer> inventoryCosts, double cropBoostPercentage) {
        super(minLevel, money, bankCosts, inventoryCosts);
        this.cropBoostPercentage = cropBoostPercentage;
    }
}
