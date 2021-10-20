# User Guide
Teaching Assistant's Assistant (TAA) is a Command Line Interface (CLI) application for keeping track of modules and students.
If you can type fast, TAA can help keep track of your modules and students faster than traditional Graphical User Interface (GUI) apps.

* [Quick Start](#quick-start)
* [Features](#features)
  * [Listing all modules: `list_modules`](#listing-all-modules-list_modules)
  * [Adding a module: `add_module`](#adding-a-module-add_module)
  * [Listing all students in a module: `list_students`](#listing-all-students-in-a-module-list_students)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Taa.jar` from [here](https://github.com/AY2122S1-CS2113T-F12-3/tp/releases).
3. Create a new folder and place `Taa.jar` inside.
4. Open Command Prompt/Terminal and navigate to the folder containing `Taa.jar`.
5. Run `java -jar Taa.jar` to start the app.
6. Enter `help` to view a list of available commands. See [Features](#features) for more information.

## Features 

### Viewing help: `help`
Displays the available commands and how to use them

Format: `help`

<br>

### Listing all modules: `list_modules`
Shows a list of all modules in the module list.

Format: `list_modules`

<br>

### Adding a module: `add_module`
Adds a module to the module list.

Format: `add_module c/<MODULE_CODE> n/<MODULE_NAME>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `MODULE_NAME` refers to the name of the module.

Examples:
* `add_module c/CS2113T n/Software Engineering and Object-oriented Programming`
* `add_module n/Effective Communication for Computing Professionals c/CS2101`

<br>

### Adding a student to a specified module: `add_student`
Adds a student to a particular module

Format: `add_student c/<MODULE_CODE> i/<STUDENT_ID> n/<STUDENT_NAME>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `STUDENT_ID` refers to the ID of the student (e.g. A0217978J)
* `STUDENT_NAME` refers to the name of the student (e.g. Jon Lim)

Examples:
* `add_student c/cs2113t i/a0212345x n/Jon Lim`
* `add_student c/cs2113t n/Jonny Lims i/a1234567i`

<br>

### Edits a student information: `edit_student`
Edits student information from a particular module

Format: `edit_student c/<MODULE_CODE> s/<STUDENT_INDEX> i/<STUDENT_ID> n/<STUDENT_NAME>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `STUDENT_INDEX` refers to the index of the student in the module list
* `STUDENT_ID` refers to the updated ID of the student (e.g. A0217978J)
* `STUDENT_NAME` refers to the updated name of the student (e.g. Jon Limbs)

Examples:
* `edit_student c/cs2113t s/1 i/a03452345x n/Jon Lim`
* `edit_student c/cs2113t s/5 n/Jonny Lims i/a120067i`

<br>

### Deletes a student information: `delete_student`
Deletes student information from a particular module

Format: `delete_student c/<MODULE_CODE> s/<STUDENT_INDEX>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `STUDENT_INDEX` refers to the index of the student in the module list

Examples:
* `delete_student c/cs2113t s/1`

<br>

### Listing all students in a module: `list_students`
Shows a list of all students in a particular module.

Format: `list_students c/<MODULE_CODE>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).

Examples:
* `list_students c/CS2113T`
* `list_students c/CS2101`

<br>

### Find students in a module given a keyword: `find_student`
Displays all students in the module matching the keyword along with their index

Format: `find_student c/<MODULE_CODE> k/<KEYWORD`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `KEYWORD` refers to the keyword used to search the student list

Examples:
* `find_student c/CS2113T k/123`
* `find_students c/CS2101 k/Jon`

<br>

### Exit the program: `exit`
Exits the program

Format: `exit`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
