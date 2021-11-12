---
layout: page
title: Ma Zijian's Project Portfoilo Page
---
### Project: SEPlanner

SEPlanner is a lightweight desktop application for Computer Engineering undergraduates from NUS to plan for the Student
Exchange Programme, optimised for use via Command Line Interface (CLI).

Given below are my contributions to the project.

* **New Feature**: Collaborated with @titustortoiseturtle1999 on the University and Module related classes as the model for SEPlanner.

* **New Feature**: Added `searchmap` command. 
  * *What it does*: Allows users to look for available mappings of a specific university/all selected universities based on the user input.
  * *Justification*: This feature allows users to search for available mappings before adding them.

* **New Feature**: Added `add /map` command.
  * *What it does*: Add a pair of module mappings to a university.

* **New Feature**: Added `find` command.
  * *What it does*: Allows user to search for universities or modules based on keywords, and access their master list index.
  * *Justification*: This feature is crucial since master list index for both universities and modules are essential for all the commands we have.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=MAZJ124&tabRepo=AY2122S1-CS2113T-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed release of `v1.0`, `v2.0`, `v2.1` on GitHub.

* **Enhancements to existing features**: 
  * Added `ParseCondition` class to check for various invalid inputs from users, and refactored classes in `Parser` package based on this. (Pull requests [#270](https://github.com/AY2122S1-CS2113T-T09-2/tp/pull/270), [#272](https://github.com/AY2122S1-CS2113T-T09-2/tp/pull/272))
  * Added JUnit tests for `AddCommandParser` and `RemoveCommandParser` to ensure correct behavior against invalid inputs by users and increase test coverage. (Pull requests [#311](https://github.com/AY2122S1-CS2113T-T09-2/tp/pull/311), [#313](https://github.com/AY2122S1-CS2113T-T09-2/tp/pull/313))

* **Documentation**:
    * User Guide:
        * Added documentation for the university and module related classes
    * Developer Guide:
        * Added introduction about university and module related classes
        * Added class diagrams for university and module related classes
        * Added sequence diagram for `searchmap` command 
        * Added section about possible future updates of the product 

* **Community**:
    * Attended weekly co-ords on PR reviews and issue creations.