package maze;

import java.io.Serializable;
import java.util.*;

public class Maze implements Serializable {
    private transient final Scanner scanner = new Scanner(System.in);
    private transient final int SIZE = scanner.nextInt();
    private final int ROWS = SIZE;
    private final int LINE = SIZE;
    private final int[][] maze = new int[ROWS][LINE];
    private final transient Stack<List<Integer>> usedCells = new Stack<>();
    private final transient Stack<List<Integer>> usedCells2 = new Stack<>();
    private transient List<Integer> currentIndexes = Arrays.asList(1, 1);
    private transient List<Boolean> neighbors = new ArrayList<>();
    private final List<Integer> entrance = Arrays.asList(0, 0);
    private final List<Integer> exit = Arrays.asList(0, 0);


    public void start() {
        createCells();
        generateEnterAndExit();
        plotRoute();
        drawMaze();
    }

    private void generateEnterAndExit() {

        entrance.set(1, 0);
        exit.set(1, LINE - 1);

        entrance.set(0, (int) (Math.random() * (ROWS - 1)));
        exit.set(0, (int) (Math.random() * (ROWS - 1)));
        boolean correct = false;
        while (!correct) {
            if (entrance.get(0) % 2 == 1) {
                correct = true;
            } else {
                entrance.set(0, (int) (Math.random() * (ROWS - 1)));
            }
        }
        correct = false;
        while (!correct) {
            if (exit.get(0) % 2 == 1) {
                correct = true;
            } else {
                exit.set(0, (int) (Math.random() * (ROWS - 1)));
            }
        }
        maze[entrance.get(0)][entrance.get(1)] = 0;
        maze[exit.get(0)][exit.get(1)] = 0;
        if (maze[entrance.get(0)][entrance.get(1) + 1] == 1) maze[entrance.get(0)][entrance.get(1) + 1] = 0;
        if (maze[exit.get(0)][exit.get(1) - 1] == 1) maze[exit.get(0)][exit.get(1) - 1] = 0;
    }

    private void createCells() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < LINE; j++) {
                if (i == 0 || j == 0 || i == ROWS - 1 || j == LINE - 1) {
                    maze[i][j] = 1;
                    continue;
                }
                if (i % 2 == 0 || j % 2 == 0) {
                    maze[i][j] = 1;
                } else {
                    maze[i][j] = 0;
                }
            }
        }
    }

    private void checkNeighbors(int rows, int line) {
        neighbors.clear();
        neighbors.add(rows - 2 > 0 && !usedCells.contains(Arrays.asList(rows - 2, line)));
        neighbors.add(line + 2 <= this.LINE - 2 && !usedCells.contains(Arrays.asList(rows, line + 2)));
        neighbors.add(rows + 2 <= this.ROWS - 2 && !usedCells.contains(Arrays.asList(rows + 2, line)));
        neighbors.add(line - 2 > 0 && !usedCells.contains(Arrays.asList(rows, line - 2)));
    }

   private void plotRoute() {
       int random = (int) (Math.random() * 4);
       checkNeighbors(1, 1);
       usedCells.push(Arrays.asList(1, 1));
       usedCells2.push(Arrays.asList(1, 1));
       int rows = currentIndexes.get(0);
       int line = currentIndexes.get(1);
       while (!usedCells2.empty()) {
           checkNeighbors(rows, line);
           while (neighbors.contains(true)) {
               switch (random) {
                   case 0:
                       if (neighbors.get(random).equals(true)) {
                           maze[rows - 1][line] = 0;
                           currentIndexes = Arrays.asList(rows - 2, line);
                           usedCells.push(Arrays.asList(rows - 2, line));
                           usedCells2.push(Arrays.asList(rows - 2, line));
                       }
                       break;
                   case 1:
                       if (neighbors.get(random).equals(true)) {
                           maze[rows][line + 1] = 0;
                           currentIndexes = Arrays.asList(rows, line + 2);
                           usedCells.push(Arrays.asList(rows, line + 2));
                           usedCells2.push(Arrays.asList(rows, line + 2));
                       }
                       break;
                   case 2:
                       if (neighbors.get(random).equals(true)) {
                           maze[rows + 1][line] = 0;
                           currentIndexes = Arrays.asList(rows + 2, line);
                           usedCells.push(Arrays.asList(rows + 2, line));
                           usedCells2.push(Arrays.asList(rows + 2, line));
                       }
                       break;
                   case 3:
                       if (neighbors.get(random).equals(true)) {
                           maze[rows][line - 1] = 0;
                           currentIndexes = Arrays.asList(rows, line - 2);
                           usedCells.push(Arrays.asList(rows, line - 2));
                           usedCells2.push(Arrays.asList(rows, line - 2));
                       }
                       break;
               }
               rows = currentIndexes.get(0);
               line = currentIndexes.get(1);
               checkNeighbors(rows, line);
               random = (int) (Math.random() * 4);
           }
           currentIndexes = usedCells2.pop();
           rows = currentIndexes.get(0);
           line = currentIndexes.get(1);
       }
   }

    public void drawMaze() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < LINE; j++) {
                if (maze[i][j] == 1) {
                    System.out.print("\u2588\u2588");
                } else if (maze[i][j] == 0){
                    System.out.print("  ");
                } else {
                    System.out.print(maze[i][j]);
                }
            }
            System.out.println();
        }
    }

    public int[][] getMaze() {
        return maze;
    }

    public List<Integer> getEntrance() {
        return entrance;
    }

    public List<Integer> getExit() {
        return exit;
    }
}
