# User Guide

## Introduction

The Smart Inventory Tracking and Updating System (SITUS) is a **desktop app for tracking
ingredient inventory.**

### How to use this guide
#### Format notes
* Words/phrases in this format: `example`, are command snippets
* Words/phrases within square brackets `[square bracket]` are user's inputs. <br>
  For example in `delete [INGREDIENT_NUMBER]`, `INGREDIENT_NUMBER` is the user's input,
  such as `delete 1`.

## Contents

[**1. Quick Start**](#1-quick-start) <br>
[**2. Features**](#2-features) <br>
&nbsp;&nbsp;[2.1. View Help](#21-view-help) <br>
&nbsp;&nbsp;[2.2. Add Ingredients](#22-add-an-ingredient) <br>
&nbsp;&nbsp;[2.3. List Ingredients](#23-list-all-ingredients) <br>
&nbsp;&nbsp;[2.4 Update Ingredients](#24-update-an-ingredient) <br>
&nbsp;&nbsp;[2.5. Delete Ingredients](#25-delete-an-ingredient) <br>
&nbsp;&nbsp;[2.7. Show Expiring Ingredients](#27-show-expiring-ingredients) <br>
&nbsp;&nbsp;[2.8. Show Alerts](#28-show-alerts) <br>
&nbsp;&nbsp;[2.9. Set Thresholds](#29-set-thresholds) <br>
&nbsp;&nbsp;[2.10. Search For Ingredients](#210-search-for-ingredients) <br>
&nbsp;&nbsp;[2.11. View/ Set Current Date](#211-view-set-current-date) <br>
&nbsp;&nbsp;[2.12. Exit Program](#212-exit-program) <br>
[**3. FAQ**](#3-faq) <br>
[**4.Command Summary**](#command-summary) <br>

## 1. Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Situs` from [here](https://github.com/AY2122S1-CS2113T-T09-3/tp/releases/tag/v1.0).
3. Copy the jar file to the folder that you want to use SITUS.
4. Open a terminal and navigate to the folder containing the jar file.
5. Type `java -jar Situs.jar` in the terminal window.
6. If successful, you will see the following message:

   ![Welcome message](images/welcome.png)

   *Figure 1: welcome message in terminal*
   
## 2. Features

This section covers the commands SITUS can execute, and how to use them.

### 2.1. View Help

Shows a list of available commands and their syntax.

Format: `help`

### 2.2. Add An Ingredient

Add an ingredient to the ingredient list.

Format: `add n/[INGREDIENT_NAME] a/[AMOUNT] u/[UNITS] e/[EXPIRY]`

The parameters used in the command are:
* `INGREDIENT_NAME`: name of the ingredient.
* `AMOUNT`: amount of the ingredient.
* `UNITS`: units specified to associate with `AMOUNT`.
* `EXPIRY`: expiration date of ingredient, in format of [TBA]

Examples:
* `add n/carrot a/200 u/sticks e/22/10/2021`
* `add n/potato a/500 u/g e/25/10/2021`

### 2.3. List All Ingredients

Display a list of all ingredients in the ingredient list

Format: `list`

### 2.4. Update An Ingredient

Update the amount, unit and expiry of an ingredient in the ingredient list

Format: `update n/[INGREDIENT_NAME] a/[AMOUNT] u/[UNITS] e/[EXPIRY]`

The parameters used in the command are:
* `INGREDIENT_NAME`: name of the ingredient.
* `AMOUNT`: updated amount of the ingredient.
* `UNITS`: updated unit of the ingredient.
* `EXPIRY`: updated expiration date of the ingredient.

Examples:
* `update n/carrot a/100 u/sticks e/21/10/2021`
* `update n/potato a/0.6 u/kg e/27/10/2021`

### 2.5. Delete An Ingredient

Delete an ingredient from the ingredient list based on its index in the list.

Format: `delete [INGREDIENT_NUMBER]`

The parameter used in the command is:
* `INGREDIENT_NUMBER`: the ingredient number to remove

Example: `delete 1`

### 2.7. Show Expiring Ingredients

List the ingredients that will expire by a specified date.

Format: `expire [DATE]`

The parameter used in the command is:
* `DATE`: the date of interest

Example: `expire 12/11/2021`

### 2.8. Show Alerts 

List ingredients that are expiring within a certain period or are running low on stock, or both.

Format: `alert [ALERT_TYPE]`
* `ALERT_TYPE`: the type of alert required. There are three types of alerts; `expiry`, `stock` and `all`.

### 2.9. Set Thresholds

Set the thresholds that trigger the `expiry` and `stock` alerts.

Format: `set [THRESHOLD_TYPE] [VALUE]`
* `THRESHOLD_TYPE`: the type of threshold to be set. Either `expiry` or `stock`.
* `VALUE`: the threshold value. Units are days for `expiry` and kilograms for `stock`.

Examples: `set expiry 5`, `set stock 10`

### 2.10. Search For Ingredients

Search for ingredients using keywords in their names.

Format: `find [INGREDIENT_NAMES]`
* `INGREDIENT_NAMES`: The words to search for in the ingredient list. Can be one or more names.

Examples: `find carrot`, `find bean apple`

### 2.11. View/ Set Current Date

View or set the system's current date. 

Format: `date [NEW_DATE]`
* `NEW_DATE`: The date to be set as the current date. If blank, the current date is shown.

### 2.12. Exit Program

Exit the program once done.

Format: `exit`

## 3. FAQ

**Q**: How do I transfer my data to another computer?

**A**: {your answer here}

## Command Summary

| Action | Command |
|---|---|
| Add ingredient | `add n/[INGREDIENT_NAME] a/[AMOUNT] u/[UNITS] e/[EXPIRY]` |
| List ingredients | `list` |
| Update ingredient | `update n/[INGREDIENT_NAME] a/[AMOUNT] u/[UNITS] e/[EXPIRY]` |
| Delete ingredient | `delete [INGREDIENT_NUMBER]` |
| Show expiring ingredients | `expire [DATE]` |
| Show alerts | `alert [ALERT_TYPE]` |
| Set thresholds | `set [THRESHOLD_TYPE] [VALUE]` |
| Search for ingredients | `find [INGREDIENT_NAMES]` |
| View/set current date | `date [NEW_DATE]` |
| Exit program | `exit` |
| View help | `help` |
