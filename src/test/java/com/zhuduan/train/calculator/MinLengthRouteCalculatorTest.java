package com.zhuduan.train.calculator;

import com.zhuduan.train.TestUtil;
import com.zhuduan.train.bo.plan.TrainPlan;
import com.zhuduan.train.bo.schedule.TrainSchedule;
import com.zhuduan.train.bo.suggestion.Suggestion;
import com.zhuduan.train.constant.EnumSuggestionType;
import com.zhuduan.train.constant.ErrorCode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * purpose of this class
 *
 * @author Haifeng.Zhu
 * created at 3/11/19
 */
public class MinLengthRouteCalculatorTest {

    private MinLengthRouteCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new MinLengthRouteCalculator();
    }

    @Test
    public void testGetSuggestionWhenInvalidPlan() throws Exception {
        Suggestion suggestion = calculator.getSuggestion(new TrainPlan());
        assertEquals(ErrorCode.INVALID_TRAIN_PLAN.getMessage(), suggestion.getMessage());
    }

    @Test
    public void getSuggestion() throws Exception {
        TrainSchedule schedule = TestUtil.getTestSchedule();

        TrainPlan trainPlan = new TrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("C"), EnumSuggestionType.MIN_ROUTE);
        assertEquals("9", calculator.getSuggestion(trainPlan).getMessage());

        trainPlan = new TrainPlan(schedule, schedule.getStationByName("B"), schedule.getStationByName("B"), EnumSuggestionType.MIN_ROUTE);
        assertEquals("9", calculator.getSuggestion(trainPlan).getMessage());

        trainPlan = new TrainPlan(schedule, schedule.getStationByName("C"), schedule.getStationByName("E"), EnumSuggestionType.MIN_ROUTE);
        assertEquals("2", calculator.getSuggestion(trainPlan).getMessage());
    }

    @Test
    public void getSuggestionWhenUnreachable() throws Exception {
        TrainSchedule schedule = TestUtil.getTestSchedule();

        TrainPlan trainPlan = new TrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("A"), EnumSuggestionType.MIN_ROUTE);
        assertEquals(ErrorCode.NO_SUCH_ROUTE.getMessage(), calculator.getSuggestion(trainPlan).getMessage());
    }
}