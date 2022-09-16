package maze;

import java.io.*;
import java.util.Scanner;

public class LoadedMaze {
    public static void loaded() {
        System.out.println("Enter path to file");
        Scanner scanner = new Scanner(System.in);
        String pathToFile = scanner.nextLine();

        try (FileInputStream fis = new FileInputStream(pathToFile);
             ObjectInputStream ois = new ObjectInputStream(fis)){
            Main.maze = (Maze) ois.readObject();
        } catch (IOException e) {
            System.out.printf("The file %s does not exist\n", pathToFile);
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot load the maze. It has an invalid format");
        }

    }
}
