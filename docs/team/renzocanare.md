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

## Summary of Contributions
*Code Contributed*: [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=renzocanare&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=renzocanare&tabRepo=AY2122S1-CS2113T-W11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&zFR=false)

* **Feature**: Adding and Editing Storage or Wastage to Ingredients
    * **What it does**: Ability to add or edit weight (in kg) to the value of total storage or wastage of a particular ingredient.
    * **Justification**: This feature is a core feature of the product as the user should be able to track the amount of storage or wastage
    an ingredient to help them reduce their ingredient wastage.

* **Feature**: Setting the Expiry Date of Ingredients
    * **What it does**: Ability to set the expiry date (in dd/MM/yyyy format) of a particular ingredient.      
    * **Justification**: This feature improves the product as the user will benefit from being able to track ingredient's expiry date
    while reducing wastage.


* **Feature**: Listing All Ingredients
    * **What it does**: Ability to display all ingredients stored in the ingredients list, the total storage and wastage (in kg) of each ingredient, the expiry dates 
        and the number of day(s) to or since expiry of each ingredient.
    * **Justification**: This feature is a core feature of the product as the user should be able to see a list of their ingredients 
        to help them reduce their ingredient wastage.


* **Feature**: Finding Dishes or Ingredients with Keyword
    * **What it does**: Ability to display all dishes or ingredients that contains the given keyword in its name,
        with the corresponding storage/wastage/expiry/limit information where possible.
    * **Justification**: This feature improves the product as the user will benefit from being able to find a particular dish
        or ingredient quickly, especially if their dish or ingredient list is large.
    

* **Feature**: User Interface
    * **What it does**: Ability to display welcome messages, error messages, display messages and exit messages to the user when 
        using the application in a user-friendly interface. Utilizes the Automatic ClearScreen function to streamline the user's interface.


* **Enhancement Added:** Automatic ClearScreen Function
    * **What it does**: The ClearScreen function clears all previous text in Windows, MacOS and Linux CLI terminals automatically.
      In conjunction with the `Ui` class, the user will always be able to see an aesthetic Summary of Commands in the terminal, together with the outcomes of the
      last input command.
    * **Justification**: This enhancement improves the user-experience (UX) of our target audience, restaurant owners, as the terminal becomes easier to read and is less
      cluttered with previous commands. Such an enhancement will help restaurant owners navigate through the application faster due to the increased readability.
    * **Highlights**: There was an initial great difficulty in implementing this function as terminal manipulation was not taught in this module.
      Most of the online resources on how to clear the terminal tend to not work in the expected manner for all operating systems.
      A viable method was eventually found that could suit to our needs with flexibility between different operating systems.
    * **Credits**: [Java-Clear-Console](https://www.delftstack.com/howto/java/java-clear-console/)


* **Contribution to Other Features**: Next Line Input for Functions with Additional Parameters
    * **What it does**: Takes inputs for additional parameters such as wastage/storage weight, expiry dates and new naming on a following input prompt.
    * **Justification**: This enhancement reduces the likelihood of mistakes being made when entering a command since users will
      now only need to enter the additional parameter on the following prompt. Furthermore, the overall command length is reduced. This will increase the user's
      efficiency as the user will not have to pause in making sure lengthy command inputs are correct.


* **Contributions to team-based tasks**:
    * Managed milestones `v2.0 - v2.1`. 
    * Managed project documentation (`UserGuide`, `DeveloperGuide`, `PPP`) issues.
    

<div style="page-break-after: always;"></div>


* **Contributions to User Guide**:
    * Overall formatting and design, and cosmetic tweaks to other existing documentation.
    * Added documentation for:
        * `edit dish name`
        * `edit dish waste`
        * `set ingr expiry`
        * `edit ingr stored`
        * `edit ingr waste`
        * `Saving the data`
        * `Manipulating the data`
        * `FAQ`
      
    * Contributed to *all* other existing documentation by:
      * Formatting expected format for inputs
      * Updating expected outputs
      * Adding `Tips` and `Notes`
      * Updating Legend
      * Updating Table of Contents
      * Updating List of Commands
      * Adding Command Format Notes


<div style="page-break-after: always;"></div>


* **Contributions to Developer Guide**:
    * Overall formatting and design (including image centering and captions), and cosmetic tweaks to other existing documentation.
      
    * Added documentation for:
        * `Design - Storage: Loading Data`
        * `Design - Storage: Saving Data`
        * `Design - User-Interface Component`
        * `Implementation - Add: Adding Dishes and Ingredients`
        * `Implementation - Add: Adding Wastage to Dishes and Ingredients, Storage to Ingredients`
        * `Implementation - Terminal Refreshing / Clear Screen`
      
    * Added PUML Diagrams for (images below are **scaled down**, refer to Developer Guide for full images):
      
        * `Design - Storage: Loading Data`
          
          <p align="center">
            <img src="https://ay2122s1-cs2113t-w11-4.github.io/tp/images/storage_load_sequence.png" width="70%" height="auto">
          </p>
          <center>Figure 4: Loading Data Sequence Diagram</center>
          <br>
          
         <div style="page-break-after: always;"></div>

        * `Design - Storage: Saving Data`
          
          <p align="center">
            <img src="https://ay2122s1-cs2113t-w11-4.github.io/tp/images/storage_write_sequence.png" width="70%" height="auto">
          </p>
          <center>Figure 5: Saving Data Sequence Diagram</center>
          <br>
          
        * `Implementation - Add: Adding Dishes and Ingredients`
          
          <p align="center">
            <img src="https://ay2122s1-cs2113t-w11-4.github.io/tp/images/add_ingr_command_sequence.png" width="90%" height="auto">
          </p>
          <center>Figure 11: AddIngredientCommand Sequence Diagram</center>          
          <br> 
          
        <div style="page-break-after: always;"></div>
    
        * `Implementation - Add: Adding Wastage to Dishes and Ingredients, Storage to Ingredients`
          
          <p align="center">
          <img src="https://ay2122s1-cs2113t-w11-4.github.io/tp/images/add_dish_waste_command_sequence.png" width="90%" height="auto">
          </p>
          <center>Figure 12: AddDishWasteCommand Sequence Diagram</center>
    

