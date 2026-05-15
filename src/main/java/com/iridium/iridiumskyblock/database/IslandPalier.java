package com.iridium.iridiumskyblock.database;

import com.iridium.iridiumteams.database.DatabaseObject;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@DatabaseTable(tableName = "island_paliers")
public class IslandPalier extends DatabaseObject {

    @DatabaseField(columnName = "id", generatedId = true, canBeNull = false, unique = true)
    private int id;

    @DatabaseField(columnName = "island_id", canBeNull = false)
    private int islandId;

    @DatabaseField(columnName = "palier_id", canBeNull = false)
    private int palierID;

    public IslandPalier(Island island, int palierID) {
        this.islandId = island.getId();
        this.palierID = palierID;
    }
}
