package ic.doc;

public class CachingProxyLocalForecaster implements LocalForecaster {

  private final LocalForecaster remoteForecaster;
  private final CacheLocalForecaster cacheForecaster;

  public CachingProxyLocalForecaster(
      LocalForecaster remoteForecaster, CacheLocalForecaster cacheForecaster) {
    this.remoteForecaster = remoteForecaster;
    this.cacheForecaster = cacheForecaster;
  }

  @Override
  public LocalForecast forecastFor(LocalRegion region, LocalDay day) {
    LocalForecast forecast;

    forecast = cacheForecaster.forecastFor(region, day);
    if (forecast != null) {
      return forecast;
    }

    forecast = remoteForecaster.forecastFor(region, day);
    cacheForecaster.add(region, day, forecast);
    return forecast;
  }
}
