package com.zhuduan.train.calculator;

import com.zhuduan.train.TestUtil;
import com.zhuduan.train.bo.plan.LengthTrainPlan;
import com.zhuduan.train.bo.plan.TrainPlan;
import com.zhuduan.train.bo.schedule.StringTrainSchedule;
import com.zhuduan.train.bo.schedule.TrainSchedule;
import com.zhuduan.train.bo.station.TrainStation;
import com.zhuduan.train.bo.suggestion.Suggestion;
import com.zhuduan.train.constant.EnumSuggestionType;
import com.zhuduan.train.constant.ErrorCode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * UT
 *
 * @author Haifeng.Zhu
 * created at 3/10/19
 */
public class RoutesWithMaxLengthCalculatorTest {

    private RoutesWithMaxLengthCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new RoutesWithMaxLengthCalculator();
    }

    @Test
    public void testGetSuggestionWhenIllegalPlan() throws Exception {
        Suggestion suggestion = calculator.getSuggestion(new TrainPlan());
        assertEquals(ErrorCode.ILLAGEL_TRAIN_PLAN.getMessage(), suggestion.getMessage());
    }

    @Test
    public void testGetSuggestionWhenInvalidNullLength() throws Exception {
        TrainStation stationA = new TrainStation(0, "A", "a");
        TrainStation stationB = new TrainStation(1, "B", "b");
        TrainStation stationC = new TrainStation(2, "C", "c");

        StringTrainSchedule trainSchedule = mock(StringTrainSchedule.class);
        when(trainSchedule.isValid()).thenReturn(true);

        LengthTrainPlan lengthTrainPlan = new LengthTrainPlan(trainSchedule, stationA, stationB,
                null, EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
        Suggestion suggestion = calculator.getSuggestion(lengthTrainPlan);
        assertEquals(ErrorCode.INVALID_TRAIN_PLAN.getMessage(), suggestion.getMessage());
    }

    @Test
    public void testGetSuggestionWhenInvalidZeroLength() throws Exception {
        TrainStation stationA = new TrainStation(0, "A", "a");
        TrainStation stationB = new TrainStation(1, "B", "b");
        TrainStation stationC = new TrainStation(2, "C", "c");

        StringTrainSchedule trainSchedule = mock(StringTrainSchedule.class);
        when(trainSchedule.isValid()).thenReturn(true);

        LengthTrainPlan lengthTrainPlan = new LengthTrainPlan(trainSchedule, stationA, stationB,
                0, EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
        Suggestion suggestion = calculator.getSuggestion(lengthTrainPlan);
        assertEquals(ErrorCode.INVALID_TRAIN_PLAN.getMessage(), suggestion.getMessage());
    }

    @Test
    public void testGetSuggestionWhenInvalidSchedule() throws Exception {
        StringTrainSchedule trainSchedule = mock(StringTrainSchedule.class);
        when(trainSchedule.isValid()).thenReturn(false);

        TrainStation stationA = new TrainStation(0, "A", "a");
        TrainStation stationB = new TrainStation(1, "B", "b");
        TrainStation stationC = new TrainStation(2, "C", "c");

        LengthTrainPlan lengthTrainPlan = new LengthTrainPlan(trainSchedule, stationA, stationB,
                3, EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);

        Suggestion suggestion = calculator.getSuggestion(lengthTrainPlan);
        assertEquals(ErrorCode.INVALID_TRAIN_PLAN.getMessage(), suggestion.getMessage());
    }

    @Test
    public void testGetSuggestion() throws Exception{
        TrainSchedule schedule = TestUtil.getTestSchedule();
        
        LengthTrainPlan lengthTrainPlan = new LengthTrainPlan(schedule, schedule.getStationByName("C"), schedule.getStationByName("C"), 
                30, EnumSuggestionType.POSSIBLE_TRIPS_MAX_LENGTH);
        assertEquals("7", calculator.getSuggestion(lengthTrainPlan).getMessage());

        lengthTrainPlan = new LengthTrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("C"), 
                15, EnumSuggestionType.POSSIBLE_TRIPS_MAX_LENGTH);
        assertEquals("3", calculator.getSuggestion(lengthTrainPlan).getMessage());

        lengthTrainPlan = new LengthTrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("C"),
                14, EnumSuggestionType.POSSIBLE_TRIPS_MAX_LENGTH);
        assertEquals("2", calculator.getSuggestion(lengthTrainPlan).getMessage());
    }
}