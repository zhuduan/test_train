package com.zhuduan.test.train.constant;

/**
 * the type of calculator
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public enum EnumCalculatorType {
    ROUTE_MIN(1, "routeMin"),
    ROUTE_LENGTH(2, "routeLen"),
    ROUTE_NUM(3, "routeNum");
    
    private Integer id;
    private String name;

    EnumCalculatorType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
