import java.util.Scanner;

public class UniversalGridAnalyzer {

    static int[][] grid = {
        {5, 3, 8, 6, 2, 7},
        {9, 1, 4, 2, 8, 3},
        {6, 7, 2, 5, 1, 9},
        {3, 8, 6, 4, 7, 2},
        {1, 2, 9, 7, 3, 5},
        {4, 6, 3, 8, 9, 1}
    };

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Grid Menu ---");
            System.out.println("1 Display Grid");
            System.out.println("2 Row and Column Sums");
            System.out.println("3 Max and Min");
            System.out.println("4 Frequency");
            System.out.println("5 Pattern");
            System.out.println("6 Transform");
            System.out.println("7 Subgrid");
            System.out.println("8 Diagonals");
            System.out.println("9 Validation");
            System.out.println("0 Exit");

            choice = input.nextInt();

            switch (choice) {
                case 1: displayGrid(); break;
                case 2: rowColumnSum(); break;
                case 3: maxMin(); break;
                case 4: frequency(input); break;
                case 5: pattern(); break;
                case 6: transform(); break;
                case 7: subgrid(); break;
                case 8: boundaryDiagonal(); break;
                case 9: validation(); break;
            }

        } while (choice != 0);
    }

    public static void displayGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void rowColumnSum() {
        for (int i = 0; i < grid.length; i++) {
            int rowSum = 0;
            for (int j = 0; j < grid[i].length; j++) {
                rowSum += grid[i][j];
            }
            System.out.println("Row " + i + ": " + rowSum);
        }

        for (int j = 0; j < grid[0].length; j++) {
            int colSum = 0;
            for (int i = 0; i < grid.length; i++) {
                colSum += grid[i][j];
            }
            System.out.println("Col " + j + ": " + colSum);
        }
    }

    public static void maxMin() {
        int max = grid[0][0];
        int min = grid[0][0];
        int maxR = 0, maxC = 0, minR = 0, minC = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] > max) {
                    max = grid[i][j];
                    maxR = i;
                    maxC = j;
                }
                if (grid[i][j] < min) {
                    min = grid[i][j];
                    minR = i;
                    minC = j;
                }
            }
        }

        System.out.println("Maximum value: " + max + " at " + maxR + "," + maxC);
        System.out.println("Minimum value: " + min + " at " + minR + "," + minC);
    }

    public static void frequency(Scanner input) {
        System.out.print("Enter number: ");
        int target = input.nextInt();
        int count = 0;

        for (int[] row : grid) {
            for (int val : row) {
                if (val == target) count++;
            }
        }

        System.out.println("Count: " + count);
    }

    public static void pattern() {
        for (int i = 0; i < grid.length; i++) {
            boolean inc = true;
            for (int j = 1; j < grid[i].length; j++) {
                if (grid[i][j] <= grid[i][j - 1]) {
                    inc = false;
                    break;
                }
            }
            if (inc) {
                System.out.println("Row " + i + " increasing");
                return;
            }
        }
        System.out.println("No increasing row");
    }

    public static void transform() {
        int last = grid[0][grid[0].length - 1];
        for (int j = grid[0].length - 1; j > 0; j--) {
            grid[0][j] = grid[0][j - 1];
        }
        grid[0][0] = last;

        int[] temp = grid[0];
        grid[0] = grid[1];
        grid[1] = temp;

        for (int i = 0; i < grid.length / 2; i++) {
            int t = grid[i][0];
            grid[i][0] = grid[grid.length - 1 - i][0];
            grid[grid.length - 1 - i][0] = t;
        }

        System.out.println("After transformation:");
displayGrid();
    }

    public static void subgrid() {
        int sum = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                sum += grid[i][j];
                if (grid[i][j] > max) max = grid[i][j];
            }
        }

        System.out.println("Sum: " + sum);
        System.out.println("Max: " + max);
    }

    public static void boundaryDiagonal() {
        int n = grid.length;

        for (int i = 0; i < n; i++) {
            System.out.print(grid[i][i] + " ");
        }

        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print(grid[i][n - 1 - i] + " ");
        }

        System.out.println();
    }

    public static void validation() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                for (int k = j + 1; k < grid[i].length; k++) {
                    if (grid[i][j] == grid[i][k]) {
                        System.out.println("Duplicate in row " + i);
                        return;
                    }
                }
            }
        }
        System.out.println("No duplicates");
    }
}
