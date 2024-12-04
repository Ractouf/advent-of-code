import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class main2 {

  public static void main(String[] args) {
    ArrayList<String> a = new ArrayList<>(Arrays.asList("A55.5", "32A3D", "DD677", "DA..A", "CCC.E"));
    a.sort(Comparator.comparing(String::valueOf));

    a.forEach(System.out::println);
  }
}
