---
layout: page
title: Rui Yang's Project Portfolio Page
---

## **Overview**

Fitbot is a desktop app that helps university students who are looking to keep track of their calorie consumption and calorie 
output with the speed and convenience of command-line based tools, especially in times of online school.

### **Summary of Contributions**
Below are all the contributions done by the author to Fitbot.

#### **New Feature**: Add, View and Delete Food Item
- What it does: This feature allows users to add, view and delete food item to the food list, together with the calorie of the food item,
  and the date and time when consuming the food.
- Justification: This feature is one of the core feature in Fitbot application. It can help the users to keep track of the 
  food calorie taken. With all the food items stored in a list, this feature enables calculation of total calorie, and the number
  of suppers taken. 
- Highlights: This feature adds a food item to the food list, view all the food items in the food list and delete unwanted food item
- Pull requests: [#34](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/34), [#40](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/40), 
  [#46](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/46), [#81](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/81)
  [#82](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/82), [#98](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/98)

#### **Code Contributed**:
Here is the [link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=tryyang2001&tabRepo=AY2122S1-CS2113T-F14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false) 
to see the code contributed by the author. 

#### **Project Management**:
- Creates Github organization for the team and set up Github team repository
- Helps in maintaining issue tracker
- Helps to review, approve and merge Github pull requests
- Helps to publish release v1.0 and v2.0 in team repo

#### **Enhancements To Existing Features**:
##### **Delete exercise according to date**
- Since exercises stored in the exercise list are split according to their date, users are now required to delete the exercise
  by specifying the index and the date.
- Pull request: [#98](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/98)

##### **Create Item class and ItemList class to make the code more OOP**
- Applies inheritance in Food, Exercise, FoodList and ExerciseList classes, helps to extract common methods, refactor 
  codes to improve efficiency, adds javaDoc to all public methods and most of the private methods, and standardizes the 
  code style.
- Pull request: [#81](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/81)
  [#82](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/82), [#98](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/98), [#118](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/118)

##### **Implement filtering items within 7 days of today tom improve code efficiency**
- When the program starts, filters the food list and exercise list to only take in items that are within 7 days of today (including today)
  so that the users can only view the food and exercise items taken within this period. 
- Pull request: [#122](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/122), [#140](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/140)

##### **Checking for Food and Exercise Item data validity**
- Checks whether the date and input calorie from a food or exercise item are valid.
- Pull request: [#220](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/220)

#### **Documentation**:
##### **Contribution to User Guide**
- Writes documentation for Section 3 (Terminology), 4.2 (Recording Food) and 4.3 (Recording Exercise) [#124](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/124)
- Fixes formatting issue in UG [#55](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/55), [#56](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/56), [#57](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/57),
- Ensures all the links provided in UG are valid [#161](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/161)
- Other Pull request: [#137](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/137)

##### **Contribution to Documentation Guide**
- Writes simple introduction, creates content page draft, ensures hyperlinks provided in DG are valid [#132](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/132)
- Diagrams drawn:
  - Architecture diagram [#106](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/106)
  - ItemBank and Item class diagram [#106](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/106), [#183](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/183)
  - Add Food Item sequence diagram
- Writes design details for Data Component (ItemBank and Item) [#133](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/133)
- Writes implementation details and design considerations for Add Food Item [#111](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/111)
- Writes documentation in glossary [#111](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/111), [#137](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/137)

#### **Community**:
- PRs reviewed (with non-trivial comments): [#32](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/32), [#33](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/33), [#35](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/35), [#36](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/36), [#39](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/39), [#43](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/43), [#80](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/80), [#216](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/216),
- Reported bugs and suggestions for other teams in the class: [Team ConTech Developer Guide Reviews](https://github.com/nus-cs2113-AY2122S1/tp/pull/24)