---
layout: page
title: xuanlc113's Project Portfolio Page
---

### Project: UNIMods

UNIMods is a light-weight Command Line Interface (CLI) Application that provides a means for students to plan for their academic journey in NUS. It is written in Java.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=xuanlc113&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=xuanlc113&tabRepo=AY2122S1-CS2113T-W12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: Added the ability to save/load timetable file
    * What it does: allows timetable details to be saved to local storage
    * Justification: This feature improves the product significantly as any changes made to their schedule in the application can be persisted across different sessions
    * Highlights: This enhancement required a data transfer object class to be created to modify  the existing timetable class into a format suitable to be stored in a JSON format
    * Credits: Gson library used to convert between object and json file

* **New Feature**: Added ability to modify current semester
    * What it does: allows user to change the semester
    * Justification: This feature is crucial to the application as it modifies the module data being used (some modules are only available in certain semester, schedule changes across semesters)

* **New Feature**: Added ability to view only user created timetable events
    * What it does: allows user to view personal events in the timetable, i.e. not module lectures/tutorials/labs
    * Justification: This feature highlights the timetable usage, not limiting it to only modules but also personal tasks
    * 
* **Enhancements to existing features**:
    * Added exam details to the timetable so that user can view exam dates on their schedule

* **General Code Enhancements**:
    * Modified timetable items to extend from an abstract class so that more specific timetable item types can easily be extended and use common functions across all timetable items [\#67](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/67)

* **Project management**:
    * Managed releases v1.0 on GitHub

* **Documentation**:
    * User Guide:
        * Added documentation for the feature `semester`, additional info for `timetable` [\#195](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/195)
    * Developer Guide:
        * Added documentation for timetable save/load [\#82](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/82/files)
        * added documentation for timetable component [\#195](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/195)