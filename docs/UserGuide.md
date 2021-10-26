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

## Table of contents

* [Quick Start](#quick-start)
* [Features](#features)
  * [Adding a Task or Lesson - `add`](#adding-a-task-or-lesson---add)
  * [Listing Tasks or Lessons - `list`](#listing-tasks-or-lessons---list)
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

> 💡 [PARAM] denotes a compulsory parameter, while {PARAM} denotes an optional parameter you can choose to omit.

> 💡 [OPTION1/OPTION2] denotes that either OPTION1 or OPTION2 must be chosen as the parameter.

### Adding a Task or Lesson - `add`

Add a task or lesson to the list. 

A task contains a title, the day of the week of the task, and an optional task information.

Format: `add task [TITLE] -d [DAY_OF_THE_WEEK] -i {INFORMATION}`

A lesson contains a title, the day of the week of the lesson, the start time of the lesson, and the end time of the lesson.

Format: `add lesson [TITLE] -d [DAY_OF] -s [START_TIME] -e [END_TIME]`

Example usage:

```
$ add task CS2113 tP -d MON
    _______________________________________________________________________________
     Noted. I've added this task:
       [T][ ] Title: CS2113 tP (Day: MON)
     Now you have 1 tasks in the list.
    _______________________________________________________________________________
    
$ add task CS2113 tP -d MON -i add documentation
    _______________________________________________________________________________
     Noted. I've added this task:
       [T][ ] Title: CS2113 tP (Day: MON)
               Info: add documentation
     Now you have 2 tasks in the list.
    _______________________________________________________________________________

$ add lesson CS2113 Lecture -d MON -s 1pm -e 3pm
    _______________________________________________________________________________
     Noted. I've added this lesson:
       [L] Title: CS2113 Lecture (Day: MON) (Start: 1pm, End: 3pm)
     Now you have 1 lessons in the list.
    _______________________________________________________________________________
    
```

### Listing Tasks or Lessons - `list`

`list` can be used to perform any of the following actions:
- list all tasks and lessons
- list all tasks only
- list all lessons only

Format: `list [task/lesson/all]`

Example usage:

```
$ list task
    _______________________________________________________________________________
     Here are the tasks in your list:
     1. [T][ ] Title: CS2113 tP (Day: MON)
     2. [T][ ] Title: CS2113 tp (Day: MON)
                Info: add documentation
    _______________________________________________________________________________
     
$ list lesson
    _______________________________________________________________________________
     Here are the lessons in your list:
     1. [L] Title: CS2113 Lecture (Day: MON) (Start: 1pm, End: 3pm)
    _______________________________________________________________________________
    
$ list all
    _______________________________________________________________________________
     Here are the tasks in your list:
     1. [T][ ] Title: CS2113 tP (Day: MON)
     2. [T][ ] Title: CS2113 tp (Day: MON)
                Info: add documentation
     
     Here are the lessons in your list:
     1. [L] Title: CS2113 Lecture (Day: MON) (Start: 1pm, End: 3pm)
    _______________________________________________________________________________
```

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

