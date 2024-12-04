import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class main1 {

  public static void main(String[] args) {
    try(BufferedReader bf = new BufferedReader(new FileReader("input8.txt"))) {
      String line;
      StringBuilder sb = new StringBuilder();

      while ((line = bf.readLine()) != null) {
          sb.append(line).append("\n");
      }

      String lines = sb.substring(0, sb.length() - 1);

      String[] map = lines.split("\n\n");

      ArrayList<Integer> leftRight = new ArrayList<>();
      for (char s : map[0].toCharArray()) {
        if (s == 'L')
          leftRight.add(0);
        else leftRight.add(1);
      }

      HashMap<String, Node> nodes = new HashMap<>();
      ArrayList<String> nodesList = new ArrayList<>();
      for (String s : map[1].split("\n")) {
        String node = s.split(" = \\(")[0];
        String nodeLeft = s.split(" = \\(")[1].split(", ")[0];
        String nodeRight = s.split(" = \\(")[1].split(", ")[1].split("\\)")[0];

        nodes.put(node, new Node(nodeLeft, nodeRight));
        nodesList.add(node);
      }

      ArrayList <String> currentNodes = new ArrayList<>();
      nodesList.forEach(n -> {
        if (n.endsWith("A"))
          currentNodes.add(n);
      });

      int steps = 0;
      int index = 0;
      while (!checkAllEndWithZ(currentNodes)) {
        for (int i = 0; i < currentNodes.size(); i++) {
          String n = currentNodes.get(i);
          Node leftOrRight = nodes.get(n);
          if (leftRight.get(index) == 0) {
            currentNodes.remove(i);
            currentNodes.add(i, leftOrRight.getNodeLeft());
          } else {
            currentNodes.remove(i);
            currentNodes.add(i, leftOrRight.getNodeRight());
          }
        }

        index ++;
        index = index % leftRight.size();
        steps ++;
      }

      System.out.println(steps);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static boolean checkAllEndWithZ(ArrayList<String> list) {
    for (String s : list) {
      if (!s.endsWith("Z")) {
        return false;
      }
    }
    return true;
  }
}
