package com.zhuduan.train.bo.schedule;

import com.zhuduan.train.TestUtil;
import com.zhuduan.train.bo.station.TrainStation;
import com.zhuduan.train.exception.DataException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * UT
 *
 * @author Haifeng.Zhu
 * created at 3/10/19
 */
public class TrainScheduleTest {

    private TrainSchedule schedule;

    @Before
    public void setUp() throws Exception {
        schedule = new StringTrainSchedule(" AB5, BC4, CD8, DC8, DE6, AD51, CE2, EB3, AE7");
    }

    @Test
    public void testContainsStation() {
        assertTrue(schedule.containsStation("A"));
        assertFalse(schedule.containsStation("F"));
    }

    @Test
    public void testGetStationByName() {
        assertEquals("A", schedule.getStationByName("A").getName());
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetStationByNameWhenNonExist() {
        assertEquals("A", schedule.getStationByName("F").getName());
    }

    @Test
    public void testIsValidWhenNonMatrix() throws Exception {
        TrainSchedule schedule = new TrainSchedule() {
            @Override
            protected void generateScheduleInfo() throws DataException {
                // do nothing
            }
        };
        assertFalse(schedule.isValid());
    }

    @Test
    public void testGetLengthBetween() {
        assertEquals(5, schedule.getLengthBetween(schedule.getStationByName("A"), schedule.getStationByName("B")).intValue());
        assertEquals(5, schedule.getLengthBetween("A", "B").intValue());
        assertEquals(5, schedule.getLengthBetween(0, 1).intValue());
    }

    @Test
    public void testGetTripsWithAllStations() throws DataException {
        List<List<TrainStation>> trips = schedule.getTripsWithAllStations();
        assertEquals(schedule.getAllStations().size(), trips.size());
    }

    @Test
    public void testIncreaseTrip() throws DataException {
        TrainStation start = schedule.getStationByName("A");

        List<List<TrainStation>> trips = schedule.increaseTrip(schedule.getTripWithStation(start));   // AB, AD, AE
        List<TrainStation> trip = trips.get(1);     // AD
        assertEquals(3, trips.size());
        assertEquals(2, trip.size());

        trips = schedule.increaseTrip(trip);        // ADC, ADE
        trip = trips.get(1);                        // ADE
        assertEquals(2, trips.size());
        assertEquals(3, trip.size());

        trips = schedule.increaseTrip(trip);        // ADEB
        trip = trips.get(0);                        // ADEB
        assertEquals(1, trips.size());
        assertEquals(4, trip.size());

        trips = schedule.increaseTrip(trip);        // ADEBC
        trip = trips.get(0);                        // ADEBC
        assertEquals(1, trips.size());
        assertEquals(5, trip.size());

        trips = schedule.increaseTrip(trip);        // ADEBCD, ADEBCE
        trip = trips.get(0);                        // ADEBCD
        assertEquals(2, trips.size());
        assertEquals(6, trip.size());
    }

    @Test
    public void testGetTripLength() throws DataException {
        List<TrainStation> trip = Arrays.asList(schedule.getStationByName("A"), schedule.getStationByName("B"));
        assertEquals(5, schedule.getTripLength(trip).intValue());

        trip = Arrays.asList(schedule.getStationByName("A"));
        assertEquals(0, schedule.getTripLength(trip).intValue());

        assertEquals(0, schedule.getTripLength(new ArrayList<>()).intValue());
    }
}