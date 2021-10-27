# User Guide

## Introduction

Expiry Eliminator (v1.0) is a desktop app for managing the freshness of ingredients in your kitchen and the recipes you want to cook, optimized for use via a Command Line Interface (CLI). If you can type fast, Expiry Eliminator can help you manage your ingredients and recipes quickly.

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

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
