# User Guide

![Unimods](./resources/UnimodsLogo.png)

# About The Project

It’s August 5th, and the Academic Year is right around the corner!
ModReg is about to start, and you have no idea what modules to take and what your timetable might even look like.

Introducing **UNI Mods**, an easy-to-use application for NUS students that provides information on all available NUS
modules and lets you pick and choose the modules and classes you want to take for that semester!
Depending on the classes you decide to take, a timetable will be generated to keep track of your personal tasks, school lessons in your daily schedule and your
total workload:

![Capture](https://user-images.githubusercontent.com/69495787/139837067-aed633dc-43f0-4748-9738-1dc6302d3c1f.JPG)

<br>

---

# Table of Contents

- [Quick Start](#quick-start)
- [Features](#features)
    * [Viewing help](#viewing-help-help)
    * [Show Module Info](#display-module-info-show-module_code)
    * [Search Module](#search-module-search-keyword--l)
    * [Update Local Database](#update-local-database-update)
    * [Add to Timetable](#add-to-timetable)
      * [Add a Module](#add-a-module-to-timetable )
      * [Add an Event](#add-an-event-to-timetable)
    * [Delete from Timetable](#delete-from-timetable-delete-module_code)
    * [Clear Timetable](#clear-timetable-clear)
    * [View Timetable](#view-timetable-timetable)
    * [Exit](#exit-exit)
- [FAQ](#faq)
- [Command Summary]()

<br>

---

# Quick Start

1. Ensure you have **Java 11** or above installed in your Computer.
2. Download the latest **unimods.jar** and from here.
3. Copy the files to the folder you want to use as the home folder for your Unimods.
4. Open your CLI of choice and run `java -jar unimods.jar`.
5. Type the command in the command box and press Enter to execute it.

   Some example commands you can try:

       - help : to list all the commands with their description
       - search <module_code> : lists module code based on the given partial regex
       - show <module_code>: displays the module information
       - add <module_code> : to add the module to the timetable
       - delete <module_code> : to remove the module from the timetable
       - timetable : lists all modules added to the timetable
       - exit : Exits the app.
       - Refer to the Features below for details of each command.

<br>

---

# Features

> :information_source: **Notes about the command formats**
> - Words in `<UPPER_CASE>` are the parameters to be given by the user. <br />
    > e.g. in `show <MODULE_CODE>`, <MODULE_CODE> is a parameter and be called like so : `show CS2113T`
    <br /><br />
> - Items in square brackets are optional <br />
    > e.g. find `search <KEYWORD> [-l]`
    > can be called as `search GEH` OR `search GEH -l`.
    <br /><br />
> - Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `bye`) will be ignored.<br />
    > e.g. `help abc` will be interpreted as  `help`

## Viewing Help: `help`

If you are stuck wondering how to even begin using this application, simply type `help` into the terminal and you will
be able to see all the available commands!
You will also be provided a link to the User Guide of this application which you can refer to, for detailed descriptions
of the commands.

Output:

```shell
~$ help
__________________________________________________________________________
	UNIMods accepts the following commands:-
		| No.| Command Syntax                |            Command Action                      |
		| 1. | search <module_code>          | Search module based on the given partial regex |
		| 2. | show <module_code>            | Display module information                     |
		| 3. | add <module_code>             | Add module to the Timetable                    |
		| 4. | delete <module_code>          | Deletes module from the Timetable              |
		| 5. | clear                         | Deletes all modules from the Timetable         |
		| 6. | timetable                     | Display the Timetable                          |
		| 7. | store <grade> > <module_code> | Stores the grades scored in the Transcript     |
		| 8. | remove <module_code>          | Remove the module from the Transcript          |
		| 9. | calculate cap                 | Displays the Cumulative Average Point          |
		| 10.| exit                          | Exit From Program                              |
	 ** Note: For details, refer to the User Guide of NUSModsLite at: 
		https://ay2122s1-cs2113t-w12-2.github.io/tp/UserGuide.html
__________________________________________________________________________


```

To begin, perhaps try looking up CS2113T by running the following command:

<br>

## Display Module Info: `show <MODULE_CODE>`

If you want to find out more about a module, type `show <module_code>` to display the following:

* Name
* MCs
* Department which offers the module
* Description
* Prerequisites
* S/U option Availability
* Semester Availability

For example, try typing `show CS2113T` and see the magic happen!

:information_source: Both command and module code is case-insensitive.

```shell
Title: Software Engineering & Object-Oriented Programming

MCs: 4

Department: Computer Science

This module introduces the necessary skills for systematic and
rigorous development of software systems. It covers requirements,
design, implementation, quality assurance, and project management
aspects of small-to-medium size multi-person software projects. The
module uses the Object Oriented Programming paradigm. Students of this
module will receive hands-on practice of tools commonly used in the
industry, such as test automation tools, build automation tools, and
code revisioning tools will be covered.

Prerequisites: CS2040C or ((CS2030 or its equivalent) and CS2040/S)

S/U able: No

Semester Availability: [1, 2]

Exam Date(s): Sem 1: Tue Nov 30 09:00 AM - 11:00 AM
              Sem 2: Thu May 05 01:00 PM - 03:00 PM

```

<br>

## Search Module: `search <KEYWORD> [-l]`

Maybe you don't know what modules are out there, and want to know what GEH modules are available.

You can type `search GEH` to display all GEH modules available:

```shell
~$ search GEH
Searching, standby...
If nothing is appearing even after a while, press ENTER to cancel the search and narrow down your search terms.
GEH1001 Globalisation and New Media 4MC
GEH1002 Economic Issues in Dev World 4MC
GEH1004 Chinese Heritage: History and Literature 4MC
GEH1005 Crime Fiction in Eng & Chinese 4MC
GEH1006 Chinese Music, Language and Literature (in English) 4MC
GEH1007 Asian Cinema: The Silent Era 4MC
GEH1008 Nations & Nationalism in South Asia 4MC
:
:
```

If you wish to cancel your search, hit `ENTER`.

You can also apply the following flags to refine the search:

- **-l (small L) :** search for mods matching the level specified e.g `-l 3000` 
- **-mc :** search for mods matching the number of MCs specified e.g `-mc 4`
- **-s :** search for mods offered in the semester e.g. `-s 2`
  <br>:information_source: 3 & 4 refer to Special Terms 1 and 2 respectively.
- **-e :** search for mods that have/do not have exams. Specify with true/false e.g `-e false`.
  <br>:information_source: Defaults to false if input is invalid.
- **-f :** search for mods from a faculty e.g `-f Computing`. Checks if faculty contains keyword.
- **-d :** search for mods from a department `-d Computer Science`. Checks if faculty contains keyword.
- **-q :** performs a local search using locally saved module data which might not be the most updated version, but is
very quick e.g. `-q`. Note that local search cannot be cancelled.

:information_source: Command, search term and flag regex are case-insensitive.

:information_source: Search too slow? See [here](#slow_search).

:warning: Flags are **NOT** case-insensitive. Inputting an invalid flag will print an error message.

<br>

## Update local database: `update`

Maybe you are going to be doing some work at the University Sports Center tomorrow. You forsee that you will lack a good
WiFi connection there, and want to access the latest information offline. Run update, and grab a cup of coffee while
waiting! 

Update will take up to 10 minutes to complete. If you wish to cancel your search at any time, simply hit 
`ENTER` to cancel it!

:information_source: Command is case-insensitive.

:information_source:  You should rarely need to execute this command since every time UNI Mods retrieves data from
NUSMods, it will update that mod in the local database. You should only realistically only need to do this before the
start of a new semester, when mods are being updated for the coming semester.

<br>

## Add to timetable: `add`

### Add a module to timetable 

You have finally decided on the modules you want to take. Try adding your first module to your timetable!

Let's add for example, `CG2271` to the timetable

You can type `add` to see what you can add to the timetable.
```shell
What would you like to do?
==>add
1. Module
2. Event
Choose your option: 
```
You can choose either Module or Event, since you are adding a module, you enter 1 into the terminal
```shell
Choose your option: 1
Enter Module Code to add it into Timetable: cg2271
Now adding CG2271 into timetable
[DISCLAIMER] Lessons with the same class number are packed together
[DISCLAIMER] Adding any type of lesson will add all lessons with similar class number into timetable

              Lecture Lesson Slots                                    Tutorial Lesson Slots                                   Laboratory Lesson Slots
1: Wednesday, 0900-1100, 01, E-Learn_A                  1: Thursday, 1100-1200, 01, E-Learn_A                   1: Friday, 0800-1000, 01, E4A-04-08                     
_________________________________________________   |   _________________________________________________   |   _________________________________________________   |   
                                                        2: Tuesday, 0800-0900, 02, E-Learn_A                    2: Friday, 1000-1200, 02, E4A-04-08                     
                                                        _________________________________________________   |   _________________________________________________   |   
                                                        3: Friday, 1400-1500, 03, E-Learn_A                     
                                                        _________________________________________________   |   
```
If lessons are found, a prompt to indicate a choice for each lesson type will be shown as such.
```shell
Which Lecture would you like to choose? 
Which Tutorial would you like to choose? 
Which Laboratory would you like to choose? 
```
For modules where lessons belong to the same class number, lessons will be packed together with a divider between each different class.

For Example CS2113T lecture slot: 
```shell
Lecture Lesson Slots  
1: Friday, 1600-1800, C01, E-Learn_C  
1: Monday, 1200-1400, C01, E-Learn_C  
1: Thursday, 1200-1400, C01, E-Learn_C                  
1: Wednesday, 1200-1300, C01, E-Learn_C  
_________________________________________________   | 
```
In addition, lessons that are currently in conflict with the timetable will be displayed with a disclaimer, and prompt you whether you want to proceed.

For example:
```shell
1: Wednesday, 0900-1200, 01, E4A-04-08 [CONFLICT]
```
`NOTE:`Lessons that are added into timetable with existing lessons/events will override the slot

If all lesson types have been successfully added, program will print out 
```shell
Lessons for all modules have been successfully added
```

### Adding an Event to timetable

Perhaps you have a team meeting that takes place every Monday, you can likewise add your personal events into the timetable

Let's add for example, `Team Meeting on Monday, 8-9pm on Zoom` to your timetable

This time after typing `add` select the event option.
```shell
1. Module
2. Event
Choose your option: 2
Description of Event (E.g. Read Micah): 
```

Fill in all the relevant information and take note that time is strictly using 24-hr time format

For example:
```shell
Description of Event (E.g. Read Micah): Team Meeting CS2113T
Date of Event (E.g. Monday): Monday
Starting time of Event (E.g. 1600): 2000
Ending time of Event (E.g. 1800): 2100
Location of Event (Optional): Discord
Alright!! Event: Team Meeting CS2113T on Monday, from 2000 to 2100 at Team Meeting CS2113T has been added to your timetable
```

If the selected timeslot is already occupied, the program will let you know and the event will not be added until the timeslot
has been freed up.
<br>

## Delete from timetable: `delete <module_code>`

You can remove any module that you added to your timetable using this command.

For example: If you have CS2113T already added to your timetable. You can type
`delete CS2113T` to remove this module from your timetable.

```shell
~$ delete CS2113T
CS2113T is successfully deleted from your Timetable.
```

<br>

## Clear timetable: `clear`

You can remove **all** added modules from your timetable by typing `clear`. You can then view the empty timetable by
typing the command `timetable`.

For Example:

```shell
~$ clear
All modules have been successfully removed from your Timetable.

```

<br>

## View timetable: `timetable`

You can view the current timetable which details your daily schedule as well as the total MCs taken and classes and time
slots for each day of the week in a timetable structure.

Simply type `timetable` into the input and voila!

```shell
~$ timetable

				900             1000            1100            1200            1300            1400            1500            1600            1700            1800            
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				                                                |   CS2113T                     |                                                                               
		MON		                                                |   LEC[C01]                    |                                                                               
				                                                |   E-Learn_C                   |                                                                               
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				                                                                                                                                                                
		TUE		                                                                                                                                                                
				                                                                                                                                                                
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				                                                |   CS2113T     |                                                                                               
		WED		                                                |   LEC[C01]    |                                                                                               
				                                                |   E-Learn_C   |                                                                                               
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				                                                |   CS2113T                     |                                                                               
		THU		                                                |   LEC[C01]                    |                                                                               
				                                                |   E-Learn_C                   |                                                                               
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				                                                                                                                |   CS2113T                     |               
		FRI		                                                                                                                |   LEC[C01]                    |               
				                                                                                                                |   E-Learn_C                   |               
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				                                                                                                                                                                
		SAT		                                                                                                                                                                
				                                                                                                                                                                
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				                                                                                                                                                                
		SUN		                                                                                                                                                                
				                                                                                                                                                                

*******************
Modules taken this semester: 

CS2113T Software Engineering & Object-Oriented Programming 4MC

Total MCs taken this semester: 4.0

*******************

```

<br>

## Store grades secured in various modules: `store <GRADE> > <MODULE_CODE>`

You can store the grades you have secured for various modules by using this command.These will be stored in the list of
completed modules and will be used for CAP calculation.

For Example : Let's store `A+` grade for `CS2113T` module in the records.

You can type `store A+ > CS2113T` to store A+ grade for the module CS2113T in the records.

```shell
~$ store A+ > CS2113T
CS2113T with grade A+ has been added to the list of modules completed.
__________________________________________________________________________

```

You can also store modules with CS/CU grades for record purposes.These modules will not be considered for CAP
calculation.

For Example : Let's store `CS` grade for `CFG1002` module in the records.

```shell
~$ store CS > CFG1002
CFG1002 with grade CS has been added to the list of modules completed.
__________________________________________________________________________

```

You can choose to store modules for which you have exercised the S/U option. These modules will not be considered for
CAP calculation. For Example : Let's store `S` grade for `CS1231` module in the records.

```shell
~$ store S > CS1231
CS1231 with grade S has been added to the list of modules completed.
__________________________________________________________________________

```

<br>

## Remove modules from the list of completed modules: `remove <module_code>`

You can remove any module that you added to your list of completed modules using this command.

For example: If you have CS2113T already to your list of completed modules. You can type
`remove CS2113T` to remove this module and its corresponding grade from the list of modules completed.

```shell
~$ remove CS2113T
CS2113T is successfully removed from your Transcript.
__________________________________________________________________________
```

<br>

## Calculate CAP : `calculate cap`

You can calculate your **CAP** based on the list of modules you have completed and the corresponding grades scored in
them.

You can type `calculate cap` to view your Cumulative Average Point.

For Example:

```shell
~$ calculate cap
Cumulative Average Point : 3.0
__________________________________________________________________________

```

<br>

## View Unofficial Transcript : `transcript`

You can view your Unofficial Transcript to get an idea of your degree progress.This will display the modules you have
completed and their corresponding grades. The total number of credits completed and the CAP will also be displayed.

You can type `transcript` to view your Unofficial Transcript.

For Example:

```shell
~$==>transcript
                                --	National University of Singapore	--
                                	--	Unofficial Transcript	--
                                	------------------------------


Name               : Jake Peralta
Enrolled in Major  : Bachelor of Engineering- Computer Engineering
Year of study      : 2

Date Issued : 6-11-2021

MODULE                                                                              GRADE  CREDITS

CS2113T     Software Engineering & Object-Oriented Programming                        A+    4.0   
CS1231      Discrete Structures                                                       A     4.0   
EE2026      Digital Design                                                            B+    4.0   
CG1111      Engineering Principles and Practice I                                     A-    6.0   
CS1010      Programming Methodology                                                   B     4.0   
CFG1002     Career Catalyst                                                           CS    2.0   
CG1112      Engineering Principles and Practice II                                    S     6.0   

Total Credits Fulfilled : 30.0

Cumulative Average Point : 4.41

	This is not an official transcript issued by the Office of the Registrar.
________________________________________________________________________________________________________________________

```

<br>

---

# FAQ

**Q**: Why does this module not show up even though my search term is its exact title?
<br>
**A**: Search keyword is only for the module code. Searching for the title of a module is currently
unsupported, since NUS modules are far more often referred to by their module code.

**Q**: Why am I getting garbage characters/ Mojibake when searching for/ showing modules?
<br>
**A**: This is unfortunately caused by most shells not using UTF-8 encoding. If possible, running 
`chcp 65001` or `chcp.com 65001` from your CLI should resolve most issues.

**Q**<a name = "slow_search"></a>: Why is the search taking so long?
<br>
**A**: Your query is probably too broad. You can greatly cut down search time by specifying at least the letter codes 
of the mods you are interested in. If you absolutely need to execute that query, it is recommended to perform a 
quicksearch with the -q flag instead, which will execute in a matter of seconds. <br>
See [here](#extra_info) if you wish to know more about the technical details behind why it takes so long.

**Q**: Alright, I decided to do a quicksearch instead. Why are no mods appearing even though I know some mods must 
match my search?
<br>
**A**: Local search is dependent on local data. If certain modules cannot be found offline, and you are sure that
they do exist and match your query, then it is likely that you do not have the local data, or your local data is 
outdated.

**Q**: How do I save my timetable so I don’t have to add all my modules again?
<br>
**A**: The timetable is saved automatically on every update (add/delete etc.)

**Q**<a name = "extra_info"></a>: How does the search actually work?
<br>
**A**: Due to how the API works, the search first checks for matches in module code, level and semesters. The app will
make a request to the API for every match. Hence, including some part of a module code, and or the level/ semester 
flags will greatly increase search speed by cutting down the number of requests needed to be made.
Other flags, such as the faculty, department and exams are checked against the mod information after the request and as 
such have little to no effect on how fast the search goes. The most common cause of a slow search is running `search`
without any search term specified, which means that UNIMods has to make 12000+ requests to the API, one for each mod,
and is very similar to just running `update`. 

<br>

---

## Command Summary

| Command                         | Meaning                                                                                            |
| --------------                  | ----------                                                                                         |
| `help`                          | Shows available commands and flags. <br> Example: `help`                                           |
| `search <KEYWORD> [-l]`         | Lists modules that have partial matches by regex to the keyword. <br> Example: `search GEH -l 1000`|
| `show <MODULE_CODE>`            | Display relevant module information. <br> Example: `show CS2113T`                                  |
| `update`                        | Fetches all mods from the API to a local save. <br> Example: `update`                              |
| `add`                           | Adds modules or tasks to the timetable. <br> Example: `add`                                        |
| `delete <MODULE_CODE/TASK>`     | Deletes the module from the timetable. <br> Example: `delete CS2113T`                              |
| `clear`                         | Deletes all modules from the timetable. <br> Example: `clear`                                      |
| `edit`                          | Edit a personal task in the timetable. <br> Example: `edit`                                        |
| `timetable`                     | Displays the timetable in a weekly grid format <br> Example: `timetable`                           |
| `semester`                      | Changes the academic semester that you wish to plan for <br> Example: `semester`                   |
| `check <MODULE_CODE>`           | Check whether the module's pre-requisite is met <br> Example: `check CS2113T`                      |
| `store <GRADE> > <MODULE_CODE>` | Assigns a grade to a module and stores it on record <br> Example: `store B+ > CS2113T`             |
| `remove <MODULE_CODE>`          | Removes the module from the profile's record <br> Example: `remove CS2113T`                        |
| `calculate`                     | Calculates your CAP from your profile. <br> Example: `calculate`                                   |
| `transcript`                    | Creates and displays an unofficial transcript <br> Example: `transcript`                           |
| `exit`                          | Saves the current state and exits UniMods safely <br> Example: `exit`                              |                                                   |

---
