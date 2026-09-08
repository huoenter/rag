# Strategy Pattern
## First in the Semester

---
# Lab 0

* It does have a "problem solving" piece
  * Other labs don't

---
# The Duck Problem

![](https://drive.google.com/uc?id=1HNYtzBdJ2tjstYSLgi_B3eMXnm604u5F)

---
# First, Definitely Not OOP...

* Before even talking about the design pattern, let's see something that's not
  even OOP
* Who will do [this](https://replit.com/@engr120/strategy-anti)?
  * Make a rubber duck
  * Why is it bad?

---
# The Duck Problem

![](https://drive.google.com/uc?id=1GIbxvYwMLKWG2K09cPyy9jAXfdleejL-)

---
# The Duck Problem

![](https://drive.google.com/uc?id=1NFVZcg83EIApHb08gECnnGqnD8yet_Ul)

---
# UML

* Unified Modeling Language
  * Many kinds of UMLs
* [Class Diagram](https://en.wikipedia.org/wiki/Class_diagram)


---
# SWE200 UML Arrow Types

* Solid line with an arrow head for `extends`
  * from the subclass to the superclass
* Dashed line with an arrow head for `implements`
* Solid line with a solid diamond at the source for `contains`
* Arrow head can be solid or hollow, but must be consistent

---
# Design Principle 1

Identify the aspects of your application that vary and separate them from what
stays the same.

---
# Strategy Pattern

The __Strategy Pattern__ defines a family of algorithms, encapsulates each one,
and makes them interchangeable. Strategy lets the algorithm vary independently
from clients that use it.

---
# Strategy Pattern

* The Strategy Pattern defines __a family of algorithms__, encapsulates each
  one, and makes them interchangeable. Strategy lets the algorithm vary
independently from clients that use it.
* In this duck case, the different ways to fly
  * e.g. `FlyNoWay`, `FlyWithWings`

---
# Strategy Pattern

* The Strategy Pattern defines a family of algorithms, __encapsulates each
  one__, and makes them interchangeable. Strategy lets the algorithm vary
independently from clients that use it.
* Each fly behavior is a class

---
# Strategy Pattern

* The Strategy Pattern defines a family of algorithms, encapsulates each one,
  and __makes them interchangeable__. Strategy lets the algorithm vary
independently from clients that use it.
* We make a `FlyBehavior` interface
* The `Duck` class can still assume there is a fly behavior to use before
  deciding which behavior to use

---
# Abstraction as A Field

```java
interface FlyBehavior { public void fly(); }
abstract class Duck {
  FlyBehavior fb;
  public class Duck() {}
  /* Using fb before knowing which exact behavior to use */
  public void performFly() { fb.fly(); }
}
```

---
# Note: Code in Slides, Repl.it, and White Board

* To save space and to improve readability, some details are omitted for code
  in slides such as `private` for instance variables and `public` for classes,
etc.
* In general, all instance variables should be `private` unless it is also
  `final static`. You should be judicious whether a method should be private,
protected, package private, or public. The more restrictive the better.

---
# Java Namespace

| Modifier | Class | Package | Subclass | World |
|----------|-------|---------|----------|-------|
|public | Y | Y | Y | Y |
|protected | Y | Y | Y | N |
|package private (no modifier) | Y | Y | N | N |
|private | Y | N | N | N |

---
# Examples

* `private` is less `private` than you
  [thought](https://replit.com/@engr120/Student#Student.java)
* The physical locations of source code don't matter
  * lab 0 example
* The access modifiers are
  [_static_](https://replit.com/@engr120/Student#Main.java)
  * Note that "Main.java" calls `addCourse` which will call a private
    `updateGPA` from "Student.java"
  * That's okay: access modifiers are restricting the source code only

---
# Examples

* Wait! Your "classes" are not the same as we learned last year
  * Neither `Student` nor `Main` is `public`
* Why `Main` works for now?

---
# Design Principle 2

Program to an interface, not an implementation.

* `FlyBehavior` is like a placeholder. We can plug in many variants.
* `FlyBehavior` is like a placeholder. We can finish `Duck` before any actual
  fly behavior.

---
# Design Principle 3

Favor composition over inheritance.

* For example, we can switch the `fb` field on the fly

```java
public void setFB(FlyBehavior f) {
  fb = f;
}
```

---
# Behaviors

```java
interface FlyBehavior { public void fly(); }
class FlyWithWings implements FlyBehavior {
  public void fly() { println("Flapping!"); }
}
class FlyNoWay implements FlyBehavior {
  public void fly() { println("No way!"); }
}
```

---
# UML

* Refined arrow: use dotted line for implementing an interface

![](https://drive.google.com/uc?id=1rrmpi4fEZ2jnB4f9crS2QTJMKSzmJQYm)

---
# Ducks

```java
class MallardDuck extends Duck {
  MallardDuck() {
    fb = new FlyWithWings();
  }
}
```

---
# Exercise

* [here](https://replit.com/@engr120/strategy-pre-1)
* Add fly behaviors to ducks
  * How would you make this change to the "anti-pattern"?
* Add a `WoodenDuck` class
  * How would you make this change to the "anti-pattern"?

---
# A Potential Problem

* We can add `FlyWithRotorWings` for `RoboDuck`s
* We are allowed to give `FlyWithRotorWings` to a `RedheadDuck`
* Is there a good way to restrict it?
  * Abstracting the types

---
# Adding Type Parameters

Tell `FlyBehavior` that it only works for a certain type

```java
interface FlyBahvior<D>
```

---
# Adding Type Parameters

Tell `FlyBehavior` that it only works for a certain type of __Duck__

```java
interface FlyBehavior<D extends Duck> {
  void fly();
}
```

---
# Adding Type Parameters

Tell `Duck` that it can only accepts a "compatible" `FlyBehavior`

```java
abstract class Duck {
  FlyBehavior fb;
  void setFlyBehavior(FlyBehavior f) {...}
  void performFly() { fb.fly(); }
}
```

---
# Adding Type Parameters

What is `???`? And remember this mechanism better work even `RedheadDuck`
extends `Duck`

```java
abstract class Duck {
  FlyBehavior<???> fb;
  void setFlyBehavior(FlyBehavior<???> f) {...}
  void performFly() { fb.fly(); }
}
```

---
# Adding Type Parameters

What is `???`? `Duck` needs to know who "itself" is.

```java
abstract class Duck<D> {
  FlyBehavior<D> fb;
  void setFlyBehavior(FlyBehavior<D> f) {...}
}
```

---
# Adding Type Parameters

* There are two problems here.
  * Now `Duck` needs a type parameter

```java
interface FlyBehavior<D extends Duck>
```

---
# Adding Type Parameters

* There are two problems here.
  * Now `Duck` needs a type parameter

```java
interface FlyBehavior<D extends Duck<D>>
```

---
# Adding Type Parameters

* There are two problems here.
  * `D` must be a subclass of `Duck`

```java
abstract class Duck<D extends Duck<D>> {
  FlyBehavior<D> fb;
  void setFlyBehavior(FlyBehavior<D> f) {...}
}
```

---
# Let the Compiler Do the Work

```java
class RedheadDuck extends Duck<RedheadDuck>
class RoboDuck extends Duck<RoboDuck>
class FlyWithWings implements FlyBehavior<RedheadDuck>
class FlyWithRotorWings implements FlyBehavior<RoboDuck>
```

---
# Let the Compiler Do the Work

```java
RedheadDuck redhead = new RedheadDuck();
redhead.setFlyBehavior(new FlyWithRotorWings()); //does not compile
```

---
# Let the Compiler Do the Work

```java
class TerminatorDuck extends RoboDuck
TerminatorDuck terminator = new TerminatorDuck();
terminator.setFlyBehavior(new FlyWithRotorWings()); //Okay
```

---
# Homework

* Read Chapter 1
* Get ready for Quiz 1
* Complete crossword on Page 33

* Thursday: Lab 1 in class
