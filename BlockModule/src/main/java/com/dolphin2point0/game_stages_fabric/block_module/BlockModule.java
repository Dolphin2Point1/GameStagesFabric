package com.dolphin2point0.game_stages_fabric.block_module;

import com.dolphin2point0.game_stages_fabric.block_module.api.BlockStageChecker;
import net.fabricmc.api.ModInitializer;

public class BlockModule implements ModInitializer {
    public static BlockStageChecker BLOCK_STAGE_CHECKER;

    @Override
    public void onInitialize() {
        BLOCK_STAGE_CHECKER = new HashMapBlockStageChecker();
    }
}
