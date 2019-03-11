package com.zhuduan.train.bo.schedule;

import com.zhuduan.train.constant.EnumIOType;
import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.exception.ParamException;
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
     *      notice: default will load `input.txt` as the data file, and using file generator
     * 
     * @param args
     * @return
     * @throws Exception
     */
    public static TrainSchedule generateTrainSchedule(String[] args) throws Exception{
        EnumIOType ioType = EnumIOType.FILE;
        String value = "input.txt";
        if (args!=null && args.length>=2){
            ioType = EnumIOType.getIOType(args[0]);
            value = args[1];
        }
        
        switch (ioType){
            case ARG_STRING:
                return new StringTrainSchedule(value);
            case FILE:
                return new FileTrainSchedule(value);
            default:
                throw new TypeException(ErrorCode.NOT_SUPPORT_TYPE);
        }
    }
}
