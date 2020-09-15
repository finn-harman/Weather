package ic.doc;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;

public class WeatherComAdapter implements Adapter {

  @Override
  public Region adaptRegion(LocalRegion region) throws IllegalArgumentException {
    return Region.valueOf(region.toString());
  }

  @Override
  public Day adaptDay(LocalDay day) throws IllegalArgumentException {
    return Day.valueOf(day.toString());
  }

  public LocalForecast adaptForecast(Forecast forecast) {
    LocalForecast localForecast = new LocalForecast(forecast.summary(), forecast.temperature());
    return localForecast;
  }
}
