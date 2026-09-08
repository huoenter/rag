public class Carousel {
  public static void main(String[] args) {
    Shish s1 = new Onion(
      new Onion(
        new Onion(
          new Skewer())));

    System.out.println(s1.substLambForOnion());
  }
}

abstract class Shish {
  OnlyOnions ooFn = new OnlyOnions();
  SubstLambForOnion substFn = new SubstLambForOnion();
  abstract boolean onlyOnions();
  abstract Shish substLambForOnion();
}

class OnlyOnions {
  boolean forSkewer() { return true; }
  boolean forOnion(Shish s) { return s.onlyOnions(); }
  boolean forLamb(Shish s) { return false; }
  boolean forTomato(Shish s) { return false; }
}

class SubstLambForOnion {
  Shish forSkewer() { return new Skewer(); }
  Shish forOnion(Shish s) { return new Lamb(s.substLambForOnion()); }
  Shish forLamb(Shish s) { return new Lamb(s.substLambForOnion()); }
  Shish forTomato(Shish s) { return new Tomato(s.substLambForOnion()); }
}

class Skewer extends Shish {
  public boolean onlyOnions() { return ooFn.forSkewer(); }
  public Shish substLambForOnion() { return substFn.forSkewer(); }
  public String toString() { return "---<|"; }
}

class Onion extends Shish {
  private Shish s;
  public Onion(Shish ss) { s = ss; }
  public boolean onlyOnions() { return ooFn.forOnion(s); }
  public Shish substLambForOnion() { return substFn.forOnion(s); }
  public String toString() { return "-Onion-" + s.toString(); }
}

class Lamb extends Shish {
  private Shish s;
  public Lamb(Shish ss) { s = ss; }
  public boolean onlyOnions() { return ooFn.forLamb(s); }
  public Shish substLambForOnion() { return substFn.forLamb(s); }
  public String toString() { return "-Lamb-" + s.toString(); }
}

class Tomato extends Shish {
  private Shish s;
  public Tomato(Shish ss) { s = ss; }
  public boolean onlyOnions() { return ooFn.forTomato(s); }
  public Shish substLambForOnion() { return substFn.forTomato(s); }
  public String toString() { return "-Tomato-" + s.toString(); }
}
