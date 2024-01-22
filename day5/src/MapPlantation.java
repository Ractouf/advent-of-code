public class MapPlantation {
  private long destination, source, range;

  public MapPlantation(long destination, long source, long range) {
    this.destination = destination;
    this.source = source;
    this.range = range;
  }

  public long getDestination() {
    return destination;
  }
  public long getSource() {
    return source;
  }
  public long getRange() {
    return range;
  }
}
