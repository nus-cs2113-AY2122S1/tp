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
###Expenses





## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary
* Add todo `todo n/TODO_NAME d/DEADLINE`
