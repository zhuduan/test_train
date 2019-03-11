package com.zhuduan.train.bo.schedule;

import com.zhuduan.train.constant.DefaultSetting;
import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.exception.DataException;
import com.zhuduan.train.bo.station.TrainStation;
import com.zhuduan.train.util.UtilTool;

import java.io.IOException;
import java.util.ArrayList;
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

    public TrainSchedule() {
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
    public Boolean containsStation(String name) {
        return allStations.stream().anyMatch(existed -> existed.isSameByName(name));
    }

    /***
     * get the station by name
     * @param name
     * @return
     */
    public TrainStation getStationByName(String name) {
        return allStations.stream().filter(station -> station.isSameByName(name)).findFirst().get();
    }

    /***
     * check if the data is valid
     *
     * @return
     */
    public Boolean isValid() {
        if (adjacentMatrix == null || adjacentMatrix.length == 0) {
            return false;
        }

        if (allStations == null) {
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
        if (startIndex > adjacentMatrix.length || endIndex > adjacentMatrix[0].length) {
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

    /***
     * get trips which start from every station
     *
     * @return
     */
    public List<List<TrainStation>> getTripsWithAllStations() throws DataException {
        List<List<TrainStation>> trips = new ArrayList<>();
        for (TrainStation trainStation : getAllStations()) {
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
    public List<TrainStation> getTripWithStation(TrainStation start) throws DataException {
        if (start == null) {
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
    public List<List<TrainStation>> increaseTrip(List<TrainStation> trip) throws DataException {
        if (trip == null) {
            throw new DataException(ErrorCode.INVALID_ROUTE_INFO);
        }

        List<List<TrainStation>> increasedTrips = new ArrayList<>();

        TrainStation currentStation = trip.get(trip.size() - 1);
        getAllStations().forEach(nextStation -> {
            Integer routeLength = getLengthBetween(currentStation, nextStation);
            if (!UtilTool.isEqualInteger(routeLength, DefaultSetting.UNREACHABLE)) {
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
    public Integer getTripLength(List<TrainStation> trip) throws DataException {
        if (trip == null) {
            throw new DataException(ErrorCode.INVALID_ROUTE_INFO);
        }

        Integer routeLength = 0;
        for (int i = 0; i < trip.size() - 1; i++) {
            routeLength = routeLength + getLengthBetween(trip.get(i), trip.get(i + 1));
        }
        return routeLength;
    }

    protected abstract void generateScheduleInfo() throws DataException, IOException;
}
