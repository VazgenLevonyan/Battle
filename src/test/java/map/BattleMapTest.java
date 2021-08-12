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
  void mapsize() {
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
    BattleMap battleMap = new BattleMap(10);
    Point p = new Point(4, 4);

    // battleMap.putShip(p);
    assertEquals(1, battleMap.getAt(p), "No ship found at " + p);
  }

  @Test
  @DisplayName("Position [0:9] in map should be '1' ")
  void is_busy() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(0, 9);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Position [0:9] in map should be '1'  FFALSE")
  void is_busy21() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(0, 9);
    assertTrue(ships[point.x][point.y].equals("0"), "Is  busy");
  }

  @Test
  @DisplayName("Position [1:1] in map should be '1' ")
  void is_busy1() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(1, 1);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Position [1:3] in map should be '1' ")
  void is_busy2() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(1, 3);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Position [1:4] in map should be '1' ")
  void is_busy3() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(1, 4);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Position [3:1] in map should be '1' ")
  void is_busy4() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(3, 1);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Position [3:3] in map should be '1' ")
  void is_busy5() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(3, 3);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Position [3:4] in map should be '1' ")
  void is_busy6() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(3, 4);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Position [4:8] in map should be '1' ")
  void is_busy7() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(4, 8);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Position [5:1] in map should be '1' ")
  void is_busy8() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(5, 1);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Position [5:8] in map should be '1' ")
  void is_busy9() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(5, 8);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Position [6:1] in map should be '1' ")
  void is_busy10() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(6, 1);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Position [6:3] in map should be '1' ")
  void is_busy11() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(6, 3);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Position [6:4] in map should be '1' ")
  void is_busy12() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(6, 4);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Position [6:5] in map should be '1' ")
  void is_busy13() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(6, 5);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Position [6:6] in map should be '1' ")
  void is_busy14() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(6, 6);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Position [6:8] in map should be '1' ")
  void is_busy15() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(6, 8);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Position [8:1] in map should be '1' ")
  void is_busy17() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(8, 1);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Position [9:7] in map should be '1' ")
  void is_busy18() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(9, 7);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Position [9:8] in map should be '1' ")
  void is_busy19() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(9, 8);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Position [9:9] in map should be '1' ")
  void is_busy20() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    Point point = new Point(9, 9);
    assertTrue(ships[point.x][point.y].equals("1"), "Is  busy");
  }

  @Test
  @DisplayName("Change ship description approach(Putting horizontal)")
  void ship_positions() {
    Point point = new Point(8, 3);
    int length = 4;
    int i = 0;
    int size = 10;
    boolean is_horizontal = true;
    List<Point> list = new ArrayList<>();
    while (i < length) {
      if (is_horizontal) {
        if (point.y + i >= size)
          throw new IndexOutOfBoundsException("This position is out of map size");
        Point point1 = new Point(point.x, point.y + i);
        list.add(point1);
        i++;
      } else {
        if (point.x + i >= size)
          throw new IndexOutOfBoundsException("This position is out of map size");
        Point point1 = new Point(point.x + i, point.y);
        list.add(point1);
        i++;
      }
    }

    assertEquals(4, list.size(), "WTF");
  }

  @Test
  @DisplayName("Change ship description approach(Exploding vertical)")
  void ship_positions1() {
    Point point = new Point(8, 3);
    int length = 4;
    int i = 0;
    int size = 10;
    boolean is_horizontal = false;
    List<Point> list = new ArrayList<>();
    while (i < length) {
      if (is_horizontal) {
        if (point.y + i >= size)
          throw new IndexOutOfBoundsException("This position is out of map size");
        Point point1 = new Point(point.x, point.y + i);
        list.add(point1);
        i++;
      } else {
        if (point.x + i >= size)
          throw new IndexOutOfBoundsException("This position is out of map size");
        Point point1 = new Point(point.x + i, point.y);
        list.add(point1);
        i++;
      }
    }
    assertEquals(4, list.size(), "WTF");
  }

  @Test
  @DisplayName("Change ship description approach(Exploding horizontal)")
  void ship_positions2() {
    Point point = new Point(8, 8);
    int length = 4;
    int i = 0;
    int size = 10;
    boolean is_horizontal = true;
    List<Point> list = new ArrayList<>();
    while (i < length) {
      if (is_horizontal) {
        if (point.y + i >= size)
          throw new IndexOutOfBoundsException("This position is out of map size");
        Point point1 = new Point(point.x, point.y + i);
        list.add(point1);
        i++;
      } else {
        if (point.x + i >= size)
          throw new IndexOutOfBoundsException("This position is out of map size");
        Point point1 = new Point(point.x + i, point.y);
        list.add(point1);
        i++;
      }
    }

    assertEquals(4, list.size(), "WTF");
  }

  @Test
  @DisplayName("Change ship description approach(Putting vertical)")
  void ship_positions3() {
    Point point = new Point(8, 3);
    int length = 2;
    int i = 0;
    int size = 10;
    boolean is_horizontal = false;
    List<Point> list = new ArrayList<>();
    while (i < length) {
      if (is_horizontal) {
        if (point.y + i >= size)
          throw new IndexOutOfBoundsException("This position is out of map size");
        Point point1 = new Point(point.x, point.y + i);
        list.add(point1);
        i++;
      } else {
        if (point.x + i >= size)
          throw new IndexOutOfBoundsException("This position is out of map size");
        Point point1 = new Point(point.x + i, point.y);
        list.add(point1);
        i++;
      }
    }
  }

  @Test
  @DisplayName("To check is the ship sunk")
  void is_sunk() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "2", "1", "2", "0", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    List<Point> pointList = new ArrayList<>();
    Point point = new Point(4, 3);
    Point point1 = new Point(4, 4);
    Point point2 = new Point(4, 5);
    int qount = 0;
    pointList.add(point);
    pointList.add(point1);
    pointList.add(point2);
    for (Point pot : pointList) {
      if (ships[pot.x][pot.y].equals("2"))
        qount++;
    }
    assertTrue(pointList.size() == qount,
        "The ship size is " + pointList.size() + " but hits part of ship is " + qount);
  }

  @Test
  @DisplayName("To check is the ship sunk")
  void is_sunk2() {
    String[][] ships = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "1", "0", "1", "1", "0", "0", "0", "0", "0" }, { "0", "0", "0", "2", "2", "2", "2", "0", "1", "0" },
        { "0", "1", "0", "0", "0", "0", "0", "0", "1", "0" }, { "0", "1", "0", "1", "1", "1", "1", "0", "1", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "1", "0", "0", "0", "0", "0", "0", "0", "0" },
        { "0", "0", "0", "0", "0", "0", "0", "1", "1", "1" } };

    List<Point> pointList = new ArrayList<>();
    Point point = new Point(4, 3);
    Point point1 = new Point(4, 4);
    Point point2 = new Point(4, 5);
    Point point3 = new Point(4, 6);
    int qount = 0;
    pointList.add(point);
    pointList.add(point1);
    pointList.add(point2);
    pointList.add(point3);
    for (Point pot : pointList) {
      if (ships[pot.x][pot.y].equals("2"))
        qount++;
    }
    assertTrue(pointList.size() == qount,
        "The ship size is " + pointList.size() + " but hits part of ship is " + qount);
  }

  @Test
  @DisplayName("Is List of Points contains a specific Point")
  void is_contain() {
    boolean isContain = false;
    List<Point> pointList = new ArrayList<>();
    Point point = new Point(4, 3);
    Point point1 = new Point(4, 4);
    Point point2 = new Point(4, 5);
    Point point3 = new Point(4, 6);
    Point specificPoint = new Point(4, 4);
    pointList.add(point);
    pointList.add(point1);
    pointList.add(point2);
    pointList.add(point3);

    for (Point pont : pointList) {
      if (pont.x == specificPoint.x && pont.y == specificPoint.y) {
        isContain = true;
      }
    }
    assertTrue(isContain, "The list of Points does not contain the specific Point");
  }

  @Test
  @DisplayName("Is List of Points contains a specific Point")
  void is_contain2() {
    boolean isContain = false;
    List<Point> pointList = new ArrayList<>();
    Point point = new Point(4, 3);
    Point point1 = new Point(4, 4);
    Point point2 = new Point(4, 5);
    Point point3 = new Point(4, 6);
    Point specificPoint = new Point(7, 4);
    pointList.add(point);
    pointList.add(point1);
    pointList.add(point2);
    pointList.add(point3);

    for (Point pont : pointList) {
      if (pont.x == specificPoint.x && pont.y == specificPoint.y) {
        isContain = true;
      }
    }
    assertTrue(isContain, "The list of Points does not contain the specific Point");
  }
}
