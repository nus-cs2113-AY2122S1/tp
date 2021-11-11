---
layout: page title: aditichadha1310's Project Portfolio Page
---

### Project: UNIMods

UNIMods is a light-weight Command Line Interface (CLI) Application that provides a means for students to plan for their
academic journey in NUS. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Implemented the Delete feature, and created the DeleteCommand.
    * What it does: Deletes the module or personal task which is passed in by the user along with the `delete` Command
      from the timetable.
      <br>
    * Justification: This feature is extremely important as it allows users to delete module/tasks from the timetable to
      give them the flexibility to customize the timetable time to time according to their needs.<br>
    * Highlights:This feature was first implemented in v1.0 to delete only the added modules in the timetable, but this
      feature was later improvised in further versions so that it can delete module tasks created by the user.<br>

* **New Feature**: Implemented the Remove feature, and created the RemoveCommand.
    * What it does: Removes the modules which is passed in by the user along with the `remove command` with its
      corresponding grade from the Transcript.
      <br>
    * Justification: This feature is extremely important as it allows users to remove unwanted modules and their grades
      from their transcript. It gives the users the flexibility to customize the transcript.<br>
    * Highlights: This feature was first implemented after the transcript feature was made fully functional. <br>

* **New Feature**: Implemented the Clear feature, and created the ClearCommand.
    * What it does: Removes all the modules and tasks from the timetable or removes all modules and their grades from
      the Transcript depending on the user's choice. The `clear` command prompts the user to choose an option to clear
      the timetable of all modules and tasks or the transcript of all grades and modules. It then clears according to
      the input given by the user.
      <br>
    * Justification: This feature is extremely important as it allows users to remove unwanted modules and their grades
      from their transcript. It gives users the flexibility to customize the transcript.<br>
    * Highlights: This feature was first implemented only to clear the modules and tasks from the timetable. However,
      this feature evolved with the iterations and was made functional to clear the transcript of all modules and
      grades. <br>

<div style="page-break-after: always;"> </div>

* **New Feature**: Added the Helpcommand.
    * What it does: allows the user to ask for help if they are stuck and not sure about what commands they should use.
      The `help` command returns a few commands along with the link to the comprehensive User Guide so that the users
      can refer to it.
    * Justification: This feature is useful as it allows users to find help without having to manually look through the
      User Guide.
    * Highlights: This feature was enabled in v1.0 along with a very neat and tabular UI so that it is convenient for
      users to look for help.<br>

* **New Feature**: Added the feature to allow users to input their grades secured in various modules and implemented the
  StoreResultsCommand.
    * What it does: allows the user to input their completed modules and the corresponding grades.
      The `store grade > <MODULE_CODE>` allows users to exercise S/U option as well.
    * Justification: This feature is useful as it allows users to keep a record of their progress in their academic
      journey and a list of all modules completed so far along with the total number of Module credits fulfilled.
    * Highlights: This feature simply takes in input from the user and checks the validity of the grades entered. In
      case the S/U option is exercised, it verifies if the module is allowed to be S/Ued and prompts the user if not
      allowed. <br>

* **New Feature**: Implemented the Calculate CAP feature, added the CalculateCapCommand.
    * What it does: Calculates CAP according to all modules and their corresponding grades as stored in the transcript.
      The `calculate` command returns CAP in 2 decimal points. <br>
    * Justification: This feature is important as it allows users to monitor the progress in their academic journey and
      view their most recent CAP.<br>
    * Highlights: This feature calculates CAP based on all modules stores in the Transcript. However, it excludes all
      the modules which are CS/CU and those modules, for which the S/U option is exercised.<br>

* **New Feature**: Created the Transcript feature, added the TranscriptUI and the TranscriptCommand.
    * What it does: Base class structure to store user's profile information, user's completed modules and the
      corresponding grades, and allows users to view the transcript in an organised and nicely structured UI form.<br>
    * Justification: This feature is extremely important as it allows to track their progress in their academic journey.
      The `transcript` command prints the user's profile information, the completed modules with their grades, final CAP
      and the total number of Module credits fulfilled.
    * Highlights: This feature involves a lot of functionality in the back-end but prints a nicely drafted Transcript
      for the user.<br>

<div style="page-break-after: always;"></div>
* **Code
  contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=aditichadha1310&tabRepo=AY2122S1-CS2113T-W12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Documentation**:
    * User Guide:
        * Added some parts to the Table of Contents.
        * Added guide for the following features
            * `delete <MODULE_CODE>/<TASK>`
            * `remove <MODULE_CODE>`
            * `clear`
            * `calculate`
            * `store > <MODULE_CODE>`
            * `transcript`
        * Added the Quick start section
        * Added some parts to the FAQ and command summary section.
    * Developer Guide:
        * Added the introduction and Setting up, Getting Started section.
        * Added the `remove from transcript` section along with the UML diagram.
        * Added the `delete from timetable` section along with the UML diagram.
        * Added all the major headings to give the developer guide a structure.
