package ic.doc;

import com.weather.Day;
import com.weather.Region;

public class RemoteLocalForecaster implements LocalForecaster {
  private final com.weather.Forecaster forecaster;
  private final WeatherComAdapter adapter;

  public RemoteLocalForecaster() {
    forecaster = new com.weather.Forecaster();
    adapter = new WeatherComAdapter();
  }

  @Override
  public LocalForecast forecastFor(LocalRegion region, LocalDay day) {
    Region remoteRegion = adapter.adaptRegion(region);
    Day remoteDay = adapter.adaptDay(day);

    LocalForecast forecast = adapter.adaptForecast(forecaster.forecastFor(remoteRegion, remoteDay));
    return forecast;
  }
}
