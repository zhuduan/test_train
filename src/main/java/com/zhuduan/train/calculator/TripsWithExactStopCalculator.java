package com.zhuduan.train.calculator;

import com.zhuduan.train.model.schedule.TrainSchedule;
import com.zhuduan.train.model.station.TrainStation;

import java.util.ArrayList;
import java.util.List;

public class TripsWithExactStopCalculator extends TripsWithStopCalculator {

    @Override
    protected Integer getTrips(TrainSchedule schedule, Integer stopNum,
                               TrainStation startStation, TrainStation endStation) {
        List<List<TrainStation>> trips = new ArrayList<>();


        // TODO:
        return null;
    }
}
