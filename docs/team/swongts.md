# Samantha Wong - Project Portfolio Page

## Overview: TourPlanner
**TourPlanner** is a desktop application meant for employees of travel agencies. 
Its main purpose is to manage clients, flights, accommodations and client packages data, optimized for use via a Command Line Interface (CLI).

It is written in Java, and has about 7 kLoC.

### Summary of Contributions
**Code contributed**: [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=F11&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=swongts&tabRepo=AY2122S1-CS2113T-F11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

**Enhancements implemented**:
* `ClientPackage` and `ClientPackageList` classes
  * What it does: Stores the related `Client`, `Flight` and `Tour` into one `ClientPackage`.
  * Justification: This feature is crucial to TourPlanner and serves as a link between these classes.
  * Highlights: To make it easier to use and more efficient for commands like `find` and `cut` to use,
`ClientPackage` stores the actual `Client`, `Flight` and `Tour` objects instead of simply using a reference String id.
  * Highlights: Created methods to get an ArrayList of `ClientPackage` based on the `Client` / `Flight` / `Tour` which 
were used across a few features.

* `AddClientPackageCommand`
  * What it does: Allows user to create the `ClientPackage` using the client, flight and tour id.
  * Highlights: The command searches for the ids with the respective `ObjectList` to find the 
`Object` (`Client`, `Tour`, `Flight`) to store into `ClientPackage`.

* `CutCommand` for `Client`, `Tour`, `Flight` and `ClientPackage`
  * What it does: Deletes the specific `Object` from its respective `ObjectList`.
  * Justification: Crucial feature for the application to be functional.
  * Highlights: When the `Client` / `Tour` / `Flight` is deleted, the corresponding `ClientPackage` which contains the specific
`Object` must also be deleted from the `ClientPackageList`. This is so that it wouldn't be incoherent that the `ClientPackage`
contains an `Object` which no longer exists.

* `HelpCommand`
  * What it does: Lists all available commands.
  * Justification: Allows user to have an overview of how to use the commands conveniently.

**Contributions to documentation**:
* User guide:
  * Added `cut` feature
* Developer guide:
  * Added diagrams and explanations for `cut`, `sort`, `list` feature and `Command` class
  * Added Appendix D and E

**Contributions to team-based tasks**:
* Manage issue tracker: In charge of issue tracker for `v2.1`, helped with labels for `v1.0`.
* Release management: In charge of release for `v1.0`.
* General code enhancement: Helped look out for code quality, and refactor other's codes to ensure SLAP 
(e.g. `TourPlanner`).

**Review/mentoring contributions**: 
* Major bug fixes to ClientPackageStorage as the client packages weren't loading properly from storage.
* Frequently reviewed and approved pull requests.
* Helped to proof-read Developer's Guide and link everyone's parts to ensure it sounds coherent.