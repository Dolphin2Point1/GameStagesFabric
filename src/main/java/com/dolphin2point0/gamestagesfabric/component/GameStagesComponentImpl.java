package com.dolphin2point0.gamestagesfabric.component;

import com.dolphin2point0.gamestagesfabric.api.component.GameStagesComponent;
import com.google.gson.Gson;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashSet;

public class GameStagesComponentImpl implements GameStagesComponent {
    private HashSet<String> stages = new HashSet<>();
    private final PlayerEntity provider;
    private static final Gson GSON = new Gson();

    public GameStagesComponentImpl(PlayerEntity provider) {
        this.provider = provider;
    }

    @Override
    public boolean hasStage(String stage) {
        return stages.contains(stage);
    }

    @Override
    public boolean addStage(String stage) {
        boolean wasSuccessful = stages.add(stage);
        GameStagesComponent.KEY.sync(this.provider);
        return wasSuccessful;
    }

    @Override
    public boolean removeStage(String stage) {
        boolean wasSuccessful = stages.remove(stage);
        GameStagesComponent.KEY.sync(this.provider);
        return wasSuccessful;
    }

    public HashSet<String> getStages() {
        return stages;
    }

    @Override
    public PlayerEntity getProvider() {
        return provider;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readFromNbt(CompoundTag compoundTag) {
        String stagesJson = compoundTag.getString("gameStages");
        stages = GSON.fromJson(stagesJson, stages.getClass());
    }

    @Override
    public void writeToNbt(CompoundTag compoundTag) {
        compoundTag.putString("gameStages", GSON.toJson(stages));
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return player == this.provider;
    }

    /*@Override
    public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
        buf.setInt(0, 2);
        buf.writeString(GSON.toJson(stages));
    }

    /**
     * @param operation the operation that is going to be used.
     *                  0 = add stage
     *                  1 = remove stage
     *                  2 = all stages
     */
    /*
    public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity player, int operation, String stage) {
        if(operation == 2) {
            writeSyncPacket(buf, player);
            return;
        }
        buf.writeVarInt(operation);
        buf.writeString(stage);
    }

    @SuppressWarnings("unchecked")
    public void applySyncPacket(PacketByteBuf buf) {
        GameStagesFabric.LOGGER.info("Applying syncronization packet: " + buf.readString());
        switch(buf.readVarInt()) {
            case 0:
                stages.add(buf.readString());
            case 1:
                stages.remove(buf.readString());
            case 2:
                stages = GSON.fromJson(buf.readString(), stages.getClass());
        }
    }
    */

}
