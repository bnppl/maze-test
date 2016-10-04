package uk.gov.dwp.maze;

import java.io.IOException;
import java.net.URISyntaxException;

public class MazeExplorerGame {
    public static void main(String[] args) throws IOException, URISyntaxException {
        String path = MazeExplorerGame.class.getClassLoader().getResource("").getPath() + "Maze1.txt";
        Maze maze = new Maze(path);
        Explorer explorer = new Explorer(maze);

        System.out.println("Select your direction using the WASD keys for Up, Left, Down, Right - press enter to move.");
        while (explorer.isFinished() == false) {
            System.out.println(explorer.drawLocationOnMap());
            char direction = (char) System.in.read();
            if (direction == 'w') {
                explorer.faceUp();
                explorer.move();
            }
            if (direction == 's') {
                explorer.faceDown();
                explorer.move();
            }
            if (direction == 'a') {
                explorer.faceLeft();
                explorer.move();
            }
            if (direction == 'd') {
                explorer.faceRight();
                explorer.move();
            }
        }
        System.out.println("Maze completed! Move history: " + explorer.getMoves());
    }
}
