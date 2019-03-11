package com.zhuduan.train.bo.schedule;

import com.zhuduan.train.bo.station.TrainStation;
import com.zhuduan.train.constant.DefaultSetting;
import com.zhuduan.train.constant.ErrorCode;
import com.zhuduan.train.exception.DataException;
import com.zhuduan.train.util.UtilTool;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * get the input data from file
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class FileTrainSchedule extends TrainSchedule {

    private static final String ROUTE_PREFIX = "Graph: ";
    private static final String ROUTE_SPLIT = ",";

    private String fileUrl;

    public FileTrainSchedule(String fileUrl) throws DataException, IOException {
        this.fileUrl = fileUrl;
        generateScheduleInfo();
    }

    @Override
    protected void generateScheduleInfo() throws DataException, IOException {
        String actualRoute = readStrFromFile().replace(ROUTE_PREFIX, StringUtils.EMPTY).trim();

        // should generate the station list before the matrix
        generateStationList(actualRoute);
        generateMatrix(actualRoute);
    }

    private String readStrFromFile() throws DataException, IOException {
        if (StringUtils.isEmpty(this.fileUrl)) {
            throw new DataException(ErrorCode.INVALID_FILE_NAME);
        }

        File file = new File(this.fileUrl);
        if (!file.exists()) {
            System.out.println(file.getPath());
            System.out.println(file.getAbsolutePath());
            throw new DataException(ErrorCode.NO_SUCH_FILE);
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String inputData = br.readLine();       // only read one line for now

        return inputData;
    }

    private void generateStationList(String route) {
        // init firstly
        this.allStations = new ArrayList<>();

        char[] details = route.toCharArray();
        for (int i = 0; i < details.length; i++) {
            if (Character.isAlphabetic(details[i])
                    && !containsStation(String.valueOf(details[i]))) {
                TrainStation station = new TrainStation(this.allStations.size(), String.valueOf(details[i]),
                        String.valueOf(details[i]));
                this.allStations.add(station);
            }
        }
    }

    private void generateMatrix(String route) throws DataException {
        // initial matrix at the first
        initMatrix();

        String[] data = route.split(ROUTE_SPLIT);
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].trim();

            TrainStation startStation = getStationByName(data[i].substring(0, 1));
            TrainStation endStation = getStationByName(data[i].substring(1, 2));
            if (startStation == null || endStation == null) {
                throw new DataException(ErrorCode.INVALID_ROUTE_INFO);
            }

            Integer dataEndIndex = data[i].indexOf(ROUTE_SPLIT);
            dataEndIndex = dataEndIndex == -1 ? data[i].length() : dataEndIndex;
            Integer length = Integer.parseInt(data[i].substring(2, dataEndIndex));
            adjacentMatrix[startStation.getIndex()][endStation.getIndex()] = length;
        }
    }

    private void initMatrix() {
        this.adjacentMatrix = new Integer[allStations.size()][allStations.size()];
        UtilTool.fillIntMatrix(this.adjacentMatrix, DefaultSetting.UNREACHABLE);
    }
}
