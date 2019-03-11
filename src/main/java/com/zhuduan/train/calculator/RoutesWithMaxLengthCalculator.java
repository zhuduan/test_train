package com.zhuduan.train.calculator;

import com.zhuduan.train.bo.plan.LengthTrainPlan;
import com.zhuduan.train.bo.plan.StopsTrainPlan;
import com.zhuduan.train.bo.plan.TrainPlan;
import com.zhuduan.train.bo.schedule.TrainSchedule;
import com.zhuduan.train.bo.station.TrainStation;
import com.zhuduan.train.bo.suggestion.Suggestion;
import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.exception.DataException;
import com.zhuduan.train.util.UtilTool;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoutesWithMaxLengthCalculator implements Calculator {

    @Override
    public Suggestion getSuggestion(TrainPlan trainPlan) throws Exception {
        if (trainPlan instanceof LengthTrainPlan){
            LengthTrainPlan lengthTrainPlan = (LengthTrainPlan) trainPlan;
            if (!lengthTrainPlan.isValid()) {
                return new Suggestion(ErrorCode.INVALID_TRAIN_PLAN.getMessage());
            }

            Integer trips = getTrips(lengthTrainPlan);
            return new Suggestion(String.valueOf(trips));
        }
        return new Suggestion(ErrorCode.ILLAGEL_TRAIN_PLAN.getMessage());
    }
    
    private Integer getTrips(LengthTrainPlan trainPlan) throws DataException {
        TrainSchedule schedule = trainPlan.getTrainSchedule();
        List<List<TrainStation>> allTrips = new ArrayList<>();
        
        List<List<TrainStation>> currentTrips = new ArrayList<>();
        currentTrips.add(schedule.getTripWithStation(trainPlan.getStartStation()));
        
        // find all the routes until all the trip is bigger than the length
        while (currentTrips.size()>0) {
            List<List<TrainStation>> increasedTrips = new ArrayList<>();
            for (List<TrainStation> trip : currentTrips) {
                List<List<TrainStation>> tempTrips = schedule.increaseTrip(trip);
                for (List<TrainStation> tempTrip : tempTrips){
                    // only keep the trip little than the length
                    if (schedule.getTripLength(tempTrip)<trainPlan.getRouteLength()){
                        increasedTrips.add(tempTrip);
                    } 
                }
            }
            // add all the increased list, and use the list as the new start list
            allTrips.addAll(increasedTrips);
            currentTrips = increasedTrips;
        }

        List<List<TrainStation>> resultList = allTrips.stream()
                .filter( trip -> trip.get(trip.size()-1).equals(trainPlan.getEndStation()))
                .collect(Collectors.toList());
        return resultList.size();
    }
}
