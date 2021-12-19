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

  public boolean check_if_the_ship_of_given_length_is_on_the_map(Point point, int length, boolean horizontal) {
    return horizontal ? point.y + length <= size : point.x + length <= size;
  }

  public List<Point> create_ship_positions(Point point, int length, boolean horizontal) {
    List<Point> pointList = new ArrayList<Point>();
    if (check_if_the_ship_of_given_length_is_on_the_map(point, length, horizontal)) {
      for (int i = 0; i < length; i++) {
        pointList.add(new Point(point.x + (horizontal ? 0 : i), point.y + (horizontal ? i : 0)));
      }
    }
    return pointList;
  }
}
