# User Guide

## Introduction

**ConTech** is a **Command Line Interface (CLI) application for managing computing-related contacts**. 
If you are a computing professional that often uses the command line for work, **ConTech** can get your contact 
management tasks done faster than any traditional GUI app. 

With our `add`, `edit`, `view`, and many more [features](#features) specifically set for computing-related contacts 
such as saving a colleague's GitHub username, **ConTech** aims to improve your efficiency at work by cutting down the
time required to look up for various usernames when working with colleagues. 

* **[Quick Start](#start)**
* **[Features](#features)**
    * **[Viewing help : `help`](#help)**
    * **[Adding a contact : `add`](#add)**
    * **[Listing all contacts : `list`](#list)**
    * **[Viewing a contact : `view`](#view)**
    * **[Viewing personal contact : `me`](#me)**
    * **[Deleting a contact : `rm`](#delete)**
    * **[Editing a contact : `edit`](#edit)**
    * **[Searching for a contact : `search`](#search)**
    * **[Importing a contact : `import`](#import)**
    * **[Exiting the program : `exit`](#exit)**
    
* **[FAQ](#faq)**
* **[Command Summary](#summary)**

<a name="start"></a>
## Quick Start

1. Ensure that you have Java `11` or above installed in your Computer.
1. Download the latest version of `contech.jar` from [here](https://github.com/AY2122S1-CS2113T-T09-1/tp/releases).
1. Copy the `contech.jar` file to the folder you want to use as the _home folder_ for **ConTech**.
1. Open your desired _Command Line Interface_ from the folder with `contech.jar` and enter the following code: 
`java -jar contech.jar`.
1. Type a command in the command box and press _Enter_ to execute it. e.g., typing `help` and pressing _Enter_ will 
   display the help message. <br>
   Some example commands you can try:
   * `add -n Alex Lee -g alexlee -e alex.lee@contech.sg -te alexl33`: Adds a contact named `Alex Lee` 
     to the ConTech Book.
   * `list`: Lists all contacts.
   * `view 1`: Displays all the details of the contact at index `1`.   
   * `rm 1`: Deletes the contact at index `1` in the list.
   * `exit`: Exits the program.

1. Refer to the [Features](#features) below for details of each command.

<a name="features"></a>
## Features 

<a name="help"></a>
### Viewing help: `help`
Displays a help message explaining what commands are available and each input format.

Usage: `help`

Expected outcome of usage:

```
help
____________________________________________________________
add: ConTech adds the specified contact with provided parameters.
 Parameters: -n NAME -g GITHUB -e EMAIL -te TELEGRAM -l LINKEDIN -tw TWITTER
 Note: Parameters need not be in order and are optional except for NAME.
 Example: add -n John Doe -g johndoecoder -e john@email.com -te johndoe

list: Displays the name of all saved contacts.
 Example: list

edit: Edit any parameter in an existing contact.
 Parameters: INDEX -n NAME -g GITHUB -e EMAIL -te TELEGRAM -l LINKEDIN -tw TWITTER
 Note: Parameters need not be in order and are optional except for INDEX.
       "me" is used as the INDEX for personal contact.
 Examples: edit 1 -e john.doe@email.com
           edit me -e john.doe@email.com

view: Displays all details for index specified contact.
 Parameter: INDEX
 Note: Index starts from 0.
 Example: view 2

me: Displays all details for personal contact.
 Example: me

rm: Deletes the index specified contact.
 Parameter: INDEX
 Note: Index starts from 0.
       "-rf" is used as the INDEX to delete all contacts.
 Examples: rm 0
           rm -rf

search: Search for a contact containing a specified query.
 Note: Flags may be used to specify a detail type to search.
       If no flag is specified, contact name is searched by default.
 Example: search -g QUERY

help: Displays application usage instructions.
 Example: help

import: Imports contacts from a CSV Text File.
 Note: Please ensure that data is saved in data/import.txt
 Example: import
____________________________________________________________
```

<a name="add"></a>
### Adding a contact: `add`

<a name="list"></a>
### Listing all contacts: `list`

<a name="view"></a>
### Viewing a contact: `view`
Displays all the details saved to a contact in the ConTech Book.

Usage: `view INDEX`
* Displays all the details of the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index must be within the range of indexes displayed in the contact list _(zero-based)_.

Expected outcome of usage:
```
view 0
____________________________________________________________
0. Alex Lee
   Github:   github.com/alexlee
   Email:    alex.lee@contech.sg
   Telegram: t.me/alexl33
____________________________________________________________
```
<a name="me"></a>
### Viewing personal contact: `me`
Displays all the details saved to the personal contact in the ConTech Book.

Usage: `me`
* Displays all the details of the personal contact.

Expected outcome of usage:
```
me
____________________________________________________________
Name:     Marcus Bo
Github:   github.com/marcusbohre
Email:    marcus.bo@contech.sg
Telegram: t.me/m4rcusb0
____________________________________________________________
```

<a name="delete"></a>
### Deleting a contact: `rm`
Removes the contact with a specified index from the ConTech Book. Before any deletion, ConTech Book will
display the details of the specified contact to be deleted, and prompt user to confirm deletion.

Usages:

`rm INDEX`
* Deletes the contact with the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index must be within the range of indexes displayed in the contact list _(zero-based)_.
  
`rm -rf`
* Deletes all contacts in the ConTech Book

Expected outcome of usages:
* Remove selected contact:
```
rm 0
____________________________________________________________
Delete this contact?  (y/n)
0. Alex Lee
Github:   github.com/alexlee
Email:    alex.lee@contech.sg
Telegram: t.me/alex133
____________________________________________________________
```
```
y
____________________________________________________________
ConTech has removed the specified contact: Alex Lee
You now have 2 contact(s).
____________________________________________________________
```

* Remove all contacts:

```
rm -rf
____________________________________________________________
Delete all of your contacts?  (y/n)
____________________________________________________________
```
```
y
____________________________________________________________
ConTech has removed all 2 of your contact(s).
____________________________________________________________
```

<a name="edit"></a>
### Editing a contact : `edit`

<a name="search"></a>
### Searching for a contact: `search`

<a name="me"></a>
### Searching for a contact: `search`

<a name="import"></a>
### Importing contacts: `import`

<a name="exit"></a>
### Exiting the program: `exit`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}


## <a name="summary"></a>Command Summary

Action | Format | Examples
--------|------------|---------
|  |
|  |
|  |



--------------------------------------------------------------------------------------------------------------------

