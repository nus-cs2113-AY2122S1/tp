# User Guide

## Introduction
Restaurant Buddy is a desktop application for restaurant managers to help **keep track of restaurant data** 
such as its employees, dishes and ingredients in storage via a **Command Line Interface (CLI)**.

## Purpose
?
## How to use
?

## Contents
* [Quick start](#quick-start)
* [Features](#features)
  * Employee
    * [Adding an employee: `add-employee`](#adding-an-employee-add-employee)
    * [Removing an employee: `remove-employee`](#removing-an-employee-remove-employee)
    * [Listing all employees: `list-employee`](#listing-all-employees-list-employee)
  * Dish
    * [Adding a dish: `add-dish`](#adding-a-dish-add-dish)
    * [Removing a dish: `remove-dish`](#removing-a-dish-remove-dish)
    * [Editing a dish's price: `edit-dish`](#editing-a-dishs-price-edit-dish)
    * [Adding a discount to a dish: `discount-dish`](#adding-a-discount-to-a-dish-discount-dish)
    * [Listing all dishes: `list-menu`](#listing-all-dishes-list-menu)
  * Ingredient
    * [Adding an ingredient: `add-ingredient`](#adding-an-ingredient-add-ingredient)
    * [Removing an ingredient: `remove-ingredient`](#removing-an-ingredient-remove-ingredient)
    * [Listing all ingredients: `list-ingredient`](#listing-all-ingredients-list-ingredient)
  * Finance
    * [Adding a finance record: `add-finance`](#adding-a-finance-record-add-finance)
    * [Removing a finance record: `remove-finance`](#removing-a-finance-record-remove-finance)
    * [Editing a finance record: `edit-finance`](#editing-a-finance-record-edit-finance)
    * [Showing total account of finance records: `show-finance`](#showing-total-account-of-finance-records-show-finance)
    * [Listing all finance records: `list-finance`](#listing-all-finance-records-list-finance)
  * [Exiting the program: `bye`](#exiting-the-program-bye)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Quick start
1. Ensure you have **Java 11** or above installed on your computer.
2. Download the latest `tp.jar` from [here](https://github.com/AY2122S1-CS2113T-T12-4/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for Restaurant Buddy.
4. Navigate to your _home folder_ as chosen in step 3 in command prompt.
   1. Search for `cmd` in your search bar.
   2. Then, press enter to open command prompt.
   3. Next enter `cd DIRECTORY_NAME` until you reached your _home folder_. e.g. `cd desktop` navigates to your 
   desktop.
5. Type `java -jar tp.jar` in the command prompt and press enter to run Restaurant Buddy.
6. Type a command in Restaurant Buddy's command box and press enter to execute it.
7. Refer to the **[Features](#features)** below for details of all available commands.

## Features

**Notes about the command format:**
  * Words in UPPER_CASE are the parameters to be supplied by the user. 
    e.g. in `add-employee/EMPLOYEE_NAME/PHONE_NUMBER`, `EMPLOYEE_NAME` and `PHONE_NUMBER` are 
    parameters which can be used as `add-employee/John/81145812`.

### Adding an employee: `add-employee`

Adds an employee to the employee list.

Format: `add-employee/EMPLOYEE_NAME/PHONE_NUMBER`
  * `PHONE_NUMBER` must be a positive integer.

Example:
`add-employee/John/81145812` Adds the employee named `Dylan` with `81304854` as his phone number to the 
employee list.
```
---------------------------------------------
 I have added: 
   Dylan 81304854
 You now have 1 employees.
---------------------------------------------
```

### Removing an employee: `remove-employee`

Removes an employee from the employee list.

Format: `remove-employee/EMPLOYEE_INDEX`
  * Removes the employee at the specified `EMPLOYEE_INDEX`.
  * `EMPLOYEE_INDEX` refers to the index number shown in the displayed employee list.
  * `EMPLOYEE_INDEX` must be a positive integer.

Example:
`remove-employee/1` Removes the 1st employee from the employee list.
```
---------------------------------------------
 I have deleted: 
   Bob 83245053
---------------------------------------------
```

### Listing all employees: `list-employee`

Lists all employees in the employee list.

Format: `list-employee`

Example:
`list-employee`
```
---------------------------------------------
 Here are the employees in your list:
   1. Don - 81145812
   2. Jack - 83408743
   3. Alice - 81230844
   4. David - 81230412
---------------------------------------------
```

### Adding a dish: `add-dish`

Adds a dish to the menu.

Format: `add-dish/DISH_NAME/PRICE`
  * `PRICE` must be a positive number.

Example:
`add-dish/Pizza/5.80` Adds `Pizza` which costs `$5.80` to the menu.
```
---------------------------------------------
 I have added the following dish to the menu:
   1. Pizza - $5.8
---------------------------------------------
```

### Removing a dish: `remove-dish`

Removes a dish from the menu.

Format: `remove-dish/DISH_INDEX`
  * Removes the dish at the specified `DISH_INDEX`.
  * `DISH_INDEX` refers to the index number shown in the displayed menu.
  * `DISH_INDEX` must be a positive integer.

Example:
`remove-dish/1` Removes the 1st dish from the menu.
```
---------------------------------------------
 I have removed the following dish from the menu:
   1. Cake - $7.5
---------------------------------------------
```

### Editing a dish's price: `edit-dish`

Edits the price of a dish in the menu.

Format: `edit-dish/DISH_INDEX/NEW_PRICE`
  * Updates the price of the dish at the specified `DISH_INDEX` to `NEW_PRICE`.
  * `DISH_INDEX` refers to the index number shown in the displayed finance list.
  * `DISH_INDEX` must be a positive integer.
  * `NEW_PRICE` must be a number.

Example:
`edit-dish/1/10` Increases the price of the 1st dish in the menu to `$10`.
```
---------------------------------------------
 Got it! The updated price of the dish is as follows:
   1. Steak - $10.0
---------------------------------------------
```

### Adding a discount to a dish: `discount-dish`

Adds a discount to a dish in the menu.

Format: `discount-dish/DISH_INDEX/DISCOUNT(%)`
  * Adds a `DISCOUNT` to the dish at the specified `DISH_INDEX`.
  * `DISH_INDEX` refers to the index number shown in the displayed finance list.
  * `DISH_INDEX` must be a positive integer.
  * `DISCOUNT` must be a positive number.

Example:
`discount-dish/1/30` Adds a `30%` discount to the 1st dish in the menu.
```
---------------------------------------------
 Got it! I have added the discount to the dish!
 The discounted price is as follows:
   1. Roast Chicken - $10.0 ---> $7.0
---------------------------------------------
```

### Listing all dishes: `list-menu`

Lists all dishes in the menu.

Format: `list-menu`

Example:
`list-menu`
```
---------------------------------------------
 Here are the dishes in your menu:
   1. Laksa - $5.8
   2. Curry Chicken - $4.0
   3. Prata - $2.0
   4. Steamed Fish - $6.3
---------------------------------------------
```

### Adding an ingredient: `add-ingredient`

Adds an ingredient to the ingredient list.

Format: `add-ingredient/INGREDIENT_NAME/QUANTITY`
  * `QUANTITY` must be a positive integer.

Example:
`add-ingredient/Carrot/50` Adds `50` `carrots` to the ingredient list.
```
---------------------------------------------
 Got it. This ingredient was added:
   Ingredient Name: Carrot
   Ingredient Quantity: 50
---------------------------------------------
```

### Removing an ingredient: `remove-ingredient`

Removes an ingredient from the ingredient list.

Format: `remove-ingredient/INGREDIENT_INDEX`
  * Removes the ingredient at the specified `INGREDIENT_INDEX`.
  * `INGREDIENT_INDEX` refers to the index number shown in the displayed ingredient list.
  * `INGREDIENT_INDEX` must be a positive integer.

Example:
`remove-ingredient/1` Removes the 1st ingredient from the ingredient list.
```
---------------------------------------------
 Got it. This ingredient was deleted:
   Strawberry
---------------------------------------------
```

### Listing all ingredients: `list-ingredient`

Lists all ingredients in the ingredient list.

Format: `list-ingredient`

Example:
`list-ingredient`
```
---------------------------------------------
 Here are the ingredients in your list:
   1. Potato [55]
   2. Stock Cube [30]
   3. Chilli [23]
   4. Onion [44]
---------------------------------------------
```

### Adding a finance record: `add-finance`

Adds a finance record to the finance list.

Format: `add-finance/DATE/ACCOUNT`
  * `ACCOUNT` must be an integer.

Example:
`add-finance/23 Oct 2021/5000` Adds a finance account of `$5000` on `23 October 2021`.
```
---------------------------------------------
 Got it. This account is added:
   Finance Date: 23 Oct 2021
   Account: 5000
---------------------------------------------
```

### Removing a finance record: `remove-finance`

Removes the specified finance record from the finance list.

Format: `remove-finance/FINANCE_INDEX`
  * Removes the finance record at the specified `FINANCE_INDEX`.
  * `FINANCE_INDEX` refers to the index number shown in the displayed finance list.
  * `FINANCE_INDEX` must be a positive integer.

Example:
`remove_finance/1` Removes the 1st finance record in the finance list.
```
---------------------------------------------
 Got it. This account was deleted:
   16 Feb 2021  $3980
---------------------------------------------
```

### Editing a finance record: `edit-finance`

Edits a finance record in the finance list.

Format: `edit-finance/FINANCE_INDEX/NEW_ACCOUNT`
  * Updates the account of the finance record at the specified `FINANCE_INDEX` to `NEW_ACCOUNT`.
  * `FINANCE_INDEX` refers to the index number shown in the displayed finance list.
  * `FINANCE_INDEX` must be a positive integer.
  * `NEW_ACCOUNT` must be an integer.

Example:
`edit-finance/1/9000` Increases the account of the 1st finance record in the finance list to `$9000`.
```
---------------------------------------------
 Got it. This account is edited:
   Finance Date: 31 Aug 2021
   Account: 9000
---------------------------------------------
```

### Showing total account of finance records: `show-finance`

Shows the total account of all finance records in the finance list.

Format: `show-finance`

Example:
`show-finance`
```
---------------------------------------------
 Here is the total account you have: $15324
---------------------------------------------
```

### Listing all finance records: `list-finance`

Lists all finance records in the finance list.

Format: `list-finance`

Example:
`list-finance`
```
---------------------------------------------
 Here are the accounts in your list:
   1. 23 Oct 2021 9000
   2. 11 Jan 2021 8432
   3. 7 Nov 2021 7819
   4. 27 Sep 2021 10031
---------------------------------------------
```


### Exiting the program: `bye`

Exits the program.

Format: `bye`

Example:
`bye` 
```
---------------------------------------------
 Thank you. Goodbye!
---------------------------------------------
---------------------------------------------
 Storage saved successfully.
 See you again!
---------------------------------------------
```

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Copy the _home folder_ of Restaurant Buddy over to the other computer and run it as usual.

## Command Summary

| Command | Format |
| ------------ | ------------- |
| add-employee | `add-employee/EMPLOYEE_NAME/PHONE_NUMBER` |
| remove-employee | `remove-employee/EMPLOYEE_INDEX` |
| list-employee | `list-employee` |
| add-dish | `add-dish/DISH_NAME/PRICE` |
| remove-dish | `remove-dish/DISH_INDEX` |
| edit-dish | `edit-dish/DISH_INDEX/NEW_PRICE` |
| discount-dish | `discount-dish/DISH_INDEX/DISCOUNT(%)` |
| list-menu | `list-menu` |
| add-ingredient | `add-ingredient/INGREDIENT_NAME/QUANTITY` |
| remove-ingredient | `remove-ingredient/INGREDIENT_INDEX` |
| list-ingredient | `list-ingredient` |
| add-finance | `add-finance/DATE/ACCOUNT` |
| remove-finance | `remove-finance/FINANCE_INDEX` |
| edit-finance | `edit-finance/FINANCE_INDEX/NEW_ACCOUNT` |
| show-finance | `show-finance` |
| list-finance | `list-finance` |
| bye | `bye` |
