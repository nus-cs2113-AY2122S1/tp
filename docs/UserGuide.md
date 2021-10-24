# User Guide
Teaching Assistant's Assistant (TAA) is a Command Line Interface (CLI) application for keeping track of modules and students.
If you can type fast, TAA can help keep track of your modules and students faster than traditional Graphical User Interface (GUI) apps.

* [Quick Start](#quick-start)
* [Features](#features)
  * [Viewing help: `help`](#viewing-help-help)
  * [Listing all modules: `list_modules`](#listing-all-modules-list_modules)
  * [Adding a module: `add_module`](#adding-a-module-add_module)
  * [Editing a module: `edit_module`](#editing-a-module-edit_module)
  * [Deleting a module: `delete_module`](#deleting-a-module-delete_module)
  * [Adding a student to a specified module: `add_student`](#adding-a-student-to-a-specified-module-add_student)
  * [Editing student information: `edit_student`](#editing-student-information-edit_student)
  * [Deleting student information: `delete_student`](#deleting-student-information-delete_student)
  * [Listing all students in a module: `list_students`](#listing-all-students-in-a-module-list_students)
  * [Finding students in a module given a keyword: `find_student`](#finding-students-in-a-module-given-a-keyword-find_student)
  * [Setting attendance for a particular lesson for a student: `set_attendance`](#setting-attendance-for-a-particular-lesson-for-a-student-set_attendance)
  * [Listing all assessments in a module: `list_assessments`](#listing-all-assessments-in-a-module-list_assessments)
  * [Adding an assessment to a module: `add_assessment`](#adding-an-assessment-to-a-module-add_assessment)
  * [Editing an assessment in a module: `edit_assessment`](#editing-an-assessment-in-a-module-edit_assessment)
  * [Deleting an assessment from a module: `delete_assessment`](#deleting-an-assessment-from-a-module-delete_assessment)
  * [Listing marks for an assessment: `list_marks`](#listing-marks-for-an-assessment-list_marks)
  * [Setting marks for a student's assessment: `set_marks`](#setting-marks-for-a-students-assessment-set_marks) 
  * [Editing marks for a student's assessment: `edit_mark`](#editing-marks-for-a-students-assessment-edit_mark)
  * [Deleting marks for a student's assessment: `delete_mark`](#deleting-marks-for-a-students-assessment-delete_mark)
  * [Finding average marks for an assessment: `average_marks`](#finding-average-marks-for-an-assessment-average_marks)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Taa.jar` from [here](https://github.com/AY2122S1-CS2113T-F12-3/tp/releases).
3. Create a new folder and place `Taa.jar` inside.
4. Open Command Prompt/Terminal and navigate to the folder containing `Taa.jar`.
5. Run `java -jar Taa.jar` in Command Prompt/Terminal to start the app.
6. Enter `help` to display the list of available commands. See [Features](#features) for more information.

## Features 

### Viewing help: `help`
Displays the list of available commands and how to use them.

Format: `help`

<br>

### Listing all modules: `list_modules`
Displays the list of all modules in the module list.

Format: `list_modules`

<br>

### Adding a module: `add_module`
Adds a module to the module list.

Format: `add_module c/<MODULE_CODE> [n/<MODULE_NAME>]`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `MODULE_NAME` [Optional] refers to the name of the module to be added.

Examples:
* `add_module c/CS2113T n/Software Engineering and Object-oriented Programming`
* `add_module n/Effective Communication for Computing Professionals c/CS2101`
* `add_module c/CS2102`

### Editing a module: `edit_module`
Edits the code or name of an existing module.

Format: `edit_module c/<MODULE_CODE> [nc/<NEW_MODULE_CODE>] [n/<NEW_MODULE_NAME>]`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `NEW_MODULE_CODE` [Optional] refers to the updated code of the module.
* `NEW_MODULE_NAME` [Optional] refers to the updated name of the module.
> 💡 **Note:**<br />
> Either `NEW_MODULE_CODE`, `NEW_MODULE_NAME`, or both must be provided.

Examples:
* `edit_module c/CS2113T nc/cs2113t`
* `edit_module c/CS2113T n/software engineering`
* `edit_module c/CS2113T nc/cs2113t n/software engineering`

### Deleting a module: `delete_module`
Deletes an existing module from the module list.

Format: `delete_module c/<MODULE_CODE>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).

Examples:
* `delete_module c/CS2113T`
* `delete_module c/CS2101`

<br>

### Adding a student to a specified module: `add_student`
Adds a student to a particular module

Format: `add_student c/<MODULE_CODE> i/<STUDENT_ID> n/<STUDENT_NAME>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `STUDENT_ID` refers to the ID of the student (e.g. A0217978J).
* `STUDENT_NAME` refers to the name of the student (e.g. Jon Lim).

Examples:
* `add_student c/cs2113t i/a0212345x n/Jon Lim`
* `add_student c/cs2113t n/Jonny Lims i/a1234567i`

<br>

### Editing student information: `edit_student`
Edits student information from a particular module

Format: `edit_student c/<MODULE_CODE> s/<STUDENT_INDEX> i/<STUDENT_ID> n/<STUDENT_NAME>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `STUDENT_INDEX` refers to the index of the student in the student list of the module.
* `STUDENT_ID` refers to the updated ID of the student (e.g. A0217978J).
* `STUDENT_NAME` refers to the updated name of the student (e.g. Jon Limbs).

Examples:
* `edit_student c/cs2113t s/1 i/a03452345x n/Jon Lim`
* `edit_student c/cs2113t s/5 n/Jonny Lims i/a120067i`

<br>

### Deleting student information: `delete_student`
Deletes student information from a particular module.

Format: `delete_student c/<MODULE_CODE> s/<STUDENT_INDEX>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `STUDENT_INDEX` refers to the index of the student in the student list of the module.

Examples:
* `delete_student c/cs2113t s/1`

<br>

### Listing all assessments in a module: `list_assessments`
List all assessments in a module.

Format: `list_assessments c/<MODULE_CODE>`
* MODULE_CODE refers to the code of the module (e.g. CS2113T).

Examples:
* `list_assessments c/CS2113T`
* `list_assessments c/CS2101`

<br>

### Adding an assessment to a module: `add_assessment`
Adds an assessment to a module.

Format: `add_assessment c/<MODULE_CODE> n/<ASSESSMENT_NAME> m/<MAXIMUM_MARKS> w/<WEIGHTAGE>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `ASSESSMENT_NAME` refers to the name of the assessment (e.g. Midterm, Assignment 1, etc.).
* `MAXIMUM_MARKS` refers to the maximum marks of the assessment (e.g. 100).
* `WEIGHTAGE` refers to the weightage of the assessment (e.g. 10%).

Examples:
* `add_assessment c/CS2101 n/OP1 m/20 w/10`
* `add_assessment c/CS2101 n/OP2 m/25 w/15`

<br>

### Editing an assessment in a module: `edit_assessment`
Edits an assessment of a module.

Format: `edit_assessment c/<MODULE_CODE> n/<ASSESSMENT_NAME> [nn/<NEW_ASSESSMENT_NAME>] [m/<NEW_MAXIMUM_MARKS>]`
* At least one of the optional fields must be provided.
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `ASSESSMENT_NAME` refers to the name of the assessment (e.g. Midterm, Assignment 1, etc.).
* `NEW_ASSESSMENT_NAME` refers to the new name of the assessment (e.g. Finals, Assignment 2, etc.).
* `NEW_MAXIMUM_MARKS` refers to the new maximum marks of the assessment (e.g. 50).
* `NEW_WEIGHTAGE` refers to the new weightage of the assessment (e.g. 20%).
> 💡 **Note:**<br />
> TODO

Examples:
* `edit_assessment c/CS2101 n/OP1 m/20 w/10`
* `edit_assessment c/CS2101 n/OP2 nn/UGDG`

<br>

### Deleting an assessment from a module: `delete_assessment`
Deletes an assessment of a module.

Format: `delete_assessment c/<MODULE_CODE> n/<ASSESSMENT_NAME>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `ASSESSMENT_NAME` refers to the name of the assessment (e.g. Midterm, Assignment 1, etc.).

Examples:
* `delete_assessment c/CS2101 n/OP1`
* `delete_assessment c/CS2101 n/OP2`

<br>

### Listing all students in a module: `list_students`
Shows a list of all students in a particular module.

Format: `list_students c/<MODULE_CODE>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).

Examples:
* `list_students c/CS2113T`
* `list_students c/CS2101`

<br>

### Finding students in a module given a keyword: `find_student`
Displays all students in the module matching the keyword along with their index.

Format: `find_student c/<MODULE_CODE> k/<KEYWORD>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `KEYWORD` refers to the keyword used to search the student list of the module.

Examples:
* `find_student c/CS2113T k/123`
* `find_students c/CS2101 k/Jon`

<br>

### Setting attendance for a particular lesson for a student: `set_attendance`
Sets a student's attendance for a lesson

Format: `set_attendance c/<MODULE_CODE s/<STUDENT_INDEX> l/<LESSON_NUMBER> p/<PRESENT>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `STUDENT_INDEX` refers to the index of the student in the student list of the module.
* `LESSON_NUMBER` refers to the lesson number
* `PRESENT` refers to the whether a student is present. (1 for present, 0 for not present)

### Setting marks for a student's assessment: `set_marks`
Adds a student's marks for an assessment.

Format: `set_marks c/<MODULE_CODE> s/<STUDENT_INDEX> a/<ASSESSMENT_NAME> m/<MARKS>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `STUDENT_INDEX` refers to the index of the student in the student list of the module.
* `ASSESSMENT_NAME` refers to the name of the particular assessment.
* `MARKS` refers to the marks given for the assessment.

Examples:
* `set_marks c/CS2113T s/1 a/Midterms m/70.6`
* `set_marks c/CS2101 s/3 a/Oral Presentation m/80`

<br>

### Editing marks for a student's assessment: `edit_mark`
Edits a student's mark for an assessment.

Format: `edit_mark c/<MODULE_CODE> s/<STUDENT_INDEX> a/<ASSESSMENT_NAME> m/<NEW_MARKS>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `STUDENT_INDEX` refers to the index of the student in the student list of the module.
* `ASSESSMENT_NAME` refers to the name of the particular assessment.
* `NEW_MARKS` refers to the new marks to be given for the assessment.

Examples:
* `edit_mark c/CS2113T s/1 a/Midterms m/80.6`
* `edit_mark c/CS2101 s/3 a/Oral Presentation m/90`

<br>

### Deleting marks for a student's assessment: `delete_mark`
Deletes a student's marks for an assessment.

Format: `set_marks c/<MODULE_CODE> s/<STUDENT_INDEX> a/<ASSESSMENT_NAME>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `STUDENT_INDEX` refers to the index of the student in student list of the module.
* `ASSESSMENT_NAME` refers to the name of the particular assessment.

Examples:
* `delete_mark c/CS2113T s/1 a/Midterms`
* `delete_mark c/CS2101 s/3 a/Oral Presentation`

<br>

### Finding average marks for an assessment: `average_marks`
Computes and prints the average marks for an assessment.

Format: `set_marks c/<MODULE_CODE> a/<ASSESSMENT_NAME>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `ASSESSMENT_NAME` refers to the name of the particular assessment.

Examples:
* `average_marks c/CS2113T a/Midterms`
* `average_marks c/CS2101 a/Oral Presentation`

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

### Exiting the program: `exit`
Exits the program

Format: `exit`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Simply transfer the `data` folder to your new computer and ensure that it is in the same folder as the jar file.

## Command Summary

{Give a 'cheat sheet' of commands here}

Command | Format and Examples |
  ------ | --------------- |
add_module | `add_module c/<MODULE_CODE> n/<MODULE_NAME>` <br /> e.g.`add_module c/CS2113T n/Software Engineeringand Object-oriented Programming`
add_student | `add_student c/<MODULE_CODE> i/<STUDENT_ID> n/<STUDENT_NAME>` <br /> e.g. `add_student c/CS2113T i/a0212345x n/Jon Lim`
add_assessment | `add_assessment c/<MODULE_CODE> n/<ASSESSMENT_NAME> m/<MAXIMUM_MARKS> w/<WEIGHTAGE>` <br /> e.g. `add_assessment c/CS2113T n/test m/100 w/20 `
set_attendance | `set_attendance c/<MODULE_CODE> s/<STUDENT_INDEX> l/<LESSON_NUMBER> p/<PRESENT>` <br /> e.g. `set_attendance c/CS2113T s/1 l/1 p/1`
set_marks | `set_marks c/<MODULE_CODE> s/<STUDENT_INDEX> a/<ASSESSMENT_NAME> m/<MARKS>` <br /> e.g. `set_marks c/CS2113T s/1 a/Midterms m/70.6`
delete_student | `delete_student c/<MODULE_CODE> s/<STUDENT_INDEX>` <br /> e.g. `delete_student c/cs2113t s/1`
delete_assessment | `delete_assessment c/<MODULE_CODE> n/<ASSESSMENT_NAME>` <br /> e.g. `delete_assessment c/CS2113T n/finals`
delete_mark | `delete_mark c/<MODULE_CODE> s/<STUDENT_INDEX> a/<ASSESSMENT_NAME>` <br /> e.g. `delete_mark c/CS2113T s/1 a/Midterms`
edit_mark | `edit_mark c/<MODULE_CODE> s/<STUDENT_INDEX> a/<ASSESSMENT_NAME> m/<NEW_MARKS>` <br /> e.g. `edit_mark c/CS2113T s/1 a/Midterms m/80.6`
edit_student | `edit_student c/<MODULE_CODE> s/<STUDENT_INDEX> i/<NEW_ID> n/<NEW_NAME>` <br /> e.g. `edit_student c/CS2113T s/1 i/12345 n/jon geh`
edit_assessment | `edit_assessment c/<MODULE_CODE> n/<ASSESSMENT_NAME> [nn/<NEW_ASSESSMENT_NAME>] [m/<NEW_MAXIMUM_MARKS>]` <br /> e.g. `edit_assessment c/CS2101 n/OP1 m/20 w/10`
list_modules | `list_modules`
list_students | `list_students c/<MODULE_CODE>` <br /> e.g. `list_students c/CS2113T`
list_attendance | `list_attendance c/<MODULE_CODE> s/<STUDENT_INDEX>` <br /> e.g. `list_attendance c/CS2113T s/1`
list_assessments | `list_assessments c/<MODULE_CODE>` <br /> e.g. `list_assessments c/CS2113T`
list_marks | `list_marks c/<MODULE_CODE> a/<ASSESSMENT_NAME>` <br /> e.g. `list_marks c/CS2113T a/Midterms`
find_student | `find_student c/<MODULE_CODE> k/<KEYWORD>` <br /> e.g. `find_student c/CS2113T k/123`
average_marks | `average_marks c/<MODULE_CODE> a/<ASSESSMENT_NAME>` <br /> e.g. `average_marks c/CS2113T a/Midterms`
help | `help`
exit | `exit`


