# See Jian Hui's Profile Portfolio Page

## Project: Food-O-Rama

Food-O-Rama is a food wastage tracking application for Restaurant Owners to keep track of food resources. It provides
users with insights on the amount of ingredients in storage and the amount of food wasted, be it in the form of dish
waste or ingredient waste. This allows Restaurants to better plan purchases of raw ingredients and enables more
efficient allocation of said ingredients to the cooking of dishes, ultimately saving cost and increasing profits.

### Summary of Contributions

* **Code
  contributed**: [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=jhsee5&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=jhsee5&tabRepo=AY2122S1-CS2113T-W11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
* **Feature**: Add Ingredients `add ingr`
    * **What it does**: Allows the user to add a new Ingredient to the List of Ingredients in Food-O-Rama.
    * **Justification**: This feature is one of the core features of Food-O-Rama which keeps track of Ingredients.
* **Feature**: Sort Ingredients `sort ingr`
    * **What it does**: Allows the user to sort the List of Ingredients in Food-O-Rama to see in descending order by
      weight wasted, the List of Ingredients.
    * **Justification**: This feature gives the user convenience to find out the most wasted Ingredient using just one
      command.
* **Feature**: Sort Dishes `sort dish`
    * **What it does**: Allows the user to sort the List of Dishes in Food-O-Rama to see in descending order by weight
      wasted, the List of Dishes.
    * **Justification**: This feature gives the user convenience to find out the most wasted Dish using just one
      command.
* **Feature**: Random Dish Generator `rdish`
    * **What it does**: Allows the user to get a Dish name from a fixed pool of Carbohydrates, Proteins, Sauces and
      Cooking Methods.
    * **Justification**: This feature allows the user to come up with new Dish ideas to add to the restaurant's mennu.
* **Contribution to other features**: Link `link`
    * **Contribution**: Change `add constituent` command to `link`.
    * **Justification**: Makes Food-O-Rama more user-friendly due to the amount of words required to activate the
      command.
* **Contribution to other features**: Edit Ingredient Stored `edit ingr stored`
    * **Contribution**: Fix bug of the command editing wastage to accurately editing the stored weight amount.
    * **Justification**: Correct the functionality to Edit Ingredient Stored.
* **Contribution to other features**: Add JUnit5 Tests
    * **Contribution**: Implement JUnit5 Tests for `SortIngrCommand`, `SortDishCommand`, `RandomDishCommand` and
      `EditIngrStoredCommand`.
    * **Justification**: To accurately test functionalty of Commands.
* **Contribution to other features**: Add JavaDoc to Classes
    * **Contribution**: Add JavaDoc to `Food-O-Rama`, `Dish`, `ListCommand`, `RandomDishCommand`, `SetDishLimitCommand`, `SetIngrExpiryCommand`,
      `SetIngrLimitCommand`, `SortDishCommand` & `SortIngrCommand`
    * **Justification**: JavaDoc improves code readability.

<div style="page-break-after: always;"></div>

* **Contributions to UG**:
    * Add documentation for Introduction, Table of Contents, Quick Start, List of Commands and Features.
    * Assist in fixing hyperlinks in the UG.

<div style="page-break-after: always;"></div>

* **Contributions to DG**:

 * Create and assist in PUML for `SortDishCommand`.
    <p align="center">
    <img src="https://ay2122s1-cs2113t-w11-4.github.io/tp/images/sort_dish_sequence.png">
    </p>
    <center>PUML for SortDishCommand</center>
    
          
  * Create and assist in PUML for `SortIngrCommand`.
    <p align="center">
    <img src="https://ay2122s1-cs2113t-w11-4.github.io/tp/images/sort_ingr_sequence.png">
    </p>
    <center>PUML for SortIngrCommand</center>
    
    
  * Add majority content for Instructions for Manual Testing.
    
  * Assist in centering of Images/Figures.
  