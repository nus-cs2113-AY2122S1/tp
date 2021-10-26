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
> This guide uses blocks of text with icons to denote certain warnings and notes.
> 
> 💡 refers to an important note 
> 
> ❗️ refers to a warning

## Table of contents

* [Quick Start](#quick-start)
* [Features](#features)
  * [Getting help - `help`](#getting-help---help)
  * [Adding a Task, Lesson or Module - `add`](#adding-a-task-lesson-or-module---add)
  * [Listing Tasks, Lessons or Modules - `list`](#listing-tasks-lessons-or-modules---list)
  * [Marking a Task as done - `done`](#marking-a-task-as-done---done)
  * [Deleting Tasks, Lessons or Modules - `delete`](#deleting-tasks-lessons-or-modules---delete)
  * [Finding Tasks or lessons by keyword - `find`](#finding-tasks-or-lessons-by-keyword---find)
  * [Retrieving Module information - `moduleinfo`](#retrieving-module-information---moduleinfo)
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

### Getting help - `help`

Shows you a summary of the commands you can use with NUSBuddy, and the format of their parameters if applicable.

Format: `help`

Example usage:
```
$ help
    _______________________________________________________________________________
     Here is a list of possible commands:
     add task [TITLE] -d [DAY_OF_THE_WEEK] -i {INFORMATION} -p {PRIORITY}
     add lesson [TITLE] -d [DAY_OF] -s [START_TIME] -e [END_TIME] -l {LINK}
     add module [MODULE_CODE]
     list [task/lesson/module]
     done [INDEX]
     delete [task/lesson/module] [INDEX]
     find [task/lesson] [KEYWORD]
     moduleinfo [MODULE_CODE]
     exit
     More details: https://ay2122s1-cs2113t-w11-3.github.io/tp/UserGuide.html
    _______________________________________________________________________________
```
You can use this as a quick syntax guide while using NUSBuddy. The format of the syntax shown by `help` is the same as
the one used by this guide, which can be found [here](#features).

### Adding a Task, Lesson or Module - `add`

Allows you to add a task, lesson or module to your list of tasks, lessons or modules. 

> 💡 A task contains a title, the day of the week of the task, and an optional task information.

Format: `add task [TITLE] -d [DAY_OF_THE_WEEK] -p {PRIORITY} -i {INFORMATION}`
* `TITLE` refers to the title of the task you wish to create
* `DAY_OF_THE_WEEK` can be any of the following, not case-sensitive: `mon`, `tue`, `wed`, `thu`, `fri`, `sat`, `sun`
and refers to the day on which you want to do the task
* the `-i` flag is optional and `INFORMATION` refers to any additional information about the task to add
* the `-p` flag is optional and `PRIORITY` can be either `L`, `M` or `H`, for low, medium and high priority respectively
* the default priority for each task is set to low (L)

Example usage:

```
$ add task CS2113 tP -d MON
    _______________________________________________________________________________
     Noted. I've added this task:
       [ ] CS2113 tP (Monday)
            Priority: L
            Info: -
     Now you have 2 tasks in the list.
    _______________________________________________________________________________
```
This first example shows that an incomplete new task "CS2113 tP" has been added to the list of tasks, and is to be done 
on Monday. It has no additional information, and has low priority. You can also see the number of tasks you have 
in the list. The empty braces `[ ]` before the task title indicates that the task is incomplete. You can [visit this 
section](#marking-a-task-as-done---done) on marking a task as done to change the completion status of the task. 

```
$ add task CS2113 tP -d MON -p L -i add documentation
    _______________________________________________________________________________
         Noted. I've added this task:
           [ ] CS2113 tP (Monday)
                Priority: L
                Info: add documentation
         Now you have 3 tasks in the list.
    _______________________________________________________________________________
```
This second example shows how to use the optional flags with `add task`. This example shows that the task "CS2113 tP" has
been added for Monday, and has the additional information "add documentation", with low priority assigned.

> 💡 A lesson contains a title, the day of the week of the lesson, the start time of the lesson, and the end time of the lesson.

Format: `add lesson [TITLE] -d [DAY_OF_THE_WEEK] -s [START_TIME] -e [END_TIME] -l {ZOOM_LINK}`
* `TITLE` refers to the title of the lesson to add, typically a module code or subject name
* `DAY_OF_THE_WEEK` can be any of the following, not case-sensitive: `mon`, `tue`, `wed`, `thu`, `fri`, `sat`, `sun`
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
* `MODULE_CODE` refers to the module code you can find on NUSMods, not case-sensitive
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
            Priority: H
            Info: add documentation
     2. [ ] prepare for presentation (Friday)
            Priority: L
            Info: -
    _______________________________________________________________________________
```
This example lists out all 2 tasks. The first task in this list is marked as complete, as indicated by the `[X]` beside the
task title, "cs2113t tp". The "Wednesday" in brackets also indicates that the task is assigned to be done on Wednesday. 
Additional information for the task is listed as "add documentation", and the task has High priority. The second task
on the list is marked as incomplete, as indicated by the `[ ]` beside the task title, "prepare for presentation". It is 
assigned for Friday. The " - " symbol beside "Info:" indicates that the task was added with no additional information, 
the "L" beside "Priority:" indicates that it has low priority.
     
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

You can also choose to view tasks by priority with `list`.

Format: `list task priority`

```
$ list task priority
    _______________________________________________________________________________
     Here are the tasks sorted by priority:
     1. [ ] prepare for presentation (Wednesday)
            Priority: L
            Info: -
     2. [X] math homework (Thursday)
            Priority: M
            Info: -
     3. [ ] cs2113 tP (Friday)
            Priority: H
            Info: add documentation
    _______________________________________________________________________________

```
As seen in this example, you can view your existing tasks sorted by priority, with low priority tasks shown at the top
of the list and high priority tasks shown at the bottom.

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

Allows you to indicate that a task is complete.

Format: `done task [INDEX]`
* `INDEX` refers to the index of the task. You can view this index as the number beside the task title using `list task`.

Example usage:

```
$ done task 2
    _______________________________________________________________________________
     Nice! I've marked this task as done: 
       [X] prepare for presentation (Friday)
            Priority: L
            Info: -
     Now you have 0 pending tasks.
    _______________________________________________________________________________
```
The example output shows that the second task in the list, "prepare for presentation" is now marked as complete. You
can also see the complete information about the task, and the number of remaining tasks not marked as done. In this 
example, there are no more pending tasks on the list.

### Deleting Tasks, Lessons or Modules - `delete`

Enables you to remove a task, lesson or module from your list.

> ❗️ Once removed, deleted tasks, lessons or modules cannot be restored.

Format: 

To delete a task or lesson: `delete [task/lesson] [INDEX]`
* `delete task` will only display results that are tasks
* `delete lesson` will only display results that are lessons
* `INDEX` refers to the index of the task or lesson on your list. You can view this index using `list task` or `list lesson`
as the number beside the task or lesson title.

To delete a module: `delete module [MODULE_CODE]`
* `MODULE_CODE` refers to the module code assigned to the module as per NUSMods, not case-sensitive

Example usage:

```
$ delete task 1
    _______________________________________________________________________________
     Ok. The following task has been deleted:
       [X] cs2113t tp (Wednesday)
            Priority: H
            Info: add documentation
     Now you have 1 task(s) in the list.
    _______________________________________________________________________________
```
In this example output, the first task on the list has been deleted. You can see complete information about the deleted 
task, which has the title "cs2113 tp", was assigned for Wednesday, and was marked as complete. You can also see the number
of tasks left on the list, and in this case, there is 1 task on the list.

```
$ delete lesson 2
    _______________________________________________________________________________
     Ok. The following lesson has been deleted:
       cg2028 lab
        Thursday, 02:00 PM - 05:00 PM
        Meeting URL: meetingLink.com
     Now you have 3 lesson(s) in the list.
    _______________________________________________________________________________
```
This example output shows that the second lesson on the list, "cg2028" lab, has been deleted. Along with additional 
information including the day, start time, end time and meeting link for the lesson, you can view the number of remaining
lessons on the list, 3 in this case.

```
$ delete module cg2028
    _______________________________________________________________________________
     Ok. The following module has been deleted:
       CG2028 Computer Organization (2MCs) 
     Now you have 2 module(s) in the list.
    _______________________________________________________________________________
```
In this example, the module CG2028 was deleted from the list. You can see information about the deleted module including 
the module title and modular credits. You can also see the number of remaining modules in the list, 2 in this case.

### Finding Tasks or Lessons by keyword - `find`

Allows you to locate tasks and lessons that match a keyword of your choice.

Format: `find [task/lesson] [KEYWORD]`
* `find task` will only display results that are tasks
* `find lesson` will only display results that are lessons
* `KEYWORD` refers to the word that you intend to find matching tasks and lessons for, not case-sensitive

Example usage:

```
$ find task cs2113
    _______________________________________________________________________________
     Here are the matching tasks in your list:
     1. [X] cs2113tp (Wednesday)
            Priority: L
            Info: -
     2. [ ] cs2113t ip (Thursday)
            Priority: L
            Info: -
    _______________________________________________________________________________
```
In this first example, there are 2 tasks in the list that match the keyword "cs2113t". You can see relevant information about them,
including the day assigned to the task, the completion status, additional information and priority. The first matching 
task in the list, for example, is marked as complete, as shown by the `[X]`, is assigned for Wednesday, and has no additional
information, with low priority.

```
$ find lesson cs2113t
    _______________________________________________________________________________
     Here are the matching lessons in your list:
     1. cs2113t lecture
        Friday, 04:00 PM - 06:00 PM
        Meeting URL: -
    _______________________________________________________________________________
```
In this example, there is one lesson with "cs2113t" in its title, "cs2113t lecture". You can view additional information
about this lesson, specifically the day of the lesson, its start and end time, and attached meeting link. This matching
lesson is scheduled for Friday from 2pm to 6pm and has no attached meeting link.

### Retrieving Module information - `moduleinfo`

Shows you a complete list of information regarding any module on NUSMods. You can find information such as the module title,
modular credits, department, faculty, preclusion, pre-requisites and other details using this command.

Format: `moduleinfo [MODULE_CODE]`
* `MODULE_CODE` refers to the module code as per NUSMods, not case-sensitive

Example usage:
```
$ moduleinfo cs2113t
    _______________________________________________________________________________
     CS2113T Software Engineering & Object-Oriented Programming (4MCs) 
     Department: Computer Science
     Faculty: Computing
     Preclusion: CS2103, CS2103T, (CS2113T for CS2113), (CS2113 for CS2113T)
     Pre-requisite: CS2040C or ((CS2030 or its equivalent) and CS2040/S)
     Core Requisites: CS2101 Effective Communication for Computing Professionals is co-requisite for CS2113T. Students exempted from CS2101 will take CS2113 which does not have CS2101 as co-req. Otherwise, CS2113 and CS2113T are identical.
    _______________________________________________________________________________
```
This example shows the information about the module CS2113T. The title of this module is "Software Engineering & 
Object-Oriented Programming", and it bears 4 modular credits. It is under the Computer Science department and Computing
faculty. You can also see the preclusion, pre-requisites and core requisites with additional notes.

### Exiting the program - `exit`

Exits the program.

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

