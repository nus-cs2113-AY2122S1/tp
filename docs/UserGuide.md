# User Guide

Restaurant Buddy is a desktop application designed for restaurant managers to help **keep track of restaurant data** 
such as its employees, dishes in the menu and ingredients in storage via a **Command Line Interface (CLI)**.
In particular, Restaurant Buddy was developed specifically for users who can type fast and prefer typing to other 
means of input. 

1. [Quick Start](#1-quick-start-for-windows)
2. [Features](#2-features)
   1. [Employee](#2i-employee)
      1. [Adding an employee: `add-employee`](#2ia-adding-an-employee-add-employee)
      2. [Removing an employee: `remove-employee`](#2ib-removing-an-employee-remove-employee)
      3. [Listing all employees: `list-employee`](#2ic-listing-all-employees-list-employee)
   2. Dish
      1. [Adding a dish: `add-dish`](#2iia-adding-a-dish-add-dish)
      2. [Removing a dish: `remove-dish`](#2iib-removing-a-dish-remove-dish)
      3. [Editing a dish's price: `edit-dish`](#2iic-editing-a-dishs-price-edit-dish)
      4. [Adding a discount to a dish: `discount-dish`](#2iid-adding-a-discount-to-a-dish-discount-dish)
      5. [Listing all dishes: `list-dish`](#2iie-listing-all-dishes-list-dish)
   3. Ingredient
      1. [Adding an ingredient: `add-ingredient`](#2iiia-adding-an-ingredient-add-ingredient)
      2. [Removing an ingredient: `remove-ingredient`](#2iiib-removing-an-ingredient-remove-ingredient)
      3. [Listing all ingredients: `list-ingredient`](#2iiic-listing-all-ingredients-list-ingredient)
      4. [Finding expired ingredients: `find-expired-ingredient`](#2iiid-finding-expired-ingredients-find-expired-ingredient)
   4. Finance
      1. [Adding a finance record: `add-finance`](#2iva-adding-a-finance-record-add-finance)
      2. [Removing a finance record: `remove-finance`](#2ivb-removing-a-finance-record-remove-finance)
      3. [Editing a finance record: `edit-finance`](#2ivc-editing-a-finance-record-edit-finance)
      4. [Showing total account of finance records: `show-finance`](#2ivd-showing-total-account-of-finance-records-show-finance)
      5. [Listing all finance records: `list-finance`](#2ive-listing-all-finance-records-list-finance)
   5. [Listing all commands: `help`](#2v-listing-all-commands-help)
   6. [Exiting the program: `bye`](#2vi-exiting-the-program-bye)
3. [FAQ](#3-faq)
4. [Command Summary](#4-command-summary)

---

## 1. Quick Start for Windows
1. Ensure you have **Java 11** or above installed on your Windows computer.
2. Download the latest `tp.jar` from [here](https://github.com/AY2122S1-CS2113T-T12-4/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for Restaurant Buddy.
4. Navigate to your _home folder_ as chosen in step 3 in command prompt.
   1. Search for `cmd` in your search bar.
   2. Then, press enter to open command prompt.
   3. Next enter `cd DIRECTORY_NAME` until you reached your _home folder_. e.g. `cd desktop` navigates to your 
   desktop.
5. Type `java -jar tp.jar` in the command prompt and press enter to run Restaurant Buddy.
6. Type a command in Restaurant Buddy's command box and press enter to execute it.
7. Refer to the **[Features](#2-features)** below for details of all available commands.

---

## 2. Features
**Notes about the command format:**
  * Words in UPPER_CASE are the parameters to be supplied by the user. 
    e.g. in `add-employee/EMPLOYEE_NAME/PHONE_NUMBER/EMPLOYMENT_STATUS/SALARY`, `EMPLOYEE_NAME` and `PHONE_NUMBER` are 
    parameters which can be used as `add-employee/John/81145812/PERM/6000`.

### 2.i Employee
Employee refers to any person working at your restaurant. Restaurant Buddy needs some details from you about the employees.
This includes: employee name, phone number, employment status and salary.

### 2.i.a Adding an employee: `add-employee`
Adds an employee to the employee list.

Format: `add-employee/EMPLOYEE_NAME/PHONE_NUMBER/EMPLOYMENT_STATUS/SALARY`
  * `PHONE_NUMBER` must be a positive integer.
  * `EMPLOYMENT_STATUS` must be either PERM, TEMP or ADHOC.
  * `SALARY` must be a positive integer.

Example:
`add-employee/John/81145812/PERM/6000` Adds the employee named `John` with `81145812` as his phone number to the 
employee list. In addition, he/she is a `PERM` staff and gets paid $`6000`.
```
---------------------------------------------
 I have added: 
   John - 81145812 - PERM STAFF - $6000
 You now have 1 employees.
---------------------------------------------
```

### 2.i.b Removing an employee: `remove-employee`
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
   John - 81145812 - PERM STAFF - $6000
---------------------------------------------
```

### 2.i.c Listing all employees: `list-employee`
Lists all employees in the employee list.

Format: `list-employee`

Example:
`list-employee`
```
---------------------------------------------
 Here are the employees in your list:
   1. Don - 81145812 - PERM STAFF - $1000
   2. Jack - 83408743 - PERM STAFF - $2000
   3. Alice - 81230844 - TEMP STAFF - $900
   4. David - 81230412 - ADHOC STAFF - $100
---------------------------------------------
```

### 2.ii.a Adding a dish: `add-dish`
Adds a dish to the menu.

Format: `add-dish/DISH_NAME/PRICE`
  * `PRICE` must be a positive number.
  * `PRICE` would only be displayed up to 2 decimal places.

Example:
`add-dish/Pizza/5.80` Adds `Pizza` which costs `$5.80` to the menu.
```
---------------------------------------------
 I have added the following dish to the menu:
   1. Pizza - $5.8
---------------------------------------------
```

### 2.ii.b Removing a dish: `remove-dish`
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

### 2.ii.c Editing a dish's price: `edit-dish`
Edits the price of a dish in the menu.

Format: `edit-dish/DISH_INDEX/NEW_PRICE`
  * Updates the price of the dish at the specified `DISH_INDEX` to `NEW_PRICE`.
  * `DISH_INDEX` refers to the index number shown in the displayed finance list.
  * `DISH_INDEX` must be a positive integer.
  * `NEW_PRICE` must be a number.
  * `NEW_PRICE` would only be displayed up to 2 decimal places.

Example:
`edit-dish/1/10` Increases the price of the 1st dish in the menu to `$10`.
```
---------------------------------------------
 Got it! The updated price of the dish is as follows:
   1. Steak - $10.0
---------------------------------------------
```

### 2.ii.d Adding a discount to a dish: `discount-dish`
Adds a discount to a dish in the menu.

Format: `discount-dish/DISH_INDEX/DISCOUNT(%)`
  * Adds a `DISCOUNT` to the dish at the specified `DISH_INDEX`.
  * `DISH_INDEX` refers to the index number shown in the displayed finance list.
  * `DISH_INDEX` must be a positive integer.
  * `DISCOUNT` must be a positive number.
  * Maximum value `DISCOUNT` is 100 i.e. maximum possible discount is 100%.
  * Discounted price would only be displayed up to 2 decimal places.

Example:
`discount-dish/1/30` Adds a `30%` discount to the 1st dish in the menu.
```
---------------------------------------------
 Got it! I have added the discount to the dish!
 The discounted price is as follows:
   1. Roast Chicken - $10.0 ---> $7.0
---------------------------------------------
```

### 2.ii.e Listing all dishes: `list-dish`
Lists all dishes in the menu.

Format: `list-dish`
  * Prices would only be displayed up to 2 decimal places.

Example:
`list-dish`
```
---------------------------------------------
 Here are the dishes in your menu:
   1. Laksa - $5.8
   2. Curry Chicken - $4.0 ---> $2.5
   3. Prata - $2.0
   4. Steamed Fish - $6.3
---------------------------------------------
```

### 2.iii.a Adding an ingredient: `add-ingredient`
Adds an ingredient to the ingredient list.

Format: `add-ingredient/INGREDIENT_NAME/QUANTITY/PRICE/EXPIRY_DATE`
  * `INGREDIENT_NAME` must be a string.
  * `QUANTITY` must be a positive, non-zero integer.
  * `PRICE` must be a positive number of at least `0.01`. All values will be rounded to 2 decimal places.
  * `EXPIRY_DATE` must be a valid date in YYYY-MM-DD format.

Example:
`add-ingredient/Carrot/50/1.50/2021-10-21` Adds `50` `carrots` with unit price `1.50` and expiry date `2021-10-21` 
to the ingredient list.
```
---------------------------------------------
 Got it. This ingredient was added:
   Ingredient Name: Carrot
   Ingredient Quantity: 50
   Ingredient Unit Price: 1.50
   Expiry Date: 2021-10-21
   
   Carrot [50] [$1.50] [2021-10-21]
---------------------------------------------
```

### 2.iii.b Removing an ingredient: `remove-ingredient`
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
   Strawberry [30] [$1.00] [2021-10-30]
---------------------------------------------
```

### 2.iii.c Listing all ingredients: `list-ingredient`
Lists all ingredients in the ingredient list.

Format: `list-ingredient`

Example:
`list-ingredient`
```
---------------------------------------------
 Here are the ingredients in your list:
   1. Potato [55] [$1.50] [2021-11-01]
   2. Stock Cube [30] [$0.50] [2021-12-20]
   3. Chilli [23] [$0.70] [2021-11-18]
   4. Onion [44] [$1.00] [2021-10-29]
---------------------------------------------
```

### 2.iii.d Finding expired ingredients: `find-expired-ingredient`
Finds all ingredients from the ingredient list that are expired on a particular
date. There is some flexibility here for you to choose the input date according
to your needs. For instance, you can enter a future date to check which ingredients
would be expired by that date, and hence plan your inventory accordingly. If you
wish to find currently expired ingredients, simply enter the current date as
the input date.

Format: `find-expired-ingredient/INPUT_DATE`
  * `INPUT_DATE` must be a valid date in YYYY-MM-DD format.

Example:
`find-expired-ingredient/2021-11-05`
```
---------------------------------------------
 These ingredients are expired:
   Potato [55] [$1.50] [2021-11-01]
   Onion [44] [$1.00] [2021-10-29]
---------------------------------------------
```

### 2.iv.a Adding a finance record: `add-finance`
Adds a finance record to the finance list.

Format: `add-finance/DATE/ACCOUNT`
  * `DATE` must be a valid date in YYYY-MM-DD format
  * `ACCOUNT` must be an integer.
Example:
`add-finance/2021-10-23/5000` Adds a finance account of `$5000` on `23 October 2021`.
```
---------------------------------------------
 Got it. This account is added:
   Finance Date: 2021-10-23
   Account: 5000
---------------------------------------------
```

### 2.iv.b Removing a finance record: `remove-finance`
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

### 2.iv.c Editing a finance record: `edit-finance`
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

### 2.iv.d Showing total account of finance records: `show-finance`
Shows the total account of all finance records in the finance list.

Format: `show-finance`

Example:
`show-finance`
```
---------------------------------------------
 Here is the total account you have: $15324
---------------------------------------------
```

### 2.iv.e Listing all finance records: `list-finance`
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

### 2.v Listing all commands: `help`

Lists all commands.

Format: `help`

Example: `help`

```
---------------------------------------------
Hello! You seem like you need a hand to get started.
To interact with Restaurant Buddy, type in any of the following commands and press Enter!
The words that are CAPITALISED are the parameters that you can change.
---------------------------------------------
- Employee Commands:
add-employee/EMPLOYEE_NAME/PHONE_NUMBER/EMPLOYMENT_STATUS/SALARY
remove-employee/EMPLOYEE_INDEX
list-employee
---------------------------------------------
- Menu Commands:
add-dish/DISH_NAME/PRICE
remove-dish/DISH_INDEX
edit-dish/DISH_INDEX/NEW_PRICE
discount-dish/DISH_INDEX/DISCOUNT(%)
list-dish
---------------------------------------------
- Ingredient Commands:
add-ingredient/INGREDIENT_NAME/QUANTITY
remove-ingredient/INGREDIENT_INDEX
list-ingredient
---------------------------------------------
- Finance Commands:
add-finance/DATE/ACCOUNT
remove-finance/FINANCE_INDEX
edit-finance/FINANCE_INDEX/NEW_ACCOUNT
show-finance
list-finance
---------------------------------------------
To exit the program safely, please type in the command:
bye
---------------------------------------------
If you require more help, please refer to the User Guide on Github.
Let's get started at once! Feel free to try out the commands :D
---------------------------------------------
```

### 2.vi Exiting the program: `bye`
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

---

## 3. FAQ
**Q**: How do I transfer my data to another computer?

**A**: Copy the _home folder_ of Restaurant Buddy over to the other computer and run it as usual.

---

## 4. Command Summary

| Command | Format |
| ------------ | ------------- |
| add-employee | `add-employee/EMPLOYEE_NAME/PHONE_NUMBER/EMPLOYMENT_STATUS/SALARY` |
| remove-employee | `remove-employee/EMPLOYEE_INDEX` |
| list-employee | `list-employee` |
| add-dish | `add-dish/DISH_NAME/PRICE` |
| remove-dish | `remove-dish/DISH_INDEX` |
| edit-dish | `edit-dish/DISH_INDEX/NEW_PRICE` |
| discount-dish | `discount-dish/DISH_INDEX/DISCOUNT(%)` |
| list-dish | `list-dish` |
| add-ingredient | `add-ingredient/INGREDIENT_NAME/QUANTITY/PRICE/EXPIRY_DATE` |
| remove-ingredient | `remove-ingredient/INGREDIENT_INDEX` |
| list-ingredient | `list-ingredient` |
| find-expired-ingredient | `find-expired-ingredient/INPUT_DATE` |
| add-finance | `add-finance/DATE/ACCOUNT` |
| remove-finance | `remove-finance/FINANCE_INDEX` |
| edit-finance | `edit-finance/FINANCE_INDEX/NEW_ACCOUNT` |
| show-finance | `show-finance` |
| list-finance | `list-finance` |
| help | `help` |
| bye | `bye` |
