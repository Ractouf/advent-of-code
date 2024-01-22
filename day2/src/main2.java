import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class main2 {
  public static void main(String[] args) {
    File input = new File("input2.txt");

    try (BufferedReader bf = new BufferedReader(new FileReader(input))) {
      String line;
      int sum = 0;

      while ((line = bf.readLine()) != null) {
        String[] picks = line.split(": ")[1].split("; ");
        HashMap<String, Integer> map = new HashMap<>();

        for (String pick : picks) {
          String[] colors = pick.split(", ");
          for (String color : colors) {
            String c = color.split(" ")[1];
            int amount = Integer.parseInt(color.split(" ")[0]);

            map.merge(c, amount, Integer::max);
          }
        }

        sum += map.getOrDefault("red", 0) * map.getOrDefault("green", 0) * map.getOrDefault("blue", 0);
      }

      System.out.println(sum);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
