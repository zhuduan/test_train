package com.zhuduan.train.constant;

import java.util.Arrays;

/**
 * the type you want to input/output the Data, and change it to TrainSchedule
 *
 * @author Haifeng.Zhu
 * created at 3/10/19
 */
public enum EnumIOType {

    ARG_STRING(1, "argString"),
    FILE(2, "file"),
    CONSOLE_STRING(3, "consoleString");

    private int id;
    private String name;

    EnumIOType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static EnumIOType getIOType(String name) {
        return Arrays.stream(EnumIOType.values()).filter(type -> type.getName().equals(name)).findAny().get();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
