# Final Review

---
# Time and Location

* Thursday 12/8, 3:30-5:30, DHC108
* There's an exam 1-3. I'll be there at 3 for those who may want an early
  start.

---
# Overview

* One cheatsheet, both sides, must be handwritten
* Go through all the quizzes, in-class exercises
* Go through all the code examples on replit
* Go through all the slides
  * UMLs from slides
* Related labs

---
# Visitor

* A family of classes (variants)
  * e.g I `<-` A, B, C, ...
* A visitor interface has a method for each variant
  * `void visit(B b)`
* Each variant has an `accept(Visitor v)` method
  * `v.visit(this)` to apply the visitor to "this" object
* Note: `v.visit(baseType)` does not work (why?)
  * Have to reverse it: `baseType.accept(v)`
  * See in `Backpack`

---
# Visitor: Double Dispatch

* What are the static/dynamic types of `v`, `this`, etc.

Figure here

---
# Answering "Identify how dynamic dispatch is used"

* Identify the specific location: on which line/which Java statement
* What's the static/dynamic type
* Which method is dynamically dispatched

---
# Visitor

* One quiz
* Example: backpacks and books
* The visitors may or may not see the internal structures of the data
  * `Backpack` in the quiz
* Visitors can have states
  * e.g. `TotalWeightVisitor`

---
# State

* One quiz, one ICE
* Example: the gumball machine
* 2nd example: the student
* Sketch the state diagram
* Sketch the transition as a table Φ
* Anti-pattern is column-based
  * if you do x, then if you are in s state, ...
* State pattern is row-based

---
# State

* The states have a reference to the context
* Each state has all the action methods
* The context has `setState`
* The context has concrete state instances and their getters
* The context has the same action methods
  * But just make the "currentState" do the work
* General UML in the slides

---
# Template

* One quiz
* Example: making tea and coffee, hook for condiments
* Names: template method, primitive operations, concrete operations
  * The operations refer to the methods within the template method
  * The template method is _final_. What does _final_ mean for different Java
    names?
  * Primitive operations are abstract
* Subclasses implement the primitive operations
* Hook?
  * A boolean method with default implementation and can be overridden by
    subclasses (`customerWantsCondiments`)

---
# Adapter

* One quiz
* Example: Duck and Turkey, Enumeration and Iterator
* Names: target, adaptee
* Notice the "direction" (which is the target?)
* Fool the compiler, wrap an adaptee, "translate" the methods
* How to use it as a client
    * e.g. how to use `showDuck` when you don't have ducks
* UML

---
# Random Example

* One ICE
* Random integers, boolean
* Choosing one element from a list of choices
* Generate a list of random elements
* Sample question: Given a Java class, make a random generator for it

---
# Miscellaneous

* May ask for UMLs like in exam 2
* Data structures vs. design patterns
* May ask ~~high level~~ questions for our lab project
  * "Which class is a singleton in lab 5?"
* For our labs, which folders should (not) be pushed to gitlab?
