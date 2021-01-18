package com.dolphin2point0.gamestagesfabric.api;

import com.dolphin2point0.gamestagesfabric.GameStagesFabric;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.SinglePreparationResourceReloadListener;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.profiler.Profiler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author apace100
 * Original from: https://github.com/apace100/origins-fabric/blob/master/src/main/java/io/github/apace100/origins/util/MultiJsonDataLoader.java
 */
public abstract class MultiJsonDataLoader extends SinglePreparationResourceReloadListener<Map<Identifier, List<JsonElement>>> {
    private static final int FILE_SUFFIX_LENGTH = ".json".length();
    private final Gson gson;
    private final String dataType;

    public MultiJsonDataLoader(Gson gson, String dataType) {
        this.gson = gson;
        this.dataType = dataType;
    }

    protected Map<Identifier, List<JsonElement>> prepare(ResourceManager resourceManager, Profiler profiler) {
        Map<Identifier, List<JsonElement>> map = Maps.newHashMap();
        int i = this.dataType.length() + 1;
        Iterator var5 = resourceManager.findResources(this.dataType, (stringx) -> {
            return stringx.endsWith(".json");
        }).iterator();
        Set<String> resourcesHandled = new HashSet<>();
        while(var5.hasNext()) {
            Identifier identifier = (Identifier) var5.next();
            String string = identifier.getPath();
            Identifier identifier2 = new Identifier(identifier.getNamespace(), string.substring(i, string.length() - FILE_SUFFIX_LENGTH));
            resourcesHandled.clear();
            try {
                resourceManager.getAllResources(identifier).forEach(resource -> {
                    if(!resourcesHandled.contains(resource.getResourcePackName())) {
                        resourcesHandled.add(resource.getResourcePackName());
                        try {
                            Throwable var10 = null;
                            try {
                                InputStream inputStream = resource.getInputStream();
                                Throwable var12 = null;

                                try {
                                    Reader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                                    Throwable var14 = null;

                                    try {
                                        JsonElement jsonElement = JsonHelper.deserialize(this.gson, reader, JsonElement.class);
                                        if (jsonElement != null) {
                                            if(map.containsKey(identifier2)) {
                                                map.get(identifier2).add(jsonElement);
                                            } else {
                                                List<JsonElement> elementList = new LinkedList<>();
                                                elementList.add(jsonElement);
                                                map.put(identifier2, elementList);
                                            }
                                        } else {
                                            GameStagesFabric.LOGGER.error("Couldn't load data file {} from {} as it's null or empty", identifier2, identifier);
                                        }
                                    } catch (Throwable var62) {
                                        var14 = var62;
                                        throw var62;
                                    } finally {
                                        if (reader != null) {
                                            if (var14 != null) {
                                                try {
                                                    reader.close();
                                                } catch (Throwable var61) {
                                                    var14.addSuppressed(var61);
                                                }
                                            } else {
                                                reader.close();
                                            }
                                        }

                                    }
                                } catch (Throwable var64) {
                                    var12 = var64;
                                    throw var64;
                                } finally {
                                    if (inputStream != null) {
                                        if (var12 != null) {
                                            try {
                                                inputStream.close();
                                            } catch (Throwable var60) {
                                                var12.addSuppressed(var60);
                                            }
                                        } else {
                                            inputStream.close();
                                        }
                                    }

                                }
                            } catch (Throwable var66) {
                                var10 = var66;
                                throw var66;
                            } finally {
                                if (resource != null) {
                                    if (var10 != null) {
                                        try {
                                            resource.close();
                                        } catch (Throwable var59) {
                                            var10.addSuppressed(var59);
                                        }
                                    } else {
                                        resource.close();
                                    }
                                }

                            }
                        } catch (IllegalArgumentException | IOException | JsonParseException var68) {
                            GameStagesFabric.LOGGER.error("Couldn't parse data file {} from {}", identifier2, identifier, var68);
                        }
                    }
                });
            } catch (IOException e) {
                GameStagesFabric.LOGGER.error("Couldn't parse data file {} from {}", identifier2, identifier, e);
            }
        }

        return map;
    }
}
