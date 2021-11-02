package com.rzx.common.utils.uuid;

import com.rzx.common.utils.Tools;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * ID生成器工具类
 *
 * @author zy
 */
public class IdUtils {

    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID() {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 随机UUID
     */
    public static String fastUUID() {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String fastSimpleUUID() {
        return UUID.fastUUID().toString(true);
    }

    private static List<Integer> array = new ArrayList<>(30);
    public static String getId() {
        // 获取17位时间戳
        String tempString = Tools.date2Str(new Date(), "yyyyMMddHHmmssSSS");
        int random = (int) ((Math.random() * 9 + 1) * 100000);
        while (array.contains(random)) {
            random = (int) ((Math.random() * 9 + 1) * 100000);
        }
        tempString += random;
        array.add(random);
        if (array.size() == 30) {// 数组长度达到30个就清空
            array.clear();
        }
        return tempString;
    }

}
