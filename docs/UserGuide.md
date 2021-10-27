# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Adding a todo: `todo`

Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### Adding a recipe: `add recipe`

Adds a recipe with its respective ingredients to the database

Format: `add recipe r/RECIPE i/INGREDIENT q/QUANTITY i/INGREDIENT q/QUANTITY...`

- Adds a recipe and its INGREDIENT with the corresponding QUANTITY for each ingredient. 
  QUANTITY must be a non-negative integer with the corresponding units for ease of reference later on.
- The ingredient and recipe names are case insensitive (E.g.: ChiCkEN SoUP would be recorded as Chicken Soup)
- The recipe is added to the next item on the list
- The ingredients and the quantity can be added in any order, but the first ingredient will correspond to the first
 quantity added, second ingredient to second quantity etc.

Example of usage:
`add recipe r/Chicken Soup i/Chicken q/300g i/Salt q/10g`


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
