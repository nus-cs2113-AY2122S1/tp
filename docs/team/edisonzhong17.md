# EdisonZhong17 - Project Portfolio Page
## Project: Budget Tracker
Budget Tracker is a desktop app for managing expenses, budget and loans, optimized for use via a Command Line Interface (CLI) for tech-savvy students who have trouble keeping track of their expenses.

Given below are my contributions to the project.

* **New Feature**: Added a delete command for budget, expenditure and loan (Pull requests [#43](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/43), [#57](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/57)).
    * What it does: allows the user to delete their previously added budget, expenditure and loan records.
    * Justification: This feature improves the product significantly because a user can regret in adding a wrong budget or expenditure or loan and the app should provide a convenient way to delete them.
    * Highlights: This enhancement affects existing commands and commands to be added in the future. It requires an in-depth analysis of design alternatives. The implementation too is challenging as it required changes to existing commands.

* **New Feature**: Added a set of delete commands for deleting single / multiple / all expenditures and loans (Pull requests [#88](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/88), [#109](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/109)). 
    * What it does: allows users to delete multiple or even all expenditures / loans at one time other than only deleting a single record.
    * Highlights: The implementation is challenging as the commands vary from delete singel record to multiple records to all records.
    

* **Code contributed**: My code on tP Code Dashboard: [click here](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)


* **Project management**:
    * Managed releases `v1.0` - `v2.1` (3 releases) on GitHub


* **Enhancements to existing features**:
    * Added a LoanReminder to remind user of loans that due already every time when user start our app (Pull requests [#146](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/146), [#154](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/154)).
    * Fixed a bug to make all amount in budget, expenditure, loan defaulted to be displayed as 2 decimal points whenever needs to be printed out (Pull requests [#201](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/201), [#204](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/204)).


* **Team-based tasks** :
    * Actively participated in weekly project meeting.
    * Set up issue tracker with detailed descriptions and attached labels from time to time (Examples: Issues [#200](https://github.com/AY2122S1-CS2113T-F11-2/tp/issues/200), [#142](https://github.com/AY2122S1-CS2113T-F11-2/tp/issues/142)).


* **Documentation**:
    * User Guide:
        * Added documentation for feature `delete` (Pull request [#118](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/118), [#123](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/123)).
        * Added documentation for features - `Loan Reminder` (Pull request [#157](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/157)).
    * Developer Guide:
        * Added implementation details of the `delete` feature (Pull request [#105](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/105)).
        * Added design details of the `UI component` part. (Pull request [#214](https://github.com/AY2122S1-CS2113T-F11-2/tp/pull/214)).