public class VisitorWithStates {}

interface Shish {
  <A> A accept(ShishVisitor<A> v);
}

interface ShishVisitor<A> {
  A forSkewer();
  A forOnion(Shish s);
  A forLamb(Shish s);
  A forTomato(Shish s);
}

class LtdSubstLambForOnion implements ShishVisitor<Shish> {
  private int c;
  public LtdSubstLambForOnion(int n) { c = n; }
  public Shish forSkewer() { return new Skewer(); }
  public Shish forOnion(Shish s) {
    return c == 0 ? new Onion(s) : new Lamb(s.accept(new LtdSubstLambForOnion(c-1))); }
  public Shish forLamb(Shish s) { return new Lamb(s.accept(this)); }
  public Shish forTomato(Shish s) { return new Tomato(s.accept(this)); }
}

class Skewer implements Shish {
  public <A> A accept(ShishVisitor<A> v) { return v.forSkewer(); }
  public String toString() { return "---<|"; }
}

class Onion implements Shish {
  private Shish s;
  public Onion(Shish ss) { s = ss; }
  public <A> A accept(ShishVisitor<A> v) { return v.forOnion(s); }
  public String toString() { return "-Onion-" + s.toString(); }
}

class Lamb implements Shish {
  private Shish s;
  public Lamb(Shish ss) { s = ss; }
  public <A> A accept(ShishVisitor<A> v) { return v.forLamb(s); }
  public String toString() { return "-Lamb-" + s.toString(); }
}

class Tomato implements Shish {
  private Shish s;
  public Tomato(Shish ss) { s = ss; }
  public <A> A accept(ShishVisitor<A> v) { return v.forTomato(s); }
  public String toString() { return "-Tomato-" + s.toString(); }
}
