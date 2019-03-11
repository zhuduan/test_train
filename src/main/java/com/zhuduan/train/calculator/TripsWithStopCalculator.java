package com.zhuduan.train.calculator;

import com.zhuduan.train.bo.schedule.TrainSchedule;
import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.bo.plan.StopsTrainPlan;
import com.zhuduan.train.bo.plan.TrainPlan;
import com.zhuduan.train.bo.station.TrainStation;
import com.zhuduan.train.bo.suggestion.Suggestion;
import com.zhuduan.train.exception.DataException;

import java.util.*;

public abstract class TripsWithStopCalculator implements Calculator {

    @Override
    public Suggestion getSuggestion(TrainPlan trainPlan) throws Exception {
        if (trainPlan instanceof StopsTrainPlan) {
            StopsTrainPlan stopsTrainPlan = (StopsTrainPlan) trainPlan;
            if (!stopsTrainPlan.isValid()) {
                return new Suggestion(ErrorCode.INVALID_TRAIN_PLAN.getMessage());
            }

            Integer trips = getTrips(stopsTrainPlan);
            return new Suggestion(String.valueOf(trips));
        }
        return new Suggestion(ErrorCode.ILLAGEL_TRAIN_PLAN.getMessage());
    }

    /* get all trips with stop number */
    protected List<List<TrainStation>> getAllTripsWithStop(StopsTrainPlan trainPlan) throws DataException {
        TrainSchedule schedule = trainPlan.getTrainSchedule();
        List<List<TrainStation>> allTrips = new ArrayList<>();

        // get initial reachable route and add to the all trips
        List<List<TrainStation>> currentTrips = new ArrayList<>();
        currentTrips.add(schedule.getTripWithStation(trainPlan.getStartStation()));

        // get all passed routes util stop end
        for (int i = 0; i < trainPlan.getStopNumber(); i++) {
            List<List<TrainStation>> increasedTrips = new ArrayList<>();
            for (List<TrainStation> trip : currentTrips) {
                increasedTrips.addAll(schedule.increaseTrip(trip));
            }
            // add all the increased list, and use the list as the new start list
            allTrips.addAll(increasedTrips);
            currentTrips = increasedTrips;
        }
        return allTrips;
    }

    /***
     * get the trips due to the condition
     *
     * @param trainPlan
     * @return
     */
    protected abstract Integer getTrips(StopsTrainPlan trainPlan) throws DataException;
}
