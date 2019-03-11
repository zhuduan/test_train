package com.zhuduan.train.view;

import com.zhuduan.train.bo.schedule.StringTrainSchedule;
import com.zhuduan.train.bo.schedule.TrainSchedule;
import com.zhuduan.train.constant.EnumIOType;
import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.exception.TypeException;

/**
 * Factory to decide using which method to render the value
 *
 * @author Haifeng.Zhu
 * created at 3/10/19
 */
public class ViewFactory {

    /***
     * render the value according to the output type
     * 
     * @param ioType
     * @return
     * @throws Exception
     */
    public static View getView(EnumIOType ioType) throws Exception{
        switch (ioType){
            case CONSOLE_STRING:
                return new ConsoleView();
            case ARG_STRING:
            case FILE:
            default:
                throw new TypeException(ErrorCode.NOT_SUPPORT_TYPE);
        }
    }
}
