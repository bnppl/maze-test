package uk.gov.dwp.maze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Maze {
    private String pathToMazeTxtFile;
    private String mazeString;

    public Maze(String pathToMazeTxtFile) throws IOException {
        this.pathToMazeTxtFile = pathToMazeTxtFile;
        mazeString = new String(Files.readAllBytes(Paths.get(pathToMazeTxtFile)));
    }

    public String getMazeAsString() {
        return mazeString;
    }
}
