package com.zhuduan.train.model.station;

public class TrainStation {

    private Integer index;
    private String name;
    private String alias;

    public TrainStation(Integer index, String name, String alias) {
        this.index = index;
        this.name = name;
        this.alias = alias;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
