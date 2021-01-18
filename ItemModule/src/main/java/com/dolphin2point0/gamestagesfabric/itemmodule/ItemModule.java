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
        itemStageChecker = new HashMapItemStageCheckerImpl();
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new JsonItemStageLoader());
    }
}
