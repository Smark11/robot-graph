package com.mark.robot.models;

public class Hole extends Cell {
    private Integer goToRowLocation;
    private Integer goToColumnLocation;

    public Hole(Integer rowCoordinate, Integer columnCoordinate,
                Integer goToRowLocation, Integer goToColumnLocation) {
        super(rowCoordinate, columnCoordinate);
        this.goToRowLocation = goToRowLocation;
        this.goToColumnLocation = goToColumnLocation;
    }

    public Integer getGoToRowLocation() {
        return goToRowLocation;
    }

    public void setGoToRowLocation(Integer goToRowLocation) {
        this.goToRowLocation = goToRowLocation;
    }

    public Integer getGoToColumnLocation() {
        return goToColumnLocation;
    }

    public void setGoToColumnLocation(Integer goToColumnLocation) {
        this.goToColumnLocation = goToColumnLocation;
    }
}
