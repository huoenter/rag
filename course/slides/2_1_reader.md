# Reader
## Dependency Injection

---
# First, Feedback on Lab 0 Part II

---
# Excerpt

```java
  public void setByFahrenheit(double f) {
    if (f < -459.67) {
      throw new TemperatureException();
    } else {
      this.fahrenheit = f;
      this.celsius = (f - 32) / (9.0 / 5.0);
      this.kelvin = (f + 459.67) * (5.0 / 9.0);
      this.tempSet = true;
    }
  }
```

---
# Traditional Software Development

* Plan driven
* Reality
  * We do not anticipate many _changes_
  * Many of the changes we do anticipate are _not needed_
  * "Requirements" (and things other than code) tend to go _out of date_ very
    quickly

---
# Agile Methods

* Recognizes the reality
* Start __small__ and __evolve__ over time
  * Get a _working_ solution first (Lab1) and then add things (the following
    labs)
* Embracing _software evolution_ instead of _fighting it_
  * Test Driven Development (TDD) is an implementation of agile development

---
# Agile Methods: Tests Are Documentation

* Because of constant _evolution_, the only things that are up-to-date are the
  executables
  * application and tests
  * all other artifacts, e.g. lab description, could be inconsistent
* Agile methods tend to discard formal documentations
  * tests serve as documentation
  * We run tests _every time_ we make a change to the software

---
# Review

* Parallel class hierarchy

---
# What Problem Does the Strategy Pattern Solve?

* Delay the choice of `FlyBehavior`

---
# Weakness

* You don't know the `Behavior` is set or not
  * Or whether it should be set
  * Might get a confusing `NPE`
  * No explicit requirement
* The `Behavior` can be set more than once
  * Not clear this should be valid or not

---
# A (Functional) Alternative

* Reader pattern
* Recall that method(function) parameters provide abstraction
* Go on without the actual value
* e.g. in Strategy `void fly() { behavior.performFly(); }`
* when you write this code, behavior is not there yet

---
# A (Functional) Alternative

* Idea: `Duck`s have `FlyBehavior`s, if the `Duck` does not have the
  `FlyBehavior` set, then it is a `Function` from `FlyBehavior -> Duck`.

---
# Function and Functional Interface

* An instance of `Function<A,B>` is a function that takes in an `A` value and
  returns a `B` value
* Functional Interface: An `interface` with only one method
  * Function Is a Functional Interface
  * You can instantiate a functional interface using lambda expression

---
# In Java 8, Functions Are FIRST-CLASS Objects

* Methods have the same privileges as variables
  * You can pass methods around as arguments
  * Assign a method to a variable

---
# Higher Order Functions

* A function that takes other functions in as parameters

---
# Lambda Expression

* Instantiate a functional interface
* `<return-type> <method-name>(<parameters>) { return <method-body> }`
* `<parameters> -> <method-body>`

---
# Try It Out

* `jshell`: a REPL since Java 9
* `REPL`: read, evaluate, print, and loop
* You can assume you are typing code inside of a `main` method

---
# Reader Pattern

* A function takes in the dependency and returns the object
* Can be composed with `flatMap`
  * chain `Reader`s together and inject the dependency at the end

---
# Blockers Family

---
# What is flatMap?

```java
public static <R,A,B> Reader<R,B> flatMap(Function<A,Reader<R,B>> f, Reader<R,A> ra)
```

---
# Free Theorem

* Wadler, 1989
* Knowing important properties of the code with just type information
  * without knowing the program body
![](https://dreamsongs.com/OOPSLA2007/Photos/Impressions%20Pix/wadler.gif)

---
# Identity

* Here is a task for you:
* (Use type variables) write a (static) method which takes in a type `A`
  parameter and returns a type `A` parameter
* Can be annotated as `A -> A`

---
# Identity

```java
static <A> A m(A a) {

}
```

---
# Identity

```java
<A> static A identity(A a) {
  return a;
}
```

---
# Free Items

* What are the following methods?
* `A -> B -> A`
* `A -> B -> B`

---
# flatMap Is Also Quite Limited

```java
public default <B> Reader<R,B> flatMap(Function<A,Reader<R,B>> f) {

}
```

---
# Quiz

* Get a copy today
* Take the quiz MONDAY in class

---
# On Wednesday

* Using `git` on your local machine
* Lab 2 (Strategy pattern)
* Before Wednesday class, finish reading "Developers Who Use Spaces Make More
  Money Than Those Who Use Tabs"
  * Question: Give a math function for the red line in the last figure
    "Tabs/spaces gap within each developer type and language"
  * Question: What does it mean if a dot is above/below the line?
