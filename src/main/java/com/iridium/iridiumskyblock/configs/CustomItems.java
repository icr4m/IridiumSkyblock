package com.iridium.iridiumskyblock.configs;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedHashMap;
import java.util.Map;

public class CustomItems {

    public Map<String, CustomItemDefinition> items = new LinkedHashMap<>();

    public CustomItems() {
        items.put("mineral_jade", new CustomItemDefinition("PAPER", 10007));
    }

    @NoArgsConstructor
    @AllArgsConstructor
    public static class CustomItemDefinition {
        public String material = "PAPER";
        public int customModelData = 0;

        public boolean matches(ItemStack item) {
            if (item == null || item.getType().isAir()) return false;
            if (!item.getType().name().equals(material)) return false;
            if (customModelData > 0) {
                ItemMeta meta = item.getItemMeta();
                return meta != null && meta.hasCustomModelData() && meta.getCustomModelData() == customModelData;
            }
            return true;
        }
    }
}
