# Developer Guide
* [Acknowledgements](#acknowledgements)
* [Design](#design)
* [Implementation](#implementation)
* [Project scope](#product-scope)
* [User Stories](#user-stories)
* [Non-Functional Requirements](#non-functional-requirements)
* [Glossary](#glossary)

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design
### Architecture
![ArchitectureDiagram](diagrams/ArchitectureDiagram.png)
<br>
_Figure 1. Architecture Diagram_<br>
<br>
The _Architecture Diagram_ shown above explains the high-level design of the Application.<br>
<br>
**Overview of components**
* `Main`
  * On app launch: Creates and runs an instance of `Duke`.
* `UI`
  * Handles UI operations.
* `Taa`
  * On creation: Initializes the `UI` and `Storage` components.
  * On run: Loads data saved in `Storage`, receives and processes user input from `UI`.
* `Command`
  * Defines how a command is to be executed.
* `Model`
  * Represents a collection of classes that holds the data of the application in-memory.
  * Includes: `ModuleList`, `Modules`, `StudentList` , `Student`, `AssessmentList` , `Assessment`, `AttendanceList`
, `Attendance`
* `Storage`
  * Handles data storage operations (e.g. Reading from and writing to data file).
* `Commons`
  * Represents a collection of classes used by other components.
  * `Parser`: Used to translate user input into a `Command` and to parse an argument string into its various values.
  * `Util`: Contains utility functions used by other classes.

<br>

**Interactions between components**
<br>

![ArchitectureSequenceDiagram](diagrams/ArchitectureSequenceDiagram.png)
<br>
_Figure 2. An example of interactions between components for `add_module c/CS2113T n/Software Engineering`._

## Implementation
### Add Module
The add module mechanism is facilitated by `AddModuleCommand`. It extends `Command` and uses `ModuleList` which stores
module internally as an ArrayList `modules`.<br>

`AddModuleCommand` implements the following methods:
* `AddModuleCommand#execute(moduleList:ModuleList, ui:Ui, storage:Storage)` - Performs operations for the command.

`ModuleList` implements the following methods:
* `ModuleList#getSize()` - Returns the no. of modules currently in the list.
* `ModuleList#getModules()` - Returns an ArrayList containing all the modules.
* `ModuleList#getModule(code:String)` - Returns a module with a particular code.
* `ModuleList#isValidIndex(index:int)` - Checks if an index is valid w.r.t the `modules` ArrayList.
* `ModuleList#addModule(module:Module)` - Adds a module to the `modules` ArrayList.
* `ModuleList#getModuleAt(index:int)` - Returns a `Module` object at the specified `index` within the `modules` 
ArrayList.

Below is an example scenario of how the add module feature behaves at each step:<br>
* Step 1 - The user executes `add_module c/CS2113T n/Software Engineering` to add a module. The `add_module` command 
calls the `AddModuleCommand#execute` method. Within `AddModuleCommand#execute`, `ModuleList#getModule("CS2113T")` is 
called to ensure that there is no existing module with code `CS2113T`.
* Step 2 - If no existing module with code `CS2113T` is found, a new `Module` object with code and name set to `CS2113T`
and `Software Engineering` respectively. Then, `ModuleList#addModule` is called to add the newly created `Module` 
object into the `modules` ArrayList within `ModuleList`.

### Add Student
The add student mechanism is facilitated by `AddStudentCommand`. It extends `Command` and uses `StudentList` which
stores student internally as an ArrayList `students`.
<br>  

`AddStudentCommand` implements the following methods:
* `AddStudentCommand#execute(moduleList:ModuleList, ui:Ui, storage:Storage)` - Performs operations for the command.

`StudentList` implements the following methods:
* `StudentList#getSize()` - Returns the no. of students currently in the list.
* `StudentList#getStudents()` - Returns an ArrayList containing all the students.
* `StudentList#getStudentAt(index:int)` - Returns a student with the particular index.
* `StudentList#isValidIndex(index:int)` - Checks if an index is valid w.r.t the `students` ArrayList.
* `StudentList#addStudent(student:Student)` - Adds a student to the `students` ArrayList.
* `StudentList#deleteStudent(index:int)` - Deletes the `student` object at the specified `index` within the `students` 
ArrayList.
* `StudentList#findStudent(keyword:String)` - Returns an ArrayList of students containing the keyword

Below is an example scenario of how the add student feature behaves at each step:<br>
* Step 1 - The user executes `add_student c/CS2113T i/a0217978j n/jonny` to add a module. The `add_student` command 
calls the `AddStudentCommand#execute` method. Within `AddStudentCommand#execute`, `ModuleList#getModule("CS2113T")` is 
called to ensure that there is an existing module with code `CS2113T`.
* Step 2 - If an existing module with code `CS2113T` is found, a new `Student` object with id and name set to 
`a0217978j` and `jonny` respectively. Then, `StudentList#addModule` is called to add the newly created `Student` 
object into the `students` ArrayList within `StudentList`.



### Set Attendance
The set attendance mechanism is facilitated by SetAttendanceCommand. It extends `Command` and
uses  `AttendanceList` which stores a student's lessons attendance as an
ArrayList `attendances`.
<br>

`SetAttendanceCommand` implements the following methods:
* `SetAttendanceCommand#execute(moduleList:ModuleList, ui:Ui, storage:Storage)` - Performs operations for the command.

`AttendanceList` implements the following methods:
* `AttendanceList#getSize()` - Returns the no. of attendance currently in the list.
* `AttendanceList#getAttendances()` - Returns an ArrayList containing all the attendances.
* `AttendanceList#getAttendance(lessonNumber:String)` - Returns an attendance with a particular lesson number.
* `AttendanceList#isValidIndex(index:int)` - Checks if an index is valid w.r.t the `attendances` ArrayList.
* `AttendanceList#addAttendance(attendance:Attendance)` - Adds an attendance to the `attendances` ArrayList.
* `AttendnaceList#getAttendnaceIndex(lessonNumInput:String)` - Returns the attendance index in the `attendances` 
ArrayList.
* `AttendanceList#deleteAttendance(lessonNumInput:String)` - Deletes an attendance with a particular lesson number.
* `AttendanceList#sortAttendances` - Sorts the attendance in the `attendances` ArrayList in ascending order based on 
lesson number.

Below is an example scenario of how the set attendance feature behaves at each step:
* Step 1 - The user executes `set_attendance c/CS2113T s/1 l/1 p/1` to set an attendance to `Present` for student at 
index `1`, lesson number `1` to set a student's attendance for a lesson. The `set_attendance` command calls the 
`SetAttendanceCommand#execute` method. Within `SetAttendanceCommand#execute`, `AttendanceList#getAttendance("1")` is 
called to ensure that there is no existing attendance with the
lesson number `1`for student at index `1`.
* Step 2 - If no existing attendance object with lesson number `1` for student at index `1` is found, a new 
`Attendance` object
with lesson number `1` and attendance record `Present` is set as its lesson number and attendance record respectively.
Then, `AttendanceList#addAttendance` is called to add the newly created `Attendance` object into the `attendances` 
ArrayList within `AttendanceList`.

### Add Assessment
HI

### Set Marks
The set marks mechanism is facilitated by `SetMarksCommand`. It extends `Command` and uses a `results` HashMap to store 
`assessmentName` and the `marks` for it as a key-value pair.<br>  

`SetMarksCommand` implements the following methods:
* `SetMarksCommand#execute(moduleList:ModuleList, ui:Ui, storage:Storage)` - Checks input for errors before calling the 
`setMarks` command and saving the data.
* `SetMarksCommand#setMarks(ui:Ui, student:Student, assessmentName:String, marks:double)` - Puts a key-value pair with
`assessmentName` as key and `marks` as the value into the `results` HashMap of `student`.

Below is an example scenario of how the set marks mechanism behaves at each step:
* Precursor Step - The user must have executed the commands to create `module`, `assessment`, and `student` entities.
For purpose of this example, the user will have created `CS2113T` as the `module`, `Midterms` as the `assessment` with
a maximum mark of `100`, and `Jim Ho` as the `student`.
* Step 1 - The user executes the `set_marks c/CS2113T s/1 a/Midterms m/50` command to set marks for student at index `1`
for the `Midterms` assessment under the `CS2113T` module. The `set_marks` command calls the  `SetMarksCommand#execute` 
method. Within  `SetMarksCommand#execute`, `StudentList#getStudentAt("1")` is called to ensure that the student to be 
marked (in this case student at index `1`) exists.
* Step 2 - `AssessmentList#getAssessment(Midterms)` is then called to ensure that the `Midterms` assessment exists and
that the student has yet to be marked for it.
* Step 3 - `Assessment#getMaximumMarks();` is then called to ensure that the marks to be given for the `Midterms` 
assessment do not exceed the maximum marks attributed to it.
* Step 4 - If the above steps do not output an error, the `SetMarksCommand#setMarks` command is then called, which in 
turn calls the `Student#setMarks` command to put the key-value pair into the `results` HashMap of the student at index 
`1`.


## Product scope
### Target user profile

* Teaching assistant who has a need to manage a significant number of students and their information.
* Prefers desktop app over other types of apps.
* Can type fast.
* Prefers typing to mouse interactions.
* Reasonably comfortable using CLI apps.

### Value proposition

* Manage student information faster than a typical mouse/GUI driven app.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements
1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should 
be able to accomplish most of the tasks faster using commands than using the mouse.

## Glossary
* Mainstream OS: Windows, Linux, Unix, OS-X


## Method Summary

Method | Description |
  ------ | --------------- |
add_module | adds a module lol
