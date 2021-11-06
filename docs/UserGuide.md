# User Guide
## Contents
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
  - [Display food menu](#display-food-menu-menu)
  - [Adding orders](#adding-orders-add)
  - [Deleting orders](#deleting-orders-delete)
  - [Edit current orders](#edit-current-orders-edit)
  - [Finding person](#finding-person-find)
  - [List current orders](#list-current-orders-list)
  - [Exiting LOTS](#exiting-lots-bye)
  - [Storing data](#storing-data)
- [Command Summary](#command-summary)

## Introduction

Large Order Tracking System (LOTS) is a Command Line (CLI) program that enables users to keep track of multiple food 
orders from a pre-set list of food items. The program helps users to collate the orders and 
displays a summary of all the orders along with other information such as an individual person’s order and the 
corresponding cost, total cost of all the orders and more. As LOTS is a CLI program, this would 
greatly benefit any user that excels in typing.

Target users includes:
1. Dinners
2. Order collators (Middleman)

---

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `LOTS` from [here](https://github.com/AY2122S1-CS2113-T13-2/tp/releases).
3. Navigate to the folder where `LOTS` is located using terminal.
4. Start the `LOTS` program by executing `java -jar CS2113TP.jar` in the terminal.
5. You can start using the `LOTS` program by inputting commands into the command line. The list of commands and 
instructions on how to use them can be found in the following section.

> :exclamation: **Caution**: When entering commands, if the command does not include any additional parameters, please 
> ensure that no extra inputs are present after the command. Strictly keep to the command format.

---

## Features

### Display food menu: `menu`

Lists the menu along with the index, food name and price. 

**Format:** `menu`  

**Note:**
1. Do not add any inputs before or after `menu` command.
   
**Example of usage:** 
 
  
![Menu Screenshot](https://raw.githubusercontent.com/thaddyyz/tp/master/UMLdiagrams/EditCommandDiagrams/menuUDDiagram.png)

### Adding orders: `add`

Adds a specific order for a particular person.

**Format:** `add /n [PERSON_NAME] /i [FOOD_INDEX] /q [QUANTITY]`

**Example:** `add /n Jeremy /i 2 /q 23`

* `[PERSON_NAME]` refers to the person's name who ordered this set of food.
  * The length of `[PERSON_NAME]` must be between **1 character to 51 characters** including spaces.
  * `[PERSON_NAME]` can only be in **alphanumeric**.
* `[FOOD_INDEX]` refers to the index of the food in the menu.
  * To find out which `[FOOD_INDEX]` correspond to which food, hit the `menu` command.
  * Only accept **integers** 1,2,3...
* `[QUANTITY]` refers to the quantity of this particular order.
  * `[QUANTITY]` only accepts **integers**.
  * Range of `[QUANTITY]` is from **1 to 999**.

**Note:**
1. `[PERSON_NAME]` is **individualised**. 
   * You can only use **1 name** as a reference to **1 person**.
   * Any additional `add` command with the same name will be tagged under the same person's order.
2. Adding the same order to a person with different quantity will result to **increment of the original quantity** of the order.
   * E.g. Refer to **Example of usage**.

**Example of usage:**
1. Start with empty list. 
2. Adds a new order under person named Jeremy, ordering 1 Plain Prata. 
   * `add /n Jeremy /i 1 /q 1`
  
![Add1 Screenshot](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/docs/UG%20Images/UG_AddCommand_Example1.png)

### Deleting orders: `delete`   

Deletes a specific order from a particular person. 

**Format:** `delete [PERSON_INDEX]/[FOOD_INDEX]`  
* Deletes the specific order of `FOOD_INDEX`  from the person of `PERSON_INDEX`.
* The `PERSON_INDEX` refers to the index number of a particular person shown in the displayed order list.
* The `FOOD_INDEX` refers to the index number of a specific order shown in the displayed order list.
* `PERSON_INDEX` & `FOOD_INDEX` **must be a positive integer** 1, 2, 3, …  

**Example of usage:** 
* `list` followed by `delete 1/2` deletes the order of index '`2`' from the person of index '`1`'.    
  
![Delete Screenshot](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/docs/UG%20Images/UG_DeleteCommand_Example.png)

### Edit current orders: `edit`

Edits the quantity of a specific order from a particular person. 

**Format:** `edit [PERSON_INDEX]/[FOOD_INDEX] /q [QUANTITY]`  
* Edits the quantity of the specific order of `FOOD_INDEX`  from the person of `PERSON_INDEX`.
* The `PERSON_INDEX` refers to the index number of a particular person shown in the displayed order list.
* The `FOOD_INDEX` refers to the index number of a specific order shown in the displayed order list.
* The `Quantity` refers to the quantity of the particular order to be changed to.
* `PERSON_INDEX` & `FOOD_INDEX` **must be a positive integer** 1, 2, 3, … 
* `QUANTITY` **must be a non-negative integer** 0, 1, 2, 3, …

**Example of usage:** 
* `list` followed by `edit 1/2 /q 5` edits the order of index '`2`' from the person of index '`1`' and changes the quantity to '`5`'.    
  
![Edit Screenshot](https://raw.githubusercontent.com/thaddyyz/tp/master/UMLdiagrams/EditCommandDiagrams/editUGDiagram.png)

### Finding person: `find`

Find persons with names that contain a given string and print out their orders.

**Format:** `find /n [SEARCH_STRING]`

- `SEARCH_STRING` specifies the sub-string that you wish to search.
  - `SEARCH_STRING` must contain **at least 1** `alphanumeric` character. E.g) `a`,`1`.
- The search is **case-insensitive**. Meaning that `ABcd = abcd = ABCD`.
- The `SEARCH_STRING` does not need to contain the exact name you wish to match.
**Any** sub-string of the name will be considered a match.
    - E.g) `find /n drew` will return the name `andrew` as a match.
    
**Example of usage**
- Assume our current list contains 3 people named `TOM`, `KIM` & `KIMMY` as seen below.
- The command `find /n kim` will return both `KIM` & `KIMMY` as both names contain the string `"KIM"`.

![Find Image](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/docs/UG%20Images/FindList.jpg)



### List current orders: `list`

Shows a list of all the orders that have been made.

**Format:** `list`

**Note:**
- Input **must not** contain any extra characters before or after `list`.

**Example of usage**
- Assume that the current stored order contains orders made by `Jerry`, `Lewis` & `Smith`.
- The `list` command will display everyone's orders, and a summary of the total number of people who have ordered and
the total quantity of food ordered.

![List Image](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/docs/UG%20Images/OrdersList.JPG)

### Exiting LOTS: `bye`

Exits the LOTS program.

**Format:** `bye`

**Example of usage**

![Bye Image](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/docs/UG%20Images/ByeExample.JPG)

### Storing data

- Data such as user orders are saved automatically after every command.
- The data are stored in a text file called `.orders.txt` located in the same directory as the main JAR file.
- If the `.orders.txt` file is not found upon initial startup, LOTS will automatically create a new empty `.orders.txt` file.
- As such, there is no need for users to manually save the data while using LOTS.  

> :exclamation: **Caution**: Users should not edit the `.orders.txt` file as it may corrupt the data stored.

---

## Command Summary

|Actions|Format & Example|
|:-----:|:---------------|
|Add|Format: `add /n [PERSON_NAME] /i [FOOD_INDEX] /q [QUANTITY]` <br>Example: `add /n Andrew /i 8 /q 2` (Adds 2 orders of item number 8 for Andrew)|
|Delete|Format: `delete [PERSON_INDEX]/[FOOD_INDEX]`<br>Example: `delete 1/2` (Deletes the order of index ‘2’ from the person of index ‘1’.)|
|Edit|Format: `edit [PERSON_INDEX]/[FOOD_INDEX] /q [QUANTITY]` <br>Example: `edit 3/1 /q 8` (Changes the quantity of the order with index '1' from the person of index '3' to 8.)|
|Find|Format: `find /n [SEARCH_STRING]` <br>Example:`find /n drew` (Searches for names containing 'drew'.)|
|Menu|Example: `menu`|
|List|Example: `list`|
|Bye|Example: `bye`|

