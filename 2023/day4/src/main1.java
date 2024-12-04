import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class main1 {
  public static void main(String[] args) {
    File file = new File("input4.txt");
    try (BufferedReader bf = new BufferedReader(new FileReader(file)) ) {
      String ligne;
      int sum = 0;
      while ((ligne = bf.readLine()) != null) {
        String[] jeu = ligne.split(": ")[1].split(" \\| ");
        String winning = jeu[0].stripLeading();
        String played = jeu[1].stripLeading();

        ArrayList<Integer> winningNumbers = new ArrayList<>();
        ArrayList<Integer> playedNumbers = new ArrayList<>();

        for (String n : winning.split("\\s+")) {
          winningNumbers.add(Integer.parseInt(n));
        }

        for (String n : played.split("\\s+")) {
          playedNumbers.add(Integer.parseInt(n));
        }

        int matches = 0;
        for (int number : playedNumbers) {
          if (winningNumbers.contains(number))
            matches ++;
        }

        sum += (int) Math.pow(2, matches - 1);
      }

      System.out.println(sum);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
