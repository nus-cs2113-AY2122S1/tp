# Tan Choon Kai Glenn - Project Portfolio Page

## Overview

**CCA Manager** is a simple and easy to use command-line based application aimed to assist you, a CCA manager, to easily add, delete or alter information about your
CCA members. Moreover, **CCA Manager** also allows you to perform its functions on any training-related activities
you may have in order to help you organise your CCA's timetable.

## Summary of Contributions

[Code Contributed](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=poppolette&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=poppolette&tabRepo=AY2122S1-CS2113T-F12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false
)

## Features Implemented
* Set up `training` package
  * Implemented `TrainingSchedule` class
    * `TrainingSchedule` is a class that stores `TrainingName`, `TrainingVenue` and `TrainingTime`, used to identify a specific training
    * `TrainingSchedules` have _unique_ `TrainingName`, multiple `TrainingSchedule` entries cannot have the same name
    * Implemented getters and setters for `TrainingName`, `TrainingVenue` and `TrainingTime`
    * Implemented constructor for `TrainingSchedule`
    * Formatted `toString()` to be more readable
    * Continued updating class throughout implementation to keep it up-to-date
  * Implemented `TrainingList` class
    * Contains an ArrayList of `TrainingSchedules`
    * Stores all recorded `TrainingSchedules` input by user
    * Implemented method to get a specific `TrainingSchedule` from ArrayList
    * Implemented method to delete a specific `TrainingSchedule` from ArrayList via index
    * Implemented method to find for `TrainingSchedule` using a String `name` keyword
    * Implemented method to update index of `TrainingList`. Called whenever a `TrainingSchedule` is deleted
  * Implemented `AddTraining` command; allows users to add a `TrainingSchedule` to `TrainingList`
    * Checks if entry to be added has a `TrainingName` that already exists. If it does, the command will not succeed
    * Checks that all fields are filled
  * Implemented `EditTraining` command; allows users to edit an existing `TrainingSchedule`
    * If user is editing `TrainingName`, check if the name to edit to already exists. If it does, the command will not succeed
  * Implemented `DeleteTraining` command; allows users to delete an existing `TrainingSchedule`
  * Implemented `FindTraining` command; allows users to filter and display `TrainingSchedules` based on `TrainingName`
  * Handled exceptions for the above commands
  * Set up file read/write operations, training schedule details are stored in `CCATrainings.csv`


## UG Contributions
[User Guide](https://ay2122s1-cs2113t-f12-4.github.io/tp/UserGuide.html)
  * Due to last minute changes to User Guide, most of my team member's code might be attributed to me in the above link. This leads to inaccuracy, and you should view my team members' Project Portfolio Pages to get a better overview of everyones' contribution to the User Guide and Developer Guide
  * Set up Table of Contents for User Guide
  * Set up `Training` section of User Guide
  * Set up `Pre-requisites` section of User Guide
  * Set up `FAQ` section of User Guide
  * Set up `Command Summary` section of User Guide
    * This section was then meticulously updated by other team members whenever changes were made



## DG Contributions
[Developers Guide](https://ay2122s1-cs2113t-f12-4.github.io/tp/DeveloperGuide.html)
* Set up Table of Contents for DeveloperGuide.md
* Set up skeletal framework for DeveloperGuide.md
* Set up `Architecture` section of DeveloperGuide.md
  * Designed architecture diagrams and architecture sequence diagrams for DeveloperGuide.md
* Set up `Training Component` section of DeveloperGuide.md
* Set up `Non-Functional Requirements` section
* Set up `Manual Testing` section