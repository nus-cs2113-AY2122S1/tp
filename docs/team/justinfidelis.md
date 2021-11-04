# Justin Wong - Project Portfolio Page

## Overview

MedBot is a Command Line Interface (CLI) application for head nurses to manage patients’ personal information, and
schedule appointments between them and medical staff.

### Summary of Contributions

* Code Contributed: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=justinfidelis&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=justinfidelis&tabRepo=AY2122S1-CS2113-T13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
* Enhancements Implemented:
  * `Parser` class, including `ParserUtils`, `PatientCommandParser`, `StaffCommandParser` and `SchedulerCommandParser`
    * 
  * Appointment management functionality, including relevant `Scheduler` class methods, `Appointment` class and `SchedulerAppointmentList` class
* User Guide Contribution
  * Added the Quick Start Guide
  * Added information about the scheduler commands, namely `add`, `delete`, `edit`, `view` and `list`
* Developer Guide Contribution
  * Added __Main Components__ and __Component Interaction__ sections (including __Component Interaction__ sequence diagram but excluding architecture diagram)
which provided an overview of MedBot's architecture
  * Added __Scheduler Component__ section which included information about the `Scheduler` class and how it works
  * Added __Appointment Management__ section which details the appointment management functionality of MedBot, its implementation details
and the reasons motivating the implementation decisions
* Contribution of Team-Based Tasks
  * Setting up of GitHub team org/repo
  * Occasional refactoring of code to reduce coupling, improve encapsulation and reduce redundancies
  * Occasional addition of JavaDoc to improve documentation