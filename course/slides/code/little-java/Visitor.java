public class Visitor {}

interface Shish {
  Object accept(ShishVisitor v);
}

interface ShishVisitor {
  Object forSkewer();
  Object forOnion(Shish s);
  Object forLamb(Shish s);
  Object forTomato(Shish s);
}

class OnlyOnions implements ShishVisitor {
  public Boolean forSkewer() { return true; }
  public Boolean forOnion(Shish s) { return (Boolean) s.accept(this); }
  public Boolean forLamb(Shish s) { return false; }
  public Boolean forTomato(Shish s) { return false; }
}

class SubstLambForOnion implements ShishVisitor {
  public Shish forSkewer() { return new Skewer(); }
  public Shish forOnion(Shish s) { return new Lamb((Shish) s.accept(this)); }
  public Shish forLamb(Shish s) { return new Lamb((Shish) s.accept(this)); }
  public Shish forTomato(Shish s) { return new Tomato((Shish) s.accept(this)); }
}

class Skewer implements Shish {
  public Object accept(ShishVisitor v) { return v.forSkewer(); }
  public String toString() { return "---<|"; }
}

class Onion implements Shish {
  private Shish s;
  public Onion(Shish ss) { s = ss; }
  public Object accept(ShishVisitor v) { return v.forOnion(s); }
  public String toString() { return "-Onion-" + s.toString(); }
}

class Lamb implements Shish {
  private Shish s;
  public Lamb(Shish ss) { s = ss; }
  public Object accept(ShishVisitor v) { return v.forLamb(s); }
  public String toString() { return "-Lamb-" + s.toString(); }
}

class Tomato implements Shish {
  private Shish s;
  public Tomato(Shish ss) { s = ss; }
  public Object accept(ShishVisitor v) { return v.forTomato(s); }
  public String toString() { return "-Tomato-" + s.toString(); }
}
