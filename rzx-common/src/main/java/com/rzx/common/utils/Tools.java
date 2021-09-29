package com.rzx.common.utils;

import cn.hutool.core.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 常用工具
 *
 * @author zy
 * @date 2021/6/8 14:45
 */
public class Tools {

    private final static Logger LOGGER = LoggerFactory.getLogger(Tools.class);

    /**
     * 判断不能全是相同的数字或者字母
     *
     * @param numOrStr
     * @return
     */
    public static boolean equalStr(String numOrStr) {
        boolean flag = true;
        char str = numOrStr.charAt(0);
        for (int i = 0; i < numOrStr.length(); i++) {
            if (str != numOrStr.charAt(i)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * 不能是连续的数字--递增（如：123456、12345678）
     *
     * @param numOrStr
     * @return 连续数字返回true
     */
    public static boolean isOrderNumeric(String numOrStr) {
        //如果全是连续数字返回true
        boolean flag = true;
        //如果全是数字返回true
        boolean isNumeric = true;
        for (int i = 0; i < numOrStr.length(); i++) {
            if (!Character.isDigit(numOrStr.charAt(i))) {
                isNumeric = false;
                break;
            }
        }
        //如果全是数字则执行是否连续数字判断
        if (isNumeric) {
            for (int i = 0; i < numOrStr.length(); i++) {
                //判断如123456
                if (i > 0) {
                    int num = Integer.parseInt(numOrStr.charAt(i) + "");
                    int num_ = Integer.parseInt(numOrStr.charAt(i - 1) + "") + 1;
                    if (num != num_) {
                        flag = false;
                        break;
                    }
                }
            }
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String date2Str(Date date) {
        return date2Str(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 按照参数format的格式，日期转字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } else {
            return "";
        }
    }

    /**
     * 获取32位UUID
     *
     * @return
     */
    public static String get32UUID() {
        return RandomUtil.randomUUID().replace("-","");
    }


}
