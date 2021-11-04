# User Guide

- [Introduction](#introduction)
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
  17. [Automatic sorting of Workouts](#17-automatic-sorting-of-workouts)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Introduction

Get Jack'd is a **desktop app for managing and planning exercise routines, optimized for use via a Command Line Interface** (CLI).
If you can type fast, Get Jack'd can manage your workouts faster than traditional GUI apps.

## Quick Start

1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest `GetJackd.jar` from [here](https://github.com/AY2122S1-CS2113T-F12-2/tp/releases/)
3. Go to the folder you saved `GetJackd.jar` and note the **absolute file path**.
4. If you are using **Windows**, open up a Command prompt terminal `cmd.exe` or `powershell.exe` and
   navigate to the folder where `GetJackd.jar` is stored (using the file path).
5. For **Mac** and **Linux** users, do the same as step 4 with the terminal of your respective systems.
6. Execute `java -jar GetJackd.jar`in the terminal and the application will start running.

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

## Using this guide
This guide is meant to describe and explain how to use Get Jack'd, including the various commands and inputs.
Here are some things to take note of while using this guide:
* ⚠️ indicates something important to note


* ❗ indicates something very important to note, failure to adhere to it may cause the program to crash.


* Words wrapped in square braces `[words]` are parameters, they are the parts of the command that you can change.
   Words not wrapped in square braces are part of the command format. Not following said command format may cause your 
   input to not be recognised by the application. 

  
## Features

### 1. Creating a new workout: `create`

Adds a new workout to the list of workouts. You are free to add a deadline to your workouts too.

Format
- No deadline: `create [WORKOUT_DESCRIPTION]`

- With deadline: `create [WORKOUT_DESCRIPTION], [DEADLINE]`

⚠️  `DEADLINE` needs to be in format `yyyy-MM-dd`.

⚠️ There must be a space between the comma `,` and the next parameter as shown in the format.
 
Usage examples: 

- No deadline: `create abs`
- With deadline: `create leg day, 2021-10-21`

Expected outcome (**no deadline specified**):

```
create abs
________________________________________________________
New workout created: abs
________________________________________________________
```

Expected outcome (**deadline specified**):

```
create leg day, 2021-10-21
________________________________________________________
New workout created: leg day finish by: 21 Oct 2021
________________________________________________________
```

### 2. Entering into a Workout: `enter`

Allows you to enter into a workout so that you can `add` ,`remove` , mark as `done` and `display` exercises
in the context of the workout routine you have entered, saving you the trouble of entering the workout index everytime.

Entering a workout also confines your search results to within the particular workout you are in.


Format: `enter [WORKOUT_INDEX]`

Example of usage:

`enter 2` = enter into the workout with index 2

Expected outcome:
```
enter 2
			________________________________________________________
			Now inside workout 2 : leg day finish by: 2 Oct 2021
			________________________________________________________
```


Tip: You may enter another workout, say workout 2, while you are currently in one workout, say workout 1.
You may also add, remove, edit exercises in workout 2 while you are in workout 1.
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

Allows you to exit back into the main view once you have entered into a workout.

Format `back`

Expected outcome:

```
back
________________________________________________________
Back to Main View
________________________________________________________
			
```

### 4. Show all workouts: `list`

Shows you all the workouts in the current list of workouts, in **sorted** order: 
workouts with deadlines (in ascending order), then workouts without deadlines.

⚠ Since the workouts are sorted , whenever you create or delete a workout, the indices of other workout might change!️

Usage example:

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

### 5. Deleting a workout: `delete`

Deletes a workout from the list of workouts
Tip: If you are in Workout Mode and you delete the workout that you are currently in, you will automatically be returned 
to Main Mode.

Format: `delete [WORKOUT_INDEX]`

* Workout index is the index of the workout in the list

Usage example:

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

Format 
- Main Mode: `add [EXERCISE_DESCRIPTION], [SETS] [REPS], [WORKOUT_INDEX]`
- Workout Mode: `add [EXERCISE_DESCRIPTION], [SETS] [REPS]`

* Note the comma and space `, `  separating the command arguments
* Sets and reps are entered in the form of two numbers separated by a space 

⚠️ There must be a space between the comma `, ` and the next parameter as shown in the format.

Usage examples:

- Main mode: `add squats, 5 10, 2`  = squats, 5 sets of 10 reps, add to workout 2

- Workout Mode: `enter 1` then `add lunges, 4 8` = lunges, 4 sets of 8 reps, add to workout 1

Expected outcome (main mode):

```
add squats, 5 10, 2
________________________________________________________
New exercise added: [ ] squats | 5 sets of 10 reps
________________________________________________________
```

Expected outcome (Workout Mode):
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

Marks an exercise from a specified workout as done

Format 
- Main mode: `done [EXERCISE_INDEX], [WORKOUT_INDEX]`

- Workout Mode: `done [EXERCISE_INDEX]`

⚠️ There must be a space between the comma `,` and the next parameter as shown in the format.

Usage example:

- Main mode: `done 1, 2` = mark exercise 1 from workout 2 as done
- Workout Mode: `enter 1` then `done 1` = mark exercise 1 from workout 1 as done

Expected outcome (main mode):

```
done 2, 1
________________________________________________________
Completed: [X] lunges | 4 sets of 8 reps
________________________________________________________
```
Expected outcome (Workout Mode): 
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

Edits an exercise in a specified workout

Format
- Main Mode: `edit [EXERCISE_INDEX], [WORKOUT_INDEX], [NEW_DESCRIPTION], [SETS] [REPS]`
- Workout Mode: `edit [EXERCISE_INDEX], [NEW_DESCRIPTION], [SETS] [REPS]`

⚠️ There must be a space between the comma `,` and the next parameter as shown in the format.

Usage example:
- Main Mode: `edit 2, 1, kickbacks, 4 12 ` = edit exercise `2` from workout `1` to new exercise description of`kickbacks`, new
sets of `4` and new reps of `12`
- Workout Mode: `enter 1` then `edit 2, kickbacks, 4 12 ` = edit exercise `2` from workout `1` to new exercise description of`kickbacks`, new
  sets of `4` and new reps of `12`

Expected outcome:
```
edit 2, 1, kickbacks, 4 12
________________________________________________________
The edited exercise: [X] kickbacks | 4 sets of 12 reps
________________________________________________________
```

### 9. Show all exercises from a workout: `display`

Shows you all the exercises in a specified workout

Format
- Main Mode: `display [WORKOUT_INDEX]`
- Workout Mode: `display`

Examples of usage:

- Main Mode: `display 1` = Show all exercises from workout 1
- Workout Mode: `enter 1` then `display` = show all exercises from workout 1

Expected outcome:

```
display 1
________________________________________________________
Exercises in leg day
1. [ ] squats | 5 sets of 10 reps
2. [X] kickbacks | 4 sets of 12 reps
________________________________________________________
```

### 10. Removing an exercise from a workout: `remove`

Removes an exercise from a specified workout

Format
- Main Mode: `remove [EXERCISE_INDEX], [WORKOUT_INDEX]`
- Workout Mode: `remove [EXERCISE_INDEX]`

⚠️ There must be a space between the comma `,` and the next parameter as shown in the format.

⚠️ Only one exercise can be removed at a time. 


Examples of usage:

- Main Mode: `remove 1, 1` = remove exercise 1 from workout 1
- Workout Mode: `enter 1` then `remove 1` = remove exercise 1 from workout 1

Expected outcome (Main Mode):

```
remove 1, 1
________________________________________________________
Removed exercise: [ ] squats | 5 sets of 10 reps
________________________________________________________
```

Expected outcome (Workout Mode): 
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

Main Mode: 
- Search by name: Displays workouts or exercises that contain the specified keyword in the workout or exercise name.
- Search by date: Displays workouts that has the specified date (in the format `DD MMM YYY`) as the workout deadline.

Workout Mode: 
- Search by name: Displays exercises that contain the specified keyword in the exercise name, within the particular workout.

Format
- search by name: `search [KEYWORD]`
- search by date: `search [DD MMM YYYY]`

Examples of usage:

- Main Mode: 
  - `search kick` = search for workouts and exercises that contain "kick" in the description
  - `search 2 oct 2021` = search for workouts that have the deadline 2 Oct 2021
- Workout Mode:
  - `enter 1` then `search kick` = search for exercises within workout 1 

Expected outcome:

- Main Mode:
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

- Workout Mode
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

Provides a given set of workouts with exercises.

⚠️ This command will add the recommended workouts to your workout list.

Format: `recommend [beginner/ intermediate/ pro]`

Example of usage: `recommend beginner`

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

Helps you to remove all the exercises in a specified workout or all workouts in the application in one
go.

Format: `clear [exercise/ workout] [WORKOUT_INDEX]`

⚠️ Include `WORKOUT_INDEX` only when you want to clear exercises.

Example of usage: 

`clear exercise 1` - Clears all the exercises present inside workout 1

`clear workout` - Clears all the workouts present in the application

Expected outcome:

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

### 14. Exiting the application: `bye`

Allows you to exit from the application.

Format: `bye`

Expected outcome:
```
bye
________________________________________________________
Bye. Hope you get your desired body soon, have a great day!
________________________________________________________
```

### 15. Saving data

GetJackd's workout list data is saved in the hard disk automatically after any command that changes the data (Adding 
or editing workouts and exercises). There is no need to
save manually.

### 16. Editable data file

GetJackd's data is saved as a json file `[JAR file location]/data/workouts.json`. Advanced users are welcome to update data
directly by editing that data file.

❗  **Caution**: If your changes to the data file is detected to be corrupted or does not conform to GetJackd's
saved file format (json), GetJack'd will automatically stop running and display an error message. GetJackd will be able
to run again when the data file conforms to the saved file format (json) again. 

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

### 17. Automatic sorting of Workouts

GetJackd automatically sorts your workouts by their deadlines every time you add a new workout to your workout list. The
workouts will be sorted in descending order where workouts with the earliest deadlines being on top. Workouts without 
deadlines will be at the bottom of the list and ranked according to the time they were added to your workout list. 

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
