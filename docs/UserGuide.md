# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 


###Expenses

---
####View Summary of expenses

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
---
####List Expenses

List all expenses added to the current trip.

To view all expenses in a trip, ensure you are already inside a trip, then type ```view```

####View Expenses By Index

Show expense with corresponding index in the current trip. User must already be inside a trip and 
have at least 1 expense.

```view index```

####Filter Expenses By Attributes

To filter the list of expenses (i.e. only show expenses matching the entered attribute information), type

```view filter [expense-attribute] [attribute-information]```

For example, to only show expenses in Johor Bahru, enter

```view filter description Johor Bahru```


Be sure that the ```expense-attribute``` information entered is exactly the same or a substring of what is stored, otherwise the program may not be able to find the expense. 
This means that if you had originally entered JB as the expense location, filtering by Johor Bahru will not show the expense with the location entered as JB.


Note that the program currently only supports filtering using a single attribute.

####

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
