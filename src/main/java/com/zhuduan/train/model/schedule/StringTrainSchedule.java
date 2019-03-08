package com.zhuduan.train.model.schedule;

import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.exception.DataException;
import org.apache.commons.lang3.StringUtils;

import javax.xml.crypto.Data;

/**
 * get input file from String
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class StringTrainSchedule extends TrainSchedule{
    
    // the route data 
    // e.g. AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
    private String routeData;

    public StringTrainSchedule(String routeData) throws DataException{
        this.routeData = routeData;
    }

    @Override
    public void generateAdjacentMatrix() throws DataException {
        if (StringUtils.isEmpty(routeData)){
            throw new DataException(ErrorCode.INVALID_ROUTE_INFO);
        }
        
        // todo:
    }
}
