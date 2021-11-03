# User Guide

## Introduction

Expiry Eliminator is a desktop app for managing the freshness of ingredients in your kitchen and the recipes you want to cook, optimized for use via a Command Line Interface (CLI). If you can type fast, Expiry Eliminator can help you manage your ingredients and recipes quickly.

* Table of Contents
{:toc}

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `ExpiryEliminator` from [here](https://github.com/AY2122S1-CS2113-T16-3/tp/releases).
3. Copy the file to the folder you want to use as the home folder for this program.
4. Open a command prompt / PowerShell / terminal window and run the program with `java -jar [filepath of jar file]`.

## Features

> **Notes about the command format:**
>
> - Words in UPPER_CASE are the parameters to be supplied by the user (e.g. in `view i/INGREDIENT`, `INGREDIENT` is a parameter which can be used as `view i/Red Apple`).
> - Parameters in square brackets are optional (e.g. `[u/UNIT]`).
> - Parameters with `...` after them can be used multiple times, but must appear at least once (e.g. `q/QUANTITY...`).
> - Parameters can be in any order.
> - There must be no spaces between / and the parameters. (e.g. `u/ UNITS`).

### Adding an ingredient: `add`

Adds an ingredient to the ingredient repository.

Format: `add i/INGREDIENT [u/UNIT] [q/QUANTITY e/EXPIRY_DATE]`

- Unit is optional.
- Quantity and expiry date is optional, but you cannot have one and not the other (i.e. you either don't provide both quantity and expiry date, or you provide both quantity and expiry date).
- Expiry date must be in the format of `yyyy-mm-dd`. It is possible to add an ingredient that has already expired.
- Quantity must be a positive integer.
- Note that ingredient names are case-insensitive, and will be automatically stored in title case (e.g. `ReD aPplE` will be stored as `Red Apple`).
- Ingredient names must be unique.
- If the ingredient name already exists, an error message will be shown.

Example of usage:
- `add i/Red Apple`
- `add i/Salt u/g`
- `add i/Red Apple q/5 e/2021-10-08`
- `add i/Salt u/g q/1000 e/2021-01-01`

<br/>

### Incrementing quantities for an ingredient: `increment`

Increments the specified quantities for the specified ingredient in the ingredient repository.

Format: `increment i/INGREDIENT q/QUANTITY e/EXPIRY_DATE`

- It is possible to have multiple batches of the same ingredient with different expiry dates.
- Names are case-insensitive (e.g. `red apple` will match `Red Apple`).
- Expiry date must be in the format of `yyyy-mm-dd`. It is possible to add an ingredient that has already expired.
- Quantity must be a positive integer.
- If the ingredient name is not found, an error message will be shown.

Example of usage:
- `increment i/Red Apple q/6 e/2021-10-22`
- `increment i/Salt q/200 e/2021-10-22`

<br/>

### Decrementing quantities for an ingredient: `decrement`

Decrements the specified quantities for the specified ingredient in the ingredient repository.

Format: `decrement i/INGREDIENT q/QUANTITY`

- The batches of ingredients that expire the earliest will be decremented first.
- Names are case-insensitive (e.g. `red apple` will match `Red Apple`).
- Quantity must be a positive integer.
- If the ingredient name is not found, an error message will be shown.
- If the specified quantity is greater than the quantity of the specified ingredient in the ingredient repository, an error message will be shown.

Example of usage:
- `decrement i/Red Apple q/2`
- `decrement i/Salt q/10`

<br/>

### Updating units of an ingredient: `update units`

Updates the units of the specified ingredient in both the ingredient repository and recipe list.

Format: `update units i/INGREDIENT u/UNIT`

- The units will be updated in both the ingredient repository and recipes.
- If units is left blank, the units will be deleted (back to default value).
- There cannot be a space between / and your input units 

Example of usage:
- `update units i/salt u/grams`
- `update units i/chicken u/kilograms`
- `update units i/salt u/`

Example incorrect usage:
- `update units i/salt u/ kilograms`
- `update units i/salt u/6`

<br/>

### Listing all ingredients: `list`

Shows a list of all ingredients in the ingredient repository, along with their quantities and expiry dates.

Format: `list`

- As it is possible to have different batches of the same ingredient with different expiry dates, all batches will be shown.

Example of usage:
- `list`

<br/>

### Listing ingredients that are expiring: `list expiring`

Shows a list of all ingredients in the ingredient repository that are expiring within one week, along with their quantities and expiry dates.

Format: `list expiring`

Example of usage:
- `list expiring`

<br/>

### Listing ingredients that have expired: `list expired`

Shows a list of all ingredients, its quantity and expiry dates, that have expired.

Format: `list expired`

- Ingredient is considered to have expired if its expiry date is before the current date. 
  - E.g.: Ingredient with expiry date of 2021-01-01 is considered as expired.
- Hence, an ingredient expiring on the current day is not yet counted as expired.

Example of usage:
- `list expired`

<br/>

### Viewing a specific ingredient: `view`

Shows a specific ingredient with its quantity and expiry date.

Format: `view i/INGREDIENT`

- Names are case-insensitive (e.g.: `SalT` will match `Salt`).
- The order of the keywords matter. e.g. `Apple Red` will NOT match `Red Apple`.
- Only the full name will be matched. e.g. `Apple` will NOT match `Red Apple`.
- If the ingredient name is not found, an error message will be shown.

Example of usage:
- `view i/Salt`
- `view i/Red apple`
- `view i/Chicken`

<br/>

### Deleting an ingredient: `delete`

Deletes the specified ingredient from the ingredient repository.

Format: `delete i/INGREDIENT`

- An ingredient cannot be deleted if it is part of a recipe. To delete an ingredient, delete all recipes that contain the ingredient first.
- To clear all quantities of an ingredient in the ingredient repository, use the `decrement` command instead. That way, the ingredient can still be used as part of a recipe.
- Names are case-insensitive (e.g. `red apple` will match `Red Apple`).
- If the ingredient name is not found, an error message will be shown.

Example:
- `delete i/Red Apple`

<br/>

### Deleting all expired ingredients: `delete expired`

Removes all expired batches (not Ingredients) of ingredients from the ingredient repository.

Format: `delete expired`

- Even if all batches of an ingredient are expired, this command will not delete the ingredient entry in the repository (i.e. the ingredient will continue to exist but with zero quantity.
- Ingredient is considered to have expired if its expiry date is before the current date.
  - E.g.: Ingredient with expiry date of 2021-01-01 is considered as expired.
- Hence, an ingredient expiring on the current day is not yet counted as expired.

Example of usage:
- `delete expired`

<br/>

### Adding a recipe: `add recipe`

Adds a recipe with its respective ingredients and quantities to the recipe list.

Format: `add recipe r/RECIPE i/INGREDIENT q/QUANTITY i/INGREDIENT q/QUANTITY ...`

- Adds a recipe with the name `RECIPE` and its `INGREDIENT` with the corresponding `QUANTITY` for each ingredient. 
  `QUANTITY` must be a positive integer. It is not possible to have zero quantity.
- The ingredient and recipe names are case-insensitive, and will be automatically stored in title case 
  (E.g.: `ChiCkEN SoUP` would be recorded as `Chicken Soup`)
- The recipe is added to the recipe list.
- The ingredients and the quantity can be added in any order, but the first ingredient will correspond to the first
 quantity entered, second ingredient to second quantity entered etc.
- A recipe must have at least one ingredient.
- If there are too many ingredients in the recipe, users can add at least one ingredient first with `add recipe` and
  add the rest later on to avoid confusion
  (see [Update recipe](#updating-quantities-of-ingredients-in-a-recipe-update-recipe))
- The units of the ingredients in the recipe will follow the units of the ingredients 
  saved in the ingredient repository.
- If the ingredients of the recipe do not exist in the ingredient repository when adding the recipe, the
  ingredients will be added into the ingredient repository with zero quantity and no units (to add units, see [Updating units of an ingredient](#updating-units-of-an-ingredient-update-units)).

Example of usage:
- `add recipe r/Chicken Soup i/Chicken q/300 i/Salt q/10`
- `add recipe r/Chicken Soup i/Chicken i/Salt q/300 q/10`

Output:
- If the ingredients do not exist in the list,
  ```
      ____________________________________________________________________________________________________
       I've added this recipe:

       Chicken Soup
        - Chicken (qty: 1)
        - Salt (qty: 20)

       Now you have 3 recipe(s)

       I've also added these ingredients:

       Chicken
       Salt

       Please update the unit if required.
      ____________________________________________________________________________________________________
  ```
- If the ingredients exist in the list,
  ```
      ____________________________________________________________________________________________________
       I've added this recipe:

       Chicken Soup
       - Chicken (qty: 1)
       - Salt (qty: 20)

       Now you have 3 recipe(s)
      ____________________________________________________________________________________________________
  ```
  
  

<br/>

### Deleting a recipe: `delete recipe`

Deletes a recipe from the recipe list.

Format : `delete recipe r/RECIPE`

- Deletes a recipe with the name `RECIPE` and all its information.
- The recipe is removed from the list.
- The recipe name is case-insensitive (typing `cHiCKEn sOUP` would cause `Chicken Soup` 
  to be deleted if it’s in the list).
- The recipe name specified must exist in the list.

Example of usage:

- `delete recipe r/Chicken Soup`

Output:
- If recipe is in the list.
```
    ____________________________________________________________________________________________________
     I've deleted this recipe for you:
     
     Chicken Soup
     - Chicken (qty: 1)
     - Salt (qty: 20)
     
     Now you have 2 recipe(s)
    ____________________________________________________________________________________________________
```

<br/>

### Removing ingredients when a recipe is cooked: `cooked`

Removes certain amount of ingredients from the ingredient repository based on the recipe that is cooked.

Format: `cooked r/RECIPE`

- Removes a certain amount of ingredients from the ingredient repository according to the amount of the ingredients
  for the `RECIPE`.
- The recipe name is case-insensitive (typing `cHiCKEn sOUP` would cause the given amount of ingredients in 
  `Chicken Soup` to be removed from the ingredient repository).
- The recipe name specified must exist in the list.
- The quantities of ingredients in the ingredient repository should be sufficient to cook the recipe 
  for it to be removed.
- The batches of ingredients that expire the earliest will be remove first.
- Expired ingredients can be removed with this command so the user must be careful in managing 
  expired ingredients (see [Deleting all expired ingredients](#deleting-all-expired-ingredients-delete-expired)).

Example of usage: 
- `cooked r/Chicken Soup`

Output:
- If there are sufficient ingredients.
```
    ____________________________________________________________________________________________________
     Now you have these quantities left for your ingredients:

     Chicken (qty: 1)
     - 1 (2021-11-20)

     Salt (qty: 20)
     - 20 (2021-12-31)
    ____________________________________________________________________________________________________
```

<br/>

### Listing recipes that can be cooked: `list recipes I can cook`

Returns a list of recipes which the user can cook with the ingredients they currently have 
in their ingredient repository.

Format: `list recipes i can cook`

- There must be sufficient ingredients in the ingredient repository to cook the recipe for the recipe
  to be shown in the list.
- The recipe will be shown even if some ingredients have expired and the user will be informed 
  of the ingredients that have expired.

Example of usage: 
- `list recipes i can cook`

Output: 
- If there are no expired ingredients
```
    ____________________________________________________________________________________________________
     Here are the recipes you can cook with the ingredients you have:

     Chicken Soup
     - Chicken (qty: 1)
     - Salt (qty: 20)
    ____________________________________________________________________________________________________
```

- If there are expired ingredients
```
    ____________________________________________________________________________________________________
     Here are the recipes you can cook with the ingredients you have:

     Chicken Soup
     - Chicken (qty: 1)
     - Salt (qty: 20)

     Note that some of these ingredients have expired:

     Chicken

     Please remove them if you don't want to use them for your recipe.
    ____________________________________________________________________________________________________
```

<br/>

### Listing all recipes: `list recipes`

Returns a list of recipes that are stored in the recipe list.

Format: `list recipes`

<br/>

### Viewing a specific recipe: `view recipe`

Views a specific recipe in the recipe list, along with the corresponding ingredients and quantities.

Format: `view recipe r/RECIPE`

- The search is case-insensitive. e.g `pasta` will match `Pasta`. 
- The order of the keywords matter. e.g. `Nasi Padang` will NOT match `Padang Nasi`. 
- The input should only contain the name of the recipe. 
- Only the full name will be matched. e.g. `Chicken` will NOT match `Curry Chicken`.

Example of usage:
- `view recipe r/Curry Chicken`

<br/>

### Updating quantities of ingredients in a recipe: `update recipe`

Updates a recipe by modifying the quantity of ingredients in that recipe.

Format: `update recipe r/RECIPE i/INGREDIENT q/QUANTITY i/INGREDIENT q/QUANTITY ...`

- It is not possible to update an ingredient to be zero quantity.

Example of usage:
- `update recipe r/Apple Pie i/apple q/3 i/flour q/200`

<br/>

### Creating a shopping list of ingredients for a list of recipes: `shopping list`

Takes in multiple recipes the user wants to cook, and generates a shopping list of ingredients that
the user does not have and needs to buy.

Format: `shopping list r/RECIPE...`

- Can take in multiple recipe inputs.

Example of usage:
- `shopping list r/Chicken Soup`
- `shopping list r/Chicken Soup r/Pork Soup`

<br/>

### Viewing help: `help`

Shows a help message explaining the various commands.

Format: `help`

<br/>

### Exiting the program: `bye`

Exits the program.

Format: `bye`

<br/>

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

1. Adding an ingredient
    - Command: `add i/INGREDIENT [u/UNIT] [q/QUANTITY e/EXPIRY_DATE]`
    - E.g.: `add i/Red Apple`
    - E.g.: `add i/Salt u/g`
    - E.g.: `add i/Red Apple q/5 e/2021-10-08`
    - E.g.: `add i/Salt u/g q/1000 e/2021-01-01`
2. Incrementing quantities for an ingredient
    - Command: `increment i/INGREDIENT q/QUANTITY e/EXPIRY_DATE`
    - E.g.: `increment i/Red Apple q/6 e/2021-10-22`
    - E.g.: `increment i/Salt q/200 e/2021-10-22`
3. Decrementing quantities for an ingredient
    - Command: `decrement i/INGREDIENT q/QUANTITY`
    - E.g.: `decrement i/Red Apple q/2`
    - E.g.: `decrement i/Salt q/10`
4. Updating units of an ingredient
    - Command: `update units i/INGREDIENT u/UNIT`
    - E.g.: `update units i/salt u/kilograms`
    - E.g.: `update units i/salt u/`
5. Listing all ingredients
    - Command: `list`
6. Listing ingredients that are expiring
    - Command: `list expiring`
7. Listing ingredients that have expired
    - Command: `list expired`
8. Viewing a specific ingredient
    - Command: `view i/INGREDIENT`
    - E.g.: `view i/salt`
9. Deleting an ingredient
    - Command: `delete i/INGREDIENT`
    - E.g. `delete i/Red Apple`
10. Deleting all expired ingredients
    - Command: `delete expired`
11. Adding a recipe
    - Command: `add recipe r/RECIPE i/INGREDIENT q/QUANTITY i/INGREDIENT q/QUANTITY ...`
    - E.g.: `add recipe r/Chicken Soup i/Chicken q/300 i/Salt q/10`
    - E.g.: `add recipe r/Chicken Soup i/Chicken i/Salt q/300 q/10`
12. Deleting a recipe
    - Command : `delete recipe r/RECIPE`
    - E.g.: `delete recipe r/Chicken Soup`
13. Removing ingredients when a recipe is cooked:
    - Command: `cooked r/RECIPE`
    - E.g.: `cooked r/Chicken Soup`
14. Listing recipes that can be cooked
    - Command: `list recipes i can cook`
15. Listing all recipes
    - Command: `list recipes`
16. Viewing a specific recipe
    - Command: `view recipe r/RECIPE`
    - E.g.: `view recipe r/Curry Chicken`
17. Updating quantities of ingredients in a recipe
    - Command: `update recipe r/RECIPE i/INGREDIENT q/QUANTITY i/INGREDIENT q/QUANTITY ...`
    - E.g.: `update recipe r/Apple Pie i/apple q/3 i/flour q/200`
18. Creating a shopping list of ingredients for a list of recipes
    - Command: `shopping list r/RECIPE...`
    - E.g.: `shopping list r/Chicken Soup`
    - E.g.: `shopping list r/Chicken Soup r/Pork Soup`
19. Viewing help
    - Command: `help`
20. Exiting the program
    - Command: `bye`
