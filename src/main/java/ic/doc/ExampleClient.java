package ic.doc;

public class ExampleClient {

  public static void main(String[] args) {

    LocalForecaster remoteForecaster = new RemoteLocalForecaster();
    CacheLocalForecaster cacheForecaster = new CacheLocalForecaster(10);

    LocalForecaster forecaster = new CachingProxyLocalForecaster(remoteForecaster, cacheForecaster);

    LocalForecast londonForecast = forecaster.forecastFor(LocalRegion.LONDON, LocalDay.MONDAY);

    System.out.println("London outlook: " + londonForecast.summary());
    System.out.println("London temperature: " + londonForecast.temperature());

    LocalForecast londonForecast2 = forecaster.forecastFor(LocalRegion.LONDON, LocalDay.MONDAY);

    System.out.println("London outlook: " + londonForecast2.summary());
    System.out.println("London temperature: " + londonForecast2.temperature());

    LocalForecast edinburghForecast = forecaster.forecastFor(LocalRegion.EDINBURGH,
            LocalDay.MONDAY);

    System.out.println("Edinburgh outlook: " + edinburghForecast.summary());
    System.out.println("Edinburgh temperature: " + edinburghForecast.temperature());
  }
}
