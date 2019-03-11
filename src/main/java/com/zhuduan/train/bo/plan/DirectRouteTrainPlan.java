package com.zhuduan.train.bo.plan;

import com.zhuduan.train.constant.EnumSuggestionType;
import com.zhuduan.train.bo.schedule.TrainSchedule;
import com.zhuduan.train.bo.station.TrainStation;

import java.util.List;

/**
 * The plan contains tour route
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class DirectRouteTrainPlan extends TrainPlan{
    
    private List<TrainStation> route;

    public DirectRouteTrainPlan() {
    }

    public DirectRouteTrainPlan(TrainSchedule trainSchedule, TrainStation startStation, TrainStation endStation,
                                List<TrainStation> route, EnumSuggestionType suggestionType) {
        super(trainSchedule, startStation, endStation, suggestionType);
        this.route = route;
    }

    @Override
    public Boolean isValid() {
        if (route==null || route.size()<=1){
            return false;
        }
        return super.isValid();
    }

    public List<TrainStation> getRoute() {
        return route;
    }

    public void setRoute(List<TrainStation> route) {
        this.route = route;
    }
}
