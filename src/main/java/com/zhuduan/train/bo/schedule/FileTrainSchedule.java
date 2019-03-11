package com.zhuduan.train.bo.schedule;

import com.zhuduan.train.exception.DataException;

/**
 * get the input data from file
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class FileTrainSchedule extends TrainSchedule {
    
    private String fileUrl;

    public FileTrainSchedule(String fileUrl) throws DataException {
        this.fileUrl = fileUrl;
        generateScheduleInfo();
    }

    @Override
    protected void generateScheduleInfo() {
        // todo: 
    }
}
