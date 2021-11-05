# SchedUrMods User Guide
SchedUrMods is a **desktop application for NUS Students to manage their NUSMODS timetable and
everyday tasks optimised for usage via a Command Line Interface (CLI).** If you can type fast, 
SchedUrMods can help you manage your daily tasks faster than traditional GUI application.  

Below is a guide on how you can get started using our program to start **"Scheduling Ur Mods"**.  

## Overview <a id="scrollToHere"></a>
- [1. Quick Start](#1-quick-start)
- [2. Features](#2-features)
    - [2.1 Viewing help: `help`](#21-viewing-help--help)
    - [2.2 Adding your tasks:](#22-adding-your-tasks)
      - [2.2.1 Todo: `todo`](#221-todo-todo)
      - [2.2.2 Deadline: `deadline`](#222-deadline-deadline)
      - [2.2.3 Event: `event`](#223-event-event)
      - [2.2.4 Date Format: `<dateFormat>`](#224-date-format-dateformat)
    - [2.3 Adding your NUS timetable:](#23-adding-your-nus-timetable)
      - [2.3.1 Module: `module`](#231-module-module)
      - [2.3.2 Browse: `browse`](#232-browse-browse)
    - [2.4 Listing your tasks: `list`](#24-listing-your-tasks-list)
      - [2.4.1 Listing your entire tasklist](#241-listing-your-entire-tasklist)
      - [2.4.2 Filtering your tasklist](#242-filtering-your-tasklist)
      - [2.4.3 Listing the recurrence of a task](#243-listing-the-recurrence-of-a-task)
    - [2.5 Sorting your tasklist: `sort`](#25-sorting-your-task-list-sort)
    - [2.6 Setting reminders to your tasks: `reminder`](#26-setting-reminders-to-your-tasks-reminder)
    - [2.7 Editing your tasks: `edit`](#27-editing-your-tasks-edit)
    - [2.8 Deleting your tasks: `delete`](#28-deleting-your-tasks-delete)
    - [2.9 Exiting the program: `bye`](#29-exiting-the-program-bye)
    - [2.10 Storage](#210-storage)
- [3. FAQ](#3-faq)
- [4. Command Summary](#4-command-summary)

## 1. Quick Start
1. Ensure you have Java **11** or above installed in your Computer, 
and that `java` is in your `PATH` environment variable.
2. Download the latest `SchedUrMods.jar` from [here](https://github.com/AY2122S1-CS2113T-W13-3/tp/releases/latest).

   > **⚠️Warning**: Do *not* decompress the jar file even if it shows as an archive on your file manager.
3. Copy the file to the folder you want to use as the _home folder_ for Duke.
   >💡 **Note**: Ensure you have permission to create files and folders in this _home folder_.
5. Open a terminal window in the _home folder_.
6. Type `java -jar SchedUrMods.jar` into the terminal to start Duke.
7. The following display should appear in a few seconds:

```
 _____        _                _  _   _       ___  ___            _
/  ___|      | |              | || | | |      |  \/  |           | |
\ `--.   ___ | |__    ___   __| || | | | _ __ | .  . |  ___    __| | ___
`--.  \ / __|| '_ \  / _ \ / _` || | | || '_| | |\/| | / _ \  / _` |/ __|
/\__/ /| (__ | | | ||  __/| (_| || |_| || |   | |  | || (_) || (_| |\__ \
\____/  \___||_| |_| \___| \__,_| \___/ |_|   \_|  |_/ \___/  \__,_||___/
-------------------------------------------------------------------------
Command-Line Interface for NUSMODS                               (v2.1.0)
-------------------------------------------------------------------------
[user]:
```

7. You may now enter commands into SchedUrMods. Type a command beside
`[user]:` and press `Enter` on your keyboard to execute the command.
8. Refer to the [Features](#2-features) below for details of each command.

## 2. Features
### 2.1 Viewing help : `help`
Displays all commands available for the SchedUrMods application.

**Format**: `help`

### 2.2 Adding your tasks:

> #### 📝 **Some notes when adding tasks**:
> - All commands are case-insensitive. For example, `todo` is the same as `Todo` or `TODO`
> - Command arguments within `< >` are **mandatory** fields and must be followed strictly.
> - Command arguments within `[ ]` are **optional** fields and may be arranged in any order or left out.
> - Command arguments such as `<a|b|c>` Means that the argument must **strictly** either be `a`, `b` or `c`. 
> - If `[--priority <high|medium|low>]` flag is not used, the **default priority** will be set to `medium`. 
> - If `[--recur <daily|weekly|monthly|yearly>]` flag is not used, the **default recurrence** will be set to `none`. 

### 2.2.1 Todo: `todo`
Adds your **todo** to your task list.

**Format**: `todo <description> [--flag <argument>]`
- `<description>` specifies the description of your todo.
- `[--flag <argument>]` specifies optional details of your todo.
  - When your task is to be done: <code>[--doOn <a href="#224-date-format-dateformat">&lt;dateFormat&gt;</a>]</code>
     >💡 **Note**: if flag is unused, date will be set to the current time rounded up to the nearest hour.
  - The priority of your task: `[--priority high|medium|low]`
  - How often your task is to repeat: `[--recur daily|weekly|monthly|yearly]`

**Example**: `todo read book --priority low --doOn 20-10-2021 02:00 --recur daily`
- Adds a todo task with description `read book` to the task list.
- Specifies the priority to be `low`.
- Specifies the task is to be done on `20-10-2021 02:00`.
- Specifies the task is to repeat `daily`.

**Expected Outcome**:

```
-------------------------------------------------------------------------
[user]: todo read book --priority low --doOn 20-10-2021 02:00 --recur daily
|| Task created!
|| [T] read book <low> {daily} (doOn: 20-10-2021 02:00)
-------------------------------------------------------------------------
```

### 2.2.2 Deadline: `deadline`
Adds your **deadline** to your task list.

**Format**: `deadline <description> <--due dd/MM/yyyy HH:mm> [--flag <argument>]`
- `<description>` specifies the description of your deadline.
- <code>&lt;--due <a href="#224-date-format-dateformat">&lt;dateFormat&gt;</a>&gt;</code> specifies when your deadline is.
- `[--flag <argument>]` specifies optional details of your deadline.
  - The priority of your task: `[--priority <high|medium|low>]`
  - How often your task is to repeat: `[--recur <daily|weekly|monthly|yearly>]`

**Example**: `deadline CS2106 Lab 3 --priority high --due 20-10-2021 02:00`
- Adds a deadline task with description `CS2106 Lab 3` to the task list.
- Specifies the priority to be `high`.
- Specifies the task is to be done on `20-10-2021 02:00`.
- Specifies the task to have no recurrence.

**Expected Outcome**:

```
-------------------------------------------------------------------------
[user]: deadline CS2106 Lab 3 --priority high --due 20-10-2021 02:00
|| Task created!
|| [D] CS2106 Lab 3 <high> {none} (dueDate: 20-10-2021 02:00)
-------------------------------------------------------------------------
```

### 2.2.3 Event: `event`
Adds your **event** to your task list.

**Format**: `event <description> <--start dd/MM/yyyy HH:mm> <--end dd/MM/yyyy HH:mm> [--flag <argument>]`
- `<description>` specifies the description of your event.
- <code>&lt;--start <a href="#224-date-format-dateformat">&lt;dateFormat&gt;</a>&gt;</code> specifies when your event starts.
- <code>&lt;--end <a href="#224-date-format-dateformat">&lt;dateFormat&gt;</a>&gt;</code> specifies when your event ends.
- `[--flag <argument>]` specifies optional details of your event.
  - The priority of your task: `[--priority <high|medium|low>]`
  - How often your task is to repeat: `[--recur <daily|weekly|monthly|yearly>]`

**Example**: `event Marquee Christmas Party --priority high --start 25-12-2020 22:00 --end 26-12-2020 04:00`
- Adds an event task with description `Marquee Christmas Party` to the task list.
- Specifies the priority to be `high`.
- Specifies the task to start on `25-12-2020 22:00` and end on `26-12-2020 04:00`.
- with no recurrence.

**Expected Outcome**:

```
-------------------------------------------------------------------------
[user]: event Marquee Christmas Party --priority high --start 25-12-2020 22:00 --end 26-12-2020 04:00
|| Task created!
|| [E] Marquee Christmas Party <high> {none} (startDate: 25-12-2020 22:00 - endDate: 26-12-2020 04:00)
-------------------------------------------------------------------------
```

### 2.2.4 Date Format: `<dateFormat>`
The accepted date formats in our program:  
- `dd/MM/yyyy HH:mm` or `dd/MM/yy HH:mm`
- `dd/MM/yyyy HH` or `dd/MM/yy HH`
- `dd/MM HH:mm`
- `dd/MM HH`
- `dd HH:mm`
- `dd HH`
- `HH`  

**Legend**:
- `dd` represents the **day** the of month.
- `MM` represents the **month** the of year.
- `yyyy` represents the **year**.
- `yy` represents the **year** (in the 21st century)
    -  e.g. `04` represents 2004.
- `HH` represents the **hour** of the day on a 24-hour clock.
- `mm` represents the **minute** of the hour.

>💡 **Note**: When day (`dd`), month (`MM`) or year (`yyyy` or `yy`) is unspecified, they will be replaced with the **current** day, month and year respectively.  

>💡 **Note**: When minute (`mm`) is unspecified, it will be replaced with `0`.

**Examples**:
- `03/10/2004 03` translates to `3rd October 2004, 3:00am`.
- `15:30` translates to `Your current year, month and day at 3.30pm`.
- `15 12:30` translates to `Your current year and month on the 15th at 12.30pm`.

### 2.3 Adding your NUS timetable:

#### 2.3.1 Module: `module`

#### 2.3.2 Browse: `browse`

### 2.4 Listing your tasks: `list`

There are **3 main features** you can use with the list command.

#### 2.4.1 Listing your entire tasklist
List all tasks currently stored locally in your tasklist.

**Format**: `list`
- The command displays the following information for each task:
  - **Task id**:
    - A positive integer i.e. `1.` to identify the task
  - **Task type**:
    - A task can be either Todo, Deadline, Event or Lesson and is represented with the first letter of their task type
    - `[T]` represents a `Todo`
    - `[D]` represents a `Deadline`
    - `[E]` represents a `Event`
    - `[L]` represents a `Lesson`
  - **Task description**:
    - General details of the task
  - **Task priority `[priority]`**:
    - A task can either have `low`, `medium`, or `high` priority. 
    - This value determines the urgency of the task.
  - **Task recurrence ``**:
    - A task can either have `none`, `daily`, `weekly`, `monthly`, or `yearly` recurrence.
    - This value determines how often a task recurs.
  - **Task date field**:
    - For a `Todo`, it represents the datetime to perform the task.
    - For a `Deadline`, it represents the due date of the task.
    - For an `Event`, it represents the date and duration of the event.
    - For a `Lesson`, it represents the date and duration of the lesson.
    
**Example**: `list`
- List all tasks currently stored locally in your tasklist.

**Expected Outcome**:

```
[user]: list
|| -------------
||  MY TASKLIST
|| -------------
|| 1. [T] read book <low> {daily} (doOn: 05-11-2021 02:00)
|| 2. [D] return book <medium> {weekly} (dueDate: 11-11-2021 03:00)
|| 3. [E] project meeting <high> {monthly} (startDate: 22-11-2021 04:00 - endDate: 22-11-2021 05:00)
|| 4. [T] exercise <medium> {none} (doOn: 04-11-2021 18:00)
|| 5. [T] wash clothes <medium> {weekly} (doOn: 10-11-2021 02:00)
|| 6. [D] project submission <high> {monthly} (dueDate: 21-11-2021 03:00)
|| 7. [E] movie screening <low> {daily} (startDate: 05-11-2021 04:00 - endDate: 05-11-2021 05:00)
```

#### 2.4.2 Filtering your tasklist
Filters the tasklist for specific tasks that matches the filters applied.

**Format**: `list [--filter <argument>] [--filter <argument>] ...`
- `[--flag <argument>]` specifies the filter to be applied to your task list.
  - Type of task: `[--type <todo|deadline|event|lesson>]`
  - Priority of the task: `[--priority <low|medium|high>]`
  - Recurrence of the task: `[--recur <none|daily|weekly|monthly|yearly>]`

**Example**: `list --type todo --priority medium`
- List all tasks that are type `Todo` and priority is `medium`.

**Expected Outcome**:

```
[user]: list --type todo --priority medium
|| -------------
||  MY TASKLIST
|| -------------
|| 1. [T] exercise <medium> {none} (doOn: 04-11-2021 18:00)
|| 2. [T] wash clothes <medium> {weekly} (doOn: 10-11-2021 02:00)
```

#### 2.4.3 Listing the recurrence of a task
Displays the next 4 recurrences of a task.

**Format 2**: `list [task id]`
- `[task id]` specifies the id of the task to display the recurrence.

**Example (with task id)**: `list 3`
- List the next 4 recurrences for task with task id `3`.

**Expected Outcome**:

```
[user]: list 3
|| -------------
||  MY TASKLIST
|| -------------
|| Listing next 4 recurrences for:
|| [E] project meeting <high> {monthly} (startDate: 22-11-2021 04:00 - endDate: 22-11-2021 05:00)
|| -> 22-12-2021 04:00
|| -> 22-01-2022 04:00
|| -> 22-02-2022 04:00
|| -> 22-03-2022 04:00
```

>💡 **Note**: To obtain the correct task id of each task, please use the `list` command without any filters.

### 2.5 Sorting your task list: `sort`
Sorts your task list by a given criteria.

**Format**: `sort --by <criteria>`
- `<criteria>` specifies what to sort your task list by.
  - Type of task: `type`.
  - Description of task: `description`
  - Priority of the task: `priority`

**Example**: `sort --by priority` + `list`
- Sort your current task list by `priority` from `high` to `low`
- Print out the sorted list with the `list` command

**Expected Outcome**:

```
[user]: sort --by priority
|| [!] Tasklist has been sorted by: priority
-------------------------------------------------------------------------
[user]: list
|| -------------
||  MY TASKLIST
|| -------------
|| 1. [E] project meeting <high> {monthly} (startDate: 22-11-2021 04:00 - endDate: 22-11-2021 05:00)
|| 2. [D] project submission <high> {monthly} (dueDate: 21-11-2021 03:00)
|| 3. [D] return book <medium> {weekly} (dueDate: 11-11-2021 03:00)
|| 4. [T] exercise <medium> {none} (doOn: 04-11-2021 18:00)
|| 5. [T] wash clothes <medium> {weekly} (doOn: 10-11-2021 02:00)
|| 6. [T] read book <low> {daily} (doOn: 05-11-2021 02:00)
|| 7. [E] movie screening <low> {daily} (startDate: 05-11-2021 04:00 - endDate: 05-11-2021 05:00)
```

### 2.6 Setting reminders to your tasks: `reminder`

### 2.7 Editing your tasks: `edit`
Edits a single [Todo](#221-todo-todo), [Deadline](#222-deadline-deadline) or [Event](#223-event-event) based on the flags entered.
**Format**: `edit <index> <--flag <value>> [--flag <value>] [--flag <value>]...`
- `<index>` is the index of the task you want to edit from the most recent [list](#24-listing-your-tasks-list) command that you have run.
   >💡 **Note**: if the task you want to edit was not in your most recent [list](#24-listing-your-tasks-list) command, simply type `list` to list all tasks and use its corresponding index to edit it.
- at least one `<--flag <value>>` must be specified, though you can edit multiple values at once by providing multiple more `[--flag <value>]` arguments.
- **Flags**
  - `--description <description>` modifies Task description.
  - `--priority <high|medium|low>` modifies Task priority.
  - `--recur <daily|weekly|monthly|yearly>` modifies Task recurrence.
  - <code>--doOn <a href="#224-date-format-dateformat">&lt;dateFormat&gt;</a></code> modifies when [Todo](#221-todo-todo) is to be done.
  - <code>--due <a href="#224-date-format-dateformat">&lt;dateFormat&gt;</a></code> modifies when [Deadline](#222-deadline-deadline) is due.
  - <code>--start <a href="#224-date-format-dateformat">&lt;dateFormat&gt;</a></code> modifies when [Event](#223-event-event) starts.
  - <code>--end <a href="#224-date-format-dateformat">&lt;dateFormat&gt;</a></code> modifies when [Event](#223-event-event) ends.
  - `--link <url>` modifies your Lesson link.

### 2.8 Deleting your tasks: `delete`
Deletes Tasks specified in comma-seperated argument.
**Format**: `delete <indexes>`
- `<indexes>` are the indexes of the task you want to edit from the most recent [list](#24-listing-your-tasks-list) command that you have run.
   >💡 **Note**: if the task(s) you want to delete was not in your most recent [list](#24-listing-your-tasks-list) command, simply type `list` to list the tasks and use their indexes to delete them.
  - Indexes should be comma seperated single integers such as `5` and/or ranges of two indexes such as `1-3`.
  - **Example**:
   - `delete 1,3,5` would delete tasks with indexes 1, 3 and 5.
   - `delete 1-3` would delete tasks with indexes 1, 2 and 3.
   - `delete 1-3, 5` would delete tasks with indexes 1, 2, 3 and 5.
      >💡 **Note**: Whether there are any spaces between the commas, it does not matter.


**Example**: `delete 1-3, 5`
- delete tasks with indexes 1, 2, 3 and 5.

**List before deletion**:

```
-------------------------------------------------------------------------
[user]: list
|| -------------
||  MY TASKLIST
|| -------------
|| 1. [T] read book <low> {none} (doOn: 20-10-2021 02:00)
|| 2. [T] return book <low> {none} (doOn: 21-10-2021 03:00)
|| 3. [E] project meeting <high> {none} (startDate: 22-10-2021 04:00 - endDate: 22-10-2021 05:00)
|| 4. [T] wash clothes <medium> {none} (doOn: 20-10-2021 02:00)
|| 5. [D] homework <low> {none} (dueDate: 21-10-2021 03:00)
|| 6. [E] movie screening <low> {none} (startDate: 22-10-2021 04:00 - endDate: 22-10-2021 05:00)
-------------------------------------------------------------------------
```
**Expected Outcome**:

```
-------------------------------------------------------------------------
[user]: delete 1-3,5
|| Task(s) deleted:
|| [T] read book <low> {none} (doOn: 20-10-2021 02:00)
|| [T] return book <low> {none} (doOn: 21-10-2021 03:00)
|| [E] project meeting <high> {none} (startDate: 22-10-2021 04:00 - endDate: 22-10-2021 05:00)
|| [D] homework <low> {none} (dueDate: 21-10-2021 03:00)
-------------------------------------------------------------------------
```

**List after deletion**:

```
-------------------------------------------------------------------------
[user]: list
|| -------------
||  MY TASKLIST
|| -------------
|| 1. [T] wash clothes <medium> {none} (doOn: 20-10-2021 02:00)
|| 2. [E] movie screening <low> {none} (startDate: 22-10-2021 04:00 - endDate: 22-10-2021 05:00)
-------------------------------------------------------------------------
```

### 2.9 Exiting the program: `bye`
Displays goodbye message and exits the program.

An end-of-file condition in the input, such as <kbd>Ctrl-D</kbd> on Bash, has the same effect as this command.

**Format**: `bye`

**Expected Outcome**:

```
-------------------------------------------------------------------------
[user]: bye
|| Exiting program!
-------------------------------------------------------------------------
```

### 2.10 Storage
The program will store and automatically update your Tasks in the the file `./data/task.dat` on any change in your Task list.  
   > **⚠️Warning**: The team **strongly discourages** users from directly modifying Task data in `task.dat` as it can lead to undefined behaviour. The team **will not be responsible** for any *loss of data* or *undefined behaviour* as a result of users directly modifying values in the save file.


## 3. FAQ
**Q**: What operating systems can the application work on?

**A**: The SchedUrMods application supports all Windows, Linux and Unix operating systems 
that have `java 11` installed on their systems. 

**Q**: How do I transfer my data to another computer?

**A**: Install the program on the other computer and replace the new empty `tasks.dat`
it creates with the `tasks.dat` that you wish to transfer from your previous `SchedUrMods.jar`
home folder.

## 4. Command Summary

Action   | Format                                           | Example                                                                                              |
-------- | -------------------------------------------------| -----------------------------------------------------------------------------------------------------|
help     | `help`                                           | `help`                                                                                               |
todo     | `todo <description> [--flag <argument>]`         | `todo read book`                                                                                     |
deadline | `deadline <description> [--flag <argument>]`     |`deadline CS2106 Lab 3 --priority high --due 20-10-2021 02:00:00`                                     |
event    | `event <description> [--flag <argument>]`        | `event Marquee Christmas Party --priority high --start 25-12-2020 22:00:00 --end 26-12-2020 04:00:00`|
module   |                                                  |                                                                                                      | 
browse   |                                                  |                                                                                                      |
list     | `list [--filter <argument>]` or `list [task id]` | `list` or `list --type todo --priority medium` or `list 3`                                           |
reminder |                                                  |                                                                                                      |
sort     | `sort --by <criteria>`                           | `sort --by priority`                                                                                 |
delete   | `delete <indexes of tasks to delete>`            | `delete 1, 2, 4-7`                                                                                   |
bye      | `bye`                                            | `bye`                                                                                                |
