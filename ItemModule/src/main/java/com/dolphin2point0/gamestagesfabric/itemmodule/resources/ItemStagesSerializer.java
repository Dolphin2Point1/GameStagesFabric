package com.dolphin2point0.gamestagesfabric.itemmodule.resources;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;

public class ItemStagesSerializer {
    public static Pair<Item[], String[]> read(JsonObject jsonObject) {
        LinkedList<Item> items = new LinkedList<>();
        for (JsonElement jsonElement : JsonHelper.getArray(jsonObject, "items")) {
            Item item = JsonHelper.asItem(jsonElement, jsonElement.getAsString());
            items.add(item);
        }

        ArrayList<String> stages = new ArrayList<>();
        for (JsonElement element : JsonHelper.getArray(jsonObject, "stages")) {
            stages.add(element.getAsString());
        }
        return new Pair<>(items.toArray(new Item[0]), stages.toArray(new String[0]));
    }
}
