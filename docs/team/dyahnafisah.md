# Dyah Ayu Nurun Nafisah - Project Portfolio Page

## Overview

Libmgr is a desktop app for managing the inventory of libraries, optimised for use via a Command Line Interface (CLI).
Designed for fast typists, it can help to augment the day-to-day tasks of a librarian and can help them to get tasks done in an efficient manner.

## Summary of Contributions

### Code Contributed

[Click to view code contribution on RepoSense](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=dyahnafisah&tabRepo=AY2122S1-CS2113-T16-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

### Enhancements Implemented

1. Added `Catalogue` class which acts as a container for the `Item` class and its inheritance.
2. Implemented `LoanCommand` class to loan an item to a user and change the details/attributes of the item 
   (status, dueDate, loanee) accordingly.
3. Implemented `ReturnCommand` class to return an item that has been borrowed by a user before.
4. Added enumeration class `Status` to make the item status more consistent and easier to implement.
4. Implemented `ReserveCommand` class to reserve an item before it is being loaned out.
5. Implemented `DeadlineCommand` class to list out the loaned items based on their deadline 
   (today, overdue, or a specific date)

### Contributions to the User Guide

1. Added usage for `loan` and `return` commands.
2. Added usage for `deadline today`, `deadline overdue`, and `deadline d/dd-mm-yyyy`.
3. Added some additional information for the other commands related to the issues that have been solved. 
   (E.g. status can be case insensitive in `search` command).


### Contributions to the Developer Guide

1. Created sequence diagrams and the details for `LoanCommand` and `DeadlineCommand`.
2. Created an object diagram of a specific example in the implementation of `LoanCommand`. 
3. Added v2.1 user stories.

### Contributions to team-based-tasks

1. Implemented skeleton code for the team project which included `Catalogue` container class 
   and `Status` enumeration class.
2. Helped in maintaining the issue tracker.
3. Helped in reviewing and merged other teammates' pull requests.
4. Helped in testing and bug-fixing other command classes, especially in `SearchCommand` and `ListCommand`.

### Review/mentoring contributions
1. PR reviewed: https://github.com/AY2122S1-CS2113-T16-1/tp/issues/158
2. Suggested issue: https://github.com/AY2122S1-CS2113-T16-1/tp/issues/136

