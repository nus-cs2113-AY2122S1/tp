# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

### Searching for available module mappings: searchmap
Displays all available module mappings for a specific university.
The mappings displayed will be based on the list of selected modules.
* Mappings here are labeled with an index, this index will be used for subsequent commands.  
  Format: searchmap UNIVERSITY_INDEX
  Examples:
  ```
  searchmap 4
  Potential mappings for Boston University:
  [1] CS1231 - MET CS 248 : Discrete Mathematics
  [2] CS1231 - CAS CS131 : Combinatoric Structures
  ```
### Adding a module mapping: add /map
Adds a specific pair of module mapping under a selected university.
Format: add /map UNIVERSITY_INDEX MODULE_MAPPING_INDEX  
Examples:
```
add /map 4 2
The following module mapping has been added under:
[4] Boston University
CS1231 - CAS CS131 : Combinatoric Structures
```
## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

Command | Format | Purpose
--------|---------|-----------
`add` | `add /uni <UNI_INDEX>` `add /mod <MOD_INDEX>` `add /map <UNI_INDEX> <MAP_INDEX>` | Add a university/module/mapping
`remove` | `remove /uni <UNI_INDEX>` `remove /mod <MOD_INDEX>` `remove /map <UNI_INDEX> <MAP_INDEX>` | Remove a university/module/mapping
`list` | `list /muni` `list /mmod` `list /suni` `list /smod` | List master/selected university list or module list
`searchmap` | `searchmap <UNI_INDEX>`| Search for potential mappings for the selected university
`find` | `find /uni <UNI_NAME>` `find /mod <MOD_CODE>` | Find the possible university or module
`exit` | `exit` | Exit the application
