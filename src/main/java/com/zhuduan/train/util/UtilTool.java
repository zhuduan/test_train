package com.zhuduan.train.util;

import com.zhuduan.train.bo.station.TrainStation;

import java.util.Arrays;
import java.util.List;

/**
 * the tools
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class UtilTool {

    /***
     * Judge two Integer is equal or not
     * use the int rather Integer to do the compare, avoid Integer cache error
     *
     * @param source
     * @param target
     * @return true if two Integer's value is same, or false if not same
     */
    public static Boolean isEqualInteger(Integer source, Integer target) {
        return source.intValue() == target.intValue();
    }

    /***
     * check the number is not a negative number or null
     *
     * @param number
     * @return true if number is not null and not less than 0
     */
    public static Boolean isNotNegative(Integer number) {
        if (number == null || number < 0) {
            return false;
        }
        return true;
    }

    /***
     *
     * @param number
     * @return
     */
    public static Boolean isPositive(Integer number) {
        if (number != null && number > 0) {
            return true;
        }
        return false;
    }

    /***
     *
     * @param trip
     * @return
     */
    public static String changeTrip2String(List<TrainStation> trip) {
        StringBuilder stringBuilder = new StringBuilder();
        trip.forEach(trainStation -> stringBuilder.append(trainStation.getName()).append("->"));
        return stringBuilder.subSequence(0, stringBuilder.lastIndexOf("->")).toString();
    }

    /***
     *
     * @param trips
     * @return
     */
    public static String changeTrips2String(List<List<TrainStation>> trips) {
        StringBuilder stringBuilder = new StringBuilder();
        trips.forEach(trip -> stringBuilder.append(changeTrip2String(trip)).append("\r\n"));
        return stringBuilder.toString();
    }

    /***
     * fill the the matrix with value
     *
     * @param matrix
     * @param value
     */
    public static void fillIntMatrix(Integer[][] matrix, Integer value) {
        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(matrix[i], value);
        }
    }

    /***
     * deep clone the int matrix
     *
     * @param sourceMatrix
     * @return
     */
    public static Integer[][] cloneIntMatrix(Integer[][] sourceMatrix) {
        assert sourceMatrix != null : "Source matrix is null!";
        assert sourceMatrix.length > 0 : "Source matrix length is 0 or negative!";

        Integer matrixSize = sourceMatrix.length;
        Integer[][] clonedMatrix = new Integer[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                clonedMatrix[i][j] = sourceMatrix[i][j];
            }
        }
        return clonedMatrix;
    }
}
