# TermiNUS User Guide

## Table of Contents

- [Introduction](#introduction)
- [Purpose](#purpose)
- [Using this Guide](#using-this-guide)
- [Getting Started](#getting-started)
- [Quick guide to using TermiNUS](#quick-guide-to-using-terminus)
- [Section: Module Management](#section-module-management)
    * [Accessing the module management workspace : `module`](#accessing-the-module-management-workspace)
    * [Adding a module : `add "<module_code>"`](#adding-a-module)
    * [Deleting a module : `delete <index>`](#deleting-a-module)
    * [Updating a module :`update <index> "<new_module_code>"`](#updating-a-module)
    * [Viewing module information `view`](#viewing-module-information)
- [Section: Module](#section-module)
    * [Accessing module workspace : `go <module_code>`](#accessing-module-workspace)
- [Section: Note](#section-note)
    * [Accessing note workspace : `note`](#accessing-note-workspace)
    * [Adding a Note : `add "<name>" "<content>"`](#adding-a-note)
    * [Deleting a Note : `delete <index>`](#deleting-a-note)
    * [Viewing note information : `view {index}`](#viewing-note-information)
    * [Exporting all notes: `export`](#exporting-all-notes)
- [Section: Schedule](#section-schedule)
    * [Accessing schedule workspace : `schedule`](#accessing-schedule-workspace)
    * [Adding a Schedule : `add "<description>" "<day>" "<start_time>" "<duration>" "<zoom_link>"`](#adding-a-schedule)
    * [Deleting a Schedule : `delete <index>`](#deleting-a-schedule)
    * [Viewing schedule information : `view`](#viewing-schedule-information)
- [Section: Question](#section-question)
    * [Accessing question workspace : `question`](#accessing-question-workspace)
    * [Adding a Question : `add "<question>" "<answer>"` ](#adding-a-question)
    * [Deleting a Question : `delete <index>`](#deleting-a-question)
    * [Viewing question information : `view {index}`](#viewing-question-information)
    * [Testing Yourself with Active Recall : `test {count}`](#testing-yourself-with-active-recall)
- [Displaying all schedules across all modules : `timetable {day}`](#displaying-all-schedules-across-all-modules)
- [Returning to previous workspace : `back`](#returning-to-previous-workspace)
- [Exiting TermiNUS : `exit`](#exiting-terminus)
- [Accessing Help : `help`](#accessing-help)
- [Advanced Usage of Commands](#advanced-usage-of-commands)
- [FAQ](#faq)
- [Workspace Command Summary](#workspace-command-summary)
- [Command Summary](#command-summary)

## Introduction

TermiNUS is a CLI (command line interface) program for NUS Students who wish to consolidate their
NUS academic materials such as zoom links, questions and notes for the modules that they are taking.
With TermiNUS, it aims to aid Students and improve their learning experiences while studying in NUS.

## Purpose

This documents aims to provide you with instruction on how to use `TermiNUS` and tips & tricks
included to improve your experiences while using it. The document will bring you through a detailed
guide on all existing commands as well as aiding you in installing `TerminNUS`.

---

## Using this Guide

Along the way, you might encounter commands with specific brackets around some values such as `{}`
and `<>`.

The below table represents what each means.

| Command options | Description |
| ----------- | ----------- |
| `{value}` |  The value is optional, and including it may provide different results.|
| `<value>` | The value is required for the command to work properly.|
|`"<value>"`| Some command have `""` quotes in it, this is required for the command to work properly. For example, `add "<value>"` would need to be `add "something"`.|
|`index`|A number identifying an item in TermiNUS. This index can only be viewed using the `view` command.|
|`start_time`|The `start time` must be in a **HH:mm** format which follows the **24-hour notation**. For example, `14:20` is valid which represents `2:20 pm`.|
|`day`|The `day` must be a day spelled out fully. For example, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday` and `Sunday` are the only **7** valid days.|
|`duration`|The `duration` must be a **positive** number and upon addition with `start_time`, it must not exceed the 24-hour limit of the day. For example, if `start_time` is `21:00`, `2` is a valid `duration` but `-1` and `4` are not.|

Terminologies used throughout this guide:

| Terminology | Description |
| ----------- | ----------- |
| workspace |  A workspace is the environment in which you are currently assessing. For example, when you want to access some files inside a folder, you will need to enter the folder first. As such the folder is a **workspace**.|
|workspace indicator|A workspace indicator shows the current workspace you are currently in when using `TermiNUS`. For example, in the expected output for commands, you will be able to see `[] >>>` which means you are in the **default workspace**, `[module] >>>` which means you are in the **module management workspace**. etc.|
|default workspace|The very first workspace when executing the `TermiNUS` program. For example, `[] >>>` indicates you are currently in the **default workspace**.|
|module|A module refers to a NUS module. For example, `CS2113T` is a module.|

Icons used throughout this guide:

> ⚠️A caution that you have to keep in mind when using the command.

> 💡 Take note when you see this icon, as it might tell you something important.

> ❗ Important things you should take note of before using the command.

Lastly, text that is blue like this example, are clickable [links](#test) that will bring you to the 
relevant part of this user guide.

---

## Getting Started

1. Ensure that you
   have [Java 11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html) or
   above installed.
2. Download the latest version of `TermiNUS.jar`
   from [here](https://github.com/AY2122S1-CS2113T-T10-2/tp/releases/).
3. Copy the file to the folder you want to use for `TermiNUS`.
    1. This is because `TermiNUS` will store all its saved data in the folder it is currently in.
4. Open your command prompt and go into the folder containing the `TermiNUS.jar` file.
    1. You can do so by `win + R` and run `cmd`.
    2. Next, a console will appear, type in the following `cd C:/folder` where `folder` is the
       folder containing the `TermiNUS.jar` file.
5. Next to run `TermiNUS`, in the command prompt type in `java -jar TermiNUS.jar`.
6. When you first start the `TermiNUS`, you will be greeted with our banner:

    ```
    Welcome to TermiNUS!
    You have no schedule for today.
    
    Type any of the following to get started:
    > exit
    > help
    > module
    > go
    > timetable
    
    [] >>>  
    ```

7. To get started, you can run the following commands:

    - module
    - go
    - timetable
    - help
    - exit
8. For more information of each command, please refer to the [Features](#section-note) below.

___

## Quick guide to using TermiNUS

1. Before adding any **note**, **schedule** and **question**, we will need to have a **module** first in which these items belong to.
2. You can do so by adding a module within the **module management workspace**. Please refer to: [Section: Module Management](#section-module-management).
3. After adding a module, you can proceed to add items such as **note**, **schedule** and **question** into it.
4. But firstly you will need to enter the **module workspace** in which you have just created. Please refer to: [Section: Module](#section-module).
5. Once entering the **module workspace**, you can add the items related to the module here.
    1. Please refer to [Section: Note](#section-note) for any related **Note** item.
    2. Please refer to [Section: Schedule](#section-schedule) for any related **Schedule** item.
    3. Please refer to [Section: Question](#section-question) for any related **Question** item.
6. Once you are done, you can exit **TermiNUS** using the `exit` command.

Hope you enjoy your experience with TermiNUS!

---

## Section: Module Management

All commands related to the workspace `Module Management` will be displayed in this section. These
commands enable users to create, delete and view `modules` within TermiNUS.

### Accessing the module management workspace

**Format:** `module`

Enters the Module Management workspace.

Example: `module`

Expected Output:

```
[] >>> module

You have 0 modules

Type any of the following to get started:
> add
> exit
> help
> view
> back
> update
> delete

[module] >>> 
```

List of Module Management workspace commands:

| Command      | Description |
| ----------- | ----------- |
|add|add a module in TermiNUS|
|delete|delete a module in TermiNUS|
|update|update a module code currently in TermiNUS|
|view|view modules information currently in TermiNUS|
|help|view all commands and their usage within the module management workspace|
|back|escape and return to the default workspace|
|exit|exit and closes TermiNUS|

### Adding a module

**Format:** `add "<module_code>"`

Adds a module when in the module management workspace.

> ⚠️The `<module code>` cannot be more than **30** characters and cannot have any `spaces` in it.

> 💡 When executing this command, it will add the module into TermiNUS and creates a folder with the module code.

> ❗ If there exists a folder with the same code of the newly added module, all contents in that folder will be wiped.

Example: `add "CS2113T"`

Expected Output:

```
[module] >>> add "CS2113T"
Module CS2113T has been added
[module] >>> 
```

### Deleting a module

**Format:** `delete <index>`

Deletes the specified module given by its **index** when in the module management workspace.

> ❗ When the specified module is being deleted, all contents in its folder will be wiped.

Example: `delete 1`

Expected Output:

```
[module] >>> delete 1
Deleted module CS2113T.
[module] >>> 
```

### Updating a module

**Format:** `update <index> "<new_module_code>"`

Updates a module code in the module management workspace.
> ⚠️The `<new_module_code>` will have the same restrictions as the [add command](#adding-a-module).  

Example: `update 1 "CS2113"`

Expected Output:

```
[module] >>> update 1 "CS2113"
Updated CS2113T to CS2113 successfully.
[module] >>> 
```

### Viewing module information

**Format:** `view`

Views all modules when in the module management workspace.

Example: `view`

Expected Output:

```
[module] >>> view
1. CS2113T
2. CS2106
[module] >>> 
```

---

## Section: Module

All commands related to accessing the existing modules in TermiNUS. These commands enable users to
enter the module workspace in Terminus.

> 💡 The module mentioned here are the modules created within the **module management workspace**. Please refer to [Section: Module Management](#section-module-management) for more information.

### Accessing module workspace

**Format:** `go <module_code>`

Enters the module workspace to access data within the module.

Example: `go CS2113T`

Expected Output:

```
[] >>> go CS2113T

Entering CS2113T workspace

Type any of the following to get started:
> exit
> help
> note
> schedule
> question
> back

[CS2113T] >>> 
```

List of Module workspace commands:

| Command      | Description |
| ----------- | ----------- |
|note|enter the note workspace|
|schedule|enter the schedule workspace|
|question|enter the question workspace|
|help|view all commands and their usage within its module workspace|
|back|escape and return to the default workspace|
|exit|exit and closes TermiNUS|

---

## Section: Note

All commands related to the workspace `Note` will be displayed in this section. These commands
enable users to create, delete and view `notes` within the module.

### Accessing note workspace

**Format:** `note`

Enters the Note workspace.

Example: `note`

Expected Output:

```
[CS2113T] >>> note

You have 0 note(s) inside this workspace.

Type any of the following to get started:
> add
> exit
> help
> view
> back
> delete
> export

[CS2113T > note] >>> 
```

List of Note workspace commands:

| Command      | Description |
| ----------- | ----------- |
|add|add a note for the module|
|delete|delete a note from the module|
|view|view notes information from the module|
|export|export all current notes for the module as a pdf|
|help|view all commands and their usage in the note workspace|
|back|escape and return to the module workspace|
|exit|exit and closes TermiNUS|

### Adding a Note

**Format:** `add "<name>" "<content>"`

Adds a note when in the note workspace.

> ⚠️The `<name>` cannot be more than **30** characters.

> 💡 When executing this command, it will add the note into its module and creates a `.txt` file inside the module folder. The `.txt` file will be named after the newly added note name.

> ❗ If there exists a file with the same name of the newly added note, all contents in that file will be overwritten.

Example: `add "coding style" "switch case identation should be aligned."`

Expected Output:

```
[CS2113T > note] >>> add "coding style" "switch case identation should be aligned."
Your note on 'coding style' has been added!
[CS2113T > note] >>> 
```

### Deleting a Note

**Format:** `delete <index>`

Deletes the specified note given by its **index** when in the note workspace.

> ❗ When the specified note is being deleted, the file that stores the note will be deleted.

Example: `delete 1`

Expected Output:

```
[CS2113T > note] >>> delete 1
Your note on 'coding style' has been deleted!
[CS2113T > note] >>> 
```

### Viewing note information

**Format:** `view {index}`

Views a list of notes in the module or views all information for that specific note when in the note
workspace.

Example 1: `view`

Expected Output 1:

```
[CS2113T > note] >>> view
List of Content
---------------
1. coding style
2. coding comments

Rerun the same command with an index behind to view the content.
[CS2113T > note] >>> 
```

Example 2: `view 1`

Expected Output 2:

```
[CS2113T > note] >>> view 1
Name: coding style
Content:
switch case identation should be aligned.

[CS2113T > note] >>> 
```

### Exporting all notes 

**Format:** `export`

Exports all notes within the note workspace as a PDF file.
> ⚠️ To export a file, there must be at least 1 note contained within the note workspace.

> ⚠️ The export process can take a while.   
> A very large text can cause the program to take a long time to export.

Example: `export`

Expected Output:

```
[CS2113T > note] >>> export
Exported notes! Check the data folder.
[CS2113T > note] >>> 
```

---

## Section: Schedule

All commands related to the workspace `Schedule` will be displayed in this section. These commands
enable users to create, delete and view `schedule` within the module.

### Accessing schedule workspace

**Format:** `schedule`

Enters the Schedule workspace.

Example: `schedule`

Expected Output:

```
[CS2113T] >>> schedule

You have 0 link(s) in this workspace.

Type any of the following to get started:
> add
> exit
> help
> view
> back
> delete

[CS2113T > schedule] >>> 
```

List of Schedule workspace commands:

| Command      | Description |
| ----------- | ----------- |
|add|add a schedule for the module|
|delete|delete a schedule from the module|
|view|view schedule information from the module|
|help|view all commands and their usage in the schedule workspace|
|back|escape and return to the module workspace|
|exit|exit and closes TermiNUS|

### Adding a Schedule

**Format:** `add "<description>" "<day>" "<start_time>" "<duration>" "<zoom_link>"`

Adds a schedule when in the schedule workspace.

> ⚠️The `<day>` must be a valid **day spelled fully**. For example, `monday` is a valid day but `mon` is not.

> 💡The accepted input for the `<day>` argument includes "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", and "Sunday".
 
> ⚠️The `<start_time>` must be in a **HH:mm** format which follows the **24-hour notation**. For example, `14:20` is valid which represents `2:20 pm`.

> ⚠️The `<duration>` must be a **positive** integer. For example, `2` is a valid duration but `-1.5` is not.

Example: `add "CS2113T Tutorial 1" "Thursday" "10:00" "2" "https://zoom.us/test"`

Expected Output:

```
[CS2113T > schedule] >>> add "CS2113T Tutorial 1" "Thursday" "10:00" "2" "https://zoom.us/test"
Your schedule on 'CS2113T Tutorial 1' has been added!
[CS2113T > schedule] >>> 
```

### Deleting a Schedule

**Format:** `delete <index>`

Deletes the specified schedule given by its **index** when in the schedule workspace.

Example: `delete 1`

Expected Output:

```
[CS2113T > schedule] >>> delete 1
Your link on 'CS2113T Tutorial 1' has been deleted!
[CS2113T > schedule] >>> 
```

### Viewing schedule information

**Format:** `view`

Views a list of schedules in the module when in the schedule workspace.

Example: `view`

Expected Output:

```
[CS2113T > schedule] >>> view
List of Content
---------------
1. CS2113T Tutorial 1 (Thursday, 10:00 - 12:00): https://zoom.us/test
2. CS2113T Lab 1 (Friday, 12:30 - 14:30): https://zoom.us/test

Rerun the same command with an index behind to view the content.
[CS2113T > schedule] >>> 
```

---

## Section: Question

All commands related to the workspace Question will be displayed in this section. These commands
enable users to create, delete and view questions within the module.

In addition, users can test themselves with the question added into the module.

### Accessing question workspace

**Format:** `question`

Enters the Question workspace.

Example: `question`

Expected Output:

```
[CS2113T] >>> question

You have 0 question(s) in this workspace.

Type any of the following to get started:
> add
> exit
> help
> view
> test
> back
> delete

[CS2113T > question] >>> 
```

### Adding a Question

**Format:** `add "<question>" "<answer>"`

Adds a question when in the question workspace.

Example: `add "What is 1+1?" "2"`

Expected Output:

```
[CS2113T > question] >>> add "What is 1+1?" "2"
Your question on 'What is 1+1?' has been added!
[CS2113T > question] >>> 
```

### Deleting a Question

**Format:** `delete <index>`

Deletes the specified question given by its **index** when in the question workspace.

Example: `delete 1`

Expected Output:

```
[CS2113T > question] >>> delete 1
Your question on 'What is 1+1?' has been deleted!
[CS2113T > question] >>> 
```

### Viewing question information

**Format:** `view {index}`

Views a list of questions in the module or views all information for that specific question when in
the question workspace.

Example 1: `view`

Expected Output 1:

```
[CS2113T > question] >>> view
List of Content
---------------
1. What is EP?
2. What is UML?
3. What is SUT?

Rerun the same command with an index behind to view the content.
[CS2113T > question] >>> 
```

Example 2: `view 1`

Expected Output 2:

```
[CS2113T > question] >>> view 1
Name: What is EP?
Content:
Equivalence partitioning

[CS2113T > question] >>> 
```

### Testing Yourself with Active Recall

**Format:** `test {count}`  
You can start an Active Recall session by running the `test` command.

By default, it will test 10 questions (or less if there are not enough questions). You may specify
how many questions you wish to run in that session by keying in the number of questions after the
`test` command.

When you begin, you will be prompted with the following (do note that the question pool may be
smaller if there are not enough questions in the workspace). Press the <kbd>Enter</kbd> key to
start.

```
[CS2113T > question] >>> test 3
---[Active Recall]---

We will be starting your active recall training session.
This session will consist of 3 questions.

When you are ready, press [Enter] to continue.
```

The first question will be displayed, and once you are ready to reveal the answer, press the
<kbd>Enter</kbd> key again.

```
---

Question:
What is EP?

When you are ready, press [Enter] to continue.
```

After revealing the answer, provide feedback to TermiNUS to allow it to know if the question should
appear more often in the future.

- Key in `1` if you felt it was easy.
- Key in `2` if you felt it was normal.
- Key in `3` if you felt it was hard.
- Key in `E` if you wish to exit the session.

```
You took 172 seconds to reveal the answer.

Answer:
Equivalence partitioning

How did you find the question? (Compare against past attempts if any)
[1] Easy; [2] Normal / Same; [3] Hard; [E] Exit
[1/2/3/E] >> 
```

After choosing your difficulty, you may proceed to reveal the next question by pressing
<kbd>Enter</kbd> key again.

```
[1/2/3/E] >> 2

When you are ready, press [Enter] to continue.
```

Once the question pool is empty, or when you decided to stop, TermiNUS will drop you back into the
command prompt.

```
This training session has ended.
Returning you back to main program.
[CS2113T > question] >>> 
```

---

## Displaying all schedules across all modules

**Format:** `timetable {day}`

Displays all schedules from all modules in TermiNUS or displays certain schedules that falls on the
specified day.

> ⚠️The `<day>` must be a valid **day spelled fully**. For example, `monday` is a valid day but `mon` is not.

Example 1: `timetable`

Expected Output 2:

```
[] >>> timetable
THURSDAY:
1. CS2113T Tutorial 1 (Thursday, 10:00 - 12:00): https://zoom.us/test
FRIDAY:
1. CS2113T Lab 1 (Friday, 12:30 - 14:30): https://zoom.us/test

[] >>> 
```

Example 2: `timetable thursday`

Expected Output 2:

```
[] >>> timetable thursday
1. CS2113T Tutorial 1 (Thursday, 10:00 - 12:00): https://zoom.us/test

[] >>> 
```

## Returning to previous workspace

**Format:** `back`

Returns to the previous workspace prior to the current workspace you are in.

> 💡 The **default** workspace is the only workspace that has no `back` command. This is because this workspace is the very first workspace of TermiNUS.

Example: `back` from note workspace

Expected Output:

```
[CS2113T > note] >>> back

Entering CS2113T workspace

Type any of the following to get started:
> exit
> help
> note
> schedule
> question
> back

[CS2113T] >>> 
```

> 💡 Notice how the workspace indicator changes from `[CS2113T > note]` to `[CS2113T]`.

## Exiting TermiNUS

**Format:** `exit`

To exit the program, simply run the following command:

```
[] >>> exit 
Goodbye! 
```

## Accessing Help

**Format:** `help`

Depending on your current workspace, you may get different help messages.  
The following shows the help message in the default workspace:

```
[] >>> help

Help Menu
---------
exit : Exits the program.
Format: exit

help : Prints the help page.
Format: help

module : Move to the module workspace
Format: module

go : Go to a specific module's workspace
Format: go <module name>

timetable : Displays all your schedule.
Format: timetable {day}

[] >>> 
```

## Advanced Usage of Commands

**Format:** `<workspace> <available workspace command>`

Users can access workspace command directly without entering its environment. Seen below are some
command examples.

A workspace command is a command that will bring you to its own workspace. Current workspace command
includes note, schedule, question, and module.

> 💡 To access the module in TermiNUS, you will need to use the `go` command. For more information, please refer to [Section: Module](#section-module).

> ⚠️This advance command do not allow any chaining with the `back` command.

Example:

- Adding a note without entering the note workspace.

```
[CS2113T] >>> note add "Advance command"  "Advance command is cool"
Your note on 'Advance command' has been added!
[CS2113T] >>> 
```

- Adding a schedule without entering the schedule workspace.

```
[CS2113T] >>> schedule add "Lecture" "Friday" "16:00" "2" "https://zoom.us/test"
Your schedule on 'Lecture' has been added!
[CS2113T] >>> 
```

- Adding a question without entering the module workspace.

```
[] >>> go CS2113T question add "What is Java?" "It is a programming language."
Your question on 'What is Java?' has been added!
[] >>> 
```

___  

## FAQ
**Q:** Can I edit the information in the`data` directory?  
**A:** Yes! **TermiNUS** saves and loads your information from the `data` folder. You are able to edit the files
in the `data` folder. We recommend you to update the **contents** of any notes which should be a `.txt` file in the 
`data/module_code` directory. However, we **do not** recommend modifying any configuration file such as `.json` file 
or renaming any folders in the `data` folder including the `data` folder itself. For these, you should use **TermiNUS** instead to edit.

___  

## Workspace Command Summary

| Action | Format| Examples |
| ----------- | ----------- |----------- |
|access **module management** workspace|`module`|-|
|access **module** workspace|`go <module_code>` | `go CS2113T`|
|access **note** workspace|`note`|-|
|access **schedule** workspace|`schedule`|-|
|access **question** workspace|`question`|-|

___  

## Command Summary
> ⚠️ To understand which command applies to which workspace mentioned below, 
> please refer to [Workspace Command Summary](#workspace-command-summary)

| Workspace   | Action | Format| Examples |
| ----------- | ----------- | ----------- |----------- |
|module management|add module|`add "<module_code>"`|`add "CS2113T"`|
|module management|update module |`update <index> "<new_module_code>"`|`update 1 "CS2113"`|
|note|add note|`add "<name>" "<content>"`|`add "Note1" "Hello world."`|
|note|export note|`export`|`export`|
|schedule|add schedule|`add "<description>" "<day>" "<start_time>" "<duration>" <zoom_link>"`|`add "CS2113T Tutorial 1" "Thursday" "10:00" "1" "https://zoom.us/test"`|
|question|add question|`add "<question>" "<answer>"`|`add "What is 1+1?" "2"`|
|question|active learning|`test {count}`|`test`, `test 3`|
|default|view timetable|`timetable {day}`|`timetable`, `timetable Thursday`|
|**ALL** except module|delete item|`delete <index>`|`delete 1`|
|**ALL** except module|view item information|`view {index}`|`view`, `view 1`|
|**ALL**|help|`help`|-|
|**ALL** except default|go back to previous workspace|`back`|-|
|**ALL**|exit the program|`exit`|-|

---



