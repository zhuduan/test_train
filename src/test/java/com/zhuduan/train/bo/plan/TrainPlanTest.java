package com.zhuduan.train.bo.plan;

import com.zhuduan.train.TestUtil;
import com.zhuduan.train.constant.EnumSuggestionType;
import com.zhuduan.train.bo.schedule.TrainSchedule;
import com.zhuduan.train.bo.station.TrainStation;
import org.junit.Before;
import org.junit.Test;

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
                EnumSuggestionType.POSSIBLE_TRIPS_EXACT);
    }

    @Test
    public void testIsValid() {
    }

    @Test
    public void testGetTripsWithAllStations() {
        List<List<TrainStation>> trips = trainPlan.getTripsWithAllStations();
        assertEquals(trainPlan.getTrainSchedule().getAllStations().size(),trips.size());
    }

    @Test
    public void testIncreaseTrip() {
        TrainStation start = trainPlan.getTrainSchedule().getStationByName("A");
        
        List<List<TrainStation>> trips = trainPlan.increaseTrip(trainPlan.getTripWithStation(start));   // AB, AD, AE
        List<TrainStation> trip = trips.get(1);     // AD
        assertEquals(3, trips.size());
        assertEquals(2, trip.size());
        
        trips = trainPlan.increaseTrip(trip);       // ADC, ADE
        trip = trips.get(1);                        // ADE
        assertEquals(2, trips.size());
        assertEquals(3, trip.size());

        trips = trainPlan.increaseTrip(trip);       // ADEB
        trip = trips.get(0);                        // ADEB
        assertEquals(1, trips.size());
        assertEquals(4, trip.size());

        trips = trainPlan.increaseTrip(trip);       // ADEBC
        trip = trips.get(0);                        // ADEBC
        assertEquals(1, trips.size());
        assertEquals(5, trip.size());

        trips = trainPlan.increaseTrip(trip);       // ADEBCD, ADEBCE
        trip = trips.get(0);                        // ADEBCD
        assertEquals(2, trips.size());
        assertEquals(6, trip.size());
    }
}