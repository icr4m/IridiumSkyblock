package com.iridium.iridiumskyblock.configs;

import com.google.common.collect.ImmutableMap;
import com.iridium.iridiumcore.Background;
import com.iridium.iridiumteams.configs.inventories.NoItemGUI;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

public class Paliers {

    public NoItemGUI gui = new NoItemGUI(27, "&9&lPaliers", new Background(ImmutableMap.of()));

    public List<PalierConfig> paliers = Arrays.asList(
            new PalierConfig(1, 1000, "size", 2,
                    "&9&lPalier 1 &8| &7Taille II",
                    Arrays.asList("&7Atteignez &9%required_value% &7de valeur d'île", "&7pour débloquer le niveau &9Taille &72."),
                    10),
            new PalierConfig(2, 5000, "generator", 2,
                    "&9&lPalier 2 &8| &7Générateur II",
                    Arrays.asList("&7Atteignez &9%required_value% &7de valeur d'île", "&7pour débloquer le niveau &9Générateur &72."),
                    12),
            new PalierConfig(3, 20000, "size", 3,
                    "&9&lPalier 3 &8| &7Taille III",
                    Arrays.asList("&7Atteignez &9%required_value% &7de valeur d'île", "&7pour débloquer le niveau &9Taille &73."),
                    14)
    );

    public String lockedLore = "&8[Verrouillé] &cValeur requise : &4%required_value%";
    public String claimableLore = "&a▶ Cliquez pour récupérer !";
    public String claimedLore = "&2✔ Récupéré";
    public String notEnoughValueMessage = "%prefix% &7La valeur de votre île est trop basse pour réclamer ce palier.";
    public String newPalierMessage = "%prefix% &7Un nouveau palier est disponible ! Utilisez &9/is paliers &7pour le récupérer.";

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PalierConfig {
        public int id;
        public double requiredValue;
        public String enhancement;
        public int enhancementLevel;
        public String name;
        public List<String> lore;
        public int slot;
    }
}
