package com.zhuduan.train.bo.plan;

import com.zhuduan.train.TestUtil;
import com.zhuduan.train.bo.schedule.TrainSchedule;
import com.zhuduan.train.bo.station.TrainStation;
import com.zhuduan.train.constant.EnumSuggestionType;
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
public class DirectRouteTrainPlanTest {

    @Test
    public void isValid() throws Exception {
        TrainSchedule schedule = TestUtil.getTestSchedule();
        DirectRouteTrainPlan plan = new DirectRouteTrainPlan(schedule, schedule.getStationByName("A"),
                schedule.getStationByName("B"), new ArrayList<>(), EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
        assertFalse(plan.isValid());

        plan = new DirectRouteTrainPlan(schedule, schedule.getStationByName("A"),
                schedule.getStationByName("B"), null, EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
        assertFalse(plan.isValid());

        List<TrainStation> stationList = Arrays.asList(schedule.getStationByName("A"));
        plan = new DirectRouteTrainPlan(schedule, schedule.getStationByName("A"),
                schedule.getStationByName("B"), stationList, EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
        assertFalse(plan.isValid());

        stationList = Arrays.asList(schedule.getStationByName("A"), schedule.getStationByName("B"));
        plan = new DirectRouteTrainPlan(schedule, schedule.getStationByName("A"),
                schedule.getStationByName("B"), stationList, EnumSuggestionType.POSSIBLE_TRIPS_EXACT_STOP);
        assertTrue(plan.isValid());
    }
}