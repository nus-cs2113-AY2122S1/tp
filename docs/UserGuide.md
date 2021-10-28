# User Guide

## Introduction

PayMeBack is a CLI-based expense tracker for a group of friends travelling overseas together.

In such a group, it is easier for an individual to pay for expenses on behalf of the group rather than each
individual paying for their share for every activity. At the end of the day, it is quite troublesome to
manually calculate how much money each individual owes to another. PayMeBack is designed to make this process
easy and fuss-free.

## Quick Start
1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `PayMeBack` from [here](https://github.com/AY2122S1-CS2113T-T12-2/tp/releases), 
and move the downloaded file to your preferred folder.
3. Open any command-line application (such as Terminal, Command Prompt, Powershell) and navigate to the folder
   containing your downloaded copy of `PayMeBack`.
4. In the command-line interface, and type `java -jar PayMeBack.jar`.
5. Note that for this user guide, all entries enclosed in square brackets, "[" and "]" refer to user inputs.
6. If the program starts successfully, you should see the following on your screen:
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
<br />

## Features

### Saving your data

By default, the program attempts to save your data each time after you run a command. This includes
the `quit` command.

If the save attempt is unsuccessful, you will see this error message:

```
//error message not yet determined
```

The program will then attempt to save again the next time you run a command.

<br />

### Trips

#### - Create New Trip

Creates a new trip in the program.

You must have at least one trip created (or loaded from the save file) to use any other function.

The input syntax to create a trip is:
```
create [location] [date] [foriegn-currency-ISO-code] [exchange-rate] [persons-in-trip]
```

- `[date]` must follow the format of dd-mm-yyyy.
- `[foriegn-currency-ISO-code]` Currently there are 30 currencies supported, the currencies' names and ISO codes are listed below.
  - The program is still runnable with unknown currencies, however the symbol and decimal place will not be accurate.
- `[exchange-rate]` should be how much 1 of your home currency costs in foreign currency.
  - Example: SGD $1 is equivalent to USD $0.74, hence the `exchange-rate` will be 0.74.
  - Note that the default home currency is SGD, unless edited by the user.
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
create America 02-02-2021 USD 0.74 Ben, Jerry, Tom
```

Output:

```
Your trip to America on 02 Feb 2021 has been successfully added!
```

<br />

#### - Open Trip

In order to add, edit or delete expenses within a trip, you must first open the trip containing
the expenses you wish to access.

The input syntax for open:
```
open [trip-number]
```

For example,

Input:
````
open 1
````
Output:
```
You have opened the following trip:
America | 02 Feb 2021
```
<br />

#### - Close Trip
Closes the current trip you are in, allowing the `list` and `delete` command to be used.

Only can be used if you have already opened a trip.

The input syntax for close:
```
close
```
If successful, the output will be as follows:
```
You have closed the following trip:
America | 02 Feb 2021
```

<br />

#### - List Trips
Lists down the trips that you have created. Note that no trip must be open when executing this command.

The input syntax for list:
```
list
```
If successful, the output will be as follows:
```
List of Trips:
    1. London | 27 Oct 2021
```

<br />

#### - Delete a Trip

Deletes a trip from the program.

The input syntax to delete a trip is:
```
delete [trip-number]
```
- `[trip-number]` is the index of the trip you wish to delete, and can be found by using `whatcommand` command.

For example,

Input:
````
delete 1
````
Output:
```
Your trip to America on 02 Feb 2021 has been successfully removed.
```
<br />

### Expenses
#### - Create Expense
Creates a new expense entry for the current trip. 

A trip needs to open a trip to use this command.

All arguments are compulsory.

The input syntax is as follows:
````  
expense [amount] [category] [people] /[description]
````

- `[amount]` is the total amount spent on the expense.

- `[cat]` is a category tag to the expense. Only one tag is allowed.

- `[people]` Denotes the people involved in the expense. Multiple people involved are separated by commas.
  - Entering `-all` will add every person in the trip to the expense

- `[description]` is the description of the expense.

For Example:
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
- If the expense only has one person involved, the expense will be automatically added as the rest of the expense portion is redundant.

PayMeBack will then ask for the name of the person who paid for the expense:
````
Who paid for the expense?: 
````
- The name entered has to be part of the expense or PayMeBack will request for the name again.

PayMeBack will then ask for the amounts each person involved in the expense spent on that particular expense along
with how much of the amount has yet to be assigned:
````
Who paid for the expense?: Ben
Enter "equal" if expense is to be evenly split, enter individual spending otherwise
There is USD $30.00 left to be assigned. How much did Tom Ben?: 
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

Upon successful adding of an expense the following output will be printed:
````
Your expense has been added successfully
````

<br />

#### - List Expenses

List all expenses in the current opened trip.

To list all expenses in a trip, ensure you have opened a trip, then type ``list``.

For example,

Input:
````
Enter your command: list
````

Output:
````
List of Expenses: 
	1. In-and-Out Burgers | 03 Feb 2021
	2. Gift shopping at mall | 03 Feb 2021
	3. Dinner at Taco Bell | 03 Feb 2021
````

<br />

#### - View Expenses

Shows the details of a particular expense of a trip.

User must already be inside a trip and have at least 1 expense.

The input syntax is as follows:
````
view [index]
````
- Entering `view` without index will print all expenses in the currently opened trip.
For example,

Input:
````
Enter your command: view 1
````
Output:
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

#### - Filter Expenses By Attributes
Allows the user to view specific expenses based on an attribute of their choice.

The input syntax is as follows:
````
view filter [expense-attribute] [search-keyword]
````
- `[expense-attribute]` can be either `[category]`, `[payer]`, `[person]` or `[description]`.

For example, if the user would like to search for all expenses in the category "food",

Input:
````
Enter your command: view filter category food
````
Output:
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

### Summary
#### - View Summary of expenses
Shows an overall summary of current trip’s expenses. User needs to have opened a trip to use this command.
The input syntax is as follows:
````
summary [name]
````
- Entering `summary` without a name will print the summary of everybody in the opened trip.

For example,

Input:

```
Enter your command: summary
```

Output:
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
---







=======



## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary
* Add todo `todo n/TODO_NAME d/DEADLINE`
