package map;

public class Point {
  public final int x;
  public final int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Integer to1D(Integer size) {
    if ((x >= size) || (y >= size)) {
      throw new IndexOutOfBoundsException("Battlefield map size should NOT be >= X OR  >= Y");
    }

    return (size * x) + y;
  }

  @Override
  public String toString() {
    return String.format("(X:%d, y:%d)", x, y);
  }

  @Override
  public boolean equals(Object point) {
    if (this == point)
      return true;
    if (point == null)
      return false;
    if (getClass() != point.getClass())
      return false;
    Point other = (Point) point;
    if (x != other.x)
      return false;

    if (y == (other.y))
      return true;
    return false;
  }
}