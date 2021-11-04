# TermiNUS User Guide

## Table of Contents

- [Introduction](#introduction)
  - [Purpose of this Guide](#purpose-of-this-guide)
  - [Using this Guide](#using-this-guide)
- [Getting Started](#getting-started)
  - [Installing TermiNUS](#installing-terminus)
  - [Quick Guide of TermiNUS](#quick-guide-of-terminus)
- [Command Syntax](#command-syntax)
- [Section: Managing all your modules](#section-managing-all-your-modules)
    * [Accessing the module management workspace: `module`](#accessing-the-module-management-workspace)
    * [Adding a module : `add "<module_code>"`](#adding-a-module)
    * [Deleting a module : `delete <index>`](#deleting-a-module)
    * [Updating a module :`update <index> "<new_module_code>"`](#updating-a-module)
    * [Viewing module information `view`](#viewing-module-information)
- [Returning to previous workspace : `back`](#returning-to-previous-workspace)
- [Section: Specific Module](#section-specific-module)
    - [Accessing a specific module workspace : `go <module_code>`](#accessing-a-specific-module-workspace)
    - [Section: Note](#section-note)
        * [Accessing note workspace : `note`](#accessing-note-workspace)
        * [Adding a Note : `add "<name>" "<content>"`](#adding-a-note)
        * [Viewing note information : `view {index}`](#viewing-note-information)
        * [Deleting a Note : `delete <index>`](#deleting-a-note)
        * [Reloading all notes: `reload`](#reloading-all-notes)
        * [Exporting all notes: `export`](#exporting-all-notes)
    - [Section: Schedule](#section-schedule)
        * [Accessing schedule workspace : `schedule`](#accessing-schedule-workspace)
        * [Adding a Schedule : `add "<description>" "<day>" "<start_time>" "<duration>" "<zoom_link>"`](#adding-a-schedule)
        * [Viewing schedule information : `view {index}`](#viewing-schedule-information)
        * [Deleting a Schedule : `delete <index>`](#deleting-a-schedule)
    - [Section: Question](#section-question)
        * [Accessing question workspace : `question`](#accessing-question-workspace)
        * [Adding a Question : `add "<question>" "<answer>"` ](#adding-a-question)
        * [Viewing question information : `view {index}`](#viewing-question-information)
        * [Deleting a Question : `delete <index>`](#deleting-a-question)
        * [Testing Yourself with Active Recall : `test {count}`](#testing-yourself-with-active-recall)
- [Displaying all schedules across all modules : `timetable {day}`](#displaying-all-schedules-across-all-modules)
- [Exiting TermiNUS : `exit`](#exiting-terminus)
- [Accessing Help : `help`](#accessing-help)
- [Advanced Usage of Commands](#advanced-usage-of-commands)
- [FAQ](#faq)
- [Workspace Command Summary](#workspace-command-summary)
- [Command Summary](#command-summary)

## Introduction

TermiNUS is a CLI (command line interface) program for NUS students who wish to consolidate their
NUS academic needs such as schedules, questions and notes for the modules that they are taking.
With TermiNUS, it aims to aid students and improve their learning experiences while studying in NUS.

### Purpose of this Guide

This documents aims to provide you with instruction on how to use `TermiNUS` and tips & tricks
included to improve your experiences while using it. The document is for both beginners and advanced
users. The document will bring you through the installation process and guide you on all 
the commands available within `TerminNUS`.


### Using this Guide

Along the way, you might encounter these messages with icons throughout this guide:

> ⚠️A caution that you have to keep in mind when using the command.

> 💡 Take note when you see this icon, as it might tell you something important.

> ❗ Important things you should take note of before using the command.

Text that is blue like this example, are clickable [links](#test) that will bring you to the
relevant part of this user guide.

Terminologies used throughout this guide:

| Terminology | Description |
| ----------- | ----------- |
| workspace |  A workspace is the environment in which you are currently assessing. For example, when you want to access some files inside a folder, you will need to enter the folder first. As such the folder is a **workspace**.|
|workspace indicator|A workspace indicator shows the current workspace you are currently in when using `TermiNUS`. For example, in the expected output for commands, you will be able to see `[] >>>` which means you are in the **default workspace**, `[module] >>>` which means you are in the **module management workspace**. etc.|
|default workspace|The very first workspace when executing the `TermiNUS` program. For example, `[] >>>` indicates you are currently in the **default workspace**.|
|module|A module refers to a NUS module. For example, `CS2113T` is a module.|

---

## Getting Started

This section will detail the `TermiNUS` installation guide, as well a quick guide on how to use 
`TermiNUS`.

### Installing TermiNUS

1. Ensure that you
   have [Java 11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html) or
   above installed.
2. Download the latest version of `TermiNUS.jar`
   [here](https://github.com/AY2122S1-CS2113T-T10-2/tp/releases/).
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

7. For more information of each command, please refer to the [Features](#section-note) below.

### Quick Guide of TermiNUS

1. Before adding any **note**, **schedule** and **question**, we will need to have a **module** first in which these items belong to.
2. You can do so by adding a module within the **module management workspace**.  
   Please refer to: [Section: Managing all your Modules](#section-managing-all-your-modules).
3. After adding a module, you can proceed to add items such as **note**, **schedule** and **question** into it.
4. But firstly you will need to enter the **module workspace** in which you have just created.  
   Please refer to: [Section: Specific Module](#section-specific-module).
5. Once entering the **module workspace**, you can add the items related to the module here.
    1. Please refer to [Section: Note](#section-note) for any related **Note** item.
    2. Please refer to [Section: Schedule](#section-schedule) for any related **Schedule** item.
    3. Please refer to [Section: Question](#section-question) for any related **Question** item.
6. Once you are done, you can exit **TermiNUS** using the `exit` command.

Hope you enjoy your experience with TermiNUS!

---

## Command Syntax

> 💡 Commands are **case-insensitive**, but the `value` you provide can be **case-sensitive**.   
> Commands may also **ignore unnecessary data** appended behind commands.
> 
> Example: <code>HELP &nbsp;&nbsp;&nbsp;</code> is a valid command, and the spaces after `HELP` 
> will be ignored.

As you use this guide, you will notice commands with specific brackets around some values such as `{}` and `<>`.

The below table represents what each means.

| Command options | Description |
| ----------- | ----------- |
| `{value}` |  The value is optional, and including it may provide different results.|
| `<value>` | The value is required for the command to work properly.|
|`"<value>"`| Some command have `""` quotes in it, this is required for the command to work properly. For example, `add "<value>"` would need to be `add "something"`.|
|`index`|A number identifying an item in TermiNUS. This index can only be viewed using the `view` command.|
|`start_time`|The `start time` must be in a **HH:mm** format which follows the **24-hour notation**. For example, `14:20` is valid which represents `2:20 pm`.|
|`day`|The `day` must be a day spelled out fully and is case-insensitive. For example, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday` and `Sunday` are the only **7** valid days.|
|`duration`|The `duration` must be a **positive** integer and upon addition with `start_time`, it must not exceed the 24-hour limit of the day. For example, if `start_time` is `21:00`, `2` is a valid `duration` but `-1` and `4` are not.|

## Section: Managing all your modules

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
|view|view modules information currently in TermiNUS|
|delete|delete a module in TermiNUS|
|update|update a module code currently in TermiNUS|
|help|view all commands and their usage within the module management workspace|
|back|escape and return to the default workspace|
|exit|exit and closes TermiNUS|

### Adding a module

**Format:** `add "<module_code>"`

Adds a module when in the module management workspace.

> ⚠️The `<module code>` will be converted to **UPPERCASE** and cannot be more than **30** characters and cannot have any `spaces` in it.

> 💡 When executing this command, it will add the module into TermiNUS and creates a folder with the
> module code in **uppercase** (e.g. creating `cs2113t` will create a folder in `data` called `CS2113T`).

> ❗ If there exists a folder with the same code of the newly added module, all contents in that 
> folder will be wiped. [Please see the FAQ for more information.](#faq)

Example: `add "cs2113t"`

Expected Output:

```
[module] >>> add "cs2113t"
Module CS2113T has been added
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

### Deleting a module

**Format:** `delete <index>`

Deletes the specified module given by its **index** in the `view` command when in the module 
management workspace.

> ❗ When the specified module is being deleted, all contents in its folder will be wiped.  
> [Please see the FAQ for more information.](#faq)

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

---

## Returning to previous workspace

**Format:** `back`

Returns to the previous workspace prior to the current workspace you are in.

> 💡 The **default** workspace is the only workspace that has no `back` command. This is because this workspace is the very first workspace of TermiNUS.

Example: `back` from module workspace

Expected Output:

```
[CS2113T] >>> back

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

> 💡 Notice how the workspace indicator changes from `[CS2113T] >>>` to `[] >>>`.

---

## Section: Specific Module

All commands related to accessing the existing modules in TermiNUS. These commands enable users to
enter the module workspace in Terminus.

> 💡 The module mentioned here are the modules created within the **module management workspace**. Please refer to [Section: Module Management](#section-managing-all-your-modules) for more information.

### Accessing a specific module workspace

**Format:** `go <module_code>`

Enters the module workspace to access data within the module.

> ⚠ The module code is **case-insensitive** and will be converted to **UPPERCASE** as shown below.

Example: `go cs2113t`

Expected Output:

```
[] >>> go cs2113t

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
> reload
> back
> delete
> export

[CS2113T > note] >>> 
```

List of Note workspace commands:

| Command      | Description |
| ----------- | ----------- |
|add|add a note for the module|
|view|view notes information from the module|
|delete|delete a note from the module|
|reload|reload all the notes from the `data/<module code>` folder|
|export|export all current notes for the module as a pdf|
|help|view all commands and their usage in the note workspace|
|back|escape and return to the module workspace|
|exit|exit and closes TermiNUS|

### Adding a Note

**Format:** `add "<name>" "<content>"`

Adds a note when in the note workspace.

> ⚠️The `<name>` cannot be more than **30** characters.

> 💡 When executing this command, it will add the note into its module and creates a `.txt` file inside the module folder. The `.txt` file will be named after the newly added note name.

> ❗ If there is a file with the same name as the newly added note and is not registered in TermiNUS, 
> all contents in that file will be overwritten. 
> 
> To add that note, please refer to [Reloading all notes](#reloading-all-notes).

Example: `add "coding style" "switch case identation should be aligned."`

Expected Output:

```
[CS2113T > note] >>> add "coding style" "switch case identation should be aligned."
Your note on 'coding style' has been added!
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

### Reloading all notes

**Format:** `reload`

Reloads all the notes found within your `data/<module code>`.

> 💡 This command is useful when you have updated your notes file in another application, and would 
> wish to update the contents into TermiNUS.

Example: `reload`

Expected Output:

```
[CS2113T > note] >>> reload
Your Notes for CS2113T is being reloaded.
[CS2113T > note] >>> 
```

### Exporting all notes 

**Format:** `export`

For your convenience to print and bring physical notes to exams, you can export all notes within the
note workspace as a PDF file.

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
|view|view schedule information from the module|
|delete|delete a schedule from the module|
|help|view all commands and their usage in the schedule workspace|
|back|escape and return to the module workspace|
|exit|exit and closes TermiNUS|

### Adding a Schedule

**Format:** `add "<description>" "<day>" "<start_time>" "<duration>" "<zoom_link>"`

Adds a schedule when in the schedule workspace.

> ⚠️The `<day>` must be a valid **day spelled fully**. For example, `monday` is a valid day but `mon` is not.

> 💡 The accepted input for the `<day>` argument includes "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", and "Sunday".
 
> ⚠️The `<start_time>` must be in a **HH:mm** format which follows the **24-hour notation**. For example, `14:20` is valid which represents `2:20 pm`.

> ⚠️The `duration` must be a **positive** integer and upon addition with `start_time`, 
> it must not exceed the 24-hour limit of the day. For example, if `start_time` is `21:00`, `2` is a valid `duration` but `-1` and `4` are not.

> ⚠️The `<zoom_link>` must begin with a "http://" or "https://" in order for the link to be valid.

Example: `add "CS2113T Tutorial 1" "Thursday" "10:00" "2" "https://zoom.us/test"`

Expected Output:

```
[CS2113T > schedule] >>> add "CS2113T Tutorial 1" "Thursday" "10:00" "2" "https://zoom.us/test"
Your schedule on 'CS2113T Tutorial 1' has been added!
[CS2113T > schedule] >>> 
```

### Viewing schedule information

**Format:** `view {index}`

Views a list of schedules in the module when in the schedule workspace.

Example 1: `view`

Expected Output 1:

```
[CS2113T > schedule] >>> view
List of Content
---------------
1. CS2113T Tutorial 1 (Thursday, 10:00 - 12:00): https://zoom.us/test
2. CS2113T Lab 1 (Friday, 12:30 - 14:30): https://zoom.us/test

Rerun the same command with an index behind to view the content.
[CS2113T > schedule] >>> 
```

Example 2: `view 1`

Expected Output 2:

```
[CS2113T > schedule] >>> view 1
CS2113T Tutorial 1 (Thursday, 10:00 - 12:00): https://zoom.us/test
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

---

## Section: Question

All commands related to the workspace Question will be displayed in this section. These commands
enable users to create, delete and view questions within the module.

In addition, users can test themselves with the question added into the module.

List of Question workspace commands:

| Command      | Description |
| ----------- | ----------- |
|add|add a question for the module|
|view|view question(s) from the module|
|delete|delete a question from the module|
|test|test yourself with questions from the module|
|help|view all commands and their usage in the question workspace|
|back|escape and return to the module workspace|
|exit|exit and closes TermiNUS|

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

### Testing Yourself with Active Recall

**Format:** `test {count}`

Starts an Active Recall session.

You may specify the number of questions to be tested by adding a `{count}` at the end of the 
command. 

> ⚠ To start a session, there must be at least 1 question contained within the question workspace.

> 💡 By default, `{count}` will be set to **10** questions if left unspecified. 
> 
> If there are not enough questions, it will take the whole pool of questions in the module 
> (i.e. if you have 7 questions in your current module, you will only receive 7 questions when you 
> indicate a `{count}` greater than 7 or leave it empty).

When you begin, you will be prompted with the following:

```
[CS2113T > question] >>> test 3
---[Active Recall]---

We will be starting your active recall training session.
This session will consist of 3 questions.

When you are ready, press [Enter] to continue.
```

Press the <kbd>Enter</kbd> key to start.
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

> ⚠️The `<day>` must be a valid **day spelled fully**. For example, `monday` is a valid day but 
> `mon` is not.
> 
> You may view [Adding a Schedule](#adding-a-schedule) for the valid list of days.

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

> 💡 To access the module in TermiNUS, you will need to use the `go` command. For more information, 
> please refer to [Section: Specific Module](#section-specific-module).

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

**Q:** (Windows) What will happen if I create a new module when I have an existing folder with the same module code in the `data` directory?  
**A:** Regardless of **case sensitivity** (i.e. `cS2113T` or `CS2113T`), the files within the folder will be deleted.

**Q:** (MacOS/Linux) What will happen if I create a new module when I have an existing folder with the same module code in the `data` directory?  
**A:** Only the files within the **uppercase** folder (i.e. `CS2113T`) will be deleted. If there are folders with mixed or
lowercase (i.e. `cS2113t` or `cs2113t`), the **content within will be ignored** (for loading, saving and deleting).

**Q:** Why is my module folder in the `data` folder not deleted?  
**A:** There can be 2 reason. Firstly, there are files inside the folder that are locked by the 
operating system. This means there might be other applications using the file, which is preventing 
TermiNUS from deleting the folder cleanly. Usually restarting the computer and deleting it will 
solve the issue. Secondly, if you are running macOS or Linux, we do not delete folders that are 
**not uppercase** (e.g. `CS2113T` will be deleted but `CS2113t` will not be). 

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
|note|reload notes|`reload`|`reload`|
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



