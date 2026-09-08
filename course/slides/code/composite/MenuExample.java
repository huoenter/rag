public class MenuExample {}

interface MenuComponent { public void print(); }

class MenuItem implements MenuComponent {
  private String name;
  public MenuItem(String n) { name = n; }
  public void print() { System.out.println(name); }
}

class Menu implements MenuComponent {
  List<MenuComponent> items;
  public Menu(List<MenuComponent> l) { items = l; }
  public void print() {
    System.out.println("Menu");
    items.forEach(item -> item.print());
  }
}
