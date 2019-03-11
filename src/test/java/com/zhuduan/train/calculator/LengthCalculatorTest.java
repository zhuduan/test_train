package com.zhuduan.train.calculator;

import com.zhuduan.train.constant.DefaultSetting;
import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.bo.plan.DirectRouteTrainPlan;
import com.zhuduan.train.bo.plan.TrainPlan;
import com.zhuduan.train.bo.schedule.StringTrainSchedule;
import com.zhuduan.train.bo.station.TrainStation;
import com.zhuduan.train.bo.suggestion.Suggestion;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * UT for LengthCalculator
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class LengthCalculatorTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    private LengthCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new LengthCalculator();
    }

    @Test
    public void testGetSuggestionWhenIllegalPlan() throws Exception {
        Suggestion suggestion = calculator.getSuggestion(new TrainPlan());
        assertEquals(ErrorCode.ILLAGEL_TRAIN_PLAN.getMessage(), suggestion.getMessage());
    }

    @Test
    public void testGetSuggestionWhenInvalidNullRoute() throws Exception {
        StringTrainSchedule trainSchedule = mock(StringTrainSchedule.class);
        when(trainSchedule.isValid()).thenReturn(true);

        DirectRouteTrainPlan directRouteTrainPlan = new DirectRouteTrainPlan();
        directRouteTrainPlan.setTrainSchedule(trainSchedule);
        directRouteTrainPlan.setRoute(null);
        Suggestion suggestion = calculator.getSuggestion(directRouteTrainPlan);
        assertEquals(ErrorCode.INVALID_TRAIN_PLAN.getMessage(), suggestion.getMessage());
    }

    @Test
    public void testGetSuggestionWhenInvalidSizeRoute() throws Exception {
        StringTrainSchedule trainSchedule = mock(StringTrainSchedule.class);
        when(trainSchedule.isValid()).thenReturn(true);

        // case : empty route list
        DirectRouteTrainPlan directRouteTrainPlan = new DirectRouteTrainPlan();
        directRouteTrainPlan.setTrainSchedule(trainSchedule);
        directRouteTrainPlan.setRoute(new ArrayList<>());
        Suggestion suggestion = calculator.getSuggestion(directRouteTrainPlan);
        assertEquals(ErrorCode.INVALID_TRAIN_PLAN.getMessage(), suggestion.getMessage());

        // case : only one element route list
        directRouteTrainPlan.setRoute(Arrays.asList(new TrainStation(0, "A", "a")));
        suggestion = calculator.getSuggestion(directRouteTrainPlan);
        assertEquals(ErrorCode.INVALID_TRAIN_PLAN.getMessage(), suggestion.getMessage());
    }

    @Test
    public void testGetSuggestionWhenInvalidSchedule() throws Exception {
        StringTrainSchedule trainSchedule = mock(StringTrainSchedule.class);
        when(trainSchedule.isValid()).thenReturn(false);

        TrainStation stationA = new TrainStation(0, "A", "a");
        TrainStation stationB = new TrainStation(1, "B", "b");
        TrainStation stationC = new TrainStation(2, "C", "c");

        DirectRouteTrainPlan directRouteTrainPlan = new DirectRouteTrainPlan();
        directRouteTrainPlan.setTrainSchedule(trainSchedule);
        directRouteTrainPlan.setRoute(Arrays.asList(stationA, stationB, stationC));

        Suggestion suggestion = calculator.getSuggestion(directRouteTrainPlan);
        assertEquals(ErrorCode.INVALID_TRAIN_PLAN.getMessage(), suggestion.getMessage());
    }

    @Test
    public void testGetSuggestionWhenNoRoute() throws Exception {
        TrainStation stationA = new TrainStation(0, "A", "a");
        TrainStation stationB = new TrainStation(1, "B", "b");
        TrainStation stationC = new TrainStation(2, "C", "c");

        StringTrainSchedule trainSchedule = mock(StringTrainSchedule.class);
        when(trainSchedule.isValid()).thenReturn(true);
        when(trainSchedule.getLengthBetween(stationA, stationB)).thenReturn(1);
        when(trainSchedule.getLengthBetween(stationB, stationC)).thenReturn(DefaultSetting.UNREACHABLE);

        DirectRouteTrainPlan directRouteTrainPlan = new DirectRouteTrainPlan();
        directRouteTrainPlan.setTrainSchedule(trainSchedule);
        directRouteTrainPlan.setRoute(Arrays.asList(stationA, stationB, stationC));
        directRouteTrainPlan.setStartStation(stationA);
        directRouteTrainPlan.setEndStation(stationC);

        Suggestion suggestion = calculator.getSuggestion(directRouteTrainPlan);
        assertEquals(ErrorCode.NO_SUCH_ROUTE.getMessage(), suggestion.getMessage());
    }

    @Test
    public void testGetSuggestion() throws Exception {
        TrainStation stationA = new TrainStation(0, "A", "a");
        TrainStation stationB = new TrainStation(1, "B", "b");
        TrainStation stationC = new TrainStation(2, "C", "c");

        StringTrainSchedule trainSchedule = mock(StringTrainSchedule.class);
        when(trainSchedule.isValid()).thenReturn(true);
        when(trainSchedule.getLengthBetween(stationA, stationB)).thenReturn(1);
        when(trainSchedule.getLengthBetween(stationB, stationC)).thenReturn(2);

        DirectRouteTrainPlan directRouteTrainPlan = new DirectRouteTrainPlan();
        directRouteTrainPlan.setTrainSchedule(trainSchedule);
        directRouteTrainPlan.setRoute(Arrays.asList(stationA, stationB, stationC));
        directRouteTrainPlan.setStartStation(stationA);
        directRouteTrainPlan.setEndStation(stationC);

        Suggestion suggestion = calculator.getSuggestion(directRouteTrainPlan);
        assertEquals(String.valueOf(3), suggestion.getMessage());
    }
}