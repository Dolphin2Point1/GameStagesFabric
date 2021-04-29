package com.dolphin2point0.gamestagesfabric.blockmodule.resources;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.LinkedList;

public class BlockStagesSerializer {
    public static Pair<Block[], String[]> read(JsonObject jsonObject) {
        LinkedList<Block> blocks = new LinkedList<>();
        for (JsonElement element : JsonHelper.getArray(jsonObject, "blocks")) {
            Block block = Registry.BLOCK.getOrEmpty(new Identifier(element.getAsString())).orElseThrow(() ->
                    new JsonSyntaxException("Invalid block: " + element.getAsString()));
            blocks.add(block);
        }

        ArrayList<String> stages = new ArrayList<>();
        for (JsonElement element : JsonHelper.getArray(jsonObject, "stages")) {
            stages.add(element.getAsString());
        }

        return new Pair<>(blocks.toArray(new Block[0]), stages.toArray(new String[0]));
    }
}
