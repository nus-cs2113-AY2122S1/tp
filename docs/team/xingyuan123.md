# Kom Xing Yuan - Project Portfolio Page

## Overview

**CCA Manager** is a simple and easy to use command-line based application aimed to assist you, a CCA manager, to easily add, delete or alter information about your
CCA members. Moreover, **CCA Manager** also allows you to perform its functions on any training-related activities
you may have in order to help you organise your CCA's timetable.

### Summary of Contributions

* [Code Contributed](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=xingyuan123&tabRepo=AY2122S1-CS2113T-F12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code&authorshipIsBinaryFileTypeChecked=false)

* Enhancements Implemented
  * Enhanced `AddMember` command
    * checks the data to see whether they are valid.
  * Set up Storage component
    * Implemented `MemberStorage`, `TrainingStorage` and `AttendanceStorage` classes.
    * Implemented  a feature that multiple attendance files can be generated based on a single attendance list.
    * Implemented a feature such that attendance file names is based on training name.
    * Verification of all the data when manually edit the CSV file.
    * Implemented a feature such that it automatically changes the relevant CSV file whenever a valid command is made.
    * Handled most exceptions in those classes mentioned above.

* UG Contributions
  * Set up `Storage` section. Mostly gives warnings about what not to do regarding Storage.

* DG Contributions
  * Set up `Storage` DeveloperGuide.md.