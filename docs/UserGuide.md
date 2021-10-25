# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

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


####View Summary of expenses
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
#Available Currencies
###Expenses




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

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
