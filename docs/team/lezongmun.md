# Mun Le Zong's Project Portfolio Page

## Overview
**ConTech** is a Command Line Interface (CLI) application for managing computing-related contacts.

The project started on 3 September 2021, where our team brainstormed and came up with our target users and app
direction. We proceeded to create user stories related to our desired product and narrowed them down for each
iteration so that we were able to comfortably deliver our features.

### Summary of Contributions

### Code contributed
The code written by me (`lezongmun`) can be found [here](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=lezongmun&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabAuthor=lezongmun&tabRepo=AY2122S1-CS2113T-T09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&tabType=authorship).
<br />

### Enhancements implemented
1. `rm` feature 
    - Feature to allow users to delete selected contacts or all contacts at once from the ConTech Book.
2. `me` feature
    - Feature to allow users to view their saved personal contact details.
3. Personal Contact System
    - Creation of personal contact for first time use
    - Saving of personal contact details using `ContactsEncoder.java`
    - Loading of saved personal contact details in `Storage.java` using `ContactsDecoder.java` for subsequent runs
4. Parsers
    - `AddPersonalContactParser.java`: Parser that is responsible for prompting users to input their personal contact
      details and parsing them into `Contact` details for their personal contact
    - `IndexParser`: Parser that is responsible for obtaining the `Contact` index from a user input command string. Used 
      for commands involving `Contact` indexes like `rm`, `view` and `edit` commands.

### Contributions to UG
- Added documentation for features `me` and `rm`
- Tweaked documentation (formatting, usages, examples) for features with `all` and `me` as inputs

### Contributions to DG
- Added implementation details and class diagram for `Storage` system
- Added implementation details and sequence diagrams for `rm` feature (delete selected contacts, delete all contacts and
  delete contact fields)

### Team-based tasks

