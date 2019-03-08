package com.zhuduan.train.model.plan;

import com.zhuduan.train.constant.EnumStation;

import java.util.List;

/**
 * The plan contains tour route
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class DirectRouteTrainPlan extends TrainPlan{
    
    private List<EnumStation> route;

    @Override
    public Boolean isValid() {
        if (route==null || route.size()<=1){
            return false;
        }
        return super.isValid();
    }

    public List<EnumStation> getRoute() {
        return route;
    }

    public void setRoute(List<EnumStation> route) {
        this.route = route;
    }
}
