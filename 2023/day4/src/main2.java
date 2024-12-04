import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class main2 {
  public static void main(String[] args) {
    File file = new File("input4.txt");
    ArrayList<Card> allCards = new ArrayList<>();

    try (BufferedReader bf = new BufferedReader(new FileReader(file)) ) {
      String ligne;
      ArrayList<Card> list = new ArrayList<>();

      while ((ligne = bf.readLine()) != null) {
        String[] jeu = ligne.split(": ")[1].split(" \\| ");
        Card card = new Card(Integer.parseInt(ligne.split(": ")[0].split("\\s+")[1]));
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

        card.setWinningNumbers(winningNumbers);
        card.setPlayedNumbers(playedNumbers);

        list.add(card);
        allCards.add(card);
      }

      int index = 0;
      while (index != list.size() - 1) {
        Card card = list.get(index);
        testCard(card, list, allCards);

        index++;
      }

      System.out.println(list.size());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void testCard(Card card, ArrayList<Card> list, ArrayList<Card> allCards) {
    int matches = 0;
    for (int number : card.getPlayedNumbers()) {
      if (card.getWinningNumbers().contains(number))
        matches ++;
    }

    for (int i = card.getIndex() + 1; i <= card.getIndex() + matches; i++) {
      Card cardToAdd = allCards.get(i - 1);
      list.add(cardToAdd);
    }
  }
}
