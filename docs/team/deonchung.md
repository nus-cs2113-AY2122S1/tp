# Deon Chung Hui - Project Portfolio Page

This is a student project for a university software development course and I am one of the contributors to `MediVault`.

## Summary of Contributions

Given below are my contributions to the project.

Code contributed: more than 2000 lines of
code. [RepoSense](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=deonchung&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25)
### Features

#### v1.0:

* Implemented `addstock` command.
    * Functionality: Users are able to add stock information with one command.
    * Justification: User will be able to add medication's price, quantity, maximum quantity, 
  description and expiry date using a single command.
    * Pull request: [[#23](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/23)]

#### v2.0:

* Implemented `addprescription` command.
    * Functionality: Users are able to add prescription information with one command.
    * Justification: Users will be able to track prescription of customers and also deducting the stock when prescribing medication.
    * Pull request: [[#74](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/74)]

* Implemented `deleteprescription` command.
    * Functionality: Users are able to delete prescription information with one command.
    * Justification: Users will be able to delete prescription from prescription list and 
  also deducting the adding the stock quantity back to stock with a single command.
    * Pull request: [[#147](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/147)]

* Implemented `deleteorder` command.
    * Functionality: Users are able to delete order information with one command.
    * Justification: Users will be able to delete order from the order list.
    * Pull request: [[#119](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/119)]

#### v2.1:

* Fix bug in `addstock` where maximum quantity can be exceeded if the same medication with the same expiry date is added.
  * Pull request: [[#255](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/255)]
* Fix bug in `addprescription` where expired medication can be prescribed. 
  * Pull request: [[#295](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/295)]
* Fix bug in `addprescription` where the wrong error message is shown.
  * Pull request: [[#306](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/306)]
* Demo video for prescription features.
    
### Enhancements to Existing Features

* Implemented `Prescription Validator` method.
    * Functionality: Checks if prescription ID,customer ID, Staff Name,prescription date and prescription column/alias is valid.
    * Justification: This method can be called for any of the prescription command that need to check for any of the inputs as stated above.
    * Pull request: [[#86](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/86)]

* Implemented `Order Validator` method.
    * Functionality: Checks if order ID, order status,order date and order column/alias is valid.
    * Justification: This method can be called for any of the order command that need to check for any of the inputs as stated above.
    * Pull request: [[#119](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/119)]

* Implemented `getNotExpiredStockQuantity` method in `PrescriptionManager`
  * Functionality: Retrieves the total stock quantity for medicine with same name that has not expired.
  * Justification: This method can be called for any of the prescription command that need to retrieve quantity that has not expired.
  * Pull request: [[#295](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/295)]

* Included JUnit tests for:
    * `Prescription Validator` class
        * Pull request: [[#89](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/89)]
    * `Order Validator` class
        * Pull request: [[#140](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/140)]
    * `AddStock`, `AddPrescription`, `DeletePrescription` and `DeleteStock`
      * Pull request: [[#306](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/306)]
* Added functionality for `addstock` command to limit number of medication for stock.
    * Functionality: User will not be able to input quantity above the maximum quantity. 
    * Justification: Prevents user from adding too much medication to the stock.
    * Pull request: [[#42](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/42)]

### Documentation

* [User Guide](../UserGuide.md)
    * Added documentation for `addstock` commands
      . [[#96](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/96)]
    * Added documentation for `addprescription`, `deleteprescription` and `deleteorder` commands
      . [[#171](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/171)]

* [Developer Guide](../DeveloperGuide.md) 
  * Acknowledgement 
    * [[#194](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/194)]
  * Setting up environment
    * Added explanation on how to set up and start the program.
             [[#194](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/194)]
  * Implementation
    * `addstock`, `addprescription` ,`deleteprescription` and `UpdateOrderCommand` sequence diagrams and detailed explanation.
             [[#171](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/171)]
  * User stories
    * Added all the user stories.
             [[#255](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/255)]
  * Instruction for manual testing
    * Added details and explanation for manual testing.
             [[#258](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/258)]

### Team-Based

* Attended weekly team meetings.
* Release management.
* Pull Requests reviewed with non-trivial review comments. [[#298](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/298)]

### Community

* Reported bugs and suggestions for other teams in the class. [[#1](https://github.com/deonchung/ped/issues/1), [#2](https://github.com/deonchung/ped/issues/2),
  [#3](https://github.com/deonchung/ped/issues/3), [#4](https://github.com/deonchung/ped/issues/4), [#5](https://github.com/deonchung/ped/issues/5),   
  [#6](https://github.com/deonchung/ped/issues/6), [#7](https://github.com/deonchung/ped/issues/7)]
