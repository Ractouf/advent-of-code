import java.util.Arrays;

public class Spring {
  String map;
  int[] groups;

  public Spring(String map, int[] groups) {
    this.map = map;
    this.groups = groups;
  }

  public String getMap() {
    return map;
  }

  public int[] getGroups() {
    return groups;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Spring spring = (Spring) o;

    if (!map.equals(spring.map)) {
      return false;
    }
    return Arrays.equals(groups, spring.groups);
  }

  @Override
  public int hashCode() {
    int result = map.hashCode();
    result = 31 * result + Arrays.hashCode(groups);
    return result;
  }
}
