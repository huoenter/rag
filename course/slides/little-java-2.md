# A Little Java, A Few Patterns

![](https://images-na.ssl-images-amazon.com/images/I/51bwqQFmmKL._SX403_BO1,204,203,200_.jpg)

---
# Last Time

* Decorator-like

```java
new Onion(
  new Lamb(
    new Onion(
      new Skewer()))).onlyOnions()
```

---
# Only Onions

```java
interface Shish { boolean onlyOnions(); }

class Skewer implements Shish {
  public boolean onlyOnions() { return true; }
}

class Onion implements Shish {
  private Shish s;
  public Onion(Shish ss) { s = ss; }
  public boolean onlyOnions() { return s.onlyOnions(); }
}
```

---
# Only Onions

```java
class Lamb implements Shish {
  private Shish s;
  public Lamb(Shish ss) { s = ss; }
  public boolean onlyOnions() { return false; }
}

class Tomato implements Shish {
  private Shish s;
  public Tomato(Shish ss) { s = ss; }
  public boolean onlyOnions() { return false; }
}
```

---
# Remove Onions

* Note that it returns a `Shish`

```java
interface Shish { Shish removeOnions(); }
```

---
# Remove Onions

```java
class Onion implements Shish {
  Shish s; /* other things */
  public Shish removeOnions() {
    return ???;
  }
}
```

---
# Remove Onions

```java
class Onion implements Shish {
  Shish s; /* other things */
  public Shish removeOnions() {
    return s;
  }
}
```

---
# Remove Onions

```java
class Tomato implements Shish {
  Shish s; /* other things */
  public Shish removeOnions() {
    return ???;
  }
}
```

---
# Remove Onions

```java
class Tomato implements Shish {
  Shish s; /* other things */
  public Shish removeOnions() {
    return new Tomato(s.removeOnions());
  }
}
```

---
# Remove Onions

```java
class Skewer implements Shish {
  Shish s; /* other things */
  public Shish removeOnions() {
    return new Tomato(s.removeOnions());
  }
}
```

---
# Top Onion With Lamb

```java
interface Shish { Shish topOnionWithLamb(); }
```

---
# Top Onion With Lamb

```java
class Tomato implements Shish {
  Shish s; /* other things */
  public Shish topOnionWithLamb() {
    return ???;
  }
}
```

---
# Top Onion With Lamb

```java
class Tomato implements Shish {
  Shish s; /* other things */
  public Shish topOnionWithLamb() {
    return new Tomato(s.topOnionWithLamb());
  }
}
```

---
# Top Onion With Lamb

```java
class Onion implements Shish {
  Shish s; /* other things */
  public Shish topOnionWithLamb() {
    return ???;
  }
}
```

---
# Top Onion With Lamb

```java
class Onion implements Shish {
  Shish s; /* other things */
  public Shish topOnionWithLamb() {
    return new Lamb(new Onion(s.topOnionWithLamb()));
  }
}
```

---
# What's This?

* Give a name to `.topOnionWithLamb().removeOnions()`

---
# What's This?

* Give a name to `.topOnionWithLamb().removeOnions()`
* Implement `substLambForOnion` directly (ice)

---
# Factoring Out

* We opened the classes for too many times
* Consider `removeOnions` and `substLambForOnion`

---
# Factoring Out the Implementation

* Subtle question: why do they need an `s`?

```java
class OnlyOnions {
  boolean forSkewer() { return ture; }
  boolean forOnion(Shish s) { return s.onlyOnions(); }
  boolean forLamb(Shish s) { return false; }
  boolean forTomato(Shish s) { return false; }
}
```

---
# Factoring Out the Implementation

* Subtle question: why do they need `s`?
* There is a price for extracting them out
  * We cannot let the outsider know how we put things inside
  * But we explicitly tell the outsider what's inside

```java
class OnlyOnions {
  boolean forSkewer() { return ture; }
  boolean forOnion(Shish s) { return s.onlyOnions(); }
  boolean forLamb(Shish s) { return false; }
  boolean forTomato(Shish s) { return false; }
}
```

---
# Factoring Out the Implementation

```java
abstract class Shish {
  OnlyOnions ooFn = new OnlyOnions();
  abstract boolean onlyOnions();
}

class Onion extends Shish {
  /* other methods */
  public boolean onlyOnions() { return ooFn.forOnion(s); }
}

/* other classes */
```

---
# Come to Our Carousel

ch4

---
# Come to Our Carousel

```java
new Onion(
  new Onion(
    new Lamb(
      new Skewer()))).onlyOnions()
```

{.column}

```java
class OnlyOnions {
  boolean forSkewer() {
    return ture; }
  boolean forOnion(Shish s) {
    return s.onlyOnions(); }
  boolean forLamb(Shish s) {
    return false; }
}
```

---
# Come to Our Carousel

```java
new Onion(
  new Onion(
    new Lamb(
      new Skewer()))).onlyOnions()

//in Onion
boolean onlyOnions() {
  return ooFn.forOnion(s);
}
```

{.column}

```java
class OnlyOnions {
  boolean forSkewer() {
    return ture; }
  boolean forOnion(Shish s) {
    return s.onlyOnions(); }
  boolean forLamb(Shish s) {
    return false; }
}
```

---
# Come to Our Carousel

```java
new Onion(
  new Onion(
    new Lamb(
      new Skewer()))).onlyOnions()

//in Onion
boolean onlyOnions() {
  return ooFn.forOnion(s);
}
```

{.column}

```java
class OnlyOnions {
  boolean forSkewer() {
    return ture; }
  boolean forOnion(Shish s) {
    return s.onlyOnions(); }
  boolean forLamb(Shish s) {
    return false; }
}
```

---
# Come to Our Carousel

```java
new Onion(
  new Onion(
    new Lamb(
      new Skewer()))).onlyOnions()

//in Skewer
boolean onlyOnions() {
  return ooFn.forSkewer(s);
}
```

{.column}

```java
class OnlyOnions {
  boolean forSkewer() {
    return ture; }
  boolean forOnion(Shish s) {
    return s.onlyOnions(); }
  boolean forLamb(Shish s) {
    return false; }
}
```

---
# Come to Our Carousel

```java
new Onion(
  new Onion(
    new Lamb(
      new Skewer()))).onlyOnions()

//in Skewer
boolean onlyOnions() {
  return ooFn.forSkewer(s);
}
```

{.column}

```java
class OnlyOnions {
  boolean forSkewer() {
    return ture; }
  boolean forOnion(Shish s) {
    return s.onlyOnions(); }
  boolean forLamb(Shish s) {
    return false; }
}
```


---
# Factoring Out the Implementation

* Note that the "carousel" still exists

```java
abstract class Shish {
  OnlyOnions ooFn = new OnlyOnions();
  SubstLambForOnion substFn = new SubstLambForOnion();
  abstract boolean onlyOnions();
  abstract Shish substLambForOnion();
}
```

---
# Factoring Out the Implementation

```java
class Onion extends Shish {
  /* other methods */
  public boolean onlyOnions() { return ooFn.forOnion(s); }
  public Shish substLambForOnion() { return substFn.forOnion(s); }
}

/* other classes */
```

---
# Boring Protocols

ch6

---
# Boring Protocols

* We do not want to repeat almost the same thing in two methods

```java
public boolean onlyOnions() { return ooFn.forOnion(s); }
public Shish substLambForOnion() { return substFn.forOnion(s); }
```

---
# Interesting Protocol

* One method returns `boolean` and the other returns `Shish`
  * It's better to use type parameters but here just `Object`s

```java
interface Shish {
  Object accept(ShishVisitor v);
}
```

---
# Interesting Protocol

* Different from the book

```java
interface ShishVisitor {
  Object forSkewer();
  Object forTomato(Shish s);
  Object forLamb(Shish s);
  Object forOnion(Shish s);
}
```

---
# Interesting Protocol

```java
class SubstLambForOnion implements ShishVisitor {
  public Shish forSkewer() { return new Skewer(); }
  public Shish forOnion(Shish s) { return new Lamb((Shish) s.accept(this)); }
  public Shish forLamb(Shish s) { return new Lamb((Shish) s.accept(this)); }
  public Shish forTomato(Shish s) { return new Tomato((Shish) s.accept(this)); }
}
```

---
# Interesting Protocol

* Ugly casts (otherwise will not compile)
* In quiz/exam, you don't have to provide this
  * You need to know it's wrong without it ☻

```java
class SubstLambForOnion implements ShishVisitor {
  public Shish forSkewer() { return new Skewer(); }
  public Shish forOnion(Shish s) { return new Lamb((Shish) s.accept(this)); }
  public Shish forLamb(Shish s) { return new Lamb((Shish) s.accept(this)); }
  public Shish forTomato(Shish s) { return new Tomato((Shish) s.accept(this)); }
}
```

---
# Interesting Protocol

```java
class Onion implements {
  Shish s; /* other things */
  public Object accept(ShishVisitor v) {
    return v.forOnion(s);
  }
}
/* other classes */
```

---
# Getting Rid of Type-casts

* See in `PrettyVisitor.java`

```java
interface Shish { <A> A accept(ShishVisitor<A> v); }

interface ShishVisitor<A> {
  A forSkewer();
  A forOnion(Shish s);
  A forLamb(Shish s);
  A forTomato(Shish s);
}
```

---
# Visitor With States

* `LtdSubstLambWithOnion` only converts the first `n` onions to `Lamb`
* ice
* See in `VisitorWithStates.java`

---
# A Bit More Academic

What is the value of `(7 + ((4-3) * 5))`?

---
# A Bit More Academic

What is the value of `(+ 7 (* (- 4 3) 5))`?

---
# A Bit More Academic

What is the value of

```java
new Plus(
  new Const(new Integer(7)),
  new Prod(
    new Diff(
      new Const(new Integer(4)),
      new Const(new Integer(3))),
    new Const(new Integer(5))))
```

---
# Sets

`({7, 5} ∪ (({4} \ {3}) ∩ {5}))`

---
# Sets

```java
new Plus(
  new Const(Set.of(7,5)),
  new Prod(
    new Diff(
      new Const(Set.of(4)),
      new Const(Set.of(3))),
    new Const(Set.of(5))))
```

---
# A Visitor for Expressions

See in `ExprVisitor.java`
