package com.zhuduan.train.calculator;

import com.zhuduan.train.constant.EnumSuggestionType;
import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.exception.TypeException;

public class CalculatorFactory {

    // the `stop` and `length` type may can be exacted with strategy pattern if needed
    public static Calculator getCalculator(EnumSuggestionType suggestionType) throws Exception{
        switch (suggestionType){
            case MIN_ROUTE:
                return new MinLengthRouteCalculator();
            case DIRECT_ROUTE:
                return new LengthCalculator();
            case POSSIBLE_TRIPS_MAX_STOP:
                return new TripsWithMaxStopCalculator();
            case POSSIBLE_TRIPS_EXACT_STOP:
                return new TripsWithExactStopCalculator();
            case POSSIBLE_TRIPS_MAX_LENGTH:
                return new RoutesWithMaxLengthCalculator();
            default:
                throw new TypeException(ErrorCode.NOT_SUPPORT_TYPE);
        }
    }
}
