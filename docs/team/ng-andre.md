# Ng Andre - Project Portfolio Page

## Overview
**ConTech** is a Command Line Interface (CLI) application for managing computing-related contacts.

The project started on 3 September 2021, where our team brainstormed and came up with our target users and app
direction. We proceeded to create user stories related to our desired product and narrowed them down for each
iteration so that we were able to comfortably deliver our features.


## Summary of Contributions

### Code Contributed
The code written by me (`ng-andre`) can be found [here](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=ng-andre&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=falsehttps://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=ng-andre&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false). 


### Enhancements Implemented

1. `Edit` feature
   - Command to allow the user to edit a contact's details
   - Implementation allows flexible user input with minimal restrictions
2. `Search` feature
   - Command to allow the user to search for a contact
   - Implementation allows flexible user input with optional flags for searching
3. Flags for `delete` feature
   - Update command to allow users to specify a field to be deleted for a contact
   - Implementation allows optional user input for flags to specify deleted fields
4. Parsers
   - `EditContactParser.java`: Parses user input, specified index and specified flags, and creates a syntactically valid `EditContactCommand` to be executed
   - `SearchContactParser.java`: Parses user input for search query and optional flag, and creates a syntactically valid `SearchContactCommand` to be executed
   - `IndexParser.java`: Parses the index specified by the user input for all commands and returns the corresponding `int` value


### Contributions to the UG
- Added documentation for `edit` function
- Added documentation for `search` function
- Updated documentation formatting and overall readability

### Contributions to the DG
- Added documentation for `edit` function
- Added documentation for `search` function
- Added UML diagrams for all the above-mentioned features

### Contributions to team-based tasks
- Handled release management and published v1.0 and v2.0 releases
- Updated User Guide (UG) and Developer Guide (DG) documentation that were not specific to any feature:
  - Reviewed and updated overall User Guide and fixed inconsistencies between UG and error messages in application

### Review/mentoring contributions
- Reviewed PRs and ensured overall code quality/formatting was up to standard

### Contributions beyond the project team
