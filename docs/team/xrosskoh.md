# Koh Meng Kiat, Kenneth - Project Portfolio Page

## Overview
CardLI is a desktop app that helps you create, organise, and review flashcards via a Command Line
Interface. It is written in Java.

### Summary of Contributions

#### Implemented features
**Added marking and scoring of tests**

This feature marks a user's test and outputs the test score to the system output.
This feature will let users know the questions on which they have made errors, and use 
the test score to gauge their overall competency with the content on the flashcards.

**Test order randomizer**

This feature randomizes the order in which a user's flashcards are tested. 
The feature prevents users from memorizing the answers to the test in
the order of the flashcards within the deck. It aims to simulate an examination situation 
where the user would not know the order of the questions beforehand, making the test feature
a good gauge of the user's grasp on the concepts tested. 

**Flashcard storage**

This feature saves a user's decks of flashcards into an external `json` file after
each command. It also reads from the `json` file upon each startup of CardLI to
initialize the user's saved deck of flashcards. 
This feature provides users with access to decks of flashcards added in earlier
sessions without having to re-add then upon every startup of the application. 

**Test history storage**

This feature saves a user's test history into an external `json` file after each 
command. It also reads from the same `json` file upon each startup of CardLI to 
initialize the user's test history.
This feature allows users to view tests that they had previously done, including
tests that were completed during an earlier session. This can help them revise 
better by learning from mistakes made in past tests.

#### Feature enhancements

1. Determined and implemented the format of the saved data within the `json` files.
2. Added JUnit tests all for `toString()` and `toJSONObject()` methods.

#### Code Contributions

My RepoSense Link can be found [here](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=xRossKoh&tabRepo=AY2122S1-CS2113T-F12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false).

#### Documentation Contributions

**User Guide**

Contributed to the `Introduction`, `Quick Start` and `FAQs` sections.

**Developer Guide** 

1. Contributed the implementation details and diagrams under the `Storage` section.
2. Contributed the `Deleting a deck` subsection under the `Instructions for Manual Testing` section.
3. Contributed the screenshots of the JSON files.

#### Project Management

1. Managed PRs for milestone v1.0
2. Managed GitHub issues for milestone v2.0

### Community
1. Reviewed and gave suggestions on other team's [Developer Guide](https://github.com/nus-cs2113-AY2122S1/tp/pull/10).
2. Tested and reported bugs on other team's application in PE-DryRun.

