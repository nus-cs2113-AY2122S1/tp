# Yip Wayne - Project Portfolio Page

## Overview - TourPlanner

TourPlanner is a data management solution, for tour agencies. The user interacts with it using a CLI, to update a
database with flights, tours, clients, and combine them into packages.

It is written in Java, and has about 7 kLoC.

Given below are my contributions to the project.

* Feature: **LIST** - Added the ability to **list** clients, flights, tours and client in the database.
    * What it does: Allows the user to list a certain data type by typing into the CLI input, in the format of:
      `list [DATA_TYPE]`

    * Justification: This is the functionality that is core to the program, as it allows the travel agency employee to view all the clients, 
      tours, flights and client packages at a quick glance.

    * Highlights: The list feature required the elements of a certain data type to be printed incrementally. Since each data
      type has its own attributes, a custom method to print each type of data had to be used.

* Feature: **FIND** - Added the ability to **find** clients, flights, tours and client in the database.
    * What it does: Allows the user to find a certain data type by typing into the CLI input, in the format of:
      `list [DATA_TYPE] [SUBSTRING]` for clients, and `list [DATA_TYPE] [ID]` for the other tours and flights.

    * Justification: This is the functionality that is core to the program. In the event that there is a lot of data within the database, this 
      function allows the travel agency employee to quickly find the specific entry that they are looking for.

    * Highlights: The find feature was different between the data types. Hence, separate implementations were required.

    * Highlights: For finding tours and flights, it also printed out the relevant clients that subscribed to them. This implementation
      was challenging as required interaction with other object classes, such as the ClientPackage class.

* Task: General implementation of Ui class
* Task: General implementation of Tour and TourList classes
* Task: Implementation of list and find-related Parser exception functions


* Code contributed: : [Reposense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=YipWayne&tabRepo=AY2122S1-CS2113T-F11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&reverseAuthorshipOrder=true)


* Project Management:
    * Created general idea and structure for this project.
    * Created issues and milestones for Milestone v1.0 and some of 2.0
    * Approved and reviewed PRs frequently.


* Documentation:
    * User Guide:
        * Added documentation for the features: `list` amd `find`
        * Added `Quick Start`  and `Introduction to Data Types` 
        * Added `Command summary` table for UG
        * Did clean-up, make sure structure was consistent among members

    * Developers Guide:
        * Added Table of Contents (TOC) and navigation for DG
        * Added `Architecture` section along with relevant UML diagrams
        * Added documentation for the feature `find`, along with relevant UML diagrams
        * Addeed `Getting Started` and `User Stories` section


* Community:
    * Reviewed and approved PRs frequently
    * Made suggestions for the other teams in the class during tutorial
