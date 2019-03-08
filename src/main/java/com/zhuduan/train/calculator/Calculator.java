package com.zhuduan.train.calculator;

import com.zhuduan.train.model.plan.TrainPlan;
import com.zhuduan.train.model.suggestion.Suggestion;

/**
 * the calculator to get the suggestion from the Train Plan
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public interface Calculator {
    
    Suggestion getSuggestion(TrainPlan trainPlan) throws Exception;
}
