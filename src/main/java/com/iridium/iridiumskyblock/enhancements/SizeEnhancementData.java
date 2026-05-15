package com.iridium.iridiumskyblock.enhancements;

import com.iridium.iridiumcore.utils.Placeholder;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class SizeEnhancementData extends SkyblockEnhancementData {
    public int size;

    public SizeEnhancementData(int minLevel, int money, Map<String, Double> bankCosts, int size) {
        super(minLevel, money, bankCosts, null);
        this.size = size;
    }

    public SizeEnhancementData(int minLevel, int money, Map<String, Double> bankCosts, Map<String, Integer> inventoryCosts, int size) {
        super(minLevel, money, bankCosts, inventoryCosts);
        this.size = size;
    }

    @Override
    public List<Placeholder> getPlaceholders() {
        return Arrays.asList(
                new Placeholder("size", String.valueOf(size))
        );
    }
}
