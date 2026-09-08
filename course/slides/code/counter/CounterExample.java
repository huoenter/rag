import java.util.List;
import java.util.ArrayList;

public class CounterExample {
}

interface Observer {
  public void update();
}

class Counter implements Observer {
  private String name;
  private int cnt;
  public Counter(String s) { name = s; }
  public void update() {
    cnt += 1;
    System.out.println(name + " counts " + cnt);
  }
}

interface Subject {
  public void addObserver(Observer ob);
  public void notifyObservers();
}

class Signal implements Subject {
  private List<Observer> obs = new ArrayList<>();
  public void addObserver(Observer ob) { obs.add(ob); }
  public void notifyObservers() {
    obs.forEach(ob -> ob.update());
  }
}
