# Generics, Anonymous Class, Lambda Expression
## Embracing Modern Java 1

---
# Piazza Bonus

* Satisfying Overall Participation
* 1% bonus for everyone who has registered
  * Although there are some who's only been online for less than 3 days.
* More bonuses to come with cut off
  * The median of "contributions"

---
# Motivation

```
      A       B      C
  +---------------------+
1 |      |       |      |
  +---------------------+
2 |      |       |      |
  +---------------------+
```

* `B2 := 2 * A1`
* `C2 := A2 + B2`


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
# Functional Interface

* An interface is a functional interface when it has one method
* If so, the interface can be instantiated by a lambda expression
* (Java) Lambda expression
  * A function/method without a name
  * `<parameters> -> <body>`
  * When body is one single expression, `return` is not necessary!
* `Function<A,B>` is a functional interface with `B apply(A a)`

---
# Generics and Type Variables

* Will not talk about motivation today
* Terminology
* `List<A>`
* `A` in `List<A>` is a *type variable*
  * It's not a *generic type*
* A type is a *generic type* when it contains type variables
  * e.g. `List<A>`
* A generic type used without the type variable is a called a *raw type*, e.g.
  `List`

---
# Analogies - One

* `int addOne(int n)` is a function/method
* Apply `addOne` to an integer `2`, you get another integer
* `List<A>` is a generic type
* Apply `List<A>` to a type `Integer`, you get another type `List<Integer>`
* `n` in `addOne` is a parameter, `A` in `List<A>` is a type parameter

---
# Analogies - Two

* What is the type of `2`? `Integer`
* What is the type of `int addOne(int n)`? `Int -> Int`
  * or "a function which takes in an int and returns an int"
* So what is the "type" of a type, e.g. `Int`?
* The term is "kind"
* The kind of `Int` is `*`
* The kind of `List<A>` is `* -> *`

---
# Analogies - Three

* For `int n`, what are possible values of `n`?
* `1,-1,2,-2,...`
* For `A`, what are possible values of `A`?
* `Object, Int, Boolean, Student, ...`
* Note that `A` cannot be primitive types such as `int, boolean,...`

---
# Review Terminologies

* Type variable
* Generic type
* Raw type

---
# Spread Sheet Example

* Simulating simple formulas on cells

```java
interface Subject<A> {    // declare type variable
  // make sure only add observers that can understand your message
  void addObserver(Observer<A> o);
  void notifyObservers(); }
interface Observer<A> {  // declare type variable
  void update(A a); }  // only expects to receive A
// Note the two A's are not related - different scope
// Like int add(int n)     and   int abs(int n)
```

---
# SimpleCell

* A simple cell is both a subject and an observer.

```java
class SimpleCell implements Subject<Integer>, Observer<Integer>
```

---
# Motivation

```
      A       B      C
  +---------------------+
1 |      |       |      |
  +---------------------+
2 |      |       |      |
  +---------------------+
```

* `B2 := 2 * A1`
* To simplify things, a cell gets a name like "A2"

---
# SimpleCell

* Fields?
* Name
* Int value
* A list of observers

---
# SimpleCell

* Methods?
* First, the ones to be implemented
* Second, constructor
* What else?

---
# Update and others

* `void update(Integer a)`
* assigns a to val
* notify the downstream observers!
* the others are simple

---
# Motivation

```
      A       B      C
  +---------------------+
1 |      |       |      |
  +---------------------+
2 |      |       |      |
  +---------------------+
```

* `B1 :=  A1`
* Who's the observer? Who's the subject?

---
# Setting Up

```java
var A1 = new SimpleCell("A1")
var B1 = new SimpleCell("B2")
A1.addObserver(B2)
A1.update(12)
A1.update(333)
```

---
# More

* `B2 := 2 * A1`
* How to do this?

---
# Another Kind of Observer

* A new class of observer whose update
* doubles the value it takes in
* Change `val` and `name` to `protected`

```java
class DoubleCell extends SimpleCell  {
  public DoubleCell(String s) { super(s); }
  @Override
  public void update(Integer a) {
    val = 2 * a;
    System.out.println(name + ":" + val);
    notifyObservers();
  }
}
```

---
# Setting up

```java
var A1 = new SimpleCell("A1")
var B2 = new DoubleCell("B2")
```

---
# A Probe Only

* Think: Why p1 is allowed to be added to A1?

```java
class Probe implements Observer<Integer>  {
  @Override
  public void update(Integer a) {
    System.out.println("Value propagated: " + a);
  }
}

var p1 = new Probe()
A1.addObserver(p1)
```

---
# Anonymous Object

* We have seen this
* The `new Probe()` object does not have a name
  * (does not have a variable referencing to it)

```java
A1.addObserver(new Probe())
```

---
# Anonymous Class

* We can also make a Java class (out of an interface) without a name!
* So we don't need to write a `Probe` class
* We define one on the fly
* In real world, there are many chances that you will only use a class
  definition once

```java
var p1 = new Observer<Integer>() {
  public void update(Integer a) {
    System.out.println("Value propagated: " + a);
  }
}
```

---
# Simplifying It Even More

---
# Functional Interface

* An interface is a functional interface when it has one method
* If so, the interface can be instantiated by a lambda expression
* (Java) Lambda expression
  * A function/method without a name
  * `<parameters> -> <body>`
  * When body is one single expression, `return` is not necessary!

---
# Observer is a Functional Interface

* Why?
* This time you need to specify the static type

```java
Observer<Integer> probe = a -> System.out.println("Value propagated: " + a)
```

---
# Final Challenge

* `B3 := B1 + B2`
* Hint: Make an observer that monitors `s1`'s changes and updates the `left`
  field accordingly, same for `right`
* https://repl.it/@engr120/observer-spreadsheet-incomplete

```java
class ArithmeticCell extends SimpleCell {
  private int left, right;
  public ArithmeticCell(String s, SimpleCell s1, SimpleCell s2) {
    super(s);
    /* ??? */
  }
  void setLeft(int n) { }
  void setRight(int n) { }
}
```

---
# Homework

* Quiz 2 due Friday 10a
* About observer pattern, 5 attempts, no time limit

* Wednesday: Lab 3
  * Threads
