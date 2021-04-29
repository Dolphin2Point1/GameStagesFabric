package com.dolphin2point0.gamestagesfabric.blockmodule.resources;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

public class BlockStagesSerializer {
    public static Pair<Block, String[]> read(JsonObject jsonObject) {
        Block block = Registry.BLOCK.getOrEmpty(
                new Identifier(
                        jsonObject.get("block").getAsString()
                )
        ).orElseThrow(
                () -> new JsonSyntaxException("Invalid block: " + jsonObject.get("block").getAsString())
        );
        JsonArray stages = JsonHelper.getArray(jsonObject, "stages");
        ArrayList<String> list = new ArrayList<>();
        stages.forEach((jsonElement) -> {
            list.add(jsonElement.getAsJsonPrimitive().getAsString());
        });
        String[] strings = new String[list.size()];
        list.toArray(strings);
        return new Pair<>(block, strings);
    }
}
