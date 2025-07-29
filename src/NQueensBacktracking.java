public class NQueensBacktracking {

    static int N = 8; // Change this to test with other N
    static int[][] board = new int[N][N];

    static void printBoardCLI() {
        System.out.println("Chessboard with Queens:\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    System.out.print(" Q ");
                } else {
                    // Alternate pattern
                    if ((i + j) % 2 == 0)
                        System.out.print("⬜ ");
                    else
                        System.out.print("⬛ ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++)
            if (board[i][col] == 1)
                return false;

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        for (int i = row, j = col; i >= 0 && j < N; i--, j++)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    static boolean solve(int row) {
        if (row == N)
            return true;

        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                board[row][col] = 1;

                if (solve(row + 1))
                    return true;

                board[row][col] = 0;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int firstQueenCol = 0; // You can change this manually
        board[0][firstQueenCol] =0; // Pre-place first queen

        if (solve(1)) {
            printBoardCLI();
        } else {
            System.out.println("No solution exists for N = " + N + " with first queen at column " + firstQueenCol);
        }
    }
}
