# mayankp291's Product Portfolio Page (PPP)

## Overview

**ConTech** is a Command Line Interface (CLI) application for managing computing-related contacts.

The project started on 3 September 2021, where our team brainstormed and came up with our target users and app
direction. We proceeded to create user stories related to our desired product and narrowed them down for each iteration
so that we were able to comfortably deliver our features.

## Summary of contributions

### Code contributed

The code written by me (`mayankp291`) can be
found [here](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=mayank&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=mayankp291&tabRepo=AY2122S1-CS2113T-T09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
.
<br />

### Enhancements implemented

1. Parsers
    - `RegexParser.java`: Add Input Validation for contact fields added to ConTech. Covers fields - Name, GitHub, Email,
      Twitter and Telegram. The fields for GitHub, Twitter and Telegram are checked against requirements from the
      respective companies. For Email it is checked against the [RFC 5322](https://www.ietf.org/rfc/rfc5322.txt) 
      standard. 
    - `MainParser.java` : Update exception handling and implement method to handle regex exceptions.
2. `ls` feature
   - Feature to list all contacts with their index and name.
3. Exception handling
    - Update exception handling for command executions and regex checks.
    - Streamline exceptions to cover more test cases and provide clearer and helpful error messages.
5. Sorting of contacts
    - Store contacts sorted in ascending order in the `ContactList` structure.
    - Sorted after contacts are added, edited, or imported.
6. System testing and writing tests
    - Write JUnit and IO tests for various commands to increase stability and test coverage.
    - Stress test every component to make sure the program does not fail under unexpected circumstances.

### Contributions to UG

The link to the User Guide (UG) can be found [here](https://ay2122s1-cs2113t-t09-1.github.io/tp/UserGuide.html)
- Added documentation for `ls` and `exit` features
- Added Command Summary
- Ensured overall consistency for UG

### Contributions to DG

The link to the Developer Guide (DG) can be found [here](https://ay2122s1-cs2113t-t09-1.github.io/tp/DeveloperGuide.html)
- Added documentation for `ls`
- Added Sequence Diagrams for `ls` and System Architecture
- Added Design Considerations

### Team-based tasks

- Conducted weekly team meetings for issue review and work allocation
- Maintained issue tracker (ensured that PRs were tagged to according Milestones and labels)
- Conducted regular system testing and debugging to find bugs and ensure system worked as expected
- Consistently updated exceptions and error messages for a better user experience
- Solved bugs from PE Dry Run
- Updated gradle and testing scripts

### Review/mentoring contributions

- Reviewed PRs and provided suggestions to improve overall code quality/formatting

### Community

- Reported 13 Bugs during PE Dry Run and suggested ways to solve them
