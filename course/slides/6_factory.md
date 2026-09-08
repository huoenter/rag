# Factory Pattern

---
# A Pizza Store

```java
class PizzaStore {
  // ...
  Pizza orderPizza() { /* ... */ }
}

// Client
PizzaStore ps = new PizzaStore(); // Get a reference to a store
ps.orderPizza(); // Order a pizza from that store
```

---
# `orderPizza`

```java
Pizza orderPizza(String type) {
  Pizza pizza = new Pizza();

  pizza.prepare(); pizza.bake(); pizza.cut();
  pizza.cut(); pizza.box();
  return pizza;
}
```

---
# Adding More Types of Pizzas

* Cheese, Greek, Pepperoni

```java
abstract class Pizza {/* prepare, bake, etc */}
class CheesePizza extends Pizza { ... }
class GreekPizza extends Pizza { ... }
class PeperoniPizza extends Pizza { ... }
```

---
# Pizza

```java
public abstract class Pizza {
	String name;
	String dough;
	String sauce;
	ArrayList toppings = new ArrayList();
  /* ... */
```

---
# CheesePizza

```java
public class CheesePizza extends Pizza {
	public CheesePizza() {
		name = "Cheese Pizza";
		dough = "Regular Crust";
		sauce = "Marinara Pizza Sauce";
		toppings.add("Fresh Mozzarella");
		toppings.add("Parmesan");
	}
}
```

---
# Update `orderPizza` in `PizzaStore`

```java
Pizza orderPizza(String type) {
  Pizza pizza;
	if (type.equals("cheese")) {
		pizza = new CheesePizza();
	} else if (type.equals("greek")) {
		pizza = new GreekPizza();
	} else if (type.equals("pepperoni")) {
		pizza = new PepperoniPizza();
	}

  //prepare and return pizza
}
```

---
# Changes, Changes

```java
Pizza orderPizza(String type) {
  Pizza pizza;
	if (type.equals("cheese")) {
		pizza = new CheesePizza();
	} else if (type.equals("greek")) {
		pizza = new GreekPizza();
	} else if (type.equals("pepperoni")) {
		pizza = new PepperoniPizza();
	} else if (type.equals("clam") {
    pizza = new ClamPizza();
  } else if (type.equals("veggie") {
    pizza = new VeggiePizza();
  }

  //prepare and return pizza
}
```

---
# Design Principle

* The Dependency Inversion Principle: Depend upon abstractions. Do not
  dependent upon concrete classes.
* Now `PizzaStore` depends on lots of pizza classes

---
# Factoring the Changing Part Out

* We factor the changing part out to a place that's designated for changes

```java
Pizza orderPizza(String type) {
  Pizza pizza;

  /* Take this part out */

  pizza.prepare(); pizza.bake(); pizza.cut();
  pizza.cut(); pizza.box();
  return pizza;
}
```

{.column}

```java
if (type.equals("cheese")) {
  pizza = new CheesePizza();
} else if (type.equals("pepperoni")) {
  pizza = new PepperoniPizza();
} else if (type.equals("sausage")) {
  pizza = new SausagePizza();
} else if (type.equals("veggie")) {
  pizza = new VeggiePizza();
}
```

---
# The Simple Factory

* Put the changing part in a `SimplePizzaFactory`

```java
public class SimplePizzaFactory {
	public Pizza createPizza(String type) {
		Pizza pizza;
		if (type.equals("cheese")) {
			pizza = new CheesePizza();
		} else if (type.equals("pepperoni")) {
			pizza = new PepperoniPizza();
		} else if (type.equals("sausage")) {
			pizza = new SausagePizza();
		} else if (type.equals("veggie")) {
			pizza = new VeggiePizza();
		}
		return pizza;
	}
}
```

---
# What's Left in PizzaStore?

* `PizzaStore` is more stable now

```java
public class PizzaStore {
	SimplePizzaFactory factory;
	public PizzaStore(SimplePizzaFactory factory) {
		this.factory = factory; }

	public Pizza orderPizza(String type) {
		Pizza pizza;
		pizza = factory.createPizza(type);
    // Prepare pizza
		return pizza;
	}
}
```

{.column}

* `SimplePizzaFactory`

```java
public class SimplePizzaFactory {
	public Pizza createPizza(String type) {
		Pizza pizza;
		if (type.equals("cheese")) {
			pizza = new CheesePizza();
		} else if (type.equals("pepperoni")) {
			pizza = new PepperoniPizza();
		} /* ... */
		return pizza;
	}
}
```

---
# Simple Factory Pattern

UML

---
# Franchising the Pizza Store

* Stores in different locations
  * New York style
  * Chicago style
* Type: cheese, clam, ...
* Stype: NY, Chicago, ...
  * The NY cheese pizza is a cheese pizza using NY style ingredients
  * The Chicago cheese pizza is a cheese pizza using Chicago style ingredients

---
# NYStyleCheesePizza

```java
public class NYStyleCheesePizza extends Pizza {

	public NYStyleCheesePizza() {
		name = "NY Style Sauce and Cheese Pizza";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";

		toppings.add("Grated Reggiano Cheese");
	}
}
```


---
# Option 1: Following the Simple Factory Design

* We can extend the simple factory
* For example, the `NYPizzaFactory` has New York version of `createPizza`

```java
class SimplePizzaFactory {}
class NYPizzaFactory extends SimplePizzaFactory {}
class ChicagoPizzaFactory extends SimplePizzaFactory {}

NYPizzaFactory nyFactory = new NYPizzaFactory();
PizzaStore nyStore = new PizzaStore(nyFactory);
nyStore.orderPizza("Veggie");
```

---
# Controlling the Creation of Pizzas

* We, as the owner of the franchise, may want an alternative approach so that
  we can directly control the creation of the pizzas in the stores.
* Note that in simple factory pattern, clients can create different factories
* For example, it is bad to have `new PizzaStore(new SewerStyleFactory())`

---
# Factory Method

* `createPizza` is contained in the stores
  * called the "factory method"
* Regional stores extends the abstract `PizzaStore`

```java
public abstract class PizzaStore {
	public Pizza orderPizza(String type) {
		Pizza pizza;
		pizza = createPizza(type);

		pizza.prepare(); pizza.bake();
		pizza.cut(); pizza.box();
		return pizza;
	}
	abstract Pizza createPizza(String type);
}
```

---
# The New York Style

```java
public class NYPizzaStore extends PizzaStore{
	public Pizza createPizza(String type) {
		if (type.equals("cheese")) {
			return new NYStyleCheesePizza();
		} else if (type.equals("veggie"))  {
			return new NYStyleVeggiePizza();
		} else if(type.equals("sausage"))  {
			return new NYStyleSausagePizza();
		} else if (type.equals("pepperoni"))  {
			return new NYStylePepperoniPizza();
		} else return null;
	}
}
```

---
# The Factory Method Up Close

```java
abstract Product factoryMethod(String type)
```

---
# The Factory Method Up Close

```java
abstract Product factoryMethod(String type)
```

---
# The Factory Method Up Close

```java
abstract Product factoryMethod(String type)
```

---
# The Factory Method Up Close

```java
abstract Product factoryMethod(String type)
```

---
# The Factory Method Pattern

The Factory Method Pattern defines an interface for creating an object, but
lets the subclasses decide which class to instantiate. Factory Method lets a
class defer instantiation to subclasses.

---
# General UML

* General UML

---
# The Ingredients

* Previously the ingredients are strings.
  * The choices for each style is arbitrary
  * `pepperoni = "rat pepperoni"`
* We want to restrict the types of ingredients
  * Make them classes
  * `Clam` has `FrozenClam` and `FreshClam` variants
* And use a `IngredientFactory` to control the ingredients

---
# Before

```java
public abstract class Pizza  {
	String dough;
  /* other fields */
}
```

---
# After

```java
public abstract class Pizza  {
	Dough dough;
  /* other fields */
}
interface Dough {}
class ThinCrustDough implements Dough {}
class ThickCrustDough implements Dough {}
```

---
# IngredientFactory

```java
public class NYPizzaIngredientFactory
              implements PizzaIngredientFactory  {
	public Dough createDough()  {
		return new ThinCrustDough();
	}

	public Sauce createSauce()  {
		return new MarinaraSauce();
	}
  /* Other methods */
}
```

---
# CheesePizza

* With the ingredients parameterized, we don't need pizza classes for each
  style and type.

```java
public class CheesePizza extends Pizza {
	PizzaIngreditentFactory ingredientFactory
	public CheesePizza(PizzaIngredientFactory f)  {
		this.ingredientFactory = f;
	}
	public void prepare()  {
		System.out.println(“Preparing “+name);
		dough = ingredientFactory.createDough();
		sauce = ingredientFactory.createSauce();
		cheese = ingredientFactory.createCheese();
	}
}
```

---
# The Stores Control the Ingredients

```java
public class NYPizzaStore extends PizzaStore{
	protected Pizza createPizza(String type) {
    Pizza pizza = null;
    PizzaIngredientFactory ingredientFactory =
              new NYPizzaIngredientFactory();

		if (type.equals("cheese"))
			pizza = new CheesePizza(ingredientFactory);
    /* ... */
  }
}
```

---
# Abstract Factory Pattern

* The Abstract Factory Pattern provides an interface for creating families of
  related or dependent objects without specifying their concrete classes.
* "families of objects" means the ingredients -- clams, cheese, dough, ...
* `NYPizzaIngredientFactory` provides the "interface" to be used by
  `CheesePizza`
  * When making a `CheesePizza`, you don't need to specify the concrete cheese
    type.
* Note: The "product" here are the ingredients

---
# Next

* Read Chapter 4
* Lab 5 on Monday
  * Find your own team
  * Sign up form on my door
