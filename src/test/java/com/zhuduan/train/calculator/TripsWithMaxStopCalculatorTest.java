package com.zhuduan.train.calculator;

import com.zhuduan.train.TestUtil;
import com.zhuduan.train.model.schedule.TrainSchedule;
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
    public void getTrips() {
        TrainSchedule schedule = TestUtil.getTestSchedule();

        Integer tripNum = calculator.getTrips(schedule, 3, schedule.getStationByName("C"), schedule.getStationByName("C"));
        assertEquals(2, tripNum.intValue());

        tripNum = calculator.getTrips(schedule, 3, schedule.getStationByName("A"), schedule.getStationByName("C"));
        assertEquals(3, tripNum.intValue());

        tripNum = calculator.getTrips(schedule, 3, schedule.getStationByName("A"), schedule.getStationByName("B"));
        assertEquals(3, tripNum.intValue());
    }
}