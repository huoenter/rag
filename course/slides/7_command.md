# Command Pattern

---
# A "Programmable" Remote

* The remote can be configured to control different devices
  * TV, CeilingFan, Hottube, etc

---
# A Simple Remote

* We start with a remote with a single button
* ![](https://cdn.drawception.com/images/panels/2015/1-3/gnPwyScXe4-2.png)

```java
class SimpleRemote {
  public void buttonPressed() {
    /* ??? */
  }
}
```

---
# Option 1

* We don't want the remote to know too much about the devices to be controlled
  * In the example below, the remote needs to know how to turn the TV on
* We'd like to separate the concerns

```java
class SimpleRemote {
  Device d;
  public void setDevice(Device dd) { d = dd; }
  public void buttonPressed() {
    if (d instanceof TV) {
      ((TV) d).on();
    } else if ( /* ... */ ) {}
    /* ... */
  }
}
```

---
# Decomposition and Composition

* Most of the most cited paper in psychology, _The Magical Number Seven, Plus
  or Minus Two_, said that we can only keep 7±2 pieces of information in our
minds.
* We all know that we need to decompose the problem in introductory programming
  courses
* Once you decompose the problem, you can solve the smaller problems.
* Then you need to put things back -- composition

---
# The Remote

```
         Remote
          /\
         /  \
        /    \
       /      \
button-press  device-operations
```

---
# Remote

* We pull out the device operations to `Command`s

```java
class SimpleRemote {
  Command c;
  public void buttonPressed() {
    c.execute();
  }
}
```

---
# Remote

* The secrets of how to operate the devices are all inside of `Command c`

```java
class SimpleRemote {
  Command c;
  public void buttonPressed() {
    c.execute();
  }
}
```

---
# The Command

```java
interface Command {
  public void execute();
}
```

---
# Updated for Java 8?

* screen shot of the cover

---
# Program as Data

```java
class A { static void m(int n) { return n + 1; } }
class B { static void m(int n) { return n + n; } }
class C { static void m(int n) { return n * n; } }

class DoMath {
  static void changeN(int n, ??? anInterestingWayToChangeN) {
    // use anInterestingWayToChangeN to change N, e.g. A.m or B.m
  }
}
//Can we do ?
DoMath.changeN(3, B.m);
```

---
# First-class Object

* Unfortunately, you cannot do this Java (before Java 8)
  * Since Java 8, the lambda expressions and method references give you the
    illusion that you can.
* You can pass an `int` as a method argument
  * You cannot pass a method as a method argument
* You can return an `int` from a method
  * You cannot return a method from a method
* So `int` is the first-class object while a method/function/procedure is
  "second-class" object in Java

---
# First-class Object

* It's easy to work around it. Not elegantly though.
* We can wrap the program in a Java object
* We can pass this object around and invoke the methods of the object when
  necessary

---
# The Command

```java
interface Command {
  public void execute();
}
```

---
# First-class Object

* It's easy to work around it. Not elegantly though.
* We can wrap the program in a Java object
* Many third party libraries try to make functions look like first-class
  objects
  * e.g. Google's Guava
  * e.g. Apache Commons Lang
* Java realizes that it will be hopelessly out of date if it does not change
  * Java 8 in 2014 (Functions and Streams)

---
# Concrete Commands

* A command that turns on a TV. Assume TV has a `on()` method.

```java
class TVOn implements Command {
  TV tv;
  public class TVOn(TV tv) { this.tv = tv; }
  @Override
  public void execute() { tv.on(); }
}

TV tv = new TV();
Command tvOnCommand = new TVOn(tv);
```

---
# Anonymous Class

* (Concrete) Classes that don't have a name.

```java
TV tv = new TV();
Command tvOnCommand = new Command() {
  public void execute() { tv.on(); }
}
```

---
# Lambda Expression and Functional Interface

* As said in the first class, you can use a _lambda expression_ to instantiate
  a _functional interface_
* A functional interface is an interface with one method
* The compiler can help you check it if you use the optional
  `@FunctionalInterface` annotation
  * Like `@Override`

---
# Lambda Expression

* A lambda expression is a concise representation of an anonymous function
  (class) that can be passed around
* The general form is `(parameters) -> expression` or `(parameters) -> {
  statements; }`
* Can omit `()` when there's one parameter
* (Roughly) Expressions return values and statements don't.
  * `1 + 2` is an expression
  * `System.out.println("Hi");` is a statement

---
# Lambda Expression

```java
TV tv = new TV();
Command tvOnCommand = () -> tv.on();
```


---
# Lambda Expression and Static Scoping

* `tv` is said to be __free__ in `() -> tv.on()`
  * `n` is __bound__ in `n -> n + 1`
* This `tv` refers to the outer `tv` when `tvOnCommand` is defined
* This is called _static scoping_

```java
TV tv = new TV();
Command tvOnCommand = () -> tv.on();
```

---
# Macro Command: Composition

```java
public class MacroCommand implements Command {
  Command[] commands;

  public MacroCommand(Command[] commands) {
    this.commands = commands; }

  public void execute() {
    for(int i = 0; i< commands.length; i++) {
      commands[i].execute(); }}}
```

---
# Macro Command: Composition

* `MacroCommand` definitely works as expected
  * It is still a `Command` by inheritance
* But is it true composition?
* An array is used explicitly to glue the commands together

---
# Composition

`3 + 4 = 3`

---
# Composition

`3 * 4 = 12`

---
# Composition

```java
commmand1 andThen command2 = command3
```

---
# Composition

* Another change in Java 8: interfaces can have `default` implementations
* This is not necessary for `Command`. Just to keep `Command` a functional
  interface

```java
interface Command {
  public void execute();
  public default Command andThen(Command c) {
    return () -> { execute(); c.execute(); };
  }
}
```

---
# Composition

```java
TV tv = new TV();
Command tvOn = () -> tv.on();
Command tvOff = () -> tv.off();
Command macro = tvOn.andThen(tvOff).andThen(tvOn);
```

---
# The Command Pattern

The Command Pattern encapsulates a request as an object, thereby letting you
parameterize other objects with different requests, queue or log requests, and
support undoable operations.

---
# Put Commands in the Remote

```java
class SimpleRemote {
  private Command c;
  public void setCommand(Command comm) { c = comm; }
  public void buttonPressed() {
    c.execute();
  }
}
```

---
# A More Complicated Remote

screen shot

---
# Undoable Command

* Here I think the book assumes the commands are just On and Offs
* Otherwise it is not clear whether an action is revertible (think about
  "groups" and "monoids" in math)

```java
public interface Command  {
  public void execute();
  public void undo();
}
```

---
# Light On

```java
public class LightOnCommand implements Command {
	Light light;
	public LightOnCommand(Light light) {
		this.light = light; }

	public void execute() { light.on(); }

	public void undo() { light.off(); }}
```

---
# A More Complicated Remote

```java
public class RemoteControlWithUndo {
	Command[] onCommands;
	Command[] offCommands;
	Command undoCommand;

	public RemoteControlWithUndo() {
		onCommands = new Command[7];
		offCommands = new Command[7];
		Command noCommand = new NoCommand();
		for(int i=0;i<7;i++) {
			onCommands[i] = noCommand;
			offCommands[i] = noCommand; }
		undoCommand = noCommand;
	} /* more */
```

---
# A More Complicated Remote

```java
public void setCommand(int slot, Command onCommand,
                                 Command offCommand) {
  onCommands[slot] = onCommand;
  offCommands[slot] = offCommand;
}
public void onButtonWasPushed(int slot) {
  onCommands[slot].execute();
  undoCommand = onCommands[slot];
}
public void offButtonWasPushed(int slot) {
  offCommands[slot].execute();
  undoCommand = offCommands[slot];
}
```

---
# Client

```java
RemoteControlWithUndo remote = new RemoteControlWithUndo();
Light l = new Light();
Command lightOn = new LightOnCommand(l);
Command lightOff = new LightOffCommand(l);
remote.setCommand(0, lightOn, lightOff);
remote.onButtonWasPushed(0); //light on
```

---
# UML

abc

---
# A Restaurant Example

aa

---
# A Restaurant Example

aa

---
# A Restaurant Example

aa

---
# Homework

* Read Chapter 6
* Quiz 7 (10/30)
