# User Guide

* Table of Contents
{:toc}

## Introduction

Expiry Eliminator is a desktop app for managing the freshness of ingredients in your kitchen and the recipes you want to cook, optimized for use via a Command Line Interface (CLI). If you can type fast, Expiry Eliminator can help you manage your ingredients and recipes quickly.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features

### Adding an ingredient: `add`

Adds an ingredient to the ingredient repository.

Format: `add i/INGREDIENT [u/UNIT] [q/QUANTITY e/EXPIRY_DATE]`

- Unit is optional.
- Quantity and expiry date is optional, but you cannot have one and not the other (i.e. you either don't provide both quantity and expiry date, or you provide both quantity and expiry date).
- Expiry date must be in the format of `yyyy-mm-dd`.
- Quantity must be an integer.
- Note that ingredient names are case insensitive, and will be automatically stored in title case (e.g. `ReD aPplE` will be stored as `Red Apple`).
- Ingredient names must be unique.
- If the ingredient name already exists, an error message will be shown.

Example of usage:
- `add i/Red Apple`
- `add i/Salt u/g`
- `add i/Red Apple q/5 e/2021-10-08`
- `add i/Salt u/g q/1000 e/2021-01-01`

<br/>

### Update units of an ingredient: `update units`

Updates the units of all ingredients throughout the program.

Format: `update units i/INGREDIENT u/UNITS`

- Ingredients both in ingredient repository and recipes will also be updated to the new units.
- If units is left blank, you will get a "units deleted" message.


Example of usage:
- `update units i/salt u/kilograms`
- `update units i/salt u/`

<br/>

### Listing all ingredients: `list`

Shows a list of all ingredients, its quantity and expiry dates.

Format: `list`

Example of usage:
- `list`

<br/>

### Listing ingredients that are expiring: `list expiring`

Shows a list of all ingredients, its quantity and expiry dates, that are expiring within the week.

Format: `list expiring`

Example of usage:
- `list expiring`

<br/>

### Listing ingredients that have expired: `list expired`

Shows a list of all ingredients, its quantity and expiry dates, that have expired.

Format: `list expired`

Example of usage:
- `list expired`

<br/>

### List a specific ingredient: `view`

Shows a specific ingredient with its quantity and expiry date.

Format: `view i/INGREDIENT`

- The ingredient name are case-insensitive, and will be automatically stored in title case
  (E.g.: `SalT` would be recorded as `Salt`)
- If the ingredient does not exist, it will notify the user.

Example of usage:
- `view i/salt`


<br/>

### Deleting all expired ingredients: `delete expired`

Deletes all the expired ingredients in the repository.

Format: `delete expired`

- Ingredient is considered to have expired if its expiry date is before the current date.

Example of usage:
- `delete expired`

<br/>


### Adding a recipe: `add recipe`

Adds a recipe with its respective ingredients to the Recipe List.

Format: `add recipe r/RECIPE i/INGREDIENT q/QUANTITY i/INGREDIENT q/QUANTITY...`

- Adds a recipe with the name `RECIPE` and its `INGREDIENT` with the corresponding `QUANTITY` for each ingredient. 
  `QUANTITY` must be a non-negative integer.
- The ingredient and recipe names are case-insensitive, and will be automatically stored in title case 
  (E.g.: `ChiCkEN SoUP` would be recorded as `Chicken Soup`)
- The recipe is added to the list
- The ingredients and the quantity can be added in any order, but the first ingredient will correspond to the first
 quantity entered, second ingredient to second quantity entered etc.
- The units of the ingredients in the recipe will follow the units of the ingredients 
  saved in the Ingredient Repository.
- If the ingredients of the recipe does not exist in the Ingredient Repository when adding the recipe, the
  ingredients will be added into the Ingredient Repository with zero quantity and no units.

Example of usage:
- `add recipe r/Chicken Soup i/Chicken q/300 i/Salt q/10`
- `add recipe r/Chicken Soup i/Chicken i/Salt q/300 q/10`

<br/>

### Deleting a recipe : `delete recipe`

Deletes a recipe from the Recipe List.

Format : `delete recipe r/RECIPE`

- Deletes a recipe with the name `RECIPE` and all its information.
- The recipe is removed from the list.
- The recipe name is case-insensitive (typing `cHiCKEn sOUP` would cause `Chicken Soup` 
  to be deleted if it’s in the list).
- The recipe name specified must exist in the list.

Example of usage:

- `delete recipe r/Chicken Soup`

<br/>


### Remove ingredients when recipe is cooked : `cooked`

Removes certain amount of ingredients from the Ingredient Repository based on the recipe that is cooked.

Format: `cooked r/RECIPE`

- Removes a certain amount of ingredients from the Ingredient Repository according to the amount of the ingredients
  for the `RECIPE`.
- The recipe name is case-insensitive (typing `cHiCKEn sOUP` would cause the given amount of ingredients in 
  `Chicken Soup` to be removed from the Ingredient Repository).
- The recipe name specified must exist in the list.
- The quantities of ingredients in the Ingredient Repository should be sufficient to cook the recipe 
  for it to be removed.
- The ingredients that expire the earliest will be removed first.
- Expired ingredients can be removed with this command so the user must be careful in managing 
  expired ingredients.

Example of usage: 
- `cooked r/Chicken Soup`

<br/>

### List recipes that can be cooked : `list recipes I can cook`

Returns a list of recipes which the user can cook with the ingredients they currently have 
in their Ingredient Repository.

Format: `list recipes i can cook`

- There must be sufficient ingredients in the Ingredient Repository to cook the recipe for the recipe
  to be shown in the list.
- The recipe will be shown even if some ingredients have expired and the user will be informed 
  of the ingredients that have expired.

Example of usage: 
- `list recipes i can cook`

<br/>

### List recipes in Recipe List : `list recipes`
Returns a list of recipes that are stored in the Recipe List.

Format: `list recipes`

<br/>

### View/Find a recipe in Recipe List : `view recipes`
Views a specific recipe whose names contain any of the given keywords, 
with the corresponding ingredients and the quantity of each ingredient available in the storage.

Format: `view recipe r/RECIPE`

The search is case-insensitive. e.g “pasta” will match “Pasta”. 
The order of the keywords matter. e.g. “Nasi Padang” will NOT match “Padang Nasi”. 
The input should only contain the name of the recipe. 
Only the full name will be matched. e.g. “Chicken” will NOT match “Curry Chicken”.

Example of usage:
- `view recipe r/Curry Chicken`

<br/>

### Update quantities of ingredients in a recipe : `update recipe`
According to your own flavour, update a recipe by modifying the quantity of ingredients in that recipe.

Format: `update recipe r/RECIPE i/INGREDIENT q/QUANTITY`

Without the information about the current quantity of that ingredients you want to update, you can directly update
the quantity with the corresponding q/QUANTITY that you input.
Ingredients with 0 quantity is not allowed, as 0 quantity is meaningless.

Example of usage:
- `update recipe r/Apple Pie i/apple q/3 i/flour q/200`

<br/>

### Create a shopping list of ingredients to buy for recipes user wants to make: `shopping list`

Takes in multiple recipes the user wants to cook, and generates a shopping list of ingredients that
the user does not have and needs to buy.

Format: `shopping list r/RECIPE r/RECIPE r/RECIPE ....`

- Can take in multiple recipe inputs

Example of usage:
- `shopping list r/Chicken Soup`
- `shopping list r/Chicken Soup r/Pork Soup`

<br/>

### View Help Message : `help`
Shows a message explaining how to access the help page. i.e. the user guide
Format: `help`

<br/>

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

1. Add recipe 
    - Command: `add recipe r/RECIPE i/INGREDIENT q/QUANTITY i/INGREDIENT q/QUANTITY ……`
    - E.g.: `add recipe r/Chicken Soup i/Chicken q/300 i/Salt q/10`
    - E.g.: `add recipe r/Chicken Soup i/Chicken i/Salt q/300 q/10`
2. Delete recipe 
    - Command: `delete recipe r/RECIPE`
    - E.g.: `delete recipe r/Chicken Soup`
3. List recipes that can be cooked
    - Command: `list recipes i can cook`
    - E.g.: `list recipes i can cook`
4. Remove ingredients when recipe is cooked
    - Command: `cooked r/RECIPE`
    - E.g.: `cooked r/Chicken Soup`
5. List all recipes in Recipe List
    - Command: `list recipes`
    - E.g.: `list recipes`
6. Find a recipe in Recipe List
    - Command: `view recipe r/RECIPE`
    - E.g.: `view recipe r/Curry Chicken`
7. Update ingredients in a recipe
    - Command: `update recipe r/RECIPE i/INGREDIENT q/QUANTITY`
    - E.g.: `update recipe r/Apple Pie i/apple q/3 i/flour q/200`
8. View Help
    - Command: `help`
    - E.g.: `help`
    