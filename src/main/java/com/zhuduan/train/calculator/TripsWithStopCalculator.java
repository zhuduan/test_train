package com.zhuduan.train.calculator;

import com.zhuduan.train.constant.DefaultSetting;
import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.model.plan.StopsTrainPlan;
import com.zhuduan.train.model.plan.TrainPlan;
import com.zhuduan.train.model.schedule.TrainSchedule;
import com.zhuduan.train.model.station.TrainStation;
import com.zhuduan.train.model.suggestion.Suggestion;
import com.zhuduan.train.util.UtilTool;

import java.util.*;

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

    /***
     * get the trips due to the condition
     *
     * @param schedule
     * @param stopNum
     * @param startStation
     * @param endStation
     * @return
     */
    protected abstract Integer getTrips(TrainSchedule schedule, Integer stopNum,
                                        TrainStation startStation, TrainStation endStation);

    /* get all trips with stop number */
    protected List<List<TrainStation>> getAllTripsWithStop(TrainSchedule schedule, Integer stopNum,
                                                           TrainStation startStation, TrainStation endStation){
        // get initial reachable route
        List<List<TrainStation>> trips = addNextReachableStation(schedule, startStation);

        // get all passed routes util step end
        // notice as the first route generated above, should start at index 1
        for (int i=1; i<stopNum; i++) {
            List<List<TrainStation>> mergedTrips = new ArrayList<>();
            for ( List<TrainStation> trip : trips) {
                TrainStation newStartStation = trip.get(trip.size() - 1);
                List<List<TrainStation>> newTrips = addNextReachableStation(schedule, newStartStation);
                mergedTrips.addAll(mergeTwoTrip(trips, newTrips));
            }
            trips.addAll(mergedTrips);
            trips = removeDuplicate(trips);
        }
        return trips;
    }

    private List<List<TrainStation>> addNextReachableStation(TrainSchedule schedule, TrainStation start){
        List<List<TrainStation>> trips = new ArrayList<>();

        schedule.getAllStations().forEach( nextStation -> {
            Integer routeLength = schedule.getLengthBetween(start, nextStation);
            if (!UtilTool.isEqualInteger(routeLength, DefaultSetting.UNREACHABLE)){
                List<TrainStation> trip = new ArrayList<>();
                trip.add(start);
                trip.add(nextStation);
                trips.add(trip);
            }
        });
        return trips;
    }

    /* merge if the second's start is the first's end */
    private List<List<TrainStation>> mergeTwoTrip(List<List<TrainStation>> firstTrips,
                                                  List<List<TrainStation>> secondTrips){
        List<List<TrainStation>> mergedTrips = new ArrayList<>();
        firstTrips.forEach( firstTrip -> {
            TrainStation firstEndStation = firstTrip.get(firstTrip.size() - 1);
            secondTrips.forEach( secondTrip -> {
                TrainStation secondStartStation = secondTrip.get(0);
                if (secondStartStation.equals(firstEndStation)){
                    List<TrainStation> newTrip = new ArrayList<>();
                    newTrip.addAll(firstTrip);
                    newTrip.addAll(secondTrip.subList(1, secondTrip.size()));
                    mergedTrips.add(newTrip);
                }
            });
        });
        return mergedTrips;
    }

    private List<List<TrainStation>> removeDuplicate(List<List<TrainStation>> trips){
        List<List<TrainStation>> uniqueList = new ArrayList<>();
        Set<String> sourceRouteSet = new HashSet<>();
        trips.forEach( trip ->{
            StringBuilder stringBuilder = new StringBuilder();
            trip.forEach( station -> stringBuilder.append(station.getName()).append("->"));

            if (!sourceRouteSet.contains(stringBuilder.toString())){
                sourceRouteSet.add(stringBuilder.toString());
                uniqueList.add(trip);
            }
        });
        return uniqueList;
    }
}
