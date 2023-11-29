package com.assembble.gc.util;

public class TimeConverter {
    private long time;

    public TimeConverter(long time, String unit) {
        this.time = time;
    }

    public static String toTimeString(long millis) {
        long seconds = millis / 1000;
        String timeString = "";


        if (seconds > 0) {
            timeString += seconds;
        }

        return timeString;
    }
}
