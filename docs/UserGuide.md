# User Guide

## Introduction

The Smart Inventory Tracking and Updating System (SITUS) is a **desktop app for tracking
ingredient inventory.** 

_TO INSERT HYPERLINKS HERE_

## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Situs` from [here](https://github.com/AY2122S1-CS2113T-T09-3/tp/releases/tag/v1.0).

## Features 

### [TBA] Viewing help: `help`

Shows a list of available commands and their syntax.

### Adding an ingredient: `add`

Adds an ingredient to the ingredient list. 

Format: `add n/INGREDIENT_NAME a/AMOUNT u/UNITS e/EXPIRY`

Examples:
* `add n/carrot a/200 u/sticks e/22/10/2021`
* `add n/potato a/500 u/g e/25/10/2021`

### Listing all ingredients: `list`

Displays a list of all ingredients in the ingredient list

Format: `list`

### Updating an ingredient: `update`

Updates the amount, unit and expiry of an ingredient in the ingredient list

Format: `update n/INGREDIENT_NAME a/AMOUNT u/UNITS e/EXPIRY`

Examples:
* `update n/carrot a/100 u/sticks e/21/10/2021`
* `update n/potato a/0.6 u/kg e/27/10/2021`

### Stop tracking an ingredient :`delete`

Deletes an ingredient from the ingredient list based on its index in the list.

Format: `delete INDEX`

Example: `delete 1`

### [TBA] Setting thresholds: `set`

_TO BE ADDED_

### [TBA] Show expiring ingredients: `expiry`

Lists the ingredients expiring before a given date.

### Exiting the program: `exit`

Exits the program

Format: `exit`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
