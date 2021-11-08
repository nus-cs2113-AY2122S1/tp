# Wu Luoyu's Project Portfolio Page

## Project: The Great Detective

_The Great Detective_ is a desktop CLI application that allows players to enjoy the fun of role-playing and logical reasoning to find out the truth of a murder case. The player gets the chance to investigate in the case by gathering information about the events that lead to the murder and clues about the suspects.

Given below are my contributions to the project.

* **Key Architecture**: Added `Narrative` class
  * What it does: models the storytelling process of each scene of the game, including key methods such as `getNarrative()` and `displayNarrative()`.
  * Justification: Narratives which immerse players in the story are essential for the gameplay.
  
* **Key Architecture**: Added `Clue` class
  * What it does: models a clue object which consists of key attributes `image`, `description` and `isChecked`, and methods such as `setCheck()` and `toString()`.
  * Justification: Clues are fundamentals of the interactive gameplay, providing interesting materials for users to investigate in.
  * Highlights: Each clue is given a succinct name and an ASCII art image to allow enhanced visuals of the game.


* **New Feature**: Added a `View` command
    * What it does: allows the user to review the clues he/she has gathered
    * Justification: This feature improves the product significantly because a user can easily forget the clues they have gathered due to the large number of clues available and a convenient way for him to review it should be provided.
    * Highlights: This enhancement affects existing commands such as investigation. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands and additional helper classes such as `SearchedClueTracker`.


* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=WU-LUOYU-SERENA&tabRepo=AY2122S1-CS2113-T14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


* **Enhancements to existing features**:
    * Updated `View` command so that it takes in names of suspects as arguments, allowing the user to query clues that have been gathered for specific suspects instead of all. (Pull request [\#78](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/78))
    * Added more appropriate output messages in `Ui` when no checked clues are found for `View` command. (Pull request [\#223](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/223))
    * Made the instantiation of `Scene` more OOP by updating the instantiation of `Narrative`. (Pull request [\#86](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/86))
    

* **Project management**:
  * Managed assignments of issues on GitHub.

* **Community**:
  * Set up the GitHub team org and repo
  * Enabled assertion in build.gradle (Pull request [\#62](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/62))
  * PRs reviewed (with non-trivial review comments): [\#7](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/7), [\#27](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/27), [\#125](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/125)
  

* **Documentation**:
  * JavaDoc:
    * Added headers comments to improve code readability. (Pull request [\#218](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/218))
  * User Guide:
    * Added introduction, table of contents, quick start, important notes about the command format and command summary. (Pull requests [\#118](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/118) [\#219](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/219) [\#245](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/245))
    * Added documentation for the features `Changing the number of lines printed` and `view`. (Pull requests [\#103](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/103) [\#118](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/118))
  * Developer Guide:
    * Added acknowledgements and table of contents. (Pull requests [\#129](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/129))
    * Added descriptions of the overall architecture of the application, including the design of an overall architecture diagram and an overall sequence diagram. (Pull requests [\#103](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/103) [\#127](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/127) [\#225](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/225))
    * Added implementation details of the display checked clues feature. (Pull request [\#64](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/64))
    * Added descriptions of the design of the command component, including a class diagram and a sequence diagram for the user input `/next` as an example. (Pull requests [\#94](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/94) [\#222](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/222))
    * Made cosmetic changes. (Pull request [\#236](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/236) [\#258](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/258))
