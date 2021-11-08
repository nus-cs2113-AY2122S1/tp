# User Guide

Budget Tracker is a desktop app for managing expenses, budget and loans, optimized for use via a Command Line Interface (CLI) 
for tech-savvy students who have trouble keeping track of their expenses.


## Table of Content
* [Intro](#introduction)
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
  * [Delete](#delete)
    * [Budget: `-b`](#delete-budget)
    * [Expenditure: `-e`](#delete-expenditure)
    * [Loan: `-l`](#delete-loan)
  * [Stat](#stat) 
    * [Month: `-c`](#stat-budget)
    * [Year: `-l`](#stat-year)
  * [Guides](#guides)
    * [Help](#help)
  * [DB](#db)
    * [DB](#d-b)
  * [Exit](#exit)
    * [Bye](#bye)
* [FAQ](#faq)
* [Command summary](#command-summary)

## <a id="introduction"></a> Introduction

Budget Tracker is a desktop app for managing expenses, budget and loans, optimized for use via a 
Command Line Interface (CLI) for tech-savvy students who have trouble keeping track of their expenses.

## <a id="getting-started"></a> Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Budget Tracker` from [here]().

## <a id="features"></a>Features

1. Storage
<br/>  Allows saving and loading of budget and loan data.
	
2. Statistics

3. Loan Tracker

4. Loan Reminder
<br/>  Every time when user starts the app, it reminds user of the loans that are due under the welcome screen.

5. Budget Tracker

6. Expenditure Categories

## Notes about the command format:
* Words encased in `<>` brackets are optional parameters.
* Command words (i.e. `add`, `edit`, etc.) must strictly be in **lower case**.
* Words in UPPER_CASE are the parameters to be supplied by the user. <br />
e.g. in `add -b a/AMOUNT m/MONTH`, `AMOUNT` and `MONTH` are parameters which can be used as `add -b a/500 m/12`.
NOTE: **These parameters cannot be left empty.**


<br />

# <a id="commands"></a> Commands

# <a id="add"></a> &nbsp; &nbsp;Add: `add`

The command word `add` adds a record of either Budget, Expenditure, or Loan to the record list. 
The type of record is determined by the identifier tag after the `add` command word.

______________
<br />

### <a id="add-budget"></a> `-b` : Adds a Budget

Sets a new budget for a specific month.

Format: `add -b a/AMOUNT <m/MONTH>`

* The `AMOUNT` can be entered with 2 decimal places or without decimal places.
  * Note: `Amount` will be rounded up to 2dp (for budget, expenditure, and loan).
  * E.g. $1.006 will be rounded up to $1.01.
* The `MONTH` must strictly be within the range of 1 to 12.

Example of usage:

`add -b a/500 m/12`

Expected outcome: Budget of $500.00 is added to the month of December of that particular year.

```
========================================================
Your budget of $500.00 for December is successfully set!
========================================================
```
<br />

________________________

### <a id="add-expenditure"></a> `-e` : Adds an expenditure

Records the details of a new expenditure including the _description_, the _amount spent_, the _date on which expenditure was made_ and the _category which the expenditure falls under_ .

Format: `add -e n/DESCRIPTION a/AMOUNT <d/DATE_OF_EXPENDITURE> <c/CATEGORY>`

* The `DESCRIPTION` can be in a natural language format.
  * NOTE: **If Description column exceeds 30characters limit, it will be truncated during list view**
* The `AMOUNT` entered can be specified up to 2 decimal places.
  * NOTE: `Amount` **cannot exceed 1billion (1000000000)**
* The `<DATE_OF_EXPENDITURE>` must strictly be in the form of _YYYY-MM-DD_. 
  * If left empty, the current date according to the
  system will be entered by default.
  * NOTE: **YYYY value should correspond to the current storage file year**
* The `<CATEGORY>` must be one of the following values:
  * _GENERAL, CLOTHES, FOOD, ENTERTAINMENT, GIFTS, HEALTH_
  * If left empty, the default value will be _GENERAL_

Example of usage:

`add -e n/CS2113T Textbooks a/60 d/2021-08-20 c/GENERAL`

Expected outcome: The buying of CS2113T textbooks, costing $60.00, on the 20th of August 2021 has been successfully added.

```
========================================================
Expenditure successfully added!
Description: CS2113T Textbooks
Amount: $60.00
Date: 2021-08-20
Category: GENERAL
========================================================
```

<br />

_________________________________________________________


### <a id="add-loan"></a> `-l` : Adds a Loan

Records down a loan record specifying when and who the money was lent out to.

Format: `add -e n/BORROWER_NAME a/AMOUNT <d/DATE_OF_EXPENDITURE>`

* `AMOUNT` entered will be rounded to the nearest 2 decimal places and cannot exceed 1 billion dollars.
* `<DATE_OF_EXPENDITURE>` must strictly be in the form of _YYYY-MM-DD_.
    * If left empty, the current date according to the
  system will be entered by default.
    * Note: **YYYY value should correspond to the current storage file year, please use `year`
      command to switch to the correct year before entering an expenditure of another year**

Example of usage:

`add -l n/Yap Wei Xuan a/500 d/2021-08-20`

Expected outcome:

```
========================================================
Loan successfully added!
Yap Wei Xuan owes you: $500.00
Date of loan: 2021-08-20
========================================================
```

<br />

_________________________________________________________

<br/>

# <a id="edit"></a> &nbsp;&nbsp; Edit: `edit` 

Edits a budget or expenditure entry.
<br />

_________________________________________________________


### `-b` : Edit a Budget

Edit the _amount_ of budget allowance for a particular month.

Format: `edit -b m/MONTH a/AMOUNT`

* `AMOUNT` can be entered with 2 decimal places or without decimal places and cannot be empty.
* `MONTH` must strictly be within the range of 1 to 12 and cannot be empty.

Example of usage:

`edit -b m/10 a/100020`

Expected outcome: A message will be shown to alert the user that the budget for october have been changed 
to $100020.00.

```
========================================================
Budget has been successfully edited!
New values: 
Amount: $100020.00
Month: 10
========================================================
```
<br />

_________________________________________________________


### `-e` : Edit an Expenditure 

Edits the values of an expenditure, including its _description_, _amount_, _date of expenditure_ and _category_.

Format: `edit -e m/MONTH i/INDEX <n/DESCRIPTION> <a/AMOUNT> <d/DATE_OF_EXPENDITURE> <c/CATEGORY>`
* `<AMOUNT>`,`<DATE_OF_EXPENDITURE>`,`<DESCRIPTION>` and `<CATEGORY>` are optional. Any parameters entered will be edited, while those not entered will remain the same. At least **one** of these 
    parameters must exist.


* `<DESCRIPTION>` can be in a natural language format.
    * Note: **If `<DESCRIPTION>` exceeds 30 characters limit, it will be truncated during list view.**
* `<AMOUNT>` entered will be rounded to the nearest 2 decimal places and cannot exceed 1 billion dollars.
* `<DATE_OF_EXPENDITURE>` must strictly be in the form of _YYYY-MM-DD_.
    * Note: **YYYY value should correspond to the current storage file year, please use `year`
      command to switch to the correct year before entering an expenditure of another year**
* `<CATEGORY>` must be one of the following values:
    * _GENERAL, CLOTHES, FOOD, ENTERTAINMENT, GIFTS, HEALTH, TECH_

Example expenditure:

`DESCRIPTION` as _Fish n Chips_, `AMOUNT` as _$5.00_, `DATE_OF_EXPENDITURE` as _2021-10-13_ and `CATEGORY` as _Food_ with _$500_ budget.

Example usage on example expenditure:

`edit -e m/10 i/1 n/Chicken Rice a/4 d/2021-10-12`

Expected outcome:

The previously set `CATEGORY` is unchanged, while the rest of the values are set to the provided values accordingly.

```
========================================================
edit -e m/10 i/1 n/Chicken Rice a/4 d/2021-10-12
========================================================
Expenditure has been successfully edited!
New values: 
Description: Chicken Rice
Amount: $4.00
Date: 2021-10-12
Category: FOOD
Total Amount Spent in October: $4.00
Percentage of Budget Left: 99.20%
========================================================
```
<br />

_________________________________________________________


### `-e` : Edit a Loan
Edits the loan for a particular month.

Format: `edit -l m/MONTH i/INDEX <a/AMOUNT> <d/DATE_OF_LOAN> <n/BORROWER_NAME>`

* `<AMOUNT>`,`<DATE_OF_LOAN>` and `<BORROWER_NAME>` are optional. Any parameters entered will be edited, while those not entered will remain the same. At least **one** of these
  parameters must exist.


* `<BORROWER_NAME>` can be in a natural language format.
    * Note: **If `<BORROWER_NAME>` exceeds 30 characters limit, it will be truncated during list view.**
* `<AMOUNT>` entered will be rounded to the nearest 2 decimal places and cannot exceed 1 billion dollars.
* `<DATE_OF_LOAN>` must strictly be in the form of _YYYY-MM-DD_.
    * Note: **YYYY value should correspond to the current storage file year, please use `year`
      command to switch to the correct year before entering an expenditure of another year**

Example Loan:

`BORROWER_NAME` as _Jon_, `AMOUNT` as _$4000_ and `DATE_OF_LOAN` as _2021-11-05_

Example usage on example loan:

`edit -l m/11 i/1 a/5000`

Expected outcome:
The previously set `BORROWER_NAME` and `DATE_OF_LOAN` is unchanged, while `AMOUNT` is set to the provided value accordingly.

```
========================================================
Loan has been successfully edited!
New values: 
Debtor: Jon
Amount: $5000.00
Date: 2021-11-05
========================================================
```
<br />

_________________________________________________________

<br />

# &nbsp;&nbsp; Year: `year`

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
<br />

_________________________________________________________


#  &nbsp;&nbsp; Find: `find`

### Finding a Particular Expenditure, Loan and Budget: `find`

Find all the records of Expenditure, loan and Budget that have a matching key word on the search term. 
The search term is the keyword.

Format: `find KEYWORD`

* The needs to be a white space between find and the KEYWORD.

Example of usage:

`find Chicken`

Expected outcome: All records that contains the "Chicken" keyword will be returned.

```
find Chicken
========================================================
Here are the Expenditure and Loan matches we have found!
/////////////////////////////////////////////////////////
No Expenditures found for this month
--------------------------------------------------------
No Loan found for this month
========================================================
No Expenditures found for this month
--------------------------------------------------------
No Loan found for this month
========================================================
No Expenditures found for this month
--------------------------------------------------------
No Loan found for this month
========================================================
No Expenditures found for this month
--------------------------------------------------------
No Loan found for this month
========================================================
No Expenditures found for this month
--------------------------------------------------------
No Loan found for this month
========================================================
No Expenditures found for this month
--------------------------------------------------------
No Loan found for this month
========================================================
No Expenditures found for this month
--------------------------------------------------------
No Loan found for this month
========================================================
No Expenditures found for this month
--------------------------------------------------------
No Loan found for this month
========================================================
No Expenditures found for this month
--------------------------------------------------------
No Loan found for this month
========================================================
No Expenditures found for this month
--------------------------------------------------------
No Loan found for this month
========================================================
Here are the Expenditures we found!
/////////////////////////////////////////////////////////
1.Chicken                      | $100.00            | 2021-11-08         | GENERAL           
--------------------------------------------------------
No Loan found for this month
========================================================
No Expenditures found for this month
--------------------------------------------------------
No Loan found for this month
========================================================
```

<br />

_________________________________________________________

<br/>

# &nbsp;&nbsp;List: `list`

<br />

_________________________________________________________

### Listing all Budget & Expenditure

List all the Budget and expenditure for that particular year.

Format: `list m/all [c/CATEGORY]`
* `CATEGORY` filters out the expenditures to show only those falling under the specified category.
  * Note: If `CATEGORY` is left empty, expenditures of all categories will be shown

Example of usage:

`list m/all`

Expected outcome: Listing all the budget and expenditure for the particular year.

```
========================================================
Your budget for January: Not Set
Your expenditures:
No Expenditure records yet.
Your loans: 
No Loan records yet. 
========================================================
Your budget for February: Not Set
Your expenditures:
No Expenditure records yet.
Your loans: 
No Loan records yet. 
========================================================
Your budget for March: Not Set
Your expenditures:
No Expenditure records yet.
Your loans: 
No Loan records yet.
========================================================
Your budget for April: Not Set
Your expenditures:
No Expenditure records yet.
Your loans: 
No Loan records yet.
========================================================
Your budget for May: Not Set
Your expenditures:
No Expenditure records yet.
Your loans: 
No Loan records yet.
========================================================
Your budget for June: Not Set
Your expenditures:
No Expenditure records yet.
Your loans: 
No Loan records yet.
========================================================
Your budget for July: Not Set
Your expenditures:
No Expenditure records yet.
Your loans: 
No Loan records yet.
========================================================
Your budget for August: Not Set
Your expenditures:
  Description            | Amount             | Date               | Category
1.CS2113T Textbooks      | $60.00             | 2021-08-20         | GENERAL
Your loans: 
No Loan records yet.
========================================================
Your budget for September: Not Set
Your expenditures:
  Description            | Amount             | Date               | Category
1.Chicken Rice           | $5.00              | 2021-09-13         | FOOD
Your loans: 
No Loan records yet.
========================================================
You did not overspend for October, Good JOB!
1.52% of your overall budget has been spent
Your budget for October: $330.00
Your expenditures:
  Description            | Amount             | Date               | Category
1.Chicken Rice           | $5.00              | 2021-10-13         | FOOD
Your loans: 
No Loan records yet.
========================================================
Your budget for November: Not Set
Your expenditures:
No Expenditure records yet.
Your loans: 
No Loan records yet.
========================================================
Your budget for December: $500.00
Your expenditures:
No Expenditure records yet.
Your loans: 
No Loan records yet.
========================================================
```
<br />

_________________________________________________________


### Listing Budget & Expenditure for a Particular Month: `list`

Adds a new expenditure to a specific budget of a month.

Format: `list m/MONTH [c/CATEGORY]`

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
<br />

_________________________________________________________

<br />

# &nbsp;&nbsp; Delete: `delete`

Deletes an entry / entries (maybe user wants to cut off the expenses) from the budget tracker data set.
<br />

_________________________________________________________

### `-b` - Delete a budget 

Deletes the budget of a specific month

Format: `delete -b m/MONTH`

* The `MONTH` represents the month of the budget will be deleted and cannot be empty.

Example of usage:

`delete -b m/10`

Expected outcome:
```
========================================================
Successfully deleted the Budget for this month!
Now the budget amount is 0.00!      
========================================================
```
<br />

_________________________________________________________


### `-e` - Delete (an) expenditure(s)

Deletes (an) expenditure(s) of a specific month.

Format:`delete -e m/MONTH` 
<br/> `delete -e  m/MONTH i/INDEX`
<br/> `delete -e m/MONTH/ i/INDEX_FROM-INDEX_TO `

* The `MONTH` represents the month of the expenditure(s) will be deleted and cannot be empty.
* The `INDEX` refers to the index number or a range of index numbers shown in the displayed entry list.
* The `INDEX` must be within the range of the list.
* If the `INDEX` is empty, then all the expenditures of this month will be deleted.

Examples of usage:

 `delete -e m/10 i/3-5`

Expected outcome:
```
========================================================
Successfully deleted Expenditure 3:
Description: breakfast
Amount: $100.00
Date: 2021-10-10
Category: GENERAL
========================================================
Successfully deleted Expenditure 4:
Description: lunch
Amount: $100.00
Date: 2021-10-10
Category: GENERAL
========================================================
Successfully deleted Expenditure 5:
Description: dinner
Amount: $100.00
Date: 2021-10-10
Category: GENERAL
========================================================
Total Amount Spent in October: $100.00
Percentage of Budget Left: 99.00%
========================================================
```
<br />

_________________________________________________________


### `-l` - Delete (a) loan record(s)

Deletes (a) loan record(s) of a specific month

Format:`delete -l m/MONTH` 
<br/> `delete -l m/MONTH i/INDEX`
<br/> `delete -l m/MONTH i/INDEX_FROM-INDEX_TO`

* The `MONTH` represents the month of the loan record(s) will be deleted and cannot be empty.
* The `INDEX` refers to the index number or a range of index numbers shown in the displayed entry list.
* The `INDEX` must be within the range of the list.
* If the `INDEX` is empty, then all the loan records of this month will be deleted.

Examples of usage:

 `delete -l m/10 i/1-2`

Expected outcome:
```
========================================================
Successfully deleted Loan 1:
Debtor: xinghan
Amount: $100.00
Date: 2021-10-10
========================================================
Successfully deleted Loan 2:
Debtor: kobe
Amount: $200.00
Date: 2021-10-03
========================================================
```
<br />

_________________________________________________________

<br />

# &nbsp;&nbsp; Stat: `stat`

The command word `stat` display some statistics graphs and information about the expenditure for 
the year or particular month.

_________________________________________________________
<br />


### `-c` : View Statistics for the Month by categories


Display the statistics for a particular month's budget and expenditure by categories. 

How it works:
 * The graph shows the percentage spent on each category relative to the overall spending that month.
 * The accuracy of the graph is represented by **3bars for every 5%** and is rounded up (e.g. 3% is rounded up to 5%).

Format: `stat -c m/MONTH`

* The `MONTH` must strictly be within the range of 1 to 12 and cannot be empty.

Example of usage:

`stat -c m/10`

Expected outcome: A graph showing the percentages which each category contributes to the overall monthly
expenditures.

```
========================================================
Here are the statistics for the month of October:
              0%    10%   20%   30%   40%   50%   60%   70%   80%   90%   100%  
GENERAL                                                                       0.85%
CLOTHES       ###                                                             3.1%
FOOD          ################################################                79.51%
ENTERTAINMENT                                                                 0%
GIFTS         ###                                                             4.64%
HEALTH                                                                        0%
TECH          ######                                                          11.9%
ALL                                                                           0%
Your budget: $500
The category you spent the most on: FOOD
The amount you spent on this category: $5136.50
========================================================
```
<br />

_________________________________________________________



### `-y` : View Statistics for the Year

Display the statistics for the current database year which the user is working on. 

Format: `stat -y t/TYPE_OF_GRAPHICAL_VIEW`

* The `TYPE_OF_GRAPHICAL_VIEW` can only be a value of 1 or 2. Type 1 is a bar graph of monthly percentage
  (12 bar graph) of budget used. Type 2 is overall yearly percentage (single bar graph) of budget used. 

Example of usage:

`stat -y t/1`

Expected outcome: A histogram of the percentage of money spend for each month of the year will be shown
with vertical axis showing percentage ranging from 0 to 100%.

```
========================================================
Percentage of Money Spent in 2021
     JAN FEB MAR APR MAY JUN JUL AUG SEP OCT NOV DEC 
100%                          #                      
90%                       #   #                      
80%                       #   #               #      
70%                       #   #               #      
60%                       #   #           #   #      
50%                       #   #       #   #   #      
40%                       #   #       #   #   #      
30%                       #   #       #   #   #      
20%           #   #       #   #       #   #   #      
10%           #   #       #   #       #   #   #     
========================================================
```

`stat -y t/2`

Expected outcome: An overall percentage bar graph showing the amount of money spent in that particular year and if the 
overall expenditure exceeds the budget available, an explosion will be shown on the graph.

```
========================================================
Percentage of Money Spent throughout the year

Percentage: 56.00%
100%                  .....
95%                   .....
90%                   .....
85%                   .....
80%                   .....
75%                   .....
70%                   .....
65%                   .....
60%                   .....
55%                   #####
50%                   #####
45%                   #####
40%                   #####
35%                   #####
30%                   #####
25%                   #####
20%                   #####
15%                   #####
10%                   #####
5%                    #####
========================================================
```

```
Percentage: 143.03%
              _.-^^---....,,---_
           _--                  --_
          <          Overspent!       >)
           \._                   _./
              ```--. . , ; .--'''
                    | |   |
                 .-=||  | |=-.
                 `-=#$%&%$#=-'
                    | ;  :|
           _____.,-#%&$@%#&#~,._____
100%                  #####
95%                   #####
90%                   #####
85%                   #####
80%                   #####
75%                   #####
70%                   #####
65%                   #####
60%                   #####
55%                   #####
50%                   #####
45%                   #####
40%                   #####
35%                   #####
30%                   #####
25%                   #####
20%                   #####
15%                   #####
10%                   #####
5%                    #####
========================================================
```

<br />

_________________________________________________________

<br />

## &nbsp;&nbsp; `help`
 
### Command Guides: `help`

Command description and format.

Format: `help`

* The format must be strictly as stated above.

Example of usage:

`help`

Expected outcome: Lists all available commands and their parameters.

```
1. add
Adds an expenditure record.
Parameters: -e n/EXPENDITURE_NAME a/COST d/[DATE_OF_EXPENDITURE] c/[CATEGORY]
Note:
 * If DATE_OF_EXPENDITURE is not specified, current system date will be the default value.
 * If CATEGORY is not specified, GENERAL will be the default category.

Adds a budget record.
Parameters: -b a/AMOUNT m/[MONTH]
Note:
 * If MONTH is not specified, current system month will be the default value.

Adds a loan record.
Parameters: -l n/BORROWER_NAME a/AMOUNT d/[DATE_OF_LOAN]
Note: 
 * If DATE_OF_LOAN is not specified, the current system date will be the default value.
========================================================
2. edit
Edits a budget record.
Parameters: -b m/MONTH a/AMOUNT
Note:
 * MONTH must be strictly within the range of 1 to 12. 

Edits an expenditure record.
Parameters: -e m/MONTH i/INDEX [a/AMOUNT] [d/DATE_OF_EXPENDITURE] [n/DESCRIPTION] [c/CATEGORY]
Note:
 * AMOUNT, DATE_OF_EXPENDITURE, DESCRIPTION and CATEGORY are optional but at least one must exist to edit.

Edits a loan record.
Parameters: -l m/MONTH i/INDEX [a/AMOUNT] [d/DATE_OF_LOAN] [n/BORROWER_NAME]
Note:
 * AMOUNT, DATE_OF_LOAN and BORROWER_NAME are optional, but at least one must exist to edit.

========================================================
3.find
Finds all expenditure and loan records with the specified keyword
Parameters: [KEYWORD]
========================================================
4. list
Lists all records
Parameters:  m/all [c/CATEGORY]

Lists records in a specified monthlist m/MONTH [c/CATEGORY]
========================================================
5. delete
Delete a budget record.
Parameters: -b m/MONTH

Delete all expenditure records.
Parameters: -e m/MONTH i/INDEX

Delete an expenditure record.
Parameters: -e i/INDEX_OF_EXPENDITURE m/MONTH
Note:
 * If INDEX is not specified, all the expenditure records of this month will be deleted.

Delete multiple expenditure records.
Parameters: -e m/MONTH i/START_INDEX - END_INDEX
Note:
 * If INDEX is not specified, all the expenditure records of this month will be deleted.

Delete all loan records.
Parameters: -l m/MONTH

Delete a loan record.
Parameters: -l m/MONTH [i/INDEX_OF_LOAN]
Note:
 * If INDEX_OF_LOAN is not specified, all the loan records of this month will be deleted.

Delete multiple loan records.
Parameters: -l m/MONTH i/START_INDEX - END_INDEX
Note:
 * If INDEX is not specified, all the loan records of this month will be deleted.
========================================================
6. stat
Display the statistics for a particular month’s budget and expenditure by categories.
Parameters: -c m/MONTH

Display the statistics for the current database year being worked on.
Parameters: -y t/[TYPE]
========================================================
7.help
Get all commands' information.
Parameters: help
========================================================
8.bye
Exits the app.
Parameters: bye
========================================================
```

<br />

_________________________________________________________

<br />

## &nbsp;&nbsp; `db`

### <a id="d-b"></a>  `db`

Displays all the available database. 

Format: `db`

* The format must be strictly as stated above.

Example of usage:

`db`

Expected outcome: Available databases will be shown.

```
========================================================
These are the data base you currently have!

2019.txt
2020.txt
2021.txt
========================================================
```

# &nbsp;&nbsp; `bye`

### Exiting the program: `bye`

# &nbsp;&nbsp; `bye`

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

**A**: Copy the data folder from the source computer to the destination computer on the same directory as the tp.jar file.

## Command Summary

<> - Parameters that must be stated in the command.

[<>] - Optional parameters that can be ommited.

 |no. | Command | Description |
 |--- | --------- | --------------------------------------- |
 |1.  | `add -b a/AMOUNT m/MONTH` | `add budget of $AMOUNT to MONTH of the year` |
 |2.  | `add -e c/DESCRIPTION a/AMOUNT d/DATE_OF_EXPENDITURE <c/CATEGORY>` | `add expenditure with DESCRIPTION of CATEGORY which cost $AMOUNT on DATE` |
 |3.  | `add -l n/NAME_OF_LOAN_BORROWER a/AMOUNT d/DUE_DATE_OF_LOAN` | `add a loan of $AMOUNT borrowed by NAME_OF_LOAN_BORROWER due on DUE_DATE_OF_LOAN` |
 |4.  | `edit -b m/MONTH a/AMOUNT` | `edit the MONTH budget to AMOUNT` |
 |5.  | `edit -e m/MONTH i/INDEX a/AMOUNT d/DATE_OF_EXPENDITURE n/DESCRIPTION` | `edit exependiture of MONTH and INDEX to AMOUNT, DATE_OF_EXPENDITURE and DESCRIPTION` |
 |6.  | `edit -l m/MONTH i/INDEX a/AMOUNT d/DUE_DATE_OF_LOAN n/BORROWER_NAME` | `edit the loan of MONTH and INDEX to AMOUNT, DUE_DATE_OF_LOAN and BORROWER_NAME ` |
 |7.  | `year year` | `switch database to YEAR` |
 |8.  | `find keyword` | `find a particular KEYWORD in the database` |
 |9.  | `list m/all <c/CATEGORY>` | `list all budget, expenditure and loan entries of the year of CATEGORY` |
 |10. | `list m/MONTH <c/CATEGORY>` | `list MONTH budget, expenditure and loan entries of CATEGORY` |
 |11. | `delete -b m/MONTH` | `delete the budget entry of MONTH` |
 |12. | `delete -e m/MONTH` | `delete all expenditure entries of MONTH` |
 |13. | `delete -e  m/MONTH i/INDEX` | `delete a particular expenditure of INDEX from MONTH` |
 |14. | `delete -e m/MONTH i/INDEX_FROM-INDEX_TO` | `delete all the expenditure of MONTH of INDEX_FROM-INDEX_TO` |
 |15. | `delete -l m/MONTH` | `delete all loan entries of MONTH` |
 |16. | `delete -l m/MONTH i/INDEX` | `delete a particular loan of INDEX from MONTH` |
 |17. | `delete -l m/MONTH i/INDEX_FROM-INDEX_TO` | `delete all the loan of MONTH of INDEX_FROM-INDEX_TO` |
 |18. | `stat -e m/MONTH` | `show a particualr MONTH statistics breakdown` |
 |18. | `stat -e m/MONTH` | `show a particualr MONTH statistics breakdown` |
 |19. | `stat -y t/TYPE` | `show overall statistics of TYPE for the entire year` | 
 |20. | `csv` | `save the current database into text file` | 
 |21. | `db` | `show all the available database in the user's device` | 
 |22. | `help` | `shows a list of command guides` |
 |23. | `bye` | `terminates the app` |


