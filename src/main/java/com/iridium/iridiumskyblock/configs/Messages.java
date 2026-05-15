package com.iridium.iridiumskyblock.configs;

public class Messages extends com.iridium.iridiumteams.configs.Messages {

    public Messages() {
        super("Island", "is", "IridiumSkyblock", "&9");

        teamCreated = "%prefix% &7Création de l'île terminée !";
    }

    public String voidTeleport = "%prefix% &7Vous êtes tombé de votre île. Téléportation à la maison...";
    public String itemsString = "%amount% %item_name%";
    public String voidLostItems = "%prefix% &7Vous avez perdu %items% !";
    public String netherIslandsDisabled = "%prefix% &7Les îles du Nether ont été désactivées.";
    public String netherLocked = "%prefix% &7Atteignez le niveau %level% d'île pour débloquer le Nether.";
    public String endIslandsDisabled = "%prefix% &7Les îles de l'End ont été désactivées.";
    public String endLocked = "%prefix% &7Atteignez le niveau %level% d'île pour débloquer l'End.";
    public String islandBorderChanged = "%prefix% &7%player% a changé la bordure de votre île en %color%.";
    public String borderColorDisabled = "%prefix% &7Cette couleur de bordure a été désactivée.";
    public String notAColor = "%prefix% &7Ce n'est pas une couleur valide.";
    public String cannotManageBorder = "%prefix% &7Vous ne pouvez pas modifier la bordure de l'île.";
    public String regeneratingIsland = "%prefix% &7Régénération de l'île...";
    public String cannotRegenIsland = "%prefix% &7Vous ne pouvez pas régénérer votre île.";
    public String unknownSchematic = "%prefix% &7Aucun schéma avec ce nom n'existe.";
    public String paidForRegen = "%prefix% &7%player% a régénéré l'île (%schematic%&7) pour %vault_cost%.";
    public String noSafeLocation = "%prefix% &7Impossible de trouver un emplacement sûr pour la téléportation.";
    public String cannotHurtPlayers = "%prefix% &7Vous ne pouvez pas blesser des joueurs sur votre île.";
    public String creatingIsland = "%prefix% &7Création de l'île en cours, veuillez patienter...";
    public String noBiomeCategory = "%prefix% &7Aucune catégorie de biome avec ce nom.";
    public String noBiome = "%prefix% &7Aucun biome avec ce nom.";
    public String changedBiome = "%prefix% &7%player% a changé le biome de votre île en %biome%.";
    public String specifyData = "%prefix% &7En raison de la nature destructrice de cette commande, vous devez préciser les données à supprimer (ou indiquer \"all\").";
    public String confirmDataDeletion = "%prefix% &7Vous êtes sur le point de supprimer les données suivantes : %table% | [pour : %island%] dans 5 secondes. &cÊtes-vous sûr ? (LES DONNÉES SONT &4&lINRÉCUPÉRABLES&r&c)";
    public String dataDeletion = "%prefix% &cLes données ont été supprimées avec succès. &7Vous devrez peut-être redémarrer votre serveur pour que cela prenne effet.";
}
