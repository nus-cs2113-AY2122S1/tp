# CrimsonTitan79 - Project Portfolio Page

## Overview

MedBot is a Command Line Interface (CLI) application for head nurses to manage the personal information of patients and
medical staff and to schedule appointments between the 2 parties. By utilising text-based commands instead of Graphical
User Interface (GUI)
based navigation, MedBot allows head nurses to complete their management tasks done quicker and more efficiently.

### Summary of Contributions
* Code
  contributed: [RepoSense link](https://nus--ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=CrimsonTitan79&tabRepo=AY2122S1-CS2113-T13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
* Enhancements implemented
    * `Storage` class (including `AppointmentStorage`, `PersonStorage`, `PatientStorage`, `StaffStorage` classes).
        * Object-Oriented Programming (OOP) concepts used: `AppointmentStorage` and `PersonStorage` inherit
          from `Storage`, and
          `PatientStorage` and `StaffStorage` inherit from `PersonStorage`.
        * These classes give MedBot the ability to persistently store and retrieve data that the user enters into the
          program, and even allows for porting of the data to other computers.
    * `StorageManager` class which consolidates the functionality of the aforementioned `Storage` classes.

* Documentations:
    * User Guide Contribution
      * Update User Guide with the latest features and expected outputs after changes in MedBot code
        * Added find appointments section 
      * Explanation for how persistent data is stored for MedBot.
      * Edits to refine certain explanations and certain edge cases
      * Added table of contents for easy navigation
    * Developer Guide Contribution
      * Section on instructions for manual testing
      * Implementation details for Storage component.
      * Sequence diagram for `Storage`.

* Review/mentoring contributions
    * Fix bugs from other components when they arise. (e.g. erroneous UI print messages)
    * Occasional PR reviews: [link](https://github.com/AY2122S1-CS2113-T13-1/tp/pull/46).

* Team-based tasks
    * Flagged out several bugs prior to submission, fixed some of them.
