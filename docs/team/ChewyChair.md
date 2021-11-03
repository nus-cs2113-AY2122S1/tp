---
layout: page
title: ChewyChair's Project Portfolio Page
---

### Project: UNIMods

UNIMods is a light-weight Command Line Interface (CLI) Application that provides a means for students to plan for 
their academic journey in NUS. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the search command.
    * What it does: allows the user to search for mods from the NUSMods API.
    * Justification: This feature is extremely important as it allows users to find mods. 
    * Highlights: This feature was initially implemented simply by creating Module objects from an extremely large json,
  in local storage. However, it was changed to instead search online by default and offline if there is no connection.
  Later on, the quicksearch (local search) flag was first implemented as online searches were extremely slow if the
  search term is broad. Lastly, the other search flags were implemented to allow the user to construct more detailed
  queries.
    * Credits: *GSon handles all parsing of jsons.*

* **New Feature**: Added the show command.
    * What it does: allows the user to show details pertaining to a mod.
    * Justification: This feature is extremely important as it allows users to find out more about a mod.
    * Highlights: This feature is a simple feature that simply prints information from the mod json.
    * Credits: *GSon handles all parsing of jsons.*

* **New Feature**: Added the update command.
    * What it does: allows the user to update their local database by pulling mod jsons from the NUSMods API.
    * Justification: This feature improves the product significantly because a user can keep their local database
  up to date.
    * Highlights: This feature was one of the first things implemented in order to pull mod information from the NUSMods
  API. It was later added as a command for the user to use as well. It was also integrated into the search/show commands
  so that pulling any mod jsons from the API will now update the local database.
    * Credits: *GSon handles all parsing of jsons.*

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=chewychair&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=ChewyChair&tabRepo=AY2122S1-CS2113T-W12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed issues for v2.0 on GitHub

* **Enhancements to existing features**:

* **Documentation**:
    * User Guide:
        * Added documentation for the features `search`, `show` and `update`
        * Did cosmetic tweaks to existing documentation of feature `show`: [\#178]()
    * Developer Guide:
        * Added implementation details of the `search`, `show` and `update` features.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#60](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/60)
    