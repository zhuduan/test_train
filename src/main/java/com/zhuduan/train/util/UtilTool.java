package com.zhuduan.train.util;

/**
 * the tools 
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class UtilTool {

    /***
     * Judge two Integer is equal or not
     * use the int rather Integer to do the compare, avoid Integer cache error
     * 
     * @param source
     * @param target
     * @return true if two Integer's value is same, or false if not same
     */
    public static Boolean isEqualInteger(Integer source, Integer target){
        return source.intValue() == target.intValue();
    }
}
