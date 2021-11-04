# Ishaan Vyas's Project Portfolio Page

## Project: Get Jack'd
Get Jack'd is a Command-Line application that helps busy people manage their workouts and fitness routines quickly by
typing in commands. It is written in Java and has about 6 kLoC. (6000 lines of code)

Given below are my contributions to the project.

- **New Feature**: Added `EnterWorkout` command which lets users enter into a workout (v2.0).
  * **What it does**: Users can enter into workouts so that the `add`, `edit`, `remove`, `display`, `clear` and `done` commands don't require a workout index to be entered.
  * **Justification**: Lets say a user has just created a workout and is wishing to add and edit several exercises. In such a scenario having to option to enter into a workout and quickly add exercises without having to enter the workout index could greatly boost efficiency.
  * **Difficulties faced**: Implementing the `EnterWorkout` function was tricky as this was a functionality that we had come up with after setting up v1.0 and there were very few ways to implement this functionality without majorly changing all the previously written code.
    I ended up adding a static variable called `workoutMode` in the `Command` class so that special commands could edit this `workoutMode`.
    The parser class was edited so that incase the user was inside a workout then it would use this `workoutMode` instead of the `workoutIndex`. 
    Furthermore, as more features were being added, I had to also ensure that all features worked as expected when called inside a workout.

- **Enhancement to existing feature**: Made it so that recommended workouts get saved into data file after the `recommend` command is called.
  * **Justification**: So that users don't have to continuously call the `recommend` command when they want a recommendation.
  * **Difficulties faced**: Quite significant changes had to be made to the `Workout` object as a specialized constructor and methods were required so that all recommended exercises could be added at once.

- **Extras**: 
  * Provided Skeleton for `Workout` and `WorkoutList` Classes.
  * Added `LoggerUtil` class to help setup loggers for each class and attach all to a command FileHandler.
  * **Difficulties faced**: Setting up a logger ended up being much harder than expected as it is recommended to have a different logger for each class.
    At first, I made a separate `setupLogger()` function for each class, but that would have caused a redundant method in each class.
    Furthermore, this also caused multiple log files to be created as a new FileHandler was created each time a new logger was set up in each class.
    To solve this, I created a LoggerUtil class which could assist in the setup of the logger so that only a single common FileHandler is attached to each logger.
    This also ensured that all loggers have the same level.

- **Code Contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=IshaaanVyas&tabRepo=AY2122S1-CS2113T-F12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

- **Documentation**: 
  * User Guide:
    * Added information for `enter` command and `back` command.
    * Added expected outcome when inside a workout for certain commands. (`edit`,`clear`,`display`)
  * Developers Guide:
    * Integrated plantUML for class/sequence diagram and provided an example for drawing class diagrams.
    * Added basic architecture diagram.
    * Added sequence diagram for basic architecture.
    * Added class diagram for `data` component (`WorkoutList`,`Workout`, and `Exercise`).
    * Added sequence diagram for `data` component on how the lists are converted into models.
    * Added Logger Guide.
  * **Difficulties faced**: Plant UML was used for the UML class and sequence diagrams which was initially challenging to learn and get used to its syntax.
    Since many of the default settings of plant UML were not optimal for class diagrams of the type we learnt in the lecture (this was before prof released tips for using plant UML), it took quite abit of research to figure out a way to  change those settings (such as changing visibility to "+"/"-" and removing the circles).

- **Project Management**:
  * Helped solve merge conflicts
  * Managed DG by appropriately editing other class diagrams to fit the style of the DG (common colour scheme and visibility issues)
  * Helped find and solve several bugs.
  * Ensured issues were created properly (assigned to people properly).
  * Enabled Assertions in `build.gradle`.

- **Community**:
  * PRs reviewed (Team-members): [#12](https://github.com/AY2122S1-CS2113T-F12-2/tp/pull/12), [#16](https://github.com/AY2122S1-CS2113T-F12-2/tp/pull/16), [#68](https://github.com/AY2122S1-CS2113T-F12-2/tp/pull/68), [#129](https://github.com/AY2122S1-CS2113T-F12-2/tp/pull/129)
  * PRs reviewed (Other teams): [#14](https://github.com/nus-cs2113-AY2122S1/tp/pull/14/files/906ac5177be76e065fff71a73dfd2abc87762e4c).