# Today

* Check lab 6
* Go over lab 7 outlines
* Quiz 8
* Thursday: "The Random Library", Quiz 9

---
# Lab 6 Bazaar

* Thursday 3:30 - 4:30 @ MCT158
* Buying team pay 5% of lab 7 to the selling team
* The sales rep should show that their project is well-documented, how to
  provide tech-support, etc.
* The "trade" must happen during the bazaar
  * Email me, cc all members of the two teams

---
# Lab 7 Team

* Lab 7 can be done by 2 or 3 people
  * No GUI
* Can make adjustments
* Come to me during today's office hours

---
# Thursday - A Library for Random Things

* Ad-hoc solution that a programmer must repeat every time
* Part of the Final Exam

```java
RecoveryBehavior rb = null; // anti-pattern
int recoveryBehavior =
  (int) (Math.random() * 3); //randomly select 1 of 3 recovery behaviors

if(recoveryBehavior == 0)  {
  rb = new RecoveryNone();
} else if (recoveryBehavior == 1) {
  rb = new RecoveryLinear(10);
} else if (recoveryBehavior == 2) {
  double percent = Math.random() * 0.3;
  rb = new RecoveryFractional(percent);
}
```

---
# A Library

```java
jshell> var rlfs = new RandList<LifeForm>(new RandLifeForm(), 10)

rlfs ==> RandList@28ba21f3

jshell> rlfs.choose()
$22 ==> [Human: Denise LF: 41 Armor: 4
, Human: Alice LF: 39 Armor: 4
, Human: Bob LF: 35 Armor: 2
, Human: Alice LF: 42 Armor: 3
, Alien: E.T. LF: 45 RB: Linear Recovery
, Alien: Roger LF: 47 RB: Fractional Recovery
, Human: Chad LF: 35 Armor: 0
, Human: Bob LF: 34 Armor: 5
, Human: Alice LF: 43 Armor: 4
, Alien: E.T. LF: 37 RB: Fractional Recovery
]
```
