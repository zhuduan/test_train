package com.zhuduan.test.train.model.suggestion;

import com.zhuduan.test.train.calculator.Calculator;
import com.zhuduan.test.train.constant.EnumPlanType;
import com.zhuduan.test.train.model.plan.TrainPlan;

import java.util.List;

/**
 * the suggestion of the plan
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class Suggestion {
    
    private TrainPlan trainPlan;
    private Calculator calculator;
    private String conclusion;
    
    private void generateConclusion(){
        conclusion = calculator.getConclusion(trainPlan);
    }
}
