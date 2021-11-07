# Wai Kit's - Project Portfolio Page

## Project - Large Order Tracking System(LOTS)
Large Order Tracking System (LOTS) is a Command Line (CLI) program that enables users to keep track
of multiple food orders from a pre-set list of food items from different stores. The program helps
users to collate the orders and displays a summary of all the orders along with other information such
as an individual person’s order and the corresponding cost, total cost of all the orders and more. 
As LOTS is a CLI program, this would greatly benefit any user that excels in typing.

### Summary of Contributions
**Code Contributed** - [Reposense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25)

**Link to Project List Page** - [Project List Page](https://github.com/AY2122S1-CS2113-T13-2/tp/pulls?q=is%3Apr+author%3AWaiKit-nus)

###Added Features:
* **Add Command Feature**:
    * **Function:** Allow adding of people into the list.
    * **Justification:** The program requires people to be added into the list to manage the orders.
    
###Added Classes:
* `Person Class`
    * **Function:** To hold all the information regarding the individual person.
    * **Justification:** Utilising OOP to better manage the individual's data.
    
* `PeopleManager Class`
    * **Function:** To manage a list of people.
    * **Justification:** Utilising OOP to allow for better tracking of the list of person.
    
###Testing:
* **AddCommandTest:** 
    * **Function:** To test and capture as many edge cases as possible.
    * **Justification:** Reduce the number of bugs in the code.
    
* **Final Product Testing:**
    * **Function:** To try and break the program by throwing as many edge cases as possible at it.
    * **Justification:** Reduce the number of bugs in the code.
    
    
###Enhancements implemented:
* Made all the person's name uppercase. 
    * **Justification:** To ensure that the person's names are standardised during storage, to prevent
    duplicates.
* Added Total Cost and Total Quantity of everyone in the list.
    * **Justification:** Increase convenience to users, allowing them to view the total cost and
      quantity easily for everyone.
* Added `executeFromFile` function to allow data to be added from storage into the list.
    * **Justification:** Allows retrieval of data from the storage.
* Edited error messages for Add Command in response to issue (Issues [#177](https://github.com/AY2122S1-CS2113-T13-2/tp/issues/177), 
  [#190](https://github.com/AY2122S1-CS2113-T13-2/tp/issues/190), 
  [#205](https://github.com/AY2122S1-CS2113-T13-2/tp/issues/205),
  [#208](https://github.com/AY2122S1-CS2113-T13-2/tp/issues/208)).
    * **Justification:** A better error message has been crafted to allow users to understand the error.
  
###Contributions to UG
* Added the guideline for `Add Function` in the user guide. (Pull Request [#243](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/243)).

###Contributions to DG
* Manager Component with diagram under Design.
* Add, Delete, Edit, Orders and Find Command Classes under Implementation.
* Added `MenuAndOrdersSequenceDiagram`.

###Contributions to team-based tasks
* Creating User Stories.
* Maintaining Issue Trackers.
* Testing the overall program.
* Keeps track of whole program flow using Google Jamboard.
* Proactive in participating during discussions and distribution of issues to manage.

###Review/Mentoring Contributions:
* PRs Reviewed: [#262](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/262),
  [#260](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/260),
  [#242](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/242),
  [#218](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/218),
  [#214](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/214),
  and many more.
  
### Contributions beyond Team Project
* DG bugs and suggestions: (Team RestuarantBuddy [#35](https://github.com/nus-cs2113-AY2122S1/tp/pull/35/files/0f4169382361d3421b310bf917f732105be9a082)).
* Bug testing for CS2113T-F12-4 during PE dry run.

  