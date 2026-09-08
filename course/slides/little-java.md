# A Little Java, A Few Patterns

![](https://images-na.ssl-images-amazon.com/images/I/51bwqQFmmKL._SX403_BO1,204,203,200_.jpg)

---
# What This Book is Not About

Java provides many useful features and libraries beyond its object-oriented
core. While these additional Java elements are important for professional
programming, their coverage would distract from the book's important goals:
object-oriented programming and the use of design patterns.

---
# Shish Kebab

```java
interface Shish {}
class Skewer implements Shish {}
```

---
# Ingredients

```java
class Onion extends Shish {
  Shish s;
  Onion(Shish sh) { s = sh; }}
class Lamb extends Shish {
  Shish s;
  Lamb(shish sh) { s = sh; }}
class Tomato extends Shish {
  Shish s;
  Tomato(Shish sh) { s = sh; }}
```

---
# Construct A Shish Kebab

* How about `new Skewer()`?
* `new Onion(new Skewer())`
  * Why does this work? Check the constructor.

---
# Construct A Shish Kebab

* What is the instance variable `s` for this kebab?

```java
new Onion(
  new Lamb(
    new Onion(
      new Skewer())))
```

---
# Questions

* Are there only Onions on `new Onion(new Skewer())`?
* How about `new Lamb(new Skewer())`?

---
# Questions

How about

```java
new Onion(
  new Onion(
    new Onion(
      new Skewer())))
```

---
# Questions

How about

```java
new Onion(
  new Lamb(
    new Onion(
      new Skewer())))
```

---
# Supporting "onlyOnions"

* Write `onlyOnions` using "onlyOnions", "s", ".", "{", "}", "(", ")", ";",
  "true", "false", "return", and "boolean"
* How about `new Skewer())`?
  * The book said the name should be `nothingButOnions` but it's too long for a
    method name in the book
* See in the exercise
