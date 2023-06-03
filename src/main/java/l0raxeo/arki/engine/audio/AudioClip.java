package l0raxeo.arki.engine.audio;

import l0raxeo.arki.engine.assetFiles.FileLoader;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

import static javax.sound.sampled.FloatControl.Type.MASTER_GAIN;

public class AudioClip {

    public AudioInputStream audioInputStream;
    public Clip clip;

    // Attributes

    public final String name;
    public final String path;
    public final float volume;

    public AudioClip(String referenceName, String filePath, float decidableAddends) {
        this.name = referenceName;
        this.path = filePath;
        this.volume = decidableAddends;

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

    private void setVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(MASTER_GAIN);
        gainControl.setValue(volume);
    }

    public Clip getClip() {
        return clip;
    }

    public AudioInputStream getAudioInputStream() {
        return audioInputStream;
    }
}
