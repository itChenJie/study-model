package com.javaAdvanced;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author ChenWenJie
 * @Classname Test
 * Describe:
 * @Date 2020/2/12 16:07
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        String dateStr = "2020-4";
        DateFormat formatFrom = new SimpleDateFormat("yyyy-MM", Locale.ENGLISH);
        Date date = formatFrom.parse(dateStr);
        formatFrom.format(date);
        System.out.println(date.getTime());
    }

    public static void JSONString() {

    }


}
