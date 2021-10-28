# SLAM User Guide
Welcome to **SLAM**! **SLAM** is a desktop application for student group leaders in NUS to manage their group events, tasks, members and various activities. This application allows you to effectively plan and manage the organisation of events by allowing users to create and add such events to their schedule. Leading up to the actual date and time of the event, committee heads can add tasks (e.g. meetings, purchase of logistics) to complete under each event to keep track of progress. Group members can also be added and assigned to these tasks. This application uses a Command Line Interface (CLI); that is, the application is operated by typing commands into a Command Box.

---

# Table of Contents
1. [Quick Start](#1-quick-start)
2. [Navigating This Document](#2-navigating-this-document)
   1. [Events](#21-events)
   2. [Tasks](#22-tasks)
3. [General Commands](#3-general-commands)
   1. [Help](#31-help)
   3. [List all Events](#32-listing-events-tasks-or-members)
   2. [Exiting the program](#33-exiting-the-program)
4. [Event Commands](#4-event-commands)
   1. [Add an event](#41-add-an-event)
   2. [Select an event](#42-select-an-event)
   3. [Mark an event as done](#43-mark-an-event-as-done)
   4. [Mark an event as undone](#44-mark-an-event-as-undone)
   5. [Delete an event](#45-delete-an-event)
   10. [Delete all Events](#46-delete-all-events)
   6. [Find Event(s) based on keywords(s)](#47-find-events-based-on-keywords)
   8. [Display next upcoming Event](#48-display-next-upcoming-event)
   9. [Update an Event](#49-update-an-event)
5. [Task Commands](#5-task-commands)
   1. [Add a task](#51-add-a-task)
   2. [Mark a task as done](#52-mark-a-task-as-done)
   3. [Mark a task as undone](#53-mark-a-task-as-undone)
   4. [Delete a task](#54-delete-a-task)
6. [Member Commands](#6-member-commands)
   1. [Add a member](#61-add-a-member)
   2. [Delete a member](#62-delete-a-member)
7. [Saving the data](#7-saving-the-data)
8. [FAQ](#8-faq)
9. [Command summary](#9-command-summary)

---

## 1. Quick Start

1. Ensure that you have Java 11 or above installed on your computer.
2. Download the latest `.jar` file release of SLAM from [here](https://github.com/AY2122S1-CS2113T-W12-3/tp/).
3. Place the downloaded `.jar` file in the directory you intend to store **SLAM**’s data at.
4. Double-click the `.jar` file to launch the app. If double-clicking does not work, open the command prompt, navigate to the directory the file is located at, and enter the following command to launch the program:
```
 java -jar SLAM.jar
```
5. Congratulations! You are good to go. 

---

## 2. Navigating this document 
This document shows you the available features in **SLAM**, and provides you with the relevant commands to use them.

> 💡 **Notes about reading this document:**
> - Parameters that are supplied by the user are written in `UPPER_CASE`<br /> E.g. in `add -e n/TITLE`, a possible parameter for `TITLE` can be `Concert`
> - 🚨 **IMPORTANT** 🚨<br /> When inputting date & time as commands, the format should always be DD-mm-YYYY hhMM<br /> E.g. 31-12-2021 2359
    Since **SLAM** is an application revolving around the management of events, tasks and committee members, it is important to be aware of the respective details that **SLAM** can use in managing the overall catalog.

### 2.1 Events
The following two tables show the fields which **SLAM** works with in managing events in the overall catalog.

| **Required Fields** | **Description**                      |
| ------------------- | -------------------------------- |
| Title | The title of the event                         |
| Date & Time | The date and time the event is happening | 
| Venue | The venue at which the event is happening      |
| Budget | The amount of budget allocated to the event   |

| **Optional Fields** | Description                      |
| ------------------- | -------------------------------- |
| Description | A brief description of the event                         |

### 2.2 Tasks
The following two tables show the fields which **SLAM** works with in managing the tasks you can add and keep track of under each event created.

| **Required Fields** | **Description**                       |
| ------------------- | --------------------------------- |
| Title | The title of the task                           |
| Deadline | The deadline the task has to be completed by |

| **Optional Fields** | **Description**                     |
| ------------------- | ------------------------------- |
| Description         | A brief description of the task |


[Back to table of contents](#table-of-contents)

---

## 3. General Commands
This section contains the general basic commands for **SLAM**.

### 3.1 Help 
This allows you to quickly view all the available commands and how to use them, all in one place.

**Format:** `help`

### 3.2 Listing Events, Tasks or Members
You may use this feature of **SLAM** to view the desired selection of your **SLAM** database. 

You may choose to view all your scheduled `events` in SLAM. SLAM sorts this chronologically for you so you don't have to. 

**Format:** `list`

**Example:** 
```
> list
```

```
OVERALL SCHEDULE
=======================
1. [E][ ] Tembusu Concert (at: 19 Feb 2022 - 20:00)
2. [E][ ] Faculty Funfair (at: 20 Mar 2022 - 16:00)

FURTHER COMMANDS
-----------------------
To list Task: list [Event Index] -t
To list Members of a Task: list [Event Index] t/[Task Index]
--------END OF LIST-----------
```

💡 `list` can also be used to display all members in your current roster. 

**Format:** `list -m`

**Example:** 
```
> list -m
```

```
List of members in MemberRoster
1. JOHN DOE
2. JANE DOE
--------END OF LIST-----------
```

💡 You may also choose to view all the `tasks` you have assigned to any specified event.

**Format:** `list EVENT_INDEX -t`

**Example:** `list 1 -t`

**Expected Output:** 
```
> list 1 -t
```
```
Event: Tembusu Concert
========================
1. [T][X] Buy stage lights (by: 10 Feb 2022 - 22:00)
2. [T][ ] Stage layout meeting (by: 12 Feb 2022 - 20:00)
--------END OF LIST-----------
```

💡 **SLAM** also allows you to choose to **view all the assigned members for a particular task** too.

**Format:** `list EVENT_INDEX t/TASK_INDEX`

**Example:** `list 1 t/1`

**Expected Output:**
```
Event: Tembusu Concert
Task: Buy stage lights
=======================
1. JOHN DOE
2. JANE DOE
--------END OF LIST-----------
```

### 3.3 Exiting the program
This allows you to safely terminate the program.

**Format:** `bye` 

[Back to table of contents](#table-of-contents)

---

## 4. Event Commands
This section contains the available commands to manage events in **SLAM**.

### 4.1 Add an Event
Adds an event to your current catalog of events.

To add an event, first describe the event using the following command:

**Format:** `add -e n/TITLE d/DATE_&_TIME v/VENUE b/BUDGET`

**Example:** `add -e n/Temasek Hall Concert d/30-11-2021 2300 v/MPH b/500`

Following which, **SLAM** will prompt you for an optional description for your event which should look like this:

```
> add -e n/Temasek Hall Concert d/30-11-2021 2300 v/MPH b/500
```
```
Please add an optional description for your item and press enter.
```
```
>
```
Key in your description and press `enter`, or just press `enter` if you have none. You have successfully added an event into **SLAM**!

### 4.2 Select an Event
This allows you to select an event from your list using the event’s numerical position in the list (i.e. the event’s ID). Upon selection, more details about that event will be displayed, and you may perform additional commands with the selected event (e.g. adding tasks to complete).

**Format:** `select -e EVENT_ID`

**Example:** `select -e 1`
> 💡 The `select` command is especially useful to keep in mind! An event needs to be selected before any changes to its tasks can be done.

**Expected Output:**
```
> select -e 1
```
```
Here are the details of the event:
Title: Tembusu Concert
Date: 19 Feb 2022 - 20:00
Description: Semesterly Arts Week Fundraiser
Venue: MPH
Budget: $1000.0
Tasks:
1. Make props
```

### 4.3 Mark an Event as `done`
This will allow you to mark an event as done after the event is over. 

**Format:** `done -e EVENT_ID`

**Example:** `done -e 1`

**Expected Output:**
```
> done -e 1
```
```
Nice! I have marked these items as done!
[E][X] Tembusu Concert (at: 19 Feb 2022 - 20:00)
--------LIST UPDATED--------
```

### 4.4 Mark an Event as `undone`
This will allow you to undo an event that was marked done if the event is not over yet.

**Format:** `undo -e EVENT_ID`

**Example:** `undo -e 1`

**Expected Output:**
```
Ok, I have unmarked these items:
[E][ ] Tembusu Concert (at: 19 Feb 2022 - 20:00)
--------LIST UPDATED--------
```

### 4.5 Delete an Event
Deletes an event from the list.

**Format:** `delete -e EVENT_ID`

**Example:** `delete -e 1`

> 🚨 If you choose to `delete` an event, all of its associated tasks will be deleted too.

**Expected Output:**
```
> delete -e 1
```
```
This event has been removed: Tembusu Concert
```
### 4.6 Delete all Events
This allows you to delete all events.

> 🚨 As this can potentially affect a huge amount of your data, the program will confirm with you again if you want to delete all events.

**Format:** `delete all`

After entering `delete all`, this is what you will see:

```
> delete all
```
```
Are you sure you want to delete all events? (Y/N)
> Y
I have deleted everything!
```
> 🚨 Once you confirmed that you want to delete all events, the action is irreversible!

[Back to table of contents](#table-of-contents)

### 4.7 Find Event(s) based on keyword(s)

### 4.8 Display next upcoming Event

### 4.9 Update an Event


---

## 5. Task Commands
This section contains the available commands for managing tasks for events in **SLAM**. Because all tasks belong to a specific event in your catalog, an event must first be selected ([see Section 5.2 on Selecting Events](#52-select-an-event)) before any of the below commands can be used.

### 5.1 Add a task 
Note: For this current version of **SLAM**, pre-selecting an `Event` is not required for this command.

**Prerequisite:** You must have added at least one event and one member before adding a task.

Adds a task to an existing event in your current catalog of events.

To add a task, first describe the task using the following command:

**Format:** `add -t n/TITLE d/DATE_&_TIME`

**Example:** `add -t n/Buy stage lights d/10-11-2021 1600`

### 5.2 Mark a Task as `done`

### 5.3 Mark a Task as `undone`

### 5.4 Delete a Task

[Back to table of contents](#table-of-contents)

---

## 6. Member Commands
This section contains the available commands for managing members in `SLAM`.

### 6.1 Add a member 

### 6.2 Delete a member

[Back to table of contents](#table-of-contents)

---

## 7. Saving the Data
**SLAM** data is saved in the hard disk automatically after you exit the program. There is no need to save manually.

[Back to table of contents](#table-of-contents)

## 8. FAQ

>**Q**: How do I transfer my data to another computer? 
>
>**A**: You can transfer your data to another computer by copying the `data` folder found in the directory where SLAM was used, and pasting it in the directory of the device where **SLAM** is intended to be run.

>**Q**: Can I delete all members from my current roster using `delete all`? 
> 
>**A**: The current verson of **SLAM** (2.0) does not allow for this. We are working on this on a future update for v2.1.

>**Q**: Will deleting a member from my roster remove that member from all tasks he/she is assigned to? 
>
>**A**: SLAM 2.0 does not support this functionality and we are working on a fix for this for our v2.1 update. Hang on tight in the meanwhile and ensure your members produce work for you before kicking them out! 😉

[Back to table of contents](#table-of-contents)

## 9. Command Summary

### General commands
| Command                   | Format                                              |
|---------------------------|-----------------------------------------------------|
| Display user manual       | `help`                                              |
| List events/members/tasks | For events: `list`<br />For tasks: `list EVENT_INDEX -t`<br />For members: `list -m`<br />For members assigned to a task: `list EVENT_INDEX t/TASK_INDEX`|
| Exit program              | `bye`                                               |

### Event commands
| Command               | Format                                              |
|-----------------------|-----------------------------------------------------|
| Add an event          | `add -e n/TITLE d/dd-MM-yyyy HHmm v/VENUE b/BUDGET` |
| Delete an event       | `delete -e INDEX`                                   |
| Delete all events     | `delete all`                                        |
| Select an event       | `select -e INDEX`                                   | 
| Find event(s)         | `find EVENT_KEYWORD(S)`                             |
| Mark event as done    | `done -e INDEX`                                     | 
| Un-mark event as done | `undo -e INDEX`                                     |

### Task commands
| Command               | Format                        |
|-----------------------|-------------------------------|
| Add a task            | `add -t n/TITLE d/dd-MM-yyyy` |
| Delete a task         | `delete -t INDEX`             | 
| Select a task         | `select -t INDEX`             |
| Mark a task as done   | `done -t INDEX`               |
| Un-mark a task as done| `undo -t INDEX`               |

### Member commands
| Command         | Format                  |
|-----------------|-------------------------|
| Add a member    | `add -m MEMBER_NAME`    | 
| Delete a member | `delete -m MEMBER_NAME` |

[Back to table of contents](#table-of-contents)
