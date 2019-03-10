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

    /***
     * check the number is not a negative number or null
     *
     * @param number
     * @return true if number is not null and not less than 0
     */
    public static Boolean isNotNegative(Integer number){
        if (number==null || number<0){
            return false;
        }
        return true;
    }
}
