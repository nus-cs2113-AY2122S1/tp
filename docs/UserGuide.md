# Food-O-Rama User Guide

## Introduction

Food-O-Rama is a food wastage tracking application for restaurant owners and hawkers to 
keep track of their food resources. It provides the user with insight on the amount of ingredients 
in their storage and how much food is wasted. This allows the user to better plan their 
purchase of raw ingredients and cooking of dishes which ultimately saves cost.

* [Quick Start](#quick-start)
* [List of Commands](#list-of-commands)
* [Features](#features)
  * [Get some help](#get-some-help-help)
  * [Add a new Ingredient](#add-a-new-ingredient-add-ingr)
  * [Add more storage to an existing Ingredient](#add-more-storage-to-an-existing-ingredient-add-ingr-stored)
  * [View all exising Ingredients](#view-all-exising-ingredients-list-ingr)
  * [Add a new Dish](#add-a-new-dish-add-dish)
  * [View all existing dishes](#view-all-existing-dishes-list-dish)
  * [Link a Dish to an Ingredient](#link-a-dish-to-an-ingredient-add-constituent)
  * [Add Ingredient Wastage](#add-ingredient-wastage-add-ingr-waste)
  * [Add Dish Wastage](#add-dish-wastage-add-dish-waste)
  * [Delete an existing Ingredient](#delete-an-existing-ingredient-del-ingr)
  * [Delete an existing Dish](#delete-an-existing-dish-del-dish)
  * [Exit Food-O-Rama](#exit-food-o-rama-bye)

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
|View the list of commands| help|
|Add a new Ingredient| add ingr [ingrName]  followed by [weight in KG] |
|Add storage to an existing Ingredient| add ingr stored [ingrName] followed by [weight in KG] |
|View all Ingredients| list ingr|
|Add a new Dish   | add dish [dishName]|
|View all Dishes|list dish|
|Link a Dish to a Ingredient| add constituent [dishName] / [ingrName]|
|Add Ingredient Wastage| add ingr waste [ingrName] followed by [weight in KG]|
|Add the Dish Wastage | add dish waste [dishName] followed by [weight in KG]|
|Delete an Ingredient | del [ingrName]|
|Delete a Dish| del [dishName] |
|Exit Food-O-Rama| bye|

## Features 

### Get some help: `help`
Display the list of commands that Food-O-Rama utilises

Format: `help`

* Only the command `help` is required.

Example of usage:

`help`

Expected Outcome:

```
____________________________________________________________
Here are the commands you can use: 
1. Adding a Dish : 'add dish [dishName]' 
    Example: 'add dish chicken rice' 
2. Adding Dish Wastage : 'add dish waste [dishName]' followed by '[weight in KG]' 
    Example: 'add dish waste chicken rice' ; '0.8' 
3. Deleting a Dish : 'del [dishName] 
    Example: 'del dish chicken rice' 
4. Viewing all Dishes : 'list dish' 
5. Adding a new Ingredient : 'add ingr [ingrName]' followed by '[weight in KG]' 
    Example: 'add ingr chicken' ; '2' 
6. Adding storage to an already existing Ingredient : 'add ingr stored [ingrName]' followed by '[weight in KG]' 
    Example: 'add ingr stored chicken' ; '1.5' 
7. Adding Ingredient Wastage : 'add ingr waste [ingrName]' followed by '[weight in KG]' 
    Example: 'add ingr waste chicken' ; '0.7' 
8. Linking an Ingredient to a Dish : 'add dish constituent [dishName] / [ingrName]'
    Example: 'add constituent chicken rice / chicken' 
9. Deleting an Ingredient : 'del [ingrName]' 
    Example: 'del ingr chicken' 
10. Viewing all Ingredients : 'list ingr' 
11. Viewing this list of commands: help 
12. Exiting the program: bye
____________________________________________________________

```


### Add a new Ingredient: `add ingr`
Add a new Ingredient to your Ingredient list

Format: `add ingr ingrName`

* The `ingrName` can be in a natural language format. 
* You will be prompted to enter the weight of `ingrName` in the next step.

Example of usage: 

`add ingr chicken`

Expected Outcome:

```
____________________________________________________________
Enter the weight of chicken in KG:

```

Example of usage:

`2`

Expected Outcome:

```
____________________________________________________________
Ingredient added to list: chicken (Weight: 2.0 kg)
____________________________________________________________

```

### Add more storage to an existing Ingredient: `add ingr stored`
Add more storage in KG to an Ingredient that exists in the Ingredient list.

Format: `add ingr stored ingrName`

* The `ingrName` has to be an existing Ingredient in the Ingredient list.
* You will be prompted to enter the weight of additional `ingrName` stored in the next step.

Example of usage:

`add ingr stored chicken`

Expected Outcome:

```
____________________________________________________________
Enter the weight of chicken in KG:
```
Example of usage:

`5`

Expected Outcome:

```
Storage of chicken is now 7.0 kg
____________________________________________________________
```
### View all exising Ingredients: `list ingr`
View all exisitng Ingredients in the Ingredient list

Format: `list ingr`

* The `list` must come before `ingr`.

Example of usage:

`list ingr`

Expected Outcome:

```
____________________________________________________________
Here are the ingredients you have: 
1. chicken
   Storage: 7.0 kg
   Wastage: 0.0 kg
You can use command 'add' to add new ingredients!
____________________________________________________________
```
### Add a new Dish: `add dish`
Add a new Dish to your Dish list

Format: `add dish dishName`

* The `dishName` can be in a natural language format.

Example of usage:

`add dish chicken rice`

Expected Outcome:

```
____________________________________________________________
Dish added to list: chicken rice
____________________________________________________________
```
### View all existing dishes: `list dish`
View all exisitng Dishes in the Dish list

Format: `list dish`

* The `list` must come before `dish`.

Example of usage:

`list dish`

Expected Outcome:

```
____________________________________________________________
Here are the dishes you have: 
1. chicken rice
   Wastage: 0.0 kg
   Constituents: None
You can use command 'add' to add new dishes!
____________________________________________________________
```
### Link a Dish to an Ingredient: `add constituent`
Link an existing Dish with an existing Ingredient

Format: `add constituent dishName / ingrName`

*`dishName` space `/` space `ingrName`.

Example of usage:

`add constituent chicken rice / chicken`

Expected Outcome:

```
____________________________________________________________
Added chicken as ingredient of chicken rice
____________________________________________________________
```

## Add Ingredient Wastage: `add ingr waste`
Incur wastage on an existing Ingredient

Format: `add ingr waste ingrName`

* Can only be performed on existing `ingrName`
* You will be prompted to enter the weight of `ingrName` wasted in the next step.

Example of usage:

`add ingr waste chicken`

Expected Outcome:

```
____________________________________________________________
Enter the wastage of chicken in KG:
```
Example of usage:

`1.5`

Expected Outcome:

```
Wastage of chicken is now 1.5 kg
____________________________________________________________
```
## Add Dish Wastage: `add dish waste`
Incur wastage on an existing Dish

Format: `add dish waste ingrName`

* Can only be performed on existing `dishName`
* You will be prompted to enter the weight of `dishrName` wasted in the next step.

Example of usage:

`add dish waste chicken rice`

Expected Outcome:

```
____________________________________________________________
Enter the wastage of chicken rice in KG:
```
Example of usage:

`2.5`

Expected Outcome:

```
Wastage of chicken rice is now 2.5 kg
____________________________________________________________
```

### Delete an existing Ingredient: `del ingr`
Delete an existing Ingredient from your Ingredient list

Format: `del ingr ingrName`

* The `ingrName` has to be existing in the Ingredient list for it to be deleted.

Example of usage:

`del ingr rice`

Expected Outcome:

```
____________________________________________________________
Ingredient, rice has been removed!
____________________________________________________________
```

### Delete an existing Dish: `del dish`
Delete an existing Ingredient from your Ingredient list

Format: `del dish dishName`

* The `dishName` has to be existing in the Ingredient list for it to be deleted.

Example of usage:

`del dish chicken rice`

Expected Outcome:

```
____________________________________________________________
Dish, chicken rice has been removed!
____________________________________________________________
```

### Exit Food-O-Rama: `bye`
Exit Food-O-Rama and save all data entries.

Format: `bye`

* All data will be saved.

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
#          ~~                                            \/____/         #
###########################################################################
Thank you for using Food-O-Rama to track your food wastage.
Have a nice day!
____________________________________________________________
```
## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

