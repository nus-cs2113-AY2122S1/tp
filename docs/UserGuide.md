# User Guide
Teaching Assistant's Assistant (TAA) is a Command Line Interface (CLI) application for keeping track of modules and students.
If you can type fast, TAA can help keep track of your modules and students faster than traditional Graphical User Interface (GUI) apps.

* [Quick Start](#quick-start)
* [Features](#features)
  * [Listing all modules: `list_modules`](#listing-all-modules-list_modules)
  * [Adding a module: `add_module`](#adding-a-module-add_module)
  * [Adding a student to a specified module: `add_student`](#adding-a-student-to-a-specified-module-add_student)
  * [Editing student information: `edit_student`](#editing-student-information-edit_student)
  * [Deleting student information: `delete_student`](#deleting-student-information-delete_student)
  * [Listing all students in a module: `list_students`](#listing-all-students-in-a-module-list_students)
  * [Find students in a module given a keyword: `find_student`](#find-students-in-a-module-given-a-keyword-find_student)
  * [Listing marks for an assessment: `list_marks`](#listing-marks-for-an-assessment-list_marks)
  * [Setting marks for a student's assessment: `set_marks`](#setting-marks-for-a-students-assessment-set_marks) 
  * [Editing marks for a student's assessment: `edit_mark`](#editing-marks-for-a-students-assessment-edit_mark)
  * [Deleting marks for a student's assessment: `delete_mark`](#deleting-marks-for-a-students-assessment-delete_mark)
  * [Finding average marks for an assessment: `average_marks`](#finding-average-marks-for-an-assessment-average_marks)
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

### Editing student information: `edit_student`
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

### Deleting student information: `delete_student`
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

Format: `find_student c/<MODULE_CODE> k/<KEYWORD>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `KEYWORD` refers to the keyword used to search the student list

Examples:
* `find_student c/CS2113T k/123`
* `find_students c/CS2101 k/Jon`

<br>

### Listing marks for an assessment: `list_marks`
Lists all students and their marks for an assessment.

Format: `list_marks c/<MODULE_CODE> a/<ASSESSMENT_NAME>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `ASSESSMENT_NAME` refers to the name of the assessment whose marks are to be listed.

Examples:
* `list_marks c/CS2113T a/Midterms`
* `list_marks c/CS2101 a/Oral Presentation`

<br>

### Setting marks for a student's assessment: `set_marks`
Add a student's marks for an assessment.

Format: `set_marks c/<MODULE_CODE> s/<STUDENT_INDEX> a/<ASSESSMENT_NAME> m/<MARKS>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `STUDENT_INDEX` refers to the index of the student in the module list.
* `ASSESSMENT_NAME` refers to the name of the assessment that is to be marked.
* `MARKS` refers to the marks given for the assessment.

Examples:
* `set_marks c/CS2113T s/1 a/Midterms m/70.6`
* `set_marks c/CS2101 s/3 a/Oral Presentation m/80`

<br>

### Editing marks for a student's assessment: `edit_mark`
Edits a student's mark for an assessment.

Format: `edit_mark c/<MODULE_CODE> s/<STUDENT_INDEX> a/<ASSESSMENT_NAME> m/<NEW_MARKS>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `STUDENT_INDEX` refers to the index of the student in the module list.
* `ASSESSMENT_NAME` refers to the name of the assessment that is to be remarked.
* `NEW_MARKS` refers to the new marks to be given for the assessment.

Examples:
* `edit_mark c/CS2113T s/1 a/Midterms m/80.6`
* `edit_mark c/CS2101 s/3 a/Oral Presentation m/90`

<br>

### Deleting marks for a student's assessment: `delete_mark`
Deletes a student's marks for an assessment.

Format: `set_marks c/<MODULE_CODE> s/<STUDENT_INDEX> a/<ASSESSMENT_NAME>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `STUDENT_INDEX` refers to the index of the student in the module list.
* `ASSESSMENT_NAME` refers to the name of the assessment that is to be marked.

Examples:
* `delete_mark c/CS2113T s/1 a/Midterms`
* `delete_mark c/CS2101 s/3 a/Oral Presentation`

<br>

### Finding average marks for an assessment: `average_marks`
Finds the average marks for an assessment.

Format: `set_marks c/<MODULE_CODE> a/<ASSESSMENT_NAME>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `ASSESSMENT_NAME` refers to the name of the assessment that is to be marked.

Examples:
* `average_marks c/CS2113T a/Midterms`
* `average_marks c/CS2101 a/Oral Presentation`

<br>

### Exit the program: `exit`
Exits the program

Format: `exit`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

Command | Format and Examples |
  ------ | --------------- |
add_assessment | `add_assessment c/<MODULE_CODE> n/<ASSESSMENT_NAME> m/<MAXIMUM_MARKS> w/<WEIGHTAGE>` <br /> e.g. `hi`
add_module | `add_module c/<MODULE_CODE> n/<MODULE_NAME>` <br /> e.g. `add_module c/CS2113T n/Software Engineering and Object-oriented Programming`
add_student | `add_student c/<MODULE_CODE> i/<STUDENT_ID> n/<STUDENT_NAME>` <br /> e.g. `add_student c/cs2113t i/a0212345x n/Jon Lim`
set_attendance | `set_attendance c/<MODULE_CODE> s/<STUDENT_INDEX> l/<LESSON_NUMBER> p/<PRESENT>` <br /> e.g. `hi`
set_marks | `set_marks c/<MODULE_CODE> s/<STUDENT_INDEX> a/<ASSESSMENT_NAME> m/<MARKS>` <br /> e.g. `set_marks c/CS2113T s/1 a/Midterms m/70.6`
average_marks | `average_marks c/<MODULE_CODE> a/<ASSESSMENT_NAME>` <br /> e.g. `average_marks c/CS2113T a/Midterms`
delete_student | `delete_student c/<MODULE_CODE> s/<STUDENT_INDEX>` <br /> e.g. `delete_student c/cs2113t s/1`
delete_assessment | `delete_assessment c/<MODULE_CODE> n/<ASSESSMENT_NAME>` <br /> e.g. `hi`
delete_mark | `delete_mark c/<MODULE_CODE> s/<STUDENT_INDEX> a/<ASSESSMENT_NAME>` <br /> e.g. `delete_mark c/CS2113T s/1 a/Midterms`
edit_mark | `edit_mark c/<MODULE_CODE> s/<STUDENT_INDEX> a/<ASSESSMENT_NAME> m/<NEW_MARKS>` <br /> e.g. `edit_mark c/CS2113T s/1 a/Midterms m/80.6`
edit_student | `edit_student c/<MODULE_CODE> s/<STUDENT_INDEX> i/<NEW_ID> n/<NEW_NAME>` <br /> e.g. `hi`
find_student | `find_student c/<MODULE_CODE> k/<KEYWORD>` <br /> e.g. `find_student c/CS2113T k/123`
exit | `exit`
help | `help`
list_assessments | `list_assessments c/<MODULE_CODE>` <br /> e.g. `hi`
list_attendance | `list_attendance c/<MODULE_CODE> s/<STUDENT_INDEX>` <br /> e.g. `hi`
list_marks | `list_marks c/<MODULE_CODE> a/<ASSESSMENT_NAME>` <br /> e.g. `list_marks c/CS2113T a/Midterms`
list_modules | `list_modules`
list_students | `list_students c/<MODULE_CODE>` <br /> e.g. `list_students c/CS2113T`


