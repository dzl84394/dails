package cn.dails.base.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String F19 = "yyyy-MM-dd HH:mm:ss";
	
    public static String F14 = "yyyyMMddHHmmss";
 
    public static String F10 = "yyyy-MM-dd";
 
    public static String F8 = "yyyyMMdd";

    public static final String TIME_PATTERN = "HH:mm:ss";
    public static final String DATE_TIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.S";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_YYYYMMDD_PATTERN = "yyyyMMdd";
    public static final String DATE_YYYY_MM_DD_PATTERN = "yyyy-MM-dd";
    public static final String TIME_HHMM_PATTERN = "HH:mm";
    public static final String TIME_HHMM_PATTERN2 = "HHmm";
    public static final String TIME_HHMMSS_PATTERN = "HH:mm:ss";
    public static final String TIME_HHMMSS_PATTERN2 = "HHmmss";
    public static final String DATE_TIME_NO_HORI_PATTERN = "yyyyMMdd HH:mm:ss";
    public static final String DATE_TIME_NO_SPACE_PATTERN = "yyyyMMddHHmmss";
    public static final String DATE_TIME_NO_SPACE_MS_PATTERN = "yyyyMMddHHmmssS";
    public static final String DATE_TIME_PLAYBILL_PATTERN = "yyyyMMdd HH:mm";
    public static final String DATE_TIME_INDEX_PLAYBILL_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_ENGLISH_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy";
    
    public static final long ONE_DAY_STAMP = 1000 * 60 * 60 * 24;

    public static String formatDate(String pattern, Date adate) {
        if (adate == null) {
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(adate);
    }

    public static Date parseDate(String pattern, String dateStr) {

        if (dateStr == null || "".equals(dateStr)) {
            return null;
        }


        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {

        }

        return null;
    }



}
