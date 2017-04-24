package com.sanja.example.twitterapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    private static final Locale LOCALE = Locale.ENGLISH;

    private static final SimpleDateFormat TWEET_INPUT_DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", LOCALE);
    private static final SimpleDateFormat TWEET_OUTPUT_DATE_FORMAT = new SimpleDateFormat("HH:mm:ss - dd MMMM, yyyy", LOCALE);

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