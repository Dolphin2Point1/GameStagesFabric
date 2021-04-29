package com.dolphin2point0.gamestagesfabric.itemmodule;

import com.dolphin2point0.gamestagesfabric.itemmodule.api.ItemStageChecker;
import com.dolphin2point0.gamestagesfabric.itemmodule.resources.JsonItemStageLoader;
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
        LOGGER.warn("The datapack folder has been moved to gamestagesfabric/itemstages from item_stages/item_stage! Make" +
                " sure you have moved all your stage files, and updated them to the new format! The new format allows " +
                "arrays of items to be specified. Surround a list of the identifiers of the items you want to stage with" +
                " []");
    }
}
