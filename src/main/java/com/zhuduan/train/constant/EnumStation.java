package com.zhuduan.train.constant;

/**
 * the station of the train
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public enum EnumStation {
    A(0, "A", "a"),
    B(1, "B", "b"),
    C(2, "C", "c"),
    D(3, "D", "d"),
    E(4, "E", "e"),
    F(5, "F", "f");
    
    private Integer index;
    private String name;
    private String alias;

    EnumStation(Integer index, String name, String alias) {
        this.index = index;
        this.name = name;
        this.alias = alias;
    }

    public Integer getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }
}
