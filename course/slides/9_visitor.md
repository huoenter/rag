# The Visitor Pattern

---
# Today

* Review for Exam 2
  * Nov 6 (Wednesday in class)
* Quiz 8
  * solutions of quiz 7 and 8
* Visitor pattern
  * Visitor pattern continued next Monday
* Next Wednesday (10/13): doing lab 6 in class
  * That's one week before the due date!
  * Designs need to be approved and uploaded to gitlab!
* Tentative lab 6 "auction"

---
# Review for Exam 2

* Singleton, Factory, Command, Iterator, Composite
* Text definition, General UML from the textbook
* Everything from the slides
* Cheatsheet
  * letter/A4 size
  * both sides
  * must be handwritten

---
# Singleton

* Private constructor
* Private instance variable to hold the singleton
  * lazy initialization
* `getInstance` as the "visiting time"
  * If lazy, check if the instance exists before returning the instance
    variable
* If you have an almost Singleton pattern, but use loose access modifiers, what
  could go wrong?
* When two (instead of one) threads compete for the singleton, what could go
  wrong? Check the slides.

---
# Factory

* Three variants
* Simple Factory
  * Encapsulates the production in the simple factory
    * `createPizza` in the factory
  * As an instance variable of the pizza store
  * Problem is that any third party can pass a sewer pizza factory to a pizza
    store
* Factory Method
  * An abstract method in the pizza store
    * Like `createPizza` but the control of production is back to the stores
  * Concrete stores implement the production in different ways

---
# Factory

* Abstract Factory
* Example
  * `AbstractIngredientFactory` creates ingredients of different styles
  * The pizza stores of different styles create the factories and pass them to
    pizza constructors
  * The pizza constructors use the factories the get the corresponding
    ingredients

---
# Command

* Actions (functions) as first-class objects
* Example: "Programmable" remote control
  * Adding an `undo` button
* Roles: invoker, receiver, client, etc
* Macro commands
  * individual commands in an array
  * the binary operator `andThen`

---
# Iterator

* A uniformed experience to traverse different sorts of collections
  * `hasNext` and `next`
  * `next` returns an element and advances the iterator
  * At the beginning, the iterator is to the "left" of the first element
* Example: Same experience for traversing the menu items in a menu with an
  array and a menu with a list
* Instead of making the menus iterators themselves, we added a "thin" method
  `createIterator` to the menus.
  * Because (ideally) each class should have one responsibility
  * The `createIterator` method returns an iterator implemented in a separate
    family of classes

---
# Composite

* part-whole hierarchy
* individual and aggregate object are treated the same way
  * That's the very opposite of using `instanceof`
* Create a common interface for the individual and the aggregate types
* Define a method for both
  * Easy for the individual type
  * The method must handle the structure of the aggregate type

---
# Composite

* Example: Printing the backpack (and the items in it)
  * Goal: calling `print` on a given backpack takes care of all
    * no matter how "deep" the backpack is
  * `components.forEach(c -> c.print());`
  * can customize `print`, e.g. with indentation levels `print(String indent)`
  * Can you achieve indentation without using parameters? i.e. keep using
    `print()`

---
# UML

`![](https://drive.google.com/uc?id=1c2W9wRDK7pU4vrrRblNy4GWYQX-9g9Xb)`

---
# Lambda Expressions

* Anonymous class (which you'll use a lot in lab 6)
* `Command` is a `FunctonalInterface`
  * In particular, `execute` in `Command` is a method with no inputs and no
    outputs
  * `() -> /* some side effects */` makes a `Command`
* `forEach` from `Iterable` (Note that `List` is `Iterable`)
  * `forEach` takes in a `Consumer`
  * `elem -> /* some side effects */`
  * e.g. `obs.forEach(ob -> ob.update())`
  * `ob -> ob.update()` instantiates a `Consumer` via a lambda expression
* Git: I'll leave it to the final

---
# Accepting Visitors

---
# Two Approaches

* The Backpack way
* The Little Java way

---
# Recap

```java
interface Item { public void print(); }
class Book implements Item {
  String title;
  public Book(String n) { title = n; }
  public void print() { System.out.println(title); }}
class Backpack implements Item {
  List<Item> items;
  public Backpack(List<Item> l) { items = l; }
  public void print() {
    System.out.println("Backpack:");
    items.forEach(item -> item.print()); }}
```

---
# Weight

* Imagine `Book` and `Backpack` now both have prices
* Can we print out the price for each item?

```java
class Book implements Item {
  String title; double price;
  public Book(String n, double p) {
    title = n; price = p; }
  public void print() {
    System.out.println(title); }}
```

---
# Show Price

```java
interface Item {
  void print();
  void showPrice(); }
```

---
# Show Price

* Add `showPrice` in `Book` and `Backpack`
* We don't want to "open" all the classes over and over for additional
  functionalities
* Similar to `createIterator` or `forEach`, we just leave a door for all
  possibilities
* Create another family for the functionalities

---
# Studying `forEach`

* `Iterable` has `void forEach(Consumer<A> action)`
  * `A` matches the element type of the container (`Iterable`)
  * e.g. A list of `Book` will need a `Consumer<Book>`
  * e.g. `b -> b.print()` Why is it a `Consumer<Book>`?
* `forEach` opens a door and invites all kinds of *actions* to come
* Literally the action is performed on each (element)

---
# Studying `forEach`

```java
List<Book> books = List.of(book1, book2, book3);
Consumer<Book> printBook = book -> book.print();

books.forEach(printBook);
```

---
# A Naive `forEach` for Lists

```java
class List<A> {
  /* other methods */
  void forEach(Consumer<A> action) {
    Iterator<A> iter = iterator(); //List is Iterable
    while (iter.hasNext()) {
      A elem = iter.next();
      action.accept(elem); }}}
```

---
# Our `forEach`

* While the elements in a container are homogeneous (same), in our part-whole
  hierarchy, we have different types of things like books and backpacks
* This brings some challenges

---
# Trying Out

```java
interface Item {
  void print();
  void forEach(??? action);
}
```

---
# Trying Out

* Call it a visitor (instead of `Consumer`)
* Leave a pet door for the visitor: `forEach`

```java
interface Item {
  void forEach(ItemVisitor action);
}
```

---
# The Item Visitor

* So far we just need a visitor that can print

```java
class ItemVisitor {
  void visit(Item item) {
    /* visiting! */
  }
}
```

---
# The Item Visitor

```java
class ItemVisitor {
  void visit(Item item) {
    if (item instanceof Book) {
      Book b = (Book) item;
      b.print();
    }}}
```

---
# The Item Visitor

```java
class ItemVisitor {
  public void visit(Item item) {
    if (item instanceof Book) {
      Book b = (Book) item;
      System.out.println(b.title);
    } else {
      Backpack bp = (Backpack) item;
      System.out.println("Backpack:");
      bp.items.forEach(i -> i.forEach(this)); }}}
```

---
# Leave a Pet Door

```java
class Book implements Item {
  String title;
  public Book(String n) { title = n;}
  public void forEach(ItemVisitor v) { v.visit(this); }}
class Backpack implements Item {
  List<Item> items;
  public Backpack(List<Item> l) { items = l;}
  public void forEach(ItemVisitor v) { v.visit(this); }}
```

---
# Two Thises

* In the visitor `bp.items.forEach(i -> i.forEach(this))`
* In the book `void forEach(ItemVisitor v) { v.doit(this); }}`

---
# The Evil `instanceof` and Type-casts

* For many many reasons, you are only supposed to use `instanceof` in an
  `equals` method
* For many many reasons, you should avoid (type-)casting

---
# Split the Visit Method

* The `forEach` of Lists only works with one single type of data
* We split the visit method so that each deals with one single type of variant
  * Hopefully the new visit methods will be dispatched correctly somehow

---
# Comparison

```java
class ItemVisitor {
  public void visit(Item item) {
    if (item instanceof Book) {
      Book b = (Book) item;
      System.out.println(b.title);
    } else {
      Backpack bp = (Backpack) item;
      System.out.println("Backpack:");
      bp.items.forEach(i -> i.forEach(this)); }}
}
```

{.column}

```java
class ItemVisitor {
  public void visit(Book b) {
    System.out.println(b.title);}

  public void visit(Backpack bp) {
    System.out.println("Backpack:");
    bp.items.forEach(i -> i.forEach(this)); }}
```

---
# Why the Methods Are Dispatched Correctly?

* `void forEach(ItemVisitor v) { v.visit(this); }`
  * It is the same for `Book` and `Backpack`
  * The `this` for `Book` is a `Book`
  * So `visit(Book b)` will be (statically) dispatched
* `bp.items.forEach(i -> i.forEach(this))`
  * Why not `bp.items.forEach(i -> this.visit(i))`?
  * Which `forEach` will `i` use? It depends on what `i` is during runtime
  * So `forEach` is dynamically dispatched
* The visitor pattern is said to be double-dispatch

---
# We Are Close

* Rename `forEach` to `accept`

---
# Structure in the Visitor or the Items?

* Should `accept` or `visit` handle the structure?
  * Many may not care about the difference
  * In previous examples, it was `visit`
* It should be `accept`
  * Visitor are *outsiders*
  * The backpack has no obligation to tell the outsiders about the structure.
    Maybe it should not
  * We had to expose `items` list in our example

---
# Revising Backpack

```java
class Backpack implements Item {
  List<Item> items; double price;
  public Backpack(List<Item> l, double p) {
    items = l; price = p; }
  public void accept(ItemVisitor v) {
    v.visit(this);
    items.forEach(item -> item.accept(v)); }}
```

---
# Simplifying the Visitor

```java
class ItemVisitor {
  public void visit(Book b) {
    System.out.println(b.title); }

  public void visit(Backpack bp) {
    System.out.println("Backpack:"); }}
```


---
# The Visitor Pattern

```java
interface Visitor {
  public void visit(Backpack b);
  public void visit(Book b);
}
```

---
# The PrintVisitor

```java
class PrintVisitor implements ItemVisitor {
  public void visit(Book b) {
    System.out.println(b.title); }

  public void visit(Backpack bp) {
    System.out.println("Backpack:"); }
}
```

---
# The PriceVisitor

* Visitors can have states

```java
class PriceVisitor implements ItemVisitor {
  private double total;
  public void visit(Book b) { total += b.price; }
  public void visit(Backpack bp) { total += bp.price; }
  public void showTotal() { println("Total is $"+total); }
}
```

---
# Exercise

* Write a visitor which prints out the price of the most expensive item in a
  backpack (could be a book or a backpack)
