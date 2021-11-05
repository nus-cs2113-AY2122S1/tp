# Peh Zhenhao, Amos - Project Portfolio Page

## Overview
**SchedUrMods** is a desktop application for NUS students who wish to manage their assignments 
and semester-related information via CLI (command-line interface). If you can type fast, SchedUrMods 
can help you manage your daily tasks faster than traditional GUI application.

Given below are the contributions I have made to this project.

### Summary of Contributions
- **Code contributed**: [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=apzh&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=APZH&tabRepo=AY2122S1-CS2113T-W13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

- **Enhancements implemented**:
  - General abstraction/skeleton of the TP program based on my IP program structure.
  - Implemented the `Ui.java` class to handle the reading of user inputs into the terminal and the displaying of 
  application related messages such as the result of a command execution based on the user's input.
  - Implemented the `CommandParser.java` class to parse user input into the correct `Command` object.
    - Added feature to allow flags to be extracted out of the user input to be universally used across all
    `Command` objects to allow flexibility/modularity for each command to allow additional arguments to be added subsequently.
  - Implemented the `list` command that allows users to 1) **filter the tasklist based on the filter arguments entered by the user** 
  and 2) **view the recurrences of specific tasks in the tasklist**. Majority of the methods in `TaskManager.java` class (around 80%) 
  are used to implement such functionality.
    - Added the relevant command class `ListCommand.java` and the relevant exceptions associated with the `list` command.
  - Implemented the `sort` command that allows users to sort the tasklist by various descriptors associated each task, 
  such as by **task type**, **priority**, or **description**. Methods are implemented in `TaskManager.java` class.
    - Added the relevant command class `SortCommand.java` and the relevant exceptions associated with the `sort` command.
    
- **Contributions to the UG**:
  - Created and maintained the skeleton/overview for the User Guide to help the team be consistent when adding information.
  - Added the information in "1. Quick Start".
  - Added the information in "2.4 Listing your tasks: `list`".
    - Including all it's sub-sections: "2.4.1 Listing your entire tasklist", "2.4.2 Filtering your tasklist", and
      "2.4.3 Listing the recurrence of a task".
  - Added the information in "2.5 Sorting your tasklist: `sort`".
  - Updated "4. Command Summary" table with changes made to the `list` and `sort` command.
  
- **Contributions to the DG**:
  - Created and maintained the skeleton/overview for the Developer Guide to help the team be consistent when adding information.
  - Added the information in "1. Introduction".
    - Including all it's sub-sections: "1.1 Purpose" and "1.2 Acknowledgments".
  - Added the information in "2. Setting up, getting started".
    - Including all it's sub-sections: "2.1 Pre-requisites", "2.2 Download the project on your computer", and "2.3 Setting up the project in IntelliJ".
  - Added the information in "3.1 Architecture"
    - All text descriptions in this section.
    - [Image] The **Architecture Diagram** - Showing the main components and flow of the general program
    - [Image] The **Sequence Diagram** - Showing how the components interact with each other for the scenario 
    where the user inputs any valid command in to the application. 
  - Added the information in "3.2 UI Component"
    - All text descriptions in this section.
    - [Image] The **Class Diagram** of the Ui component.
  - Added the information in "3.3 Parser Component"
    - All text descriptions in this section.
    - [Image] The **Class Diagram** of the Parser component.
  - Added the information in "4.2 Filtering the tasklist".
    - All text descriptions in this section.
    - [Image] The **Sequence Diagram** - Showing how the process of filtering the user's tasklist that is managed 
    - by the TaskManager class.

- **Contributions to team-based tasks**:
  - Maintained the issue tracker and milestones for our team.
  - Handled the releases of versions 1.0 and 2.0 of our application.
  - General format update to both the User Guide and Developer Guide to ensure consistency and allow others to easily
  add their assignment parts without affecting the general structure of the guide.
  - Added FAQs, Target Users, and tidied up sections in both the User Guide and Developer Guide.
- **Review/mentoring contributions**:
  - Help team members to test their features to ensure they work prior to releases each version of our application on GitHub.
  - Help review PRs made by team members in general and offer advice and feedback prior to merging the PRs.

### Contributions to the Developer Guide (Extracts):

These are the diagrams (General, Class, Sequence...) I have added into the Developer Guide:

#### For "3.1 Architecture"...
<p align="center">
    <img src="https://github.com/AY2122S1-CS2113T-W13-3/tp/blob/master/docs/images/AmosUMLDiagrams/Architecture.png">
</p>

<p align="center">
    <img src="https://github.com/AY2122S1-CS2113T-W13-3/tp/blob/master/docs/images/AmosUMLDiagrams/SD_ValidInput.png">
</p>

#### For "3.2 UI Component"...
<p align="center">
    <img src="https://github.com/AY2122S1-CS2113T-W13-3/tp/blob/master/docs/images/AmosUMLDiagrams/CD_UIComponent.png">
</p>

#### For "3.3 Parser Component"...
<p align="center">
    <img src="https://github.com/AY2122S1-CS2113T-W13-3/tp/blob/master/docs/images/AmosUMLDiagrams/CD_ParserComponent.png">
</p>

#### For"4.2 Filtering the tasklist"...
<p align="center">
    <img src="https://github.com/AY2122S1-CS2113T-W13-3/tp/blob/master/docs/images/AmosUMLDiagrams/SD_FilteringTasklist.png">
</p>

