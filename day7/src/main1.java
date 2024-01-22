import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main1 {
  private static final Map<Character, Character> letterMap = new HashMap<>();
  static {
    letterMap.put('T', 'A');
    letterMap.put('J', '.');
    letterMap.put('Q', 'C');
    letterMap.put('K', 'D');
    letterMap.put('A', 'E');
  }

  private static final char[] RANKS = "23456789ACDE".toCharArray();

  public static int score(String hand) {
    int[] counts = new int[128];

    for (char card : hand.toCharArray()) {
      counts[card]++;
    }

    if (contains(counts, 5)) {
      return 6;
    }
    if (contains(counts, 4)) {
      return 5;
    }
    if (contains(counts, 3)) {
      if (contains(counts, 2)) {
        return 4;
      }
      return 3;
    }
    if (countOccurrences(counts, 2) == 2) {
      return 2;
    }
    if (contains(counts, 2)) {
      return 1;
    }
    return 0;
  }

  public static List<String> replacements(String hand) {
    List<String> result = new ArrayList<>();

    if (hand.isEmpty()) {
      result.add("");
    } else {
      char firstCard = hand.charAt(0);
      String remainingHand = hand.substring(1);

      char[] choices = (firstCard == '.') ? RANKS : new char[]{firstCard};

      for (char x : choices) {
        for (String y : replacements(remainingHand)) {
          result.add(x + y);
        }
      }
    }

    return result;
  }

  public static int classify(String hand) {
    return replacements(hand).stream().mapToInt(main1::score).max().orElse(0);
  }

  public static int strength(String hand) {
    return classify(hand);
  }

  public static String translate(String hand) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < hand.length(); i++) {
      char card = hand.charAt(i);
      sb.append(letterMap.getOrDefault(card, card));
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    ArrayList<day7.src.Hand> hands = new ArrayList<>();
    try(BufferedReader bf = new BufferedReader(new FileReader("day7/input7.txt"))) {
      String line;

      while ((line = bf.readLine()) != null) {
        String[] l = line.trim().split(" ");
        hands.add(new day7.src.Hand(l[0], Integer.parseInt(l[1])));
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    hands.sort(Comparator.comparing((day7.src.Hand a) -> strength(translate(a.getHand()))).thenComparing(a -> translate(a.getHand())));

    int index = 1;
    int sum = 0;
    for (day7.src.Hand hand : hands) {
      sum += hand.getBet() * index;
      index++;
    }

    System.out.println(sum);
  }

  private static boolean contains(int[] counts, int value) {
    for (int count : counts) {
      if (count == value) {
        return true;
      }
    }
    return false;
  }

  private static int countOccurrences(int[] counts, int value) {
    int occurrences = 0;
    for (int count : counts) {
      if (count == value) {
        occurrences++;
      }
    }
    return occurrences;
  }
}
