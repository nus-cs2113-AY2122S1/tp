# Teo Phing Huei, Aeron - Project Portfolio Page

This is a student project for a university software development course and I am one of the contributors to `MediVault`.

`MediVault` is a Command Line Interface (CLI) application that will help to manage medication supplies within a pharmacy. It is an integrated solution that provides real-time tracking of stocks, prescriptions and orders.

## Summary of Contributions

Given below are my contributions to the project.

Code contributed: more than 3000 lines of
code. [[RepoSense](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=a-tph&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=a-tph&tabRepo=AY2122S1-CS2113T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)]

### Features

v1.0:

* Implemented `updatestock` command. [[#25](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/25)]

v2.0:

* Implemented `updateorder` command. [[#121](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/121)]
* Implemented `updateprescription` command. [[#184](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/184)]

v2.1:

* Fixed functionality and documentation bugs raised during PE. [[#296](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/296)]
* Fixed bugs found in `updateprescription` command. [[#324](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/324)]
* Fixed bug found in `MedicineValidator` class. [[#328](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/328)]

### Enhancements to Existing Features

* Implemented universal `containsInvalidParameterValues()` method. [[#40](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/40)]

* Included JUnit tests for:
    * `StockValidator` [[#59](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/59)]
    * `MedicineManager` [[#93](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/93)]
    * `UpdateStockCommand`, `UpdateOrderCommand` and `UpdatePrescriptionCommand` [[#316](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/316)]

* Wrote and refractored `Manager` classes for `stock`, `prescription` and `order`. [[#69](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/69), [#141](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/141)]

* Added functionality for `deletestock` command to delete all expired stocks. [[#135](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/135)]

### Documentation

* [User Guide](../UserGuide.md)
    * Added documentation for `updatestock`, `updateprescription` and `updateorder` commands. [[#186](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/186)]

* [Developer Guide](../DeveloperGuide.md)
    * Created skeleton Developer guide for the team. [[#210](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/210)]
    * Included [[#213](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/213), [#270](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/270)]
        * Design
            * Architecture and Command components with class diagram and detailed explanation.
        * Implementation
            * Initial validation checker sequence diagram and detailed explanation under Main Logic
            * `UpdateStockCommand`, `UpdatePrescriptionCommand` and `UpdateOrderCommand` sequence diagrams and detailed
              explanation.

### Team-Based

* Created and contributed to [Trello](https://trello.com/b/nMVm0vgz/cs2113t-user-stories) dashboard.
* Attended weekly team meetings.
* Contributed to release managements.
* Ensured UG and DG has a consistent format.
* Pull Requests reviewed with non-trivial review comments. [[#171](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/171), [#295](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/295)]

### Community

* Reported bugs and provided suggestions for other teams in the class. [[#1](https://github.com/a-tph/ped/issues/1), [#2](https://github.com/a-tph/ped/issues/2), [#3](https://github.com/a-tph/ped/issues/3), [#4](https://github.com/a-tph/ped/issues/4), [#5](https://github.com/a-tph/ped/issues/5)]