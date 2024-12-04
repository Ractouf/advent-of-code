import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class main1 {
  public static void main(String[] args) {
    try(BufferedReader bf = new BufferedReader(new FileReader("input15.txt"))) {
      String line;
      while ((line = bf.readLine()) != null) {
        int sum = 0;
        for (String s : line.split(",")) {
          int current = 0;
          for (char c : s.toCharArray()) {
            int ascii = c;
            int calc = current + ascii;
            int res = calc * 17;
            current = res % 256;
          }
          sum += current;
        }
        System.out.println(sum);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
