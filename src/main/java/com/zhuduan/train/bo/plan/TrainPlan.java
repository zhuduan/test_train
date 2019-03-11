package com.zhuduan.train.bo.plan;


import com.zhuduan.train.constant.DefaultSetting;
import com.zhuduan.train.constant.EnumSuggestionType;
import com.zhuduan.train.bo.schedule.TrainSchedule;
import com.zhuduan.train.bo.station.TrainStation;
import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.exception.DataException;
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

    private Integer id;
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
    public List<List<TrainStation>> getTripsWithAllStations() throws DataException{
        List<List<TrainStation>> trips = new ArrayList<>();
        for (TrainStation trainStation : trainSchedule.getAllStations()){
            trips.add(getTripWithStation(trainStation));
        }
        return trips;
    }

    /***
     * get trip which start defined station
     * 
     * @param start
     * @return
     * @throws DataException
     */
    public List<TrainStation> getTripWithStation(TrainStation start) throws DataException{
        if (start==null){
            throw new DataException(ErrorCode.INVALID_ROUTE_INFO);
        }
        
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
     * @throws DataException
     */
    public List<List<TrainStation>> increaseTrip(List<TrainStation> trip) throws DataException{
        if (trip==null){
            throw new DataException(ErrorCode.INVALID_ROUTE_INFO);
        }
        
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

    /****
     * get the length of trip
     * 
     * @param trip
     * @return
     * @throws DataException
     */
    public Integer getTripLength(List<TrainStation> trip) throws DataException{
        if (trip==null){
            throw new DataException(ErrorCode.INVALID_ROUTE_INFO);
        }
        
        Integer routeLength = 0;
        for(int i=0; i<trip.size()-1; i++){
            routeLength = routeLength + trainSchedule.getLengthBetween(trip.get(i), trip.get(i+1));
        }
        return routeLength;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
