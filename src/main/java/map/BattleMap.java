package map;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

  public List<Point> create_surronding_coordinates_list(List<Point> pointList) {
    List<Point> pointList2 = new ArrayList<Point>();
    for (Point p : pointList) {

      pointList2.add(new Point(p.x, p.y - 1));
      pointList2.add(new Point(p.x - 1, p.y));
      pointList2.add(new Point(p.x, p.y + 1));
      pointList2.add(new Point(p.x + 1, p.y));
    }
    return pointList2;
  }

  public boolean check_if_the_cell_surrounding_is_empty(Point point, int length, boolean horizontal) {
    List<Point> shipPositions = create_ship_positions(point, length, horizontal);
    List<Point> listOfSurroundingsPoints = create_surronding_coordinates_list(shipPositions);
    Set<Point> setOfSurroundingsPoints = new HashSet<Point>(listOfSurroundingsPoints);
    setOfSurroundingsPoints.removeAll(shipPositions);
    List<Point> filteredSurroundingsPointList = setOfSurroundingsPoints.stream()
        .filter(p -> p.x >= 0 && p.x < size && p.y >= 0 && p.y < size).collect(Collectors.toList());

    for (Point p : filteredSurroundingsPointList) {
      if (check_if_the_cell_is_busy(p))
        return false;
    }
    return true;
  }
}
