import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class main1 {
  public static void main(String[] args) {
    File inpute = new File("input3.txt");
    String input = "";

    try (BufferedReader bf = new BufferedReader(new FileReader(inpute))) {
      String line;


      while ((line = bf.readLine()) != null) {
        input += line + "\n";
      }

      input = input.substring(0, input.length() - 1);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    String[] split = input.split("\n");
    int lineSize = split[0].length();
    String[][] tab = new String[split.length][lineSize];


    for (int i = 0; i < split.length; i++) {
      String line = split[i];
      char[] charArray = line.toCharArray();
      for (int j = 0; j < charArray.length; j++) {
        char c = charArray[j];
        tab[i][j] = String.valueOf(c);
      }
    }

    ArrayList<Integer> findings = new ArrayList<>();

    // chaque ligne
    for (int i = 0; i < tab.length; i++) {
      String[] line = tab[i];
      // chaque char
      for (int j = 0; j < line.length; j++) {
        String c = line[j];
        // si symbole
        if (!Character.isDigit(c.charAt(0)) && !c.equals(".")) {
          // regarde en haut et en bas 3 fois -1 1 +1
          ArrayList<Integer> thisLineFindings = new ArrayList<>();
          for (int k = -1; k <= 1; k++) {
            char testUp = tab[i - 1][j + k].charAt(0);
            int indexUp = j + k;

            char testDown = tab[i + 1][j + k].charAt(0);
            int indexDown = j + k;

            // j'ai trouvé digit
            if (Character.isDigit(testUp)) {
              // remonte le number
              while (Character.isDigit(tab[i - 1][indexUp].charAt(0))) {

                if (indexUp - 1 == -1 || !Character.isDigit(tab[i - 1][indexUp - 1].charAt(0)))
                  break;
                indexUp -= 1;
              }

              // affiche le number
              String sb = "";
              while (Character.isDigit(tab[i - 1][indexUp].charAt(0))) {
                sb += tab[i - 1][indexUp];

                if (indexUp > lineSize - 1 || !Character.isDigit(tab[i - 1][indexUp + 1].charAt(0))) {
                  break;
                }
                indexUp ++;
              }

              if (!thisLineFindings.contains(Integer.parseInt(sb)))
                thisLineFindings.add(Integer.parseInt(sb));
            }
            if (Character.isDigit(testDown)) {
              while (Character.isDigit(tab[i + 1][indexDown].charAt(0))) {

                if (indexDown - 1 == -1 || !Character.isDigit(tab[i + 1][indexDown - 1].charAt(0)))
                  break;
                indexDown -= 1;
              }

              String sb = "";
              while (Character.isDigit(tab[i + 1][indexDown].charAt(0))) {
                sb += tab[i + 1][indexDown];

                if (indexDown > lineSize - 1 || !Character.isDigit(tab[i + 1][indexDown + 1].charAt(0))) {
                  break;
                }
                indexDown ++;
              }

              if (!thisLineFindings.contains(Integer.parseInt(sb)))
                thisLineFindings.add(Integer.parseInt(sb));
            }
          }

          if (Character.isDigit(tab[i][j - 1].charAt(0))) {
            int index = j - 1;
            String sb = "";
            while (Character.isDigit(tab[i][index].charAt(0))) {
              sb = tab[i][index] + sb;
              if (index - 1 == -1 || !Character.isDigit(tab[i][index - 1].charAt(0)))
                break;
              index -= 1;
            }
            thisLineFindings.add(Integer.parseInt(sb));
          }
          if (Character.isDigit(tab[i][j + 1].charAt(0))) {
            int index = j + 1;
            String sb = "";
            while (Character.isDigit(tab[i][index].charAt(0))) {
              sb += tab[i][index];
              if (index + 1 > lineSize - 1 || !Character.isDigit(tab[i][index + 1].charAt(0)))
                break;
              index += 1;
            }
            thisLineFindings.add(Integer.parseInt(sb));
          }
          findings.addAll(thisLineFindings);
        }
      }
    }
    int sum = 0;
    for (int i : findings) {
      sum += i;
    }

    System.out.println(sum);
  }
}
