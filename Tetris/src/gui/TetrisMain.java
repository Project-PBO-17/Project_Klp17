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

    // Add these methods for looping and stopping
    public static void loopBackground() {
        audioPlayer.loopBackground();
    }

    public static void stopBackground() {
        audioPlayer.stopBackground();
    }

    public static void loopGameover() {
        audioPlayer.loopGameover();
    }

    public static void stopGameover() {
        audioPlayer.stopGameover();
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
    public static void setClearLineVolume(float volume) {
        audioPlayer.setClearLineVolume(volume);
    }

    public static void setGameoverVolume(float volume) {
        audioPlayer.setGameoverVolume(volume);
    }

    public static void setOpeningVolume(float volume) {
        audioPlayer.setOpeningVolume(volume);
    }

    public static void setBackgroundVolume(float volume) {
        audioPlayer.setBackgroundVolume(volume);
    }

    public static void setPauseVolume(float volume) {
        audioPlayer.setPauseVolume(volume);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartForm());
    }
}
