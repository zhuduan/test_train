package com.zhuduan.train.calculator;

import com.zhuduan.train.TestUtil;
import com.zhuduan.train.constant.EnumSuggestionType;
import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.model.plan.DirectRouteTrainPlan;
import com.zhuduan.train.model.plan.StopsTrainPlan;
import com.zhuduan.train.model.plan.TrainPlan;
import com.zhuduan.train.model.schedule.StringTrainSchedule;
import com.zhuduan.train.model.schedule.TrainSchedule;
import com.zhuduan.train.model.station.TrainStation;
import com.zhuduan.train.model.suggestion.Suggestion;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripsWithExactStopCalculatorTest {

    private TripsWithExactStopCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new TripsWithExactStopCalculator();
    }

    @Test
    public void testGetSuggestionWhenIllegalPlan() throws Exception {
        Suggestion suggestion = calculator.getSuggestion(new TrainPlan());
        assertEquals(ErrorCode.ILLAGEL_TRAIN_PLAN.getMessage(), suggestion.getMessage());
    }

    @Test
    public void testGetSuggestionWhenInvalidNullStop() throws Exception {
        TrainStation stationA = new TrainStation(0, "A", "a");
        TrainStation stationB = new TrainStation(1, "B", "b");
        TrainStation stationC = new TrainStation(2, "C", "c");

        StringTrainSchedule trainSchedule = mock(StringTrainSchedule.class);
        when(trainSchedule.isValid()).thenReturn(true);

        StopsTrainPlan stopsTrainPlan = new StopsTrainPlan(trainSchedule, stationA, stationB,
                null, EnumSuggestionType.POSSIBLE_TRIPS_EXACT);
        Suggestion suggestion = calculator.getSuggestion(stopsTrainPlan);
        assertEquals(ErrorCode.INVALID_TRAIN_PLAN.getMessage(), suggestion.getMessage());
    }

    @Test
    public void testGetSuggestionWhenInvalidZeroStop() throws Exception {
        TrainStation stationA = new TrainStation(0, "A", "a");
        TrainStation stationB = new TrainStation(1, "B", "b");
        TrainStation stationC = new TrainStation(2, "C", "c");

        StringTrainSchedule trainSchedule = mock(StringTrainSchedule.class);
        when(trainSchedule.isValid()).thenReturn(true);

        StopsTrainPlan stopsTrainPlan = new StopsTrainPlan(trainSchedule, stationA, stationB,
                0, EnumSuggestionType.POSSIBLE_TRIPS_EXACT);
        Suggestion suggestion = calculator.getSuggestion(stopsTrainPlan);
        assertEquals(ErrorCode.INVALID_TRAIN_PLAN.getMessage(), suggestion.getMessage());
    }

    @Test
    public void testGetSuggestionWhenInvalidSchedule() throws Exception {
        StringTrainSchedule trainSchedule = mock(StringTrainSchedule.class);
        when(trainSchedule.isValid()).thenReturn(false);

        TrainStation stationA = new TrainStation(0, "A", "a");
        TrainStation stationB = new TrainStation(1, "B", "b");
        TrainStation stationC = new TrainStation(2, "C", "c");

        StopsTrainPlan stopsTrainPlan = new StopsTrainPlan(trainSchedule, stationA, stationB,
                3, EnumSuggestionType.POSSIBLE_TRIPS_EXACT);

        Suggestion suggestion = calculator.getSuggestion(stopsTrainPlan);
        assertEquals(ErrorCode.INVALID_TRAIN_PLAN.getMessage(), suggestion.getMessage());
    }

    @Test
    public void testGetAllTripsWithStop() {
        TrainSchedule schedule = TestUtil.getTestSchedule();
        List<List<TrainStation>> trips = calculator.getAllTripsWithStop(schedule, 4,
                schedule.getStationByName("A"), schedule.getStationByName("C"));

        trips.stream().filter( trip -> (trip.size()==5 && trip.get(trip.size()-1).isSameByName("C") ) )
                .forEach( trip -> {
                    StringBuilder stringBuilder = new StringBuilder();
                    trip.forEach( station -> stringBuilder.append(station.getName()).append("->") );
                    System.out.println(stringBuilder.toString());
        });
    }

    @Test
    public void testGetTrips() {
        TrainSchedule schedule = TestUtil.getTestSchedule();
        Integer tripNum = calculator.getTrips(schedule, 4,
                schedule.getStationByName("A"), schedule.getStationByName("C"));
        assertEquals(3, tripNum.intValue());

    }
}