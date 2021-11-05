# User Guide

## Introduction

PayMeBack is a CLI-based expense tracker for a group of friends travelling overseas together.

In such a group, it is easier for an individual to pay for expenses on behalf of the group rather than having each
individual pay for their share for every activity. At the end of the day, it is quite troublesome to
manually calculate how much money each individual owes to another. PayMeBack is designed to make this process
easy and fuss-free, by helping you calculate how much each person owes every other person.

<br />

## Table of Contents
* [Using this guide](#using-this-guide)
* [Quick Start](#quick-start)
* [Features](#features)
  * [Saving your data](#saving-your-data)
  * [Loading your saved data](#loading-your-saved-data)
  * [Trips](#trips)
    * [Create Trip](#--create-trip)
    * [Open Trip](#--open-trip)
    * [Close Trip](#--close-trip)
    * [List Trips](#--list-trips)
    * [View People in Trip](#--view-people-in-trip)
    * [Delete Trip](#--delete-trip)
  * [Expenses](#expenses)
    * [Create Expense](#--create-expense)
    * [List Expenses](#--list-expenses)
    * [View an Expense](#--view-an-expense)
    * [Filter Expenses by Attribute](#--filter-expenses-by-attribute)
    * [Delete Expense](#--delete-expense)
  * [Optimize Transactions](#optimize-transactions)
  * [Summary of Expenses](#summary-of-expenses)
* [FAQ](#faq)
* [Command Summary](#command-summary)


## Using this guide

- Text bounded by `this formatting` refers to elements displayed in your window as the program is running.
It can either be an input entered by the user, or an output displayed by the program.
- All entries enclosed in square brackets, "[" and "]" refer to user-defined inputs.
For example, input values displayed as `[something]` can be determined by the user, where `something` is the data
entered by the user.

<br />


## Quick Start
1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `PayMeBack` from [here](https://github.com/AY2122S1-CS2113T-T12-2/tp/releases), 
and move the downloaded file to your preferred folder.
3. Open any command-line application (such as Terminal, Command Prompt, or Powershell) and navigate to the folder
   containing your downloaded copy of `PayMeBack`.
4. In the command-line interface, type `java -jar PayMeBack.jar`.
5. If the program starts successfully, you should see the following on your screen:


```
Welcome to
    ____              __  ___     ____             __  
   / __ \____ ___  __/  |/  ___  / __ )____ ______/ /__
  / /_/ / __ `/ / / / /|_/ / _ \/ __  / __ `/ ___/ //_/
 / ____/ /_/ / /_/ / /  / /  __/ /_/ / /_/ / /__/ ,<   
/_/    \__,_/\__, /_/  /_/\___/_____/\__,_/\___/_/|_|  
            /____/                                     

Enter your command: 
```


## Features

### Saving your data

By default, the program attempts to save your data to a file named `trips.json` located in the same directory
as the app each time after you run a command. This includes the `quit` command.

If the save attempt is unsuccessful, you will see this error message:

```
Sorry, there was an error saving your data. We'll try to save your data again the next time you enter a command."
```

The program will then attempt to save again the next time you run a command.

<br />

### Loading your saved data

The first time you open the program, or if the save file was deleted or moved, the program will create a save file 
named `trips.json` for you.

The next time open the program, it will attempt to load your saved data from the save file. If the data is successfully 
loaded, you will see the following message:
```
Your saved data was successfully loaded!
```

If there is an error loading your save file, please go to the [FAQ](#faq)  for more information.

<br />

### Trips

#### - Create Trip

Creates a new Trip in the program.

Input syntax:
```
create /[location] /[date] /[foreign-currency-ISO-code] /[exchange-rate] /[persons-in-trip]
```

All fields are compulsory. Note the following:
- `[location]` is the location of the trip. Any string can be entered.
- `[date]` must follow the format of dd-mm-yyyy.
- `[foriegn-currency-ISO-code]`: Currently there are 30 currencies supported, the currencies' names and ISO codes are listed below.
  - The program is still runnable with unknown currencies, however the symbol and decimal place will not be accurate.
- `[exchange-rate]` should be how much 1 of your home currency costs in foreign currency.
  - Example: SGD $1 is equivalent to USD $0.74, hence the `exchange-rate` will be 0.74.
  - Note that the default home currency is SGD.
- `persons-in-trip` should be separated with commas.

#### Compatible Currencies

Currency Name | ISO Code
------------ | -----------
United States Dollar | USD
Singapore Dollar | SGD
Australian Dollar | AUD
Canadian Dollar | CAD
New Zealand Dollar | NZD
Euro | EUR
Pound Sterling| GBP
Malaysian Ringgit | MYR
Hong Kong Dollar | HKD
Thai Baht | THB
Russian Ruble | RUB
South African Rand | ZAR
Turkish Lira | TRY
Brazilian Real | BRL
Danish Krone | DKK
Polish Zloty | PLN
Israeli New Shekel | ILS
Saudi Riyal | SAR
Chinese Yuan | CNY
Japanese Yen | JPY
South Korean Won | KRW
Indonesian Rupiah | IDR
Indian Rupee | INR
Swiss Franc | CHF
Swedish Krona | SEK
Norwegian Krone | NOK
Mexican Peso | MXN
New Taiwan Dollar | TWD
Hungarian Forint | HUF
Czech Koruna | CZK
Chilean Peso | CLP
Philippine Peso | PHP
United Arab Emirates Dirham | AED
Colombian Peso | COP
Romanian Leu | RON

For example,

Input:

```
create /America /02-02-2021 /USD /0.74 /Ben, Jerry, Tom
```

If successful, the output will be as follows:

```
Your trip to America on 02 Feb 2021 has been successfully added!
```

<br />

#### - Open Trip

Opens a Trip, which allows the user to interact with other functionalities of our app, such as adding and viewing expenses.

Input syntax:
```
open [trip-number]
```

For example,

Input:

````
open 1
````
If successful, the output will be as follows:
```
You have opened the following trip:
America | 02 Feb 2021
```

You can also run the `open` command while a Trip is already open. This will close the currently-opened
Trip, and open the specified Trip in the most recent command.

*Note: Only one Trip can be open at any time.*

<br />

#### - Close Trip

Closes the current Trip you are in. This allows the user to list all trips or to delete a Trip.

This command can only be used if a Trip is open.

Input syntax:
```
close
```
For example,

Input:
````
close
````
If successful, the output will be as follows:
```
You have closed the following trip:
America | 02 Feb 2021
```

<br />

#### - List Trips
Lists all the Trips that you have created. 
This command can only be used if no Trip is open.

Note that if a Trip is open, this command will list down your expenses instead. 

Input syntax:
```
list
```
For example,

Input:
````
list
````
If successful, the output will be as follows:
```
List of Trips:
    1. London | 27 Oct 2021
```

<br />

#### - View People in Trip
Lists the persons involved in a particular Trip. 
This command can only be used if you have opened a Trip.

Input syntax:
```
people
```
For example,

Input:
````
people
````
If successful, the output will be as follows:
```
These are the people involved in this trip:
	Ben
	Jerry
	Tom
```

<br />

#### - Delete Trip

Deletes a Trip from the program.

Input syntax:
```
delete [trip-number]
```
- `[trip-number]` is the index of the Trip you wish to delete, which can be found by using `list` command while no Trip is open.
- `delete last` to delete last trip


For example,

Input:

````
delete 1
````
If successful, the output will be as follows:
```
Your trip to America on 02 Feb 2021 has been successfully removed.
```
<br />

#### - Edit Trip

Edit attributes of a Trip

Input syntax:
```
edit [trip num] [attribute] [new value]

attributes: -location, -date, -exchange rate, -forcur, -homecur
```
- The hyphen preceding an attribute is part of the syntax.
- `[trip-number]` is the index of the Trip you wish to edit, which can be found by using `list` command while no Trip is open.
- `last` can be used for `[trip num]`


For example,

Input:

````
delete 1
````
If successful, the output will be as follows:
```
Your trip to America on 02 Feb 2021 has been successfully removed.
```
<br />

### Expenses
#### - Create Expense
Creates a new expense entry for the current Trip. 

This command can only be used if you have opened a Trip.

Input syntax:
````  
expense [amount] [category] [people] /[description]
````
All fields are compulsory. Note the following:
- `[amount]` is the total amount spent on the expense.

- `[category]` is a category tag for the expense. Only 1 category per Expense is allowed.

- `[people]` denotes the people involved in the expense. Multiple people involved are to be separated by commas.
  - Entering `-all` will add every person in the trip to the expense

- `[description]` is the description of the expense.

For example,

Input:
````
expense 30 food Ben, Jerry /In-and-Out Burgers
````

PayMeBack will then ask you to enter the date of the expense:
````
Enter date of expense:
	Press enter to use today's date
````
- The date must follow the format of dd-mm-yyyy.
- Pressing the enter key without keying in anything will use the current date.
- If the expense only has one person involved, the steps below will be skipped and the expense will be automatically added.

PayMeBack will then ask for the name of the person who paid for the expense:
````
Who paid for the expense?: 
````
- The name entered has to be part of the expense or PayMeBack will request for the name again.

PayMeBack will then ask for the amounts spent by each person involved along
with how much of the amount has yet to be assigned:
````
Who paid for the expense?: Ben
Enter "equal" if expense is to be evenly split, enter individual spending otherwise
There is USD $30.00 left to be assigned. How much did Tom spend?: 
````
- The program will automatically cycle through every person involved in the expense.
- Entering `equal` when the program asks for the amount spent for the first person will cause the program to evenly split the expense among all the people involved in it.

If there is no amount remaining but there are still people left to be assigned, PayMeBack will prompt the user if they would like to assign 0 to the rest of the people involved in the expense:
````
There is USD $30.00 left to be assigned. How much did Ben spend?: 30
There will be people involved that don't need to pay, are you sure? (y/n): 
````
- Entering `y` will allow PayMeBack to assign 0 to the remaining people involved in the expense.
- Entering `n` will result in PayMeBack requesting for the name of the person who paid for the expense again, followed by the same sequence of steps.

PayMeBack will automatically prompt the user to assign the remaining amount when it requests for the amount spent of the last person in the expense:
````
There is USD $30.00 left to be assigned. How much did Ben spend?: 20
Assign the remaining USD $10.00 to Jerry? (y/n): 
````
- Entering `y` will allow PayMeBack to assign the amount to the persons stated.
- Entering `n` will result in PayMeBack requesting for the name of the person who paid for the expense again, followed by the same sequence of steps.

If successful, the output will be as follows:
````
Your expense has been added successfully
````

<br />

#### - List Expenses

List all expenses in the current opened trip.
This command can only be used if a trip is open.

Note that if a trip is not currently open, this command will list trips instead.

Input syntax:
````  
list
````

For example,

Input:
````
list
````

If successful, the output will be as follows:
````
List of Expenses: 
	1. In-and-Out Burgers | 03 Feb 2021
	2. Gift shopping at mall | 03 Feb 2021
	3. Dinner at Taco Bell | 03 Feb 2021
````

<br />


#### - View an Expense
Shows the details of a particular expense of a trip.

This command can only be used if a trip is open, and there is at least 1 expense.

Input syntax:
````
view [expense-number]
````
- Note that entering `view` without an index will print all expenses in the currently opened trip.
- `view last` to view expense

For example,

Input:
````
view 1
````
If successful, the output will be as follows:
````
	In-and-Out Burgers
	Date: 03 Feb 2021
	Amount Spent: USD $30.00
	People involved: 
		1) Ben, USD $20.00
		2) Jerry, USD $10.00
	Payer: Ben
	Category: food
````


<br />

#### - Filter Expenses By Attribute
Allows the user to view specific expenses based on an attribute of their choice.

This command can only be used if a trip is open, and there is at least 1 expense.

Input syntax:
````
view filter [expense-attribute] [search-keyword]
````
- `[expense-attribute]` can be either `[category]`, `[description]`, `[payer]`, `[person]` or `[date]`.

For example, if the user would like to search for all expenses in the category "food",

Input:
````
Enter your command: view filter category food
````
If successful, the output will be as follows:
````
1.  In-and-Out Burgers
    Date: 03 Feb 2021
    Amount Spent: USD $30.00
    People involved: 
        1) Ben, USD $20.00
        2) Jerry, USD $10.00
    Payer: Ben
    Category: food
	
3.  Dinner at Taco Bell
    Date: 03 Feb 2021
    Amount Spent: USD $20.00
    People involved:
        1) Ben, USD $8.00 
        2) Jerry, USD $6.00
        3) Tom, USD $6.00
    Payer: Tom
    Category: food
````


<br />

#### - Delete Expense

Deletes an expense from a trip.

This command can only be used if a trip is open, and there is at least 1 expense.

Input syntax:
```
delete [expense-number]
```
- `[expense-number]` is the index of the expense you wish to delete, 
and can be found by using `list` command while a Trip is open.
- `delete last` to delete last expense

For example,

Input:
````
delete 1
````
If successful, the output will be as follows:
```
Your expense of SGD 50.00 has been successfully removed.
```
<br />

### Settling Expenses
There are 2 commands that you can run to get a list of who pays who (WPW) to 
settle expenses. `amount` displays the WPW for 1 person, while `optimize` displays the WPW for everyone in 
the trip. We recommend you use `optimize` most of the time.

<br />

#### Amount
Shows the transactions that the input person have to make to ensure that everyone is being paid back. User needs to have opened a trip and have expenses to use the command. Note that the WPW list is not optimized, and you will encounter cases where the WPW list will list:

```
Adam needs to pay USD 10.00 (SGD 10.00) to Eve
Eve needs to pay USD 3.00 (SGD 3.00) to Adam
```
instead of
```
Adam needs to pay USD 7.00 (SGD 7.00) to Eve
```
<br />

Input syntax:
```
amount [person-in-trip]
```

For example,

Input:

```
amount Ben
```

If successful, the output will be as follows:

```
Ben spent SGD $3050.50 (SGD $3050.50) on the trip so far
Ben owes SGD $3000.00 (SGD $3000.00) to Jerry
Ben does not owe anything to Dick
Ben needs to pay USD 7.00 (SGD 7.00) to Eve
```
<br /><br />
#### Optimize Transactions

Shows the most optimized number of transactions to ensure that everyone is being paid back. User needs to have opened a trip and have expenses to use the command. 

Input syntax:

```
optimize
```

For example,

Input:

```
optimize
```

If successful, the output will be as follows:

```
Here is the optimized payment transactions:
yuzhao needs to pay USD 8.00 (SGD 8.00) to yikai
yuzhao needs to pay USD 13.00 (SGD 13.00) to qian
```

If no transactions are required, the user will see this message:

Output:
```
All are paid! :)
```
<br />

### Summary of Expenses
Shows an overall summary of individual expenses in a trip.

This command can only be used if you have opened a trip.

The input syntax is as follows:
````
summary [name]
````
- [name] is an optional argument.
- Note that entering `summary` without a name will print the summary of everybody in the opened trip.

For example,

Input:

```
summary
```

If successful, the output will be as follows:
```
Ben has spent USD $50.00 (SGD $67.57) on 3 expenses on the following categories:
food: USD $30.00 (SGD $40.54)
travel: USD $20.00 (SGD $27.03)

Jerry has spent USD $150.00 (SGD $202.71) on 6 expenses on the following categories:
food: USD $30.00 (SGD $40.54)
travel: USD $20.00 (SGD $27.03)
leisure: USD $100.00 (SGD $135.14)

Tom has spent USD $75.00 (SGD $101.35) on 3 expenses on the following categories:
food: USD $25.00 (SGD $33.78)
travel: USD $20.00 (SGD $27.03)
shopping: USD $30.00 (SGD $40.54)

```

### Help
Shows a quick help message, depending on which stage the user is at.

Input syntax:

``
help
``

For example,

Input:

```
help
```

Output for if no trip is open:
````
Type "open [trip number]" to open your trip
While a trip is open, type "expense" to create an expense for that trip
Type "quit" to exit
````

Output for if trip is open:
````
You are inside a trip. Trip specific commands:
	expense: creates an expense
	view: list all expenses
	view filter [options] [search keyword]: list filtered expenses.
		filter options: [category, description, payer, person, date]
	view [index]
	summary: shows how much each person spent in total for this trip
	amount [person]: for settling repayment at the end of the trip,shows how much this person owes to others, or how much others owe this person
	open [trip num]: open another trip
	quit: exit the program
````

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: To transfer your data to another device, simply copy over the "trips.json" file in the same directory as this app to the device you wish to use.

**Q**: There was an error loading my saved file.

**A**: Ensure that your saved file is in the same directory as the jar file, and that you did not modify the contents of the json file directly. Delete all json files, and run the app again, and re-enter your data.

## Command Summary
Hyphen before square brackets (eg `summary -[name]`) denotes optional arguments

### General commands
Action | Command syntax
---|---
Display help|`help`
Quit|`quit`

<br />

### Trip commands

Action | Command syntax | Example
---|---|---
Create trip | `create /[location] /[date] /[foreign-currency-ISO-code] /[exchange-rate] /[persons-in-trip]`|`create /America /02-02-2021 /USD /0.74 /Ben, Jerry, Tom`
Open trip | `open [trip-number]` | `open 1`
Close trip | `close` | `close`
List trips | `list` when no trip is opened| `list`
List persons involved in a trip | `people` | `people`
Delete trip | `delete [trip-number]`|`delete 1`
Edit trip | `edit [trip num] [attribute] [new value]` <br /><br /> `[-attribute]: -location, -date, -exchange rate, -forcur, -homecur`  | `edit 1 -location Afghanistan`

<br />

### Expense commands
Open a trip to use

Action | Command syntax | Example
---|---|---
Create expense|`expense [amount] [category] [people] /[description]`|`expense 30 food Ben, Jerry /In-and-Out Burgers`
List expenses |`list` when inside a trip| `list`
View an expense in detail |`view [expense-number]`| `view 1`
View filtered expenses in detail | `view filter [expense-attribute] [search-keyword]` <br /><br /> `expense-attribute: category, payer, person, description`|`view filter category food`
Delete expense|`delete [expense-number]`|`delete 1`
View list of expense| `summary -[name]` <br /> If name is not provided, displays all expenses like `list`| `summary` or `summary Ben`
View expense settlement actions of a person| `amount [person-in-trip]` | `amount Ben`
View optimized settlement actions for everyone in the trip|`optimize`|`optimize`






