# User Guide

## Introduction

The Smart Inventory Tracking and Updating System (SITUS) is a **desktop app for tracking
ingredient inventory designed for restaurant/ F&B inventory managers.** 

Have you ever found yourself tired of clicking buttons endlessly when adding or removing items from your current 
inventory-tracking software? Would you much rather type a single line command to achieve the same things a long-winded GUI 
could do? 

**If so, SITUS is for you!**

SITUS allows you to not only track the stock of your ingredients but also their expiry dates, 
search for ingredients by name or expiry date, and even set and receive alerts when stocks they are running low or reaching expiry.

This user guide will give you a quick rundown of all the things SITUS can do for you and how you can use these tools effortlessly.  

### How to use this guide
There are 4 main sections in this guide, [Quick Start](#1-quick-start), [Features](#2-features), [FAQ](#3-faq), 
and [Command Summary](#4-command-summary). All of these are included in [Contents](#contents) for your convenience. 

[Quick Start](#1-quick-start) provides you with a short tutorial on how to get SITUS set up correctly on your own device.
[Features](#2-features) are split into different subsections for each of SITUS' commands. They all have the same structure:
1. A brief description of what you can do with the command
2. The format of the command and an explanation of its user-defined parameters if it requires any (more on user-defined 
parameters [here](#format-notes))
   * If there are user-defined parameters, examples of possible commands are also given  
3. Expected outputs from SITUS after running the command

[FAQ](#3-faq) answers some possible questions you may have getting started with SITUS. [Command Summary](#4-command-summary)
provides a table summarising all SITUS commands and their formats for your quick reference.

#### Format notes
* All SITUS commands are case-insensitive. Hence, if you enter `carrot`, `Carrot`, or even `caRRoT` as part of commands, they are 
read as the same word by SITUS
* Words/phrases in this format: `example`, are command snippets
* Words/phrases within square brackets `[square bracket]` are user's (your) inputs. <br>
  For example, in `delete [INGREDIENT_NUMBER]`, `INGREDIENT_NUMBER` is the user's input,
  such as `delete 1`.
  > Note: Ensure *ALL* parameters are specified when entering the command. There are no optional parameters for commands
  > apart from [the `date` command](#211-view-set-current-date).
* All information about ingredients expiring in **x** days is calculated based on the current date of the system.

## Contents

[**1. Quick Start**](#1-quick-start)  <br>
[**2. Features**](#2-features)  <br>
&nbsp;&nbsp;[2.1. Help](#21-viewing-help) <br>
&nbsp;&nbsp;[2.2. Add Ingredients](#22-add-ingredients) <br>
&nbsp;&nbsp;[2.3. List Ingredients](#23-list-ingredients) <br>
&nbsp;&nbsp;[2.4. Update Ingredients](#24-update-ingredients) <br>
&nbsp;&nbsp;[2.5. Subtract Ingredients Stock](#25-subtract-ingredient-stock) <br>
&nbsp;&nbsp;[2.6. Delete Ingredients](#26-delete-ingredients) <br>
&nbsp;&nbsp;[2.7. Search Ingredients By Expiry](#27-search-ingredients-by-expiry) <br>
&nbsp;&nbsp;[2.8. Search Ingredients By Name](#28-search-ingredients-by-name) <br>
&nbsp;&nbsp;[2.9. Display Alerts](#29-display-alerts) <br>
&nbsp;&nbsp;[2.10. Set Thresholds](#210-set-thresholds) <br>
&nbsp;&nbsp;[2.11. View/ Set Current Date](#211-view-set-current-date) <br>
&nbsp;&nbsp;[2.12. Exit Program](#212-exit-program) <br>
[**3. FAQ**](#3-faq) <br>
[**4. Command Summary**](#4-command-summary) <br>

## 1. Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Situs` from [here](https://github.com/AY2122S1-CS2113T-T09-3/tp/releases/tag/v2.0).
3. Copy the jar file to the folder that you want to use SITUS.
4. Open a terminal and navigate to the folder containing the jar file.
5. Type `java -jar Situs.jar` in the terminal window.
6. If successful, you should see a similar message when the program starts up:

```
____________________________________________________
Welcome to SITUS!
What would you like to do first?
To see what I can do, use "help"
____________________________________________________
____________________________________________________
No ingredients expiring by [5 days from current date]
No ingredients with stock less than 1.0 kg
____________________________________________________
```
   
## 2. Features

This section covers the commands SITUS can execute - how you can use them and their expected outputs. 

### 2.1. Viewing Help

You can view a quick summary of SITUS's commands and their syntax from within SITUS.

Command: `help`

Output:
```
help
____________________________________________________
These are the commands I can currently carry out:
	(intended action - command format)
	1. add an ingredient - add n/INGREDIENT_NAME a/AMOUNT e/EXPIRY
	2. list all ingredients - list
	3. update an ingredient - update GROUP_INDEX.INGREDIENT_INDEX a/AMOUNT
	4. subtract an ingredient's stock - subtract GROUP_INDEX a/AMOUNT
	5. delete an ingredient - delete GROUP_INDEX.INGREDIENT_INDEX
	6. search ingredients by expiry - expire DATE
	7. search ingredients by name - find INGREDIENT_NAMES
	8. view alerts - alert ALERT_TYPE
	9. set alert thresholds - set ALERT_TYPE VALUE
	10. view SITUS's current date - date
	11. edit SITUS's current date - date DATE (in dd/mm/yyyy format)
	12. exit SITUS - exit
____________________________________________________
```

### 2.2. Add Ingredients

You can add an ingredient to the ingredient list to start tracking it.

Command: `add n/[INGREDIENT_NAME] a/[AMOUNT] e/[EXPIRY]`

The parameters used in the command are:
* `INGREDIENT_NAME`: the ingredient name to add
* `AMOUNT`: the ingredient amount to be added, in kilograms.
* `EXPIRY`: the expiration date of ingredient, in format of `dd/mm/yyyy`.

Examples: `add n/carrot a/20 e/01/03/2022`, `add n/potato a/5 e/25/12/2021`

Outputs:
```
add n/carrot a/20 e/01/03/2022
____________________________________________________
Got it. This ingredient has been added to the inventory:
	Carrot | Amount Left: 20.000 kg | Expiry Date: 01/03/2022
Current inventory has 1 items.
This ingredient will expire in 131 days.
____________________________________________________
```
```
add n/potato a/5 e/25/12/2021
____________________________________________________
Got it. This ingredient has been added to the inventory:
    Potato | Amount Left: 5.000 kg | Expiry Date: 25/12/2021
Current inventory has 2 items.
This ingredient will expire in 65 days.
____________________________________________________
```

> Note: It is important you name your ingredients either **ALL** singular or **ALL** plural to avoid adding both `Carrot` and 
> `Carrots` as separate ingredients, as SITUS only looks for an exact match when grouping ingredients by name (more on this
> grouping will be explained in the next section).

### 2.3. List Ingredients

You can view the full list of your ingredients that SITUS is currently tracking. The list shows your ingredients in groups. 
These groups are ingredients of the same name (e.g. `Carrot` or `Potato`), and within each group are the different expiry
dates of certain amounts of that ingredient. For example in the output shown below, it shows that there are 20 kg of the 
ingredient `Carrot`, and all 20 kg are expiring on 01/03/2022.  

Command: `list`

Output:
```
list
____________________________________________________
Here is the list of the ingredients currently in inventory:
	1. Carrot | Total Amount: 20.000 kg
		1.1. Amount Left: 20.000 kg | Expiry Date: 01/03/2022

	2. Potato | Total Amount: 5.000 kg
		2.1. Amount Left: 5.000 kg | Expiry Date: 25/12/2021
____________________________________________________
```

> Note: You are recommended to use `list` before most of SITUS's commands as many of them require the index of the 
> ingredients (the numbers before each ingredient in the list, such as 1.1 or 2.1).

### 2.4. Update Ingredients

You can update the amount of an ingredient in your ingredient list if it needs
changes.

Command: `update [GROUP_INDEX.INGREDIENT_INDEX] a/[AMOUNT]`

The parameters used in the command are:
* `GROUP_INDEX`: the index of the group the ingredient to update is in
* `INGREDIENT_INDEX` : the index of the ingredient to update within its group
* `AMOUNT`: the ingredient amount to be updated, in kilograms

Example: `update 1.1 a/100`

Output (using list shown in the [list section](#23-list-ingredients)):
```
update 1.1 a/100
____________________________________________________
Got it. This ingredient has been updated:
	Carrot | Amount Left: 100.000 kg | Expiry Date: 01/03/2022
____________________________________________________
```

### 2.5. Subtract Ingredient Stock

You can subtract a given amount from an ingredient's total amount if you have used/ sold that amount. If the amount 
given is equal to the existing stock of the ingredient, the ingredient is automatically removed from the list (instead 
of displaying zero stock for the item.)
> Note: If the ingredient has multiple entries, the earliest expiring one will be deducted first, as a good practice for inventory management.

Command: `subtract [GROUP_INDEX] a/[AMOUNT]`

The parameters used in the command are:

* `GROUP_INDEX`: the index of the ingredient group to subtract from
* `AMOUNT`: the ingredient amount to be subtracted, in kilograms

Example: `subtract 1 a/50`

Output (using list shown in the [list section](#23-list-ingredients)):

```
subtract 1 a/50
____________________________________________________
Got it. 50.0 kg has been subtracted from Carrot
____________________________________________________
```

### 2.6. Delete Ingredients

You can delete an ingredient from the ingredient list based on its name and expiry date in the list if you wish to stop 
tracking it (e.g. if that batch has expired).

Command: `delete [GROUP_INDEX.INGREDIENT_INDEX]`

The parameter used in the command are:
* `GROUP_INDEX`: the index of the group the ingredient to delete is in
* `INGREDIENT_INDEX` : the index of the ingredient to update within its group

Example: `delete 1.1`

Output (using list shown in the [list section](#23-list-ingredients)):
```
delete 1.1
____________________________________________________
Got it. This ingredient has been removed:
	Carrot | Amount Left: 50.000 kg | Expiry Date: 01/03/2022
____________________________________________________
```

### 2.7. Search Ingredients By Expiry

You can search for ingredients that will expire by a specified date instead of looking through the full list for them. 
The ingredients expiring sooner are shown at the top. Expired ingredients are also shown for your action.

Command: `expire [DATE]`

The parameter used in the command is:
* `DATE`: the date of interest, in the format of `dd/mm/yyyy`.

Example: `expire 26/12/2022`

This is the current list: 
```
list
____________________________________________________
Here is the list of the ingredients currently in inventory:
	1. Carrot | Total Amount: 2.000 kg
		1.1. Amount Left: 2.000 kg | Expiry Date: 11/11/2021

	2. Plum | Total Amount: 7.000 kg
		2.1. Amount Left: 2.000 kg | Expiry Date: 06/11/2021
		2.2. Amount Left: 5.000 kg | Expiry Date: 12/11/2021

	3. Pumpkin | Total Amount: 7.000 kg
		3.1. Amount Left: 6.000 kg | Expiry Date: 06/11/2021
		3.2. Amount Left: 1.000 kg | Expiry Date: 21/11/2021
____________________________________________________

```

Output:
```
expire 13/11/2021
____________________________________________________
There are 4 ingredients expiring by: 13/11/2021
    Plum | Amount Left: 2.000 kg | Expiry Date: 06/11/2021
    Pumpkin | Amount Left: 6.000 kg | Expiry Date: 06/11/2021
    Carrot | Amount Left: 2.000 kg | Expiry Date: 11/11/2021
    Plum | Amount Left: 5.000 kg | Expiry Date: 12/11/2021
____________________________________________________
```

### 2.8. Search Ingredients By Name

You can search for ingredients using keywords in their names to find specific ingredients without looking through the
full list.

Command: `find [INGREDIENT_NAMES]`
* `INGREDIENT_NAMES`: the words to search for in the ingredient list. 
One or more ingredient names can be entered. Separate them using *spaces*.
> Note: these names are case-insensitive, so the query `carrot` will give results that may not be exact matches, such as `Carrot`.

Examples: `find radish`, `find potato tomato`

Outputs (using list shown in [Section 2.7](#27-search-ingredients-by-expiry):
```
find carrot
____________________________________________________
I found these ingredients for "carrot":
	1. Carrot | Total Amount: 2.000 kg
		1.1. Amount Left: 2.000 kg | Expiry Date: 11/11/2021
____________________________________________________
```
```
find plum pumpkin
____________________________________________________
I found these ingredients for "plum":
	2. Plum | Total Amount: 7.000 kg
		2.1. Amount Left: 2.000 kg | Expiry Date: 06/11/2021
		2.2. Amount Left: 5.000 kg | Expiry Date: 12/11/2021
I found these ingredients for "pumpkin":
	3. Pumpkin | Total Amount: 7.000 kg
		3.1. Amount Left: 6.000 kg | Expiry Date: 06/11/2021
		3.2. Amount Left: 1.000 kg | Expiry Date: 21/11/2021
____________________________________________________
```


### 2.9. Display Alerts

You can view the different types of alerts to see which ingredients are running low on stock or expiring.

Command: `alerts [ALERT_TYPE]`

There are 3 possible `[ALERT_TYPE]`:
1. `expiry`: displays all ingredients expiring within the *number of days* as specified by the threshold, with ingredients expiring sooner/that have expired shown first.
2. `stock`: displays all ingredients with stock lower than the threshold value
3. `all`: displays both of the above

Output (using list shown in [Section 2.7](#27-search-ingredients-by-expiry)):
```
alerts expiry
____________________________________________________
There are 3 ingredients expiring by: 11/11/2021
    Plum | Amount Left: 2.000 kg | Expiry Date: 06/11/2021
    Pumpkin | Amount Left: 6.000 kg | Expiry Date: 06/11/2021
    Carrot | Amount Left: 2.000 kg | Expiry Date: 11/11/2021
____________________________________________________
alerts stock
____________________________________________________
There are 3 ingredients with stock less than 10.0 kg
    Carrot | Total Amount: 2.000 kg
    Plum | Total Amount: 7.000 kg
    Pumpkin | Total Amount: 7.000 kg
____________________________________________________
```

### 2.10. Set thresholds

You can set the expiry threshold or the stock threshold for the [alerts](#29-display-alerts) command.

Command: `set [TYPE] [NEW_VALUE]`

The parameters used in the command are:
* `TYPE`: either `expiry` or `stock`
* `NEW_VALUE`: the new threshold for which alerts will be displayed (in days for `expiry` and kilograms for `stock`)

Examples: `set expiry 30`, `set stock 4.0`

Outputs:
```
set expiry 30
____________________________________________________
Successfully set expiry threshold to 30 days
____________________________________________________
```
```
set stock 4.0
____________________________________________________
Successfully set low stock threshold to 4.0 kg
____________________________________________________
```


### 2.11. View/ Set Current Date

You can view or set SITUS's current date to ensure it matches the actual date. 

Command: `date [NEW_DATE]`
  * `NEW_DATE`: The date in the format of `dd/mm/yyyy`. 
  * To view current date, leave this field empty
  * To set a new current date, key in the new date

Examples: `date`, `date 01/01/2022`

Outputs:
```
date
____________________________________________________
Current session date is: 21/10/2021
____________________________________________________
```
```
date 01/01/2022
____________________________________________________
The current session date has been changed to 01/01/2022
____________________________________________________
```

### 2.12. Exit Program

You can exit SITUS once your inventory check/ update is complete.

Command: `exit`

Output:
```
exit
____________________________________________________
Okay, see you soon! Goodbye.
____________________________________________________
```

## 3. FAQ

**Q**: How do I save my ingredient list?

**A**: The ingredient list is automatically saved after any operations that edits its contents. 
- The ingredient data is saved under `data/ingredients.txt`
- The threshold data for expiry and stock are saved under `data/thresholds.txt`

**Q**: How do I transfer my data to another computer?

**A**: You can copy the `data` directory to the directory holding the jar file on the other computer.


## 4. Command Summary

The following table shows a summary of all of SITUS's commands and links to their sections for your convenience.

| Action | Command |
|---|---|
| [View help](#21-viewing-help) | `help` |
| [Add ingredient](#22-add-ingredients) | `add n/[INGREDIENT_NAME] a/[AMOUNT] e/[EXPIRY]` |
| [List ingredients](#23-list-ingredients) | `list` |
| [Update ingredient](#24-update-ingredients) | `update [GROUP_INDEX.INGREDIENT_INDEX] a/[AMOUNT]` |
| [Subtract Ingredients Stock](#25-subtract-ingredient-stock) | `subtract [GROUP_INDEX] a/[AMOUNT]` | 
| [Delete ingredient](#26-delete-ingredients) | `delete [GROUP_INDEX.INGREDIENT_INDEX]` |
| [Search Ingredients By Expiry](#27-search-ingredients-by-expiry) | `expire [DATE]` |
| [Search Ingredients By Name](#28-search-ingredients-by-name) | `find [INGREDIENT_NAMES]` |
| [Display Alerts](#29-display-alerts) | `alert [ALERT_TYPE]` |
| [Set Thresholds](#210-set-thresholds) | `set [TYPE] [NEW_VALUE]` |
| [View/ Set Current Date](#211-view-set-current-date) | `date {NEW_DATE}` |
| [Exit Program](#212-exit-program) | `exit` |
