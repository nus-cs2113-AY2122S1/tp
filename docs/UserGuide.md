# User Guide

_Ha(ppy)Bit_ is a desktop app aimed to **empower students** to achieve their
**goals**—whether personal, academical, or health—amidst the hectic and stressful
**university life**, through cultivating good **habits**.
The app operates on a [Command Line Interface (CLI)](https://en.wikipedia.org/wiki/Command-line_interface)
while still comprising features typically found in a
[Graphical User Interface (GUI)](https://en.wikipedia.org/wiki/Graphical_user_interface).
Students who type fast will find that _Ha(ppy)Bit_ performs habit tracking more efficiently than your
conventional GUI apps.

You can find out more about _Ha(ppy)Bit_'s Developer Guide or 
its developers (😀 us!) at our [Main Page](README.md).

------------

* [Quick Start](#quick-start)
  * [Loading Screen Navigation](#loading-screen-navigation)
* [Using this Guide](#using-this-guide)
  * [Terminology](#terminology)
  * [Icons & Format]()
* [Features](#features)
    * [Ask for Help: `help`](#ask-for-help-help)
    * [Set a Goal: `set`](#set-a-goal-set)
    * [List all Goals: `list`](#list-all-goals-list)
    * [Update a Goal: `update`](#update-a-goal-update)
    * [Remove a Goal: `remove`](#remove-a-goal-remove)
    * [Add a Habit: `add`](#add-a-habit-add)
    * [Change a Habit Name: `change`]()
    * [Complete a Habit: `done`](#complete-a-habit-done)
    * [Delete a Habit: `delete`](#delete-a-habit-delete)
    * [View all Habit: `view`](#view-all-habits-view)
    * [Exit the Program: `bye`](#exit-the-program-bye)
* [About Loading and Saving Data](#about-loading-and-saving-data)
* [FAQ](#faq)
* [Command Summary](#command-summary)
* [Useful Links](#useful-links)

------------

## Quick Start

1. Hype yourself up by dancing. Here begins your journey with _Ha(ppy)Bit_.
2. Ensure you have Java `11` installed in your computer. If you do not have it installed,
   download it [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html).
3. Download the latest `tp.jar` [here](https://github.com/AY2122S1-CS2113T-F14-1/tp/releases/tag/v1.0).
4. Copy the file to the folder you want to use as the **home folder** for your _Ha(ppy)Bit_.
5. Open your terminal of choice inside the folder containing `tp.jar` file.
6. Run the file by typing the command `java -jar tp.jar`.
7. Upon loading the app you should see the following screen, indicating that you have successfully run the app.

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
    Any keyboard input will appear after `Option: `. Here is where interaction between you and _Ha(ppy)Bit_ takes place.

### Loading Screen Navigation
In the current loading screen, users will be able to execute the following actions based on the number they input.
1. `About Ha(ppy)Bit` - Shows brief description of the program and a short meet the team section

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

2. `User Guide` - Opens up User Guide github page in user's web browser.

   ```
    Option: 2

    Opening in a web browser...
    
    Press enter to return to main menu...
    ```

3. `Developer Guide` - Opens up Developer Guide github page in user's web browser.

    ```
    Option: 3

    Opening in a web browser...
    
    Press enter to return to main menu...
    ```

4. `Demo Video` - Links to Demo Video (Not done yet)
    ```
   video not available.
    ```

5. `Start Application` - Brings user to the application. 
New user can begin their journey with _Ha(ppy)Bit_ (😀 thanks!) here.
Returning users can continue using _Ha(ppy)Bit_ where they left off.

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
    Any keyboard input will appear after `Command: `. Here is where interaction between you and _Ha(ppy)Bit_ takes place.

## Using this Guide
We understand there may a lot to take in from this user guide. Here are some things to take note
of which make the guide more friendly and helpful.

### Terminology

Name  | Description | Example
----- | ---------------------------------------------------------------- | -------------------
Goal  | A long term achievement you wish to accomplish.                  |`Lose 5kg by Dec`
Habit | Checkpoints; small, actionable tasks to be done to achieve goal. | `Run 5km`

Here we distinguish between goals and habits. Although they are commonly used words, the way we
design _Ha(ppy)Bit_ is tightly integral to the way we define them above. We would also like you
to know that the purpose of `habits` is to break down daunting, colossal `goals` into easy, bite-sized
`habits`; tasks that you can complete within a day.

### Icons & Format
Unfamiliar symbols may confuse. Here is comprehensive collection of all emojis and syntax we employ.  

> 📃 **Notes**: {explanation here}

> ⚠ **Warning**:  {explanation here}
 
> 💡 **Pro-Tip!** {explanation here} are we targeting advance and basic users?
> then we need to use this strategically.

> 😀 &#8594; Used to show developers' exuberance!!!

> 👍 &#8594; Used to show developers' love and support for you, the users.


>  📃 **Notes about command format:**
> * words enclosed in angle brackets, `<>` are the parameters which is the input the user is supposed to provide.
> * e.g `view g/<goal index>` can be `view g/1`.
> * The parameters enclosed in curly brackets, `{}`, are optional.

## Features

Below are the commands that the app supports.
Each section describes the function of the command and the command format.

> 📃 **Note:** <br>
> For all commands and features, apart from the positioning of the command word,
> all other flags and parameters can be placed in any order as long as
> their respective tags are present e.g. `n/, s/, e/` etc.

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
start dates are optional (well, we wouldn't you to be procrastinating on your goals right?)

Format: `set n/<GOAL_NAME> { t/<GOAL_TYPE> s/<START_DATE> } e/<END_DATE>`

> 📃 **Note:**
> 1. Dates must be in `DDMMYYYY` format. For example, 01 January 2021 must be written as `01012020`.
> 2. The two flags contained within the `{}` brackets indicate the
> optional inputs of `t/<GOAL_TYPE>` and `s/<START_DATE>`.
> 3. The optional `<GOAL_TYPE>` argument can take one of the following flags:
>> `sl` &#8594; Sleep <br>
>> `fd` &#8594; Food <br>
>> `ex` &#8594; Exercise <br>
>> `sd` &#8594; Study
> 4. Otherwise, goal type will be `df` &#8594; Default.

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

### List all Goals: `list`
Lists all goals currently set by the user. Here you can see the attributes a goal can have.

Format: `list`

Output:
```
There are 3 goals currently being tracked:
-----------------------------------------------------------------------------
|Index|Name      |Type      |Start Date     |End Date       |Habit Count    |
-----------------------------------------------------------------------------
|1    |example   |Default   |24-Oct-2021    |01-Jan-2022    |0              |
-----------------------------------------------------------------------------
|2    |test 1    |Default   |24-Oct-2021    |30-Oct-2021    |0              |
-----------------------------------------------------------------------------
|3    |test 2    |Default   |24-Oct-2021    |27-Oct-2021    |0              |
-----------------------------------------------------------------------------
```

> 📃 **Note**: <br>
> Each goal has a corresponding <GOAL_INDEX> depending on its location in the list.
> They may change when the goals are deleted (more on how later.) Remembering the index of a goal
> can help in executing commands faster without having to refer to the list every time. 

### Update a Goal: `Update`
Updates an attribute (Name, Date, or Goal Type) of a goal specified by its index. <br>
(To err is human. That's why we have the `update` feature; designed to be more forgiving,
when we have the occasional oopsies. 👍 )

> 📃 **Note:**
> 1. `<GOAL_INDEX>` used in the following commands is an integer.

#### Updating Name

Format: `update g/<GOAL_INDEX> n/<NEW_GOAL_NAME>`

Example:

```
Command: update g/3 n/Reach for the Stars
```
Output:
```
________________________________________________________________________________________________________________________
Your goal "Reach for the Moon" has been changed to "Reach for the Stars".
________________________________________________________________________________________________________________________

Press enter to return to command mode...
```

> 📃 **Note:** <br>
> The update features below are still underway.

#### Updating Start/End Date
Format: `update g/<GOAL_INDEX> s/<NEW_START_DATE>` <br>
_or_ <br>
Format: `update g/<GOAL_INDEX> e/<NEW_END_DATE>`

Example:

```

```
Output:
```

```

#### Updating Goal Type
Format: `update g/<GOAL_INDEX> t/<NEW_GOAL_TYPE>`

Example:

```

```
Output:
```

```

#### Updating Goal Interval
Format: `update g/<GOAL_INDEX> i/<INTERVALS>`

Example:

```

```
Output:
```

```

### Remove a Goal: `remove`
Removes a goal specified by its index. <br>
(Life gets us sometimes. Things don't go as planned. But this isn't calling it quits. It's admitting
defeat today, but to return stronger to fight another time. We got ya fam.)

Format: `remove g/<GOAL_INDEX>`

> 📃 **Note:**
> 1. `<GOAL_INDEX>` is an integer.

Example:
```
Command: remove g/1
```

Output:
```
________________________________________________________________________________________________________________________
Your goal: [DF] Decrease Spending has been removed.
________________________________________________________________________________________________________________________

Press enter to return to command mode...
```

### Add a Habit: `add`
Adds a habit that is linked to a goal. <br>
(Habits are meant to easy and doable. Completing them over time, with consistent dedication, 
one of these habits will be the metaphorical straw that breaks the camel's back. Your goals reached,
without even pulling a muscle.)

Format: `add  n/<HABIT_NAME> g/<GOAL_INDEX> i/<INTERVALS>`

> 📃 **Note:**
> 1. `<GOAL_INDEX>` is an integer.
> 2. `<INTERVALS>` is an integer indicating the number of days between each recurring instance of a habit.

Example:
```
Command: add n/Don't drink BBT g/1 i/3 
```

Output:
```
________________________________________________________________________________________________________________________
Your habit: Don't drink BBT has been added to your goal: [DF] Reduce Spending
________________________________________________________________________________________________________________________

Press enter to return to command mode...
```

### Change a Habit Name: `change`
Changes and updates the name of a habit.

Format: `change g/<GOAL_INDEX> h/<HABIT_INDEX> n/<NEW_HABIT_NAME>`

Example:
```

```

Output:
```

```

### Complete a Habit: `done`
Marks a habit under a goal as done.

Format: `done g/<GOAL_INDEX> h/<HABIT_INDEX>`

> 📃 **Note:**
> 1. `<GOAL_INDEX>` is an integer.
> 2. `<HABIT_INDEX>` is an integer.

Example:
```
done g/1 h/1
```

Output:
```
________________________________________________________________________________________________________________________
Your habit of "Don't drink BBT" under the goal "[DF] Reduce Spending" has been set as done.
________________________________________________________________________________________________________________________

Press enter to return to command mode...
```


### Delete a Habit: `delete`
Deletes a habit under a goal.

Format: `delete g/<GOAL_INDEX> h/<HABIT_INDEX>`

> 📃 **Note:**
> 1. `<GOAL_INDEX>` is an integer.
> 2. `<HABIT_INDEX>` is an integer.

Example:
```
delete g/1 h/1
```

Output:
```
________________________________________________________________________________________________________________________
Your habit of "Don't drink BBT" under the goal "[DF] Reduce Spending" has been deleted.
________________________________________________________________________________________________________________________

Press enter to return to command mode...
```

### View all Habits: `view`
Lists all habits under a specific goal.

Format: `view g/<GOAL_INDEX>`

> 📃 **Note:**
> 1. `<GOAL_INDEX>` is an integer.

Example:
```
view g/1
```

Output
```
________________________________________________________________________________________________________________________
Here are your 1 habit(s) under the goal "[DF] Reduce Spending".
1. [ ] Don't drink BBT (every 3 days)
Last: 25/10/2021, Next: 28/10/2021
________________________________________________________________________________________________________________________
```

### Exit the Program: `bye`
Exits the program. <br>
(Don't stay on _Ha(ppy)Bit_ too long. You have a life out there, go live it!)

> ⚠ **Warning:** <br>
> Make sure to use this command before exiting the program
to ensure that your data are saved properly.

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

* Everytime you make changes to any of your goals and habits, the changes
  are **immediately** reflected in the storage file. This is to ensure that in the
  event you closed the program without using the command `bye`, we can ensure that
  **most of your data are saved**.
* Before you exit the program, the goals you set and the habits you have added
  are **automatically** saved in a text file at the relative storage path, `data/habits.txt`.

## FAQ

**Q**: How do I transfer my data to another computer?
<br>
**A**: You can just copy-paste the habits.txt in the path `data/habits.txt` file from one computer to another and all
your data will be there!

> 💡 **Pro Tip!** Have your home folder reside in a cloud storage service (OneDrive, iCloud, etc.) to sync your data
> between devices.

**Q**: Are there any books you recommend?
<br>
**A**: Data Structures and Algorithms in Java &#8594; [here](https://www.amazon.com/Data-Structures-Algorithms-Java-2nd/dp/0672324539)

## Command Summary

Action | Command Format | Example
------------ | ------------ | ------------
Set goal | `set n/<GOAL_NAME> { t/<GOAL_TYPE> s/<START_DATE> } e/<END_DATE>` | `set n/Reduce spending e/31122022`
List goals | `list` | `list`
Update goal | `update g/<GOAL_INDEX> n/<NEW_GOAL_NAME>` | `update g/1 n/Decrease Spending`
Remove goal | `remove g/<GOAL_INDEX>` | `remove g/1`
Add habit | `add n/<HABIT_NAME> g/<GOAL_INDEX> i/<INTERVAL>` | `add n/Don't drink BBT g/1 i/3`
Done habit | `done g/<GOAL_INDEX> h/<HABIT_INDEX>` | `done g/1 h/1`
Delete habit  | `delete <GOAL_INDEX> <HABIT_INDEX>` | `delete g/1 g/1`
View habits | `view g/<GOAL_INDEX>` | `view g/1`
Exit Program | `bye` | `bye`

## Useful Links

Visit our [Main Page](README.md) to find more useful links.

Watch our wacky Demo Video v0 [here](https://www.youtube.com/watch?v=dQw4w9WgXcQ)!
