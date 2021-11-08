# Paramita Tejasvi - Project Portfolio Page
## Overview
NUS Buddy is a fast, flexible and unobtrusive weekly planner. Designed specifically for NUS students, it includes an
intuitive task and lesson manager, as well as a module planner with CAP calculation features that can be accessed
quickly via a Command Line Interface (CLI), taking up the least of your time, so you can focus on tasks and lessons
that are important to you.

### Summary of Contributions
#### Code contributed: [Reposense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=ptejasv&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)

#### Enhancements Implemented
These are the main functions I worked on during the project:
1. Added functionality to commands in the task and lesson manager of NUSBuddy
   * Implemented the DeleteTaskCommand and DeleteLessonCommand classes' main functionality, which would delete the user 
   specified task or lesson respectively, print out a message to acknowledge this, and then save the data to storage using 
   a function from the Storage class
2. Added functionality to the module information stored by NUSBuddy
    * Made use of the Gson library to parse the module information in NUSMods in json format into a usable array format
   in the JsonUtil class
    * Added the Module, ModuleList and FullModuleList classes, that would then be used to perform functions relating to 
   module management
    * Implemented the FullModuleList class, which would make use of the earlier implemented JsonUtil class to load all
   module information from the NUSMods data, and then convert it into a hashmap for ease of use by other classes and functions
    * Implemented some functions in the ModuleList class in order to add and delete modules in the user's list
    * Added the AddModuleCommand class and its functionality, which would allow the user to add NUSMods modules to their 
   list of modules 

#### Contributions to User Guide
I drafted part of command descriptions in the initial user guide for version 1.0, adding basic instructions to the guide 
that would later be enhanced.
In the final version of the user guide, I added the product description and introduction on how to use the guide, and the 
notes and legends describing how to use the guide. I also added the initial description and format for commands including 
`help`, `delete` and `find`, and added the initial command summary as in the PR [#85](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/85)

#### Contributions to Developer Guide
I added the descriptions and diagrams to the Model component of the developer guide ([#73](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/73))
and ([#79](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/79)).

#### Contributions to Team-Based tasks
* general bugfixes and code enhancements ([#161](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/161)), ([#81](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/81))
* reviewed and approved pull requests
* set up extra gradle dependencies for Gson
* created issues to handle bugs ([#174](https://github.com/AY2122S1-CS2113T-W11-3/tp/issues/174)), ([#82](https://github.com/AY2122S1-CS2113T-W11-3/tp/issues/82))
