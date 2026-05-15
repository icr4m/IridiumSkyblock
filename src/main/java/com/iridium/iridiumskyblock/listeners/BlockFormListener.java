package com.iridium.iridiumskyblock.listeners;

import com.cryptomorin.xseries.XMaterial;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.RandomAccessList;
import com.iridium.iridiumskyblock.enhancements.GeneratorEnhancementData;
import dev.lone.itemsadder.api.CustomBlock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;

import java.util.*;

public class BlockFormListener implements Listener {

    private final Map<Integer, RandomAccessList<String>> normalOreLevels = new HashMap<>();
    private final Map<Integer, RandomAccessList<String>> netherOreLevels = new HashMap<>();

    private final List<XMaterial> generatorMaterials = Arrays.asList(XMaterial.STONE, XMaterial.COBBLESTONE, XMaterial.BASALT);
    private final boolean itemsAdderEnabled;

    public BlockFormListener() {
        this.itemsAdderEnabled = Bukkit.getPluginManager().getPlugin("ItemsAdder") != null;
        for (Map.Entry<Integer, GeneratorEnhancementData> oreUpgrade : IridiumSkyblock.getInstance().getEnhancements().generatorEnhancement.levels.entrySet()) {
            normalOreLevels.put(oreUpgrade.getKey(), new RandomAccessList<>(oreUpgrade.getValue().ores));
            netherOreLevels.put(oreUpgrade.getKey(), new RandomAccessList<>(oreUpgrade.getValue().netherOres));
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockForm(BlockFormEvent event) {
        XMaterial newMaterial = XMaterial.matchXMaterial(event.getNewState().getType());
        if (!generatorMaterials.contains(newMaterial)) return;
        IridiumSkyblock.getInstance().getIslandManager().getTeamViaLocation(event.getNewState().getLocation()).ifPresent(island -> {
            int upgradeLevel = IridiumSkyblock.getInstance().getIslandManager().getTeamEnhancement(island, "generator").getLevel();
            boolean isBasaltGenerator = newMaterial == XMaterial.BASALT;
            RandomAccessList<String> randomMaterialList = isBasaltGenerator ? netherOreLevels.get(upgradeLevel) : normalOreLevels.get(upgradeLevel);
            if (randomMaterialList == null) return;

            Optional<String> materialKeyOpt = randomMaterialList.nextElement();
            if (!materialKeyOpt.isPresent()) return;

            if (isBasaltGenerator && IridiumSkyblock.getInstance().getConfiguration().netherOnlyGenerator && event.getNewState().getWorld().getEnvironment() != World.Environment.NETHER) return;

            String materialKey = materialKeyOpt.get();
            if (itemsAdderEnabled && materialKey.contains(":")) {
                // ItemsAdder custom block: place on the next tick so the block position exists
                Location location = event.getNewState().getLocation();
                Bukkit.getScheduler().runTask(IridiumSkyblock.getInstance(), () -> CustomBlock.place(materialKey, location));
            } else {
                XMaterial.matchXMaterial(materialKey).ifPresent(xMaterial -> {
                    Material material = xMaterial.parseMaterial();
                    if (material == Material.COBBLESTONE && newMaterial == XMaterial.STONE) material = Material.STONE;
                    if (material != null) event.getNewState().setType(material);
                });
            }
        });
    }
}