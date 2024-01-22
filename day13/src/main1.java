import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class main1 {
  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();

    try(BufferedReader bf = new BufferedReader(new FileReader("input13.txt"))) {
      String line;
      while ((line = bf.readLine()) != null) {
        sb.append(line).append("\n");
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    ArrayList<ArrayList<ArrayList<String>>> mirrors = new ArrayList<>();
    String[] split = sb.toString().split("\n\n");
    for (int i = 0; i < split.length; i++) {
      String mirror = split[i];
      mirrors.add(new ArrayList<>());

      String[] strings = mirror.split("\n");
      for (int k = 0; k < strings.length; k++) {
        String s = strings[k];
        mirrors.get(i).add(new ArrayList<>());
        for (int j = 0; j < s.length(); j++) {
          mirrors.get(i).get(k).add(String.valueOf(s.charAt(j)));
        }
      }
    }

    int sum = 0;
    for (int i = 0; i < mirrors.size(); i++) {
      ArrayList<ArrayList<String>> mirror = mirrors.get(i);

      int horizontal = horizontal(mirror);
      int vertical = vertical(mirror);

      System.out.println(horizontal + " " + vertical);
      sum += horizontal * 100 + vertical;
    }

    System.out.println(sum);
  }

  public static int horizontal(ArrayList<ArrayList<String>> mirror) {
    for (int i = 0; i < mirror.size(); i ++) {
      ArrayList<String> currentline = mirror.get(i);

      if (i + 1 >= mirror.size()) {
        break;
      }

      ArrayList<String> nextline = mirror.get(i + 1);

      boolean isMirror = true;
      boolean smudge = false;
      for (int j = 0; j < currentline.size(); j++) {
        String current = currentline.get(j);
        String next = nextline.get(j);


        if (!current.equals(next)) {
          if (smudge) {
            isMirror = false;
            break;
          } else
            smudge = true;
        }
      }

      if (isMirror) {
        if ((i == 0 || i == mirror.size() - 2) && smudge) {
          return i + 1;
        }
        int indexC = i - 1;
        int indexN = i + 2;
        while (indexC >= 0 && indexN < mirror.size()) {
          currentline = mirror.get(indexC);
          nextline = mirror.get(indexN);

          for (int j = 0; j < currentline.size(); j++) {
            String current = currentline.get(j);
            String next = nextline.get(j);

            if (!current.equals(next)) {
              if (smudge) {
                isMirror = false;
                break;
              } else
                smudge = true;
            }
          }

          if (!isMirror) {
            break;
          }

          if ((indexC == 0 || indexN == mirror.size() - 1) && smudge) {
            return i + 1;
          }
          indexC --;
          indexN ++;
        }
      }
    }

    return 0;
  }

  public static int vertical(ArrayList<ArrayList<String>> mirror) {
    for (int i = 0; i < mirror.get(0).size(); i++) {
      boolean isMirror = true;
      boolean smudge = false;
      for (ArrayList<String> strings : mirror) {

        if (i + 1 >= strings.size()) {
          isMirror = false;
          break;
        }

        if (!strings.get(i).equals(strings.get(i + 1))) {
          if (smudge) {
            isMirror = false;
            break;
          } else
            smudge = true;
        }
      }

      if (isMirror) {
        if ((i == 0 || i + 1 == mirror.get(0).size() - 1) && smudge) {
          return i + 1;
        }
        int indexC = i - 1;
        int indexN = i + 2;
        while (indexC >= 0 && indexN < mirror.get(0).size()) {
          for (ArrayList<String> strings : mirror) {
            if (!strings.get(indexC).equals(strings.get(indexN))) {
              if (smudge) {
                isMirror = false;
                break;
              } else
                smudge = true;
            }
          }

          if (!isMirror) {
            break;
          }

          if ((indexC == 0 || indexN == mirror.get(0).size() - 1) && smudge) {
            return i + 1;
          }
          indexC --;
          indexN ++;
        }
      }
    }

    return 0;
  }
}
