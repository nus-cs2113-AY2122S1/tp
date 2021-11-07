# Click - Developer Guide

## Table of Contents

1. [Introduction](#1-introduction)\
1.1 [Background](#11-background)\
1.2 [Purpose](#12-purpose)\
1.3 [Scope](#13-scope)
2. [Setting Up](#2-setting-up)\
2.1 [Prerequisite](#21-prerequisite)\
2.2 [Setting up the Project in Your Computer](#22-setting-up-the-project-in-your-computer)
3. [Design](#3-design)
4. [Implementation](#4-implementation)\
4.1 [Module-related Features](#41-module-related-features)\
4.2 [Zoom-related Features](#42-zoom-related-features)\
4.3 [Calendar-related Features](#43-calendar-related-features)\
4.4 [Journal-related Features](#44-journaling-feature)\
4.5 [Food-related Features](#45-food-related-features)\
4.6 [Help Command](#46--help-command)\
4.7 [Logging](#47-logging)
5. [Testing](#5-testing)
6. [Dev Ops](#6-dev-ops)

## 1. Introduction

### 1.1 Background

Click is a Java-based Command-Line Application targeted at Computing students at NUS. 

Click aims to provide an all-in-one platform for students to:
1. Manage modules and zoom links
1. Manage tasks and compile them onto a calendar
1. Manage journals and notebooks
1. Manage food intake and consumption

### 1.2 Purpose

This guide aims to give you a big-picture view of how our application operates. From the macro view on how Click runs its main program,
to the micro view on  how  we  translate sentence input to an object with attributes.

By sharing this information, we appreciate feedback on any ways we can improve the functionality or documentation in order to give you the best experience on Click.

### 1.3 Scope

This document describes the software architecture, software design requirement, implementation and testing for Click. This guide is mainly for software developers, designers, and software engineers that are going to work on Click.

## 2. Setting Up

### 2.1 Prerequisite

1. JDK `11`
2. IntelliJ IDEA

### 2.2 Setting up the Project in Your Computer

1. Fork [this repository](https://github.com/AY2122S1-CS2113T-T09-4/tp) and clone the fork to your computer.
2. Open IntelliJ IDEA (if you are not in the welcome screen, close the existing project by clicking `File` > `Close Project`)
3. Set up the correct JDK version:\
i. Click `File` > `Project Structure`. Under `Project Setting`, choose `Project` > `Project SDK`.\
ii. If `JDK 11` is listed in the dropdown, select it. Otherwise, click `+ Add SDK` > `JDK...` and select the directory where you installed `JDK 11`.\
iii. Click `OK`.
4. Click `Import Project`.
5. Locate the `buid.gradle` file in the repository you have cloned and select it. Click `OK`.
6. Click `OK` to accept the default setting.
7. Verify the setup:\
i. Go to `src/main/java/seedu.duke.Click`, run `Click.main()` and trying a few commands.\
ii. Go to `src/test/java/seedu.duke` and run `Tests in seedu.duke` to ensure they all pass.

## 3. Design


### 3.1. Architecture

This section is designed to demonstrate our software design description, and aims to provide you with an overall guidance to the architecture of Click.

The following sequence diagram illustrates a command call by the user to Click. 
The steps are as follows:

1. When the user runs Click, a greeting would be printed to the user
2. User enters some input which may be a command
3. The command entered by the user would be parsed\
   3.1. If command, go ahead and execute it respectively\
   3.2. If not, throw exception and ask for input again
4. Parser returns control to Click

![](./images/ClickRun.png)

You should note that this is a general overview of the Click functionality, and the `:Command` entity simply represents a Command to be called by the Parser.
Another point for you to note is the difference between a Click exception and other exception. Click, as aptly referenced from our project, has unique
exceptions that belong to our program. For instance, invalid dates extending beyond a  student's  matriculation,  or a  lack of entries when adding a journal. 
This is different from that of an "other" exception, which could be briefly categorized as a general exception. For instance, a `NumberFormatException` on the parsing
of a String to an Integer.

### 3.2. UI Component

The `UI` package contains the `Ui` class, which deals with interactions with the user.

The `UI` component:
* Takes in user commands
* Formats messages and prints out responses

### 3.3. Logic Component

1. `Click` uses `Parser` class to parse the user command.
2. `Parser` checks if the command is valid or not, splits the user input into interpretable portions, and returns the respective commands with arguments.
3. All commands inherit from the abstract class `Command` with and `execute()` method. 
4. Command interacts with models, `Storage` to carry out user's command.
5. Command also makes use of `UI` to display the messages to the user.

### 3.4. Storage Component

The storage of `Click` refers to storing files of user's data into respective local subdirectory in a local directory called `storage`, which is in the same directory as the project root.

## 4. Implementation

This  section provides  the mechanisms of the many features of Click, where you can find documentation for the features as well
as code examples.

### 4.1. Module-related Features

#### 4.1.1 Adding a Module

This feature allows user to add a new Module.

If the creation is successful, a confirmation message on the newly created Module will be displayed to the user.

**Implementation**

The command for listing all modules is implemented by the `AddModuleCommand` class that extends `Command`.

When the user types `module add c/CS2113T n/Software Engineering e/A`, the following sequence of steps will then occur:

1. User executes `module add c/CS2113T n/Software Engineering e/A`\
   i. `Click` receives user's input.\
   ii. `Parser` calls `parser.parseCommand(userInput)` to parse user's input into a `Command`.
2. Creating `AddModuleCommand` object.
3. Executing command.\
   i. `AddModuleCommand` find `indexOfCode`, `indexOfName`, and `indexOfExpectedGrade` in user's input.\
   ii. `AddModuleCommand` calls `getModule()` to create a new `module` based on user's input.\
   iii. `AddModuleCommand` calls `storage.StorageModule.readDataFromFile()` to read Module-related data `moduleList` from the storage file.\
   iv. `AddModuleCommand` calls `moduleList.addModule(module)` to add a new `module` to the list.\
   v. `AddModuleCommand` prompts the successful message to the user.
   vi. `AddModuleCommand` calls `storage.storageModule.saveDataToFile(moduleList)` to save the new data to the storage file.

#### 4.1.2 Removing a Module

This feature allows user to remove a Module created in the past.

If the deletion is successful, a confirmation message on the Module deletion will be displayed to the user.

**Implementation**

The command for listing all modules is implemented by the `DeleteModuleCommand` class that extends `Command`.

When the user types `module delete 2`, the following sequence of steps will then occur:

1. User executes `module delete 2`\
   i. `Click` receives user's input.\
   ii. `Parser` calls `parser.parseCommand(userInput)` to parse user's input into a `Command`.
2. Creating `DeleteModuleCommand` object.
3. Executing command.\
   i. `DeleteModuleCommand` calls `storage.StorageModule.readDataFromFile()` to read Module-related data from the storage file.\
   ii. `DeleteModuleCommand` finds the `moduleIndex` based on user's input.\
   iii. `DeleteModuleCommand` checks if `moduleIndex` is valid or not. If not, throw an `IllegalModuleIndexException`.\
   iv. `DeleteModuleCommand` calls `moduleList.addModule(module)` to add a new `module` to the list.\
   v. `DeleteModuleCommand` prompts the successful message to the user.\
   vi. `DeleteModuleCommand` calls `moduleList.removeModuleByIndex(moduleIndex)` to delete the specified module.\
   vii. `DeleteModuleCommand` calls `storage.storageModule.saveDataToFile(moduleList)` to save the new data to the storage file.

#### 4.1.3 Listing All Modules

This feature allows user to view all Modules.

**Implementation**

The command for listing all modules is implemented by the `ListModuleCommand` class that extends `Command`.

When the user types `module list`, the following sequence of steps will then occur:

1. User executes `module list`\
   i. `Click` receives user's input.\
   ii. `Parser` calls `parser.parseCommand(userInput)` to parse user's input into a `Command`.
2. Creating `ListModuleCommand` object.
3. Executing command.\
   i. `ListModuleCommand` calls `storage.StorageModule.readDataFromFile()` to read Module-related data from the storage file.\
   ii. `ListModuleCommand` check if there is any Modules in the list. If not, prints the message of having no Modules then return.\
   iii. `ListModuleCommand` prompts the message to list the Modules to the user and prints out the Modules line by line.

The sequence diagram below summarizes how listing modules work:

![](images/module/ListModule.png)



### 4.2 Zoom related features

#### 4.2.1 Showing all available zoom links

This command is implemented by the `ShowZoomLinks` class. The basic functionality of this command is to access the data stored on the local drive to display the relevant zoom links along with the module codes.
The `ShowZoomLinks` class extends `Command`.

Syntax: `zoom list`

#### 4.2.2 Adding a new zoom link

This command is implemented by the `AddZoomCommand` class. The basic functionality of this command is to write a new zoom link to a local storage file and associate it to the relevant module code. The `AddZoomCommand`
class extends `Command`.

Syntax: `zoom add LINK MODULE_CODE`
Example: `zoom add nus.sg/testlink ABC101`


### 4.3 Calendar-related Features

#### 4.3.1 Displaying the calendar

This feature allows the user to view a calendar with tasks and lectures.

**Implementation**

The command for displaying the calendar for a specific month is implemented by the `DisplayCommand` class that extends `Command`.

Given below is an example usage scenario and how the display calendar mechanism behaves at each step.

1. The user enters the command `calendar display 10-2021`. This command is then sent for parsing in the `Click` class to `parseCommand` method in the `Parser` class. The `parseCommand` method first splits the entire command into an array `todoArguments` containing `calendar`, `display` and `10-2021`.

2. The string `calendar` from the first index of todoArguments is checked against switch cases and is found to match `COMMAND_CALENDAR` which is the constant string "calendar". Upon finding this match, the string from the second index `todoArguments`is further split based on the delimiter of a single white space. The string `display` id checked against possible suffixes and `Command` object `DisplayCommand` is returned to the `Click` class.
3. `Click` class then calls the method `execute` of `DisplayCommand` class. `DisplayCommand` extends `Command` class and has three steps in its `execute` method.\
         i. The `parseCalendarCommand` is first called, and it returns the year and month values after splitting `10-2021` into `10` and `2021`. This is put together into an YearMonth object `inputYearMonth`\
      >  **NOTE:** The input is validated first and if the input date given is invalid, i.e., the month not between 1-12, then the calendar for the current month is displayed.
   
      ii. The `inputYearMonth` is passed into `Ui` class method `printCalenderTitle` and this prints out the title of that month with the month name and the year. In this example, it will display as given in the figure below.
     ![](./images/calendar/calendar_header.png)

      iii. Then, the method `arrangeTaskList` in `Schedule` class is called, and it takes in `storage.tasksList` (the TaskList object with all the currently stored tasks drawn from storage), `calendarTasks` (an ArrayList<ArrayList<String>> object initialized with empty ArrayLists of type String), `month` (the month input by the user, which in this example is the integer `10`) and year `month` (the year input by the user, which in this example is the integer `2021`), and adds the tasks to the days in the empty String ArrayLists initialized before in `calendarTasks`.

      iv.The method `arrangeLectureList` is also called and the process is same as in the previous step, except with `storage.lectureList` and `calendarLecture` replacing the first two input parameters of `arrangeTaskList`.

      v.Then, the method `displayCalendar` in `Schedule` class is called, and it takes in `inputYearMonth` (the YearMonth object created from the month and year parsed from the user input), the `calendarTasks` (that was filled with the tasks for each day in the Step (iii)) and `calendarLecture` (that was filled with the lectures for each day in the Step (iv)). The method `displayCalendar` performs the necessary logic to print out the calendar.
  >  **NOTE:** Two tasks and two lectures are displayed for each day based on the order in which the user added them, and if there are more, they will show as and when the user deletes the tasks/lectures that are currently displayed.

The below sequence diagram shows the execution process of the calendar display feature.
![](./images/calendar/CalendarDisplaySequence.png)

**Design Considerations**

The following design considerations were kept in mind while implementing the calendar display feature,
- Aspect: Calendar visual display
    - Alternative 1: Display two tasks and two lectures at any time.
        - Pros : The calendar displayed would appear even and solution is easier to implement.
        - Cons: All the tasks and lectures not displayed.
    - Alternative 2: Set the size of the calendar to accommodate the largest number of tasks and lectures for a particular day. For example, if a day has 8 tasks and lectures and that is the highest amongst all the days, then the calendar would change to show all 8 for this day and the remaining days would have filled display up till how many tasks and lectures they have and the remaining spots empty.
        - Pros : The calendar displayed would show all the tasks and lectures.
        - Cons: Difficult to implement.

#### 4.3.2 Adding a Task

This feature allows user to add a new Task.

**Implementation**

The command for adding a task is implemented by the `AddTodoCommand` class that extends `Command`.

Given below is an example usage scenario and how the add task mechanism behaves at each step.

1. User executes `calendar todo n/ RANDOMNAME d/ 10-10-2021`\
   i. `Click` receives user's input.\
   ii. `Parser` calls `parser.parseCommand(userInput)` to parse user's input into a `Command`.
2. `AddTodoCommand` object is created.
3. Execution of the command.\
   i. `AddTodoCommand` gets the task description as well as the date from the user's input after it is parsed by `parseTodoCommand()` of `ParserSchedule`.
   ii. `AddTodoCommand` calls `Todo()` to create a new `Todo` object based on user's input.\
   iii. `AddTodoCommand` calls `checkIfDateValid()` to throw an exception if the date given byg the user is invalid.\
   iv. `AddTodoCommand` calls `addTask()` to add the `Todo` object created to `storage.tasksList`.\
   v. `AddTodoCommand` prints the successful message to the user.
   vi. `AddTodoCommand` calls `StorageTasks.writeTaskList(storage.tasksList)` to save the new data to the storage file.

Below is an activity diagram for the execution of this feature.

![](./images/calendar/AddTaskActivity.png)

#### 4.3.3 Adding a Lecture

This feature allows user to add a new Lecture.

**Implementation**

The command for adding a lecture is implemented by the `AddLectureCommand` class that extends `Command`.

Given below is an example usage scenario and how the add lecture mechanism behaves at each step.

1. User executes `calendar lecture m/ CS2113T s/ 10-10-2021 e/ 30-10-2021`\
   i. `Click` receives user's input.\
   ii. `Parser` calls `parser.parseCommand(userInput)` to parse user's input into a `Command`.
2. `AddLectureCommand` object is created.
3. Execution of the command.\
   i. `AddLectureCommand` gets the lecture module code as well as the start date and end date from the user's input after it is parsed by `parseLectureCommand()` of `ParserSchedule`.
   ii. `AddLectureCommand` calls `Lecture()` to create a new `Lecture` object based on user's input.\
   iii. `AddLectureCommand` performs checks to determine if the start date is before the end date as given by the user, and if it is not, then the exception `LectureIncorrectDateException` is thrown.\
   iv. `AddLectureCommand` calls `addLecture()` to add the `Lecture` object created to `storage.lectureList`.\
   v. `AddLectureCommand` prints the successful message to the user.
   vi. `AddLectureCommand` calls `StorageLecture.writeLectureList(storage.lectureList)` to save the new data to the storage file.

#### 4.3.4 Listing All Tasks

This feature allows user to view all Tasks.

**Implementation**

The command for listing all tasks is implemented by the `ListTasksCommand` class that extends `Command`.

Given below is an example usage scenario and how the list task mechanism behaves at each step.

1. User executes `calendar list task`\
   i. `Click` receives user's input.\
   ii. `Parser` calls `parser.parseCommand(userInput)` to parse user's input into a `Command`.
2. `ListTasksCommand` object is created.
3. Execution of the command.\
   i. `ListTasksCommand` calls `StorageTasks.readTaskList()` to read Task-related data from the storage file.\
   ii. `ListTasksCommand` calls `printTaskList(tasks.getTaskList())` of `Ui` package to print out the tasks to the user.\

#### 4.3.5 Listing All Lectures

This feature allows user to view all Lectures.

**Implementation**

The command for listing all lectures is implemented by the `ListLecturesCommand` class that extends `Command`.

Given below is an example usage scenario and how the list lecture mechanism behaves at each step.

1. User executes `calendar list lec`\
   i. `Click` receives user's input.\
   ii. `Parser` calls `parser.parseCommand(userInput)` to parse user's input into a `Command`.
2. `ListLecturesCommand` object is created.
3. Execution of the command.\
   i. `ListLecturesCommand` calls `StorageLecture.readLectureList()` to read Lecture-related data from the storage file.\
   ii. `ListLecturesCommand` calls `printLectureList(lectures.getLectureList())` of `Ui` package to print out the tasks to the user.\

#### 4.3.6 Deleting a Task

This feature allows user to delete a Task created in the past.

**Implementation**

The command for deleting a task is implemented by the `DeleteTaskCommand` class that extends `Command`.

Given below is an example usage scenario and how the delete task mechanism behaves at each step.

1. User executes `calendar delete task 1`\
   i. `Click` receives user's input.\
   ii. `Parser` calls `parser.parseCommand(userInput)` to parse user's input into a `Command`.
2. `DeleteTaskCommand` object is created.
3. Execution of the command.\
   i. `DeleteTaskCommand` checks if the index as gotten from `getTaskIndex()` of `Parser` class is in the task list and if it not then `CalendarIndexNotFoundException()` is thrown.\
   ii. `DeleteTaskCommand` calls `deleteTask(this.index)` for the index of the task as given by the user to delete the task.\
   iii. The delete successful message is printed to the user.\
   iv. `DeleteTaskCommand` calls `StorageTasks.writeTaskList(Storage.tasksList)` to save the new data to the storage file.

Below is a sequence diagram that demonstrates this feature.

![](./images/calendar/DeleteTaskCommand.png)

#### 4.3.7 Deleting a Lecture

This feature allows user to delete a Lecture created in the past.

**Implementation**

The command for deleting a task is implemented by the `DeleteLectureCommand` class that extends `Command`.

Given below is an example usage scenario and how the delete lecture mechanism behaves at each step.

1. User executes `calendar delete lec 1`\
   i. `Click` receives user's input.\
   ii. `Parser` calls `parser.parseCommand(userInput)` to parse user's input into a `Command`.
2. `DeleteLectureCommand` object is created.
3. Execution of the command.\
   i. `DeleteLectureCommand` checks if the index as gotten from `getLectureIndex()` of `Parser` class is in the task list and if it not then `LectureIndexNotFoundException()` is thrown.\
   ii. `DeleteLectureCommand` calls `deleteLecture(this.index)` for the index of the lecture as given by the user to delete the lecture.\
   iii. The delete successful message is printed to the user.\
   iv. `DeleteLectureCommand` calls `StorageLecture.writeLectureList(Storage.lectureList)` to save the new data to the storage file.

#### 4.3.8 Editing a Task

This feature allows user to edit a Task created in the past.

**Implementation**

The command for editing a task is implemented by the `EditTasksCommand` class that extends `Command`.

Given below is an example usage scenario and how the edit task mechanism behaves at each step.

1. User executes `calendar edit task 1`\
   i. `Click` receives user's input.\
   ii. `Parser` calls `parser.parseCommand(userInput)` to parse user's input into a `Command`.
2. `EditTasksCommand` object is created.
3. Execution of the command.\
   i. `EditTasksCommand` checks if the index as gotten from `getTaskIndexForEdit()` of `Parser` class is in the task list and if it not then `CalendarIndexNotFoundException()` is thrown.\
   ii. `EditTasksCommand` prompts the user to enter the command to add a todo task and parses the command using `parseTodoCommand()` The date given by the user is checked with `checkIfDateValid(date)` and if the date is incorrect then an exception is thrown.\
   iii. `EditTasksCommand` calls `editTask()` to edit the task.\
   iv. Edit successful message is printed back to the user.
   iv. `EditTasksCommand` calls `StorageTasks.writeTaskList(Storage.tasksList)` to save the new data to the storage file.

### 4.4 Journaling Feature

#### 4.4.1 Feature list
#### Add notebook feature

The command for adding notebook is implemented by the `AddNoteCommand` class that extends `Command`.
On adding notebook successfully, the message "Great you have added the note: NOTEBOOK_NAME" will be displayed.

#### Add entry feature

The command for adding entry is implemented by the `AddEntryCommand` class that extends `Command`.

On adding entry successfully, the message "Great you have added the entry: ENTRY_NAME" will be displayed.

#### List notebooks and entries

The command for adding notebook is implemented by the `ListJournalCommand` class that extends `Command`.

A list of notebooks along with their entries will be displayed.

#### 4.4.2 Testing

1. You can enter the command journal notebook n/ <NOTEBOOK_NAME> to add a notebook.
   You are free to enter a notebook name of your choice and observe the output of this command.

   `Test case: journal notebook n/ TRIAL
   Expected: Great you have added the notebook: TRIAL`

2.You can enter the command journal entry n/ <ENTRY_NAME> to add an entry to a specific notebook.
You are free to enter an entry name of your choice and observe the output of this command.

    Test case: journal entry n/ TRIAL e/ TEST
    Expected: Great you have added the entry: TEST

3.You can enter the command journal list to view list of notebooks and their entries

    Test case: journal list
    Expected: The notebook TRIAL contains:
              TEST

### 4.5 Food related features

This segment focuses on describing the implementation of food-related features,
the functionality of the commands as well as the design considerations taken.

#### 4.5.1 Architecture

##### Class Diagram of Food

Food-related commands operate on a list of food records, and a food storage object. The following diagram  illustrates
how the storage in a text file, and the current food list interact with each other.
Food storage object contains static methods to read and write data from said list.
The saving of data from the list to the storage file is elaborated further in the diagram below.
Further discussion on the design considerations of writing an abstract class are discussed in 
design considerations.
![](./images/food/foodClassDiagram.png)

##### Sequence diagram when food is parsed

The following diagram displays the interactions between the classes when the user enters a command starting with
"food". You should take note of the interactions between the constructed command classes, and the current list it's
iterating over - `WhatIAteTodayList`, especially the updates shown after the  end of every  command. An update is 
defined as a manual overwrite over the text file saved in the user's hard disk. By convention, this text file is saved
in the directory `fooddata` , with the text file named aptly as `food.txt`. Current improvements in this functionality
would be to integrate the dates in `Calendar` with  the `food.txt` file, enabling the user to search what they ate on a
given day.
![](./images/food/food_architecture.png)

#### 4.5.2 Feature List

> **Note**: the methods invoked in the following commands are visually depicted in the sequence  diagram,
> and thus only the general functionality  is discussed, as  well as the design considerations taken.

##### Adding Food Record

This feature allows user to add a new Food Record.
Tags `n/` `c/` stand for name and calorie count respectively.


**Code example**

`food add n/ Samurai Burger c/ 433`

##### Removing Food Record

This feature allows user to remove a Food Record created in the past.


**Code example**

`food delete [INDEX]`

##### Listing All Food Records

This feature allows user to view all Food Records.

This would be particularly useful for deleting items.

**Code example**

`food list`

##### Clearing food list

This feature allows users to clear their Food List.

**Code example**

`food clear`

##### Saving food list on successful command

The storage on hard-disk would be automatically
updated on every successful command entered by the user.

The interworking of this is described in detail in architecture.

#### 4.5.3 Food Design considerations

1. Why is there a need for calorie count?
   > Health tracking is important for students, especially during the pandemic
   where we stress eat during online lessons.
1. Why is there a need to remove a record? We can't un-eat stuff
   > Yes, however due to the limitations of the CLI interface there may be wrongly spelled
   entries that the user wouldn't want reflected in the list.
   Additionally, each food calorie count
   would be summed up to show the user the total calories consumed per day. If the calories
   entered are missing or have an additional 0 the sum would be overinflated 
1. Why implement  `ListOfRecords`?  It seems like a duplicate of `ArrayList<T>`
    > The abstract class `ListOfRecords` may seem uneventful to you on the surface, however
    much consideration was taken in account in the making. For instance, in our current iteration
    we are introducing a way to integrate current food names and calorie count from existing
    food courts - e.g. TechnoEdge. Another class could inherit from `ListOfRecords` in order to parse in
    the correct data, hence the generic `T` type used in the list.
   
### 4.6  Help command

This segment focuses on describing the not so-simple implementations behind what would otherwise
be considered a simple feature. An interesting point for you to note before exploring this portion 
would be the runtime-analysis of classes  done by the compiler, which reads the classes from the
source code and extracts the syntax. This is perhaps more functional than printing out a string
concatenating all command syntax.

#### 4.6.1 Architecture  of Help Command

The following diagram illustrates the  interactions between the functional class `ClassPackageReader` and
a particular command `ClickCommand`.
![](./images/help/helpCommandsClassDiagram.png)
A class package reader  is implemented in order to:
1.  Read classes from a specified package
1.  Get names of all classes declared
1.  Filter out the classes that are not commands by inheritance
1.  Collect the commands in a set
1.  Iterate through all other listed packages and merge the sets
1.  Sort the filtered commands by alphabetical order for readability
1.  Read the specific syntax  of a command, and print it to the user

The abstract class `Command` has the following methods which 
implement steps 3 & 4:
1. `public int compare(Command command1, Command command2){...}`
   >This comparator is used in the
`ClassPackageReader` to sort out the Commands by name, and is further elaborated in Logic.
1. `public void printClassNameAndSyntax()` 
    > This function splits the name of the class by upper and lower case, and also to remove the
    "command" word at the end of the class. 

Next, we decided to run the `ClassPackageReader` through a package rather than iterate through all classes.
The former is better than the latter considering our implementation of the commands. For instance, all module-
related commands are grouped together in the `module` package, food-related commands in `food` etc. Thus, by
accessing the packages and filtering out the commands, the `ClassPackageReader` presents the name of the command
and the syntax in a readable format to the user. Do note that the packages have to be manually input by the developers.
However, the core functionality of Click is already partitioned nicely into the packages and hence we do not expect
many updates over the lifeline of this project.

#### 4.6.2 Logic of Help Command

After describing the [architecture](#461-architecture--of-help-command) of the help command, this portion will then describe the sequence of activation by
the user when parsing a `help` command. Take the following sequence diagram for reference.
![](./images/help/HelpCommand_execute-Help_Commands.png)
The sequence diagram provides a high-level view on how the entities interact. You should notice the interaction between
`ClassPackageReader` and the `Command` entities, where the former gets the syntax of the latter by having a class as
input. This translates into a scalable option on addition of commands, where a syntax attribute is required to be present
in an empty constructor rather than concatenating additional syntax onto a constant String variable.

We reviewed the high-level functionality of `ClassPackageReader`, but it is also important for developers to take note of how
this class works on a lower-level. The function described here is `getCommandsAndPrintSyntax()`
1. Get classes from given packages, have each package converted into a Set of Classes.
1. Merge all the Sets together
1. Filter out the classes that are not Commands by inheritance, and add them into a List
1. Sort classes in List by name
1. For each command in List, (non-command classes are excluded in previous step)
    1. Get the declared method of abstract class Command `printClassNameAndSyntax()`
    1. Create default constructor of command and invoke the method given in (i)
    
You should take note that by step 5, this help functionality relies heavily on a _default constructor_. 
>Thus, 
when writing new `Commands`, a default constructor that contains no parameters has to overwrite the `syntax` element
in abstract class `Command`. This ensures that the  method creation and invocation of method in step 5 would be ready
to execute.

#### 4.6.3 Design considerations of Help Command

##### Aspect:  How to implement Help feature    

| \ | Alternative 1 (current choice): Reads the name and syntax from the Classes | Alternative 2 (previous choice): Prints all available commands from a String, hard-coding every syntax and printing |
|---|---|---|
| Pros | 1. Dynamic, works well and sorts the names by order as long as the constructor is included for a command<br>2. Very readable and testable due to sorted names<br>3. OOP implementation with overloaded methods and branching on inheritance<br>4. The user gets to easily view *ALL*  possible commands with a single word | 1. Easy to implement, just adding all available commands into a String and print it out<br>2. Relatively fewer lines of code (LoC)<br>3. User gets specific syntax with command entered |
| Cons | 1. Possible depreciated methods (`Class.getMethod`, `Class.getDeclaredConstructor`) which may be outdated, however,<br>  are still functional<br>2. Many more lines of code  (LoC) for implementation <br>3. The user  is bombarded with *ALL* possible commands with a single word | 1. Hard-coding and sorting help commands manually is a chore<br>2. User still has to remember the command in order to access the syntax |

While we submitted Alternative 2 in version 1 due to a lack of time and easier implementation. with more time given in Version 2.0 - we
decided  to switch over to Alternative 1 for the user to easily view all the syntax at a glance.

### 4.7. Logging

Logging in the application refers to storing exceptions, warnings and messages that occur during the execution of the program. It was included to help developers to identify bugs and to simplify their debugging process.

The `java.util.logging` package is used for logging. The logging mechanism is managed by the `ClickLogger` class through the `logger` attribute and all information is logged into a log file, `logs/ClickLogs.log`.

Logging Levels:
*`Level.SEVERE`: a serious failure, which prevents normal execution of the program, for end users and system administrators.
*`Level.WARNING`: a potential problem, for end users and system administrators.
*`Level.INFO`: reasonably significant informational message for end users and system administrators.
*`Level.CONFIG`: hardware configuration, such as CPU type.
*`Level.FINE`, `Level.FINER`, `Level.FINEST`: three levels used for providing tracing information for the software developers.

`ClickLogger` follows Singleton Pattern. Therefore, other classes can access the `logger` by calling `ClickLogger.getNewLogger()`.

Example of usage:

```Java

public class Click {

   private static Logger logger;
   
   // ...

   private static void run() {
      logger = ClickLogger.getNewLogger();
      logger.info(RUNNING_CLICK_LOG_MESSAGE);
      
      // ...
   }
}
```

## 5. Testing


## 6. Dev Ops

## Appendices 

### Appendix A: Product scope

**Target user profile**:

* Computing student at NUS.
* Reasonably comfortable using CLI.
* Student taking multiple modules.
* Has a need to manage food consumption.
* Has a need to schedule tasks and lecture timings.
* Has a need to view calendar with tasks and lecture timings.
* Enjoys journaling.
* Has a need for an easy way to access zoom links for lectures.

**Value proposition**:

* Allows for Computing students to conveniently manage studies and lifestyle.
* An easy-to-use all-in-one application for managing modules, tracking food
  consumption, task and lecture scheduling, journaling, and CAP planning.


### Appendix B: User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see the list of commands|know how to use the application|
|v1.0|user|add a new module|keep track of the module I'm going to take this semester|
|v1.0|user|list all modules|get the information about my modules|
|v1.0|user|delete recorded module|remove a module I'm not taking from the list|

### Appendix C: Non-Functional Requirements

1. Should work an any mainstream OS as long as it has Java `11` or above installed.
2. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
3. Should not require user to install program file/dependencies.
4. Should work for a single user.
5. Should be able to run without Internet connection.

### Appendix D: Glossary

**Mainstream OS**: Windows, Linux, Unix, OS-X

