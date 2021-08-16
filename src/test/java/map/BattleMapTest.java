package map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BattleMapTest {
  @Test
  @DisplayName("A fresh map should contain just 0-s")

  void aFreshMapShouldContainJustZeros() {
    BattleMap battleMap = new BattleMap(3);
    final Integer nonZeroElements = battleMap.map.stream().filter(e -> e != 0).collect(Collectors.toList()).size();

    assertEquals(nonZeroElements, 0, "Invalid map");
  }

  @Test
  @DisplayName("Map size cannot be zero or negative")
  void mapSize() {

    assertThrows(InvalidParameterException.class, () -> new BattleMap(-3));
  }

  @Test
  @DisplayName("Any field in an empty battle map should be 0")
  void allFieldsInAnEmptyBattlefieldShouldBeZeros() {
    final Integer size = 3;
    final BattleMap battleMap = new BattleMap(size);
    Integer sum = 0;

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        sum += battleMap.getAt(new Point(i, j));
      }
    }

    assertEquals(0, sum, "Not all fields were 0-s");
  }

  @Test
  @DisplayName("In coordinate p the number must be 1")
  void checkSpecificPosition() {
    BattleMap battleMap = new BattleMap(5);
    Point p = new Point(2, 2);
    battleMap.putShip(p);

    assertEquals(1, battleMap.getAt(p), "No ship found at " + p);
  }

  @Test
  @DisplayName("The coordinate point must be busy")
  void isBusy() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    battleMap.putShip(point);

    assertTrue(battleMap.isBusy(point), "This coordinate is not busy");
  }

  @Test
  @DisplayName("List of Points must contain a specific Point")
  void contains() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(1, 1);
    Point point1 = new Point(1, 2);
    Point point2 = new Point(1, 3);
    Point specPoint = new Point(1, 2);
    List<Point> pointList = new ArrayList<>();
    pointList.add(point);
    pointList.add(point1);
    pointList.add(point2);

    assertTrue(battleMap.contains(pointList, specPoint), "List of points does not contain specPoint");
  }
}
