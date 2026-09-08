# Git Exercise 2

Sit w/ your lab 4 teammates, form 2-2 or 2-3 groups

* There are two Roles: A, B
    * Switch roles for the 2nd round

If there are three students: x, y, z, go over the exercise THREE times as below:

1. A-x, B-y: student x plays A and student y plays B
1. A-y, B-z
1. A-z, B-x

## SSH Key

You may be asked for the SSH key. To generate the SSH key, either on the
terminal of lab machine or the Git Bash on Windows, type the command:

```
ssh-keygen
```

For all questions, hit Enter (no input needed).

To see your public key which locates in your HOME folder `.ssh/id_rsa.pub`:

```
cat ~/.ssh/id_rsa.pub
```

Copy and paste the output to add this key to Gitlab.


## Begin of the Exercise Steps

---
Role A:

* if this is not the 1st round, either delete the folder from previous rounds
  or start in a new parent folder
* Create `klasses` folder
* Create `fall2020.txt` and `spring2020.txt`
* Put two courses in each txt file
* Stage and commit

---
Role A:

* On `gitlab.engr.ship.edu`, "New Project" with some awesome name
  * F23: Now it asks you to "Create a group", please follow the instruction
  * F23: Now it checks "adding README" by default. Do NOT add a README
* Make it private
* Our option is "Push an existing folder"
* You don't need to follow all the commands
* You just need to do the following:
    * `git remote add origin <URL>` The URL should start with `https` not `git`
    * `git remote` examines the remotes, `-v` for details
    * `git push origin master`, `-u` remembers the choice, just `git push` next
      time
* Examine your project on gitlab

---
Role A:

* settings -> Members: add Role B as a maintainer
  * F23: Protection shall be off by default now, ignore the steps below
  * Turn off master/main branch protection: settings -> repository -> protected branch
  * If master/main is unprotected, B can be a developer

---
Role B:

* `git clone ...`
  * To avoid possible name clash, clone it in some other folder
* Modify `spring2020.txt`, stage and commit
* `git log` to see the remote head and local head
* `git push origin master`
* `git log` to see the remote and local are the same now
* Examine the gitlab

---
Role A:

* `git log`
* `git pull origin master`
  * `git pull` is the combination of `fetch` and `merge`
  * officially we only use `pull`
* Notice the information of the `pull`
* `git log`

* Let's Create a Conflict
* Add a class to `fall2020.txt`, stage, commit, push

---
Role B:

* DON'T PULL!
* Add a _different_ class to `fall2020.txt`, stage, commit, push
* This is a typically situation: B is not aware of A's change

```
warning: redirecting to https://gitlab.engr.ship.edu/chuo/swe200-git-tutorial.git/
To https://gitlab.engr.ship.edu/chuo/swe200-git-tutorial
 ! [rejected]        master -> master (fetch first)
error: failed to push some refs to 'https://gitlab.engr.ship.edu/chuo/swe200-git-tutorial'
hint: Updates were rejected because the remote contains work that you do
hint: not have locally. This is usually caused by another repository pushing
hint: to the same ref. You may want to first integrate the remote changes
hint: (e.g., 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.
```

* Read the error message
* `git pull origin master`
    * If you instead get a message which asks you to set the strategy,
      following the command that sets `rebase` to `false`, and do the pull
      again

```
From https://gitlab.engr.ship.edu/chuo/swe200-git-tutorial
 * branch            master     -> FETCH_HEAD
   7e114b5..cc92edf  master     -> origin/master
Auto-merging fall2020.txt
CONFLICT (content): Merge conflict in fall2020.txt
Automatic merge failed; fix conflicts and then commit the result.
```

* conflict in `fall2020.txt`
* edit the file

```
swe200
cmpe220
<<<<<<< HEAD
univ101
=======
eng114
>>>>>>> cc92edf9b13ef3aa62fe3b182ab703db03911196
```

* from `HEAD` through `====`, that's what you currently have
* the other part is what the remote has
* You can modify the file in any way that makes sense
* For example, keep `univ101` (you can also keep both, etc.)
* `git add ...`
* `git commit ...`
* `git log --oneline --graph`
    * You should be able to see the "diamond" shape in the graph
* `git push origin master`
* Check it on Gitlab

---
Role A:

* `git pull ...`
* `git log --oneline --graph`
    * You should be able to see the "diamond" shape in the graph


THE END OF EXERCISE

---
## Note

* Tags can be pushed to remote
  * `git push origin <tag-name>`
* Optional video: [MIT Missing Semester - Version Control](https://missing.csail.mit.edu/2020/version-control/)
  * https://missing.csail.mit.edu/2020/version-control/
  * Explains the design of git
  * There are other lessons such as some command line skills
