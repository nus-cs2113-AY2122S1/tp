# Food-O-Rama User Guide

## Introduction

Food-O-Rama is a food wastage tracking application for restaurant owners and hawkers to 
keep track of their food resources. It provides the user with insight on the amount of ingredients 
in their storage and how much food is wasted. This allows the user to better plan their 
purchase of raw ingredients and cooking of dishes which ultimately saves cost.

* [Quick Start](#quick-start)
* [List of Commands](#list-of-commands)
* [Features](#features)

## Quick Start
1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Food-O-Rama` from [here](http://link.to/duke).
3. Copy 'Food-O-Rama.jar' into an empty folder.
4. Open command promppt, nagivate to where 'Food-O-Rama.jar' is located. e.g. cd [file path]
5. Launch 'Food-O-Rama' using 'java -jar Food-O-Rama.jar'.
6. Enter commands to use Food-O-Rama

Demo:

```
java -jar Food-O-Rama.jar

################################################################################################################
#  ________  ______    ______   _______            ______           _______    ______   __       __   ______   #
# /        |/      \  /      \ /       \          /      \         /       \  /      \ /  \     /  | /      \  #
# $$$$$$$$//$$$$$$  |/$$$$$$  |$$$$$$$  |        /$$$$$$  |        $$$$$$$  |/$$$$$$  |$$  \   /$$ |/$$$$$$  | #
# $$ |__   $$ |  $$ |$$ |  $$ |$$ |  $$ | ______ $$$  \$$ | ______ $$ |__$$ |$$ |__$$ |$$$  \ /$$$ |$$ |__$$ | #
# $$    |  $$ |  $$ |$$ |  $$ |$$ |  $$ |/      |$$$$  $$ |/      |$$    $$< $$    $$ |$$$$  /$$$$ |$$    $$ | #
# $$$$$/   $$ |  $$ |$$ |  $$ |$$ |  $$ |$$$$$$/ $$ $$ $$ |$$$$$$/ $$$$$$$  |$$$$$$$$ |$$ $$ $$/$$ |$$$$$$$$ | #
# $$ |     $$ \__$$ |$$ \__$$ |$$ |__$$ |        $$ \$$$$ |        $$ |  $$ |$$ |  $$ |$$ |$$$/ $$ |$$ |  $$ | #
# $$ |     $$    $$/ $$    $$/ $$    $$/         $$   $$$/         $$ |  $$ |$$ |  $$ |$$ | $/  $$ |$$ |  $$ | #
# $$/       $$$$$$/   $$$$$$/  $$$$$$$/           $$$$$$/          $$/   $$/ $$/   $$/ $$/      $$/ $$/   $$/  #
################################################################################################################

Hello, welcome to FOOD-O-RAMA! The number one solution for food waste management.
Type a command to get us started, or type help for a list of commands!
____________________________________________________________
```

## List of Commands

| Function | Command  |
|---|---|
|Add a new Dish   | add dish [dishName]   |
|Add the Dish Wastage | add dish waste [dishName] followed by [weight in KG]        |
|Delete a Dish| del [dishName] |
|View all Dishes|    list dish|
|Add a new Ingredient| add ingr [ingrName]  followed by [weight in KG] |
|Add storage to an existing Ingredient| add ingr stored [ingrName] followed by [weight in KG] |
|Add Ingredient Wastage| add ingr waste [ingrName] followed by [weight in KG]|
|Link an Ingredient to a Dish| add dish constituent [dishName] / [ingrName]|
|Delete an Ingredient | del [ingrName]|
|View all Ingredients| list ing|
|View the list of commands| help|
|Exit Food-O-Rama| bye|

## Features 



### Adding a todo: `todo`1
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### Adding a todo: `todo`2
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.

Example of usage:

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### Adding a todo: `todo`3
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.

Example of usage:

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### Adding a todo: `todo`4
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.

Example of usage:

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### Adding a todo: `todo`5
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.

Example of usage:

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### Adding a todo: `todo`6
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.

Example of usage:

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
