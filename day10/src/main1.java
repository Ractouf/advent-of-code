import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class main1 {
  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();

    try(BufferedReader bf = new BufferedReader(new FileReader("input10.txt"))) {
      String line;
      while ((line = bf.readLine()) != null) {
        sb.append(line).append("\n");
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    String[] lines = sb.toString().split("\n");

    String[][] map = new String[lines.length][lines[0].length()];
    int startI = 0;
    int startJ = 0;
    for (int i = 0; i < lines.length; i++) {
      for (int j = 0; j < lines[0].length(); j++) {
        String c = lines[i].substring(j, j + 1);
        if (c.equals("S")) {
          startI = i;
          startJ = j;
        }
        map[i][j] = c;
      }
    }

    ArrayList<Item> alreadyFound = new ArrayList<>();
    Item currentItem = new Item("S", startI, startJ);
    int steps = 0;
    do {
      currentItem = connectsTo(alreadyFound, currentItem, map);

      steps++;
    } while (currentItem != null);

    Item up = null;
    ArrayList<Item> stuckItems = new ArrayList<>();
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        Item item = new Item(map[i][j], i, j);

        if (alreadyFound.contains(item)) {
          Item it = alreadyFound.get(alreadyFound.indexOf(item));

          if (it.getGoingUp() == 1) {
            up = item;
          } else if (it.getGoingUp() == 0) {
            up = null;
          }
        } else if (up != null) {
          stuckItems.add(item);
        }
      }
      up = null;
    }

    System.out.println(steps / 2);
    System.out.println(stuckItems.size());
  }

  public static Item connectsTo(ArrayList<Item> alreadyFound, Item item, String[][] map) {
    Item newItem = null;

    int i = item.getI();
    int j = item.getJ();
    String it = item.getItem();

    if (it.equals("|") || it.equals("L") || it.equals("J") || it.equals("S")) {
        newItem = checkUp(i, j, map, alreadyFound, item);
    }
    if (it.equals("|") || it.equals("7") || it.equals("F") || it.equals("S")) {
      if (newItem == null)
        newItem = checkDown(i, j, map, alreadyFound, item);
    }
    if (it.equals("-") || it.equals("F") || it.equals("L") || it.equals("S")) {
      if (newItem == null)
        newItem = checkRight(i, j, map, alreadyFound);
    }
    if (it.equals("-") || it.equals("7") || it.equals("J") || it.equals("S")) {
      if (newItem == null)
        newItem = checkLeft(i, j, map, alreadyFound);
    }

    alreadyFound.add(newItem);

    if (it.equals("S"))
      alreadyFound.add(item);

    return newItem;
  }

  public static Item checkUp(int i, int j, String[][] map, ArrayList<Item> alreadyFound, Item item) {
    if (i - 1 >= 0)
      if (!alreadyFound.contains(new Item(map[i - 1][j], i - 1, j)))
        if (map[i - 1][j].equals("F") || map[i - 1][j].equals("7") || map[i - 1][j].equals("|")) {
          Item ni = new Item(map[i - 1][j], i - 1, j);
          ni.setGoingUp(1);
          return ni;
        }
    return null;
  }
  public static Item checkDown(int i, int j, String[][] map, ArrayList<Item> alreadyFound, Item item) {
    if (i + 1 < map.length)
      if (!alreadyFound.contains(new Item(map[i + 1][j], i + 1, j)))
        if (map[i + 1][j].equals("|") || map[i + 1][j].equals("L") || map[i + 1][j].equals("J")) {
          item.setGoingUp(0);
          return new Item(map[i + 1][j], i + 1, j);
        }
    return null;
  }
  public static Item checkLeft(int i, int j, String[][] map, ArrayList<Item> alreadyFound) {
    if (j - 1 >= 0)
      if (!alreadyFound.contains(new Item(map[i][j - 1], i, j - 1)))
        if (map[i][j - 1].equals("-") || map[i][j - 1].equals("F") || map[i][j - 1].equals("L")) {
          return new Item(map[i][j - 1], i, j - 1);
        }
    return null;
  }
  public static Item checkRight(int i, int j, String[][] map, ArrayList<Item> alreadyFound) {
    if (j + 1 < map[0].length)
      if (!alreadyFound.contains(new Item(map[i][j + 1], i, j + 1)))
        if (map[i][j + 1].equals("-") || map[i][j + 1].equals("7") || map[i][j + 1].equals("J"))
          return new Item(map[i][j + 1], i, j + 1);
    return null;
  }
}
