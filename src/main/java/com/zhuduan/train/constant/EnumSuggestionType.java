package com.zhuduan.train.constant;

/**
 * the type of suggestion
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public enum EnumSuggestionType {

    DIRECT_ROUTE(1, "directRoute", "get the length due to certain route"),
    MIN_ROUTE(2, "minRoute", "get the min length route between two station"),
    POSSIBLE_TRIPS_MAX_LENGTH(3, "possibleTrips", "the possible routes between two station within length"),
    POSSIBLE_TRIPS_EXACT_STOP(4, "possibleTripsExactStop", "the possible trips between two station with exact stops"),
    POSSIBLE_TRIPS_MAX_STOP(5, "possibleTripsMaxStop", "the possible trips between two station with max stops");

    private Integer id;
    private String name;
    private String description;

    EnumSuggestionType(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
