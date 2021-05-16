package com.util;

import org.apache.commons.lang3.time.FastDateFormat;

public class TimeUtils {
    public static String format(Long timestamp, String pattern) {
        return FastDateFormat.getInstance(pattern).format(timestamp);
    }
}
