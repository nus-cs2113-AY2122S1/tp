# ashrafjfr's Project Portfolio Page

## Overview
**ConTech** is a Command Line Interface (CLI) application for managing computing-related contacts.

The project started on 3 September 2021, where our team brainstormed and came up with our target users and app
direction. We proceeded to create user stories related to our desired product and narrowed them down for each
iteration so that we were able to comfortably deliver our features.

### Summary of Contributions

### Code contributed
The code written by me (`ashrafjfr`) can be found [here](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=ashrafjfr&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=ashrafjfr&tabRepo=AY2122S1-CS2113T-T09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false).
<br />

### Enhancements implemented
1. `view` feature
    - Feature to allow users to view a selected contact based on its index from the ConTech Book.
    - Also implemented `ViewMessageFormatterUI.java` in order to return a formatted string of what the application will
      print for each field.
2. `help` feature
    - Feature to allow users to find out all available commands in the application.
3. Duplicate Checker
    - Implemented the duplicate checker for the `add` feature and asks for users confirmation if there is
      already a similar field in the ConTech Book. The duplicate checker methods are within the `AddContactCommand.java`.
    - Implemented the duplicate checker for the `edit` feature and asks for users confirmation if there is
      already a similar field in the ConTech Book. The duplicate checker methods are within the `EditContactCommand.java`.
      Duplicate checker for edit has an extra implementation compared to add as it only compares the fields that have
      been edited.
4. Parser
    - `MainParser.java`: Implemented method to handle user input and parse for index when `view` feature is called.
5. UI
    - Split `ExceptionTextUI` from `TextUi` to separate error messages from main UI messages.
    - Refactored messages for consistency across all features

### Contributions to UG
The link to the User Guide (UG) can be found [here](https://ay2122s1-cs2113t-t09-1.github.io/tp/UserGuide.html).
- Implemented the structure with reference from 
  [AddressBook Level 3 User Guide](https://se-education.org/addressbook-level3/UserGuide.html)
- Added documentation for `view` feature
- Added documentation for `help` feature

### Contributions to DG
The link to the Developer Guide (DG) can be found [here](https://ay2122s1-cs2113t-t09-1.github.io/tp/DeveloperGuide.html).
- Added implementation details and sequence diagram for `view` feature

### Contributions to team-based tasks
- Updated User Guide (UG) and Developer Guide (DG) documentation that were not specific to any feature:
   - Updated overall UG and fixed inconsistencies between UG and output from application
   - Added the `Table of Contents` with links to each feature in the UG
   - Added table of user stories for `User Stories` section in the DG
   - Updated UML diagrams following proper diagram standards in the DG 
   - Added `Instructions for Manual Testing` section in the DG
   - Formatted UG and DG for PDF conversion 
- Hosted weekly meeting sessions with team to plan for product

### Review/mentoring contributions
- Reviewed PRs and ensured overall code quality/formatting was up to standard

