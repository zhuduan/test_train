package com.zhuduan.train.calculator;

import com.zhuduan.train.TestUtil;
import com.zhuduan.train.constant.EnumSuggestionType;
import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.bo.plan.StopsTrainPlan;
import com.zhuduan.train.bo.plan.TrainPlan;
import com.zhuduan.train.bo.schedule.StringTrainSchedule;
import com.zhuduan.train.bo.schedule.TrainSchedule;
import com.zhuduan.train.bo.station.TrainStation;
import com.zhuduan.train.bo.suggestion.Suggestion;
import org.junit.Before;
import org.junit.Test;

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
                null, EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
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
                0, EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
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
                3, EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);

        Suggestion suggestion = calculator.getSuggestion(stopsTrainPlan);
        assertEquals(ErrorCode.INVALID_TRAIN_PLAN.getMessage(), suggestion.getMessage());
    }

    @Test
    public void testGetAllTripsWithStop() throws Exception{
        TrainSchedule schedule = TestUtil.getTestSchedule();
        
        StopsTrainPlan trainPlan = new StopsTrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("C"), 1, EnumSuggestionType.POSSIBLE_TRIPS_MAX_LENGTH);
        List<List<TrainStation>> trips = calculator.getAllTripsWithStop(trainPlan);
        assertEquals(3, trips.size());

        trainPlan = new StopsTrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("C"), 2, EnumSuggestionType.POSSIBLE_TRIPS_MAX_LENGTH);
        trips = calculator.getAllTripsWithStop(trainPlan);
        assertEquals(7, trips.size());

        trainPlan = new StopsTrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("C"), 3, EnumSuggestionType.POSSIBLE_TRIPS_MAX_LENGTH);
        trips = calculator.getAllTripsWithStop(trainPlan);
        assertEquals(13, trips.size());
        assertEquals(schedule.getStationByName("A"), trips.get(trips.size()-1).get(0));
        assertEquals(schedule.getStationByName("E"), trips.get(trips.size()-1).get(1));
        assertEquals(schedule.getStationByName("B"), trips.get(trips.size()-1).get(2));
        assertEquals(schedule.getStationByName("C"), trips.get(trips.size()-1).get(3));
    }

    @Test
    public void testGetTrips() throws Exception {
        TrainSchedule schedule = TestUtil.getTestSchedule();
        
        StopsTrainPlan trainPlan = new StopsTrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("C"), 4, EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
        Integer tripNum = calculator.getTrips(trainPlan);
        assertEquals(3, tripNum.intValue());

        trainPlan = new StopsTrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("C"), 3, EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
        tripNum = calculator.getTrips(trainPlan);
        assertEquals(1, tripNum.intValue());

        trainPlan = new StopsTrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("D"), 3, EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
        tripNum = calculator.getTrips(trainPlan);
        assertEquals(2, tripNum.intValue());
    }

    @Test
    public void testGetSuggestion() throws Exception{
        TrainSchedule schedule = TestUtil.getTestSchedule();

        StopsTrainPlan trainPlan = new StopsTrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("C"), 4, EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
        assertEquals("3", calculator.getSuggestion(trainPlan).getMessage());
    }
}