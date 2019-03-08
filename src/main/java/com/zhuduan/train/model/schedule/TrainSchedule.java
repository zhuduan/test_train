package com.zhuduan.train.model.schedule;

import com.zhuduan.train.constant.DefaultSetting;
import com.zhuduan.train.constant.EnumStation;
import com.zhuduan.train.exception.DataException;

/**
 * the parent of all TrainSchedules
 * and using the template mode here to generate the
 * Train Schedule from several input types
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public abstract class TrainSchedule {

    private Integer[][] adjacentMatrix;
    
    public TrainSchedule() throws DataException {
        // should generate the matrix firstly
        generateAdjacentMatrix();
    }

    /**
     * get the matrix, the value means weight, the index means the point(station index)
     * notice: UNREACHABLE means there is no route between two points
     *
     * @return
     */
    public Integer[][] getAdjacentMatrix() {
        return adjacentMatrix;
    }

    /***
     * check if the data is valid
     *
     * @return
     */
    public Boolean isValid() {
        if (adjacentMatrix == null) {
            return false;
        }
        return true;
    }

    // todo: getLengthBetween(String, String)
    public Integer getLengthBetween(Integer startIndex, Integer endIndex) {
        if ( startIndex> adjacentMatrix.length || endIndex> adjacentMatrix[0].length){
            return DefaultSetting.UNREACHABLE;
        }
        return adjacentMatrix[startIndex][endIndex];
    }

    public Integer getLengthBetween(EnumStation startStation, EnumStation endStation) {
        return getLengthBetween(startStation.getIndex(), endStation.getIndex());
    }

    /***
     *  generate the adjacent matrix
     *      the sub class will decide the way to get the matrix 
     * 
     * @throws DataException when input data error or the generate matrix failed
     */
    public abstract void generateAdjacentMatrix() throws DataException ;
}
