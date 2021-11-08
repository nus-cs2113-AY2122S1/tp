# User Guide

## Table of Content
- [1. Introduction](#1-introduction)
- [2. Quick Start Guide](#2-quick-start-guide)
  - [2.1 Installation](#21-installation)
  - [2.2 Using MedBot](#22-using-medbot)
- [3. Features](#3-features)
- [4. Commands](#4-commands)
  - [4.1 General Notes](#41-general-notes)
- [4.2 General Commands](#42-general-commands)
  - [4.2.1 Access user guide](#421-access-user-guide-help)
  - [4.2.2 Switch view](#422-switch-view-switch)
  - [4.2.3 Get Current View](#423-get-current-view-get-view)
  - [4.2.4 Exit Program](#424-exit-program-exit)
- [4.3 Patient/Medical Staff Management Commands](#43-patientmedical-staff-management-commands)
  - [4.3.1 Add a patient](#431-add-a-patient-add)
  - [4.3.2 Delete a patient](#432-delete-a-patient-delete)
  - [4.3.3 View a patient's information](#433-view-a-patients-information-view)
  - [4.3.4 List information of all patients](#434-list-information-of-all-patients-list)
  - [4.3.5 Edit a patient's information](#435-edit-a-patients-information-edit)
  - [4.3.6 Find patients](#436-find-patients-find)
  - [4.3.7 Hide a patient](#437-hide-a-patient-hide)
  - [4.3.8 Show a patient](#438-show-a-patient-show)
- [4.4 Scheduler Commands](#44-scheduler-commands)
  - [4.4.1 Add an appointment](#441-add-an-appointment-add)
  - [4.4.2 Delete an appointment](#442-delete-an-appointment-delete)
  - [4.4.3 View an appointment's information](#443-view-an-appointments-information-view)
  - [4.4.4 List information of all appointments](#444-list-information-of-all-appointments-list)
  - [4.4.5 Edit an appointment's information](#445-edit-an-appointments-information-edit)
  - [4.4.6 Find appointments](#446-find-appointments-find)
- [5 Future Implementations](#5-future-implementations-coming-in-v30)
  - [5.1 Table Paging](#51-table-paging)
  - [5.2 Patient's Medical Information](#52-patients-medical-information)
  - [5.3 View Patient/Staff Appointments](#53-view-patients-or-staffs-appointments)
  - [5.4 Different Identification Numbers](#54-different-identification-numbers)
  - [5.5 Different Phone Number Formats](#55-different-phone-number-formats)
  - [5.5 Find Patient/Staff For Hidden/Shown Persons](#56-find-patientstaff-command-to-distinguish-hiddenshown-persons)
- [6 FAQ](#6-faq)
- [7 Command Summary](#7-command-summary)
  - [7.1 General Commands](#71-general-commands)
  - [7.2 Patient/Medical Staff Management Commands](#72-patientmedical-staff-management-commands)
  - [7.3 Scheduler Commands](#73-scheduler-commands)

## 1. Introduction

MedBot is a Command Line Interface (CLI) application for head nurses to manage patients’ and medical staff's personal
information, and schedule appointments between them.

## 2. Quick Start Guide

### 2.1 Installation

1. Ensure that you are currently using `Java 11`. 
2. Download the latest version of `MedBot.jar` from [here](https://github.com/AY2122S1-CS2113-T13-1/tp/releases).
3. Move the file to the folder that you want to use as the MedBot's root folder.
4. Execute the `java -jar MedBot.jar` command in the terminal in the same folder as the `MedBot.jar` file to launch
   MedBot. The following output should be observed:
```
Hello, I'm MedBot!
How can I help you today?
```

### 2.2 Using MedBot

1. Type in commands into the terminal and press **Enter** to execute it.
2. Refer to the [Commands](#4-commands) below for details on each command.

## 3. Features

These are the main features of MedBot:

* Manage patient and medical staff information efficiently
* Schedule medical appointments for patients seamlessly

Each feature is contained inside a different view/tab in MedBot, which can be switched between using the `switch`
command, explained further down below.

## 4. Commands

### 4.1 General Notes

These notes apply to all commands that are supported by MedBot.
* Words in upper case are the parameters of the command.
  * e.g. in `add p/PATIENT_ID s/STAFF_ID d/DATE_TIME`, `PATIENT_ID`, `STAFF_ID` and `DATE_TIME` are parameters for the 
command
* Parameters not in square brackets are compulsory parameters.
  * e.g. in `delete PATIENT_ID`, a `PATIENT_ID` is required for this command.
* Parameters in square brackets are optional parameters.
  * e.g. in `help [COMMAND]`, the `COMMAND` is optional for this command.

## 4.2 General Commands

These commands will work the same for any view that you are currently in.

### 4.2.1 Access user guide: `help`

Provides an internal user guide that is accessible via the terminal.

Format: `help [COMMAND]`

Simply typing help without parameters will display a list of commands that can be used in the current view.

Expected Output when the program is in the Patient Management view:

```
Here is the list of commands:

help
add
delete
edit
view
list
find
hide
show
switch
get view
exit

To view more information about each command and their respective command formats, type:
help [COMMAND]

*Note that all commands will remove any '|' inputs for format parsing purposes.
For expected output examples, please refer to the User Guide.
```

### 4.2.2 Switch view: `switch`

Switches between the different views of MedBot.

Format: `switch [VIEW_TYPE]`

Here is the list of views and their corresponding `[VIEW_TYPE]` specifiers:

| View                     | Specifier | Alternate Specifier |
| :---                     | :---      | :---                |
| `Patient Management`       | `p`       | `1`                 |
| `Staff Management` | `m`       | `2`                 |
| `Scheduler`                | `s`       | `3`                 |

Using either specifier will switch to the corresponding view. I.e., `switch m` and `switch 2` will both switch to the 
Staff Management view.

If the switch command is called without any parameters, the view that is switched to will depend on the current view in 
the following order.

`Patient Management` --> `Staff Management` --> `Scheduler` --> `Patient Management`

Example: `switch p`

Expected Output:

```
  ___  _ _____ ___ ___ _  _ _____ 
 | _ \/_\_   _|_ _| __| \| |_   _|
 |  _/ _ \| |  | || _|| .` | | |  
 |_|/_/ \_\_|_|___|___|_|\_| |_|  
 |_ _| \| | __/ _ \               
  | || .` | _| (_) |              
 |___|_|\_|_|_\___/    __         
 \ \ / /_ _| __\ \    / /         
  \ V / | || _| \ \/\/ /          
   \_/ |___|___| \_/\_/           
                                  
You are now in the Patient Management view.
```

### 4.2.3 Get current view: `get view`

Gets the current view type of the program.

Format: `get view`

Expected Output when the program is in the Patient Management view:

```
You are currently in the Patient Management view.
```


### 4.2.4 Exit Program: `exit`

Exits the program.

Format: `exit`

Expected Output:

```
Thank you for using MedBot!
See you again!
```

## 4.3 Patient/Medical Staff Management Commands

Commands in the Patient Management view and Medical Staff Management view function similarly. Below are the descriptions
of the patient management commands that are used in the Patient Management view, but these commands would work the same 
way for staff management in the Medical Staff Management view.

### 4.3.1 Add a patient: `add`

Adds a patient with the given information to the patient manager. Upon successfully adding a patient, MedBot will 
generate a unique patient ID for reference and use in other commands.

Format: `add [i/PATIENT_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`

At least one parameter must be provided. Parameters can be entered in any order.

Example: `add i/S7812345X n/John Doe p/87654321 e/john.doe@gmail.com a/Blk 123 Bishan Street 7, #03-07`

Expected output:

```
Added patient with Patient ID: 1
IC: S7812345X
Name: John Doe
H/P: 87654321
Email: john.doe@gmail.com
Address: Blk 123 Bishan Street 7, #03-07
```

#### Notes on adding patients:

- The first letter of each word in `NAME` and `ADDRESS` will be capitalized, and the remaining letters will be set to 
lowercase.
- `PHONE_NUMBER` must be an 8-digit phone number. Spaces, dashes and underscore characters will be accepted but removed
- `EMAIL` must be in a valid email format
    - eg. tim_ong@example.com
- `PATIENT_IC` must be in a valid NRIC format:
    - The checksum of the NRIC will not be checked.
    - Alphabets will be auto-capitalized.
    - No two patients can have the same NRIC
- No parameters are made compulsory but at least one parameter must be given. The purpose is to allow for the addition 
of patients into MedBot in emergency cases where personal information is not yet available.

### 4.3.2 Delete a patient: `delete`

Deletes a patient from the patient manager.

Format: `delete PATIENT_ID`

Example: `delete 3`

Expected Output:

```
Patient with ID 3 deleted from system.
```

#### Notes:

* Deleting a patient would not affect the IDs of other patients
<br>Reasons :
  * ID uniquely identifies a patient and should not be changed.
  * ID does not indicate the row number in the patient list.
* Deleting a patient also deletes all appointments associated with the patient.

### 4.3.3 View a patient’s information: `view`

View a patient’s personal information.

Format: `view PATIENT_ID`

Example: `view 1`

Expected Output:

```
Here's the requested patient information:

Patient ID: 1
IC: S7812345X
Name: John Doe
H/P: 87654321
Email: john.doe@gmail.com
Address: Blk 123 Bishan Street 7, #03-07
```

### 4.3.4 List information of all patients: `list`

List the information of all patients in the patient manager.

Format: `list [-h]`

`list` will only show all not-hidden patients. To show hidden patients, use `list -h` instead.

Example Output:

```
Here is a list of all not-hidden patients:
For full details of each patient, please use the command "view PATIENT_ID"
 ----------------------------------------------------------------------------------------------------- 
 |  ID  | IC Number |         Name         | Phone No. |        Email         |       Address        | 
 ----------------------------------------------------------------------------------------------------- 
 | 1    | S7812345X | John Doe             | 87654321  | john.doe@gmail.com   | Blk 123 Bishan St... | 
 | 2    | S8765432G | Alice Tan            | 81234567  | alice123@yahoo.com   | Blk 519 Queenstow... | 
 | 3    | S9234567M | Muhd Faiz            | 91231234  | faiz123@gmail.com    | Blk 217 Ang Mo Ki... | 
 | 4    | S9412345R | Sarah Fernandez      | 81112222  |                      | 47 Lentor Crescent   | 
 ----------------------------------------------------------------------------------------------------- 
```

#### Notes:

* `Name`, `Email`, `Address` that are longer than 20 characters will be truncated to the first 17 characters with a 
`...` appended.
<br>Reason:
  * To maintain the table layout and prevent overflow of a cell.
* To get the full information of a particular patient without the truncated details, use `VIEW PATIENT_ID`.

### 4.3.5 Edit a patient's information: `edit`

Edit the personal information of a patient in the patient manager. 

Format: `edit PATIENT_ID [i/PATIENT_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`

At least one parameter must be provided. Parameters can be entered in any order. Only the parameters that are given will
be modified.

Example: `edit 1 p/87776666 e/john_doe@gmail.com`

Expected output:

```
The information of the patient with ID 1 has been edited to:

Patient ID: 1
IC: S7812345X
Name: John Doe
H/P: 87776666
Email: john_doe@gmail.com
Address: Blk 123 Bishan Street 7, #03-07
```

#### Notes: 

Refer to [notes on adding patients](#notes-on-adding-patients)

### 4.3.6 Find patients: `find`

Find all patients that contain the given attributes.

Format `find [i/PATIENT_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`

Example:
`find n/John`

Expected Output:

```
Here is a list of matched patients:
 ----------------------------------------------------------------------------------------------------- 
 |  ID  | IC Number |         Name         | Phone No. |        Email         |       Address        | 
 ----------------------------------------------------------------------------------------------------- 
 | 1    | S7812345X | John Doe             | 87654321  | john.doe@gmail.com   | Blk 123 Bishan St... | 
 ----------------------------------------------------------------------------------------------------- 
```

#### Notes:

* The attributes given do not have to be in full.
* At least one attribute must be given.
* Hidden patients will also be displayed.
* An empty value for a parameter will return rows with an empty value for the particular parameter
    * e.g. `find n/ e/` will show all patients with empty Name and Email.

### 4.3.7 Hide a patient: `hide`

Hides a patient in the list. Hidden patients are not shown with `list` command. To view all hidden patients,
use `list -h`.

This command only works for non-hidden patients.

Format: `hide PATIENT_ID`

Example: `hide 1`

Expected Output:

`The patient with ID 1 is now hidden.`

### 4.3.8 Show a patient: `show`

Show a patient in the list. Shown (not-hidden) patients are shown on `list`

This command only works for hidden patients.

Format: `show PATIENT_ID`

Example: `show 1`

Expected Output:

`The patient with ID 1 is now not hidden.`

## 4.4 Scheduler Commands

### 4.4.1 Add an appointment: `add`

Adds an appointment to the appointment manager. MedBot will check if the appointment clashes with others and display an 
error message if it does. Upon successfully adding an appointment, MedBot will generate a unique appointment ID for 
reference and use in other commands.

Format: `add p/PATIENT_ID s/STAFF_ID d/DATE_TIME`

The format for `DATE_TIME` is `DDMMYY hhmm`. I.e. 9 February 2021, 0800HRS should be written as `090221 0800`

Example: `add p/2 s/1 d/091121 1200`

Expected output:

```
Added appointment with Appointment ID: 1
Patient ID: 2
Staff ID: 1
Date/Time: 09 Nov 21 1200HRS
```
#### Notes:
- Appointments are managed on an hourly basis. 

  - e.g. any appointments set to any time between 0800HRS and 0859HRS will be treated as an appointment from 0800HRS to 
  0859HRS. Setting an appointment time at 0837HRS will have MedBot correcting the appointment time to 0800HRS, and it 
  will last until 0859HRS.
  - No subsequent appointment can then be scheduled for both the patient and the medical staff during that time frame.

### 4.4.2 Delete an appointment: `delete`

Delete an appointment from the appointment manager.

Format: `delete APPOINTMENT_ID`

Example: `delete 1`

Expected output:

```
Appointment with ID 1 deleted from system.
```

#### Notes:

* Deleting an appointment will not affect the appointment IDs of other appointments.
  <br>Reasons :
    * ID uniquely identifies an appointment and should not be changed.
    * ID does not indicate the row number in the appointment list.
* Deleting a patient/medical staff will also delete all appointments associated with them.

### 4.4.3 View an appointment's information: `view`

View the information of an appointment.

Format: `view APPOINTMENT_ID`

Example: `view 1`

Expected output:

```
Here is the requested appointment information:

Appointment ID: 1
Patient ID: 1
Staff ID: 1
Date/Time: 12 Nov 21 1200HRS
```

### 4.4.4 List information of all appointments: `list`

List the information of all appointments, including those of hidden patients.

Format: `list`

Example output:

```
Here is a list of all appointments:
 -------------------------------------------------------------------------------------------------- 
 |  ID  |     Date/Time     | Patient ID |     Patient Name     | Staff ID |      Staff Name      | 
 -------------------------------------------------------------------------------------------------- 
 | 1    | 12 Nov 21 1200HRS | 1          | John Doe             | 1        | Dr Tan               | 
 | 2    | 10 Nov 21 1100HRS | 1          | John Doe             | 1        | Dr Tan               | 
 | 3    | 19 Nov 21 1400HRS | 3          | Muhd Faiz            | 2        | Dr Lee               | 
 | 4    | 01 Dec 21 0900HRS | 2          | Alice Tan            | 2        | Dr Lee               | 
 -------------------------------------------------------------------------------------------------- 
```

#### Notes:

* `Patient Name` and `Staff Name` that are longer than 20 characters will be truncated to first 17 characters 
with a `...` appended.
  <br>Reason:
  * To maintain the table layout and prevent overflow of a cell.


### 4.4.5 Edit an appointment's information: `edit`

Edit an appointment's information. MedBot will check if the edited appointment clashes with others and display an error
message if it does.

Format: `edit APPOINTMENT_ID [p/PATIENT_ID] [s/STAFF_ID] [d/DATE_TIME]`

At least one parameter must be provided. Only the parameters that are given will be modified.

Example: `edit 1 d/121121 1200`

Expected output:

```
The information of appointment with ID 1 has been edited to:

Appointment ID: 1
Patient ID: 1
Staff ID: 1
Date/Time: 12 Nov 21 1200HRS
```

### 4.4.6 Find appointments: `find`

- Finds a person’s (patient or medical staff) list of appointments. 
- The search query can be filtered by date/time to display the list of appointments before/after a certain date 
involving the particular person.

Format: `find PERSON_TYPE/PERSON_ID [FILTER_TYPE/DATE_TIME]`
- The format for DATE_TIME is DDMMYY hhmm. I.e. 9 February 2021, 0800HRS should be written as 090221 0800
- `PERSON_TYPE` is p (patient) or s (staff)
- `FILTER_TYPE` is b (before) or a (after)

Example: `find p/6`

Example Output:
```
Here is a list of matched appointments:
 -------------------------------------------------------------------------------------------------- 
 |  ID  |     Date/Time     | Patient ID |     Patient Name     | Staff ID |      Staff Name        | 
 -------------------------------------------------------------------------------------------------- 
 | 6    | 04 Jan 22 1300HRS | 6          | Emma                 | 2        | Doctor Two           | 
 | 10   | 04 Jan 22 1800HRS | 6          | Emma                 | 2        | Doctor Two           | 
 | 11   | 05 Jan 22 1300HRS | 6          | Emma                 | 2        | Doctor Two           | 
 | 12   | 06 Jan 22 1300HRS | 6          | Emma                 | 2        | Doctor Two           | 
 | 13   | 07 Jan 22 1300HRS | 6          | Emma                 | 2        | Doctor Two           | 
 | 14   | 08 Jan 22 1300HRS | 6          | Emma                 | 2        | Doctor Two           | 
 -------------------------------------------------------------------------------------------------- 
```

Example: `find p/6 a/060122 0000`

Example Output
```
 -------------------------------------------------------------------------------------------------- 
 |  ID  |     Date/Time     | Patient ID |     Patient Name     | Staff ID  |      Staff Name       | 
 -------------------------------------------------------------------------------------------------- 
 | 12   | 06 Jan 22 1300HRS | 6          | Emma                 | 2        | Doctor Two           | 
 | 13   | 07 Jan 22 1300HRS | 6          | Emma                 | 2        | Doctor Two           | 
 | 14   | 08 Jan 22 1300HRS | 6          | Emma                 | 2        | Doctor Two           | 
 -------------------------------------------------------------------------------------------------- 
```

#### Notes:
- Before or after DATE_TIME also includes the appointment (if any) at the date/time specified.

## 5. Future Implementations [coming in v3.0]
### 5.1 Table paging
* A page in a table shown by `list` will be limited to only 50 records to prevent overly long lists.
* `list` will include paging through the inclusion of `next` and `prev` commands to navigate between
   different pages in the table.

### 5.2 Patient's medical information
* Patient's medical information will be added into MedBot so that medical staff can make the 
  necessary arrangements for their appointments (e.g. Patient X needs to take a blood test an hour before
  their appointment).

### 5.3 View appointments between specific patients and staff
* View all appointments scheduled for a particular patient and a particular staff in order to check for 
  recurring appointments.

### 5.4 Different identification numbers
* Accept other forms of identification for patients such as their passport number.

### 5.5 Different phone number formats
* Accept different phone number formats from different countries.

### 5.6 Find Patient/Staff command for hidden/shown persons
* Just like `list` and `list -h`, `find` will be used to filter among not-hidden persons
  and `find -h` to filter among hidden persons.

## 6. FAQ

**Q**: How do I transfer my data to another computer?

**A**: All data will be stored in the `MedBotData` directory that is created in the same working directory as `MedBot.jar`.
There are 3 text files in `MedBotData` - `patient.txt`, `staff.txt` and `appointment.txt`. Simply transfer
the `MedBotData` directory containing the three text files to the other computer, and run the `MedBot.jar` in the same new
location as the `MedBotData` directory.

## 7. Command Summary

### 7.1 General Commands

| Action       | Format + Examples |
| :---         | :-----            |
| **help**     | `help [COMMAND]` <br/>eg., `help add`|
| **switch**   | `switch [VIEW_TYPE]` <br/>eg., `switch s`|
| **get view** | `get view`|
| **exit**     | `exit`|

### 7.2 Patient/Medical Staff Management Commands

| Action       | Format + Examples        |
| :---         | :---                     |
| **add**      | `add i/PERSON_ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`<br/>eg.,`add i/S1231234A n/John Smith p/91234567 e/johnsmit@eg.com a/ABC Street 123`|
| **delete**   | `delete [PERSON_ID]`<br/>eg., `delete 2`|
| **view**     | `view [PERSON_ID]`<br/>eg., `view 3`|
| **list**     | `list [-h]`|
| **edit**     | `edit PERSON_ID [i/PATIENT_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]` <br/>eg., `edit 2 n/Bob Smith`|
| **find**     | `find [i/PERSON_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`<br/>eg.,`find i/s1231234A`|
| **hide**     | `hide PATIENT_ID`<br/>eg., `hide 1`|
| **show**     | `show PATIENT_ID`<br/>eg., `show 1`|

### 7.3 Scheduler Commands

| Action       | Format + Examples        |
| :---         | :---                     |
| **add**      | `add p/PERSON_ID s/STAFF_ID d/DATE_TIME` <br/> E.g., `add p/19 s/1 d/090222 0900`|
| **delete**   | `delete APPOINTMENT_ID`  |
| **view**     | `view APPOINTMENT_ID` <br/> E.g., `view 3` |
| **list**     | `list`                   |
| **edit**     | `edit APPOINTMENT_ID [p/PATIENT_ID] [s/STAFF_ID] [d/DATE_TIME]` <br/> E.g., `edit 2 s/3 d/100322 0800`|         
| **find**     | `find PERSON_TYPE/PERSON_ID [FILTER_TYPE/DATE_TIME]`<br/> E.g., `find s/3 b/100322 0800`|         




