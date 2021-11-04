# User Guide

## Introduction

MedBot is a Command Line Interface (CLI) application for head nurses to manage patients’ personal and medical
information. By utilising text-based commands instead of traditional Graphical User Interface (GUI) based navigation,
MedBot can allow head nurses to get their management tasks done quicker and more efficiently.

## Quick Start Guide

### Installation

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `tp.jar` from [here](https://github.com/AY2122S1-CS2113-T13-1/tp/releases).
3. Move the file to the folder that you want to use as the MedBot's root folder.
4. Execute the `java -jar tp.jar` command in the terminal in the same folder as the `tp.jar` file to launch
   MedBot. The following output should be observed:

### Using MedBot

1. Type in commands into the terminal and press **Enter** to execute it.
2. Refer to the [Commands](#commands) below for details on each command.

## Features

The main features of MedBot are:

* Manage patient and medical staff information efficiently
* Schedule medical appointments for patients seamlessly

Each feature is contained inside a different view/tab in MedBot, which can be switched between using the `switch`
command, which will be explained further down below.

## Commands

### General Commands

These commands will work the same for any view that you are currently in.

### Switching between views: `switch`

Switches between the different views of MedBot. If the switch command is called without any parameters, the view that is
switched to will depend on the current view in the order shown below.

(PATIENT_INFO --> MEDICAL_STAFF_INFO --> SCHEDULER --> PATIENT_INFO)

Format: `switch [VIEW_TYPE]`

Expected Output:

Inputting `switch p`:

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
                                  
View has been switched to PATIENT_INFO
```

Here is the list of views and their corresponding `[VIEW_TYPE]` specifiers:

| View               | Specifier | Alternate Specifier |
| :---               | :---      | :---                |
| Patient Info       | `p`       | `1`                 |
| Medical Staff Info | `m`       | `2`                 |
| Scheduler          | `s`       | `3`                 |

Using either specifier will switch to the corresponding view.

I.e., `switch m` and `switch 2` will both switch to the Medical Staff Info view

### Exit program: `exit`

Exits the program.

Format: `exit`

Expected Output:

```
Thank you for using MedBot!
See you again!
```

### Get the current view: `get view`

Gets the current view type of the program.

Format: `get view`

Expected Output (when the program is in Patient's View):

```
You are currently in the Patient's View.
```

### Accessing user guide: `help`

Provides an internal user guide that is accessible via the command line. 

Format: `help [COMMAND]`

Simply typing help without parameters will display a list of commands that
can be used in the current view.

Expected Output:

Inputting `help` without parameters in Patient's View:

```
Here are the list of commands:

help
add
list
view
edit
find
delete
switch
exit
help
show
get view

To obtain more information on each command and their respective required inputs, type:
help [COMMAND]

*Note that all commands will remove any '|' inputs for format parsing purposes
```

### Patient/Medical Staff Information Commands

Functionally, the commands for the patient information view and medical staff information view are the same. Here, we'll
be using
`PATIENT_INFO_VIEW`-specific examples, but these commands would apply exactly the same for the `STAFF_INFO_VIEW` as
well.

### Adding a patient: `add`

Adds a patient to the patient list.

Format: `add [i/PATIENT_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`

At least one parameter must be provided, they can be entered in any order.

Expected output:

```
Added patient with patient ID: PATIENT_ID
```

Examples:
`add i/S7812345X n/John Doe p/87654321 e/john.doe@gmail.com a/John Street, block 1234, #01-01`

#### Notes:

- `NAME` and `ADDRESS` will be auto-capitalized for every word from user input.
- `EMAIL` must be in a valid email format
    - eg. tim_ong@example.com
- `PATIENT_IC` must be in a valid NRIC format:
    - The checksum of the NRIC will not be checked.
    - Alphabets will be auto-capitalized. The input alphabets therefore non-case-sensitive.
- No fields are made compulsory. The purpose is to allow adding an urgent (e.g. car accident) patient
  into MedBot in case all information is not yet available, to allow scheduling an urgent appointment.

### Delete a patient: `delete`

Deletes a patient from the list.

Format: `delete PATIENT_ID`

Expected Output:

```
Patient with id PATIENT_ID deleted from system.
```

Examples:
`delete 123` deletes the patient with PATIENT_ID 123 in the list.

### View a patient’s information: `view`

View a patient’s personal information.

Format: `view PATIENT_ID`

Expected Output:

```
Here's the requested patient:

Patient ID: PATIENT_ID
IC: PATIENT_IC
Name: NAME
H/P: PHONE_NUMBER
Email: EMAIL
Address: ADDRESS
```

### List information of all current patients: `list`

List all patients in the patient list.

Format: `list [-h]`

`list` will only show all not-hidden patients. 
To show hidden patients, use `list -h` instead.

Example Output:

```
Here is a list of all patients:
For full details of each patient, please use the command "view PATIENT_ID"
 ----------------------------------------------------------------------------------------------------- 
 |  ID  | IC Number |         Name         | Phone No. |        Email         |       Address        | 
 ----------------------------------------------------------------------------------------------------- 
 | 1    | S2121211A | Alice                |           |                      |                      | 
 | 2    | S1212121B | Bob                  |           |                      |                      | 
 | 3    | S1234561C | Charlie              |           |                      |                      | 
 | 4    | S1231234A | John Smith           | 91234567  | johnsmit@eg.com      | Qweqwqwenoiqwenqw    | 
 ----------------------------------------------------------------------------------------------------- 
```

### Edit information of a patient: `edit`

Edit the personal and medical information of a patient in the list.

Format: `edit PATIENT_ID [i/PATIENT_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`

Expected output:

```
The information of patient with ID [PATIENT_ID] has been edited to:

Patient ID: PATIENT_ID
IC: PATIENT_IC
Name: NAME
H/P: PHONE_NUMBER
Email: EMAIL
Address: ADDRESS
```

Notes:
Refer to [Adding a patient - notes](#notes)

### Find patients based on attributes: `find`

Find all patients that contains the given attributes.

Format `find [i/PATIENT_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`

* The attributes given do not have to be in full.
* At least one attribute must be present.
* Hidden patients will also be displayed.

Example:
`find n/Smith`

Expected output:

```
Here is a list of all patients:
For full details of each patient, please use the command "view PATIENT_ID"
 ----------------------------------------------------------------------------------------------------- 
 |  ID  | IC Number |         Name         | Phone No. |        Email         |       Address        | 
 ----------------------------------------------------------------------------------------------------- 
 | 3    | S1231234A | John Smith           | 91234567  | johnsmit@eg.com      | Qweqwqwenoiqwenqw    | 
 ----------------------------------------------------------------------------------------------------- 
```

### Hide a patient: `hide`

Hides a patient in the list. Hidden patients are not shown with `list` command. To show all hidden patients,
use `list -h`.

This command only works for currently not-hidden patients.

Format: `hide PATIENT_ID`

Expected output:

`The patient with ID: 1 is now hidden.`

### Show a patient: `show`

Show a patient in the list. Shown (not-hidden) patients are shown on `list`

This command only works for currently hidden patients.

Format: `show PATIENT_ID`

Expected output:

`The patient with ID: 1 is now not hidden.`

### Scheduler Commands

### Adding an appointment: `add`

Adds an appointment to the list. MedBot will check if the appointment clashes with others and display an error message
if it does.

Format: `add p/PATIENT_ID s/STAFF_ID d/DATE_TIME`

The format for `DATE_TIME` is `DDMMYY hhmm`. I.e. 9 February 2021, 0800HRS should be written as `090221 0800`

Expected output:

```
Appointment added: Appointment Id: APPOINTMENT_ID Date/Time: DATE_TIME Patient ID: PATIENT_ID Staff ID: STAFF_ID
```

Do note that the appointments are managed at an hourly basis. For example, any appointments set to any time between
0800HRS and 0859HRS will be treated as an appointment from 0800HRS to 0859HRS. No subsequent appointment can then be
scheduled for either the patient and the medical staff during that window.

### Deleting an appointment: `delete`

Delete an appointment from the list.

Format: `delete APPOINTMENT_ID`

Expected output:

```
deleted appointment with Id: APPOINTMENT_ID
```

### Editing an appointment's information: `edit`

Edit an appointment's information. MedBot will check if the edited appointment clashes with others and display an error
message if it does.

Format: `edit APPOINTMENT_ID [p/PAITENT_ID] [s/STAFF_ID] [d/DATE_TIME]`

Expected output:

```
Appointment APPOINTMENT_ID changed to Appointment Id: APPOINTMENT_ID Date/Time: DATE_TIME Patient ID: PATIENT_ID Staff ID: STAFF_ID
```

### Viewing an appointment's information: `view`

View the information of an appointment.

Format: `view APPOINTMENT_ID`

Expected output:

```
Appointment Id: APPOINTMENT_ID Date/Time: DATE_TIME Patient ID: PATIENT_ID Staff ID: STAFF_ID
```

### Listing information of all appointments: `list`

List the information of all appointments, including those of hidden patients.

Format: `list`

Example output:

```
Here is a list of all appointments:
 -------------------------------------------------------------------------------------------------- 
 |  ID  |     Date/Time     | Patient ID |     Patient Name     | Staff ID |      Staff Name      | 
 -------------------------------------------------------------------------------------------------- 
 | 1    | 12 Nov 21 0900HRS | 1          | James Tan            | 2        | Dr Tay               | 
 | 2    | 10 Dec 21 1000HRS | 1          | James Tan            | 2        | Dr Tay               | 
 | 3    | 19 Nov 21 1400HRS | 2          | Eliot Ong            | 2        | Dr Tay               | 
 | 4    | 31 Oct 21 1200HRS | 3          | Daniel Chan          | 1        | Dr Lim               | 
 -------------------------------------------------------------------------------------------------- 
```


## FAQ

**Q**: How do I transfer my data to another computer?

**A**: All data will be stored in the `MedBotData` directory that is created in the same working directory as `medbot.jar`. There are 3
text files in `MedBotData` - `patient.txt`, `staff.txt` and `appointment.txt`. Simply transfer the `MedBotData` directory containing the
three text files to the other computer, and run the `medbot.jar` in the same new location as the `MedBotData` directory.


## Command Summary

### View Independent Commands

| Action       | Format + Examples |
| :---         | :-----            |
| **switch**   | `switch [VIEW_TYPE]` <br/>eg., `switch s`|
| **help**     | `help [COMMAND]` <br/>eg., `help add`|
| **get view**     | `get view`|
| **exit**     | `exit`|

### Patient/Medical Staff Information View

| Action       | Format + Examples        |
| :---         | :---                     |
| **add**      | `add i/PERSON_ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`<br/>eg.,`add i/S1231234A n/John Smith p/91234567 e/johnsmit@eg.com a/ABC Street 123`|
| **list**     | `list`|
| **edit**     | `edit PERSON_ID [i/PATIENT_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]` <br/>eg., `edit 2 n/Bob Smith`|
| **view**     | `view [PERSON_ID]`<br/>eg., `view 3`|
| **delete**   | `delete [PERSON_ID]`<br/>eg., `delete 2`|
| **find**     | `find [i/PERSON_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`<br/>eg.,`find i/s1231234A`|
| **hide**     | `hide PATIENT_ID`<br/>eg., `hide 1`|
| **show**     | `show PATIENT_ID`<br/>eg., `show 1`|

### Scheduler View

| Action       | Format + Examples        |
| :---         | :---                     |
| **add**      | `add p/PERSON_ID s/STAFF_ID d/DATE_TIME` <br/> E.g., `add p/19 s/1 d/090222 0900`|
| **delete**   | `delete APPOINTMENT_ID`  |
| **edit**     | `edit APPOINTMENT_ID [p/PAITENT_ID] [s/STAFF_ID] [d/DATE_TIME]` <br/> E.g., `edit 2 s/3 d/100322 0800`|         |
| **list**     | `list`                   |
| **view**     | `view APPOINTMENT_ID` <br/> E.g., `view 3` |


