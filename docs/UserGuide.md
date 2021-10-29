# User Guide

NUS Buddy is a **fast, flexible and unobtrusive weekly planner**.
Designed specifically for NUS students, it includes an intuitive **task and lesson manager**, as well as a **module planner** 
with CAP calculation features that can be accessed quickly via a Command Line Interface (CLI), taking up the least of 
your time, so you can focus on tasks and lessons that are important to you.

This user guide will help you get started on using NUS Buddy so you can go about organising your tasks, 
lessons and modules right away. The [quick start](#quick-start) will show you how to set up NUS Buddy 
on your computer. The [features](#features) section will show you the list of features and how to use 
them. If you encounter any issues while using NUS Buddy, the [FAQ](#faq) section may help with troubleshooting 
your problems. Finally, the [command summary](#command-summary) will give you an overview of all the 
commands that you can execute.

> Symbols used in this guide:
> 
> * 💡 denotes important information
> * ❗ denotes a warning

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
  * [Setting module grade - `set grade`](#setting-module-grade---set-grade)
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

```shell
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

> ❗ The new files created are used to store the data of your tasks/lessons/modules. 
> Please refrain from deleting these files in order to preserve the data.

## Features

> 💡 The **UPPER_CASE** in the command denotes the parameter that you can enter your value into.
> 
> `[COMPULSORY_PARAM]`: Square brackets denote a compulsory parameter.
> 
> `[OPTION1/OPTION2]`: Denotes either `OPTION1` or `OPTION2` must be chosen as the parameter.
> 
> `{OPTIONAL_PARAM}`: Braces denote an optional parameter.

### Getting help - `help`

Shows you a summary of the commands you can use with NUSBuddy, and the format of their parameters if applicable.

Format: `help`

Example usage:

```shell
$ help
      ______________________________________________________________________________________
       Here are the list of commands that you can try.
       ------------------------------------------------------------------------------------
       add        task   [TITLE] -d [DAY_OF_THE_WEEK] -p {PRIORITY} -i {INFORMATION}
       add        lesson [TITLE] -d [DAY_OF] -s [START_TIME] -e [END_TIME] -l {MEETING_URL}
       add        module [MODULE_CODE]
       list       task {PERIOD/PRIORITY}
       list       lesson {PERIOD}
       list       module {grade}
       delete     task [INDEX]
       delete     lesson [INDEX]
       delete     module [MODULE_CODE]
       done       task [INDEX]
       find       [task/lesson] [KEYWORD]
       moduleinfo [MODULE_CODE]
       set        grade [MODULE_CODE] [GRADE]
       exit           

       Notes: Square brackets -> [COMPULSORY_PARAMETER]
              Curly braces    -> {OPTIONAL_PARAMETER}
              More details: https://ay2122s1-cs2113t-w11-3.github.io/tp/UserGuide.html
      ______________________________________________________________________________________
```

You can use this as a quick guide while using NUS Buddy. The format of the commands shown by `help` 
is the same as the one used by this guide, which can be found [here](#features).

### Adding a Task, Lesson or Module - `add`

#### Task

A task contains a title, day of the week, a priority, and an information.

Format: `add task [TITLE] -d [DAY_OF_THE_WEEK] -p {PRIORITY} -i {INFORMATION}`

* `TITLE` refers to the title of the task.
* `DAY_OF_THE_WEEK` can be any of the following, not case-sensitive: `mon`, `tue`, `wed`, `thu`, `fri`, 
  `sat`, `sun`.
* `PRIORITY` can be either `L`, `M` or `H`, for low, medium, and high priority respectively.
* `INFORMATION` refers to any additional information about the task.

> 💡 The task priority is low by default.

Example:

```shell
$ add task Do CS2113T tP -d sat -p H
      ______________________________________________________________________________________
       Noted. I've added this task:
         [ ] Do CS2113T tP (Saturday)
              Priority: HIGH
              Info: -
       Now you have 1 tasks in the list.
      ______________________________________________________________________________________

$ add task Update CS2113T docs -d thu -i User Guide
      ______________________________________________________________________________________
       Noted. I've added this task:
         [ ] Update CS2113T docs (Thursday)
              Priority: LOW
              Info: User Guide
       Now you have 2 tasks in the list.
      ______________________________________________________________________________________
```

> 💡 The empty braces `[ ]` indicates that the task is incomplete. You can
> [visit this section](#marking-a-task-as-done---done) for more information
> on how to mark a task as done.

#### Lesson

A lesson contains a title, day of the week, the start time and the end time of the lesson.

Format: `add lesson [TITLE] -d [DAY_OF_THE_WEEK] -s [START_TIME] -e [END_TIME] -l {MEETING_URL}`

* `TITLE` refers to the title of the lesson.
* `DAY_OF_THE_WEEK` can be any of the following, not case-sensitive: `mon`, `tue`, `wed`, `thu`, `fri`, 
  `sat`, `sun`.
* `START_TIME` refers to the start time of the lesson, uses 24-hour clock format `HH:MM`.
* `END_TIME` refers to the end time of the lesson, uses 24-hour clock format `HH:MM`.
* `MEETING_URL` refers to the meeting url/link (if available).

Example:

```shell
$ add lesson CS2113T Lecture -d fri -s 16:00 -e 18:00 
      ______________________________________________________________________________________
       Noted. I've added this lesson:
         CS2113T Lecture
          Friday, 04:00 PM - 06:00 PM
          Meeting URL: https://meetinglink.com/
       Now you have 1 lessons in the list.
      ______________________________________________________________________________________

$ add lesson CG2028 Tutorial -d thu -s 13:00 -e 14:00
      ______________________________________________________________________________________
       Noted. I've added this lesson:
         CG2028 Tutorial
          Thursday, 01:00 PM - 02:00 PM
          Meeting URL: -
       Now you have 2 lessons in the list.
    ______________________________________________________________________________________
```

> 💡 The input times are automatically converted to 12-hour clock format.

#### Module

A module is referred to by its module code, and the data is retrieved from [NUSMods](https://nusmods.com/).

Format: `add module [MODULE_CODE]`

* `MODULE_CODE` refers to the module (based on NUSMods), not case-sensitive.

Example:

```shell
$ add module CS2113T
      ______________________________________________________________________________________
       Noted. I've added this module:
         CS2113T Software Engineering & Object-Oriented Programming (4MCs) 
       Now you have 1 modules in the list.
      ______________________________________________________________________________________

$ add module CG2028
      ______________________________________________________________________________________
       Noted. I've added this module:
         CG2028 Computer Organization (2MCs) 
       Now you have 2 modules in the list.
      ______________________________________________________________________________________
```

### Listing Tasks, Lessons or Modules - `list`

#### Task

Format: `list task {PERIOD/PRIORITY}`

* `PERIOD` can be either day of the week, and `today` or `tomorrow`.

Example:

```shell
$ list task
      ______________________________________________________________________________________
       Here are the tasks in your list:
       1. [ ] Update CS2113T docs (Thursday)
              Priority: LOW
              Info: User Guide
       2. [ ] Do CS2113T tP (Saturday)
              Priority: HIGH
              Info: -
      ______________________________________________________________________________________
```

> 💡 By default, the list of tasks is sorted by the day the of the week.

```shell
$ list task today
      ______________________________________________________________________________________
       Here are the tasks on THU:
       1. [ ] Update CS2113T docs (Thursday)
              Priority: LOW
              Info: User Guide
      ______________________________________________________________________________________

$ list task priority
      ______________________________________________________________________________________
       Here are the tasks sorted by priority:
       1. [ ] Do CS2113T tP (Saturday)
              Priority: HIGH
              Info: -
       2. [ ] Update CS2113T docs (Thursday)
              Priority: LOW
              Info: User Guide
      ______________________________________________________________________________________
```

#### Lesson

Format: `list lesson {PERIOD/PRIORITY}`

* `PERIOD` can be either day of the week, and `today` or `tomorrow`.

```shell
$ list lesson
      ______________________________________________________________________________________
       Here are the lessons in your list:
       1. CS2113T Lecture
          Friday, 04:00 PM - 06:00 PM
          Meeting URL: https://meetinglink.com/
       2. CG2028 Lab
          Wednesday, 02:00 PM - 05:00 PM
          Meeting URL: -
      ______________________________________________________________________________________

$ list lesson tomorrow
      ______________________________________________________________________________________
       Here are the lessons on FRI:
       1. CS2113T Lecture
          Friday, 04:00 PM - 06:00 PM
          Meeting URL: -
      ______________________________________________________________________________________
``` 

#### Module

Format: `list module {grade}`

* `grade` displays the grade for each module and your current CAP.

```shell
$ list module
      ______________________________________________________________________________________
       Here are the modules in your list:
       1. CS2113T Software Engineering & Object-Oriented Programming (4MCs) 
       2. CG2028 Computer Organization (2MCs) 
      ______________________________________________________________________________________

$ list module grade
      ______________________________________________________________________________________
       Here are the modules in your list:
       1. CS2113T Software Engineering & Object-Oriented Programming (4MCs) 
          Grade: A+
       Your current CAP is: 5.0
      ______________________________________________________________________________________
```

### Marking a Task as done - `done`

Format: `done task [INDEX]`

* `INDEX` refers to the index of the task. You can view it by executing `list task`.

Example:

```shell
$ list task
      ______________________________________________________________________________________
       Here are the tasks in your list:
       1. [ ] Update CS2113T docs (Thursday)
              Priority: LOW
              Info: User Guide
       2. [ ] Do CS2113T tP (Saturday)
              Priority: HIGH
              Info: -
      ______________________________________________________________________________________

$ done task 2
      ______________________________________________________________________________________
       Nice! I've marked this task as done: 
         [X] Do CS2113T tP (Saturday)
              Priority: HIGH
              Info: -
       Now you have 1 pending tasks.
      ______________________________________________________________________________________

$ list task
      ______________________________________________________________________________________
       Here are the tasks in your list:
       1. [ ] Update CS2113T docs (Thursday)
              Priority: LOW
              Info: User Guide
       2. [X] Do CS2113T tP (Saturday)
              Priority: HIGH
              Info: -
      ______________________________________________________________________________________
```

> 💡 `[X]` indicates that the task is completed.

### Deleting Tasks, Lessons or Modules - `delete`

> ❗ Once removed, the deleted task/lesson/module cannot be restored.

#### Task

Format: `delete task [INDEX]`

Example:

```shell
$ list task
      ______________________________________________________________________________________
       Here are the tasks in your list:
       1. [ ] Update CS2113T docs (Thursday)
              Priority: LOW
              Info: User Guide
       2. [X] Do CS2113T tP (Saturday)
              Priority: HIGH
              Info: -
      ______________________________________________________________________________________

$ delete task 2
      ______________________________________________________________________________________
       Ok. The following task has been deleted:
         [X] Do CS2113T tP (Saturday)
              Priority: HIGH
              Info: -
       Now you have 1 task(s) in the list.
      ______________________________________________________________________________________

$ list task
      ______________________________________________________________________________________
       Here are the tasks in your list:
       1. [ ] Update CS2113T docs (Thursday)
              Priority: LOW
              Info: User Guide
      ______________________________________________________________________________________
```

#### Lesson

Format: `delete lesson [INDEX]`

Example:

```shell
$ list lesson
      ______________________________________________________________________________________
       Here are the lessons in your list:
       1. CS2113T Lecture
          Friday, 04:00 PM - 06:00 PM
          Meeting URL: -
       2. CG2028 Tutorial
          Thursday, 01:00 PM - 02:00 PM
          Meeting URL: -
      ______________________________________________________________________________________

$ delete lesson 2
      ______________________________________________________________________________________
       Ok. The following lesson has been deleted:
         CG2028 Tutorial
          Thursday, 01:00 PM - 02:00 PM
          Meeting URL: -
       Now you have 1 lesson(s) in the list.
      ______________________________________________________________________________________

$ list lesson
      ______________________________________________________________________________________
       Here are the lessons in your list:
       1. CS2113T Lecture
          Friday, 04:00 PM - 06:00 PM
          Meeting URL: -
      ______________________________________________________________________________________
```

#### Module

Format: `delete module [MODULE_CODE]`

Example:

```shell
$ list module
      ______________________________________________________________________________________
       Here are the modules in your list:
       1. CS2113T Software Engineering & Object-Oriented Programming (4MCs) 
       2. CG2028 Computer Organization (2MCs) 
      ______________________________________________________________________________________

$ delete module CG2028
      ______________________________________________________________________________________
       Ok. The following module has been deleted:
         CG2028 Computer Organization (2MCs) 
       Now you have 1 module(s) in the list.
      ______________________________________________________________________________________

$ list module
      ______________________________________________________________________________________
       Here are the modules in your list:
       1. CS2113T Software Engineering & Object-Oriented Programming (4MCs) 
      ______________________________________________________________________________________
```

### Finding Tasks or Lessons by keyword - `find`

Finds tasks/lessons that contains the specific keeyword.

#### Task

Format: `find task [KEYWORD]`

* `KEYWORD` refers to the intended keyword, not case-sensitive

Example:

```shell
$ find task CS2113T
      ______________________________________________________________________________________
       Here are the matching tasks in your list:
       1. [ ] Update CS2113T docs (Thursday)
              Priority: LOW
              Info: User Guide
      ______________________________________________________________________________________
```

#### Lesson

Format: `find lesson [KEYWORD]`

* `KEYWORD` refers to the intended keyword, not case-sensitive

Example:

```shell
$ find lesson CS2113T
      ______________________________________________________________________________________
       Here are the matching lessons in your list:
       1. CS2113T Lecture
          Friday, 04:00 PM - 06:00 PM
          Meeting URL: -
      ______________________________________________________________________________________
```

### Retrieving Module information - `moduleinfo`

Shows you a complete list of information for any modules that are listed on NUSMods. You can find information 
such as module title, modular credits, department, faculty, preclusion, pre-requisites, etc.

Format: `moduleinfo [MODULE_CODE]`

* `MODULE_CODE` refers to the module code (based on NUSMods), not case-sensitive

Example:

```shell
$ moduleinfo CS3219
      ______________________________________________________________________________________
       CS3219 Software Engineering Principles and Patterns (4MCs) 
       Department: Computer Science
       Faculty: Computing
       Preclusion: CS3213 Software Systems Design
       Pre-requisite: CS2103 or its equivalent
       Core Requisites: null
      ______________________________________________________________________________________
```

### Setting module grade - `set grade`

Format: `set grade [MODULE_CODE] [GRADE]`

Example:

```shell
$ set grade CS2113T A+
      ______________________________________________________________________________________
       You have changed your grade for this module: 
          CS2113T Software Engineering & Object-Oriented Programming (4MCs) 
          Grade: A+
      ______________________________________________________________________________________
```

### Exiting the program - `exit`

Format: `exit`

Example:

```shell
$ exit
      _______________________________________________________________________________
       Bye!
      _______________________________________________________________________________
```

### Saving the data

Data from NUS Buddy is automatically saved on your local machine.

## FAQ

**Q:** How do I transfer my data to another Computer?<br>
**A:** Launch the app once on the new computer and exit. Afterwards, replace the data folder in the Duke home folder of your new computer with that of your previous computer.<br>

## Command summary

| Command                                                                                | Purpose                                          |
| :------------------------------------------------------------------------------------- | :----------------------------------------------- |
| `add        task   [TITLE] -d [DAY_OF_THE_WEEK] -p {PRIORITY} -i {INFORMATION}`        | To add a task                                    |
| `add        lesson [TITLE] -d [DAY_OF] -s [START_TIME] -e [END_TIME] -l {MEETING_URL}` | To add a lesson                                  |
| `add        module [MODULE_CODE]`                                                      | To add a module                                  |
| `list       task {PERIOD/PRIORITY}`                                                    | To list all tasks                                |
| `list       lesson {PERIOD}`                                                           | To list all lessons                              |
| `list       module {grade}`                                                            | To list all modules                              |
| `delete     task [INDEX]`                                                              | To delete a task                                 |
| `delete     lesson [INDEX]`                                                            | To delete a lesson                               |
| `delete     module [MODULE_CODE]`                                                      | To delete a module                               |
| `done       task [INDEX]`                                                              | To mark a task as done                           |
| `find       [task/lesson] [KEYWORD]`                                                   | To find tasks/lessons with the specified keyword |
| `moduleinfo [MODULE_CODE]`                                                             | To display module details                        |
| `set        grade [MODULE_CODE] [GRADE]`                                               | To set the grade for a particular module         |
| `exit`                                                                                 | To exit the program                              |
