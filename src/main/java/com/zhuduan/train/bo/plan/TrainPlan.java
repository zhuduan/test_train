package com.zhuduan.train.bo.plan;


import com.zhuduan.train.constant.DefaultSetting;
import com.zhuduan.train.constant.EnumSuggestionType;
import com.zhuduan.train.bo.schedule.TrainSchedule;
import com.zhuduan.train.bo.station.TrainStation;
import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.exception.DataException;
import com.zhuduan.train.util.UtilTool;

import java.util.ArrayList;
import java.util.List;

/**
 * the plan need to get some suggestions
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class TrainPlan {

    private Integer id;
    private TrainSchedule trainSchedule;
    private TrainStation startStation;
    private TrainStation endStation;
    private EnumSuggestionType suggestionType;

    public TrainPlan() {
    }

    public TrainPlan(TrainSchedule trainSchedule, TrainStation startStation,
                     TrainStation endStation, EnumSuggestionType suggestionType) {
        this.trainSchedule = trainSchedule;
        this.startStation = startStation;
        this.endStation = endStation;
        this.suggestionType = suggestionType;
    }

    /***
     * check if the schedule and start/end station is valid
     *
     * @return true if data is valid, or false
     */
    public Boolean isValid() {
        if (startStation == null || endStation == null) {
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

    public TrainStation getStartStation() {
        return startStation;
    }

    public void setStartStation(TrainStation startStation) {
        this.startStation = startStation;
    }

    public TrainStation getEndStation() {
        return endStation;
    }

    public void setEndStation(TrainStation endStation) {
        this.endStation = endStation;
    }

    public EnumSuggestionType getSuggestionType() {
        return suggestionType;
    }

    public void setSuggestionType(EnumSuggestionType suggestionType) {
        this.suggestionType = suggestionType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
