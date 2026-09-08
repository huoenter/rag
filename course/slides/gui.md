# Java Swing GUI

---
# Lab 5

* Make sure you put a tag on lab 5 when finished
* Lab 6 and 7 on the same project

---
# Lab 6

* 2.5 to 3 weeks
* Manuals already on D2L
* You will design the interaction
  * Demo here
* No lab 6 instructor's test since every team may have different design
* "Acceptance test" is a fancy name for you to show me that your program works
  * Now details next week

---
# Lab 6 UML

* Three standard types of arrows
  * extends, implements, contains
* A regular arrow with a name
* `+` public, `-` private, `#` protected
* Example

---
# GUI (Graphical User Interface)

* Pronounced "gooey"
* Java Swing
  * Designed for office applications

---
# Swing

* Java's GUI system consists of four main parts: Containers (Windows and
  Panels), Layout Managers (Manages where stuff goes in the Containers),
Components (Buttons, Text Fields, etc.), and Action Handlers (Handling button
clicks, etc.).
* Oracle Tutorial https://docs.oracle.com/javase/tutorial/uiswing/TOC.html

---
# JFrame

* A `JFrame` is a top-level window with a title and a border.
* `pack()` fits the preferred size and layouts
* Common design `class SimpleRemote extends JFrame`
* Can use a `LayoutManager` directly on `JFrame`
* Prefer laying one `JPanel` on top of `JFrame` to do finer setups
* Takes care of title, border, size, etc.

---
# JPanel

* `setLayOut(LayoutManager mgr)`
* `add(Component comp, int index)`
  * Panels, buttons, text boxes, etc. are all `Component`s
  * `index`, e.g. `BorderLayout.NORTH`

---
# Layout Manager

* Determines the size and position of the components within a container
* A Visual Guide to Layout Managers
  * https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
* `BorderLayout`, `GridLayout`

---
# Eclipse Window Builder

* A plugin for drag-and-drop style Swing GUI design
* `Help` -> `Eclipse Marketplace`

---
# Make It Alive

* All the above are _static_
* Add actions by `addActionListener`
* `void actionPerformed(ActionEvent e)`

---
# Example

* A single button on a simple remote
* Add a text area on the remote
* Another window: the tv
* Black screen when off
* Play news channel when on
* Can turn the tv on with the remote
* Can toggle
* Make the on/off report back to the remote

---
# Exercise

* Work in your lab567 team
* Add buttons to the remote control
* Add more channels
* Be creative and add more functionalities
