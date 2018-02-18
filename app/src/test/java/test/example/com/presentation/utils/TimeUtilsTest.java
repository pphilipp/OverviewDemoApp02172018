package test.example.com.presentation.utils;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import test.example.com.presentation.utils.TimeUtils;

import static test.example.com.data.datasource.Constants.DATA_PATTERN_DAY;
import static test.example.com.presentation.utils.TimeUtils.getDatePeriodInStringFormat;
import static test.example.com.presentation.utils.TimeUtils.normalizePercentageValues;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TimeUtilsTest {

    private static final String PATTERN = "yyyy-MM-dd";

    @Test
    public void getCurrentTimeSuccess() throws Exception {
        assertTrue(TimeUtils.getCurrentTime() > 0);
    }

    @Test
    public void normalizePercentageValuesSuccess() throws Exception {
        assertEquals(normalizePercentageValues(1517209200000L, 1517209200000L, 1517215500000L), 0);
        assertEquals(normalizePercentageValues(1517215500000L, 1517209200000L, 1517215500000L), 100);
        assertEquals(normalizePercentageValues(1517212350000L, 1517209200000L, 1517215500000L), 50);
    }

    @Test
    public void getDatePeriodInStringFormatSuccess() throws Exception {
        Calendar cal = Calendar.getInstance();
        String from = new SimpleDateFormat(DATA_PATTERN_DAY, Locale.getDefault()).format(cal.getTime());

        cal.add(Calendar.DATE, +1);
        String to = new SimpleDateFormat(DATA_PATTERN_DAY, Locale.getDefault()).format(cal.getTime());


        assertEquals(getDatePeriodInStringFormat(), from + "~" + to);
    }

}