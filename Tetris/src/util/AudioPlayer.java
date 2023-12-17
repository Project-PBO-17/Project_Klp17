package util;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
    private String soundsFolder = "E:\\IT\\Code\\Project_Klp17\\Tetris\\src\\tetrisSounds\\" + File.separator;
    private String clearLinePath = soundsFolder + "clear.wav";
    private String gameOverPath = soundsFolder + "18. Game Over.wav";
    private String backgroundPath = soundsFolder + "Tetris.wav";
    private String pausePath = soundsFolder + "pause game.wav";
    private String openingPath = soundsFolder + "8bittownthemesong-59266.wav";
    private String fallPath = soundsFolder + "fall.wav";
    private String rotatePath = soundsFolder + "Tetris_turn_Effect.wav";
    private String movePath = soundsFolder + "Tetris_moving.wav";

    private Clip clearSound, gameoverSound, backgroundSound, pauseSound, openingSound,
            fallSound, rotateSound, moveSound;

    public AudioPlayer() {
        try {
            clearSound = AudioSystem.getClip();
            gameoverSound = AudioSystem.getClip();
            pauseSound = AudioSystem.getClip();
            backgroundSound = AudioSystem.getClip();
            openingSound = AudioSystem.getClip();
            fallSound = AudioSystem.getClip();
            rotateSound = AudioSystem.getClip();
            moveSound = AudioSystem.getClip();

            clearSound.open(AudioSystem.getAudioInputStream(new File(clearLinePath).getAbsoluteFile()));
            backgroundSound.open(AudioSystem.getAudioInputStream(new File(backgroundPath).getAbsoluteFile()));
            gameoverSound.open(AudioSystem.getAudioInputStream(new File(gameOverPath).getAbsoluteFile()));
            pauseSound.open(AudioSystem.getAudioInputStream(new File(pausePath).getAbsoluteFile()));
            openingSound.open(AudioSystem.getAudioInputStream(new File(openingPath).getAbsoluteFile()));
            fallSound.open(AudioSystem.getAudioInputStream(new File(fallPath).getAbsoluteFile()));
            rotateSound.open(AudioSystem.getAudioInputStream(new File(rotatePath).getAbsoluteFile()));
            moveSound.open(AudioSystem.getAudioInputStream(new File(movePath).getAbsoluteFile()));

        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void setVolume(Clip clip, float volume) {
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
        }
    }

    public void setBackgroundVolume(float volume) {
        setVolume(backgroundSound, volume);
    }

    public void playClearLine() {
        clearSound.setFramePosition(0);
        clearSound.start();
    }

    public void playFall() {
        fallSound.setFramePosition(0);
        fallSound.start();
    }

    public void playMove() {
        moveSound.setFramePosition(0);
        moveSound.start();
    }

    public void playRotate() {
        rotateSound.setFramePosition(0);
        rotateSound.start();
    }

    public void playGameover() {
        gameoverSound.setFramePosition(0);
        gameoverSound.start();
    }

    public void loopOpening() {
        openingSound.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopOpening() {
        openingSound.stop();
        openingSound.setFramePosition(0);
    }

    public void loopBackground() {
        backgroundSound.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopBackground() {
        backgroundSound.stop();
        backgroundSound.setFramePosition(0);
    }

    public void loopPause() {
        pauseSound.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopPause() {
        pauseSound.stop();
        pauseSound.setFramePosition(0);
    }
}
