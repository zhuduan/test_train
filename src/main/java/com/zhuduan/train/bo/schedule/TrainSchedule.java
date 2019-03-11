package com.zhuduan.train.bo.schedule;

import com.zhuduan.train.constant.DefaultSetting;
import com.zhuduan.train.exception.DataException;
import com.zhuduan.train.bo.station.TrainStation;

import java.util.List;

/**
 * the parent of all TrainSchedules
 * and using the template mode here to generate the
 * Train Schedule from several input types
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public abstract class TrainSchedule {

    protected Integer[][] adjacentMatrix;
    protected List<TrainStation> allStations;
    
    public TrainSchedule(){
    }

    /**
     * get the matrix, the value means weight, the index means the point(station index)
     * notice: UNREACHABLE means there is no route between two points
     *
     * @return
     */
    public Integer[][] getAdjacentMatrix() {
        return adjacentMatrix;
    }

    /***
     * get all stations
     *
     * @return
     */
    public List<TrainStation> getAllStations() {
        return allStations;
    }

    /***
     * if the station is already in the station list
     *
     * @param name
     * @return
     */
    public Boolean containsStation(String name){
        return allStations.stream().anyMatch( existed -> existed.isSameByName(name));
    }

    /***
     * get the station by name
     * @param name
     * @return
     */
    public TrainStation getStationByName(String name){
        return allStations.stream().filter( station -> station.isSameByName(name) ).findFirst().get();
    }

    /***
     * check if the data is valid
     *
     * @return
     */
    public Boolean isValid() {
        if (adjacentMatrix == null 
                || adjacentMatrix.length == 0
                || adjacentMatrix[0].length == 0) {
            return false;
        }

        if (allStations == null){
            return false;
        }
        return true;
    }

    /***
     * get length between two station
     * 
     * @param startIndex
     * @param endIndex
     * @return
     */
    public Integer getLengthBetween(Integer startIndex, Integer endIndex) {
        if ( startIndex> adjacentMatrix.length || endIndex> adjacentMatrix[0].length){
            return DefaultSetting.UNREACHABLE;
        }
        return adjacentMatrix[startIndex][endIndex];
    }

    /***
     * get length between two station
     * 
     * @param startStation
     * @param endStation
     * @return
     */
    public Integer getLengthBetween(TrainStation startStation, TrainStation endStation) {
        return getLengthBetween(startStation.getIndex(), endStation.getIndex());
    }

    /***
     * get length between two station
     * 
     * @param startName
     * @param endName
     * @return
     */
    public Integer getLengthBetween(String startName, String endName) {
        return getLengthBetween(getStationByName(startName), getStationByName(endName));
    }

    protected abstract void generateScheduleInfo() throws DataException ;
}
