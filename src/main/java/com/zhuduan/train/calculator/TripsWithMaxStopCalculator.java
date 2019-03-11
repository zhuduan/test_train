package com.zhuduan.train.calculator;

import com.zhuduan.train.model.schedule.TrainSchedule;
import com.zhuduan.train.model.station.TrainStation;

import java.util.List;
import java.util.stream.Collectors;

public class TripsWithMaxStopCalculator extends TripsWithStopCalculator {

    @Override
    protected Integer getTrips(TrainSchedule schedule, Integer stopNum,
                               TrainStation startStation, TrainStation endStation) {
        // get all passed route
        List<List<TrainStation>> trips = getAllTripsWithStop(schedule, stopNum, startStation);

        // filter the result
        List<List<TrainStation>> result = trips.stream().filter( trip ->{
            TrainStation tripEnd = trip.get(trip.size() - 1);
            return tripEnd.equals(endStation);
        }).collect(Collectors.toList());
        return result.size();
    }
}
