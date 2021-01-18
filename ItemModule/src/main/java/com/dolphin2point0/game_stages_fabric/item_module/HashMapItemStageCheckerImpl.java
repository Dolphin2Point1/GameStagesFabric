package com.dolphin2point0.game_stages_fabric.item_module;

import com.dolphin2point0.game_stages_fabric.item_module.api.ItemStageChecker;
import com.dolphin2point0.game_stages_fabric.api.GameStagesAPI;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HashMapItemStageCheckerImpl implements ItemStageChecker {
    HashMap<Item, String[]> hashMap = new HashMap<>();

    @Override
    public void addStageToItem(Item item, String stage) {
        if(stage == null || item == null) {
            return;
        }
        if(hashMap.containsKey(item)) {
            String[] oldStageArray = hashMap.get(item);
            if(!Arrays.asList(oldStageArray).contains(stage)) {
                String[] newStageArray = new String[oldStageArray.length + 1];
                System.arraycopy(oldStageArray, 0, newStageArray, 0, oldStageArray.length);
                newStageArray[oldStageArray.length] = stage;
                hashMap.put(item, newStageArray);
            }
        } else {
            hashMap.put(item, new String[]{stage});
        }
    }

    @Override
    public void addStagesToItem(Item item, String[] stages) {
        for (String stage: stages) {
            addStageToItem(item, stage);
        }
    }

    @Override
    public void removeStageFromItem(Item item, String stage) {
        if(stage == null || item == null) {
            return;
        }
        if(hashMap.get(item).length > 1) {
            List<String> listArray = Arrays.asList(hashMap.get(item));
            if(listArray.contains(stage)) {
                listArray.remove(stage);
                hashMap.put(item, (String[]) listArray.toArray());
            }
        } else {
            hashMap.remove(item);
        }
    }

    @Override
    public boolean itemUsableByPlayer(PlayerEntity p, Item i) {
        if(hashMap.containsKey(i)) {
            for (String stage: hashMap.get(i)) {
                if(!GameStagesAPI.hasStage(p, stage)) {
                    return false;
                }
            }
        }
        return true;
    }
}
