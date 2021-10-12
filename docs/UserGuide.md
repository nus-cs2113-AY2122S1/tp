# StonksXD User Guide

## Introduction

StonksXD is a ...

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

Format: `help

### Create expense entry: `add_ex`
Adds an expense entry.

Format: `add_ex d/DESCRIPTION a/AMOUNT`

- `DESCRIPTION` has to be non-empty.
- `AMOUNT` has to be a positive amount.

Examples:

- `add_ex d/KFC lunch a/10.20` Adds an expense entry regarding lunch that costs $10.20.

### Delete expense entry: `del_ex`


### List all expense entries: `list_ex`


### View total expense: `total_ex`


### Create income entry: `add_in`


### Delete income entry: `del_in`


### List all income entries: `list_in`


### View total income: `total_in`


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


