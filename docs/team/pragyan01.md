# Samal Sthitipragyan - Project Portfolio Page

## Overview
CLI.ckFit is a desktop-based fitness app which can be accessed easily via CLI. CLI.ckFit allows you to input your calories,
weight, foods, and workouts throughout the day. It also comes with a BMI and recommended caloric intake calculator
which can give you an idea of your current fitness level.

## Summary of Contributions
### Code contributed
[Reposense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=pragyan01&tabRepo=AY2122S1-CS2113T-F14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

### Enhancements implemented
* New feature for Fluid Class-getCalories(): Added ability to obtain total caloric intake due to fluids consumed for any specific date.
    * What it does: This method allows the user to view his/her total caloric intake due to     fluids for any specific date. This is done by adding up calories for all listed fluid intakes for that specific date.
    * Justification: This feature provides convenience to the user as he/she can view and track her total caloric intake from only fluids for any specific date.
    * Highlights: This enhancement adds on to its twin method from Meal Class as its intended   to add to the cumulative caloric intake from meals to provide the user his total calorie      count. For any specific date inputted by the user, the method runs through the fluid array and for any fluid entry in the array containing that specific date, its calorie parameter is extracted and added up to provide the total calorie count for fluids. 


* New feature for Fluid class-getVolume(): Added ability to obtain total volume consumed due to fluid intakes for any specific date.
    * What it does: This method allows the user to view his/her total volume intake due to fluids for any specific date. This is done by adding up calories for all listed fluid intakes for that specific date.
    * Justification: This feature provides convenience to the user as he/she can view and track her total volume intake from only fluids for any specific date.
    * Highlights: For any specific date inputted by the user, the method runs through the fluid array and for any fluid entry in the array containing that specific date, its volume parameter is extracted and added up to provide the total volume consumed for fluids.


* New feature for FoodBank class: Added ability to store fluids and their associated calories in library
  * What it does: Users do not have to key in their calories when adding fluids that have already been saved to their fluid library, for repetitive fluids of the same description.
The fluid library also automatically adds the calories associated to that fluid from the meal library.
  * Justification: This feature ensures that users do not need to input calories for fluids that they consume regularly, which may be difficult to remember or tedious to type it in again. They only need to input the name of the fluid, and the fluid library takes care of how many calories the fluid contains, provided that they have added this particular fluid its respective calorie count before to the fluid library.
  * Highlights: When the user adds a fluid without its calories, the fluid library is checked. The name of the fluid that the user inputs is compared with the names of all the fluids within the fluid library. If the fluid exists, the calories of that particular fluid will be returned. Else, an exception will be thrown. One challenge that was faced was ensuring that no duplicate fluids were stored in the fluid library. Thus, when adding a fluid to the fluid library, the name of the fluid added is compared with all existing fluids within the fluid library. Another challenge was to ensure that the user had input the fluid with a description (name). Thus, the "getDescription()" method is called to obtain the description of the meal. The description was then checked for any separators. If it contained any separators, it was considered invalid, and an exception was thrown.


* New feature for DateTracker class: Added ability to sort array list containing dates and times in ascending order.
  * What it does: DateTracker array list is now sorted in ascending order in the order of date and then time.
  * Justification: Sorted array list allows text to be written to a local .txt file in ascending order for better readability
  * Highlights: This feature enhances the saving and loading functionalities to/from a .txt file and supports array lists in other classes such as the Meal class, keep them all organised and sorted. One challenge faced is when the user might not input a date with his entries, making this sortDate() method unnecessary. This hurdle was overcame eventually as the program now inputs the user's system date and time at the time of his entry if the user does not input a date/time manually.
  
#### Contributions to UG
* Technical start up section
* Add/delete/list fluids command sections
* Add/delete/list fluids in library command sections
* List calories/volume in the list command section  
* Some parts of known limitations in each section

#### Contributions to DG
* Fluid class diagram
* Adding fluid sequence diagram
* Deleting fluid sequence diagram
* Get total calories for specific date fluid sequence diagram

#### Contributions to team-based tasks
* Some reformatting of the UG
* Contributed in refactoring of the code
* Contributed in debugging and finding loopholes

#### Review/mentoring contributions
* Group mate PR reviews:
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/169](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/169)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/168](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/168)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/138](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/138)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/114](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/114)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/110](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/110)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/107](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/107)
    
  
* Peer DG review:
  * [https://github.com/nus-cs2113-AY2122S1/tp/pull/29](https://github.com/nus-cs2113-AY2122S1/tp/pull/29)
  