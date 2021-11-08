# Wang Chuhan's Project Portfolio Page

## Project: Code Hunt

### Overview 
Code Hunt is a text-based adventure game using command lines for children aged
4 to 9 who have little prior coding experience but have an interest in coding
to learn basic coding knowledge through playing games and collecting knowledge cards.

### Summary of Contributions
Given below are my contributions to the project.

* New Feature: Add the QuizGame in both easy level and difficult level
  * What it does: Allow users to take quiz as a challenge.
  * Justification: This feature improves the variety of games which improves the user experience.
  * Comments: This feature can help user to learn the basic knowledge of programming directly. When user answers the question wrongly,
  the answer and description will also be shown which can help user to learn knowledge.
  
* New feature: Add the delete command for Card.
  * What it does: Delete an unwanted card in your package by index.
  * Justification: This feature improves the product significantly because a user can choose to delete the cards 
    that have already been used or where the knowledge on is known already.

* New feature: Add help command in Main Menu.
  * What it does: Display a list of available commands that can be used in Main Menu.
  * Comments: It helps user to quickly understand how to start the Code Hunt.

* New feature: Add back command in Card Page.
  * What it does: Back to game menu.
  * Comments: It helps user to back to game menu and continue playing games.

* New feature: Add GameMainMenu.
  * what it does: It loads previous player record or initializes a new player.
  * Justification: It allows user to start a new game or continues previous history, thus relies on to  ````GameMenu.java```` execute command.
  
* New feature: Add StringParser in UI.
  * what it does: Parser to parser user inputs from Ui.
  * Justification: It translates the input of user so that it can be further progressed in GameMenu and CardMenu.

* Code contributed: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Vincent6896&tabRepo=AY2122S1-CS2113-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* Project management:
  * Managed releases v2.0 (one release) on github
  * Maintaining the issue tracker.
  * Necessary general code enhancements.

* Enhancements to existing features:
  * Wrote additional tests for existing features to increase coverage

* Contributions to User Guide:
  * Wrote Features Notes: I added the brief introduction that seeks to inform the user of what 
  our Code Hunt does. In addition, I also added the notes about command format and game levels in Code Hunt.
  * Wrote Command Summary: I added the command summary for Main Page, which is clean and easy for user to understand.

* Contributions to Developer Guide:
  * Added the image in Game component.
  * Added the implementation details of CodeHunt components of the architecture.
  * Added the instruction and implementation details of GameMainMenu
  * Added several user stories.