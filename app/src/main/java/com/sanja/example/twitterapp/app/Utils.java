package com.sanja.example.twitterapp.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    private static final Locale LOCALE = Locale.ENGLISH;

    private static final SimpleDateFormat TWEET_INPUT_DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", LOCALE);
    private static final SimpleDateFormat TWEET_OUTPUT_DATE_FORMAT = new SimpleDateFormat("MMMM, dd yyyy, HH:mm:ss", LOCALE);

    public static String apiDateToUIDate(String apiDate, String defaultValue) {
        String parsedDate;
        try {
            Date date = TWEET_INPUT_DATE_FORMAT.parse(apiDate);
            parsedDate = TWEET_OUTPUT_DATE_FORMAT.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            parsedDate = defaultValue;
        }
        return parsedDate;
    }
}