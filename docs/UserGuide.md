# User Guide

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
* Words in UPPER_CASE are the parameters to be supplied by the user. <br />
e.g. in `add -b a/AMOUNT m/MONTH`, `AMOUNT` and `MONTH` are parameters which can be used as `add -b a/500 m/12`.
NOTE: **These parameters cannot be left empty.**
* Words encased in `<>` brackets are optional parameters.

<br />

# <a id="commands"></a> Commands

# <a id="add"></a> &nbsp;&nbsp; `add`

The command word `add` adds a record of either Budget, Expenditure, or Loan to the record list. 
The type of record is determined by the identifier tag after the `add` command word.

______________
<br />

### <a id="add-budget"></a> `-b` : Add a Budget

Adds a new budget to a specific month.

Format: `add -b a/AMOUNT m/<MONTH>`

* The `AMOUNT` can be entered with 2 decimal places or without decimal places.
* The `MONTH` must strictly be within the range of 1 to 12.

Example of usage:

`add -b a/500 m/12`

Expected outcome: Budget of $500.00 is added to the month of December of that particular year.

```
========================================================
Your budget of 500.0 for December is successfully added!
========================================================
```
<br />

________________________

### <a id="add-expenditure"></a> `-e` : Add an expenditure

Adds a new expenditure to a month.

Format: `add -e n/DESCRIPTION a/AMOUNT d/<DATE_OF_EXPENDITURE> c/<CATEGORY>`

* The `DESCRIPTION` can be in a natural language format.
  * NOTE: **Description has a 30characters limit**
* The `AMOUNT` entered can be up to 2 decimal places and cannot be empty.
* The `<DATE_OF_EXPENDITURE>` must strictly be in the form of _YYYY-MM-DD_. 
  * If left empty, the current date according to the
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
Category: GENERAL
========================================================
```

<br />

_________________________________________________________


### <a id="add-loan"></a> `-l` : Add a Loan

Adds a new loan to a month.

Format: `add -e n/<BORROWER_NAME> a/AMOUNT d/<DATE_OF_EXPENDITURE>`

* `AMOUNT` entered can be up to 2 decimal places and cannot be empty.
* `<DATE_OF_EXPENDITURE>` must strictly be in the form of _YYYY-MM-DD_. If left empty, the current date according to the
  system will be entered by default.

Example of usage:

`add -l n/Yap Wei Xuan a/500 d/2021-08-20`

Expected outcome:

```
========================================================
Loan successfully added!
Borrower Name: Yap Wei Xuan
Amount: $500.00
Date: 2021-08-20
========================================================
```

<br />

_________________________________________________________

<br/>

# <a id="edit"></a> &nbsp;&nbsp; `edit` 

Edits a budget or expenditure entry.
<br />

_________________________________________________________


### Edit Budget: `edit`  

Edit the amount of budget allowance for a particular month.

Format: `edit -b m/MONTH a/AMOUNT`

* The `AMOUNT` can be entered with 2 decimal places or without decimal places and cannot be empty.
* The `MONTH` must strictly be within the range of 1 to 12 and cannot be empty.

Example of usage:

`edit -b m/10 a/10000`

Expected outcome: A message will be shown to alert the user that the budget for october have been changed 
to $10000.

```
========================================================

========================================================
```
<br />

_________________________________________________________


### Edit Expenditure: `edit` 

Edit the amount of budget allowance for a particular month.

Format: `edit -e m/MONTH i/INDEX a/AMOUNT d/<DATE_OF_EXPENDITURE> n/DESCRIPTION`

* The `MONTH` must strictly be within the range of 1 to 12 and cannot be empty.
* The `INDEX` must strictly be within the range of the total number of expenditure for that particular month.
* The `AMOUNT` can be entered with 2 decimal places or without decimal places and cannot be empty.
* The `<DATE_OF_EXPENDITURE>` must strictly be in the form of _YYYY-MM-DD_. If left empty, the current 
date according to the system will be entered by default.

Example of usage:

`edit -e m/10 i/2 a/1000 d/2021-10-12 n/Chicken Rice`

Expected outcome: A message will be shown to alert the user that the expenditure number 2 for 12 october 2021
have been changed to $1000 with description of Chicken Rice.

```
========================================================

========================================================
```
<br />

_________________________________________________________


### Edit Loan: `edit` 
Edits the loan for a particular month.

Format: `edit -l m/MONTH i/INDEX <a/AMOUNT> <d/DATE_OF_LOAN> <n/BORROWER_NAME>`

* `MONTH` must strictly be within the range of 1 to 12 and cannot be empty.
* `INDEX` must strictly be within the range of the total number of expenditure for that particular month, and cannot be empty.
* `AMOUNT, DATE_OF_LOAN, BORROWER_NAME` are optional. If not specified, the values will not be changed for that specific parameter. However, at least one of these must be present.
* `AMOUNT` if entered, can be entered with 2 decimal places.
* `DATE_OF_EXPENDITURE` if entered, must strictly be in the form of _YYYY-MM-DD_.

Example of usage:

`edit -l m/10 i/2 a/1000 d/2021-10-12 n/Wei Hng`

Expected outcome:

```
========================================================
Loan has been successfully edited!
New values: Wei Hng         | $1000.0       | 2021-10-12       
========================================================
```
<br />

_________________________________________________________

<br />

# &nbsp;&nbsp; `year`

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


#  &nbsp;&nbsp;`find`

### Finding a Particular Expenditure/Budget: `find`

<br />

_________________________________________________________

<br/>

# &nbsp;&nbsp;`list`

<br />

_________________________________________________________

### Listing all Budget & Expenditure: `list`

List all the Budget and expenditure for that particular year.

Format: `list m/all [c/CATEGORY]`

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

# &nbsp;&nbsp; `delete`

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

Deletes (an) expenditure(s) of a specific month

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
Successfully deleted Expenditure 3.chicken rice3          | $5.0               | 2021-10-21       
Successfully deleted Expenditure 4.chicken rice4          | $5.0               | 2021-10-21       
Successfully deleted Expenditure 5.chicken rice5          | $5.0               | 2021-10-21     
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

 `delete -l m/10 i/3-5`

Expected outcome:
```
========================================================
Successfully deleted Loan 3.Wei Xuan               | $1000.0            | 2021-10-24       
Successfully deleted Loan 4.Luoyuang               | $1000.0            | 2021-10-24       
Successfully deleted Loan 5.Yixuan                 | $1000.0            | 2021-10-24      
========================================================
```
<br />

_________________________________________________________

<br />

# &nbsp;&nbsp; `stat`

The command word `stat` display some statistics graphs and paramters about the expenditure for 
the year or month.

_________________________________________________________
<br />


### `-e` : View Statistics for the Month by categories


Display the statistics for a particular month's budget and expenditure by categories. 

Format: `stat -c m/MONTH`

* The `MONTH` must strictly be within the range of 1 to 12 and cannot be empty.

Example of usage:

`stat -c m/10`

Expected outcome: A graph showing the percentages which each category contributes to the overall monthly
expenditures.

```
========================================================
               10%   20%   30%   40%   50%   60%   70%   80%   90%   100% 
GENERAL                                                                    0.85%
CLOTHES       ###                                                          3.1%
FOOD          ################################################             79.51%
ENTERTAINMENT                                                              0%
GIFTS         ###                                                          4.64%
HEALTH                                                                     0%
TECH          ######                                                       11.9%
ALL                                                                        0%
The category you spent the most on is: FOOD
The amount you spent on this category is: $5136.5
========================================================
```
<br />

_________________________________________________________



### `-y` : View Statistics for the Year

Display the statistics for a particular the current database year which the user is working on. 

Format: `stat -y t/TYPE_OF_GRAPHICAL_VIEW`

* The `TYPE_OF_GRAPHICAL_VIEW` is ... 

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

<br />

_________________________________________________________

<br />

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
 no. | `Command` | `Description` 
 --- | -------- | -------------- 
 1. | `add -b a/<AMOUNT> m/<MONTH>` | `add budget of $AMOUNT to MONTH of the year` 
 2. | `add -e c/<DESCRIPTION> a/<AMOUNT> d/<DATE_OF_EXPENDITURE> [c/<CATEGORY>]` | `add expenditure with DESCRIPTION of CATEGORY which cost $<AMOUNT> on <DATE>` 
 3. | `add -l n/<NAME_OF_LOAN_BORROWER> a/<AMOUNT> d/<DUE_DATE_OF_LOAN>` | `add a loan of $AMOUNT borrowed by NAME_OF_LOAN_BORROWER due on <DUE_DATE_OF_LOAN>` 
 4. | `edit -b m/<MONTH> a/<AMOUNT>` | `edit the MONTH budget to AMOUNT` 
 5. | `edit -e m/<MONTH> i/<INDEX> a/<AMOUNT> d/<DATE_OF_EXPENDITURE> n/<DESCRIPTION>` | `edit exependiture of MONTH and INDEX to AMOUNT, DATE_OF_EXPENDITURE and DESCRIPTION` |
 6. | `edit -l m/<MONTH> i/<INDEX> a/<AMOUNT> d/<DUE_DATE_OF_LOAN> n/<BORROWER_NAME>` | `edit the loan of MONTH and INDEX to AMOUNT, DUE_DATE_OF_LOAN and BORROWER_NAME ` 
 7. | `year <year>` | `switch database to YEAR` 
 8. | `find <keyword>` | `find a particular KEYWORD in the database` 
 9. | `list m/all [c/<CATEGORY]` | `list all budget, expenditure and loan entries of the year of CATEGORY` 
 10. | `list m/MONTH [c/<CATEGORY]` | `list MONTH budget, expenditure and loan entries of CATEGORY` 
 11. | `delete -b m/<MONTH>` | `delete the budget entry of MONTH` 
 12. | `delete -e m/MONTH` | `delete all expenditure entries of MONTH` 
 13. | `delete -e  m/MONTH i/INDEX` | `delete a particular expenditure of INDEX from MONTH` 
 14. | `delete -e m/MONTH/ i/INDEX_FROM-INDEX_TO` | `delete all the expenditure of MONTH of INDEX_FROM-INDEX_TO` 
 15. | `delete -l m/MONTH` | `delete all loan entries of MONTH` 
 16. | `delete -l m/MONTH i/INDEX` | `delete a particular loan of INDEX from MONTH` 
 17. | `delete -l m/MONTH i/INDEX_FROM-INDEX_TO` | `delete all the loan of MONTH of INDEX_FROM-INDEX_TO` ` 
 18. | `stat -e m/MONTH` | `show a particualr MONTH statistics breakdown` 
 19. | `stat -l t/<TYPE>` | `show overall statistics of TYPE for the entire year` 
 20. | `help` | `shows a list of command guides` 
 21. | `bye` | `terminates the app` 


