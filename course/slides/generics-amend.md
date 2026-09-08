# Declaring Type Variables in Java (1/2)

* When declaring a class

```java
class SomeKlass<A,B,C> {
  int fun1(A a, C c) { /* some code */ }
  B fun2(double d, C c) { /* some code */ }
}
```
---
# Declaring Type Variables in Java (1/2)

* When declaring a class

```java
class SomeKlass<A,B,C> {
  A field1;
  int fun1(A a, C c) { /* some code */ }
  B fun2(double d) { /* some code */ }
}
```

---
# Declaring Type Variables in Java (2/2)

* When declaring a method

```java
<A> List<A> repeat(A a, int n) { /* some code */ }
```

---
# Assigning Types to Type Variables - Class

* Supplying the concrete types

```java
SomeKlass<Integer, Double, Boolean> sk = new SomeKlass<>();
```

---
# Assigning Types to Type Variables - Method

* For methods, in most case, it can be done implicitly
* The compiler can infer the choice of the type variable

```java
// a call statement
repeat(new Cell(), 5);  // A becomes Cell
```

---
# What's Next (Optional)

![](https://images-na.ssl-images-amazon.com/images/I/51Jklhlz7ML._SX387_BO1,204,203,200_.jpg)

[Generic Strategy Pattern](https://replit.com/@engr120/strategy-type-variable)
restricts what behaviors can be assigned to what containing classes.

[Generic Observer Pattern](https://replit.com/@engr120/hunter-vs-duck) Hunters
observes their `DuckRadar` which is a subject. We don't want `Duck`s to see the
radar.

[Generic Weather Station](https://replit.com/@engr120/fsgawgafzdsfwfwafwe)
Parameterize what can observe what to eliminate possible programming errors.
