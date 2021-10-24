# User Guide

## Table of Content
* [Intro](#introducing-duke-cs2113t)
* [Getting Started](#getting-started)
* [Features](#features)
* [Commands](#commands)
  * [Add](#add) 
    * [Budget: `-b`](#add-budget)
    * [Expenditure: `-e`](#add-expenditure)
    * [Loan: `-l`](#add-loan)
  * [Edit](#edit)
    * [Budget: `-b`](#edit-budget)
    * [Expenditure: `-e`](#edit-expenditure)
    * [Loan: `-l`](#edit-loan)
  * [Database Selector](#dbselect)
	* [year](#year)
  * [Find & Filter](#find&filter)
    * [Find](#find)
  * [Listing](#listing)
    * [List](#list)
  * [Deletion](#deletion)
    * [Delete](#delete)
  * [Guides](#guides)
    * [Help](#help)
  * [Exit](#exit)
    * [Bye](#bye)
* [FAQ](#faq)
* [Command summary](#command-summary)

## Introduction

Budget Tracker is a desktop app for managing expenses, budget and loans, optimized for use via a 
Command Line Interface (CLI) for tech-savvy students who have trouble keeping track of their expenses.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Budget Tracker` from [here]().

## Features

### Notes about the command format:
* Words in UPPER_CASE are the parameters to be supplied by the user. <br />
e.g. in `add -b a/AMOUNT m/MONTH`, `AMOUNT` and `MONTH` are parameters which can be used as `add -b a/500 m/12`
* Words encased in `<>` are optional parameters.

<br />

## Commands

## `add`

The command word `add` adds a record of either Budget, Expenditure, or Loan to the record list. 
The type of record is determined by the identifier tag after the `add` command word.

### `-b` : Add a Budget

Adds a new budget to a specific month.

Format: `add -b a/AMOUNT m/MONTH`

* The `AMOUNT` can be entered with 2 decimal places or without decimal places and cannot be empty.
* The `MONTH` must strictly be within the range of 1 to 12 and cannot be empty.

Example of usage:

`add -b a/500 m/12`

Expected outcome: Budget of $500.00 is added to the december of that particular year.

```
========================================================
Your budget of 500.0 for this month is successfully added!
========================================================
```

### `-e` : Add an expenditure

Adds a new expenditure to a month.

Format: `add -e n/<DESCRIPTION> a/AMOUNT d/<DATE_OF_EXPENDITURE> c/<CATEGORY>`

* The `DESCRIPTION` can be in a natural language format. It can be left empty if the user does not wish
to add specific information about the expenditure.
* The `AMOUNT` entered can be up to 2 decimal places and cannot be empty.
* The `<DATE_OF_EXPENDITURE>` must strictly be in the form of _YYYY-MM-DD_. If left empty, the current date according to the
system will be entered by default.
* The `<CATEGORY>` must be one of the following values:
  * _GENERAL, CLOTHES, FOOD, ENTERTAINMENT, GIFTS, HEALTH_
  * If left empty, the default value will be _GENERAL_

Example of usage:

`add -e n/CS2113T Textbooks a/60 d/2021-08-20 c/GENERAL`

Expected outcome: Expenditure of $60.00 2021-08-20 on has been successfully added.

```
========================================================
Expenditure successfully added!
Description: CS2113T Textbooks
Amount: $60.00
Date: 2021-08-20
========================================================
```

### Adding a Loan: `add`

## `edit` 

### Edit Budget: `edit`  

### Edit Expenditure: `edit` 

### Edit Loan: `edit` 

## `year`

### Switching Database Year: `year`

Selection of the database year.

Format: `year <year>`

* The <year> must strictly be 4 characters long and must only be numeric.

Example of usage:

`year 2020`

Expected outcome: A message will be shown to alert the user that the database have been switched to 2020.

```
========================================================
You are currently working on year 2020 database!
========================================================
```

## `find`

### Finding a Particular Expenditure/Budget: `find`

## `list`

### Listing all Budget & Expenditure: `list`

Adds a new expenditure to a specific budget of a month.

Format: `list m/all`

* The command format must strictly be as shown above.

Example of usage:

`list m/all`

Expected outcome: Listing all the budget and expenditure for the particular year.

```
========================================================
Your budget for January: Not Set
Your expenditures:
 None 
========================================================
Your budget for February: Not Set
Your expenditures:
 None 
========================================================
Your budget for March: Not Set
Your expenditures:
 None 
========================================================
Your budget for April: Not Set
Your expenditures:
 None 
========================================================
Your budget for May: Not Set
Your expenditures:
 None 
========================================================
Your budget for June: Not Set
Your expenditures:
 None 
========================================================
Your budget for July: Not Set
Your expenditures:
 None 
========================================================
Your budget for August: Not Set
Your expenditures:
  Description            | Amount             | Date           
1.CS2113T Textbooks      | $60.0              | 2021-08-20       
========================================================
Your budget for September: Not Set
Your expenditures:
  Description            | Amount             | Date           
1.Chicken Rice1          | $500.0             | 2021-09-13       
========================================================
You are spending too much for October !
Your budget for October: $330.0
Your expenditures:
  Description            | Amount             | Date           
1.Chicken Rice1          | $500.0             | 2021-10-13       
========================================================
Your budget for November: Not Set
Your expenditures:
 None 
========================================================
Your budget for December: $500.0
Your expenditures:
 None 
========================================================
```

### Listing Budget & Expenditure for a Particular Month: `list`

Adds a new expenditure to a specific budget of a month.

Format: `list m/<month>`

* The 'month' must strictly be within the range of 1 to 12.

Example of usage:

`list m/10`

Expected outcome: Listing the budget and all expenditures for that particular month.

```
========================================================
You are spending too much for October !
Your budget for October: $330.0
Your expenditures:
  Description            | Amount             | Date           
1.Chicken Rice1          | $500.0             | 2021-10-13       
========================================================
```

## `delete`

Deletes an entry / entries (maybe user wants to cut off the expenses) from the budget tracker data set.

### `b/` - Delete a budget 

Deletes the budget of a specific month

Format: `delete b/ m/MONTH`

* The `MONTH` represents the month of the budget will be deleted and cannot be empty.

Example of usage:

`delete b/ m/10`

Expected outcome:
```
========================================================
Successfully deleted Budget for this month!
Now the budget amount is 0.00!      
========================================================
```

### `e/` - Delete (an) expenditure(s)

Deletes (an) expenditure(s) of a specific month

Format:`delete e/ m/MONTH` 
<br/> `delete e/INDEX m/MONTH`
<br/> `delete e/INDEX_FROM-INDEX_TO m/MONTH`

* The `MONTH` represents the month of the expenditure(s) will be deleted and cannot be empty.
* The `INDEX` refers to the index number or a range of index number shown in the displayed entry list.
* The `INDEX` must be within the range of the list.
* If the `INDEX` is empty, then all the expenditures of this month will be deleted.

Examples of usage:

 `delete e/3-5 m/MONTH`

Expected outcome:
```
========================================================
Successfully deleted Expenditure 3.chicken rice3          | $5.0               | 2021-10-21       
Successfully deleted Expenditure 4.chicken rice4          | $5.0               | 2021-10-21       
Successfully deleted Expenditure 5.chicken rice5          | $5.0               | 2021-10-21     
========================================================
```

### `l/` - Delete (a) loan record(s)

Deletes (a) loan record(s) of a specific month

Format:`delete l/ m/MONTH` 
<br/> `delete l/INDEX m/MONTH`
<br/> `delete l/INDEX_FROM-INDEX_TO m/MONTH`

* The `MONTH` represents the month of the loan record(s) will be deleted and cannot be empty.
* The `INDEX` refers to the index number or a range of index number shown in the displayed entry list.
* The `INDEX` must be within the range of the list.
* If the `INDEX` is empty, then all the loan records of this month will be deleted.

Examples of usage:

 `delete l/3-5 m/MONTH`

Expected outcome:
```
========================================================
Successfully deleted Loan 3.Wei Xuan               | $1000.0            | 2021-10-24       
Successfully deleted Loan 4.Luoyuang               | $1000.0            | 2021-10-24       
Successfully deleted Loan 5.Yixuan                 | $1000.0            | 2021-10-24      
========================================================
```

## `help`
 
### Command Guides: `help`

Command description and format.

Format: `help`

* The format must be strictly as stated above.

Example of usage:

`help`

Expected outcome: Listing the budget and all expenditures for that particular month.

```
========================================================
1. add
Adds an expenditure record.
Parameters: -e c/DESCRIPTION a/COST d/<DATE_OF_EXPENDITURE>
Note: If DATE_OF_EXPENDITURE is not specified, the current system date will be the default value.

Adds a budget record.
Parameters: -b a/AMOUNT m/MONTH [y/YEAR]

Adds a loan record.
Parameters: l/DEBTOR_NAME a/AMOUNT [d/DATE_OF_LOAN]
========================================================
2. edit m/MONTH b/AMOUNT
========================================================
3. edit m/MONTH i/INDEX [e/NEW_EXPENDITURE_NAME] [a/NEW_AMOUNT] [d/NEW_DATE]
========================================================
4. edit m/MONTH i/INDEX [l/DEBTOR_NAME] [a/AMOUNT] [d/DATE_OF_LOAN]
========================================================
5. find [m/MONTH] b/DESCRIPTION
========================================================
6. list m/all
========================================================
7. list m/MONTH
========================================================
8. delete m/MONTH
========================================================
9. delete m/MONTH i/INDEX
========================================================
10. delete m/MONTH i/INDEX_FROM-INDEX_TO
========================================================
11. help
========================================================
12. bye
========================================================
```

## `bye`

### Exiting the program: `bye`
 
Exits the program. 

Format: `bye`

* The format must be strictly as stated above.

Example of usage:

`bye`

Expected outcome: Goodbye message shown.

```
========================================================
Bye, see you again soon!
========================================================
```

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: {your answer here}

## Command Summary
| `no.` | `Command` | `Description` |
| --- | --- | --- |
| `1` | `add -b a/AMOUNT m/MONTH` | `add budget of AMOUNT to MONTH` |
| `2` | `add -e c/DESCRIPTION a/AMOUNT d/<DATE_OF_EXPENDITURE>` | `add expenditure <description> of <amount> on <date>` |
| `3` | `` | `` |
| `4` | `` | `` |
| `5` | `` | `` |
| `6` | `` | `` |
| `7` | `` | `` |
| `8` | `` | `` |
| `9` | `` | `` |
| `10` | `` | `` |
| `11` | `` | `` |
| `12` | `` | `` |
| `13` | `` | `` |
| `14` | `` | `` |
| `15` | `` | `` |

