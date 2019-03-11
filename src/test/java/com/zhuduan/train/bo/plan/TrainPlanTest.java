package com.zhuduan.train.bo.plan;

import com.zhuduan.train.TestUtil;
import com.zhuduan.train.constant.EnumSuggestionType;
import com.zhuduan.train.bo.schedule.TrainSchedule;
import com.zhuduan.train.bo.station.TrainStation;
import com.zhuduan.train.exception.DataException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * UT
 *
 * @author Haifeng.Zhu
 * created at 3/10/19
 */
public class TrainPlanTest {

    private TrainPlan trainPlan;

    @Before
    public void setUp() throws Exception {
        TrainSchedule schedule = TestUtil.getTestSchedule();
        trainPlan = new TrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("C"),
                EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
    }

    @Test
    public void testIsValid() throws Exception {
        TrainSchedule schedule = TestUtil.getTestSchedule();
        TrainPlan plan = new TrainPlan(schedule, null, schedule.getStationByName("C"),
                EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
        assertFalse(plan.isValid());

        plan = new TrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("C"),
                EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
        assertTrue(plan.isValid());
    }
}