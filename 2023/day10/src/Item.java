public class Item {
  String item;
  int i;
  int j;
  int distance = 0;
  int goingUp = -1;

  public Item(String item, int i, int j) {
    this.item = item;
    this.i = i;
    this.j = j;
  }

  public String getItem() {
    return item;
  }

  public int getI() {
    return i;
  }

  public int getJ() {
    return j;
  }

  public int getDistance() {
    return distance;
  }

  public int getGoingUp() {
    return goingUp;
  }

  public void setGoingUp(int goingUp) {
    this.goingUp = goingUp;
  }

  public void setDistance(int distance) {
    this.distance = distance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Item item = (Item) o;

    if (i != item.i) {
      return false;
    }
    return j == item.j;
  }

  @Override
  public int hashCode() {
    int result = i;
    result = 31 * result + j;
    return result;
  }
}
