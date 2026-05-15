package com.iridium.iridiumskyblock.configs;

import com.cryptomorin.xseries.XMaterial;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableMap;
import com.iridium.iridiumcore.Background;
import com.iridium.iridiumcore.Item;
import com.iridium.iridiumskyblock.configs.inventories.BorderInventoryConfig;
import com.iridium.iridiumteams.configs.inventories.InventoryConfig;
import com.iridium.iridiumteams.configs.inventories.NoItemGUI;
import com.iridium.iridiumteams.configs.inventories.SingleItemGUI;

import java.util.Arrays;
import java.util.Collections;

public class Inventories extends com.iridium.iridiumteams.configs.Inventories {

    @JsonIgnore
    private final Background background1 = new Background(ImmutableMap.<Integer, Item>builder().build());
    @JsonIgnore
    private final Background background2 = new Background(ImmutableMap.<Integer, Item>builder()
            .put(9, new Item(XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()))
            .put(10, new Item(XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()))
            .put(11, new Item(XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()))
            .put(12, new Item(XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()))
            .put(13, new Item(XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()))
            .put(14, new Item(XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()))
            .put(15, new Item(XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()))
            .put(16, new Item(XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()))
            .put(17, new Item(XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()))
            .build());

    public BorderInventoryConfig islandBorderGUI = new BorderInventoryConfig(27, "&7Bordure de l'île", background2,
            new Item(XMaterial.BLUE_STAINED_GLASS_PANE, 10, 1, "&9&lBleu", Collections.emptyList()),
            new Item(XMaterial.RED_STAINED_GLASS_PANE, 12, 1, "&c&lRouge", Collections.emptyList()),
            new Item(XMaterial.GREEN_STAINED_GLASS_PANE, 14, 1, "&a&lVert", Collections.emptyList()),
            new Item(XMaterial.WHITE_STAINED_GLASS_PANE, 16, 1, "&f&lOff", Collections.emptyList())
    );

    public InventoryConfig islandMenu = new InventoryConfig(36, "&7Menu de l'île", background1, ImmutableMap.<String, Item>builder()
            .put("is home", new Item(XMaterial.WHITE_BED, 10, 1, "&9&lAccueil de l'île", Collections.singletonList("&7Téléportez-vous à votre île")))
            .put("is members", new Item(XMaterial.PLAYER_HEAD, 12, "Peaches_MLG", 1, "&9&lMembres de l'île", Collections.singletonList("&7Voir les membres de votre île")))
            .put("is warps", new Item(XMaterial.END_PORTAL_FRAME, 14, 1, "&9&lWarps de l'île", Collections.singletonList("&7Voir les warps de votre île")))
            .put("is upgrades", new Item(XMaterial.DIAMOND, 16, 1, "&9&lAméliorations de l'île", Collections.singletonList("&7Voir les améliorations de votre île")))
            .put("is border", new Item(XMaterial.BEACON, 19, 1, "&9&lBordure de l'île", Collections.singletonList("&7Changer la bordure de votre île")))
            .put("is bank", new Item(XMaterial.PLAYER_HEAD, 21, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM4MWM1MjlkNTJlMDNjZDc0YzNiZjM4YmI2YmEzZmRlMTMzN2FlOWJmNTAzMzJmYWE4ODllMGEyOGU4MDgxZiJ9fX0", 1, "&9&lBanque de l'île", Collections.singletonList("&7Voir la banque de votre île")))
            .put("is permissions", new Item(XMaterial.WRITABLE_BOOK, 23, 1, "&9&lPermissions de l'île", Collections.singletonList("&7Voir les permissions de votre île")))
            .put("is paliers", new Item(XMaterial.NETHER_STAR, 25, 1, "&9&lPaliers de l'île", Collections.singletonList("&7Voir les paliers de votre île")))
            .put("is delete", new Item(XMaterial.BARRIER, 35, 1, "&9&lSupprimer l'île", Collections.singletonList("&7Supprimer votre île")))
            .build()
    );

    public SingleItemGUI visitGUI = new SingleItemGUI(54, "&7Visiter une île", background1, new Item(XMaterial.PLAYER_HEAD, 0, "%island_owner%", 1, "&9&l%island_name%", Arrays.asList(
            "&9Créée : &7%island_create%",
            "&9Propriétaire : &7%island_owner%"
    )));

    public NoItemGUI islandSchematicGUI = new NoItemGUI(27, "&7Choisir un schéma", background2);
    public NoItemGUI biomeOverviewGUI = new NoItemGUI(27, "Biomes", background2);
    public NoItemGUI biomeCategoryGUI = new NoItemGUI(54, "Biomes - %biome_category_name%", background1);

    public Inventories() {
        super("Island", "&9");
        missionTypeSelectorGUI.weekly.enabled = false;
    }
}
