package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.weather.Day;
import com.weather.Region;
import org.junit.Test;

public class WeatherComAdapterTest {

  @Test
  public void regionReturnsCorrectRegion() {
    Adapter adapter = new WeatherComAdapter();
    assertThat(adapter.adaptRegion(LocalRegion.LONDON), is(Region.LONDON));
  }

  @Test(expected = IllegalArgumentException.class)
  public void regionReturnsErrorForInvalidRegion() {
    Adapter adapter = new WeatherComAdapter();
    adapter.adaptRegion(LocalRegion.LONDO);
  }

  @Test
  public void dayReturnsCorrectDay() {
    Adapter adapter = new WeatherComAdapter();
    assertThat(adapter.adaptDay(LocalDay.MONDAY), is(Day.MONDAY));
  }

  @Test(expected = IllegalArgumentException.class)
  public void dayReturnsErrorForInvalidDay() {
    Adapter adapter = new WeatherComAdapter();
    adapter.adaptDay(LocalDay.MONDA);
  }
}