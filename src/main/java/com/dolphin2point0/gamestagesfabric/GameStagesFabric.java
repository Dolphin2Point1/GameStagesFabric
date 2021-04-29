package com.dolphin2point0.gamestagesfabric;

import com.dolphin2point0.gamestagesfabric.command.GameStagesBasicCommands;
import com.dolphin2point0.gamestagesfabric.event.SyncComponentOnJoin;
import nerdhub.cardinal.components.api.event.PlayerSyncCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameStagesFabric implements ModInitializer {
    public static Logger LOGGER = LogManager.getLogger("GameStagesFabric");

    @Override
    public void onInitialize() {
        LOGGER.log(Level.INFO, "Initializing commands...");
        CommandRegistrationCallback.EVENT.register(new GameStagesBasicCommands());
        PlayerSyncCallback.EVENT.register(new SyncComponentOnJoin());
    }
}
