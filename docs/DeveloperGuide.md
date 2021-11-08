# Developer Guide

## Table of Contents

- [1. Acknowledgements](#1-acknowledgements)
- [2. Introduction](#2-introduction)
- [3. Design](#3-design)
    - [3.1 Architecture](#31-architecture)
        - [3.1.1 Main Components](#311-main-components)
        - [3.1.2 Component Interaction](#312-component-interaction)
    - [3.2 Ui Component](#32-ui-component)
    - [3.3 Parser Component](#33-parser-component)
    - [3.4 Scheduler Component](#34-scheduler-component)
    - [3.5 Storage Component](#35-storage-component)
    - [3.6 Command Class](#36-command-class)
- [4. Implementation](#4-implementation)
    - [4.1 Switch View Feature](#41-switch-view-feature)
    - [4.2 Find Feature](#42-find-feature)
    - [4.3 Edit Feature](#43-edit-feature)
    - [4.4 Appointment Management](#44-appointment-management)
- [Appendix A: Product Scope](#appendix-a-product-scope)
- [Appendix B: User Stories](#appendix-b-user-stories)
- [Appendix C: Non-Functional Requirements](#appendix-c-non-functional-requirements)
- [Appendix D: Instructions for Manual Testing](#appendix-d-instructions-for-manual-testing)

## 1. Acknowledgements

* Referenced the [SE-EDU AB3 Developer Guide](https://se-education.org/addressbook-level3/DeveloperGuide.html)

## 2. Introduction

MedBot is a Command Line Interface (CLI) application for head nurses to manage patients’ and medical staff's personal 
information, and schedule appointments between them.

## 3. Design

### 3.1 Architecture

Given below is a quick overview of the main components of MedBot and how they interact with one another.

#### 3.1.1 Main Components

![MedBot Architecture Diagram](diagrams/ArchitectureDiagram.png)

The main class of MedBot is the `MedBot` class. It is responsible for initialising the other core components of MedBot
at application startup and for handling the interactions between these components.

The 4 core components of MedBot are:

* `Ui`: Handles the UI of MedBot.
* `Parser`: Parses user inputs and creates `Command` objects.
* `Scheduler`: Holds data in memory and contains the methods to read and write to it.
* `Storage`: Loads data from, and stores data to the hard disk.

In addition, the `Command` class facilitates the execution of user instructions.

#### 3.1.2 Component Interaction

Given below is a simplified sequence diagram of how the core components of MedBot interact with each other when the user
inputs the command `delete 1`.

![MedBot Architecture](diagrams/overallSequenceDiagram.png)

### 3.2 Ui Component

The Ui Component is responsible for reading user inputs and printing outputs to users.

#### Ui Class

The Ui Component is handled by the `Ui` class. It handles the reading of user inputs and the printing of outputs. 

It generates output messages through relying on the following classes.

* `PersonUi`: Generates output messages for patient- and staff-management commands.
    * `PatientUi`: Inherits `PersonUi` to generate output messages for patient-management commands.
    * `StaffUi`: Inherits `PersonUi` to generate output messages for staff-management commands.
* `SchedulerUi`: Generates output messages for scheduler commands.

#### How the Ui component works

##### Reading user inputs

When the user inputs a line of text into the terminal, terminated with a newline character, the line will be read by the
`readInput()` method of the `Ui` class which will return the String to be parsed by the Parser Component

##### Printing outputs

* After user input is parsed by `Parser`, depending on the current `viewType`, the `Ui` will call methods from
  different `Ui` subclasses
    * When `viewType` is `PATIENT_INFO`, `PatientUi` methods are called.
    * When `viewType` is `MEDICAL_STAFF_INFO`, `StaffUi` methods are called.
    * When `viewType` is `SCHEDULER`, `SchedulerUi` methods are called.

Given below is a sequence diagram of how the `Ui` component works after the `Parser`
parses `help delete` input given by a user.

![Ui Sequence Diagram](diagrams/UiSequenceDiagram.png)

### 3.3 Parser Component

The Parser Component is responsible for parsing the user input and returning the corresponding Command class object to
be executed.

Given below is a partial class diagram to better illustrate the Parser Component:

![ParserClassDiagram](diagrams/ParserClassDiagram.png)

#### How the Parser component works:

* After getting the user input, MedBot calls the `parseCommand(userInput)` method of the `Parser` class to parse the
  input.
* Depending on the current MedBot view, the `Parser` class then calls the `ParseXYZCommand()` method of the view 
  specific parser `XYZCommandParser`  (`XYZ` is a placeholder for the current view, namely `Patient`, `Staff` and 
  `Scheduler`).
* The `XYZCommandParser` then determines the type of command that the user input corresponds to and calls the 
  corresponding `parseABCCommand()` method which creates and returns the corresponding `Command` object (`ABC` is a 
  placeholder for the command type, e.g. `AddPatient`, `DeleteStaff`, `ListAppointment`).
* The methods in `ParserUtils` are used by the `Parser` and `XYZCommandParser` classes to process some parts of the user
  input.

The sequence diagram below better illustrates the working process described above:

![ParserSequenceDiagram](diagrams/ParserSequenceDiagram.png)

<em>(User is trying to add a patient's information in the PatientInfoView)</em>

### 3.4 Scheduler Component

The Scheduler Component is responsible for the storage and modification of patient, staff and appointment information.

Here is a partial class diagram to better illustrate the Scheduler Component.

![SchedulerClassDiagram](diagrams/SchedulerClassDiagram.png)

#### Scheduler Class

The `Scheduler` class consists of 3 internal lists, `patientList`, `medicalStaffList` and `schedulerAppointmentList`,
that store patient, staff and appointment information respectively. It has various public methods for the viewing and
modification of the information stored in the lists, and for the interfacing between the Scheduler and Storage
Components. A `Scheduler` class object is instantiated upon MedBot startup.

#### How the Scheduler component works:

* When MedBot calls the `.execute(Scheduler, Ui)` method of a `Command` object, a corresponding method of the
  `Scheduler` object will be called.
* This method will then view or modify the patient, staff or appointment information depending on the command.

For example:

* When the `.execute(Scheduler, Ui)` method of an `AddStaffCommand` object is called, the `addStaff(Person)` method of
  the `Scheduler` object will be called.
* The `addStaff(Person)` method will then add the `Person` to the `medicalStaffList`

### 3.5 Storage Component

The Storage Component is responsible for the persistent storage of the personal information of all patients and 
medical staff, and appointment details between the two parties. 

Here is a partial class diagram to better illustrate the Storage Component:

![SchedulerClassDiagram](diagrams/StorageClassDiagram.png)

#### StorageManager Class

The `StorageManager` class performs the responsibilities of the Storage Component. It consists of a `PatientStorage`, a 
`StaffStorage` and an `AppointmentStorage` class object. The `PatientStorage`, `StaffStorage` and 
`AppointmentStorage` classes are subclasses of the `Storage` class and are responsible for the storage of patient,
staff and appointment information respectively.

#### How the Storage component works:

##### Initialization

* When MedBot is launched, `MedBot` initializes a `StorageManager` class object.
* `MedBot` then calls the `initializeStorages()` method of the `StorageManager` class object which initializes the
  `PatientStorage`, `StaffStorage` and `AppointmentStorage` class objects.
* These three `Storage` sub-classes will create a `MedBotData` directory in the current working directory if 
`MedBotData` does not already exist.
* Each of the three `Storage` classes will create their respective storage text files in `MedBotData` if they do not 
exist. 
    * `PatientStorage` will create `patient.txt` in `MedBotData
    * `StaffStorage` will create `staff.txt` in `MedBotData
    * `AppointmentStorage` will create `appointment.txt` in `MedBotData
* If the storage files exist, the respective `Storage` sub-class will ignore it.

##### Loading data:

* `StorageManager` calls the `loadStorage()` method of the `PatientStorage`, `StaffStorage` and 
 `AppointmentStorage`.
* Each of the 3 `Storage` sub-class objects then reads their corresponding storage files and stores the information in 
memory into the corresponding lists of `MedBot`.
* If there are errors in any of the storage files, an error message will be output to inform the user of the specific
line and file which has erroneous data

##### Saving data:

* After a command is executed, `MedBot` calls the `saveToStorage()` method of the `StorageManager` class object.
* `StorageManager` then calls the `saveData()` method of the `PatientStorage`, `StaffStorage` and
  `AppointmentStorage` objects.
* Each of the 3 `Storage` sub-class objects then writes the storage data into their respective data text files.


##### Format of stored data for patients and medical staff

The storage files `patient.txt` and `staff.txt` follow the same storage format when storing data. Each line of these
two storage files corresponds to the personal information of a patient or medical staff respectively:

Format:
```
ID | NRIC | NAME | PHONE_NUMBER | EMAIL | ADDRESS | HIDE_STATUS
```

Example:
```
3 | S8367812K | Sasha Alexander | 91238765 | X | Mauville City 2nd Street | S
```

Notes:
* `X`  denotes that the field is empty
* `HIDE_STATUS` refers to whether the patient/staff is to be shown or hidden
  * `S` means to "show"
  * `H` means to "hide"

##### Format of stored data for appointments

Each line of `appointment.txt` corresponds to the details of an appointment.

Format:
```
ID | DATE_TIME | PATIENT_ID | STAFF_ID
```

Example:
```
7 | 051221 0900 | 2 | 2
```


### 3.6 Command Class

The `Command` class and its subclasses are responsible for handling the execution of user instructions.

Each `Command` subclass contains:

* An `isExit()` method which return true only if it is an `Exit Command`.
* An `execute(Scheduler, Ui)` method which performs the specified user instruction and prints the output message to the 
  user.

Some `Command` subclasses also contain attributes specific to that subclass.
* E.g., the `deletePatientCommand` contains a `personId` attribute that specifies the ID of the patient that is to
  be deleted from MedBot.

There are 3 categories of commands:

1. General Commands for MedBot navigation and accessing help.
2. Person Commands which are divided into:
   1. Patient Commands for patient management in the Patient Management view.
   2. Staff Commands for staff management in the Staff Management view.
3. Appointment Commands for appointment management in the Scheduler view.

Given below is the complete list of commands and their categories:

|Command Name| Type |
|--------|---|
|Help Command|General|
|Switch Command|General|
|Get View Command|General|
|Exit Command|General|
|Add Person Command|Person|
|Delete Person Command|Person|
|View Person Command|Person|
|List Person Command|Person|
|Edit Person Command|Person|
|Find Person Command|Person|
|Hide Person Command|Person|
|Show Person Command|Person|
|Add Appointment Command|Appointment|
|Delete Appointment Command|Appointment|
|View Appointment Command|Appointment|
|List Appointment Command|Appointment|
|Edit Appointment Command|Appointment|
|Find Appointment Command|Appointment|

## 4. Implementation

This section describes some noteworthy details on how certain features are implemented.

### 4.1 Switch view feature

#### Implementation

The switch view mechanism is heavily linked to the `Parser` class. By having a
`ViewType` enumeration property in `Parser`, the view of the console can be switched by executing the
appropriate `SwitchCommand` class, which modifies the corresponding `ViewType`
of the `Parser`. The 3 possible views and the corresponding user input commands are as follows:

* `switch p` or `switch 1` - switches to the `Patient Management` view.
* `switch m` or `switch 2` - switches to the `Staff Management` view.
* `switch s` or `switch 3` - switches to the `Scheduler` view.
* `switch` - will switch to another view depending on the current view in the order shown below:

`Patient Management` --> `Staff Management` --> `Scheduler` --> `Patient Management`


Each command calls the `Parser.setViewType(ViewType)` method, which will set the corresponding
`ViewType` property in the `Parser` class. Additionally, the `Ui.clearConsoleFromIde()` method will be evoked, which
performs a pseudo clear of the console before printing a message that indicates the user has switched view.

#### Design Considerations:

**Aspect: How console is cleared:**

* Alternative 1(Current Choice)
    * Perform a pseudo-clear using multiple `System.lineSeparator()` to move the current console outputs upwards.
        * Pros: Easy to implement, system independent
        * Cons: Only works for terminals up to a certain size (bigger terminals can still see previous console outputs)
    * Perform an actual clear using the `ProcessBuilder` / `Runtime` class in java.
        * Pros: Size of terminal doesn't matter since it actually clears the console.
        * Cons: More complex implementation, need to consider different sets of implementation for different OSes, does
          not work on IDE.

### 4.2 Find feature

#### Functionality

This command will find a list of `Person` that match the given attributes in a table format.

#### Implementation

The `find` feature is facilitated by the `FindPersonCommand` class. It extends from `Command` class and overrides
the `execute()` method to achieve the desired functionality.

The example below gives a direction on how this command behaves.


1. User executes the `find n/John` command. The `Parser#parseCommand()` method will parse this command and eventually
returns a `new FindPatientCommand()` object.


2. The `MedBot#interactWithUser()` method will run the `execute()` method in the `new FindPatientCommand()` object.


3. The `execute()` method will call `PersonList#findPersons()` method with the parameter `n/John` passed in.


4. `PersonList#findPersons()` will check all the `persons` list and returns all `Person` in the list whose name contains
the string `john`. The attribute match is case-insensitive.


5. The filtered `Person` list is then passed into the `Ui` class to be displayed into a table format through
`Ui#getFindPatientsMessage()`.


### 4.3 Edit feature

#### Functionality

This command will edit a specified `Person` object with the attributes given in the command.

#### Implementation

The `edit` feature is facilitated by the `EditPersonCommand` class. It extends from `Command` class and overrides
the `execute()` method to achieve the desired functionality.

The example below gives a direction on how this command behaves.


1. User executes the `edit n/John` command when the attribute `Parser#viewType` is `PATIENT_INFO`.
The `Parser#parseCommand()` method will parse this command and eventually returns a `new EditPatientCommand()` object.


2. The `MedBot#interactWithUser()` method will run the `execute()` method in the `new EditPatientCommand()` object.


3. The `execute()` method will call `PersonList#editPerson()` method with the new `Person` object having the parameter
`n/John` passed in. (All other attributes of the object are set to `null`)


4. `PersonList#editPerson()` will attempt to replace all attributes of the old `Person`
object with the non-null attributes given in the new `Person`.


5. The edited `Person` is then passed into the `Ui` class to be displayed through`Ui#getEditPatientMessage()`.

### 4.4 Appointment management

#### Functionality

The appointment management feature is designed to allow head nurses to schedule appointments between medical staff and
patients while ensuring that there are no appointment clashes for both staff and patients.

Below is a list of key design considerations for this feature:

* Upon adding/editing an appointment, MedBot will check if the new appointment clashes with an existing appointment and
  prevent such additions/edits.
* Users should be able to edit the date/time of appointments to reschedule appointments, or to fix mistakes.
    * Users should also be able to change the staff involved in that appointment for cases where the original staff is
      not available, or to fix mistakes.
    * Users should also be able to change the patient involved in that appointment to allow for the rearranging of
      appointments, or to fix mistakes.
* Users should be able to view a list of all appointments in the system.
* Users should be able to view a list of all appointments for a particular patient/staff.

#### Implementation

Appointment management is performed by the `Scheduler` class and involves the storage and modification of `Appointment`
class objects. Appointments are managed on an hourly basis.

The `Appointment` class is an association class between `Patient` and `Staff`, with additional `appointmentId` and
`dateTimeCode` attributes. It is stored in the `SchedulerAppointmentList` class object in the `Scheduler` class and in
`PersonalAppointmentList` class objects in each `Person` class object.

The `SchedulerAppointmentList` object keeps track of all appointments in the system. This allows for the viewing of all
appointments in the system. The object consists of a `HashMap` that stores `Appointment` objects with their
`appointmentId` as their key, this prevents the system having multiple `Appointment` objects of the same
`appointmentId`.

The `PersonalAppointmentList` object in each `Person` class object keeps track of the appointments of that person. This
allows for the viewing of all appointments of that person. The object consists of a `TreeSet` that stores `Appointment`
objects with their `dateTimeCode` as the comparable value. This prevents the system from adding clashing appointments to
a patient/staff and allows their appointments to be listed by their date.

Below is a simplified sequence diagram of the `addAppointment(Appointment)` method in the `Scheduler` class:

![Add Appointment Sequence Diagram](diagrams/AddAppointmentSequenceDiagram.png)

## Appendix A: Product scope

### Target user profile

Head nurses who are fast typists or prefer using a Command Line Interface (CLI) instead of a Graphical User Interface 
(GUI) for their daily jobs

### Value proposition

1. Easily manage patient and medical staff information.
2. Easily schedule and manage appointments between patients and staff.

## Appendix B: User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v1.0|user|add a new patient's information|track new patients admitted to the hospital|
|v1.0|user|remove a patient's information|discharged patients will no longer be tracked by the system|
|v1.0|user|edit a patient's information|rectify data entry errors or reflect changes in personal details|
|v2.0|user|add appointment timeslots for medical staff|easily administer their visiting hours|
|v2.0|user|be informed if there are any conflicting visiting timings for medical staff|reschedule to more appropriate timings.
|v2.0|user|edit appointment timeslots for medical staff|account for changes in schedule of the medical staff|
|v2.0|user|see all available appointment timeslots for medical staff|know to not assign any conflicting timings|
|v2.0|user|filter appointments by date/time|decide how to assign new appointments more quickly|

## Appendix C: Non-Functional Requirements

1. Should work on Windows, Linux, or OS-X as long as it has `Java 11` or above installed.
2. A user with above average typing speed for regular English text (i.e. not code, not system admin commands)
   should be able to accomplish most of the tasks faster using commands than using the mouse.
3. Should be able to hold up to a thousand appointments and patient/staff records without any noticeable decrease in
   performance.

## Appendix D: Instructions for manual testing

Below are instructions to perform manual testing of the application. Please refer to the
[User Guide](https://ay2122s1-cs2113-t13-1.github.io/tp/UserGuide.html) for more details on the usage of the various
commands.

### Launch and exit

1. Launch
    1. Please follow the instructions in
       the [Quick Start Guide](https://ay2122s1-cs2113-t13-1.github.io/tp/UserGuide.html#quick-start-guide)
       to launch the application.
2. Exit
    1. Enter `exit` to quit MedBot.

### Switching Views

1. Switching between the different views of MedBot, in any view
    1. Test case: `switch` <br>
       Expected: MedBot will switch to the next view. The views switch in this order, and loop back: <br>
       `Patient Management` --> `Staff Management` --> `Scheduler` --> `Patient Management`

2. Switching from other views to `Staff Management` view specifically
    1. Test case: `switch m` when in `Patient Management` view or `Scheduler` view <br>
       Expected: MedBot will switch to `Staff Management` view, and print: <br>

        ``` 
          ___ _____ _   ___ ___  
         / __|_   _/_\ | __| __| 
         \__ \ | |/ _ \| _|| _|  
         |___/_|_/_/_\_\_| |_|   
         |_ _| \| | __/ _ \      
          | || .` | _| (_) |     
         |___|_|\_|_|_\___/    __
         \ \ / /_ _| __\ \    / /
          \ V / | || _| \ \/\/ / 
           \_/ |___|___| \_/\_/  
        You are now in the Staff Management view.
        ```

3. Switching view to the current view
    1. Test case: `switch 3` when in the `Scheduler` view <br>
       Expected: View remains the same, prints: `You are already in the Scheduler view.`
4. Invalid switch commands
    1. Test case: `switch 100`, `switch abc`, ... <br> Expected: View remains the same,
       prints: `Invalid view type code.`

### Get current view

1. Get the current view type of MedBot
    1. Test case: `get view` when in the `Scheduler` view <br>Expected: `You are currently in the Scheduler view.`

### Accessing user guide

1. Using `help` in the `Scheduler` view
    1. Test case: `help` in `Scheduler` view. <br>Expected:
        ```
       Here is the list of commands:
       
       help
       add
       delete
       edit
       view
       list
       find
       switch
       get view
       exit
       
       To view more information about each command and their respective command formats, type:
       help [COMMAND]
       
       *Note that all commands will remove any '|' inputs for format parsing purposes.
       For expected output examples, please refer to the actual user guide.

        ```
2. Getting view specific help for a specific command
    1. Test case: `help add` in `Patient Management` view <br>Expected:
        ```
        Adds a patient to the patient list.
        Format:
        add i/PATIENT_IC [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]
        ```
3. Invalid `help` usage
    1. Test case: `help abc` in any view <br>Expected: `Unable to parse command.`

### Patient/Medical Staff Information Commands

* The commands for `Patient Management` view and `Staff Management` view are the same, but apply to patients and staff respectively.
  `Patient Management` view examples will be used here, but feel free to test the same commands for `Staff Management` view.
* Note that the Patient ID/Staff ID generated is dependent on the existing patients/staff information in MedBot.
* The expected output of the test cases below assumes that the commands are executed in the order that they are
  presented in this guide.

#### Adding a patient

1. Add a patient to the patient list with all details filled.
    1. Test case: `add i/S7812345X n/John Doe p/87654321 e/john.doe@gmail.com a/John Street, block 1234, #01-01` <br>
       Expected:
        ```
        Added patient with Patient ID: 1
        IC: S7812345X
        Name: John Doe
        H/P: 87654321
        Email: john.doe@gmail.com
        Address: John Street, Block 1234, #01-01
        ```
2. Add a patient to the patient list with some details filled.
    1. Test case: `add e/jimbob@hotmail.com n/jIm boB` <br>
       Expected:
        ```
        Added patient with Patient ID: 2
        IC: 
        Name: Jim Bob
        H/P: 
        Email: jimbob@hotmail.com
        Address: 
        ```
    2. Test case `add n/Raven Darkholme i/S8912345A` <br>
       Expected:
        ```
        Added patient with Patient ID: 3
        IC: S8912345A
        Name: Raven Darkholme
        H/P: 
        Email: 
        Address: 
        ```
3. Add a patient using an invalid attribute specifier
    1. Test case (Invalid command): `add h/` <br>
       Expected: `"h/" is not a valid attribute specifier`
4. Add a patient with an NRIC that already exists in MedBot
    1. Test case (Invalid command): `add i/S7812345X` <br>
       Expected: `The patient with IC S7812345X is already in the record.`

#### Delete a patient

1. Delete an existing patient from the list.
    1. Test case: `delete 3`<br>
       Expected: `Patient with ID 3 deleted from system.`
2. Delete a non-existent patient from the list.
    1. Test case: `delete 4`<br>
       Expected: `No Patient with ID 4 found.`
3. Invalid usage of `delete`
    1. Test case (Invalid command): `delete asd`<br>
       Expected: `ID not specified or not a positive number.`

#### View a patient's information

1. View an existing patient's information.
    1. Test case: `view 2` <br>
       Expected:
        ```
       Here's the requested patient information:
       
       Patient ID: 2
       IC: 
       Name: Jim Bob
       H/P: 
       Email: jimbob@hotmail.com
       Address: 
        ```
2. View a non-existent patient's information.
    1. Test case: `view 89` <br>
       Expected: `No Patient with ID 89 found.`

#### Hide a patient

* Refer to [this](#list-information-of-all-current-patients) to understand how `hide` affects `list`.
* Feel free to test `hide`, `show` and `list` together.

1. Hide a patient in the list.
    1. Test case: `hide 2`<br>
       Expected: `The patient with ID 2 is now hidden.`
    2. Test case: `hide 1`<br>
       Expected: `The patient with ID 1 is now hidden.`
2. Invalid usage of `hide`
    1. Test case (Invalid command): `hide ab`<br>
       Expected: `ID not specified or not a positive number.`

#### Show a patient

* Refer to [this](#list-information-of-all-current-patients) to understand how `show` affects `list`
* Feel free to test `hide`, `show` and `list` together.

1. Show a previously hidden patient in the list.
    1. Test case: `show 1`<br>
       Expected: `The patient with ID 1 is now not hidden.`
2. Invalid usage of `show`
    1. Test case: `show ab`<br>
       Expected: `ID not specified or not a positive number.`

#### List information of all current patients

* `list` shows all non-hidden patients.
* `list -h` shows all hidden patients.

1. List all non-hidden patients
    1. Test case: `list`
    <br>Expected:
    
        ```
        Here is a list of all not-hidden patients:
        For full details of each patient, please use the command "view PATIENT_ID"
        ----------------------------------------------------------------------------------------------------- 
        |  ID  | IC Number |         Name         | Phone No. |        Email         |       Address        | 
        ----------------------------------------------------------------------------------------------------- 
        | 1    | S7812345X | John Doe             | 87654321  | john.doe@gmail.com   | John Street, Bloc... | 
        ----------------------------------------------------------------------------------------------------- 
        ```
2. List all hidden patients
    1. Test case: `list -h`
    <br>Expected:
     
        ```
        Here is a list of all hidden patients:
        For full details of each patient, please use the command "view PATIENT_ID"
        ----------------------------------------------------------------------------------------------------- 
        |  ID  | IC Number |         Name         | Phone No. |        Email         |       Address        | 
        ----------------------------------------------------------------------------------------------------- 
        | 2    |           | Jim Bob              |           | jimbob@hotmail.com   |                      | 
        ----------------------------------------------------------------------------------------------------- 
        ```
3. Invalid usage of `list`
    1. Test case: `list -l`<br>
       Expected: `Parameter type specified is not valid.`

#### Edit information of a patient

1. Edit an existing patient's personal information, selected by ID.
    1. Test case: `edit 1 p/99999999 n/John Xavier Doe`<br>
       Expected:
        ```
        The information of patient with ID 1 has been edited to:
       
        Patient ID: 1
        IC: S7812345X
        Name: John Xavier Doe
        H/P: 99999999
        Email: john.doe@gmail.com
        Address: John Street, Block 1234, #01-01
        ```
2. Edit a non-existent patient's personal information
    1. Test case: `edit 898 n/BOb`<br>
       Expected: `No Patient with ID 898 found.`

#### Find patients based on attributes

1. Search for non-hidden patients with NRIC containing "12345"
    1. Test case: `find i/12345`<br>
       Expected:
        ```
        Here is a list of matched patients:
        ----------------------------------------------------------------------------------------------------- 
        |  ID  | IC Number |         Name         | Phone No. |        Email         |       Address        | 
        ----------------------------------------------------------------------------------------------------- 
        | 1    | S7812345X | John Xavier Doe      | 99999999  | john.doe@gmail.com   | John Street, Bloc... | 
        ----------------------------------------------------------------------------------------------------- 
        ```

2. Invalid usage of `find`
    2. Test case: `find g/`<br>
       Expected: `The specifier g/ is invalid.`

### Scheduler Commands

#### Prerequisites

* Run the following commands before running the `Scheduler` commands to populate the patient and medical staff lists
  with more data:
    1. `switch 1`
    2. `add n/Sasha Alexander i/S8367812K p/91238765 a/Mauville City 2nd Street`
    3. `switch 2`
    4. `add n/Doctor One i/S8754321B p/81819191 a/American Gardens Building West 81st Street e/doctorone@hospital.net`
    5. `add n/Nurse One i/S9171234D p/91234566 a/Petalburg City Route 103 e/nurseone@hospital.net`
    6. `switch 3`

#### Adding an appointment

* Adds an appointment to the scheduler list. MedBot will check if there are any clashes in the appointments and display
  and error message if there are any.
* The format for DATE_TIME is DDMMYY hhmm. I.e. 9 February 2021, 0800HRS should be written as 090221 0800
* Do note that the appointments are managed at an hourly basis. For example, any appointments set to any time between
  0800HRS and 0859HRS will be treated as an appointment from 0800HRS to 0859HRS. No subsequent appointment can then be
  scheduled for either the patient and the medical staff during that window.

1. Add a valid appointment with no clashes
    1. Test case: `add p/1 s/1 d/011221 0900`<br>
       Expected:
        ```
        Added appointment with Appointment ID: 1
        Patient ID: 1
        Staff ID: 1
        Date/Time: 01 Dec 21 0900HRS
        ```
    2. Test case: `add p/1 s/2 d/011221 1000`<br>
       Expected:
        ```
        Added appointment with Appointment ID: 2
        Patient ID: 1
        Staff ID: 2
        Date/Time: 01 Dec 21 1000HRS
        ```
2. Valid appointment, but the time is corrected to the hour
    1. Test case: `add p/1 s/2 d/011221 1105`<br>
       Expected:
       ```
       Added appointment with Appointment ID: 3
       Patient ID: 2
       Staff ID: 2
       Date/Time: 01 Dec 21 1100HRS
       ```
3. Adding a clashing appointment
    1. Test case (Invalid): `add p/2 s/1 d/011221 0900`<br>
       Expected: `Staff unavailable, appointment 1 at that time. `
4. Adding an appointment with invalid date/time format
    1. Test case (Invalid): `add p/1 s/2 d/01122123`<br>
       Expected: `Incorrect Date/Time format.`

#### Delete an appointment

1. Delete an existing appointment from the scheduler.
    1. Test case: `delete 2`<br>
       Expected: `Appointment with ID 2 deleted from system.`
2. Delete a non-existent appointment from the scheduler.
    1. Test case: `delete 89`<br>
       Expected: `No appointment with ID 89 found.`

#### Editing an appointment's information

1. Edit an existing appointment's information
    1. Test case: `edit 3 s/1`<br>
       Expected:
        ```
       The information of appointment with ID 3 has been edited to:
       
        Patient ID: 2
        Staff ID: 1
        Date/Time: 01 Dec 21 1100HRS
        ```
2. Edit an appointment to clash with another appointment
    1. Test case (Invalid): `edit 3 d/011221 0900`<br>
       Expected: `Staff unavailable, appointment 1 at that time.`

#### Viewing an appointment's information

1. View an existing appointment's information
    1. Test case: `view 1`<br>
       Expected:
        ```
        Here is the requested appointment information:

        Appointment Id: 1
        Patient ID: 1
        Staff ID: 1
        Date/Time: 01 Dec 21 0900HRS
        ```
2. View a non-existing appointment's information
    1. Test case (Invalid): `view 9`<br>
       Expected: `No appointment with ID 9 found.`

#### Listing information of all appointments

1. List the information of all appointments, including those of hidden patients and medical staff.
    1. Test case: `list`
       <br>Expected:
        ```
       Here is a list of all appointments:
       -------------------------------------------------------------------------------------------------- 
       |  ID  |     Date/Time     | Patient ID |     Patient Name     | Staff ID  |      Staff Name       |
       -------------------------------------------------------------------------------------------------- 
       | 1    | 01 Dec 21 0900HRS | 1          | John Xavier Doe      | 1        | Doctor One           |
       | 3    | 01 Dec 21 1100HRS | 2          | Jim Bob              | 1        | Doctor One           |
       -------------------------------------------------------------------------------------------------- 
        ```

#### Find appointments
* Prerequisites:
   * Execute the following commands first to populate the `Scheduler` with more data:
      1. `switch 3`
      2. `switch 1`
      3. `show 2`
      4. `switch 2`
      5. `show 2`
      6. `switch 3`
      7. `add p/1 s/2 d/021221 0900`
      8. `add p/2 s/2 d/031221 0900`
      9. `add p/3 s/2 d/041221 0900`
      10. `add p/2 s/2 d/051221 0900`
      11. `add p/3 s/2 d/061221 0900`

1. Find all appointments that involve a particular medical staff
   1. Test case: `find s/2`
   <br>Expected:
    ```
   Here is a list of matched appointments:
    -------------------------------------------------------------------------------------------------- 
    |  ID  |     Date/Time     | Patient ID |     Patient Name     | Staff ID  |      Staff Name       | 
    -------------------------------------------------------------------------------------------------- 
    | 4    | 02 Dec 21 0900HRS | 1          | John Xavier Doe      | 2        | Nurse One            | 
    | 5    | 03 Dec 21 0900HRS | 2          | Jim Bob              | 2        | Nurse One            | 
    | 6    | 04 Dec 21 0900HRS | 3          | Sasha Alexander      | 2        | Nurse One            | 
    | 7    | 05 Dec 21 0900HRS | 2          | Jim Bob              | 2        | Nurse One            | 
    | 8    | 06 Dec 21 0900HRS | 3          | Sasha Alexander      | 2        | Nurse One            | 
    -------------------------------------------------------------------------------------------------- 
    ```
2. Find all appointments that involve a particular medical staff, after a certain date/time
   1. Test case: `find s/2 a/051221 0000`
   <br>Expected: 
   ```
    Here is a list of matched appointments:
     -------------------------------------------------------------------------------------------------- 
     |  ID  |     Date/Time     | Patient ID |     Patient Name     | Staff ID  |      Staff Name       | 
     -------------------------------------------------------------------------------------------------- 
     | 7    | 05 Dec 21 0900HRS | 2          | Jim Bob              | 2        | Nurse One            | 
     | 8    | 06 Dec 21 0900HRS | 3          | Sasha Alexander      | 2        | Nurse One            | 
     -------------------------------------------------------------------------------------------------- 
   ```

### Saving data

* Persistent storage can be found in the `MedBotData` directory, in the same directory as the JAR file.
* Inside MedBotData, there is `appointment.txt`, `patient.txt` and `staff.txt`.

1. Dealing with corrupted data files
    1. Loading from storage, an appointment with non-existent staff ID (staff ID 10)
        1. Test case: Replace the current contents in the text file `appointment.txt` to this:
            ```
            1 | 011221 0900 | 1 | 10
            3 | 011221 1100 | 2 | 1
            ```
           and then launch MedBot using the terminal.<br><br> Expected:
            ```
            Hello, I'm MedBot!
     
            Error: Line 1 of MedBotData/appointment.txt is invalid!
     
            Please decide if you wish to:
     
            1. Enter 'exit' to exit MedBot to correct the storage files
            2. Enter other valid commands to OVERWRITE all invalid data!
         
            How can I help you today?
            ```

        * Do not enter any commands after this output. Close the terminal window and move on the next test case.<br>

    2. Loading from storage, patients with invalid phone number and invalid NRIC.
        1. Test case: Replace the current contents in the text file `patient.txt` to this:
            ```
               1 | S7812345X | John Xavier Doe | 899999999 | john.doe@gmail.com | John Street, Block 1234, #01-01 | S
               2 | X | Jim Bob | X | jimbob@hotmail.com | X | H
               3 | A8367812K | Sasha Alexander | 91238765 | X | Mauville City 2nd Street | S
            ```
           Expected:
            ```
                Hello, I'm MedBot!
     
                Error: Line 1 of MedBotData/patient.txt is invalid!

                Error: Line 3 of MedBotData/patient.txt is invalid!

                Error: Line 1 of MedBotData/appointment.txt is invalid!
         
                Please decide if you wish to:
         
                1. Enter 'exit' to exit MedBot to correct the storage files
                2. Enter other valid commands to OVERWRITE all invalid data!
             
                How can I help you today?
            ```
