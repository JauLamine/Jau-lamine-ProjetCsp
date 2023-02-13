package Choco;

public class ForwardCheckingSudoku {
	static int[][] board = {{9, 5, 0, 0, 1, 3, 8, 0, 0},
			{0, 0, 1, 0, 5, 0, 0, 0, 0},
			{0, 0, 7, 0, 0, 0, 0, 0, 9},
			{2, 4, 0, 0, 7, 0, 0, 0, 8},
			{0, 0, 0, 6, 0, 0, 3, 0, 0},
			{0, 0, 9, 0, 0, 0, 0, 0, 0},
			{5, 8, 0, 0, 4, 0, 0, 0, 2},
			{0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 7, 0, 4, 0}};
	private static boolean forwardChecking(int[][] board, int row, int col, int num) {
		// Check row constraints
		for (int i = 0; i < 9; i++) {
			if (board[row][i] == num) {
				return false;
			}
		}

		// Check column constraints
		for (int i = 0; i < 9; i++) {
			if (board[i][col] == num) {
				return false;
			}
		}

		// Check the 3x3 box constraints
		int rowStart = (row / 3) * 3;
		int colStart = (col / 3) * 3;
		for (int i = rowStart; i < rowStart + 3; i++) {
			for (int j = colStart; j < colStart + 3; j++) {
				if (board[i][j] == num) {
					return false;
				}
			}
		}

		return true;
	}

	public static boolean solveSudoku(int[][] board) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (board[row][col] == 0) {
					for (int num = 1; num <= 9; num++) {
						if (forwardChecking(board, row, col, num)) {
							board[row][col] = num;
							if (solveSudoku(board)) {
								return true;
							}
							board[row][col] = 0;
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	public static void executionTime(int [][] board) {
		long startTime = System.nanoTime();
		solveSudoku(board);
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		WriteInFile t=new WriteInFile() ;
		t.writeInFile(""+totalTime,"FcTest");
		System.out.println("Temps d'exécution en nanosecondes : " + totalTime);
	}
	public static void main(String[] args) {
		executionTime(board);
		if (board[0][0]!=0) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(board[i][j] + " ");
				}
				System.out.println();
			}
		} else {
			System.out.println("No solution found");

		}
	}
}

