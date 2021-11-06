# User Guide

_Ha(ppy)Bit_ is a desktop application aimed to **empower students** to achieve their
**goals**—whether personal, academical, or health—amidst the hectic and stressful
**university life**, through cultivating good **habits**.
The application operates on a [Command Line Interface (CLI)](https://en.wikipedia.org/wiki/Command-line_interface)
while still comprising features typically found in a
[Graphical User Interface (GUI)](https://en.wikipedia.org/wiki/Graphical_user_interface).
Students who type fast will find that _Ha(ppy)Bit_ performs habit tracking more efficiently than your
conventional GUI applications.

You can find out more about _Ha(ppy)Bit_'s Developer Guide or 
its developers (😀 us!) at our [Main Page](README.md).

------------

* [1. Quick Start](#1-quick-start)
  * [1.1 Start-Up Interface](#11-start-up-interface)
  * [1. 2 Main Interface](#12-main-interface)
* [2. Using this Guide](#2-using-this-guide)
  * [2.1 Terminology](#21-terminology)
  * [2.2 Icons](#22-icons)
* [3. Using _Ha(ppy)Bit_](#3-using-_happybit_)
  * [3.1 Instructions from _Ha(ppy)Bit_](#31-instructions-from-_happybit_)
  * [3.2 Command Format](#32-command-format)
* [4. Features](#features)
    * [4.1 Ask for Help: `help`](#41-ask-for-help-help)
    * [4.2 Set a Goal: `set`](#42-set-a-goal-set)
    * [4.3 List all Goals: `list`](#43-list-all-goals-list)
    * [4.4 Update a Goal: `update`](#44-update-a-goal-update)
    * [4.5 Remove a Goal: `remove`](#45-remove-a-goal-remove)
    * [4.6 Add a Habit: `add`](#46-add-a-habit-add)
    * [4.7 Change a Habit Name: `change`](#47-change-a-habit-change)
    * [4.8 Complete a Habit: `done`](#48-complete-a-habit-done)
    * [4.9 Delete a Habit: `delete`](#49-delete-a-habit-delete)
    * [4.10 View all Habit: `view`](#410-view-all-habits-view)
    * [4.11 Return to Start-Up: `return`](#411-return-to-start-up-return)
    * [4.12 Exit the Program: `exit`](#412-exit-the-program-exit)
* [5. About Loading and Saving Data](#5-about-loading-and-saving-data)
* [6. FAQ](#6-faq)
* [7. Command Summary](#7-command-summary)
* [8. Useful Links](#8-useful-links)

------------

## 1. Quick Start

1. Hype yourself up by dancing. Here begins your journey with _Ha(ppy)Bit_.
2. Ensure you have Java `11` installed in your computer. If you do not have it installed,
   download it [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html).
3. Download the latest `happybit.jar` [here](https://github.com/AY2122S1-CS2113T-F14-1/tp/releases/tag/v2.0).
4. Copy the file to the folder you want to use as the **home folder** for your _Ha(ppy)Bit_.
5. Open your terminal of choice inside the folder containing `happybit.jar` file.
6. Run the file by typing the command `java -jar happybit.jar`.
7. Upon loading the app you should see the following screen, 
indicating that you have successfully ran the app.

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

### 1.1 Start-Up Interface
In the current loading screen, you will be able to navigate to five other screens.
Choose an option from one to five by typing 1, 2, 3, 4, or 5 and hitting "enter" to load the desired screen.
Below are the outcomes.

#### Option 1: `About Ha(ppy)Bit`
Learn more about our vision for _Ha(ppy)Bit_ and the team behind it.

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

#### Option 2: `User Guide`
Learn what you can accomplish with this app.\
This option opens the User Guide in your web browser.

    Option: 2

    Opening in a web browser...
    
    Press enter to return to main menu...

#### Option 3: `Developer Guide`
Learn about the app from the perspective of a software engineer.\
This option opens the Developer Guide in your web browser.

    Option: 3

    Opening in a web browser...
    
    Press enter to return to main menu...

#### Option 4: `Demo Video`
Don't like reading long wordy guides? A video may be just the thing for you!\
This option opens the Demo Video in your web browser.\
(The video is currently unavailable and will be up in v2.1)

    Option: 4

    Opening in a web browser...

    Press enter to return to main menu...

#### Option 5: `Start Application`
If you're a new user, look forward to beginning your _Ha(ppy)Bit_ journey here.\
If you're a returning user, fret not! Everything you gained from your journey has been saved and will be loaded.\
This option starts up the main interface of the application.
    
    Option: 5
    
    Starting application...
    Type 'return' to return to main menu

### 1.2 Main Interface
Upon formally starting the application, you should see the following screen. 
(See [Start Application](#option-5-start-application) if you are unsure how to get here)
   
    ==============================================================================
     _  _   __   ____  ____  _  _  ____  __  ____
    / )( \ / _\ (  _ \(  _ \( \/ )(  _ \(  )(_  _)
    ) __ (/    \ ) __/ ) __/ )  /  ) _ ( )(   )(
    \_)(_/\_/\_/(__)  (__)  (__/  (____/(__) (__)     tracking habits made fun...
    ==============================================================================
    
    Command:
    
Any keyboard input will appear after `Command: `. Here is where interaction between you and _Ha(ppy)Bit_ takes place.

## 2. Using this Guide
We understand there may be a lot to take in from this user guide. Here are some things to take note
which may make the guide more friendly and helpful.

### 2.1 Terminology

Name     | Description                                                      | Example
-----    | ---------------------------------------------------------------- | -------------------
Goal     | A long term achievement you wish to accomplish.                  |`Lose 5kg by Dec`
Habit    | Checkpoints; small, actionable tasks to be done to achieve goal. |`Run 5km`
Interval | The frequency (in days) which you want the habit to recur.       |`Run 5km every 7 days` 

**Goals and habits** <br>
Here we distinguish between goals and habits. Although they are commonly used words, the way we
design _Ha(ppy)Bit_ is tightly integral to the way we define them above. We would also like you
to know that the purpose of `habits` is to break down daunting, colossal `goals` into easy, bite-sized
`habits`; tasks that you can complete within a day or as defined by your `interval`.

**Intervals** <br>
An `interval` is user-defined. It is for `habits` that will occur periodically, saving you the hassle
of keying in individual `habits` every time it occurs. It is also the window period for you to complete a `habit`.
Here are three things to note:
1. `interval` is not allowed to extend longer than your `goal` time frame. <br>
To explain with an example, a goal that starts and ends within a month is _not_ to have a`habit` with an `interval` 
longer than 30 days, otherwise it should've been a one-off `habit`! 
2. One-off `habits` (hey, an oxymoron!) are habits that do not repeat. <br>
This is achieved by setting the `interval` as `0`, rather than any positive integer.
3. _Ha(ppy)Bit_ does not allow for an `interval` to be shorter than 1 day. <br>
This if for those who are planning to do a habit more than once a day. 
You may consider having multiple recurring habits instead.

### 2.2 Icons
Unfamiliar symbols may confuse. Here is a comprehensive collection of all emojis and syntax we employ.  

> 📃 **Notes**: important information you should take note off (especially if you encounter input errors)

> ⚠ **Warning**: avoid doing the things mentioned here at all costs (unless you're feeling lucky)
 
> 💡 **Pro-Tip!** additional information that may make your tracking journey easier

> 😀 &#8594; Used to show the developers' exuberance!!!

> 👍 &#8594; Used to show the developers' love and support for you.

## 3. Using _Ha(ppy)Bit_
_Ha(ppy)Bit_ comes with some quirks that might need some time getting used to. To get you up to speed and warmed
up with _Ha(ppy)Bit_, here are instructions and command formats to follow. 👍

### 3.1 Instructions from _Ha(ppy)Bit_

You may have noticed the instructions `Press enter to return to main menu...` and `Type 'return' to return to main menu`
at the bottom of some responses from _Ha(ppy)Bit_. This is because _Ha(ppy)Bit_ clears and refreshes the page after 
every command. Previously typed commands will also be cleared, so press the "up" key on your keyboard to navigate 
through recently typed commands. 

Do follow the instructions before typing anything, lest _Ha(ppy)Bit_ does not
register your input. On the topic of inputs...

### 3.2 Command Format
_Ha(ppy)Bit_ is very particular about your commands. You begin off with a "command word" to tell _Ha(ppy)Bit_ the exact 
action to carry out (See [Command Summary](#command-summary) for all actions). The actions are then followed up with
"parameters", which are information you wish to pass to _Ha(ppy)Bit_ to carry out the actions with it. To indicate the 
type of parameters, they are prefixed with "flags". The flags help _Ha(ppy)Bit_ identify and distinguish the type of
parameters.

A command could look like this: `add n/Run 2.4km g/1 i/7`. Where, <br>
`add` is the command word, <br>
`Run 2.4km`, `1`, and `7` are parameters, <br>
and `n/`, `g/`, and `i/` are flags.

Here are guidelines and tips for the command format:
* **Commands** are only accepted when you see `Command: ` on the screen (See [Main Interface](#main-interface)). 
  Your inputs appearing after `Command: ` indicates you're doing it right.
* **Commands** may require one or more flags. You may choose to use the flags in any order but ensure that all flags 
  for that command are present.\
  (e.g. `add` requires the `n/`, `g/` and `i/` flag, and possible variations include `add n/habit 1 g/1 i/1` and
  `add i/1 n/habit 1 g/1`)
* **Parameters** `<goal_index>` and `<habit_index>`can be checked with the commands `list` and `view` respectively.
* **Parameters** enclosed in angle brackets `<>` are meant to be replaced.\
  (e.g. `view g/<goal_index>` could be `view g/1`)
* **Parameters** enclosed within curly brackets, `{}` are optional 
* **Parameters** enclosed withing square brackets, `[]` suggest that at least one of the parameters are required
* **Flags** should have a whitespace before them <br>
  (e.g. `view g/1` instead of `viewg/1`)

## 4. Features

Below are the commands supported by the application.
Each section describes the function of each command and its format. <br>

### 4.1 Ask for Help: `help`
What did you say? You're not sure what the commands are? Relax, that's why the `help` command exists.\
This command displays a list of all possible commands.
If an invalid command is typed, this command will be invoked by default.

**Format:** `help`

**Output:**
```
Here are the list of commands:
--------------------------------------------------------------------------------------------------------------
| Action                            | Format                                                                 | 
--------------------------------------------------------------------------------------------------------------
| open command list                 | help                                                                   | 
--------------------------------------------------------------------------------------------------------------
| set a goal                        | set n/<goal_name> { t/<goal_type> s/<start_date> } e/<end_date>        | 
--------------------------------------------------------------------------------------------------------------
| add a habit                       | add g/<goal_index> n/<habit_name> i/<interval>                         | 
--------------------------------------------------------------------------------------------------------------
| update a goal name/type/end date  | update g/<goal_index> { n/<goal_name> t/<goal_type> e/<end_date> }     | 
--------------------------------------------------------------------------------------------------------------
| change a habit name/interval      | change g/<goal_index> h/<habit_index> { n/<habit_name> i/<interval> }  | 
--------------------------------------------------------------------------------------------------------------
| list all goals                    | list                                                                   | 
--------------------------------------------------------------------------------------------------------------
| view all habits of a goal         | view g/<goal_index>                                                    | 
--------------------------------------------------------------------------------------------------------------
| remove a goal                     | remove g/<goal_index>                                                  | 
--------------------------------------------------------------------------------------------------------------
| delete a habit                    | delete g/<goal_index> h/<habit_index>                                  | 
--------------------------------------------------------------------------------------------------------------
| mark a habit as done              | done g/<goal_index> h/<habit_index>                                    | 
--------------------------------------------------------------------------------------------------------------
| return to start screen            | return                                                                 | 
--------------------------------------------------------------------------------------------------------------
| exit the application              | exit                                                                   | 
--------------------------------------------------------------------------------------------------------------
* Replace <> with relevant terms (Exp: <goal_name> --> goal 1)
* Items enclosed within { } are optional
* Use 'list' and 'view' to check the goal and habit indexes respectively

Press enter to return to command mode...
```

### 4.2 Set a Goal: `set`
Sets a new goal for a long term achievement you wish to accomplish. Goals must have an end date while the goal type and
start dates are optional (well, we wouldn't you to be procrastinating on your goals right?)

**Format:** `set n/<goal_name> { t/<goal_type> s/<start_date> } e/<end_date>`

* Goal name can be any name of your choosing (make it meaningful otherwise you won't know what you're tracking).
* Dates must be in `DDMMYYYY` format. For example, 01 January 2021 must be written as `01012020`.
* Goal type is an optional parameter and can take one of the following arguments
  * `sl` &#8594; Sleep
  * `fd` &#8594; Food
  * `ex` &#8594; Exercise
  * `sd` &#8594; Study
  * `df` &#8594; Default (This will be the goal type if the parameter is omitted)
* Start date is an optional parameter. Omitting it will set the start date as today.

**Example:**

Command 1: `set n/Reduce spending s/29102021 e/31122021`\
Command 2: `set n/Exercise more t/ex e/01012022`\
Command 3: `set t/fd e/31122022 n/Become a vegetarian`

Output 1: `The goal '[DF] Reduce spending' has been added.`\
Output 2: `The goal '[EX] Exercise more' has been added.`\
Output 3: `The goal '[FD] Become a vegetarian' has been added.`

> 📃 If more than 8 numbers are used for the date, only the 1st 8 numbers will be treated as the date.

### 4.3 List all Goals: `list`
Can't remember what goals you have? Use this command to list all goals that you have set. 
In addition, you get to view some useful statistics.

**Format:** `list`

**Example:**
```
Command: list
```

**Output:**
```
3 goal(s) currently being tracked:
----------------------------------------------------------------------------------------------------------------
| Index   | Name                 | Type      | Start Date   | End Date     | Habit Count   | Completion Rate   |
----------------------------------------------------------------------------------------------------------------
| 1       | Reduce spending      | Default   | 29-Oct-2021  | 31-Dec-2021  | 0             | Not Applicable    |
----------------------------------------------------------------------------------------------------------------
| 2       | Exercise more        | Exercise  | 28-Oct-2021  | 01-Jan-2022  | 0             | Not Applicable    |
----------------------------------------------------------------------------------------------------------------
| 3       | Become a vegetarian  | Food      | 28-Oct-2021  | 31-Dec-2022  | 0             | Not Applicable    |
----------------------------------------------------------------------------------------------------------------
```

> 📃 Goal index may change if a goal is deleted. e.g. deleting the goal `Exercise more` will cause the index of `Become
> a vegetarian` to change from 3 to 2.

### 4.4 Update a Goal: `Update`
Updates a parameter of a goal specified by its index.\
(To err is human. That's why we have the `update` feature; designed to be more forgiving, 
when we have the occasional oopsies. 👍 )

**Format:** `update g/<goal_index> { n/<goal_name> t/<goal_type> e/<end_date> }`

**Example:**\
Command 1: `update g/1 n/Read more`\
Command 2: `update g/2 e/31122021`\
Command 3: `update t/sd g/1`

Output 1: `The goal name 'Sleep more' has been updated to 'Read more'.`\
Output 2: `The goal end date of goal 'Exercise more' has been changed from '01-Jan-2022' to '30-Nov-2021'.`\
Output 3: `The goal type 'Default' has been updated to 'Study'.`

> 📃 Only one parameter can be updated at a time (to be changed to multiple parameters in v2.1)

### 4.5 Remove a Goal: `remove`
Removes a goal specified by its index.\
(Life gets us sometimes. Things don't go as planned. But this isn't calling it quits. It's admitting
defeat today, but to return stronger to fight another time. We got ya fam.)

**Format:** `remove g/<goal_index>`

**Example:**
```
Command: remove g/1
```

**Output:**
```
________________________________________________________________________________________________________________________
The goal '[SD] Read more' has been removed.
________________________________________________________________________________________________________________________
```

> 📃 Goal index may change if a goal is deleted. Use the `list` command to check what the new goal indexes are.

### 4.6 Add a Habit: `add`
Adds a habit that is linked to a goal.\
(Habits are meant to easy and doable. Completing them over time, with consistent dedication, 
one of these habits will be the metaphorical straw that breaks the camel's back. Your goals reached,
without even pulling a muscle.)

**Format:** `add n/<habit_name> g/<goal_index> i/<interval>`
* name your habit with an achievable and actionable task (e.g. Run 2.4km, Sleep at 10pm)
* 'Interval' is a number that indicates the period of time you have to complete the habit before the next cycle
* 'Interval' defined as 0 indicates a one-off habit; you only have to complete the habit once

**Example:**\
Input 1: `add n/Run 2.4km g/1 i/7`\
Input 2: `add g/1 i/1 n/Do 20 pushups`\
Input 3: `add i/14 n/Learn a vegetarian dish g/2`

Output 1: `The habit 'Run 2.4km' has been added to goal '[EX] Exercise more'`\
Output 2: `The habit 'Do 20 pushups' has been added to goal '[EX] Exercise more'`\
Output 3: `The habit 'Learn a vegetarian dish' has been added to goal '[FD] Become a vegetarian'`

> 📃 You should notice a list of habits to be done on the main interface whenever a new habit is added.

### 4.7 Change a Habit: `change`
Change and update the name and/or interval of a habit.

**Format:** `change g/<goal_index> h/<habit_index> { n/<habit_name> i/<interval> }`
* You may change either the `habit_name` or `interval` at a time, but not both
* There must be at least one parameter changed - otherwise why call this command at all?

**Example:**\
Input 1: `change g/2 h/1 i/7`\
Input 2: `change n/Do 30 pushups h/2 g/1`

Output 1: `The habit 'Learn a vegetarian dish' of goal 'Become a vegetarian' has its interval changed to '7'`\
Output 2: `The habit 'Do 20 pushups' of goal 'Exercise more' has been changed to 'Do 30 pushups'`

> 📃 Only one parameter can be updated at a time (to be changed to multiple parameters in v2.1)

### 4.8 Complete a Habit: `done`
Mark a habit under a goal as done.\
(Congratulations on the completed habit... for that cycle :P)

**Format:** `done g/<goal_index> h/<habit_index>`

**Example:**
```
Command: done g/1 h/1
```

**Output:**
```
________________________________________________________________________________________________________________________
The habit 'Run 2.4km' of goal '[EX] Exercise more' has been completed for 28102021 to 04112021.
The next interval will begin on 05112021
________________________________________________________________________________________________________________________
```

### 4.9 Delete a Habit: `delete`
Deletes a habit under a goal.\
(This should be used a last resort. Don't give up, hang in there and make your habits stick.)

**Format:** `delete g/<goal_index> h/<habit_index>`

**Example:**
```
Command: delete g/1 h/2
```

**Output:**
```
________________________________________________________________________________________________________________________
The habit 'Do 30 pushups' of goal '[EX] Exercise more' has been deleted.
________________________________________________________________________________________________________________________
```

### 4.10 View all Habits: `view`
List all habits under a specific goal for a bird's eye view on your goal.

**Format:** `view g/<goal_index>`

**Example:**
```
Command: view g/1
```

**Output:**
```
1 habit(s) currently being tracked for Exercise more:
-------------------------------------------------------------------------------------------------------
| Index   | Name       | Interval   | Completion   | Completed   | Remaining   | Expired   | Streak   |
-------------------------------------------------------------------------------------------------------
| 1       | Run 2.4km  | 7          | 20%          | 1           | 4           | 0         | 1        |
-------------------------------------------------------------------------------------------------------
```

### 4.11 Return to Start-Up: `return`
Return to the start-up interface.\
(Where did that bring you? Back to the start.)

**Format:** `return`

**Example:**
```
Command: return
```

**Output:**
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

### 4.12 Exit the Program: `exit`
Exits the program.\
(Don't stay on _Ha(ppy)Bit_ too long. You have a life out there, go live it!)

> ⚠ Make sure to use this command before exiting the program to ensure that your data is saved properly.

**Format:** `exit`

**Example:**
```
Command: exit
```

**Output:**
```
________________________________________________________________________________________________________________________
Thanks for using Ha(ppy)Bit, see you in a bit! (hehe)
"We are what we repeatedly do. Excellence, then, is not an act, but a habit."
 — Will Durant
________________________________________________________________________________________________________________________
```

## 5. About Loading and Saving Data
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

## 6. FAQ

**Q**: How do I transfer my data to another computer?
<br>
**A**: You can just copy-paste the habits.txt in the path `data/habits.txt` file from one computer to another and all
your data will be there!

> 💡 **Pro Tip!** Have your home folder reside in a cloud storage service (OneDrive, iCloud, etc.) to sync your data
> between devices.

**Q**: Why do distinguish between `delete` and `remove`, and other similar commands but named differently? 
<br>
**A**:

**Q**: Why do I have to hit "enter" every time I want to key in a new command?
<br>
**A**:

**Q**: Are there any books you recommend?
<br>
**A**: Data Structures and Algorithms in Java &#8594; [here](https://www.amazon.com/Data-Structures-Algorithms-Java-2nd/dp/0672324539)

## 7. Command Summary

Action             | Command Format                                                          | Example
------------------ | ----------------------------------------------------------------------- | ----------------------------------
Get help           | `help`                                                                  | `help`
Set goal           | `set n/<goal_name> { t/<goal_type> s/<start_date> } e/<end_date>`       | `set n/Reduce spending e/31122022`
List goals         | `list`                                                                  | `list`
Update goal        | `update g/<goal_index> { n/<goal_name> t/<goal_type> e/<end_date> }`    | `update g/1 n/Decrease Spending`
Remove goal        | `remove g/<goal_index>`                                                 | `remove g/1`
Add habit          | `add g/<goal_index> n/<habit_name> i/<interval>`                        | `add n/Don't drink BBT g/1 i/3`
Change habit       | `change g/<goal_index> h/<habit_index> { n/<habit_name> i/<interval> }` | `change g/1 h/2 i/3`
Done habit         | `done g/<goal_index> h/<habit_index> `                                  | `done g/1 h/1`
Delete habit       | `delete g/<goal_index> h/<habit_index>`                                 | `delete g/1 h/1`
View habits        | `view g/<goal_index>`                                                   | `view g/1`
Return to Start-Up | `return`                                                                | `return`
Exit Program       | `exit`                                                                  | `exit`

## 8. Useful Links

Visit our [Main Page](README.md) to find more useful links.

Interested in what makes Ha(ppy)Bit tick? Visit our [GitHub website](https://github.com/AY2122S1-CS2113T-F14-1/tp)
to explore and geek out on. What did you say? An explanation for the inner workings? Okay, okay.
Here is our [Developer Guide](https://ay2122s1-cs2113t-f14-1.github.io/tp/DeveloperGuide.html).

What now? You prefer a ***guided walkthrough*** because of the digital age and the attention economy today where
corporations bombard your with enticing information to profit of your valuable attention 
has shortened your attention span tremendously such that you don't have the patience nor focus to sit down and refer
to our User Guide which we have painstakingly written to be comprehensive for users like you in order to provide a 
better user experience to a new app they may be unfamiliar with? Fine! We get it! We have prepared a 
[Demo Video](https://www.youtube.com/watch?v=dQw4w9WgXcQ) for you, the guided walkthrough you _oh_ so desired.

Watch our wacky Demo Video bloopers [here](https://www.youtube.com/watch?v=dQw4w9WgXcQ)!
