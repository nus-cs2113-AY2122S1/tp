# Leyond's Project Portfolio Page

## Project: Teaching Assistant's Assistant (TAA)
Teaching Assistant's Assistant (TAA) is a Command Line Interface (CLI) application for keeping track of classes and students.
It is written in Java, and has about 9 kLoC.

Given below are my contributions to the project.
* **New Feature**: Added `Command` abstract class.
  * What it does: Separates the logic of each command into different classes.
  * Justification: This upholds the Separation of Concerns (SOC) principle and help achieve modularity by separating the
    code of different commands into separate classes.

* **New Feature**: Added `Parser` class.
  * What it does: Parses user input into a `Command` object and extracts values from the argument string.
  * Justification: This upholds the Single Responsibility Principle (SRP) by allowing `Command` classes to only contain
    code relating to the command and remove the need for it to parse user input.

* **New Feature**: Added `Ui` class.
  * What it does: Handles all user interaction operations
  * Justification: This provides methods to obtain user input and display messages to user in a standardised format.

* **New Feature**: Added `Storage` class
  * What it does: Handles persistent data within the application.
  * Justification: This provides an abstraction for managing file operations and allows other classes to easily save the
    application data without worrying about the underlining implementation.

* **New Feature**: Added `Taa` class
  * What it does: `Taa` contains the static `main` method to initialize and run the `Taa` instance.

* **New Feature**: Added `list_classes`, `add_class`, `edit_class`, `delete_class` commands.
  * What it does: Allows user to add, edit, or delete classes.
  * Justification: Users will be able to easily manage their data.

* **New Feature**: Added `help` command.
  * What it does: Allows the user to view the list of available commands.
  * Justification: The command provides users with a quick way to view all the available commands instead of having
    to constantly refer to the user guide.

* **New Feature**: Added `exit` command.
  * What it does: Allows the user to exit the application.
  * Justification: The command exits the application gracefully instead of having to use `CTRL + C`.

* **Code contributed**: [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=leyondlee&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=leyondlee&tabRepo=AY2122S1-CS2113T-F12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Setup milestones `v1.0` - `v2.1` on GitHub
  * Created labels for issue tracker
  * Assisted in the management of releases `v1.0` - `v2.1` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Wrote additional tests for `Parser` to ensure that it is working as intended. ([#216](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/216/files))
  * Wrote deserializers for `GSON` to ensure that the appropriate constructors are called. ([#77](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/77/files))
    * Note: By default, `GSON` calls the default (no-args) constructor to initialize a class.

* **Documentation**:
  * User Guide:
    * Added documentation for `Class` features. ([#91](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/91),
      [#119](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/119),
      [#215](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/215),
      [#224](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/224))
    * Added documentation for `archive` and `reset` commands. ([#147](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/147))
    * Added horizontal lines between command categories to improve readability. (([#223](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/223))
  * Developer Guide:
    * Added implementation details and sequence diagram of the `add_class` feature. ([#90](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/90))
    * Added Architecture and Architecture Sequence Diagrams ([#90](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/90),
      [#96](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/96),
      [#129](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/129),
      [#146](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/146))
    * Added Class Diagrams and explanation for components. ([#232](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/232),
      [#238](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/238))

* **Team-Based Tasks**:
  * Setup skeleton code.
    * Created base classes (e.g. `Command`, `Taa`, `TaaException`, `Parser`, `Ui`, etc).
    * Provided `getArgumentsFromString` method in `Parser` to extract argument values from the user input.
  * Added user stories in Developer Guide. ([#132](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/132))
  * Maintained Issues and Pull Requests
  * Helped teammates test for bugs in their implementation.

* **Community**:
  * Reported bugs for other teams for [PED](https://github.com/leyondlee/ped/issues).

* **Tools**:
  * Integrated [GSON](https://github.com/google/gson) for data storage. ([#77](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/77))
