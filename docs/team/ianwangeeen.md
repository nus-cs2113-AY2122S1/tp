# Ian Wang Ee En - Project Portfolio Page

## Overview

**CCA Manager** is a simple and easy to use command-line based application aimed to assist you, a CCA manager, to easily add, delete or alter information about your
CCA members. Moreover, **CCA Manager** also allows you to perform its functions on any training-related activities
you may have in order to help you organise your CCA's timetable.

### Summary of Contributions

[Code Contributed](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=ianwangeeen&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByNone&breakdown=false&tabOpen=true&tabType=zoom&tabAuthor=ianwangeeen&tabRepo=AY2122S1-CS2113T-F12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&zFR=false&zA=ianwangeeen&zR=AY2122S1-CS2113T-F12-4%2Ftp%5Bmaster%5D&zACS=157.44077603812116&zS=2021-09-25&zFS=&zU=2021-11-04&zMG=false&zFTF=commit&zFGS=groupByRepos)


Features Implemented
* Set up Parser Class
  * Implemented Skeleton Code for overall Parser Class
  * Implemented Keyword Enum class for the overall parsing of values
  * Implemented various boolean `check` and `get` functions alongside other members
  * Handled exceptions in Parser Class alongside other group members

* Set up Entry Class
  * Implemented addEntry function to parse data through parser class from entry class

* Set up Ui Class
  * Handled any printing to come from Ui class instead of using `System.out.println()` in the other functions

* Feature Enhancements
  * Handled filter checks for `EditMember` class; disallow numbers in Names, letters in phone numbers, disallowed invalid genders
  * Handled version 2 of `deleteAttendance` class; changed delete attendance parameters to be more specific to training name
  * Handled Exceptions for `AttendanceStorage` class
  * Handled Exceptions for `deleteAttendance` class

UG Contributions
* Set up `Introduction` section of UserGuide.md
* Set up `List` portions of UserGuide.md
* Set up `Format` portions of UserGuide.md
* Set up `Examples` portions of UserGuide.md

DG Contributions
* Set up Introduction for DeveloperGuide.md
* Designed sequence diagram for `delete /m 1` command for DeveloperGuide.md
* Designed object diagrams for `Ui`, `Parser`, `Command` and `Entry` Classes for DeveloperGuide.md
* General editing of spacings and use of markdowns throughout DeveloperGuide.md