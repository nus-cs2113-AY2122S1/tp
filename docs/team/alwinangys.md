## Alwin Ang (alwinangys) - Project Portfolio Page

### Project: Student Life Affairs Manager (SLAM)
SLAM is a desktop application for student group leaders in NUS to manage their group events, tasks and members. This application allows students to plan and manage the organisation of events by allowing users to add custom events to their schedule. Members can then be assigned to tasks. Hence, student group leaders can have a neat and concise overview of everything under their charge, all through a fast CLI.

Given below are my contributions to the project.

- **New Feature**: Added ability to select any `Event`, `Task` or `Member`.
  - _What it does_: Allows the user to view the details of the `Event`, `Task` or `Member` of their choice. They can use the `select` command again to view items in greater detail.
  - _Justification_: It would be messy if the program simply lists all every `Event` with its respective nested `Task` list and `Member` roster. This feature provides a neat way of viewing the details of the item of their choice.
  - _Highlights_: This feature was challenging to implement as this command is the precursor to many other commands used to modify items. For example, to select a nested `Task` under a particular `Event`, the last selected `Event` had to be stored. If that `Event` is deleted or does not exist, `select` must prompt the user to select an `Event` first, otherwise the wrong `Event` may be returned.
  

- **New Feature**: Added ability to delete any `Event`, `Task` or `Member`, or delete everything via `delete all`, effectively resetting the whole program.
  - _What it does_: Allows the user to delete any item of their choice, while making sure that the requirements are still met (i.e. a `Task` must have at least one `Member` assigned to it).
  - _Highlights_: This feature was the most challenging to implement, especially after the `Member` class was added as there was coupling present between the `Task` and `Member` classes. The main constraint was that a `Task` must have at least one `Member`. This required systematic and detailed checks to ensure that deletion would not only fulfill the constraint, but be fully deleted from the program. This was an insightful experience on how coupling affects the implementation of features.


- **New Feature**: Added ability to find any `Event`.
  - _What it does_: Allows the user to search for Event(s) containing the keyword(s) entered.
  - _Justification_: This feature only is for finding Events as after the respective Events are returned from the search, `select` can be used to view the details of any nested items. For example, after using `find` to return a list of Events, `select` is used to view the details of a particular Event's nested Tasks, and possibly members after that.


- **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=alwinangys&tabRepo=AY2122S1-CS2113T-W12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


- **Project management**: 
  - Collaboratively managed releases `v1.0` - `v2.1` (3 releases) with project group members on GitHub.
  - Set up the GitHub team organisation and repository.
  - Helped to firm up the new role of the `Parser` class from v2.0 to v2.1 with the team via extensive discussions with sequence diagrams to assist in illustration and understanding.


- **Enhancements to existing features**
  - Ensured test for `find` had 100% coverage (PR [#190](https://github.com/AY2122S1-CS2113T-W12-3/tp/pull/190))


- **Documentation**:
  - User Guide:
    - Laid down the overall structure of the user guide in collaboration with [@crabnuggets](https://github.com/crabnuggets)
    - Added documentation for specific commands pertaining to `Event`, `Task` and `Member`. These include `select`, `delete` and `find`.
  - Developer Guide:
    - Added implementation


- **Community**:
  - PRs reviewed with non-trivial comments: [#25](https://github.com/AY2122S1-CS2113T-W12-3/tp/pull/25)