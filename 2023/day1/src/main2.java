import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main2 {
  public static void main(String[] args) {
    HashMap<String, Character> map = new HashMap<>();
    map.put("one", '1');
    map.put("two", '2');
    map.put("three", '3');
    map.put("four", '4');
    map.put("five", '5');
    map.put("six", '6');
    map.put("seven", '7');
    map.put("eight", '8');
    map.put("nine", '9');
    Pattern pattern = Pattern.compile("(?=(one|two|three|four|five|six|seven|eight|nine))");

    File input = new File("input1.txt");

    try (BufferedReader bf = new BufferedReader(new FileReader(input))) {
      String line;
      int sum = 0;

      while ((line = bf.readLine()) != null) {
        DigitPos lastDigit = null;
        DigitPos firstDigit = null;

        char[] charArray = line.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
          char c = charArray[i];
          if (Character.isDigit(c)) {
            if (firstDigit == null) {
              firstDigit = new DigitPos(c, i);
            }
            lastDigit = new DigitPos(c, i);
          }
        }

        ArrayList<DigitPos> matches = new ArrayList<>();

        Matcher m = pattern.matcher(line);
        while (m.find()) {
          matches.add(new DigitPos(map.get(m.group(1)), m.start()));
        }

        for (DigitPos match : matches) {
          if (firstDigit == null) {
            firstDigit = match;
            lastDigit = match;
          }
          if (match.getPos() < firstDigit.getPos()) firstDigit = match;
          if (match.getPos() > lastDigit.getPos()) lastDigit = match;
        }

        sum += Integer.parseInt(firstDigit.getDigit() + "" + lastDigit.getDigit());
      }

      System.out.println(sum);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
