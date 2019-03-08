package com.zhuduan.test.train.model.plan;

import com.zhuduan.test.train.constant.EnumStation;

import java.util.List;

/**
 * purpose of this class
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class DirectRouteTrainPlan extends TrainPlan{
    
    private List<EnumStation> route;
    
    public List<EnumStation> getRoute() {
        return route;
    }

    public void setRoute(List<EnumStation> route) {
        this.route = route;
    }
}
