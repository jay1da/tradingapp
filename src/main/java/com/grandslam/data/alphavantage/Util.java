package com.grandslam.data.alphavantage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Util class for alphavantage data
 */
public class Util {

    private static final Logger logger = LoggerFactory.getLogger(Util.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * interval parsing constants
     */
    private static final String minutes = "min";

    /**
     * Parses a date string in the format "2018-02-19 16:00:00" to a date object
     *
     * @param date
     * @return
     */
    public static Date parseDate(String date)
    {
        //TODO: May want to add the timezone in here if needed.
        Date result = null;
        try {
            synchronized (dateFormat) {
                result = dateFormat.parse(date);
            }
        } catch (ParseException | NullPointerException ex){
            logger.error("Unable to parse date: {}", date, ex);
        }

        return result;
    }

    /**
     * Parse an interval string to a long
     * @param intervalString
     * @return
     * @throws NullPointerException
     */
    public static long parseInterval(String intervalString) throws NullPointerException{
        long result = -1l;
        String trimmed = intervalString.trim();
        String unit = trimmed.substring(trimmed.length() - 3);

        if (minutes.equals(unit)){
            String duration = trimmed.substring(0, trimmed.length() - 3);
            int intDuration = Integer.parseInt(duration);
            result = intDuration * 1000 * 60;
        }
        return result;
    }
}
