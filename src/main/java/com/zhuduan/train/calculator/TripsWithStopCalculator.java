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
                                                           TrainStation startStation){
        // get initial reachable route and add to the all trips
        List<List<TrainStation>> existTrips = addNextReachableStation(schedule, startStation);
        List<List<TrainStation>> allTrips = new ArrayList<>();
        allTrips.addAll(existTrips);

        // get all passed routes util stop end
        // notice as the first route generated above, should start at index 1
        for (int i=1; i<stopNum; i++) {
            // merge list means the length increased list
            List<List<TrainStation>> increasedTrips = new ArrayList<>();
            for ( List<TrainStation> trip : existTrips) {
                TrainStation newStartStation = trip.get(trip.size() - 1);
                List<List<TrainStation>> newTrips = addNextReachableStation(schedule, newStartStation);
                increasedTrips.addAll(increaseTrip(trip, newTrips));
            }
            // add all the increased list, and use the list as the new start list
            allTrips.addAll(increasedTrips);
            existTrips = increasedTrips;
        }
        return allTrips;
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

    /* increase if the second's start is the first's end 
       (notice the route may be split as the next station may more than 1) */
    private List<List<TrainStation>> increaseTrip(List<TrainStation> trip, List<List<TrainStation>> newTrips){
        List<List<TrainStation>> increasedTrip = new ArrayList<>();
        
        TrainStation tripEnd = trip.get(trip.size() -1);
        newTrips.forEach( newTrip ->{
            TrainStation newTripStart = newTrip.get(0);
            if (tripEnd.equals(newTripStart)){
                List<TrainStation> newTip = new ArrayList<>();
                newTip.addAll(trip);
                newTip.addAll(newTrip.subList(1, newTrip.size()));
                
                increasedTrip.add(newTip);
            }
        });
        return increasedTrip;
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
