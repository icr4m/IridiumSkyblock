package com.iridium.iridiumskyblock.configs;

import java.util.*;

public class BlockLimits {

    // Limites de base appliquées à toutes les îles (sans palier requis).
    // -1 = illimité. Les clés sont des noms XMaterial (ex: DIAMOND_BLOCK).
    public Map<String, Integer> defaultLimits = new LinkedHashMap<String, Integer>() {{
        put("DIAMOND_BLOCK", 10);
        put("EMERALD_BLOCK", 5);
        put("GOLD_BLOCK", 20);
    }};

    // Limites par ID de palier. Quand un palier est réclamé, ses limites remplacent
    // les defaultLimits pour les blocs qu'il définit.
    // La limite du palier réclamé le plus élevé est prioritaire.
    public Map<Integer, Map<String, Integer>> palierLimits = new LinkedHashMap<Integer, Map<String, Integer>>() {{
        put(1, new LinkedHashMap<String, Integer>() {{
            put("DIAMOND_BLOCK", 50);
            put("EMERALD_BLOCK", 20);
            put("GOLD_BLOCK", 100);
        }});
        put(2, new LinkedHashMap<String, Integer>() {{
            put("DIAMOND_BLOCK", 200);
            put("EMERALD_BLOCK", 100);
            put("GOLD_BLOCK", 500);
        }});
        put(3, new LinkedHashMap<String, Integer>() {{
            put("DIAMOND_BLOCK", -1);
            put("EMERALD_BLOCK", -1);
            put("GOLD_BLOCK", -1);
        }});
    }};

    // Message affiché quand la pose est annulée.
    // Placeholders : %prefix%, %block%, %current%, %limit%
    public String limitReachedMessage = "%prefix% &cVous avez atteint la limite de &e%limit% %block%&c sur votre île &e(%current%/%limit%)&c.";
}
