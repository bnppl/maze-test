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
}
