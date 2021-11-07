# Deon Chung Hui - Project Portfolio Page

This is a student project for a university software development course and I am one of the contributors to `MediVault`.

## Summary of Contributions

Given below are my contributions to the project.

Code contributed: more than 2300 lines of
code. [RepoSense](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=deonchung&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25)
### Features

v1.0:

* Implemented `addstock` command. [[#23](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/23)]

v2.0:

* Implemented `addprescription` command. [[#74](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/74)]
* Implemented `deleteprescription` command. [[#147](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/147)]
* Implemented `deleteorder` command. [[#119](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/119)]

v2.1:

* Fix bug in `addstock` where maximum quantity can be exceeded if the same medication with the same expiry date is added. [[#255](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/255)]
* Fix bug in `addprescription` where expired medication can be prescribed. [[#295](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/295)]
* Fix bug in `addprescription` where the wrong error message is shown. [[#306](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/306)]
* Demo video for prescription features.
    
### Enhancements to Existing Features

* Implemented `Prescription Validator` method. [[#86](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/86)]
    * Functionality: Checks if prescription ID,customer ID, Staff Name,prescription date and prescription column/alias is valid.
    * Justification: This method can be called for any of the prescription command that need to check for any of the inputs as stated above.
    
* Implemented `Order Validator` method. [[#119](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/119)]
    * Functionality: Checks if order ID, order status,order date and order column/alias is valid.
    * Justification: This method can be called for any of the order command that need to check for any of the inputs as stated above.
    
* Implemented `getNotExpiredStockQuantity` method in `PrescriptionManager`. [[#295](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/295)]

* Added functionality for `addstock` command to limit number of medication for stock. [[#42](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/42)]

* Included JUnit tests for:
  * `Prescription Validator` class. [[#89](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/89)]
  * `Order Validator` class. [[#140](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/140)]
  * `AddStock`, `AddPrescription`, `DeletePrescription` and `DeleteStock`. [[#306](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/306)]

### Documentation

* [User Guide](../UserGuide.md)
    * Added documentation for `addstock` commands. [[#96](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/96)]
    * Added documentation for `addprescription`, `deleteprescription` and `deleteorder` commands. [[#171](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/171)]

* [Developer Guide](../DeveloperGuide.md) 
  * Acknowledgement. [[#194](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/194)]
  * Setting up environment. [[#194](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/194)]
  * Section on `addstock`, `addprescription` ,`deleteprescription` and `UpdateOrderCommand` and their respective sequence diagrams. [[#171](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/171)]
  * User stories. [[#255](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/255)]
  * Instruction for manual testing. [[#258](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/258)]

### Team-Based

* Attended all weekly team meetings.
* Documentation of acknowledgement, setting up environment, user stories and instruction for manual testing in DG.
* Pull Requests reviewed with non-trivial review comments [[#298](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/298)].

### Community

* Reported bugs and suggestions for other teams in the class. [[#1](https://github.com/deonchung/ped/issues/1), [#2](https://github.com/deonchung/ped/issues/2),
  [#3](https://github.com/deonchung/ped/issues/3), [#4](https://github.com/deonchung/ped/issues/4), [#5](https://github.com/deonchung/ped/issues/5),   
  [#6](https://github.com/deonchung/ped/issues/6), [#7](https://github.com/deonchung/ped/issues/7)]
