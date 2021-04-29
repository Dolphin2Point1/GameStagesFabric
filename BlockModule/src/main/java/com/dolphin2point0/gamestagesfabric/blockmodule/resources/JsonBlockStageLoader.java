package com.dolphin2point0.gamestagesfabric.blockmodule.resources;

import com.dolphin2point0.gamestagesfabric.GameStagesFabric;
import com.dolphin2point0.gamestagesfabric.api.resources.MultiJsonDataLoader;
import com.dolphin2point0.gamestagesfabric.blockmodule.BlockModule;
import com.dolphin2point0.gamestagesfabric.blockmodule.HashMapBlockStageChecker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.block.Block;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.Pair;
import net.minecraft.util.profiler.Profiler;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JsonBlockStageLoader extends MultiJsonDataLoader implements IdentifiableResourceReloadListener {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private static final Identifier id = new Identifier("game_stages_fabric", "block_stages");

    public JsonBlockStageLoader() {
        super(GSON, "block_stages");
    }

    @Override
    protected void apply(Map<Identifier, List<JsonElement>> loader, ResourceManager manager, Profiler profiler) {
        BlockModule.BLOCK_STAGE_CHECKER = new HashMapBlockStageChecker();
        for (Map.Entry<Identifier, List<JsonElement>> entry : loader.entrySet()) {
            for(JsonElement element : entry.getValue()) {
                Pair<Block, String[]> deserializedItemStages = BlockStagesSerializer.read(JsonHelper.asObject(element, "top element"));
                BlockModule.BLOCK_STAGE_CHECKER.addStagesToBlock(deserializedItemStages.getLeft(), deserializedItemStages.getRight());
                GameStagesFabric.LOGGER.info("Added stages " + Arrays.toString(deserializedItemStages.getRight())
                        + " to block " + deserializedItemStages.getLeft().getTranslationKey());
            }
        }
        GameStagesFabric.LOGGER.info("Added stages to blocks.");
    }

    @Override
    public Identifier getFabricId() {
        return id;
    }
}
