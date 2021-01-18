package com.dolphin2point0.gamestagesfabric.api;

import com.dolphin2point0.gamestagesfabric.api.component.GameStagesComponent;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashSet;

/**
 * A convienient and fast way to access the GameStagesAPI
*/
public class GameStagesAPI {

    public static boolean addStage(PlayerEntity p, String stage) {
        return GameStagesComponent.KEY.get(p).addStage(stage);
    }

    public static boolean removeStage(PlayerEntity p, String stage) {
        return GameStagesComponent.KEY.get(p).removeStage(stage);
    }

    public static boolean hasStage(PlayerEntity p, String stage) {
        return GameStagesComponent.KEY.get(p).hasStage(stage);
    }

    public static HashSet<String> getStages(PlayerEntity p) {
        return GameStagesComponent.KEY.get(p).getStages();
    }
}
