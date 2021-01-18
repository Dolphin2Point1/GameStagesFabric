package com.dolphin2point0.game_stages_fabric.component;

import com.dolphin2point0.game_stages_fabric.GameStagesFabric;
import com.dolphin2point0.game_stages_fabric.api.component.GameStagesComponent;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import org.apache.logging.log4j.Level;

public class GameStagesFabricComponents implements EntityComponentInitializer {
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        GameStagesFabric.LOGGER.log(Level.INFO, "Initializing gamestage component...");
        registry.registerForPlayers(GameStagesComponent.KEY, GameStagesComponentImpl::new, RespawnCopyStrategy.ALWAYS_COPY);
    }
}
