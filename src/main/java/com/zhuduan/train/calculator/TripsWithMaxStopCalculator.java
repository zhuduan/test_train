package com.zhuduan.train.calculator;

import com.zhuduan.train.bo.plan.StopsTrainPlan;
import com.zhuduan.train.bo.station.TrainStation;
import com.zhuduan.train.exception.DataException;

import java.util.List;
import java.util.stream.Collectors;

public class TripsWithMaxStopCalculator extends TripsWithStopCalculator {

    @Override
    protected Integer getTrips(StopsTrainPlan trainPlan) throws DataException {
        // get all passed route
        List<List<TrainStation>> trips = getAllTripsWithStop(trainPlan);

        // filter the result
        List<List<TrainStation>> result = trips.stream().filter(trip -> {
            TrainStation tripEnd = trip.get(trip.size() - 1);
            return tripEnd.equals(trainPlan.getEndStation());
        }).collect(Collectors.toList());
        return result.size();
    }
}
