# Koh Qianqi's Project Portfolio Page

## Project: Get Jack'D

Get Jack'D is a Command-Line application that helps busy people manage their workouts and fitness routines quickly by
typing in commands. It is written in Java and has about 6 kLoC.

Given below are my contributions to the project.

- **New Feature**: Added basic `Command` classes as well as their `Parser` classes in v1.0
    - Basic `Command` classes include: `AddExerciseCommand`, `DisplayExercisesCommand`, `MarkExerciseAsDoneCommand`, 
    `RemoveExerciseCommand`, `CreateWorkoutCommand`, `DeleteWorkoutCommand`
    - `Parser` classes include: `AddExerciseParser`, `CreateWorkoutParser`, `RemoveExerciseParser`
    - **What it does**: Adds and removes workouts or exercises, lists workouts, and marks exercises as done.  
    - **Justification**: These basic commands provide the foundation of the app.
    - **Difficulties faced**: Writing the JUnit tests was difficult, since I was not familiar with writing tests.
    - Credits: [AddressBook-Level2](https://github.com/se-edu/addressbook-level2) The commands were based on 
    AddressBook-Level2.

- **New Feature**: Added the Search command in v2.0.
    - **What it does**: Allows the user to search through workouts and exercises by a keyword or date.
    - **Justification**: Allows the user to find their target workouts or exercises.
    - **Difficulties faced**: Figuring out a way to place all the necessary information to be displayed to the user was 
    difficult, so I created the `CommandResult` class to help with this.

- **Enhancement to existing feature**: Changed the Ui component to include a `CommandResult` class in v2.0.
    - **What it does**: `CommandResult` contains all information that will be displayed to the user.
    - **Justification**: In v1.0, there was too much coupling between the `Command` classes and the `Ui` class,
      and messages were pretty much displayed directly from the `executeUserCommand` method in the `Command` classes.
    - **Difficulties faced**: Thinking of how to store multiple `String`, `ArrayList<Workout>` and `ArrayList<Exercise>`
      was difficult, especially because in Java all `ArrayLists` have the same erasure. I eventually decided that a
      `HashMap` was the best solution.

- **Enhancement to existing feature**: Added the option to view a list of exercises in a table format in v2.0.
    - **What it does**: Prints the list of exercises in a table format.
    - **Justification**: This makes the list of exercises easier to read for the user.
    - **Difficulties faced**: Ensuring that the UTF-8 characters were displayed correctly was the biggest challenge. 
        Even after I managed to build the application with UTF-8 enabled, some characters were still not displayed 
        correctly, which I later realised to be a problem that only occurred on IntelliJ. 
    - Credits: [Ascii Table](https://mvnrepository.com/artifact/de.vandermeer/asciitable) This is the library that I
        used to print the table.

- **Code Contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=qqkoh&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=qqkoh&tabRepo=AY2122S1-CS2113T-F12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

- **Documentation**:
    - User Guide:
        - Added Command Summary.
        - Added documentation for Search command.
        - Edited other commands.
    - Developer's Guide:
        - Added documentation for Ui component.
        - Added Manual Testing for Search commmand.
        
- **Community**:
    - PRs reviewed (with non-trivial review comments): [#59](https://github.com/AY2122S1-CS2113T-F12-2/tp/pull/59#discussion_r725626678),

- **Tools**:
    - Integrated a third party library (Ascii Table) to the project. ([#129](https://github.com/AY2122S1-CS2113T-F12-2/tp/pull/129))