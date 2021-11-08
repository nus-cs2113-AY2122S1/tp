# User Guide

## Introduction

**Accouminate** is a desktop application designed to manage finances for busy students. With **Accouminate**, students can take charge of their finances by keeping track of their daily expenses, setting both short-term and long-term financial goals, and maintaining their investment records.

This application uses a Command Line Interface (CLI) for quick and easy task management activities.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Accouminate` [here](https://github.com/AY2122S1-CS2113-T16-4/tp/releases/download/v2.1/CS2113-T16-4.Accouminate.jar).
3. Open your terminal (Mac/Linux) or your command prompt (Windows).
4. Navigate to the folder where you have placed your `accouminate.jar` and run `java -jar accouminate.jar`. The standard greeting message will be shown if there is no error.

## Features 

In the **Accouminate** application, there are four main features.

1. Manage expenses
2. Manage incomes
3. Manage budgets
4. Manage investments

After every command is executed, the user data is automatically saved on the computer. The next time the application is launched, all the user data will be loaded automatically.


## Commands

The main commands are separated according to their features. Subcommands are used with each main command to achieve user's desired task. For example, `[MAIN_CMD] [SUB_CMD] [ARGS] [-FLAGS]`.

### Help - `help`
The command `help` will show a list of all the available commands to the user.

### Exit - `exit`
The command `exit` will exit the application.

### Expense - `expense`

#### Adding an expense - `add`
Adds an expense to the database. The current date will be tagged to the expense added.

Format: `expense add [NAME] -v [VALUE] -c [CATEGORY]`

Arguments:
- `NAME`
  - The name of the expense is case-sensitive, and shall contain any characters except for “-”.
  - The name can be non-unique.
- `VALUE`
  - The value shall be any positive number (integer or float values).
  - Float values with more than two decimal places specified are truncated to two decimal places.
  - If a non-numeric string, negative number, or zero is provided, an error message is shown.
- `CATEGORY`
  - The name of the category is case-sensitive, and shall contain any characters except for "-".
  - The category is **not** a mandatory field.

Example of usage with category: 
- `expense add Eat Lunch -v 10.99 -c Meals`

Example of usage without category: 
- `expense add Eat Lunch -v 10.99`


<br />

#### Listing expenses - `list`
Shows a list of all expenses, or a list of expenses grouped by their categories.

Format: `expense list [-c]`

Arguments:
- `-c` - optional flag
  - If the category flag is not specified, all the expenses in are listed, in chronological order.
  - If the category flag is specified with a category, only expenses that belong to that category will be shown.

Example of usage:
- `expense list -c fruits`

<br />

#### Deleting an expense - `delete`
Deletes expense record(s) from the database by the unique identifier (from list) or by the name.

Format: `expense delete -n [NAME]`
OR
Format: `expense delete -i [ID]`

Arguments:
- `NAME`
  - The names of the expense are case-sensitive, and can contain any characters except for “-”.
  - The length of the name is limited to 64 characters.
  - If there are multiple repeated names, then all these expenses will be selected as candidates for deletion.
- `ID`
  - The id number shall be a positive integer.
  - If not a positive integer is provided, then an error message is shown.

Both arguments are mutually exclusive. This means that if both the name and id are specified, an error message is displayed.

Example of usage:

- `expense delete -n Eat lunch`
- `expense delete -i 2`

<br />

#### Updating an expense - `update`
Updates the value or category of an expense that has already been added.

Format: `expense update [NAME] -v [VALUE] -c [CATEGORY]`

Arguments:
- `NAME`
  - The names of the expense are case-sensitive, and can contain any characters except for “-”.
  - The length of the name is limited to 64 characters.
  - If there are multiple repeated names, then all these expenses will be selected as candidates for update.
- `VALUE`
  - Compulsory to specify a new value of update.
  - The value must be a positive number that represents monetary value. Cents are also accepted.
- `CATEGORY`
  - The name of the category is case-sensitive, and shall contain any characters except for "-".
  - The category is **not** a mandatory field.

Both arguments can be specified together.

Example of usage:

- `expense update Eat lunch -v 10`
- `expense update notebook -v 5 -c book`

<br />

#### Setting expense warning limit - `warning`
A warning limit can be set such that the remaining budget after adding an expense is close to reaching the limit, warnings will be shown.

Different Warnings:
- If the remaining budget after adding an expense is **close** to reaching a limit.
- If the remaining budget after adding an expense is **$0**.
- If after adding an expense, the remaining budget has become **negative**. 

Format: `expense warning -v [VALUE]`

Arguments:
- `VALUE`
  - Compulsory to specify a value for the warning limit.
  - The value must be a positive number that represents monetary value. Cents are also accepted.

Example of usage:

- `expense warning -v 100`

<hr />

### Income - `income`

#### Adding an income source - `add`
Adds an income source.

Format: `income add [NAME] -v [VALUE]`

Arguments:
- `NAME`
  - The name of the income source is case-sensitive, and shall contain any characters except for “-”.
  - The length of the name is limited to 64 characters.
  - Names can be non-unique.
- `VALUE`
  - The value shall be any positive number (integer or float values).
  - Float values with more than two decimal places specified are truncated to two decimal places.
  - If a non-numeric string, negative number, or zero is provided, an error message is shown.

Example of usage:

- `income add Pocket Money -v 50`

<br />

#### Listing all income sources - `list`
Shows a list of all income sources.

Format: `income list`

<br />

#### Deleting an income source - `delete`

Deletes an income source from the database by its unique identifier or by its name. Note that the name must match exactly to that in the database in order for it to be deleted.

Format: `income delete -n [NAME] -i [ID]`

Arguments:
- `NAME`
  - The names of the income sources are case-sensitive, and can contain any characters except for “-”.
  - The length of the name is limited to 64 characters.
  - If there are multiple repeated names, then all these expenses will be selected as candidates for deletion.
- `ID`
  - The id number shall be a positive integer.
  - If not a positive integer is provided, then an error message is shown.

<br />

#### Updating an income source - `update`
Updates an income source.

Format: `income update [NAME] -v [VALUE]`

Arguments:
- `NAME`
  - The name of the income source is case-sensitive, and shall contain any characters except for “-”.
  - The length of the name is limited to 64 characters.
  - Names can be non-unique.
- `VALUE`
  - The value shall be any positive number (integer or float values).
  - Float values with more than two decimal places specified are truncated to two decimal places.
  - If a non-numeric string, negative number, or zero is provided, an error message is shown.

Example of usage:

- `income update Pocket Money -v 50`

<hr />

### Budget - `budget`

#### Adding a budget - `add`

Adds a budget plan.

Format: `budget add -v [VALUE]`

Argument:
- `VALUE`
  - The value shall be any positive number (integer or float values).
  - Float values with more than two decimal places specified are truncated to two decimal places.
  - If a non-numeric string, negative number, or zero is provided, an error message is shown.

Example of usage:
- `budget add -v 1000`

<br />

#### Listing the budget - `list`

Shows the current budget, as specified by the user.

Format: `budget list`

<br />

#### Deleting the budget - `delete`

Deletes the budget set effectively setting it to 0.

Format: `budget delete`

<hr />


### Invest - `invest`
**Accouminate** provides users the ability to track basic investments, namely stocks and savings.

Subcommands for each investment type [TYPE] are used:
- Stock: `stocks`
- Savings: `savings`


#### Adding an investment - `add`

Adds an investment to the portfolio.

##### Adding a stock - `stocks`

Adds a stock to the investment portfolio. If a stock with the same name already exists in the portfolio, then the number of shares and the average price will be updated.

Format: `invest add stocks [NAME] -n [NUM] -p [PRICE]`

Argument:
- `NAME`
  - The name of the income source is case-sensitive, and shall contain any characters except for “-”.
  - Names are unique.
- `NUM`
  - The number of stocks shall be a positive integer.
  - If a non-numeric string is provided, an error message is shown.
- `PRICE`
  - The price shall be any positive number (integer or float values).
  - Float values with more than two decimal places specified are truncated to two decimal places.
  - If a non-numeric string, negative number, or zero is provided, an error message is shown.

Example of usage:
- `invest add stocks OCBC -n 100 -p 11.50`

<br />

##### Adding a savings account -  `savings`

Adds a savings account to the investment portfolio. If a savings account with the same name already exists in the portfolio, then the account balance will be updated.

Format: `invest add savings [NAME] -v [VALUE]`

Argument:
- `NAME`
  - The name of the income source is case-sensitive, and shall contain any characters except for “-”.
  - Names are unique.
- `VALUE`
  - The value shall be any positive number (integer or float values).
  - Float values with more than two decimal places specified are truncated to two decimal places.
  - If a non-numeric string, negative number, or zero is provided, an error message is shown.

Example of usage:
- `invest add savings Main -v 15000`

<br />

#### Deleting an investment - `delete`

Deletes an investment from the portfolio.

##### Deleting a stock - `stocks`

Deletes a stock from the investment portfolio by its unique identifier (from `list`).

Format: `invest delete stocks -i [ID]`

Argument:
- `ID`
  - The id number shall be a positive integer.
  - If not a positive integer is provided, then an error message is shown.


Example of usage:
- `invest delete stocks -i 1`

<br />

##### Deleting a savings account -  `savings`

Deletes a savings account from the investment portfolio by its unique identifier (from `list`).

Format: `invest delete savings -i [ID]`

Argument:
- `ID`
  - The id number shall be a positive integer.
  - If not a positive integer is provided, then an error message is shown.

Example of usage:
- `invest delete savings -i 1`

<br />

#### Listing investments - `list`

Lists a type of all investments in the portfolio.

##### Listing all stocks - `stocks`

Lists all the stocks in the investment portfolio.

Format: `invest list stocks`


<br />

##### Listing all savings accounts -  `savings`

Lists all the savings accounts in the investment portfolio.

Format: `invest list savings`

<hr />


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: A folder called data containing your records will be in the same directory as your jar file. Simply transfer it to the other computer, where the new jar file is located.

## Command Summary


| Function | Command |
| ---------| --------|
| Add expense | `expense add [NAME] -v [VALUE] -c [CATEGORY]` |
| Delete expense | `expense delete -n [NAME] -i [ID]` |
| List expenses | `expense list` |
| Update expense | `expense update [NAME] -v [VALUE] -c [CATEGORY]` |
| Expense warning limit | `expense warning -v [VALUE]` |
| Add income | `income add [NAME] -v [VALUE]` |
| Delete income | `income delete -n [NAME] -i [ID]` |
| List incomes | `income list` |
| Add budget | `budget add -v [VALUE]` |
| List budget | `budget list` |
| Delete budget | `budget delete` |
| Add savings | `invest add savings [NAME] -v [VALUE]` |
| Add stocks | `invest add stocks [NAME] -n [NUM] -p [PRICE]` |
| Delete savings | `invest delete stocks [NAME] -i [ID]` |
| Delete stocks | `invest delete stocks -i [ID]` |
| List savings | `invest list savings` |
| List stocks | `invest list stocks` |
| View all available commands | `help` |
| Exit the application | `exit` |
