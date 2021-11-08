# Hoang Trong Tan - Project Portfolio Page

## Overview

MedBot is a Command Line Interface (CLI) application for head nurses to manage patients’ personal and medical
information. By utilising text-based commands instead of traditional Graphical User Interface (GUI)
based navigation, MedBot can allow head nurses to get their management tasks done quicker and more efficiently.

Given below are my contributions to the project.

### Summary of Contributions

* **Code
  Contributed:** [Reposense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=jushg&tabRepo=AY2122S1-CS2113-T13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
* **New Feature:**
    * Implement two different role for person: patients and staffs.
        * What it does: Allows a person's record to be added as a patient or as a staff.
        * Justification: Facilitate the development of the `Scheduler` class which rely on the existent of two separate
          lists.

    * Added the ability to modify existing person record via the `edit` command.
        * What it does: Allows the user to modify existing record of the program.
        * Justification: It can help the user to save time instead of remove and re-add the record.
        * Highlights: Users can remove existing information by indicating a blank field when entering the command.

    * Added a way to get the current views via the `get view` command.
        * What it does: Allows the user to know if they are in `PATIENT_INFO`, `STAFF_INFO`, or `SCHEDULER`
          views.
        * Justification: Help the user to easily identify which part of the system they are in.


* **Enhancements Implemented :**
    * `Command` class, including `PersonCommand`, `GeneralCommand`, and `StaffCommand`.


* **User Guide Contribution**
  * Added information for `get view` command
  * Formatted some information for basic commands for Patient/Staff Info view (`add`, `delete`, `edit`, `exit`)
  * Revamped some the expected outputs for the various commands after `Ui` class was refactored


* **Documentation Guide Contribution**
    * Added Command Class Component which includes implementation details about the `Command` class.
    * Added the overall architecture details of the program.


* **Contribution to Team-Based Tasks**
    * Assigned milestone `v2.0` and `v2.1` due dates.
    * Occasional refactoring of code to align towards OOP and reduce coupling
    * Write test cases for `Ui`, `Parser` and `PersonList` classes.
