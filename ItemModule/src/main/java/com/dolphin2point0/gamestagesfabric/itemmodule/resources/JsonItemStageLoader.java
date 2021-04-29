package com.dolphin2point0.gamestagesfabric.itemmodule.resources;

import com.dolphin2point0.gamestagesfabric.itemmodule.ItemModule;
import com.dolphin2point0.gamestagesfabric.itemmodule.HashMapItemStageCheckerImpl;
import com.dolphin2point0.gamestagesfabric.api.resources.MultiJsonDataLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.item.Item;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.Pair;
import net.minecraft.util.profiler.Profiler;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JsonItemStageLoader extends MultiJsonDataLoader implements IdentifiableResourceReloadListener {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private static final Identifier id = new Identifier("gamestagesfabric", "itemstages");

    public JsonItemStageLoader() {
        super(GSON, "itemstages");
    }

    @Override
    protected void apply(Map<Identifier, List<JsonElement>> loader, ResourceManager manager, Profiler profiler) {
        ItemModule.itemStageChecker = new HashMapItemStageCheckerImpl();
        for (Map.Entry<Identifier, List<JsonElement>> entry : loader.entrySet()) {
            for(JsonElement element : entry.getValue()) {
                Pair<Item[], String[]> deserializedItemStages = ItemStagesSerializer.read(JsonHelper.asObject(element, "top element"));
                for (Item item : deserializedItemStages.getLeft()) {
                    ItemModule.itemStageChecker.addStagesToItem(item, deserializedItemStages.getRight());
                    ItemModule.LOGGER.info("Added stages " + Arrays.toString(deserializedItemStages.getRight())
                            + " to item " + item.getTranslationKey());
                }
            }
        }
        ItemModule.LOGGER.info("Added stages to items.");
    }

    @Override
    public Identifier getFabricId() {
        return id;
    }
}
