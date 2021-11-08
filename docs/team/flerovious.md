
# Varun - Project Portfolio Page

## Overview
_Ha(ppy)Bit_ is a desktop app aimed to **empower students** to achieve their
**goals**—whether personal, academical, or health—amidst the hectic and stressful
**university life**, through cultivating good **habits**.
Users can only run the app on a Command Line Interface (CLI).

It is written in Java and has about 10 kLoC.

## Summary of Contributions
Below are my key contributions to Ha(ppy)Bit.

### Code Contribution
View my [RepoSense](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=flerovious&tabRepo=AY2122S1-CS2113T-F14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

### Features

#### v1.0

- Implemented the `Command` class `AddCommand` to add habits with full test coverage
- Integrated `AddCommand` with `GoalList` and `Ui`

#### v2.0

- Updated most of the `Parser` subclasses, except AddParser and UpdateParser
- Refactored classes in the `parser` package to reduce code redundancy through abstraction and inheritance
- Added JUnit tests for most `parser` package classes to achieve 100% line coverage and about 80% branch coverage
- Fix bugs with `parser` package

#### v2.1

- Updated and refactored code for `parser` packages for new Regex based `getParameters`
- Updated `parser` package testing for new `HaBitParserException` raised during runtime
- Extended test coverage for `ParserManager` and the base `Parser` class
- Fixed bugs uncovered from `PE Dry Run` together with the rest of the team

### Documentation

#### [User Guide (UG)](https://ay2122s1-cs2113t-f14-1.github.io/tp/UserGuide.html)

- Contributed to UG for `add` and `set` commands

#### [Developer Guide (DG)](https://ay2122s1-cs2113t-f14-1.github.io/tp/DeveloperGuide.html)

- Added code implementation details on DG section 4
- Added `AddGoalCommandSequenceDiagram` and review the rest of the sequence diagrams