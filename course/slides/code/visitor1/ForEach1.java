public class ForEach1 {}

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
  public void visit(Item item) {
    if (item instanceof Book) {
      Book b = (Book) item;
      System.out.println(b.title);
    } else {
      Backpack bp = (Backpack) item;
      System.out.println("Backpack:");
      bp.items.forEach(i -> i.forEach(this)); }}
}
