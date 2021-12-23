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

  void a_fresh_map_should_contain_just_zeros() {
    BattleMap battleMap = new BattleMap(3);
    final Integer nonZeroElements = battleMap.map.stream().filter(e -> e != 0).collect(Collectors.toList()).size();

    assertEquals(nonZeroElements, 0, "Invalid map");
  }

  @Test
  @DisplayName("Map size cannot be zero or negative")
  void map_size() {

    assertThrows(InvalidParameterException.class, () -> new BattleMap(-3));
  }

  @Test
  @DisplayName("Any field in an empty battle map should be 0")
  void all_fields_in_an_empty_battlefield_should_be_zeros() {
    final Integer size = 3;
    final BattleMap battleMap = new BattleMap(size);
    Integer sum = 0;

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        sum += battleMap.get_at(new Point(i, j));
      }
    }

    assertEquals(0, sum, "Not all fields were 0-s");
  }

  @Test
  @DisplayName("A Healthy cell can not be changed to Free")
  void set_cell_state_changing_from_healthy_to_free() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    States healthyCellState = States.HEALTHY;
    States freeCellState = States.FREE;
    battleMap.set_cell_state(point, healthyCellState);

    assertThrows(InvalidParameterException.class, () -> battleMap.set_cell_state(point, freeCellState));
  }

  @Test
  @DisplayName("A Hit cell can not be changed to Healthy")
  void set_cell_state_changing_from_hit_to_healthy() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    States healthyCellState = States.HEALTHY;
    States hitCellState1 = States.HIT;
    battleMap.set_cell_state(point, healthyCellState);
    battleMap.set_cell_state(point, hitCellState1);

    assertThrows(InvalidParameterException.class, () -> battleMap.set_cell_state(point, healthyCellState));
  }

  @Test
  @DisplayName("A Hit cell can not be changed to Free")
  void set_cell_state_changing_from_hit_to_free() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    States healthyCellState = States.HEALTHY;
    States hitCellState = States.HIT;
    States freeCellState = States.FREE;
    battleMap.set_cell_state(point, healthyCellState);
    battleMap.set_cell_state(point, hitCellState);

    assertThrows(InvalidParameterException.class, () -> battleMap.set_cell_state(point, freeCellState));
  }

  @Test
  @DisplayName("A Sunk cell can not be changed to Hit")
  void set_cell_state_changing_from_sunk_to_hit() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    States healthyCellState = States.HEALTHY;
    States hitCellState = States.HIT;
    States sunkCellStates = States.SUNK;
    battleMap.set_cell_state(point, healthyCellState);
    battleMap.set_cell_state(point, hitCellState);
    battleMap.set_cell_state(point, sunkCellStates);

    assertThrows(InvalidParameterException.class, () -> battleMap.set_cell_state(point, hitCellState));
  }

  @Test
  @DisplayName("A Sunk cell can not be changed to Healthy")
  void set_cell_state_changing_from_sunk_to_healthy() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    States healthyCellState = States.HEALTHY;
    States hitCellState = States.HIT;
    States sunkCellStates = States.SUNK;
    battleMap.set_cell_state(point, healthyCellState);
    battleMap.set_cell_state(point, hitCellState);
    battleMap.set_cell_state(point, sunkCellStates);

    assertThrows(InvalidParameterException.class, () -> battleMap.set_cell_state(point, healthyCellState));
  }

  @Test
  @DisplayName("A Sunk cell can not ve changed to Free")
  void set_cell_state_changing_from_sunk_to_free() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    States freeCellState = States.FREE;
    States healthyCellState = States.HEALTHY;
    States hitCellState = States.HIT;
    States sunkCellStates = States.SUNK;
    battleMap.set_cell_state(point, healthyCellState);
    battleMap.set_cell_state(point, hitCellState);
    battleMap.set_cell_state(point, sunkCellStates);

    assertThrows(InvalidParameterException.class, () -> battleMap.set_cell_state(point, freeCellState));
  }

  @Test
  @DisplayName("An empty cell cannot be set to hit")
  void set_cell_state_changing_from_free_to_hit() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(1, 1);
    States hitCellState = States.HIT;

    assertThrows(InvalidParameterException.class, () -> battleMap.set_cell_state(point, hitCellState));
  }

  @Test
  @DisplayName("An empty cell cannot be set to sunk")
  void set_cell_state_changing_from_free_to_sunk() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(1, 1);
    States sunkCellState = States.SUNK;

    assertThrows(InvalidParameterException.class, () -> battleMap.set_cell_state(point, sunkCellState));
  }

  @Test
  @DisplayName("An empty cell can be set as healthy")
  void set_cell_state_changing_from_free_to_healthy() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(1, 1);
    States healthyCellState = States.HEALTHY;
    battleMap.set_cell_state(point, healthyCellState);

    assertEquals(healthyCellState.getValue(), battleMap.get_at(point), "No ship found at " + point);
  }

  @Test
  @DisplayName("An healthy cell can be set as hit")
  void set_cell_state_changing_from_healthy_to_hit() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(1, 1);
    States healthyCellState = States.HEALTHY;
    States hitCellState = States.HIT;
    battleMap.set_cell_state(point, healthyCellState);
    battleMap.set_cell_state(point, hitCellState);

    assertEquals(hitCellState.getValue(), battleMap.get_at(point), "No hit position found at " + point);
  }

  @Test
  @DisplayName("An hit cell can be set as sunk")
  void an_hit_cell_can_be_set_as_sunk() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(1, 1);
    States healthyCellState = States.HEALTHY;
    States hitCellState = States.HIT;
    States sunkCellState = States.SUNK;
    battleMap.set_cell_state(point, healthyCellState);
    battleMap.set_cell_state(point, hitCellState);
    battleMap.set_cell_state(point, sunkCellState);

    assertEquals(sunkCellState.getValue(), battleMap.get_at(point), "No sunk position found at " + point);
  }

  @Test
  @DisplayName("An healthy cell can be set as sunk only for lone")
  void an_healthy_cell_can_be_set_as_sunk_only_for_lone() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(1, 1);
    States healthyCellState = States.HEALTHY;
    States hitCellState = States.HIT;
    States sunkCellState = States.SUNK;
    battleMap.set_cell_state(point, healthyCellState);
    battleMap.set_cell_state(point, hitCellState);
    battleMap.set_cell_state(point, sunkCellState);

    assertEquals(sunkCellState.getValue(), battleMap.get_at(point), "No sunk position found at " + point);
  }

  @Test
  @DisplayName("A empty cell has to be free")
  void a_empty_cell_has_to_be_free() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);

    assertEquals(false, battleMap.check_if_the_cell_is_busy(point), "An empty cell is busy");
  }

  @Test
  @DisplayName("A healthy cell has to be busy")
  void a_healthy_cell_has_to_be_busy() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    States healthyCellState = States.HEALTHY;
    battleMap.set_cell_state(point, healthyCellState);

    assertEquals(true, battleMap.check_if_the_cell_is_busy(point), "A healthy cell is not busy");
  }

  @Test
  @DisplayName("A hit cell has to be busy")
  void a_hit_cell_has_to_be_busy() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    States healthyCellState = States.HEALTHY;
    States hitCellState = States.HIT;
    battleMap.set_cell_state(point, healthyCellState);
    battleMap.set_cell_state(point, hitCellState);

    assertEquals(true, battleMap.check_if_the_cell_is_busy(point), "A hit cell is not busy");
  }

  @Test
  @DisplayName("A sunk cell has to be busy")
  void a_sunk_cell_has_to_be_busy() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    States healthyCellState = States.HEALTHY;
    States hitCellState = States.HIT;
    States sunkCellState = States.SUNK;
    battleMap.set_cell_state(point, healthyCellState);
    battleMap.set_cell_state(point, hitCellState);
    battleMap.set_cell_state(point, sunkCellState);

    assertEquals(true, battleMap.check_if_the_cell_is_busy(point), "A sunk cell state is not busy");
  }

  @Test
  @DisplayName("The ship of 1 length in horizontal is on the map")
  void the_ship_of_1_length_in_horizontal_is_on_the_map() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    boolean horizontal = true;
    int shipLength = 1;

    assertEquals(true, battleMap.check_if_the_ship_of_given_length_is_on_the_map(point, shipLength, horizontal),
        "The ship of 1 length in horizontal is out of the map");
  }

  @Test
  @DisplayName("The ship of 1 length in vertical is on the map")
  void the_ship_of_1_length_in_vertical_is_on_the_map() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    boolean horizontal = false;
    int shipLength = 1;

    assertEquals(true, battleMap.check_if_the_ship_of_given_length_is_on_the_map(point, shipLength, horizontal),
        "The ship of 1 length in horizontal is out of the map");
  }

  @Test
  @DisplayName("The ship of 1 length in horizontal is out of the map")
  void the_ship_of_1_length_in_horizontal_is_out_of_the_map() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 5);
    boolean horizontal = true;
    int shipLength = 1;
    assertEquals(false, battleMap.check_if_the_ship_of_given_length_is_on_the_map(point, shipLength, horizontal),
        "The ship of 1 length in horizonatl is on the map");
  }

  @Test
  @DisplayName("The ship of 1 length in vertical is out of the map")
  void the_ship_of_1_length_in_vertical_is_out_of_the_map() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(5, 3);
    boolean horizontal = false;
    int shipLength = 1;
    assertEquals(false, battleMap.check_if_the_ship_of_given_length_is_on_the_map(point, shipLength, horizontal),
        "The ship of 1 length in vertical is on the map");
  }

  @Test
  @DisplayName("The ship of 2 length in horizontal is on the map")
  void the_ship_of_2_length_in_horizontal_is_on_the_map() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    boolean horizontal = true;
    int shipLength = 2;

    assertEquals(true, battleMap.check_if_the_ship_of_given_length_is_on_the_map(point, shipLength, horizontal),
        "The ship of 2 length in horizontal is out of the map");
  }

  @Test
  @DisplayName("The ship of 2 length in vertical is on the map")
  void the_ship_of_2_length_in_vertical_is_on_the_map() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    boolean horizontal = false;
    int shipLength = 2;

    assertEquals(true, battleMap.check_if_the_ship_of_given_length_is_on_the_map(point, shipLength, horizontal),
        "The ship of 2 length in vertical is out of the map");
  }

  @Test
  @DisplayName("The ship of 1 length in horizontal is out of the map")
  void the_ship_of_2_length_in_horizontal_is_out_of_the_map() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 4);
    boolean horizontal = true;
    int shipLength = 2;

    assertEquals(false, battleMap.check_if_the_ship_of_given_length_is_on_the_map(point, shipLength, horizontal),
        "The ship of 2 length in horizonatl is on the map");
  }

  @Test
  @DisplayName("The ship of 2 length in vertical is out of the map")
  void the_ship_of_2_length_in_vertical_is_out_the_map() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(4, 3);
    boolean horizontal = false;
    int shipLength = 2;

    assertEquals(false, battleMap.check_if_the_ship_of_given_length_is_on_the_map(point, shipLength, horizontal),
        "The ship of 2 length in vertical is on the map");
  }

  @Test
  @DisplayName("The ship of 3 length in horizontal is on the map")
  void the_ship_of_3_length_in_horizontal_is_on_the_map() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    boolean horizontal = true;
    int shipLength = 3;

    assertEquals(true, battleMap.check_if_the_ship_of_given_length_is_on_the_map(point, shipLength, horizontal),
        "The ship of 3 length in horizontal is out of the map");
  }

  @Test
  @DisplayName("The ship of 3 length in vertical is on the map")
  void the_ship_of_3_length_in_vertical_is_on_the_map() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    boolean horizontal = false;
    int shipLength = 3;

    assertEquals(true, battleMap.check_if_the_ship_of_given_length_is_on_the_map(point, shipLength, horizontal),
        "The ship of 3 length in vertical is out of the map");
  }

  @Test
  @DisplayName("The ship of 3 length in horizontal is out of the map")
  void the_ship_of_3_length_in_horizontal_is_out_of_the_map() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 3);
    boolean horizontal = true;
    int shipLength = 3;

    assertEquals(false, battleMap.check_if_the_ship_of_given_length_is_on_the_map(point, shipLength, horizontal),
        "The ship of 3 length in horizonatal is on the map");
  }

  @Test
  @DisplayName("The ship of 3 length in vertical is out of the map")
  void the_ship_of_3_length_in_vertical_is_out_of_the_map() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(3, 3);
    boolean horizontal = false;
    int shipLength = 3;

    assertEquals(false, battleMap.check_if_the_ship_of_given_length_is_on_the_map(point, shipLength, horizontal),
        "The ship of 3 length in vertical is on the map");
  }

  @Test
  @DisplayName("The ship of 4 length in horizontal is on the map")
  void the_ship_of_4_length_in_horizontal_is_on_the_map() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 1);
    boolean horizontal = true;
    int shipLength = 4;

    assertEquals(true, battleMap.check_if_the_ship_of_given_length_is_on_the_map(point, shipLength, horizontal),
        "The ship of 4 length in horizontal is out of the map");
  }

  @Test
  @DisplayName("The ship of 4 length in vertical is on the map")
  void the_ship_of_4_length_in_vertical_is_on_the_map() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(1, 1);
    boolean horizontal = false;
    int shipLength = 4;

    assertEquals(true, battleMap.check_if_the_ship_of_given_length_is_on_the_map(point, shipLength, horizontal),
        "The ship of 4 length in vertical is out of the map");
  }

  @Test
  @DisplayName("The ship of 4 length in horizontal is out of the map")
  void the_ship_of_4_length_in_horizontal_is_out_of_the_map() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 5);
    boolean horizontal = true;
    int shipLength = 4;

    assertEquals(false, battleMap.check_if_the_ship_of_given_length_is_on_the_map(point, shipLength, horizontal),
        "The ship of 4 length in horizonatal is on the map");
  }

  @Test
  @DisplayName("The ship of 4 length in vertical is out of the map")
  void the_ship_of_4_length_in_vertical_is_out_of_the_map() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(5, 2);
    boolean horizontal = false;
    int shipLength = 4;

    assertEquals(false, battleMap.check_if_the_ship_of_given_length_is_on_the_map(point, shipLength, horizontal),
        "The ship of 4 length in vertical is on the map");
  }

  @Test
  @DisplayName("Create positions for 1 length ship in horizontal")
  void create_positions_for_1_length_ship_in_horizontal() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    int length = 1;
    boolean horizontal = true;
    List<Point> pointList = new ArrayList<>();
    pointList.add(new Point(2, 2));

    assertEquals(battleMap.create_ship_positions(point, length, horizontal), pointList,
        "It is impossible to create ship coordinate with the given data");
  }

  @Test
  @DisplayName("It is impossible to create positions for 1 length ship in horizontal which coordinates are out of map size")
  void it_is_impossible_to_create_positions_for_1_length_ship_in_horizontal_which_coordinates_are_out_of_map_size() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 5);
    int length = 1;
    boolean horizontal = true;
    List<Point> pointList = new ArrayList<>();

    assertEquals(battleMap.create_ship_positions(point, length, horizontal), pointList,
        "It is possible to create ship coordinate with the given data");
  }

  @Test
  @DisplayName("Create positions for 1 length ship in vertical")
  void create_positions_for_1_length_ship_in_vertical() {

    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    int length = 1;
    boolean horizontal = false;
    List<Point> pointList = new ArrayList<>();
    pointList.add(new Point(2, 2));

    assertEquals(battleMap.create_ship_positions(point, length, horizontal), pointList,
        "It is impossible to create ship coordinate with the given data");
  }

  @Test
  @DisplayName("It is impossible to create positions for 1 length ship in vertical which coordinates are out of map size")
  void it_possible_to_create_positions_for_1_length_ship_in_vertical_which_coordinates_are_out_of_map_size() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 5);
    int length = 1;
    boolean horizontal = false;
    List<Point> pointList = new ArrayList<>();
    pointList.add(new Point(2, 5));

    assertEquals(battleMap.create_ship_positions(point, length, horizontal), pointList,
        "It is possible to create ship coordinate with the given data");
  }

  @Test
  @DisplayName("Create positions for 2 length ship in horizontal")
  void create_positions_for_2_length_ship_in_horizontal() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    int length = 2;
    boolean horizontal = true;
    List<Point> pointList = new ArrayList<>();
    pointList.add(new Point(2, 2));
    pointList.add(new Point(2, 3));

    assertEquals(battleMap.create_ship_positions(point, length, horizontal), pointList,
        "It is impossible to create ship coordinate with the given data");
  }

  @Test
  @DisplayName("It is impossible to create positions for 2 length ship in horizontal which coordinates are out of map size")
  void it_is_impossible_to_create_positions_for_2_length_ship_in_horizontal_which_coordinates_are_out_of_map_size() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 4);
    int length = 2;
    boolean horizontal = true;
    List<Point> pointList = new ArrayList<>();

    assertEquals(battleMap.create_ship_positions(point, length, horizontal), pointList,
        "It is possible to create ship coordinate with the given data");
  }

  @Test
  @DisplayName("Create positions for 2 length ship in vertical")
  void create_positions_for_2_length_ship_in_vertical() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    int length = 2;
    boolean horizontal = false;
    List<Point> pointList = new ArrayList<>();
    pointList.add(new Point(2, 2));
    pointList.add(new Point(3, 2));

    assertEquals(battleMap.create_ship_positions(point, length, horizontal), pointList,
        "It is impossible to create ship coordinate with the given data");
  }

  @Test
  @DisplayName("It is impossible to create positions for 2 length ship in vertical which coordinates are out of map size")
  void it_is_impossible_to_create_positions_for_2_length_ship_in_vertical_which_coordinates_are_out_of_map_size() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(4, 2);
    int length = 2;
    boolean horizontal = false;
    List<Point> pointList = new ArrayList<>();

    assertEquals(battleMap.create_ship_positions(point, length, horizontal), pointList,
        "It is possible to create ship coordinate with the given data");
  }

  @Test
  @DisplayName("Create positions for 3 length ship in horizontal")
  void create_positions_for_3_length_ship_in_horizontal() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    int length = 3;
    boolean horizontal = true;
    List<Point> pointList = new ArrayList<>();
    pointList.add(new Point(2, 2));
    pointList.add(new Point(2, 3));
    pointList.add(new Point(2, 4));

    assertEquals(battleMap.create_ship_positions(point, length, horizontal), pointList,
        "It is impossible to create ship coordinate with the given data");
  }

  @Test
  @DisplayName("It is impossible to create positions for 3 length ship in horizontal which coordinates are out of map size")
  void it_is_impossible_to_create_positions_for_3_length_ship_in_horizontal_which_coordinates_are_out_of_map_size() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 3);
    int length = 3;
    boolean horizontal = true;
    List<Point> pointList = new ArrayList<>();

    assertEquals(battleMap.create_ship_positions(point, length, horizontal), pointList,
        "It is possible to create ship coordinate with the given data");
  }

  @Test
  @DisplayName("Create positions for 3 length ship in vertical")
  void create_positions_for_3_length_ship_in_vertical() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 2);
    int length = 3;
    boolean horizontal = false;
    List<Point> pointList = new ArrayList<>();
    pointList.add(new Point(2, 2));
    pointList.add(new Point(3, 2));
    pointList.add(new Point(4, 2));

    assertEquals(battleMap.create_ship_positions(point, length, horizontal), pointList,
        "It is impossible to create ship coordinate with the given data");
  }

  @Test
  @DisplayName("It is impossible to create positions for 3 length ship in horizontal which coordinates are out of map size")
  void it_is_impossible_to_create_positions_for_3_length_ship_in_vertical_which_coordinates_are_out_of_map_size() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(3, 2);
    int length = 3;
    boolean horizontal = false;
    List<Point> pointList = new ArrayList<>();

    assertEquals(battleMap.create_ship_positions(point, length, horizontal), pointList,
        "It is possible to create ship coordinate with the given data");
  }

  @Test
  @DisplayName("Create positions for 4 length ship in horizontal")
  void create_positions_for_4_length_ship_in_horizontal() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(1, 1);
    int length = 4;
    boolean horizontal = true;
    List<Point> pointList = new ArrayList<>();
    pointList.add(new Point(1, 1));
    pointList.add(new Point(1, 2));
    pointList.add(new Point(1, 3));
    pointList.add(new Point(1, 4));

    assertEquals(battleMap.create_ship_positions(point, length, horizontal), pointList,
        "It is impossible to create ship coordinate with the given data");
  }

  @Test
  @DisplayName("It is impossible to create positions for 4 length ship in horizontal which coordinates are out of map size")
  void it_is_impossible_to_create_positions_for_4_length_ship_in_horizontal_which_coordinates_are_out_of_map_size() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(1, 2);
    int length = 4;
    boolean horizontal = true;
    List<Point> pointList = new ArrayList<>();

    assertEquals(battleMap.create_ship_positions(point, length, horizontal), pointList,
        "It is possible to create ship coordinate with the given data");
  }

  @Test
  @DisplayName("Create positions for 4 length ship in vertical")
  void create_positions_for_4_length_ship_in_vertical() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(1, 1);
    int length = 4;
    boolean horizontal = false;
    List<Point> pointList = new ArrayList<>();
    pointList.add(new Point(1, 1));
    pointList.add(new Point(2, 1));
    pointList.add(new Point(3, 1));
    pointList.add(new Point(4, 1));

    assertEquals(battleMap.create_ship_positions(point, length, horizontal), pointList,
        "It is impossible to create ship coordinate with the given data");
  }

  @Test
  @DisplayName("It is impossible to create positions for 4 length ship in horizontal which coordinates are out of map size")
  void it_is_impossible_to_create_positions_for_4_length_ship_in_vertical_which_coordinates_are_out_of_map_size() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(2, 1);
    int length = 4;
    boolean horizontal = false;
    List<Point> pointList = new ArrayList<>();

    assertEquals(battleMap.create_ship_positions(point, length, horizontal), pointList,
        "It is possible to create ship coordinate with the given data");
  }

  @Test
  @DisplayName("The cell suroundings of Point(1:1) for 4 length ship is empty")
  void the_cell_surrounding_for_four_length_ship_is_empty() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(0, 1);
    boolean horizontal = true;
    int length = 4;

    assertEquals(true, battleMap.check_if_the_cell_surrounding_is_empty(point, length, horizontal),
        "The cell suroundings of Point(1:1) for 4 length ship is  not empty");
  }

  @Test
  @DisplayName("The cell suroundings of Point(1:1) for 4 length ship is not empty")
  void the_cell_surrounding_is_not_empty() {
    BattleMap battleMap = new BattleMap(5);
    Point point = new Point(1, 1);
    Point point1 = new Point(1, 0);
    battleMap.set_cell_state(point1, States.HEALTHY);
    boolean horizontal = false;
    int length = 4;

    assertEquals(false, battleMap.check_if_the_cell_surrounding_is_empty(point, length, horizontal),
        "The cell suroundings of Point(1:1) for 4 length ship is empty");
  }
}
