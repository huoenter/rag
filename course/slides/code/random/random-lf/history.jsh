aliens.choose()
humans.choose()
humans.choose()
intGen.choose()
intGen.choose()
intGen.choose()
intGen.choose()
intList.choose()
intList.choose()
intList.choose()
boolList.choose()
boolList.choose()
boolList.choose()
namePicker.choose()
namePicker.choose()
namePicker.choose()
namePicker.choose()
namePicker.choose()
namePicker.choose()
namePicker.choose()
namePicker.choose()
namePicker.choose()
recPicker.choose()
recPicker.choose()
recPicker.choose()
recPicker.choose()
recPicker.choose()
recPicker.choose()
recPicker.choose()
recPicker.choose()
recPicker.choose()
recPicker.choose()
recPicker.choose()
humans.choose()
humans.choose()
aliens.choose()
aliens.choose()
aliens.choose()
aliens.choose()
aliens.choose()
var intGen = new RandInt(30, 50);
intGen.choose()
intGen.choose()
intGen.choose()
intGen.choose()
intGen.choose()
intGen.choose()
intGen.choose()
intGen.choose()
intGen.choose()
intGen.choose()
intGen.choose()
intGen.choose()
intGen.choose()
intGen.choose()
var intList = new RandList<>(new RandInt(15,30), 6);
intList.choose()
intList.choose()
intList.choose()
var llg = new RandList<>(intList, 3);
llg.choose()
llg.choose()
llg.choose()
var lllg = new RandList<>(llg, 3);
lllg.choose()
lllg.choose()
lllg.choose()
lllg.choose()
lllg.choose()
lllg.choose()
var boolList = new RandList<>(new RandBool(), 12);
boolList.choose()
boolList.choose()
boolList.choose()
boolList.choose()
boolList.choose()
boolList.choose()
boolList.choose()
var namePicker = new FromList<>(List.of("Alice", "Bob", "Chad", "Denise"));
namePicker.choose()
namePicker.choose()
namePicker.choose()
namePicker.choose()
namePicker.choose()
namePicker.choose()
namePicker.choose()
namePicker.choose()
namePicker.choose()
namePicker.choose()
namePicker.choose()
namePicker.choose()
namePicker.choose()
namePicker.choose()
var humanPicker = new RandHuman();
humanPicker.choose()
humanPicker.choose()
humanPicker.choose()
var humans = new RandList<>(humanPicker, 5);
humans.choose()
humans.choose()
humans.choose()
var recPicker = new RandRecovery();
recPicker.choose()
recPicker.choose()
var alienPicker = new RandAlien();
alienPicker.choose()
alienPicker.choose()
alienPicker.choose()
alienPicker.choose()
var aliens = new RandList<>(alienPicker, 10);
aliens.choose()
aliens.choose()
aliens.choose()
aliens.choose()
public interface Random<A> { A choose(); }
class RandInt implements Random<Integer> {
  //[lo, hi)
  private int lo; private int hi;
  public RandInt(int l, int h) { lo = l; hi = h; }
  public Integer choose() {
    return new java.util.Random().nextInt(hi - lo) + lo;
  }
}
class RandBool implements Random<Boolean> {
  public Boolean choose() {
    return new RandInt(0, 2).choose() == 0 ? true : false;
  }
}
class RandList<A> implements Random<List<A>> {
  private Random<A> ra; private int n;
  public RandList(Random<A> r, int m) { ra = r; n = m; }
  public List<A> choose() {
    List<A> ans = new ArrayList<A>();
    for (int i = 0; i < n; i++) ans.add(ra.choose());
    return ans;
  }
}
class FromList<A> implements Random<A> {
  List<A> choices;
  public FromList(List<A> l) { choices = l; }
  public A choose() {
    return choices.get(new RandInt(0, choices.size()).choose());
  }
}
interface LifeForm {}
class Alien implements LifeForm {
  String name; int points; Recovery rb;
  public Alien(String n, int p, Recovery r) {
    name = n; points = p; rb = r;
  }
  public String toString() {
    return String.format("Alien: %s LF: %d RB: %s\n", name, points, rb);
  }
}
class Human implements LifeForm {
  String name; int points; int armor;
  public Human(String n, int p, int a) {
    name = n; points = p; armor = a;
  }
  public String toString() {
    return String.format("Human: %s LF: %d Armor: %d\n", name, points, armor);
  }
}
interface Recovery {}
class Linear implements Recovery {
  public String toString() { return "Linear Recovery"; }
}
class Fractional implements Recovery {
  public String toString() { return "Fractional Recovery"; }
}
class NoRecovery implements Recovery {
  public String toString() { return "No Recovery"; }
}
class RandHuman implements Random<Human> {
  List<String> nameChoices = List.of("Alice", "Bob", "Chad", "Denise");
  public Human choose() {
    return new Human(new FromList<>(nameChoices).choose(),
                     new RandInt(30, 50).choose(),
                     new RandInt(0, 10).choose());
  }
}
class RandRecovery implements Random<Recovery> {
  List<Recovery> choices = List.of(new NoRecovery(), new Linear(), new Fractional());
  public Recovery choose() { return new FromList<Recovery>(choices).choose(); }
}
class RandAlien implements Random<Alien> {
  List<String> nameChoices = List.of("E.T.", "Xenomorph", "Zoiberg", "Roger");
  public Alien choose() {
    return new Alien(new FromList<>(nameChoices).choose(),
                     new RandInt(30, 50).choose(),
                     new RandRecovery().choose());
  }
}
class RandLifeForm implements Random<LifeForm> {
  public LifeForm choose() {
    return new RandBool().choose() ?
            new RandAlien().choose() : new RandHuman().choose();
  }
}
var lfs = new RandList<>(new RandLifeForm(), 12);
lfs.choose()
lfs.choose()
lfs.choose()