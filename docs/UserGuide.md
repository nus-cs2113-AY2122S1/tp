# Mint User Guide v2.0

## Introduction

Mint is an all-in-one money managing app that helps you track your daily expenses, set budgets and long term financial
goals(coming soon). It is optimized for use via a Command Line Interface (CLI).

As our team comprises university students, we hope that we can help fellow young adults in keeping track of their
finances.

Using this guide, you will be able to navigate the app and use all of its functionalities through
step-by-step-instructions.

<div style="page-break-after: always;"></div>

## Table of Contents

- [Quick Start](#quickStart)
    - [Setting Up](#settingUp)
    - [Running the Programme](#runningTheProgramme)
    - [[For users new to CLI] Changing the Directory](#changingTheDirectory)
- [Prerequisites before using Mint](#know)
    - [Available tag formats](#tagFormat)
    - [Available date formats](#dateFormat)
    - [List of categories](#categoryList)
- [Features](#features)
    - [Notes about commands](#commandNote)
    - [Notes about recurring entries](#recurringNote)
    - [Viewing help](#help)
    - [Adding entries](#add)
    - [Adding recurring entries](#addR)
    - [Viewing entries](#view)
    - [Deleting entries](#delete)
    - [Deleting recurring entries](#deleteR)
    - [Deleting all entries](#deleteAll)
    - [Editing entries](#edit)
    - [Editing recurring entries](#editR)
    - [Viewing categories](#cat)
    - [Setting budget](#set)
    - [View monthly budget ](#budget)
    - [Exiting the program](#exit)
- [Command Summary](#command-summary)
- [Frequently Asked Questions](#faq)

<div style="page-break-after: always;"></div>

## <a name="quickStart"></a>Quick Start

> Before you get started, ensure that you have Java 11 or above installed in your Computer. Once that is done, follow
> the steps below!

### <a name="settingUp"></a>Setting Up

1. Download the latest version of tp.jar from [here](https://github.com/AY2122S1-CS2113T-W11-2/tp/releases/tag/v2.1).

2. Copy the tp.jar file to the folder you desire.

### <a name="runningTheProgramme"></a>Running the Programme

1. Open your desired Command Line Interface and ensure that you are in the directory where you saved the folder. If you
   are new to git, click [here](#changingTheDirectory) to see how you can change the directory.
2. Once you ensured you are in the correct directory, run the programme using the command `java -jar tp.jar`.
3. To test if the programme is working, type a command and press Enter to execute it. e.g., typing `help` and pressing
   Enter will display the list of commands to help you use our application.

**Some example commands you can try:**

1. Add an expense to your list: `add a/13 d/2021-12-03 n/Movie ticket c/1`
   > This command adds a Movie ticket that you have purchased for 13 dollars on December 3rd 2021 under the
   > Entertainment category

2. Exit the programme: `exit`
   > This command terminates the program.

Refer to the [Features Section](#features) below for details of each comm

### <a name="changingTheDirectory"></a>[For users new to CLI] Changing the Directory to your tp.jar file

1. Right-click on your tp.jar file and select Properties. There would be a pop up with all the information.
2. Look for the Location and copy the entire string.
3. Go back to your Command Line Interface and enter the command `cd [paste what you copied here]`
4. Mint is now at your service!

<div style="page-break-after: always;"></div>

## <a name="know"></a>Prerequisites before using Mint

### <a name="tagFormat"></a>Acceptable tag formats

|Tag | Description |Format | Example of input |
|--------|----|-------------------|-------------|
| `n/NAME`    | Name of the entry | Any string of characters                                            | `n/Hai di lao`, `n/123`|
| `a/AMOUNT`  | Amount in dollars. Numbers after decimal points are in cents.   | Positive number smaller than 1 million. It will be automatically rounded to 2 decimal points if higher precision is given.| `a/12.50` | 
| `d/DATE`    | Date or start date | Any of the [acceptable date formats](#dateFormat) from `2000-01-01` to `2200-12-31`. If the date is not specified, the default date set will be today.  | `d/2016-02-13`, `d/2016-2-13`  would be the date of entry added.   |
| `c/CATEGORY_NUMBER`| Category number | Please refer to the [available categories](#categoryList). If the `CATEGORY_NUMBER` is not specified, the default `CATEGORY_NUMBER` would be `c/7` which is `Others`.   | `c/1` |
| `i/INTERVAL`| How often one receives or pay for entries| String of either MONTH or YEAR. The string is not case-sensitive.   | `i/mOnTH`, `i/year`, `i/YEAR` |                                                            
| `e/END_DATE` | End date of the recurring period. One will not receive or pay for the recurring entry from this date.| Any of the [acceptable date formats](#dateFormat) from `2000-01-01` to `2200-12-31` that is after the `d/DATE`. If the end date is not specified, the default date set would be forever (`2200-12-31`) | `e/2016-02-13`, `e/2016-2-13`  |

---

<div style="page-break-after: always;"></div>

### <a name="dateFormat"></a>Acceptable date formats

Example: 5th Jaunary 2020

|Format | Example of input |
|--------|----------|
| yyyy-MM-dd | 2020-01-05|
| yyyy-M-dd  | 2020-1-05 | 
| yyyy-MM-d  | 2020-01-5 |
| yyyy-M-d | 2020-1-5 |
| dd-MM-yyyy | 05-01-2020 | 
| d-MM-yyyy | 5-01-2020 |
| d-M-yyyy | 5-1-2020 |
| dd-M-yyyy |05-1-2020 |
| dd MMM yyyy (for MMM, capitalise only the first letter, e.g. Jan) | 05 Jan 2020 |
| d MMM yyyy (for MMM, capitalise only the first letter, e.g. Jan) | 5 Jan 2020 |

Note:

- day must be between '1' to '31'.
- if user give a day that is not supposed to exist, day will be rounded down.
    - e.g. `2021-09-31` will be received as `2021-09-30` , as September only has 30 days.

<div style="page-break-after: always;"></div>

### <a name="categoryList"></a>Available categories

|Category tag | Expense Category | Income Category
|--------|----------|----------|
| c/0 | Food | Allowance
| c/1 | Entertainment | Wages
| c/2| Transportation | Salary
| c/3 | Household | Interest
| c/4 | Apparel | Investment
| c/5 | Beauty | Commission
| c/6 | Gift | Gift
| c/7 | Others | Others

---

<div style="page-break-after: always;"></div>

## <a name="features"></a>Features

---

### <a name="commandNote"></a> Notes about commands

- Items in square brackets are optional.

  e.g `n/NAME [d/DATE]` can be used as `n/burger d/2021-10-20` or as `n/burger`
- Parameters with tags or optional modifiers can be in any order.

  e.g. if the command specifies `n/NAME` `a/AMOUNT`, the order `a/AMOUNT` `n/NAME` is also acceptable.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `budget` and `exit`)
  will be ignored.

  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

- Extraneous parameters in between the command and the first tag for commands that take in tag parameters will not be
  considered if it is not valid.

  e.g. if the command specifies `add oh yummy food income n/wage`, it will be interpreted as `add income n/wage`.

  e.g. if the command specifies `delete hmm income a/15.0 c/1`, it will be interpreted as `delete a/15.0 c/1`, as
  `delete` is used for both expense and income.

---


<div style="page-break-after: always;"></div>

---

### <a name="recurringNote"></a> Notes about recurring entries

- Recurring entries will be recurred on the same day as the `DATE` if the interval is `MONTH`; same day and month if the
  interval is `YEAR`
- If the`END_DATE` of the recurring entry is before the next recurring date, it will not be recurred on that next
  recurring date.
- If the day is not available on some months or years, it will automatically be rounded down.

Examples:

- Netflix monthly subscription starts on `2021-09-21` and ends on `2030-03-20`.
    - `DATE` is `2021-03-21`; `INTERVAL` is `MONTH`; `END_DATE` is `2030-03-20`.
    - It will be billed on `2021-09-21`, `2021-10-21`, and so on until `2030-02-21`.

- Nintendo yearly subscription starts on `2020-02-29` and ends on `2023-01-15`.
    - `DATE` is `2020-02-29`;`INTERVAL` is `YEAR`; `END_DATE` is `2023-01-15`.
    - It will be billed on `2020-02-29`, `2021-02-28`, and `2022-02-28` only.
    - As February 29th is not available on non-Leap years, the date was rounded down to February 28th for those years.
    - As `2023-01-15` is before the next recurring date of `2023-02-28`, it will not be recurred on `2023-02-28`.

- The school starts to give monthly allowance on `2021-08-31` until `2023-08-31`.
    - `DATE` is `2021-08-31`; `INTERVAL` is `MONTH`; `END_DATE` is `2023-08-31`.
    - It will be received on `2021-08-31`, `2021-09-30`, and so on until `2023-08-31`.
    - As 31st is not available on September, the day was rounded down to 30th.

---

<div style="page-break-after: always;"></div>

## <a name="help"></a>Viewing help: `help`

Shows a list of possible user commands

Format: `help`

## <a name="add"></a>Adding entries: `add`

Adds an expense or income to your tracker

Format: `add [income] n/NAME a/AMOUNT [d/DATE] [c/CATEGORY_NUMBER]`

- Adds an entry of the specified `NAME`, `DATE`, `AMOUNT` and `CATEGORY_NUMBER`
- If `income` is included after `add`, entry will be an income entry, else it will be an expense entry.
- Refer to [acceptable tag formats](#tagFormat) for more information about tag definitions and formats.

Examples:

- Adding a textbook that costs $15: `add n/textbook a/15`
- Adding a cheeseburger that costs $4.20 that I had on 20th April 2021 and categorize it under "
  Food": `add n/Cheese Burger a/4.2 d/2021-04-20 c/0`
- Adding the income I made from sales, amounting to $34 `add income n/Sales a/34 d/2021-02-19 c/1`

Examples and Expected Output

```
add n/Textbook a/15
I've added: Expense  | OTHERS | 2021-10-28 | Textbook | $15.00
add n/Cheese burger a/4.2 d/2021-04-20 c/0
I've added: Expense  | FOOD | 2021-04-20 | Cheese burger | $4.20
add income n/Sales a/34 d/2021-02-19 c/1
I've added: Income  | WAGES | 2021-02-19 | Sales | $34.00
```

<div style="page-break-after: always;"></div>

## <a name="addR"></a>Adding Recurring Entries: `addR`

Adds an expense or income to your tracker

Format: `addR [income] n/NAME a/AMOUNT i/INTERVAL [d/DATE] [c/CATEGORY_NUMBER] [e/END_DATE]`

- Adds an entry of the specified `NAME`, `DATE`, `AMOUNT`, `INTERVAL`, `END_DATE` and `CATEGORY_NUMBER`
- If `income` is included after `add`, entry will be an income entry, else it will be an expense entry.
- Refer to [acceptable tag formats](#tagFormat) for more information about tag definitions and formats.

Examples:

- `addR a/90 d/2021-12-03 n/phone bills c/3 i/MONTH`
- `addR a/5 n/phone bills c/4 i/year e/2023-10-26`
- `addR income a/10000 n/salary d/2021-10-10 i/mOnTh`

Examples and Expected Output

```
addR a/90 d/2021-12-03 n/phone bills c/3 i/MONTH
I've added: Expense | HOUSEHOLD | 2021-12-03 | phone bills |-$90.00 | MONTH | Forever :D
addR a/5 n/phone bills c/4 i/year e/2023-10-26
I've added: Expense | APPAREL | 2021-10-29 | phone bills |-$5.00 | YEAR | 2023-10-26
addR income a/10000 n/salary d/2021-10-10 i/mOnTh
I've added: Income  | OTHERS | 2021-10-10 | salary | $10,000.00 | MONTH | Forever :D
```

<div style="page-break-after: always;"></div>

## <a name="view"></a>Viewing entries: `view`

Shows a list of all the entries, each with the associated `NAME`, `CATEGORY_NUMBER`, `DATE`, and `AMOUNT`
(`INTERVAL` and `END_DATE` also for recurring entries.)

Format: `view [income] [expense] [by SORTTYPE] [month MONTH] [year YEAR] [from STARTDATE [ENDDATE]] [up/ascending]`

- Views all entries with the specified `MONTH`, `YEAR`, from `STARTDATE` to `ENDDATE`, sorted by `SORTTYPE`.
- `[income](optional)` and `[expense](optional)` if appended, only shows the entries of the corresponding type.
- `SORTTYPE` can be any of the following types: `name`, `date`, `amount`, `category`
- `MONTH(optional)` can be any number from 1 to 12.
- If `MONTH` is not specified, the default will be the current month.
- `YEAR(optional)` can be any 4-digit number from 2000 to 2200.
- If `YEAR` is not specified, the default will be the current year.
- `STARTDATE(optional)` and `ENDDATE(optional)` can be any of the [acceptable date formats](#dateFormat).
- If `STARTDATE` is specified but `ENDDATE` is not specified, the default `ENDDATE` set would be the current date.
- `up(optional)` or `ascending(optional)` if appended with sort, will sort the list in ascending , else the default will
  sort the list in descending order.
- In addition to the normal entries, recurring entries will also be shown on the list.
    - Depending on the above options, recurring entries will be automatically added to the entries' list according to
      the recurring period.
    - Refer to [notes about recurring entries](#recurringNote) for more information on what criteria the automatic
      addition will be based on.
    - If no date options are specified correctly, it will default to viewing recurring entries up to current date.
    - There will be a separate list at the bottom to show the original recurring entries.
        - If neither date options nor `[expense]`/`[income]` options are specified, the separate list will show all
          recurring entries.
        - If `[expense]`/`[income]` options are specified but not date options, the separate list will show applicable
          recurring entries, where some may not have been added to the entries' list.
        - If date options are specified correctly, the separate list will only show recurring entries that were added to
          the entries' list.
    - For more information about why the `view` works this way, refer to [Frequently Asked Questions](#faq).

<div style="page-break-after: always;"></div>

Examples:
Assume today's date is `2021-11-06`

- `view`
- `view income`
- `view month 4 year 2021`
- `view from 2022-01-13 2022-03-15 by amount ascending`

Examples and Expected Output:

```
view
Here is the list of your entries:
  Type  |    Category    |    Date    |        Name         | Amount  | Every |   Until
Income  |      GIFT      | 2021-12-25 | Christmas allowance | $200.00 |       |
Expense | TRANSPORTATION | 2021-11-04 |        Taxi         |-$6.99   |       |
Income  |   ALLOWANCE    | 2021-10-31 |      Allowance      | $1.00   | MONTH | 2023-08-31
Expense | ENTERTAINMENT  | 2021-10-21 |       Netflix       |-$12.00  | MONTH | 2030-02-20
Expense | ENTERTAINMENT  | 2021-10-04 |        Movie        |-$22.44  |       |
Income  |   ALLOWANCE    | 2021-09-30 |      Allowance      | $1.00   | MONTH | 2023-08-31
Expense | ENTERTAINMENT  | 2021-09-21 |       Netflix       |-$12.00  | MONTH | 2030-02-20
Income  |   ALLOWANCE    | 2021-08-31 |      Allowance      | $1.00   | MONTH | 2023-08-31
Expense |      FOOD      | 2021-04-20 |    Cheese burger    |-$15.00  |       |
Expense |     OTHERS     | 2021-02-28 |      Nintendo       |-$19.99  | YEAR  | 2023-01-15
Expense |     OTHERS     | 2020-02-29 |      Nintendo       |-$19.99  | YEAR  | 2023-01-15
                                                 Net Total: | $94.59
Here is the list of all recurring entries, where some were added to the above list:
Expense |     OTHERS     | 2022-01-01 |      New year       |-$100.00 | YEAR  | Forever :D
Expense | ENTERTAINMENT  | 2021-09-21 |       Netflix       |-$12.00  | MONTH | 2030-02-20
Income  |   ALLOWANCE    | 2021-08-31 |      Allowance      | $1.00   | MONTH | 2023-08-31
Expense |     OTHERS     | 2020-02-29 |      Nintendo       |-$19.99  | YEAR  | 2023-01-15
```

```
view income
Here is the list of your entries:
  Type  | Category  |    Date    |        Name         | Amount  | Every |   Until
Income  |   GIFT    | 2021-12-25 | Christmas allowance | $200.00 |       |
Income  | ALLOWANCE | 2021-10-31 |      Allowance      | $1.00   | MONTH | 2023-08-31
Income  | ALLOWANCE | 2021-09-30 |      Allowance      | $1.00   | MONTH | 2023-08-31
Income  | ALLOWANCE | 2021-08-31 |      Allowance      | $1.00   | MONTH | 2023-08-31
                                            Net Total: | $203.00
Here is the list of applicable recurring entries, where some were added to the above list:
Income  | ALLOWANCE | 2021-08-31 |      Allowance      | $1.00   | MONTH | 2023-08-31
```

<div style="page-break-after: always;"></div>

```
view month 4 year 2021
For the year 2021:
For the month of APRIL:
Here is the list of your entries:
  Type  | Category |    Date    |     Name      | Amount | Every |   Until
Expense |   FOOD   | 2021-04-20 | Cheese burger |-$15.00 |       |
                                     Net Total: |-$15.00
Here is the list of recurring entries added to the above list:
```

```
view from 2022-01-13 2022-03-15 by amount ascending
Here is the list of your entries:
Since 2022-01-13 to 2022-03-15:
  Type  |   Category    |    Date    |   Name    | Amount | Every |   Until
Income  |   ALLOWANCE   | 2022-01-31 | Allowance | $1.00  | MONTH | 2023-08-31
Income  |   ALLOWANCE   | 2022-02-28 | Allowance | $1.00  | MONTH | 2023-08-31
Expense | ENTERTAINMENT | 2022-01-21 |  Netflix  |-$12.00 | MONTH | 2030-02-20
Expense | ENTERTAINMENT | 2022-02-21 |  Netflix  |-$12.00 | MONTH | 2030-02-20
Expense |    OTHERS     | 2022-02-28 | Nintendo  |-$19.99 | YEAR  | 2023-01-15
                                      Net Total: |-$41.99
Here is the list of recurring entries added to the above list:
Income  |   ALLOWANCE   | 2021-08-31 | Allowance | $1.00  | MONTH | 2023-08-31
Expense | ENTERTAINMENT | 2021-09-21 |  Netflix  |-$12.00 | MONTH | 2030-02-20
Expense |    OTHERS     | 2020-02-29 | Nintendo  |-$19.99 | YEAR  | 2023-01-15
```

<div style="page-break-after: always;"></div>

## <a name="delete"></a>Deleting an entry: `delete`

Deletes an existing entry.

Format: `delete [n/NAME] [d/DATE] [a/AMOUNT] [c/CATEGORY_NUMBER]`

- At least one of the optional fields must be provided.
- Our program searches the entry that matches the fields provided by the user.
    - If there is more than 1 `Expense` or `Income` matching the query, the program will return a list for the user to
      choose from. The user would then have to confirm the deletion of the entry.
    - If there is 1  `Expense` or `Income` matching the query, the program will prompt the user to confirm the deletion
      of that  `Expense` or `Income` .
- Deletes an entry of the specified `NAME`, `DATE`, `AMOUNT`, or `CATEGORY_NUMBER`
- Refer to [acceptable tag formats](#tagFormat) for more information about tag definitions and formats.
- Dummy strings between `delete` and the first tag will not affect the program.

Examples:

- `delete n/Textbook d/2012-09-21 a/15`
- `delete n/Cheese Burger d/2020-04-20 a/4.2`

Examples and Expected Output:

- If user query only matches 1 `Expense` or `Income` in the expense list

```
delete n/Textbook d/2012-09-21
Is this what you want to delete?
    Expense  | OTHERS | 2012-09-21 | Textbook | $40.00
Type "y" if yes. Type "n" if not.
y
I have deleted: Expense  | OTHERS | 2012-09-21 | Textbook | $40.00
```

<div style="page-break-after: always;"></div>

- If user query matches more than 1 `Expense` or `Income` in the list

```
delete n/Cheese Burger d/2020-04-20 a/4.2
Here is the list of items containing the keyword.
 Index |   Type  | Category |    Date    |     Name      | Amount | Every |   Until
   1   | Income  |  OTHERS  | 2020-04-20 | Cheese Burger |-$4.20  
   2   | Expense |  OTHERS  | 2020-04-20 | Cheese Burger |-$4.20  
Enter the index of the item you want to delete. To cancel, type "cancel"
1
I have deleted: Income  | OTHERS | 2020-04-20 | Cheese Burger | $4.20
```

<div style="page-break-after: always;"></div>

## <a name="deleteR"></a>Deleting a recurring entry: `deleteR`

Deletes an existing recurring entry.

Format: `deleteR [n/NAME] [d/DATE] [a/AMOUNT] [c/CATEGORY_NUMBER] [i/INTERVAL] [e/END_DATE]`

- At least one of the optional fields must be provided.
- Our program searches the entry that matches the fields provided by the user.
    - If there is more than 1 `RecurringExpense` or `RecurringIncome` matching the query,the program will return a list
      for the user to choose from. The user would then have to confirm the deletion of the entry.
    - If there is 1  `RecurringExpense` or `RecurringIncome` matching the query, the program will prompt the user to
      confirm the deletion of that  `Expense` or `Income` .
- Deletes an entry of the specified `NAME`, `DATE`, `AMOUNT`, or `CATEGORY_NUMBER`
- Refer to [acceptable tag formats](#tagFormat) for more information about tag definitions and formats.

Examples:

- `deleteR n/Netflix`
- `deleteR i/mOnTh`

Examples and Expected Output:

- If user query only matches 1 `RecurringExpense` or `RecurringIncome` in the expense list

```
deleteR n/Netflix
Is this what you want to delete?
    Expense | OTHERS | 2021-10-28 | Netflix |-$90.00 | YEAR | Forever :D
Type "y" if yes. Type "n" if not.
y
I have deleted: Expense | OTHERS | 2021-10-28 | Netflix |-$90.00 | YEAR | Forever :D
```

- If user query matches more than 1 `RecurringExpense` or `RecurringIncome` in the list

```
deleteR i/mOnTh
Here is the list of items containing the keyword.
 Index |   Type  | Category |    Date    |  Name   | Amount | Every |   Until
   1   | Expense |  OTHERS  | 2021-10-28 | Netflix |-$40.00 | MONTH | Forever :D
   2   | Expense |  OTHERS  | 2021-10-28 |   Viu   |-$30.00 | MONTH | Forever :D
Enter the index of the item you want to delete. To cancel, type "cancel"
1
I have deleted: Expense | OTHERS | 2021-10-28 | Netflix |-$40.00 | MONTH | Forever :D
```

<div style="page-break-after: always;"></div>

## <a name="deleteAll"></a>Deleting all entries: `deleteAll`

Deletes all existing entries.

Format: `deleteAll [normal] [recurring]`

- Deletes all normal and recurring entries in the list.
- `[normal](optional)` and `[recurring](optional)` if appended, only deletes all entries of the corresponding type.
- If no modifiers are specified, it defaults to deleting all entries regardless of type.
- `normal` and `recurring` can be substituted for `n` and `r` respectively as a shortcut.
- `deleteall` also accepted as a command.

Examples:

- `deleteAll`
- `deleteall normal`

Examples and Expected Output:

```
deleteAll
Are you sure you want to delete all entries?
Type "y" if yes. Type "n" if not.
y
All entries successfully deleted.
```

```
deleteall normal
Are you sure you want to delete all entries?
Type "y" if yes. Type "n" if not.
y
All entries successfully deleted.
```

<div style="page-break-after: always;"></div>

## <a name="edit"></a>Editing an entry: `edit`

Edits an existing entry

Format: `edit [n/NAME] [a/AMOUNT] [d/DATE] [c/CATEGORY_NUMBER]`

- At least one of the optional fields must be provided
- When editing fields, existing fields of the `Expense` or `Income` indicated by the user will be replaced.
- Our program searches the entry that matches the fields provided by the user.
    - If there is 1 `Expense` or `Income` matching the query, the program will prompt the user to confirm if they wish
      to edit that entry.
    - If there is more than 1 `Expense` or `Income` matching the query, the program will return a list for the user to
      choose from. The user would then have to confirm if they wish to edit the entry.
- Refer to [acceptable tag formats](#tagFormat) for more information about tag definitions and formats.

Examples:

- `edit n/Textbook d/2012-09-21 a/15`
- `edit n/Cheese Burger d/2020-04-20 a/4.2`

Examples and Expected Output:

- If user query only matches 1 `Expense` or `Income` in the expense list

```
edit n/Textbook d/2012-09-21 a/15
Is this what you want to edit?
    Expense  | OTHERS | 2012-09-21 | Textbook | $15.00
Type "y" if yes. Type "n" if not.
y
What would you like to edit?
a/14
Got it! I will update the fields accordingly!
```

<div style="page-break-after: always;"></div>

- If user query matches more than 1 `Expense` or `Income` in the list

```
edit n/Cheese Burger d/2020-04-20 a/4.2
Here is the list of items containing the keyword.
 Index |   Type  | Category |    Date    |     Name      | Amount | Every |   Until
   1   | Expense |  OTHERS  | 2020-04-20 | Cheese Burger |-$4.20  
   2   | Expense |  OTHERS  | 2020-04-20 | Cheese Burger |-$4.20  
Enter the index of the item you want to edit. To cancel, type "cancel"
1
What would you like to edit?
c/7
Got it! I will update the fields accordingly!
```

<div style="page-break-after: always;"></div>

## <a name="editR"></a>Editing a recurring entry: `editR`

Edits an existing recurring entry

Format: `editR [n/NAME] [d/DATE] [a/AMOUNT] [c/CATEGORY_NUMBER] [i/INTERVAL] [e/END_DATE]`

- At least one of the optional fields must be provided
- When editing fields, existing fields of the `Expense` or `Income` indicated by the user will be replaced.
- Our program searches the entry that matches the fields provided by the user.
    - If there is 1 `Expense` or `Income` matching the query, the program will prompt the user to confirm if they wish
      to edit that entry.
    - If there is more than 1 `Expense` or `Income` matching the query, the program will return a list for the user to
      choose from. The user would then have to confirm if they wish to edit the entry.
- Refer to [acceptable tag formats](#tagFormat) for more information about tag definitions and formats.

Examples:

- `editR n/Textbook d/2012-09-21 a/15`
- `editR n/Cheese Burger d/2020-04-20 a/4.2`

Examples and Expected Output:

- If user query only matches 1 `Expense` or `Income` in the expense list

```
editR n/Textbook d/2012-09-21 a/15
Is this what you want to edit?
    Expense | OTHERS | 2012-09-21 | Textbook |-$15.00 | MONTH | Forever :D
Type "y" if yes. Type "n" if not.
y
What would you like to edit?
n/NETFLIX
Got it! I will update the fields accordingly!
```

<div style="page-break-after: always;"></div>

- If user query matches more than 1 `Expense` or `Income` in the list

```
editR n/Textbook d/2012-09-21 a/15
Here is the list of items containing the keyword.
 Index |   Type  | Category |    Date    |   Name   | Amount | Every |   Until
   1   | Expense |  OTHERS  | 2012-09-21 | Textbook |-$15.00 | MONTH | Forever :D
   2   | Expense |  OTHERS  | 2012-09-21 | Textbook |-$15.00 | MONTH | Forever :D
Enter the index of the item you want to edit. To cancel, type "cancel"
1
What would you like to edit?
a/5
Got it! I will update the fields accordingly!
```

<div style="page-break-after: always;"></div>

## <a name="cat"></a>View available categories: `cat`

Shows a list of all available categories and its corresponding tag number.

Format: `cat`

Expected Output:

```
Here are the categories and its tag number
Expenses           | Income
c/0 FOOD           | c/0 ALLOWANCE
c/1 ENTERTAINMENT  | c/1 WAGES
c/2 TRANSPORTATION | c/2 SALARY
c/3 HOUSEHOLD      | c/3 INTERESTED
c/4 APPAREL        | c/4 INVESTMENT
c/5 BEAUTY         | c/5 COMMISSION
c/6 GIFT           | c/6 GIFT
c/7 OTHERS         | c/7 OTHERS
```

## <a name="set"></a>Setting budget: `set`

Set budget for individual categories>

Format: `set c/CATEGORY_NUMBER a/AMOUNT`

- `set` takes in 2 mandatory fields, `c/CATEGORY_NUMBER` and `a/AMOUNT`.
- `AMOUNT` is in dollars. Numbers after the decimal point are in cents. Eg. 4.50 is $4.50.
- `CATEGORY_NUMBER` is any integer from 0 to 7. Please refer to the [available categories](#categoryList).

Example: If you want to set budget for "FOOD" to $100, type `set c/0 a/100`, as `c/0` correspond to "FOOD".

Expected Output:

```
Budget for FOOD set to $100.00
```

<div style="page-break-after: always;"></div>

## <a name="budget"></a>View monthly budget: `budget`

View monthly spending and budget for current month

Format: `budget`

Expected Output:

```
Here is the budget for NOVEMBER 2021
   Category    | Amount | Budget  | Percentage
     FOOD      |  $5.00 / $100.00 | 5.00%
ENTERTAINMENT  |  $0.00 / Not set | 
TRANSPORTATION |  $0.00 / Not set | 
  HOUSEHOLD    |  $0.00 / Not set | 
   APPAREL     |  $0.00 / Not set | 
    BEAUTY     |  $0.00 / Not set | 
     GIFT      |  $0.00 / Not set | 
    OTHERS     | $23.50 / Not set | 
```

## <a name="exit"></a>Exit the program: `exit`

Exits the Mint program.

Format: `exit`

Expected Output:

```
Bye! Thanks for using Mint. See you soon :D
```

<div style="page-break-after: always;"></div>

## <a name="Commannd Summary"></a>Command Summary

Please refresh page if table is not rendered properly.

| Command | Format,Examples |
| -----| -----|
|add |`add` `[income]` `n/NAME` `a/AMOUNT` `[d/DATE]` `[c/CATEGORY_NUMBER]` <br> e.g. `add n/burger a/5 d/2021-10-20 c/0` | 
|addR | `addR` `[income]` `n/NAME a/AMOUNT` `[d/DATE]` `[c/CATEGORY_NUMBER]` `i/INTERVAL` `[e/END_DATE]` <br> e.g. `addR a/90 d/2021-12-03 n/phone bills c/3 i/MONTH` |
|edit |`edit` `[n/NAME]` `[a/AMOUNT]` `[d/DATE]` `[c/CATEGORY_NUMBER]`<br>  e.g. `edit n/burger a/5 d/2021-10-20 c/0` | 
|editR |`editR` `[n/NAME]` `[d/DATE]` `[a/AMOUNT]` `[c/CATEGORY_NUMBER]` `[i/INTERVAL]` `[e/END_DATE]` <br> e.g.`editR n/Textbook d/2012-09-21 a/15` | 
|delete |`delete` `[n/NAME]` `[a/AMOUNT]` `[d/DATE]` `[c/CATEGORY_NUMBER]` <br> e.g. `delete n/Cheese Burger d/20-10-2021 a/4.2` | 
|deleteR |`deleteR [n/NAME] [d/DATE] [a/AMOUNT] [c/CATEGORY_NUMBER] [i/INTERVAL] [e/END_DATE]`<br>  e.g. `deleteR n/Netflix` | 
|view |`view` `[income]` `[expense]` `[by SORTTYPE]` `[month MONTH]` `[year YEAR]` `[from STARTDATE [ENDDATE]]` `[up/ascending]` <br> e.g. `view from 2021-03-25 2022-01-02 by amount ascending`|
|view categories | `cat` | 
|set | `set` `c/CATEGORY_NUMBER` `a/AMOUNT`| 
|budget | `budget` |
|deleteAll | `deleteAll` `[normal]` `[recurring]` |
|help | `help` | 
|exit | `exit` |

<div style="page-break-after: always;"></div>

## <a name="faq"></a>Frequently Asked Questions

- Why are users able to record `AMOUNT` as "$0"?
    - This is to allow users to keep a record of entries that are free after discounts or vouchers.

- Why are users allowed to make recurring entries' `END_DATE` shorter than the `INTERVAL` (e.g., set monthly `Expense`'s
  `DATE` as `2021-11-04` and `END_DATE` as `2021-11-15`)?
    - This is so that users can use the `END_DATE` as a reminder to themselves to cancel subscriptions or be aware of
      the end of receiving fixed incomes.

- Why does simply entering `view` without date options show all the normal entries for recurring entries only up to
  today's date in the entries' list?
    - This is because if users do not specify the `END_DATE`, default is set to `2200-12-31`(forever). Thus, if we show
      all the recurring entries that are added, recurring entries with `END_DATE` set as forever will show all recurring
      until `2200-12-31` which would hinder the users from viewing important, recent entries.

- Why does simply entering `view` show recurring entries that are not added to the entries' list still shown in the
  separate list, but specifying filter options like `view month 12` only show entries that are added to the entries'
  list in the separate list?
    - This is because users may have recurring entries that start in the future. Only entering `view` without filter
      options show recurring entries up to today's date. Thus, if only the entries that are added to the entries' list
      are shown in the separate list, recurring entries that have not started yet would never be shown in the separate
      list, and users will not be able to see those that start in the future unless they specify to view those
      dates. `view` is meant to be a simple tool to view all entries, including recurring entries.

![](images/FAQrecurring.PNG)

This image explains what the entries' list and separate list indicate.
  
