# More on Observer Pattern

---
# Review

```java
interface Observer {
  public void update(Object o);
}
interface Subject {
  public void addObserver(Observer ob);
  public void notifyObservers();
}
```

---
# Hunters vs. Ducks

* You have to implement the methods again, e.g. `addObservers`.
* (Catalog) Design patterns are different from library data structures (e.g.
  `TreeSet`).

```java
class DuckRadar implements Subject {
  List<Observer> obs = new ArrayList<>();
  public void addObserver(Observer ob) { obs.add(ob); }
  public void notifyObservers() {
      //sending strings
      obs.forEach(ob -> ob.update("Ducks spotted near MCT")); }
```

---
# Hunters

```java
class Hunter implements Observer {
  public void update(Object o) {
    //expecting integers
    if (o instanceof Integer) {
      Integer i = (Integer) o; }}}
```

---
# Using the Radar

```java
DuckRadar dr = new DuckRadar();
Hunter h = new Hunter();
dr.addObserver(h); //compiles but problematic
dr.notifyObservers(); //silent bug, hunters never get information
```

---
# Restricting the Type of the Message

* (First) Problem solved

```java
interface Observer<A> {
  public void update(A a); }
interface Subject<A> {
  public void addObserver(Observer<A> ob);
  public void notifyObservers(); }
class DuckRadar implements Subject<String>
  //Must add Observer of Strings
  public void addObserver(Observer<String> ob) { obs.add(ob); }}
```

---
# Duck Spies

* `Duck` is also an Observer of Strings

```java
class Duck implements Observer<String> {
  public void update(String s) {
    System.out.println("Run Ducks! RUN!"); } }
```

---
# Duck Spies

```java
DuckRadar dr = new DuckRadar();
Hunter h1 = new Hunter();
Hunter h2 = new Hunter();
Duck duckSpy = new Duck();
dr.addObserver(h1);
dr.addObserver(h2);
dr.addObserver(duckSpy); //problematic
dr.notifyObservers();
```

---
# Restricting the Type of Observers

* `Subject` takes in an `O`
* `DuckRadar` must take in `Hunter`s

```java
interface Observer<A> { //same
  public void update(A a); }
class DuckRadar implements Subject<Hunter, String> { /* same */ }
interface Subject<O extends Observer<A>, A> {
  public void addObserver(O ob);
  public void notifyObservers(); }
class DuckRadar implements Subject<Hunter, String> { /* .. */ }
```

---
# Restricting the Type of Observers

```java
DuckRadar dr = new DuckRadar();
Hunter h1 = new Hunter();
Hunter h2 = new Hunter();
Duck duckSpy = new Duck();
dr.addObserver(h1);
dr.addObserver(h2);
dr.addObserver(duckSpy); //compiler error

dr.notifyObservers();
```

---
# Fake Radar

* Very natural to think about restricting the type of subjects
* Ducks can make a fake radar station to fool the hunters

```java
class FakeRadar implements Subject<Hunter, String> {
  public void notifyObservers() {
      obs.forEach(ob ->
        ob.update("Ducks near Chambersburg.")); }}
```

---
# Fake Radar

```java
DuckRadar dr = new DuckRadar();
FakeRadar fr = new FakeRadar();
Hunter h1 = new Hunter();
Hunter h2 = new Hunter();
fr.addObserver(h1);
fr.addObserver(h2);

fr.notifyObservers()
```

---
# Restricting the Subject Type

```java
interface Observer<S extends Subject<S, O, A>,
                   O extends Observer<S, O, A>, A> {
  public void update(A a);
}

interface Subject<S extends Subject<S, O, A>,
                  O extends Observer<S, O, A>, A> {
  public void addObserver(O ob);
  public void notifyObservers();
}
```

---
# Restricting the Subject Type

```java
class Hunter implements Observer<DuckRadar, Hunter, String>
class DuckRadar implements Subject<DuckRadar, Hunter, String>
class FakeRadar implements Subject<FakeRadar, Hunter, String>
//FakeRadar does not compile. Why?
```

---
# Final Version

* Available on D2L/Contents/Code Examples/hunter-vs-ducks.zip

---
# Weather Display

* Skeleton available on D2L/Contents/Code Examples/quiz4-weather-display
* Quiz 4 (9/23 in class)
