package Library.Audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    public AudioPlayer() {
    }

    public void play(File file) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void play(Sound sound) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(sound.getFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}