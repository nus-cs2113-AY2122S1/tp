# User Guide

## Introduction

Expiry Eliminator is a desktop app for managing the freshness of ingredients in your kitchen and the recipes you want to cook, optimized for use via a Command Line Interface (CLI). If you can type fast, Expiry Eliminator can help you manage your ingredients and recipes quickly.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Adding an ingredient: `add`

Adds an ingredient to the ingredient repository.

Format: `add i/INGREDIENT [u/UNIT] [q/QUANTITY e/EXPIRY_DATE]

- Unit is optional.
- Quantity and expiry date is optional, but you cannot have one and not the other (i.e. you either don't provide both quantity and expiry date, or you provide both quantity and expiry date).
- Expiry date must be in the format of `yyyy-mm-dd`.
- Quantity must be an integer.
- Note that ingredient names are case insensitive, and will be automatically stored in title case (e.g. `ReD aPplE` will be stored as `Red Apple`).
- Ingredient names must be unique.
- If the ingredient name already exists, an error message will be shown.

Example of usage:
- `add i/Red Apple`
- `add i/Salt u/g`
- `add i/Red Apple q/5 e/2021-10-08`
- `add i/Salt u/g q/1000 e/2021-01-01`

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
