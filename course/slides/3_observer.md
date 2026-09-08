# Observer Pattern

---
# LAB 1 FAQ

* Everything in Java is a **reference**. Except for **primitive types**.
* Primitive types: `int`, `double`, ...
* Reference types: Array, String, other classes
* Reference: a restricted pointer
  * Actual value of a reference?

---
# Myth 1: What does it print out?

* If `toString()` is not overridden, the string representation of an object is
  its (heap) reference id

---
# Myth 2: `equals` and `==`

* `==` compares the reference ids
* If `equals` is not overridden, `equals` and `==` is the same
* Whenever you override `equals`, you MUST also override `hashCode`.
  * so that: Two object `equals` iff their hash-codes are the same
  * Otherwise your system is broken
* More about [equals](https://docs.oracle.com/javase/10/docs/api/java/lang/Object.html#equals(java.lang.Object))
  * reflexive, symmetric, transitive, consistent

---
# Myth 3: Default Values

* Java: local variables must be initialized
* Instance variables will have default values
* Primitive types: `int` 0, `double` 0.0, ...
* Reference types: `null`

---
# Myth 3: Default Values

```java
Cell[][] cells;  // cells is null

Cell[][] cells = new Cell[3][3];
// cells is not null, it refers to an array,
// the element type is Cell which is a reference type, so all the array elements
// are null


Cell[][] cells = new Cell[3][3];  cells[1][1] = new Cell();
// cells is not null, it refers to an array
// cells[1][1] is not null, it refers to a Cell object
// other elements are null
```

---
# Computer Lab Access

* The process is for entering the building when it's locked
* If you stay in the lab, it's fine
  * surveillance cameras

---
# When One Passes Data to Others

![](https://drive.google.com/uc?id=1LEE_Pc2XtaY_VnP2Z_wrHNWwhDq8gswn)

---
# First, Most Primitive Form of Observer Pattern

```java
interface Observer {
  void update();
}

interface Subject {
  void addObserver(Observer o);
  void notifyObservers();
}
```

---
# SWE200 UML

* Visibility
  * `+` public
  * `-` private
  * `#` protected
  * `~` package private (not modifier in Java)
* Access modifiers always required
* A reading assignment about access modifiers

---
# SWE200 UML

* Each "record" has three compartments
  * Class/interface name
    * if `abstract` or `interface`, use `<<abstract>>` or `<<interface>>` prefix
  * (important) instance variables
  * (important) methods
* A bit different syntax
  * variable types or method return types follows the names
  * separated by `:`

---
# SWE200 UML

* Solid line with an arrow head for `extends`
  * from the subclass to the superclass
* Dashed line with an arrow head for `implements`
* Solid line with a solid diamond at the source for `contains`
* Arrow head can be solid or hollow, but must be consistent
<!-- * ![](https://upload.wikimedia.org/wikipedia/commons/0/0b/Uml_class_relation_arrows_en.svg.png) -->

---
# Implementations

* The subject: a signal that sends out pulses
  * Keeps a list of observers
* The observers: several counters for the pulses
  * A counter has its name
  * A counter remembers the number of pulses _it_ sees

---
# The Counter

```java
class Counter implements Observer {
  String name; int n=0;
  Counter(String s) { name = s; }
  void update() {
    n += 1; println(name + " counts " + n); }}
```

---
# The Signal

* Note the use of `forEach`

```java
class Signal implements Subject {
  List<Observer> obs = new ArrayList<>();
  void addObserver(Observer o) { obs.add(o); }
  void notifyObservers() { obs.forEach(ob -> ob.update()); }
}
```

---
# Example Use

* Create a subject, add the objects using `addObserver`
* You could add `removeObserver` to remove an observer from the list

---
# Design Principal 4

Strive for loosely coupled designs between objects that interact.


---
# Loosely Coupled

* The relation between the observers and the subject is not too tight.
* An observer can be added or removed on the fly.

```java
interface Observer { void update(); }
interface Subject {
  void addObserver(Observer o);
  void removeObserver(Observer o);
  void notifyObservers();
}
```

---
# Excercise

* Add `removeObserver` to the signal example. Try it out on `jshell`

---
# Example

* In reality, `notifyObservers` is triggered by some other methods that give useful information
* Weather Station! https://repl.it/@engr120/WeatherStation-Observer


---
# Myth: `List` or `ArrayList`?

* It is slightly different from the textbook (and the lab manual).

```java
class Signal implements Subject {
  List<Observer> obs = new ArrayList<>();

  // In the textbook:
  ArrayList<Observer> obs = new ArrayList<>();
```

---
# The Collection Hierarchy

<!-- ![](https://tutorialstechmytalk.files.wordpress.com/2014/07/collectionsummary.jpg) -->

* `https://tutorialstechmytalk.files.wordpress.com/2014/07/collectionsummary.jpg`

---
# The Least Thing Can be Iterated

* `Iterable`
  * In fact it's `Iterable` which provides the `forEach` method
  * And the `for-each` style loop
* `Iterable<Observer> obs = new ArrayList<>();`
  * static type? dynamic type?

---
# Avoid Being Too Specific

* **Avoid unnecessary assumptions**
  * Code can be ambiguous
  * Some are _essential_ and some are _incidental_
* You are allowed to write both
  * `List<Observer> obs = new ArrayList<>();`
  * `ArrayList<Observer> obs = new ArrayList<>();`

<!--
---
# Benefits Using Higher Level Abstraction

* Assume you changed Subject interface so that it can take in a "beep" of
  Observers
  * `setObservers(<beep> observers) { obs = observers; }`
* You still have `ArrayList<Observer> obs = new ArrayList<>();`
* If Joe uses you code and he happens to have a `LinkedList` of Counters ...
-->

---
# Modern Java Tips

* Use the `of` builders for constructing (immutable) collections
  * Cannot `add` or `remove` on such collections
  * Immutable data structures do not violate the consistency rule of `equals`

```java
List<Int> lst = new ArrayList<>();
lst.add(1);
lst.add(2);
lst.add(2);
// VS
List<Int> lst = List.of(1, 2, 3);
Set<Int> lst = Set.of(1, 2, 3);
```

---
# What's the `A` in `List<A>`?

* In Java, it's called _generics_ (since Java 5, year 2005)
* `A` is a type parameter (type variable)
* `List<A>` is a _generic type_
  * `Human` is a type, `List<A>` is not a type
  * `List<Observer>` is a type
  * `List<Integer>` is a type

---
# Type Variable

Compare | (Regular) Variable | Type Variable
-|-|-
Name | x, y, ... | A, B, T, ...
values | 3, "abc", ... | Integer, Object, Human, ...

---
# Declare Type Variable

1. After the name of the class. The scope is the entire class
1. Before a method declaration. The scope is the entire method

```java
class List<A> {
  A x;
  <B> void go(B b) { /* ... */ }
}
```

---
# Applying Types

* Like a function application, we "apply" a concrete type to a generic type to
  make a concrete type

```java
List<Integer> list = // ...

Integer b = // ...
go(b);
```

---
# Relation between Subjects and Observers: Push and Pull

* `Push` means the subject sends data to the observers
  * `update(Object o)`
  * Will use `int` in lab 3
  * Can be improved with generics
  * `update(A a)`
* `Pull` means the observers retrieves the data from the subject
  * if we use `update(Subject s)`, the observer may retrieve information from
    the subject

---
# Spread Sheet Example

* Simulating simple formulas on cells

```java
interface Subject<A> {
  void addObserver(Observer<A> o);
  void notifyObservers(); }
interface Observer<A> {
  void update(A a); }
```

---
# SimpleCell

* A simple cell is both a subject and an observer.

```java
class SimpleCell implements Subject<Integer>, Observer<Integer>
```

---
# Get the Sum

* A cell that always gets the sum of two other cells
* It's convenient to make it a sub-class of `SimpleCell`
* Should have two instance variables as the operands

---
# Homework

* Read textbook Ch2
  * "Code magnets" on page 69
  * Observable and Observers
    * Pay attention to the `instanceof` and the type cast on page 68.
    * Pay attention to "The dark side of java.util.Observable" on page 71.

<!--
---
# Quiz 1

* Multiple choices or T/F, 5 attempts
* java.util.Observable, interface or class?
* What does `Observable.setChanged()` do?
* The red line in "Tab vs. Space" article.
* What is the least thing that have `add` method in Java Collection Hierarchy?
-->

---
# Thursday

* Lab 1 is due at 9a
* Start to use `git` in Lab 2
  * If you do plan to use your laptop, install it before the Wednesday class
  * Google `git install windows`, https://git-scm.com/download/win
  * You will use the free online book `Pro Git` for reference
    * chapter 1.5 for installing git
* Start Lab 2, using strategy pattern
