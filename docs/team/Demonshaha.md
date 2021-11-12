# Zhou Chengxu - Project Portfolio Page

## Overview - TourPlanner

TourPlanner is a data management solution, for tour agencies. The user interacts with it using a CLI, to update a
database with flights, tours, clients, and combine them into packages.

It is written in Java, and has about 7kLoC.

Given below are the contributions to the project.

* Feature: **Storage** - Added 4 class files to let the program be able to load and save the files to data folder(Because my vpn cannot work, bobowoo2468 helped
      me to push)
      
    * What it does:  Allows the program to load the clients, tours, flights, and clientpackages that user added before, and save the data they add every time.

    * Justification: This function is essential, because Touragencies need to save all the data they have added in, without the storage function, this program
      cannot save the data users have added every time the user close the program, all of the data will disappear.
      
    * Highlight: In order to make the program convinient for the user, the clients, tours, flights, clientpakcages will be stored to 4 different files in data folder.

* Feature: **Add's exception** --Add exceptions to add commantd which help to check human error

  * What it does: Help to check if the information input by the user is correct. 
    Including:
    1. Email checking
    2. Contact number checking
    3. Price checking
    4. Departure date and return date checking
  
  * Justification: This can help to check human errors which can improve the effciency of users and this is one of the advantage of our program. For example, if the employee
    of the touragencies input the departure time and return time reversely, the program can warning him or her.
    
  * Highlights: For date checking, it implements java.data class to check the if the departure date and return dates are illegal and if they are reverse.

* Task: **Storage** methods and code structure
* Task: **Modify clientlist, tourlist, fightlist, clientpackagelist classes** in order to implement storage
* Task: **Modify** toString methods

<br>

Code contributed: : [Reposense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=demonshaha&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=Demonshaha&tabRepo=AY2122S1-CS2113T-F11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
  
<br>

* Documentation
    * User Guide:
        * Added sort commands guideline

    * Developers Guide:
        * Added implementation details for Storage

* Coummunity:
  * Reviewed and approved PRs frequently
  * Give suggestions on codes.
