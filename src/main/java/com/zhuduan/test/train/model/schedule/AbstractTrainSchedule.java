package com.zhuduan.test.train.model.schedule;

/**
 * the parent of all TrainSchedules 
 *      and using the template mode here to generate the 
 *      Train Schedule from several input types
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public abstract class AbstractTrainSchedule {
    
    private Integer[][] adjacentMatrix;
    
    public AbstractTrainSchedule() {
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

    // todo: getLengthBetween(String, String)
    public Integer getLengthBetween(Integer pointA, Integer pointB){
        //
        return null;
    }

    /* the sub class will decide the way to get the matrix */
    public abstract void generateAdjacentMatrix();
}
