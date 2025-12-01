import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Day01
 */
public class Day01 {
  static int part01(List<String> movements) throws Exception {
    int number = 0;
    int password = 0;
    int dial = 50;
    for (String movement : movements) {
      if (movement.startsWith("L")) {
        number = Integer.parseInt(movement.substring(1));
        dial = (dial - number) % 100;
        if (dial < 0) {
          dial += 100;
        }
        if (dial == 0) {
          password++;
        }

      } else if (movement.startsWith("R")) {
        number = Integer.parseInt(movement.substring(1));
        dial = (dial + number) % 100;
        if (dial == 0) {
          password++;
        }
      }

    }
    return password;

  }

  static int part02(List<String> movements) throws Exception {
    int number = 0;
    int password = 0;
    int aux = 0;
    int dial = 50;
    for (String movement : movements) {
      aux = dial;
      if (movement.startsWith("L")) {
        number = Integer.parseInt(movement.substring(1));
        for (int i = 1; i <= number; i++) {
          aux--;
          if (aux < 0) {
            aux = 99;
          }
          if (aux == 0) {
            password++;
          }
        }
        dial = (dial - number) % 100;
        if (dial < 0) {
          dial += 100;
        }

      } else if (movement.startsWith("R")) {
        number = Integer.parseInt(movement.substring(1));
        for (int i = 1; i <= number; i++) {
          aux++;
          if (aux > 99) {
            aux = 0;
            password++;
          }
        }
        dial = (dial + number) % 100;
      }

    }
    return password;

  }

  public static void main(String[] args) throws Exception {
    List<String> input = Files.readAllLines(Path.of("input.txt"));
    int part01 = part01(input);
    int part02 = part02(input);
    System.out.println("Part 1: " + part01);
    System.out.println("Part 2: " + part02);
  }
}
