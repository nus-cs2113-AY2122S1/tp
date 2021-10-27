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
#### Format notes
* Words/phrases in this format: `example`, are command snippets
* Words/phrases within square brackets `[square bracket]` are user's inputs. <br>
  For example, in `delete [INGREDIENT_NUMBER]`, `INGREDIENT_NUMBER` is the user's input,
  such as `delete 1`.
  > Note: Ensure *ALL* parameters are specified when entering the command. There are no optional parameters for commands
  > apart from [the `date` command](#210-view-set-current-date).
* All information about ingredients expiring in **x** days is calculated based on the current date of the system.

## Contents

[**1. Quick Start**](#1-quick-start)  <br>
[**2. Features**](#2-features)  <br>
&nbsp;&nbsp;[2.1. Help](#21-viewing-help) <br>
&nbsp;&nbsp;[2.2. Add Ingredients](#22-add-ingredients) <br>
&nbsp;&nbsp;[2.3. List Ingredients](#23-list-ingredients) <br>
&nbsp;&nbsp;[2.4. Update Ingredients](#24-update-ingredients-to-be-updated) <br>
&nbsp;&nbsp;[2.5. Subtract Ingredients Stock](#25-subtract-ingredient-stock-to-be-added) <br>
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
2. Download the latest version of `Situs` from [here](https://github.com/AY2122S1-CS2113T-T09-3/tp/releases/tag/v1.0).
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

This section covers the commands SITUS can execute - how you can use them and the expected outputs. 

### 2.1. Viewing Help [to update]

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
	3. update an ingredient - update n/INGREDIENT_NAME a/AMOUNT e/EXPIRY
	4. subtract an ingredient's stock - subtract n/INGREDIENT_NAME a/AMOUNT
	5. delete an ingredient - delete INDEX
	6. search ingredients by expiry - expire DATE
	7. search ingredients by name - find INGREDIENT_NAMES
	8. view alerts - alert ALERT_TYPE
	9. set alert thresholds - set ALERT_TYPE VALUE
	10. view SITUS's current date - date
	11. edit SITUS's current date - date DATE (in yyyy-mm-dd format)
	12. exit SITUS - exit
____________________________________________________
```

### 2.2. Add Ingredients

You can add an ingredient to the ingredient list to start tracking it.

`add n/[INGREDIENT_NAME] a/[AMOUNT] e/[EXPIRY]`

The parameters used in the command are:
* `INGREDIENT_NAME`: the ingredient name to add
* `AMOUNT`: the ingredient amount to be added, in kilograms.
* `EXPIRY`: the expiration date of ingredient, in format of `dd/mm/yyyy`.

Examples:
* `add n/carrot a/20 e/01/03/2022`
* `add n/potato a/5 e/25/12/2021`

Output:
```
add n/carrot a/20 e/01/03/2022
____________________________________________________
Got it. This ingredient has been added to the inventory:
	Carrot | Amount Left: 20.0 kg | Expiry Date: 01/03/2022
Current inventory has 1 items.
This ingredient will expire in 131 days.
____________________________________________________
```
```
add n/potato a/5 e/25/12/2021
____________________________________________________
Got it. This ingredient has been added to the inventory:
    Potato | Amount Left: 5.0 kg | Expiry Date: 25/12/2021
Current inventory has 2 items.
This ingredient will expire in 65 days.
____________________________________________________
```

### 2.3. List Ingredients

You can view the full list of your ingredients that SITUS is currently tracking.

Command: `list`

Output:
```
list
____________________________________________________
Here is the list of the ingredients currently in inventory:
    1. Carrot | Total Amount: 20.0 kg
        Amount Left: 20.0 kg | Expiry Date: 01/03/2022
	    
    2. Potato | Total Amount: 5.0 kg
        Amount Left: 5.0 kg | Expiry Date: 25/12/2021

____________________________________________________
```

### 2.4. Update Ingredients

You can update the amount, unit and expiry of an ingredient in your ingredient list if it needs
changes.

Command:
`update n/[INGREDIENT_NAME] a/[AMOUNT] e/[EXPIRY]`

The parameters used in the command are:
* `INGREDIENT_NAME`: the ingredient name to update
* `AMOUNT`: the ingredient amount to be updated, in kilograms
* `EXPIRY`: the updated expiration date of the ingredient, in format of `dd/mm/yyyy`.

Example: `update n/carrot a/100 e/05/04/2022`

Outputs:
```
update n/carrot a/100 e/05/04/2022
____________________________________________________
Got it. This ingredient has been updated:
    Carrot | Amount Left: 100.0 kg | Expiry Date: 05/04/2022
____________________________________________________
```

### 2.5. Subtract Ingredient Stock

You can subtract a given amount from an ingredient's total amount.

Command: `subtract n/[INGREDIENT_NAME] a/[AMOUNT]`

The parameters used in the command are:

* `INGREDIENT_NAME`: the ingredient name to subtract
* `AMOUNT`: the ingredient amount to be subtracted, in kilograms

Example: `subtract n/carrot a/100`

Output:

```
subtract n/carrot a/100
____________________________________________________
Got it. This 100.0 kg has been subtracted from Carrot
____________________________________________________
```

### 2.6. Delete Ingredients

You can delete an ingredient from the ingredient list based on its name and expiry date in the list if you wish to stop 
tracking it.

> Note: You are recommended to use the `list` command *prior* to deleting to confirm the ingredient number of the 
> ingredient you intend to remove.

Command: `delete n/[INGREDIENT_NAME] e/[EXPIRY_DATE]`

The parameter used in the command are:
* `INGREDIENT_NAME`: the ingredient name to remove
* `EXPIRY_DATE`: the expiry date of the ingredient to remove, in format of `dd/mm/yyyy`

Example: `delete n/carrot e/01/03/2022`

Output:
```
delete n/carrot e/01/03/2022
____________________________________________________
Got it. This ingredient has been removed:
    Carrot | Amount Left: 20.0 kg | Expiry Date: 01/03/2022
____________________________________________________
```

### 2.7. Search Ingredients By Expiry

You can search for ingredients that will expire by a specified date instead of looking through the full list for them. 

Command: `expire [DATE]`

The parameter used in the command is:
* `DATE`: the date of interest, in the format of `dd/mm/yyyy`.

Example: `expire 26/12/2022`

Current list: 
```
list
____________________________________________________
Here is the list of the ingredients currently in inventory:
    1. Potato | Amount Left: 0.6 kg | Expiry Date: 30/12/2021
    2. Carrot | Amount Left: 200.0 sticks | Expiry Date: 01/03/2022
    3. Radish | Amount Left: 43.8 kg | Expiry Date: 19/01/2022
    4. Tomato | Amount Left: 23.7 kg | Expiry Date: 21/11/2021
____________________________________________________
```

Output:
```
expire 30/01/2022
____________________________________________________
There are 3 ingredients expiring by: 2022-01-30
    Potato | Amount Left: 0.6 kg | Expiry Date: 30/12/2021
    Radish | Amount Left: 43.8 kg | Expiry Date: 19/01/2022
    Tomato | Amount Left: 23.7 kg | Expiry Date: 21/11/2021	
____________________________________________________
```

### 2.8. Search Ingredients By Name

You can search for ingredients using keywords in their names to find specific ingredients without looking through the
full list.

Command: `find [INGREDIENT_NAMES]`
* `INGREDIENT_NAMES`: the words to search for in the ingredient list
> Note: One or more ingredient names can be entered. Separate them using *spaces*.

Examples: `find radish`, `find potato tomato`

Output:
```
find carrot
____________________________________________________
I found these ingredients for "radish":
    3. Radish | Amount Left: 43.8 kg | Expiry Date: 19/01/2022
____________________________________________________
```
```
find potato tomato
____________________________________________________
I found these ingredients for "potato":
    1. Potato | Amount Left: 0.6 kg | Expiry Date: 30/12/2021
I found these ingredients for "tomato":
    4. Tomato | Amount Left: 23.7 kg | Expiry Date: 21/11/2021
____________________________________________________
```


### 2.9. Display Alerts

You can view the different types of alerts to see which ingredients are running low on stock or expiring.

Command: `alerts [ALERT_TYPE]`

There are 3 possible `[ALERT_TYPE]`:
1. `expiry`: displays all ingredients expiring within the *number of days* as specified by the threshold
2. `stock`: displays all ingredients with stock lower than the threshold value
3. `all`: displays both of the above

Full Ingredient List: 
```
list
____________________________________________________
Here is the list of the ingredients currently in inventory:
    1. Potato | Amount Left: 0.6 kg | Expiry Date: 30/12/2021
    2. Carrot | Amount Left: 200.0 sticks | Expiry Date: 01/03/2022
    3. Radish | Amount Left: 43.8 kg | Expiry Date: 19/01/2022
    4. Tomato | Amount Left: 23.7 kg | Expiry Date: 21/11/2021
____________________________________________________
```

Output:
```
alerts expiry
____________________________________________________
No ingredients expiring by 27/10/2021
____________________________________________________
alerts stock
____________________________________________________
There are 1 ingredients with stock less than 5.0 kg
    Potato | Amount Left: 0.6 kg | Expiry Date: 30/12/2021
____________________________________________________
```

### 2.10. Set thresholds

You can set the expiry threshold or the stock threshold for the [alerts](#27-display-alerts) command.

Command: `set [TYPE] [NEW_VALUE]`

The parameters used in the command are:
* `TYPE`: either `expiry` or `stock`
* `NEW_VALUE`: the new threshold for which alerts will be displayed

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
  * To view current date: Leave field empty
  * To set a new current date: Key in the new date

Output:
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

**A**: The ingredient list is automatically saved after any operations that edits its contents. They are saved under `data\"ingredients.txt"`

**Q**: How do I transfer my data to another computer?

**A**: You can copy the `data` directory to the directory holding the jar file on the other computer.


## 4. Command Summary

| Action | Command |
|---|---|
| Add ingredient | `add n/[INGREDIENT_NAME] a/[AMOUNT] u/[UNITS] e/[EXPIRY]` |
| List ingredients | `list` |
| Update ingredient | `update n/[INGREDIENT_NAME] a/[AMOUNT] u/[UNITS] e/[EXPIRY]` |
| Delete ingredient | `delete [INGREDIENT_NUMBER]` |
| Show expiring ingredients | `expire [DATE]` |
| Show alerts | `alert [ALERT_TYPE]` |
| Set thresholds | `set [TYPE] [NEW_VALUE]` |
| Search for ingredients | `find [INGREDIENT_NAMES]` |
| View/set current date | `date [NEW_DATE]` |
| Exit program | `exit` |
| View help | `help` |
