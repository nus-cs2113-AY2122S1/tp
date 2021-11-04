# Kureans  - Project Portfolio Page

## Overview

MedBot is a Command Line Interface (CLI) application for head nurses to manage patients’ personal and 
medical information. By utilising text-based commands instead of traditional Graphical User Interface (GUI) 
based navigation, MedBot can allow head nurses to get their management tasks done quicker and more efficiently.
It is written in Java, and has about 8kLoC.

Given below are my contributions to the project.

### Summary of Contributions

* **Code Contributed:** [Reposense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=kureans)
* **New Feature:** Added the ability to filter for personal appointments by date/time.
    * What it does: Allows the user to find appointments before or after a
      certain date/time for a specific patient or medical staff.
    * Justification: This allows head nurses to quickly check which time slots are taken
      up for a specific range of dates, which helps them plan out how they should schedule a
      given appointment. 
    * Highlights: This feature required an alternative implementation in terms of the data structure
      compared to the main list of appointments in the `Scheduler` view, in order to sort appointments
      in `log(n)` time. Smart modification of the `SortedSet` comparator help to reduce code length.
  
    
* **New Feature:** Added an internal user guide via the `help` command.
  * What it does: Allows the user to get information about the various commands in MedBot internally. 
  * Justification: It can help the user to save time if they forgot the syntax of the commands, but they are
                   already familiar enough with the application that they don't need the full user guide.
  * Highlights: Users can obtain further information for each command by inputting `help [COMMAND]` instead of
                just `help`. 
  

* **New Feature:** Added a way to switch views via the `switch` command.
  * What it does: Allows the user to switch between `PATIENT_INFO`, `STAFF_INFO`, AND `SCHEDULER`
                    views.
  * Justification: Helps to provide a cleaner 'ui' since only related commands can be executed in their respective
                   views, which can help make the workflow of the user smoother.


* **User Guide Contribution**
  * Added information for basic commands for Patient/Staff Info view (`add`, `delete`, `edit`, `help`, `exit`, `list`)
  * Revamped all the expected outputs for the various commands after `Ui` class was refactored


* **Documentation Guide Contribution**
  * Added Parser Component which includes implementation details about the `Parser` class. 


* **Contribution to Team-Based Tasks**
  * Assigned milestone `v2.0` and `v2.1` due dates 
  * Occasional refactoring of code to align towards OOP
