# Project Portfolio Page - Decodex

## Overview
This is a student project for Software Engineering & Object-Oriented Programming (CS2113/T). And, I am **Sim Tian Boon**, one of the contributors of `Decodex`.

## Project Name: `Decodex`
`Decodex` is a Command Line Interface (CLI) application for Capture-The-Flag (CTF) players to quickly transform data from one format to another easily and intuitively.

## Summary of Contributions
Below are my contributions to the `Decodex`

*Note that the PR links below are the more notable ones - easier to find.*

Features/Functionalities

1. Added `Parser` functionality to parse user inputs (For non-recipe commands) ([#53](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/53), [#72](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/72/files))
    - Functionality - Performs the parsing of user input to corresponding command.
    - Justification - Helps to parse user input so that `Decodex` can run the correct command that users expect.

2. Added the ability to show current data ([#274](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/274))
    - Feature - Shows the user the current data that they have. By default, this current data will be your original data if no modules has been run on it.
    - Justification - Allows user to keep track of the current data they are working on, and also credits to @alwinangys for the [suggestion](https://github.com/alwinangys/ped/issues/4).

3. Added Base64 Modules (Base64 Encoder and Decoder) ([#35](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/35))
    - Feature - Allows users to use these modules to encode/decode using base64 formats.
    - Justification - This is one of the more common encoding formats that appear in CTFs.

4. Added ability to load recipes from files on start up ([#208](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/208/))
    - Functionality - Performs the loading of saved recipe files upon run of `Decodex`.
    - Justification - Allows users to load previously saved recipes and provides convenience.

5. Added ability to save recipe into file, and delete the recipe file ([#208](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/208/))
    - Functionality - Saves (Auto) the recipe into a file named `recipeName.txt` and stores it in the `recipe/` directory. The auto-deletion of the recipe file happens when user deletes the recipe on `Decodex`.
    - Justification - Allows users to save recipes into file and delete without having to manually do it.

## Enhancements to Existing Features
- Refactored and improved Coding Standards
    - `Storage` - Remove the magic literals [#280](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/280) and removed dead code [#251](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/251/files)
    - `Decodex` - Grouped a code fragment together and made the while loop less complex/more readable [#280](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/280)
    - Others - [#325](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/325), [#366](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/366)
- Assertion for `Parser` - [#99](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/99/files)
- Logging for `Decodex.class` - [#96](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/96)
- Wrote JUnit Test for Base64 Modules, Parser and Storage - [#35](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/35), [#72](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/72/), [#91](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/91), [#319](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/319), [#325](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/325)
- Attempted to standardize coding styles/documentation - [#47](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/47), [#147](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/147)
    - I learnt that this is probably one of the hardest thing to do since everyone has their own style.
- JavaDocs for `Decodex` and `Storage` - Some notable ones ([#280](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/280))
- Bug Fixes - [#274](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/274), [#286](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/286)

## Documentation (Most classified under *Others* due to page limit)
- [User Guide](https://ay2122s1-cs2113t-t10-3.github.io/tp/UserGuide.html)
    - Added documentation for Storage, Basic Commands - [#246](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/246), [#308](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/308)
    - Others - [#103](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/103), [#220](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/220/files), [#317](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/317)
- [Developer Guide](https://ay2122s1-cs2113t-t10-3.github.io/tp/DeveloperGuide.html)
    - Added/Updated diagrams and description for Data Component, ShowCommand, HelpCommand and Storage Testing - [#321](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/321), [#323](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/323), [#360](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/360)
    - Others - [#180](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/180), [#231](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/231), [#323](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/323)

## Community
- Non-trivial PR reviews - [#39](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/39), [#52](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/52), [#148](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/148) , [#156](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/156), [#166](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/166), [#183](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/183), [#190](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/190), [#192](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/192), [#194](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/194), [#196](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/196), [#275](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/275)
- Contributions to CS2113/T forum - [1 (not tP related)](https://github.com/nus-cs2113-AY2122S1/forum/issues/12), [2](https://github.com/nus-cs2113-AY2122S1/forum/issues/101), [3](https://github.com/nus-cs2113-AY2122S1/forum/issues/110)
- Bugs reported and other team reviews - [1](https://github.com/nus-cs2113-AY2122S1/tp/pull/32), [2](https://github.com/AY2122S1-CS2113T-T09-2/tp/issues/219), [3](https://github.com/AY2122S1-CS2113T-T09-2/tp/issues/210)
  , [4](https://github.com/AY2122S1-CS2113T-T09-2/tp/issues/212), [5](https://github.com/AY2122S1-CS2113T-T09-2/tp/issues/198), [6](https://github.com/AY2122S1-CS2113T-T09-2/tp/issues/195), [7](https://github.com/AY2122S1-CS2113T-T09-2/tp/issues/179), [8](https://github.com/AY2122S1-CS2113T-T09-2/tp/issues/184)
