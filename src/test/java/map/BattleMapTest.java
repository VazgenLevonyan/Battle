package map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    assertEquals(true, battleMap.contains(pointList, specPoint), "List of points does not contain specPoint");
  }

  @Test
  @DisplayName("The coordinate point must be busy if a ship state is not hit")
  void isBusyInNormalState() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    battleMap.putShip(point);

    assertEquals(true, battleMap.isBusy(point), "This coordinate is not busy");
  }

  @Test
  @DisplayName("The coordinate point must be busy if a ship state is hit or sunk")
  void isBusyInHitState() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    battleMap.putShip(point);
    battleMap.changeShipState(point);

    assertEquals(true, battleMap.isBusy(point), "This coordinate is not busy");
  }

  @Test
  @DisplayName("The coordinate point must not be busy if there is no ship")
  void isBusyInEmptyState() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);

    assertEquals(false, battleMap.isBusy(point), "This coordinate  is not busy");
  }

  @Test
  @DisplayName("The ship state must be sunk if all coordinate values are 2")
  void isSunk() {
    BattleMap battleMap = new BattleMap(5);
    List<Point> list = new ArrayList<>();
    Point point = new Point(1, 1);
    Point point1 = new Point(1, 2);
    Point point2 = new Point(1, 3);
    battleMap.changeShipState(point);
    battleMap.changeShipState(point1);
    battleMap.changeShipState(point2);
    list.add(point);
    list.add(point1);
    list.add(point2);

    assertEquals(true, battleMap.isSunk(list), "The ship state is not sunk");
  }

  @Test
  @DisplayName("The ship state must be sunk if all coordinate values are 2")
  void isNotSunk() {
    BattleMap battleMap = new BattleMap(5);
    List<Point> list = new ArrayList<>();
    Point point = new Point(1, 1);
    Point point1 = new Point(1, 2);
    Point point2 = new Point(1, 3);
    battleMap.changeShipState(point);
    battleMap.changeShipState(point2);
    list.add(point);
    list.add(point1);
    list.add(point2);

    assertEquals(false, battleMap.isSunk(list), "The ship state is not sunk");
  }
}
