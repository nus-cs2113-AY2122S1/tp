# User Guide

## Introduction

MedBot is a Command Line Interface (CLI) application for head nurses to manage 
patients’ personal and medical information. By utilising text-based commands 
instead of traditional Graphical User Interface (GUI) based navigation, MedBot 
can allow head nurses to get their management tasks done quicker and more efficiently.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

The main features of MedBot are:

  * Manage patient and medical staff information efficiently
  * Schedule medical appointments for patients seamlessly

Each feature is contained inside a different view/tab in MedBot, which can be
switched between using the `switch` command, which will be explained further
down below.

## Commands

### View Independent Commands

These commands will work the same for any view that you are currently in.

### Switching between views: `switch`

Switches between the different views of MedBot.
If the switch command is called without any parameters,
the view that is switched to will depend on the current view
in the order shown below.

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


### Exit program: `exit`

Exits the program.

Format: `exit`

Expected Output:
```
Thank you for using MedBot!
See you again!
```

### Accessing user guide: `help`

Provides an internal user guide that is accessible via the command line.

Format: `help [COMMAND]`

Expected Output: 

Inputting `help` without parameters:
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
archive
unarchive

To obtain more information on each command and their respective required inputs, type:
help [COMMAND]

*Note that all commands will remove any '|' inputs for format parsing purposes
```

### Patient / Medical Staff Information

Functionally, the commands for the patient information view and
medical staff information view are the same. Here, we'll be using
`PATIENT_INFO_VIEW`-specific examples, but these commands would apply exactly
the same for the `STAFF_INFO_VIEW` as well.

### Adding a patient: `add`

Adds a patient to the patient list.

Format: `add i/PATIENT_ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`

Expected output:
```
Patient with the following information has been successfully added to the list:

IC: PATIENT_IC
Name: NAME
H/P: PHONE_NUMBER
Email: EMAIL
Address: ADDRESS
```

Examples:
`add i/1234 n/”John Doe” p/87654321 e/john.doe@gmail.com a/”John Street, block 1234, #01-01”`

### Delete a patient: `delete`

Deletes a patient from the list.

Format: `delete PATIENT_ID`

Expected Output:
```
Patient with the following information has been successfully deleted from the list:

Patient ID: PATIENT_ID
IC: PATIENT_IC
Name: NAME
H/P: PHONE_NUMBER
Email: EMAIL
Address: ADDRESS
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

`list` will show all unarchived patients.

To show archived patients, use `list -ar`

Format: list

Example Output:
```
Here is a list of all patients:
For full details of each patient, please use the command "view PATIENT_ID"
 ----------------------------------------------------------------------------------------------------- 
 |  ID  | IC Number |         Name         | Phone No. |        Email         |       Address        | 
 ----------------------------------------------------------------------------------------------------- 
 | 0    | S2121211A | Alice                |           |                      |                      | 
 | 1    | S1212121B | Bob                  |           |                      |                      | 
 | 2    | S1234561C | Charlie              |           |                      |                      | 
 | 3    | S1231234A | John Smith           | 91234567  | johnsmit@eg.com      | Qweqwqwenoiqwenqw    | 
 ----------------------------------------------------------------------------------------------------- 
```

### Edit information of a patient: `edit`

Edit the personal and medical information of a patient in the list.

Format: `edit PATIENT ID [i/PATIENT_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`

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

### Find patients based on attributes: `find`

Find all patients that contains the given attributes.

Format `find [i/PATIENT_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`
* The attributes given do not have to be in full.
* At least one attribute must be present.

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

### Archive a patient: `archive`

Archive a patient in the list. Archived patients are not shown with `list` command.
To show all archived patients, use `list -ar`.

This command only works for currently unarchived patients.

Format: `archive PATIENT_ID`

Expected output:

`The patient with ID: 1 is archived successfully.`

### Unarchive a patient: `unarchive`

Unarchive a patient in the list. Unarchived patients are shown on `list`

This command only works for currently archived patients.

Format: `unarchive PATIENT_ID`

Expected output:

`The patient with ID: 1 is unarchived successfully.`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

### View Independent Commands

| Action       | Format + Examples |
| :---         | :-----            |
| **switch**   | `switch [VIEW_TYPE]` <br/>eg., `switch s`|
| **help**     | `help [COMMAND]` <br/>eg., `help add`|
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
| **archive**  | `archive PATIENT_ID`<br/>eg., `archive 1`|
| **unarchive**| `unarchive PATIENT_ID`<br/>eg., `unarchive 1`|

### Scheduler View

| Action       | Format + Examples        |
| :---         | :---                     |


