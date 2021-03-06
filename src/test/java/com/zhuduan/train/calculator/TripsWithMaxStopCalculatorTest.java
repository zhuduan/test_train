package com.zhuduan.train.calculator;

import com.zhuduan.train.TestUtil;
import com.zhuduan.train.constant.EnumSuggestionType;
import com.zhuduan.train.bo.plan.StopsTrainPlan;
import com.zhuduan.train.bo.schedule.TrainSchedule;
import com.zhuduan.train.exception.DataException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * UT
 *
 * @author Haifeng.Zhu
 * created at 3/10/19
 */
public class TripsWithMaxStopCalculatorTest {

    private TripsWithMaxStopCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new TripsWithMaxStopCalculator();
    }

    @Test
    public void getTrips() throws DataException {
        TrainSchedule schedule = TestUtil.getTestSchedule();

        StopsTrainPlan trainPlan = new StopsTrainPlan(schedule, schedule.getStationByName("C"), schedule.getStationByName("C"), 3, EnumSuggestionType.POSSIBLE_TRIPS_MAX_STOP);
        Integer tripNum = calculator.getTrips(trainPlan);
        assertEquals(2, tripNum.intValue());

        trainPlan = new StopsTrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("C"), 3, EnumSuggestionType.POSSIBLE_TRIPS_MAX_STOP);
        tripNum = calculator.getTrips(trainPlan);
        assertEquals(3, tripNum.intValue());

        trainPlan = new StopsTrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("B"), 3, EnumSuggestionType.POSSIBLE_TRIPS_MAX_STOP);
        tripNum = calculator.getTrips(trainPlan);
        assertEquals(3, tripNum.intValue());
    }

    @Test
    public void testGetSuggestion() throws Exception {
        TrainSchedule schedule = TestUtil.getTestSchedule();

        StopsTrainPlan trainPlan = new StopsTrainPlan(schedule, schedule.getStationByName("C"), schedule.getStationByName("C"), 3, EnumSuggestionType.POSSIBLE_TRIPS_MAX_STOP);
        assertEquals("2", calculator.getSuggestion(trainPlan).getMessage());
    }
}