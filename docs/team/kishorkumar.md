# Kishor Kumar - Project Portfolio Page

#### Code contribution - [Click here](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=Kishor&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=KishorKumar11&tabRepo=AY2122S1-CS2113T-F12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

### v1.0

#### Week 8
* Provided code skeleton for the project with relevant classes.
* Added JUnit tests for `Workout` class.
* Added Ui - testing.

__Difficulties faced:__ Creating a code skeleton whilst predicting the future features proved to be helpful and difficult at the same time.
I had to constantly change each files to correct the flow of code so that the other members would not get confused. Moreover, the
Ui testing kept failing although everything seemed to be fine. I had to submit the PR with failed checks so that others could
work on the features. However, immediately after merging the pull request I found out that there were invisible spaces that differentiated
the `ACTUAL.TXT` and `EXPECTED.TXT`.

#### Week 9
* Added assert statements into `Exercise` class.
* Added logging in `Exercise` class.
* Added JavaDoc for `Ui` and `Exercise` class.
* Reformatted the entire code wherever necessary to tidy up and look for violation of coding standard.

__Difficulties faced:__  Reformatting the whole code to make it look like it was written by a single person posed a problem as the 
implementations were done slightly different. 

### v2.0

#### Week 10
* Implemented `recommend` function where users could receive a set of workouts with multiple exercises of different difficulties (beginner/intermediate/pro).
* Added assertions, logging, JUnit tests and JavaDoc for `recommend` function.
* Implemented `edit` function where users could edit a current exercise inside a given workout.
* Added assertions, logging, JUnit tests and JavaDoc for `edit` function.
* Created UML diagrams for `Command` and `Parser` classes in Developer Guide.

__Difficulties faced:__ Adding the `edit` function with pre-existing parser methods was slightly challenging. This was due to the fact
that other command's formats were straight forward and required lesser parameter. However, in this case the edit user format required
`workoutIndex`, `exerciseIndex`, `newDescription`, `sets and reps`. Thus, extracting and making sense of each parameter proved to be tedious. 
Moreover, adding Junit tests in `RecommendWorkoutCommandTest` proved to be a hassle as although the test written showed the same output, the 
test method of checking if it is showing the correct recommended workout kept failing.

#### Week 11
* Implemented `clear` function which allows users to remove all the exercises in a workout or remove all the workouts in the application.
* Added assertions, logging and JavaDoc for `clear` function.
* Updated User and Developer Guides.
* Added author tags for the team to define the workload/contribution for each member.

__Difficulties faced:__ Adding the `clear` function initially kept throwing exception. This was because I was planning to use a single constructor
at the start to make it look neater and easier. I tried sending `null` in place of `workoutIndex` when it came to `clear workout` command as
it did not require a `workoutIndex`. However, I realised that it is not possible to assign null with the current implementation. Thus, I created 
two constructors in `ClearComamnd` class to handle clearing of workouts and exercises separately.

### v2.1

#### Week 12
* Made this PPP document.
* Polished the User and Developer Guides.
* Tried to find bugs in our code.