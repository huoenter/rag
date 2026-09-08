import java.util.List;

public class Composite {
  public static void main(String[] args) {
    Backpack bp = new Backpack(List.of(new Book("Design Patterns", 2), new Book("Computer Organization", 3),
                               new Backpack(List.of(new Book("SPC", 4)), 5), new Book("Intro to Opera", 6)), 7);

    WeightVisitor wv = new WeightVisitor();
    bp.accept(wv);
    System.out.println(wv.total);

    PrintVisitor pv = new PrintVisitor();
    bp.accept(pv);
  }
}

interface Visitor {
  public void visit(Book b);
  public void visit(Backpack bp);
}

class PrintVisitor implements Visitor {
  public void visit(Book b) { System.out.println(b.name);}
  public void visit(Backpack bp) { System.out.println("Backpack:"); }
}

class WeightVisitor implements Visitor {
  int total = 0;
  public void visit(Book b) { total += b.weight; }
  public void visit(Backpack bp) { total += bp.weight; }
}

class Backpack extends Component {
  List<Component> components;
  public Backpack(List<Component> l, int w) { components = l; weight = w;}
  public void accept(Visitor v) {
    v.visit(this);
    components.forEach(c -> c.accept(v));
  }
}
abstract class Component {
  int weight;
  public abstract void accept(Visitor v);
}

class Book extends Component {
  String name;
  public Book(String n, int w) { name = n; weight = w;}
  public void accept(Visitor v) {
    v.visit(this);
  }
}

