# Zhang Haoyu - Project Portfolio Page
## Overview
CLIverShelf is a desktop application used for inventory management and quick data analysis tool for small businesses
such as small bookstore and convenient stores. The user interacts with it using a Command-Line Interface.
### Summary of Contributions
Given below are my contributions to the project.
* **Code
  contributed:** [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=haoyusimon&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other)
* **Enhancements implemented:**
    * **New Feature**: Create or delete a shelf (pull request: [#100](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/100))
        * What it does: Allows the user to create or delete a shelf.
        * Justification: This feature is the most basic function of the application.
    * **New Features**: Add/Edit/Delete items (pull
      request: [#27](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/27)
      , [#85](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/85))
        * What it does: Allow the user to add/edit/delete items in a shelf.
        * Justification: There are three important basic functions for the user to manage items.
        * Highlights: After adding new items, IDs will be automatically generated for each item. This helps the user to label their items. Later when the user needs to sell the item, he can sell it using the item ID conveniently without having to check which item it is.
    * **New Feature**: Sell an item (pull request: [#191](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/191))
        * What it does: Allow the user to use the item ID to sell the item.
        * Justification: Sell an item is a basic and important function for the user to manage the items.
        * Highlights: Selling an item using its ID is very convenient and intuitive. Moreover, additional checks have
          been added to prevent the user from selling non-existent items.
    * **New Feature**: Get the report of SoldItems (pull
      request: [#85](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/85)
      , [#201](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/201))
        * What it does: Allow the user view reports of soldItems (both details and overall statistics).
        * Justification: It helps the user to analyse the sales trend and help to adjust his business strategies.
        * Highlights: The format of the report has been specifically designed: the details of soldItems are shown in a
          table for clarity.
        * Credits: The ReportCommand's functionality relies on the helper methods in `SalesManager` and `SalesReport`
          written by Li Xin.
* **Quality Assurance:**
    * Added JUnit tests for `AddCommand`, `CreateShelfCommand`, `DeleteCommand`, `EditCommand`, `RemoveShelfCommand`
      , `SellCommand`, `SalesManager`, and `SalesReport`. The test coverage (methods, lines and branch) for most of
      these classes are above 80%.
    * Tested the code and reported bugs in GitHub
      issues:([#189](https://github.com/AY2122S1-CS2113T-F11-4/tp/issues/189)
      , [#202](https://github.com/AY2122S1-CS2113T-F11-4/tp/issues/202))
    * Found a hidden bug in `SalesManager` after hours of debugging using JUnit
      test ([#242](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/242))
* **Contributions to the UG:**
    * Adding instructions and expected outputs to: [Add new items](https://ay2122s1-cs2113t-f11-4.github.io/tp/UserGuide.html#add-new-items).
    * Adding expected outputs to: [Update an item](https://ay2122s1-cs2113t-f11-4.github.io/tp/UserGuide.html#edit-an-item), [Delete item from list](https://ay2122s1-cs2113t-f11-4.github.io/tp/UserGuide.html#delete-an-item).
    * Add new instructions to: [Usage](https://ay2122s1-cs2113t-f11-4.github.io/tp/UserGuide.html#usage).
    * Reviewed the parts written by other teammates.
* **Contributions to the DG:**
    * Wrote the description and created corresponding sequence diagrams for: [Command](https://ay2122s1-cs2113t-f11-4.github.io/tp/DeveloperGuide.html#logic-subcomponent-command), [Adding an item](https://ay2122s1-cs2113t-f11-4.github.io/tp/DeveloperGuide.html#adding-an-item), [Editing an item](https://ay2122s1-cs2113t-f11-4.github.io/tp/DeveloperGuide.html#editing-an-item).
    * Created sequence diagrams for: [Selling an item](https://ay2122s1-cs2113t-f11-4.github.io/tp/DeveloperGuide.html#selling-an-item), [Generating sales report](https://ay2122s1-cs2113t-f11-4.github.io/tp/DeveloperGuide.html#generating-sales-report).
    * Improved on the Introduction section, adding Aim, Target Audience and How to Use This Developer Guide sub-section: [Introduction](https://ay2122s1-cs2113t-f11-4.github.io/tp/DeveloperGuide.html#introduction) 
    * Adding the test cases, command and expected result of Creating a shelf test, Removing a shelf test, Adding an item
      test, Deleting an item test and Editing an item test: [Instructions for manual testing](https://ay2122s1-cs2113t-f11-4.github.io/tp/DeveloperGuide.html#instructions-for-manual-testing)
    * Reviewed the parts written by other teammates.
* **Contributions to team-based task:**
    * Fixed bugs from PE-D
    * Updated developer documentations that are not specific to a feature: Target user profile, Aim and How to use this
      guide.
* **Review/mentoring contributions:**
    * Actively review teammates' pull requests and give useful
      suggestions ([#24](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/24)
      , [#43](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/43)
      , [#60](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/60)
      , [#79](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/79)
      , [#89](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/89)
      , [#112](https://github.com/AY2122S1-CS2113T-F11-4/tp/pull/112))
* **Contributions beyond the project team:**
    * Reviewed the Developer Guide of another team: [Team T09-3](https://github.com/nus-cs2113-AY2122S1/tp/pull/25)
    * Found bugs from another team's application during PE-D: [PED link](https://github.com/haoyusimon/ped/issues)
    