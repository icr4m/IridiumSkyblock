package com.iridium.iridiumskyblock.enhancements;

import com.iridium.iridiumteams.enhancements.EnhancementData;

import java.util.Map;

public class CropBoostEnhancementData extends EnhancementData {

    public double cropBoostPercentage;

    public CropBoostEnhancementData(int minLevel, int money, Map<String, Double> bankCosts, double cropBoostPercentage) {
        super(minLevel, money, bankCosts);
        this.cropBoostPercentage = cropBoostPercentage;
    }

    public CropBoostEnhancementData() {}
}
