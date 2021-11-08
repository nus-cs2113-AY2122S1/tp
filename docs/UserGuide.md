# User Guide

## Introduction

Expiry Eliminator is a desktop app for managing the freshness of ingredients in your kitchen and the recipes you want to cook, optimized for use via a Command Line Interface (CLI). If you are a young adult living in your own home, and you can type fast, Expiry Eliminator can help you manage your ingredients and recipes.

* Table of Contents
{:toc}

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `ExpiryEliminator` from [here](https://github.com/AY2122S1-CS2113-T16-3/tp/releases).
3. Copy the file to the folder you want to use as the home folder for this program.
4. Open a command prompt / PowerShell / terminal window and run the program with `java -jar [filepath of jar file]`. You should see the following:

![](images/expiryeliminator-startup.png)

5. Key features to try:

   - [Add ingredients](#adding-an-ingredient-add)
   - [Increment](#incrementing-quantities-for-an-ingredient-increment)/[decrement](#decrementing-quantities-for-an-ingredient-decrement) quantities for ingredient
   - [Add recipes](#adding-a-recipe-add-recipe)
   - [List recipes you can cook](#listing-recipes-that-can-be-cooked-list-recipes-i-can-cook)
   - List ingredients that are [expiring](#listing-ingredients-that-are-expiring-list-expiring)/[expired](#listing-ingredients-that-have-expired-list-expired)
   - [Generate shopping list](#creating-a-shopping-list-of-ingredients-for-a-list-of-recipes-shopping-list)


## Features

> **Notes about the command format:**
>
> - Words in UPPER_CASE are the parameters to be supplied by the user (e.g. in `view i/INGREDIENT`, `INGREDIENT` is a parameter which can be used as `view i/Red Apple`).
> - Parameters in square brackets are optional (e.g. `i/INGREDIENT [u/UNIT]` can be used as `i/Chicken u/kg` or as `i/Chicken`).
> - Parameters with `...` after them can be used multiple times, but must appear at least once (e.g. `r/RECIPE...` can be used as `r/Apple Pie`, `r/Apple Pie r/Chicken Soup`, etc.).
> - Parameters can be in any order (e.g. if the command specifies `q/QUANTITY e/EXPIRY_DATE`, `e/EXPIRY_DATE q/QUANTITY` is also acceptable).
> - Commands are case-insensitive (e.g. if the command specifies `help`, `HELP` and `hElP` are also acceptable).
> - There must be no spaces between the `/` and the parameters. (e.g. `u/ UNITS` is not allowed).
> - If a parameter is expected only once in the command, but you specified it multiple times, an error message will be shown.
> - Extraneous parameters are not allowed. If you provide a parameter that is not accepted, an error message will be shown. 

<br/>

### Viewing help: `help`

Shows a help message explaining the various commands.

Format: `help`

<br/>

### Exiting the program: `bye`

Exits the program.

Format: `bye`

<br/>

### Adding an ingredient: `add`

Adds an ingredient to the ingredient repository.

Format: `add i/INGREDIENT [u/UNIT]`

- Note that this only adds the ingredient name (and optional unit) to the ingredient repository. If you would like to add quantities for an ingredient, please use the `increment` command instead (see [Incrementing quantities for an ingredient](#incrementing-quantities-for-an-ingredient-increment)).
- Unit is optional.
- Note that ingredient names are case-insensitive, and will be automatically stored in title case (e.g. `ReD aPplE` will be stored as `Red Apple`).
- Ingredient names and units must only contain letters (i.e. `a-Z`) and spaces. Numbers and special characters are not allowed.
- Ingredient names must be unique. If the ingredient name already exists, an error message will be shown.

Example of usage:
- `add i/Red Apple`
- `add i/Salt u/g`
- `add i/Chicken u/kg`

Output: 
- If adding ingredient for the first time:
```
add i/Chicken u/kg
    ____________________________________________________________________________________________________
     I've added this ingredient:

     Chicken (qty: 0 kg)

     Now you have 1 ingredient(s)
    ____________________________________________________________________________________________________
```

- If ingredient name already exists:
```
add i/Chicken u/kg
    ____________________________________________________________________________________________________
     Unable to add ingredient: Chicken
     You already have it in your list
     If you are trying to add quantities for an ingredient, please use the `increment` command instead
    ____________________________________________________________________________________________________
```

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
- `increment i/red apple q/10 e/2021-10-28`
- `increment i/Salt q/200 e/2021-10-22`
- `increment i/Chicken q/2 e/2021-11-15`
- `increment i/Chicken q/2 e/2021-11-08`

Output:
```
increment i/Chicken q/2 e/2021-11-08
    ____________________________________________________________________________________________________
     I've incremented this ingredient by 2 kg:

     Chicken (qty: 2 kg)
     - 2 kg (2021-11-08)
    ____________________________________________________________________________________________________
```

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

Output:
- If there is sufficient quantity to decrement:
```
decrement i/Salt q/10
    ____________________________________________________________________________________________________
     I've decremented this ingredient by 10 g:

     Salt (qty: 190 g)
     - 190 g (2021-10-22)
    ____________________________________________________________________________________________________
```
- If there is insufficient quantity to decrement:
```
decrement i/Salt q/1000
    ____________________________________________________________________________________________________
     Sorry, you currently only have 190 g of this ingredient.
     You cannot decrease it by 1000 g.
    ____________________________________________________________________________________________________
```

<br/>

### Updating units of an ingredient: `update units`

Updates the units of the specified ingredient in both the ingredient repository and recipe list.

Format: `update units i/INGREDIENT u/UNIT`

- The units will be updated in both the ingredient repository and recipes.
- If units is left blank, the units will be deleted (back to default value).
- There cannot be a space between `/` and your input units.

Example of usage:
- `update units i/salt u/grams`
- `update units i/chicken u/kilograms`
- `update units i/pork u/kg`
- `update units i/salt u/`

Example of incorrect usage:
- `update units i/salt u/ kilograms`
- `update units i/salt u/6`

Output: 
- If a new unit is specified:
```
update units i/chicken u/kilograms
    ____________________________________________________________________________________________________
     The units for this ingredient has been updated to kilograms.
    ____________________________________________________________________________________________________
```

- If the new unit is specified as blank:
```
update units i/salt u/
    ____________________________________________________________________________________________________
     The units for this ingredient has been deleted
    ____________________________________________________________________________________________________
```

<br/>

### Listing all ingredients: `list`

Shows a list of all ingredients in the ingredient repository, along with their quantities and expiry dates.

Format: `list`

- As it is possible to have different batches of the same ingredient with different expiry dates, all batches will be shown.

Example of usage:
- `list`

Output:
```
     ____________________________________________________________________________________________________
     Here are the ingredients in your list:

     Chicken (qty: 4 kg)
     - 2 kg (2021-11-08)
     - 2 kg (2021-11-15)

     Red Apple (qty: 10)
     - 10 (2021-10-28)

     Salt (qty: 200 kilograms)
     - 200 kilograms (2021-10-22)

     You have a total of 3 ingredient(s)
    ____________________________________________________________________________________________________
```

<br/>

### Listing ingredients that are expiring: `list expiring`

Shows a list of all ingredients in the ingredient repository that are expiring within one week, along with their quantities and expiry dates.

Format: `list expiring`

Example of usage:
- `list expiring`

Output:
- If no expiring ingredients:
```
    ____________________________________________________________________________________________________
     Here are the expiring ingredients in your list:
    ____________________________________________________________________________________________________
```

- If have expiring ingredients:
```
    ____________________________________________________________________________________________________
     Here are the expiring ingredients in your list:

     Chicken (qty: 2 kg)
     - 2 kg (2021-11-08)
    ____________________________________________________________________________________________________
```

<br/>

### Listing ingredients that have expired: `list expired`

Shows a list of all ingredients, its quantity and expiry dates, that have expired.

Format: `list expired`

- Ingredient is considered to have expired if its expiry date is before the current date. 
  - E.g.: Ingredient with expiry date of 2021-01-01 is considered as expired.
- Hence, an ingredient expiring on the current day is not yet counted as expired.

Example of usage:
- `list expired`

Output:
- If no expired ingredients:
```
    ____________________________________________________________________________________________________
     Here are the expired ingredients in your list:
    ____________________________________________________________________________________________________
```

- If have expired ingredients:
```
    ____________________________________________________________________________________________________
     Here are the expired ingredients in your list:

     Red Apple (qty: 10)
     - 10 (2021-10-28)

     Salt (qty: 200 kilograms)
     - 200 kilograms (2021-10-22)
    ____________________________________________________________________________________________________
```

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

Output:
- If ingredient exists:
```
view i/Chicken
    ____________________________________________________________________________________________________
     Here is the ingredient in your list:

     Chicken (qty: 4 kg)
     - 2 kg (2021-11-08)
     - 2 kg (2021-11-15)
    ____________________________________________________________________________________________________
```

<br/>

### Deleting an ingredient: `delete`

Deletes the specified ingredient from the ingredient repository.

Format: `delete i/INGREDIENT`

- Note that this command should only be used if you no longer want to use this ingredient anymore (e.g. you accidentally added this ingredient due to a typo). If you merely want to decrease the quantity to 0 but keep the ingredient in your repository, please use the `decrement` command instead (see [Decrementing quantities for an ingredient](#decrementing-quantities-for-an-ingredient-decrement)). That way, the ingredient can still be used as part of a recipe.
- An ingredient cannot be deleted if it is part of a recipe. To delete an ingredient, delete all recipes that contain the ingredient first.
- Names are case-insensitive (e.g. `red apple` will match `Red Apple`).
- If the ingredient name is not found, an error message will be shown.

Example:
- `delete i/Red Apple`

Output:
```
delete i/red appel
    ____________________________________________________________________________________________________
     I've deleted this ingredient for you:
     Red Appel (qty: 0)

     Now you have 0 ingredient(s)
    ____________________________________________________________________________________________________
```

<br/>

### Deleting all expired ingredients: `delete expired`

Removes all expired batches (not the entire ingredient) of ingredients from the ingredient repository.

Format: `delete expired`

- Even if all batches of an ingredient are expired, this command will not delete the ingredient entry in the repository (i.e. the ingredient will continue to exist but with zero quantity.
- Ingredient is considered to have expired if its expiry date is before the current date.
  - E.g.: Ingredient with expiry date of 2021-01-01 is considered as expired.
- Hence, an ingredient expiring on the current day is not yet counted as expired.

Example of usage:
- `delete expired`

Output:
- If there are expired ingredients:
```
    ____________________________________________________________________________________________________
     All expired ingredients have been deleted!
    ____________________________________________________________________________________________________
```

- If there are no expired ingredients:
```
    ____________________________________________________________________________________________________
     You do not have expired ingredients!
    ____________________________________________________________________________________________________
```
<br/>

### Adding a recipe: `add recipe`

Adds a recipe with its respective ingredients and quantities to the recipe list.

Format: `add recipe r/RECIPE i/INGREDIENT... q/QUANTITY...`

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
  (see [Updating recipe](#updating-recipe-update-recipe))
- The units of the ingredients in the recipe will follow the units of the ingredients 
  saved in the ingredient repository.
- If the ingredients of the recipe do not exist in the ingredient repository when adding the recipe, the
  ingredients will be added into the ingredient repository with zero quantity and no units (to add units, see [Updating units of an ingredient](#updating-units-of-an-ingredient-update-units)).

Example of usage:
- `add recipe r/Chicken Soup i/Chicken q/300 i/Salt q/10`
- `add recipe r/Chicken Soup i/Chicken i/Salt q/300 q/10`
- `add recipe r/Pork Soup i/Pork q/300 i/Salt q/10`

Output:
- If the ingredients do not exist in the list,
 ```
add recipe r/Chicken Soup i/Chicken q/1 i/Salt q/20
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
- If the ingredients already exist in the list,
```
add recipe r/Chicken Soup i/Chicken q/1 i/Salt q/20
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
```
delete recipe r/Chicken Soup
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
- If there are sufficient ingredients:
```
cooked r/Chicken Soup
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

Output:
- If the Recipe List is empty
```
    ____________________________________________________________________________________________________
     The recipe list is currently empty!
    ____________________________________________________________________________________________________
```

- If the Recipe List is not empty
```
    ____________________________________________________________________________________________________
     Here are the recipes in your recipe list:

     Chicken Soup
     - Chicken (qty: 10)
     - Salt (qty: 5)

     Pork Soup
     - Pork (qty: 10)
     - Salt (qty: 5)

     You have a total of 2 recipe(s)
    ____________________________________________________________________________________________________
```

<br/>

### Viewing a specific recipe: `view recipe`

Views a specific recipe in the recipe list, along with the corresponding ingredients and quantities.

Format: `view recipe r/RECIPE`

Example of usage:
- `view recipe r/Curry Chicken`

Output:
```
view recipe r/Pork Soup
    ____________________________________________________________________________________________________
     Here is the recipe in your recipe list:

     Pork Soup
     - Pork (qty: 10)
     - Salt (qty: 5)
    ____________________________________________________________________________________________________
```

<br/>

### Updating recipe: `update recipe`

Updates a recipe by modifying the quantity of ingredients in that recipe, or simply adding or deleting ingredients in the recipe.

Format: `update recipe r/RECIPE i/INGREDIENT... q/QUANTITY...`

- You can modify the quantities of ingredients in a recipe.
- You can also add a new ingredient that was previously not in the recipe.
- You can also delete an ingredient from the recipe by setting the quantity to be zero (i.e. `q/0`).
- Note that it is not possible to delete ingredients from the recipe if there is only 1 ingredient left.

Example of usage:
- `update recipe r/Apple Pie i/apple q/3 i/flour q/200`
- `update recipe r/Apple Pie i/flour q/0`

Output:
- If updating a recipe by modifying the quantity of ingredients
```
update recipe r/Chicken Soup i/Chicken q/500
    ____________________________________________________________________________________________________
     I've updated this recipe:

     Chicken Soup
     - Chicken (qty: 500)
     - Salt (qty: 5)


     You may want to use the 'list recipes' command to check the whole recipe list.
    ____________________________________________________________________________________________________
```

- If updating a recipe by deleting an ingredient in this recipe (not the last ingredient)
```
update recipe r/Chicken Soup i/Cabbage q/0
    ____________________________________________________________________________________________________
     I've updated this recipe:

     Chicken Soup
     - Chicken (qty: 500)
     - Salt (qty: 5)


     You may want to use the 'list recipes' command to check the whole recipe list.
    ____________________________________________________________________________________________________
```

- If updating a recipe by deleting an ingredient in this recipe (only 1 ingredient left)
```
update recipe r/Chicken Soup i/chicken q/0
    ____________________________________________________________________________________________________
     Unable to update this recipe:

     Chicken Soup

     Why update fails:
     There should be at least one ingredient in the recipe.
     But your command may result in a recipe without any ingredients.
     Therefore, please add another ingredient first if you still want to delete this ingredient.
    ____________________________________________________________________________________________________
```

- If updating a recipe by deleting an ingredient which does not exist in the recipe
```
update recipe r/Chicken Soup i/dummyInput q/0
    ____________________________________________________________________________________________________
     Unable to update this recipe:

     Chicken Soup

     No matching recipes or ingredients found, please check your input again.
    ____________________________________________________________________________________________________
```

<br/>

### Creating a shopping list of ingredients for a list of recipes: `shopping list`

Takes in multiple recipes the user wants to cook, and generates a shopping list of ingredients that
the user does not have and needs to buy.

Format: `shopping list r/RECIPE...`

- Can take in multiple recipe inputs.

Example of usage:
- `shopping list r/Chicken Soup`
- `shopping list r/Chicken Soup r/Pork Soup`

Output:
- If recipe exists:
```
shopping list r/Chicken Soup r/Pork Soup
    ____________________________________________________________________________________________________
     Here is your shopping list!


     Chicken (qty: 296 kg)
     Pork (qty: 300)
     Salt (qty: 20 kilograms)
    ____________________________________________________________________________________________________
```

- If at least one recipe does not exist:
```
shopping list r/Chicken Soup r/Pork Soup
    ____________________________________________________________________________________________________
     Sorry. One or more of your recipes are not found!
    ____________________________________________________________________________________________________
```

<br/>

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Download the app on the other computer. Then, copy the data from the original computer (i.e. `data` folder in the `ExpiryEliminator` home folder) and overwrite the data in the `ExpiryEliminator` home folder on the other computer.

## Command Summary

1. Viewing help
    - Command: `help`
2. Exiting the program
    - Command: `bye`
3. Adding an ingredient
    - Command: `add i/INGREDIENT [u/UNIT]`
    - E.g.: `add i/Red Apple`
    - E.g.: `add i/Salt u/g`
4. Incrementing quantities for an ingredient
    - Command: `increment i/INGREDIENT q/QUANTITY e/EXPIRY_DATE`
    - E.g.: `increment i/Red Apple q/6 e/2021-10-22`
    - E.g.: `increment i/Salt q/200 e/2021-10-22`
5. Decrementing quantities for an ingredient
    - Command: `decrement i/INGREDIENT q/QUANTITY`
    - E.g.: `decrement i/Red Apple q/2`
    - E.g.: `decrement i/Salt q/10`
6. Updating units of an ingredient
    - Command: `update units i/INGREDIENT u/UNIT`
    - E.g.: `update units i/salt u/kilograms`
    - E.g.: `update units i/salt u/`
7. Listing all ingredients
    - Command: `list`
8. Listing ingredients that are expiring
    - Command: `list expiring`
9. Listing ingredients that have expired
    - Command: `list expired`
10. Viewing a specific ingredient
     - Command: `view i/INGREDIENT`
     - E.g.: `view i/salt`
11. Deleting an ingredient
     - Command: `delete i/INGREDIENT`
     - E.g. `delete i/Red Apple`
12. Deleting all expired ingredients
    - Command: `delete expired`
13. Adding a recipe
    - Command: `add recipe r/RECIPE i/INGREDIENT... q/QUANTITY...`
    - E.g.: `add recipe r/Chicken Soup i/Chicken q/300 i/Salt q/10`
    - E.g.: `add recipe r/Chicken Soup i/Chicken i/Salt q/300 q/10`
14. Deleting a recipe
    - Command : `delete recipe r/RECIPE`
    - E.g.: `delete recipe r/Chicken Soup`
15. Removing ingredients when a recipe is cooked:
    - Command: `cooked r/RECIPE`
    - E.g.: `cooked r/Chicken Soup`
16. Listing recipes that can be cooked
    - Command: `list recipes i can cook`
17. Listing all recipes
    - Command: `list recipes`
18. Viewing a specific recipe
    - Command: `view recipe r/RECIPE`
    - E.g.: `view recipe r/Curry Chicken`
19. Updating quantities of ingredients in a recipe
    - Command: `update recipe r/RECIPE i/INGREDIENT... q/QUANTITY...`
    - E.g.: `update recipe r/Apple Pie i/apple q/3 i/flour q/200`
20. Creating a shopping list of ingredients for a list of recipes
    - Command: `shopping list r/RECIPE...`
    - E.g.: `shopping list r/Chicken Soup`
    - E.g.: `shopping list r/Chicken Soup r/Pork Soup`
