# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

### Searching for available module mappings: `searchmap`
Displays all available module mappings for a specific university.
The mappings displayed will be based on the list of selected modules.

Mappings here are labeled with an index, this index will be used for subsequent commands.  
  
Format: searchmap UNIVERSITY_INDEX
  
Examples:
  
* Input: `searchmap 4`

* Output:
  ```
  Potential mappings for Boston University:
  [1] CS1231 - MET CS 248 : Discrete Mathematics
  [2] CS1231 - CAS CS131 : Combinatoric Structures
  ```
### Adding a module mapping: add /map
Adds a specific pair of module mapping under a selected university.

Format: add /map UNIVERSITY_INDEX MODULE_MAPPING_INDEX 

Examples:

* Input: `add /map 4 2`

* Output:
```
The following module mapping has been added under:
[4] Boston University
CS1231 - MET CS 248 : Discrete Mathematics
CS1231 - CAS CS131 : Combinatoric Structures
```

### Viewing all selected universities: `list /suni`
Displays all universities added by the user.
Format: `list /suni`  
Examples:
`list /suni`  
[35] Singapore Management Univeristy  
[69] University of Leeds  
[77] - University of Waterloo  

### Viewing all selected modules: `list /smod`
Displays all modules added by the user.  
Format: `list /smod`  
Examples:
`list /smod`  
Here are the modules in the list:  
[1] CS1010 : Programming Methodology  
[2] CS1231 : Discrete Structures  
[3] CS3233 : Competitive Programming

### Viewing all master universities: `list /muni`
Displays all universities from the master list.
Format: `list /muni`  
Examples:
`list /muni`  
Here are the modules in the list:  
[1] ACC1701X : Accounting for Decision Makers  
[2] ACC2706 : Managerial Accounting  
[3] AR2102 : Design 4  
...  
[803] TR3008 : Technological Innovation  
[804] UD5221 : Urban Design Theory and Disco  
[805] UD5628 : Critique of Contemporary Urban Design  

### Viewing all master modules: `list /mmod`
Displays all modules from the master list.  
Format: `list /mmod`  
Examples: 
`list /mmod`  
Here are the universities and module mappings in the list:  
[1] - Aarhus School of Business  
[2] - Aarhus University  
[3] - Arizona State University  
...  
[78] - Uppsala University  
[79] - Waseda University  
[80] - Western University  

### Adding a partner university: add /uni
Adds a university that the user is interested in into the university list. 
* The argument required here is the index of the university instead of its name.
  This is to prevent too much typing by the user.  
  Format: `add /uni UNIVERSITY_INDEX  `  
  Example:  
  Input    -> `add /uni 47`  
  Output   -> `New university added: [47] - The University of Hong Kong`

### Adding a NUS module: add /mod
Adds a NUS module that the user wants to complete during SEP.
* The argument required can be either the module code, or the module index.  
  Format: add /mod MODULE_CODE add /mod MODULE_INDEX  
  Examples:  
  Input   -> `add /mod CS1010`  
  Output  -> `New module added: [77] CS1010 : Programming Methodology`
  Input   -> `add /mod 77`  
  Output  -> `New module added: [77] CS1010 : Programming Methodology`  

{Give detailed description of each feature}

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I save my selected universities and modules? 

**A**: All your universities, modules and module mappings are automatically saved at 
each command and will be auto-loaded on program start. 

## Command Summary

Command | Format | Purpose
--------|---------|-----------
`add` | `add /uni <UNI_INDEX>` `add /mod <MOD_INDEX>` `add /map <UNI_INDEX> <MAP_INDEX>` | Add a university/module/mapping
`remove` | `remove /uni <UNI_INDEX>` `remove /mod <MOD_INDEX>` `remove /map <UNI_INDEX> <MAP_INDEX>` | Remove a university/module/mapping
`list` | `list /muni` `list /mmod` `list /suni` `list /smod` | List master/selected university list or module list
`searchmap` | `searchmap <UNI_INDEX>`| Search for potential mappings for the selected university
`find` | `find /uni <UNI_NAME>` `find /mod <MOD_CODE>` | Find the possible university or module
`exit` | `exit` | Exit the application
