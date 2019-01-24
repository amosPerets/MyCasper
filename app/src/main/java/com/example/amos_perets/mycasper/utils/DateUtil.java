package com.example.amos_perets.mycasper.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String getCurrentDate(){
       return new SimpleDateFormat("yyyy/MM/dd")
                .format(Calendar.getInstance().getTime());
    }

    public static String getCurrentTime(){
        return new SimpleDateFormat("HH:mm:ss")
                .format(Calendar.getInstance().getTime());
    }

    public static int getNumberDaysInMonth(){
        return Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static int getNumberOfCurrentDay(){
        return  Integer.valueOf(new SimpleDateFormat("dd").format(new Date()));
    }
}
