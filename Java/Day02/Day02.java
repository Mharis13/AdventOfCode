import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Day02
 */
public class Day02 {
  static long part1(String[] idRanges) {
    long invalidIds = 0;
    for (String idRange : idRanges) {
      String[] ids = idRange.split("-");
      long firstId = Long.parseLong(ids[0]);
      long lastId = Long.parseLong(ids[1]);

      for (long i = firstId; i <= lastId; i++) {
        String id = String.valueOf(i);
        int n = id.length();

        if (n % 2 == 0) {
          String firstHalfId = id.substring(0, n / 2);
          String secondHalfId = id.substring(n / 2);
          if (firstHalfId.equals(secondHalfId)) {
            invalidIds += Long.parseLong(id);
          }
        }
      }
    }

    return invalidIds;
  }

  static long part2(String[] idRanges) {
    long invalidIds = 0;
    for (String idRange : idRanges) {
      String[] ids = idRange.split("-");
      long firstId = Long.parseLong(ids[0]);
      long lastId = Long.parseLong(ids[1]);
      for (long i = firstId; i <= lastId; i++) {
        if (isInvalid(String.valueOf(i))) {
          invalidIds += i;
        }
      }
    }
    return invalidIds;
  }

  static boolean isInvalid(String id) {
    int n = id.length();

    for (int patternLength = 1; patternLength <= n / 2; patternLength++) {
      if (n % patternLength == 0) {
        String pattern = id.substring(0, patternLength);
        boolean isValid = true;

        for (int j = patternLength; j < n; j += patternLength) {
          String segment = id.substring(j, j + patternLength);
          if (!segment.equals(pattern)) {
            isValid = false;
            break;
          }
        }

        if (isValid) {
          return true;
        }
      }
    }

    return false;
  }

  public static void main(String[] args) throws Exception {

    List<String> input = Files.readAllLines(Path.of("input.txt"));
    String allLines = String.join("", input);
    String[] idRanges = allLines.split(",");
    long part1 = part1(idRanges);
    long part2 = part2(idRanges);
    System.out.println("Part 1: " + part1);
    System.out.println("Part 2: " + part2);
  }
}
