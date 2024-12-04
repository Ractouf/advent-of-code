import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class main2 {

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
      ArrayList<Integer> currentNodesSteps = new ArrayList<>();
      for (int i = 0; i < currentNodes.size(); i++) {
        currentNodesSteps.add(0);
      }
      while (!checkArrayListFull(currentNodesSteps)) {
        for (int i = 0; i < currentNodes.size(); i++) {
          String n = currentNodes.get(i);

          if (n.endsWith("Z") && currentNodesSteps.get(i) == 0) {
            currentNodesSteps.remove(i);
            currentNodesSteps.add(i, steps);

            if (checkArrayListFull(currentNodesSteps)) {
              break;
            }
          }

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

      System.out.println(lcm_of_array_elements(currentNodesSteps.stream().mapToInt(i -> i).toArray()));

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static boolean checkArrayListFull(ArrayList<Integer> list) {
    for (int s : list) {
      if (s == 0) {
        return false;
      }
    }
    return true;
  }

  public static long lcm_of_array_elements(int[] element_array) {
    long lcm_of_array_elements = 1;
    int divisor = 2;

    while (true) {
      int counter = 0;
      boolean divisible = false;

      for (int i = 0; i < element_array.length; i++) {
        if (element_array[i] == 0) {
          return 0;
        } else if (element_array[i] < 0) {
          element_array[i] = element_array[i] * (-1);
        }
        if (element_array[i] == 1) {
          counter++;
        }
        if (element_array[i] % divisor == 0) {
          divisible = true;
          element_array[i] = element_array[i] / divisor;
        }
      }
      if (divisible) {
        lcm_of_array_elements = lcm_of_array_elements * divisor;
      } else {
        divisor++;
      }
      if (counter == element_array.length) {
        return lcm_of_array_elements;
      }
    }
  }
}

