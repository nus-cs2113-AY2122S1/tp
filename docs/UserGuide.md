# User Guide

## Introduction

SEPlanner is a lightweight desktop application for Computer Engineering undergraduates to plan for the Student
Exchange Programme, optimised for use via Command Line Interface (CLI).

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `SEPlanner` from [here](https://github.com/AY2122S1-CS2113T-T09-2/tp/releases/tag/v1.0).
3. Copy the file to the folder you want to use as the home folder for your SEPlanner.
4. Double-click the file to start the app.
5. Type the command in the command box and press Enter to execute it.
6. Refer to the Features below for details for each command.

## Features 

### Viewing all selected universities: `list /suni`
Displays all universities added by the user.
Format: `list /suni`  
Examples:
`list /suni`  
[35] Singapore Management Univeristy  
[69] University of Leeds  
[77] - University of Waterloo

### Viewing all master universities: `list /muni`
Displays all universities from the master list.
Format: `list /muni`  
Examples:
```list /muni
Here are the modules in the list:  
[1] ACC1701X : Accounting for Decision Makers  
[2] ACC2706 : Managerial Accounting  
[3] AR2102 : Design 4  
...  
[803] TR3008 : Technological Innovation  
[804] UD5221 : Urban Design Theory and Disco  
[805] UD5628 : Critique of Contemporary Urban Design  
```

### Viewing all master modules: `list /mmod`
Displays all modules from the master list.  
Format: `list /mmod`  
Examples: 
```list /mmod
Here are the universities and module mappings in the list:  
[1] - Aarhus School of Business  
[2] - Aarhus University  
[3] - Arizona State University  
...  
[78] - Uppsala University  
[79] - Waseda University  
[80] - Western University  
```

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

### Viewing all selected universities: `list /suni`
Displays all universities added by the user.
Format: `list /suni`  
Examples:
```list /suni 
[35] Singapore Management Univeristy  
[69] University of Leeds  
[77] - University of Waterloo  
```

### Viewing all selected modules: `list /smod`
Displays all modules added by the user.  
Format: `list /smod`  
Examples:
```list /smod
Here are the modules in the list:  
[1] CS1010 : Programming Methodology  
[2] CS1231 : Discrete Structures  
[3] CS3233 : Competitive Programming
``` 

### Finding a specific university: `find /uni`
Displays detail of universities that matches the keyword by the user.  
* The output also consists of the index of the university, which is needed for subsequent commands.  
Format: `find /uni KEYWORD`
Examples:  
```
find /uni tsing
[29] - National Tsing Hua University
[54] - Tsinghua University
```

### Finding a specific module: `find /mod`
Displays detail of modules that matches the keyword by the user.
* The output also consists of the index of the module, which is needed for subsequent commands.  
  Format: `find /mod KEYWORD`
  Examples:
```
find /mod CS10
[1] CS1010 : Programming Methodology
[2] CS1010E : Programming Methodology
[3] CS1010J : Programming Methodology
[4] CS1010S : Programming Methodology
```

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

### Removing a selected university: `remove /uni`  
Removes a university from the selected list.
Format: `remove /uni UNIVERSITY_INDEX`  
Examples: 
```
remove /uni 4
The following university has been removed: 
[4] Boston University
```

### Removing a selected module: `remove /mod`  
Removes a module from the selected list.
Format: `remove /mod MODULE_CODE` `remove /mod MODULE_INDEX`
Examples:
```
remove /mod CS1231
The following module has been removed: 
[81] CS1231 : Discrete Structures
```
```
remove /mod 81
The following module has been removed: 
[81] CS1231 : Discrete Structures
```

### Removing a selected module mapping: `remove /map`  
Removes a module mapping under a selected university.
Format: `remove /map UNIVERSITY_INDEX MAPPING_INDEX`  
Examples: 
```
remove /map 4 2
The following module mapping has been removed:
[4] Boston University
CS1231 - CAS CS131 : Combinatoric Structures
```

## FAQ

**Q**: How do I transfer my data to another Computer?  
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that 
contains the data of your previous AddressBook home folder.


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
