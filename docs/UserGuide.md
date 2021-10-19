# StonksXD User Guide

![](https://melmagazine.com/wp-content/uploads/2019/07/Stonks_Meme.jpg)

## Introduction

- StonksXD is a smart money management application that tracks daily expenses, sets/adjusts spending limits and gives advice based on daily expenses. 


- As a user you could view your financial records in different forms of currencies and in the forms of graphs to help you better understand your finances.


- It targets students who travel frequently and prefer logging their financial records daily. With the application, students will then be able to manage their finances in different currencies with functions optimised for daily logging.

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


2. Download the latest version of `StonksXD.jar` from [here](https://github.com/AY2122S1-CS2113T-T12-3/tp/releases).


3. Copy the file to the folder you want to use as the home folder for your `StonksXD`.


4. Open the Command-Line interface (CLI) and navigate to the directory where you saved the `.jar` file and run `java -jar tp.java` in the command line. `StonksXD` will start up.


5. In `StonksXD`, type the command in the CLI and press Enter to execute it. (Tip: type `help` to show all available commands and their format.)


6. Use the format `add_ex d/DESCRIPTION a/AMOUNT c/CATEGORY` to add expense entries to `StonksXD`.


7. Use the format `add_in d/DESCRIPTION a/AMOUNT c/CATEGORY` to add income entries to `StonksXD`.


8. Type `balance` to view your net saving.


9. Refer to the [Features](#features) below for the full details of each command.
## Features 

### Notes

- Words in `UPPER_CASE` are the parameters to be supplied by the user.
  e.g. in add `a/AMOUNT`, `AMOUNT` is a parameter which can be used as `a/12.30`.

### View all possible commands: `help`
Shows a list of all possible commands.

Format: `help`

<font size=1>_Below is a collapsible section that allows you to see the run time output. Do check them if you want to visualize what the product looks like!_</font>

<details>
  <summary> Expected output in run window (Click to expand!)</summary>

````
-----------------------------------------------------------------------------------------------------
This is a list of commands and their format!
-----------------------------------------------------------------------------------------------------
List Out All Commands: help
Adding Expense: add_ex d/DESCRIPTION a/AMOUNT
Deleting Expense: del_ex i/INDEX
Listing Expense: list_ex
Show Total Expense: total_ex
Show Total Expense between 2 dates: btw_ex s/START_DATE e/END_DATE
Adding Income: add_in d/DESCRIPTION a/AMOUNT
Deleting Income: del_in i/INDEX
Listing Income: list_in
Show Total Income: total_in
To Find Using Date: find YYYY-MM-DD
To Find Based On Keyword: find KEYWORD
To Display Total Balance: balance
Show Total Income between 2 dates: btw_in s/START_DATE e/END_DATE
To Terminate The Program: end
-----------------------------------------------------------------------------------------------------
````
</details>

<br>

### Create expense entry: `add_ex`
Adds an expense entry.

Format: `add_ex d/DESCRIPTION a/AMOUNT c/CATEGORY`

- `DESCRIPTION` has to be non-empty.
- `AMOUNT` has to be a positive amount.
- `CATEGORY` has to be either `food`, `transport`, `bills`, `medical`, or `entertainment`.

Examples:

- `add_ex d/KFC lunch a/10.20 c/food` Adds an expense entry regarding lunch that costs $10.20.

<details>
  <summary> Expected output in run window </summary>

````
-----------------------------------------------------------------------------------------------------
Your most recent spending: 
[E] KFC lunch - $10.20 (19 Oct 2021)
-----------------------------------------------------------------------------------------------------
````
</details>

<br>

### Delete expense entry: `del_ex`

Delete unwanted expense entries by providing the index of said entry.

Format: `del_ex i/INDEX`

- `INDEX` has to be non-empty.
- `INDEX` has to be a valid non-negative integer.

Examples:

- `del_ex i/1` Deletes the 1st entry from the expense list.

<details>
  <summary> Expected output in run window </summary>

Before deletion the expense list is as follows:

````
-----------------------------------------------------------------------------------------------------
Below is a list of all of your recent spending!
-----------------------------------------------------------------------------------------------------
1: [E] pillow - $500.00 (18 Oct 2021)
2: [E] bought cookies - $500.00 (18 Jan 2021)
3: [E] bought home - $555.00 (18 Jul 2021)
-----------------------------------------------------------------------------------------------------
````
<br>

When command `del_ex i/1` is given, you get the following message:

````
-----------------------------------------------------------------------------------------------------
You removed this: 
[E] pillow - $500.00 (18 Oct 2021)
-----------------------------------------------------------------------------------------------------
````
<br>
After deletion, we see that the list has removed the previous first entry!

````
-----------------------------------------------------------------------------------------------------
1: [E] bought cookies - $500.00 (18 Jan 2021)
2: [E] bought home - $555.00 (18 Jul 2021)
-----------------------------------------------------------------------------------------------------
````
</details>
<br>

### List all expense entries: `list_ex`

Displays the previously added expense entries in the form of a list.

Format: `list_ex`

<details>
  <summary> Expected output in run window </summary>

````
-----------------------------------------------------------------------------------------------------
Below is a list of all of your recent spending!
-----------------------------------------------------------------------------------------------------
1: [E] bought cookies - $500.00 (18 Jan 2021)
2: [E] bought home - $555.00 (18 Jul 2021)
3: [E] bought car - $4777.00 (18 Jun 2021)
4: [E] bought condo - $87654888878.00 (18 May 2021)
5: [E] KFC lunch - $10.20 (19 Oct 2021)
-----------------------------------------------------------------------------------------------------
````

</details>

<br>

### View total expense: `total_ex`

Displays the total amount of all expenses in your list.

Format: `total_ex`

<details>
  <summary> Expected output in run window </summary>

Here we have a simple expense list with three items:
````
-----------------------------------------------------------------------------------------------------
1: [E] bought cookies - $100.00 (18 Jan 2021)
2: [E] bought home - $200.50 (18 Jul 2021)
3: [E] bought car - $300.00 (18 Jun 2021)
-----------------------------------------------------------------------------------------------------
````
<br>

If we give the command `total_ex`, it will display the total expenditure:
````
-----------------------------------------------------------------------------------------------------
Your total expense is: $600.50
-----------------------------------------------------------------------------------------------------
````

</details>
<br>

### Create income entry: `add_in`

Adds an income entry.

Format: `add_in d/DESCRIPTION a/AMOUNT c/CATEGORY`

- `DESCRIPTION` has to be non-empty.
- `AMOUNT` has to be a positive amount.
- `CATEGORY` has to be either `salary`, `allowance`, or `adhoc`.

Examples:

- `add_in d/lunch money a/1000 c/allowance` Adds an income entry regarding an allowance of $1000.

<details>
  <summary> Expected output in run window </summary>

````
-----------------------------------------------------------------------------------------------------
Your most recent earning: 
[I] lunch money - $1000.00 (19 Oct 2021)
-----------------------------------------------------------------------------------------------------
````

</details>
<br>

### Delete income entry: `del_in`

Delete unwanted income entries by providing the index of said entry.

Format: `del_in i/INDEX`

- `INDEX` has to be non-empty.
- `INDEX` has to be a valid non-negative integer.

Examples:

- `del_in i/1` Deletes the 1st entry from the income list.

<details>
  <summary> Expected output in run window </summary>

Before deletion the income list is as follows:

````
-----------------------------------------------------------------------------------------------------
Below is a list of all of your recent earnings!
-----------------------------------------------------------------------------------------------------
1: [I] rob a bank - $800.00 (18 Oct 2021)
2: [I] rob a church - $300.00 (18 Nov 2021)
3: [I] rob a car - $400.00 (18 Dec 2021)
-----------------------------------------------------------------------------------------------------
````
<br>

When command `del_in i/1` is given, you get the following message:

````
-----------------------------------------------------------------------------------------------------
You removed this: 
[I] rob a bank - $800.00 (18 Oct 2021)
-----------------------------------------------------------------------------------------------------
````
<br>
After deletion, we see that the list has removed the previous first entry!

````
-----------------------------------------------------------------------------------------------------
1: [I] rob a church - $300.00 (18 Nov 2021)
2: [I] rob a car - $400.00 (18 Dec 2021)
-----------------------------------------------------------------------------------------------------
````

</details>
<br>

### List all income entries: `list_in`

Displays the previously added income entries in the form of a list.

Format: `list_in`

<details>
  <summary> Expected output in run window </summary>

````
-----------------------------------------------------------------------------------------------------
Below is a list of all of your recent earnings!
-----------------------------------------------------------------------------------------------------
1: [I] rob a church - $300.00 (18 Nov 2021)
2: [I] rob a car - $400.00 (18 Dec 2021)
3: [I] rob a home - $500.00 (18 Sep 2021)
4: [I] rob a child - $600.00 (18 Aug 2021)
5: [I] lunch money - $1000.00 (19 Oct 2021)
-----------------------------------------------------------------------------------------------------
````
</details>
<br>

### View total income: `total_in`

Displays the total amount of all income entries in your list.

Format: `total_in`
<details>
  <summary> Expected output in run window </summary>

Here we have a simple income list with three items:
````
-----------------------------------------------------------------------------------------------------
1: [I] rob a church - $300.00 (18 Nov 2021)
2: [I] rob a car - $400.00 (18 Dec 2021)
3: [I] rob a home - $500.00 (18 Sep 2021)
-----------------------------------------------------------------------------------------------------
````
<br>

If we give the command `total_in`, it will display the total income:
````
-----------------------------------------------------------------------------------------------------
Your total income is: $2800.00
-----------------------------------------------------------------------------------------------------
````
</details>
<br>

### Find entry using date: `find YYYY-MM-DD`

Finds and displays the income or expense entries recorded on the given date.

Format: `find YYYY-MM-DD`

- If the date given is not in the recognised format, it will be treated as a String and be passed into keyword search.

Examples:

- `find 2021-10-19` returns income and/or expense entries recorded on the given date.

<details>
  <summary> Expected output in run window </summary>

If you enter `find 2021-10-19`, it will find the entry recorded on that date:
````
-----------------------------------------------------------------------------------------------------
Your most recent earning: 
[I] Birthday Money! - $200.00 (19 Oct 2021)
-----------------------------------------------------------------------------------------------------
````

</details>
<br>

### Find entry using keyword: `find KEYWORD`

Finds and displays the income or expense entries that contain the given keyword.

Format: `find KEYWORD`

- `KEYWORD` has to be a non-empty
- `KEYWORD` can be any alpha-numeric string

Examples:

- `find FOOD` returns income and/or expense entries that contain the keyword `FOOD` in their description or categories.

<details>
  <summary> Expected output in run window </summary>

If you wish to search based on category, for e.g. all `food` expenses: 

- Give the command `find food` and it will return the following:
````
-----------------------------------------------------------------------------------------------------
Below is a list of all your findings!
-----------------------------------------------------------------------------------------------------
1: [E] KFC lunch - $10.20 (19 Oct 2021)
-----------------------------------------------------------------------------------------------------
````

If you wish to search based on description, for e.g. all entries that contain the keyword `bought`:

- Give the command `find bought` and it will return the following:
````
-----------------------------------------------------------------------------------------------------
Below is a list of all your findings!
-----------------------------------------------------------------------------------------------------
1: [E] bought cookies - $500.00 (18 Jan 2021)
2: [E] bought home - $555.00 (18 Jul 2021)
3: [E] bought car - $4777.00 (18 Jun 2021)
4: [E] bought condo - $87654888878.00 (18 May 2021)
-----------------------------------------------------------------------------------------------------
````
</details>
<br>

### View total balance: `balance`

Shows the net balance you have leftover from your expenses and incomes.

Format: `balance`
<details>
  <summary> Expected output in run window </summary>

````
-----------------------------------------------------------------------------------------------------
Your current balance is: $-87654891720.20
-----------------------------------------------------------------------------------------------------
````
Wow, you are way too poor.... :(

</details>
<br>

### Show total income between 2 dates: `btw_in`

Shows the total income you had accumulated during a given time period.

Format: `btw_in s/START_DATE e/END_DATE`

- `START_DATE` & `END_DATE` are of the form YYYY-MM-DD
- `START_DATE` & `END_DATE` have to be valid and non-empty

Examples: 
- `btw_in s/1987-12-06 e/1999-11-21` will return the total income of all entries between the given dates.
<details>
  <summary> Expected output in run window </summary>

If you wish to find your income between 10th Aug 2021 and 23rd Oct 2021:

- Give the command `btw_in s/2021-07-10 e/2021-10-23`:
````
-----------------------------------------------------------------------------------------------------
Your total income between 10 Jul 2021 and 23 Oct 2021 is : $2300.00
-----------------------------------------------------------------------------------------------------
````

</details>
<br>

### Terminate program: `end`

Exits the program when the user is done.

Format: `end`
<details>
  <summary> Expected output in run window </summary>

````
-----------------------------------------------------------------------------------------------------
██████  ██    ██ ███████        ██  
██   ██  ██  ██  ██          ██  ██ 
██████    ████   █████           ██ 
██   ██    ██    ██          ██  ██ 
██████     ██    ███████        ██ 
-----------------------------------------------------------------------------------------------------

````
</details>


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
| Find entry using date | `find YYYY-MM-DD` |
| Find entry using keyword | `find KEYWORD` |
| View total balance | `balance` |
| Show total income between 2 dates | `btw_in s/START_DATE e/END_DATE` |
| To terminate program | `end` |


