package com.iridium.iridiumskyblock.configs;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.iridium.iridiumcore.Item;
import com.iridium.iridiumskyblock.enhancements.CropBoostEnhancementData;
import com.iridium.iridiumskyblock.enhancements.GeneratorEnhancementData;
import com.iridium.iridiumskyblock.enhancements.SizeEnhancementData;
import com.iridium.iridiumskyblock.enhancements.VoidEnhancementData;
import com.iridium.iridiumteams.enhancements.Enhancement;
import com.iridium.iridiumteams.enhancements.EnhancementType;

import java.util.Arrays;

public class Enhancements extends com.iridium.iridiumteams.configs.Enhancements {

    public Enhancements() {
        super("&9");

        // Disable booster-type enhancements
        this.farmingEnhancement.enabled = false;
        this.spawnerEnhancement.enabled = false;
        this.experienceEnhancement.enabled = false;
        this.flightEnhancement.enabled = false;

        // Disable unused upgrade-type enhancements
        this.potionEnhancements.get("haste").enabled = false;
        this.potionEnhancements.get("speed").enabled = false;
        this.potionEnhancements.get("jump").enabled = false;
        this.warpsEnhancement.enabled = false;

        this.membersEnhancement.item.slot = 10;
        this.membersEnhancement.levels.forEach((integer, membersEnhancementData) -> membersEnhancementData.bankCosts = new ImmutableMap.Builder<String, Double>().put("Crystals", 5.00).build());
    }

    public Enhancement<SizeEnhancementData> sizeEnhancement = new Enhancement<>(true, EnhancementType.UPGRADE, new Item(XMaterial.GRASS_BLOCK, 11, 1, "&9&lSize Upgrade", Arrays.asList(
            "&7Need more room to expand? Buy this",
            "&7upgrade to increase your island size.",
            "",
            "&9&lInformation:",
            "&9&l * &7Current Level: &9%current_level%",
            "&9&l * &7Current Size: &9%size%x%size% Blocks",
            "&9&l * &7Upgrade Cost: &9%vault_cost%, %Crystals_cost% Island Crystals",
            "&9&lLevels:",
            "&9&l * &7Level 1: &950x50 Blocks",
            "&9&l * &7Level 2: &975x75 Blocks",
            "&9&l * &7Level 3: &9100x100 Blocks",
            "&9&l * &7Level 4: &9125x125 Blocks",
            "&9&l * &7Level 5: &9150x150 Blocks",
            "",
            "&9&l[!] &7Obtenu via &9/is paliers&7."
    )), new ImmutableMap.Builder<Integer, SizeEnhancementData>()
            .put(0, new SizeEnhancementData(5, 10000, new ImmutableMap.Builder<String, Double>().put("Crystals", 5.00).build(), 50))
            .put(1, new SizeEnhancementData(5, 10000, new ImmutableMap.Builder<String, Double>().put("Crystals", 5.00).build(), 75))
            .put(2, new SizeEnhancementData(10, 10000, new ImmutableMap.Builder<String, Double>().put("Crystals", 5.00).build(), 100))
            .put(3, new SizeEnhancementData(10, 10000, new ImmutableMap.Builder<String, Double>().put("Crystals", 5.00).build(), 125))
            .put(4, new SizeEnhancementData(15, 10000, new ImmutableMap.Builder<String, Double>().put("Crystals", 5.00).build(), 150))
            .build());

    public Enhancement<VoidEnhancementData> voidEnhancement = new Enhancement<>(false, EnhancementType.UPGRADE, new Item(XMaterial.RED_BED, 0, 1, "&9&lVoid Upgrade", Arrays.asList(
            "&7Keep falling off your island?",
            "&7upgrade to improve void teleport.",
            "",
            "&9&lInformation:",
            "&9&l * &7Current Level: &9%current_level%",
            "&9&l * &7Upgrade Cost: &9%vault_cost%, %Crystals_cost% Island Crystals",
            "&9&lLevels:",
            "&9&l * &7Level 1: &9No Void Teleport",
            "&9&l * &7Level 2: &9100% Item Loss",
            "&9&l * &7Level 3: &975% Item Loss",
            "&9&l * &7Level 4: &950% Item Loss",
            "&9&l * &7Level 5: &925% Item Loss",
            "&9&l * &7Level 6: &90% Item Loss",
            "",
            "&9[!] &7Must be level %minLevel% to purchase",
            "&9&l[!] " + "&9Left Click to Purchase Level %next_level%."
    )), new ImmutableMap.Builder<Integer, VoidEnhancementData>()
            .put(0, new VoidEnhancementData(5, 10000, new ImmutableMap.Builder<String, Double>().put("Crystals", 5.00).build(), true, 0.00))
            .build());

    public Enhancement<CropBoostEnhancementData> cropBoostEnhancement = new Enhancement<>(true, EnhancementType.UPGRADE, new Item(XMaterial.WHEAT, 12, 1, "&9&lCrop Boost", Arrays.asList(
            "&7Augmente les drops de vos cultures.",
            "",
            "&9&lInformations :",
            "&9&l * &7Niveau actuel : &9%current_level%",
            "&9&l * &7Coût : &9%vault_cost%, %Crystals_cost% Crystals",
            "&9&lNiveaux :",
            "&9&l * &7Niveau 1 : &9+25% drops",
            "&9&l * &7Niveau 2 : &9+50% drops",
            "&9&l * &7Niveau 3 : &9+75% drops",
            "&9&l * &7Niveau 4 : &9+100% drops",
            "",
            "&9&l[!] &7Obtenu via &9/is paliers&7."
    )), new ImmutableMap.Builder<Integer, CropBoostEnhancementData>()
            .put(0, new CropBoostEnhancementData(0, 0, new ImmutableMap.Builder<String, Double>().put("Crystals", 500.00).build(), 0.25))
            .put(1, new CropBoostEnhancementData(0, 0, new ImmutableMap.Builder<String, Double>().put("Crystals", 1000.00).build(), 0.50))
            .put(2, new CropBoostEnhancementData(0, 0, new ImmutableMap.Builder<String, Double>().put("Crystals", 2000.00).build(), 0.75))
            .put(3, new CropBoostEnhancementData(0, 0, new ImmutableMap.Builder<String, Double>().put("Crystals", 4000.00).build(), 1.00))
            .build());

    public Enhancement<GeneratorEnhancementData> generatorEnhancement = new Enhancement<>(true, EnhancementType.UPGRADE, new Item(XMaterial.DIAMOND_ORE, 13, 1, "&9&lGenerator Upgrade", Arrays.asList(
            "&7Want to improve your generator? Buy this",
            "&7upgrade to improve your island generator.",
            "",
            "&9&lInformation:",
            "&9&l * &7Current Level: &9%current_level%",
            "&9&l * &7Upgrade Cost: &9%vault_cost%, %Crystals_cost% Island Crystals",
            "",
            "&9[!] &7Must be level %minLevel% to purchase",
            "&9&l[!] " + "&9Left Click to Purchase Level %next_level%."
    )), new ImmutableMap.Builder<Integer, GeneratorEnhancementData>()
            .put(0, new GeneratorEnhancementData(5, 10000, new ImmutableMap.Builder<String, Double>().put("Crystals", 5.00).build(),
                    ImmutableMap.<String, Integer>builder()
                            .put("COBBLESTONE", 3)
                            .put("COAL_ORE", 1)
                            .build(),
                    ImmutableMap.<String, Integer>builder()
                            .put("BASALT", 1)
                            .build()))
            .put(1, new GeneratorEnhancementData(5, 10000, new ImmutableMap.Builder<String, Double>().put("Crystals", 5.00).build(),
                    ImmutableMap.<String, Integer>builder()
                            .put("REDSTONE_ORE", 10)
                            .put("LAPIS_ORE", 10)
                            .put("COAL_ORE", 20)
                            .put("COBBLESTONE", 40)
                            .build(),
                    ImmutableMap.<String, Integer>builder()
                            .put("BASALT", 20)
                            .put("GLOWSTONE", 20)
                            .put("NETHERRACK", 20)
                            .build()))
            .put(2, new GeneratorEnhancementData(5, 10000, new ImmutableMap.Builder<String, Double>().put("Crystals", 5.00).build(),
                    ImmutableMap.<String, Integer>builder()
                            .put("IRON_ORE", 10)
                            .put("REDSTONE_ORE", 10)
                            .put("GOLD_ORE", 10)
                            .put("LAPIS_ORE", 10)
                            .put("COAL_ORE", 20)
                            .put("COBBLESTONE", 40)
                            .build(),
                    ImmutableMap.<String, Integer>builder()
                            .put("BASALT", 10)
                            .put("GLOWSTONE", 10)
                            .put("NETHER_QUARTZ_ORE", 10)
                            .put("NETHER_GOLD_ORE", 10)
                            .put("NETHERRACK", 10)
                            .build()))
            .put(3, new GeneratorEnhancementData(5, 10000, new ImmutableMap.Builder<String, Double>().put("Crystals", 5.00).build(),
                    ImmutableMap.<String, Integer>builder()
                            .put("DIAMOND_ORE", 3)
                            .put("IRON_ORE", 10)
                            .put("REDSTONE_ORE", 10)
                            .put("GOLD_ORE", 10)
                            .put("LAPIS_ORE", 10)
                            .put("COAL_ORE", 20)
                            .put("COBBLESTONE", 40)
                            .build(),
                    ImmutableMap.<String, Integer>builder()
                            .put("BASALT", 10)
                            .put("GLOWSTONE", 10)
                            .put("NETHER_QUARTZ_ORE", 10)
                            .put("NETHER_GOLD_ORE", 10)
                            .put("NETHERRACK", 10)
                            .put("ANCIENT_DEBRIS", 1)
                            .build()))
            .build());
}
