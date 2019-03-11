package com.zhuduan.train.bo.station;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * UT
 *
 * @author Haifeng.Zhu
 * created at 3/10/19
 */
public class TrainStationTest {

    @Test
    public void isSameByName() {
        TrainStation stationA = new TrainStation(1, "A", "a");
        assertTrue(stationA.isSameByName("A"));
        assertFalse(stationA.isSameByName("B"));
    }
}