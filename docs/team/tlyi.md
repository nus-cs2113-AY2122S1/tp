---
layout: page
title: Le Yi's Project Portfolio Page
---

## Overview
Fitbot is a desktop app that helps university students who are looking to keep track of their calorie consumption and calorie
output with the speed and convenience of command-line based tools, especially in times of online school.

## Summary of Contributions
The following sections summarise what I have contributed to the project.

#### **New Feature**: Added the ability to parse user input into commands
- What it does: Makes sense of user input and extracts the relevant type of command and parameters.
- Justification: This feature is necessary for Fitbot to understand the user inputs.
- Highlights:
  - _Flexible parameter ordering_ - Parameters can be provided in any order and will still be parsed appropriately for the user's convenience.
  - _Optional parameters_ - Some commands are able to take in many parameters, but not all are required at all times. Hence, the parser was implemented such that it can handle variable number of parameters for the same command. For example, to edit the profile using the `profile` command, users can input up to 7 parameters, but at any time, only one parameter is required. 
  This makes the commands shorter and more user-friendly.
  - _Error handling_ - Specific error messages are shown for cases where user input is invalid, e.g. item type is not specified, unnecessary parameters detected, string given where numbers were expected, etc. 
- Pull requests: [#36](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/36), [#42](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/42), [#96](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/96), [#121](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/121), [#169](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/169)


#### **New Feature**: Add, View, and Delete Food Bank/Exercise Bank Items
- What it does: The Food Bank and Exercise Bank allows users to store commonly consumed food/commonly done exercise items and their respective calories so that the next time they want to record them, they no longer have to provide the calories for these items. 
- Justification: This makes it much more convenient for users to record their calories as they do not have to manually key in the calories everytime.
- Highlights: 
  - _Case insensitive match for item names_: For example, if the user stores the item with name "jogging" in the Exercise Bank, the next time he/she tries to record "JOGGING" as an Exercise Item, the calories for the item "jogging" will be automatically retrieved. This is so that it is more convenient for the user and he/she does not need to worry if the names are an exact match. 
- Pull requests: [#96](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/96)




#### **Code Contributed**:  [Reposense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=tlyi&tabRepo=AY2122S1-CS2113T-F14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


#### **Project Management**:
- Created issues and user stories for `v2.0`, `v2.0b`
- Assigned PE-D issues to team for `v2.1`
- Oversaw the code integration and reviewing process with the team for major code increments 
([#37](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/37), 
[#123](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/123),
[#224](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/224),
 [#230](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/230),
[#234](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/234/files))


#### **Enhancements To Existing Features**:

#### **Built the skeleton code for Command classes**
To start off the project, we needed a base structure to build on for parsing and execution of commands. I set up the abstract `Command` class and the initial few `XYZCommand` classes for v1.0 to provide the structure for the rest of the team to build on.
([#36](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/36))

#### **Make the Logic Component more OOP**

This was done in several increments.
1. Created `Parser` as an interface with various `Parser` classes specific to each `Command` class implementing it ([#102](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/102))
2. Integrate command parsing and execution into a single `LogicManager` class ([#141](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/141))
3. Integrate `DataManager` and `StorageManager` with `LogicManager` so that all operations involving command parsing, data manipulation and data saving are contained within `LogicManager`. ([#145](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/145))



#### **Documentation**:
- User Guide:
  - Added more details to the introduction of Fitbot ([#128](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/128))
  - Added a section to explain the usage of the User Guide ([#128](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/128))
  - Added documentation for all Food Bank and Exercise Bank related commands, i.e. the sections [_"Building Your Food Bank"_](https://ay2122s1-cs2113t-f14-2.github.io/tp/UserGuide.html#45-building-your-food-bank) and [_"Building Your Exercise Bank"_](https://ay2122s1-cs2113t-f14-2.github.io/tp/UserGuide.html#46-building-your-exercise-bank).
  - Added shortcuts back to content page after every major section for easier navigation ([#230](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/230))
- Developer Guide:
  - Added documentation for the [_`Logic` component_](https://ay2122s1-cs2113t-f14-2.github.io/tp/DeveloperGuide.html#logic-component) ([#134](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/134), [#241](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/241))
  - Added documentation for the implementation of [_"Parsing of Commands"_](https://ay2122s1-cs2113t-f14-2.github.io/tp/DeveloperGuide.html#parsing-of-commands) ([#241](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/241))
  - Added instructions of manual testing on [_"Building Food Bank"_](https://ay2122s1-cs2113t-f14-2.github.io/tp/DeveloperGuide.html#building-food-bank) and [_"Building Exercise Bank"_](https://ay2122s1-cs2113t-f14-2.github.io/tp/DeveloperGuide.html#building-exercise-bank) ([#230](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/230))
  - Standardised styling for PlantUML diagrams ([#117](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/117))
- Standardised the styling on Github Pages for Product Website. ([#115](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/115), [#236](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/236))


#### **Community**: 
- PRs reviewed (with non-trivial comments): [#32](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/32), [#33](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/33), [#34](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/34), [#35](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/35). [#39](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/39)

