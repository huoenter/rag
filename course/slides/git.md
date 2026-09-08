# Using Git
## A Version Control Tool

---
# Version Control

* A system that keeps records of the changes
* Allows collaborative development
* Allows you to know who made what changes and when
* Allows you to revert any changes and go back to a previous state

---
# Git

* Git is not github.com

---
# Three Stages

* Committed: the data is safely stored in your local database
* Modified: you just changed the file
* Staged: you mark a modified file to go into you next commit snapshot

---
# Three Stages

![](https://drive.google.com/uc?id=1eM1k_dq2Z4Pl_wt6GCB0GTIiCpnfDeVe)

---
# Initialize a Repository

* `git init`

---
# Adding a NEW File

* `git add <file>`
* `<file>` can be a file or a folder

---
# Viewing Changes

* `git status`
* `git diff`

---
# Committing the Changes

* `git commit -m "..."`
  * committing staged changes with the message

---
# Viewing Commit History

* `git log`
* `git dag`

---
# Our Labs: Step 1

* `git init`
* `git add src`
* `git commit -m 'adding lab1'`
* `git tag lab1`

---
# Step 2

* Once code has been added
* `git add src`
  * Note: don't `git add *`
* `git commit -m '<some additional job>'`

---
# Step 3

* Get the `diff`
* `git diff <previous> <current>`
  * can be tag names, branch names, hash, etc.
* For HARD COPY submissions, `git diff lab1 lab2`
* `git diff lab1 lab2 > difference.txt`
  * `difference.txt` can have any name...
  * The text is directed to `difference.txt`

---
# Getting Help

* Book: _Pro Git_
  * Available online (link on D2L)
* Ad-hoc questions: google it
* Office Hours & Tutors in MCT162

---
# Homework

* Reading: Pro Git 1.3, 2.1, 2.2, 2.3 by 9/23
  * You'll need them for Lab 2
  * More chapters when we start the team labs
