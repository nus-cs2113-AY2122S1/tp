# Jia Yixuan - Project Portfolio Page

##Project: Budget Tracker
Budget Tracker is a desktop app for managing expenses, budget and loans, optimized for use via a Command Line Interface (CLI) for tech-savvy students who have trouble keeping track of their expenses.

Given below are my contributions to the project.
* **Code Contributed**: [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=jyxhazcake&tabRepo=AY2122S1-CS2113T-F11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
* **Feature Implemented**: Edit command for expenditures and loans
  * What it does:  Allows the user to edit their previously added expenditure and loan records.
  * Justification: This feature was necessary as users may enter incorrect information in their expenditures and loans and it is important to allow them to change these values easily.
  * Highlights: This feature allows the users to work with optional parameters. They can omit parameters which need not be edited, which greatly improves user-friendliness.
* **Feature Implemented**: Add command for loans
  * What it does:  Allows users to add a loan record to their list.
  * Justification: A necessary feature to allow users to record down the loans lent out.
* **Enhancement added**: Using RecordList class as our main data structure, capable of storing ArrayLists of expenditures and loans, with attributes and methods handling the modification of such data. [\#39](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/39) [\#42](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/42)
  * Justification: In a month's list, we needed to hold different lists to display expenditures and loans separately. Furthermore, our program's logic depended on the methods in this class for the modification and retrieval of data, and this  enhancement allowed it to be done easier.  
* **Enhancement added**: Refactoring Parser into different classes and implemented a new way of parsing arguments. [\#138](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/138), [\#134](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/134), [\#138](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/138)
  * Justification: Parser is the main logic of our program and refactoring into utility and individual classes made it easier to utilise parsing methods to suit each individual command. 
  * Highlights: A new way of splitting arguments made use of a HashMap and allows any command to split their arguments based on their prefixes. This improved code efficiency greatly, and was easier for my teammates to understand this method to use for their own commands.
* **Contributions to team-based tasks**:
    * Managed releases `v1.0` - `v2.1` (3 releases) on GitHub
      * Actively reviewed and merged teammates' PRs in all 3 releases.
      * Frequent use of issue tracker to create issues for teammates. Examples: [Issue 131](https://github.com/AY2122S1-CS2113T-F11-2/tp/issues/131), [Issue 98](https://github.com/AY2122S1-CS2113T-F11-2/tp/issues/98)
      * Helped to find bugs in teammates' code
      * Actively started discussion on the progress of our project.
* **Documentation**:
    * User Guide:
        * Added documentation for the some parts in features `add` and `edit`[\#148](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/148), [\#257](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/257)
          * Specifically, `add` loans, `edit` expenditure and `edit` loans.
    * Developer Guide:
        * Added documentation for Design in the Logic component for Parser and Commands. [\#218](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/218)
        * Created a UML class diagram to show the program's logic.
        * Created a UML sequence diagram to show an example of the logic flow of a user command.
        * Added implementation details of the `edit` feature.
* **Contributions beyond project team**:
    * Reviewed and provided bug reports for another team during PE Dry Run. [PED Issues](https://github.com/jyxhazcake/ped/issues)
    