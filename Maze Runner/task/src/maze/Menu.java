package maze;

import java.util.Scanner;

public class Menu {
    public static void menu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Generate a new maze");
        System.out.println("2. Load a maze");
        if (Main.maze != null) {
            System.out.println("3. Save the maze");
            System.out.println("4. Display the maze");
            System.out.println("5. Find the escape");
        }
        System.out.println("0. Exit");
    }

    public static void option() {
        Scanner scanner = new Scanner(System.in);
        int opt = -1;
        try {
            opt = scanner.nextInt();
        } catch (Exception e) {
        }
        if (Main.maze == null) {
            switch (opt) {
                case 1:
                    Main.action = Action.GENERATE;
                    break;
                case 2:
                    Main.action = Action.LOAD;
                    break;
                case 0:
                    Main.action = Action.EXIT;
                    break;
                default:
                    Main.action = Action.START;
                    break;
            }
        } else {
            switch (opt) {
                case 1:
                    Main.action = Action.GENERATE;
                    break;
                case 2:
                    Main.action = Action.LOAD;
                    break;
                case 3:
                    Main.action = Action.SAVE;
                    break;
                case 4:
                    Main.action = Action.DISPLAY;
                    break;
                case 5:
                    Main.action = Action.ESCAPE;
                    break;
                case 0:
                    Main.action = Action.EXIT;
                    break;
                default:
                    Main.action = Action.START;
                    break;
            }
        }
    }
}
