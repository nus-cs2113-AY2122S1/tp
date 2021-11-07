# ngoivanessa - Project Portfolio Page

### Project: SITUS (Smart Inventory Tracking and Updating System)

The Smart Inventory Tracking and Updating System (SITUS) is a desktop app
for tracking ingredient inventory designed for restaurant/ F&B inventory managers.
Users interact with in through a CLI, and is written in Java.

Given below are my contributions to the project:

* **Code Contributed:** You can view the code [here](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=t09-3&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=ngoivanessa&tabRepo=AY2122S1-CS2113T-T09-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
* **Enhancements Implemented:**
    1. `add` feature 
       - **What it does**: Allows users to add ingredients based on the defined parameters (name, amount and expiry date)
       - **Rationale**: This is an extremely important feature as it will allow users to store their inventory inside SITUS.  
    2. `IngredientGroup` class
       - **What it does**: Stores all entries related to a single ingredient in a single `IngredientGroup`. 
       For example, 2 batches of carrots with different expiry dates are considered as 2 different entries, yet they are grouped a single `IngredientGroup` as they are the same ingredient.
       - **Rationale**: This allows users to accurately track different batches of the same ingredient. 
       Hence, the restaurant aware of the different expiry dates, reducing food wastage which is a major problem in restaurants. 
    3. `IngredientList` class
       - **What it does**: Stores all ingredients in the entire inventory. It is made up of multiple `IngredientGroup`
       - **Rationale**: This efficiently collates the inventory of the entire restaurant together. 
       This makes it easier to perform commands at the entire inventory level, such as list or find. 
    4. Expiry Date feature
        - **What it does**: Converts the expiry data parameter that is initially read in as String to LocalDate type. 
        - **Rationale**: This allows for an extensive list of operations to be done on the expiry dates, such as comparing between 2 dates or subtraction between dates. 
    5. Others
       * toString() methods in `Ingredient` and `IngredientGroup` for accurate printing of the inventory list. 
    
* **User Guide:** You can find the UG [here](https://ay2122s1-cs2113t-t09-3.github.io/tp/UserGuide.html)
    * Contributions:
        * Add Command
        * Modified code blocks of example outputs 
        * Edited overall language and style to ensure correctness and uniformity 
* **Developer Guide:** You can find the DG [here](https://ay2122s1-cs2113t-t09-3.github.io/tp/DeveloperGuide.html)
    * Contributions:
        * Edited the `style.puml` file for the diagrams.
        * Added the description of the `IngredientGroup` component
        * Described implementation of Adding Ingredients (Section 4.1)
        * Created sequence diagram for `add` feature
* **Team Tasks:**
    * Contributions:
        * Participated in setting up of team repository
        * Wrote documentation for methods in Java code
        * Initiated discussions on possible features to further improve SITUS
* **Community:** Reported Bugs during PE (Dry-Run and Actual)
