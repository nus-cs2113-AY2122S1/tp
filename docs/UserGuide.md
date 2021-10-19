# User Guide

- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
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
   navigate to the folder where `iP.jar` is stored (using the file path).
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

{Give detailed description of each feature}

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### Entering into a Workout: `enter`
Allows you to enter into a workout so that you can `add` ,`remove` , mark as `done` and `display` exercises
in the context of the workout routine you have entered, saving you the trouble of entering the workout index everytime.

Format: `enter [WORKOUT_INDEX]`

Example of usage:
 * `enter 1`
 * `enter 2`

### Exiting from a workout: `back`
Allows you to exit back into the main view once you have entered into a workout.

Format `back`

Example interaction of `enter` and `back`:

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

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

| Action                                           | Format, Examples                                                                                       |
|--------------------------------------------------|--------------------------------------------------------------------------------------------------------|
| Add exercise                                     | Format: add [EXERCISE_DESCRIPTION], [SETS REPS], [WORKOUT_INDEX] <br /> Example: add Push-ups, 5 10, 1 |
| Display exercises in <br /> a particular workout | Format: display [WORKOUT_INDEX] <br />Example: display 1                                               |
| Mark exercise as done                            | Format: done [EXERCISE_INDEX], [WORKOUT_INDEX] <br />Example: done 5, 1                                |
| Remove exercise                                  | Format: remove [EXERCISE_INDEX], [WORKOUT_INDEX] <br />Example: remove 5, 1                            |
| Create workout                                   | Format: create [WORKOUT_DESCRIPTION] <br />Example: create abs                                         |
| Delete workout                                   | Format: delete [WORKOUT_INDEX] <br />Example: delete 1                                                 |
| List all workouts                                | Example: list                                                                                          |
| Enter workout                                    | Format: enter [WORKOUT_INDEX] <br />Example: enter 1                                                   |
| Exit workout                                     | Example: back                                                                                          |
| Help                                             | Format: help {COMMAND_WORD} <br />Example: help add                                                    |
| Search                                           | Format: search [KEYWORD] <br />Example: search legs                                                    |
| Exit                                             | Example: bye                                                                                           |
