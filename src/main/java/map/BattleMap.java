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

  public List<Integer> getMap() {
    return new ArrayList<>(map);
  }

  public boolean isBusy(Point point) {
    return getAt(point) == 1;
  }

  public boolean isSunk(List<Point> list) {
    return list.stream().allMatch(point -> getAt(point) == 2);
  }

  public boolean contains(List<Point> pointList, Point specPoint) {
    return pointList.contains(specPoint);
  }

  public void putShip(Point p) {
    map.add(p.to1D(size), 1);
  }

  public Integer getAt(Point p) {
    return map.get(p.to1D(size));
  }

  public void printMap() {
    map.forEach(System.out::println);
  }

  public List<Point> createShipsPositions(Point p, Integer length, Boolean is_Horizontal) {
    int i = 0;
    List<Point> list = new ArrayList<>();
    while (i < length) {
      if (is_Horizontal) {
        if (p.y + i > size)
          throw new IndexOutOfBoundsException("The ship positions that rely on a given length are out of the map size");
        Point point1 = new Point(p.x, p.y + i);
        list.add(point1);
        i++;
      } else {
        if (p.x + i >= size)
          throw new IndexOutOfBoundsException("The ship positions that rely on a given length are out of the map size");
        Point point1 = new Point(p.x + i, p.y);
        list.add(point1);
        i++;
      }
    }
    return list;
  }

  public void changeShipState(Point point) {
    map.add(point.to1D(size), 2);
  }

  public boolean checkGivenCoordinateArea(Point point) {
    int p = point.to1D(size);
    return (map.get(p + size) == 0 && map.get(p - size) == 0 && map.get(p + 1) == 0 && map.get(p - 1) == 0);
  }

  @Override
  public String toString() {
    String result = "";
    for (Integer i : map) {
      result = result + i + " ";
    }
    return result;
  }
}
