package ic.doc;

import java.util.LinkedHashMap;
import java.util.Map;

import javafx.util.Pair;

public class CacheLocalForecaster implements LocalForecaster {

  private final Cache forecastCache;

  public CacheLocalForecaster(int capacity) {
    forecastCache = new Cache(capacity);
  }

  public void add(LocalRegion region, LocalDay day, LocalForecast forecast) {
    forecastCache.put(new Pair<>(region, day), forecast);
  }

  public int getSize() {
    return forecastCache.size();
  }

  public boolean containsValue(LocalForecast forecast) {
    return forecastCache.containsValue(forecast);
  }

  @Override
  public LocalForecast forecastFor(LocalRegion region, LocalDay day) {
    LocalForecast forecast = forecastCache.get(new Pair<>(region, day));
    return forecast;
  }

  private class Cache extends LinkedHashMap<Pair<LocalRegion, LocalDay>, LocalForecast> {
    private final int maxSize;

    public Cache(int capacity) {
      this.maxSize = capacity;
      if (this.maxSize < 1) {
        throw new IllegalArgumentException("Capacity must be a positive integer");
      }
    }

    @Override
    protected boolean removeEldestEntry(
        Map.Entry<Pair<LocalRegion, LocalDay>, LocalForecast> eldest) {
      return this.size() > maxSize;
    }
  }
}
