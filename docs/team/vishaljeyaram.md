# Vishal Jeyaram - Project Portfolio Page

## Overview
CLI.ckFit is a desktop-based fitness app which can be accessed easily via CLI. CLI.ckFit allows you to input your calories,
weight, foods, and workouts throughout the day. It also comes with a BMI and recommended caloric intake calculator
which can give you an idea of your current fitness level.

### Summary of Contributions

#### Enhancements implemented
* New Feature: Added ability to store meals and their associated calories in library
    * What it does: Users do not have to key in their calories when adding meals that have already been saved to their meal library.
    * The meal library automatically adds the calories associated to that meal from the meal library.
    * Justification: This feature ensures that users do not need to input calories for meals that they consume regularly, 
    * which may be difficult to remember. They only need to input the name of the meal and the meal library takes care of 
    * how many calories their meal contains, provided that they have added this particular meal and its calories to their 
    * meal library.
    * Highlights: When the user adds a meal without its calories, the meal library is checked. The name of the meal that the user
    * inputs is compared with the names of all the meals within the meal library. If the meal exists, the calories of that particular
    * meal will be returned. Else, an exception will be thrown. One challenge that was faced was ensuring that no duplicate meals were stored
    * in the meal library. Thus, when adding a meal to the meal library, the name of the meal added to compared with all existing 
    * meals within the meal library. 

* New feature: The current date will be generated if the date has not been keyed in. 
    * What it does: Obtains user's system's date and uses this date if the user has not input a date for any of their
    * fitness parameters like adding their meals, fluids, weight or workouts.
    * Justification: When adding meals, fluids, weight or workouts, it is inconvenient for the user to type out the date 
    * for every single new input. Furthermore, if they do enter a wrong date by any chance, they will need to delete their
    * incorrect input. This wastes more time. Hence, in order to quicken the process, the current date will be input, saving 
    * the user time, if they do not manually input the date.
    * Highlights: The user input's is checked to see if the user has input a date separator. If not, the system date will 
    * be generated using the Java's "getSystemDate()" method. 

* New feature: Optional calculator calculates recommended caloric intake for the user.
    * What it does: Generates the ideal recommended caloric intake for users by taking in their activity level, sex, weight,
    * height, and age.
    * Justification: Users that are into pursuing fitness, especially those who are starting out, may need a rough gauge on 
    * how many calories they need to consume to maintain their body weight. This will also give them an idea of how many calories 
    * they will need to consume to either gain or lose weight. Thus, we can allow them to generate this ideal recommended 
    * caloric intake if they wish to, as it is an optional feature, everytime they launch the application.
    * Highlights: A series of questions will be asked, prompting the user to key in their age, height, weight, sex and 
    * activity level on a scale of 1 - 5. Once the user has input these parameters, the Harris-Benedict Formula is used to 
    * generate the user's ideal recommended caloric intake. A challenge faced was making sure that the 
    * user also knew what approach they needed to take ie. gain, lose or maintain their weight. Thus, this calculator was used 
    * in conjunction with the BMI calculator to give the user a clearer idea of what approach they need to take.
    * Credits: Harris-Benedict Equation was used. It is a method used to estimate an individual's basal metabolic rate (BMR).

#### Code contributed
[Reposense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=vishal&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=VishalJeyaram&tabRepo=AY2122S1-CS2113T-F14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

#### Documentation

* User Guide 
  * Parts of product introduction
  * Add//delete/list meal command sections
  * Add/delete/list meal library commands sections
  * "List everything on all dates" command section 
  * FAQ section
  
* Developer's Guide 
  * Meal Tracker class diagram 
  * Meal Tracker: List meals sequence diagram and explanation. 
  * Foodbank: Adding custom meal sequence diagram and explanation.

#### Contributions to team-based tasks
* Contributed in debugging code.
* Contributed in integrating code together via command manager.
* Helped to refactor code and shorten methods.
* Gave ideas on using class-level attributes for methods.

#### Review/mentoring contributions
* Group mate PR reviews:
    * [#103](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/103)
    * [#108](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/108)
    * [#178](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/178)

* Peer PR reviews:
    * [[CS2113T-F11-4] CLIvershelf #43](https://github.com/nus-cs2113-AY2122S1/tp/pull/43)
