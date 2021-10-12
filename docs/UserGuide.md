# User Guide

## Introduction

MediVault is a Command Line Interface (CLI) application that will help to 
manage medication supplies within a pharmacy. It will solve the issue of 
tracking the stocks manually, discrepancies between actual and physical 
stocks as well as make the user's life easier by keeping track of expired 
medication.
## Contents

{to include content page}

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `MediVault` from [here](jar-file-location).

## Features
Notes about the commands:
* Parameters enclosed in `[]` are **one or more** optional parameters.
* Parameters enclosed in `{}` are **totally** optional parameters.
* Parameters you specify can be in any order.
  * E.g. `update i/1 q/100 m/200` and `update i/1 m/200 q/100` are both
  acceptable.
* MediVault ignores additional parameters provided when commands do not
require one.
* If you specify the same parameter multiple times, MediVault will accept 
the last occurrence.
  * E.g. `delete i/2 i/1`, MediVault interprets the command as `delete i/1`.
* MediVault's commands are case-insensitive.
* Dates in the `e/EXPIRY_DATE` field are in `DD-MM-YYYY` format.


### Adding a medication stock: `add`

Format:

Example:

Expected output:

### Deleting a medication stock: `delete`

Format:

Example:

Expected output:

### Updating medication stock information: `update`

Edit an existing medication information in the inventory.
* Edits the optional parameters specified by the stock Id that you input. 
  * The stock Id must exist in MediVault.
* You cannot update the stock Id.
* If you include the `n/NAME`, `d/DESCRIPTION` or `m/MAX_QUANTITY` parameter, 
MediVault updates all entries that has same existing medication name given the 
`i/STOCK_ID` with your input values for these parameters.

Format: `update i/STOCK_ID [n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE d/DESCRIPTION
m/MAX_QUANTITY`

Example:
`update i/1 n/panadol p/20 q/50 e/01-12-2021 
d/Best medicine to cure heacache, fever and pains m/100`

Expected output:
```
Updated! Number of rows affected: 1
+====+=========+========+==========+=============+=================================================+==============+
| ID |  NAME   | PRICE  | QUANTITY | EXPIRY_DATE |                   DESCRIPTION                   | MAX_QUANTITY | 
+====+=========+========+==========+=============+=================================================+==============+
| 1  | panadol | $20.00 |    50    | 01-12-2021  | Best medicine to cure headache, fever and pains |     100      | 
+----+---------+--------+----------+-------------+-------------------------------------------------+--------------+
```

### Listing medication stock : `list`

Format:

Example:

Expected output:

### Purging existing medication : `purge`

Format:

Example:

Expected output:

### Showing help page : `help`

Format:

Example:

Expected output:

### Exiting MediVault : `exit`

Format:

Example:

Expected output:

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{input summary page}