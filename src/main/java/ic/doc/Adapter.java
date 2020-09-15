package ic.doc;

import com.weather.Day;
import com.weather.Region;

public interface Adapter {
  Region adaptRegion(LocalRegion region);

  Day adaptDay(LocalDay day);
}
