public class Deco {}

interface Shish { boolean onlyOnions(); }

class Skewer implements Shish {
  public boolean onlyOnions() { return true; }
  public String toString() { return "---<|"; }
}

class Onion implements Shish {
  private Shish s;
  public Onion(Shish ss) { s = ss; }
  public boolean onlyOnions() { return s.onlyOnions(); }
  public String toString() { return "-Onion-" + s.toString(); }
}

class Lamb implements Shish {
  private Shish s;
  public Lamb(Shish ss) { s = ss; }
  public boolean onlyOnions() { return false; }
  public String toString() { return "-Lamb-" + s.toString(); }
}

class Tomato implements Shish {
  private Shish s;
  public Tomato(Shish ss) { s = ss; }
  public boolean onlyOnions() { return false; }
  public String toString() { return "-Tomato-" + s.toString(); }
}
