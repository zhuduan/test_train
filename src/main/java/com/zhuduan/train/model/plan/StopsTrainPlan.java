package com.zhuduan.train.model.plan;

import com.zhuduan.train.constant.EnumSuggestionType;
import com.zhuduan.train.model.schedule.TrainSchedule;
import com.zhuduan.train.model.station.TrainStation;
import com.zhuduan.train.util.UtilTool;

public class StopsTrainPlan extends TrainPlan {

    private Integer stopNumber;

    public StopsTrainPlan(TrainSchedule trainSchedule, TrainStation startStation, TrainStation endStation,
                          Integer stopNumber, EnumSuggestionType suggestionType) {
        super(trainSchedule, startStation, endStation, suggestionType);
        this.stopNumber = stopNumber;
    }

    @Override
    public Boolean isValid() {
        if (!UtilTool.isPositive(stopNumber)){
            return false;
        }
        return super.isValid();
    }

    public Integer getStopNumber() {
        return stopNumber;
    }

    public void setStopNumber(Integer stopNumber) {
        this.stopNumber = stopNumber;
    }
}
