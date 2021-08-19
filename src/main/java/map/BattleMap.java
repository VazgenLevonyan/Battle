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
    return (getAt(point) == 1 || getAt(point) == 2);
  }

  public boolean isSunk(List<Point> list) {
    return list.stream().allMatch(point -> getAt(point) == 2);
  }

  public Integer getAt(Point p) {
    return map.get(p.to1D(size));
  }

  public void setCellState(Point point, States shipState) {
    if (shipState.getValue() < getAt(point))
      throw new InvalidParameterException("Something goes wrong");
    if (shipState.getValue() - getAt(point) > 1)
      throw new InvalidParameterException("Something goes wrong");
    if (shipState.getValue() - getAt(point) == 0)
      throw new InvalidParameterException("Something goes wrong");

    if (shipState.getValue() - getAt(point) == 1)
      map.add(point.to1D(size), shipState.getValue());
  }
}
