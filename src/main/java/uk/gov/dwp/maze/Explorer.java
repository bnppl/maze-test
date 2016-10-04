package uk.gov.dwp.maze;

public class Explorer {

    private Maze maze;
    private int[] location;

    public Explorer(Maze maze) {

        this.maze = maze;
        this.location = maze.getStartPoint();
    }

    public int[] getLocation() {
        return location;
    }
}
