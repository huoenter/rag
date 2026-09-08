import java.util.List;

public class VisitorExample {
  public static void main(String[] args) {
    Backpack bp = new Backpack(List.of(new Book("Design Patterns", 2), new Book("Computer Organization", 3),
                               new Backpack(List.of(new Book("SPC", 4)), 5), new Book("Intro to Opera", 6)), 7);

    WeightVisitor wv = new WeightVisitor();
    bp.accept(wv);
    System.out.println(wv.total);
  }
}

interface Visitor {
  void visit(Backpack bp);
  void visit(Book b);
}

class WeightVisitor implements Visitor {
  int total = 0;
  public void visit(Backpack bp) { total += bp.weight; }
  public void visit(Book b) { total += b.weight; }
}

abstract class Component {
  int weight;
  public abstract void accept(Visitor v);
}

class Book extends Component {
  String name;
  public Book(String n, int w) { name = n; weight = w; }
  public void accept(Visitor v) {
    v.visit(this);
  }
}

class Backpack extends Component {
  List<Component> components;
  public Backpack(List<Component> l, int w) { components = l; weight = w; }
  public void accept(Visitor v) {
    v.visit(this);
    //components.forEach(c -> c.accept(v));
    components.forEach(c -> v.visit(c));
  }
}
