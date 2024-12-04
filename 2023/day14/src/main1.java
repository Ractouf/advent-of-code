import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class main1 {

  public static void main(String[] args) {
    List<List<Character>> list = new ArrayList<>();

    try(BufferedReader bf = new BufferedReader(new FileReader("input14.txt"))) {
      String line;
      while ((line = bf.readLine()) != null) {
        List<Character> parts = new ArrayList<>();
        for (char c : line.toCharArray()) {
          parts.add(c);
        }
        list.add(parts);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    HashSet<List<List<Character>>> seen = new HashSet<>();
    ArrayList<List<List<Character>>> states = new ArrayList<>();

    int l = 0;
    while (true) {
      l += 1;
      if (seen.contains(list)) {
        break;
      }

      List<List<Character>> copy = new ArrayList<>();
      for (List<Character> sublist : list) {
        copy.add(new ArrayList<>(sublist));
      }
      seen.add(copy);
      states.add(copy);

      for (int i = 0; i < list.size(); i++) {
        for (int j = 0; j < list.get(0).size(); j++) {
          if (list.get(i).get(j) == 'O') {
            list.get(i).set(j, '.');
            rollStoneNorth(list, i, j);
          }
        }
      }

      for (int i = 0; i < list.size(); i++) {
        for (int j = 0; j < list.get(0).size(); j++) {
          if (list.get(i).get(j) == 'O') {
            list.get(i).set(j, '.');
            rollStoneWest(list, i, j);
          }
        }
      }

      for (int i = list.size() - 1; i >= 0; i--) {
        for (int j = 0; j < list.get(0).size(); j++) {
          if (list.get(i).get(j) == 'O') {
            list.get(i).set(j, '.');
            rollStoneSouth(list, i, j);
          }
        }
      }

      for (int i = 0; i < list.size(); i++) {
        for (int j = list.get(0).size() - 1; j >= 0 ; j--) {
          if (list.get(i).get(j) == 'O') {
            list.get(i).set(j, '.');
            rollStoneEast(list, i, j);
          }
        }
      }
    }

    int first = states.indexOf(list);
    List<List<Character>> result = states.get((1000000000 - first) % (l - 1 - first) + first);

    int sum = 0;
    for (int i = 0; i < result.size(); i++) {
      for (int j = 0; j < result.get(0).size(); j++) {
        if (result.get(i).get(j) == 'O') {
          sum += result.size() - i;
        }
      }
    }

    System.out.println(sum);
  }

  public static void rollStoneNorth(List<List<Character>> list, int i, int j) {
    if (i <= 0 || list.get(i - 1).get(j) == '#' || list.get(i - 1).get(j) == 'O') {
      list.get(i).set(j, 'O');
      return;
    }

    if (list.get(i - 1).get(j) == '.') {
      rollStoneNorth(list, i - 1, j);
    }
  }

  public static void rollStoneEast(List<List<Character>> list, int i, int j) {
    if (j >= list.get(0).size() - 1 || list.get(i).get(j + 1) == '#' || list.get(i).get(j + 1) == 'O') {
      list.get(i).set(j, 'O');
      return;
    }

    if (list.get(i).get(j + 1) == '.') {
      rollStoneEast(list, i, j + 1);
    }
  }

  public static void rollStoneSouth(List<List<Character>> list, int i, int j) {
    if (i >= list.size() - 1 || list.get(i + 1).get(j) == '#' || list.get(i + 1).get(j) == 'O') {
      list.get(i).set(j, 'O');
      return;
    }

    if (list.get(i + 1).get(j) == '.') {
      rollStoneSouth(list, i + 1, j);
    }
  }

  public static void rollStoneWest(List<List<Character>> list, int i, int j) {
    if (j <= 0 || list.get(i).get(j - 1) == '#' || list.get(i).get(j - 1) == 'O') {
      list.get(i).set(j, 'O');
      return;
    }

    if (list.get(i).get(j - 1) == '.') {
      rollStoneWest(list, i, j - 1);
    }
  }
}
