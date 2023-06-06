package l0raxeo.arki.engine.assetFiles;

import l0raxeo.arki.engine.audio.AudioClip;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class AssetPool
{

    // path, resource
    private static final Map<String, AudioClip> loadedAudioClips = new HashMap<>();

    private static final Map<String, Asset<Font>> loadedFonts = new HashMap<>();
    private static final Map<String, Asset<BufferedImage>> loadedBufferedImages = new HashMap<>();

    public static BufferedImage getBufferedImage(String referenceName)
    {
        if (loadedBufferedImages.containsKey(referenceName))
            return loadedBufferedImages.get(referenceName).getResource();

        return null;
    }

    public static void indexBufferedImage(String referenceName, String path)
    {
        BufferedImage img;

        if (!loadedBufferedImages.containsKey(referenceName))
        {
            img = FileLoader.loadImage(path);
            loadedBufferedImages.put(referenceName, new Asset<>(path, img));
        }

    }

    public static AudioClip getAudioClip(String referenceName)
    {
        if (loadedAudioClips.containsKey(referenceName))
            return loadedAudioClips.get(referenceName);

        return null;
    }

    public static void indexAudioClip(String referenceName, String path, float decibelAddends)
    {
        if (!loadedAudioClips.containsKey(referenceName))
        {
            AudioClip audioClip = new AudioClip(referenceName, path, decibelAddends);
            loadedAudioClips.put(referenceName, audioClip);
        }
    }

    public static Font getFont(String referenceName)
    {
        if (loadedFonts.containsKey(referenceName))
            return loadedFonts.get(referenceName).getResource();

        return null;
    }

    public static void indexFont(String referenceName, String path, int size)
    {
        boolean hasReference = loadedFonts.containsKey(referenceName);
        boolean differentSize = hasReference && loadedFonts.get(referenceName).getResource().getSize() != size;
        if (!hasReference || differentSize)
        {
            Font font = FileLoader.loadFont(path, size);
            loadedFonts.put(referenceName, new Asset<>(path, font));
        }
    }

    public static void unloadAllBufferedImages()
    {
        loadedBufferedImages.clear();
    }

    public static void unloadAllFonts()
    {
        loadedFonts.clear();
    }

    public static void unloadAllAudioClips()
    {
        loadedAudioClips.clear();
    }

}
