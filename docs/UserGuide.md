# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

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
