package map;

enum States {
  FREE(0), HEALTHY(1), HIT(2), SUNK(3);

  private States(int i) {
    this.i = i;
  }

  public int getValue() {
    return i;
  }

  public final int i;
}
