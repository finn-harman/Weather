package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RemoteLocalForecasterTest {

  @Test
  public void forecastForReturnsSameForSameInputs() {
    LocalForecaster forecaster = new RemoteLocalForecaster();
    LocalForecast forecast = forecaster.forecastFor(LocalRegion.LONDON, LocalDay.MONDAY);
    LocalForecast forecast1 = forecaster.forecastFor(LocalRegion.LONDON, LocalDay.MONDAY);

    assertThat(forecast.summary(), is(forecast1.summary()));
    assertThat(forecast.temperature(), is(forecast1.temperature()));
  }
}