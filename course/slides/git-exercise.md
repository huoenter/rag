# GIT In Class Exercise

---
* Create a new folder `klasses`
* Inside of the folder, `git init`
* Edit a file `fall2020.txt`, put `swe200` as the 1st line of the file
* `git add fall2020.txt`
* Use `git status` to check the status

---
* Commit `git commit -m 'add swe200 in fall2020.txt'`
* Use `git log` to check the commit
* Play with the `--oneline`, `--pretty`, `--graph`, `--decorate` options in
  different combinations
* `git tag tagA`
* `git tag` shows all the tags
* `git log` with options you like, you can see the tags

---
* Add `cmpe220` to `fall2020.txt` or some other course you are taking this
  semester
* `git diff` to see the difference
* `git status`
* `git add fall2020.txt`
* `git diff`
* `git status`
* `git commit -m 'add cmpe220'`
* `git log` with options you like

---
* Add a file `spring2020.txt` in the same folder
* Input the names of the courses you took
* `git status`
* `git diff`
* `git add spring2020.txt`
* `git commit` with some message

---
* `git tag tagB`
* `git tag`
* `git diff tagA tagB`
* `git diff tagA tagB > diff.txt`
* Make a pdf out of `diff.txt`
