package l0raxeo.arki.engine.audio;

import l0raxeo.arki.engine.assetFiles.FileLoader;

import javax.sound.sampled.*;
import java.io.IOException;

import static javax.sound.sampled.FloatControl.Type.MASTER_GAIN;

public class AudioClip {

    public AudioInputStream audioInputStream;
    public Clip clip;

    // Attributes

    public final String name;
    public final String path;
    /**
     * [-81, 7]
     */
    private float decibelAddends;

    public AudioClip(String referenceName, String path, float decibelAddends) {
        this.name = referenceName;
        this.path = path;
        this.decibelAddends = decibelAddends;

        createClip();
    }

    private void createClip() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(FileLoader.loadFile(path));

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            setVolume();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void setDecibelAddends(float decibelAddends)
    {
        this.decibelAddends = decibelAddends;
    }

    public void setVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(MASTER_GAIN);
        gainControl.setValue(decibelAddends);
    }

    public Clip getClip() {
        return clip;
    }

    public AudioInputStream getAudioInputStream() {
        return audioInputStream;
    }
}
