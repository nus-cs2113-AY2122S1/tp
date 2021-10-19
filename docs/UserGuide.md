# User Guide

- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
  1. [Creating a new workout: `create`](#1-creating-a-new-workout-create)
  2. [Show all workouts: `list`](#2-show-all-workouts-list)
  3. [Deleting a workout: `delete`](#3-deleting-a-workout-delete)
  4. [Adding an exercise to a workout: `add`](#4-adding-an-exercise-to-a-workout-add)
  5. [Mark an exercise done: `done`](#5-mark-an-exercise-done-done)
  6. [Show all exercises from a workout: `display`](#6-show-all-exercises-from-a-workout-display)
  7. [Removing an exercise from a workout: `remove`](#7-removing-an-exercise-from-a-workout-remove)
  8. [Entering into a Workout: `enter`](#8-entering-into-a-workout-enter)
  9. [Exiting from a workout: `back`](#9-exiting-from-a-workout-back)
  10. [Exiting the application: `bye`](#10-exiting-the-application-bye)
  11. [Saving data](#11-saving-data)
  12. [Editable data file](#12-editable-data-file)
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

## Features

### 1. Creating a new workout: `create`
Adds a new workout to the list of workouts

Format: `create [WORKOUT_DESCRIPTION], [DEADLINE]`

⚠️  `DEADLINE` needs to be in format `yyyy-MM-dd`.

Usage examples: 

`create abs, 2021-12-25`

`create leg day, 2021-10-21`

Expected outcome:

```
create abs, 2021-12-25
________________________________________________________
New workout created: abs finish by: 25 Dec 2021
________________________________________________________
```

### 2. Show all workouts: `list`
Shows you all the workouts in the current list of workouts

Usage example:

`list`

Expected outcome:

```
list
________________________________________________________
Workout list:
1. abs finish by: 25 Dec 2021
2. leg day finish by: 21 Oct 2021
________________________________________________________
```

### 3. Deleting a workout: `delete`
Deletes a workout from the list of workouts

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

### 4. Adding an exercise to a workout: `add`
Adds an exercise to a specified workout

Format: `add [EXERCISE_DESCRIPTION], [SETS] [REPS], [WORKOUT_INDEX]`
* Note the comma  `,`  separating the command arguments
* Sets and reps are entered in the form of two numbers separated by a space 

Usage examples:

`add squats, 5 10, 1`  = squats, 5 sets of 10 reps, add to workout 1

`add lunges, 4 8, 2` = lunges, 4 sets of 8 reps, add to workout 1

Expected outcome:

```
add squats, 5 10, 1
________________________________________________________
New exercise added: [ ] squats | 5 sets of 10 reps
________________________________________________________
```

### 5. Mark an exercise done: `done`
Marks an exercise from a specified workout as done

Format: `done [EXERCISE_INDEX], [WORKOUT_INDEX]`

Usage example:

`done 1, 2` = mark exercise 1 from workout 2 as done

Expected outcome:

```
done 2, 1
________________________________________________________
Completed: [X] lunges | 4 sets of 8 reps
________________________________________________________
```

### 6. Show all exercises from a workout: `display`
Shows you all the exercises in a specified workout

Format: `display [WORKOUT_INDEX]`

Usage example:

`display 1` = Show all exercises from workout 1

Expected outcome:

```
display 1
________________________________________________________
Exercises in leg day
1. [ ] squats | 5 sets of 10 reps
2. [X] lunges | 4 sets of 8 reps
________________________________________________________
```

### 7. Removing an exercise from a workout: `remove`
Removes an exercise from a specified workout

Format: `remove [EXERCISE_INDEX], [WORKOUT_INDEX]`

Examples of usage:

`remove 1, 2` = remove exercise 1 from workout 2

Expected outcome:

```
remove 2, 1
________________________________________________________
Removed exercise: [X] lunges | 4 sets of 8 reps
________________________________________________________
```

### 8. Entering into a Workout: `enter`
Allows you to enter into a workout so that you can `add` ,`remove` , mark as `done` and `display` exercises
in the context of the workout routine you have entered, saving you the trouble of entering the workout index everytime.

Format: `enter [WORKOUT_INDEX]`

Example of usage:
* `enter 1` = enter into the workout with index 1
* `enter 2` = enter into the workout with index 2

### 9. Exiting from a workout: `back`
Allows you to exit back into the main view once you have entered into a workout.

Format `back`

Expected outcome of `enter` and `back`:

```
You: list

GetJackd:
________________________________________________________
Workout list:
1. workout2 finish by: 11 Nov 2102
2. workout3 finish by: 25 Dec 2020
________________________________________________________

You: enter 1

GetJackd:
			________________________________________________________
			Now inside Workout: workout2 finish by: 11 Nov 2102
			________________________________________________________
			
			You: add pushups, 5 20
			
			GetJackd:

			________________________________________________________
			New exercise added: [ ] pushups | 5 sets of 20 reps
			________________________________________________________
			
			You: display
			
			GetJackd:
			
			Exercises in workout2
			1. [ ] pushups | 5 sets of 20 reps
			________________________________________________________
			
			You: back

GetJackd:
________________________________________________________
Back to Main View
________________________________________________________
			
```

### 10. Exiting the application: `bye`
Allows you to exit from the application.

Format `bye`

Expected outcome:
```
bye
________________________________________________________
Bye. Hope you get your desired body soon, have a great day!
________________________________________________________
```

### 11. Saving data

GetJackd's workout list data is saved in the hard disk automatically after any command that changes the data (Adding 
or editing workouts and exercises). There is no need to
save manually.

### 12. Editable data file

GetJackd's data is saved as a json file `[JAR file location]/data/workouts.json`. Advanced users are welcome to update data
directly by editing that data file.

❗  **Caution**: If your changes to the data file is detected to be corrupted or does not conform to GetJackd's
saved file format (json), GetJack'd will not run.

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Go to the folder you saved `GetJackd.jar`, save a copy of the `data` folder and copy it to the folder 
`GetJackd.jar` is saved on your other computer.

## Command Summary

| Action                                           | Format, Examples                                                                                       |
|--------------------------------------------------|--------------------------------------------------------------------------------------------------------|
| List all workouts                                | Example: `list`                                                                                        |
| Create workout                                   | Format: `create [WORKOUT_DESCRIPTION], [DEADLINE]` <br />Example: `create abs workout, 2021-12-25`                                         |
| Delete workout                                   | Format: `delete [WORKOUT_INDEX]` <br />Example: `delete 1`                                                 |
| Display exercises in <br /> a particular workout | Format: `display [WORKOUT_INDEX]` <br />Example: `display 1`                                               |
| Add exercise                                     | Format: `add [EXERCISE_DESCRIPTION], [SETS] [REPS], [WORKOUT_INDEX]` <br /> Example: `add Push-ups, 5 10, 1` |
| Mark exercise as done                            | Format: `done [EXERCISE_INDEX], [WORKOUT_INDEX]` <br />Example: `done 5, 1`                                |
| Remove exercise                                  | Format: `remove [EXERCISE_INDEX], [WORKOUT_INDEX]` <br />Example: `remove 5, 1`                            |
| Enter workout                                    | Format: `enter [WORKOUT_INDEX]` <br />Example: `enter 1`                                                   |
| Exit workout                                     | Example: `back`                                                                                          |
| Help                                             | Format: `help [COMMAND_WORD]` <br />Example: `help add`                                                    |
| Search                                           | Format: `search [KEYWORD]` <br />Example: `search legs`                                                    |
| Exit                                             | Example: `bye`                                                                                           |
