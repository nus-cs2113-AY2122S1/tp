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

[**1. Quick Start**](#1-quick-start)  <br>
[**2. Features**](#2-features)  <br>
&nbsp;&nbsp;[2.1. Viewing Help](#21-viewing-help) <br>
&nbsp;&nbsp;[2.2. Adding Ingredients](#22-adding-an-ingredient) <br>
&nbsp;&nbsp;[2.3. Listing Ingredients](#23-listing-all-ingredients) <br>
&nbsp;&nbsp;[2.4 Updating Ingredients](#24-updating-an-ingredient) <br>
&nbsp;&nbsp;[2.5. Delete Ingredients](#25-stop-tracking-an-ingredient) <br>
&nbsp;&nbsp;[2.6. Showing Expiring Ingredients](#26-showing-expiring-ingredients) <br>
&nbsp;&nbsp;[2.7. Displaying Alerts](#27-displaying-alerts) <br>
&nbsp;&nbsp;[2.8. Setting Thresholds](#28-setting-thresholds) <br>
&nbsp;&nbsp;[2.9. Searching For Ingredients](#29-searching-for-ingredients) <br>
&nbsp;&nbsp;[2.10. Viewing/ Setting Current Date](#210-viewing-setting-current-date) <br>
&nbsp;&nbsp;[2.11. Exiting Program](#211-exiting-program) <br>
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

### 2.1. Viewing Help

Shows a list of available commands and their syntax.

Format: `help`

### 2.2. Adding An Ingredient

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

### 2.3. Listing All Ingredients

Display a list of all ingredients in the ingredient list

Format: `list`

### 2.4. Updating An Ingredient

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

### 2.5. Stop Tracking An Ingredient

Delete an ingredient from the ingredient list based on its index in the list.

Format: `delete [INGREDIENT_NUMBER]`

The parameter used in the command is:
* `INGREDIENT_NUMBER`: the ingredient number to remove

Example: `delete 1`

### 2.6. Showing expiring ingredients

List the ingredients that will expire by a specified date.

Format: `expire [DATE]`

The parameter used in the command is:
* `DATE`: the date of interest

Example: `expire 12/11/2021`

### 2.7 Displaying alerts

Displays the specified type of alert

Format: `alerts [TYPE]`

There are 3 possible `[TYPE]`:
1. `expiry`: displays the ingredients expiring within a threshold number of days
2. `stock`: displays the ingredients with stock less than a threshold value
3. `all`: displays both of the above

For more about thresholds and setting them, see [here](#28-setting-thresholds).

### 2.8 Setting thresholds

Sets the expiry threshold or the stock threshold for the `[alerts](#27-displaying-alerts) function.

Format: `set [TYPE] [NEW_VALUE]`

The parameters used in the command are:
* `TYPE`: either `expiry` or `stock`
* `NEW_VALUE`: the new threshold for which alerts will be displayed

### 2.9. Searching For Ingredients

Search for ingredients using keywords in their names.

Format: `find [INGREDIENT_NAMES]`
* `INGREDIENT_NAMES`: The words to search for in the ingredient list. Can be one or more names.

Examples: `find carrot`, `find bean apple`

### 2.10. Viewing/ Setting Current Date

View or set the system's current date. 

Format: `date [NEW_DATE]`
* `NEW_DATE`: The date to be set as the current date. If blank, the current date is shown.

### 2.11. Exiting Program

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
| Set thresholds | `set [TYPE] [NEW_VALUE]` |
| Search for ingredients | `find [INGREDIENT_NAMES]` |
| View/set current date | `date [NEW_DATE]` |
| Exit program | `exit` |
| View help | `help` |
