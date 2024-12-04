import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class main1 {

  public static void main(String[] args) {
    HashMap<String, Integer> map = new HashMap<>();
    map.put("blue", 14);
    map.put("red", 12);
    map.put("green", 13);

    File input = new File("input2.txt");

    try (BufferedReader bf = new BufferedReader(new FileReader(input))) {
      String line;
      int sum = 0;

      while ((line = bf.readLine()) != null) {
        int ID = Integer.parseInt(line.split(": ")[0].split(" ")[1]);
        boolean possible = true;

        String[] picks = line.split(": ")[1].split("; ");
        for (String pick : picks) {
          if (!possible) break;
          String[] colors = pick.split(", ");
          for (String color : colors) {
            int amount = Integer.parseInt(color.split(" ")[0]);
            String c = color.split(" ")[1];

            if (map.get(c) < amount) {
              possible = false;
              break;
            }
          }
        }

        if (possible) sum += ID;
      }

      System.out.println(sum);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
