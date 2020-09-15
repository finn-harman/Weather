package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CacheLocalForecasterTest {

  @Test
  public void cacheEmptyAtInitialisation() {
    CacheLocalForecaster forecaster = new CacheLocalForecaster(10);
    assertThat(forecaster.getSize(), is(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void cacheInitialisedWithNegativeCapacity() {
    CacheLocalForecaster forecaster = new CacheLocalForecaster(-1);
  }

  @Test
  public void cacheAddMethodAddsForecastToCache() {
    CacheLocalForecaster forecaster = new CacheLocalForecaster(10);
    LocalForecast forecast = new LocalForecast("sunny", 11);
    forecaster.add(LocalRegion.LONDON, LocalDay.MONDAY, forecast);
    assertThat(forecaster.containsValue(forecast), is(true));
  }

  @Test
  public void cacheRemovesOldestEntryWhenFull() {
    CacheLocalForecaster forecaster = new CacheLocalForecaster(1);

    LocalForecast forecast = new LocalForecast("sunny", 11);
    forecaster.add(LocalRegion.LONDON, LocalDay.MONDAY, forecast);
    assertThat(forecaster.containsValue(forecast), is(true));

    LocalForecast forecast1 = new LocalForecast("cloudy", 11);
    forecaster.add(LocalRegion.LONDON, LocalDay.TUESDAY, forecast1);
    assertThat(forecaster.containsValue(forecast), is(false));
    assertThat(forecaster.containsValue(forecast1), is(true));
  }
}