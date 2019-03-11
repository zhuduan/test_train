package com.zhuduan.train;

import com.zhuduan.train.bo.plan.DirectRouteTrainPlan;
import com.zhuduan.train.bo.plan.TrainPlan;
import com.zhuduan.train.bo.schedule.ScheduleFactory;
import com.zhuduan.train.bo.schedule.TrainSchedule;
import com.zhuduan.train.bo.suggestion.Suggestion;
import com.zhuduan.train.calculator.Calculator;
import com.zhuduan.train.calculator.CalculatorFactory;
import com.zhuduan.train.constant.EnumIOType;
import com.zhuduan.train.constant.EnumSuggestionType;
import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.exception.ParamException;
import com.zhuduan.train.view.View;
import com.zhuduan.train.view.ViewFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) throws Exception{
        // 1. read the args, and generate schedule
        if (args ==null || args.length<2){
            throw new ParamException(ErrorCode.INVALID_PARAMS);
        }
        TrainSchedule schedule = ScheduleFactory.generateTrainSchedule(EnumIOType.getIOType(args[0]), args[1]);
        
        // 2. generate plan as request
        List<TrainPlan> planList = generateTrainPlan(schedule);
        
        // 3. through calculator to get suggestion
        List<Suggestion> suggestionList = new ArrayList<>();
        for (TrainPlan plan : planList){
            Calculator calculator = CalculatorFactory.getCalculator(plan.getSuggestionType());
            Suggestion suggestion = calculator.getSuggestion(plan);
            suggestion.setPlanId(plan.getId());
            suggestionList.add(suggestion);
        }
        
        // 4. use view to render the result
        View view = ViewFactory.getView(EnumIOType.CONSOLE_STRING);
        suggestionList.forEach( suggestion -> view.render(suggestion));
    }
    
    private static List<TrainPlan> generateTrainPlan(TrainSchedule schedule){
        List<TrainPlan> planList = new ArrayList<>();

        // 1. ABC
        TrainPlan plan = new DirectRouteTrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("C"),
                Arrays.asList(schedule.getStationByName("A"), schedule.getStationByName("B"), schedule.getStationByName("C"))
                , EnumSuggestionType.DIRECT_ROUTE);
        plan.setId(1);
        planList.add(plan);
        
        // 2. AD
        plan = new DirectRouteTrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("D"),
                Arrays.asList(schedule.getStationByName("A"), schedule.getStationByName("D")), EnumSuggestionType.DIRECT_ROUTE);
        plan.setId(2);
        planList.add(plan);
        
        // 3. ADC
        plan = new DirectRouteTrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("C"),
                Arrays.asList(schedule.getStationByName("A"), schedule.getStationByName("D"), schedule.getStationByName("C"))
                , EnumSuggestionType.DIRECT_ROUTE);
        plan.setId(3);
        planList.add(plan);
        
        // 4. AEBCD
        plan = new DirectRouteTrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("D"),
                Arrays.asList(schedule.getStationByName("A"), schedule.getStationByName("E"), schedule.getStationByName("B"),
                        schedule.getStationByName("C"), schedule.getStationByName("D"))
                , EnumSuggestionType.DIRECT_ROUTE);
        plan.setId(4);
        planList.add(plan);
        
        // 5. AED
        plan = new DirectRouteTrainPlan(schedule, schedule.getStationByName("A"), schedule.getStationByName("D"),
                Arrays.asList(schedule.getStationByName("A"), schedule.getStationByName("E"), schedule.getStationByName("D"))
                , EnumSuggestionType.DIRECT_ROUTE);
        plan.setId(5);
        planList.add(plan);
        
        // 6. 
        
        // 7.
        
        // 8.
        
        // 9.
        
        //10.
        
        
        return planList;
    }
}
