package com.daowen.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SequenceUtil {

    private static long sequence = 0;

    public static synchronized String buildSequence(String prefix) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(new Date());
        sequence = (sequence + 1) % 10000;
        return prefix + dateStr + String.format("%04d", sequence);
    }

    public static String buildSequence() {
        return buildSequence("");
    }
}