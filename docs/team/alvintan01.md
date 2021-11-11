# Alvin Tan Guo Hao - Project Portfolio Page

This is a student project for a university software development course and I am one of the contributors to `MediVault`.

`MediVault` is a Command Line Interface (CLI) application that will help to manage medication supplies within a pharmacy.
It is an integrated solution that provides real-time tracking of stocks, prescriptions and orders.

## Summary of Contributions

Given below are my contributions to the project.

Code contributed: more than 4000 lines of
code. [[RepoSense](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=alvintan01&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=alvintan01&tabRepo=AY2122S1-CS2113T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)]

### Features

v1.0 tasks:

- Implemented skeleton code for MediVault. [[#10](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/10)]
- Implemented `Ui` class for MediVault. [[#10](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/10)]
- Implemented sort for `liststock` command. [[#45](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/45)]
- Implemented `exit`, `purge` and `help` command. [[#10](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/10), [#13](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/13), [#45](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/45)]

v2.0 tasks:

- Implemented `listprescription` command. [[#181](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/181)]
- Implemented `receiveorder` command. [[#208](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/208)]
- Implemented sort for `listorder` command and mode feature. [[#162](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/162), [#123](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/123)]

v2.1 tasks:

- Fixed bug where ID does not reset after `purge`, `receiveorder` does not mark an order as delivered and whitespace renders command invalid. [[#280](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/280)]
- Demo video for stock features.

### Enhancements to Existing Features

- Added order quantity to `liststock` command. [[#109](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/109)]
- Refactor `HashMap<String, String>` to `LinkedHashMap<String, String>`. [[#123](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/123)]
- Refactor `command` objects. [[#133](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/133)]
- Refactor `Dispense` to `Prescription` [[#208](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/208)]
- Included JUnit tests for `CommandParser`, `ListPrescription`, `Help`, `Purge` and sort for `ListOrder` and `ListStock`. [[#92](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/92), [#305](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/305)]

### Documentation

- [User Guide](../UserGuide.md)
    - Added documentation for `mode`, `purge`, `exit`, `help`, `listprescription` and `receiveorder`. [[#97](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/97), [#208](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/208)]
    - Added command summary. [[#97](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/97)]
    - Reordered the sections into add, list, delete and update. [[#229](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/229)]
    - Added glossary section [[#309](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/309)]

- [Developer Guide](../DeveloperGuide.md)
  - Design
    - Main application logic and explanation. [[#163](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/163)]
    - Validator class diagram and explanation. [[#198](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/198)]
    - Inventory class diagram and explanation. [[#198](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/198)]
  - Sections on `list` and `receiveorder` and their respective sequence diagrams. [[#163](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/163), [#223](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/223)]

### Team-Based
- Setting up the GitHub team org/repo.
- Assigning user stories to team members.
- Maintaining [Trello](https://trello.com/b/nMVm0vgz/cs2113t-user-stories) dashboard.
- Hosting weekly team meetings.
- Release management.
- Ensured UG and DG has a consistent format.
- Pull Requests reviewed with non-trivial review comments. [[#74](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/74), [#143](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/143), [#172](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/172)]

### Community
- Reported bugs and suggestions for other teams in the class. [[#1](https://github.com/alvintan01/ped/issues/1), [#2](https://github.com/alvintan01/ped/issues/2), [#3](https://github.com/alvintan01/ped/issues/3), [#4](https://github.com/alvintan01/ped/issues/4), [#5](https://github.com/alvintan01/ped/issues/5), [#6](https://github.com/alvintan01/ped/issues/6), [#7](https://github.com/alvintan01/ped/issues/7), [#8](https://github.com/alvintan01/ped/issues/8), [#9](https://github.com/alvintan01/ped/issues/9), [#10](https://github.com/alvintan01/ped/issues/10)]
