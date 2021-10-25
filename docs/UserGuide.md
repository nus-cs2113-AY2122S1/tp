# Gordon's User Guide

## Contents
* [Who is Gordon?](#who-is-gordon)
* [How do you start Gordon?](#how-do-you-start-gordon)
* [What can Gordon do?](#what-can-gordon-do)
  1. [Add a recipe](#1-add-a-recipe-addrecipe)
  1. [Delete a recipe](#2-delete-a-recipe-deleterecipe)
  1. [Look up a recipe](#3-look-up-a-recipe-check)
  1. [Display all recipes](#4-display-all-recipes-listrecipe)
  1. [Exit Gordon](#5-exit-gordon-exit)
  1. [Lend a hand](#6-lend-a-hand-help)
  1. [Set attributes of a recipe](#7-set-attributes-of-a-recipe-set)
  1. [Find a recipe by attribute](#8-find-a-recipe-by-attribute-find)
* [Cheat sheet](#cheat-sheet)  

## Who is Gordon?

<!-- Images -->
![Markdown Logo](https://www.denofgeek.com/wp-content/uploads/2019/10/robot-chicken-gordon-ramsay-adult-swim.jpg?resize=768%2C432)

It's me, **Gordon v2.0**, and today, I'm gonna teach you donkeys how to use the Gordon CLI application.

I am a CLI-based recipe database application, and I can help you to;
1) Store your recipes
2) Check them when you're ready to cook
3) Organise your collection of recipes
4) Delete recipes like the chicken you cooked so raw I can still hear it clucking

It's absolutely bonkers. Now let's get down to business.

## How do you start Gordon?

1. Ensure that you have Java 11 or above installed.
1. Checkout the latest version of **Gordon** [here](https://github.com/AY2122S1-CS2113T-W13-2/tp).
1. Copy the absolute path of the Gordon.jar file, `ABSOLUTE_PATH`
1. In your terminal, type `java -jar ABSOLUTE_PATH` to run Gordon

## What can Gordon do?

### 1. Add a recipe: `addRecipe`

Adds a new recipe to my database of recipes.

#### Format: `addRecipe RECIPE_NAME /ingredients INGREDIENTS /steps STEPS`

* The `RECIPE_NAME` can be in a natural language format.
* Each individual ingredient in `INGREDIENTS` can be separated by a '+' sign.
* Each individual step in `STEPS` can be separated by a '+' sign.
* You cannot add two recipes with the same name.

#### Example of usage: 

`addRecipe Chicken Rice /ingredients Chicken+Rice /steps Cook+Mix`

#### Expected outcome:

```
Added Chicken Rice recipe! Yum!
Chicken Rice
Ingredients needed: 
1. Chicken
2. Rice 
Method: 
1. Cook
2. Mix
```

#### Description of the outcome:

- I'll let you know that the recipe has been added
- I'll list down the ingredients and steps required for said recipe
- Finally some good ******** food...
    
---

### 2. Delete a recipe: `deleteRecipe`

Removes an existing recipe from my database of recipes.

#### Format: `deleteRecipe RECIPE_INDEX`

* The `RECIPE_INDEX` must be a positive integer representing the index of the recipe you want to remove.

#### Example of usage:

`delete 1`

#### Expected outcome:

```
OK! The recipe has been deleted from your cookbook.
```

#### Description of the outcome:
- I'll let you know that the recipe has been removed
- You can type `list` to confirm if the correct recipe has been removed

---

### 3. Look up a recipe: `check`

Prints the details of the specified recipe

#### Format: `check RECIPE_NAME`

* The `RECIPE_NAME` must be a valid existing recipe.

#### Example of usage:

`check Chicken Rice`

#### Expected outcome:

```
Finding recipes called Chicken Rice.....
--------------------
Chicken Rice
Ingredients needed: 
1. Chicken
2. Rice 
Method: 
1. Cook
2. Mix
--------------------
```

#### Description of the outcome.

- I'll show you the ingredients you need for that recipe, and the steps required to prepare it.

---

### 4. Display all recipes: `listRecipe`

Shows all recipes saved in the database

#### Format: `list`

#### Example of usage:

`listRecipe`

#### Expected outcome:

```
1. Chicken Rice
2. Lemon Tea
3. McSpicy
```
#### Description of the outcome:

- I'll show you all the existing recipes saved in my database.

---

### 5. Exit Gordon: `exit`

Leaves the program

#### Format: `exit`

#### Example of usage:

`exit`

#### Expected outcome:

```
Bye bye!
```

#### Description of the outcome:

- Pack your bags; you're off the show

---

### 6. Lend a hand: `help`

Shows you the proper format of commands.

#### Format: `help`

#### Example of usage:

`help`

#### Expected outcome:

```
1. Add a recipe: addRecipe \"recipe name\" \"/ingredients\" 1+2 \"/steps\" 1+2"
2. Delete a recipe: deleteRecipe \"Index of recipe\""
3. List all your recipes: listRecipes"
4. Find a recipe: find \"Keyword\""
5. Check a specific recipe: check \"Name of Recipe\""
6. Tag a recipe: tag \"/ recipeName\" \"/ tagName1 + tagName2 + ...\""
7. Untag a recipe: untag \"/ recipeName\" \"/ tagName1 + tagName2 + ...\""
8. List all tags: listTags"
9. Help me: help
```

#### Description of the outcome.

- I'll show you the proper format for using the `add` command, you ******!

---

### 7. Set attributes of a recipe: `set`

Sets the attributes of recipes, e.g. time needed, calories etc.

**Format:** `set RECIPE_NAME /ATTRIBUTE VALUE`

* The program first looks for the recipe using `RECIPE_NAME`, similar to find.
* `ATTRIBUTE` can be any one of the following:
  * Calories (`VALUE` must be an integer)
  * Difficulty (`VALUE` must be among None, Easy, Medium and Hard)
  * Price (`VALUE` must be a decimal)
  * Time (`VALUE` must be two integers, separated by a comma)
* For calories, price and time, changing the `VALUE` to -1 will hide it from the recipe 
* Any values below -1 are not accepted.
* `ATTRIBUTE` is not case-sensitive.

#### Examples of usage:
 
`set Chicken Rice /calories 350`  
`set Chicken Rice /difficulty hard`  
`set Chicken Rice /price $3.00`  
`set Chicken Rice /time 20, 40`

```
Setting calories...
Calories set successfully.
Setting difficulty...
Difficulty set successfully.
Setting price...
Price set successfully.
Setting times...
Times set successfully.
```

---

### 8. Find a recipe by attribute: `find`

Finds recipes by their attributes, e.g. time needed, calories etc.

#### Format: `find /ATTRIBUTE VALUE`

* The program automatically sorts the results from greatest to smallest `VALUE` if applicable.
* If the `ATTRIBUTE` of any recipe is not set, it will show up in the results.

#### Example of usage:

`find /calories 400`  
`find /difficulty normal`  
`find /price $5.00`  
`find /time 30`

#### Expected outcome

```
Searching by calories...
1. Chicken Rice (Calories (kcal): 350)
2. Caprese Salad (Calories (kcal): 150)
Searching by difficulty...
1. Cookies (Difficulty: Normal)
Searching by price...
1. Chicken Rice (Price: $3.00)
2. Cookies (Price: $1.50)
Searching by total time...
1. Cookies (Total time: 30)
2. Caprese Salad (Total time: 10)
```

---

## Cheat sheet

Instruction | Command format
------------ | -------------
Add a recipe | `addRecipe RECIPE_NAME /ingredients INGREDIENTS /steps STEPS`
Delete a recipe | `deleteRecipe RECIPE_INDEX`
Look up a recipe | `check RECIPE_NAME`
Display all recipes | `listRecipes`
Exit Gordon | `exit`
Get some help | `help`
Set recipe attributes | `set RECIPE_NAME /ATTRIBUTE VALUE`
Find a recipe | `find /ATTRIBUTE VALUE`
Tag a recipe | `tag`
Untag a recipe | `untag`
List all tags | `listTags`
Delete a tag | `deleteTag`
