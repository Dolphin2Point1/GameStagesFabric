package com.dolphin2point0.game_stages_fabric.block_module;

import com.dolphin2point0.game_stages_fabric.block_module.api.BlockStageChecker;
import com.dolphin2point0.game_stages_fabric.block_module.resources.JsonBlockStageLoader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

public class BlockModule implements ModInitializer {
    public static BlockStageChecker BLOCK_STAGE_CHECKER;


    @Override
    public void onInitialize() {
        BLOCK_STAGE_CHECKER = new HashMapBlockStageChecker();
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new JsonBlockStageLoader());
    }
}
