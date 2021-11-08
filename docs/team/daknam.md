# Dak Nam's Project Portfolio Page

## Project: Teaching Assistant's Assistant (TAA)
Teaching Assistant's Assistant (TAA) is a Command Line Interface (CLI) application for keeping track of classes and students.
It is written in Java, and has about 6 kLoC.

Given below are my contributions to the project.
* **New Feature**: Added `Attendance` class.
    * **What it does**: Added the `Attendance` class to represent the attendance of a student.
    * **Justification**: Enables the creation of `Attendance` objects with relevant attributes.
  
* **New Feature**: Added `AttendanceList` class.
    * **What it does**: Added the `AttendanceList` class to represent the attendances of a student for lessons.
    * **Justification**: Enables the creation of `AttendanceList` objects that contains the student's attendance for 
      lessons.
* **New Feature**: Added `LessonAttendanceList` class.
   * **What it does**: Added the `LessonAttendanceList` class to represent the class attendance for a lesson.
   * **Justification**: Enables the creation of `AttendanceLessonList` objects that contains the students' attendances,
    grouped by lesson.
* **New Feature**: Added `ParseStudentIndexes` class.
   * **What it does**: Added the `ParseStudentIndexes` class to recognize multiple student index inputs.
   * **Justification**: Enables the user to input a range or a selection of student indexes.
* **New Feature**: Added `StudentIndexArray` class.
   * **What it does**: Added the `StudentIndexArray` class to store student indexes.
   * **Justification**: Enables the program to loop through the student indexes.
* **New Feature**: Added `command` classes for `Attendance` across `v1.0` - `v2.1`
   * These include: `SetAttendanceCommand`, `ListAttendanceCommand`, `ListLessonAttendanceCommand` and 
  `DeleteAttendanceCommand`.
   * **What they do**: Enable user to set, delete and view student attendance in `AttendanceList`and lesson attendance 
  in `LessonAttendanceList`. Users can also set and delete multiple attendances in one go.
  * **Justification** These commands are essential in allowing the user to set and view students' attendances to keep
   track of them.

* **Code contributed**: [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=daknam2001&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=daknam2001&tabRepo=AY2122S1-CS2113T-F12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
   * Managed releases `v1.0` - `v2.1` (3 releases) on GitHub
   * Created issues on issue tracker based on user stories

* **Enhancements to existing features**:
   * Wrote additional tests for existing features to increase coverage
    ([\#306](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/306/files))

* **Documentation**:
    * User Guide:
       * Added documentation for the `attendance` features
        ([\#117](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/117/files),
         [\#263](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/263/files),
         [\#306](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/306/files))
    * Developer Guide:
        * Added implementation details and sequence diagram of the `set_attendance` feature.
          ([\#306](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/306/files))
        * Added implementation details and sequence diagram of the `list_attendance` feature.
          ([\#326](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/326/files))
        * Added implementation details and sequence diagram of the `list_lesson_attendance` feature.
          ([\#326](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/326/files))
        * Added implementation details and sequence diagram of the `delete_attendance` feature.
          ([\#326](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/326/files))
        * Added class diagram for Attendance class.
        * ([\#331](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/331/files))

* **Team-Based Tasks**:
    * Participated in Zoom meetings to discuss features and issues
    * Maintained the issue tracker
    * Tested bugs as a team

* **Community**:
    * Reported bugs and suggestions for other teams in the class in 
      [PED](https://github.com/daknam2001/ped/tree/main/files).
