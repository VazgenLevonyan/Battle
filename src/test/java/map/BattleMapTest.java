package map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.InvalidParameterException;
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
  @DisplayName("A Healthy cell can not be changed to Free")
  void setCellStateChangingFromHealthyToFree() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    States healthyCellState = States.HEALTHY;
    States freeCellState = States.FREE;
    battleMap.setCellState(point, healthyCellState);

    assertThrows(InvalidParameterException.class, () -> battleMap.setCellState(point, freeCellState));
  }

  @Test
  @DisplayName("A Hit cell can not be changed to Healthy")
  void setCellStateChangingFromHitToHealthy() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    States healthyCellState = States.HEALTHY;
    States hitCellState1 = States.HIT;
    battleMap.setCellState(point, healthyCellState);
    battleMap.setCellState(point, hitCellState1);

    assertThrows(InvalidParameterException.class, () -> battleMap.setCellState(point, healthyCellState));
  }

  @Test
  @DisplayName("A Hit cell can not be changed to Free")
  void setCellStateChangingFromHitToFree() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    States healthyCellState = States.HEALTHY;
    States hitCellState = States.HIT;
    States freeCellState = States.FREE;
    battleMap.setCellState(point, healthyCellState);
    battleMap.setCellState(point, hitCellState);

    assertThrows(InvalidParameterException.class, () -> battleMap.setCellState(point, freeCellState));
  }

  @Test
  @DisplayName("A Sunk cell can not be changed to Hit")
  void setCellStateChangingFromSunkToHit() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    States healthyCellState = States.HEALTHY;
    States hitCellState = States.HIT;
    States sunkCellStates = States.SUNK;
    battleMap.setCellState(point, healthyCellState);
    battleMap.setCellState(point, hitCellState);
    battleMap.setCellState(point, sunkCellStates);

    assertThrows(InvalidParameterException.class, () -> battleMap.setCellState(point, hitCellState));
  }

  @Test
  @DisplayName("A Sunk cell can not be changed to Healthy")
  void setCellStateChangingFromSunkToHealthy() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    States healthyCellState = States.HEALTHY;
    States hitCellState = States.HIT;
    States sunkCellStates = States.SUNK;
    battleMap.setCellState(point, healthyCellState);
    battleMap.setCellState(point, hitCellState);
    battleMap.setCellState(point, sunkCellStates);

    assertThrows(InvalidParameterException.class, () -> battleMap.setCellState(point, healthyCellState));
  }

  @Test
  @DisplayName("A Sunk cell can not ve changed to Free")
  void setCellStateChangingFromSunkToFree() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    States freeCellState = States.FREE;
    States healthyCellState = States.HEALTHY;
    States hitCellState = States.HIT;
    States sunkCellStates = States.SUNK;
    battleMap.setCellState(point, healthyCellState);
    battleMap.setCellState(point, hitCellState);
    battleMap.setCellState(point, sunkCellStates);

    assertThrows(InvalidParameterException.class, () -> battleMap.setCellState(point, freeCellState));
  }

  @Test
  @DisplayName("An empty cell cannot be set to hit")
  void setCellStateChangingFromFreeToHit() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(1, 1);
    States hitCellState = States.HIT;

    assertThrows(InvalidParameterException.class, () -> battleMap.setCellState(point, hitCellState));
  }

  @Test
  @DisplayName("An empty cell cannot be set to sunk")
  void setCellStateChangingFromFreeToSunk() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(1, 1);
    States sunkCellState = States.SUNK;

    assertThrows(InvalidParameterException.class, () -> battleMap.setCellState(point, sunkCellState));
  }

  @Test
  @DisplayName("An empty cell can be set as healthy")
  void setCellStateChangingFromFreeToHealthy() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(1, 1);
    States healthyCellState = States.HEALTHY;
    battleMap.setCellState(point, healthyCellState);

    assertEquals(healthyCellState.getValue(), battleMap.getAt(point), "No ship found at " + point);
  }

  @Test
  @DisplayName("An healthy cell can be set as hit")
  void setCellStateChangingFromHealthyToHit() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(1, 1);
    States healthyCellState = States.HEALTHY;
    States hitCellState = States.HIT;
    battleMap.setCellState(point, healthyCellState);
    battleMap.setCellState(point, hitCellState);

    assertEquals(hitCellState.getValue(), battleMap.getAt(point), "No hit position found at " + point);
  }

  @Test
  @DisplayName("An hit cell can be set as sunk")
  void setCellStateChangingFromHitToSunk() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(1, 1);
    States healthyCellState = States.HEALTHY;
    States hitCellState = States.HIT;
    States sunkCellState = States.SUNK;
    battleMap.setCellState(point, healthyCellState);
    battleMap.setCellState(point, hitCellState);
    battleMap.setCellState(point, sunkCellState);

    assertEquals(sunkCellState.getValue(), battleMap.getAt(point), "No sunk position found at " + point);
  }

  @Test
  @DisplayName("An healthy cell can be set as sunk only for lone")
  void setCellStateChangingFromHealthyToSunkOnlyForLone() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(1, 1);
    States healthyCellState = States.HEALTHY;
    States hitCellState = States.HIT;
    States sunkCellState = States.SUNK;
    battleMap.setCellState(point, healthyCellState);
    battleMap.setCellState(point, hitCellState);
    battleMap.setCellState(point, sunkCellState);

    assertEquals(sunkCellState.getValue(), battleMap.getAt(point), "No sunk position found at " + point);
  }
}
