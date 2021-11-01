# Alvin Tan Guo Hao - Project Portfolio Page

This is a student project for a university software development course and I am one of the contributors to `MediVault`.

## Summary of Contributions

Given below are my contributions to the project.

Code contributed: more than 3000 lines of
code. [[RepoSense](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=alvintan01&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=alvintan01&tabRepo=AY2122S1-CS2113T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)]

### Features

v1.0 tasks:

- Implemented skeleton code for MediVault.
    - Functionality: Users were able to type commands and parameters into MediVault.
    - Justification: Valid commands will be accepted by MediVault. Else, InvalidCommandException will be thrown.
    - Pull request [[#10](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/10)]
- Implemented `Ui` class for MediVault.
    - Functionality: Handles all the printing done by MediVault.
    - Justification: Users will be able to view the stocks, prescriptions and orders in a table format and any other
      error messages.
    - Pull request [[#10](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/10)]
- Implemented sort for `liststock` command.
    - Functionality: Users will be able to sort any columns of the stocks in ascending or descending order.
    - Justification: Users will be able to view the stocks in sorted order such as by ascending price.
    - Pull request [[#45](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/45)]
- Implemented `exit` command.
    - Functionality: MediVault will quit when `exit` is received.
    - Justification: Users will be able to quit the application.
    - Pull request [[#10](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/10)]
- Implemented `purge` command.
    - Functionality: MediVault will clear all the data in the program when `purge` is received.
    - Justification: Users will be able to easily clear all the data in MediVault.
    - Pull request [[#13](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/13)]
- Took over `help` command.
    - Functionality: MediVault will print a table to show the command parameters and syntax.
    - Justification: Users will be able to view all the commands accepted by MediVault in one command. This is also to
      have a consistent theme throughout MediVault where most outputs are in table format.
    - Pull request [[#45](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/45)]

v2.0 tasks:

- Implemented mode feature.
    - Functionality: MediVault will change modes when `stock`, `prescription` or `order` is received.
    - Justification: Users will be able to type shorter commands for operations in MediVault.
    - Pull request [[#123](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/123)]
- Implemented sort for `listorder` command.
    - Functionality: Users will be able to sort any columns of the orders in ascending or descending order.
    - Justification: Users will be able to view the orders in sorted order such as by ascending date.
    - Pull request [[#162](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/162)]
- Implemented `listprescription` command.
    - Functionality: Users will be able to view all the prescriptions in a table and filter them.
    - Justification: Users will be filter the prescriptions by any column such as by customer ID.
    - Pull request [[#181](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/181)]
- Implemented `receiveorder` command.
    - Functionality: Users will be able to receive an order and add the order to the stocks.
    - Justification: Users will be able to mark an order as received and add it to the stocks in one step.
    - Pull request [[#208](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/208)]

v2.1 tasks:

- Fixed bug where ID does not reset after `purge`.
  - Functionality: MediVault should reset the ID to 1 after `purge` command is executed.
  - Justification: To prevent user confusion as they expect ID to start from 1.
  - Pull request [[#280](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/280)]
- Fixed bug where whitespace renders command invalid.
  - Functionality: MediVault should ignore extra whitespaces at the start and end of commands.
  - Justification: To allow users to copy and paste commands ignoring extra whitespaces.
  - Pull request [[#280](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/280)]
- Fixed bug where `receiveorder` does not mark an order as delivered.
  - Functionality: MediVault should mark an order as delivered once `receiveorder` is executed successfully.
  - Justification: To prevent users from receiving an order multiple times.
  - Pull request [[#280](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/280)]
    

### Enhancements to Existing Features

- Added order quantity to `liststock` command.
    - Functionality: `liststock` command would show the current pending order quantity in the quantity column.
    - Justification: To allow pharmacists to know easily if a new order was already placed.
    - Pull request [[#109](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/109)]
- Refactor `HashMap<String, String>` to `LinkedHashMap<String, String>`.
    - Functionality: `LinkedHashMap<String, String>` would preserve the order of parameters
      unlike `HashMap<String, String>`.
    - Justification: This helps preserve the order of parameters input by the user as MediVault will take the last
      occurrence if the same parameter is provided multiple times.
    - Pull request [[#123](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/123)]
- Refactor `command` objects.
    - Functionality: Removed the input parameters of the `execute()` function.
    - Justification: Most of the parameters like `Ui` can be made into a singleton.
    - Pull request [[#133](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/133)]
- Refactor `Dispense` to `Prescription`
    - Functionality: Refactor `Dispense` object to `Prescription`.
    - Justification: From peer feedback, users were confused what `Dispense` was, as such our team decided
      that `Prescription` is a better word to use.
    - Pull request [[#208](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/208)]

### Documentation

- [User Guide](../UserGuide.md)
    - Added documentation for `mode`, `purge`, `exit`, `help`, `listprescription` and `receiveorder`. [[#97](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/97)], [[#208](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/208)]
    - Added command summary. [[#97](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/97)]
    - Reordered the sections into add, list, delete and update. [[#229](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/229)]

- [Developer Guide](../DeveloperGuide.md)
  - Design
    - Main application logic and explanation. [[#163](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/163)]
    - Validator class diagram and explanation. [[#198](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/198)]
    - Inventory class diagram and explanation. [[#198](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/198)]
  - Sections on `list` and `receiveorder` and their respective sequence diagrams. [[#163](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/163)], [[#223](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/223)]

### Team-Based
- Setting up the GitHub team org/repo.
- Assigning user stories to team members.
- Maintaining [Trello](https://trello.com/b/nMVm0vgz/cs2113t-user-stories) dashboard.
- Hosting weekly team meetings.
- Release management.
- Ensured UG and DG has a consistent format.

### Community

- Pull Requests reviewed with non-trivial review comments. [[#74](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/74)], [[#143](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/143)], [[#172](https://github.com/AY2122S1-CS2113T-T10-1/tp/pull/172)]
