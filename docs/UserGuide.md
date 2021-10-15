# User Guide

Ha(ppy)Bit is a desktop app aimed to improve both the **physical and mental lifestyle of students**, 
through the setting and tracking of goals to cultivate good habits. 
The app operates on a Command Line Interface (CLI) while still 
comprising features typically found in a Graphical User Interface (GUI). 
Students who type fast will find that Ha(ppy)Bit performs habit tracking more efficiently than your 
conventional GUI apps.

* [Quick Start](#quick-start)
* [Terminology](#terminology)
* [Features](#features)
    * [Ask for Help: `help`](#ask-for-help-help)
    * [Set a Goal: `set`](#set-a-goal-set)
    * [Remove a Goal: `remove`](#remove-a-goal-remove)
    * [List all Goals: `list`](#list-all-goals-list)
    * [Add a Habit: `add`](#add-a-habit-add)
    * [Complete Habit: `done`](#complete-habit-done)
    * [Delete Habit: `delete`](#delete-habit-delete)
    * [View Habit: `view`](#view-habit-view)
    * [Exit Program: `bye`](#exit-program-bye)
* [About Loading and Saving Data](#about-loading-and-saving-data)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Quick Start

1. Ensure you have Java 11 installed in your computer. If you do not have it installed, download it from [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html).
2. Download the latest tp.jar from [here](https://github.com/AY2122S1-CS2113T-F14-1/tp/releases/tag/v1.0). 
3. Copy the file to the folder you want to use as the home folder for your Ha(ppy)Bit.
4. Open terminal of choice inside folder containing jar file
5. Run jar file as java -jar tp.jar.

## Terminology

Name | Description | Example
------- | ------------------------------------------------ | -------------------
Goal | A long term achievement you wish to accomplish | `Lose 5kg by Dec` 
Habit | Checkpoints / Steps taken to achieve goal | `Run 5km`         

## Features 

Below are the commands that the app supports.
Each section includes the function of the command and the command format.

### Ask for Help: `help`
Displays a  list of all possible commands. If a user types in an invalid command, it will invoke this method by default.

Format: `help`

Example:
```
Hello! These are all the possible commands for this habit tracker :) (flags within {} brackets are optional)
1. set a goal: set <goal name> {-<goal type> /<start date>} /<end date>
   -> Goal types include: default, sleep, food, exercise and study
2. remove a goal: remove <goal index>
3. list all goals for that habit: list
4. add a habit to a goal: add <goal index> <habit name>
5. delete a habit from a goal: delete <goal index> <habit index>
6. indicate a habit as done: done <goal index> <habit index>
7. View all the habits user has under a goal: view <goal index>
8. Exit habit tracker program: bye
```

### Set a Goal: `set`
Set a new goal for long term achievement you wish to accomplish. Goals must have an end date while goal type and
start dates are optional.

Format: `set <GOAL_NAME> {-<GOAL_TYPE> /<START_DATE>} /<END_DATE>` 

Note:
1. Dates must be in `DDMMYYYY` format. For example, 01 January 2021 must be written as `01012020`.
2. If the optional `/<START_DATE>` argument is not provided, `/<END_DATE>` will be used instead. 
3. The optional `-<GOAL_TYPE>` argument can take one of the following flags:
   1. `sl` &#8594; Sleep
   2. `fd` &#8594; Food
   3. `ex` &#8594; Exercise
   4. `sd` &#8594; Study

Example:
```
set Reduce spending /01012022 /31122022
```

Output:
```
Your goal: [DF] Reduce spending has been added.
```

### Remove a Goal: `remove`

### List all Goals: `list`
Lists all goals currently added and set by the user

Format: `list`

Example:
```
There are 2 goal(s) in your list:
[EX] Exercise More
[DF] Become Gudetama
```

### Add a Habit: `add`
Adds a habit that is linked to a goal.

Format: `add <GOAL_INDEX> <HABIT_NAME>`

Example:
```
add 1 Run 4km
```

Output:
```
Your habit:  Run 4km has been added to your goal: [DF] Exercise More
```


### Complete Habit: `done`
Mark a habit under a goal as done.

Format: `done <GOAL_INDEX> <HABIT_INDEX>`

Example: 
```
done 1 2
```

Output: 
```
Your habit of " Run 6km" under the goal "[DF] Exercise More" has been set as done.
```


### Delete Habit: `delete`

### View Habits: `view`
List all habits' user has added under a specific goal.

Format: `view <GOAL_INDEX>` 

Example:
```
view 1
```

Output
```
Here are your 2 habit(s) under the goal "[EX] Exercise More".
[ ]  Run 4km
[X]  Run 6km
```

### Exit Program: `bye`

## About Loading and Saving Data
**Loading saved data:** 

Whenever you run the program, the program will **automatically** look for any
saved data in the relative storage path, `data/habits.txt`. 
* If the storage file is found, `File exists` will be printed. 
* If the storage file is not found, the program will create 
one for you at the relative storage path, `data/habits.txt`.

**Saving data:** 

Before you exit the program, the goals you set and the habits you tracked
are **automatically** saved in a text file at the relative storage path, `data/habits.txt`.

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

Action | Command Format | Example
------------ | ------------ | ------------
Set goal | `set <GOAL_NAME> {-<GOAL_TYPE> /<START_DATE>} /<END_DATE>` | `set Reduce spending /01012022 /31122022`
Remove goal | `remove <GOAL_INDEX>` | `remove 1`
List goals | `list` | `list`
Add habit | `add <GOAL_INDEX> <HABIT_NAME>` | `add 1 Run 4km`
Done habit | `done <GOAL_INDEX> <HABIT_INDEX>` | `done 1 2`
Delete habit  | `delete <GOAL_INDEX> <HABIT_INDEX>` | `delete 2 1`
View habits | `view <GOAL_INDEX>` | `view 1`
Exit Program | `bye` | `bye`

