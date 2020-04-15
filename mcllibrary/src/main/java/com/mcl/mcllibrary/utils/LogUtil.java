package com.mcl.mcllibrary.utils;

import android.content.pm.PackageManager;
import android.util.Log;


public class LogUtil {
    private static String tag = "LogUtil-->";

    private static final String TOP_BORDER     = "╔═══════════════════════════════════════════════════════════════════════════════════════════════════";
    private static final String LEFT_BORDER    = "║";
    private static final String BOTTOM_BORDER  = "╚═══════════════════════════════════════════════════════════════════════════════════════════════════";
    private static LogUtil log;
    private PackageManager manager;
    private LogUtil() {}

    /**
     * 获取调用log方法的名字
     * @return Name
     */
    private String getFunctionName() {

        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(this.getClass().getName())) {
                continue;
            }

            return "("+ st.getFileName() + ":" + st.getLineNumber() + ")  "
                    + st.getMethodName() + " -->";
        }
        return null;
    }


    public static void i(Object str) {
        printLog(Log.INFO, str);
    }

    public static void d(Object str) {
        printLog(Log.DEBUG, str);
    }

    public static void v(Object str) {
        printLog(Log.VERBOSE, str);
    }

    public static void w(Object str) {
        printLog(Log.WARN, str);
    }

    public static void e(Object str) {
        printLog(Log.ERROR, str);
    }

    /**
     * 调用系统的打印
     * @param index
     * @param str
     */
    private static void printLog(int index, Object str) {

        if (log == null) {
            log = new LogUtil();
        }
        String name = log.getFunctionName();
        if (name != null) {
            str = name + str;
        }
        switch (index) {
            case Log.VERBOSE:
                Log.v(tag,TOP_BORDER);
                Log.v(tag,LEFT_BORDER+ str.toString());
                Log.v(tag,BOTTOM_BORDER);
                break;
            case Log.DEBUG:
                Log.d(tag,TOP_BORDER);
                Log.d(tag,LEFT_BORDER+ str.toString());
                Log.d(tag,BOTTOM_BORDER);
                break;
            case Log.INFO:
                Log.i(tag,TOP_BORDER);
                Log.i(tag,LEFT_BORDER+ str.toString());
                Log.i(tag,BOTTOM_BORDER);
                break;
            case Log.WARN:
                Log.w(tag,TOP_BORDER);
                Log.w(tag,LEFT_BORDER+ str.toString());
                Log.w(tag,BOTTOM_BORDER);
                break;
            case Log.ERROR:
                Log.e(tag,TOP_BORDER);
                Log.e(tag,LEFT_BORDER+ str.toString());
                Log.e(tag,BOTTOM_BORDER);
                break;
            default:
                break;
        }
    }
}
