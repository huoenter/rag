import java.util.List;
import java.util.ArrayList;

public class SpreadSheet {

  public static void main(String[] args) {
    SimpleCell c1 = new SimpleCell("C1");
    SimpleCell c2 = new SimpleCell("C2");
    c1.addObserver(c2);
    c1.update(10);
  }
}





interface Observer<A> {
  void update(A a);
}

interface Subject<A> {
  void addObserver(Observer<A> o);
  void notifyObservers();
}


class LeftObserver implements Observer<Integer> {
  private ArithmeticCell ac;
  public LeftObserver(ArithmeticCell a) {
    ac = a;
  }

  public void update(Integer i) {
    ac.setLeft(i);
  }
}



class ArithmeticCell extends SimpleCell {
  private int left, right;

  public ArithmeticCell(String s) {
    super(s);
  }

  public ArithmeticCell(String s, SimpleCell s1, SimpleCell s2) {
    super(s);
    //s1.addObserver(n -> setLeft(n));
    s1.addObserver(new LeftObserver(this));
    s2.addObserver(n -> setRight(n));
  }
  
  void setLeft(int n) {
    left = n;
    update(left+right);
  }
  
  void setRight(int n) {
    right = n;
    update(left+right);
  }
  
}

class SimpleCell implements Subject<Integer>, Observer<Integer> {
  private String name;
  private int val;
  private List<Observer<Integer>> obs;
  
  public SimpleCell(String s) {
    name = s;
    obs = new ArrayList<>();
    val = 0;
  }

  @Override
  public void update(Integer a) {
    val = a;
    System.out.println(name + ":" + val);
    notifyObservers();
  }

  @Override
  public void addObserver(Observer<Integer> o) {
    obs.add(o);
  }

  @Override
  public void notifyObservers() {
    obs.forEach(ob -> ob.update(val));
  }
}
