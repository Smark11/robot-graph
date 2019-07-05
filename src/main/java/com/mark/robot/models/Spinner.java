package com.mark.robot.models;

public class Spinner extends Cell {
    private Integer rotation;

    public Spinner(Integer rowCoordinate,
                   Integer columnCoordinate,
                   Integer rotation) {
        super(rowCoordinate, columnCoordinate);
        this.rotation = rotation;
    }

    public Integer getRotation() {
        //1 = 90 degrees
        return rotation + 1;
    }

    public void setRotation(Integer rotation) {
        this.rotation = rotation;
    }
}
