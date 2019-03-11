package com.zhuduan.train.calculator;

import com.zhuduan.train.bo.plan.TrainPlan;
import com.zhuduan.train.bo.station.TrainStation;
import com.zhuduan.train.bo.suggestion.Suggestion;
import com.zhuduan.train.constant.DefaultSetting;
import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.util.UtilTool;

import java.util.Arrays;
import java.util.List;

import static com.zhuduan.train.constant.DefaultSetting.UNREACHABLE;

public class MinLengthRouteCalculator implements Calculator {

    @Override
    public Suggestion getSuggestion(TrainPlan trainPlan) throws Exception {
        if (!trainPlan.isValid()) {
            return new Suggestion(ErrorCode.INVALID_TRAIN_PLAN.getMessage());
        }

        Integer shortestLength = getShortestLength(trainPlan);
        if (UtilTool.isEqualInteger(shortestLength, UNREACHABLE)){
            return new Suggestion(ErrorCode.NO_SUCH_ROUTE.getMessage());
        }
        return new Suggestion(String.valueOf(shortestLength));
    }

    // use the floyd algorithm to calculate the result
    // may be can exact with strategy pattern ( e.g. Dijkstra method)
    private Integer getShortestLength(TrainPlan trainPlan) {
        Integer[][] matrix = UtilTool.cloneIntMatrix(trainPlan.getTrainSchedule().getAdjacentMatrix());
        Integer[][] path = new Integer[matrix.length][matrix.length];
        UtilTool.fillIntMatrix(path, UNREACHABLE);
        floyd(matrix, path);

        return matrix[trainPlan.getStartStation().getIndex()][trainPlan.getEndStation().getIndex()];
    }

    // floyd algorithm to calculate the shortest route : o(n^3)
    private void floyd(final Integer[][] adjacentMatrix, final Integer[][] path) {
        int matrixSize = adjacentMatrix.length;
        for (int k = 0; k < matrixSize; k++) {
            for (int i = 0; i < matrixSize; i++) {
                for (int j = 0; j < matrixSize; j++) {
                    // as we use the UNREACHABLE as the biggest, should special dealing here
                    if (adjacentMatrix[i][k]==UNREACHABLE || adjacentMatrix[k][j]==UNREACHABLE) {
                        continue;
                    }
                    else if (adjacentMatrix[i][j]==UNREACHABLE){
                        adjacentMatrix[i][j] = adjacentMatrix[i][k] + adjacentMatrix[k][j];
                        path[i][j] = k;
                        continue;
                    }
                    else if(adjacentMatrix[i][j] > (adjacentMatrix[i][k] + adjacentMatrix[k][j])) {
                        adjacentMatrix[i][j] = adjacentMatrix[i][k] + adjacentMatrix[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }
    }
}
