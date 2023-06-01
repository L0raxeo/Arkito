package l0raxeo.arki.engine.audio;

import l0raxeo.arki.engine.assetFiles.FileLoader;

import javax.sound.sampled.*;
import java.io.IOException;

public class AudioClip
{

    public AudioInputStream audioInputStream;
    public Clip clip;

    // Attributes

    public final String name;
    public final String path;
    public final float volume;

    // class

    /**
     * @param name the reference name of the audio clip
     * @param path the path of the raw wave file (not a resource)
     * @param volume of the wave file at which it will be played at
     */
    public AudioClip(String name, String path, float volume)
    {
        this.name = name;
        this.path = path;
        this.volume = volume;

        createClip();
    }

    /**
     * Create the clip by initializing the audio input stream,
     * and then using it to initialize and open the clip;
     */
    private void createClip()
    {
        try
        {
            audioInputStream = AudioSystem.getAudioInputStream(FileLoader.loadFile(path));

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            e.printStackTrace();
        }
    }

    // Getters

    public Clip getClip()
    {
        return clip;
    }

    public AudioInputStream getAudioInputStream()
    {
        return audioInputStream;
    }

}
