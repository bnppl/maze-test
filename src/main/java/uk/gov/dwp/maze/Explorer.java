package uk.gov.dwp.maze;

import java.util.StringJoiner;

public class Explorer {

    private Maze maze;
    private int[] location;

    private int yIncrement;
    private int xIncrement;

    private String currentDirection;
    private StringJoiner moveHistory = new StringJoiner(", ");

    public Explorer(Maze maze) {

        this.maze = maze;
        this.location = maze.getStartPoint();
        faceUp();
    }

    public int[] getLocation() {
        return location;
    }

    public void move() {
        if (maze.getTile(location[0] + yIncrement, location[1] + xIncrement) != 'X') {
            location[0] += yIncrement;
            location[1] += xIncrement;

            moveHistory.add(currentDirection);
        }
    }

    public void faceDown() {
        currentDirection = "down";
        yIncrement = 1;
        xIncrement = 0;
    }

    public void faceUp() {
        currentDirection = "up";
        yIncrement = -1;
        xIncrement = 0;
    }

    public void faceRight() {
        currentDirection = "right";
        yIncrement = 0;
        xIncrement = 1;
    }

    public void faceLeft() {
        currentDirection = "left";
        yIncrement = 0;
        xIncrement = -1;
    }

    public char whatIsNextTile() {
        return maze.getTile(location[0] + yIncrement, location[1] + xIncrement);
    }

    public String getMoves() {
        return moveHistory.toString();
    }

    public boolean isFinished() {
        if (maze.getTile(location[0], location[1]) == 'F') {
            return true;
        }
        return false;
    }

    public String drawLocationOnMap() {
        String drawing = "";
        char[][] mapAsGrid = maze.getMapAsGrid();
        mapAsGrid[location[0]][location[1]] = '*';
        for (char[] row : mapAsGrid) {
            drawing += new String(row) + "\n";
        }
        return drawing;
    }
}
