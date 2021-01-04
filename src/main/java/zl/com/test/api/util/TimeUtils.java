package zl.com.test.api.util;

import zl.com.test.api.common.Constants;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class TimeUtils {
    private static final String FORMAT = "yyyyMMddHHmmss";
    private static final String FORMAT1 = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT2 = "yyyyMM";

    public static Date getDiffTime(int unit, int diff) {
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(unit, diff);// 3分钟之前的时间
        Date beforeD = beforeTime.getTime();
        return beforeD;
    }

    public static List<String> getMonthsByStep(Date date, int size) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT2);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        size = Math.abs(size);
        List<String> list = new ArrayList();
        for (int i = 0; i <= size; i++) {
            c.setTime(date);
            c.add(Calendar.MONTH, i);
            Date m = c.getTime();
            list.add(sdf.format(m));
        }
//        Collections.reverse(list);
        return list;

    }

    public static int getDateDiff(String date1, String date2, String unit) {
        int diff = 0;

        date1 = date1.substring(0, 10);
        date2 = date2.substring(0, 10);
        Period period = Period.between(LocalDate.parse(date1), LocalDate.parse(date2));
        switch (unit) {
            case Constants.TimeUnit.YEAR:
                diff = period.getDays();
                break;
            case Constants.TimeUnit.MONTH:
                diff = period.getMonths();
                break;
            case Constants.TimeUnit.DAY:
                diff = period.getDays();
                break;
            case Constants.TimeUnit.HOUR:
                diff = period.getDays();
                break;
        }
        return diff;
    }


    public static String getStrFromDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT1);
        return dateFormat.format(date);
    }

    public static Date getDateFromStr(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT1);
        try {
            return dateFormat.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
