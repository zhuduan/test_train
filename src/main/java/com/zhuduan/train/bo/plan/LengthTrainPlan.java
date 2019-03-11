package com.zhuduan.train.bo.plan;

import com.zhuduan.train.constant.EnumSuggestionType;
import com.zhuduan.train.bo.schedule.TrainSchedule;
import com.zhuduan.train.bo.station.TrainStation;
import com.zhuduan.train.util.UtilTool;

public class LengthTrainPlan extends TrainPlan {

    private Integer routeLength;

    public LengthTrainPlan(TrainSchedule trainSchedule, TrainStation startStation, TrainStation endStation,
                           Integer routeLength, EnumSuggestionType suggestionType) {
        super(trainSchedule, startStation, endStation, suggestionType);
        this.routeLength = routeLength;
    }

    @Override
    public Boolean isValid() {
        if (!UtilTool.isPositive(routeLength)){
            return false;
        }
        return super.isValid();
    }

    public Integer getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(Integer routeLength) {
        this.routeLength = routeLength;
    }
}
