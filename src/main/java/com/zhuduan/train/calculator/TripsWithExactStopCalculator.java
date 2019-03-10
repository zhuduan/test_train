package com.zhuduan.train.calculator;

import com.zhuduan.train.model.schedule.TrainSchedule;
import com.zhuduan.train.model.station.TrainStation;

import java.util.List;
import java.util.stream.Collectors;

public class TripsWithExactStopCalculator extends TripsWithStopCalculator {

    @Override
    protected Integer getTrips(TrainSchedule schedule, Integer stopNum,
                               TrainStation startStation, TrainStation endStation) {
        // get all passed route
        List<List<TrainStation>> trips = getAllTripsWithStop(schedule, stopNum, startStation, endStation);

        // filter the ones due to condition
        List<List<TrainStation>> result = trips.stream().filter( trip -> {
            TrainStation lastStation =  trip.get(trip.size()-1);
            Integer tripSize = stopNum + 1;
            return (trip.size()==tripSize && lastStation.equals(endStation) );
        }).collect(Collectors.toList());
        return result.size();
    }
}
