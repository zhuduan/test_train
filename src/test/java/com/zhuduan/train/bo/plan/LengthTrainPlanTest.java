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
 * created at 3/11/19
 */
public class LengthTrainPlanTest {

    @Test
    public void testIsValid() throws Exception{
        TrainSchedule schedule = TestUtil.getTestSchedule();
        LengthTrainPlan plan = new LengthTrainPlan(schedule, schedule.getStationByName("A"),
                schedule.getStationByName("B"), 0, EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
        assertFalse(plan.isValid());

        plan = new LengthTrainPlan(schedule, schedule.getStationByName("A"),
                schedule.getStationByName("B"), null, EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
        assertFalse(plan.isValid());

        plan = new LengthTrainPlan(schedule, schedule.getStationByName("A"),
                schedule.getStationByName("B"), 20, EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
        assertTrue(plan.isValid());
    }
}