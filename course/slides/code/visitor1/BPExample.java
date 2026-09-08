public class BPExample {}

interface Item {
  void print(String indent);
  void showPrice(); }

class Book implements Item {
  String title; double price;
  public Book(String n, double p) {
    title = n; price = p; }
  public void print(String indent) {
    System.out.println(indent + title); }
  public void showPrice() {
    System.out.println(prices); }
}

class Backpack implements Item {
  List<Item> items; double price;
  public Backpack(List<Item> l, double p) {
    items = l; price = p; }
  public void print(String indent) {
    System.out.println(indent + "Backpack:");
    items.forEach(item -> item.print(indent + "**")); }
  public void showPrice() {
    System.out.println(price);
    items.forEach(item -> item.showPrice());
  }
}
