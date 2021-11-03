### Project: Student Life Affairs Manager (SLAM)

SLAM is a desktop application for student group leaders in NUS to manage their group events, tasks and members. This application allows students to plan and manage the organisation of events by allowing users to add custom events to their schedule. Users can add tasks to complete under each event and keep track of the progress of such tasks and events. These tasks also have group members assigned to them from a user-generated roster of members. The user interacts with this application through a CLI. 

Given below are my contributions to the project.

* **New Feature**: Added the ability for the user to save and load file data.
  * What it does: allow the user to save the current schedule of events and tasks, and roster of members into a `.txt` file in a locally created directory. The user can also load this `.txt` file on program startup to  restore this schedule of events and tasks, and roster of members.
  * Justification: This feature improves the product significantly because a user can resume the program where they have left off without having to retype all the commands to set-up the schedule of events  and tasks, and roster of members. 
  * Highlights:

* **Code contributed**: [RepoSense link]()

* **Project management**: 

* **Documentation**: 
  * User Guide: 
    * Laid down the overall structure of the user guide in colloboration with [@alwinangys](https://github.com/alwinangys)



---

* **New Feature**: Added the ability to undo/redo previous commands.
    * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
    * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
    * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*
  
* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.
  
* **Code contributed**: [RepoSense link]()

* **Project management**:
    * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
    * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `delete` and `find` [\#72]()
        * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
    * Developer Guide:
        * Added implementation details of the `delete` feature.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
    * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
    * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
    * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
    * Integrated a third party library (Natty) to the project ([\#42]())
    * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_