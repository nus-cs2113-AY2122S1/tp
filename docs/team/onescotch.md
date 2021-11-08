# Wang Ruisi's Project Portfolio Page

        ## Project: Code Hunt

        ### Overview
        Code Hunt is a text-based adventure game using command lines for children aged
        4 to 9 who have little prior coding experience but have an interest in coding
        to learn basic coding knowledge through playing games and collecting knowledge cards.

        ### Summary of Contributions
        Given below are my contributions to the project.

        * New Feature: Add the guessNumber in both easy level
        * What it does: Allow users to take guess number as a challenge.
        * Justification: This feature improves the variety of games which improves the user experience.
        * Comments: This feature can help user to learn the basic knowledge of programming directly. When user guesses
        the number wrongly, user can choose whether try again or continue to play other games.

        * New feature: Add the list command for Card.
        * What it does: List all the cards you gained for different game levels, show card information including the index of the card and if the card is already used.
        * Justification: This feature improves the product significantly because a user can choose to view all the cards that he has achieved during the challenge.

        * New feature: Add help command in Card Page.
        * What it does: Display a list of available commands.
        * Comments: It helps user to quickly understand how to check the information in Card.

        * New feature: Add the check command in Intermediate page:
        * What it does: Check current game progress.

        * New feature: Add GameMenu.
        * what it does: Chooses difficulty level of games then triggers ``EasyMenu.java`` or ``DifficultMenu``.
        * Justification: It allows user to start a new game or continues previous history, thus relies on to  ````GameMenu.java```` execute command.
        * Comments: It checks game progress, thus depends on some classes in the Player component and keeps the reference to CarMenu to check and edit holding cards.

        * New feature: Add Errors in UI.
        * what it does: Show different error information.
        * Justification: It recognises the invaild user's inputs and notifies users.

        * Code contributed: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=oneScotch&tabRepo=AY2122S1-CS2113-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

        * Project management:
        * Setting up the GitHub team org/repo
        * Maintaining the issue tracker.
        * Create demo video

        * Enhancements to existing features:
        * Wrote additional tests for existing features to increase coverage

        * Contributions to User Guide:
        * Wrote Main page: I added the brief introduction that seeks to inform the user the commands can be input in MainPage.
        In addition, I also added instruction of several commands.
        * Wrote Command Summary: I added some columns in the command summary for Intermediate Page, which is clean and easy for user to understand.

        * Contributions to Developer Guide:
        * Added the image in Player component.
        * Added the implementation details of Check Game History.
        * Added the instruction and implementation details in UI component. (EasyMenu and GameMainMenu)
        * Added several user stories.
