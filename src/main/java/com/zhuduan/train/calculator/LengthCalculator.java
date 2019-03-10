package com.zhuduan.train.calculator;

import com.zhuduan.train.constant.DefaultSetting;
import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.model.plan.DirectRouteTrainPlan;
import com.zhuduan.train.model.plan.TrainPlan;
import com.zhuduan.train.model.schedule.TrainSchedule;
import com.zhuduan.train.model.station.TrainStation;
import com.zhuduan.train.model.suggestion.Suggestion;
import com.zhuduan.train.util.UtilTool;

import java.util.List;

/**
 * calculate the route length
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class LengthCalculator implements Calculator {

    @Override
    public Suggestion getSuggestion(TrainPlan trainPlan) throws Exception {
        // only dealing the suitable plan
        if (trainPlan instanceof DirectRouteTrainPlan) {
            DirectRouteTrainPlan directRouteTrainPlan = (DirectRouteTrainPlan) trainPlan;
            if (!directRouteTrainPlan.isValid()) {
                return new Suggestion(ErrorCode.INVALID_TRAIN_PLAN.getMessage());
            }

            Integer routeLength = getRouteLength(directRouteTrainPlan.getTrainSchedule(), directRouteTrainPlan.getRoute());
            if (UtilTool.isEqualInteger(routeLength, DefaultSetting.UNREACHABLE)) {
                return new Suggestion(ErrorCode.NO_SUCH_ROUTE.getMessage());
            }

            return new Suggestion(String.valueOf(routeLength));
        }
        return new Suggestion(ErrorCode.ILLAGEL_TRAIN_PLAN.getMessage());
    }

    private Integer getRouteLength(TrainSchedule trainSchedule, List<TrainStation> route) {
        Integer routeLength = 0;
        for (int i = 0; i < (route.size() - 1); i++) {
            Integer twoStationLength = trainSchedule.getLengthBetween(route.get(i), route.get(i + 1));
            // if the route is unreachable, just return the route as unreachable
            if (UtilTool.isEqualInteger(DefaultSetting.UNREACHABLE, twoStationLength)) {
                return DefaultSetting.UNREACHABLE;
            }
            routeLength = routeLength + twoStationLength;
        }
        return routeLength;
    }
}
