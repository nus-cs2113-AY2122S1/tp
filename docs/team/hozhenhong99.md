# Ho Zhen Hong's Project Portfolio Page

## Project: Teaching Assistant's Assistant (TAA)
Teaching Assistant's Assistant (TAA) is a Command Line Interface (CLI) application for keeping track of classes and students.
It is written in Java, and has about 9 kLoC.

Given below are my contributions to the project.
* **New Feature**: Added `Student` class.
  * **What it does**: Added the `Student` class to represent a student taking a class.
  * **Justification**: Enable the creation of `Student` objects with relevant attributes
  
* **New Feature**: Added `StudentList` class.
    * **What it does**: Added the `StudentList` class to represent all students taking a class.
    * **Justification**: Enable the creation of the `ArrayList` of `Student` objects and the relevant methods.

* **New Feature**: Added `command` classes for `Student` across `v1.0` - `v2.1`
  * These include: `AddStudentCommand`, `EditStudentCommand`, `DeleteStudentCommand`, `ListStudentCommand`
  , `FindStudentCommand` and `SortByScoresCommand`.
  * **What they do**: Enable user to add, edit, delete and view student information to the `StudentList`.
  Users can also find students by certain keywords and sort students based on their overall score.
  * **Justification** These commands are essential in allowing the user to add students to their classes to keep 
    track of them. In addition, users can see how their class is doing through the `SortByScores` command.

* **New Feature**: Added `command` classes for `comment` attribute of `Student` across `v2.0` - `v2.1`
  * These include `SetCommentCommand`, `DeleteCommentCommand` and `ListCommentCommand`.
  * **What they do**: Enable user to add, edit, delete and view comment entries for students.
  * **Justification**: These commands are essential in allowing the user to add comment entries to their students to
  keep track of how each student is doing in the class.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=hozhenhong99&tabRepo=AY2122S1-CS2113T-F12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.0` - `v2.1` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Wrote additional tests for existing features to increase coverage
      (Pull request [\#234](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/234/files)) TO EDIT

* **Documentation**:
    * User Guide:
        * Added documentation for the `Student` and `Comment` features
          ([\#97](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/97/files),
          [\#108](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/108/files),
          [\#207](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/207/files),
          [\#251](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/251/files))
          , add after added diagrams
    * Developer Guide:
        * Added implementation details and sequence diagram of the `AddStudentCommand` feature.

* **Team-Based Tasks**:
  * Setting up the GitHub team repo
  * Update Developer guide with target user profile and value proposition.
  * Organising and hosting Zoom meetings to discuss features and issues.
  * Maintaining the issue tracker.
  * Delegation of work

* **Community**:
    * Reported bugs and suggestions for other teams in the class.
      ([example](https://github.com/hozhenhong99/ped/tree/main/files))