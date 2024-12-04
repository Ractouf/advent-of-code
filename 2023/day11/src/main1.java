import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class main1 {

  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();

    try(BufferedReader bf = new BufferedReader(new FileReader("input11.txt"))) {
      String line;
      while ((line = bf.readLine()) != null) {
        sb.append(line).append("\n");
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    String[] l = sb.toString().split("\n");
    ArrayList<ArrayList<String>> galaxy = new ArrayList<>();

    for (int i = 0; i < l.length; i++) {
      galaxy.add(new ArrayList<>());
      for (char c : l[i].toCharArray()) {
        galaxy.get(i).add(String.valueOf(c));
      }
    }

    // check empty rows
    ArrayList<Integer> emptyRows = new ArrayList<>();
    for (int i = 0; i < galaxy.size(); i++) {
      boolean emptyRow = true;
      for (int j = 0; j < galaxy.get(i).size(); j++) {
        if (!galaxy.get(i).get(j).equals(".")) {
          emptyRow = false;
          break;
        }
      }

      if (emptyRow) {
        emptyRows.add(i);
      }
    }

    // check empty columns
    ArrayList<Integer> emptyCols = new ArrayList<>();
    for (int i = 0; i < galaxy.get(0).size(); i++) {
      boolean emptyColumn = true;
      for (ArrayList<String> strings : galaxy) {
        if (!strings.get(i).equals(".")) {
          emptyColumn = false;
          break;
        }
      }

      if (emptyColumn) {
        emptyCols.add(i);
      }
    }

    // get every galaxy
    ArrayList<Galaxy> galaxies = new ArrayList<>();
    for (int i = 0; i < galaxy.size(); i++) {
      for (int j = 0; j < galaxy.get(i).size(); j++) {
        if (!galaxy.get(i).get(j).equals(".")) {
          galaxies.add(new Galaxy(i, j));
        }
      }
    }

    long sum = 0;
    for (int i = 0; i < galaxies.size(); i++) {
      for (int j = i + 1; j < galaxies.size(); j++) {
        Galaxy g1 = galaxies.get(i);
        Galaxy g2 = galaxies.get(j);

        long distance = Math.abs(g1.getI() - g2.getI()) + Math.abs(g1.getJ() - g2.getJ());

        // Check rows
        for (int row = Math.min(g1.getI(), g2.getI()); row <= Math.max(g1.getI(), g2.getI()); row++) {
          if (emptyRows.contains(row)) {
            distance += 999999;
          }
        }

        // Check columns
        for (int column = Math.min(g1.getJ(), g2.getJ()); column <= Math.max(g1.getJ(), g2.getJ()); column++) {
          if (emptyCols.contains(column)) {
            distance += 999999;
          }
        }

        sum += distance;
      }
    }

    System.out.println(sum);
  }
}
