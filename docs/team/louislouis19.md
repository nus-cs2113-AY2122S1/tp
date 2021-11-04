# Louis - Project Portfolio Page

## Overview

**TermiNUS** is a CLI (command line interface) program for NUS students who wish to consolidate their NUS academic needs such as schedules, questions and notes for the modules that they are taking. With TermiNUS, it aims to aid students and improve their learning experiences while studying in NUS.

### Summary of Contributions

- New Feature: Implemented add, view, and delete functionalities in the Schedule workspace
  - What it does: Allows users to add, view, and delete schedules in the schedule workspace.
  
  - Justification: Users might want to keep track of their online meeting schedules, 
  view all their meeting schedules, and delete the meeting schedules that are no longer used.
  
  - Highlights: The `add` feature only allows valid links to be added. 
  The add feature will also be integrated with the conflict manager feature by default. 
  Moreover, the functionalities in the Schedule workspace are integrated with the Timetable feature.
  

- New Feature: Implemented the Timetable feature
  - What it does: Allows users to view their compiled and organized schedule in a timetable format.
  - Justification: Users might want to conveniently organize, compile, and view their daily or weekly schedule.
  - Highlights: Sorts all the schedules on a chronological order for user's ease of viewing.
  Timetable is automatically synced and integrated with the schedule workspace.
  

- New Feature: Implemented the Conflict Manager
  - What it does: Warns users for any conflicts in their schedules.
  - Justification: Users might want to be reminded or made aware whenever they have conflicting schedules.
  - Highlights: Gives rise to a warning message indicating the conflicting schedules whenever user adds a new conflicting schedule.
  The conflict manager checks for all user's schedule, and by default is integrated to the Add Link Command.
  

- Code Contributed: [Reposense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=LouisLouis19&tabRepo=AY2122S1-CS2113T-T10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


- Documentation:
  - User Guide:
    - Added documentation for CRUD functionalities of `schedule` and `note` workspace
    - Added documentation for the `timetable` feature
    
  - Developer Guide:
    - Added implementation details of `Timetable` command
    - Added implementation details of `ConflictManager`
    - Added instructions for manual testing

  
- Team-based Tasks:
  - Fix bug on providing better error messages: [#172](https://github.com/AY2122S1-CS2113T-T10-2/tp/issues/172), 
  [#175](https://github.com/AY2122S1-CS2113T-T10-2/tp/issues/175)
  - Add JUnit Tests to improve coverage: [#65](https://github.com/AY2122S1-CS2113T-T10-2/tp/issues/65)


- Beyond Project Team Tasks:
  - Conducted review and provide feedback for the User Guide and Developer Guide of the other teams.
  - Reported bugs and suggestions for other teams in the PE-D.