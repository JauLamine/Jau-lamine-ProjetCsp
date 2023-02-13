package Choco;

public class BackTrackSudoku {
	static int[][] grid = {{9, 5, 0, 0, 1, 3, 8, 0, 0},
			{0, 0, 1, 0, 5, 0, 0, 0, 0},
			{0, 0, 7, 0, 0, 0, 0, 0, 9},
			{2, 4, 0, 0, 7, 0, 0, 0, 8},
			{0, 0, 0, 6, 0, 0, 3, 0, 0},
			{0, 0, 9, 0, 0, 0, 0, 0, 0},
			{5, 8, 0, 0, 4, 0, 0, 0, 2},
			{0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 7, 0, 4, 0}};;

			public static void executionTime() {
				long startTime = System.nanoTime();
				solve(0, 0);
				long endTime = System.nanoTime();
				long totalTime = endTime - startTime;
				WriteInFile t=new WriteInFile() ;
				t.writeInFile(""+totalTime,"BtTest");
				System.out.println("Temps d'exécution en nanosecondes : " + totalTime);
			}

			public static void main(String[] args) {
				executionTime();
				if (grid[8][8]!=0) 
				{
					// Affichage de la solution
					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							System.out.print(grid[i][j] + " ");
						}
						System.out.println();
					}
				} else {
					System.out.println("Pas de solution");
				}
			}


			private static boolean solve(int row, int col) {
				if (row == 9) {
					row = 0;
					if (++col == 9) {
						return true;
					}
				}

				if (grid[row][col] != 0) {
					return solve(row + 1, col);
				}

				for (int val = 1; val <= 9; val++) {
					if (isValid(row, col, val)) {
						grid[row][col] = val;

						if (solve(row + 1, col)) {
							return true;
						}

						grid[row][col] = 0;
					}
				}

				return false;
			}

			private static boolean isValid(int row, int col, int val) {
				for (int i = 0; i < 9; i++) {
					if (val == grid[i][col] || val == grid[row][i]) {
						return false;
					}
				}

				int regionRow = row / 3 * 3;
				int regionCol = col / 3 * 3;

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (val == grid[regionRow + i][regionCol + j]) {
							return false;
						}
					}
				}

				return true;
			}
}

