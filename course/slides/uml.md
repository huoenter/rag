---
# UML

* Unified Modeling Language
  * Many kinds of UMLs
* [Class Diagram](https://en.wikipedia.org/wiki/Class_diagram)

---
# Arrows

* `extends`: Solid line, arrow head
  * source extends target
* `implements`: dashed line, arrow head
  * source implements target
* `contains`: diamond at source, optional arrow head
  * source contains target (as an instance variable)
  * put `n` on the arrow means source contains one or more targets

---
# The "Box"

* Three parts if you want to mention instance variables
* For an `interface` or if you don't want to mention instance variables, two
  parts

---
# Top part

* `<<abstract>>` or `<<interface>>` if necessary
* class name

---
# Middle part

* instance variables
* `<name>: <type>` - different from Java syntax
* e.g. `age: int`, `obs: List<Observer>`
* (Scala and some other languages use this syntax)

---
# Bottom part

* methods
* `<method-name-with-parameters>: <return-type>`
  * The parameter list uses the reverse syntax too
  * e.g. `recover(): void`, `addObserver(o: Observer): void`
* special case: constructors
  * `<<create>>` prefix
  * `<<create>>Alien(name: String, life: int)`

---
# Visibility

* `+` public
* `-` private
* `#` protected
* `~` package private (not modifier in Java)

* Put them at the start of the line

---
# Visibility Review

Modifier | Alpha | Beta | Alphasub | Gamma
-|-|-|-|-
public | Y | Y | Y | Y
protected | Y | Y | Y | N
no modifier | Y | Y | N | N
private | Y | N | N | N

---
# figure

![](https://docs.oracle.com/javase/tutorial/figures/java/classes-access.gif)

---
# Try it

* https://replit.com/@engr120/observer-counter

---
# UML

haha

---
# Try Another One

* https://replit.com/@engr120/uml-2

---
# UML

haha
