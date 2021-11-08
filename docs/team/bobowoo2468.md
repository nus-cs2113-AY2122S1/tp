# Woo Bo Tuan - Project Portfolio Page

## Overview - TourPlanner

TourPlanner is a data management solution, for tour agencies. The user interacts with it using a CLI, to update a
database with flights, tours, clients, and combine them into packages.

It is written in Java, and has about 7 kLoC.

Given below are the contributions to the project.

* Feature: **ADD** - Added the ability to **add** clients, flights, tours into the database.
    * What it does: Allows the user to add by typing into the CLI input, in the format of:
      `add -[IDENTIFIER] /PREFIX1 DATA1 /PREFIX2 DATA2 ...`

    * Justification: This is the functionality that is core to the program, as it allows the input of data into
      database. As such, many exceptions have to be handled, along with catchers for erroneous inputs.

    * Highlights: The add feature implemented (including parsing of user input) allows for greater flexibility during
      input. The prefixes can be arranged in any order during adding, and additional white spaces in the command will be
      trimmed.

    * Highlights: The exception handling for add is rather extensive. First, erroneous inputs such as missing
      identifier, missing prefixes (hence data fields), duplicated prefixes, missing data fields will all be flagged as
      an error. Erroneous or illogical entries will either be flagged out as a **WARNING** or an **ERROR**, depending on
      its severity. 

* Feature: **SORT** - Added the ability to **sort** clients, flights, tours in the database.
    * What it does: Allows the user to sort, in the format of:
      `sort -[IDENTIFIER] /FILTER`

    * Justification: This is an additional functionality for tour agency planners to be able to perform meaningful
      operations with data. For example, sorting by ascending price can allow tour agency planners to have a
      side-by-side comparison of the budget of tours, from the least expensive to the most expensive tours.

    * Highlights: Sorting is mostly implemented with using the natural sorting of String or Float. Sorting for local date-time requires 
      the need for a defined `Comparator`. The program has allowed for sorting regardless of duplicates in the data.

* Task: General **Parser** methods and code structure
* Task: **Refactor code** to follow OOP guidelines
* Task: **Linking Storage Class** to Main, and ObjectLists

* Code
  contributed: [Reposense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=bobowoo2468&tabRepo=AY2122S1-CS2113T-F11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&reverseAuthorshipOrder=true)

* Project Management:
    * Managed releases for v2.0 on GitHub
    * Created the *developers* team and *team repo*, including settings for GitHub workflow
    * Set-up GHPages for `docs` (containing UG and DG)
    * Managed *issues*, *assigned* work and *tracked* completion for Milestone v2.0
    * Approved and reviewed PRs frequently.

* Documentation:
    * User Guide:
        * Added Table of Contents (TOC) and navigation for UG
        * Added documentation for the features: `add`
        * Added `How-to-use` for UG
        * Added `Supporting Command Information` table for UG

    * Developers Guide:
        * Added implementation details for the `add` feature
        * Added implementation details for the `Parser` feature

* Community:
  * Reviewed and approved PRs frequently
  * Made suggestions for the other teams in the class during tutorial
