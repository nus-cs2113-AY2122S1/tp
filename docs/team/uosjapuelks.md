# Anderson Leong Ke Sheng - Project Portfolio Page

## Overview

Fridget is a CLI application that helps users manage their fridge contents better.
It helps automate functions such as sorting and finding of the item list inside the fridge and notifies the users
periodically when any item in the fridge is about to expire. 

## Summary of Contributions

### Code Contributed

[Click this to view Anderson's Reposense Page!](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=uosjapuelks&tabRepo=AY2122S1-CS2113T-W12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&zFR=false)

### Enhancements Implemented

#### New Feature: Added the ability to exit from Fridget.

- What it does: Allows user to close and exit the application with the command line: `exit`
- Justification: This feature is to allow users to safely close the program and ensures that the program works properly.

#### New Feature: Added the ability for items to contain expiry dates.

- What it does: Allows for items to store date of expiry of the items users add into Fridget.
- Justification: This is needed to allow other functions like listing items by date, or find out which items have expired or are nearing expiry.
- Highlights: The implementation includes a system to catch for errors such as if the date has already passed upon addition into Fridget.

#### New Feature: Added the ability to list items in Fridget.

- What it does: Allows users to get Fridget to display the list of items store in Fridget.
- Justification: This is needed to allow users to be able to see what they have in their fridge, which allows them to also know what they have in their fridge.
- Highlights: Originally meant to simply list the items, this feature was further enhanced by including the ability to sort the list by name, or by the expiry date. This was made possible by adding comparators into the `Item` Class which allowed for the comparison between the items. Users can even call 'expiring' to see a list of items that have expired or are expiring.

#### New Feature: Added the ability to swiftly update the quantity of items in Fridget.

- What it does: Allows users to specifically change the quantity of any item in Fridget to a specified amount.
- Justification: This is needed as for some items, checking how much is left of an item may be easier compared to measuring how much is used.
- Highlights: This feature even implements the removal of items if updated quantity is `0`. For a complex feature like this, coding standards like SLAP were closely followed to ensure the code is up to standards.

### Team-based Contributions

- Maintained Issue Tracker.
- Reviewed and commented on PRs.
- Assisted in fixing bugs and applying coding standards into each other's code through refactoring.
- Standardised the use of the word `item` and changing out all use of the word `ingredient` in the repo.
- Simplified system that gets additional info from users when needed to fit coding standard.

### Documentation

#### User Guide

- Helped put in the initial structure such as Quick start, and the basic outline in a Google Doc before transfer into markdown file.
- Maintained the following features: `update`, `list`, `expiring`.
- Assisted in ensuring User Guide has no language mistakes.
- Assisted in ensuring there the Example of usage for each command is consistent with our product.

#### Developer Guide

- ItemList and ShoppingList Class Diagrams and explanations.
- Maintained the following features implementation explanation: Listing Items, Listing Expiring Items, Find Items, Exit Fridget
- Checked for error and potential misleading words in the Developer guide and fixed them. 
- Assisted in the Shutdown section of architecture.

### Beyond the team

- You may view the comments by me (uosjapuelks) to another team [here](https://github.com/nus-cs2113-AY2122S1/tp/pull/26) 