public class Galaxy {
  private int i;
  private int j;
  private int galaxyId;
  private static int nextGalaxyId = 1;

  public Galaxy(int i, int j) {
    this.i = i;
    this.j = j;
    this.galaxyId = nextGalaxyId;
    nextGalaxyId ++;
  }

  public int getI() {
    return i;
  }
  public int getJ() {
    return j;
  }
  public int getGalaxyId() {
    return galaxyId;
  }
}
