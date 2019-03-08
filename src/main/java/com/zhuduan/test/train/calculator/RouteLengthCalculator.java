package com.zhuduan.test.train.calculator;

import com.zhuduan.test.train.constant.ErrorCode;
import com.zhuduan.test.train.model.plan.DirectRouteTrainPlan;
import com.zhuduan.test.train.model.plan.TrainPlan;

/**
 * purpose of this class
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class RouteLengthCalculator implements Calculator {
    
    @Override
    public String getConclusion(TrainPlan trainPlan) {
        if (trainPlan instanceof DirectRouteTrainPlan){
            DirectRouteTrainPlan directRouteTrainPlan = (DirectRouteTrainPlan) trainPlan;
            
        }
        return ErrorCode.ILLAGEL_TRAIN_PLAN.getMessage();
    }
}
