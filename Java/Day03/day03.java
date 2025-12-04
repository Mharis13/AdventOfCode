import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * day03
 */
public class day03 {
  static int part1(List<String> input) {
    int b1 = 0;
    int b2 = 0;
    int joltage = 0;
    for (String banks : input) {
      String[] batteries = banks.split("");

      for (int i = 0; i < batteries.length - 1; i++) {
        b1 = Math.max(b1, Integer.parseInt(batteries[i]));
      }
      int idx = -1;
      for (int i = 0; i < batteries.length; i++) {
        if (Integer.parseInt(batteries[i]) == b1) {
          idx = i;
          break;
        }
      }
      idx++;
      for (int i = idx; i < batteries.length; i++) {
        b2 = Math.max(b2, Integer.parseInt(batteries[i]));

      }
      joltage += Integer.parseInt(String.valueOf(b1) + String.valueOf(b2));
      b1 = 0;
      b2 = 0;

    }
    return joltage;
  }

  static long part2(List<String> input) {
    String build = "";
    long joltage = 0;
    int idx = -1;
    for (String banks : input) {
      String[] batteries = banks.split("");
      for (int i = 0; i < 12; i++) {
        int b = 0;
        int limit = batteries.length - (12 - i);
        for (int j = idx + 1; j <= limit; j++) {
          b = Math.max(b, Integer.parseInt(batteries[j]));
        }

        for (int j = idx + 1; j < batteries.length; j++) {
          if (b == Integer.parseInt(batteries[j])) {
            idx = j;
            break;
          }
        }
        build += String.valueOf(b);
      }
      idx = -1;
      joltage += Long.parseLong(build);
      build = "";
    }
    return joltage;
  }

  public static void main(String[] args) throws Exception {
    List<String> input = Files.readAllLines(Path.of("input.txt"));

    int part1 = part1(input);
    long part2 = part2(input);
    System.out.println("PArt 1: " + part1);
    System.out.println("Part 2: " + part2);
  }
}
