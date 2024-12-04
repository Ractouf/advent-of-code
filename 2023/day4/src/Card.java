import java.util.ArrayList;

public class Card {
  int index;
  ArrayList<Integer> winningNumbers;
  ArrayList<Integer> playedNumbers;

  public Card(int index) {
    this.index = index;
  }

  public int getIndex() {
    return index;
  }

  public ArrayList<Integer> getWinningNumbers() {
    return winningNumbers;
  }

  public ArrayList<Integer> getPlayedNumbers() {
    return playedNumbers;
  }

  public void setWinningNumbers(ArrayList<Integer> winningNumbers) {
    this.winningNumbers = winningNumbers;
  }

  public void setPlayedNumbers(ArrayList<Integer> playedNumbers) {
    this.playedNumbers = playedNumbers;
  }
}
