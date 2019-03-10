package com.zhuduan.train.calculator;

import com.zhuduan.train.model.schedule.TrainSchedule;
import com.zhuduan.train.model.station.TrainStation;

public class TripsWithMaxStopCalculator extends TripsWithStopCalculator {

    @Override
    protected Integer getTrips(TrainSchedule schedule, Integer stopNum,
                               TrainStation startStation, TrainStation endStation) {
        return null;
    }
}
