# Gordon's User Guide

## Who is Gordon?

<!-- Images -->
![Markdown Logo](https://www.denofgeek.com/wp-content/uploads/2019/10/robot-chicken-gordon-ramsay-adult-swim.jpg?resize=768%2C432)

It's me, **Gordon Ramsay v1.0**, and today, I'm gonna teach you ****** how to use the Gordon CLI application.

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

### 1. Add a recipe: `add`

Adds a new recipe to my database of recipes.

#### Format: `add RECIPE_NAME /ingredients INGREDIENTS /steps STEPS`

* The `RECIPE_NAME` can be in a natural language format.
* Each individual ingredient in `INGREDIENTS` can be separated by a '+' sign.
* Each individual step in `STEPS` can be separated by a '+' sign.

#### Example of usage: 

`add Chicken Rice /ingredients Chicken+Rice /steps Cook+Mix`

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

### 2. Delete a recipe: `delete`

Removes an existing recipe from my database of recipes.

#### Format: `delete RECIPE_INDEX`

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

- I'll show you the ingredients you need for that recipe, and the steps required to prepare it

---

### 4. Display all recipes: `list`

Shows all recipes saved in the database

#### Format: `list`

#### Example of usage:

`list`

#### Expected outcome:

```
1. Chicken Rice
2. Lemon Tea
3. McSpicy
```
#### Description of the outcome:

- I'll show you all the existing recipes saved in my database

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

Shows you the proper format of commands

#### Format: `help`

#### Example of usage:

`help`

#### Expected outcome:

```
add "recipe name" "/ingredients" 1+2+3 "steps" 1+2+3
```

#### Description of the outcome.

- I'll show you the proper format for using the `add` command, you ******!

---

### 7. Find a recipe: `find`

Searches for any recipes that contain the keyword that you have entered.

#### Format: `find KEYWORD`

#### Example of usage:

`find` fried rice

#### Expected outcome

Added fried rice recipe! Yum!

---

## Cheat sheet

Instruction | Command format
------------ | -------------
Add a recipe | `add RECIPE_NAME /ingredients INGREDIENTS /steps STEPS`
Delete a recipe | `deleteRecipe RECIPE_INDEX`
Look up a recipe | `check RECIPE_NAME`
Display all recipes | `listRecipes`
Exit Gordon | `exit`
Get some help | `help`
Find a recipe | `find`
Tag a recipe | `tag`
Untag a recipe | `untag`
List all tags | `listTags`
Delete a tag | `deleteTag`
