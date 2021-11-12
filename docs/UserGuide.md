# User Guide

<hr/>

## Introduction

![image info](./umlDiagrams/smallerlogo.png)

Fridget is a <strong> desktop app built to be used via Command Line Interface </strong> (CLI). Fridget is an easier way for you to keep track of the items in your fridge. It makes it convenient for you to add, remove, and find items stored in your fridge. Fridget also reminds you when items are nearing expiry.

![image info](./umlDiagrams/Ui.png)

### Who Is Fridget Meant For?

If you want to start taking charge of your fridge and stop wasting your food, Fridget is for you.

- Forget scribbling your items on a piece of paper. 
- Forget throwing your food away when it goes bad.
- Forget expensive smart fridges that are more trouble than they are worth.

Use Fridget today.

### Purpose Of This Guide

This guide is meant for you to quickly get started with Fridget, so you can stop worrying about expired food and start enjoying life.

You can use the [Index](#index) below to navigate through different sections in the user guide.

Do start at the [Legend](#legend) to get an overview of the icons and terminology we use throughout the document.

If you want to immediately get started with Fridget, just follow the instructions in the [Quick Start](#quick-start) section. Afterwards, take a look at the [Features](#features) section to learn about all that you can do with Fridget.

We highly recommend you to refer to the [Command Syntax](#command-syntax) and [Things To Note](#things-to-note) for a better understanding of the properties underlying Fridget and its features.

<hr/>

## Index

1. [Legend](#legend)
2. [Quick Start](#quick-start)
3. [Features](#features)
4. [Command Syntax](#command-syntax)
5. [Things to Note](#things-to-note)

## Legend

`add ITEM_NAME /EXPIRY_DATE` - Texts inside boxes like `this` refers to either input text by the user or output from Fridget in the terminal.
It could also refer to file names like `Fridget.jar`.

`ITEM_NAME` or `EXPIRY_DATE` - Texts which have been capitalized refer to parameters which the user should type in. For example:

A command like `add ITEM_NAME /EXPIRY_DATE` would actually be typed by the user as `add burger /2021-11-11`.

:bulb: - This icon refers to tips that can help you make the most out of Fridget.

:exclamation: - This icon refers to warnings to help you avoid mistakes when using Fridget.

<hr/>

## Quick Start

1. Ensure that you have Java 11 or above installed on your computer.
2. Download the latest `Fridget.jar` from [here](https://github.com/AY2122S1-CS2113T-W12-4/tp/releases/download/v2.1/Fridget.jar).
3. Copy the file into the folder you want to store Fridget's data.
4. Launch the terminal in that same folder on your computer. You can refer to [this website](https://towardsdatascience.com/a-quick-guide-to-using-command-line-terminal-96815b97b955) on how to launch the terminal on your specific OS (Windows/Mac/Linux).
5. Type in `java -jar Fridget.jar` into the terminal to launch the app.
6. Type your command in the command box and press Enter to execute it.
    * Some example commands you can try:
        - help: lists all commands and input format.
        - reset: prompts the user for confirmation to clear all data.
        - exit: closes the app.
7. Congratulations! You should be good to go!

<span style="display: inline-block; background-image: linear-gradient(180deg, #d8eff3, #d1ecf1); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:bulb: You can type <code>java -version</code> into your terminal to check your version of java.
</span>

This guide has been arranged according to the features offered by Fridget.
You can see the list of features in the Features section below.

<hr/>

## Features

Feature | Command Format |
-----------|-----------------
[Add an item](#add-an-item-into-fridget-add) | `add ITEM_NAME /EXPIRY_DATE`
[Remove an item](#remove-an-item-from-fridget-remove) | `remove ITEM_NAME`
[Update the quantity of an item](#update-the-quantity-of-an-item-in-fridget-update) | `update ITEM_NAME`
[Get help](#get-help-help) | `help`
[Reset item list](#reset-all-items-reset) | `reset`
[Reset shopping list](#reset-all-items-in-shopping-list-shopreset) | `shopreset`
[List all items](#list-all-items-in-fridget-list) | `list -OPTIONAL_SORT_TYPE`
[List items in shopping list](#list-items-in-shopping-list-shoplist) | `shoplist`
[Find an item](#find-items-stored-in-fridget-find) | `find KEYWORD`
[List expiring items](#list-all-items-expiring-soon-expiring) | `expiring`  
[See notifications](#see-notifications-notifs) | `notifs`
[Exit Fridget](#stop-fridget-exit) | `exit`

<hr/>

### Command Syntax

As you use this guide, you may see some commands with specific punctuation or parameters. This guide explains what they mean, and how you should use them.

For simplicity's sake, the number `2147483647` shall be referred to henceforth as `INT_MAX`.

Command Option | Description |
-----------|-----------------
`ITEM_NAME` | The `ITEM_NAME` can be any series of characters or digits of less than length `INT_MAX`. Additionally, `ITEM_NAME` should not include the following terms: `;`,`/`, or <code>&#124;</code>. <br/><br/> More explanation can be found [here](#add-an-item-into-fridget-add).
`EXPIRY_DATE` | The `EXPIRY_DATE` must be of the format `YYYY-MM-DD`.
`OPTIONAL_SORT_TYPE` | We recommend that you insert a `-` before `OPTIONAL_SORT_TYPE`. However, we do accommodate inputs without a `-` in front. Additionally, we also accept uppercase inputs.
`KEYWORD` | This `KEYWORD` can be the exact same as the name of the item you are trying to remove. You can also input a `KEYWORD` that is contained within the item you are trying to remove.
`Y|N` | Some commands may prompt you to type a `Y` to signify that you would like to proceed with the command. Do take note that these commands will also take `y` as consent.

<hr/>

### Add an item into Fridget: `add`
Use this command if you want to add your items into Fridget.

Format: `add ITEM_NAME /EXPIRY_DATE`

Steps:
1. Type the item you want to add in the following format: `add ITEM_NAME /EXPIRY_DATE`.
2. Fridget will prompt you for the quantity of the item that you want to add. You can feel free to type in the quantity you prefer. This is illustrated in [Figure 1.1](#figure-11)
3. Do ensure that this quantity is less than `INT_MAX`. The value for`INT_MAX` can be found in the [Command Syntax](#command-syntax).

Constraints:
- Do not use `/`, `|`, or `;` in the `ITEM_NAME`.
  - `/` is used to demarcate the `EXPIRY_DATE`.
  - `|` is used for Fridget's own secret purposes.
  - `;` is used for adding multiple items at once. More instructions can be found below.

<span style="display: inline-block; background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:exclamation: 
The <code>EXPIRY_DATE</code> must be in the <ins>YYYY-MM-DD</ins> format.  
</span>

<span style="display: inline-block; background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:exclamation: 
The <code>EXPIRY_DATE</code> should not be earlier than the current day.  
</span>

**Example of usage:**

<ins>If you are adding an item not in Fridget:</ins>

#### Figure 1.1
```markdown
USER INPUT: add burger /2021-11-11
__________________________________________
What quantity of [burger | 2021-11-11] would you like to add?
__________________________________________
USER INPUT: 1
__________________________________________
You have successfully added:
    burger | Qty: 1 | 11 Nov 2021
__________________________________________
```


<ins>If you are adding an item already in Fridget:</ins>

#### Figure 1.2

```markdown
USER INPUT: add burger /2021-11-11
__________________________________________
What quantity of [burger | 2021-11-11] would you like to add?
__________________________________________
USER INPUT: 9
__________________________________________
You have successfully increased the quantity of:
    burger | Qty: 1->10 | 11 Nov 2021
__________________________________________
```

<br/>
<span style="display: inline-block; background-image: linear-gradient(180deg, #d8eff3, #d1ecf1); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:bulb: For advanced users
</span>


<ins>If you want to add multiple items at the same time, separate each item with a semicolon: `;`.</ins>

**For example:**

#### Figure 1.3

```bash
USER INPUT: add burger /2021-11-11; chicken /2023-11-11
__________________________________________
What quantity of [burger | 2021-11-11] would you like to add?
__________________________________________
USER INPUT: 1
__________________________________________
You have successfully increased the quantity of:
    burger | Qty: 10->11 | 11 Nov 2021
__________________________________________
What quantity of [chicken | 2023-11-11] would you like to add?
__________________________________________
USER INPUT: 1
__________________________________________
You have successfully added:
    chicken | Qty: 1 | 11 Nov 2023
__________________________________________
```

<hr/>

### Remove an item from Fridget: `remove`
Use this command if you want to remove items from Fridget.

Format: `remove ITEM_NAME`

Steps:
1. Type the item you want to remove in the following format: `remove ITEM_NAME`.
2. If there are multiple items with similar names, Fridget will prompt you to pick one by number. Do pick the number that is beside your item of choice. This is illustrated in the [Figure 2.3](#figure-23) below.
3. Afterwards, if there is more than 1 of your chosen item, Fridget will prompt you for the quantity of the item that you want to remove. You can feel free to type in the quantity you prefer. This is illustrated in [Figure 2.2](#figure-22) below.

<span style="display: inline-block; background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:exclamation:
The <code>ITEM_NAME</code> should be the <ins>same</ins> as the name of the item you are trying to remove. 
</span>

<span style="display: inline-block; background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:exclamation:
The <code>ITEM_NAME</code> can also be a <ins>part of the name</ins> of the item. 
</span>

**Example of usage:**

<ins>If there is only one item that exactly matches your `ITEM_NAME`</ins>:

#### Figure 2.1

```markdown
USER INPUT: remove burger
__________________________________________
There are 11 items, how many would like to remove?
__________________________________________
USER INPUT: 1
__________________________________________
You have successfully removed:
    burger | Qty: 1 | 11 Nov 2021
__________________________________________

```

<ins>If there is only one item with a similar spelling (different capitalization / has more letters) to your `ITEM_NAME`</ins>:

#### Figure 2.2

```markdown
USER INPUT: remove Burger
__________________________________________
Did you mean: burger? [Y/N]
__________________________________________
USER INPUT: y
__________________________________________
There are 10 items, how many would like to remove?
__________________________________________
USER INPUT: 1
__________________________________________
You have successfully removed:
burger | Qty: 1 | 11 Nov 2021
__________________________________________
```

<ins>If there are multiple items with similar names to your `ITEM_NAME`</ins>:

#### Figure 2.3

```markdown
USER INPUT: remove burger
__________________________________________
Which item would you like to be removed? Type the index of the item below.
    1. burger | Qty: 1 | 11 Nov 2021
    2. burger | Qty: 1 | 23 Sep 2021
    3. burger a | Qty: 2 | 23 Sep 2022
__________________________________________
USER INPUT: 1
__________________________________________
You have successfully removed:
    burger | Qty: 1 | 11 Nov 2021
__________________________________________
```

<ins>If you remove an item, and there are no items left with the same name in Fridget</ins>:

<span style="display: inline-block; background-image: linear-gradient(180deg, #d8eff3, #d1ecf1); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:bulb: Our shopping list will prompt you to add depleted items to your shopping list.
</span>


#### Figure 2.4

```
USER INPUT: remove burger
__________________________________________
You have successfully removed:
    burger | Qty: 1 | 23 Sep 2021
__________________________________________
You have ran out of burger. Would you like to add it to your shopping list? (Y/N)
__________________________________________
USER INPUT: Y
__________________________________________
How many items would you like to buy?
__________________________________________
USER INPUT: 1
__________________________________________
You have successfully added:
    burger | Qty: 1
__________________________________________
```
<hr/>

### Update the quantity of an item in Fridget: `update`
Use this command if you want to update the quantity of items currently in Fridget.

Format: `update ITEM_NAME`

Steps:
1. Type the item you want to update in the following format: `update ITEM_NAME`.
2. If there are multiple items with similar names, Fridget will prompt you to pick one by number. Do pick the number that is beside your item of choice. This is illustrated in [Figure 3.3](#figure-33) below.
3. Fridget will prompt you for the quantity of the item that you want to update. You can feel free to type in the quantity you prefer. This is illustrated in [Figure 3.1](#figure-31) below.
4. Do ensure that this quantity is less than `INT_MAX`. The value for`INT_MAX` can be found in the [Command Syntax](#command-syntax).

<span style="display: inline-block; background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:exclamation:
The <code>ITEM_NAME</code> should be the <ins>same</ins> as the name of the item you are trying to remove. 
</span>

<span style="display: inline-block; background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:exclamation:
The <code>ITEM_NAME</code> can also be a <ins>part of the name</ins> of the item. 
</span>

**Example of usage:**

<ins>If there is only one item that exactly matches your `ITEM_NAME`:</ins>:

#### Figure 3.1

```markdown
USER INPUT: update burger
__________________________________________
How many of burger do you have left?
__________________________________________
USER INPUT: 5
__________________________________________
Quantity of burger is now 5.
__________________________________________
```

<ins>If there is only one item with a similar spelling (different capitalization / has more letters) to your `ITEM_NAME`:</ins>:

#### Figure 3.2

```markdown
USER INPUT: update Burger
__________________________________________
Did you mean: burger? [Y/N]
__________________________________________
USER INPUT: Y
__________________________________________
How many of burger do you have left?
__________________________________________
USER INPUT: 8
__________________________________________
Quantity of burger is now 8.
__________________________________________
```

<ins>If there are multiple items with similar names to your `ITEM_NAME`</ins>:

#### Figure 3.3

```markdown
USER INPUT: update burger
__________________________________________
Which item would you like to overwrite quantity? Type the index of the item below.
1. cheese burger | Qty: 8 | 11 Nov 2022
2. fish burger | Qty: 80 | 12 Dec 2022
3. fish burger | Qty: 30 | 13 Dec 2023
If you've changed your mind, simply type 'quit'.
__________________________________________
USER INPUT: 2
__________________________________________
How many of fish burger do you have left?
__________________________________________
USER INPUT: 32
__________________________________________
Quantity of burger is now 32.
__________________________________________
```

<ins>If you choose to update the quantity of an item to zero</ins>:

<span style="display: inline-block; background-image: linear-gradient(180deg, #d8eff3, #d1ecf1); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:bulb: Fridget will prompt you to check if you meant to completely remove the item.
</span>

<span style="display: inline-block; background-image: linear-gradient(180deg, #d8eff3, #d1ecf1); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:bulb: Fridget will further prompt you to add depleted items to your shopping list.
</span>

#### Figure 3.4

```
USER INPUT: update burger
__________________________________________
How many of burger do you have left?
__________________________________________
USER INPUT: 0
__________________________________________
You have input "0". This will remove all 32 burger from your list. 
Do you still wish to proceed? [Y/N]
__________________________________________
USER INPUT: Y
__________________________________________
You have successfully removed:
    burger | Qty: 32 | 12 Dec 2022
__________________________________________
You have ran out of burger. Would you like to add it to your shopping list? (Y/N)
__________________________________________
USER INPUT: Y
__________________________________________
How many items would you like to buy?
__________________________________________
USER INPUT: 2
__________________________________________
You have successfully added:
    burger | Qty: 2
__________________________________________
```
<hr/>

### Get help: `help`

Use this command if you want to see the list of commands from within Fridget.

Format: `help`

Steps:
1. Type `help`. A list of commands will be shown. 

**Example of usage:**

#### Figure 4.1

```
USER INPUT: help
__________________________________________
List of commands available:

add ITEM_NAME /EXPIRY_DATE(format: yyyy-mm-dd) [eg. add bacon /2022-11-11]
-> Adds an item and its expiry date to the item list. Input quantity after being prompt.

find KEYWORD [eg. find bacon]
-> Find and print all item associated with keyword.

remove ITEM_NAME [eg. delete bacon]
-> Removes the item from the item list.

list -e
-> List out all the items starting with earliest expiry date.

list -r
-> List out all the items based on the order added.

list
-> List out all the items in alphabetical order.

shoplist
-> List out all the items in the shopping list in alphabetical order.

expiring
-> Prints out items that have expired or are expiring within a week.

update ITEM_NAME [eg. update egg]
-> Prompts a quantity change for the specified item name.

notifs
-> Toggle the notification on or off depending on previous state. Default mode is on.

reset
-> Deletes all the previous item entries from the reader.

shopreset
-> Deletes all the previous item entries in the shopping list.

exit
-> Exits the program.

For more information about each command please visit our User Guide.
Link: https://ay2122s1-cs2113t-w12-4.github.io/tp/UserGuide.html
__________________________________________
```

<hr/>

### Reset all items: `reset`

Use this command if you want to reset all the items in your fridge.

Format: `reset`

Steps:
1. Type `reset`. Fridget will prompt you to confirm that you want to reset all the items in your fridge.
2. Type `Y` if you want to reset everything. Type `N` if you would like to abort.

**Example of usage:**

<ins>If you decide to not reset in the end:</ins>

#### Figure 5.1

```
USER INPUT: reset
__________________________________________
Are you sure you want to reset everything in the fridge? (Y/N)
__________________________________________
USER INPUT: N
__________________________________________
Shutting down the command...
__________________________________________
```

<ins>If you are sure you want to reset:</ins>

#### Figure 5.2

```
USER INPUT: reset
__________________________________________
Are you sure you want to reset everything in the fridge? (Y/N)
__________________________________________
USER INPUT: Y
__________________________________________
Item list has been reset successfully.
__________________________________________
```

<hr/>

### Reset all items in shopping list: `shopreset`

Use this command if you want to reset all the items in your shopping list.

Format: `shopreset`

Steps:
1. Type `shopreset`. Fridget will prompt you to confirm that you want to reset all the items in your shopping list.
2. Type `Y` if you want to reset everything. Type `N` if you would like to abort.

**Example of usage:**

<ins>If you decide to not reset in the end:</ins>

#### Figure 6.1

```
USER INPUT: shopreset
__________________________________________
Are you sure you want to reset everything in the shopping list? (Y/N)
__________________________________________
USER INPUT: N
__________________________________________
Shutting down the command...
__________________________________________
```

<ins>If you are sure you want to reset:</ins>

#### Figure 6.2

```
USER INPUT: shopreset
__________________________________________
Are you sure you want to reset everything in the shopping list? (Y/N)
__________________________________________
USER INPUT: Y
__________________________________________
Shopping list has been reset successfully.
__________________________________________
```

<hr/>

### List all items in Fridget: `list`

Use this command if you want to list all items in Fridget, in the order that you prefer.

Format: `list -OPTIONAL_SORT_TYPE`

Steps:
1. Type `list` to list all items in Fridget. You can optionally choose to add an ` -OPTIONAL_SORT_TYPE` to decide how the list should be sorted. More details can be found below.

* `list` Lists items in alphabetical order.
* `list -e` Lists items in ascending order of expiry dates.
* `list -r` Lists items in order of how recently it was added.

**Example of usage:**

<ins> If you want to list items in alphabetical order: </ins>

#### Figure 7.1

```markdown
USER INPUT: list
__________________________________________
List sorted by item name:
    1. fish Cake | Qty: 2 | 12 Dec 2020
    2. frozen duck | Qty: 1 | 10 Oct 2022
    3. Yoghurt cake | Qty: 1 | 15 Oct 2021
__________________________________________
```

<ins> If you want to list items in ascending order of expiry dates: </ins>

#### Figure 7.2

```
USER INPUT: list -e
__________________________________________
List sorted by expiry date:
    1. fish Cake | Qty: 2 | 12 Dec 2020
    2. Yoghurt cake | Qty: 1 | 15 Oct 2021
    3. frozen duck | Qty: 1 | 10 Oct 2022
__________________________________________
```

<ins> If you want to list items in order of how recently they were added: </ins>

#### Figure 7.3

```
USER INPUT: list -r
__________________________________________
List sorted by earliest added:
    1. fish Cake | Qty: 2 | 12 Dec 2020
    2. Yoghurt cake | Qty: 1 | 15 Oct 2021
    3. frozen duck | Qty: 1 | 10 Oct 2022
__________________________________________
```

<hr/>

### List items in shopping list: `shoplist`

Use this command if you want to list all the items in your shopping list.

Format: `shoplist`

Steps:
1. Type `shoplist` to list all items in your shopping list.

**Example of usage:**

#### Figure 8.1

```
USER INPUT: shoplist
__________________________________________
List sorted by item name:
    1. Burger | Qty: 1
    2. Chicken | Qty: 3
    3. Fish | Qty: 2
__________________________________________
```

<hr/>

### Find items stored in Fridget: `find`

Use this command if you want to easily find an item in Fridget by name.

Format: `find KEYWORD`

Steps:
1. Type `find KEYWORD` into Fridget to get all item(s) in Fridget that match your chosen `KEYWORD`.

<span style="display: inline-block; background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:exclamation:
The <code>KEYWORD</code> should be the <ins>same</ins> as the name of the item you are trying to remove. 
</span>

<span style="display: inline-block; background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:exclamation:
The <code>KEYWORD</code> can also be a <ins>part of the name</ins> of the item. 
</span>

**Example of usage:**

#### Figure 9.1

```
USER INPUT: find burger
__________________________________________
These are the matching items:
    1. burger | Qty: 1 | 23 Sep 2030
__________________________________________
USER INPUT: find apple
__________________________________________
No matching item found!
__________________________________________
```

<hr/>

### List all items expiring soon: `expiring`

Use this command if you want to get a list of all expiring or expired items in Fridget.

<span style="display: inline-block; background-image: linear-gradient(180deg, #d8eff3, #d1ecf1); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:bulb:
Only items expiring within 7 days will be shown. Expired items will be shown too.
</span>

Format: `expiring`

Steps:
1. Type `expiring`. Fridget will display a list of all expiring or expired items.

**Example of usage:**

#### Figure 10.1

```markdown
USER INPUT: expiring
__________________________________________
Expiring/Expired Items:
    1. fish Cake | Qty: 2 | 12 Dec 2020
    2. Yoghurt cake | Qty: 1 | 15 Oct 2021
__________________________________________
```

<hr/>

### See notifications: `notifs`

Use this command if you want to toggle notifications on or off. There are a total of 2 reminders, health and expiry.

* Health: To remind you to eat healthier. You can see it in action in [Figure 11.2](#figure-112)
* Expiry: To remind you that items are expiring and/or expired. You can see it in action in [Figure 11.3](#figure-113)

<span style="display: inline-block; background-image: linear-gradient(180deg, #d8eff3, #d1ecf1); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:bulb:
Notifications are scheduled by default to be displayed every 4 hours, if turned on.
</span>

Format: notifs

Steps:
1. Type `notifs`. This will toggle the notifications in Fridget on or off. Fridget will display the current status of the notifications after your command.

**Example of usage:**

#### Figure 11.1

````
USER INPUT: notifs
__________________________________________
Turning notification off!
__________________________________________
USER INPUT: notifs
__________________________________________
Turning notification on!
__________________________________________
````

**Example of health reminder notice:**

#### Figure 11.2

````
 ___________________________________________________
|                      HEALTH!                      |
|===================================================|
|      ___            _         __        ______    |
|     / _ \___ __ _  (_)__  ___/ /__ ____/ / / /    |
|    / , _/ -_)  ' \/ / _ \/ _  / -_) __/_/_/_/     |
|   /_/|_|\__/_/_/_/_/_//_/\_,_/\__/_/ (_|_|_)      |
|   ---------------------------------------------   |
|                                                   |
|    "If you don't take time to take care of your   |
|    health now, you're gonna have to make time     |
|    for feeling sick and tired later."             |
|                                                   |
|            Always remember to eat more            |
|              Fruits and Vegetables!               |
|___________________________________________________|
````
**Example of expiry reminder notice:**

#### Figure 11.3

````
__________________________________________
 ___________________________________________________
|                      EXPIRY!                      |
|===================================================|
|      ___            _         __        ______    |
|     / _ \___ __ _  (_)__  ___/ /__ ____/ / / /    |
|    / , _/ -_)  ' \/ / _ \/ _  / -_) __/_/_/_/     |
|   /_/|_|\__/_/_/_/_/_//_/\_,_/\__/_/ (_|_|_)      |
|___________________________________________________|
 >> Items expired! Please discard ASAP:
     1. apple | Qty: 1 | 01 Nov 2021
     2. chicken | Qty: 1 | 03 Nov 2021
 --------------------------------------------------- 
 >> Please use them before they expire:
     1. bacon | Qty: 1 | 11 Nov 2021
|___________________________________________________|
__________________________________________
````

<hr/>

### Stop Fridget: `exit`

Use this command when you are done using Fridget. Fridget will help you to remember everything you did.

Steps:
1. Type `exit`. Fridget will display a shutdown message and save all data from your current session. You can see the above-mentioned in [Figure 12.1](#figure-121)

Format: `exit`

**Example of usage:**

#### Figure 12.1

```markdown
USER INPUT: exit
__________________________________________
We'll help you remember everything you told us :)
See you again!~~
__________________________________________
```

<hr/>

## Things to Note

<span style="display: inline-block; background-image: linear-gradient(180deg, #d8eff3, #d1ecf1); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:bulb: Details about how the shopping list works
</span>

- You can only add items into the shopping list when the item you remove has run out completely (there are no more items with a similar name (different capitalisation) in the item list).
  - You can find out how to add items into the shopping list in [Figure 2.4](#figure-24) or [Figure 3.4](#figure-34).

- Removing items from the shopping list is done automatically by Fridget when:
  - An item that is in the shopping list is added into the item list.  
  - The quantity of an item in the shopping list is increased with the update command.
    

- We currently do not support manual addition or removal of items from the shopping list. Instead, it is all done automatically by Fridget.

- You may copy or print out the `savedShop.txt` file stored in the `fridgetData` directory when you need to go shopping.

<hr/>

<span style="display: inline-block; background-image: linear-gradient(180deg, #d8eff3, #d1ecf1); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:bulb: For commands <code>help</code>, <code>expiring</code>, <code>reset</code>, <code>shopreset</code>, <code>shoplist</code>, <code>list</code>, and <code>notifs</code>, adding a space and random text behind will still trigger the command.
</span>

<ins>Examples:</ins>

#### Figure 13.1

```
USER INPUT: notifs ajsdfasf
__________________________________________
Turning notification off!
____________________________________________
USER INPUT: reset jadfgasgdka
__________________________________________
Are you sure you want to reset everything in the item list? (Y/N)
__________________________________________
```

<hr/>

<span style="display: inline-block; background-image: linear-gradient(180deg, #d8eff3, #d1ecf1); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:bulb: If no item is recorded, Fridget will prompt you to use the <code>help</code> command.
</span>

#### Figure 13.2

```
USER INPUT: find a
__________________________________________
You currently have nothing in your fridge.
Input "help" to get started!
__________________________________________
USER INPUT: expiring
__________________________________________
You currently have nothing in your fridge.
Input "help" to get started!
__________________________________________
```

<hr/>

<span style="display: inline-block; background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:exclamation: All numerical inputs are limited between <code>0</code> to <code>2147483647</code>. Numbers outside this range are invalid inputs.
</span>

This is done to reduce the memory that Fridget uses on your computer.

<hr/>

<span style="display: inline-block; background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:exclamation: When adding multiple items, any error will invalidate the entire input. No items will be added.
</span>

This is done to ensure no incorrect items are added into Fridget accidentally. In your input is wrong, you can copy the previous input, fix the errors, and run the command again.

<hr/>

<span style="display: inline-block; background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:exclamation: Do not tamper with the files created in the <code>fridgetData</code> directory.
</span>

Please feel free to read and copy the content from the files. However, do not edit the files, as this may cause the program to load erroneously. If any item is detected to be corrupted, all items following it in savedItem.txt will be removed for the user's safety.

<hr/>

# Thank You

We hope you found this user guide useful to help you push Fridget to its limits! 

If you find any issues, do let us know [here](https://github.com/AY2122S1-CS2113T-W12-4/tp/issues/new/choose). 


<span style="display: inline-block; background-image: linear-gradient(180deg, #d8eff3, #d1ecf1); padding-top: 1em; padding-bottom: 1em; padding-left: 1em; padding-right: 1em; font-style: normal; border-radius: 1em; border: 2px solid #999">
:bulb:  You will need a GitHub account to access the above link.
</span>


All the best, and have fun!
