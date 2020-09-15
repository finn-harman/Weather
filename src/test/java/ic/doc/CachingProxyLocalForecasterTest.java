package ic.doc;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CachingProxyLocalForecasterTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  LocalForecaster remoteForecaster = context.mock(LocalForecaster.class);

  LocalRegion region = LocalRegion.LONDON;
  LocalDay day = LocalDay.MONDAY;

  @Test
  public void usesRemoteWhenNotInCache() {
    context.checking(new Expectations() {{
      exactly(1).of(remoteForecaster).forecastFor(region, day);
    }});

    CacheLocalForecaster cacheForecaster = new CacheLocalForecaster(10);
    LocalForecaster forecaster = new CachingProxyLocalForecaster(remoteForecaster, cacheForecaster);

    LocalForecast forecast = forecaster.forecastFor(region, day);
  }

  @Test
  public void remoteNotUsedWhenForecastAlreadyInCache() {
    context.checking(new Expectations() {{
      exactly(0).of(remoteForecaster).forecastFor(region, day);
    }});

    CacheLocalForecaster cacheForecaster = new CacheLocalForecaster(10);
    LocalForecaster forecaster = new CachingProxyLocalForecaster(remoteForecaster, cacheForecaster);

    LocalForecast forecast = new LocalForecast("sunny", 11);
    cacheForecaster.add(region, day, forecast);

    LocalForecast forecast1 = forecaster.forecastFor(region, day);
  }
}