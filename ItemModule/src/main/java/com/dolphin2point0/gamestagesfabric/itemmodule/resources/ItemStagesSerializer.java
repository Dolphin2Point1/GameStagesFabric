package com.dolphin2point0.gamestagesfabric.itemmodule.resources;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.Pair;

import java.util.ArrayList;

public class ItemStagesSerializer {
    public static Pair<Item, String[]> read(JsonObject jsonObject) {
        Item item = JsonHelper.getItem(jsonObject, "item");
        JsonArray stages = JsonHelper.getArray(jsonObject, "stages");
        ArrayList<String> list = new ArrayList<>();
        stages.forEach((jsonElement) -> {
            list.add(jsonElement.getAsJsonPrimitive().getAsString());
        });
        String[] strings = new String[list.size()];
        list.toArray(strings);
        return new Pair<>(item, strings);
    }
}
