package com.zhuduan.train.calculator;

import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.model.plan.StopsTrainPlan;
import com.zhuduan.train.model.plan.TrainPlan;
import com.zhuduan.train.model.schedule.TrainSchedule;
import com.zhuduan.train.model.station.TrainStation;
import com.zhuduan.train.model.suggestion.Suggestion;

public abstract class TripsWithStopCalculator implements Calculator{

    @Override
    public Suggestion getSuggestion(TrainPlan trainPlan) throws Exception {
        if (trainPlan instanceof StopsTrainPlan){
            StopsTrainPlan stopsTrainPlan = (StopsTrainPlan) trainPlan;
            if (!stopsTrainPlan.isValid()) {
                return new Suggestion(ErrorCode.INVALID_TRAIN_PLAN.getMessage());
            }

            Integer trips = getTrips(stopsTrainPlan.getTrainSchedule(), stopsTrainPlan.getStopNumber(),
                    stopsTrainPlan.getStartStation(), stopsTrainPlan.getEndStation());
            new Suggestion(String.valueOf(trips));
        }
        return new Suggestion(ErrorCode.ILLAGEL_TRAIN_PLAN.getMessage());
    }

    protected abstract Integer getTrips(TrainSchedule schedule, Integer stopNum,
                                        TrainStation startStation, TrainStation endStation);
}
