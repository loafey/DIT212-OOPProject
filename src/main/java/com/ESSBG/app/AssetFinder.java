package com.ESSBG.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetFinder {
    private AssetFinder() {
    }

    public static void findAssets(AssetManager am, String assetPath) {
        HashMap<String, Class<Texture>> correctFolders = new HashMap<>();
        correctFolders.put("Textures", Texture.class);

        File[] files = new File(assetPath).listFiles();
        for (File x : files) {
            if (correctFolders.containsKey(x.getName())) {
                try {
                    Files.walk(x.toPath()).filter(Files::isRegularFile).forEach((p) -> {
                        am.load(p.toString(), correctFolders.get(x.getName()));
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }
        am.finishLoading();
    }
}
