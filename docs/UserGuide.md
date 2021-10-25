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

### Create Expense

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
* Add todo `todo n/TODO_NAME d/DEADLINE`
