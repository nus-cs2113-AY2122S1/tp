# Sridharan Arvind Srinivasan's Project Portfolio Page

## Project: Foodorama

Food-O-Rama is a food wastage tracking application for Restaurant Owners to keep track of food resources. It provides
users with insights on the amount of ingredients in storage and the amount of food wasted, be it in the form of dish
waste or ingredient waste. This allows Restaurants to better plan purchases of raw ingredients and enables more
efficient allocation of said ingredients to the cooking of dishes, ultimately saving cost and increasing profits.

### Summary of Contributions

* **Code contributed**: [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=Dniv-ra&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=Dniv-ra&tabRepo=AY2122S1-CS2113T-W11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Feature**: Implement `link` feature
    * **What it does**: Allows the user to link an existing ingredient to an existing dish so that any wastage incurred
      on a particular dish reflects on the constituent ingredient as well
    * **Justification**: This feature improves the product as it helps the user consolidate all the wastage at one
      point to have a better understanding of where the losses stem from.

* **Feature**: Implement `limit` feature for dishes and ingredients
    * **What it does**: Allows the user to set a limit for wastage of existing dishes/ingredients and prompts them when
      the wastage exceeds the limit set
    * **Justification**: This feature improves the product as the user does not have to actively look for the wastage
      and instead can focus on just providing the data for foodorama to work with.

* **Feature**: Implement `graph` feature
    * **What it does**: Allows the user to view the wastage of dishes and ingredients in a graphical format
    * **Justification**: This feature improves the product as the user does not have to read through a long list of
      numbers and instead can get a more visually appealing form of the data, helping the user get a better
      understanding of the data more intuitively. This also mitigates one of the disadvatages of cli with a basic form
      of graphical output.

* **Feature**: Implement `InputParser`
    * **What it does**: Separates the components of the user input so that the command can access the correct parameters
    * **Justification**: This allows the users commands to be properly interpreted so that the outputs are not skewed

* **Feature**: Implement storage of data 
    * **What it does**: Stores data during runtime to a text file and reads text file to retrieve data when application is rerun
    * **Justification**: This allows the user to carry forward data across sessions and makes it easier for the user to pick up where they left off. They are 
    also capable of modifying the text files to manually edit the data without the assistance of Foodorama

* **Enhancement added**: Input validation to allow both index and string commands
    * **What it does**: Modified to allow the user to enter either a string to search based on name or an index to search based on position in the 
    list for the edit and add commands without clashing and unexpected outcomes
    * **Justification**: This gives the user greater freedom to choose how they want to identify their ingredients / dishes. This also allows them to use a short index instead of a long name

* **Enhancement added**: Input validation to allow both index and string commands
    * **What it does**: Allows the user to enter both types of input as a parameter for the edit and add commands without clashing and unexpected outcomes
    * **Justification**: This gives the user greater freedom to choose how they want to identify their ingredients / dishes. This also allows them to use a short index instead of a long name

* **Contribution to other features**: Code Defensiveness: Wrote JUnit tests and exceptions to improve code defensiveness.
  * **Justification**: Helps keep the code more bug free and prevents unexpected outcomes from occurring during runtime

* **Contribution to other features**: Documentation: Wrote documentation for some command classes and the `IngredientList`, `Storage` and `InputParser` classes
    * **Justification**: Helps improve code readability

* **Contributions to team-based tasks**:
    * Produced the skeletal code for Foodorama
    * Handled the v1.0 release on GitHub
    * Handled the v2.0 release on GitHub

<div style="page-break-after: always;"></div>

* **Contributions to UG**:
    * Added documentation for `limit` feature.
    * Added documentation for `graph` feature.

* **Contributions to DG**:
    * Added `Parser` component explanation.
    * Added `Graph` component explanation.
    * Added `Input Validation` component explanation.
    * Added `Storage` component explanation.
    * Drafted UML Diagrams for `General Flow`, `Command Abstraction`, `Set` and `Input Vaidation`.
    * Helped with general formatting

