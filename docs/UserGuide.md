# User Guide

## Introduction

MediVault is a Command Line Interface (CLI) application that will help to manage medication supplies within a pharmacy.
It is an integrated solution that provides real-time tracking of stock, orders and prescribing of medication.

## Contents

* [Purpose](#purpose)
* [Glossary](#glossary)
* [Quick Start](#quick-start)
    * [Setting up](#setting-up)
    * [Changing modes](#changing-modes)
* [Features](#features)
    * [Managing Stocks](#managing-stocks)
        * [Adding stocks](#adding-stocks-addstock)
        * [Listing stocks](#listing-medication-stocks-liststock)
        * [Updating stocks](#updating-stocks-updatestock)
        * [Deleting stocks](#deleting-a-medication-stock-deletestock)
    * [Managing Prescriptions](#managing-prescriptions)
        * [Adding prescriptions](#adding-prescriptions-addprescription)
        * [Listing prescriptions](#listing-prescriptions-listprescription)
        * [Updating prescriptions](#updating-prescriptions-updateprescription)
        * [Deleting prescriptions](#deleting-prescriptions-deleteprescription)
    * [Managing Orders](#managing-orders)
        * [Adding orders](#adding-an-order-addorder)
        * [Listing orders](#listing-orders-listorder)
        * [Updating orders](#updating-orders-updateorder)
        * [Deleting orders](#deleting-an-order-deleteorder)
        * [Receiving orders](#receiving-orders-receiveorder)
    * [Managing Data](#managing-data)
        * [Archive orders](#archive-orders-archiveorder)
        * [Archive prescriptions](#archive-prescriptions-archiveprescription)
        * [Purge data](#purging-existing-data--purge)
    * [Miscellaneous](#miscellaneous)
        * [Help](#showing-help-page--help)
        * [Exit](#exiting-medivault--exit)
* [Data Handling](#data-handling)
    * [Data Storage](#data-storage)
    * [Data Editing](#data-editing)
* [FAQ](#faq)
* [Command Summary](#command-summary)

# Purpose

The purpose of this user guide is for users to have a more detailed understanding and reference to usage of commands in
MediVault. In this user guide, you can expect to find examples and expected outputs of each command. MediVault caters
for normal users and advanced users so you can expect to find information about how to efficiently use the commands or
make use of the mode switching capabilities for convenience.

As a pharmacist, you would probably focus more on the sections related to stock and prescriptions. As a manager of the
pharmacy, you may be more interested in the order and data management sections of the user guide.

# Glossary

Terminology | Meaning
------ | ------
Stock | Refers to a medication.
Prescription | Refers to a prescription.
Order | Refers to ordering new medications to replenish the stocks.
Parameters | Prefixes for MediVault to understand the type of information you provide.

# Quick Start

### Setting up

1. Ensure that you have **Java 11** or above installed.
2. Download the latest version of `MediVault.jar`
   from [here](https://github.com/AY2122S1-CS2113T-T10-1/tp/releases/tag/v1.0).
3. Copy the file to the folder you want to use as the **home** folder for `MediVault`.
4. In the terminal, run `Java -jar MediVault.jar`.
5. You should see the following prompt if the program has started successfully.

```
|  \/  |          | |(_)| | | |              | || |  
| .  . |  ___   __| | _ | | | |  __ _  _   _ | || |_ 
| |\/| | / _ \ / _` || || | | | / _` || | | || || __|
| |  | ||  __/| (_| || |\ \_/ /| (_| || |_| || || |_ 
\_|  |_/ \___| \__,_||_| \___/  \__,_| \__,_||_| \__|
Welcome to MediVault!
[STOCK] > 
```

### Changing Modes

> :information_source: Power users can choose to omit mode selection to get things done faster.

MediVault includes a mode feature to make the commands simpler for you. Your current mode is indicated in the square
brackets on the bottom left of the console `[STOCK] >`. It allows you to type `add`, `list`, `update`, `delete` without
typing in the full command. For example, when you are in `stock` mode, typing `list` is equivalent to `liststock`.

Type `stock`, `prescription` or `order` to change to the respective modes.

Example (Current mode is Stock): `prescription`

Expected Output:

```
Mode has changed to PRESCRIPTION.
[PRESCRIPTION] > 
```

# Features

> :bulb: Notes about the commands:
> 
> You can refer to [Glossary](#glossary) to understand technical terms mentioned below.
> * Parameters enclosed in `[]` should contain **one or more** optional parameters.
> * Parameters enclosed in `{}` are **totally** optional parameters.
> * Parameters you specify can be in any order. 
>  * E.g. `update i/1 q/100 m/200` and `update i/1 m/200 q/100` are both acceptable.
> * MediVault ignores additional parameters provided when commands do not require one.
> * If you specify the same parameter multiple times, MediVault will accept the last occurrence.
>  * E.g. `delete i/2 i/1`, MediVault interprets the command as `delete i/1`.
> * MediVault's commands are case-insensitive.
> * Dates in the `d/DATE` and `e/EXPIRY_DATE` field are in `DD-MM-YYYY` format.
> * Column names in the `sort` parameter can be provided as the full column name or the column alias.
>  * E.g. `NAME` is equivalent to `n` and `QUANTITY` is equivalent to `q`.
> * For the `list` commands, use the `sort` parameter to sort by a column in ascending order and `rsort` parameter to
> sort in descending order.

## Managing Stocks

### Adding stocks: `addstock`

Adds medication into the inventory.

> :warning: Warning
> * If medication exists, description and maximum quantity will be optional parameters. If you include `d/DESCRIPTION` or  `m/MAX_QUANTITY` parameter, it will be ignored and MediVault will add the medication with the existing description and existing maximum quantity.
> * If medication and expiry date exists, price, description and maximum quantity will be optional parameters. If you include `p/PRICE` or `d/DESCRIPTION` or  `m/MAX_QUANTITY` parameter, it will be ignored and MediVault will add the medication with the existing price, existing description and existing maximum quantity.

Format: `addstock n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE {d/DESCRIPTION m/MAX_QUANTITY}`

Example 1 (If medication exists): `addstock n/panadol p/5 q/50 e/19-09-2021`

Expected Output 1:

```
Medicine exists. Using existing description and maximum quantity.
Medication added: panadol
+====+=========+=======+==========+=============+===============================================+==============+
| ID |  NAME   | PRICE | QUANTITY | EXPIRY_DATE |                  DESCRIPTION                  | MAX_QUANTITY | 
+====+=========+=======+==========+=============+===============================================+==============+
| 7  | panadol | $5.00 |    50    | 19-09-2021  |  BEST MEDICINE TO CURE HEADACHES, FEVER AND   |     1000     | 
|    |         |       |          |             |                     PAINS                     |              | 
+----+---------+-------+----------+-------------+-----------------------------------------------+--------------+
```

Example 2 (If medication and expiry date exists): `addstock n/panadol p/10 q/10 e/14-09-2021 d/for fever m/1000`

Expected Output 2:

```
Same Medication and Expiry Date exist. Update existing quantity.
+====+=========+========+==========+=============+==========================================+==============+
| ID |  NAME   | PRICE  | QUANTITY | EXPIRY_DATE |               DESCRIPTION                | MAX_QUANTITY | 
+====+=========+========+==========+=============+==========================================+==============+
| 2  | PANADOL | $20.00 |    30    | 14-09-2021  | BEST MEDICINE TO CURE HEADACHES, FEVER   |     1000     | 
|    |         |        |          |             |                AND PAINS                 |              | 
+----+---------+--------+----------+-------------+------------------------------------------+--------------+
```

Example 3 (If medication does not
exist): `addstock n/paracetamol q/10 p/10 e/02-11-2021 d/used to treat fever and pain m/500`

Expected Output 3:

```
Medication added: paracetamol
+====+=============+========+==========+=============+==============================+==============+
| ID |    NAME     | PRICE  | QUANTITY | EXPIRY_DATE |         DESCRIPTION          | MAX_QUANTITY | 
+====+=============+========+==========+=============+==============================+==============+
| 10 | paracetamol | $10.00 |    10    | 02-11-2021  | used to treat fever and pain |     500      | 
+----+-------------+--------+----------+-------------+------------------------------+--------------+
```

### Listing medication stocks: `liststock`

Lists all existing medications in the inventory.

* All parameters for `liststock` command are optional, you can choose to list medication by any of the parameters.
* You are able to `liststock` by any column and sort or reverse sort them.
* When you update an order information, MediVault reflects the pending stocks shown here.

Format: `liststock {i/STOCK_ID n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE d/DESCRIPTION m/MAX_QUANTITY sort/COLUMN_NAME rsort/COLUMN_NAME}`

Example 1 (Listing all medications): `liststock`

Expected output:

```
+====+==============+========+==============+=============+===============================================+==============+
| ID |     NAME     | PRICE  |   QUANTITY   | EXPIRY_DATE |                  DESCRIPTION                  | MAX_QUANTITY | 
+====+==============+========+==============+=============+===============================================+==============+
| 1  |   PANADOL    | $20.00 |      20      | 13-09-2021  |  BEST MEDICINE TO CURE HEADACHES, FEVER AND   |     1000     | 
|    |              |        | PENDING: 150 |             |                     PAINS                     |              | 
+----+--------------+--------+--------------+-------------+-----------------------------------------------+--------------+
| 2  |   PANADOL    | $20.00 |      10      | 14-09-2021  |  BEST MEDICINE TO CURE HEADACHES, FEVER AND   |     1000     | 
|    |              |        | PENDING: 150 |             |                     PAINS                     |              | 
+----+--------------+--------+--------------+-------------+-----------------------------------------------+--------------+
| 3  |   VICODIN    | $10.00 |      20      | 30-09-2021  |  POPULAR DRUG FOR TREATING ACUTE OR CHRONIC   |     500      | 
|    |              |        | PENDING: 30  |             |      MODERATE TO MODERATELY SEVERE PAIN       |              | 
+----+--------------+--------+--------------+-------------+-----------------------------------------------+--------------+
| 4  | SIMVASTATIN  | $20.00 |      25      | 10-10-2021  |   TREATS HIGH CHOLESTEROL AND REDUCES THE     |     800      | 
|    |              |        | PENDING: 20  |             |                RISK OF STROKE                 |              | 
+----+--------------+--------+--------------+-------------+-----------------------------------------------+--------------+
| 5  |  LISINOPRIL  | $20.00 |      25      | 15-10-2021  |       USED FOR TREATING HYPOTHYROIDISM        |     800      | 
|    |              |        | PENDING: 200 |             |                                               |              | 
+----+--------------+--------+--------------+-------------+-----------------------------------------------+--------------+
| 6  | AZITHROMYCIN | $20.00 |      35      | 15-10-2021  |   USED FOR TREATING EAR, THROAT, AND SINUS    |     100      | 
|    |              |        | PENDING: 100 |             |                  INFECTIONS                   |              | 
+----+--------------+--------+--------------+-------------+-----------------------------------------------+--------------+
```

Example 2 (Filter by name): `liststock n/panadol`

Expected output:

```
+====+=========+========+==============+=============+===============================================+==============+
| ID |  NAME   | PRICE  |   QUANTITY   | EXPIRY_DATE |                  DESCRIPTION                  | MAX_QUANTITY | 
+====+=========+========+==============+=============+===============================================+==============+
| 1  | PANADOL | $20.00 |      20      | 13-09-2021  |  BEST MEDICINE TO CURE HEADACHES, FEVER AND   |     1000     | 
|    |         |        | PENDING: 150 |             |                     PAINS                     |              | 
+----+---------+--------+--------------+-------------+-----------------------------------------------+--------------+
| 2  | PANADOL | $20.00 |      10      | 14-09-2021  |  BEST MEDICINE TO CURE HEADACHES, FEVER AND   |     1000     | 
|    |         |        | PENDING: 150 |             |                     PAINS                     |              | 
+----+---------+--------+--------------+-------------+-----------------------------------------------+--------------+
```

### Updating stocks: `updatestock`

Updates existing medication stock information in the inventory.

> :warning: Warning
> * The Stock ID must exist in MediVault.
> * You cannot update the Stock ID.
> * The allocation of Stock ID is determined by MediVault.
> * If you include the `n/NAME`, `d/DESCRIPTION` or `m/MAX_QUANTITY` parameter, MediVault updates
    **all** entries that has same existing medication name given the `i/ID` with your input values for these parameters.

> :information_source: Note:
> * A new Stock ID will be assigned to the current stock if your update has the `n/NAME` parameter.
> * When you update the `n/NAME` parameter, there may be an existing prescription record that is present.
> * By allocating a new Stock ID to the updated stock record, MediVault preserves the name of the old record
    > so that when you delete a prescription record, it is **guaranteed** to automatically update the quantity
    > of the stock.

Format: `updatestock i/ID [n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE d/DESCRIPTION m/MAX_QUANTITY]`

Initial stock records:

```
+====+=========+========+==========+=============+=============+==============+
| ID |  NAME   | PRICE  | QUANTITY | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | 
+====+=========+========+==========+=============+=============+==============+
| 1  | PANADOL | $20.00 |    20    | 13-09-2021  |  HEADACHES  |     1000     | 
+----+---------+--------+----------+-------------+-------------+--------------+
| 2  | PANADOL | $20.00 |    10    | 14-09-2021  |  HEADACHES  |     1000     | 
+----+---------+--------+----------+-------------+-------------+--------------+
| 3  | VICODIN | $10.00 |    20    | 30-09-2021  | SEVERE PAIN |     500      | 
+----+---------+--------+----------+-------------+-------------+--------------+
```

> :information_source: Note:
> * Examples stated below are **independent** from each other.

Example 1 (Updating parameters containing `n/NAME`):
`update i/3 n/amoxil p/20 q/50 e/01-12-2021 d/BODY INFECTIONS m/100`

Expected output 1:

```
Updated! Number of rows affected: 1
Stock Id changed from:
3 -> 4
+====+========+========+==========+=============+=================+==============+
| ID |  NAME  | PRICE  | QUANTITY | EXPIRY_DATE |   DESCRIPTION   | MAX_QUANTITY | 
+====+========+========+==========+=============+=================+==============+
| 4  | amoxil | $20.00 |    50    | 01-12-2021  | BODY INFECTIONS |     100      | 
+----+--------+--------+----------+-------------+-----------------+--------------+
```

Example 2 (Updating parameters **without** `n/NAME`):
`update i/1 p/30 d/FEVER`

Expected Output 2:

```
Updated! Number of rows affected: 2
+====+=========+========+==========+=============+=============+==============+
| ID |  NAME   | PRICE  | QUANTITY | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | 
+====+=========+========+==========+=============+=============+==============+
| 1  | PANADOL | $30.00 |    20    | 13-09-2021  |    FEVER    |     1000     | 
+----+---------+--------+----------+-------------+-------------+--------------+
| 2  | PANADOL | $20.00 |    10    | 14-09-2021  |    FEVER    |     1000     | 
+----+---------+--------+----------+-------------+-------------+--------------+
```

### Deleting a medication stock: `deletestock`

If you made a mistake and want to delete a medication from the inventory, you may do so by using this command.

* Able to delete a specific stock by specifying Stock ID using `i/ID`.
* Able to delete multiple stocks that have expiry date before and equals to specified date using `expiring/EXPIRY_DATE`.

Format: `deletestock [i/ID expiring/EXPIRY_DATE]`

Example 1 (Deletion by Stock ID): `deletestock i/3`

Expected output:

```
Deleted row with Stock Id: 3
```

Example 2 (Deletion by expiry date): `deletestock expiring/10-10-2021`

Expected output:

```
Deleted expired medications! Rows deleted: 4
```

## Managing Prescriptions

### Adding prescriptions: `addprescription`

Adds a prescription record and subtracts the medication quantity from stocks.

> :information_source: MediVault will prescribe the medication with the shortest expiry date first. If the remaining quantity of the current batch of medication is insufficient, the next batch of medication will be used to supplement the prescription.

Format: `addprescription n/NAME q/QUANTITY s/STAFF c/CUSTOMER_ID`

Example: `addprescription n/panadol q/5 s/john c/123`

Expected Output:

```
Prescribed:panadol Quantity:1 Expiry Date:14-09-2021
+====+=========+==========+=============+============+=======+==========+
| ID |  NAME   | QUANTITY | CUSTOMER ID |    DATE    | STAFF | STOCK ID | 
+====+=========+==========+=============+============+=======+==========+
| 9  | panadol |    5     |     123     | 26-10-2021 | john  |    2     | 
+----+---------+----------+-------------+------------+-------+----------+
```

### Listing prescriptions: `listprescription`

Lists all prescription records in the application.

* All parameters for `listprescription` command are optional, you can choose to list the records by any of the
  parameters.
* You are able to `listprescription` by any column and sort or reverse sort them.

Format: `listprescription {i/ID q/QUANTITY c/CUSTOMER_ID d/DATE s/STAFF_NAME sid/STOCK_ID sort/COLUMN_NAME rsort/COLUMN NAME}`

Example 1 (Listing all prescriptions): `listprescriptions`

Expected output:

```
+====+==============+==========+=============+============+=======+==========+
| ID |     NAME     | QUANTITY | CUSTOMER ID |    DATE    | STAFF | STOCK ID | 
+====+==============+==========+=============+============+=======+==========+
| 1  |   PANADOL    |    10    |  S1234567A  | 09-10-2021 | Jane  |    1     | 
+----+--------------+----------+-------------+------------+-------+----------+
| 2  |   VICODIN    |    10    |  S2345678B  | 10-10-2021 | Peter |    3     | 
+----+--------------+----------+-------------+------------+-------+----------+
| 3  | SIMVASTATIN  |    10    |  S1234567A  | 11-10-2021 |  Sam  |    4     | 
+----+--------------+----------+-------------+------------+-------+----------+
| 4  |  LISINOPRIL  |    10    |  S3456789C  | 12-10-2021 | Jane  |    5     | 
+----+--------------+----------+-------------+------------+-------+----------+
| 5  | AZITHROMYCIN |    10    |  S2345678B  | 13-10-2021 | Peter |    6     | 
+----+--------------+----------+-------------+------------+-------+----------+
```

Example 2 (Listing prescriptions sorted by staff): `listprescriptions sort/s`

Expected output:

```
+====+==============+==========+=============+============+=======+==========+
| ID |     NAME     | QUANTITY | CUSTOMER ID |    DATE    | STAFF | STOCK ID | 
+====+==============+==========+=============+============+=======+==========+
| 1  |   PANADOL    |    10    |  S1234567A  | 09-10-2021 | Jane  |    1     | 
+----+--------------+----------+-------------+------------+-------+----------+
| 4  |  LISINOPRIL  |    10    |  S3456789C  | 12-10-2021 | Jane  |    5     | 
+----+--------------+----------+-------------+------------+-------+----------+
| 2  |   VICODIN    |    10    |  S2345678B  | 10-10-2021 | Peter |    3     | 
+----+--------------+----------+-------------+------------+-------+----------+
| 5  | AZITHROMYCIN |    10    |  S2345678B  | 13-10-2021 | Peter |    6     | 
+----+--------------+----------+-------------+------------+-------+----------+
| 3  | SIMVASTATIN  |    10    |  S1234567A  | 11-10-2021 |  Sam  |    4     | 
+----+--------------+----------+-------------+------------+-------+----------+
```

### Updating prescriptions: `updateprescription`

Updates an existing prescription information.

> :warning: Warning
> * You cannot update the Stock or the Prescription ID. 
> * The allocation of Prescription ID is determined by MediVault.
> * Your provided `n/NAME` parameter **must** exist in stocks.
> * When you update a prescription record, the stock information may be affected as well

> :information_source: Note:
> * MediVault allocates a **new** Prescription ID when you update prescription records containing the `n/NAME` and 
    > `q/QUANTITY` parameter.
> * This is because MediVault deletes the old prescription record and adds the updated prescription record as a **new** 
    > prescription record.

Format: `updateprescription i/ID [n/NAME q/QUANTITY c/CUSTOMER_ID d/DATE s/STAFF_NAME]`

Initial stock records:

```
+====+=========+========+==========+=============+=============+==============+
| ID |  NAME   | PRICE  | QUANTITY | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | 
+====+=========+========+==========+=============+=============+==============+
| 1  | PANADOL | $20.00 |    20    | 13-09-2021  |  HEADACHES  |     1000     | 
+----+---------+--------+----------+-------------+-------------+--------------+
| 2  | PANADOL | $20.00 |    10    | 14-09-2021  |  HEADACHES  |     1000     | 
+----+---------+--------+----------+-------------+-------------+--------------+
| 3  | VICODIN | $10.00 |    20    | 30-09-2021  | SEVERE PAIN |     500      | 
+----+---------+--------+----------+-------------+-------------+--------------+
```

Initial prescription records:

```
+====+=========+==========+=============+============+=======+==========+
| ID |  NAME   | QUANTITY | CUSTOMER ID |    DATE    | STAFF | STOCK ID | 
+====+=========+==========+=============+============+=======+==========+
| 1  | PANADOL |    10    |  S1234567A  | 09-10-2021 | JANE  |    1     | 
+----+---------+----------+-------------+------------+-------+----------+
| 2  | VICODIN |    10    |  S2345678B  | 10-10-2021 | PETER |    3     | 
+----+---------+----------+-------------+------------+-------+----------+
```

> :information_source: Note:
> * Examples stated below are **independent** from each other.

Example 1 (Update prescription containing `n/NAME` or `q/QUANTITY`: 
`updateprescription i/1 q/5`

Expected output 1:

```
Restored 5 PANADOL
Updated prescription information!
+====+=========+==========+=============+============+=======+==========+
| ID |  NAME   | QUANTITY | CUSTOMER ID |    DATE    | STAFF | STOCK ID | 
+====+=========+==========+=============+============+=======+==========+
| 3  | PANADOL |    5     |  S1234567A  | 09-10-2021 | JANE  |    1     | 
+----+---------+----------+-------------+------------+-------+----------+
```

Updated stock record for Example 1:

```
+====+=========+========+==========+=============+=============+==============+
| ID |  NAME   | PRICE  | QUANTITY | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | 
+====+=========+========+==========+=============+=============+==============+
| 1  | PANADOL | $20.00 |    25    | 13-09-2021  |  HEADACHES  |     1000     | 
+----+---------+--------+----------+-------------+-------------+--------------+
| 2  | PANADOL | $20.00 |    10    | 14-09-2021  |  HEADACHES  |     1000     | 
+----+---------+--------+----------+-------------+-------------+--------------+
| 3  | VICODIN | $10.00 |    20    | 30-09-2021  | SEVERE PAIN |     500      | 
+----+---------+--------+----------+-------------+-------------+--------------+
```

Example 2 (Update prescription without both `n/NAME` and `q/QUANTITY` parameter:
`updateprescription i/1 s/JACK`

Expected output 2:

```
Updated prescription information!
+====+=========+==========+=============+============+=======+==========+
| ID |  NAME   | QUANTITY | CUSTOMER ID |    DATE    | STAFF | STOCK ID | 
+====+=========+==========+=============+============+=======+==========+
| 1  | PANADOL |    10    |  S1234567A  | 09-10-2021 | JACK  |    1     | 
+----+---------+----------+-------------+------------+-------+----------+
```

### Deleting prescriptions: `deleteprescription`

If you made a mistake and want to delete a prescription record you may do so by using this command and specifying of the 
Prescription ID.

Format: `deleteprescription i/PRESCRIPTION_ID`

Example: `deleteprescription i/3`

Expected output:

```
Prescription deleted for Prescription ID 3
```

## Managing Orders

### Adding an order: `addorder`

Adds an order for a stock.

> :information_source: Note:
> * The date parameter is optional, MediVault will set it as the date you added in the order if the parameter is omitted.
> * If the order quantity exceeds the maximum stock quantity allowed, you are unable to add the order.

Format: `addorder n/NAME q/QUANTITY {d/DATE}`

Example 1: `addorder n/panadol q/150 d/21-10-2021`

Expected Output:

```
Order added: panadol
+====+=========+==========+============+=========+
| ID |  NAME   | QUANTITY |    DATE    | STATUS  |
+====+=========+==========+============+=========+
| 1  | panadol |   150    | 21-10-2021 | PENDING |
+----+---------+----------+------------+---------+
```

Example 2: `addorder n/panadol q/1000`

Expected Output:

```
Order for panadol exists. Unable to add order as total order quantity exceeds maximum stock quantity.
```

### Listing orders: `listorder`

Lists all order records in the application.

* All parameters for `listorder` command are optional, you can choose to list the records by any of the parameters.
* You are able to listorder by id, name, quantity, date, status and also sort/rsort by columns.
* Example 1 demonstrates the list of all order records without parameters.
* Example 2 demonstrates the list of all orders that are PENDING.

Format: `listorder {i/ID n/NAME q/QUANTITY d/DATE s/STATUS sort/COLUMN_NAME rsort/COLUMN NAME}`

Example 1: `listorder`

Expected output:

```
+====+==============+==========+============+===========+
| ID |     NAME     | QUANTITY |    DATE    |  STATUS   | 
+====+==============+==========+============+===========+
| 1  |   PANADOL    |   100    | 09-10-2021 |  PENDING  | 
+----+--------------+----------+------------+-----------+
| 2  |   VICODIN    |    30    | 09-10-2021 |  PENDING  | 
+----+--------------+----------+------------+-----------+
| 3  |   VICODIN    |    50    | 10-10-2021 | DELIVERED | 
+----+--------------+----------+------------+-----------+
| 4  | SIMVASTATIN  |    20    | 11-10-2021 |  PENDING  | 
+----+--------------+----------+------------+-----------+
| 5  |  LISINOPRIL  |   200    | 12-10-2021 |  PENDING  | 
+----+--------------+----------+------------+-----------+
| 6  | AZITHROMYCIN |   100    | 13-10-2021 |  PENDING  | 
+----+--------------+----------+------------+-----------+
| 7  |   PANADOL    |    50    | 13-10-2021 |  PENDING  | 
+----+--------------+----------+------------+-----------+
```

Example 2: `listorder s/pending`

Expended output:

```
+====+==============+==========+============+=========+
| ID |     NAME     | QUANTITY |    DATE    | STATUS  | 
+====+==============+==========+============+=========+
| 1  |   PANADOL    |   100    | 09-10-2021 | PENDING | 
+----+--------------+----------+------------+---------+
| 2  |   VICODIN    |    30    | 09-10-2021 | PENDING | 
+----+--------------+----------+------------+---------+
| 4  | SIMVASTATIN  |    20    | 11-10-2021 | PENDING | 
+----+--------------+----------+------------+---------+
| 5  |  LISINOPRIL  |   200    | 12-10-2021 | PENDING | 
+----+--------------+----------+------------+---------+
| 6  | AZITHROMYCIN |   100    | 13-10-2021 | PENDING | 
+----+--------------+----------+------------+---------+
| 7  |   PANADOL    |    50    | 13-10-2021 | PENDING | 
+----+--------------+----------+------------+---------+
```

### Updating orders: `updateorder`

Updates an existing order information.

> :warning: Warning
> * You cannot update the Order ID or the status of the order.
> * The allocation of Order ID is determined by MediVault.
> * The status of the order will only be changed when you run the `receiveorder` command.

Format: `updateorder i/ID [n/NAME q/QUANTITY d/DATE]`

Initial order records:

```
+====+=========+==========+============+===========+
| ID |  NAME   | QUANTITY |    DATE    |  STATUS   | 
+====+=========+==========+============+===========+
| 1  | PANADOL |   100    | 09-10-2021 |  PENDING  | 
+----+---------+----------+------------+-----------+
```

Example: `updateorder i/1 q/50 d/10-10-2021`

Expected output:

```
Updated! Number of rows affected: 1
+====+=========+==========+============+=========+
| ID |  NAME   | QUANTITY |    DATE    | STATUS  | 
+====+=========+==========+============+=========+
| 1  | PANADOL |    50    | 10-10-2021 | PENDING | 
+----+---------+----------+------------+---------+
```

### Deleting an order: `deleteorder`

If you made a mistake and want to delete an order, you may do so by using this command and specifying of the Order ID.

Format: `deleteorder i/ORDER_ID`

Example: `deleteorder i/1`

Expected output:

```
Order deleted for Order ID 1
```

### Receiving orders: `receiveorder`

Adds the received medication into the current stocks.

> :information_source: Note:
>* Your input order Id must exist
>* When you run `receiveorder` with the required parameters, the medication you ordered will be automatically added into your current stocks.

> :warning: Warning
> * If medication exists, description and maximum quantity will be optional parameters. If you include `d/DESCRIPTION` or  `m/MAX_QUANTITY` parameter, it will be ignored and MediVault will add the medication with the existing description and existing maximum quantity.

Format: `receiveorder i/ID p/PRICE e/EXPIRY_DATE {d/DESCRIPTION m/MAX_QUANTITY}`

Example 1 (If medication does not exist) : `receiveorder i/1 p/20 e/20-10-2021 d/used to treat fever and pain m/500`

Expected output:

```
Medication added: paracetamol
+====+=============+========+==========+=============+==============================+==============+
| ID |    NAME     | PRICE  | QUANTITY | EXPIRY_DATE |         DESCRIPTION          | MAX_QUANTITY | 
+====+=============+========+==========+=============+==============================+==============+
| 8  | paracetamol | $20.00 |    50    | 20-10-2021  | used to treat fever and pain |     500      | 
+----+-------------+--------+----------+-------------+------------------------------+--------------+
```

Example 2 (If medication exists) : `receiveorder i/2 p/20 e/25-10-2021`

Expected output:

```
Medicine exists. Using existing description and maximum quantity.
Medication added: PANADOL
+====+=========+========+==========+=============+==========================================+==============+
| ID |  NAME   | PRICE  | QUANTITY | EXPIRY_DATE |               DESCRIPTION                | MAX_QUANTITY | 
+====+=========+========+==========+=============+==========================================+==============+
| 8  | PANADOL | $20.00 |   100    | 25-10-2021  | BEST MEDICINE TO CURE HEADACHES, FEVER   |     1000     | 
|    |         |        |          |             |                AND PAINS                 |              | 
+----+---------+--------+----------+-------------+------------------------------------------+--------------+
```

## Managing Data

This section of the user guide explains the features and usage of commands related to data management. This includes the
archive commands, purge command as well as data storage files related information.

### Archive orders: `archiveorder`

Archive order records into data/order_archive.txt file.

> :information_source: Note:
> * MediVault will remove all order records that have status of DELIVERED from the current orders that matches the user specified date and before.
> * MediVault will then archive it into data/order_archive.txt file.
> * The date parameter is compulsory.

> :warning: Warning
> * This is a one way command, there is no reversal except for you to manually add the archived records back into MediVault.

Format: `archiveorder d/DATE`

Example: `archiveorder d/10-10-2021`

Expected Output:

```
Archived orders from 10-10-2021
```

Expected Output (in data/order_archive.txt):

```
[ORDER ID: 2] 10 PANADOL WAS ORDERED ON 09-10-2021. STATUS: DELIVERED
[ORDER ID: 3] 50 VICODIN WAS ORDERED ON 10-10-2021. STATUS: DELIVERED
```

### Archive prescriptions: `archiveprescription`

Archive prescription records into data/prescription_archive.txt file.

> :information_source: Note:
> * MediVault will remove all prescription records from the current prescriptions that matches the user specified date and before.
> * MediVault will then archive it into data/prescription_archive.txt file.
> * The date parameter for this command is compulsory.

> :warning: Warning
> * This is a one way command, there is no reversal except for you to manually add the archived records back into MediVault.

Format: `archiveprescription d/DATE`

Example: `archiveprescription d/10-10-2021`

Expected Output:

```
Archived prescriptions from 10-10-2021
```

Expected Output (in data/prescription_archive.txt):

```
[PRESCRIPTION ID: 1] 10 PANADOL [STOCK ID: 1] WAS PRESCRIBED BY JANE TO S1234567A ON 09-10-2021
[PRESCRIPTION ID: 2] 10 VICODIN [STOCK ID: 3] WAS PRESCRIBED BY PETER TO S2345678B ON 10-10-2021
```

### Purging existing data : `purge`

Deletes all data in MediVault.

Format: `purge`

Example: `purge`

Expected output:

```
Are you sure you want to delete all data? (Y/N)
Y
All data has been cleared!
```

## Miscellaneous

### Showing help page : `help`

Displays the command syntax of all accepted commands by MediVault.

Format:`help`

Example: `help`

Expected output:

``` 
Welcome to the help page.
Your current mode is indicated in the square brackets at the bottom left of the console.
It allows you to type add, list, update, delete without typing in the full command.
Type stock, prescription or order to change to respective modes.
Note that parameters in {curly braces} are optional.
Parameters in [square braces] indicate that at least one of the parameter(s) must be provided.
+=====================+============================================================================================+
|       COMMAND       |                                       COMMAND SYNTAX                                       | 
+=====================+============================================================================================+
|      addstock       | addstock n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE {d/DESCRIPTION m/MAX_QUANTITY}            | 
+---------------------+--------------------------------------------------------------------------------------------+
|     deletestock     | deletestock [i/ID expiring/EXPIRY_DATE]                                                    | 
+---------------------+--------------------------------------------------------------------------------------------+
|     updatestock     | updatestock i/ID [n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE d/DESCRIPTION m/MAX_QUANTITY]    | 
+---------------------+--------------------------------------------------------------------------------------------+
|      liststock      | liststock {i/ID p/PRICE q/QUANTITY low/LESS_THAN_OR_EQUAL_QUANTITY e/EXPIRY_DATE           | 
|                     | expiring/LESS_THAN_OR_EQUAL_EXPIRY_DATE d/DESCRIPTION m/MAX_QUANTITY sort/COLUMN_NAME      | 
|                     | rsort/COLUMN NAME}                                                                         | 
+---------------------+--------------------------------------------------------------------------------------------+
|   addprescription   | addprescription n/NAME q/QUANTITY c/CUSTOMER_ID s/STAFF_NAME                               | 
+---------------------+--------------------------------------------------------------------------------------------+
| deleteprescription  | deleteprescription i/ID                                                                    | 
+---------------------+--------------------------------------------------------------------------------------------+
| updateprescription  | updateprescription i/ID [n/NAME q/QUANTITY c/CUSTOMER_ID d/DATE s/STAFF_NAME]              | 
+---------------------+--------------------------------------------------------------------------------------------+
|  listprescription   | listprescription {i/ID q/QUANTITY c/CUSTOMER_ID d/DATE s/STAFF_NAME sid/STOCK_ID           | 
|                     | sort/COLUMN_NAME rsort/COLUMN NAME}                                                        | 
+---------------------+--------------------------------------------------------------------------------------------+
| archiveprescription | archiveprescription d/DATE                                                                 | 
+---------------------+--------------------------------------------------------------------------------------------+
|      addorder       | addorder n/NAME q/QUANTITY {d/DATE}                                                        | 
+---------------------+--------------------------------------------------------------------------------------------+
|     deleteorder     | deleteorder i/ID                                                                           | 
+---------------------+--------------------------------------------------------------------------------------------+
|     updateorder     | updateorder i/ID [n/NAME q/QUANTITY d/DATE]                                                | 
+---------------------+--------------------------------------------------------------------------------------------+
|      listorder      | listorder {i/ID n/NAME q/QUANTITY d/DATE s/STATUS sort/COLUMN_NAME rsort/COLUMN NAME}      | 
+---------------------+--------------------------------------------------------------------------------------------+
|    archiveorder     | archiveorder d/DATE                                                                        | 
+---------------------+--------------------------------------------------------------------------------------------+
|    receiveorder     | receiveorder i/ID p/PRICE e/EXPIRY_DATE {d/DESCRIPTION m/MAX_QUANTITY}                     | 
+---------------------+--------------------------------------------------------------------------------------------+
|        purge        | purge                                                                                      | 
+---------------------+--------------------------------------------------------------------------------------------+
|        help         | help                                                                                       | 
+---------------------+--------------------------------------------------------------------------------------------+
|        exit         | exit                                                                                       | 
+---------------------+--------------------------------------------------------------------------------------------+
For more information, refer to User Guide: https://ay2122s1-cs2113t-t10-1.github.io/tp/
 ```

### Exiting MediVault : `exit`

Exits MediVault.

Format: `exit`

Example: `exit`

Expected output:

```
Quitting MediVault...
```

## Data Handling

### Data Storage

MediVault will automatically save your data after any operation that modifies stock, order or prescriptions. The data
will be stored in 3 separate files `data/stock.txt`, `data/order.txt` and `data/prescription.txt`. Data is saved in a
specific format with fields delimited by a pipe `|`.

Data formats:

* For `data/stock.txt`: `ID|NAME|PRICE|QUANTITY|EXPIRY_DATE|DESCRIPTION|MAX_QUANTITY|ISDELETED`
* For `data/order.txt`: `ID|NAME|QUANTITY|DATE|STATUS`
* For `data/prescription.txt`: `ID|NAME|QUANTITY|CUSTOMER_ID|DATE|STAFF|STOCK_ID`

### Data Editing

> :warning: Warning
> * It is possible for you to directly edit the data files, but it is **NOT** recommended unless you know exactly what you are doing because you risk corrupting it.
> * If MediVault detects corruption or invalid data, you will **NOT** be able to start MediVault.
> * In order for MediVault to work, you have to fix the error in the data file.
> * Invalid data will be highlighted on starting MediVault and hint you in the direction to fix it.
> * In the worst case scenario where you are unable to fix it, you may have to delete the corresponding data file.
> * It may result in unintended behaviour if data file is tampered with while the program is running.
> * Editing the data directly poses a significant risk to corruption of data.

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: You can transfer data to another computer by moving the 3 data files into the folder where MediVault.jar is.
Ensure that the data files are in a folder named `data`. You should expect to
see `stock.txt, order.txt, prescription.txt` in that folder.

## Command Summary

Command | Command Syntax
------ | ------
addstock | `addstock n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE {d/DESCRIPTION m/MAX_QUANTITY}`
deletestock | `deletestock [i/STOCK_ID expiring/DATE]`
updatestock | `updatestock i/STOCK_ID [n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE d/DESCRIPTION m/MAX_QUANTITY]`
liststock | `liststock {i/STOCK_ID p/PRICE q/QUANTITY e/EXPIRY_DATE d/DESCRIPTION m/MAX_QUANTITY sort/COLUMN_NAME rsort/COLUMN NAME}`
addprescription | `addprescription n/NAME q/QUANTITY c/CUSTOMER_ID s/STAFF_NAME`
deleteprescription | `deleteprescription i/ID`
updateprescription | `updateprescription i/ID [n/NAME q/QUANTITY c/CUSTOMER_ID d/DATE s/STAFF_NAME]`
listprescription | `listprescription {i/ID q/QUANTITY c/CUSTOMER_ID d/DATE s/STAFF_NAME sid/STOCK_ID sort/COLUMN_NAME rsort/COLUMN NAME}`
archiveprescription | `archiveprescription d/DATE`
addorder | `addorder n/NAME q/QUANTITY {d/DATE}`
deleteorder | `deleteorder i/ID`
updateorder | `updateorder i/ID [n/NAME q/QUANTITY d/DATE]  `
listorder | `listorder {i/ID n/NAME q/QUANTITY d/DATE s/STATUS sort/COLUMN_NAME rsort/COLUMN NAME}`
archiveorder | `archiveorder d/DATE`
receiveorder | `receiveorder i/ID p/PRICE e/EXPIRY_DATE {d/DESCRIPTION m/MAX_QUANTITY}`
purge | `no parameters`
help | `no parameters`
exit | `no parameters`
