# Nishant Rai - Project Portfolio Page

## Project: SITUS (Smart Inventory Tracking and Updating System)

The Smart Inventory Tracking and Updating System (SITUS) is a desktop app
for tracking ingredient inventory designed for restaurant/ F&B inventory managers.
Users interact with in through a CLI, and is written in Java.

### Summary of Contributions

* **Code Contribution**: My contributed code can be viewed [here.](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=t09-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=nishantrai-nus&tabRepo=AY2122S1-CS2113T-T09-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
* **Enhancements Implemented**:
  1. UI Features
     * **What it does**: Receives input from the user and outputs messages if the command runs successfully or 
     if an error occurred.
     * **Rationale**: Required for users to be able to use the application and to know whether their commands are 
     working properly or not.
  2. `help` Feature
     * **What it does**: Prints a help message to the user. 
     * **Rationale**: Allows users to view all SITUS commands with their respective formats without having
     to constantly refer to the User Guide.
  3. `find` Feature
     * **What it does**: Allows users to search for ingredients in their inventory by name(s). Multiple keywords
     to search for can be entered.
     * **Rationale**: Saves users from having to look through the entire list for ingredients they want to check stock 
     of, especially if there are a large amount of ingredients in the list.
  4. Improvement to `delete` and `update` features
     * **What it does**: Initially, `delete` and `update` were implemented with the following command formats:
       * `delete` (or `update`) `n/NAME a/AMOUNT e/EXPIRY_DATE`
     * This was then improved on using Vanessa's new ingredient group implementation, allowing the following formats:
       * `delete GROUP_INDEX.INGREDIENT_INDEX` (e.g. `delete 1.2`)
       * `update GROUP_INDEX.INGREDIENT_INDEX a/AMOUNT` (e.g. `update 1.2 a/40`)
     * **Rationale**: Saves time having to repeatedly type the expiry date of an ingredient (as well as having to find the ingredient's
     expiry date in the first place). After this implementation, the expiry date of an ingredient only has to be typed when adding the
     ingredient to the list.
* **User Guide Contributions** (found [here](https://ay2122s1-cs2113t-t09-3.github.io/tp/UserGuide.html)):
  * Contents Section
  * Section on how to use the UG
  * Help Command Section
  * Find Command Section
  * Part of Testing Features Section
  * Command Summary Section
  * Overview for most sections and features (first paragraph of the sections/subsections)
  * Use of "you" language 
* **Developer Guide Contributions** (found [here](https://ay2122s1-cs2113t-t09-3.github.io/tp/DeveloperGuide.html)): 
  * Sequence diagram of the overall interaction of different components of SITUS
  * Diagram of interaction between `Parser` and `Command` classes
  * Short descriptions for `UI`, `Parser` and `Command` components
  * Instructions for manual testing
* **Team-based Contributions**:
  * Lead the setting up of team organisation and repo
  * Documentation of target user in both UG and DG 
  * Documentation of value proposition in DG
  * "How to Use This Guide" section in UG
  * Documentation of code methods 
* **Community**:
  * Participated in PE Dry Run (bugs reported can be found [here](https://github.com/nishantrai-nus/ped/issues))