package com.zhuduan.train.model.schedule;

import com.zhuduan.train.constant.DefaultSetting;
import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.exception.DataException;
import com.zhuduan.train.model.station.TrainStation;
import org.apache.commons.lang3.StringUtils;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * get input file from String
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class StringTrainSchedule extends TrainSchedule{

    private static final String ROUTE_PREFIX = "Graph: ";
    private static final String ROUTE_SPLIT = ",";

    // the route data 
    // e.g. Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
    private String routeData;

    public StringTrainSchedule(String routeData) throws DataException{
        this.routeData = routeData;
        generateScheduleInfo();
    }

    @Override
    protected void generateScheduleInfo() throws DataException {
        if (StringUtils.isEmpty(routeData)){
            throw new DataException(ErrorCode.INVALID_ROUTE_INFO);
        }

        String actualRoute = routeData.replace(ROUTE_PREFIX, StringUtils.EMPTY).trim();

        // should generate the station list before the matrix
        generateStationList(actualRoute);
        generateMatrix(actualRoute);
    }

    private void generateStationList(String route){
        // init firstly
        this.allStations = new ArrayList<>();

        char[] details = route.toCharArray();
        for (int i=0; i<details.length; i++){
            if (Character.isAlphabetic(details[i])
                    && !containsStation(String.valueOf(details[i]))){
                TrainStation station = new TrainStation(this.allStations.size(), String.valueOf(details[i]),
                        String.valueOf(details[i]));
                this.allStations.add(station);
            }
        }
    }

    private void generateMatrix(String route) throws DataException{
        // initial matrix at the first
        initMatrix();

        String[] data = route.split(ROUTE_SPLIT);
        for (int i=0; i<data.length; i++){
            data[i] = data[i].trim();

            TrainStation startStation = getStationByName(data[i].substring(0,1));
            TrainStation endStation = getStationByName(data[i].substring(1,2));
            if ( startStation==null || endStation==null){
                throw new DataException(ErrorCode.INVALID_ROUTE_INFO);
            }

            Integer dataEndIndex = data[i].indexOf(ROUTE_SPLIT);
            dataEndIndex = dataEndIndex==-1 ? data[i].length() : dataEndIndex;
            Integer length = Integer.parseInt(data[i].substring(2,dataEndIndex));
            adjacentMatrix[startStation.getIndex()][endStation.getIndex()] = length;
        }
    }

    private void initMatrix(){
        this.adjacentMatrix = new Integer[allStations.size()][allStations.size()];
        for (int i=0; i<adjacentMatrix.length; i++){
            for (int j=0; j<adjacentMatrix[i].length; j++){
                adjacentMatrix[i][j] = DefaultSetting.UNREACHABLE;
            }
        }
    }
}
