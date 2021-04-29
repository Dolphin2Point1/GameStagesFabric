package com.dolphin2point0.gamestagesfabric.api.component;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.util.HashSet;

public interface GameStagesComponent extends AutoSyncedComponent, Component {
    ComponentKey<GameStagesComponent> KEY = ComponentRegistry.getOrCreate(new Identifier("game-stages-fabric", "gamestages"), GameStagesComponent.class);

    PlayerEntity getProvider();
    boolean hasStage(String stage);
    boolean addStage(String stage);
    boolean removeStage(String stage);
    HashSet<String> getStages();
}
