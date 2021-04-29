package com.dolphin2point0.gamestagesfabric.itemmodule.api;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

public interface ItemStageChecker {
    void addStageToItem(Item item, String stage);
    void addStagesToItem(Item item, String[] stage);
    void removeStageFromItem(Item item, String stage);
    boolean itemUsableByPlayer(PlayerEntity p, Item i);
}
