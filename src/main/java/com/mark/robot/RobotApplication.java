package com.mark.robot;

import com.mark.robot.exceptions.RobotInfiniteLoopException;
import com.mark.robot.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RobotApplication {
    private List<Row> grid = new ArrayList();
    private Cell robotStartingPoint;
    private char robotFacingDirection = 'F';
    private Integer totalnumberOfCells;

    /***
     * This is for unit testing - set things you need to here
     * @param grid
     */
    public RobotApplication(List<Row> grid,
                            Cell robotStartingPoint){
        this.grid = grid;
        this.totalnumberOfCells = grid.size() * grid.size();
        this.robotStartingPoint = robotStartingPoint;
    }

    /**
     * Main entry point ofr application
     * @param lrf L = Left, R = Right, F = Forward
     * @return Ending cell that the robot lands on
     */
    public Cell moveRobot(String lrf) throws RobotInfiniteLoopException {
        char[] directions = lrf.toCharArray();

        Cell robotLocationOnGrid = robotStartingPoint;

        for(char direction : directions){
            robotLocationOnGrid = navigateGrid(robotLocationOnGrid, direction);
        }

        return robotLocationOnGrid;
    }


    /***
     * Assume that it has to be a square
     * @param sizeOfGrid number of cells horizonatal and vertical
     */
    public RobotApplication(Integer sizeOfGrid){
        System.out.println("Starting to initialize grid of " + sizeOfGrid + " cells");
        
        //For each row I need to build
        for(int row = 0; row < sizeOfGrid; row++){
            //Create new row
            Row newRow = new Row();

            //Create new collection of cells
            List<Cell> cellsInRow = new ArrayList<Cell>();

            //for each column in grid
            for(int column = 0; column < sizeOfGrid; column++){
                //add a new cell
                cellsInRow.add(getMeACell(sizeOfGrid, row, column));
                
            }

            //add list of cells to the row
            newRow.setCells(cellsInRow);

            //add row to the grid
            grid.add(newRow);
        }
        
        Integer columnLocation = random.nextInt(sizeOfGrid);
        Integer rowLocation = random.nextInt(sizeOfGrid);

        totalnumberOfCells = sizeOfGrid * sizeOfGrid;
        
        robotStartingPoint = grid.get(rowLocation).getCells().get(columnLocation);
    }

    private Random random = new Random();
    private Cell getMeACell(Integer sizeOfGrid, Integer row, Integer col) {
        Integer randomNumber = random.nextInt(4);

        switch(randomNumber){
            case 0:
                System.out.println("Building a Hole");
                return new Hole(row, col, random.nextInt(sizeOfGrid - 1), random.nextInt(sizeOfGrid - 1));
            case 1:
                System.out.println("Building a Rock");
                return new Rock(row, col);
            case 2:
                System.out.println("Building a Spinner");
                return new Spinner(row, col, random.nextInt(3));
            case 3:
                System.out.println("Building a plain old cell");
                return new Cell(row, col);
        }


        return null;
    }


    private Cell navigateGrid(Cell currentCell, char direction) throws RobotInfiniteLoopException{
        Cell endingCell = null;
        currentNumberOfHandles = 0;

        GridCoordinate newGridCoordinates = null;
        
        switch(direction){
            case 'L':
                newGridCoordinates = moveLeft(currentCell);
            case 'R':
                newGridCoordinates = moveRight(currentCell);
            case 'F':
                newGridCoordinates = moveForward(currentCell);
        }

        if(newGridCoordinates.getColumn() > grid.size() || newGridCoordinates.getRow() > grid.size()) {
            System.out.println("Robot tried to move off grid - returning current location again");
            return currentCell;
        }

        Cell newCell = grid.get(newGridCoordinates.getRow()).getCells().get(newGridCoordinates.getColumn());

        endingCell = handleCell(currentCell, newCell);
        
        return  endingCell;
    }

    private Integer currentNumberOfHandles = 0;
    private Cell handleCell(Cell currentCell, Cell newCell) throws RobotInfiniteLoopException{
        currentNumberOfHandles++;

        if(currentNumberOfHandles > totalnumberOfCells){
            throw new RobotInfiniteLoopException("Infinite Loop Detected - Robot has visited every cell");
        }

        if(newCell.getClass().isInstance(Cell.class)){
            return newCell;
        }

        if(newCell.getClass().isInstance(Rock.class)){
            return currentCell;
        }

        if(newCell.getClass().isInstance(Spinner.class)){
            Spinner spinner = (Spinner)newCell;
            setNewSpinnerDirection(spinner);
        }

        if(newCell.getClass().isInstance(Hole.class)){
            Hole hole = (Hole)newCell;
            Cell transportedCell = grid.get(hole.getGoToRowLocation()).getCells().get(hole.getGoToColumnLocation());
            if(transportedCell.getClass().isInstance(Hole.class)){
                return handleCell(currentCell, transportedCell);
            }
        }

        return null;
    }

    private void setNewSpinnerDirection(Spinner spinner){
        switch (spinner.getRotation()){
            case 1:
                if (robotFacingDirection == 'F') { robotFacingDirection = 'R'; break; }
                if (robotFacingDirection == 'L') { robotFacingDirection = 'F'; break; }
                if (robotFacingDirection == 'R') { robotFacingDirection = 'B'; break; }
                if (robotFacingDirection == 'B') { robotFacingDirection = 'L'; break; }
            case 2:
                if (robotFacingDirection == 'F') { robotFacingDirection = 'B'; break; }
                if (robotFacingDirection == 'L') { robotFacingDirection = 'R'; break; }
                if (robotFacingDirection == 'R') { robotFacingDirection = 'L'; break; }
                if (robotFacingDirection == 'B') { robotFacingDirection = 'F'; break; }
            case 3:
                if (robotFacingDirection == 'F') { robotFacingDirection = 'L'; break; }
                if (robotFacingDirection == 'L') { robotFacingDirection = 'B'; break; }
                if (robotFacingDirection == 'R') { robotFacingDirection = 'F'; break; }
                if (robotFacingDirection == 'B') { robotFacingDirection = 'R'; break; }
            case 4:
                if (robotFacingDirection == 'F') { robotFacingDirection = 'F'; break; }
                if (robotFacingDirection == 'L') { robotFacingDirection = 'L'; break; }
                if (robotFacingDirection == 'R') { robotFacingDirection = 'R'; break; }
                if (robotFacingDirection == 'B') { robotFacingDirection = 'B'; break; }
        }
    }

    private GridCoordinate moveRight(Cell robotCell){
        switch(robotFacingDirection){
            case 'R':
                return new GridCoordinate(robotCell.getColumnCoordinate(), robotCell.getRowCoordinate() +1);
            case 'L':
                return new GridCoordinate(robotCell.getColumnCoordinate(), robotCell.getRowCoordinate() -1);
            case 'F':
                return new GridCoordinate(robotCell.getColumnCoordinate() + 1, robotCell.getRowCoordinate());
            case 'B':
                return new GridCoordinate(robotCell.getColumnCoordinate() - 1, robotCell.getRowCoordinate());
        }
        return null;
    }

    private GridCoordinate moveLeft(Cell robotCell){
        switch(robotFacingDirection){
            case 'R':
                return new GridCoordinate(robotCell.getColumnCoordinate(), robotCell.getRowCoordinate() -1);
            case 'L':
                return new GridCoordinate(robotCell.getColumnCoordinate(), robotCell.getRowCoordinate() +1);
            case 'F':
                return new GridCoordinate(robotCell.getColumnCoordinate() - 1, robotCell.getRowCoordinate());
            case 'B':
                return new GridCoordinate(robotCell.getColumnCoordinate() + 1, robotCell.getRowCoordinate());
        }
        return null;
    }

    private GridCoordinate moveForward(Cell robotCell){
        switch(robotFacingDirection){
            case 'R':
                return new GridCoordinate(robotCell.getColumnCoordinate() +1, robotCell.getRowCoordinate());
            case 'L':
                return new GridCoordinate(robotCell.getColumnCoordinate() - 1, robotCell.getRowCoordinate());
            case 'F':
                return new GridCoordinate(robotCell.getColumnCoordinate(), robotCell.getRowCoordinate() - 1);
            case 'B':
                return new GridCoordinate(robotCell.getColumnCoordinate(), robotCell.getRowCoordinate() + 1);
        }
        return null;
    }
}
