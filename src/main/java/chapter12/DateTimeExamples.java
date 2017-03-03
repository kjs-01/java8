package chapter12;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kjs850 on 2017. 3. 3..
 */
public class DateTimeExamples {

    private static final ThreadLocal<DateFormat> formatters =  new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue(){
            return new SimpleDateFormat("dd-MMM-yyyy");
        }
    };

    public static void main(String[] args) {
        useOldDate();
        useLocalDate();
//        useTemporalAdjuster();
//        useDateFormatter();
    }

    private static void useOldDate() {
        Date date = new Date(114, 2, 18);
        System.out.println(date);

        System.out.println(formatters.get().format(date));

        Calendar calendar =  Calendar.getInstance();
        calendar.set(2014, Calendar.FEBRUARY, 18);
        System.out.println(calendar);
    }

    private static void useLocalDate() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();
        System.out.println(date);

    }

}
