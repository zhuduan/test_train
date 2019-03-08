package com.zhuduan.test.train.calculator;

import com.zhuduan.test.train.model.plan.TrainPlan;

/**
 * the calculator to get the suggestion from the Train Plan
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public interface Calculator {
    
    String getConclusion(TrainPlan trainPlan);
}
