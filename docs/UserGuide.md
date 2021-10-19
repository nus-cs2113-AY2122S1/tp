# StonksXD User Guide

## Introduction

StonksXD is a smart and user-friendly financial tracker that allows students to easily organise their expenditures and sources of income.

It hosts a vast array of commands that allows any CLI enthusiast to manage a list of their various finances, without the need for mental notes or accounting after a day's worth of spending.

## Table of Contents

- [Quick Start](#quick-start)
- [Features](#features)
    - [View all possible commands: `help`](#view-all-possible-commands-help)
    - [Create expense entry: `add_ex`](#create-expense-entry-add_ex)
    - [Delete expense entry: `del_ex`](#delete-expense-entry-del_ex)
    - [List all expense entries: `list_ex`](#list-all-expense-entries-list_ex)
    - [View total expense: `total_ex`](#view-total-expense-total_ex)
    - [Create income entry: `add_in`](#create-income-entry-add_in)
    - [Delete income entry: `del_in`](#delete-income-entry-del_in)
    - [List all income entries: `list_in`](#list-all-income-entries-list_in)
    - [View total income: `total_in`](#view-total-income-total_in)
    - [Find entry using date: `find YYYY-MM-DD`](#find-entry-using-date-find YYYY-MM-DD)
    - [Find entry using keyword: `find KEYWORD`](#find-entry-using-keyword-find KEYWORD)
    - [View total balance: `balance`](#view-total-balance-balance)
    - [Show total income between 2 dates: `btw_in s/START_DATE e/END_DATE`](#show-total-income-between-2-dates-btw_in)
    - [To terminate program: `end`](#to-terminate-program-end)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `StonksXD.jar` from [here](https://github.com/AY2122S1-CS2113T-T12-3/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your `StonksXD`.
4. Type the command in the CLI and press Enter to execute it. e.g. typing `help` and pressing Enter will
show you all the available commands and their format.
5. Refer to the [Features](#features) below for details of each command.

## Features 

### Notes

- Words in `UPPER_CASE` are the parameters to be supplied by the user.
  e.g. in add `a/AMOUNT`, `AMOUNT` is a parameter which can be used as `a/12.30`.

### View all possible commands: `help`
Shows a list of all possible commands.

Format: `help`

<br>

### Create expense entry: `add_ex`
Adds an expense entry.

Format: `add_ex d/DESCRIPTION a/AMOUNT`

- `DESCRIPTION` has to be non-empty.
- `AMOUNT` has to be a positive amount.

Examples:

- `add_ex d/KFC lunch a/10.20` Adds an expense entry regarding lunch that costs $10.20.

<br>

### Delete expense entry: `del_ex`

Delete unwanted expense entries by providing the index of said entry.

Format: `del_ex i/INDEX`

- `INDEX` has to be non-empty.
- `INDEX` has to be a valid non-negative integer.

Examples:

- `del_ex i/1` Deletes the 1st entry from the expense list.

<br>

### List all expense entries: `list_ex`

Displays the previously added expense entries in the form of a list.

Format: `list_ex`

<br>

### View total expense: `total_ex`

Displays the total amount of all expenses in your list.

Format: `total_ex`

<br>

### Create income entry: `add_in`

Adds an income entry.

Format: `add_in d/DESCRIPTION a/AMOUNT`

- `DESCRIPTION` has to be non-empty.
- `AMOUNT` has to be a positive amount.

Examples:

- `add_in d/Allowance a/1000` Adds an income entry regarding an allowance of $1000.

<br>

### Delete income entry: `del_in`

Delete unwanted income entries by providing the index of said entry.

Format: `del_in i/INDEX`

- `INDEX` has to be non-empty.
- `INDEX` has to be a valid non-negative integer.

Examples:

- `del_in i/1` Deletes the 1st entry from the income list.

<br>

### List all income entries: `list_in`

Displays the previously added income entries in the form of a list.

Format: `list_in`

<br>

### View total income: `total_in`

Displays the total amount of all income entries in your list.

Format: `total_in`

<br>

### Find entry using date: `find YYYY-MM-DD`

Finds and displays the income or expense entries recorded on the given date.

Format: `find YYYY-MM-DD`

- `YYYY-MM-DD` has to be a valid non-empty date
- `YYYY-MM-DD` has to be positive

Examples:

- `find 1999-07-23` returns income and/or expense entries recorded on the given date.

<br>

### Find entry using keyword: `find KEYWORD`

Finds and displays the income or expense entries that contain the given keyword.

Format: `find KEYWORD`

- `KEYWORD` has to be a non-empty
- `KEYWORD` can be any alpha-numeric string

Examples:

- `find FOOD` returns income and/or expense entries that contain the keyword `FOOD` in their description.

<br>

### View total balance: `balance`

Shows the net balance you have leftover from your expenses and incomes.

Format: `balance`

<br>

### Show total income between 2 dates: `btw_in`

Shows the total income you had accumulated during a given time period.

Format: `btw_in s/START_DATE e/END_DATE`

- `START_DATE` & `END_DATE` are of the form YYYY-MM-DD
- `START_DATE` & `END_DATE` have to be valid and non-empty

Examples: `btw_in s/1987-12-06 e/1999-11-21` will return the total income of all entries between the given dates.

<br>

### Terminate program: `end`

Exits the program when the user is done.

Format: `end`


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

| Action | Format, Examples |
| ------------ | ------------- |
| View all possible commands | `help` |
| Create expense entry | `add_ex d/DESCRIPTION a/AMOUNT` <br /> e.g., `add_ex d/KFC lunch a/10.20` |
| Delete expense entry | `del_ex i/INDEX` <br /> e.g., `del_ex i/3` |
| List all expense entries | `list_ex` |
| View total expense | `total_ex` |
| Create income entry | `add_in d/DESCRIPTION a/AMOUNT` <br /> e.g., `add_in d/work a/3200` |
| Delete income entry | `del_in i/INDEX` <br /> e.g., `del_in i/2` |
| List all income entries | `list_in` |
| View total income | `total_in` |


