package com.zhuduan.train.model.plan;


import com.zhuduan.train.constant.EnumStation;
import com.zhuduan.train.model.schedule.TrainSchedule;

/**
 * the plan need to get some suggestions
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class TrainPlan {

    private TrainSchedule trainSchedule;
    private EnumStation startStation;
    private EnumStation endStation;

    public TrainPlan() {
    }

    /***
     * check if the schedule and start/end station is valid
     * 
     * @return true if data is valid, or false
     */
    public Boolean isValid(){
        if (startStation==null || endStation==null){
            return false;
        }
        return trainSchedule.isValid();
    }

    public TrainSchedule getTrainSchedule() {
        return trainSchedule;
    }

    public void setTrainSchedule(TrainSchedule trainSchedule) {
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
