public class VisitorExample {}

interface Item {
  void accept(ItemVisitor v);
}

class Book implements Item {
  String title; double price;
  public Book(String n, double p) { title = n; price = p; }
  public void accept(ItemVisitor v) { v.visit(this); }
}

class Backpack implements Item {
  List<Item> items; double price;
  public Backpack(List<Item> l, double p) {
    items = l; price = p; }
  public void accept(ItemVisitor v) {
    v.visit(this);
    items.forEach(item -> item.accept(v));
  }
}

interface ItemVisitor {
  public void visit(Book b);
  public void visit(Backpack bp);
}

class PrintVisitor implements ItemVisitor {
  public void visit(Book b) {
    System.out.println(b.title); }

  public void visit(Backpack bp) {
    System.out.println("Backpack:"); }
}

class PriceVisitor implements ItemVisitor {
  private double total;
  public void visit(Book b) { total += b.price; }

  public void visit(Backpack bp) { total += bp.price; }

  public void showTotal() {
    System.out.println("Total value is $" + total);
  }
}
