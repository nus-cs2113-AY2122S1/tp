## Antriksh Verma (antrikshv) - Project Portfolio Page

### Project: Student Life Affairs Manager (SLAM)
SLAM is a desktop application for student group leaders in NUS to manage their group events, tasks and members. This application allows students to plan and manage the organisation of events by allowing users to add custom events to their schedule. Users can add tasks to complete under each event and keep track of the progress of such tasks and events. These tasks also have group members assigned to them from a user-generated roster of members. The user interacts with this application through a CLI. 

Given below are my contributions to the project.

* **New Feature**: Added the ability to list `events` `tasks` of specific events, `members` assigned to a specific task, and the `memberRoster`.
    * _What it does:_ allows the user to view the Overall schedule of events and tasks, members assigned to a task and roster of members.
    * _Justification:_ This feature improves the product significantly because a user can view the different items added to their schedule, and they can see the allocation of the members, so they can better plan and manage their various events
    * _Highlights:_ This feature had to go through some makeovers in v2.0 due to the restructuring of interactions between objects in the program and the addition of members.


* **New Feature**: Added the ability to view the `next` `event` or `task` with the use of a calendar interface.
    * _What it does:_ allows the user to view the next upcoming event or task of an event.
    * _Justification:_ This feature allows the user to easily view what is the most pressing issue thus making the product a lot better and usable for the user
    * _Highlights:_ This feature was initially the simplest to implement, as it just showed the next upcoming event. The usage of a calendar to display the next upcoming event, proved to be  very challenging feature to add. Alongside upcoming tasks, these two things made this feature a fun challenge to implement.


* **New Feature**: Added the ability to list `update` the details of events and tasks.
    * _What it does:_ allows the user to update different details regarding events and tasks, so that the user need not delete and add a completely new task when the user wants to change something minor.
    * _Justification:_ This feature improves the product significantly because a user can much more easily update various different details of events and tasks, which would allow the user to use the product, with more ease and much more efficiency then specifically adding or deleting details when changes are required
    * _Highlights:_ This feature was the most complicated to implement, as it contained logic similr to other parts of the product from adding to removing stuff. During the restructing of the different items the update feature got much more complex due to the nesting of the updates of the details, as members are assigned to tasks which are assigned to events. Due to the nature of this feataure, there were alot of bugs and alot of exception to account which is another reason it was the most difficult feature to implement



* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&zFR=false&tabAuthor=antrikshv&tabRepo=AY2122S1-CS2113T-W12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&tabType=authorship)


* **Project management**:
    * Collaboratively managed releases `v1.0` - `v2.1` (3 releases) with project group members on GitHub.
    * Contributed the base OOP implementation and idea for the entire project.
    * Laid down the skeleton template for the project, with the implementing simple features, `help` `bye`


* **Enhancements to existing features**:
    * Ensured that tests written for applicable classes in `ListCommand` and `NextCommand` achieved 100% coverage (Pull request [#184](https://github.com/AY2122S1-CS2113T-W12-3/tp/pull/184).


* **Documentation**:
    * User Guide:
        * Added documentation for `next`, `list` and `Update` command.
    * Developer Guide:
        * Added implementation details of the `save` and `load` functionalities.
        * Added table of contents with hyperlinks for each section of the UG for easy navigation.
        * Added the Design section, Intro, Product Scope, Non-Functional Requirements and Glossary.
        * Added the style.uml file to allow for cohesion and similar theme across the different diagrams within the UG.
        * Added the implementation details of the usage for `next`, `list` and `update` functionalities.

* **Community**:
    * Reviewed DG for PR with non trivial comments [#1](https://github.com/nus-cs2113-AY2122S1/tp/pull/1)