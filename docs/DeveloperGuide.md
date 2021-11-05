---
layout: page
title: Developer Guide
---

## Contents 

* [Acknowledgements](#acknowledgements)
* [Getting Started](#getting-started)
* [Design & Implementation](#design--implementation)
  * [Main Components](#main-components) 
  * [Command Implementation](#command-implementation)
  * [User Interface](#user-interface)
  * [Storage](#storage)
  * [University and module related classes](#university-and-module-related-classes)
    * [University](#university)
    * [UniversityList](#universitylist)
    * [Module](#module)
    * [ModuleList](#modulelist)
    * [ModuleMapping](#modulemapping)
  * [Parser Component](#parser-component)
    * [Parser](#parser)
    * [AddCommandParser](#addcommandparser)
    * [RemoveCommandParser](#removecommandparser)
    * [FindCommandParser](#findcommandparser)
    * [SearchMapCommandParser](#searchmapcommandparser)
    * [HelpCommandParser](#helpcommandparser)
    * [ExitCommandParser](#exitcommandparser)
* [Product Scope](#product-scope)
  * [Target User Profile](#target-user-profile)
  * [Value Proposition](#value-proposition)
* [User Stories](#user-stories)
* [Non-Functional Requirements](#non-functional-requirements)
* [Glossary](#glossary)
* [Instructions for manual testing](#instructions-for-manual-testing)

## Acknowledgements

* __EduRec:__ For the list of possible module mappings
* __AB3:__ For the format of user guide and developer guide

## Getting started 

Refer to our user guide [here](https://ay2122s1-cs2113t-t09-2.github.io/tp/UserGuide.html#quick-start)

## Design & implementation

<img src="images/architecture.png" width = "280"/>  

The ***Architecture Diagram*** above explains the high-level design of the App. 

### Main Components

The main class of SEPlanner is the `Duke` class. It is responsible for initializing the other components when the program 
gets started while handling interactions between the other components.  
The other core components of SEPlanner are:  
`Ui` : Handles the UI of SEPlanner and prints outputs to the user.  
`Parser`: Handles user inputs and pass them down to the `Command` class.  
`Command` : Handles output passed down from `Parser` based on the user inputs and execute user instructions.  
`Storage` : Loads data from, and stores data back to the user's local machine.  
`UniversityList` and `ModuleList` : Stores information about Universities and modules as well as the methods to amend and filter them.

### Command Interaction 

<p align = "center">
<img src="images/mainseq.png" width = "800" />
</p>

The sequence diagram above illustrates the flow through our program structure when the user input `add /uni 1` 
is entered.  

When the user runs the `add /uni 1` action on the parser class, the parser class calls the `addUniCommand` method 
in the `addUniCommand` class by passing the university with master index `1` to it.  

The `addUniCommand` class then  
1. calls the `selectedUniversityList#addUniversity` method in the model class 
by passing the university to implement the action.
2. calls the `updateSelectedUniversity` method in the storage class 
by passing the selected university list to update the stored selected list
3. calls `printUniversity` in the UI class.
by passing the university to display the message of adding the selected university to the user.
 
### Command Implementation

<p align = "center">
<img src="images/commands.png" width = "800" />
</p>


Step 1. When the beginner user launches the application, to see the list of all universities available for the SEP program, the user executes the `list /muni` command. To see the list of all NUS modules available for the SEP program, the user can execute the `list /mmod` command.

Step 2. The user then executes the `add /uni Boston University` command and add his preferred university to the selected university list. The other option for the user is to execute the `add /uni 4` command by entering its university master index.

Step 3. The user executes the `add /mod CS1231` command to add his preferred NUS module to the selected module list. The other option for the user is to execute the `add /mod 81` command by entering its module master index.

Step 4. The user executes `list /suni` to see his selected university list. The user executes `list /smod` command to see his selected module list.

Step 5. The user executes the `remove /mod CS1231` or `remove /mod 81` command to delete the unwanted module from see his selected module list. The user executes the `remove /uni Boston University` or `remove /uni 4` command to delete the unwanted university from his selected university list.

Step 6. If the user forgets the master index for a university of a module, he can execute the `find /mod Discrete Structures` or `find /code CS1231` to find the module. The user executes `find /uni Boston University` to find the university.

Step 7. The user executes `searchmap 4` command to see the module mapping of all modules in the selected module list in Boston University. The user can execute `searchmap all` to see all modules in the selected module list for all universities in the selected university list.

Step 8. The user executes `add /map 4 1` command to add the first module mapping available under Boston University.

Step 9. The user executes `remove /map 4 1` command to remove the first mapping allocated to Boston University.

Step 10. The user executes `help` command to see the list of commands available for SEPlanner.

Step 11. The user executes `exit` command to terminate the program.

### User Interface

The Ui component consolidates and formats the output of the program before displaying it to the user
in the command line. 

<p align = "center">
<img src="images/ui.png" width = "800" />
</p>

The above class diagram illustrates the relationship between the classes within the Ui components.

The Ui class is the parent of every other class in the package.
* Contains helper methods for the other Ui classes.
* Provides means of printing constants.

The UiInvalid class contains methods for printing messages from the Parser component.

The UiMapping class contains methods for printing Module Mappings. 

The UiModule class contains methods for printing Modules. 

The UiUniversity class contains methods for printing Universities. 

The UiWelcome class contains a method for printing the welcome greeting. 

The UiInvalid class contains methods to display error messages to the user. 

The UiStorage class contains methods to display error messages from the Storage component to the user. 

<p align = "center">
<img src="images/uiseq.png" width = "800" />
</p>

The sequence diagram above illustrates how the classes in the Ui package interact when a printUniversity() call is made from outside the package.
In the printUniversity Method,
1. printIndex is called from the Ui class.
2. Within printIndex, we display the index, then do a self invocation on stringPadder within the Ui class to pad the string to line up the text after. 
3. After printing the index, print the university name, then depending on the boolean printMC, we pad it again with stringPadder before displaying the Module Credits. 



### Storage

#### API : [`Storage.java`](https://github.com/AY2122S1-CS2113T-T09-2/tp/blob/master/src/main/java/seedu/duke/storage/Storage.java)

<p align = "center">
<img src="images/storage.png" width = "800" />
</p>

The storage component can implement the below features:

* Read the list of module mappings offered by each university from the CSV file.
* Read the list of NUS modules which can be mapped from the CSV file.
* Save both user's module mappings for each university and their selected NUS modules in text
  file and read them back into corresponding objects.

The purpose of each class in the storage component, except the `Storage` class, is to handle a specific file. The `Storage` 
class is used to link all the other classes. This is done by creating an object of other classes, to access all the necessary
methods required.

The classes `SelectedUniversityStorage` and `SelectedModuleStorage` are responsible for reading and updating the text files
storing your selected university list and your selected module list. These classes inherit from the `UserStorage` class as the `loadFile` function
is identical other than the file path. The private methods in both the classes filters out the invalid data found while reading the 
text files.

The classes `UniversityStorage` and `ModuleStorage` are responsible for extracting the Master University List and Master Module List
from the CSV type files (`University.csv` and `modules.csv`) stored in the resources root.

### University and module related classes

The following diagrams are the class diagrams of the classes:  

<img src="images/University.png" width = "280"/>  
<img src="images/Module.png" width = "280"/>  

This component consist of the following classes: 

#### University 

* Stores basic information of a university.
* Stores the unique index for each university in `index`, this index can be used to refer to a specific university in the commands.
* Stores the university name in `name`.
* Stores `ModuleMapping` objects associated to this university in an array list `list`.
* For `University` objects under the master list, `list` stores all possible module mappings from this university for the user to choose from.
* For `University` objects under the selected list by the user, `list` stores all the module mappings the user has chosen to add to this university for their SEP application.

#### UniversityList

* Stores `University` objects.
* Can be used under the following 2 circumstances:
  * Stores the master list containing all universities available for CEG SEP application. This list is directly read from an external file `University.csv`.
  * Stores the selected list containing all universities added by the user. This list can be amended by the user, and all change to it will be stored under `data/selectedUniversities.txt`.
* Contains methods to search, filter, or amend the list based on command from the user. 

#### Module 

* Stores basic information of a module.
* For NUS modules, stores the unique index for each module in `index`, this index can be used to refer to a specific module in the commands.
* Stores the module code in `moduleCode`.
* Stores the module name in `moduleName`.
* Stores the modular credits of this module in `moduleCredits`.

#### ModuleList

* Stores `Module` objects.
* Can be used under the following 2 circumstances:
  * Stores the master list of all NUS modules available for the users to choose from.
  * Stores the selected list unique to each user based on the modules they choose to take.

#### ModuleMapping 

* Stores 2 `Module` objects as a pair of module mapping.
* Stores the local NUS module in `localModule`.
* Stores the partner university module in `mappedModule`.

### Parser Component

The parser component is made up of the following classes:
* Identify the command word and invoke the respective argument parser for the command.
* Handle the arguments and return the respective Command object.
* Ensures that the command, flags and arguments are entered in the correct format.  

#### Parser

* This is the main parser class that will handle raw inputs and identify command words and invoke the respective command parsers instance and return the respective `Command` object.

#### ParseCondition

* This class contains methods used by various parser classes to verify that the inputs are valid.

#### AddCommandParser

* This object when invoked, will first identify the flag `/uni`, `/mod` or `/map`. Once the flag is identified, the arguments corresponding to the flags will be extracted.
* For `/uni` the argument should either be an integer representing `<UNI_INDEX>` or a String representing `<UNI_NAME>`.
A new `University` object will be created and passed as an argument to the constructor for `AddUniCommand`.
* For `/mod` the argument should either be an integer representing `<MOD_INDEX>` or a String representing `<MODULE_CODE>`.
A `Module` object will be duplicated from the Master Module List and passed as an argument to the constructor for `AddModCommand`.
* Both the `/uni` and `/mod` flag will be checking for the type of inputs passes in and handle them accordingly.
* For `/map` the argument should be two integers representing `<UNI_INDEX>` and `<MAPPING_INDEX>` and will be passed into the constructor for `AddMapCommand`.

#### RemoveCommandParser

* This object when invoked, will first identify the flag `/uni`, `/mod` or `/map`. Once the flag is identified, the arguments corresponding to the flags will be extracted.
* For `/uni` the argument should either be an integer representing `<UNI_INDEX>` or a String representing `<UNI_NAME>`.
The `University` object representing the particular university from the Selected University List will be passed as an argument to the constructor for `RemoveUniCommand`.
* For `/mod` the argument should either be an integer representing `<MOD_INDEX>` or a String representing `<MODULE_CODE>`.
The `Module` object representing the particular module from Selected Module List and passed as an argument to the constructor for `RemoveModCommand`.
* Both the `/uni` and `/mod` flag will be checking for the type of inputs passes in and handle them accordingly.
* For `/map` the argument should be two integers representing `<UNI_INDEX>` and `<MAPPING_INDEX>` and will be passed into the constructor for `RemoveMapCommand`.

#### FindCommandParser

* This object when invoked, will first identify the flag `/uni`, `/code` or `/mod`. Once the flag is identified, a String representing `<KEYWORD>` is extracted and passed as an argument to the constructor for `FindUniCommand` or `FindModCommand`.

#### SearchMapCommandParser

* This object when invoked, will take the first argument and convert it into an integer representing `<UNI_INDEX>` and pass it to the constructor for `SearchMapCommand`.

#### HelpCommandParser

This object will return an instance of `HelpCommand`.

#### ExitCommandParser

This object will return an instance of `ExitCommand`.

#### ParserClassException

* This is an abstract class inherited from Java 11's `ParseException` and its children objects are thrown when an input is detected as invalid. 
* Each command word has its respective `ParserClassException` with its format inside.
* On top of the parameters for `ParseException`, and additional boolean variable is required to identify when the user has made a format error in the command and the correct format will be output to the user.

## Product scope

### Target User Profile

__SEPlanner__ is targeted at Computer Engineering students in the National University of Singapore planning for their Student Exchange Program (SEP). 

### Value Proposition

Student Exchange Program is one of the most stressful and difficult things to plan for in a NUS students life. 
With multiple sources of information and a frustrating webpage to navigate, SEPlanner aims to organise 
a list of potential exchange Universities based on the users study plan, module requirements and personal preference.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see the list of possible schools for exchange|view my options for SEP|
|v1.0|new user|see the list of available NUS modules|decide on which NUS modules I want to complete during SEP|
|v1.0|beginner user|add a University to my preferred list|view the Universities that I am interested in|
|v1.0|beginner user|add a NUS Module to my preferred list|save my module preferences for the future|
|v1.0|beginner user|list down all Universities in my preferred list|keep track of my target schools for SEP|
|v1.0|beginner user|list down all NUS modules in my preferred list|keep track of the list of NUS modules I want to complete during SEP|
|v1.0|beginner user|delete a University from the selected list|remove the University that I am not interested in|
|v1.0|beginner user|delete a module from the selected list|remove the module that I do not consider to enrol in the future|
|v1.0|user|enter commands and arguments to the application|interact with the application on the command line in an efficient way
|v2.0|familiar user|save my university and module information|maintain access to my information when I restart the application|
|v2.0|new user|view the program instructions|refer to them when I forget how to use the application|
|v2.0|familiar user|find a University by name|locate a University without having to go through the entire list|
|v2.0|familiar user|search the available module mappings for a University based on the selected module list|get a list of module mappings for this university based on my selected modules|
|v2.0|familiar user|add a pair of module mapping for a university|save a module mapping under the university that I selected|
|v2.0|familiar user|delete a mapping pair of module for a university|remove a module mapping under a selected university|
|v2.0|familiar user|pass in University as command argument using its index in the master list|access the exact University I want without having to type out its full name and facing bugs caused by typo|

## Non-Functional Requirements

* SEPlanner must operate with full functionality on all mainstream operating systems: Windows, MacOS and Ubuntu with Java 11 installed. 
* It should offer a streamlined experience using the command line interface primarily. 
* It should be fast and responsive (No more than 1000ms between user input and program output).
* It should be significantly faster than the default Student Exchange Program application portal on myEduRec. 

## Glossary

* *Master University List* The list of all partner universities.
* *Master Module List* - The list of all NUS modules available for mapping.
* *Selected University List* - The list of partner universities the user selected along with the user's selected module mappings
                               for each university
* *Selected Module List* - The list of NUS modules the user selected.
* *CSV* - Comma-separated Values
* *SEP* - Student Exchange Programme
* *NUS* - National University of Singapore

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
