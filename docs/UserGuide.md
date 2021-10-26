# User Guide

## Introduction

PayMeBack is a CLI-based expense tracker for a group of friends travelling overseas together.

In such a group, it is easier for an individual to pay for expenses on behalf of the group rather than each
individual paying for their share for every activity. At the end of the day, it is quite troublesome to
manually calculate how much money each individual owes to another. PayMeBack is designed to make this process
easy and fuss-free.

## Quick Start
1. Ensure that you have Java 11 installed.
1. Down the latest version of `PayMeBack.jar` from [here](https://github.com/AY2122S1-CS2113T-T12-2/tp/releases).

## Features

### Saving your data

By default, the program attempts to save your data each time after you run a command. This includes
the `quit` command.

If the save attempt is unsuccessful, you will see this error message:

```
//error message not yet determined
```

and the program will attempt to save again the next time you run a command.

### Trips

#### Create New Trip

Creates a new trip in the program.

You must have at least one trip created (or loaded from the save file) to use any other function.

The input syntax to create a trip is:
```
create [location] [date] [local-currency] [exchange-rate] [persons-in-trip]
```

- `date` must follow the format of dd-MM-yyyy
- `persons-in-trip` should be separated with commas and without spaces between each name

For example,

```
create America 02-02-2021 USD 0.74 ben,jerry,tom
```

If your trip is successfully created, you'll see the following message:

```
Your trip to America on 02 Feb 2021 has been successfully added.
```

#### Open Trip

In order to add, edit or delete expenses within a trip, you must first open the trip containing
the expenses you wish to add/modify/delete.

To open a trip, enter

```
open [trip-number]
```

If successful, you'll see the following message:

```
You have opened the following trip:
America | 02 Feb 2021
```

#### Delete a trip

Deletes a trip from the program.

The input syntax to delete a trip is:
```
delete [trip-number]
```
- `[trip-number]` is the index of the trip you wish to delete, and can be found by using `whatcommand` command.

If your trip is successfully deleted, you'll see the following message:

```
Your trip to America on 02 Feb 2021 has been successfully removed.
```


#### View Summary of expenses
Shows an overall summary of current trip’s expenses. User needs to have opened a trip to use this command.

```summary```

```summary [name]```

Example:
Viewing summary of everybody's expenses in South Korea trip

Input:

```summary```

Output:
```
Tom has spent $50.00 on 3 expenses in the following split:
food: $30.00
travel: $20.00

Sara has spent $150.00 on 6 expenses in the following split:
food: $30.00
travel: $20.00
leisure: $100.00

Ben has spent $75.00 on 3 expenses in the following split:
food: $25.00
travel: $20.00
shopping: $30.00

```
---
####List Expenses

###Expenses
#### Available Currencies
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

#### Create Expense

``expense [amount] [category] [people] /[description]``

Creates a new expense entry for the current trip. User
needs to open a trip to use this command.
All arguments are compulsory.


``[cat]``

Adds a category tag to the expense. Multiple tags are allowed,separated by commas.



`[people]`
Denotes the people involved in sharing the expense. Multiple people involved are separated by commas. This argument assumes that the expense is being split equally among the people involved.


`[description]`
Specifies the description of the expense.
=======
1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `PayMeBack` from [here](http://link.to/duke), and move the downloaded file to
   your preferred folder.
3. Open any command-line application (such as Terminal, Command Prompt, Powershell) and navigate to the folder
   containing your downloaded copy of `PayMeBack`.
4. In the command-line interface, type `java -jar PayMeBack.jar`.
5. If the program starts successfully, you should see the following on your screen:
```
some code block
```
## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary
* Add todo `todo n/TODO_NAME d/DEADLINE`
