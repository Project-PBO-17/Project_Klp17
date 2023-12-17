package gui;

import javax.swing.SwingUtilities;

import util.AudioPlayer;

public class TetrisMain {
    private static AudioPlayer audioPlayer = new AudioPlayer();

    public static void playClear() {
        audioPlayer.playClearLine();
    }

    public static void playGameover() {
        audioPlayer.playGameover();
    }

    public static void playClearLine() {
        audioPlayer.playClearLine();
    }

    public static void playFall() {
        audioPlayer.playFall();
    }

    public static void playMove() {
        audioPlayer.playMove();
    }

    public static void playRotate() {
        audioPlayer.playRotate();
    }

    public static void loopBackground() {
        audioPlayer.loopBackground();
    }

    public static void stopBackground() {
        audioPlayer.stopBackground();
    }

    public static void loopOpening() {
        audioPlayer.loopOpening();
    }

    public static void stopOpening() {
        audioPlayer.stopOpening();
    }

    public static void loopPause() {
        audioPlayer.loopPause();
    }

    public static void stopPause() {
        audioPlayer.stopPause();
    }

    public static void setBackgroundVolume(float volume) {
        audioPlayer.setBackgroundVolume(volume);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartForm());
    }
}
