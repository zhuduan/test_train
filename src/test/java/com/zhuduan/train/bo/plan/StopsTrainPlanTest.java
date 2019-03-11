package com.zhuduan.train.bo.plan;

import com.zhuduan.train.TestUtil;
import com.zhuduan.train.bo.schedule.TrainSchedule;
import com.zhuduan.train.constant.EnumSuggestionType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * UT
 *
 * @author Haifeng.Zhu
 * created at 3/10/19
 */
public class StopsTrainPlanTest {

    @Test
    public void testIsValidWhenZeroStop() throws Exception {
        TrainSchedule schedule = TestUtil.getTestSchedule();
        StopsTrainPlan stopsTrainPlan = new StopsTrainPlan(schedule, schedule.getStationByName("A"),
                schedule.getStationByName("B"), 0, EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
        assertFalse(stopsTrainPlan.isValid());

        stopsTrainPlan = new StopsTrainPlan(schedule, schedule.getStationByName("A"),
                schedule.getStationByName("B"), null, EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
        assertFalse(stopsTrainPlan.isValid());

        stopsTrainPlan = new StopsTrainPlan(schedule, schedule.getStationByName("A"),
                schedule.getStationByName("B"), 1, EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
        assertTrue(stopsTrainPlan.isValid());
    }
}