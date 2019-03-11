package com.zhuduan.train;

import com.zhuduan.train.bo.schedule.StringTrainSchedule;
import com.zhuduan.train.bo.schedule.TrainSchedule;
import com.zhuduan.train.bo.station.TrainStation;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestUtil {

    public static TrainSchedule getTestSchedule(){
        TrainStation stationA = new TrainStation(0, "A", "a");
        TrainStation stationB = new TrainStation(1, "B", "b");
        TrainStation stationC = new TrainStation(2, "C", "c");
        TrainStation stationD = new TrainStation(3, "D", "d");
        TrainStation stationE = new TrainStation(4, "E", "e");

        // AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
        StringTrainSchedule trainSchedule = mock(StringTrainSchedule.class);
        when(trainSchedule.isValid()).thenReturn(true);
        when(trainSchedule.getAllStations()).thenReturn(Arrays.asList(stationA, stationB,
                stationC, stationD, stationE));
        when(trainSchedule.getStationByName("A")).thenReturn(stationA);
        when(trainSchedule.getStationByName("B")).thenReturn(stationB);
        when(trainSchedule.getStationByName("C")).thenReturn(stationC);
        when(trainSchedule.getStationByName("D")).thenReturn(stationD);
        when(trainSchedule.getStationByName("E")).thenReturn(stationE);

        when(trainSchedule.getLengthBetween(any(TrainStation.class), any(TrainStation.class))).thenReturn(-1);
        when(trainSchedule.getLengthBetween(stationA, stationB)).thenReturn(5);
        when(trainSchedule.getLengthBetween(stationB, stationC)).thenReturn(4);
        when(trainSchedule.getLengthBetween(stationC, stationD)).thenReturn(8);
        when(trainSchedule.getLengthBetween(stationD, stationC)).thenReturn(8);
        when(trainSchedule.getLengthBetween(stationD, stationE)).thenReturn(6);
        when(trainSchedule.getLengthBetween(stationA, stationD)).thenReturn(5);
        when(trainSchedule.getLengthBetween(stationC, stationE)).thenReturn(2);
        when(trainSchedule.getLengthBetween(stationE, stationB)).thenReturn(3);
        when(trainSchedule.getLengthBetween(stationA, stationE)).thenReturn(7);

        return trainSchedule;
    }
}
