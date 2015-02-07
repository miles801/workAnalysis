package com.michael.workanalysis.basic;

/**
 * @author Michael
 */
public class WorkTimeDetail {
    private String fileName;
    private float workTime = 0f;
    private int rows;
    private int cols;
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public float getWorkTime() {
        return workTime;
    }

    public void setWorkTime(float workTime) {
        this.workTime = workTime;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }
}
