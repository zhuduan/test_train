package com.zhuduan.train.bo.schedule;

import com.zhuduan.train.constant.EnumIOType;
import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.exception.TypeException;

/**
 * factory to choose which method to generate the schedule
 *
 * @author Haifeng.Zhu
 * created at 3/10/19
 */
public class ScheduleFactory {

    /*** 
     * generate the schedule according to the input type
     * 
     * @param ioType
     * @param value
     * @return
     * @throws Exception
     */
    public static TrainSchedule generateTrainSchedule(EnumIOType ioType, String value) throws Exception{
        switch (ioType){
            case ARG_STRING:
                return new StringTrainSchedule(value);
            case FILE:
            default:
                throw new TypeException(ErrorCode.NOT_SUPPORT_TYPE);
        }
    }
}
