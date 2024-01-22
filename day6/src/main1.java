import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class main1 {

  public static void main(String[] args) {
    File input = new File("input6.txt");

    try(BufferedReader bf = new BufferedReader(new FileReader(input))) {
      String line;

      long time = 0;
      long distance = 0;
      while ((line = bf.readLine()) != null) {
        String[] l = line.split(":");
        if (l[0].startsWith("Time")) {
          time = Long.parseLong(l[1].replaceAll(" ", ""));
        } else {
          distance = Long.parseLong(l[1].replaceAll(" ", ""));
        }
      }

      long records = 0;
      for (long i = 0; i < time; i++) {
        if ((i * (time - i)) > distance) {
          records++;
        }
      }
      /*int res = 1;
      for (int i = 0; i < times.size(); i++) {
        int records = 0;
        for (int j = 0; j < times.get(i); j++) {
          if ((j * (times.get(i) - j)) > distance.get(i)) {
            records++;
          }
        }
        res *= records;
      }*/

      System.out.println(records);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
