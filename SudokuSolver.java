public class SudokuSolver {

    public static void main(String[] args) {
        int[][] sudokuGrid = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (solveSudoku(sudokuGrid)) {
            System.out.println("Sudoku solved successfully:");
            printSudokuGrid(sudokuGrid);
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
        }
    }

    // Function to solve the Sudoku puzzle using backtracking
    public static boolean solveSudoku(int[][] grid) {
        int[] emptyCell = findEmptyCell(grid);
        int row = emptyCell[0];
        int col = emptyCell[1];

        // If there are no more empty cells, the Sudoku puzzle is solved
        if (row == -1 && col == -1) {
            return true;
        }

        // Try filling the empty cell with numbers 1 to 9
        for (int num = 1; num <= 9; num++) {
            if (isValidMove(grid, row, col, num)) {
                grid[row][col] = num;

                // Recursively try to solve the rest of the Sudoku puzzle
                if (solveSudoku(grid)) {
                    return true;
                }

                // If placing num in grid[row][col] doesn't lead to a solution, backtrack
                grid[row][col] = 0;
            }
        }

        // No number from 1 to 9 is valid, backtrack to previous state
        return false;
    }

    // Function to find the next empty cell (cell with 0) in the Sudoku grid
    public static int[] findEmptyCell(int[][] grid) {
        int[] cell = {-1, -1};

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] == 0) {
                    cell[0] = row;
                    cell[1] = col;
                    return cell;
                }
            }
        }

        return cell; // Return {-1, -1} if no empty cell is found
    }

    // Function to check if placing num at grid[row][col] is valid
    public static boolean isValidMove(int[][] grid, int row, int col, int num) {
        // Check if num is already present in the current row
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == num) {
                return false;
            }
        }

        // Check if num is already present in the current column
        for (int i = 0; i < 9; i++) {
            if (grid[i][col] == num) {
                return false;
            }
        }

        // Check if num is already present in the 3x3 grid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (grid[i][j] == num) {
                    return false;
                }
            }
        }

        // If num is not present in current row, column, or 3x3 grid, it's a valid move
        return true;
    }

    // Function to print the Sudoku grid
    public static void printSudokuGrid(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
