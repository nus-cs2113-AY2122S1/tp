# Teo Chin Kai Remus - Project Portfolio Page

This is a student project for a university software development course and I am one of the contributors to `MediVault`. 

`MediVault` is a Command Line Interface (CLI) application that will help to manage medication supplies within a pharmacy. It is an integrated solution that provides real-time tracking of stocks, prescriptions and orders.

## Summary of Contributions

Given below are my contributions to the project.

Code contributed: more than 2400 lines of code. [[RepoSense](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=RemusTeo&tabRepo=AY2122S1-CS2113T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)]

### Features

#### v1.0:

* Implemented `deletestock` command. [[#19](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/19)]
* Implemented `help` command. [[#27](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/27)]
  
#### v2.0:

* Implemented `listorder` command. [[#116](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/116), [#134](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/134), [#178](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/178)]
* Implemented `Storage` class. [[#127](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/127), [#183](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/183)]
* Implemented `FileParser` class. [[#154](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/154), [#200](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/200)]
* Implemented `archiveprescription` and `archiveorder` command. [[#193](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/193), [#226](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/226)]

#### v2.1:

* Fix bug for `archive*` commands where time affected exact date matching. [[#222](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/222/files)]
* Improved `archiveorder` and `archiveprescription` commands. [[#297](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/297)]
* Standardized data in storage and archive to Upper Case for consistency. [[#314](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/314)]

### Enhancements to Existing Features

* Added checking for existing stock in `StockValidator isValidStockId()` method. [[#19](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/19)]
* Included JUnit tests:
    * `isValidStockId()`. [[#55](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/55)]
    * `DateParserTest`. [[#90](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/90), [#125](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/125), [#304](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/304)]
    * `DeleteStockCommandTest`. [[#304](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/304)]
    * `ListOrderCommandTest`, `ArchiveOrderCommandTest`, `ArchivePrescriptionCommandTest`. [[#314](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/314)]
* Refactor all usage of `stockID` to `stockId` to maintain consistency with `orderId` and `prescriptionId`. [[#200](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/200)]
* Implemented `removeTime()` method in `DateParser`. [[#222](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/222)]

### Documentation

* [User Guide](../UserGuide.md)
    * Added documentation for `deletestock`, `listorder`, `archiveorder`, `archiveprescription` commands
      . [[#101](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/101), [#176](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/176), [#202](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/202), [#228](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/228)]
    * Added documentation for `Data Storage`, `Data Editing`, `FAQ` sections in UG
      . [[#202](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/202)]
    * Added documentation for `Purpose` section in UG
      . [[#294](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/294)]


* [Developer Guide](../DeveloperGuide.md)
    * Section on `Storage` component
      . [[#282](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/282)]
    * Sections on `DeleteStockCommand`, `ArchivePrescriptionCommand`, `ArchiveOrderCommand`
      . [[#219](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/219), [#274](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/274)]
    * Value Proposition & Non Functional Requirements
      . [[#266](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/266)]
    * Instructions for Automated Testing
      . [[#317](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/317)]

### Team-Based

* Attended all team meetings
* Documentation of the Purpose & FAQ in UG.
* Documentation of Value Proposition & NFRs & Automated Testing in DG.
* Providing feedback and communication with TA for advice on sequence diagrams in DG.
* Github pages emoji plugin.
* Pull Requests reviewed with non-trivial review comments
  . [[#45](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/45), [#99](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/99), [#204](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/204), [#211](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/211), [#280](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/280), [#316](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/316)]

### Community

* Reported bugs and suggestions for other teams in the class [[#1](https://github.com/RemusTeo/ped/issues/1), [#2](https://github.com/RemusTeo/ped/issues/2), [#3](https://github.com/RemusTeo/ped/issues/3), [#4](https://github.com/RemusTeo/ped/issues/4), [#5](https://github.com/RemusTeo/ped/issues/5), [#6](https://github.com/RemusTeo/ped/issues/6), [#7](https://github.com/RemusTeo/ped/issues/7), [#8](https://github.com/RemusTeo/ped/issues/8)]