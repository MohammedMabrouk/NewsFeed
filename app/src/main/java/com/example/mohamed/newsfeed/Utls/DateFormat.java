package com.example.mohamed.newsfeed.Utls;

import android.net.ParseException;

import com.android.volley.toolbox.StringRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormat {

    public static String articleDateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static String layoutDateFormat = "MMMM dd, yyyy";

    public static String changeDateFormat(String dateInString, String inputFormatStr, String outputFormatStr) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputFormatStr, Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputFormatStr, Locale.ENGLISH);
        Date date;
        String str = null;
        try {
            date = inputFormat.parse(dateInString);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            // error
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
}
