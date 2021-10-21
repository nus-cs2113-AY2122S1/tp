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


### Viewing all universities available for SEP: `list /muni`
Displays the master list of CEG SEP partner universities.
Format: `list /muni`

### Viewing all available NUS modules: `list /mmod`
Displays the master list of NUS modules.
Format: `list /suni`

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
_Q_: How do I transfer my data to another Computer?  
_A_: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

## Command Summary

Command | Format | Purpose
--------|---------|-----------
`add` | `add <FLAG> <INDEX>` FLAGS: `/uni` `/mod` `/map` | Add a university/module/mapping
`list` | `list <FLAG>` FLAGS: `/muni` `mmod` `suni` `smod` | List master/selected university list or module list

* Add todo `todo n/TODO_NAME d/DEADLINE`
