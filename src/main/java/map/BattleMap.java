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

  public void putShip(Point p) {
    map.add(p.to1D(size), 1);
  }

  public Integer getAt(Point p) {
    return map.get(p.to1D(size));
  }
}
