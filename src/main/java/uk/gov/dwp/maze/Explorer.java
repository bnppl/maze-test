package uk.gov.dwp.maze;

public class Explorer {

    private Maze maze;
    private int[] location;

    private int yIncrement;

    public Explorer(Maze maze) {

        this.maze = maze;
        this.location = maze.getStartPoint();
        faceUp();
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

    public void faceUp() {
        yIncrement = -1;
    }
}
