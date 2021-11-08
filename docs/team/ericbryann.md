# Eric Bryan - Project Portfolio Page

## Overview

MedBot is a Command Line Interface (CLI) application for head nurses to manage patients’ personal information, and
scheduler appointments between them and medical staff.

### Summary of Contributions
* Code contributed: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=EricBryann&tabRepo=AY2122S1-CS2113-T13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
* New Features implemented:
  * `Ui` class (including `PersonUi`, `PatientUi`, `StaffUi` class)
    * What it does : interacts with users by reading user inputs and printing outputs.
    * Highlights :
      * Various OOP concepts are used. `Ui` is an abstraction over `PatientUi`,
        `StaffUi`, and `SchedulerUi`. `PatientUi` and `StaffUi` inherits from abstract
        `PersonUi` class.
      * Printing user-friendly outputs including the design of a table that will truncate
        long string to fit the table cell.
  * `find` command
    * What it does : filter through a list based on inputted parameters.
    * Justification : This feature mimics a search bar feature in a GUI application to address the
      project requirement of a user who likes to type.
    * Highlights :
      * Consider edge case when empty parameters are given, it will give records with empty
        values.
  * `hide`/`show` commands
    * What it does : soft deleting a person when no longer relevant (e.g. discharged).
    * Justification : While `delete` will completely remove the data, these commands will not
      remove the data, to retain past records.
    * Highlights :
      * Consider its interaction with `list` command to show not-hidden persons, and
        `list -h` to show hidden persons, ensuring the existing code does not break.
* Documentations:
  * UG:
    * Added information for `find`, `hide`, and `show` commands.
    * Added additional notes for some commands under `Notes` and `General Notes` for possible edge cases or
      additional useful information.
  * DG:
    * Added implementation details for `Ui` and `find`
    * Added a sequence diagram for `Ui`
* Contributions to team task:
  * Occasional refactoring of codes to follow more OOP principles. [#30](https://github.com/AY2122S1-CS2113-T13-1/tp/pull/30), [#92](https://github.com/AY2122S1-CS2113-T13-1/tp/pull/92), [#163](https://github.com/AY2122S1-CS2113-T13-1/tp/pull/163)
  * Assigned milestone `v1.0` due dates.