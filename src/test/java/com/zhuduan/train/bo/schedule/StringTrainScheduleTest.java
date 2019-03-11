package com.zhuduan.train.bo.schedule;

import com.zhuduan.train.constant.DefaultSetting;
import com.zhuduan.train.exception.DataException;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringTrainScheduleTest {

    @Test
    public void testConstructor() throws DataException {
        String route = "Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        StringTrainSchedule trainSchedule = new StringTrainSchedule(route);
        assertEquals(5, trainSchedule.getLengthBetween("A", "B").intValue());
        assertEquals(DefaultSetting.UNREACHABLE.intValue(), trainSchedule.getLengthBetween("B", "A").intValue());
        assertEquals(DefaultSetting.UNREACHABLE.intValue(), trainSchedule.getLengthBetween("A", "A").intValue());

        assertEquals(4, trainSchedule.getLengthBetween("B", "C").intValue());
        assertEquals(3, trainSchedule.getLengthBetween("E", "B").intValue());
        assertEquals(7, trainSchedule.getLengthBetween("A", "E").intValue());
    }

    @Test
    public void testConstructorWhenNoPrefix() throws DataException {
        String route = " AB513, BC4, CD8, DC8, DE6, AD51, CE2, EB3, AE78";
        StringTrainSchedule trainSchedule = new StringTrainSchedule(route);
        assertEquals(513, trainSchedule.getLengthBetween("A", "B").intValue());
        assertEquals(DefaultSetting.UNREACHABLE.intValue(), trainSchedule.getLengthBetween("B", "A").intValue());
        assertEquals(DefaultSetting.UNREACHABLE.intValue(), trainSchedule.getLengthBetween("A", "A").intValue());

        assertEquals(4, trainSchedule.getLengthBetween("B", "C").intValue());
        assertEquals(51, trainSchedule.getLengthBetween("A", "D").intValue());
        assertEquals(3, trainSchedule.getLengthBetween("E", "B").intValue());
        assertEquals(78, trainSchedule.getLengthBetween("A", "E").intValue());
    }

    @Test
    public void testConstructorWhenEndWithComa() throws DataException {
        String route = " AB513, BC4, CD8, DC8, DE6, AD51, CE2, EB3, AE78,";
        StringTrainSchedule trainSchedule = new StringTrainSchedule(route);
        assertEquals(513, trainSchedule.getLengthBetween("A", "B").intValue());
        assertEquals(DefaultSetting.UNREACHABLE.intValue(), trainSchedule.getLengthBetween("B", "A").intValue());
        assertEquals(DefaultSetting.UNREACHABLE.intValue(), trainSchedule.getLengthBetween("A", "A").intValue());

        assertEquals(4, trainSchedule.getLengthBetween("B", "C").intValue());
        assertEquals(51, trainSchedule.getLengthBetween("A", "D").intValue());
        assertEquals(3, trainSchedule.getLengthBetween("E", "B").intValue());
        assertEquals(78, trainSchedule.getLengthBetween("A", "E").intValue());
    }

    @Test(expected = NumberFormatException.class)
    public void testConstructorWhenMultiChars() throws DataException {
        String route = " ABC513, BC4, CD8, DC8, DE6, AD51, CE2, EB3, AE78,";
        StringTrainSchedule trainSchedule = new StringTrainSchedule(route);
        trainSchedule.getLengthBetween("A", "B");
    }

    @Test(expected = NumberFormatException.class)
    public void testConstructorWhenNonInt() throws DataException {
        String route = " ABC513c, BC4, CD8, DC8, DE6, AD51, CE2, EB3, AE78,";
        StringTrainSchedule trainSchedule = new StringTrainSchedule(route);
        trainSchedule.getLengthBetween("A", "B");
    }

    @Test(expected = DataException.class)
    public void testConstructorWhenEmptyString() throws DataException {
        String route = "";
        StringTrainSchedule trainSchedule = new StringTrainSchedule(route);
        trainSchedule.getLengthBetween("A", "B");
    }
}