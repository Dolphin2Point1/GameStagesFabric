package com.dolphin2point0.game_stages_fabric.item_module;

import com.dolphin2point0.game_stages_fabric.item_module.api.ItemStageChecker;
import com.dolphin2point0.game_stages_fabric.item_module.resources.JsonItemStageLoader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemModule implements ModInitializer {
    public static ItemStageChecker itemStageChecker;
    public static Logger LOGGER = LogManager.getLogger("GSF: Item Module");

    @Override
    public void onInitialize() {
        LOGGER.info("Initialize item hashmap and resource listeners.");
        itemStageChecker = new HashMapItemStageCheckerImpl();
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new JsonItemStageLoader());
        LOGGER.warn("The datapack folder has been moved to game_stages_fabric/item_stage from item_stages/item_stage! Make sure you have moved all your stage files!");
    }
}
