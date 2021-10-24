# User Guide

_Ha(ppy)Bit_ is a desktop app aimed to **empower students** to improve their **well-being**,
amidst the hectic and stressful **university life**. 
The app enables students to cultivate **good habits**, through the setting and tracking of **goals**.
The app operates on a Command Line Interface (CLI) while still 
comprising features typically found in a Graphical User Interface (GUI). 
Students who type fast will find that _Ha(ppy)Bit_ performs habit tracking more efficiently than your 
conventional GUI apps.

* [Quick Start](#quick-start)
* [Navigation](#navigation)
* [Terminology](#terminology)
* [Features](#features)
    * [Ask for Help: `help`](#ask-for-help-help)
    * [Set a Goal: `set`](#set-a-goal-set)
    * [Update a Goal: `update`](#update-a-goal-update)
    * [Remove a Goal: `remove`](#remove-a-goal-remove)
    * [List all Goals: `list`](#list-all-goals-list)
    * [Add a Habit: `add`](#add-a-habit-add)
    * [Complete a Habit: `done`](#complete-a-habit-done)
    * [Delete a Habit: `delete`](#delete-a-habit-delete)
    * [View all Habit: `view`](#view-all-habits-view)
    * [Exit the Program: `bye`](#exit-the-program-bye)
* [About Loading and Saving Data](#about-loading-and-saving-data)
* [FAQ](#faq)
* [Command Summary](#command-summary)
* [Useful Links](#useful-links)

## Quick Start

1. Ensure you have Java 11 installed in your computer. If you do not have it installed, 
download it from [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html).
2. Download the latest `tp.jar` [here](https://github.com/AY2122S1-CS2113T-F14-1/tp/releases/tag/v1.0). 
3. Copy the file to the folder you want to use as the **home folder** for your _Ha(ppy)Bit_.
4. Open your terminal of choice inside the folder containing `tp.jar` file.
5. Run the file using `java -jar tp.jar`.
6. Upon loading the app you should see the following screen which would indicate that you have successfully run the app
   ```
   ==============================================================================
     _  _   __   ____  ____  _  _  ____  __  ____
    / )( \ / _\ (  _ \(  _ \( \/ )(  _ \(  )(_  _)
    ) __ (/    \ ) __/ ) __/ )  /  ) _ ( )(   )(
    \_)(_/\_/\_/(__)  (__)  (__/  (____/(__) (__)     tracking habits made fun...
    ==============================================================================

    Howdy! Welcome to Ha(ppy)Bit!
    Select from one of the following menu items:
    [1] About HappyBit
    [2] User Guide
    [3] Developer Guide
    [4] Demo Video
    [5] Start Application
    
    Option:
   ```

## Navigation
In the current loading screen, users will be able to execute the following actions based on the number they input
1. About Ha(ppy)Bit - Shows brief description of the program and a short meet the team section
    ```
   Option: 1

    Ha(ppy)Bit is a desktop app aimed to improve both the physical and mental lifestyle of students,
    through the setting and tracking of goals to cultivate good habits. The app operates on a Command
    Line Interface (CLI) while still comprising features typically found in a Graphical User Interface
    (GUI). Students who type fast will find that Ha(ppy)Bit performs habit tracking more efficiently
    than your conventional GUI apps.
    
    Meet The Team
    =================================================================
    ||  Tan Kah Heng       ||  Creative Director, Visionary        ||
    ||  Swaminathan Varun  ||  Business Manager, Technical Advisor ||
    ||  Tan Jun Heng Daren ||  Frontend Developer                  ||
    ||  Tan Hui En         ||  Backend Developer                   ||
    ||  Swann Tet Aung     ||  Backend Developer                   ||
    =================================================================
    
    Current Version: v2.0 (updated 28-Oct-2021)
    
    
    Press enter to return to main menu...
    ```
2. User Guide - Opens up User Guide github page in user's web browser.
    ```
    Option: 2

    Opening in a web browser...
    
    Press enter to return to main menu...
    ```
3. Developer Guide - Opens up Developer Guide github page in user's web browser
    ```
    Option: 3

    Opening in a web browser...
    
    Press enter to return to main menu...
    ```
4. Demo Video - Links to Demo Video (Not done yet)
5. Start Application - Brings user to the application. User can start using the app or continue where they left off.
    ```
    Starting application...
    Type 'return' to return to main menu
   
    ==============================================================================
     _  _   __   ____  ____  _  _  ____  __  ____
    / )( \ / _\ (  _ \(  _ \( \/ )(  _ \(  )(_  _)
    ) __ (/    \ ) __/ ) __/ )  /  ) _ ( )(   )(
    \_)(_/\_/\_/(__)  (__)  (__/  (____/(__) (__)     tracking habits made fun...
    ==============================================================================
    
    Command:
    ```


## Terminology

Name  | Description | Example
----- | --------------------------------------------------------- | -------------------
Goal  | A long term achievement you wish to accomplish.           |`Lose 5kg by Dec`
Habit | Checkpoints; actionable tasks to be done to achieve goal. | `Run 5km`         

## Features 

Below are the commands that the app supports.
Each section describes the function of the command and the command format.

**NOTE**: For all commands and features, apart from the positioning of the command word, all other flags and parameters 
can be placed in any order as long as their respective tags are present e.g. `n/, s/, e/` etc.

### Ask for Help: `help`
Displays a list of all possible commands. 
If a user types in an invalid command, it will invoke this method by default.

Format: `help`

Output:
```
________________________________________________________________________________________________________________________
Hello! These are all the possible commands for this habit tracker :) (flags within {} brackets are optional)
- set a goal: set n/<goal name> { t/<goal type> s/<start date> } e/<end date>
   -> Goal types include: default[df], sleep[sl], food[fd], exercise[ex] and study[sd]
- update a goal: update g/<goal index> n/<new goal name>
- remove a goal: remove g/<goal index>
- list all goals for that habit: list
- add a habit to a goal: add g/<goal index> n/<habit name> i/<interval>
- delete a habit from a goal: delete g/<goal index> h/<habit index>
- indicate a habit as done: done g/<goal index> h/<habit index>
- View all the habits user has under a goal: view g/<goal index>
- Exit habit tracker program: bye
________________________________________________________________________________________________________________________

Press enter to return to command mode...
```

### Set a Goal: `set`
Sets a new goal for a long term achievement you wish to accomplish. Goals must have an end date while the goal type and
start dates are optional.

Format: `set n/<GOAL_NAME> { t/<GOAL_TYPE> s/<START_DATE> } e/<END_DATE>` 

Note:
1. Dates must be in `DDMMYYYY` format. For example, 01 January 2021 must be written as `01012020`.
2. The two flags contained within the `{}` brackets indicate the optional inputs of `t/<GOAL_TYPE>` and `s/<START_DATE>`.
3. The optional `<GOAL_TYPE>` argument can take one of the following flags:
   1. `sl` &#8594; Sleep
   2. `fd` &#8594; Food
   3. `ex` &#8594; Exercise
   4. `sd` &#8594; Study
4. Otherwise, it will be of default goal type `df`.

Example:
```
Command: set n/Reduce Spending e/31122021
```

Output:
```
________________________________________________________________________________________________________________________
Your goal: [DF] Reduce Spending has been added.
________________________________________________________________________________________________________________________

Press enter to return to command mode...

```
### Update a Goal: `Update`
Updates the name of a goal specified by its index.

Format: `update g/<GOAL_INDEX> n/<NEW_GOAL_NAME>`

Note: As of now, only goal name may be updated. To update other goal features you just have to wait :p 

Example:

```
Command: update g/1 n/Decrease Spending
```
Output:
```
________________________________________________________________________________________________________________________
Your goal "Reduce Spending" has been changed to "Decrease Spending".
________________________________________________________________________________________________________________________

Press enter to return to command mode...
```

### Remove a Goal: `remove`
Removes a goal specified by its index.

Format: `remove g/<GOAL_INDEX>`

Example:
```
Command: remove 1
```

Output:
```
________________________________________________________________________________________________________________________
Your goal: [DF] Decrease Spending has been removed.
________________________________________________________________________________________________________________________

Press enter to return to command mode...
```

### List all Goals: `list`
Lists all goals currently set by the user.

Format: `list`

Output:
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


### Complete a Habit: `done`
Marks a habit under a goal as done.

Format: `done <GOAL_INDEX> <HABIT_INDEX>`

Example: 
```
done 1 2
```

Output: 
```
Your habit of " Run 6km" under the goal "[DF] Exercise More" has been set as done.
```


### Delete a Habit: `delete`
Deletes a habit under a goal.

Format: `delete <GOAL_INDEX> <HABIT_INDEX>`

Example:
```
delete 1 2
```

Output:
```
Your habit of " Run 6km" under the goal "[DF] Exercise More" has been deleted.
```

### View all Habits: `view`
Lists all habits under a specific goal.

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

### Exit the Program: `bye`
Exits the program.

**Make sure to use this command before exiting the program
to ensure that your data are saved properly.**

Format: `bye`

Output:
```
Thanks for using Ha(ppy)Bit, see you in a bit! (hehe)

"We are what we repeatedly do. Excellence, then, is not an act, but a habit."
 — Will Durant
```

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
Set goal | `set n/<GOAL_NAME> { t/<GOAL_TYPE> s/<START_DATE> } e/<END_DATE>` | `set Reduce spending /01012022 /31122022`
Update goal | `update g/<GOAL_INDEX> n/<NEW_GOAL_NAME>` | `update g/1 n/Decrease Spending`
Remove goal | `remove g/<GOAL_INDEX>` | `remove 1`
List goals | `list` | `list`
Add habit | `add n/<HABIT_NAME g/<GOAL_INDEX> i/<INTERVAL>` | `add 1 Run 4km`
Done habit | `done <GOAL_INDEX> <HABIT_INDEX>` | `done 1 2`
Delete habit  | `delete <GOAL_INDEX> <HABIT_INDEX>` | `delete 2 1`
View habits | `view <GOAL_INDEX>` | `view 1`
Exit Program | `bye` | `bye`

## Useful Links

Visit our [Main Page](README.md) to find more useful links.
