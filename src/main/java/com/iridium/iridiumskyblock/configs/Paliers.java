package com.iridium.iridiumskyblock.configs;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.iridium.iridiumcore.Background;
import com.iridium.iridiumcore.Item;
import com.iridium.iridiumteams.configs.inventories.NoItemGUI;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Paliers {

    public NoItemGUI gui = new NoItemGUI(27, "&9&lPaliers", new Background(ImmutableMap.of()));

    public List<PalierConfig> paliers = Arrays.asList(
            new PalierConfig(1, 1000, "size", 2,
                    new Item(XMaterial.IRON_INGOT, 1, "&9&lPalier 1 &8| &7Size II",
                            Arrays.asList("&7Reach &9%required_value% &7island value", "&7to unlock &9Size &7level &92.")),
                    10),
            new PalierConfig(2, 5000, "generator", 2,
                    new Item(XMaterial.GOLD_INGOT, 1, "&9&lPalier 2 &8| &7Generator II",
                            Arrays.asList("&7Reach &9%required_value% &7island value", "&7to unlock &9Generator &7level &92.")),
                    12),
            new PalierConfig(3, 20000, "size", 3,
                    new Item(XMaterial.DIAMOND, 1, "&9&lPalier 3 &8| &7Size III",
                            Arrays.asList("&7Reach &9%required_value% &7island value", "&7to unlock &9Size &7level &93.")),
                    14)
    );

    public String lockedLore = "&8[Locked] &cRequired value: &4%required_value%";
    public String claimableLore = "&a▶ Click to claim!";
    public String claimedLore = "&2✔ Claimed";
    public String notEnoughValueMessage = "%prefix% &7Your island's value is too low to claim this palier.";
    public String newPalierMessage = "%prefix% &7A new palier is available! Use &9/is paliers &7to claim it.";

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PalierConfig {
        public int id;
        public double requiredValue;
        public String enhancement;
        public int enhancementLevel;
        public Item item;
        public int slot;
    }
}
