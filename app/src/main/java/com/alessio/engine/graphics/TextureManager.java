package com.alessio.engine.graphics;

import java.util.HashMap;

public class TextureManager {
    private static HashMap<String, Texture> textures;

    public static void init() {
        textures = new HashMap<>();
    }

    public static Texture add(Texture texture, String name) {
        textures.put(name, texture);
        return texture;
    }

    public static Texture get(String name) {
        return textures.get(name);
    }

    public static void remove(String name) {
        textures.remove(name);
    }

    public static void clear() {
        textures.clear();
    }
}
