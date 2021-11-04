# Renzo Canare's Profile Portfolio Page

## Project: Food-O-Rama
Food-O-Rama is a food wastage tracking application for
Restaurant Owners to keep track of food resources.
It provides users with insights on the amount of
ingredients in storage and the amount of food wasted,
be it in the form of dish waste or ingredient waste.
This allows Restaurants to better plan purchases of raw ingredients
and enables more efficient allocation of said ingredients to the cooking of dishes,
ultimately saving cost and increasing profits.

Food-O-Rama is a food wastage tracking application for Restaurant Owners to keep track of food resources. 
It provides users with insights on the amount of ingredients in storage and the amount of food wasted, be it in the form of dish waste or ingredient waste. 
This allows Restaurants to better plan purchases of raw ingredients and enables more efficient allocation of said ingredients to the cooking of dishes, ultimately saving cost and increasing profits.

## Summary of Contributions
*Code Contributed*: [Renzo's RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=renzocanare&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=renzocanare&tabRepo=AY2122S1-CS2113T-W11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&zFR=false)

* **Main Features Implemented:**
    * <u>Adding Storage and Wastage to Ingredients</u>
        * Ability to add weight (in kg) to the value of total storage of a particular ingredient.
        * Ability to add weight (in kg) to the value of total wastage of a particular ingredient. 
    * <u>Setting the Expiry Date of Ingredients</u>
        * Ability to set the expiry date (in dd/MM/yyy format) of a particular ingredient.
    * <u>Listing All Ingredients</u>
        * Ability to display all ingredients stored in the ingredients list.
        * Ability to display the weight (in kg) of the total storage of each ingredient in the ingredients list.
        * Ability to display the weight (in kg) of the total wastage of each ingredient in the ingredients list.
        * Ability to display the expiry date of each ingredient in the ingredients list, and the number of day(s) to expiry or the number of day(s) since expiry.
    * <u>Finding Dishes and Ingredients with Keywords</u>
        * Ability to display all dishes stored in the dish list that contains the given keyword in its name.
        * Ability to display all ingredients stored in the ingredient list that contains the given keyword in its name.
        * Ability to display the corresponding information of each dish/ingredient, similar to the `list dish`/`list ingr` command.
    * <u>User Interface</u>
        * Ability to display welcome messages, error messages, display messages and exit messages to the user when using the application.
        * Ability to navigate the application through a user-friendly CLI interface.
    

* **Enhancements Added:**
    * <u>Automatic ClearScreen Function</u>
        * *What It Does:* The ClearScreen function, under the `ClearScreen` class, clears all previous text in Windows, MacOS and Linux CLI terminals.
          In conjunction with the `Ui` class, the user will always be able to see an aesthetic Summary of Commands in the terminal, together with the outcomes of the
          last input command. All commands before the last command are erased from the view of the user.
        * *Justification:* This enhancement improves the user-experience (UX) of our target audience, restaurant owners, as the terminal becomes easier to read and is less
          cluttered with previous commands. Such an enhancement will help restaurant owners navigate through the application faster due to the increased readability.
        * *Highlights:* There was an initial great difficulty in implementing this function as terminal manipulation was not taught in this module. 
          Furthermore, many of the online resources on how to clear the terminal tend to not work in the expected manner for all operating systems.
          However, after long research, I learned a viable method that could suit to our needs with flexibility between different operating systems.
        * *Credits:* [Java-Clear-Console](https://www.delftstack.com/howto/java/java-clear-console/)
    

* **Contribution to Other Features**:
    * <u>Next Line Input for Functions with Specific Parameters</u>
        * *What it does:*
        * *Justification:*
        * *Highlights:*  


* **Contributions to UG**:
* **Contributions to DG**:
* **Contributions to team-based tasks**:
* **Review/mentoring contributions**:
* **Contributions beyond the project team**:
