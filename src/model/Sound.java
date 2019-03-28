package model;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Sound extends JFrame {

    private String status;

    public Sound(String status) {
        this.status = status;
        addSound();
    }

    private void addSound() {
        try {
            File soundFile = callFile();
            Clip clip = AudioSystem.getClip();

            if (soundFile != null) {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                clip.open(audioIn);
                clip.start();
            } else {
                clip = AudioSystem.getClip();
                clip.stop();
            }

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private File callFile() {
        File victorySound = new File("src/resources/victory.wav");
        File defeatSound = new File("src/resources/defeat.wav");

        switch (status) {
            case "victory":
                return victorySound;
            case "defeat":
                return defeatSound;
            default:
                return null;
        }
    }
}
