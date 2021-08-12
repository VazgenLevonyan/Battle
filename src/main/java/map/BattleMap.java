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

  public List<Point> create_ships_positions(Point p, Integer length, Boolean is_Horizontal) {
    int i = 0;
    List<Point> list = new ArrayList<>();
    while (i < length) {
      if (is_Horizontal) {
        if (p.y + i > size)
          throw new IndexOutOfBoundsException("This position is out of map size");
        Point point1 = new Point(p.x, p.y + i);
        list.add(point1);
        i++;
      } else {
        if (p.x + i >= size)
          throw new IndexOutOfBoundsException("This position is out of map size");
        Point point1 = new Point(p.x + i, p.y);
        list.add(point1);
        i++;
      }
    }
    return list;
  }

  public boolean check_position_employment(Point point) {
    if (getAt(point) == 1) {
      return true;
    }
    return false;
  }

  public boolean is_sunk(List<Point> list) {
    int qount = 0;
    for (Point point : list) {
      if (getAt(point) == 2) {
        qount++;
      }
    }
    if (qount == list.size()) {
      return true;
    }
    return false;
  }

  public boolean contains(List<Point> pointList, Point specPoint) {
    boolean is_contain = false;
    for (Point point : pointList) {
      if (point.x == specPoint.x && point.y == specPoint.y) {
        is_contain = true;
      }
    }
    return is_contain;
  }

  public Integer getAt(Point p) {
    return map.get(p.to1D(size));
  }
}
