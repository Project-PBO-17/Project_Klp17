package util;

public class Calculate {
    public static int WPM(int correctWord, int time) {
        if (time == 0) time = 1;
        return (int) Math.round((double) correctWord / 5 / ((double) time / 60));
    }
}
