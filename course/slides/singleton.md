# Singleton Pattern
## One of a Kind Objects

---
# Just One

* In the physical world, sometimes there is only one object for a type.


---
# Find a Java Solution

* `static` associates properties to the class itself, not the instances of the
  class.
* For example, `Camry` is a model with many instances. We simulate it by
  creating a Java class `class Camry`.
* For "your camry", `Camry joesCamry = new Camry(...)`.
* `joesCamry` has _local states_, e.g. _mileage_, _fuel level_.
* How to count the sales number of all `Camry`s? It is a number _class-wide_
  and does nothing to an instance.
* `public static salesCount;`
* `Camry.salesCount`

---
# The Class Variables

* Sometimes the `static` variables are also called _class variables_.
* We can use such a class variable to keep the only instances of the class.

---
# Disabling `new`

* Via access modifier.

```java
public MyClass {
  private MyClass {
  }
}
```

---
# Getting the Singleton

```java
public Singleton {
  public static Singleton getInstance() {
    return new Singleton();
  }
}
```

---
# A Problem

* What's the problem in the previous slide?
  * Efficiency
  * Identity

---
# Improvement

```java
public Singleton {
  private static Singleton unique;
  public static Singleton getInstance() {
    if (unique == null) {
      unique = new Singleton();
    }

    return unique;
  }
}
```

---
# Laziness

* (Note: the `static` variable is `private`)
* We will not instantiate an instance unless it is needed.
* We will not instantiate the instance again once we have it.
* This behavior is called `lazy`

---
# Laziness

* Also called _as needed_
* 

---
# Multithreading

* While more than one thread running `getInstance()` at the _same time_, more
  than one `Singleton` will be instantiated.

Thread A | Thread B
-|-
(waiting ...) | `if (unique == null)`
`if (unique == null)` | (waiting ...)
`instaniation` | (waiting ...)
 (waiting ...) | `instaniation`

---
# Multithreading

* Turn to page 181 and "Be the JVM Solution"

---
# Using Locks

* If one thread has not finished `getInstance()`, the other ones have to wait
  for its completion.
* Every Java object is implicitly a lock.
* Obtain the lock by `synchronized`

```java
Object lock = new Object();
synchronized (lock) {
  //doing mutually exclusive stuff...
}
```

---
# The `synchronized` Keyword

* e.g. `synchronized int method1()`
* A Java _syntactic sugar_ as `synchronized (this) { ... }`

---
# The `synchronized` Keyword

By adding the `synchronized` keyword to `getInstance()`, we force every thread
to wait its turn before it can enter the method. That is, no two threads may
enter the method at the same time.

```java
public static synchronized MyClass getInstance() {
  //...
}
```

---
# Efficiency

* Synchronized is expensive.
* It's unnecessary to wait when the singleton is already there.
* Synchronization only needed when the object is not yet instantiated.

---
# Option1: Eagerness

* Maybe we discard the laziness
* No locking needed

```java
public class Singleton {
  private static Singleton uniqueInstance = new Singleton();
  private Singleton() {}
  public static Singleton getInstance() {
    return uniqueInstance;
  }
}
```

---
# Option2: Double-checked locking

* Lock only when creating the object

```java
public class Singleton {
  private volatile static Singleton unique;
  private Singleton() {}
  public static Singleton getInstance() {
    if (unique == null) {
      synchronized (Singleton.class) {
        if (unique == null)
          unique = new Singleton(); } }
    return uniqueInstance; }
}
```

---
# volatile

* The `volatile` keyword ensures that multiple threads handle the `unique`
  variable correctly when it is being initialized to the Singleton instance.
* Often Java threads write results first to the local cache and then dump a lot
  of them to the memory. The other thread may not know a `unique` is created.
