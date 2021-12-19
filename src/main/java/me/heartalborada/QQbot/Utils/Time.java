package me.heartalborada.QQbot.Utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Time {
    public static String timestampToDate(long time) {
        String dateTime;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateTime = simpleDateFormat.format(new Date(time * 1000L));
        return dateTime;
    }
}
