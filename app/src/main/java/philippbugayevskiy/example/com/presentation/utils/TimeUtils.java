package philippbugayevskiy.example.com.presentation.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static philippbugayevskiy.example.com.data.datasource.Constants.DATA_PATTERN_DAY;
import static philippbugayevskiy.example.com.data.datasource.Constants.DATA_PATTERN_TIME;

public class TimeUtils {

    public static int normalizePercentageValues(long currentTimeSeconds, long starts, long ends) {
        currentTimeSeconds = currentTimeSeconds - starts;
        ends = ends - starts;
        double percent = (double) currentTimeSeconds / ends;
        double calculatedPercent = percent * 100;

        return (int) calculatedPercent;
    }

    public static String getFormattedDate(long startTime, long endTime) {
        String to = new SimpleDateFormat(DATA_PATTERN_TIME, Locale.getDefault()).format(endTime);
        String from = new SimpleDateFormat(DATA_PATTERN_TIME, Locale.getDefault()).format(startTime);

        return from + " - " + to;
    }

    public static String getDatePeriodInStringFormat() {
        Calendar cal = Calendar.getInstance();
        String from = new SimpleDateFormat(DATA_PATTERN_DAY, Locale.getDefault()).format(cal.getTime());

        cal.add(Calendar.DATE, +1);
        String to = new SimpleDateFormat(DATA_PATTERN_DAY, Locale.getDefault()).format(cal.getTime());

        return from + "~" + to;
    }

    public static long getCurrentTime() {
        Calendar cal = Calendar.getInstance();

        return cal.getTimeInMillis();
    }

    public static boolean isEpgNow(long currentTimeInMills, long startTime, long endTime) {
        return startTime <= currentTimeInMills && currentTimeInMills <= endTime;
    }

    public static String convertTimeInMins(double runtime) {
        return String.valueOf(Math.round(runtime / 60));
    }
}
