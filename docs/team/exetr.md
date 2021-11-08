# Teo Chuan Kai - Project Portfolio Page

## Overview

Libmgr is a desktop app for managing the inventory of libraries, optimised for use via a Command Line Interface (CLI). 
Designed for fast typists, it can help to augment the day-to-day tasks of a librarian and can help them to get tasks done in an efficient manner.

## Summary of Contributions

### Code Contributed

[Click to view code contribution on RepoSense](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=t16&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=exetr&tabRepo=AY2122S1-CS2113-T16-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

### Enhancements Implemented

1. Added subclasses that extend the main `Item` class by [silin](https://github.com/silinche), which include the `Audio`, `Book`, `Magazine`, `Video` and `Miscellaneous` classes. ([#62](https://github.com/AY2122S1-CS2113-T16-1/tp/pull/62))

2. Added the `TextUI` class, handling inputs from the user and displaying messages from the application. ([#15](https://github.com/AY2122S1-CS2113-T16-1/tp/pull/15))

3. Extended on [silin's](https://github.com/silinche) `AddCommand` and all child classes (`AddAudioCommand`, `AddBookCommand`, `AddMagazineCommand`, `AddMiscellaneousCommand`, `AddVideoCommand`) ([#87](https://github.com/AY2122S1-CS2113-T16-1/tp/pull/87)).
It allows users to creating new items of different categories (audio, book, magazine, video, miscellaneous) and insert them into the catalogue.

4. Implemented `RemoveCommand` class ([#31](https://github.com/AY2122S1-CS2113-T16-1/tp/pull/31)). 
Provides method to remove a specific item from the catalogue.

5. Added `UnreserveCommand` ([#92](https://github.com/AY2122S1-CS2113-T16-1/tp/pull/92)).
Provides functionality to make available an item that has been previously reserved by some user, in the case where a previous reservation has been invalidated or cancelled, it allows the referenced item to be loaned out by other users.

6. Implemented `Parser` class to handle user commands ([#174](https://github.com/AY2122S1-CS2113-T16-1/tp/pull/174)).
Identifies the specific command issued by the user and Separates the attributes or flags and the related values supplied by the user. It also handles invalid entries such as empty or duplicate attributes.

7. Implemented `Storage` and `JsonFactory` classes ([#176](https://github.com/AY2122S1-CS2113-T16-1/tp/pull/176)).
The `storage` class acts as an interface that `libmgr` uses to perform reading and writing operations to and from `data.json`. Within this process, the `JsonFactory` is used to serialize and deserialize the contents of the catalogue.

### Contributions to the User Guide

1. Created base format of the Markdown document including different section templates, table of contents and FAQ. ([#84](https://github.com/AY2122S1-CS2113-T16-1/tp/pull/84)).
2. Added usage for `rm`, `res` and `unres` commands. ([#104](https://github.com/AY2122S1-CS2113-T16-1/tp/pull/104)).
3. Added command usage summary table ([#171](https://github.com/AY2122S1-CS2113-T16-1/tp/pull/171)).


### Contributions to the Developer Guide

1. Created base format of the Markdown document including different sections and table of contents. ([#191](https://github.com/AY2122S1-CS2113-T16-1/tp/pull/191/files)).
2. Designed high-level architecture diagram for the program ([#191](https://github.com/AY2122S1-CS2113-T16-1/tp/pull/191/files)).
3. Added the following diagrams ([#191](https://github.com/AY2122S1-CS2113-T16-1/tp/pull/191/files)).
   - Application launch and overall component interaction sequence diagrams and accompanying descriptions.
   - Data, storage and UI components
   - Sequence and object diagram for usage of add command `add b t/1984 i/91 a/George Orwell`
   - Sequence diagram for operations involved when `data.json` is read from or written to.

### Contributions to team-based-tasks

1. Implemented skeleton code for the team project which included `ui`, `commands` and `data` packages.
2. Helped in maintaining the issue tracker.
3. Managed `v1.0` and `v2.0` releases.
4. Incorporated `Jackson Databind` and `Jackson Datatype JSR310` to simplify serialization and deserialization of items within the catalogue.
5. Added assertions and logging.


### Review/mentoring contributions
- Pull Requests reviewed (with non-trivial review comments): [#32](https://github.com/AY2122S1-CS2113-T16-1/tp/pull/32), [#37](https://github.com/AY2122S1-CS2113-T16-1/tp/pull/37), [#70](https://github.com/AY2122S1-CS2113-T16-1/tp/pull/70), [#81](https://github.com/AY2122S1-CS2113-T16-1/tp/pull/81)
- Reported bugs and suggestions for other teams in the class (
   [1](https://github.com/exetr/ped/issues/1),
   [2](https://github.com/exetr/ped/issues/2),
   [3](https://github.com/exetr/ped/issues/3),
   [4](https://github.com/exetr/ped/issues/4),
   [5](https://github.com/exetr/ped/issues/5)
)
