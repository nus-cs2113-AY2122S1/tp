## Alvin Lim (Alvinlj00) - Project Portfolio Page

### Project: Student Life Affairs Manager (SLAM)
SLAM is a desktop application for student group leaders in NUS to manage their group events, tasks and members. This application allows students of an organisation to plan and manage their events by adding custom events and tasks to their schedule. Users can add tasks to complete under each event and mark them as done to keep track of the tasks or events that are over. Users can also assign tasks to members of the organisation, and do eveything in the application through the use of a CLI.

Given below are my contributions to the project.

- **New Feature**: Added the functionality to add events, tasks and members. 
  - _What It Does_: Allows the user to add events, tasks and members to the catalog of events and member roster. This catalog and roster can be accessed and updated according to future commands. 
  - _Justification_: This feature is needed for any of these items to be added, kept track of and modified. 
  - _Highlights_: This feature required many exceptions to be caught since there are many cases in which the fields are entered incorrectly, as well as the requirement of events and members to be added before a task could be added.


- **New Feature**: Added the ability to mark an item as done or undone. 
  - _What It Does_: Allows the user to mark events or tasks as done so they no longer have to keep track of it. It also helps to show which members have finished their tasks. 
  - _Justification_: The feature to mark an item as done is required to remind users of the events and tasks that are over. The ability to undo tasks and events allow organisation members to uncheck them if more things need to be done. 
  - _Highlights_: This feature allows for multiple events or tasks to be marked or unmarked. It also tells the user if the tasks or events they selected have already been marked or unmarked, so no changes would be made for those specific items.


- **Code Contributed**: [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=Alvinlj00&tabRepo=AY2122S1-CS2113T-W12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


- **Project Management**:
  - Collaboratively managed releases `v1.0` - `v2.1` (3 releases) with project group members on GitHub.
  - Contributed the ideas of nesting `tasks` under `events` and assigning `members` to `tasks`. 
  - Helped to firm up the new role of the `Parser` class from v2.0 to v2.1 with the team via extensive discussions with sequence diagrams to assist in illustration and understanding.
  

- **Review Contributions**:
  - PRs reviewed with non-trivial comments: [#24](https://github.com/AY2122S1-CS2113T-W12-3/tp/pull/24), [#61](https://github.com/AY2122S1-CS2113T-W12-3/tp/pull/61)


- **Documentation**
  - User Guide
    - Added documentation for command features `add`, `done` and `undo`
  - Developer Guide
    - Added implementation details for `add`, `done` and `undo` functionalities. 