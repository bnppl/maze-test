package uk.gov.dwp.maze;

public class Explorer {

    private Maze maze;
    private int[] location;

    private int yIncrement = -1;

    public Explorer(Maze maze) {

        this.maze = maze;
        this.location = maze.getStartPoint();
    }

    public int[] getLocation() {
        return location;
    }

    public void move() {
        location[0] += yIncrement;
    }

    public void faceDown() {
        yIncrement = 1;
    }
}
