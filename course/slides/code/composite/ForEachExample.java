import java.util.function.Supplier;

public class ForEachExample {}

class Printer {
  public void print(Menu m) {
    System.out.println("Menu");
    m.items.forEach(item -> item.forEach(this));
  }
  public void print(MenuItem i) {
    System.out.println(i.name);
  }
}

interface MenuComponent {
  public void forEach(Printer p);
}

class MenuItem implements MenuComponent {
  String name;
  public MenuItem(String n) { name = n; }
  public void forEach(Printer p) { p.print(this); }
}

class Menu implements MenuComponent {
  List<MenuComponent> items;
  public Menu(List<MenuComponent> l) { items = l; }
  public void forEach(Printer p) { p.print(this); }
}
