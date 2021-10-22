# TermiNUS User Guide

## Table of Contents

* [Getting Start](#Getting-Started)
* [Section: Note](#Section:-Note)
    * [Accessing Note : `note`](#Accessing-Note)
    * [Adding a note : `add "<name>" "<content>"`](#Adding-a-Note)
    * [Delete a note : `delete <index>`](#Delete-a-Note)
    * [View note : `view {index}`](#View-Note)
* [Section: Schedule](#Section:-Schedule)
    * [Accessing Schedule : `schedule`](#Accessing-Schedule)
    * [Adding a Schedule : `add "<description>" "<day>" "<start_time>" "<zoom_link>"`](#Adding-a-Schedule)
    * [Delete a Schedule : `delete <index>`](#Delete-a-Schedule)
    * [View Schedule : `view`](#View-Schedule)
* [Section: Question](#Section:-Question)
    * [Accessing Question : `note`](#Accessing-Note)
    * [Adding a Question : `add "<question>" "<answer>"`](#Adding-a-Question)
    * [Delete a Question : `delete <index>`](#Delete-a-Question)
    * [View Question : `view {index}`](#View-Question)
    * [Testing Yourself with Active Recall: `test {count}`](#Testing-Yourself-with-Active-Recall)
* [Exiting the Program: `exit`](#Exiting-the-Program)
* [Accessing Help: `help`](#Accessing-Help)
* [FAQ](#faq)
* [Command Summary](#Command-Summary)
* [Advanced Command Summary](#Advanced-Command-Summary)

## Introduction

TermiNUS is a CLI (command line interface) program for NUS Students who wish to consolidate their
NUS academic materials such as zoom links, questions and notes for the modules that they are taking.
With TermiNUS, it aims to aid Students and improve their learning experiences while studying in NUS.

## Purpose

This documents aims to provide you with instruction on how to use `TermiNUS` and tips & tricks
included to improve your experiences while using it. The document will bring you through a detailed
guide on all existing commands as well as aiding you in installing `TerminNUS`.

---

## Getting Started

1. Ensure that you
   have [Java 11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html) or
   above installed.
2. Download the latest version of `TermiNUS.jar`
   from [here](https://github.com/AY2122S1-CS2113T-T10-2/tp/releases/).
3. Copy the file to the folder you want to use for `Terminus`.
    1. This is because `Terminus` will store all its saved data in the folder it is currently in.
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
8. For more information of each command, please refer to the [Features](#Section:-Note) below.

___

## Using this Guide

Along the way, you might encounter commands with specific brackets around some values such as `{}`
and `<>`.

The below table represents what each means.

| Command options | Description |
| ----------- | ----------- |
| `{value}` |  The value is optional, and including it may provide different results.|
| `<value>` | The value is required for the command to work properly.|
|`index`|A number identifying an item in TermiNUS. This index can only be viewed using the `view` command.|

Terminologies used throughout this guide:

| Terminology | Description |
| ----------- | ----------- |
| workspace |  A workspace is the environment in which you are currently assessing. For example, when you want to access some files inside a folder, you will need to enter the folder first. As such the folder is a **workspace**.|
|module|A module refers to a NUS module. For example, `CS2113T` is a module.|

---

## Section: Module Management

All commands related to the workspace `Module Management` will be displayed in these section. These commands
enable users to create, delete and view `modules` within TermiNUS.

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
> delete

[module] >>> 
```

List of Module Management workspace commands:

| Command      | Description |
| ----------- | ----------- |
|add|add a module in TermiNUS|
|delete|delete a module in TermiNUS|
|view|view modules information currently in TermiNUS|
|help|view all commands and their usage within the module management workspace|
|back|escape and return to the default workspace|
|exit|exit and closes TermiNUS|

### Adding a new module

**Format:** `add "<module code>"`

Adds a module when in the module management workspace.

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

**Format:** `go <module code>`

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

All commands related to the workspace `Note` will be displayed in these section. These commands
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

[CS2113T > note] >>> 
```

List of Note workspace commands:

| Command      | Description |
| ----------- | ----------- |
|add|add a note for the module|
|delete|delete a note from the module|
|view|view notes information from the module|
|help|view all commands and their usage in the note workspace|
|back|escape and return to the module workspace|
|exit|exit and closes TermiNUS|

### Adding a Note

**Format:** `add "<name>" "<content>"`  

Adds a note when in the note workspace.

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

Views a list of note in the module or views all information for that specific note.

Two ways to use this command simply running view or view [index] View by itself will list all notes

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

---

## Section: Schedule

### Accessing Schedule

**Format:** `schedule`  
After running the schedule command, you can see the following:

```
[] >>> schedule
You have 0 link(s) in this workspace.

Type any of the following to get started: 
> add 
> edit
> help
> view
> back
> delete 

[schedule] >>> 
```

### Adding a Schedule

**Format:** `add "<description>" "<day>" "<start_time>" "<zoom_link>"`  
Adding a new schedule when in the schedule’s workspace

```
[schedule] >>> add “Module1 Tut1” "Thursday" "10:00" "https://zoom.us/test"  
You have added Module1 Tut’s scheduled zoom link! 
[schedule] >>> 
```

### Delete a Schedule

**Format:** `delete <index>`  
Delete schedule when in the schedule’s workspace

```
[schedule] >>> delete 1 
You have deleted your 1st schedule. 
Schedule `Module1 Tut, Thursday, 10:00, https://zoom.us/test` has been deleted! 
[schedule] >>> 
```

### View Schedule

**Format:** `view`  
View all schedules when in the schedule’s workspace

```
[schedule] >>> view 
You have 3 schedules inside: 
1. Module1 Tut, Thursday, 10:00, https://zoom.us/test 
2. Module2 Lecture, Friday, 14:00, https://zoom.us/test 
3. Module1 Tut1, Thursday, 10:00, https://zoom.us/test

[schedule] >>> 
```

## Section: Question

### Accessing Question

**Format:** `question`  
Accessing the question workspace After running the question command, you can see the following:

```
[] >>> question

You have 0 question(s) in this workspace.

Type any of the following to get started:
> add
> exit
> help
> view
> test
> back
> delete
 
[question] >>> 
```

### Adding a Question

**Format:** `add "<question>" "<answer>"`  
Adding a question when in the question workspace

```
[question] >>> add "What is 1+1?" "2" 
Your question on 'What is 1+1?' has been added!
[question] >>> 
```

### Delete a Question

**Format:** `delete <index>`  
Deletes the specified question given by its index.

```
[question] >>> delete 1
Your question on 'What is 1+1?' has been deleted!
[question] >>> 
```

### View Question

**Format:** `view` or `view {index}`  
Two ways to use this command simply running view or view [index]
View by itself will list all questions

```
[question] >>> view 
List of Content
---------------
1. What is segmentation?
2. What is paging?
3. What is the pro of Fixed Partitioning in Contiguous Memory allocation?

Rerun the same command with an index behind to view the content.
[question] >>> 
```

The second way to use view is with an index view [index]

```
[question] >>> view 1 
Name: What is segmentation?
Content:
Placing different data regions into different frames

[question] >>> 
```

### Testing Yourself with Active Recall

**Format:** `test` or `test {count}`  
You can start an Active Recall session by running the `test` command.

By default, it will test 10 questions (or less if there are not enough questions). You may specify
how many questions you wish to run in that session by keying in the number of questions after the
`test` command.

When you begin, you will be prompted with the following (do note that the question pool may be
smaller if there are not enough questions in the workspace). Press the <kbd>Enter</kbd> key to
start.

```
[question] >>> test 3
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
What is segmentation?

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
Placing different data regions into different frames

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
[question] >>> 
```

## Exiting the Program

**Format:** `exit`  
To exit the program, simply run the following command:

```
[] >>> exit 
Goodbye! 
```

## Accessing Help

**Format:** `help`  
Depending on your current workspace, you may get different help messages.  
The following shows the help message in the main workspace:

```
[] >>> help 

You can run the following commands in the workspace: 
> note 
    - Access all your notes that you have made.  
> schedule 
    - Access all your schedules that you have scheduled. 
> help 
    - Prints this. 
> quit 
    - Quits TermiNUS 

You can also run the following to quickly do certain tasks: 
> note add Water “Drinking more water will make me hydrated” 
> schedule view 

Running `help [command]` will print the help for the specific workspace. 

[] >>> 
```

## Advanced Usage of Commands

User can access workspace command directly without entering its environment. Seen below are some
command examples.

A workspace command is a command that will bring you to its own workspace. Current workspace command
includes notes and schedules.

Command syntax: <workspace> <available workspace command>

Adding a note without entering the note workspace.

```
[] >>> note add “Remind Cabbin” “Cabbin was here” 
Note has been added! 
[] >>> 
```

Adding a schedule without entering the schedule workspace.

```dtd
[] >>> schedule add “Module1 Tut” Thursday 10:00 https://zoom.us/test
  You have added Module1 Tut’s scheduled zoom link!
  [] >>> 
```

___  

## FAQ

___  

## Command Summary

| **Action** | **Format, Examples** |
| ------------ | -------------  |
|**access note workspace**|`note`|
|**access schedule workspace**|`schedule`|
|**add**|`add "<name>" "<content>"` <br>e.g. `add note1 note_content`|
|**delete**|`delete <index>` <br>e.g. `delete 1`|
|**view**|`view` or `view {index}`<br>e.g. `view` or `view 1`|
|**help**|`help`|
|**exit**|`exit`|

___  

## Advanced Command Summary

| **Action** | **Format, Examples** |
| ------------ | ------------- |
|**add note**|`note add "<name>" "<content>"` <br>e.g. `note add note1 note_content`|
|**add
schedule**|`schedule add "<description>" "<day>" "<start_time>" "<zoom_link>"` <br>e.g. `schedule add “Module1 Tut” "Thursday" "10:00" "https://zoom.us/test"`|
|**delete note**|`note delete <index>` <br>e.g. `note delete 1`|
|**delete schedule**|`schedule delete <index>` <br>e.g. `schedule delete 1`|
|**view note**|`note view` or `note view {index}` <br>e.g. `note view 1`|
|**view schedule**|`schedule view` <br>e.g. `schedule view`|
















