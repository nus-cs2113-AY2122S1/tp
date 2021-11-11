### Project: UNIMods

UNIMods is a light-weight Command Line Interface (CLI) Application that provides a means for students to plan for
their academic journey in NUS. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the add module command.
    * What it does: allows the user to add modules to their timetables.
    * Justification: This feature is extremely important as it allows users to plan and organise their timetable.
    * Highlights: This feature was one of the first few features to be implemented and started off by simply adding modules into
    * a user's timetable. However, future iterations has evolved this feature into parsing module and semester data in order to 
    * display lesson information for each lesson type to allow easier selection of lesson for the module. In `v2.0` this feature 
    * received an update in which it will verify with the existing schedule in a user's timetable when displaying module lesson information
    * and display a `[CONFLICT]` disclaimer to solve the problem of overlapping lessons. 
    * 

* **New Feature**: Added the add event command.
    * What it does: allows the user to add personal events to their timetables.
    * Justification: This feature is extremely important as it allows users to plan and organise their school timetable and personal timetable.
    * Highlights: This feature was added in `v2.0` to enhance the functionality of the product to make it allow users to plan their timetable more effectively.
    * Using TimetableUserItem, events are more easily added and deleted.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=Poopies&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=Poopies99&tabRepo=AY2122S1-CS2113T-W12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed issues for `v1.0`, `v2.0` & `v2.1` on GitHub

* **Enhancements to existing features**: 
    * enhanced `add` feature by notifying users of potential lesson conflicts when selecting lessons or events. This is especially important when organising timetable since overlapping lessons/events may be an oversight. [\#77](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/77)
    * enhanced `delete`, `timetable`, `timetableUI` to help ensure that all modules, lessons and events can be displayed properly [\#74](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/74), [\#93](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/93/files) [\#191](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/191)

<div style="page-break-after: always;"></div>

* **Documentation**:
    * User Guide:
        * Added documentation for the feature `add` [\#188](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/188).
        * Did cosmetic tweaks for UserGuide [\#188](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/188).
    * Developer Guide:
        * Added implementation details with sequence diagram of the feature `add` [\#188](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/188). 
        * Did cosmetic tweaks for UserGuide [\#188](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/188).

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#30](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/30), [\#55](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/55), [\#94](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/94), [\#185](https://github.com/AY2122S1-CS2113T-W12-2/tp/pull/185)
    * Reported bugs and suggestions for other teams in the class [\#23](https://github.com/nus-cs2113-AY2122S1/tp/pull/23)
