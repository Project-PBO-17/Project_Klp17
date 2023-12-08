package util;

public class TimeFormatter {
    public static String formatSeconds(int seconds) {
        int minutes = seconds / 60;
        seconds = seconds % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }
}
