# Jared Cheang - Project Portfolio Page

## Overview
Hi! I'm a Year 2 NUS Computer Engineering student.

Please see below for my contributions to Traveller.

### Summary of Contributions
* World Map
  * Implementation of Dijkstra's algorithm
  * Implementing key functionalities as conceptualised as a graph 
  * Allowing searching for the most optimal costs or time
  * Returning the path of the shortest cost or time
  * Basic exceptions and assertions
  * Creating all major test cases

* Item List
  * Search
  * Edit/Update

#### Code Contributions
* RepoSense Link: [here](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=jach23&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&since=2021-09-25)
  * Refer to this link for details of my code contributions.

#### Enhancements Implemented
* **Features**
  * `shortest-time`: Allow users to find the shortest time from one destination to another.
    * Core part of Traveller, which is to enable users to find the shortest route and the corresponding path breakdown.
  * `shortest-cost`: Allow users to find the least expensive distance from one destination to another.
    * Core part of Traveller, which is to help users to find the cheapest path and the corresponding journey breakdown.
  * `search-item`: Allow users to search using an item keyword.
    * Usually, people plan trips by day. Using the given day and trip, this enables users to search for all items
    * containing the corresponding keyword using Traveller, without having to spend time manually checking each activity
    * one by one.
  * `edit-item`: Allow users to edit existing items.
    * Based on the index of the activity and its corresponding day, the user is then able to update and edit the activity,
    * based on either time or name or both. This enables users to fix accidents like spelling errors and accommodate last
    * minute travel plan impromptu changes, without having to go through the hassle of deleting and adding the corrected
    * event again.
* **Others**
  * Conceptualised the basic architecture for Traveller's World Map Optimiser.
  * Implemented its basic structure as modelled by a graph, such as MinCalcResult.
  * Added exceptions, assertions, logging.
  * Creating major test cases for the World Map functionalities.
  
#### Contributions to UG
* Wrote all key World Map sections and functions. 
* Wrote the sections corresponding to Searching and Editing of Items.

#### Contributions to DG
* Wrote the header paragraph for World Map and its sub-classes.
* Wrote the classes for World Map, Graph List, and Logic.
* UML diagrams done: 
  * [WorldMap sequence diagram I](../documentationPics/worldmap2.1.png),
  * [WorldMap sequence diagram II](../documentationPics/worldmap2.2.png),
  * [GraphList sequence diagram](../documentationPics/graphlist.png),
  * [Logic sequence diagram](../documentationPics/logic.png)

#### Contributions to Team-based tasks
* Reviewed multiple PRs.
* Re-delegated workload regarding bugs on World Map and Item List from V2.0.

#### Review/mentoring contributions
PRs merged: 
* [#158](https://github.com/AY2122S1-CS2113T-W13-1/tp/pull/158),
* [#156](https://github.com/AY2122S1-CS2113T-W13-1/tp/pull/156),
* [#152](https://github.com/AY2122S1-CS2113T-W13-1/tp/pull/152),
* [#104](https://github.com/AY2122S1-CS2113T-W13-1/tp/pull/104),
* [#93](https://github.com/AY2122S1-CS2113T-W13-1/tp/pull/93),
* [#80](https://github.com/AY2122S1-CS2113T-W13-1/tp/pull/80),
* [#76](https://github.com/AY2122S1-CS2113T-W13-1/tp/pull/76),
* [#69](https://github.com/AY2122S1-CS2113T-W13-1/tp/pull/69),
* [#68](https://github.com/AY2122S1-CS2113T-W13-1/tp/pull/68),
* [#36](https://github.com/AY2122S1-CS2113T-W13-1/tp/pull/36).

#### Contributions beyond the project team
* Bugs found during PED.
* Comments given during UG/DG peer review.
