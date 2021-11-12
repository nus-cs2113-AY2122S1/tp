## Alvin Pang (crabnuggets) - Project Portfolio Page

### Project: Student Life Affairs Manager (SLAM)

SLAM is a desktop application for student group leaders in NUS to manage their group events, tasks and members. This application allows students to plan and manage the organisation of events by allowing users to add custom events to their schedule. Users can add tasks to complete under each event and keep track of the progress of such tasks and events. These tasks also have group members assigned to them from a user-generated roster of members. The user interacts with this application through a CLI. 

Given below are my contributions to the project.

* **New Feature**: Added the ability for the user to save and load file data.
  * _What it does:_ allow the user to save the current schedule of events and tasks, and roster of members into a `.txt` file in a locally created directory. The user can also load this `.txt` file on program startup to  restore this schedule of events and tasks, and roster of members.
  * _Justification:_ This feature improves the product significantly because a user can resume the program where they have left off without having to retype all the commands to set up the schedule of events  and tasks, and roster of members. 
  * _Highlights:_ This feature had to go through some makeovers in v2.0 due to the restructuring of interactions between objects in the program (i.e. the nesting of tasks under events and the assignment of members to tasks). It required in-depth though in designing a systematic approach to saving and loading the save data such that the appropriate tasks for each event can be created and the appropriate members from the roster can be assigned to tasks (and vice versa). This, coupled with updating the functionality to be more defensive, was an engaging and stimulating challenge. 

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&zFR=false&tabAuthor=crabnuggets&tabRepo=AY2122S1-CS2113T-W12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&tabType=authorship)

* **Project management**:
  * Collaboratively managed releases `v1.0` - `v2.1` (3 releases) with project group members on GitHub.
  * Contributed base idea/framework of `Parser` class restructuring for `v2.1` release to allow for greater single responsibility principle of classes to be achieved. 

* **Enhancements to existing features**:
  * Ensured that tests written for applicable classes in `storage` package achieved 100% branch coverage (Pull request [#192](https://github.com/AY2122S1-CS2113T-W12-3/tp/pull/192). 

* **Documentation**: 
  * User Guide: 
    * Laid down the overall structure of the user guide in collaboration with [@alwinangys](https://github.com/alwinangys).
    * Added table of contents with hyperlinks and a 'back to top' button for each section of the UG for easy navigation.
    * Added a command summary of the various commands for SLAM for easy reference by the user.
    * Added documentation for the general command features such as `help` and `list`.
    * Added documentation for `select` command.
  * Developer Guide:
    * Added implementation details of the `save` and `load` functionalities.
    * Added user stories.
    * Added instructions for manual testing.

* **Community**:
  * Reviewed DG for PR [#27](https://github.com/AY2122S1-CS2113T-T10-3/tp)
  * PRs reviewed (with non-trivial review comments): [#24](https://github.com/AY2122S1-CS2113T-W12-3/tp/pull/24), [#184](https://github.com/AY2122S1-CS2113T-W12-3/tp/pull/184)
  * Reported bugs for another team in the class ([Team W13-3](https://github.com/AY2122S1-CS2113T-W13-3/tp)
  