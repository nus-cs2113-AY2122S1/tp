# Justin Wong - Project Portfolio Page

## Overview

MedBot is a Command Line Interface (CLI) application for head nurses to manage patients’ personal information, and
schedule appointments between them and medical staff.

### Summary of Contributions

* Code Contributed: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=justinfidelis&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=justinfidelis&tabRepo=AY2122S1-CS2113-T13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
* Features Implemented:
  * `Parser` class, including `ParserUtils`, `PatientCommandParser`, `StaffCommandParser` and `SchedulerCommandParser`
    * The implementation of the `Parser` methods was somewhat challenging as I had to find ways to accommodate inputs
      that were not in a fixed order (e.g. add patient/appointment commands)
    * It was also challenging to find a balance between accepting small user mistakes/typos while minimising unexpected
      behaviour (e.g. capitalising the first letters of names, checking email/NRIC format)
  * Appointment management functionality, including relevant `Scheduler` class methods, `Appointment` class and 
    `SchedulerAppointmentList` class
    * The implementation of the appointment management functionality was challenging due to the constraint of not being
      able to use a RDMS
    * Although a single ArrayList will be sufficient in storing all appointments, it would result in very poor time
      complexity when searching for appointments that belonged to a particular patient/staff
    * Therefore, the patient management functionality was implemented using a list that stored all appointments and
      individual lists in each patient and staff object.
    * However, it became more challenging to ensure that the records were consistent across all lists and
      that adding, editing and deleting appointments was atomic and did not lead to modifications in some but not all
      relevant lists.
* User Guide Contribution
  * Added the Quick Start Guide
  * Added information about the scheduler commands, namely `add`, `delete`, `edit`, `view` and `list`
* Developer Guide Contribution
  * Added __3.1.1 Main Components__ and __3.1.2 Component Interaction__ sections (including __Component Interaction__ 
    sequence diagram but excluding architecture diagram) which provides an overview of MedBot's architecture
  * Added __3.4 Scheduler Component__ section (and the corresponding class diagram) which includes information about the
    `Scheduler` class and how it works
  * Added __4.4 Appointment Management__ section (and the corresponding sequence diagram) which details the appointment 
    management functionality of MedBot, its implementation details and the reasons motivating the implementation decisions
* Contribution of Team-Based Tasks
  * Setting up of GitHub team org/repo
  * Occasional refactoring of code to reduce coupling, improve encapsulation and reduce redundancies
  * Occasional addition of JavaDoc to improve documentation
  * Cleaning up of UG and DG for more consistent formatting