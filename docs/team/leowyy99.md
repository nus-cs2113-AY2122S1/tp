---
layout: page
title: Leow Yuan Yang's Project Portfoilo Page
---
### Project: SEPlanner

SEPlanner is a lightweight desktop application for Computer Engineering undergraduates from NUS to plan for the Student
Exchange Programme, optimised for use via Command Line Interface (CLI).

Given below are my contributions to the project.

* **New Feature**: Added the `Parser` class to handle inputs for all commands. 
 
* **New Feature**: Added the `AddCommandParser`, `RemoveCommandParser`, `ListCommandParser`, `ExitCommandParser`, `HelpCommandParser` classes to handle the arguments of various commands.

* **New Feature**: Added the `ParserClassException` and its inherited classes to handle exceptions for `Parser` component.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=leowyy99&tabRepo=AY2122S1-CS2113T-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed releases `v2.0` on GitHub

* **Enhancements to existing features**:
  * Added methods in `Ui` component to handle `ParseClassException`.
  * Added constants in `Constants` component for logging messages, command words, flags, and parse error messages.

* **Documentation**:
    * User Guide:
        * Added documentation for `Glossary` section.
        * Added documentation for `Command Summary` section.
        * Added documentation for `Features` section.
        * Added documentation for `Program Overview` section.
        * Added documentation for `Troubleshooting` section.
    * Developer Guide:
        * Added implementation and class diagram for `Parser` component.
        * Added manual testing section for `add`, `remove`, `list`, `searchmap` commands.

* **Community**:
    * Reported bugs and suggestions for other teams in the class: Verbally informed CS2113T-T09-1 group about a bug in their program.
    * Evaluated group CS2113-T13-3 for bugs.