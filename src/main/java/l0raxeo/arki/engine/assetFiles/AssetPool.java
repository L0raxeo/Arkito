package l0raxeo.arki.engine.assetFiles;

import l0raxeo.arki.engine.audio.AudioClip;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class AssetPool
{

    //          resource, File
    private static final Map<String, Font> loadedFonts = new HashMap<>();
    private static final Map<String, BufferedImage> loadedBufferedImages = new HashMap<>();
    private static final Map<String, AudioClip> loadedAudioClips = new HashMap<>();

    public static BufferedImage getBufferedImage(String resource)
    {
        BufferedImage img;

        if (!loadedFonts.containsKey(resource))
        {
            img = FileLoader.loadImage(resource);
            loadedBufferedImages.put(resource, img);
        }
        else
            img = loadedBufferedImages.get(resource);

        return img;
    }

    public static AudioClip getAudioClip(String name, String resource)
    {
        AudioClip audioClip;

        if (!loadedAudioClips.containsKey(resource))
        {
            audioClip = new AudioClip(name, resource, 1);
            loadedAudioClips.put(resource, audioClip);
        }
        else
            audioClip = loadedAudioClips.get(resource);

        return audioClip;
    }

    public static Font getFont(String resource, int size)
    {
        Font font = null;

        if (!loadedFonts.containsKey(resource))
        {
            font = FileLoader.loadFont(resource, size);
            loadedFonts.put(resource, font);
        }
        else
        {
            if (loadedFonts.get(resource).getSize() != size)
            {
                font = FileLoader.loadFont(resource, size);
                loadedFonts.put(resource, font);
            }

            for (Map.Entry<String, Font> fontSet : loadedFonts.entrySet())
            {
                if (fontSet.getKey().equals(resource) && fontSet.getValue().getSize() == size)
                    return fontSet.getValue();
            }
        }

        return font;
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
