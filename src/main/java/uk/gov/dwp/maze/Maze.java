package uk.gov.dwp.maze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Maze {
    private String pathToMazeTxtFile;
    private String mazeString;

    public Maze(String pathToMazeTxtFile) throws IOException, IllegalArgumentException {
        this.pathToMazeTxtFile = pathToMazeTxtFile;
        mazeString = new String(Files.readAllBytes(Paths.get(pathToMazeTxtFile)));

        validateMaze();
    }

    private void validateMaze() {
        if (getCountOfCharsInString(mazeString, 'S') == 0) {
            throw new IllegalArgumentException("Maze must have a start point.");
        }
        if (getCountOfCharsInString(mazeString, 'F') == 0) {
            throw new IllegalArgumentException("Maze must have an end point.");
        }
        if(!mazeString.matches("[SFX \n]*")) {
            throw new IllegalArgumentException("Maze contains invalid characters.");
        }
    }

    private long getCountOfCharsInString(String haystack, char needle) {
        return haystack.chars().filter(character -> (char) character == needle).count();
    }

    public String getMazeAsString() {
        return mazeString;
    }

    public long getNumberOfWalls() {
        return getCountOfCharsInString(mazeString, 'X');
    }

    public long getNumerOfSpaces() {
        return getCountOfCharsInString(mazeString, ' ');
    }

    public char getTile(int y, int x) {
        char[][] mapAsGrid = getMapAsGrid();

        return mapAsGrid[y][x];
    }

    protected char[][] getMapAsGrid() {
        String rows[] = mazeString.split("\n");
        char[][] mapAsGrid = new char[rows.length][rows[0].length()];
        for(int i=0; i < rows.length; i++) {
           mapAsGrid[i] = rows[i].toCharArray();
        }

        return mapAsGrid;
    }

    public int[] getStartPoint() {
        return findFirstLocationOfTile('S');
    }

    private int[] findFirstLocationOfTile(char tileChar) {
        int y = 0;
        for(char[] row: getMapAsGrid()) {
            int x = 0;
            for(char tile: row) {
                if(tile == tileChar) {
                    return new int[] {y,x};
                }
                x++;
            }
            y++;
        }
        return new int[]{};
    }

    public int[] getEndPoint() {
        return findFirstLocationOfTile('F');
    }
}
