# User Guide

NUS Buddy is a **fast, flexible and unobtrusive weekly planner**.
Designed specifically for NUS students, it includes an intuitive **task and lesson manager**, as well as a **module planner** 
with CAP calculation features that can be accessed quickly via a Command Line Interface (CLI), taking up the least of 
your time, so you can focus on tasks and lessons that are important to you.

This user guide will help you get started on using NUSBuddy so you can go about organising your tasks, lessons and modules 
right away. The [quick start](#quick-start) guide will show you how to set up NUSBuddy on your computer, and the [features](#features)
section will show you how to use commands to perform actions on tasks, lessons and modules. If you have issues using NUSBuddy,
the [FAQ](#faq) section can help with troubleshooting your problem, and the [command summary](#command-summary) will give you
an overview of all the commands you can use with NUSBuddy.

> Icons used in this guide:
> 
> This guide uses blocks of text with icons to denote certain warnings, notes and extra features.
> 
> 💡 refers to an important note 
> 
> ❗️ refers to a warning
> 
> ℹ️ refers to additional information that may be useful to note

## Table of contents

* [Quick Start](#quick-start)
* [Features](#features)
  * [Adding a Task, Lesson or Module - `add`](#adding-a-task-lesson-or-module---add)
  * [Listing Tasks, Lessons or Modules - `list`](#listing-tasks-lessons-or-modules---list)
  * [Marking a Task as done - `done`](#marking-a-task-as-done---done)
  * [Deleting Tasks and Lessons - `delete`](#deleting-tasks-or-lessons---delete)
  * [Finding Tasks or lessons by keyword - `find`](#finding-tasks-or-lessons-by-keyword---find)
  * [Exiting the program - `exit`](#exiting-the-program---exit)
  * [Saving the data](#saving-the-data)
* [FAQ](#faq)
* [Command summary](#command-summary)

## Quick Start

1. Ensure that you have Java 11 or above installed on your machine. You can follow the guide 
[here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/what-is-corretto-11.html) for more information.
2. Download the latest version of NUS Buddy from our GitHub repository.
3. Copy the `.jar` file to the folder you want to use as the home directory for the application.
4. To launch the app, run the command `java -jar {filename}.jar`.

You should see the following output when you start NUSBuddy for the first time.
```
    _______________________________________________________________________________
     I can't retrieve the saved task data. Creating new file..
    _______________________________________________________________________________

    _______________________________________________________________________________
     I can't retrieve the saved lesson data. Creating new file..
    _______________________________________________________________________________

    _______________________________________________________________________________
     I can't retrieve the saved module data. Creating new file..
    _______________________________________________________________________________

    _______________________________________________________________________________
       _   _       _   _   ____           ____     _   _   ____     ____   __   __ 
      | \ |"|   U |"|u| | / __"| u     U | __")uU |"|u| | |  _"\   |  _"\  \ \ / / 
     <|  \| |>   \| |\| |<\___ \/       \|  _ \/ \| |\| |/| | | | /| | | |  \ V /  
     U| |\  |u    | |_| | u___) |        | |_) |  | |_| |U| |_| |\U| |_| |\U_|"|_u 
      |_| \_|    <<\___/  |____/>>       |____/  <<\___/  |____/ u |____/ u  |_|   
      ||   \\,-.(__) )(    )(  (__)     _|| \\_ (__) )(    |||_     |||_ .-,//|(_  
      (_")  (_/     (__)  (__)         (__) (__)    (__)  (__)_)   (__)_) \_) (__) 
    _______________________________________________________________________________

```
> ❗️ The new files created are data files used to store information your list of tasks, lessons and modules. Please refrain 
from deleting these files in order to preserve your data.

## Features

> 💡 The **CONSTANT_CASE** in the command denotes the parameter that you can enter your value into.
>
> [PARAM] denotes a compulsory parameter, while {PARAM} denotes an optional parameter you can choose to omit.
>
> [OPTION1/OPTION2] denotes that either OPTION1 or OPTION2 must be chosen as the parameter.

### Adding a Task, Lesson or Module - `add`

Allows you to add a task, lesson or module to your list of tasks, lessons or modules. 

> 💡 A task contains a title, the day of the week of the task, and an optional task information.

Format: `add task [TITLE] -d [DAY_OF_THE_WEEK] -i {INFORMATION} -p {PRIORITY}`
* `TITLE` refers to the name of the task you wish to create
* `DAY_OF_THE_WEEK` can be any of the following, non case-sensitive: `mon`, `tue`, `wed`, `thu`, `fri`, `sat`, `sun`
and refers to the day on which you want to do the task
* the `-i` flag is optional and `INFORMATION` refers to any additional information about the task to add
* the `-p` flag is optional and `PRIORITY` can be either `low`, `medium` or `high`, referring to the priority you wish 
to assign to the new task

Example usage:
```
$ add task CS2113 tP -d MON
    _______________________________________________________________________________
     Noted. I've added this task:
       [ ] CS2113 tP (Monday)
            Info: -
            Priority: None
     Now you have 2 tasks in the list.
    _______________________________________________________________________________
```
This first example shows that an incomplete new task "CS2113 tP" has been added to the list of tasks, and is to be done 
on Monday. It has no additional information, and has no assigned priority. You can also see the number of tasks you have 
in the list. The empty braces `[ ]` before the task name indicates that the task is incomplete. You can [visit this 
section](#marking-a-task-as-done---done) on marking a task as done to change the completion status of the task. 

```
$ add task CS2113 tP -d MON -i add documentation -p low
    _______________________________________________________________________________
         Noted. I've added this task:
           [ ] CS2113 tP (Monday)
                Info: add documentation
                Priority: Low
         Now you have 3 tasks in the list.
    _______________________________________________________________________________
```
This second example shows how to use the optional flags with `add task`. This example shows that the task "CS2113 tP" has
been added for Monday, and has the additional information "add documentation", with low priority assigned.

> 💡 A lesson contains a title, the day of the week of the lesson, the start time of the lesson, and the end time of the lesson.

Format: `add lesson [TITLE] -d [DAY_OF_THE_WEEK] -s [START_TIME] -e [END_TIME] -l {ZOOM_LINK}`
* `TITLE` refers to the title of the lesson to add, typically a module code or subject name
* `DAY_OF_THE_WEEK` can be any of the following, non case-sensitive: `mon`, `tue`, `wed`, `thu`, `fri`, `sat`, `sun`
and refers to the day which your lesson occurs on
* `START_TIME` refers to the time at which your lesson starts in HH:MM format ie; 2pm would be 14:00
* `END_TIME` refers to the time at which your lesson ends in HH:MM format
* the `-l` flag is optional and `ZOOM_LINK` refers to a meeting link you may have for online lessons 

Example usage:
```
$ add lesson CS2113 Lecture -d MON -s 14:00 -e 15:00
    _______________________________________________________________________________
     Noted. I've added this lesson:
       CS2113 Lecture
        Monday, 02:00 PM - 03:00 PM
        Meeting URL: -
     Now you have 1 lessons in the list.
    _______________________________________________________________________________
```
This example shows that the lesson "CS2113 Lecture" has been added for Monday, and is from 2pm to 6pm with no attached 
meeting link, and that it is the only lesson in the list.

> 💡 A module is referred to by its module code. Information about the module, such as its number of modular credits is 
> retrieved from NUSMods using the module code.

Format: `add module [MODULE_CODE]`
* `MODULE_CODE` refers to the module code you can find on NUSMods, non case-sensitive
* only valid module codes found on NUSMods can be entered into `MODULE_CODE`

Example usage:
```
$ add module cs2113t
    _______________________________________________________________________________
     Noted. I've added this module:
       CS2113T Software Engineering & Object-Oriented Programming (4MCs) 
     Now you have 2 modules in the list.
    _______________________________________________________________________________
```
This example shows that CS2113T, which has 4 modular credits, has been added to the list of modules, and that there are 
2 modules in the list of modules.

### Listing Tasks, Lessons or Modules - `list`

Allows you to view a complete list of the tasks, lessons or modules that you have added to your list.

Format: `list [task/lesson/module]`
* `list task` will list out all added tasks
* `list lesson` will list out all added lessons
* `list module` will list out all added modules

Example usage:

```
$ list task
    _______________________________________________________________________________
     Here are the tasks in your list:
     1. [X] cs2113t tp (Wednesday)
            Info: add documentation
            Priority: High
     2. [ ] prepare for presentation (Friday)
            Info: -
            Priority: None
    _______________________________________________________________________________
```
This example lists out all 2 tasks. The first task in this list is marked as complete, as indicated by the `[X]` beside the
task name, "cs2113t tp". The "Wednesday" in brackets also indicates that the task is assigned to be done on Wednesday. 
Additional information for the task is listed as "add documentation", and the task has High priority. The second task
on the list is marked as incomplete, as indicated by the `[ ]` beside the task name, "prepare for presentation". It is 
assigned for Friday. The " - " symbol beside "Info:" indicates that the task was added with no additional information, 
the "None" beside "Priority:" indicates that there was no priority assigned to it either.
     
```
$ list lesson
    _______________________________________________________________________________
     Here are the lessons in your list:
     1. cs2113t lecture
        Friday, 04:00 PM - 06:00 PM
        Meeting URL: -
     2. cg2028 lab
        Thursday, 02:00 PM - 05:00 PM
        Meeting URL: meetingLink.com
    _______________________________________________________________________________
```
This example shows that there are 2 lessons in the list, "cs2113t lecture" and "cg2028 lab". "cs2113t lecture" is on 
Friday, from 2pm to 6pm, and has no attached meeting link, and "cg2028 lab" is on Thursday, from 2pm to 5pm, with 
meetingLink.com as the meeting link for the session.

Another format of using `list` can show you your upcoming tasks and lessons for the current and next day.

Format: `list [task/lesson] [today/tomorrow]`
* similar to the above format, `list task` is used for tasks and `list lesson` for lessons
* `today/tomorrow` is an option that you can choose, and will determine whether NUSBuddy displays upcoming tasks or lessons
* scheduled for the current day or the next day

Example usage:
```
$ list task today
    _______________________________________________________________________________
     There is no task on TUE.
    _______________________________________________________________________________
``` 
This example shows that there are no tasks scheduled for Tuesday, which is in this case the current day as determined 
by the operating system.

```
$ list lesson tomorrow
    _______________________________________________________________________________
     Here are the lessons on WED:
     1. cs2101 tutorial
        Wednesday, 10:00 AM - 12:00 PM
        Meeting URL: -
    _______________________________________________________________________________
```
In this example, the current day is Tuesday and hence, there is one lesson on Wednesday, "cs2101 tutorial" from 10am to 
12 pm with no attached meeting link.

```
$ list module
    _______________________________________________________________________________
     Here are the modules in your list:
     1. CG2028 Computer Organization (2MCs) 
     2. CS2113T Software Engineering & Object-Oriented Programming (4MCs) 
     3. CS2101 Effective Communication for Computing Professionals (4MCs) 
    _______________________________________________________________________________
```
This example shows the expected output for a list with 3 modules added, CG2028, CS2113T and CS2101. Using the first
module as an example, the module title for CG2028 is "Computer Organization" and as indicated by `(2MCs) ` beside the 
title, the module bears 2 modular credits. 

### Marking a Task as done - `done`

Indicate that the specified task is completed.

Format: `done [INDEX]`

Example usage:

```
$ done 1
    _______________________________________________________________________________
     Nice! I've marked this task as done: 
       [T][X] Title: CS2113 tP (Day: MON)
     Now you have 1 pending tasks.
    _______________________________________________________________________________
```

### Deleting Tasks or Lessons - `delete`

`delete` can be used to perform any of the following actions:
* delete all tasks and lessons 
* delete all tasks 
* delete all lessons 
* delete a specific task or lesson

Format: 
* `delete [task/lesson] [INDEX]`: deletes the task or lesson at the specified index
* `delete [task/lesson] all`: deletes all tasks or lessons as specified
* `delete all`: deletes all tasks and lessons

Example usage:

```
delete lesson 2
    _______________________________________________________________________________
     Ok. The following lesson has been deleted:
       [L] Title: CS2113 Tutorial (Day: THU) (Start: 11am, End: 12pm)
     Now you have 1 lesson(s) in the list.
    _______________________________________________________________________________

delete task 1
    _______________________________________________________________________________
     Ok. The following task has been deleted:
       [T][ ] Title: CS2113 tP (Day: WED)
     Now you have 1 task(s) in the list.
    _______________________________________________________________________________

delete lesson all
    _______________________________________________________________________________
     All your lessons have been successfully removed.
    _______________________________________________________________________________

delete all
    _______________________________________________________________________________
     All your tasks and lessons have been successfully removed.
    _______________________________________________________________________________

```

### Finding Tasks or Lessons by keyword - `find`

Locate tasks and lessons that match the specified keyword.

Format: `find [task/lesson] [KEYWORD]`

Example usage:

```
find task cs2113
    _______________________________________________________________________________
     Here are the matching tasks in your list:
     1. [T][ ] Title: CS2113 tP (Day: WED)
     2. [T][ ] Title: CS2113 iP (Day: WED)
               Info: New increment
    _______________________________________________________________________________

find lesson cs2113
    _______________________________________________________________________________
     Here are the matching lessons in your list:
     1. [L] Title: CS2113 Lecture (Day: MON) (Start: 1pm, End: 2pm)
     2. [L] Title: CS2113 Tutorial (Day: THU) (Start: 11am, End: 12pm)
    _______________________________________________________________________________

```

### Exiting the program - `exit`

Exit the program.

Format: `exit`

Example usage:

```
exit
    _______________________________________________________________________________
     Bye!
    _______________________________________________________________________________
```

### Saving the data

Data from NUS Buddy is automatically saved on your local machine.

## FAQ

## Command summary

| Action                            | Format                                                                       |
|-----------------------------------|------------------------------------------------------------------------------|
| Add                               | `add task [TITLE] -d [DAY_OF_THE_WEEK] -i {INFORMATION}`                     |
| List                              | `list [task/lesson/all]`                                                     |
| Mark task as done                 | `done [INDEX]`                                                               |
| Delete task or lesson             | `delete [task/lesson] [INDEX]` or `delete [task/lesson] all` or `delete all` |
| Find tasks and lessons by keyword | `find [task/lesson] [KEYWORD]`                                               |
| Exit the program                  | `exit`                                                                       |

