# Teo Chin Kai Remus - Project Portfolio Page

This is a student project for a university software development course and I am one of the contributors to `MediVault`.

## Summary of Contributions

Given below are my contributions to the project.

Code contributed: more than 2400 lines of code. [[RepoSense](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=RemusTeo&tabRepo=AY2122S1-CS2113T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)]

### Features

#### v1.0:

* Implemented `deletestock` command.
    * Functionality: Users are able to delete stock information with one command.
    * Justification: Allow user to delete stock information for whatever reason
    * Pull request: [[#19](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/19)]
  

* Implemented `help` command. (Only implemented in 1.0, did not maintain it till the end)
  * Functionality: Users are able to display help information with one command.
  * Justification: Allow user to have some place to reference command usage.
  * Pull request: [[#27](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/27)]
  
#### v2.0:

* Implemented `listorder` command.
    * Functionality: Users are able to list order information along with optional parameters.
    * Justification: Allow user to see current order information.
    * Pull request: [[#116](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/116), [#134](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/134), [#178](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/178)]


* Implemented `Storage` class. 
    * Functionality: Persistent Data Storage for Stock, Prescription & Order.
    * Justification: Allow user to retain data across sessions. 
    * Pull request: [[#127](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/127), [#183](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/183)]
  

* Implemented `FileParser` class. 
    * Functionality: Data Validation for Stock, Prescription & Order data files. Throw error suggesting specific line in specific file to fix error if detected.
    * Justification: Prevent invalid input from entering MediVault through direct tampering of data file.
    * Pull request: [[#154](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/154), [#200](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/200)]


* Implemented `archiveprescription` and `archiveorder` command.
    * Functionality: Archive orders and prescriptions to remove records <= specified date and append into a static file that has readable format. 
    * Justification: Allow user to remove cluttered records. After long term usage will have many insignificant records. 
    * Pull request: [[#193](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/193), [#226](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/226)]

  
#### v2.1:

* Fix bug for `archive*` commands where time affected exact date matching.
  * Pull request: [[#222](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/222/files)]


* Improved `archiveorder` and `archiveprescription` commands.
    * Functionality: To show number of records archived.
    * Justification: Provide more information to the user.
    * Pull request: [[#297](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/297)]


* Standardized data in storage and archive to Upper Case for consistency.
  * Pull request: [[#314](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/314)]


### Enhancements to Existing Features

* Added checking for existing stock in `StockValidator isValidStockId()` method.
    * Functionality: Check if stock already exists.
    * Justification: Cannot be a valid stockId if already exists in stock.
    * Pull request: [[#19](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/19)]


* Included JUnit tests:
    * `isValidStockId()` method
        * Pull request: [[#55](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/55)]
    * `DateParserTest`
        * Pull request: [[#90](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/90), [#125](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/125), [#304](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/304)]
    * `DeleteStockCommandTest`
        * Pull request: [[#304](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/304)]
    * `ListOrderCommandTest`, `ArchiveOrderCommandTest`, `ArchivePrescriptionCommandTest`
        * Pull request: [[#314](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/314)]
   
 
* Refactor all usage of `stockID` to `stockId` to maintain consistency with `orderId` and `prescriptionId`.
    * Pull requests: [[#200](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/200)]


* Implemented `removeTime()` method in `DateParser`.
    * Functionality: Remove time from Date object.
    * Justification: For use when archiving to match user specified date.
    * Pull request: [[#222](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/222)]

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