package point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import map.BattleMap;
import map.Point;

public class PointTest {
  @Test
  @DisplayName("Point(1, 1) in a size 1 battlefield should be illegal")
  void checkIfTheGivenPositionIsOutOfMapSize() {
    BattleMap battleMap = new BattleMap(1);
    Point point = new Point(1, 1);

    assertThrows(IndexOutOfBoundsException.class, () -> battleMap.getAt(point));
  }

  @Test
  @DisplayName("Point(0, 0) in a size 1 battlefield should be 0 in 1d")
  void checkValueOfPosition00ForSize1() {
    BattleMap battleMap = new BattleMap(1);
    Point point = new Point(0, 0);

    assertEquals(0, battleMap.getAt(point), "Point(0, 0) in a size 1 battlefield isn't 0 in 1D");
  }

  @Test
  @DisplayName("Point(0, 0) in a size 2 battlefield should be 0 in 1d")
  void checkValueOfPosition00ForSize2() {
    BattleMap battleMap = new BattleMap(2);
    Point point = new Point(0, 0);

    assertEquals(0, battleMap.getAt(point), "Point(0, 0) in a size 2 battlefield isn't 0 in 1D");
  }

  @Test
  @DisplayName("Point(10, 10) in a size 10 battlefield should be illegal")
  void checkIsTheGivenPositionOutOfMapFor10Size() {
    BattleMap battleMap = new BattleMap(10);
    Point point = new Point(10, 10);

    assertThrows(IndexOutOfBoundsException.class, () -> battleMap.getAt(point));
  }

  @Test
  @DisplayName("Point(11, 9) in a size 10 battlefield should be illegal")
  void checkIsThePositionOutOfBoundForSize10() {
    BattleMap battleMap = new BattleMap(10);
    Point point = new Point(11, 9);

    assertThrows(IndexOutOfBoundsException.class, () -> battleMap.getAt(point));
  }

  @Test
  @DisplayName("Point(9, 10) in a size 10 battlefield should be illegal")
  void checkIsThisPositionOutOfBoundForSize10() {
    BattleMap battleMap = new BattleMap(10);
    Point point = new Point(9, 10);

    assertThrows(IndexOutOfBoundsException.class, () -> battleMap.getAt(point));
  }

  @Test
  @DisplayName("Point(1, 1) in a size 2 battlefield should be 3 in 1d")
  void checkThisPositionValueOnTwoDimensionalMatrix() {
    BattleMap battleMap = new BattleMap(2);
    Point point = new Point(1, 1);

    assertEquals(0, battleMap.getAt(point), "Point(1, 1) in a size 2 battlefield is 3 in 1D");
  }

  @Test
  @DisplayName("Point(1, 1) in a size 3 battlefield should be 4 in 1d")
  void checkThisPositionValueOnThreeOnThreeMatrix() {
    BattleMap battleMap = new BattleMap(3);
    Point point = new Point(1, 1);

    assertEquals(0, battleMap.getAt(point), "Point(1, 1) in a size 3 battlefield is 4 in 1D");
  }
}