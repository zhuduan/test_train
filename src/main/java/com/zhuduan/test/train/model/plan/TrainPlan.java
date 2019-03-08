package com.zhuduan.test.train.model.plan;


import com.zhuduan.test.train.constant.EnumPlanType;
import com.zhuduan.test.train.constant.EnumStation;
import com.zhuduan.test.train.model.schedule.AbstractTrainSchedule;

/**
 * the plan need to get some suggestions
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class TrainPlan {

    private EnumPlanType planType;
    private AbstractTrainSchedule trainSchedule;

    private EnumStation startStation;
    private EnumStation endStation;

    public TrainPlan() {
    }

    public EnumPlanType getPlanType() {
        return planType;
    }

    public void setPlanType(EnumPlanType planType) {
        this.planType = planType;
    }

    public AbstractTrainSchedule getTrainSchedule() {
        return trainSchedule;
    }

    public void setTrainSchedule(AbstractTrainSchedule trainSchedule) {
        this.trainSchedule = trainSchedule;
    }

    public EnumStation getStartStation() {
        return startStation;
    }

    public void setStartStation(EnumStation startStation) {
        this.startStation = startStation;
    }

    public EnumStation getEndStation() {
        return endStation;
    }

    public void setEndStation(EnumStation endStation) {
        this.endStation = endStation;
    }
}
