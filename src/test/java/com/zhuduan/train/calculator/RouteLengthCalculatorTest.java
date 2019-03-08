package com.zhuduan.train.calculator;

import com.zhuduan.train.constant.DefaultSetting;
import com.zhuduan.train.constant.EnumStation;
import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.model.plan.DirectRouteTrainPlan;
import com.zhuduan.train.model.plan.TrainPlan;
import com.zhuduan.train.model.schedule.StringTrainSchedule;
import com.zhuduan.train.model.suggestion.Suggestion;
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
 * UT for RouteLengthCalculator
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class RouteLengthCalculatorTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    private RouteLengthCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new RouteLengthCalculator();
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
        directRouteTrainPlan.setRoute(Arrays.asList(EnumStation.A));
        suggestion = calculator.getSuggestion(directRouteTrainPlan);
        assertEquals(ErrorCode.INVALID_TRAIN_PLAN.getMessage(), suggestion.getMessage());
    }

    @Test
    public void testGetSuggestionWhenInvalidSchedule() throws Exception {
        StringTrainSchedule trainSchedule = mock(StringTrainSchedule.class);
        when(trainSchedule.isValid()).thenReturn(false);

        DirectRouteTrainPlan directRouteTrainPlan = new DirectRouteTrainPlan();
        directRouteTrainPlan.setTrainSchedule(trainSchedule);
        directRouteTrainPlan.setRoute(Arrays.asList(EnumStation.A, EnumStation.B, EnumStation.C));

        Suggestion suggestion = calculator.getSuggestion(directRouteTrainPlan);
        assertEquals(ErrorCode.INVALID_TRAIN_PLAN.getMessage(), suggestion.getMessage());
    }

    @Test
    public void testGetSuggestionWhenNoRoute() throws Exception {
        StringTrainSchedule trainSchedule = mock(StringTrainSchedule.class);
        when(trainSchedule.isValid()).thenReturn(true);
        when(trainSchedule.getLengthBetween(EnumStation.A, EnumStation.B)).thenReturn(1);
        when(trainSchedule.getLengthBetween(EnumStation.B, EnumStation.C)).thenReturn(DefaultSetting.UNREACHABLE);

        DirectRouteTrainPlan directRouteTrainPlan = new DirectRouteTrainPlan();
        directRouteTrainPlan.setTrainSchedule(trainSchedule);
        directRouteTrainPlan.setRoute(Arrays.asList(EnumStation.A, EnumStation.B, EnumStation.C));
        directRouteTrainPlan.setStartStation(EnumStation.A);
        directRouteTrainPlan.setEndStation(EnumStation.C);

        Suggestion suggestion = calculator.getSuggestion(directRouteTrainPlan);
        assertEquals(ErrorCode.NO_SUCH_ROUTE.getMessage(), suggestion.getMessage());
    }

    @Test
    public void testGetSuggestion() throws Exception {
        StringTrainSchedule trainSchedule = mock(StringTrainSchedule.class);
        when(trainSchedule.isValid()).thenReturn(true);
        when(trainSchedule.getLengthBetween(EnumStation.A, EnumStation.B)).thenReturn(1);
        when(trainSchedule.getLengthBetween(EnumStation.B, EnumStation.C)).thenReturn(2);

        DirectRouteTrainPlan directRouteTrainPlan = new DirectRouteTrainPlan();
        directRouteTrainPlan.setTrainSchedule(trainSchedule);
        directRouteTrainPlan.setRoute(Arrays.asList(EnumStation.A, EnumStation.B, EnumStation.C));
        directRouteTrainPlan.setStartStation(EnumStation.A);
        directRouteTrainPlan.setEndStation(EnumStation.C);

        Suggestion suggestion = calculator.getSuggestion(directRouteTrainPlan);
        assertEquals(String.valueOf(3), suggestion.getMessage());
    }
}