# SWE200 - DESIGN PATTERNS

---
# Good Computer Program

* Correct
  * "Free of bugs"
  * Correct today, correct tomorrow

---
# Good Computer Program

* Easy to understand
  * by you
  * by the future you
  * by colleagues
  * by clients

---
# Good Computer Program

* Ready for changes
  * changes are inevitable

---
# Design Patterns

* ~~correct~~
  * We do tests
  * ~~Specify invariants~~
  * ~~Proofs~~
* easy to understand
* __ready for changes__

---
# Design Patterns: The Origin

* Design Patterns: Elements of __Reusable__ Object-Oriented Software
* ![](https://images-na.ssl-images-amazon.com/images/I/51kuc0iWoKL._SX326_BO1,204,203,200_.jpg)

---
# Design Patterns: The Origin

* Also called the "Gang of Four" book
  * 23 patterns
  * Written in 1994
* Here _Design Patterns_ is in the _narrow sense_
  * Object oriented (C++)

---
# Patterns in SWE200

* About 15 patterns total, in Java
* 6 built into the labs
  * An interactive game
  * Several faculties have worked on the lab part over the years

---
# A Little Java, A Little Patterns

* Written by researchers in programming languages
* "The Little" series
  * _The Little Schemer_
  * _The Little Prover_
  * etc
* The whole book is a (long) conversation

---
# Labs

* Due dates are on Gradescope ONLY
* Gradescope is always for pdf submissions, not code
  * Gitlab will do the automatic grading starting lab4
* Lab 0, Lab 8 are bonus labs
* Lab 1, 2, 3 are individual ones
* I will assign the team for Lab 4
* Form your own team for Lab 5, 6, 7

---
# Quizzes

* Written quizzes
* Take the ~~same~~ questions home and then take the quiz in class, close-book
* Make use of Piazza discussions

---
# Exams

* In-class, one letter-size (double sides) cheat-sheet allowed
* The final is not cumulative
* Sample exam will be given before Exam 1

---
# Late Policy

* Every lab has a 48-hour late due date
  * 50% off if late
* Exceptions may be given to emergency such as acute illness (w/ doctor's
  notes)
* Exceptions will not be given for anything involves that long-term planning.
  For example, having two full-time jobs and 18 credit hours this semester.

---
# Attendance

* You will not get grades if you miss the quizzes or lab 0
* Same exception rules apply to attendance
* When work in teams for later labs, the team may make (meeting) attendance
  rules too
* Computers (including personal devices) shall be used for class activities
  only

---
# Extra Credits

* Piazza participation
* Surprise attendance check
* Extra quiz opportunities
* etc.
* There won't be "individual extra credit opportunities"

---
# Getting Help

* Piazza
  * Questions can be anonymous
  * SI and fellow students can also answer questions
  * Answers can be shared
  * Use a "private post" for an email
  * Posts with exact solutions need to be _private_
  * (easier to format code)
* My Office Hours - See on my page
* Supplemental Instruction Peer - See D2L announcement
* CS/SWE Tutor Hours - See the schedule at MCT165

---
# Miscellaneous

* Bring the textbook to class
* Take notes
* Review materials on D2L after each class
* Turn on Email notifications on D2L

---
# Object Oriented Programming (OOP)

* What is OOP? (Java, Python, Scala, Ruby, ...)

1. _Objects_ encapsulate behavior (methods) and state (stored in fields)
1. _Classes_ group objects that differ only in their state
1. _Inheritance_ allows new classes to be derived from existing ones
1. _Polymorphism_ allows "messages" to be sent to objects of different classes

---
# Playground

* `repl.it` and `jshell`
* Log in the computers and create an account on repl.it
* Nothing will be submitted via repl.it
* Let's do an example

---
# Static and Dynamic Types

* What is the `type` of `dog1`?
* `Mammal dog1 = new Dog();`
* `Mammal` is `dog1`'s __static type__ or __compile type__
* `Dog` is `dog1`'s __dynamic type__ or __runtime type__

---
# Dynamic Dispatch

* The meaning of the method is determined by the object (at runtime), not the
  surrounding source code
* https://replit.com/@engr120/example

```java
class A {
  public void m1() { this.m2(); }
  public void m2() { System.out.println("A's m2"); }
}

public class DynExample {
  public static void main(String[] args) {
    A a = new A(); a.m1(); //what's the output?
  }
}
```

---
# Dynamic Dispatch

```java
class B extends A {
  public void m2() { System.out.println("B's m2"); }
}

public class DynExample {
  public static void main(String[] args) {
    B b = new B(); b.m1(); //what's the output?
  }
}
```

---
# Static Dispatch?

* Yes, there is.

```java
class A {
  public void m1() { this.m2(); }
  public void m2() { System.out.println("A's m2"); }
}

class B extends A {
  public void m2() { System.out.println("B's m2"); }
  public void m3() { super.m2(); }
}

class C extends B {}
```

---
# Static Dispatch?

* `public void m3() { super.m2(); }`

```java
public class DynExample {
  public static void main(String[] args) {
    B b = new B();
    b.m1();

    C c = new C();
    c.m3();
  }
}
```

---
# Typical Usage of Dynamic Dispatch

* We say the two m's in B and C overrides the m in A

```java
class A { void m() {println("A");} }
class B extends A { void m() {println("B");} }
class C extends A { void m() {println("C");} }
```

---
# Typical Usage of Dynamic Dispatch

```java
class Z {
  A a;
  Z(A aa) { a = aa; }
  void n() { a.m(); }
}
```

---
# Running on repl.it

* You can do `javac` and `java`
* Or `jshell`

```java
class Main {
  public static void main(String[] args) {
    Z z = new Z(new B());
    z.m();
  }
}
```

---
# Commandline Compiler

* `javac Main.java`
  * Compiles the classes to Java Bytecode
* `java Main`
  * Runs the `main` method in the `Main` class

---
# Using `jshell`

* It's a Java "REPL"
  * Read-Evaluate-Print-Loop
  * Since Java 10
* `jshell` starts the shell
* `/open Main.java` reads the source code into the shell
* Then you can "evaluate expressions" such as
  * `Z z = new Z(new B())`

---
# Using Dynamic Dispatch

* How would you use it to refactor the `Student` program?
* Swap out the `switch` statement
* https://replit.com/@engr120/Fall2020-FirstExample


---
# Wednesday

* Lab 0 in class
