package com.dolphin2point0.gamestagesfabric.event;

import com.dolphin2point0.gamestagesfabric.api.component.GameStagesComponent;
import nerdhub.cardinal.components.api.event.PlayerSyncCallback;
import net.minecraft.server.network.ServerPlayerEntity;

public class SyncComponentOnJoin implements PlayerSyncCallback {

    @Override
    public void onPlayerSync(ServerPlayerEntity serverPlayerEntity) {
        GameStagesComponent.KEY.sync(serverPlayerEntity);
    }
}
