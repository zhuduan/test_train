package com.zhuduan.train.calculator;

import com.zhuduan.train.bo.plan.StopsTrainPlan;
import com.zhuduan.train.bo.station.TrainStation;

import java.util.List;
import java.util.stream.Collectors;

public class TripsWithExactStopCalculator extends TripsWithStopCalculator {

    @Override
    protected Integer getTrips(StopsTrainPlan plan) {
        // get all passed route
        List<List<TrainStation>> trips = getAllTripsWithStop(plan);

        // filter the ones due to condition
        List<List<TrainStation>> result = trips.stream().filter( trip -> {
            TrainStation lastStation =  trip.get(trip.size()-1);
            Integer tripSize = plan.getStopNumber() + 1;
            return (trip.size()==tripSize && lastStation.equals(plan.getEndStation()) );
        }).collect(Collectors.toList());
        return result.size();
    }
}
