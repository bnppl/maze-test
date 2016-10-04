package uk.gov.dwp.maze;

public class Explorer {

    private Maze maze;
    private int[] location;

    private int yIncrement;
    private int xIncrement;

    public Explorer(Maze maze) {

        this.maze = maze;
        this.location = maze.getStartPoint();
        faceUp();
    }

    public int[] getLocation() {
        return location;
    }

    public void move() {
        if (maze.getTile(location[0]+ yIncrement, location[1]+ xIncrement) != 'X') {
            location[0] += yIncrement;
            location[1] += xIncrement;
        }
    }

    public void faceDown() {
        yIncrement = 1;
        xIncrement = 0;
    }

    public void faceUp() {
        yIncrement = -1;
        xIncrement = 0;
    }

    public void faceRight() {
        yIncrement = 0;
        xIncrement = 1;
    }

    public void faceLeft() {
        yIncrement = 0;
        xIncrement = -1;
    }
}
