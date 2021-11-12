# Bryan Elmer - Project Portfolio Page

## Overview

I was involved in a greenfield project called Fridget. Fridget is a Java-written, CLI-based software that helps users manage all the items in their fridge. Eliminating the need to do things manually, Fridget helps automate processes such as adding, listing, and finding items in the fridge.

## Summary of Contributions

### Code Contributed: [Reposense](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=BryanElmer&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&zFR=false&tabAuthor=BryanElmer&tabRepo=AY2122S1-CS2113T-W12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&tabType=authorship&until=2021-11-08)

### Enhancements Implemented

#### New Feature: Added the ability to save all items in Fridget.

- What it does: Allows Fridget to remember all the items in the item list and shopping list before exiting the program.
- Justification: This feature is necessary to ensure users do not lose their item list and shopping lists upon re-entering the program.
- Highlights: The data is stored in separate texts files which allow manual editing for more advanced users. This allows users to transfer data from one program instance to another by replacing the data files.

<hr/>

#### New Feature: Added Shopping List in Fridget.

- What it does: Allow users to keep track of items that they want to replenish once they run out.
- Justification: This feature ensures that users will meet their essential needs when necessary without user involvement.
- Highlights:
    - Fridget automatically removes items from the shopping list when they are added to the item list.
    - Fridget will prompt the users to add items into the shopping list only if they run out.
    - Users cannot add and remove items in the shopping list freely as it is done automatically by Fridget.

<hr/>

#### New Feature: Added the ability to reset item list in Fridget.

- What it does: Allow users to clear and start with a new and fresh item list by typing in `reset`.
- Justification: This feature is needed so that users can start with a fresh item list after trying Fridget out for the first time with dummy test data.

<hr/>

#### New Feature: Added the ability to reset shopping list in Fridget.

- What it does: Allow users to clear and start with a new and fresh shopping list by typing in `shopreset`.
- Justification: This feature is needed so that users can start with a fresh shopping list after trying Fridget out for the first time with dummy test data.

<hr/>

#### New Feature: Added the ability to list items in the shopping list.

- What it does: Allow users to get Fridget to display the list of items stored in the shopping list by typing in `shoplist`.
- Justification: This feature is needed to allow users to be able to see what they have in their shopping list.
- Highlights: Items are listed in alphabetical order.

<hr/>

#### Enhance Feature: Updated add command to update quantity of item if added item already exists in item list.

- What it does: Increase the quantity of an existing item if they have the same name and expiry date as added item.
- Justification: This feature avoids the same items to be added in multiple entry lines.
- Highlights: Updated codes in ItemList and Ui.

<hr/>

#### Enhance Feature: Updated remove command to allow removing multiple quantities at once.

- What it does: Allow users to remove multiple quantities of the same item.
- Justification: This feature provides an efficient way of removing multiple quantities at once.
- Highlights: Update codes in RemoveCommand, ItemList, and Ui.

### Team-based/Mentoring Contributions:

- Implementing several key features in Fridget.
- Assisting in code review by commenting and approving pull requests to ensure high-quality implementation and coding standards, such as SLAP, are utilized.
- Contributing to project management by updating issues in the issue tracker.
- Searching and fixing potential bugs.

### Documentation Contributions:
- User Guide:
    - Added documentation for features `reset`, `shopreset`, `shoplist`.
    - Added documentation for shopping list in `remove` and things to note.
- Developer Guide:
    - Added documentation for features `remove`, `reset`, `shopreset`, `shoplist`.
    - Added sequence diagrams for `remove`, `reset`, `shopreset`, `shoplist`.
    - Added class diagram for storage.
  