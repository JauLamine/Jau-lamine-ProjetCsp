package Choco;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

public class ChocoSolverSudoku {

    public static void main(String[] args) {
    	
        // Création d'un modèle Choco
        Model model = new Model("Sudoku");

        // Définition de la grille de Sudoku comme un tableau 2D de variables entières
        IntVar[][] grid = new IntVar[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = model.intVar("cell-" + i + "-" + j, 1, 9);
            }
        }
        grid[0][1] = model.intVar("cell-" + 0 + "-" + 1, 5);
        grid[1][2] = model.intVar("cell-" + 1 + "-" + 2, 6);
        grid[2][0] = model.intVar("cell-" + 2 + "-" + 3, 4);
        grid[1][3] = model.intVar("cell-" + 1 + "-" + 3, 4);
        grid[2][4] = model.intVar("cell-" + 2 + "-" + 4, 9);
        grid[0][7] = model.intVar("cell-" + 0 + "-" + 7, 2);
        grid[1][7] = model.intVar("cell-" + 1 + "-" + 7, 3);
        grid[1][6] = model.intVar("cell-" + 2 + "-" + 4, 1);
        grid[5][1] = model.intVar("cell-" + 5 + "-" + 1, 3);
        grid[4][2] = model.intVar("cell-" + 4 + "-" + 2, 8);
        grid[3][3] = model.intVar("cell-" + 3 + "-" + 3, 1);
        grid[5][4] = model.intVar("cell-" + 5 + "-" + 4, 7);
        grid[3][8] = model.intVar("cell-" + 3 + "-" + 8, 2);
        grid[4][8] = model.intVar("cell-" + 4 + "-" + 8, 9);
        grid[5][6] = model.intVar("cell-" + 5 + "-" + 6, 8);
        grid[5][7] = model.intVar("cell-" + 5 + "-" + 7, 1);
        grid[6][2] = model.intVar("cell-" + 6 + "-" + 2, 3);
        grid[8][1] = model.intVar("cell-" + 8 + "-" + 1, 7);
        grid[6][3] = model.intVar("cell-" + 6 + "-" + 3, 9);
        grid[8][5] = model.intVar("cell-" + 8 + "-" + 5, 5);
        grid[6][6] = model.intVar("cell-" + 6 + "-" + 6, 6);
        grid[6][7] = model.intVar("cell-" + 6 + "-" + 7, 4);
        grid[7][8] = model.intVar("cell-" + 7 + "-" + 8, 8);
      

        // Ajout des contraintes pour s'assurer que chaque ligne, colonne et région de 3x3 contient une seule fois chaque chiffre de 1 à 9
        for (int i = 0; i < 9; i++) {
            model.allDifferent(grid[i]).post(); // contraintes sur les lignes
            model.allDifferent(getColumn(grid, i)).post(); // contraintes sur les colonnes
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                model.allDifferent(getRegion(grid, i, j)).post(); // contraintes sur les régions de 3x3
            }
        }

        // Recherche d'une solution
        long startTime = System.nanoTime();
        model.getSolver().findSolution();
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        WriteInFile t =new WriteInFile();
        t.writeInFile(""+totalTime,"ChocoTest");
        System.out.println("Temps d'exécution en nanosecondes : " + totalTime);

        // Affichage de la solution
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j].getValue() + " ");
            }
            System.out.println();
        }
    }

    // Méthode pour récupérer une colonne d'un tableau 2D
    private static IntVar[] getColumn(IntVar[][] array, int columnIndex) {
        IntVar[] column = new IntVar[array.length];
        for (int i = 0; i < array.length; i++) {
            column[i] = array[i][columnIndex];
        }
        return column;
    }
    
    private static IntVar[] getRegion(IntVar[][] array, int regionRow, int regionColumn) {
        IntVar[] region = new IntVar[9];
        int regionStartRow = regionRow * 3;
        int regionStartColumn = regionColumn * 3;
        int index = 0;
        for (int i = regionStartRow; i < regionStartRow + 3; i++) {
            for (int j = regionStartColumn; j < regionStartColumn + 3; j++) {
                region[index] = array[i][j];
                index++;
            }
        }
        return region;
    }
    
}

	
