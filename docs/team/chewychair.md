# ChewyChair's Project Portfolio Page

### Project: UniMods

UniMods is a light-weight Command Line Interface (CLI) Application that provides a means for students to plan for 
their academic journey in NUS. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the capability to parse and save jsons from the NUSMods API and local storage.
  * Justification: This feature is extremely important as it is the backbone of the search, show and update functions.
  * Highlights: This feature was implemented with GSon doing the heavy lifting. In `v2.0`, some issues were had when we
  started working with the weeks attribute which was a field with a variable data type but it quickly was resolved with 
  WeeksAdapter.
  * Credits: *GSon handles all parsing of jsons.* [Thread](https://stackoverflow.com/questions/31758872/how-to-handle-different-data-types-with-same-attribute-name-with-gson)
    from StackOverflow with a simple method to parse a json field with a variable data type (used by WeeksAdapter).

* **New Feature**: Added the search command.
    * What it does: allows the user to search for mods from the NUSMods API.
    * Justification: This feature is extremely important as it allows users to find mods. 
    * Highlights: This feature was initially implemented simply by creating Module objects from an extremely large json,
  in local storage. However, it was changed to instead search online by default and offline if there is no connection.
  Later on, the quicksearch (local search) flag was first implemented as online searches were extremely slow if the
  search term is broad. In `v2.0`, the other search flags were implemented to allow the user to construct more detailed
  queries. Lastly, due to the case where a user would want to cancel a slow search command, the search command was
  rewritten as a thread which can be canceled via another thread.

* **New Feature**: Added the show command.
    * What it does: allows the user to show details pertaining to a mod.
    * Justification: This feature is extremely important as it allows users to find out more about a mod.
    * Highlights: This feature is a simple feature that simply prints information from the mod json. In `v2.0` an
  additional printout for exam dates was added.

* **New Feature**: Added the update command.
    * What it does: allows the user to update their local database by pulling mod jsons from the NUSMods API.
    * Justification: This feature improves the product significantly because a user can keep their local database
  up to date.
    * Highlights: This feature was one of the first things implemented in order to pull mod information from the NUSMods
  API. It was later added as a command for the user to use as well. It was also integrated into the search/show commands
  so that pulling any mod jsons from the API will now update the local database. Towards the end of `v2.0`, like the
  search command, the update command was rewritten as a thread which can be cancelled via another thread.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=chewychair&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=ChewyChair&tabRepo=AY2122S1-CS2113T-W12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed issues for `v2.0` and `v2.1` on GitHub

* **Documentation**:
    * User Guide:
        * Added documentation for the features `search`, `show` and `update`
        * Did cosmetic tweaks to existing documentation of feature `show`: [\#178]()
        * Cleaned up some documentation bugs found in the PE-D.
        * Added some common issues to the QnA section.
    * Developer Guide:
        * Added implementation details of the `search`, `show` and `update` features and associated diagrams.
        * Added user stories.
        * Added component diagrams for UI, Online and Storage.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#24](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/24), 
  [\#26](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/26), 
  [\#60](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/60)
    