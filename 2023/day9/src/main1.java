import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class main1 {
  public static void main(String[] args) {
    try (BufferedReader bf = new BufferedReader(new FileReader("input9.txt"))) {
      String line;
      int sum = 0;

      while ((line = bf.readLine()) != null) {
        ArrayList<ArrayList<Integer>> seq = new ArrayList<>();
        seq.add(new ArrayList<>());

        for (String s : line.split(" ")) seq.get(0).add(Integer.parseInt(s));

        int index = 0;
        while (!checkAll0(seq.get(index))) {
          seq.add(new ArrayList<>());
          for (int i = 0; i < seq.get(index).size() - 1; i++) {
            seq.get(index + 1).add(seq.get(index).get(i + 1) - seq.get(index).get(i));
          }

          index ++;
        }

        seq.get(seq.size() - 1).add(0, 0);
        for (int i = seq.size() - 2; i >= 0; i--) {
          seq.get(i).add(0,seq.get(i).get(0)  - seq.get(i + 1).get(0));
        }

        sum += seq.get(0).get(0);
      }

      System.out.println(sum);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static boolean checkAll0(ArrayList<Integer> list) {
    for (int i : list) {
      if (i != 0) return false;
    }

    return true;
  }
}
