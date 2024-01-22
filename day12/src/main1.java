import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class main1 {

  public static void main(String[] args) {
    try(BufferedReader bf = new BufferedReader(new FileReader("input12.txt"))) {
      String line;
      long total = 0;
      while ((line = bf.readLine()) != null) {
        String[] parts = line.split(" ");
        String unFoldedMap = String.join("?", Collections.nCopies(5, parts[0]));
        String unFoldedNums = String.join(",", Collections.nCopies(5, parts[1]));

        int[] groups = Arrays.stream(unFoldedNums.split(",")).mapToInt(Integer::parseInt).toArray();

        HashMap<Spring, Long> alreadySeen = new HashMap<>();
        total += count(new Spring(unFoldedMap, groups), alreadySeen);
      }

      System.out.println(total);
    } catch (
        IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static long count(Spring spring, HashMap<Spring, Long> alreadySeen) {
    String map = spring.getMap();
    int[] groups = spring.getGroups();

    if (map.isEmpty()) {
      return groups.length == 0 ? 1 : 0;
    }

    if (groups.length == 0) {
      return map.contains("#") ? 0 : 1;
    }

    if (alreadySeen.containsKey(spring)) {
      return alreadySeen.get(spring);
    }

    long result = 0;

    if (map.charAt(0) == '.' || map.charAt(0) == '?') {
      result += count(new Spring(map.substring(1), groups), alreadySeen);
    }

    if (map.charAt(0) == '#' || map.charAt(0) == '?') {
      if (groups[0] <= map.length() && !map.substring(0, groups[0]).contains(".") && (groups[0] == map.length() || map.charAt(groups[0]) != '#')) {
        if  (groups[0] == map.length())
          result += count(new Spring("", Arrays.copyOfRange(groups, 1, groups.length)), alreadySeen);
        else
          result += count(new Spring(map.substring(groups[0] + 1), Arrays.copyOfRange(groups, 1, groups.length)), alreadySeen);
      }
    }

    alreadySeen.put(spring, result);
    return result;
  }
}
