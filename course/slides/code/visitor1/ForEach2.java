public class ForEach2 {}

interface Item {
  void forEach(ItemVisitor v);
}

class Book implements Item {
  String title;
  public Book(String n) { title = n;}
  public void forEach(ItemVisitor v) {
    v.visit(this);
  }
}

class Backpack implements Item {
  List<Item> items;
  public Backpack(List<Item> l) {
    items = l;}
  public void forEach(ItemVisitor v) {
    v.visit(this);
  }
}

class ItemVisitor {
  public void visit(Book b) {
    System.out.println(b.title);}

  public void visit(Backpack bp) {
    System.out.println("Backpack:");
    bp.items.forEach(i -> i.forEach(this)); }
}
