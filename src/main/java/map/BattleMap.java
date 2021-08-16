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

  @Override
  public String toString() {
    String result = "";
    for (Integer i : map) {
      result = result + i+ " ";
    }
    return result;
  }
}
