import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * day04
 */
public class day04 {
  private static final int[][] movements = {
      { -1, -1 }, { -1, 0 }, { -1, 1 }, // Fila superior
      { 0, -1 }, { 0, 1 }, // Misma fila
      { 1, -1 }, { 1, 0 }, { 1, 1 } // Fila inferior
  };

  static int part1(String[][] input) {
    int rolls = 0;
    int count = 0;
    for (int i = 0; i < input.length; i++) {
      for (int j = 0; j < input[i].length; j++) {
        if (input[i][j].equals("@")) {
          for (int[] movement : movements) {
            int m1 = movement[0];
            int m2 = movement[1];
            int row = i + m1;
            int col = j + m2;

            if (row >= 0 && row < input.length && col >= 0 && col < input.length) {
              if (input[row][col].equals("@")) {
                count++;
              }
            }
          }
          if (count < 4) {
            rolls++;
          }
        }
        count = 0;
      }

    }
    return rolls;
  }

  static int part2(String[][] input) {
    int rolls = 0;
    int count = 0;
    int removed = 0;
    ArrayList<int[]> coord = new ArrayList<>();

    do {
      rolls = 0;
      for (int i = 0; i < input.length; i++) {
        for (int j = 0; j < input[i].length; j++) {
          if (input[i][j].equals("@")) {
            for (int[] movement : movements) {
              int m1 = movement[0];
              int m2 = movement[1];
              int row = i + m1;
              int col = j + m2;

              if (row >= 0 && row < input.length && col >= 0 && col < input.length) {
                if (input[row][col].equals("@")) {
                  count++;
                }
              }
            }
            if (count < 4) {
              rolls++;
              int[] aux = { i, j };
              coord.add(aux);
            }
          }
          count = 0;
        }

      }

      for (int[] i : coord) {
        input[i[0]][i[1]] = "x";
      }
      removed += rolls;

    } while (rolls != 0);
    return removed;
  }

  public static void main(String[] args) throws Exception {
    String input = "input.txt";

    Path path = Path.of(input);
    List<String> lineasList = Files.readAllLines(path);

    if (lineasList.isEmpty()) {
      System.out.println("El archivo está vacío. No se puede procesar.");
      return;
    }

    int numFilas = lineasList.size();
    int numCols = lineasList.get(0).length();

    String[][] mapaInicial = new String[numFilas][numCols];

    for (int r = 0; r < numFilas; r++) {
      String linea = lineasList.get(r);
      for (int c = 0; c < numCols; c++) {
        mapaInicial[r][c] = String.valueOf(linea.charAt(c));
      }
    }

    int part1 = part1(mapaInicial);
    int part2 = part2(mapaInicial);

    System.out.println("Part 1: " + part1);
    System.out.println("Part 2: " + part2);

  }
}
