package com.zhuduan.train.calculator;

import com.zhuduan.train.constant.EnumSuggestionType;

public class CalculatorFactory {

    public static Calculator getCalculator(EnumSuggestionType suggestionType){
        switch (suggestionType){
            case MIN_ROUTE:
                break;
            case DIRECT_ROUTE:
                break;
            default:
                break;
        }
        return new LengthCalculator();
    }
}
