package com.mark.robot.models;

/**
 * A rock prevents the robot from moving to this cell
 */
public class Rock extends Cell {
    public Rock(Integer rowCoordinate, Integer columnCoordinate) {
        super(rowCoordinate, columnCoordinate);
    }
}
