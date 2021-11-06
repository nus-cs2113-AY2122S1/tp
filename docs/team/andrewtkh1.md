# Andrew's - Project Portfolio Page

## Project - Large Order Tracking System(LOTS)
Large Order Tracking System (LOTS) is a Command Line (CLI) program that is written in Java and meant to run on `Java 11`.
This program enables users to keep track of multiple food orders from a pre-set list of food items.

### Summary of Contributions

- **Code contributed**: [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=andrewtkh1)

**New Features**:
   - **Added the feature `Find`.**
        - `What it does`- Allows user to search the current list of orders for a specific name.
        - `Justification` - As the name suggest, this is meant to track large number of orders.
        As such, the find feature allows the user to easily find a person in the list.
        - `Highlights` - Decision was made to match the user's search input as a substring of
        name too. This would greatly improve the user's experience, as in the event that
        one of the entries name was too long, the user could just search for a substring of the
        name and it would still return as true.
          
**Enhancements to existing features**:
- Implemented the `Parser` Class.
    - Had to implement it in such a way that we could return a single `Command` type object that can
      be used by all the different commands.
    - Processes the user's input to determine what type of command was entered.
      This allowed for a more `OOP` way of handling the other different commands and led to a neater & more readable code.
- Designed the flow of the main class `Duke` to ensure that the different components of the program work together.
    - Added `Logging` in the main class to log any unhandled exceptions.
    - Also allowed us to easily know what error is and where.
- Designed `Regular Expression (REGEX)` needed for `Add`,`Find` & `Delete` Command.
    - Using `regex` allowed us to narrow the number of edge cases significantly when taking in user inputs.
    - Just had to focus on what is the format of the command.
    - Allowed for easy catching of incorrect input.    
- Contributed to the overall program by setting up the `UI` & `Unknown Command`.
- Added `JUnit` tests for `Parser` & `Find` class.
- Designed `IO-Redirection` test based on `Equivalence partitioning`.
- Fixed multiple `Bugs` found ([#38](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/38), [#52](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/52), [#137](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/137), [#230](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/230))
- General maintenance of code ([#44](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/44), [#93](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/93))

**Project Management**:
- Setup Organization & Team Repository.
- Setup repo's `branch protection` to prevent unauthorized merging.
- Setup Repo's overall `Markdown` language to be used.
- Manage `Issue tracker` to ensure no task is left out & all pending tasks are currently tracked.
- Manage Milestones (`v1.0`, `v1.0 wrap-up` ,`v1.5`, `v2.0`, `v2.1`)
- Managed [Releases](https://github.com/AY2122S1-CS2113-T13-2/tp/releases) (`v1.0`, `v2.0`)
- PR reviewed with non-trivial comments. ([#101](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/101) ,[#37](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/37) ,[#34](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/34) ,[#42](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/42))

**User Guide**
- Added documentation for find Feature. [#159](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/159)
- Fixed `Anchor` links for content page. [#213](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/231)
    - Initial Markdown language was `GFM` but this had issues with the `anchor`.
    Ultimately decided to revert to the default Markdown language but had to restructure most of our documentation.

**Developer Guide**
  - Contributed to part of the `Logical Component` under `Design`. [#136](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/136)
  - Contributed the brief overview of class [diagram](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/UMLdiagrams/ParserDiagrams/ParserClassDiag-Page-1.jpg) for `Parser`.
  - Added the implementation of `Parser` along with the diagrams & it's alternate implementation.[#110](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/110)
  - Updated the `Product Scope` which consisted of `Target User profile` & `Value Proposition`. [#136](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/136/files)
  - Updated the `User stories` & the `Non-functional Requirements`. [#136](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/136/files)
  - Contributed the `Instructions for manual testing`. [#136](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/136/files)
      - Designed test cases based on `Equivalence Partitioning`.

**Setting Up for Developer**
- Wrote the documentation for setting up. [#118](https://github.com/AY2122S1-CS2113-T13-2/tp/pull/118)
- Active Participation in Module Forum ([Overall contribution](https://nus-cs2113-ay2122s1.github.io/dashboards/contents/forum-activities.html#2-tan-drew-andrewtkh1-33-posts))
  - Resolve GitHub CI Checks failing with Prof. [#95](https://github.com/nus-cs2113-AY2122S1/forum/issues/95)
  - Resolve issue of line separator between different OS [#39](https://github.com/nus-cs2113-AY2122S1/forum/issues/39)
- Reviewed other Team's Project ([Example](https://github.com/nus-cs2113-AY2122S1/tp/pull/48/files))
- Bugs found for other team during PED ([Bugs Found](https://github.com/andrewtkh1/ped/issues))