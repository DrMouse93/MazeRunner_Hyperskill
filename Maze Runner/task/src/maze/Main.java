package maze;

import java.util.Scanner;

public class Main {
    public static Action action = Action.START;
    public static Maze maze;

    public static void main(String[] args) {
        while (action != Action.EXIT) {
            Menu.menu();
            Menu.option();
            switch (action) {
                case GENERATE:
                    System.out.println("Please, enter the size of a maze");
                    maze = new Maze();
                    maze.start();
                    break;
                case LOAD:
                    LoadedMaze.loaded();
                    break;
                case SAVE:
                    SavedMaze.saved();
                    break;
                case DISPLAY:
                    maze.drawMaze();
                    break;
                case ESCAPE:
                    FindEscape findEscape = new FindEscape();
                    findEscape.drawEscape();
                    break;
                case EXIT:
                    System.out.println("Buy");
                    break;
                default:
                    System.out.println("Incorrect option. Please try again");
                    break;
            }
        }
    }
}
