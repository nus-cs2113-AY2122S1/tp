# GetJack'D User Guide

## Introduction

GetJack'D is a **desktop app for managing and planning exercise routines, optimized for use via a Command Line Interface** (CLI).
This application is targeted at computing students who wish to keep track of their workout and exercise routines in spite of their busy schedules.
GetJack'D guarantees faster management of workouts than traditional GUI applications. If you type fast and are looking for an application 
to help you embark on your fitness journey, GetJack'D is the one for you!

## Contents

- [Purpose](#purpose)
- [Quick Start](#quick-start)
- [Features](#features)
  1. [Creating a new workout: `create`](#1-creating-a-new-workout-create)
  2. [Entering into a Workout: `enter`](#2-entering-into-a-workout-enter)
  3. [Exiting from a workout: `back`](#3-exiting-from-a-workout-back)
  4. [Show all workouts: `list`](#4-show-all-workouts-list)
  5. [Deleting a workout: `delete`](#5-deleting-a-workout-delete)
  6. [Adding an exercise to a workout: `add`](#6-adding-an-exercise-to-a-workout-add)
  7. [Mark an exercise done: `done`](#7-mark-an-exercise-done-done)
  8. [Editing an exercise in a workout: `edit`](#8-editing-an-exercise-in-a-workout-edit)
  9. [Show all exercises from a workout: `display`](#9-show-all-exercises-from-a-workout-display)
  10. [Removing an exercise from a workout: `remove`](#10-removing-an-exercise-from-a-workout-remove)
  11. [Searching for workouts and exercises: `search`](#11-searching-for-workouts-and-exercises-search)
  12. [Recommend a workout: `recommend`](#12-recommend-a-workout-recommend)
  13. [Clearing all workouts or exercises: `clear`](#13-clearing-all-workouts-or-exercises-clear)
  14. [Exiting the application: `bye`](#14-exiting-the-application-bye)
  15. [Saving data](#15-saving-data)
  16. [Editable data file](#16-editable-data-file)
  17. [Troubleshooting](#17-troubleshooting)
- [FAQ](#faq)
- [Command Summary](#command-summary)

<div style="page-break-after: always;"></div>

## Purpose

This user guide explains how you can set up the application and utilise the various features delivered by GetJack'D. 
In addition, this guide provides the correct command format to use as well as example commands and expected outcomes for your reference.

If you are an active fitness enthusiast, you can probably dive straight into using the general workout and exercise features.

If you are a beginner, you may be more interested in the recommended workouts and work your way up from there as you get more used to 
working out and using GetJack'D.

## Quick Start

1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest `GetJackd.jar` from [here](https://github.com/AY2122S1-CS2113T-F12-2/tp/releases/)
3. Go to the folder you saved `GetJackd.jar` and note the **absolute file path** (the location of the file).
4. If you are using **Windows**, open up a Command prompt terminal `cmd.exe` or `powershell.exe` and
   navigate to the folder where `GetJackd.jar` is stored (using the file path).
5. For **Mac** and **Linux** users, do the same as step 4 with the terminal of your respective systems.
6. Execute `java -jar GetJackd.jar` in the terminal and the application will start running.

You should be able to see something like this:
```
Welcome to
   ______          _        _____              __      _  ______
 .' ___  |        / |_     |_   _|            [  |  _ | ||_   _ `.
/ .'   \_|  .---.`| |-'      | | ,--.   .---.  | | / ]\_|  | | `. \
| |   ____ / /__\\| |    _   | |`'_\ : / /'`\] | '' <      | |  | |
\ `.___]  || \__.,| |,  | |__' |// | |,| \__.  | |`\ \    _| |_.' /
 `._____.'  '.__.'\__/  `.____.'\'-;__/'.___.'[__|  \_]  |______.'

Let's begin the journey to achieve the physique you desire!
If you don't know where to start, type "help" for a list of possible commands.
Enter your command below.
________________________________________________________
```

--------------------------------------

<div style="page-break-after: always;"></div>

## Features

GetJack'D maintains a list of workouts (hereafter referred to as the "workout list"), where each workout is a list of exercises. 
Using the commands specified in this guide will allow you to manipulate these workouts and the workout list in various ways.

> 💡 Note
>* Words wrapped in square braces `[words]` are parameters, they are the parts of the command that you can change.
>* Words not wrapped in square braces are part of the command format. 
>* Not following said command formats may cause your input to not be recognised by the application.

### 1. Creating a new workout: `create`

Adds a new workout to the list of workouts. You can also add a deadline to your workouts if you want to specify a date to do them by.

Format:
- No deadline: `create [WORKOUT_DESCRIPTION]`

- With deadline: `create [WORKOUT_DESCRIPTION], [DEADLINE]`

Parameters:
* `[WORKOUT_DESCRIPTION]`: The description of the workout you are creating.
* `[DEADLINE]`: Date of to do workout by.

>⚠️ Warning  
>* `DEADLINE` must be entered in the format `yyyy-MM-dd`.
>* There must be a space between the comma `,` and the next parameter as shown in the format.

Usage example(s): 

* No deadline: `create abs`
* With deadline: `create leg day, 2021-10-21`

Expected outcome (*no deadline specified*):

```
create abs
________________________________________________________
New workout created: abs
________________________________________________________
```

Expected outcome (*deadline specified*):

```
create leg day, 2021-10-21
________________________________________________________
New workout created: leg day finish by: 21 Oct 2021
________________________________________________________
```

### 2. Entering into a Workout: `enter`

Enter into a workout so that you can `add` ,`remove` , mark as `done`, `edit` and `display` exercises
in the context of the workout routine you have entered, saving you the trouble of entering the workout index for the commands.

> 💡 Note
>* Entering a workout also confines your search results to within the particular workout you are in.

Format: `enter [WORKOUT_INDEX]`

Parameters: 
- `[WORKOUT_INDEX]`: The workout's number in the workout list.

Usage example(s):

Enter into the workout with index 2: `enter 2` 

Expected outcome:
```
enter 2
			________________________________________________________
			Now inside workout 2 : leg day finish by: 2 Oct 2021
			________________________________________________________
add Push-ups, 5 10
			________________________________________________________
			New exercise added to workout 1 : [ ] Push-ups | 5 sets of 10 reps
			________________________________________________________
```


> 💡 Tip
>* You are able to enter into a different workout from within a workout. 
>* You are able to `add`, `remove`, mark as `done`, `edit` and `display` exercises from other workouts while inside a workout, 
> provided you specify their workout index.

For example,
```
enter 1
			________________________________________________________
			Now inside workout 1 : leg day finish by: 2 Oct 2021
			________________________________________________________
add sit ups, 4 30, 2
			________________________________________________________
			New exercise added to workout 2 : [ ] sit ups | 4 sets of 30 reps
			________________________________________________________
enter 2
			________________________________________________________
			Now inside workout 2 : full body
			________________________________________________________
```

### 3. Exiting from a workout: `back`

Returns you to the main view after entering into a workout.

Format: `back`

Expected outcome:

```
back
________________________________________________________
Back to Main View
________________________________________________________
			
```

### 4. Show all workouts: `list`

Shows you all the workouts in the current list of workouts, in **sorted** order 
(i.e. workouts with deadlines, the earliest dates first, then workouts with no deadlines)


>⚠️ Warning   
>* Since the workouts are sorted, whenever you create or delete a workout, the indices of other workout might change!️

Usage example(s):

`list`

Expected outcome:

```
list
________________________________________________________
Workout list:
1. leg day finish by: 21 Oct 2021
2. abs finish by: 25 Dec 2021
3. run
4. jog
________________________________________________________
```


GetJack'D automatically sorts your workouts by their deadlines every time you add a new workout to your workout list.
The workouts will be sorted in order where workouts with the earliest deadlines appear on top. Workouts without
deadlines will be at the bottom of the list and ranked according to the order they were added to the workout list.

Example (key in the following commands in this order):
1. `create test`
2. `create xmas workout, 2021-12-25`
3. `create halloween workout, 2021-10-31`
4. `create test 2`

Expected final Workout List:
```
________________________________________________________
Workout list:
1. halloween workout finish by: 31 Oct 2021
2. xmas workout finish by: 25 Dec 2021
3. test
4. test 2
________________________________________________________
```
### 5. Deleting a workout: `delete`

Deletes a workout from the list of workouts.

> 💡 Note
>* If you are entered into a workout and delete the workout that you are currently in, you will automatically be returned 
to the main view.

Format: `delete [WORKOUT_INDEX]`

Parameters:
* `[WORKOUT_INDEX]`: The workout's number in the workout list.

Usage example(s):

`delete 1`

Expected outcome:

```
delete 1
________________________________________________________
Deleted workout: abs finish by: 25 Dec 2021
________________________________________________________
```

### 6. Adding an exercise to a workout: `add`

Adds an exercise to a specified workout

Format: 
* Main Mode: `add [EXERCISE_DESCRIPTION], [SETS] [REPS], [WORKOUT_INDEX]`
* Workout Mode: `add [EXERCISE_DESCRIPTION], [SETS] [REPS]`

Parameters:
* `[EXERCISE_DESCRIPTION]`: Description of the exercise to be added.
* `[SETS]`: Number of sets of exercise.
* `[REPS]`: Number of repetitions per set.
* `[WORKOUT_INDEX]`: The workout's number in the workout list.

>⚠️ Warning  
>* There must be a space between the comma `, ` and the next parameter as shown in the format.

Usage example(s):

* Main mode: 

Add squats, 5 sets of 10 reps, add to workout 2: `add squats, 5 10, 2` 

* Entered into workout 1: 

Add lunges, 4 sets of 8 reps: `add lunges, 4 8` 

Expected outcome (*Main mode*):

```
add squats, 5 10, 2
________________________________________________________
New exercise added: [ ] squats | 5 sets of 10 reps
________________________________________________________
```

Expected outcome (*Workout Mode*):
```
enter 1
			________________________________________________________
			Now inside Workout: leg day finish by: 21 Oct 2021
			________________________________________________________
add lunges, 4 8
			________________________________________________________
			New exercise added: [ ] lunges | 4 sets of 8 reps
			________________________________________________________
```

### 7. Mark an exercise done: `done`

Marks an exercise from a specified workout as done.

Format: 
* Main mode: `done [EXERCISE_INDEX], [WORKOUT_INDEX]`
* Workout Mode: `done [EXERCISE_INDEX]`

Parameters:
* `[EXERCISE_INDEX]`: The exercise's number in the workout.
* `[WORKOUT_INDEX]`: The workout's number in the workout list.


>⚠️ Warning  
>* There must be a space between the comma `,` and the next parameter as shown in the format.

Usage example(s):

* Main mode: 

Mark exercise 1 from workout 2 as done: `done 1, 2`

* Entered into workout 1: 

Mark exercise 1 as done: `done 1` 

Expected outcome (*Main mode*):

```
done 2, 1
________________________________________________________
Completed: [X] lunges | 4 sets of 8 reps
________________________________________________________
```
Expected outcome (*Workout mode*): 
```
enter 1
			________________________________________________________
			Now inside Workout: leg day finish by: 21 Oct 2021
			________________________________________________________
done 1
			________________________________________________________
			Completed exercise in workout 1 : [X] lunges | 3 sets of 8 reps
			________________________________________________________

```

### 8. Editing an exercise in a workout: `edit`

Edits an exercise in a specified workout.

Format:
- Main Mode: `edit [EXERCISE_INDEX], [WORKOUT_INDEX], [NEW_DESCRIPTION], [NEW_SETS] [NEW_REPS]`
- Workout Mode: `edit [EXERCISE_INDEX], [NEW_DESCRIPTION], [SETS] [REPS]`

Parameters:
* `[EXERCISE_INDEX]`: The exercise's number in the workout.
* `[WORKOUT_INDEX]`: The workout's number in the workout list.
* `[NEW_DESCRIPTION]`: Edited exercise description.
* `[NEW_SETS]`: Edited number of sets.
* `[NEW_REPS]`: Edited number of reps.

>⚠️ Warning
>* There must be a space between the comma `,` and the next parameter as shown in the format.

Usage example(s):

* Main Mode: 

Edit exercise 2 from workout 1 to new exercise description of kickbacks, new
sets of 4 and new reps of 12: `edit 2, 1, kickbacks, 4 12`

* Entered into workout 1:

Edit exercise 2 to new exercise description of kickbacks, new
sets of 4 and new reps of 12: `edit 2, kickbacks, 4 12 `

Expected outcome (*Main mode*):
```
edit 2, 1, kickbacks, 4 12
________________________________________________________
The edited exercise: [X] kickbacks | 4 sets of 12 reps
________________________________________________________
```
Expected outcome (*Workout mode*):
```text
enter 1
                        ________________________________________________________
                        Now inside workout 1 : workout
                        ________________________________________________________
edit 2, kickbacks, 4 12
                        ________________________________________________________
                        The edited exercise in workout 1 : [X] kickbacks | 4 sets of 12 reps
                        ________________________________________________________
```

### 9. Show all exercises from a workout: `display`

Shows you all the exercises in a specified workout.

Format:
* Main Mode: `display [WORKOUT_INDEX]`
* Workout Mode: `display`

Parameters:
* `[WORKOUT_INDEX]`: The workout's number in the workout list.

Usage example(s):

* Main mode: 

Show all exercises from workout 1: `display 1` 

* Entered into workout 1: 

show all exercises: `display` 

Expected outcome (*Main mode*):

```
display 1
________________________________________________________
Exercises in leg day
1. [ ] squats | 5 sets of 10 reps
2. [X] kickbacks | 4 sets of 12 reps
________________________________________________________
```

Expected outcome (*Workout mode*):
```text
enter 1
                        ________________________________________________________
                        Now inside workout 1 : workout
                        ________________________________________________________
display
                        ________________________________________________________
                        Exercises in (1) workout
                        1. [ ] squats | 5 sets of 10 reps
                        2. [X] kickbacks | 4 sets of 12 reps
                        ________________________________________________________
```

### 10. Removing an exercise from a workout: `remove`

Removes an exercise from a specified workout

Format:
* Main Mode: `remove [EXERCISE_INDEX], [WORKOUT_INDEX]`
* Workout Mode: `remove [EXERCISE_INDEX]`

Parameters:
* `[EXERCISE_INDEX]`: The exercise's number in the workout.
* `[WORKOUT_INDEX]`: The workout's number in the workout list.

>⚠️ Warning  
>* There must be a space between the comma `,` and the next parameter as shown in the format.
>* Only one exercise can be removed at a time. 


Usage example(s):

* Main mode: 

Remove exercise 1 from workout 1: `remove 1, 1` 

* Entered into workout 1:

Remove exercise 1 from workout 1: `remove 1`

Expected outcome (*Main Mode*):

```
remove 1, 1
________________________________________________________
Removed exercise: [ ] squats | 5 sets of 10 reps
________________________________________________________
```

Expected outcome (*Workout Mode*): 
```
enter 1
			________________________________________________________
			Now inside Workout: legs
			________________________________________________________
remove 1
			________________________________________________________
			Removed exercise: [ ] squats | 5 sets of 10 reps
			________________________________________________________
```

### 11. Searching for workouts and exercises: `search`

Searches for workouts and exercises containing the keyword specified.

> 💡 Note
> 
> Main Mode: 
>- Search by name: Displays workouts or exercises that contain the specified keyword in the workout or exercise name.
>- Search by date: Displays workouts that has the specified date (in the format `DD MMM YYY`) as the workout deadline.
>
> Entered into workout: 
>- Search by name: Displays exercises that contain the specified keyword in the exercise name, only within the particular workout.

Format:
* search by name: `search [KEYWORD]`
* search by date: `search [DD MMM YYYY]`

Usage example(s):

* Main Mode: 

search for workouts and exercises that contain "kick" in the description: `search kick`

search for workouts that have the deadline 2 Oct 2021: `search 2 oct 2021`

* Workout Mode:

search for exercises within workout 1 : `search kick` 

Expected outcome (*Main mode*):
```
search kick
________________________________________________________
Matching exercises in (1) leg day finsih by: 2 Oct 2021
1. [X] kickbacks | 4 sets of 12 reps
Matching exercises in (2) full body
1. [ ] kickbacks | 2 sets of 10 reps
________________________________________________________
```
```
search 2 oct 2021
________________________________________________________
Matching workouts: 
1. leg day finish by: 2 Oct 2021
________________________________________________________
```

Expected outcome (*Workout mode*)
```
enter 1
			________________________________________________________
			Now inside Workout: leg day finish by: 2 Oct 2021
			________________________________________________________
search kick
			________________________________________________________
			Matching exercises in (1) leg day finish by: 2 Oct 2021
			1. [X] kickbacks | 4 sets of 12 reps
			________________________________________________________
```

### 12. Recommend a workout: `recommend`

Recommends a set of sample workouts with exercises already added.

Format: `recommend [DIFFICULTY]`

Parameters:
* `[DIFFICULTY]`: Level of difficulty of the workouts to recommend.

> 💡 Note
>* `[DIFFICULTY]` can only take the values `beginner`, `intermediate` or `pro`
>* When the command is executed, the recommended workouts will be automatically added to your workout list.

>⚠️ Warning
>* Duplicates of same recommended workouts will be added to the list if command is executed multiple times.

Expected outcome:
```
________________________________________________________
recommend beginner
________________________________________________________
Abs
┌───────────────────┬───────────────────┬───────────────────┬──────────────────┐
│Index              │Exercise name      │Sets               │Reps              │
├───────────────────┼───────────────────┼───────────────────┼──────────────────┤
│1                  │Sit Ups            │3                  │10                │
├───────────────────┼───────────────────┼───────────────────┼──────────────────┤
│2                  │Plank              │2                  │1                 │
├───────────────────┼───────────────────┼───────────────────┼──────────────────┤
│3                  │Mountain Climbers  │3                  │15                │
└───────────────────┴───────────────────┴───────────────────┴──────────────────┘
Arm
┌───────────────────┬───────────────────┬───────────────────┬──────────────────┐
│Index              │Exercise name      │Sets               │Reps              │
├───────────────────┼───────────────────┼───────────────────┼──────────────────┤
│1                  │Push Ups           │3                  │10                │
├───────────────────┼───────────────────┼───────────────────┼──────────────────┤
│2                  │Inclined Push Ups  │3                  │5                 │
├───────────────────┼───────────────────┼───────────────────┼──────────────────┤
│3                  │Bench Dips         │4                  │12                │
├───────────────────┼───────────────────┼───────────────────┼──────────────────┤
│4                  │Bear Crawl         │4                  │10                │
└───────────────────┴───────────────────┴───────────────────┴──────────────────┘
________________________________________________________
```

### 13. Clearing all workouts or exercises: `clear`

Removes all workouts or all exercises within a workout.

Format: 
* Remove all workouts: `clear workout`
* Remove all exercises in a workout: `clear exercise [WORKOUT_INDEX]`

Parameters:

* `[WORKOUT_INDEX]`: The workout's number in the workout list.

> 💡 Note
>* Include `WORKOUT_INDEX` only when you want to clear exercises.
>* All workouts can also be cleared inside a workout mode. 
>* Clearing workouts while inside a workout will return you to the main view.

Usage example(s): 

* Main Mode:

Clears all the exercises present inside workout 1: `clear exercise 1`

Clears all the workouts present in the application: `clear workout`

* Entered into workout 1: 

Clears all exercises within workout 1: `clear exercise` 


Expected outcome (*Main Mode*):
```
clear exercise 1
________________________________________________________
All exercises in workout 1 have been cleared!
________________________________________________________

clear workout
________________________________________________________
All workouts have been cleared!
________________________________________________________
```


Expected outcome (*Workout Mode*):
```text
enter 1
                        ________________________________________________________
                        Now inside workout 1 : workout
                        ________________________________________________________
clear exercise
                        ________________________________________________________
                        All exercises in workout 1 have been cleared!
                        ________________________________________________________
```


### 14. Exiting the application: `bye`

Exits from the application.

Format: `bye`

Expected outcome:
```
bye
________________________________________________________
Bye. Hope you get your desired body soon, have a great day!
________________________________________________________
```

### 15. Saving data

The data that GetJack'D saves is saved in the hard disk automatically after any command that changes the data (Adding 
or editing workouts and exercises). There is no need to save manually.

### 16. Editable data file

GetJack'D data is saved as a json file `[JAR file location]/data/workouts.json`. Advanced users are welcome to update data
directly by editing that data file.

> ❗  **Caution**: If the data file is detected to be corrupted or does not conform to GetJack'D
> saved file format (json), GetJack'D will automatically stop running and display an error message. GetJack'D will be able
> to run again when the data file conforms to the saved file format (json) again. 

**Error Messages when data file does not conform to saved file format**:

Scenario 1 (Exercise reps and sets are not numbers)
```
________________________________________________________
☹ OOPS!!! Error reading file! Please ensure the sets and reps in data/workouts.json are numbers.
________________________________________________________
```

Scenario 2 (Workout deadlines are not in the correct date format)
```
________________________________________________________
☹ OOPS!!! Error reading file! Please ensure the deadline in data/workouts.json is in the format yyyy-mm-dd.
________________________________________________________
```

Scenario 3 (Invalid JSON file format)
```
________________________________________________________
☹ OOPS!!! Error converting from JSON due to invalid JSON format. Check for proper closing brackets "{ }" and "[ ]" in data/workouts.json.
________________________________________________________
```

**How to resolve errors?**

If you obtained the error messages in any of the 3 scenarios, do ensure that GetJack'D data file is in the valid format as shown below. 
```json
{
  "workouts" : [ {
    "exercises" : [ {
      "description" : "planks",
      "sets" : "3", 
      "reps" : "10",
      "isDone" : "false"
    } ],
    "workoutName" : "xmas workout",
    "deadline" : "2021-12-25"
  }, {
    "exercises" : [ ],
    "workoutName" : "emptyWorkout",
    "deadline" : ""
  }  ]
}
```

_**Things to look out for**_:
1. Data fields in sets and reps are numbers.
2. Data field in deadline is following the format: `YYYY-MM-DD`
3. Missing `{ }`, `[ ]`, `" "` or `,`

### 17. Troubleshooting

**1. Unable to create workouts and add exercises.**
```text
create workout
________________________________________________________
? OOPS!!! Error writing to file while data!
________________________________________________________
```

This occurs when GetJack'D is unable to create the file it stores its data in. You could:

* Go to the folder where GetJack'D is saved and search inside the `data` folder for any folder named `workouts.json`. If such a folder exists then either delete or relocate that folder.
* Reinstall GetJack'D and try moving the application JAR file to another location.

**2. Unable to start application.**

```text
Welcome to
   ______          _        _____              __      _  ______
 .' ___  |        / |_     |_   _|            [  |  _ | ||_   _ `.
/ .'   \_|  .---.`| |-'      | | ,--.   .---.  | | / ]\_|  | | `. \
| |   ____ / /__\\| |    _   | |`'_\ : / /'`\] | '' <      | |  | |
\ `.___]  || \__.,| |,  | |__' |// | |,| \__.  | |`\ \    _| |_.' /
 `._____.'  '.__.'\__/  `.____.'\'-;__/'.___.'[__|  \_]  |______.'

Let's begin the journey to achieve the physique you desire!
If you don't know where to start, type "help" for a list of possible commands.
Enter your command below.
________________________________________________________
________________________________________________________
? OOPS!!! Data file can't be created.
________________________________________________________
```

This occurs when the `data` folder in which the GetJack'D data file is stored fails to be created. You could:

* Go to the folder where GetJack'D is saved and search for any **files** named `data`. If such a file exists then either delete or relocate it.
* Reinstall GetJack'D and try mocing the application JAR file to another location.

<div style="page-break-after: always;"></div>

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Go to the folder you saved `GetJackd.jar`, save a copy of the `data` folder and copy it to the folder 
`GetJackd.jar` is saved on your other computer.

## Command Summary

| Action                                           | Format, Examples                                                                                       |
|--------------------------------------------------|--------------------------------------------------------------------------------------------------------|
| List all workouts                                | Example: `list`                                                                                        |
| Create workout (**without deadline**)                                  | Format: `create [WORKOUT_DESCRIPTION]` <br />Example: `create leg day`
| Create workout (**with deadline**)                                  | Format: `create [WORKOUT_DESCRIPTION], [DEADLINE]` <br />Example: `create abs workout, 2021-12-25`                                         |
| Delete workout                                   | Format: `delete [WORKOUT_INDEX]` <br />Example: `delete 1`                                                 |
| Display exercises in <br /> a particular workout | Format: `display [WORKOUT_INDEX]` <br />Example: `display 1`                                               |
| Recommend workout                                | Format: `recommend [beginner/ intermediate/ pro]` <br />Example: `recommend beginner`                      |
| Add exercise                                     | Format: `add [EXERCISE_DESCRIPTION], [SETS] [REPS], [WORKOUT_INDEX]` <br /> Example: `add Push-ups, 5 10, 1` |
| Mark exercise as done                            | Format: `done [EXERCISE_INDEX], [WORKOUT_INDEX]` <br />Example: `done 5, 1`                                |
| Remove exercise                                  | Format: `remove [EXERCISE_INDEX], [WORKOUT_INDEX]` <br />Example: `remove 5, 1`  
| Edit exercise                                    | Format: `edit [EXERCISE_INDEX], [WORKOUT_INDEX], [NEW_DESCRIPTION], [SETS] [REPS]` <br />Example: `edit 1, 1, Flutter, 4 10` |
| Enter workout                                    | Format: `enter [WORKOUT_INDEX]` <br />Example: `enter 1`                                                   |
| Exit workout                                     | Example: `back`                                                                                          |
| Help                                             | Format: `help [COMMAND_WORD]` <br />Example: `help add`                                                    |
| Search                                           | Format: `search [KEYWORD]` <br />Example: `search legs`                                                    |
| Clear                                           | Format: `clear [exercise/ workout] [WORKOUT_INDEX]` <br />Example: `clear exercise 1` <br />Example: `clear workout`                                               |
| Exit                                             | Example: `bye`                                                                                           |
