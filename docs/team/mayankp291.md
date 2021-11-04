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

1. `list` feature
    - Added the feature to list all contacts with their index and name.
2. Parsers
    - `RegexParser.java`: Add Input Validation for contact fields added to ConTech. Covers fields - Name, GitHub, Email,
      Twitter and Telegram. The usernames are checked against requirements from the respectable companies.
3. Exception handling
    - Add exceptions to cover more test cases and provide clear error messages.
4. Sorting of contacts
    - Store contacts sorted in ascending order in the `ContactList` structure.
    - Sort after every contact is added, edited, or imported.
5. System testing and writing tests
    - Written tests for various functions and increase test coverage.
    - Tested every component and made sure the program worked as expected

### Contributions to UG

The link to the User Guide (UG) can be found [here](https://ay2122s1-cs2113t-t09-1.github.io/tp/UserGuide.html).

### Contributions to DG

The link to the Developer Guide (DG) can be found [here](https://ay2122s1-cs2113t-t09-1.github.io/tp/DeveloperGuide.
html).

### Team-based tasks

- Attended and contributed to weekly brainstorming session during ideation phases (V1.0, V2.0, V2.1)
- Maintained issue tracker (ensured that PRs were tagged to according Milestones and labels)
- Updated gradle and testing scripts

### Community

Reported 13 Bugs during PE Dry Run and suggested ways to solve them.
