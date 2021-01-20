package com.dolphin2point0.game_stages_fabric.block_module.api;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

public interface BlockStageChecker {
    void addStageToItem(Block block, String stage);
    void addStagesToItem(Block block, String[] stage);
    void removeStageFromItem(Block block, String stage);
    boolean itemUsableByPlayer(Block block, PlayerEntity p);
}
