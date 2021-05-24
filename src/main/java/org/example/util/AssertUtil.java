package org.example.util;


import org.example.common.exception.ParamsException;

/**
 * 校验工具类
 */
public class AssertUtil {

    /**
     * 判断返回值是否为true
     *  如果为true，则抛出异常
     * @param flag
     * @param msg
     */
    public static void isTrue(boolean flag, String msg){
        //如果为true则抛出异常
        if (flag){
            throw new ParamsException(msg);
        }
    }
}
