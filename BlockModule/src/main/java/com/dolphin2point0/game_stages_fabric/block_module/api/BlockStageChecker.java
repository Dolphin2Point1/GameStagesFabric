package com.dolphin2point0.game_stages_fabric.block_module.api;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;

public interface BlockStageChecker {
    void addStageToBlock(Block block, String stage);
    void addStagesToBlock(Block block, String[] stage);
    void removeStageFromBlock(Block block, String stage);
    boolean blockUsableByPlayer(Block block, PlayerEntity p);
}
