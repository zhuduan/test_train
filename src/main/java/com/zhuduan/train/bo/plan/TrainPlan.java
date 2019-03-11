package com.zhuduan.train.bo.plan;


import com.zhuduan.train.constant.DefaultSetting;
import com.zhuduan.train.constant.EnumSuggestionType;
import com.zhuduan.train.bo.schedule.TrainSchedule;
import com.zhuduan.train.bo.station.TrainStation;
import com.zhuduan.train.util.UtilTool;

import java.util.ArrayList;
import java.util.List;

/**
 * the plan need to get some suggestions
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class TrainPlan {

    private TrainSchedule trainSchedule;
    private TrainStation startStation;
    private TrainStation endStation;
    private EnumSuggestionType suggestionType;

    public TrainPlan() {
    }

    public TrainPlan(TrainSchedule trainSchedule, TrainStation startStation,
                     TrainStation endStation, EnumSuggestionType suggestionType) {
        this.trainSchedule = trainSchedule;
        this.startStation = startStation;
        this.endStation = endStation;
        this.suggestionType = suggestionType;
    }

    /***
     * check if the schedule and start/end station is valid
     * 
     * @return true if data is valid, or false
     */
    public Boolean isValid(){
        if (startStation==null || endStation==null){
            return false;
        }
        return trainSchedule.isValid();
    }

    /***
     * get trips which start from every station
     * 
     * @return
     */
    public List<List<TrainStation>> getTripsWithAllStations(){
        List<List<TrainStation>> trips = new ArrayList<>();
        trainSchedule.getAllStations().forEach( station -> trips.add(getTripWithStation(station)));
        return trips;
    }

    /***
     * get trip which start defined station
     * 
     * @param start
     * @return
     */
    public List<TrainStation> getTripWithStation(TrainStation start){
        List<TrainStation> trip = new ArrayList<>();
        trip.add(start);
        return trip;
    }

    /***
     * increase the trip: means travel from current station to next reachable station
     *                    because we have several next reachable stations, will return 
     *                    more than one trip
     * 
     * @param trip
     * @return trip list, means all the trip can start from current station to next station
     */
    public List<List<TrainStation>> increaseTrip(List<TrainStation> trip){
        List<List<TrainStation>> increasedTrips = new ArrayList<>();

        TrainStation currentStation = trip.get(trip.size() - 1);
        this.trainSchedule.getAllStations().forEach( nextStation ->{
            Integer routeLength = this.trainSchedule.getLengthBetween(currentStation, nextStation);
            if (!UtilTool.isEqualInteger(routeLength, DefaultSetting.UNREACHABLE)){
                List<TrainStation> newTrip = new ArrayList<>();
                newTrip.addAll(trip);
                newTrip.add(nextStation);

                increasedTrips.add(newTrip);
            }
        });
        return increasedTrips;
    }

    public TrainSchedule getTrainSchedule() {
        return trainSchedule;
    }

    public void setTrainSchedule(TrainSchedule trainSchedule) {
        this.trainSchedule = trainSchedule;
    }

    public TrainStation getStartStation() {
        return startStation;
    }

    public void setStartStation(TrainStation startStation) {
        this.startStation = startStation;
    }

    public TrainStation getEndStation() {
        return endStation;
    }

    public void setEndStation(TrainStation endStation) {
        this.endStation = endStation;
    }

    public EnumSuggestionType getSuggestionType() {
        return suggestionType;
    }

    public void setSuggestionType(EnumSuggestionType suggestionType) {
        this.suggestionType = suggestionType;
    }
}
