package com.dolphin2point0.game_stages_fabric.block_module;

import com.dolphin2point0.game_stages_fabric.api.GameStagesAPI;
import com.dolphin2point0.game_stages_fabric.block_module.api.BlockStageChecker;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HashMapBlockStageChecker implements BlockStageChecker {
    HashMap<Block, String[]> hashMap = new HashMap<>();

    @Override
    public void addStageToItem(Block block, String stage) {
        if(stage == null || block == null) {
            return;
        }
        if(hashMap.containsKey(block)) {
            String[] oldStageArray = hashMap.get(block);
            if(!Arrays.asList(oldStageArray).contains(stage)) {
                String[] newStageArray = new String[oldStageArray.length + 1];
                System.arraycopy(oldStageArray, 0, newStageArray, 0, oldStageArray.length);
                newStageArray[oldStageArray.length] = stage;
                hashMap.put(block, newStageArray);
            }
        } else {
            hashMap.put(block, new String[]{stage});
        }
    }

    @Override
    public void addStagesToItem(Block block, String[] stages) {
        for (String stage: stages) {
            addStageToItem(block, stage);
        }
    }

    @Override
    public void removeStageFromItem(Block block, String stage) {
        if(stage == null || block == null) {
            return;
        }
        if(hashMap.get(block).length > 1) {
            List<String> listArray = Arrays.asList(hashMap.get(block));
            if(listArray.contains(stage)) {
                listArray.remove(stage);
                hashMap.put(block, (String[]) listArray.toArray());
            }
        } else {
            hashMap.remove(block);
        }
    }

    @Override
    public boolean itemUsableByPlayer(Block block, PlayerEntity player) {
        if(hashMap.containsKey(block)) {
            for (String stage: hashMap.get(block)) {
                if(!GameStagesAPI.hasStage(player, stage)) {
                    return false;
                }
            }
        }
        return true;
    }
}
