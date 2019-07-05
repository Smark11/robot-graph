package com.mark.robot.exceptions;

import java.io.IOException;

public class RobotInfiniteLoopException extends IOException {
    public RobotInfiniteLoopException(String message) {
        super(message);
    }
}
