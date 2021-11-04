# marcusbory's Product Portfolio Page (PPP)

## Overview
**ConTech** is a Command Line Interface (CLI) application for managing computing-related contacts.

The project started on 3 September 2021, where our team brainstormed and came up with our target users and app 
direction. We proceeded to create user stories related to our desired product and narrowed them down for each 
iteration so that we were able to comfortably deliver our features.

## Summary of contributions
### Code contributed
The code written by me (`marcusbory`) can be found [here](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=marcusbory&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=marcusbory&tabRepo=AY2122S1-CS2113T-T09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false).
<br />

### Enhancements implemented
1. Application System Architecture
   - Created an overall application system with inspiration from AB3
   - Allowed developers on the team to easily implement features and exception handling
2. `Add` feature, `Import` feature
   - Features created for users to add and import contacts
3. Implementation of enumerations
   - `DetailType`: Effective enumeration of details within each contact, providing each detail an inherent index to 
   be used across the entire application
   - `FailedCommandType`: Usage of enumerations to better identify failed commands, improving parsing and error message processing
4. Application Storage System
   - `Storage.java`: System to locate or create storage files
   - `ContactsDecoder.java`: System for decoding contacts correctly from the storage file
   - `ContactsEncoder.java`: System for storing contacts from the app to storage file
5. Parsers
   - `ContactParser.java`: Abstract class which inherits RegexParser, created for `Add` and `Edit` feature
   - `AddContactParser.java`: Parser which will attempt to create a syntactically valid `AddContactCommand`.

### Contributions to UG
The link to the User Guide (UG) can be found [here](https://ay2122s1-cs2113t-t09-1.github.io/tp/UserGuide.html).

### Contributions to DG
The link to the Developer Guide (DG) can be found [here](https://ay2122s1-cs2113t-t09-1.github.io/tp/DeveloperGuide.
html).

### Team-based tasks
- Hosted and conducted weekly brainstorming session during ideation phases (V1.0, V2.0, V2.1)
- Set up the Github team organisation and repository
- Maintained issue tracker (ensured that PRs were tagged to according Milestones and labels)
- Updated User Guide (UG) and Developer Guide (DG) documentation that were not specific to any feature:
  - UG: Added sections "How to use the User Guide", "Common Notations used"
  - DG: Set up DG with System Architecture diagram, Product Scope, Non-functional Requirements
- Refactored entire codebase (on 27/10/21) to improve code quality (apply SLAP) and fix any coding standard violations
- Updated `runtest.bat` and `runtest.sh` to automatically delete data files, and work with `import` feature

### Community
Reported Bugs during PE Dry Run.
