# User Guide
Teaching Assistant's Assistant (TAA) is a Command Line Interface (CLI) application for keeping track of modules and students.
If you can type fast, TAA can help keep track of your modules and students faster than traditional Graphical User Interface (GUI) apps.

* [Quick Start](#quick-start)
* [Features](#features)
  * [Listing all modules: `list_modules`](#listing-all-modules-list_modules)
  * [Adding a module: `add_module`](#adding-a-module-add_module)
  * [Listing all students in a module: `list_students`](#listing-all-students-in-a-module-list_students)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Taa.jar` from [here](https://github.com/AY2122S1-CS2113T-F12-3/tp/releases).
3. Create a new folder and place `Taa.jar` inside.
4. Open Command Prompt/Terminal and navigate to the folder containing `Taa.jar`.
5. Run `java -jar Taa.jar` to start the app.
6. Enter `help` to view a list of available commands. See [Features](#features) for more information.

## Features 

### Listing all modules: `list_modules`
Shows a list of all modules in the module list.

Format: `list_modules`

<br>

### Adding a module: `add_module`
Adds a module to the module list.

Format: `add_module c/<MODULE_CODE> n/<MODULE_NAME>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).
* `MODULE_NAME` refers to the name of the module.

Examples:
* `add_module c/CS2113T n/Software Engineering and Object-oriented Programming`
* `add_module n/Effective Communication for Computing Professionals c/CS2101`

<br>

### Listing all students in a module: `list_students`
Shows a list of all students in a particular module.

Format: `list_students c/<MODULE_CODE>`
* `MODULE_CODE` refers to the code of the module (e.g. CS2113T).

Examples:
* `list_students c/CS2113T`
* `list_students c/CS2101`

<br>

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
