package ic.doc;

public interface LocalForecaster {
  LocalForecast forecastFor(LocalRegion region, LocalDay day);
}
