# User Guide

## Introduction

The Smart Inventory Tracking and Updating System (SITUS) is a **desktop app for tracking
ingredient inventory.**

### How to use this guide
#### Format notes
* Words/phrases in this format: `example`, are command snippets
* Words/phrases within square brackets `[square bracket]` are user's inputs. <br>
  For example in `delete [INGREDIENT_NUMBER]`, `INGREDIENT_NUMBER` is the user's input,
  such as `delete 1`.

## Contents

[**1. Quick Start**](#1-quick-start)  <br>
[**2. Features**](#2-features)  <br>
&nbsp;&nbsp;[2.1. Help](#21-viewing-help) <br>
&nbsp;&nbsp;[2.2. Add Ingredients](#22-add-ingredients) <br>
&nbsp;&nbsp;[2.3. List Ingredients](#23-list-ingredients) <br>
&nbsp;&nbsp;[2.4. Update Ingredients](#24-update-ingredients) <br>
&nbsp;&nbsp;[2.5. Delete Ingredients](#25-delete-ingredients) <br>
&nbsp;&nbsp;[2.6. Show Expiring Ingredients](#26-show-expiring-ingredients) <br>
&nbsp;&nbsp;[2.7. Display Alerts](#27-display-alerts) <br>
&nbsp;&nbsp;[2.8. Set Thresholds](#28-set-thresholds) <br>
&nbsp;&nbsp;[2.9. Search Ingredients](#29-search-ingredients) <br>
&nbsp;&nbsp;[2.10. View/ Set Current Date](#210-view-set-current-date) <br>
&nbsp;&nbsp;[2.11. Exit Program](#211-exit-program) <br>
[**3. FAQ**](#3-faq) <br>
[**4. Command Summary**](#command-summary) <br>

## 1. Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Situs` from [here](https://github.com/AY2122S1-CS2113T-T09-3/tp/releases/tag/v1.0).
3. Copy the jar file to the folder that you want to use SITUS.
4. Open a terminal and navigate to the folder containing the jar file.
5. Type `java -jar Situs.jar` in the terminal window.
6. If successful, you should see the following message when the program starts up:

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

This section covers the commands SITUS can execute - how to use them and the expected output. 

### 2.1. Viewing Help

Shows a list of available commands and their syntax.

Command: `help`
```
help
____________________________________________________
These are the commands I can currently carry out:
	(intended action - command format)
	1. add an ingredient - add n/INGREDIENT_NAME a/AMOUNT u/UNITS e/EXPIRY
	2. list all ingredients - list
	3. update an ingredient - update n/INGREDIENT_NAME a/AMOUNT u/UNITS e/EXPIRY
	4. delete an ingredient - delete INDEX
	5. view SITUS's current date - date
	6. edit SITUS's current date - date yyyy-mm-dd
	7. exit SITUS - exit
____________________________________________________
```



### 2.2. Add Ingredients

Add an ingredient to the ingredient list.

Command:
\
`add n/[INGREDIENT_NAME] a/[AMOUNT] u/[UNITS] e/[EXPIRY]`

The parameters used in the command are:
* `INGREDIENT_NAME`: name of the ingredient.
* `AMOUNT`: amount of the ingredient.
* `UNITS`: units specified to associate with `AMOUNT`.
* `EXPIRY`: expiration date of ingredient, in format of `dd/mm/yyyy`.

> Note: Ensure *ALL* parameters are specified when entering the command.

Examples:
* `add n/carrot a/200 u/sticks e/01/03/2022`
* `add n/potato a/500 u/g e/25/12/2021`

Output:
```
add n/carrot a/200 u/sticks e/01/03/2022
____________________________________________________
Got it. This ingredient has been added to the inventory:
	Carrot | Amount Left: 200.0 sticks | Expiry Date: 01/03/2022
Current inventory has 1 items.
This ingredient will expire in 131 days.
____________________________________________________
```
```
add n/potato a/500 u/g e/25/12/2021
____________________________________________________
Got it. This ingredient has been added to the inventory:
	Potato | Amount Left: 500.0 g | Expiry Date: 25/12/2021
Current inventory has 2 items.
This ingredient will expire in 65 days.
____________________________________________________
```

### 2.3. List Ingredients

Displays a list of all ingredients in the ingredient list

Command: `list`
```
list
____________________________________________________
Here is the list of the ingredients currently in inventory:
	1. Carrot | Amount Left: 200.0 sticks | Expiry Date: 01/03/2022
	2. Potato | Amount Left: 500.0 g | Expiry Date: 25/12/2021
____________________________________________________
```


### 2.4. Update Ingredients

Update the amount, unit and expiry of an ingredient in the ingredient list

Command: 
\
`update n/[INGREDIENT_NAME] a/[AMOUNT] u/[UNITS] e/[EXPIRY]`

The parameters used in the command are:
* `INGREDIENT_NAME`: name of the ingredient
* `AMOUNT`: updated amount of the ingredient
* `UNITS`: updated unit of the ingredient
* `EXPIRY`: updated expiration date of the ingredient, in format of `dd/mm/yyyy`

Examples:
* `update n/carrot a/100 u/sticks e/05/04/2022`
* `update n/potato a/0.6 u/kg e/30/12/2021`

```
update n/carrot a/100 u/sticks e/05/04/2022
____________________________________________________
Got it. This ingredient has been updated:
	Carrot | Amount Left: 100.0 sticks | Expiry Date: 05/04/2022
____________________________________________________
```
```
update n/potato a/0.6 u/kg e/30/12/2021
____________________________________________________
Got it. This ingredient has been updated:
	Potato | Amount Left: 0.6 kg | Expiry Date: 30/12/2021
____________________________________________________
```

### 2.5. Delete Ingredients

Delete an ingredient from the ingredient list based on its number in the list.
> Note: Users are recommended to use the `list` command *prior* to deleting to confirm the ingredient number of the ingredient  they intend to remove.

Command: `delete [INGREDIENT_NUMBER]`

The parameter used in the command is:
* `INGREDIENT_NUMBER`: the ingredient number to remove

Example: `delete 1`
```
delete 1
____________________________________________________
Got it. This ingredient has been removed:
	Carrot | Amount Left: 100.0 sticks | Expiry Date: 05/04/2022
____________________________________________________
```

### 2.6. Show Expiring Ingredients

List all ingredients that will expire by a specified date.

Command: `expire [DATE]`

The parameter used in the command is:
* `DATE`: the date of interest, in the format of `dd/mm/yyyy`.

Example: `expire 30/01/2022`

Full ingredient List: 
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
```
expire 30/01/2022
____________________________________________________
There are 3 ingredients expiring by: 2022-01-30
	Potato | Amount Left: 0.6 kg | Expiry Date: 30/12/2021
	Radish | Amount Left: 43.8 kg | Expiry Date: 19/01/2022
	Tomato | Amount Left: 23.7 kg | Expiry Date: 21/11/2021	
____________________________________________________
```

### 2.7 Display Alerts

Displays the specified type of alert

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
```
alerts expiry
____________________________________________________
No ingredients expiring by 2021-11-20
____________________________________________________
alerts stock
____________________________________________________
There are 1 ingredients with stock less than 5.0 kg
	Potato | Amount Left: 0.6 kg | Expiry Date: 30/12/2021
____________________________________________________
```

For more information about setting thresholds, see [here](#28-set-thresholds).

### 2.8 Set thresholds

Sets the expiry threshold or the stock threshold for the [alerts](#27-display-alerts) command.

Command: `set [TYPE] [NEW_VALUE]`

The parameters used in the command are:
* `TYPE`: either `expiry` or `stock`
* `NEW_VALUE`: the new threshold for which alerts will be displayed
```
set expiry 30
____________________________________________________
Successfully set expiry threshold to 30days
____________________________________________________
```
```
set stock 5
____________________________________________________
Successfully set low stock threshold to 5kg
____________________________________________________
```

### 2.9. Search Ingredients

Search for ingredients using keywords in their names.

Command: `find [INGREDIENT_NAMES]`
* `INGREDIENT_NAMES`: The words to search for in the ingredient list 
> Note: One or more ingredient names can be entered. Separate them using *spaces*.

Examples: `find radish`, `find potato tomato`

```
find radish
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

### 2.10. View/ Set Current Date

View or set the system's current date. 

Command: `date [NEW_DATE]`
* `NEW_DATE`: The date in the format of yyyy/mm/dd. 
  * To view current date: Leave field empty
  * To set a new current date: Key in the new date
  
```
date
____________________________________________________
Current session date is: 21/10/2021
____________________________________________________
```
```
date 2022-01-01
____________________________________________________
The current session date has been changed to 01 01 2022
____________________________________________________
```

### 2.11. Exit Program

Exit the program once inventory check is completed.

Command: `exit`
```
exit
____________________________________________________
Okay, see you soon! Goodbye.
____________________________________________________
```

## 3. FAQ

**Q**: How do I transfer my data to another computer?

**A**: {your answer here}

## Command Summary

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
