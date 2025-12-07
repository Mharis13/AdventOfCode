import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class day05 {
  static long part1(List<String> input) {
    ArrayList<String> idRanges = new ArrayList<>();

    for (String s : input) {
      if (!s.isEmpty()) {
        idRanges.add(s);
      } else {
        break;
      }
    }
    ArrayList<String> ids = new ArrayList<>();

    for (String s : input) {
      if (s.isEmpty()) {
        ids.clear();
      } else {
        ids.add(s);
      }
    }

    long flesh = 0;

    for (String id : ids) {
      long idNumber = Long.parseLong(id);

      for (String idRange : idRanges) {
        String[] range = idRange.split("-");

        long firstId = Long.parseLong(range[0]);
        long lastId = Long.parseLong(range[1]);

        if (idNumber >= firstId && idNumber <= lastId) {
          flesh++;
          break;
        }

      }
    }
    return flesh;

  }

  // Clase interna simple para manejar los intervalos (rango inicial y final)
  private static class Range {
    long start;
    long end;

    public Range(long start, long end) {
      this.start = start;
      this.end = end;
    }
  }

  static long part2(List<String> input) {
    ArrayList<String> idRangesStr = new ArrayList<>();

    // 1. Extraer los rangos de entrada
    for (String s : input) {
      if (!s.isEmpty()) {
        idRangesStr.add(s);
      } else {
        break; // Se detiene en la primera línea vacía
      }
    }

    // 2. Convertir String[] a una lista de objetos Range
    List<Range> ranges = new ArrayList<>();
    for (String idRangeStr : idRangesStr) {
      String[] range = idRangeStr.split("-");
      long start = Long.parseLong(range[0]);
      long end = Long.parseLong(range[1]);
      ranges.add(new Range(start, end));
    }

    // 3. Ordenar los rangos por el punto de inicio (Start)
    Collections.sort(ranges, Comparator.comparingLong(r -> r.start));

    if (ranges.isEmpty()) {
      return 0;
    }

    // 4. Fusionar los rangos solapados
    List<Range> mergedRanges = new ArrayList<>();
    Range currentRange = ranges.get(0);

    for (int i = 1; i < ranges.size(); i++) {
      Range nextRange = ranges.get(i);

      // Si hay solapamiento o son adyacentes (next.start <= current.end + 1)
      if (nextRange.start <= currentRange.end + 1) {
        // Fusionar: Extender el final del rango actual hasta el máximo
        currentRange.end = Math.max(currentRange.end, nextRange.end);
      } else {
        // No hay solapamiento: Añadir el rango fusionado y empezar uno nuevo
        mergedRanges.add(currentRange);
        currentRange = nextRange;
      }
    }
    mergedRanges.add(currentRange); // Añadir el último rango fusionado

    // 5. Contar el total de IDs únicos
    long totalCount = 0;

    for (Range r : mergedRanges) {
      // Aplicar la fórmula simple a los rangos ya fusionados: B - A + 1
      totalCount += (r.end - r.start + 1);
    }

    return totalCount;
  }

  public static void main(String[] args) throws Exception {

    List<String> input = Files.readAllLines(Path.of("input.txt"));
    long part1 = part1(input);
    long part2 = part2(input);
    System.out.println("Part 1: " + part1);
    System.out.println("Part 2:" + part2);
  }
}
