# KairuiHu's Project Portfolio Page

## Overview
ExpiryEliminator aims to help young adults living by themselves manage their ingredients and recipes they can cook 
based on the ingredients they have.
The application is CLI based and is suitable for users who can type quickly and accurately.

## Summary of Contributions

### Code Contributed

- The [Reposense](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabAuthor=vincentlauhl&tabRepo=AY2122S1-CS2113-T16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&tabType=authorship)
  page shows the amount of code I have typed in terms of functionalities, documentation, and testing.

### Enhancements Implemented

#### Update recipe
- *What it does* : Allows users to update a recipe with its respective ingredients and quantities of ingredients 
   in the Recipe List. Update of a recipe involves any changes to the current recipe, including adding ingredients to 
   an existing recipe, deleting ingredients in the existing recipe, and modifying the quantities of ingredients in the 
   recipe. When adding ingredients to a recipe, it helps the user add ingredients in this recipe to the
   Ingredient Repository if it is not stored in the Ingredient Repository initially.
- *Justification* : This feature is one of the core features of the product, as it allows user to update any existing 
   recipes in the storage. With this feature, users do not have to type in all information about a 
   recipe in one command when adding the recipe to the recipe list, as they can update the recipe at any time.
- *Highlights* : The implementation of the feature was improved during the v2.0 stage of the project. It requires 
   modification to support other features and to make it more convenient for the user. It also requires some analysis 
   of the design, such as preventing users to delete an ingredient that was not stored in the recipe before, and 
   allowing users to delete an ingredient by updating the corresponding quantity to 0. A difficult part is that we do
   not allow typing in ingredients with 0 quantity in a recipe, which will trigger an exception, but by interpreting 
   the command string using the parser class, we can avoid this conflict and delete an ingredient in the recipe by 
   updating the quantity to 0.


#### View/Find a recipe
- *What it does* : Allows the user to find a recipe that is stored in the Recipe List.
- *Justification* : This feature allows user to find and view the details of a recipe if the user wants to check the 
   information about the recipe, i.e., users can check the ingredients, the corresponding quantities, and the units 
   in a recipe.


#### List recipes
- *What it does* : Helps user list all the recipes stored in the recipe list.
- *Justification* : This feature helps the user check all the recipes in the recipe list so that users can have an 
   overview about the whole recipe list.


#### Storage and loading of data
- *What it does* : Helps users automatically store the updates to the txt file once their commands are executed 
   successfully, i.e, add/remove/update of any ingredients or recipes. And all the data will be loaded back from the 
   txt files to storage once the program gets started.
- *Justification* : This feature is one of the core feature of the product, as it saves the updates of data 
   automatically. Users do not have to manually save the data after they type in the command. Additionally, the data 
   will be loaded back to the storage by reading and parsing every line in the txt files.
- *Highlights* : The design of the feature required some analysis. Saving data should be clear as the data is simply
   converted to a string output in the txt file. Loading the data from the txt file may be complicated as the scanner
   has to scan line by line to interpret the string and convert it back to a meaningful data type in the product,
   i.e., recipe, ingredient, unit, batch number, expiry date...

#### Help
- *What it does* : Display the help message to help users clarify the format of each command input and also provide
   the example command input for users to follow.
- *Justification* : This feature contains the help message which enables users to understand the objectives and formats
   of each command. Users can type in "help" to view the help message.

### Team Based Tasks
- Group discussion on the designs, features and implementations of the project.
- Add Instructions for manual testing.

### Documentation
- **User Guide**
    - Added documentations for features `update recipe`, `list recipe`, `view recipe` and `help` [#136](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/136)
- **Developer Guide**
    - Added implementation details and UML diagrams for features `list recipes`[#136](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/136)
    - Added implementation details and UML diagrams for features `view recipes`[#136](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/136)
    - Added design details and UML diagrams for `Storage` component. [#134](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/134)
    - Added manual testing.[#125](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/125)

### Review Contribution
- Suggestion given to a PR. [#24](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/24)
