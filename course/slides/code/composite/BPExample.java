public class BPExample {}

interface Item { void print(); }

class Book implements Item {
  String title;
  public Book(String n) { title = n; }
  public void print() {
    System.out.println(title);
  }
}

class Backpack implements Item {
  List<Item> items;
  public Backpack(List<Item> l) { items = l; }
  public void print() {
    System.out.println("Backpack:");
    items.forEach(item -> item.print());
  }
}
