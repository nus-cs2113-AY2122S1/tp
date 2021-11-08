# Project Portfolio Page - Decodex

## Overview
This is a student project for Software Engineering & Object-Oriented Programming (CS2113/T).

I am **Lee Kai Wen, Aloysius**, one of the contributors of `Decodex`.

## Project Name: `Decodex`
`Decodex` is a Command Line Interface (CLI) application for Capture-The-Flag (CTF) players to quickly process data from one encoding format to another with extreme ease in the form of intuitive shorthand usage.

## Summary of Contributions
Given below are my notable contributions to the project.

### New Features / Functionalities
- Implemented the `ModuleManager` class along with tests.
   - What it does: Manages all module related activities (e.g. retrieving all modules, preparing module for the `select` command).
   - Justficiation: Serves as the centralised location to conduct all module management activities.
   - Pull request: ([#43](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/43))
- Added the `select`, `list` and `recipe deselect` commands along with tests.
   - What they do:
     - The `select` command allows the user to select a module and run it on the user provided data ([#68](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/68)).
     - The `list` command allows the user to list all available modules ([#69](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/69)).
     - The `recipe deselect` command deselects the recipe currently being edited ([#275](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/275)).
<div style="page-break-after: always;"></div>
- Implemented the `Ui`, `RegularMessages` and `ErrorMessages` classes along with tests.
   - What it does: `Ui` manages all UI related activities (e.g. Prints messages to the user with the appropriate statuses) while `RegularMessages` and `ErrorMessages` servers as a central location for all messages to be printed.
   - Justification: Serves as the centralised location to conduct all UI related activities.
   - Pull request: ([#154](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/154))
- Added the ability to process data to and from binary format along with tests.
   - What it does: Allows the user to encode data into binary format and decode from binary format.
   - Pull request: ([#155](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/155))
- Implemented the `RecipeCommandParser` class along with tests.
   - What it does: Handles further subcommand parsing for all `recipe` commands.
   - Justification: Separation of `recipe` command parsing provided clearer organisation of code.
   - Pull request: ([#207](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/207))

### Enhancements to Existing Features
- Extended the `select` command to:
   - Select modules with parameters ([#166](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/166))
   - Select recipes ([#202](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/202)).
- Extended the `list` command to list recipes ([#183](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/183)).

## Documentation
  - [User Guide](https://ay2122s1-cs2113t-t10-3.github.io/tp/UserGuide.html)
    - Added introduction and FAQ ([#244](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/244)).
    - Added `recipe deselect` usage ([#275](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/275/files))
  - [Developer Guide](https://ay2122s1-cs2113t-t10-3.github.io/tp/DeveloperGuide.html)
    - Added architecture diagram and details ([#181](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/181)).
    - Added implementation details of logic, module and basic commands ([#234](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/234)). 

## Community
- Non-trivial review of team members' PRs ([#39](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/39), [#52](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/52), [#208](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/208)).
- Reported bugs for other teams ([1](https://github.com/arraysius/ped/issues/4), [2](https://github.com/arraysius/ped/issues/9), [3](https://github.com/arraysius/ped/issues/12), [4](https://github.com/arraysius/ped/issues/13), [5](https://github.com/arraysius/ped/issues/14)).
