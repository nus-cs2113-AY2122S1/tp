# Food-O-Rama User Guide

## 🌑 Introduction

*Food-O-Rama* is a food wastage tracking application for *Restaurant Owners* like you to
**keep track of your food resources**. It provides you with **insight on the amount of ingredients in your storage**
and **track how much food is wasted**. This allows you to better plan your purchase of raw ingredients and enables more
efficient allocation to the cooking of dishes which ultimately saves cost.

*Food-O-Rama* provides a variety of features that allow for food resource tracking. You can **add**, **delete**,
**list** and **find** different dishes and ingredients. What's more, you can also show **graphs** for your statistical
wastage, add an **expiry date** to ingredients and even **link** your ingredients to your dishes for advanced management!

This user guide serves to help you manoeuvre around **Food-O-Rama**, which the **[Table of Contents](#-table-of-contents)** will be of big help
to you, and includes a **[List of Commands](#-list-of-commands)** for your quick reference. We hope you enjoy using *Food-O-Rama*!

| Legend |  Description |
| --- | --- |
| 💡 *Note*  | Lightbulb requires your attention. |
| ❕ *Tip*  | Exclamation mark gives additional information to use Food-O-Rama more effectively.

## 🧾 Table of Contents

* [Quick Start](#-quick-start)
* [List of Commands](#-list-of-commands)
* [Features](#-features)
    * `help`: [List all commands](#list-all-commands--help)
    * [Dish Commands](#add-a-new-dish--add-dish)
        * `add dish`: [Add new dish](#add-a-new-dish--add-dish)
        * `add dish waste`: [Add dish wastage](#add-a-dishs-wastage--add-dish-waste)
        * `set dish limit`: [Set a limit for dish wastage](#set-a-limit-for-dish-wastage-set-dish-limit)
        * `edit dish name`: [Edit a dish's name](#edit-a-dishs-name--edit-dish-name)
        * `edit dish waste`: [Edit a dish's wastage](#edit-a-dishs-wastage--edit-dish-waste)
        * `del dish`: [Delete existing dish](#delete-existing-dish--del-dish)
        * `list dish`: [View existing dishes](#view-existing-dishes--list-dish)
        * `rdish`: [Generate a random dish name](#generate-a-random-dish-name--rdish)
    * [Ingredient Commands](#add-new-ingredient--add-ingr)
        * `add ingr`: [Add new ingredient](#add-new-ingredient--add-ingr)
        * `add ingr stored`: [Add storage to existing ingredient](#add-storage-to-existing-ingredient--add-ingr-stored)
        * `add ingr waste`: [Add an ingredient's wastage](#add-an-ingredients-wastage--add-ingr-waste)
        * `set ingr limit`: [Set a limit for ingredient wastage](#set-a-limit-for-an-ingredients-wastage--set-ingr-limit)
        * `set ingr expiry`: [Set an expiry date for an ingredient](#set-an-expiry-date-for-an-ingredient--set-ingr-expiry)
        * `edit ingr name`: [Edit an ingredient's name](#edit-an-ingredients-name--edit-ingr-name)
        * `edit ingr stored` [Edit an ingredient's storage](#edit-an-ingredients-storage--edit-ingr-stored)
        * `edit ingr waste`: [Edit an ingredient's wastage](#edit-an-ingredients-wastage--edit-ingr-waste)
        * `link`: [Link ingredient to dish](#link-ingredient-to-dish--link)
        * `del ingr`: [Delete existing ingredient](#delete-existing-ingredient--del-ingr)
        * `list ingr`: [View existing ingredients](#view-existing-ingredients--list-ingr)
    * [Graph Commands](#display-graph-of-dish-wastage--graph-dish)
        * `graph dish`: [Display graph of dish wastage](#display-graph-of-dish-wastage--graph-dish)
        * `graph ingr`: [Display graph of ingredient wastage](#display-a-graph-of-ingredient-wastage--graph-ingr)
    * [Find Commands](#find-dishes-with-matching-keyword--find-dish)
        * `find dish`: [Find dishes with matching keyword](#find-dishes-with-matching-keyword--find-dish)
        * `find ingr`: [Find ingredients with matching keyword](#find-ingredients-with-matching-keyword--find-ingr)
    * [Clear Commands](#remove-all-dishes--clear-dish)
        * `clear dish`: [Remove all dishes](#remove-all-dishes--clear-dish)
        * `clear ingr`: [Remove all ingredients](#remove-all-ingredients--clear-ingr)
        * `clear all`: [Remove all dishes & ingredients](#remove-all-dishes-and-ingredients--clear-all)
    * [Sort Commands](#sort-dishes-by-amount-of-wastage--sort-dish)
        * `sort dish`: [Sort dishes by amount of wastage](#sort-dishes-by-amount-of-wastage--sort-dish)
        * `sort ingr`: [Sort ingredients by amount of wastage](#sort-ingredients-by-amount-of-wastage--sort-ingr)
    * `bye`: [Exit Food-O-Rama](#exit-food-o-rama--bye)
    * [Saving the data](#saving-the-data)
    * [Accessing the data](#accessing-the-data)
    * [Manipulating the data](#manipulating-the-data)
* [FAQ](#-faq)
* [Final Notes](#-final-notes)

## 📖 Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Food-O-Rama.jar`
   from [here](https://github.com/AY2122S1-CS2113T-W11-4/tp/releases).
3. Copy `Food-O-Rama.jar` into an empty folder.
4. Open command prompt, navigate to where `Food-O-Rama.jar` is located. e.g. `cd [FILE_PATH]`.
5. Launch 'Food-O-Rama' using `java -jar Food-O-Rama.jar`.
6. Enter commands to use Food-O-Rama.

Your screen will look like this when you start *Food-O-Rama*!

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
#             Hello, welcome to FOOD-O-RAMA! The number one solution for food waste management.                #
#                  Type a command to get us started, or type help for a list of commands!                      #
################################################################################################################
```

Congratulations! Now, the **List of Commands** below will teach you how to use all the *commands in Food-O-Rama*.

## 💽 List of Commands

| 🏁 Function | ✔ Command | 
|---|---|
| View the list of commands | `help` |
| Add new *dish* | `add dish [DISH_NAME]` |
| Add the *dish wastage* | `add dish waste [DISH_NAME]` |
| Set a limit for *dish's wastage* | `set dish limit [DISH_NAME]` |
| Edit a *dish's name* | `edit dish name [DISH_NAME]` |
| Edit a *dish's wastage* | `edit dish waste [DISH_NAME]` |
| Delete existing *dish* | `del dish [DISH_NAME]` |
| View existing *dishes* | `list dish` |
| Generate a random *dish name* | `rdish` |
| Add a new *ingredient* | `add ingr [INGR_NAME]` |
| Add storage to an existing *ingredient* | `add ingr stored [INGR_NAME]` |
| Add *ingredient wastage* | `add ingr waste [INGR_NAME]` |
| Set a *limit* for an ingredient's wastage | `set ingr limit [INGR_NAME]` |
| Set an *expiry date* an ingredient | `set ingr expiry [INGR_NAME]` |
| Edit an *ingredient's name* | `edit ingr name [INGR_NAME]` |
| Edit an *ingredient's storage* | `edit ingr stored [INGR_NAME]` |
| Edit an *ingredient's wastage* | `edit ingr waste [INGR_NAME]` |
| Link *dish* to an *ingredient* | `link [DISH_NAME] / [INGR_NAME]` |
| Delete existing *ingredient* | `del [INGR_NAME]` |
| View existing *ingredients* | `list ingr` |
| Display graph of *dishes wastage* | `graph dish` |
| Display graph for *ingredients wastage* | `graph ingr` |
| Find a *dish* | `find dish [KEYWORD]` |
| Find an *ingredient* | `find ingr [KEYWORD]` | 
| Remove all *dishes* | `clear dish` |
| Remove all *ingredients* | `clear ingr` |
| Remove all *ingredients & dishes* | `clear all` |
| Sort the *dishes* by *amount of wastage* | `sort dish` |
| Sort the *ingredients* by *amount of wastage* | `sort ingr` |
| Exit *Food-O-Rama* | `bye` |

## 👍 Features

> 💡 **Command Format Notes**:
> * Words in `UPPER_CASE` are to be supplied by the user.  
> e.g. `add dish [DISH_NAME]`, where `[DISH_NAME]` is a parameter which can be used as `add dish chicken rice`.
> * All text supplied by the user are automatically converted into lowercase format.

<div style="page-break-after: always;"></div>

### List all commands : `help`

Display the list of commands that Food-O-Rama utilises.

The list teaches you how to use all commands available in *Food-O-Rama*.

Format: `help`

* 💡 *Note*: `help` does not require additional parameters.

Example of usage:

`help`

Expected Outcome:

```
____________________________________________________________
Here are the commands you can use:
Dish Commands:
  1. Adding a Dish : 'add dish [DISH_NAME]'
     Example: 'add dish chicken rice'
  2. Adding Dish Wastage : 'add dish waste [DISH_NAME/INDEX]'. Type the [DISH_WASTE_WEIGHT in KG] when prompted
     Example: 'add dish waste chicken rice' | '0.8'
  3. Setting Dish Limit : 'set dish limit [DISH_NAME/INDEX]'. Type the [DISH_LIMIT_WEIGHT in KG] when prompted
     Example: 'set dish limit chicken rice' | '16.9'
  4. Finding a Dish : 'find dish [keyword]'
     Example: 'find dish chicken rice'
  5. Editing Dish Name : 'edit dish name [DISH_NAME/INDEX]'.
     Type the [NEW_DISH_NAME] and 'y' to confirm and 'n' to disregard the change when prompted
     Example: 'edit dish name chicken rice' | 'hainanese chicken rice' | 'y'
  6. Editing Dish Waste : 'edit dish waste [DISH_NAME/INDEX]'.
     Type the [NEW_DISH_WASTE_WEIGHT in KG] and 'y' to confirm and 'n' to disregard the change when prompted
     Example: 'edit dish waste chicken rice' | '6.5' | 'y'
  7. Deleting a Dish : 'del [DISH_NAME/INDEX]'.
     Type 'y' to confirm and 'n' to disregard the deletion when prompted
     Example: 'del dish chicken rice' | 'y'
  8. Viewing all Dishes : 'list dish'
  9. Sorting Dishes according to Dish Wastage : 'sort dish'
  10. Viewing Graph of Dish Wastage : 'graph dish'
  11. Clearing all Dishes : 'clear dish'
  12. Creating Random Dish : 'rdish'

Ingredient Commands:
  13. Adding a new Ingredient : 'add ingr [INGR_NAME]' Type the [INGR_STORAGE_WEIGHT in KG] when prompted
      Example: 'add ingr chicken' | '2'
  14. Adding storage to an already existing Ingredient : 'add ingr stored [INGR_NAME/INDEX]'. Type the [INGR_STORAGE_WEIGHT in KG] when prompted
      Example: 'add ingr stored chicken' | '1.5'
  15. Adding Ingredient Wastage : 'add ingr waste [INGR_NAME/INDEX]'. Type the [INGR_WASTE_WEIGHT in KG] when prompted
      Example: 'add ingr waste chicken' | '0.7'
  16. Setting Ingredient Limit : 'set ingr limit [INGR_NAME/INDEX]'. Type the [INGR_LIMIT_WEIGHT in KG] when prompted
      Example: 'set ingr limit chicken' | '42.7'
  17. Setting Ingredient Expiry : 'set ingr expiry [INGR_NAME/INDEX]'. Type the [INGR_EXPIRY_DATE in dd/mm/yyyy Format] when prompted
      Example: 'set ingr limit chicken' | '42.7'
  18. Linking an Ingredient to a Dish : 'link [DISH_NAME] / [INGR_NAME]'
      Example: 'link chicken rice / chicken'
  19. Finding an Ingredient : 'find ingr [keyword]'
      Example: 'find ingr chicken'
  20. Editing Ingredient Name : 'edit ingr name [INGR_NAME/INDEX]'.
      Type the [NEW_INGR_NAME] and 'y' to confirm and 'n' to disregard the change when prompted
      Example: 'edit ingr name chicken' | 'chicken breast' | 'y'
  21. Editing Ingredient Waste : 'edit ingr waste [INGR_NAME/INDEX]'.
      Type the [NEW_INGR_WASTE_WEIGHT in KG] and 'y' to confirm and 'n' to disregard the change when prompted
      Example: 'edit ingr waste chicken' | '7.0' | 'y'
  22. Deleting an Ingredient : 'del [INGR_NAME/INDEX]'
      Type 'y' to confirm and 'n' to disregard the deletion when prompted
      Example: 'del ingr chicken' | 'y'
  23. Viewing all Ingredients : 'list ingr'
  24. Sorting Ingredients according to Ingredient Wastage : 'sort ingr'
  25. Viewing Graph of Ingredient Wastage : 'graph ingr'
  26. Clearing all Ingredients : 'clear ingr'
  27. Clearing both Dishes and Ingredients : 'clear all'
  28. Viewing this list of commands: help
  29. Exiting the program: bye
____________________________________________________________
```

<br/>

### Add a new dish : `add dish`

Add a new Dish to your Dish list.

*For adding Dishes that you will serve / Dishes on your restaurant's menu to Food-O-Rama*.

Format: `add dish [DISH_NAME]`

Example of usage:

`add dish chicken rice`

Expected Outcome:

```
____________________________________________________________
Dish added to list: chicken rice
____________________________________________________________
```

<br/>

### Add a dish's wastage : `add dish waste`

Incur wastage on an existing dish.

*Tracks the weight of a particular Dish wasted by your Restaurant.*

Format: `add dish waste [DISH_NAME]`

* Can only be performed on existing `DISH_NAME`.
* ❕ *Tip*: You can replace `[DISH_NAME]` with `[DISH_INDEX]` to quickly choose which dish to add wastage to.
* 💡 *Note*:  You will be prompted to enter the weight of `DISH_NAME` wasted in the next step.

Example of usage:

`add dish waste chicken rice` or `add dish waste 1`

Expected Outcome:

```
____________________________________________________________
Please enter the wastage weight of 'chicken rice' in kg:
____________________________________________________________
```

Example of usage:

`2.5`

Expected Outcome:

```
____________________________________________________________
Wastage of 'chicken rice' has been added and is now 2.5 kg!
____________________________________________________________
```

### Set a limit for dish wastage: `set dish limit`

Set a limit for wastage of an existing dish.

*This is so that you will not have to deal with errors arising from having more Dishes wasted than what was prepared
initially*.

Format: `set dish limit [DISH_NAME]`

* Can only be performed on existing `DISH_NAME`.
* ❕ *Tip*: You can replace `[DISH_NAME]` with `[DISH_INDEX]` to quickly choose which dish to set a limit to.
* 💡 *Note*:  You will be prompted to enter the limit to watch out for in the next step.

Example of usage:

`set dish limit chicken rice` or `set dish limit 1`

```
____________________________________________________________
Please enter the limit for 'chicken rice' in kg:
____________________________________________________________
```

Example of usage:

`3.5`

Expected Outcome:

```
____________________________________________________________
The limit for 'chicken rice' is now 3.5 kg!
____________________________________________________________
```

❕ *Tip*:

* When adding new wastage to the Dish exceeds the limit, you will receive a prompt that the current Dish limit has
  exceeded.
* An indicator *(exceeded)* shows the Dish that has exceeded its limit.

Expected outcome:

```
____________________________________________________________
Please enter the wastage weight of 'chicken rice' in kg:
____________________________________________________________
4.5
____________________________________________________________
Wastage of 'chicken rice' has been added and is now 4.5 kg!
____________________________________________________________
____________________________________________________________
Wastage of 'chicken rice' has exceeded the limit!
____________________________________________________________
```

```
____________________________________________________________
Here are the dishes you have:
1. chicken rice
   Wastage: 4.5 kg
   Ingredients Linked: None
   Limit: 3.5 (exceeded)
You can use command 'add' to add new dishes!
____________________________________________________________
```

<br/>

### Edit a dish's name : `edit dish name`

Edit the name of an existing dish.

*This is so that you will be able to make changes to a dish's name when there is an update to the menu or a typo to the
name of the dish*

Format: `edit dish name [DISH_NAME]`

* Can only be performed on existing `DISH_NAME`.
* ❕ *Tip*: You can replace `[DISH_NAME]` with `[DISH_INDEX]` to quickly choose which dish to edit.
* 💡 *Note*:  You will be prompted to enter the new dish name in the next step, and a confirmation message afterwards.

Example of usage:

`edit dish name chicken rice` or `edit dish name 1`

Expected Outcome:

```
____________________________________________________________
What would you like to change the Dish Name 'chicken rice' to?
____________________________________________________________
```

Example of usage:

`roasted chicken rice`

Expected Outcome:

```
____________________________________________________________
Are you sure you want to change Dish Name from 'chicken rice' to 'roasted chicken rice'?
Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:
____________________________________________________________
```

Example of usage (selecting **yes**):

`y`

Expected Outcome:

```
____________________________________________________________
'chicken rice' has been updated to 'roasted chicken rice'!
____________________________________________________________

```

Example of usage (selecting **no**):

`n`

Expected Outcome:

```
____________________________________________________________
Your previous command is disregarded.
____________________________________________________________
```

<br/>

### Edit a dish's wastage : `edit dish waste`

Edit the wastage of an existing dish.

*This is so that you will be able to make changes to a dish's wastage when resetting or updating a new wastage weight.*

Format: `edit dish waste [DISH_NAME]`

* Can only be performed on existing `DISH_NAME`
* ❕ *Tip*: You can replace `[DISH_NAME]` with `[DISH_INDEX]` to quickly choose which dish to edit.
* 💡 *Note*:  You will be prompted to enter the new dish wastage in the next step, and a confirmation message
  afterwards.

Example of usage:

`edit dish waste roasted chicken rice` or `edit dish waste 1`

Expected Outcome:

```
____________________________________________________________
What would you like to change the wastage weight (in kg) of 'roasted chicken rice' to?
____________________________________________________________
```

Example of usage:

`5`

Expected Outcome:

```
____________________________________________________________
Are you sure you want to change the wastage weight (in kg) from '4.5' to '5.0'?
Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:
____________________________________________________________
```

Example of usage (selecting **yes**):

`y`

Expected Outcome:

```
____________________________________________________________
Wastage of 'roasted chicken rice' has been updated to 5.0 kg!
____________________________________________________________
```

Example of usage (selecting **no**):

`n`

Expected Outcome:

```
____________________________________________________________
Your previous command is disregarded.
____________________________________________________________
```

<br/>

### Delete existing dish : `del dish`

Delete an existing Dish from your Dish list.

*When you remove a Dish off your Restaurant's menu.*

Format: `del dish [DISH_NAME]`

* The `DISH_NAME` has to be existing in the Dish list for it to be deleted.
* ❕ *Tip*: You can replace `[DISH_NAME]` with `[DISH_INDEX]` to quickly choose which dish to delete.
* 💡 *Note*:  You will be prompted with a confirmation message to delete the dish.


Example of usage:

`del dish roasted chicken rice` or `del dish 1`

Expected Outcome:

```
____________________________________________________________
Are you sure you want to remove 'roasted chicken rice'?
Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:
____________________________________________________________
```

Example of usage (selecting **yes**):

`y`

Expected Outcome:

```
____________________________________________________________
'roasted chicken rice' has been removed from your dish list!
____________________________________________________________

```

Example of usage (selecting **no**):

`n`

Expected Outcome:

```
____________________________________________________________
Your previous command is disregarded.
____________________________________________________________
```

<br/>

### View existing dishes : `list dish`

Views all existing Dishes in your Dish list.

❕ *Tip*:  Useful to check all your Dishes available in your list.

Format: `list dish`

* 💡 *Note*: `list dish` does not require additional parameters.

Example of usage:

`list dish`

Expected Outcome:

```
____________________________________________________________
Here are the dishes you have:
1. roasted chicken rice
   Wastage: 0.0 kg
   Ingredients Linked: None
   Limit: No limit has been set
You can use command 'add' to add new dishes!
____________________________________________________________
```

<br/>

### Generate a random dish name : `rdish`

Generates a random Dish name from a fixed list of Carbohydrates, Proteins, Sauces and Cooking Methods.

❕ *Tip*:  Useful to give new Dish ideas for Restaurant's menu.

Format: `rdish`

* 💡 *Note*: `rdish` does not require additional parameters.

Example of usage:

`rdish`

Expected Outcome:

```
____________________________________________________________
Here's an idea for a new Dish!
steamed chili pork noodles
____________________________________________________________
```

<br/>

### Add new ingredient : `add ingr`

Add a new Ingredient to your Ingredient list.

*For adding Ingredients that you have in your Restaurant's inventory to Food-O-Rama*.

Format: `add ingr [INGR_NAME]`

* 💡 *Note*: You will be prompted to enter the weight of `INGR_NAME` in the next step.

Example of usage:

`add ingr chicken`

Expected Outcome:

```
____________________________________________________________
Please enter the storage weight of 'chicken' in kg:
____________________________________________________________
```

Example of usage:

`2`

Expected Outcome:

```
____________________________________________________________
Ingredient added to list: chicken (Storage weight: 2.0 kg)
____________________________________________________________

```

<br/>

### Add storage to existing ingredient : `add ingr stored`

Add more storage to an Ingredient that exists in the Ingredient list by weight.

*For you to add more inventory to the existing Ingredients already available in your Restaurant.*

Format: `add ingr stored [INGR_NAME]`

* Can only be performed on existing `[INGR_NAME]`. 
* ❕ *Tip*: You can replace `[INGR_NAME]` with `[INGR_INDEX]` to quickly choose which ingredient to add storage to.
* 💡 *Note*:  You will be prompted to enter the weight of additional `INGR_NAME` stored in the next step.

Example of usage:

`add ingr stored chicken` or `add ingr stored 1`

Expected Outcome:

```
____________________________________________________________
Please enter the storage weight of 'chicken' in kg:
____________________________________________________________
```

Example of usage:

`5`

Expected Outcome:

```
____________________________________________________________
Storage of 'chicken' has been added and is now 7.0 kg!
____________________________________________________________
```

<br/>

### Add an ingredient's wastage : `add ingr waste`

Incur wastage on an existing Ingredient.

*Tracks the weight of a particular Ingredient wasted by your Restaurant.*

Format: `add ingr waste [INGR_NAME]`

* Can only be performed on existing `[INGR_NAME]`.
* ❕ *Tip*: You can replace `[INGR_NAME]` with `[INGR_INDEX]` to quickly choose which ingredient to add wastage to.
* 💡 *Note*:  You will be prompted to enter the weight of `[INGR_NAME]` wasted in the next step.

Example of usage:

`add ingr waste chicken` or `add ingr waste 1`

Expected Outcome:

```
____________________________________________________________
Please enter the wastage weight of 'chicken' in kg:
____________________________________________________________
```

Example of usage:

`1.5`

Expected Outcome:

```
____________________________________________________________
Wastage of 'chicken' has been added and is now 1.5 kg!
____________________________________________________________
```

### Set a limit for an ingredient's wastage : `set ingr limit`

Set a limit for wastage of an existing Ingredient.

*This is so that you will not have to deal with errors arising from having more Ingredients wasted than what was stored
initially*.

Format: `set ingr limit [INGR_NAME]`

* Can only be performed on existing `INGR_NAME`.
* ❕ *Tip*: You can replace `[INGR_NAME]` with `[INGR_INDEX]` to quickly choose which ingredient to set a limit to.
* 💡 *Note*:  You will be prompted to enter the limit to watch out for in the next step.

Example of usage:

`set ingr limit chicken` or `set ingr limit 1`

Expected Outcome:

```
____________________________________________________________
Please enter the limit for 'chicken' in kg:
____________________________________________________________
```

Example of usage:

`7.7`

Expected Outcome:

```
____________________________________________________________
The limit for 'chicken' is now 7.7 kg!
____________________________________________________________
```

💡 *Note*:

When adding new wastage to the Ingredient exceeds the limit, you will receive a prompt that the current Ingredient limit has
exceeded.

An indicator *(exceeded)* shows the Ingredient that has exceeded its limit.

Expected outcome:

```
____________________________________________________________
Please enter the wastage weight of 'chicken' in kg:
____________________________________________________________
8.45
____________________________________________________________
Wastage of 'chicken' has been added and is now 8.45 kg!
____________________________________________________________
____________________________________________________________
Wastage of 'chicken' has exceeded the limit!
____________________________________________________________
```

```
____________________________________________________________
Here are the ingredients you have:
1. chicken
   Storage: 7.0 kg
   Wastage: 8.45 kg
   Limit: 7.7 (exceeded)
   Expiry Date: No expiry date has been set
You can use command 'add' to add new ingredients!
____________________________________________________________
```

<br/>

### Set an expiry date for an ingredient : `set ingr expiry`

Set an expiry date for an existing ingredient.

*This is so that you will be able to track when your ingredients will expire*.

Format: `set ingr expiry [INGR_NAME]`

* Can only be performed on existing `INGR_NAME`.
* ❕ *Tip*: You can replace `[INGR_NAME]` with `[INGR_INDEX]` to quickly choose which ingredient to set an expiry date to.
* 💡 *Note*:  You will be prompted to enter the expiry date of the ingredient in the next step.

Example of usage:

`set ingr expiry chicken` or `set ingr expiry 1`

Expected Outcome:

```
____________________________________________________________
What is the expiry date of 'chicken' in 'dd/MM/yyyy' format?
____________________________________________________________
```

Example of usage:

`20/12/2021`

💡 *Note*:

* The expiry date must be in the format of `dd/MM/yyyy`.
* You cannot add a date before the current date on your PC.

Expected Outcome:

```
____________________________________________________________
The expiry date of 'chicken' has been set to 20/12/2021 (43 day(s) from today)
____________________________________________________________
```

💡 *Note*: The number of days shown to expiry is relative to the date set on your PC and does not strictly follow the
outcome shown above. This applies to the expiry dates similarly found in `list ingr` and `find ingr`.

<br/>

### Edit an ingredient's name : `edit ingr name`

Edit the name of an existing ingredient.

*This is so that you will be able to make changes to an ingredient 's name when there is an update to the menu or a typo
to the name of the ingredient*

Format: `edit ingr name [INGR_NAME]`

* Can only be performed on existing `INGR_NAME`.
* ❕ *Tip*: You can replace `[INGR_NAME]` with `[INGR_INDEX]` to quickly choose which ingredient to edit.
* 💡 *Note*:  You will be prompted to enter the new ingredient name in the next step, and a confirmation message
  afterwards.

Example of usage:

`edit ingr name chicken` or `edit ingr name 1`

Expected Outcome:

```
____________________________________________________________
What would you like to change the Ingredient Name 'chicken' to?
____________________________________________________________
```

Example of usage:

`chicken breast`

Expected Outcome:

```
____________________________________________________________
Are you sure you want to change the ingredient name from 'chicken' to 'chicken breast'?
Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:
____________________________________________________________
```

Example of usage (selecting **yes**):

`y`

Expected Outcome:

```
____________________________________________________________
'chicken' has been updated to 'chicken breast'!
____________________________________________________________
```

Example of usage (selecting **no**):

`n`

Expected Outcome:

```
____________________________________________________________
Your previous command is disregarded.
____________________________________________________________
```

<br/>

### Edit an ingredient's storage : `edit ingr stored`

Edit the storage of an existing ingredient.

*This is so that you will be able to make changes to an ingredient's storage when resetting or updating a new storage
weight.*

Format: `edit ingr stored [INGR_NAME]`

* Can only be performed on existing `INGR_NAME`.
* ❕ *Tip*: You can replace `[INGR_NAME]` with `[INGR_INDEX]` to quickly choose which ingredient to edit.
* 💡 *Note*:  You will be prompted to enter the new ingredient storage in the next step, and a confirmation message
  afterwards.

Example of usage:

`edit ingr stored chicken breast` or `edit ingr stored 1`

Expected Outcome:

```
____________________________________________________________
What would you like to change the storage weight (in kg) of 'chicken breast' to?
____________________________________________________________
```

Example of usage:

`53.2`

Expected Outcome:

```
____________________________________________________________
Are you sure you want to change the storage weight (in kg) from '7.0' to '53.2'?
Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:
____________________________________________________________

```

Example of usage (selecting **yes**):

`y`

Expected Outcome:

```
____________________________________________________________
Storage of 'chicken breast' has been updated to 53.2 kg!
____________________________________________________________

```

Example of usage (selecting **no**):

`n`

Expected Outcome:

```
____________________________________________________________
Your previous command is disregarded.
____________________________________________________________
```

<br/>

### Edit an ingredient's wastage : `edit ingr waste`

Edit the wastage of an existing ingredient.

*This is so that you will be able to make changes to an ingredient's wastage when resetting or updating a new wastage
weight.*

Format: `edit ingr waste [INGR_NAME]`

* Can only be performed on existing `INGR_NAME`.
* ❕ *Tip*: You can replace `[INGR_NAME]` with `[INGR_INDEX]` to quickly choose which ingredient to edit.
* 💡 *Note*:  You will be prompted to enter the new ingredient wastage in the next step, and a confirmation message
  afterwards.

Example of usage:

`edit ingr waste chicken breast` or `edit ingr waste 1`

Expected Outcome:

```
____________________________________________________________
What would you like to change the wastage weight (in kg) of 'chicken breast' to?
____________________________________________________________
```

Example of usage:

`5.3`

Expected Outcome:

```
____________________________________________________________
Are you sure you want to change the wastage weight (in kg) from '8.45' to '5.3'?
Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:
____________________________________________________________
```

Example of usage (selecting **yes**):

`y`

Expected Outcome:

```
____________________________________________________________
Wastage of 'chicken breast' has been updated to 5.3 kg!
____________________________________________________________
```

Example of usage (selecting **no**):

`n`

Expected Outcome:

```
____________________________________________________________
Your previous command is disregarded.
____________________________________________________________
```

<br/>

### Link ingredient to dish : `link`

Link an existing Ingredient to an existing Dish.

*To define the Dish in terms of what Ingredients make it up.*

Format: `link [DISH_NAME] / [INGR_NAME]`

* Can only be performed on existing `DISH_NAME` and `INGR_NAME`.

Example of usage:

`link roasted chicken rice / chicken breast`

Expected Outcome:

```
____________________________________________________________
Successfully added 'chicken breast' as an ingredient of 'roasted chicken rice'!
____________________________________________________________
```

<br/>

### Delete existing ingredient : `del ingr`

Delete an existing Ingredient from your Ingredient list.

*When an Ingredient no longer exists in your Restaurant's inventory.*

Format: `del ingr [INGR_NAME]`

* Can only be performed on existing `INGR_NAME`.
* ❕ *Tip*: You can replace `[INGR_NAME]` with `[INGR_INDEX]` to quickly choose which ingredient to delete.
* 💡 *Note*: You will be prompted with a confirmation message to delete the ingredient.

Example of usage:

`del ingr chicken breast` or `del ingr 1`

Expected Outcome:

```
____________________________________________________________
Are you sure you want to remove 'chicken breast'?
Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:
____________________________________________________________
```

Example of usage (when selecting **yes**):

`y`

Expected Outcome:

```
____________________________________________________________
'chicken breast' has been removed from your ingredient list!
____________________________________________________________
```

Example of usage (when selecting **no**):

`n`

Expected Outcome:

```
____________________________________________________________
Your previous command is disregarded.
____________________________________________________________

```

<br/>

### View existing ingredients : `list ingr`

View all existing Ingredients in the Ingredient list.

❕ *Tip*:  Useful to check all your Ingredients available

Format: `list ingr`

* 💡 *Note*: `list ingr` does not require additional parameters.

Example of usage:

`list ingr`

Expected Outcome:

```
____________________________________________________________
Here are the ingredients you have:
1. chicken breast
   Storage: 2.2 kg
   Wastage: 0.0 kg
   Limit: No limit has been set
   Expiry Date: No expiry date has been set
2. beef
   Storage: 2.95 kg
   Wastage: 0.0 kg
   Limit: No limit has been set
   Expiry Date: 23/05/2023 (562 day(s) from today)
You can use command 'add' to add new ingredients!
____________________________________________________________
```

<br/>

### Display graph of dish wastage : `graph dish`

Display a graph to show Dish **wastage**.

*Gives you a visual representation of the Dish wastage in your restaurant.*

Format: `graph dish`

* 💡 *Note*:
    * `graph dish` does not require additional parameters. 
    * Due to the limitations of a CLI based application, the terminal is unable to print half a character and 
      as a result the graphs are unable to display fractional values (e.g. 2.5 units is impossible)
    * To circumvent this limitation we have added a bar that has a digit instead of a `|` inside to indicate fractional units
      (e.g 4.5 units will have four bars followed by a bar that contains a 5). This is still only capable of representing up to one decimal place.

Example of usage:

`graph dish`

Expected Outcome:

```
____________________________________________________________

         [|]                 Legend:              Scale: 1 unit [|] = 3.45kg
         [|]                 A. cake: 3.45kg
         [|]                 B. chicken rice: 34.5kg
         [|]                 C. hamburger: 7.22kg
         [|]                 D. prata: 15.7kg
         [|]         [5]
         [|]         [|]
         [|]   [0]   [|]
         [|]   [|]   [|]
   [|]   [|]   [|]   [|]
    A     B     C     D 
____________________________________________________________
```

<br/>

### Display a graph of ingredient wastage : `graph ingr`

Displays a graph to show Ingredient **wastage**.

*Gives you a visual representation of the Ingredient wastage in your restaurant.*

Format: `graph ingr`

* 💡 *Note*:
    * `graph ingr` does not require additional parameters.
    * Due to the limitations of a CLI based application, the terminal is unable  to print half a character and
       as a result the graphs are unable to display fractional values (e.g. 2.5 units is impossible)
    * To circumvent this limitation we have added a bar that has a digit instead of a `|` inside to indicate fractional units
      (e.g 4.9 units will have four bars followed by a bar that contains a 9). This is still only capable of representing up to one decimal place.
       
Example of usage:

`graph ingr`

Expected Outcome:

```
____________________________________________________________

                           [|]     Legend:              Scale: 1 unit = 5.0kg
                           [|]     A. chicken: 2.56kg
                           [|]     B. rice: 21.56kg
                           [|]     C. flour: 24.56kg
                     [3]   [|]     D. potato: 26.56kg
         [3]   [9]   [|]   [|]     E. corn: 50.0kg
         [|]   [|]   [|]   [|]
         [|]   [|]   [|]   [|]
         [|]   [|]   [|]   [|]
   [5]   [|]   [|]   [|]   [|]
    A     B     C     D     E
____________________________________________________________
```

<br/>

### Find dishes with matching keyword : `find dish`

Retrieves a list of Dishes inclusive of the matching keyword.

❕ *Tip*:  Useful to find certain Dishes available in your Dish list.

Format: `find dish [KEYWORD]`

Example of usage:

`find dish pa`

Expected Outcome:

```
____________________________________________________________
As requested, here are the matching dishes in your list:
1. pasta
   Wastage: 1.57 kg
   Ingredients Linked: None
   Limit: No limit has been set
3. krabby patty
   Wastage: 38.4 kg
   Ingredients Linked: None
   Limit: No limit has been set
____________________________________________________________
```

💡 *Note*: `find dish [KEYWORD]` will print the index of matching dishes as how it appears in the dish list.
* Example original Dish list:
  
    ```
    ____________________________________________________________
    Here are the dishes you have:
    1. pasta
       Wastage: 1.57 kg
       Ingredients Linked: None
       Limit: No limit has been set
    2. chicken rice
       Wastage: 37.5 kg
       Ingredients Linked: None
       Limit: No limit has been set
    3. krabby patty
       Wastage: 38.4 kg
       Ingredients Linked: None
       Limit: No limit has been set
    You can use command 'add' to add new dishes!
    ____________________________________________________________
    ```

<br/>

### Find ingredients with matching keyword : `find ingr`

Retrieves a list of Ingredients inclusive of the matching keyword.

❕ *Tip*:  Useful to find certain Ingredients available in your Ingredient list.

Format: `find ingr [KEYWORD]`

Example of usage:

`find ingr chi`

Expected Outcome:

```
____________________________________________________________
As requested, here are the matching ingredients in your list:
2. chicken breast
   Storage: 21.12 kg
   Wastage: 0.0 kg
   Limit: No limit has been set
   Expiry Date: No expiry date has been set
3. chickpea
   Storage: 32.3 kg
   Wastage: 0.0 kg
   Limit: No limit has been set
   Expiry Date: No expiry date has been set
____________________________________________________________
```

💡 *Note*: `find ingr [KEYWORD]` will print the index of matching ingredients as how it appears in the ingredient list.
* Example original Ingredient list:
  
    ```
    ____________________________________________________________
    Here are the ingredients you have:
    1. beef
       Storage: 23.3 kg
       Wastage: 0.0 kg
       Limit: No limit has been set
       Expiry Date: No expiry date has been set
    2. chicken breast
       Storage: 21.12 kg
       Wastage: 0.0 kg
       Limit: No limit has been set
       Expiry Date: No expiry date has been set
    3. chickpea
       Storage: 32.3 kg
       Wastage: 0.0 kg
       Limit: No limit has been set
       Expiry Date: No expiry date has been set
    You can use command 'add' to add new ingredients!
    ____________________________________________________________
    ```
  
<br/>

### Remove all dishes : `clear dish`

Removes all existing Dishes from the list of Dishes.

❕ *Tip*:  Useful to obtain an empty Dish list.

Format: `clear dish`

* 💡 *Note*:
    * `clear dish` does not require additional parameters. 
    * You will be prompted with a confirmation message to delete all Dishes.

Example of usage:

`clear dish`

Expected Outcome:

```
____________________________________________________________
Are you sure you want to remove all Dishes?
Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:
____________________________________________________________
```

Example of usage (when selecting **yes**):

`y`

Expected Outcome:

```
____________________________________________________________
Dish list has been cleared.
____________________________________________________________

```

Example of usage (when selecting **no**):

`n`

Expected Outcome:

```
____________________________________________________________
Your previous command is disregarded.
____________________________________________________________

```

<br/>

### Remove all ingredients : `clear ingr`

Removes all existing Ingredients from the list of Ingredients.

❕ *Tip*:  Useful to obtain an empty Ingredient list.

Format: `clear ingr`

* 💡 *Note*:
    * `clear ingr` does not require additional parameters.
    * You will be prompted with a confirmation message to delete all Ingredients.

Example of usage:

`clear ingr`

Expected Outcome:

```
____________________________________________________________
Are you sure you want to remove all Ingredients?
Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:
____________________________________________________________
```

Example of usage (when selecting **yes**):

`y`

Expected Outcome:

```
____________________________________________________________
Ingredient list has been cleared.
____________________________________________________________
```

Example of usage (when selecting **no**):

`n`

Expected Outcome:

```
____________________________________________________________
Your previous command is disregarded.
____________________________________________________________
```

<br/>

### Remove all dishes and ingredients : `clear all`

Removes all existing Dishes and Ingredients from their respective lists.

❕ *Tip*: Useful to obtain both empty Dish and Ingredient lists.

Format: `clear all`

* 💡 *Note*:
    * `clear all` does not require additional parameters.
    * You will be prompted with a confirmation message to delete all Dishes and Ingredients.

Example of usage:

`clear all`

Expected Outcome:

```
____________________________________________________________
Are you sure you want to remove all Dishes and Ingredients?
Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:
____________________________________________________________
```

Example of usage (when selecting **yes**):

`y`

Expected Outcome:

```
____________________________________________________________
Both Dish and Ingredient lists have been cleared.
____________________________________________________________
```

Example of usage (when selecting **no**):

`n`

Expected Outcome:

```
____________________________________________________________
Your previous command is disregarded.
____________________________________________________________
```

<br/>

### Sort dishes by amount of wastage : `sort dish`

Sorts all Dishes in **descending order** of **wastage**.

❕ *Tip*: Useful to immediately see the most wasted Dish.

Format: `sort dish`

* 💡 *Note*: `sort dish` does not require additional parameters.

Example of usage:

`sort dish`

Expected Outcome:

```
____________________________________________________________
List of Dishes has been sorted.
____________________________________________________________
Here are the dishes you have:
1. krabby patty
   Wastage: 9.36 kg
   Ingredients Linked: None
   Limit: No limit has been set
2. chicken rice
   Wastage: 8.55 kg
   Ingredients Linked: None
   Limit: No limit has been set
3. meatballs and spaghetti
   Wastage: 7.38 kg
   Ingredients Linked: None
   Limit: No limit has been set
You can use command 'add' to add new dishes!
____________________________________________________________
```

<br/>

### Sort ingredients by amount of wastage : `sort ingr`

Sorts all Ingredients in **descending order** of **wastage**.

❕ *Tip*: Useful to immediately see the most wasted Ingredient.

Format: `sort ingr`

* 💡 *Note*: `sort ingr` does not require additional parameters.

Example of usage:

`sort ingr`

Expected Outcome:

```
____________________________________________________________
List of Ingredients has been sorted.
____________________________________________________________
Here are the ingredients you have:
1. chicken
   Storage: 9.32 kg
   Wastage: 14.32 kg
   Limit: No limit has been set
   Expiry Date: No expiry date has been set
2. beef
   Storage: 8.3 kg
   Wastage: 13.53 kg
   Limit: No limit has been set
   Expiry Date: No expiry date has been set
3. pork
   Storage: 6.0 kg
   Wastage: 9.57 kg
   Limit: No limit has been set
   Expiry Date: No expiry date has been set
4. fish
   Storage: 7.0 kg
   Wastage: 4.98 kg
   Limit: No limit has been set
   Expiry Date: No expiry date has been set
You can use command 'add' to add new ingredients!
____________________________________________________________
```

<br/>

### Exit Food-O-Rama : `bye`

Exit Food-O-Rama and save all data entries.

Format: `bye`

* All data will be saved automatically.

Example of usage:

`bye`

Expected Outcome:

```
###########################################################################
#           _____                _____                    _____           #
#          /\    \              |\    \                  /\    \          #
#         /::\    \             |:\____\                /::\    \         #
#        /::::\    \            |::|   |               /::::\    \        #
#       /::::::\    \           |::|   |              /::::::\    \       #
#      /:::/\:::\    \          |::|   |             /:::/\:::\    \      #
#     /:::/__\:::\    \         |::|   |            /:::/__\:::\    \     #
#    /::::\   \:::\    \        |::|   |           /::::\   \:::\    \    #
#   /::::::\   \:::\    \       |::|___|______    /::::::\   \:::\    \   #
#  /:::/\:::\   \:::\ ___\      /::::::::\    \  /:::/\:::\   \:::\    \  #
# /:::/__\:::\   \:::|    |    /::::::::::\____\/:::/__\:::\   \:::\____\ #
# \:::\   \:::\  /:::|____|   /:::/~~~~/~~      \:::\   \:::\   \::/    / #
#  \:::\   \:::\/:::/    /   /:::/    /          \:::\   \:::\   \/____/  #
#   \:::\   \::::::/    /   /:::/    /            \:::\   \:::\    \      #
#    \:::\   \::::/    /   /:::/    /              \:::\   \:::\____\     #
#     \:::\  /:::/    /    \::/    /                \:::\   \::/    /     #
#      \:::\/:::/    /      \/____/                  \:::\   \/____/      #
#       \::::::/    /                                 \:::\    \          #
#        \::::/    /                                   \:::\____\         #
#         \::/____/                                     \::/    /         #
#          ~~                                            \/____/          #
###########################################################################
#       Thank you for using Food-O-Rama to track your food wastage.       #
#                Your data has been saved successfully.                   #
#                         Have a nice day!                                #
#                   Love, the Food-O-Rama Team <3                         #
###########################################################################
```

<br/>

### Saving the data

Food-O-Rama data (*dish and ingredients list*) are saved automatically after any command that changes the data. There is
no need to save manually.

<br/>

### Accessing the data

Food-O-Rama data is saved as 2 files: *dishes.txt* and *ingredients.txt*. These files can be found in the *`data`*
folder in the same directory as the `Food-O-Rama.jar` file.

<br/>

### Manipulating the data

Food-O-Rama data can be written manually by editing the text files in the *`data`* folder. 

❕ *Tip*: Manual editing is **not recommended** unless you are sure of the changes that you are making. You may instead 
utilize the various `edit` commands in the program to edit your Dish and Ingredient data.

Dish Format: `[DISH_NAME] | [AMOUNT_WASTED_IN_KG] | [WASTAGE_DIVIDED_BY_NUM_OF_LINKED_INGR] | [WASTAGE_LIMIT] | [INGR_1|INGR_2|etc.]`

* 💡 *Note*: `[WASTAGE_LIMIT]` is `-1` when no limit is set.

Dish example of usage:

```
No Linked Ingredients, No Limit, Wastage of 2kg:
prata|2.0|2.0|-1

2 Linked Ingredients (flour and egg), Wastage of 2kg, No Limit:
prata|2.0|1.0|-1|flour|egg

2 Linked Ingredients (flour and egg), Wastage of 2kg, Limit of 3kg:
prata|2.0|1.0|3|flour|egg
```

Ingredient Format: `[INGR_NAME] | [AMOUNT_STORED_IN_KG] | [AMOUNT_WASTED_IN_KG] | [WASTAGE_LIMIT] | [EXPIRY_DATE]`

* 💡 *Note*:

    * `[WASTAGE_LIMIT]` is `-1` when no limit is set.
    * `[EXPIRY_DATE]` follows the format `dd/MM/yyyy`.
    * `[EXPIRY_DATE]` is `null` when no expiry date is set.

Ingredient example of usage:

```
No Limit, No Expiry, Storage of 2kg, Wastage of 1kg:
chicken|2.0|1.0|-1|null

Limit of 2.5kg, Expiry Set, Storage of 2kg, Wastage of 1kg:
chicken|2.0|1.0|2.5|30/10/2021
```

## 😯 FAQ

**Q**: How do I transfer my data to another computer?

**A**: Copy the `data` folder over to the new computer and put it in the same directory
as `Food-O-Rama.jar`.

Example:

![DataImage](https://user-images.githubusercontent.com/69526313/140651833-d6e15a84-a8d0-4b05-bfdb-3352c5633846.png)

## 👋 Final Notes

You have come to the end of the **Food-O-Rama User Guide**.

For any further enquiries, please do contact us through our contact information found at
our [About Us](https://ay2122s1-cs2113t-w11-4.github.io/tp/AboutUs.html)
page.
