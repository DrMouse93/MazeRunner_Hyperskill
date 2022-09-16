package maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class FindEscape {

    private final Stack<List<Integer>> escape = new Stack<>();
    private final Stack<List<Integer>> notEscape = new Stack<>();
    private List<Boolean> route = new ArrayList<>();
    private List<Integer> currentIndexes = Arrays.asList(1, 1);

    public void drawEscape() {
        findEscape();
        for (int i = 0; i < Main.maze.getMaze().length; i++) {
            for (int j = 0; j < Main.maze.getMaze().length; j++) {
                if (escape.contains(Arrays.asList(i, j))) {
                    System.out.print("//");
                } else if (Main.maze.getMaze()[i][j] == 1) {
                    System.out.print("\u2588\u2588");
                } else if (Main.maze.getMaze()[i][j] == 0){
                    System.out.print("  ");
                } else {
                    System.out.print(Main.maze.getMaze()[i][j]);
                }
            }
            System.out.println();
        }
    }

    public void findEscape() {
        int random = (int) (Math.random() * 4);
        escape.push(Main.maze.getEntrance());
        notEscape.push(Main.maze.getEntrance());
        checkRoute(Main.maze.getEntrance().get(0), Main.maze.getEntrance().get(1));
        currentIndexes = Main.maze.getEntrance();
        int rows = currentIndexes.get(0);
        int line = currentIndexes.get(1);

        while (!escape.peek().equals(Main.maze.getExit())) {
            currentIndexes = escape.pop();
            rows = currentIndexes.get(0);
            line = currentIndexes.get(1);
            checkRoute(rows, line);
            while (route.contains(true)) {
                switch (random) {
                    case 0:
                        if (route.get(random).equals(true)) {
                            if (!escape.contains(Arrays.asList(rows, line))) {
                                escape.push(Arrays.asList(rows, line));
                            }
                            escape.push(Arrays.asList(rows - 1, line));
                            currentIndexes = Arrays.asList(rows - 1, line);
                            notEscape.push(Arrays.asList(rows - 1, line));
                        }
                        break;
                    case 1:
                        if (route.get(random).equals(true)) {
                            if (!escape.contains(Arrays.asList(rows, line))) {
                                escape.push(Arrays.asList(rows, line));
                            }
                            escape.push(Arrays.asList(rows, line + 1));
                            currentIndexes = Arrays.asList(rows, line + 1);
                            notEscape.push(Arrays.asList(rows, line + 1));
                        }
                        break;
                    case 2:
                        if (route.get(random).equals(true)) {
                            if (!escape.contains(Arrays.asList(rows, line))) {
                                escape.push(Arrays.asList(rows, line));
                            }
                            escape.push(Arrays.asList(rows + 1, line));
                            currentIndexes = Arrays.asList(rows + 1, line);
                            notEscape.push(Arrays.asList(rows + 1, line));
                        }
                        break;
                    case 3:
                        if (route.get(random).equals(true)) {
                            if (!escape.contains(Arrays.asList(rows, line))) {
                                escape.push(Arrays.asList(rows, line));
                            }
                            escape.push(Arrays.asList(rows, line - 1));
                            currentIndexes = Arrays.asList(rows, line - 1);
                            notEscape.push(Arrays.asList(rows, line - 1));
                        }
                        break;
                }
                rows = currentIndexes.get(0);
                line = currentIndexes.get(1);
                checkRoute(rows, line);
                random = (int) (Math.random() * 4);
            }
        }
    }

    private void checkRoute(int rows, int line) {
        route.clear();

        route.add(rows - 1 >= 0
                && Main.maze.getMaze()[rows - 1][line] == 0
                && !notEscape.contains(Arrays.asList(rows - 1, line)));

        route.add(line + 1 < Main.maze.getMaze().length
                && Main.maze.getMaze()[rows][line + 1] == 0
                && !notEscape.contains(Arrays.asList(rows, line + 1)));

        route.add(rows + 1 < Main.maze.getMaze().length
                && Main.maze.getMaze()[rows + 1][line] == 0
                && !notEscape.contains(Arrays.asList(rows + 1, line)));

        route.add(line - 1 >= 0
                && Main.maze.getMaze()[rows][line - 1] == 0
                && !notEscape.contains(Arrays.asList(rows, line - 1)));
    }
}
