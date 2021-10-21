# User Guide

## Introduction
Restaurant Buddy is a desktop application for restaurant managers to help **keep track of restaurant data** 
such as its employees, dishes and ingredients in storage via a **Command Line Interface (CLI)**.

## Contents
* Quick start
* Features
  * Adding an employee: `add-employee`
  * Removing an employee: `remove-employee`
  * Listing all employees: `list-employee`
  * Adding a dish: `add-dish`
  * Removing a dish: `remove-dish`
  * Editing a dish's price: `edit-dish`
  * Adding a discount to a dish: `discount-dish`
  * Listing all dishes: `list-menu`
  * Adding an ingredient: `add-ingredient`
  * Removing an ingredient: `remove-ingredient`
  * Listing all ingredients: `list-ingredient`
  * Exiting the program: `bye`
* Command Summary

## Quick start
1. Ensure you have **Java 11** or above installed on your computer.
2. Download the latest tp.jar from [here](https://github.com/AY2122S1-CS2113T-T12-4/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for Restaurant Buddy.
4. Open your command prompt and navigate to your _home folder_ as chosen in step 3.
5. Type `java -jar tp.jar` in the command prompt and press enter to run Restaurant Buddy.
6. Type a command in Restaurant Buddy's command box and press enter to execute it.
7. Refer to the **Features** below for details of each command.

## Features

**Notes about the command format:**
* Words in UPPER_CASE are the parameters to be supplied by the user. 
  e.g. in `add-employee/EMPLOYEE_NAME/PHONE_NUMBER`, `EMPLOYEE_NAME` and `PHONE_NUMBER` are 
  parameters which can be used as `add-employee/John/81145812`.

### Adding an employee: `add-employee`

Adds an employee to the employee list.

Format: `add-employee/EMPLOYEE_NAME/PHONE_NUMBER`

Example:
`add-employee/John/81145812` Adds the employee named John with 81145812 as his phone number to the employee list.
```
---------------------------------------------
I have added: 
John 81145812
You now have 1 employees.
---------------------------------------------
```

### Removing an employee: `remove-employee`

Removes an employee from the employee list.

Format: `remove-employee/EMPLOYEE_INDEX`

Example:
`remove-employee/1` Removes the first employee from the employee list.
```
---------------------------------------------
I have deleted: 
John 81145812
---------------------------------------------
```

### Listing all employees: `list-employee`

Lists all employees in the employee list.

Format: `list-employee`

Example:
`list-employee` Lists all employees in the employee list.
```
---------------------------------------------
Here are the employees in your list:
1. John - 81145812
---------------------------------------------
```

### Adding a dish: `add-dish`

Adds a dish to the menu.

Format: `add-dish/DISH_NAME/PRICE`

Example:
`add-dish/Pizza/5.80` Adds Pizza which costs $5.80 to the menu.
```
---------------------------------------------
 I have added the following dish to the menu:
   1. Pizza - $5.8
---------------------------------------------
```

### Removing a dish: `remove-dish`

Removes a dish from the menu.

Format: `remove-dish/DISH_INDEX`

Example:
`remove-dish/1` Removes the first dish from the menu.
```
---------------------------------------------
 I have removed the following dish from the menu:
   1. Pizza - $5.8
---------------------------------------------
```

### Changing a dish's price: `edit-dish`

Edits the price of a dish in the menu.

Format: `edit-dish/DISH_INDEX/NEW_PRICE`

Example:
`edit-dish/1/10` Increases the price of the first dish in the menu to $10.
```
---------------------------------------------
 Got it! The updated price of the dish is as follows:
   1. Pizza - $10.0
---------------------------------------------
```

### Adding a discount to a dish: `discount-dish`

Adds a discount to a dish in the menu.

Format: `discount-dish/DISH_INDEX/DISCOUNT(%)`

Example:
`discount-dish/1/30` Adds a 30% discount to the first dish in the menu.
```
---------------------------------------------
 Got it! I have added the discount to the dish!
 The discounted price is as follows:
   1. Pizza - $10.0 ---> $7.0
---------------------------------------------
```

### Listing all dishes: `list-menu`

Lists all dishes in the menu.

Format: `list-menu`

Example:
`list-menu` Lists all dishes in the menu.
```
---------------------------------------------
 Here are the dishes in your menu:
   1. Pizza - $5.8
---------------------------------------------
```

### Adding an ingredient: `add-ingredient`

Adds an ingredient to the ingredient list.

Format: `add-ingredient/INGREDIENT_NAME/QUANTITY`

Example:
`add-ingredient/Carrot/50` Adds 50 carrots to the ingredient list.
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

Example:
`remove-ingredient/1` Removes the first ingredient from the ingredient list.
```
---------------------------------------------
Got it. This ingredient was deleted:
Carrot
---------------------------------------------
```

### Listing all ingredients: `list-ingredient`

Lists all ingredients in the ingredient list.

Format: `list-ingredient`

Example:
`list-ingredient` Lists all ingredients in the ingredient list.
```
---------------------------------------------
Here are the ingredients in your list:
1. Carrot [50]
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

**A**: Copy the home directory folder of your tp.jar over to the other computer and run it as usual.

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
| bye | `bye` |