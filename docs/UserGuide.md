# SchedUrMods User Guide
SchedUrMods is a **desktop application for NUS Students to manage their NUSMODS timetable and
everyday tasks optimised for usage via a Command Line Interface (CLI).** If you can type fast, 
SchedUrMods can help you manage your daily tasks faster than traditional GUI application.  

Below is a guide on how you can get started using our program to start **"Scheduling Ur Mods"**.  

## Overview
- [1. Quick Start](#1-quick-start)
- [2. Features](#2-features)
    - [2.1 Viewing help: `help`](#2-1-viewing-help--help)
    - [2.2 Adding your task:](#22-adding-your-task)
      - [2.2.1 Todo: `todo`](#221-todo-todo)
      - [2.2.2 Deadline: `deadline`](#222-deadline-deadline)
      - [2.2.3 Event: `event`](#223-event-event)
      - [2.2.4 Date Format: `<dateFormat>`](#224-date-format-dateformat)
    - [2.3 Listing your tasks: `list`](#23-listing-your-tasks-list)
      - [2.3.1 Listing your entire tasklist](#231-listing-your-entire-tasklist)
      - [2.3.2 Filtering your tasklist](#232-filtering-your-tasklist)
      - [2.3.3 Listing the recurrence of a task](#233-listing-the-recurrence-of-a-task)
    - [2.4 Sorting your task list: `sort`](#24-sorting-your-task-list-sort)
    - [2.5 Deleting your tasks: `delete`](#25-deleting-your-tasks-delete)
    - [2.6 Exiting the program: `bye`](#26-exiting-the-program-bye)
- [3. FAQ](#3-faq)
- [4. Command Summary](#4-command-summary)

## 1. Quick Start
1. Ensure you have Java **11** or above installed in your Computer, 
and that `java` is in your `PATH` environment variable.
2. Download the latest `SchedUrMods.jar` from [here](https://github.com/AY2122S1-CS2113T-W13-3/tp/releases/latest).

   > **⚠️Note:** Do *not* decompress the jar file even if it shows as an archive on your file manager.
3. Copy the file to the folder you want to use as the _home folder_ for Duke.
4. Open a terminal window in the _home folder_.
5. Type `java -jar SchedUrMods.jar` into the terminal to start Duke.
6. The following display should appear in a few seconds:

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

**Format:** `help`

### 2.2 Adding your task:

> #### 📝 **Some notes when adding tasks**:
> - All commands are case-insensitive. For example, `todo` is the same as `Todo` or `TODO`
> - Command arguments within `< >` are mandatory fields and must be followed strictly.
> - Command arguments within `[ ]` are optional fields and may be arranged in any order or left out.

### 2.2.1 Todo: `todo`
Adds your **todo** task to your task list.

**Format:** `todo <description> [--flag <argument>]`
- `<description>` specifies the description of your todo.
- `[--flag <argument>]` specifies additional details of your todo.
  - When your task is to be done: `[--doOn `[`<dateFormat>`](#224-date-format-dateformat)`]`
  - The priority of your task: `[--priority high|medium|low]`
  - How often your task is to repeat: `[--recur daily|weekly|monthly|yearly]`

**Example:** `todo read book --priority low --doOn 20-10-2021 02:00:00 --recur daily`
- Adds a todo task with description `read book` to the task list.
- Specifies the priority to be `low`.
- Specifies the task is to be done on `20-10-2021 02:00:00`.
- Specifies the task is to repeat `daily`.

**Expected Outcome:**

```
-------------------------------------------------------------------------
[user]: todo read book --priority low --doOn 20-10-2021 02:00:00 --recur daily
|| Task created!
|| read book [low] (doOn: 20-10-2021 02:00:00)
-------------------------------------------------------------------------
```

### 2.2.2 Deadline: `deadline`
Adds your **deadline** task to your task list.

**Format:** `deadline <description> <--due dd-MM-yyyy hh:mm> [--flag <argument>]`
- `<description>` specifies the description of your deadline.
- `<--due `[`<dateFormat>`](#224-date-format-dateformat)`>` is a mandatory flag.
- `[--flag <argument>]` specifies additional details of your deadline.
  - The priority of your task: `[--priority high|medium|low]`
  - How often your task is to repeat: `[--recur daily|weekly|monthly|yearly]`

**Example:** `deadline CS2106 Lab 3 --priority high --due 20-10-2021 02:00:00`
- Adds a deadline task with description `CS2106 Lab 3` to the task list.
- Specifies the priority to be `high`.
- Specifies the task is to be done on `20-10-2021 02:00:00`.
- without recurrence.

**Expected Outcome:**

```
-------------------------------------------------------------------------
[user]: deadline CS2106 Lab 3 --priority high --due 20-10-2021 02:00:00
|| Task created!
|| CS2106 Lab 3 [high] (dueDate: 20-10-2021 02:00:00)
-------------------------------------------------------------------------
```

### 2.2.3 Event: `event`
Adds your **event** task to your task list.

**Format:** `event <description> <--start dd-MM-yyyy hh:mm> <--end dd-MM-yyyy hh:mm> [--flag <argument>]`
- `<description>` specifies the description of your event.
- `<--start `[`<dateFormat>`](#224-date-format-dateformat)`>` and `<--end `[`<dateFormat>`](#224-date-format-dateformat)`>` are mandatory flags.
- `[--flag <argument>]` specifies additional details of your event.
  - The priority of your event: `[--priority high|medium|low]`
  - How often your event is to repeat: `[--recur daily|weekly|monthly|yearly]`

**Example:** `event Marquee Christmas Party --priority high --start 25-12-2020 22:00:00 --end 26-12-2020 04:00:00`
- Adds an event task with description `Marquee Christmas Party` to the task list.
- Specifies the priority to be `high`.
- Specifies the task to start on `25-12-2020 22:00:00` and end on `26-12-2020 04:00:00`.
- with no recurrence.

### 2.2.4 Date Format: `<dateFormat>`
The accepted date formats in our program:  
- `dd/MM/yyyy HH:mm`
- `dd/MM/yyyy HH`
- `dd/MM HH:mm`
- `dd/MM HH`
- `dd HH:mm`
- `dd HH`
- `HH`  

**Legend:**
- `dd` is day of month.
- `MM` is month of year.
- `yyyy` is the year.
- `HH` is hour on 24-hour clock
- `mm` is the minute

>💡 **Note**: When `dd`, `mm` or `yyyy` is unspecified, they will be replaced with current day, month and year respectively.  

>💡 **Note**: When `mm` is unspecified, it will be replaced with `0`.

**Example:** `03/10/2004 03` translates to `3rd October 2004, 3am`.


### 2.3 Listing your tasks: `list`

There are **3 main features** you can use with the list command.

#### 2.3.1 Listing your entire tasklist
List all tasks currently stored locally in your tasklist.

**Format:** `list`
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
    
**Example:** `list`
- List all tasks currently stored locally in your tasklist.

**Expected Outcome:**

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

#### 2.3.2 Filtering your tasklist
Filters the tasklist for specific tasks that matches the filters applied.

**Format:** `list [--filter <argument>] [--filter <argument>] ...`
- `[--flag <argument>]` specifies the filter to be applied to your task list.
  - Type of task: `[--type todo|deadline|event|lesson]`
  - Priority of the task: `[--priority low|medium|high]`
  - Recurrence of the task: `[--recur none|daily|weekly|monthly|yearly]`

**Example:** `list --type todo --priority medium`
- List all tasks that are type `Todo` and priority is `medium`.

**Expected Outcome:**

```
[user]: list --type todo --priority medium
|| -------------
||  MY TASKLIST
|| -------------
|| 1. [T] exercise <medium> {none} (doOn: 04-11-2021 18:00)
|| 2. [T] wash clothes <medium> {weekly} (doOn: 10-11-2021 02:00)
```

#### 2.3.3 Listing the recurrence of a task
Displays the next 4 recurrences of a task.

**Format 2:** `list [task id]`
- `[task id]` specifies the id of the task to display the recurrence.

**Example (with task id):** `list 3`
- List the next 4 recurrences for task with task id `3`.

**Expected Outcome:**

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

### 2.4 Sorting your task list: `sort`
Sorts your task list by a given criteria.

**Format:** `sort --by <criteria>`
- `<criteria>` specifies what to sort your task list by.
  - Type of task: `type`.
  - Description of task: `description`
  - Priority of the task: `priority`

**Example:** `sort --by priority` + `list`
- Sort your current task list by `priority` from `high` to `low`
- Print out the sorted list with the `list` command

**Expected Outcome:**

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

### 2.5 Deleting your tasks: `delete`
Deletes tasks specified in comma-seperated argument.

**Format:** `delete <indexes of tasks to delete>`

Index should be specified in either,
- Single index, such as `3`
- A range of two indexes, such as `1-3`. This is a shorthand for `1,2,3`.
- task indexes should be comma seperated.
  - `delete 1,3,5` would delete tasks with indexes 1, 3 and 5.
  - `delete 1-3` would delete tasks with indexes 1, 2 and 3.
  - `delete 1-3, 5` would delete tasks with indexes 1, 2, 3 and 5.  
 >💡 **Note**: Whether there are any spaces between the commas, it does not matter.

**Example:** `delete 1-3, 5`
- delete tasks with indexes 1, 2, 3 and 5.

**List before deletion:**

```
-------------------------------------------------------------------------
[user]: list
|| -------------
||  MY TASKLIST
|| -------------
|| 1. read book [low] (doOn: 20-10-2021 02:00:00)
|| 2. return book [medium] (dueDate: 21-10-2021 03:00:00)
|| 3. project meeting [high] (startDate: 22-10-2021 04:00:00 - endDate: 22-10-2021 05:00:00)
|| 4. wash clothes [medium] (doOn: 20-10-2021 02:00:00)
|| 5. homework [low] (dueDate: 21-10-2021 03:00:00)
|| 6. movie screening [low] (startDate: 22-10-2021 04:00:00 - endDate: 22-10-2021 05:00:00)
-----
```
**Expected Outcome:**

```
-------------------------------------------------------------------------
[user]: delete 1-3,5
|| Tasks deleted:
|| read book [low] (doOn: 20-10-2021 02:00:00)
|| return book [medium] (dueDate: 21-10-2021 03:00:00)
|| project meeting [high] (startDate: 22-10-2021 04:00:00 - endDate: 22-10-2021 05:00:00)
|| homework [low] (dueDate: 21-10-2021 03:00:00)
-------------------------------------------------------------------------
```

**List after deletion:**

```
-------------------------------------------------------------------------
[user]: list
|| -------------
||  MY TASKLIST
|| -------------
|| 1. wash clothes [medium] (doOn: 20-10-2021 02:00:00)
|| 2. movie screening [low] (startDate: 22-10-2021 04:00:00 - endDate: 22-10-2021 05:00:00)
-------------------------------------------------------------------------
```

### 2.6 Exiting the program: `bye`
Displays goodbye message and exits the program.

An end-of-file condition in the input, such as <kbd>Ctrl-D</kbd> on Bash, has the same effect as this command.

**Format:** `bye`

**Expected Outcome:**

```
-------------------------------------------------------------------------
[user]: bye
|| Exiting program!
-------------------------------------------------------------------------
```

## 3. FAQ


## 4. Command Summary

Action   | Format                                           | Example                                                                                              |
-------- | -------------------------------------------------| -----------------------------------------------------------------------------------------------------|
help     | `help`                                           | `help`                                                                                               |
todo     | `todo <description> [--flag <argument>]`         | `todo read book`                                                                                     |
deadline | `deadline <description> [--flag <argument>]`     |`deadline CS2106 Lab 3 --priority high --due 20-10-2021 02:00:00`                                     |
event    | `event <description> [--flag <argument>]`        | `event Marquee Christmas Party --priority high --start 25-12-2020 22:00:00 --end 26-12-2020 04:00:00`|
list     | `list [--filter <argument>]` or `list [task id]` | `list` or `list --type todo --priority medium` or `list 3`                                                           |
sort     | `sort --by <criteria>`                           | `sort --by priority`                                                                                 |
delete   | `delete <indexes of tasks to delete>`            | `delete 1, 2, 4-7`                                                                                   |
bye      | `bye`                                            | `bye`                                                                                                |
