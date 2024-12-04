import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class main1 {
  public static void main(String[] args) {
    File input = new File("input1.txt");

    try (BufferedReader bf = new BufferedReader(new FileReader(input))) {
      String line;
      int sum = 0;

      while ((line = bf.readLine()) != null) {
        char lastDigit = 0;
        char firstDigit = 0;

        for (char c : line.toCharArray()) {
          if (Character.isDigit(c)) {
            if (firstDigit == 0)
              firstDigit = c;
            lastDigit = c;
          }
        }

        String number = firstDigit + "" + lastDigit;
        sum += Integer.parseInt(number);
      }

      System.out.println(sum);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
