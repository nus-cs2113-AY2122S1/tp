---
layout: page
title: adricpjw's Project Portfolio Page
---

### Project: UNIMods

UNIMods is a light-weight Command Line Interface (CLI) Application that provides a means for students to plan for
their academic journey in NUS. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Created the Timetable Class, added the TimetableUI and TimetableCommand.
    * What it does: Base class structure to store lessons into a weekly schedule, and allows users to view <br>
      the timetable in a UI-formatted grid.
    * Justification: This feature is extremely important as it allows for all other features surrounding the Timetable <br>
      to be implemented. It is also a key feature by itself in displaying what the user's timetable looks like.
    * Highlights: This feature allows for future commands to be added in future concerning the timetable. <br>
      It uses fixed-size arrays instead of arraylists for 24-hour schedules to be memory efficient as well as speed-optimised 
      since the timeslot is accessed in O(1) by index. In addition, the TimetableUI uses String formatting to ensure that 
      each displayed cell within the grid is strictly formatted and will not break even for extreme-examples.

* **New Feature**: Added the check command.
    * What it does: allows the user to check if they have a module's pre-requisite.
    * Justification: This feature is useful as it allows users to find out instantly if they have met a module's pre-requisite
      without having to manually look through the pre-requisite tree.
    * Highlights: This feature required parsing of a recursive tree that did not have a fixed structure in the JSON. Some
      modules had deeply nested recursive and/or trees for prerequisites, while others simply had a single string
      (e.g. CS2040's prerequisite tree is "CS1010"). Thus, after parsing the json, a recursive function is used to determine
      if the user has satisfied every part of the prerequisite tree.
    * Credits: *GSon handles all parsing of jsons.*

* **Enhancement Feature**: Made the help command more robust.
    * What it does: Prints and displays the command summary for all the available commands that Unimods accepts
    * Justification: This enhancement improved the way help command was initially implemented, by making it print the 
      syntax and action of each command defined individually.
    * Highlights: This feature was first implemented in v1.0, but as a static printer for a chunk of text. Since v1.0, 
      many other features has been implemented and would be difficult to manually format and write into the wall of text that
      was the command summary. Instead, each command's syntax and action summary is defined individually in each class,
      and the help command simply prints them out after String formatting has been applied.
  
* **Enhancement Feature**: Implemented save storage for Profile.
    * What it does: Saves the current user profile to a local json file so it can be reloaded on launch
    * Justification: Profile and ModuleRecord was initially implemented by me, and were used primarily for checking of 
      pre-requisite. However, it did not have any longevity if there was no way to save the profile.
    * Highlights: This feature uses GSON to save the profile into a json locally.
    * Credits: *GSon handles all parsing of jsons.
  

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=W12-2&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=adricpjw&tabRepo=AY2122S1-CS2113T-W12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed issues for `v1.0` and `2.0` on GitHub
  * Reviewed 24/46 of the reviewed pull requests made by teammates, regardless if their work was related to my assigned work so that I
    would be up to date with the progress of the project.

* **Enhancements to existing features**:
  * Refactored a large amount of code to make the code more robust for `edit`, `Timetable`,
    `adding` of personal tasks, and fetching of modules. 
  (PR [#183](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/183), 
  [#102](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/102),
  [#79](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/79) ,
  [#70](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/70/))
  
  

* **Documentation**:
  * User Guide:
      * Added table of contents, changed language to user-centric
      * Added guide for features `timetable`, `check` and `help`
      * Added note on how to read the feature section

  * Developer Guide:
      * Created overall architecture diagram and component diagram for the Logic

* **Community**:
  * PRs reviewed (with non-trivial review comments): 
  [#10](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/10/),
  [#12](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/12/),
  [#13](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/13/),
  [#24](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/24/),
  [#26](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/26/),
  [#29](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/29/),
  [#30](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/30/),
  [#49](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/49/),
  [#51](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/51/),
  [#54](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/54/),
  [#55](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/55/),
  [#56](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/56/),
  [#67](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/67/),
  [#74](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/74/),
  [#90](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/90/),
  [#93](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/93/),
  [#95](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/95/),
  [#99](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/99/)
    