# Ravindiran Rakesh's Profile Portfolio Page

## Project: Food-O-Rama
Food-O-Rama is a food wastage tracking application for
Restaurant Owners to keep track of food resources.
It provides users with insights on the amount of
ingredients in storage and the amount of food wasted,
be it in the form of dish waste or ingredient waste.
This allows Restaurants to better plan purchases of raw ingredients
and enables more efficient allocation of said ingredients to the cooking of dishes,
ultimately saving cost and increasing profits.

### Summary of Contributions

* **Code contributed**: [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=Rakesh12000&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=Rakesh12000&tabRepo=AY2122S1-CS2113T-W11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&zFR=false)
* **Feature**: Added Help function
  * **What it does**: Allows the user to view a comprehensive list of 
  Commands along with Example inputs
  * **Justification**: This feature improves the product as it makes it 
  more convenient for the User to check the list of Commands when unsure.
* **Feature**: Added Edit Name feature for Dishes and Ingredients.
    * **What it does**: Allows User to change the name of existing Dishes
  and Ingredients.
    * **Justification**: This feature improves the product as the User can 
  correct the names in case of typos or update them when the menu changes
* **Feature**: Added List feature for Dishes and Ingredients.
    * **What it does**: Allows the User to view all Dishes / Ingredients added
  so far.
    * **Justification**: This feature improve the product as it allows the user
  to view all Dishes / Ingredients and their respective details in one place.
* **Feature**: Added Delete feature for Dishes and Ingredients
    * **What it does**: Allows the User to delete a particular Dish / Ingredient
    * **Justification**: This feature improves the product as the User can remove Dishes
  that are no longer served or Ingredients that are no longer used in the restaurant
* **Feature**: Added Clear feature for Dishes, Ingredients and Both
  * **What it does**: Allows the User to clear all Dishes / Ingredients / Everything
  * **Justification**: This feature improves the product as the User can remove all
  Dishes / Ingredients when starting a new Menu
* **Enhancement added**: Index Based Searching
  * **What it does**: Allows user to update Dishes and Ingredients by searching for their Index in 
  addition to searching for their String Name
* **Enhancement added**: Exception to Numbers as Input in place of Strings
  * **What it does**: Prevents user from using Numbers as Input in places that require String Input
* **Enhancement added**: JUnit Test
  * **What it does**: Makes Code more defensive by ensuring the different Classes work as intended
* **Contribution to other features**: `UI` Class
  * Added Exception Messages for specific exception cases
  * Updated Command Summary
* **Contribution to other features**: Soft Limit
  * Added Soft Limit of 10,000kg to advise Users against adding very large numbers 
  * Added to:
    * `Add Dish Waste`
    * `Add Ingr`
    * `Add Ingr Stored`
    * `Add Ingr Wasted`
    * `Edit Dish Waste`
    * `Edit Ingr Waste`
* **Contributions to UG**:
  * Added Expected Outcome for `Help`
* **Contributions to Developer Guide**:
  * Added Preface for the various components
  * Added Product Scope 
  * Added Documentation for:
    * Design - `InputParser` class
    * Implementation - `Edit` Commands
    * Implementation - `Link` Command
    * Implementation - `Sort` Commands
  * Added PUML Sequence Diagrams for:
    * Design - `InputParser` class
    
    <p align="center">
        <img src="https://ay2122s1-cs2113t-w11-4.github.io/tp/images/input_parser_sequence.png">
      </p>
      <center>Figure 3: InputParser Sequence Diagram</center>
      <br>
    
    * Implementation - `Edit` Commands
    <p align="center">
        <img src="https://ay2122s1-cs2113t-w11-4.github.io/tp/images/edit_dish_name_sequence.png">
    </p>
    <center>Figure 14: EditDishNameCommand Sequence Diagram</center>
    <br>
    
    * Implementation - `Link` Command 
    <p align="center">
        <img src="https://ay2122s1-cs2113t-w11-4.github.io/tp/images/link_sequence.png">
    </p>
    <center>Figure 16: LinkCommand Sequence Diagram</center>
    <br>
    
    * Implementation - `Sort` Commands
  <p align="center">
      <img src="https://ay2122s1-cs2113t-w11-4.github.io/tp/images/sort_dish_sequence.png">
  </p>
  <center>Figure 18: SortDishCommand Sequence Diagram</center>
  <br>

  <p align="center">
      <img src="https://ay2122s1-cs2113t-w11-4.github.io/tp/images/sort_ingr_sequence.png">
  </p>
  <center>Figure 19: SortIngrCommand Sequence Diagram</center>
  <br>
