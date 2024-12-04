import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class main1 {
  public static void main(String[] args) {
    File input = new File("input5.txt");

    try (BufferedReader bf = new BufferedReader(new FileReader(input))) {
      String line;

      ArrayList<SeedRange> seedsList = new ArrayList<>();
      ArrayList<MapPlantation> seedSoil = new ArrayList<>();
      ArrayList<MapPlantation> soilFertilizer = new ArrayList<>();
      ArrayList<MapPlantation> fertilizerWater = new ArrayList<>();
      ArrayList<MapPlantation> waterLight = new ArrayList<>();
      ArrayList<MapPlantation> lightTemperature = new ArrayList<>();
      ArrayList<MapPlantation> temperatureHumidity = new ArrayList<>();
      ArrayList<MapPlantation> humidityLocation = new ArrayList<>();

      while ((line = bf.readLine()) != null) {
        if (line.startsWith("seeds: ")) {
          String seeds = line.split("\n")[0].split(": ")[1];

          String[] seedRange = seeds.split(" ");
          for (int i = 0; i <= seedRange.length - 2; i += 2) {
            seedsList.add(new SeedRange(Long.parseLong(seedRange[i]), Long.parseLong(seedRange[i + 1])));
          }
        } else if (line.startsWith("seed-to-soil map:")) {
          lineToList(bf, seedSoil);
        } else if (line.startsWith("soil-to-fertilizer map:")) {
          lineToList(bf, soilFertilizer);
        } else if (line.startsWith("fertilizer-to-water map:")) {
          lineToList(bf, fertilizerWater);
        } else if (line.startsWith("water-to-light map:")) {
          lineToList(bf, waterLight);
        } else if (line.startsWith("light-to-temperature map:")) {
          lineToList(bf, lightTemperature);
        } else if (line.startsWith("temperature-to-humidity map:")) {
          lineToList(bf, temperatureHumidity);
        } else if (line.startsWith("humidity-to-location map:")) {
          lineToList(bf, humidityLocation);
        }
      }

      long min = Long.MAX_VALUE;
      for (SeedRange sr : seedsList) {
        for (long i = sr.getSeed(); i < sr.getSeed() + sr.getRange(); i++) {
          long test = findNext(humidityLocation, findNext(temperatureHumidity, findNext(lightTemperature, findNext(waterLight, findNext(fertilizerWater, findNext(soilFertilizer, findNext(seedSoil, i)))))));
          if (test < min) {
            min = test;
          }
        }
      }

      System.out.println(min);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static long findNext(ArrayList<MapPlantation> list, long previous) {
    long current = previous;

    for (MapPlantation ss : list) {
      if (previous >= ss.getSource() && previous < ss.getSource() + ss.getRange()) {
        current = (previous - ss.getSource()) + ss.getDestination();
        break;
      }
    }

    return current;
  }

  public static void lineToList(BufferedReader bf, ArrayList<MapPlantation> list) throws IOException {
    String lines;

    while ((lines = bf.readLine()) != null && !lines.isEmpty()) {
      String[] l = lines.split(" ");
      list.add(new MapPlantation(Long.parseLong(l[0]), Long.parseLong(l[1]), Long.parseLong(l[2])));
    }
  }
}
