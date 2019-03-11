package com.zhuduan.train.calculator;

import com.zhuduan.train.bo.plan.TrainPlan;
import com.zhuduan.train.bo.suggestion.Suggestion;

/**
 * the calculator to get the suggestion from the Train Plan
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public interface Calculator {

    /***
     * get the suggestion needed for the plan
     *
     * @param trainPlan
     * @return
     * @throws Exception
     */
    Suggestion getSuggestion(TrainPlan trainPlan) throws Exception;
}
