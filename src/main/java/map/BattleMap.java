package map;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BattleMap {
  public final List<Integer> map = new ArrayList<Integer>();
  final Integer size;

  public BattleMap(int size) {
    if (size < 1)
      throw new InvalidParameterException("Battlefield map size should be > 1");

    this.size = size;
    this.map.addAll(Collections.nCopies(size * size, 0));
  }

  public Integer get_at(Point p) {
    return map.get(p.to1D(size));
  }

  public void set_cell_state(Point point, States shipState) {
    if (shipState.getValue() - get_at(point) == 1) {
      map.add(point.to1D(size), shipState.getValue());
    } else {
      throw new InvalidParameterException("Something goes wrong");
    }
  }

  public boolean check_if_the_cell_is_busy(Point point) {
    return get_at(point) != 0;
  }
}
