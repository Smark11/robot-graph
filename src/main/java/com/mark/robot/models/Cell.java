package com.mark.robot.models;

public class Cell {
    private Integer rowCoordinate;
    private Integer columnCoordinate;

    public Cell(Integer rowCoordinate, Integer columnCoordinate) {
        this.rowCoordinate = rowCoordinate;
        this.columnCoordinate = columnCoordinate;
    }

    public Integer getRowCoordinate() {
        return rowCoordinate;
    }

    public void setRowCoordinate(Integer rowCoordinate) {
        this.rowCoordinate = rowCoordinate;
    }

    public Integer getColumnCoordinate() {
        return columnCoordinate;
    }

    public void setColumnCoordinate(Integer columnCoordinate) {
        this.columnCoordinate = columnCoordinate;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "rowCoordinate=" + rowCoordinate +
                ", columnCoordinate=" + columnCoordinate +
                '}';
    }
}
