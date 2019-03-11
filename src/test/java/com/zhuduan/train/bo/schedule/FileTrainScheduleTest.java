package com.zhuduan.train.bo.schedule;

import com.zhuduan.train.constant.DefaultSetting;
import com.zhuduan.train.exception.DataException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * UT
 *
 * @author Haifeng.Zhu
 * created at 3/11/19
 */
public class FileTrainScheduleTest {
    @Test
    public void testConstructor() throws DataException, IOException {
        String fileName = "input.txt";
        FileTrainSchedule trainSchedule = new FileTrainSchedule(fileName);
        assertEquals(5, trainSchedule.getLengthBetween("A", "B").intValue());
        assertEquals(DefaultSetting.UNREACHABLE.intValue(), trainSchedule.getLengthBetween("B", "A").intValue());
        assertEquals(DefaultSetting.UNREACHABLE.intValue(), trainSchedule.getLengthBetween("A", "A").intValue());

        assertEquals(4, trainSchedule.getLengthBetween("B", "C").intValue());
        assertEquals(3, trainSchedule.getLengthBetween("E", "B").intValue());
        assertEquals(7, trainSchedule.getLengthBetween("A", "E").intValue());
    }
}