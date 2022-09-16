package maze;

import java.io.*;
import java.util.Scanner;

public class SavedMaze {
    public static void saved() {
        System.out.println("Enter path to file");
        Scanner scanner = new Scanner(System.in);
        String pathToFile = scanner.nextLine();

        try (FileOutputStream fos = new FileOutputStream(pathToFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(Main.maze);
        } catch (IOException e) {
            System.out.println("Cannot save the maze");
        }

    }
}
