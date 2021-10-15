# User Guide

## Introduction
Restaurant Buddy is a desktop application for restaurant managers to help **keep track of restaurant data** 
such as its employees, dishes and ingredients in storage via a **Command Line Interface (CLI)**.

## Contents
* Quick start
* Features
  * Adding an employee: `add-employee`
  * Adding a dish: `add-dish`
  * Adding an ingredient: `add-ingredient`
  * Removing an employee: `remove-employee`
  * Removing a dish: `remove-dish`
  * Removing an ingredient: `remove-employee`
  * Listing all employees: `list-employee`
  * Listing all dishes: `list-menu`
  * Listing all ingredients: `list-ingredient`
  * Exiting the program: `bye`
* Command Summary

## Quick start
1. Ensure you have **Java 11** or above installed on your computer.
2. Download the latest RestaurantBuddy.jar from [here](https://github.com/jerrelllzw/ip/releases).
3. Copy the file to the folder you want to use as the _home folder_ for Duke.
4. Open your command prompt and navigate to your _home folder_ as chosen in step 3.
5. Type `java -jar ip.jar` in the command prompt and press enter to run Duke.
6. Type a command in Duke's command box and press enter to execute it.
7. Refer to the **Features** below for details of each command.

## Features

**Notes about the command format:**
* Words in UPPER_CASE are the parameters to be supplied by the user.
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.
* For commands that do not take in parameters, supplying a parameter will result in an error.
  e.g. entering `list 3` will result in an error.

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.

Example of usage:

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### Adding a todo: `todo`

Adds a todo to the task list.

Format: `todo TASK_DESCRIPTION`

Example:
`todo read book` Adds the task "read book" to the task list.
```
____________________________________________________________
 Got it. I've added this task:
  [T][ ] read book
 Now you have 1 tasks in the list.
____________________________________________________________
```

### Adding a deadline: `deadline`

Adds a task with a deadline to the task list.

Format: `deadline TASK_DESCRIPTION /by DEADLINE`

Example:
`deadline finish essay /by Mon 9pm` Adds a task "finish essay" with the deadline "Mon 9pm" to the task list.
```
____________________________________________________________
 Got it. I've added this task:
  [D][ ] finish essay (by: Mon 9pm)
 Now you have 2 tasks in the list.
____________________________________________________________
```

### Adding an event: `event`

Adds an event to the task list.

Format: `event TASK_DESCRIPTION /at DATE`

Example:
`event school dance /at Jan 28th 7-9pm` Adds an event "school dance" with the date "Jan 28th 7-9pm" to the task list.
```
____________________________________________________________
 Got it. I've added this task:
  [E][ ] school dance (at: Jan 28th 7-9pm)
 Now you have 3 tasks in the list.
____________________________________________________________
```

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

Example:
`list`
```
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] read book
 2.[D][ ] finish essay (by: Mon 9pm)
 3.[E][ ] school dance (at: Jan 28th 7-9pm)
____________________________________________________________
```

### Checking off a task: `done`

Marks a task as done.

Format: `done TASK_NUMBER`

Example:
`done 2` Marks the second task "finish essay" in the task list as done.
```
____________________________________________________________
 Nice! I've marked this task as done:
  [D][X] finish essay (by: Mon 9pm)
____________________________________________________________
```

### Finding a task by keyword: `find`

Finds tasks with a description that contain the keyword.

Format: `find KEYWORD`

Example:
`find school` Finds tasks with a description that contain the keyword "school".
```
____________________________________________________________
 Here are the matching tasks in your list:
 1.[E][ ] school dance (at: Jan 28th 7-9pm)
____________________________________________________________
```

### Deleting a task: `delete`

Deletes a task in the task list.

Format: `delete TASK_NUMBER`

Example:
`delete 1` Deletes the first task in the task list.
```
____________________________________________________________
 Noted. I've removed this task:
  [T][ ] read book
 Now you have 2 tasks in the list.
____________________________________________________________
```

### Exiting the program: `bye`

Exits the program.

Format: `bye`

Example: `bye`

```
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
```

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: {your answer here}

## Command Summary

| Command | Format |
| ------------ | ------------- |
| Todo | `todo TASK_DESCRIPTION` |
| Deadline | `deadline TASK_DESCRIPTION /by DEADLINE` |
| Event | `event TASK_DESCRIPTION /at DATE` |
| List | `list` |
| Done | `done TASK_NUMBER` |
| Find | `find KEYWORD` |
| Delete | `delete TASK_NUMBER` |
| Bye | `bye` |