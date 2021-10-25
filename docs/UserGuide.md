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

#### Create New Trip

Creates a new trip in the program.

You must have at least one trip created (or loaded from the save file) to use any other function.

The input syntax to create a trip is:
```
create [location] [date] [local currency] [exchange rate] [persons in trip]
```

- `date` must follow the format of dd-MM-yyyy
- `persons in trip` should be separated with commas and without spaces between each name

For example,

```
create America 02-02-2021 USD 0.74 ben,jerry,tom
```

If your trip is successfully created, you'll see the following message:

```
Your trip to America on 02 Feb 2021 has been successfully added.
```

#### Delete a trip

Deletes a trip from the program.

The input syntax to delete a trip is:
```
delete [tripnumber]
```
- `[tripnumber]` is the index of the trip you wish to delete, and can be found by using `whatcommand` command.

If your trip is successfully deleted, you'll see the following message:

```
Your trip to America on 02 Feb 2021 has been successfully removed.
```


###View Summary of expenses

```summary trip-index```

Example:
Viewing summary of South Korea trip

Input:
```summary 1```

Output:
```
This is the summary for your South Korea trip 13/12/2018.
Total budget for this trip: $2000.00
Total expenditure so far: $1247.00
Current budget left for this trip: $753.00
```


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
